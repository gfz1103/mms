package com.buit.mms.antimi.service;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.buit.commons.BaseException;
import com.buit.commons.BaseManagerImp;
import com.buit.commons.SysUser;
import com.buit.constans.TableName;
import com.buit.mms.antimi.dao.OpCfKjywsqjlDao;
import com.buit.mms.antimi.model.OpCfKjywsqjl;
import com.buit.mms.antimi.request.AmqcKjywsysqMzSaveReq;
import com.buit.mms.antimi.request.AmqcKjywsysqypReq;
import com.buit.mms.antimi.request.OpCfKjywsqjlReq;
import com.buit.mms.antimi.status.ErrorStatus;
import com.buit.mms.antimi.status.Status;
import com.buit.system.response.SysUserByKjywResp;
import com.buit.system.service.HrPersonnelService;
import com.buit.utill.BeanUtil;
import com.buit.utill.RedisFactory;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.hibernate.validator.constraints.EAN;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

/**
 * 门诊_处方_抗菌药物申请记录<br>
 * @author 朱智
 */
@Service
public class OpCfKjywsqjlSer extends BaseManagerImp<OpCfKjywsqjl,Integer> {

    static final Logger logger = LoggerFactory.getLogger(OpCfKjywsqjlSer.class);

    @Autowired
    private OpCfKjywsqjlDao opCfKjywsqjlDao;
    @Autowired
    private AmqcKjywsysqSer amqcKjywsysqSer;
    @Autowired
    private RedisFactory redisFactory;
    @DubboReference
    private HrPersonnelService hrPersonnelService;

    @Override
    public OpCfKjywsqjlDao getEntityMapper(){
        return opCfKjywsqjlDao;
    }

    /**
     * 新增申请抗菌药物
     * @param opCfKjywsqjl
     * @param user
     */
    @Transactional(rollbackFor = Exception.class)
    public void add(OpCfKjywsqjlReq opCfKjywsqjl, SysUser user) {
        //0.判断当前药品的申请单是否已经生成
        judgeIsExit(opCfKjywsqjl);

        //1.生成抗菌药物申请单
        Integer sqdh = null;
        if (Status.Gkfs.USE_APPLY.getCode().equals(opCfKjywsqjl.getGkfs())){
            //管控方式为 3使用申请时生成申请单
            sqdh = amqcKjywsysqSer.saveMzAntibiotics(BeanUtil.toBean(opCfKjywsqjl, AmqcKjywsysqMzSaveReq.class), user);
        }else if (Status.Gkfs.DOCTOR_GRANT.getCode().equals(opCfKjywsqjl.getGkfs())){
            //管控方式为 2上级授权需要验证上级的用户名和密码
            authentication(opCfKjywsqjl.getLoginName(),opCfKjywsqjl.getPassWord());
        }else if (Status.Gkfs.USE_REASON.getCode().equals(opCfKjywsqjl.getGkfs())){
            //管控方式为 1使用理由需要医生填写使用理由
            if (StrUtil.isBlank(opCfKjywsqjl.getSyly())){
                throw BaseException.create(ErrorStatus.ERROR_MZCFKJYWSQ_0005.getErrorCode());
            }
        }
        //2.门诊_处方_抗菌药物申请记录插入数据
        for(AmqcKjywsysqypReq yp : opCfKjywsqjl.getYpList()){
            OpCfKjywsqjl sqjl = new OpCfKjywsqjl();
            sqjl.setId(redisFactory.getTableKey(TableName.DB_NAME, TableName.OP_CF_KJYWSQJL));
            sqjl.setYpxh(yp.getYpxh());
            sqjl.setYpmc(yp.getYpmc());
            sqjl.setTs(yp.getSysj());
            sqjl.setYpyf(yp.getYpyf());//2020/12/9 用法（给药途径）
            sqjl.setSqrq(new Date(System.currentTimeMillis()));
            sqjl.setSqdh(sqdh);
            if (Status.Gkfs.USE_APPLY.getCode().equals(opCfKjywsqjl.getGkfs())){
                sqjl.setZt(Status.KjywsqjlZt.NEW_ADD.getCode());
            }else {
                sqjl.setZt(Status.KjywsqjlZt.CHECKES.getCode());
            }
            sqjl.setGkfs(opCfKjywsqjl.getGkfs());
            sqjl.setJzxh(opCfKjywsqjl.getJzxh());
            sqjl.setLoginname(opCfKjywsqjl.getLoginName());
            sqjl.setPassword(opCfKjywsqjl.getPassWord());
            sqjl.setUsereason(opCfKjywsqjl.getUsereason());
            opCfKjywsqjlDao.insert(sqjl);
        }
    }

    /**
     * 删除抗菌药物申请
     * @param id
     * @param user
     */
    @Transactional(rollbackFor = Exception.class)
    public void  delete(Integer id,SysUser user){
        OpCfKjywsqjl sqjl = opCfKjywsqjlDao.getById(id);
        if(sqjl !=null && sqjl.getZt().intValue() > 0 ){
            throw BaseException.create("ERROR_MZCFKJYWSQ_0008");
        }
        //删除门诊抗菌药物表
        opCfKjywsqjlDao.deleteById(id);
        //删除抗菌药物表医技其关联表
        if (sqjl.getSqdh() != null){
            amqcKjywsysqSer.removeAntibiotics(sqjl.getSqdh(),user);
        }
    }

    /**
     * 判断药品申请是否已经存在
     * @param opCfKjywsqjl
     */
    private void judgeIsExit(OpCfKjywsqjlReq opCfKjywsqjl){
        if (CollUtil.isEmpty(opCfKjywsqjl.getYpList())){
            throw BaseException.create(ErrorStatus.ERROR_MZCFKJYWSQ_0006.getErrorCode());
        }
        Integer ypxh = opCfKjywsqjl.getYpList().get(0).getYpxh();
        int isExit = opCfKjywsqjlDao.selectIsExit(ypxh, opCfKjywsqjl.getJzxh());
        if (isExit > 0){
            throw BaseException.create(ErrorStatus.ERROR_MZCFKJYWSQ_0007.getErrorCode());
        }
    }

    /**
     * 查询有抗菌药物权限的医生列表
     * @param jgid
     * @param kjywdj
     * @return
     */
    public List<SysUserByKjywResp> queryDocListByKjyw(Integer jgid,String kjywdj){
        return hrPersonnelService.queryByKjyw(jgid, kjywdj);
    }

    /**
     * 管控方式为2时，验证用户是否正确
     * @param loginName
     * @param passWord
     */
    private void authentication(String loginName,String passWord){
        //判断必填
        if (StrUtil.isBlank(loginName)){
            throw BaseException.create(ErrorStatus.ERROR_MZCFKJYWSQ_0003.getErrorCode());
        }
        if (StrUtil.isBlank(passWord)){
            throw BaseException.create(ErrorStatus.ERROR_MZCFKJYWSQ_0004.getErrorCode());
        }
        //根据用户名取用户ID
        Integer personId = hrPersonnelService.getPersonId(loginName);
        if (personId == null){
            //未查询到当前用户
            throw BaseException.create(ErrorStatus.ERROR_MZCFKJYWSQ_0001.getErrorCode());
        }
        //比对用户的密码是否相符
        boolean checkPassWd = hrPersonnelService.checkPassWd(personId, passWord);
        if (!checkPassWd){
            //用户密码输入错误
            throw BaseException.create(ErrorStatus.ERROR_MZCFKJYWSQ_0002.getErrorCode());
        }
    }
}

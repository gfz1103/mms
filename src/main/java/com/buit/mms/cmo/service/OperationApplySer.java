package com.buit.mms.cmo.service;

import cn.hutool.json.JSONUtil;
import com.buit.cis.dctwork.request.CisHzyzSssqApiReq;
import com.buit.cis.dctwork.service.CisHzyzService;
import com.buit.commons.SysUser;
import com.buit.constans.TableName;
import com.buit.mms.cmo.dao.CmoZdssDao;
import com.buit.mms.cmo.dao.FeeYlsfxmDao;
import com.buit.mms.cmo.dao.OptSssqDao;
import com.buit.mms.cmo.dto.DoctorDicDto;
import com.buit.mms.cmo.model.CmoZdss;
import com.buit.mms.cmo.model.OptSssq;
import com.buit.mms.cmo.model.SsJyjg;
import com.buit.mms.cmo.request.OperationSelectorReq;
import com.buit.mms.cmo.request.QueryOperationApplyReq;
import com.buit.mms.cmo.request.SaveOperationApplyReq;
import com.buit.mms.cmo.response.OperationApplyResp;
import com.buit.mms.cmo.response.OperationSelectorResp;
import com.buit.op.request.IOptSssqSaveYjReq;
import com.buit.op.response.MpiBrda;
import com.buit.op.service.OpBrzdService;
import com.buit.op.service.OpMpiBrdaService;
import com.buit.op.service.OpYjcf01Service;
import com.buit.system.response.DoctorDicResp;
import com.buit.system.service.FeeYlsfxmOutSer;
import com.buit.system.service.HrPersonnelService;
import com.buit.utill.BeanUtil;
import com.buit.utill.RedisFactory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/11/6 10:56
 */
@Service
public class OperationApplySer {

    static final Logger log = LoggerFactory.getLogger(OperationApplySer.class);

    @Autowired
    OptSssqDao optSssqDao;
    @Autowired
    RedisFactory redisFactory;
    @Autowired
    CmoZdssDao cmoZdssDao;
    @Autowired
    FeeYlsfxmDao feeYlsfxmDao;
    @DubboReference
    CisHzyzService cisHzyzService;
    @DubboReference
    HrPersonnelService hrPersonnelService;
    @DubboReference
    OpYjcf01Service opYjcf01Service;

    public Integer save(SaveOperationApplyReq req, SysUser user) {
        log.info("?????????????????????[{},{}]", JSONUtil.toJsonStr(req),JSONUtil.toJsonStr(user));

        OptSssq sssq = BeanUtil.toBean(req,OptSssq.class);
        sssq.setCzgh(user.getUserId());
        sssq.setJgid(user.getHospitalId());
        if(req.getTsss()!=null){
            sssq.setZdssbz(0);
        }
        String op;
        if(req.getSqdh()!=null&&req.getSqdh()!=0){
            op="update";
            OptSssq oldSssq = optSssqDao.getById(req.getSqdh());
            sssq.setSpbz(oldSssq.getSpbz());
            sssq.setZfbz(oldSssq.getZfbz());
        }else{
            op="create";
            sssq.setSpbz(0);
            sssq.setZfbz(0);
            sssq.setSqdh(redisFactory.getTableKey(TableName.DB_NAME,TableName.OPT_SSSQ));
        }
        optSssqDao.save(sssq);
        SsJyjg ssjyjg = BeanUtil.toBean(sssq, SsJyjg.class);
        optSssqDao.saveJyjg(ssjyjg);
        if(req.getSqlx()==2){
            CisHzyzSssqApiReq yzReq = new CisHzyzSssqApiReq();
            yzReq.setZyh(req.getZyh());
            yzReq.setOpStatus(op);
            yzReq.setSqdh(sssq.getSqdh());
            yzReq.setSqrq(sssq.getSqrq());
            yzReq.setSsmc(sssq.getSsmc());
            cisHzyzService.saveZySssqYzInfo(yzReq,user);
        }else{
            IOptSssqSaveYjReq yjReq = new IOptSssqSaveYjReq();
            yjReq.setBrid(req.getBrid());
            yjReq.setBrxm(req.getBrxm());
            yjReq.setJgid(user.getHospitalId());
            yjReq.setKsdm(req.getSqks());
            yjReq.setYsdm(req.getSqys().toString());
            yjReq.setJzxh(req.getJzxh());
            yjReq.setJzkh(req.getJzkh());
            yjReq.setJzlsh(req.getJzlsh());
            yjReq.setXmlx(6);
            yjReq.setSssqid(sssq.getSqdh());
            yjReq.setYlxh(req.getSsnm());
            yjReq.setZxks(req.getSsks());
            yjReq.setZxys(req.getSsys().toString());
            opYjcf01Service.sssqSaveYj(yjReq);
        }
        return sssq.getSqdh();
    }

    public OperationApplyResp get(Integer sqdh) {
        log.info("???????????????????????????[{}]",sqdh);
        OperationApplyResp resp = optSssqDao.getDetailBySqdh(sqdh);
        Long year = ChronoUnit.YEARS.between(resp.getCsny().toLocalDateTime(), LocalDateTime.now());
        resp.setBrnl(year+"???");
        return resp;
    }

    public void enable(Integer sqdh) {
        log.info("??????/???????????????????????????[{}]",sqdh);
        optSssqDao.updateZfbzBySqdh(sqdh);

    }

    public Integer checkMajor(Integer ssnm) {
        CmoZdss cmoZdss = cmoZdssDao.getByfyxhId(ssnm);
        return cmoZdss==null?0:1;
    }

    public List<DoctorDicResp> queryOperator(Integer ssdj, String pydm, Integer jgid) {
        log.info("?????????????????????????????????[{},{}]",ssdj,jgid);

        return hrPersonnelService.queryBySsdj(ssdj,pydm, jgid);
    }


    public List<DoctorDicResp> queryCheckOperator(Integer ssdj, String pydm, Integer jgid) {
        log.info("???????????????????????????????????????");
        return hrPersonnelService.queryCheckBySsdj(ssdj,pydm, jgid);
    }

    public PageInfo<OperationSelectorResp> selector(OperationSelectorReq req) {
        log.info("???????????????[{}]",JSONUtil.toJsonStr(req));
        PageInfo<OperationSelectorResp> resp = PageHelper.startPage(req.getPageNum(),req.getPageSize())
                .doSelectPageInfo(()->feeYlsfxmDao.ssxmSelector(req.getPydm()));
        return resp;
    }

    public PageInfo<OperationApplyResp> list(QueryOperationApplyReq req, Integer userId) {
        req.setSqys(userId);
        log.info("??????????????????[{}]",JSONUtil.toJsonStr(req));
        if(req.getSpbz()==-1){
            req.setSpbz(null);
        }
        PageInfo<OperationApplyResp> resp = PageHelper.startPage(req.getPageNum(),req.getPageSize())
                .doSelectPageInfo(()->optSssqDao.queryList(req));
        resp.getList().forEach(s->{
            Long year = ChronoUnit.YEARS.between(s.getCsny().toLocalDateTime(), LocalDateTime.now());
            s.setBrnl(year+"???");
        });
        return resp;
    }

    public String queryYszc(Integer ysgh) {
        log.info("??????????????????[{}]",ysgh);
        return optSssqDao.queryYszc(ysgh);
    }
}

package com.buit.mms.followup.service;

import cn.hutool.core.date.DateUtil;
import com.buit.commons.BaseException;
import com.buit.commons.BaseManagerImp;
import com.buit.commons.EntityDao;
import com.buit.mms.followup.dao.FollowUpRecordDao;
import com.buit.mms.followup.dao.FollowUpSzDao;
import com.buit.mms.followup.model.FollowUpRecord;
import com.buit.mms.followup.model.FollowUpSz;
import com.buit.utill.ParamUtil;
import com.buit.utill.PinyinUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * 随访配置service<br>
 * @author tianjunhao
 */
@Service
public class FollowUpSzSer extends BaseManagerImp<FollowUpSz,Integer> {

    @Autowired
    FollowUpSzDao followUpSzDao;
    @Autowired
    FollowUpRecordDao followUpRecordDao;

    @Override
    public EntityDao<FollowUpSz, Integer> getEntityMapper() {
        return followUpSzDao;
    }

    public void add(FollowUpSz followUpSz){
        FollowUpSz tempFollowUpSz = new FollowUpSz();
        tempFollowUpSz.setNrlx(followUpSz.getNrlx());
        tempFollowUpSz.setSznr(followUpSz.getSznr());
        if(followUpSzDao.findByEntity(tempFollowUpSz).size() > 0){
            throw BaseException.create("ERROR_FOLLOWUP_SZ_0001");
        }else {
            followUpSz.setPydm(PinyinUtils.getSimplePinYin(followUpSz.getSznr()));
            followUpSz.setCjsj(Timestamp.valueOf(DateUtil.now()));
            followUpSzDao.insert(followUpSz);
        }
    }

    public void remove(int id){
        Integer count = followUpRecordDao.countUsed(id);
        if(count > 0){
            throw BaseException.create("ERROR_FOLLOWUP_SZ_0002");
        }
        followUpSzDao.deleteById(id);
    }

}

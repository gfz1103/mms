package com.buit.mms.antimi.service;


import com.buit.commons.BaseManagerImp;
import com.buit.mms.antimi.dao.AmqcTsjkjywhzzjDao;
import com.buit.mms.antimi.model.AmqcTsjkjywhzzj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 特殊级抗菌药物会诊专家表<br>
 * @author GONGFANGZHOU
 */
@Service
public class AmqcTsjkjywhzzjSer extends BaseManagerImp<AmqcTsjkjywhzzj,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(AmqcTsjkjywhzzjSer.class);
    
    @Autowired
    private AmqcTsjkjywhzzjDao amqcTsjkjywhzzjDao;
    
    @Override
    public AmqcTsjkjywhzzjDao getEntityMapper(){        
        return amqcTsjkjywhzzjDao;
    }
    
}

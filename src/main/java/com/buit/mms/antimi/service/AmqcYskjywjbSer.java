package com.buit.mms.antimi.service;


import com.buit.commons.BaseManagerImp;
import com.buit.mms.antimi.dao.AmqcYskjywjbDao;
import com.buit.mms.antimi.model.AmqcYskjywjb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 医生抗菌药物级别表<br>
 * @author GONGFANGZHOU
 */
@Service
public class AmqcYskjywjbSer extends BaseManagerImp<AmqcYskjywjb,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(AmqcYskjywjbSer.class);
    
    @Autowired
    private AmqcYskjywjbDao amqcYskjywjbDao;
    
    @Override
    public AmqcYskjywjbDao getEntityMapper(){        
        return amqcYskjywjbDao;
    }
    
}

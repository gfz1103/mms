package com.buit.mms.antimi.service;


import com.buit.commons.BaseManagerImp;
import com.buit.mms.antimi.dao.AmqcYskjywsylyDao;
import com.buit.mms.antimi.model.AmqcYskjywsyly;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 医生抗菌药物使用理由表<br>
 * @author GONGFANGZHOU
 */
@Service
public class AmqcYskjywsylySer extends BaseManagerImp<AmqcYskjywsyly,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(AmqcYskjywsylySer.class);
    
    @Autowired
    private AmqcYskjywsylyDao amqcYskjywsylyDao;
    
    @Override
    public AmqcYskjywsylyDao getEntityMapper(){        
        return amqcYskjywsylyDao;
    }
    
}

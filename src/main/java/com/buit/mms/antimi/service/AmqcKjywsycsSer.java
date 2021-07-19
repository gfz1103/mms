package com.buit.mms.antimi.service;


import com.buit.commons.BaseManagerImp;
import com.buit.mms.antimi.dao.AmqcKjywsycsDao;
import com.buit.mms.antimi.model.AmqcKjywsycs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 抗菌药物使用参数<br>
 * @author GONGFANGZHOU
 */
@Service
public class AmqcKjywsycsSer extends BaseManagerImp<AmqcKjywsycs,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(AmqcKjywsycsSer.class);
    
    @Autowired
    private AmqcKjywsycsDao amqcKjywsycsDao;
    
    @Override
    public AmqcKjywsycsDao getEntityMapper(){        
        return amqcKjywsycsDao;
    }
    
}

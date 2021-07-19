package com.buit.mms.antimi.service;


import com.buit.commons.BaseManagerImp;
import com.buit.mms.antimi.dao.AmqcKjywsyhzksysDao;
import com.buit.mms.antimi.model.AmqcKjywsyhzksys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 抗菌药物使用会诊科室医生表<br>
 * @author GONGFANGZHOU
 */
@Service
public class AmqcKjywsyhzksysSer extends BaseManagerImp<AmqcKjywsyhzksys,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(AmqcKjywsyhzksysSer.class);
    
    @Autowired
    private AmqcKjywsyhzksysDao amqcKjywsyhzksysDao;
    
    @Override
    public AmqcKjywsyhzksysDao getEntityMapper(){        
        return amqcKjywsyhzksysDao;
    }
    
}

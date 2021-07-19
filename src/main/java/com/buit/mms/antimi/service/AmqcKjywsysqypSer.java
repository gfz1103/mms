package com.buit.mms.antimi.service;


import com.buit.commons.BaseManagerImp;
import com.buit.mms.antimi.dao.AmqcKjywsysqypDao;
import com.buit.mms.antimi.model.AmqcKjywsysqyp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 抗菌药物使用申请药品表<br>
 * @author GONGFANGZHOU
 */
@Service
public class AmqcKjywsysqypSer extends BaseManagerImp<AmqcKjywsysqyp,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(AmqcKjywsysqypSer.class);
    
    @Autowired
    private AmqcKjywsysqypDao amqcKjywsysqypDao;
    
    @Override
    public AmqcKjywsysqypDao getEntityMapper(){        
        return amqcKjywsysqypDao;
    }
    
}

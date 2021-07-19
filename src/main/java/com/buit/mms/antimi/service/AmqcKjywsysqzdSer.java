package com.buit.mms.antimi.service;


import com.buit.commons.BaseManagerImp;
import com.buit.mms.antimi.dao.AmqcKjywsysqzdDao;
import com.buit.mms.antimi.model.AmqcKjywsysqzd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 抗菌药物使用申请诊断表<br>
 * @author GONGFANGZHOU
 */
@Service
public class AmqcKjywsysqzdSer extends BaseManagerImp<AmqcKjywsysqzd,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(AmqcKjywsysqzdSer.class);
    
    @Autowired
    private AmqcKjywsysqzdDao amqcKjywsysqzdDao;
    
    @Override
    public AmqcKjywsysqzdDao getEntityMapper(){        
        return amqcKjywsysqzdDao;
    }
    
}

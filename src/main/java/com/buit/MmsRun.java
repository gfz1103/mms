package com.buit;

import com.buit.config.EnableLocked;
import com.buit.config.EnableMessage;
import com.buit.config.swagger2.ExportBeanConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.buit.utill.SpringContextUtil;

/**
* @ClassName: HisRun
* @Description: 启动方法
* @author 神算子
* @date 2020年4月26日 下午3:31:56
 */
@EnableDubbo
@EnableScheduling
@SpringBootApplication
@EnableLocked
@EnableRabbit
@EnableMessage
@Import(ExportBeanConfig.class)
public class MmsRun {
	public static void main(String[] args) {
		ConfigurableApplicationContext con=SpringApplication.run(MmsRun.class, args);
		SpringContextUtil.setApplicationContext(con);
	}
}
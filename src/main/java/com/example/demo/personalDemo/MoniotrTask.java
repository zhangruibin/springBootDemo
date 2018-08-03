package com.example.demo.personalDemo;

import com.example.service.RuleDao;
import io.swagger.annotations.Scope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.management.monitor.Monitor;


/**
 * Created by zhangrui on 2017/11/28.
 */
@Component("mTask")
@Scope(name = "prototype",description = "prototype")
public class MoniotrTask extends Thread {
    private final static Logger logger= LoggerFactory.getLogger(MoniotrTask.class);
    private Monitor monitor;
    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    @Resource(name = "greaterDaoImpl")
    private RuleDao greaterDaoImpl;

    @Override
    public void run() {
        logger.info("线程:"+Thread.currentThread().getName()+"运行中.....");
    }
}

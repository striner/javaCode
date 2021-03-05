/**
 * @author striner
 * @create 2018/5/13 17:59
 */
package com.striner.spring_boot.listener;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

//实现ApplicationContextInitializer
//监听IOC容器的启动
public class MyApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext){
        System.out.println("ApplicationContextInitializer....initialize..." + applicationContext);
    }
}

/**
 * @author striner
 * @create 2018/5/13 18:12
 */
package com.striner.spring_boot.listener;

import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationRunner implements org.springframework.boot.ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("MyApplicationRunner....run...");
    }
}

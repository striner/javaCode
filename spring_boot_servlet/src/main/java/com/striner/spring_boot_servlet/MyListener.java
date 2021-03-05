/**
 * @author striner
 * @create 2018/5/11 20:43
 * @Description: listener
 */
package com.striner.spring_boot_servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("MyServlet init ...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("MyServlet destroy ...");
    }
}

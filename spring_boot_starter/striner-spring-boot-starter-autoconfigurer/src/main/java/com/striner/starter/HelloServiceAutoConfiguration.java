/**
 * @author striner
 * @create 2018/5/13 19:45
 */
package com.striner.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration   //自动配置类
@ConditionalOnWebApplication  //web应用下生效
@EnableConfigurationProperties(HelloProperties.class)    //让属性文件生效
public class HelloServiceAutoConfiguration {

    @Autowired
    HelloProperties helloProperties;

    @Bean  //加入到容器中
    public HelloService helloService() {
        HelloService service = new HelloService();
        service.setHelloProperties(helloProperties);
        return service;
    }
}

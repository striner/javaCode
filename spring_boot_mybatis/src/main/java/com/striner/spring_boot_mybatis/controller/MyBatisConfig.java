/**
 * @author striner
 * @create 2018/5/12 21:42
 * @Description: the config of MyBatis
 */
package com.striner.spring_boot_mybatis.controller;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class MyBatisConfig {

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(Configuration configuration) {
                configuration.setMapUnderscoreToCamelCase(true);   //开启驼峰命名规则
            }
        };
    }
}

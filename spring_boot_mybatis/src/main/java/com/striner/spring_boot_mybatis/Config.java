/**
 * @author striner
 * @create 2018/5/12 19:42
 * @Description: DruidConfig
 */
package com.striner.spring_boot_mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class Config {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }


    //配置Druid的监控
    //1.配置一个管理后台的Servlet
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

        //配置初始化参数
        Map<String, String> initParams = new HashMap<>();
        initParams.put("allow", "localhost");  //允许谁来访问,value不写表允许所有
        initParams.put("loginUsername", "root");
        initParams.put("loginPassWord", "mulin123");

        bean.setInitParameters(initParams);
        return bean;
    }
    //2.配置一个监控的filter
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,/druid/*");   //不拦截
        bean.setInitParameters(initParams);

        //拦截请求
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}


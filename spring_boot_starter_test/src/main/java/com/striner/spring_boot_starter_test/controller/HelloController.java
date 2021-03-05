/**
 * @author striner
 * @create 2018/5/13 20:29
 */
package com.striner.spring_boot_starter_test.controller;

import com.striner.starter.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @GetMapping("/hello")
    public String hello(){
        return helloService.sayHello("xyz");
    }
}

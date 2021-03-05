/**
 * @author striner
 * @create 2018/5/13 16:08
 * @Description: UserController
 */
package com.striner.spring_boot_data_jpa.controller;

import com.striner.spring_boot_data_jpa.entity.User;
import com.striner.spring_boot_data_jpa.repository.UserRepository;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.GeneratedValue;

@RestController
public class UserController{

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        User user = userRepository.findOne(id);
        return user;
    }

    @GetMapping("/user")
    public User insertUser(User user){
        return userRepository.save(user);
    }
}

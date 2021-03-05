/**
 * @author striner
 * @create 2018/5/13 16:01
 * @Description: repository
 */
package com.striner.spring_boot_data_jpa.repository;

import com.striner.spring_boot_data_jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//继承JPARepository来完成对数据库的操作
public interface UserRepository extends JpaRepository<User, Integer> {

}

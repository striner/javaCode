/**
 * @author striner
 * @create 2018/5/13 15:41
 * @Description: UserBean
 */
package com.striner.spring_boot_data_jpa.entity;

import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity    //告诉JPA这是一个实体类
@Table(name = "tb1_user")   //@Table来指定和哪个数据表对应,如果省略,表名就是user
public class User {

    @Id   //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //自增主键
    private Integer id;

    @Column(name = "lastName", length = 50)   //这是和数据表对应的一个列
    private String lastName;
    @Column  //省略 默认列表就是属性名
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

/**
 * @author striner
 * @create 2018/5/12 20:26
 * @Description: Topic数据的增删改查
 */
package com.striner.spring_boot_mybatis.mapper;

import com.striner.spring_boot_mybatis.bean.Topic;
import org.apache.ibatis.annotations.*;

@Mapper    //指定这是一个操作数据库的mapper
//@MapperScan(value = "com.striner.spring_boot_mybatis.mapper")   表示com.striner.spring_boot_mybatis.mapper下的所有类都会自动看做@Mapper批量扫描
public interface TopicMapper {

    @Select("SELECT * FROM t_topic WHERE tid=#{tid}")
    public Topic getTopicBytid(Integer tid);

    @Delete("DELETE FROM t_topic WHERE tid=#{tid}")
    public int delectTopicByTid(Integer tid);

    @Options(useGeneratedKeys = true, keyProperty = "tid")   //是不是使用自动生成的主键,那个属性是用来封装属性的
    @Insert("INSERT INTO t_topic(title) VALUES (#{title}")
    public int insertTopic(Topic topic);

    @Update("UPDATE t_topic SET ipAddr=#{ipAddr} WHERE tid=#{tid}")
    public int updateTopic(Topic topic);
}

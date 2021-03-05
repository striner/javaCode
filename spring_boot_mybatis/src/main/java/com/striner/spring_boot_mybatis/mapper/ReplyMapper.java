/**
 * @author striner
 * @create 2018/5/13 14:53
 * @Description: the mapper of reply
 */
package com.striner.spring_boot_mybatis.mapper;

import com.striner.spring_boot_mybatis.bean.Reply;
import org.apache.ibatis.annotations.Select;

public interface  ReplyMapper {

    @Select("SELECT * FROM t_reply WHERE rid=#{rid}")
    public Reply getReplyByRid(Integer rid);
}

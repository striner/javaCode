/**
 * @author striner
 * @create 2018/5/12 20:41
 * @Description: test of TopicMapper
 */
package com.striner.spring_boot_mybatis.controller;

import com.striner.spring_boot_mybatis.bean.Reply;
import com.striner.spring_boot_mybatis.bean.Topic;
import com.striner.spring_boot_mybatis.mapper.ReplyMapper;
import com.striner.spring_boot_mybatis.mapper.TopicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {
    @Autowired
    TopicMapper topicMapper;

    @Autowired
    ReplyMapper replyMappper;

    @GetMapping("/reply/{rid}")
    public Reply  getReply(@PathVariable("rid") Integer rid) {
        return replyMappper.getReplyByRid(rid);
    }

    @GetMapping("/topic/{tid}")
    public Topic getTopic(@PathVariable("tid") Integer tid) {
        return topicMapper.getTopicBytid(tid);
    }

    @GetMapping("/topic")
    public Topic insertTopic(Topic topic) {
        topicMapper.insertTopic(topic);
        return topic;
    }
}

/**
 * @author striner
 * @create 2018/5/12 20:13
 * @Description: the bean of reply
 */
package com.striner.spring_boot_mybatis.bean;

import java.sql.Date;

public class Reply {

    private Integer rid;
    private String replyContent;
    private String ipAddr;
    private Date createDate;
    private Integer tid;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }
}
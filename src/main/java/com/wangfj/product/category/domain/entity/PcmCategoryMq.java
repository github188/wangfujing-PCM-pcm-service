package com.wangfj.product.category.domain.entity;

/**
 * 分类消息同步记录表
 * @Class Name PcmCategoryMq
 * @Author duanzhaole
 * @Create In 2015年7月2日
 */
public class PcmCategoryMq {
    private Long sid;

    private String msgContext;

    private String msgTotalNum;

    private String msgSequence;

    private Integer flag;

    private String item;

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getMsgContext() {
        return msgContext;
    }

    public void setMsgContext(String msgContext) {
        this.msgContext = msgContext == null ? null : msgContext.trim();
    }

    public String getMsgTotalNum() {
        return msgTotalNum;
    }

    public void setMsgTotalNum(String msgTotalNum) {
        this.msgTotalNum = msgTotalNum == null ? null : msgTotalNum.trim();
    }

    public String getMsgSequence() {
        return msgSequence;
    }

    public void setMsgSequence(String msgSequence) {
        this.msgSequence = msgSequence == null ? null : msgSequence.trim();
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item == null ? null : item.trim();
    }
}
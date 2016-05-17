package com.wangfj.product.price.domain.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品现价日志表
 * @Class Name PcmCurrentPriceLog
 * @Author duanzhaole
 * @Create In 2015年7月3日
 */
public class PcmCurrentPriceLog {
    private Long sid;

    /**
     * 专柜商品SID
     */
    private Long shoppeProSid;

    /**
     * old现价
     */
    private BigDecimal oldCurrentPrice;

    /**
     * new现价
     */
    private BigDecimal newCurrentPrice;

    /**
     * 开始时间
     */
    private Date beginTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 操作人
     */
    private String optUser;

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getShoppeProSid() {
        return shoppeProSid;
    }

    public void setShoppeProSid(Long shoppeProSid) {
        this.shoppeProSid = shoppeProSid;
    }

    public BigDecimal getOldCurrentPrice() {
        return oldCurrentPrice;
    }

    public void setOldCurrentPrice(BigDecimal oldCurrentPrice) {
        this.oldCurrentPrice = oldCurrentPrice;
    }

    public BigDecimal getNewCurrentPrice() {
        return newCurrentPrice;
    }

    public void setNewCurrentPrice(BigDecimal newCurrentPrice) {
        this.newCurrentPrice = newCurrentPrice;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOptUser() {
        return optUser;
    }

    public void setOptUser(String optUser) {
        this.optUser = optUser == null ? null : optUser.trim();
    }
}
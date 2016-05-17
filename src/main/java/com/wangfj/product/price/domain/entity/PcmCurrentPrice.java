package com.wangfj.product.price.domain.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品现价表
 * @Class Name PcmCurrentPrice
 * @Author duanzhaole
 * @Create In 2015年7月3日
 */
public class PcmCurrentPrice {
    private Long sid;

    /**
     * 专柜商品sid
     */
    private Long shoppeProSid;

    /**
     * 现价	
     */
    private BigDecimal currentPrice;

    /**
     * 开始时间
     */
    private Date beginTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 标记
     */
    private Integer flag;

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

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
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

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
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
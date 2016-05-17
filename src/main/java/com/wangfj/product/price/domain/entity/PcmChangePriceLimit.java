package com.wangfj.product.price.domain.entity;

import java.math.BigDecimal;
import java.util.Date;

public class PcmChangePriceLimit {
    private Long sid;

    private String shopSid;

    private Integer levelType;

    private BigDecimal levelValue;

    private Integer status;

    private Date createTime;

    private String createName;

    private Date updateTime;

    private String updateName;

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getShopSid() {
        return shopSid;
    }

    public void setShopSid(String shopSid) {
        this.shopSid = shopSid;
    }

    public Integer getLevelType() {
        return levelType;
    }

    public void setLevelType(Integer levelType) {
        this.levelType = levelType;
    }

    public BigDecimal getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(BigDecimal levelValue) {
        this.levelValue = levelValue;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }
}
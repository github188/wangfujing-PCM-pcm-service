package com.wangfj.product.supplier.domain.entity;

import java.util.Date;

public class PcmSupplyShopCode {
    private Long sid;

    private Long shoppeSid;//专柜sid

    private Long supplySid;//供应商sid

    private String promoSaleCode;//促销扣率码

    private Date beginTime;//开始时间

    private Date endTime;//结束时间

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getShoppeSid() {
        return shoppeSid;
    }

    public void setShoppeSid(Long shoppeSid) {
        this.shoppeSid = shoppeSid;
    }

    public Long getSupplySid() {
        return supplySid;
    }

    public void setSupplySid(Long supplySid) {
        this.supplySid = supplySid;
    }

    public String getPromoSaleCode() {
        return promoSaleCode;
    }

    public void setPromoSaleCode(String promoSaleCode) {
        this.promoSaleCode = promoSaleCode == null ? null : promoSaleCode.trim();
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
}
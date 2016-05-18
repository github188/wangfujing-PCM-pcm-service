package com.wangfj.product.maindata.domain.entity;

public class PcmErpProductSupply {
    private Long sid;

    private String supplySid;

    private String erpProductSid;

    private String shopSid;

    private Integer status;

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getSupplySid() {
        return supplySid;
    }

    public void setSupplySid(String supplySid) {
        this.supplySid = supplySid;
    }

    public String getErpProductSid() {
        return erpProductSid;
    }

    public void setErpProductSid(String erpProductSid) {
        this.erpProductSid = erpProductSid;
    }

    public String getShopSid() {
        return shopSid;
    }

    public void setShopSid(String shopSid) {
        this.shopSid = shopSid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
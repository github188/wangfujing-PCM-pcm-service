package com.wangfj.product.brand.domain.entity;

public class PcmShoppeBrandRelation {
    private Long sid;

    private Long shoppeSid;

    private Long brandSid;

    private Integer status;

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

    public Long getBrandSid() {
        return brandSid;
    }

    public void setBrandSid(Long brandSid) {
        this.brandSid = brandSid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
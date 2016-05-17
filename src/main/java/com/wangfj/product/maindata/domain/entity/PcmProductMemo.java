package com.wangfj.product.maindata.domain.entity;

public class PcmProductMemo {
    private Integer sid;

    private String skuSid;

    private String skuInfo;

    private Integer ifDelete;

    private String c1;

    private String c2;

    private String c3;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSkuSid() {
        return skuSid;
    }

    public void setSkuSid(String skuSid) {
        this.skuSid = skuSid;
    }

    public String getSkuInfo() {
        return skuInfo;
    }

    public void setSkuInfo(String skuInfo) {
        this.skuInfo = skuInfo;
    }

    public Integer getIfDelete() {
        return ifDelete;
    }

    public void setIfDelete(Integer ifDelete) {
        this.ifDelete = ifDelete;
    }

    public String getC1() {
        return c1;
    }

    public void setC1(String c1) {
        this.c1 = c1;
    }

    public String getC2() {
        return c2;
    }

    public void setC2(String c2) {
        this.c2 = c2;
    }

    public String getC3() {
        return c3;
    }

    public void setC3(String c3) {
        this.c3 = c3;
    }
}
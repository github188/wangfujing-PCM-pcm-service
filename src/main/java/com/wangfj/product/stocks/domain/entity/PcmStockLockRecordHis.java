package com.wangfj.product.stocks.domain.entity;

import java.util.Date;

public class PcmStockLockRecordHis {
    private Long sid;

    private String saleNo;

    private String billsNo;

    private String shoppeProSid;

    private Long lockSum;

    private Integer lockTypeSid;

    private Date lockTime;

    private String note;

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getSaleNo() {
        return saleNo;
    }

    public void setSaleNo(String saleNo) {
        this.saleNo = saleNo;
    }

    public String getBillsNo() {
        return billsNo;
    }

    public void setBillsNo(String billsNo) {
        this.billsNo = billsNo;
    }

    public String getShoppeProSid() {
        return shoppeProSid;
    }

    public void setShoppeProSid(String shoppeProSid) {
        this.shoppeProSid = shoppeProSid;
    }

    public Long getLockSum() {
        return lockSum;
    }

    public void setLockSum(Long lockSum) {
        this.lockSum = lockSum;
    }

    public Integer getLockTypeSid() {
        return lockTypeSid;
    }

    public void setLockTypeSid(Integer lockTypeSid) {
        this.lockTypeSid = lockTypeSid;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
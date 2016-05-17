package com.wangfj.product.stocks.domain.entity;

public class PcmLockAttribute {
    private Integer sid;

    private String distributedLock;

    private Integer button;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getDistributedLock() {
        return distributedLock;
    }

    public void setDistributedLock(String distributedLock) {
        this.distributedLock = distributedLock;
    }

    public Integer getButton() {
        return button;
    }

    public void setButton(Integer button) {
        this.button = button;
    }

	@Override
	public String toString() {
		return "PcmLockAttribute [sid=" + sid + ", distributedLock=" + distributedLock
				+ ", button=" + button + "]";
	}
    
    
}
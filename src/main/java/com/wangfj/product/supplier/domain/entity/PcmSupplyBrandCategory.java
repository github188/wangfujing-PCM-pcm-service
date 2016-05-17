package com.wangfj.product.supplier.domain.entity;

public class PcmSupplyBrandCategory {
    private Long sid;

    private String categoryErp;

    private String brandErp;

    private Long supplySid;

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getCategoryErp() {
        return categoryErp;
    }

    public void setCategoryErp(String categoryErp) {
        this.categoryErp = categoryErp == null ? null : categoryErp.trim();
    }

    public String getBrandErp() {
        return brandErp;
    }

    public void setBrandErp(String brandErp) {
        this.brandErp = brandErp == null ? null : brandErp.trim();
    }

    public Long getSupplySid() {
        return supplySid;
    }

    public void setSupplySid(Long supplySid) {
        this.supplySid = supplySid;
    }
}
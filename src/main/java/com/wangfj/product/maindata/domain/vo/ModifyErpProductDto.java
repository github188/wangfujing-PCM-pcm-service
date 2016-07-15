package com.wangfj.product.maindata.domain.vo;

import java.util.List;

import com.wangfj.product.maindata.domain.entity.PcmShoppeProduct;

public class ModifyErpProductDto {
    private List<PcmShoppeProduct> list;//专柜商品
    private Long sid;//大码sid

    private Long erpProductSupplySid;//大码供应商关系 sid

    public List<PcmShoppeProduct> getList() {
        return list;
    }

    public void setList(List<PcmShoppeProduct> list) {
        this.list = list;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getErpProductSupplySid() {
        return erpProductSupplySid;
    }

    public void setErpProductSupplySid(Long erpProductSupplySid) {
        this.erpProductSupplySid = erpProductSupplySid;
    }

    @Override
    public String toString() {
        return "ModifyErpProductDto{" +
                "list=" + list +
                ", sid=" + sid +
                ", erpProductSupplySid=" + erpProductSupplySid +
                '}';
    }
}

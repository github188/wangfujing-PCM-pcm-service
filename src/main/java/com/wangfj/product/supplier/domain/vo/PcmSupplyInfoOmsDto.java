package com.wangfj.product.supplier.domain.vo;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * Created by wangxuan on 2016-07-18 0018.
 * OMS查询供应商信息返回dto
 */
public class PcmSupplyInfoOmsDto extends BaseDto {

    private String supplyName;// 供应商名称

    private String shopSid;// 门店编码(组织结构编码)

    private String supplyCode;// 供应商编码

    private String joinSite;// 联营商品客退地点

    public String getSupplyName() {
        return supplyName;
    }

    public void setSupplyName(String supplyName) {
        this.supplyName = supplyName;
    }

    public String getShopSid() {
        return shopSid;
    }

    public void setShopSid(String shopSid) {
        this.shopSid = shopSid;
    }

    public String getSupplyCode() {
        return supplyCode;
    }

    public void setSupplyCode(String supplyCode) {
        this.supplyCode = supplyCode;
    }

    public String getJoinSite() {
        return joinSite;
    }

    public void setJoinSite(String joinSite) {
        this.joinSite = joinSite;
    }
}

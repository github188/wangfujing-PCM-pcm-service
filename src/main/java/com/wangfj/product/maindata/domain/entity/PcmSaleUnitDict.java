package com.wangfj.product.maindata.domain.entity;

import com.wangfj.core.framework.base.entity.BaseEntity;
/**
 * 销售单位字典表
 * @Class Name PcmSaleUnitDict
 * @Author zhangxy
 * @Create In 2015年7月15日
 */
public class PcmSaleUnitDict extends BaseEntity {
    private Long sid;
    /**
     * 销售单位编码
     */
    private String unitCode;
    /**
     * 销售单位名
     */
    private String unitName;

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
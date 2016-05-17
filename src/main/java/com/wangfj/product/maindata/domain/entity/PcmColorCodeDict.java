package com.wangfj.product.maindata.domain.entity;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * 色码表
 * @Class Name PcmColorCodeDict
 * @Author zhangxy
 * @Create In 2015年7月17日
 */
public class PcmColorCodeDict extends BaseEntity {
    private Long sid;
	// 色码
    private String colorCode;
	// 品牌编码
    private String brandCode;
	// 颜色名
    private String colorName;

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

	@Override
	public String toString() {
		return "PcmColorCodeDict [sid=" + sid + ", colorCode=" + colorCode + ", brandCode="
				+ brandCode + ", colorName=" + colorName + "]";
	}

}
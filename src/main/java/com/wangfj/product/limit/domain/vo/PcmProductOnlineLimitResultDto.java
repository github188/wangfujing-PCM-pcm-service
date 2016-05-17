package com.wangfj.product.limit.domain.vo;

import com.wangfj.core.framework.base.dto.BaseDto;

public class PcmProductOnlineLimitResultDto extends BaseDto {

	/**
	 * @Field long serialVersionUID
	 */
	private static final long serialVersionUID = -1512914282686909220L;

	private Long sid;

	private String brandName;// 品牌名称

	private Long brandSid;// 品牌sid

	private String brandCode;// 品牌编码

	private String categoryName;// 品类名称

	private Long categorySid;// 品类sid

	private String categoryCode;// 品类编码

	private Integer limitValue;// 阀值

	private Integer status;// 阀值状态:0启用，1禁用

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public Integer getLimitValue() {
		return limitValue;
	}

	public void setLimitValue(Integer limitValue) {
		this.limitValue = limitValue;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getBrandSid() {
		return brandSid;
	}

	public void setBrandSid(Long brandSid) {
		this.brandSid = brandSid;
	}

	public Long getCategorySid() {
		return categorySid;
	}

	public void setCategorySid(Long categorySid) {
		this.categorySid = categorySid;
	}

	@Override
	public String toString() {
		return "PcmProductOnlineLimitResultDto [sid=" + sid + ", brandName=" + brandName
				+ ", brandSid=" + brandSid + ", brandCode=" + brandCode + ", categoryName="
				+ categoryName + ", categorySid=" + categorySid + ", categoryCode=" + categoryCode
				+ ", limitValue=" + limitValue + ", status=" + status + "]";
	}

}
package com.wangfj.product.brand.domain.vo;

import com.wangfj.core.framework.base.dto.BaseDto;

public class SelectBrandPageDto extends BaseDto {
	private Long sid;
	private String brandSid;// 集团品牌编码
	private String brandName;// 集团品牌名称
	private String brandNameAlias;// 集团品牌别名
	private String storeBrandSid;// 门店品牌编码
	private String shopType;// 门店类型
	/** 当前页的起始索引,从1开始 */
	protected int start = 1;

	/** mysql 分页 */
	protected int limit = 10;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getBrandSid() {
		return brandSid;
	}

	public void setBrandSid(String brandSid) {
		this.brandSid = brandSid;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandNameAlias() {
		return brandNameAlias;
	}

	public void setBrandNameAlias(String brandNameAlias) {
		this.brandNameAlias = brandNameAlias;
	}

	public String getStoreBrandSid() {
		return storeBrandSid;
	}

	public void setStoreBrandSid(String storeBrandSid) {
		this.storeBrandSid = storeBrandSid;
	}

	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	@Override
	public String toString() {
		return "SelectBrandPageDto [brandSid=" + brandSid + ", brandName="
				+ brandName + ", brandNameAlias=" + brandNameAlias
				+ ", storeBrandSid=" + storeBrandSid + ", shopType=" + shopType
				+ ", start=" + start + ", limit=" + limit + "]";
	}


}

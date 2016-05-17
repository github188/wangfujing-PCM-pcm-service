package com.wangfj.product.brand.domain.vo;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * 分页查询品牌dto
 * 
 * @Class Name SelectPcmBrandPageDto
 * @Author wangx
 * @Create In 2015-8-3
 */
public class SelectPcmBrandPageDto extends BaseDto {

	/**
	 * @Field long serialVersionUID
	 */
	private static final long serialVersionUID = 8937830927435264449L;

	private Long sid;

	private Long parentSid;// 与集团品牌关联的sid

	private String brandSid;// 品牌sid

	private String brandName;// 品牌名称

	private String spell;// 拼音

	private Integer shopType;// 门店类型(0 北京，1 外埠 ，2 电商erp)

	private Integer brandType;// 品牌类型 0 集团品牌 1 门店品牌

	private Integer status;// (门店品牌)有效标记：0有效，1无效

	private Integer brandGroupStatus;// (集团品牌)有效标记：0有效，1无效

	private Integer currentPage = 1;// 当前页数

	private Integer pageSize = 10;// 每页大小

	/** 当前页的起始索引,从0开始 */
	private int start = 0;

	/** mysql 分页 */
	private int limit = 10;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Long getParentSid() {
		return parentSid;
	}

	public void setParentSid(Long parentSid) {
		this.parentSid = parentSid;
	}

	public String getBrandSid() {
		return brandSid;
	}

	public void setBrandSid(String brandSid) {
		this.brandSid = brandSid == null ? null : brandSid.trim();
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName == null ? null : brandName.trim();
	}

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell == null ? null : spell.trim();
	}

	public Integer getShopType() {
		return shopType;
	}

	public void setShopType(Integer shopType) {
		this.shopType = shopType;
	}

	public Integer getBrandType() {
		return brandType;
	}

	public void setBrandType(Integer brandType) {
		this.brandType = brandType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getBrandGroupStatus() {
		return brandGroupStatus;
	}

	public void setBrandGroupStatus(Integer brandGroupStatus) {
		this.brandGroupStatus = brandGroupStatus;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
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
		return "SelectPcmBrandPageDto [sid=" + sid + ", parentSid=" + parentSid + ", brandSid="
				+ brandSid + ", brandName=" + brandName + ", spell=" + spell + ", shopType="
				+ shopType + ", brandType=" + brandType + ", status=" + status
				+ ", brandGroupStatus=" + brandGroupStatus + ", currentPage=" + currentPage
				+ ", pageSize=" + pageSize + ", start=" + start + ", limit=" + limit + "]";
	}

}

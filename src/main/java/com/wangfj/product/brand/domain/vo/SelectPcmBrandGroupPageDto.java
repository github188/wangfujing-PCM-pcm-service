package com.wangfj.product.brand.domain.vo;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * 集团品牌分页查询dto
 * 
 * @Class Name SelectPcmBrandGroupPageDto
 * @Author wangx
 * @Create In 2015-8-3
 */
public class SelectPcmBrandGroupPageDto extends BaseDto {

	private Long sid;

	private String brandSid;// 集团品牌编码

	private String brandName;// 中文名称

	private String brandNameSpell;// 中文拼音

	private String brandNameAlias;// 别名

	private String brandNameEn;// 英文名称

	private Integer status = 0;// 有效标记：0有效，1无效（默认为0）

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

	public String getBrandNameSpell() {
		return brandNameSpell;
	}

	public void setBrandNameSpell(String brandNameSpell) {
		this.brandNameSpell = brandNameSpell == null ? null : brandNameSpell.trim();
	}

	public String getBrandNameAlias() {
		return brandNameAlias;
	}

	public void setBrandNameAlias(String brandNameAlias) {
		this.brandNameAlias = brandNameAlias == null ? null : brandNameAlias.trim();
	}

	public String getBrandNameEn() {
		return brandNameEn;
	}

	public void setBrandNameEn(String brandNameEn) {
		this.brandNameEn = brandNameEn == null ? null : brandNameEn.trim();
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SelectPcmBrandGroupPageDto [sid=" + sid + ", brandSid=" + brandSid + ", brandName="
				+ brandName + ", brandNameSpell=" + brandNameSpell + ", brandNameAlias="
				+ brandNameAlias + ", brandNameEn=" + brandNameEn + ", status=" + status
				+ ", currentPage=" + currentPage + ", pageSize=" + pageSize + ", start=" + start
				+ ", limit=" + limit + "]";
	}

}

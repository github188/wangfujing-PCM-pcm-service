/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.price.domain.voSelectPriceDto.java
 * @Create By duanzhaole
 * @Create In 2015年7月24日 下午5:18:02
 */
package com.wangfj.product.price.domain.vo;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * 变价记录查询
 * 
 * @Class Name SelectPriceDto
 * @Author kongqf
 * @Create In 2015年8月3日
 */
public class SelectChangePriceRecordDto extends BaseDto {

	/**
	 * @Field long serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private Long sid;
	private String storeCode;

	private String matnr;

	private String supplierProdCode;

	private String zsprice;

	private String siteCode;

	private String waers;

	private String bdate;

	private String edate;

	private String changeCode;

	private String channelsid;

	private String priceType;

	private String actionCode;

	private String actionDate;

	private String actionPersion;

	private String createTime;

	private String attribute1;

	private String attribute2;

	private String attribute3;

	private String attribute4;

	private String attribute5;

	/**
	 * 当前页
	 */
	private Integer currentPage = 1;

	/**
	 * 单页行数 默认20
	 */
	private Integer pageSize = 200;
	private Integer start;
	private Integer limit;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getMatnr() {
		return matnr;
	}

	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}

	public String getSupplierProdCode() {
		return supplierProdCode;
	}

	public void setSupplierProdCode(String supplierProdCode) {
		this.supplierProdCode = supplierProdCode;
	}

	public String getZsprice() {
		return zsprice;
	}

	public void setZsprice(String zsprice) {
		this.zsprice = zsprice;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getWaers() {
		return waers;
	}

	public void setWaers(String waers) {
		this.waers = waers;
	}

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public String getChangeCode() {
		return changeCode;
	}

	public void setChangeCode(String changeCode) {
		this.changeCode = changeCode;
	}

	public String getChannelsid() {
		return channelsid;
	}

	public void setChannelsid(String channelsid) {
		this.channelsid = channelsid;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getActionDate() {
		return actionDate;
	}

	public void setActionDate(String actionDate) {
		this.actionDate = actionDate;
	}

	public String getActionPersion() {
		return actionPersion;
	}

	public void setActionPersion(String actionPersion) {
		this.actionPersion = actionPersion;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
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

	public Integer getStart() {
		this.start = 0;
		if (this.currentPage > 1 && this.pageSize > 0) {
			this.start = (this.currentPage - 1) * this.pageSize;
		}
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		if (this.pageSize != null)
			this.limit = this.pageSize;
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	@Override
	public String toString() {
		return "SelectChangePriceRecordDto [sid=" + sid + ", storeCode=" + storeCode + ", matnr="
				+ matnr + ", supplierProdCode=" + supplierProdCode + ", zsprice=" + zsprice
				+ ", siteCode=" + siteCode + ", waers=" + waers + ", bdate=" + bdate + ", edate="
				+ edate + ", changeCode=" + changeCode + ", channelsid=" + channelsid
				+ ", priceType=" + priceType + ", actionCode=" + actionCode + ", actionDate="
				+ actionDate + ", actionPersion=" + actionPersion + ", createTime=" + createTime
				+ ", attribute1=" + attribute1 + ", attribute2=" + attribute2 + ", attribute3="
				+ attribute3 + ", attribute4=" + attribute4 + ", attribute5=" + attribute5
				+ ", currentPage=" + currentPage + ", pageSize=" + pageSize + ", start=" + start
				+ ", limit=" + limit + "]";
	}

}

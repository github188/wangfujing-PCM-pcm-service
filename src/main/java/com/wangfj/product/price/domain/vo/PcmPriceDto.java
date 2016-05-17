package com.wangfj.product.price.domain.vo;

import java.math.BigDecimal;

/**
 * 
 * @Class Name PcmPriceDto
 * @Author duanzhaole
 * @Create In 2015年7月10日
 */
public class PcmPriceDto {

	/**
	 * 门店sid
	 */
	private String shopSid;

	/**
	 * 商品编码(中台编码)
	 */
	private String productDetailSid;

	/**
	 * erp商品编码
	 */
	private String erpProductCode;

	/**
	 * 售价
	 */
	private BigDecimal currentPrice;
	
	/**
	 * 中台专柜编码(带门店号的中台专柜编码)
	 */
	private String shoppeCode;

	/**
	 * 货比单位
	 */

	private String unit;

	/**
	 * begin_time开始日期yyyymmdd（包含当天）
	 */
	private String beginTime;

	/**
	 * 结束日期yyyymmdd（包含当天）
	 */
	private String endTime;

	/**
	 * 变价号
	 */
	private String orderNo;

	/**
	 * 零售价、短期价（1标识零售价 2 短期价）
	 */
	private Integer valenceType;

	/**
	 * 市场价（原价）
	 */
	private BigDecimal originalPrice;

	/**
	 * A(创建)，U(更新,如果不存在则创建)，D(删除)。必填字段。
	 */
	private String actionCode;

	/**
	 * 操作发起时间戳(yyyyMMdd.HHmmssZ)，例如”20141008.101830+0800”，可以为空。
	 */
	private String actionDate;

	/**
	 * 操作员标识(英文标识)。可以为空。
	 */
	private String actionPerson;

	/** 当前页的起始索引,从1开始 */
	protected int start = 1;

	/** mysql 分页 */
	protected int limit = 10;

	public String getShoppeCode() {
		return shoppeCode;
	}

	public void setShoppeCode(String shoppeCode) {
		this.shoppeCode = shoppeCode;
	}

	public String getUnit() {
		return unit;
	}

	public String getShopSid() {
		return shopSid;
	}

	public void setShopSid(String shopSid) {
		this.shopSid = shopSid;
	}

	public String getProductDetailSid() {
		return productDetailSid;
	}

	public void setProductDetailSid(String productDetailSid) {
		this.productDetailSid = productDetailSid;
	}

	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getValenceType() {
		return valenceType;
	}

	public void setValenceType(Integer valenceType) {
		this.valenceType = valenceType;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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

	public String getActionPerson() {
		return actionPerson;
	}

	public void setActionPerson(String actionPerson) {
		this.actionPerson = actionPerson;
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

	public String getErpProductCode() {
		return erpProductCode;
	}

	public void setErpProductCode(String erpProductCode) {
		this.erpProductCode = erpProductCode;
	}

}

package com.wangfj.product.price.domain.vo;

import java.util.Date;

import com.wangfj.core.framework.base.dto.BaseDto;

public class PcmShopPaymentInfoDto extends BaseDto {

	/**
	 * SID
	 */
	private Long storeSid;

	/**
	 * 门店编码
	 */
	private String storeCode;

	/**
	 * 门店名称
	 */
	private String storeName;
	/**
	 * 支付编码
	 */
	public String payCode;
	/**
	 * 支付名称
	 */
	public String name;
	/**
	 * 上级支付编码
	 */
	public String parentCode;
	/**
	 * 上级名称
	 */
	public String parentName;

	/**
	 * 银行识别码
	 */
	public String bankBIN;

	/**
	 * 处理时间
	 */
	private String dealTime;

	/**
	 * 最后修改人
	 */
	private String lastUpdBy;

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 创建人
	 */
	private String createBy;

	/**
	 * 备注
	 */
	private String remark;

	public String getDealTime() {
		return dealTime;
	}

	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}

	public String getLastUpdBy() {
		return lastUpdBy;
	}

	public void setLastUpdBy(String lastUpdBy) {
		this.lastUpdBy = lastUpdBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getStoreSid() {
		return storeSid;
	}

	public void setStoreSid(Long storeSid) {
		this.storeSid = storeSid;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getPayCode() {
		return payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getBankBIN() {
		return bankBIN;
	}

	public void setBankBIN(String bankBIN) {
		this.bankBIN = bankBIN;
	}

	@Override
	public String toString() {
		return "PcmShopPaymentInfoDto [storeSid=" + storeSid + ", storeCode=" + storeCode
				+ ", storeName=" + storeName + ", payCode=" + payCode + ", name=" + name
				+ ", parentCode=" + parentCode + ", bankBIN=" + bankBIN + ", dealTime=" + dealTime
				+ ", lastUpdBy=" + lastUpdBy + ", createDate=" + createDate + ", createBy="
				+ createBy + ", remark=" + remark + "]";
	}

}

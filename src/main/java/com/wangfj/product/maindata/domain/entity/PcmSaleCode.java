package com.wangfj.product.maindata.domain.entity;

import java.util.Date;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * 销售码表（扣率码表）
 * 
 * @Class Name PcmSaleCode
 * @Author zhangxy
 * @Create In 2015年7月2日
 */
public class PcmSaleCode extends BaseEntity {
	private Long sid;
	/**
	 * 供应商信息表SID
	 */
	private Long supplySid;
	/**
	 * 专柜SID
	 */
	private Long shoppeSid;
	/**
	 * 从erp中取的商品编码（销售编码）类似专柜码
	 */
	private String saleCode;
	/**
	 * 操作用户sid
	 */
	private Long optUserSid;
	/**
	 * 操作时间
	 */
	private Date optUpdateTime;

	private String memo;

	private Date supplySalingTime;
	/**
	 * 供应商专柜码=供应商编码+店号+供应商在店内的流水
	 */
	private String supplyShopCode;
	/**
	 * 合同编码
	 */
	private String contractCode;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Long getSupplySid() {
		return supplySid;
	}

	public void setSupplySid(Long supplySid) {
		this.supplySid = supplySid;
	}

	public Long getShoppeSid() {
		return shoppeSid;
	}

	public void setShoppeSid(Long shoppeSid) {
		this.shoppeSid = shoppeSid;
	}

	public String getSaleCode() {
		return saleCode;
	}

	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode == null ? null : saleCode.trim();
	}

	public Long getOptUserSid() {
		return optUserSid;
	}

	public void setOptUserSid(Long optUserSid) {
		this.optUserSid = optUserSid;
	}

	public Date getOptUpdateTime() {
		return optUpdateTime;
	}

	public void setOptUpdateTime(Date optUpdateTime) {
		this.optUpdateTime = optUpdateTime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo == null ? null : memo.trim();
	}

	public Date getSupplySalingTime() {
		return supplySalingTime;
	}

	public void setSupplySalingTime(Date supplySalingTime) {
		this.supplySalingTime = supplySalingTime;
	}

	public String getSupplyShopCode() {
		return supplyShopCode;
	}

	public void setSupplyShopCode(String supplyShopCode) {
		this.supplyShopCode = supplyShopCode == null ? null : supplyShopCode.trim();
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode == null ? null : contractCode.trim();
	}
}
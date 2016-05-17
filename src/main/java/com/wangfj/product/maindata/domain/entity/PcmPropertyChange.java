package com.wangfj.product.maindata.domain.entity;

import java.util.Date;

import com.wangfj.core.framework.base.entity.BaseEntity;

public class PcmPropertyChange extends BaseEntity {
	private Long sid;
	/**
	 * 单据号
	 */
	private String billNo;
	/**
	 * 单据类别(修改的属性) 1-spu换统计分类; 2-专柜商品换品牌; 3-专柜商品换专柜;
	 */
	private Integer billType;
	/**
	 * 所变更商品编码(或sid)
	 */
	private String productCode;
	/**
	 * 新值
	 */
	private String newValue;
	/**
	 * 旧值
	 */
	private String oldValue;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 生效时间
	 */
	private Date activeTime;
	/**
	 * 创建人
	 */
	private String createName;
	/**
	 * 已操作标记
	 */
	private Integer isScan;
	/**
	 * 请求json
	 */
	private String jsonText;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public Integer getBillType() {
		return billType;
	}

	public void setBillType(Integer billType) {
		this.billType = billType;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public Integer getIsScan() {
		return isScan;
	}

	public void setIsScan(Integer isScan) {
		this.isScan = isScan;
	}

	public String getJsonText() {
		return jsonText;
	}

	public void setJsonText(String jsonText) {
		this.jsonText = jsonText;
	}
}
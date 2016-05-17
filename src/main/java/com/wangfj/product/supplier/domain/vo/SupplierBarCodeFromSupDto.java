/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.supplier.domain.voSupplierBarCodeFromSupDto.java
 * @Create By wangc
 * @Create In 2016-3-2 下午2:08:06
 * TODO
 */
package com.wangfj.product.supplier.domain.vo;

import java.math.BigDecimal;

/**
 * @Class Name SupplierBarCodeFromSupDto
 * @Author  wangc
 * @Create In 2016-3-2
 */
public class SupplierBarCodeFromSupDto {

	private String storeCode; /* 门店(中台的门店编码) */
	private String matnr; /* 商品的ERP编码 */
	private String lifnr; /* 供应商编码 */
	private String counterCode; /* 专柜编码 */
	private String sbarcode; /* 供应商商品条码 */
	private String sbarcodeType; /* 条码类型 */
	private String sbarcodeName; /* 条码名称 */
	private String saleUnit; /* 销售单位 */
	private String saleAmount; /* 多包装的含量 */
	private BigDecimal salePrice; /* 售价 */
	private String actionCode; /* 本条记录对应的操作 (A添加；U更新；D删除) */
	private String actionDate; /* 操作时间 */
	private String actionPerson; /* 操作人 */
	private String originLand;//产地
	
	
	public String getOriginLand() {
		return originLand;
	}

	public void setOriginLand(String originLand) {
		this.originLand = originLand;
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

	public String getLifnr() {
		return lifnr;
	}

	public void setLifnr(String lifnr) {
		this.lifnr = lifnr;
	}

	public String getCounterCode() {
		return counterCode;
	}

	public void setCounterCode(String counterCode) {
		this.counterCode = counterCode;
	}

	public String getSbarcode() {
		return sbarcode;
	}

	public void setSbarcode(String sbarcode) {
		this.sbarcode = sbarcode;
	}

	public String getSbarcodeType() {
		return sbarcodeType;
	}

	public void setSbarcodeType(String sbarcodeType) {
		this.sbarcodeType = sbarcodeType;
	}

	public String getSbarcodeName() {
		return sbarcodeName;
	}

	public void setSbarcodeName(String sbarcodeName) {
		this.sbarcodeName = sbarcodeName;
	}

	public String getSaleUnit() {
		return saleUnit;
	}

	public void setSaleUnit(String saleUnit) {
		this.saleUnit = saleUnit;
	}

	public String getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(String saleAmount) {
		this.saleAmount = saleAmount;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
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

	@Override
	public String toString() {
		return "SupplierBarCodeFromEfutureDto [storeCode=" + storeCode + ", matnr=" + matnr
				+ ", lifnr=" + lifnr + ", counterCode=" + counterCode + ", sbarcode=" + sbarcode
				+ ", sbarcodeType=" + sbarcodeType + ", sbarcodeName=" + sbarcodeName
				+ ", saleUnit=" + saleUnit + ", saleAmount=" + saleAmount + ", salePrice="
				+ salePrice + ", actionCode=" + actionCode + ", actionDate=" + actionDate
				+ ", actionPerson=" + actionPerson + "]";
	}
}

package com.wangfj.product.organization.domain.vo;

public class PcmShoppeErpDto {
	private String code; /* 专柜编码 */
	private String name; /* 专柜名称 */
	private String storeCode; /* 门店编码 */
	private String supplierCode; /*供应商编码*/
	private String supplierErpCode; /* erp供应商编码 */
	private String floorCode; /* 楼层编码 */
	private String floorName; /* 楼层名称 */
	private String counterGroup; /* 柜组 */
	private char businessType; /* 业态类型 */
	private String counterType; /* 专柜类型 */
	private String counterInventoryType; /* 专柜库存管理类型 */
	private String counterShippingPoint; /* 专柜集货地点 */
	private String refCounter; /* 参考奥莱专柜 */
	private char counterStatus; /* 专柜状态 */
	private char isNegInventory; /* 是否负库存 0=Y 1=N */
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStoreCode() {
		return storeCode;
	}
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getSupplierErpCode() {
		return supplierErpCode;
	}
	public void setSupplierErpCode(String supplierErpCode) {
		this.supplierErpCode = supplierErpCode;
	}
	public String getFloorCode() {
		return floorCode;
	}
	public void setFloorCode(String floorCode) {
		this.floorCode = floorCode;
	}
	public String getFloorName() {
		return floorName;
	}
	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}
	public String getCounterGroup() {
		return counterGroup;
	}
	public void setCounterGroup(String counterGroup) {
		this.counterGroup = counterGroup;
	}
	public char getBusinessType() {
		return businessType;
	}
	public void setBusinessType(char businessType) {
		this.businessType = businessType;
	}
	public String getCounterType() {
		return counterType;
	}
	public void setCounterType(String counterType) {
		this.counterType = counterType;
	}
	public String getCounterInventoryType() {
		return counterInventoryType;
	}
	public void setCounterInventoryType(String counterInventoryType) {
		this.counterInventoryType = counterInventoryType;
	}
	public String getCounterShippingPoint() {
		return counterShippingPoint;
	}
	public void setCounterShippingPoint(String counterShippingPoint) {
		this.counterShippingPoint = counterShippingPoint;
	}
	public String getRefCounter() {
		return refCounter;
	}
	public void setRefCounter(String refCounter) {
		this.refCounter = refCounter;
	}
	public char getCounterStatus() {
		return counterStatus;
	}
	public void setCounterStatus(char counterStatus) {
		this.counterStatus = counterStatus;
	}
	public char getIsNegInventory() {
		return isNegInventory;
	}
	public void setIsNegInventory(char isNegInventory) {
		this.isNegInventory = isNegInventory;
	}
	@Override
	public String toString() {
		return "PcmShoppeErpDto [code=" + code + ", name=" + name
				+ ", storeCode=" + storeCode + ", supplierCode=" + supplierCode
				+ ", supplierErpCode=" + supplierErpCode + ", floorCode="
				+ floorCode + ", floorName=" + floorName + ", counterGroup="
				+ counterGroup + ", businessType=" + businessType
				+ ", counterType=" + counterType + ", counterInventoryType="
				+ counterInventoryType + ", counterShippingPoint="
				+ counterShippingPoint + ", refCounter=" + refCounter
				+ ", counterStatus=" + counterStatus + ", isNegInventory="
				+ isNegInventory + "]";
	}
	
}

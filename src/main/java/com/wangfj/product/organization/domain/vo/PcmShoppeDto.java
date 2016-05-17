package com.wangfj.product.organization.domain.vo;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * 
 * @Class Name PcmShoppeDto
 * @Author yedong
 * @Create In 2015年7月3日
 */
public class PcmShoppeDto extends BaseDto {
	private String code; /* 专柜编码 */
	private String name; /* 专柜名称 */
	private String storeCode; /* 门店编码 */
	private String supplierCode; /**/
	private String supplierErpCode; /* 供应商编码 */
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
	private String actionCode; /* 本条记录对应的操作 (A添加；U更新；D删除) */
	private String actionDate; /*
								 * 操作时间(格式为yyyyMMdd.HHmmssZ，结果形如
								 * "20141008.101603+0800")
								 */
	private String actionPerson; /* 操作人 */
	private char isShippingPoint;//是否为集货地点（0是，1否）
	
	private Long sid;//楼层sid
	

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public char getIsShippingPoint() {
		return isShippingPoint;
	}

	public void setIsShippingPoint(char isShippingPoint) {
		this.isShippingPoint = isShippingPoint;
	}
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
		return "PushCounterDto [code=" + code + ", name=" + name + ", storeCode=" + storeCode
				+ ", supplierCode=" + supplierCode + ", supplierErpCode=" + supplierErpCode
				+ ", floorCode=" + floorCode + ", floorName=" + floorName + ", counterGroup="
				+ counterGroup + ", businessType=" + businessType + ", counterType=" + counterType
				+ ", counterInventoryType=" + counterInventoryType + ", counterShippingPoint="
				+ counterShippingPoint + ", refCounter=" + refCounter + ", counterStatus="
				+ counterStatus + ", isNegInventory=" + isNegInventory + ", actionCode="
				+ actionCode + ", actionDate=" + actionDate + ", actionPerson=" + actionPerson
				+ "]";
	}
}

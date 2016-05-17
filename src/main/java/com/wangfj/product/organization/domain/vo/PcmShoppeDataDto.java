package com.wangfj.product.organization.domain.vo;

/**
 * 专柜导入终端--由主数据获取专柜信息-返回参数
 * 
 * @Class Name PcmShoppeDataDto
 * @Author niuzhifan
 * @Create In 2015-8-25
 */
public class PcmShoppeDataDto {

	private String sid;

	private String code;// 专柜编码

	private String name;// 专柜名称

	private String shopSid;// 门店sid

	private String storeCode;// 门店编号

	private String storeName;// 门店名称

	private String supplierCode;// 专柜对应的中台门店供应商编码。目前未使用。建议接收方不作处理。

	private String supplierErpCode;// 参考供应商编码（对于门店是门店ERP的供应商编码，对于电商是电商ERP的供应商编码）

	private String floorSid;// 楼层sid

	private String floorCode;// 楼层编码

	private String floorName;// 楼层名称

	private String counterGroup;// 柜组

	private String industryConditionSid;// 专柜所属业态(0百货；1超市；2电商)

	private String businessType;// 经营方式

	private String businessTypeSid;// 经营方式SID

	private String businessName;// 经营方式

	private String counterType;// 专柜类型(01 单品管理专柜 02 非单品管理专柜 03 部分单品管理专柜)

	private String counterInventoryType;// 专柜库存管理类型

	private String counterShippingPoint;// 专柜对应的集货地点（基础组织架构里面的集货仓编码）

	private String refCounter;// 参考奥莱专柜号（用于电商SAP产生取货单）

	private String counterStatus;// 专柜状态（1正常；2停用；3撤柜）

	private String isNegInventory;// 是否负库存(Y/N)(0 允许 ,1 不允许)

	private String isShippingPoint;// 是否为集货地点（0是，1否）

	public String getIsShippingPoint() {
		return isShippingPoint;
	}

	public void setIsShippingPoint(String isShippingPoint) {
		this.isShippingPoint = isShippingPoint;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode == null ? null : storeCode.trim();
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode == null ? null : supplierCode.trim();
	}

	public String getSupplierErpCode() {
		return supplierErpCode;
	}

	public void setSupplierErpCode(String supplierErpCode) {
		this.supplierErpCode = supplierErpCode == null ? null : supplierErpCode.trim();
	}

	public String getFloorCode() {
		return floorCode;
	}

	public void setFloorCode(String floorCode) {
		this.floorCode = floorCode == null ? null : floorCode.trim();
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName == null ? null : floorName.trim();
	}

	public String getCounterGroup() {
		return counterGroup;
	}

	public void setCounterGroup(String counterGroup) {
		this.counterGroup = counterGroup == null ? null : counterGroup.trim();
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType == null ? null : businessType.trim();
	}

	public String getCounterType() {
		return counterType;
	}

	public void setCounterType(String counterType) {
		this.counterType = counterType == null ? null : counterType.trim();
	}

	public String getCounterInventoryType() {
		return counterInventoryType;
	}

	public void setCounterInventoryType(String counterInventoryType) {
		this.counterInventoryType = counterInventoryType == null ? null : counterInventoryType
				.trim();
	}

	public String getCounterShippingPoint() {
		return counterShippingPoint;
	}

	public void setCounterShippingPoint(String counterShippingPoint) {
		this.counterShippingPoint = counterShippingPoint == null ? null : counterShippingPoint
				.trim();
	}

	public String getRefCounter() {
		return refCounter;
	}

	public void setRefCounter(String refCounter) {
		this.refCounter = refCounter == null ? null : refCounter.trim();
	}

	public String getCounterStatus() {
		return counterStatus;
	}

	public void setCounterStatus(String counterStatus) {
		this.counterStatus = counterStatus == null ? null : counterStatus.trim();
	}

	public String getIsNegInventory() {
		return isNegInventory;
	}

	public void setIsNegInventory(String isNegInventory) {
		this.isNegInventory = isNegInventory == null ? null : isNegInventory.trim();
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getShopSid() {
		return shopSid;
	}

	public void setShopSid(String shopSid) {
		this.shopSid = shopSid;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getFloorSid() {
		return floorSid;
	}

	public void setFloorSid(String floorSid) {
		this.floorSid = floorSid;
	}

	public String getIndustryConditionSid() {
		return industryConditionSid;
	}

	public void setIndustryConditionSid(String industryConditionSid) {
		this.industryConditionSid = industryConditionSid;
	}

	public String getBusinessTypeSid() {
		return businessTypeSid;
	}

	public void setBusinessTypeSid(String businessTypeSid) {
		this.businessTypeSid = businessTypeSid;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	@Override
	public String toString() {
		return "PcmShoppeDataDto [sid=" + sid + ", code=" + code + ", name=" + name + ", shopSid="
				+ shopSid + ", storeCode=" + storeCode + ", storeName=" + storeName
				+ ", supplierCode=" + supplierCode + ", supplierErpCode=" + supplierErpCode
				+ ", floorSid=" + floorSid + ", floorCode=" + floorCode + ", floorName="
				+ floorName + ", counterGroup=" + counterGroup + ", industryConditionSid="
				+ industryConditionSid + ", businessType=" + businessType + ", businessTypeSid="
				+ businessTypeSid + ", businessName=" + businessName + ", counterType="
				+ counterType + ", counterInventoryType=" + counterInventoryType
				+ ", counterShippingPoint=" + counterShippingPoint + ", refCounter=" + refCounter
				+ ", counterStatus=" + counterStatus + ", isNegInventory=" + isNegInventory + "]";
	}

}

package com.wangfj.product.organization.domain.vo;

import java.util.Date;

public class PcmShoppeResultDto {

	private Long sid;

	private Long groupSid;// 所属集团sid

	/* 所属门店SID */
	private Long shopSid;

	/* 所属门店名称 */
	private String shopName;
	/* 门店编码 */
	private String shopCode;
	/* 供应商中台编码 */
	private String supplierCode;

	/* 参考供应商编码（对于门店是门店ERP的供应商编码，对于电商是电商ERP的供应商编码） */
	private String supplierErpCode;

	/* 楼层SID */
	private Long floorSid;

	/* 楼层编码 */
	private String floorCode;

	/* 楼层名称 */
	private String floorName;

	/* 专柜所属业态SID */
	private Integer industryConditionSid;

	/* 专柜所属业态名称 */
	private String industryConditionName;

	/* 专柜编码 */
	private String shoppeCode;

	/* 专柜名称 */
	private String shoppeName;

	/* 状态 */
	private Integer shoppeStatus;

	/* 先销后采(0是，1否） */
	private Integer goodsManageType;

	/* 经营方式 */
	private Integer businessTypeSid;

	/* 最后一次修改人 */
	private String optUser;

	/* 修改时间 */
	private Date updateTimes;

	private String updateTimeStr;

	/* 创建人 */
	private String createName;

	/* 创建时间 */
	private Date createTimes;

	private String createTimeStr;

	/* 柜组 */
	private String shoppeGroup;

	/* 专柜类型 */
	private String shoppeType;

	/* 集货地点 (编码) */
	private String shoppeShippingPoint;

	/* 参考奥莱专柜 */
	private String refCounter;

	/* 是否负库存销售 */
	private Integer negativeStock;
	/* 是否为集货地点 */
	private Integer isShippingPoint;
	/* 集货地点名称 */
	private String shoppeShippingPointName;

	private String supplySid;// 供应商sid

	private String supplyCode;// 供应商编码

	private Integer businessPattern;// 供应商的经营方式

	private String supplyName;// 供应商的名称

	private Integer brandSid;// 品牌的sid

	private String brandName;// 品牌的名称

	private String contractCode;// 要约编号

	public String getShoppeShippingPointName() {
		return shoppeShippingPointName;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public void setShoppeShippingPointName(String shoppeShippingPointName) {
		this.shoppeShippingPointName = shoppeShippingPointName;
	}

	public Integer getIsShippingPoint() {
		return isShippingPoint;
	}

	public void setIsShippingPoint(Integer isShippingPoint) {
		this.isShippingPoint = isShippingPoint;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Long getShopSid() {
		return shopSid;
	}

	public void setShopSid(Long shopSid) {
		this.shopSid = shopSid;
	}

	public Long getFloorSid() {
		return floorSid;
	}

	public void setFloorSid(Long floorSid) {
		this.floorSid = floorSid;
	}

	public Integer getIndustryConditionSid() {
		return industryConditionSid;
	}

	public void setIndustryConditionSid(Integer industryConditionSid) {
		this.industryConditionSid = industryConditionSid;
	}

	public String getShoppeCode() {
		return shoppeCode;
	}

	public void setShoppeCode(String shoppeCode) {
		this.shoppeCode = shoppeCode == null ? null : shoppeCode.trim();
	}

	public String getShoppeName() {
		return shoppeName;
	}

	public void setShoppeName(String shoppeName) {
		this.shoppeName = shoppeName == null ? null : shoppeName.trim();
	}

	public Integer getShoppeStatus() {
		return shoppeStatus;
	}

	public void setShoppeStatus(Integer shoppeStatus) {
		this.shoppeStatus = shoppeStatus;
	}

	public Integer getGoodsManageType() {
		return goodsManageType;
	}

	public void setGoodsManageType(Integer goodsManageType) {
		this.goodsManageType = goodsManageType;
	}

	public Integer getBusinessTypeSid() {
		return businessTypeSid;
	}

	public void setBusinessTypeSid(Integer businessTypeSid) {
		this.businessTypeSid = businessTypeSid;
	}

	public String getOptUser() {
		return optUser;
	}

	public void setOptUser(String optUser) {
		this.optUser = optUser == null ? null : optUser.trim();
	}

	public Date getUpdateTimes() {
		return updateTimes;
	}

	public void setUpdateTimes(Date updateTimes) {
		this.updateTimes = updateTimes;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName == null ? null : createName.trim();
	}

	public Date getCreateTimes() {
		return createTimes;
	}

	public void setCreateTimes(Date createTimes) {
		this.createTimes = createTimes;
	}

	public String getShoppeGroup() {
		return shoppeGroup;
	}

	public void setShoppeGroup(String shoppeGroup) {
		this.shoppeGroup = shoppeGroup == null ? null : shoppeGroup.trim();
	}

	public String getShoppeType() {
		return shoppeType;
	}

	public void setShoppeType(String shoppeType) {
		this.shoppeType = shoppeType == null ? null : shoppeType.trim();
	}

	public String getShoppeShippingPoint() {
		return shoppeShippingPoint;
	}

	public void setShoppeShippingPoint(String shoppeShippingPoint) {
		this.shoppeShippingPoint = shoppeShippingPoint == null ? null : shoppeShippingPoint.trim();
	}

	public String getRefCounter() {
		return refCounter;
	}

	public void setRefCounter(String refCounter) {
		this.refCounter = refCounter == null ? null : refCounter.trim();
	}

	public Integer getNegativeStock() {
		return negativeStock;
	}

	public void setNegativeStock(Integer negativeStock) {
		this.negativeStock = negativeStock;
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr == null ? null : updateTimeStr.trim();
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr == null ? null : createTimeStr.trim();
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName == null ? null : shopName.trim();
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName == null ? null : floorName.trim();
	}

	public String getIndustryConditionName() {
		return industryConditionName;
	}

	public void setIndustryConditionName(String industryConditionName) {
		this.industryConditionName = industryConditionName == null ? null : industryConditionName
				.trim();
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

	public Long getGroupSid() {
		return groupSid;
	}

	public void setGroupSid(Long groupSid) {
		this.groupSid = groupSid;
	}

	public String getSupplySid() {
		return supplySid;
	}

	public void setSupplySid(String supplySid) {
		this.supplySid = supplySid;
	}

	public String getSupplyCode() {
		return supplyCode;
	}

	public void setSupplyCode(String supplyCode) {
		this.supplyCode = supplyCode;
	}

	public Integer getBusinessPattern() {
		return businessPattern;
	}

	public void setBusinessPattern(Integer businessPattern) {
		this.businessPattern = businessPattern;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public Integer getBrandSid() {
		return brandSid;
	}

	public void setBrandSid(Integer brandSid) {
		this.brandSid = brandSid;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	@Override
	public String toString() {
		return "PcmShoppeResultDto [sid=" + sid + ", groupSid=" + groupSid + ", shopSid=" + shopSid
				+ ", shopName=" + shopName + ", shopCode=" + shopCode + ", supplierCode="
				+ supplierCode + ", supplierErpCode=" + supplierErpCode + ", floorSid=" + floorSid
				+ ", floorCode=" + floorCode + ", floorName=" + floorName
				+ ", industryConditionSid=" + industryConditionSid + ", industryConditionName="
				+ industryConditionName + ", shoppeCode=" + shoppeCode + ", shoppeName="
				+ shoppeName + ", shoppeStatus=" + shoppeStatus + ", goodsManageType="
				+ goodsManageType + ", businessTypeSid=" + businessTypeSid + ", optUser=" + optUser
				+ ", updateTimes=" + updateTimes + ", updateTimeStr=" + updateTimeStr
				+ ", createName=" + createName + ", createTimes=" + createTimes
				+ ", createTimeStr=" + createTimeStr + ", shoppeGroup=" + shoppeGroup
				+ ", shoppeType=" + shoppeType + ", shoppeShippingPoint=" + shoppeShippingPoint
				+ ", refCounter=" + refCounter + ", negativeStock=" + negativeStock
				+ ", isShippingPoint=" + isShippingPoint + ", shoppeShippingPointName="
				+ shoppeShippingPointName + ", supplySid=" + supplySid + ", supplyCode="
				+ supplyCode + ", businessPattern=" + businessPattern + ", supplyName="
				+ supplyName + ", brandSid=" + brandSid + ", brandName=" + brandName
				+ ", contractCode=" + contractCode + "]";
	}

}

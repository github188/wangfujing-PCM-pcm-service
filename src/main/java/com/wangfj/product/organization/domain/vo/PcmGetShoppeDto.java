/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.organization.persistencePcmGetShoppeDto.java
 * @Create By wuxiong
 * @Create In 2015年7月29日 下午8:00:55
 */
package com.wangfj.product.organization.domain.vo;

import java.util.Date;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * @Class Name PcmGetShoppeDto
 * @Author wuxiong
 * @Create In 2015年7月29日
 */
public class PcmGetShoppeDto extends BaseDto {
	private Long sid;
	private String shoppeCode;/* 专柜编码 */
	private String shoppeName;/* 专柜名称 */
	private String floorCode; /* 楼层编码 */
	private String floorName; /* 楼层名称 */
	private Integer industrySid;// 专柜所属业态SID
	private String shoppeType; // 专柜类型(01 单品管理专柜 02 非单品管理专柜 03 部分单品管理专柜)
	private Integer goodManageType; // 专柜库存管理类型(01 门店专柜 02 电商自有专柜 03 电商奥莱 04
									// 电商供应商虚仓)
	private String shippingPoint; // 专柜集货地点
	private String refCounter; // 参考奥莱专柜
	private Integer shoppeStatus;// 1，正常；2，停用；3，撤柜（默认1）
	private Integer negIiveStock; // 是否负库存销售 ：0 允许，1不允许（默认为0）
	private String businessTypeSid;
	private String orgCode;/* 门店编码 */
	private String orgName;/* 门店名称 */
	private String supplyCode;
	private String shoppeGroup;// 柜组
	private String actionCode; /* 本条记录对应的操作 (A添加；U更新；D删除) */
	private String actionDate; /*
								 * 操作时间(格式为yyyyMMdd.HHmmssZ，结果形如
								 * "20141008.101603+0800")
								 */
	private String actionPerson; /* 操作人 */
	private String optUser;//经营方式
	private Long floorSid;//楼层sid
	private String createName;//创建人
	private Date createTime;//创建时间
	private Long shopSid;//门店sid
	private Date updateTime;//修改时间
	private Integer isShippingPoint;//是否为集货地点（0是，1否）
	
	public Integer getIsShippingPoint() {
		return isShippingPoint;
	}



	public void setIsShippingPoint(Integer isShippingPoint) {
		this.isShippingPoint = isShippingPoint;
	}



	public Date getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}



	public Long getShopSid() {
		return shopSid;
	}



	public void setShopSid(Long shopSid) {
		this.shopSid = shopSid;
	}



	public Date getUpdateTime() {
		return updateTime;
	}



	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}



	public String getCreateName() {
		return createName;
	}



	public void setCreateName(String createName) {
		this.createName = createName;
	}



	public Long getFloorSid() {
		return floorSid;
	}



	public void setFloorSid(Long floorSid) {
		this.floorSid = floorSid;
	}



	public String getOptUser() {
		return optUser;
	}



	public void setOptUser(String optUser) {
		this.optUser = optUser;
	}



	public Long getSid() {
		return sid;
	}



	public void setSid(Long sid) {
		this.sid = sid;
	}



	public String getShoppeCode() {
		return shoppeCode;
	}



	public void setShoppeCode(String shoppeCode) {
		this.shoppeCode = shoppeCode;
	}



	public String getShoppeName() {
		return shoppeName;
	}


	public void setShoppeName(String shoppeName) {
		this.shoppeName = shoppeName;
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


	public Integer getIndustrySid() {
		return industrySid;
	}

	public void setIndustrySid(Integer industrySid) {
		this.industrySid = industrySid;
	}

	public String getShoppeType() {
		return shoppeType;
	}


	public void setShoppeType(String shoppeType) {
		this.shoppeType = shoppeType;
	}


	public Integer getGoodManageType() {
		return goodManageType;
	}


	public void setGoodManageType(Integer goodManageType) {
		this.goodManageType = goodManageType;
	}


	public String getShippingPoint() {
		return shippingPoint;
	}


	public void setShippingPoint(String shippingPoint) {
		this.shippingPoint = shippingPoint;
	}


	public String getRefCounter() {
		return refCounter;
	}


	public void setRefCounter(String refCounter) {
		this.refCounter = refCounter;
	}


	public Integer getShoppeStatus() {
		return shoppeStatus;
	}


	public void setShoppeStatus(Integer shoppeStatus) {
		this.shoppeStatus = shoppeStatus;
	}


	public Integer getNegIiveStock() {
		return negIiveStock;
	}


	public void setNegIiveStock(Integer negIiveStock) {
		this.negIiveStock = negIiveStock;
	}



	public String getBusinessTypeSid() {
		return businessTypeSid;
	}


	public void setBusinessTypeSid(String businessTypeSid) {
		this.businessTypeSid = businessTypeSid;
	}


	public String getOrgCode() {
		return orgCode;
	}


	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}


	public String getOrgName() {
		return orgName;
	}


	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSupplyCode() {
		return supplyCode;
	}

	public void setSupplyCode(String supplyCode) {
		this.supplyCode = supplyCode;
	}

	public String getShoppeGroup() {
		return shoppeGroup;
	}

	public void setShoppeGroup(String shoppeGroup) {
		this.shoppeGroup = shoppeGroup;
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
		return "PcmGetShoppeDto [sid=" + sid + ", shoppeCode=" + shoppeCode
				+ ", shoppeName=" + shoppeName + ", floorCode=" + floorCode
				+ ", floorName=" + floorName + ", industrySid=" + industrySid
				+ ", shoppeType=" + shoppeType + ", goodManageType="
				+ goodManageType + ", shippingPoint=" + shippingPoint
				+ ", refCounter=" + refCounter + ", shoppeStatus="
				+ shoppeStatus + ", negIiveStock=" + negIiveStock
				+ ", businessTypeSid=" + businessTypeSid + ", orgCode="
				+ orgCode + ", orgName=" + orgName + ", supplyCode="
				+ supplyCode + ", shoppeGroup=" + shoppeGroup + ", actionCode="
				+ actionCode + ", actionDate=" + actionDate + ", actionPerson="
				+ actionPerson + ", optUser=" + optUser + ", floorSid="
				+ floorSid + ", createName=" + createName + ", createTime="
				+ createTime + ", shopSid=" + shopSid + ", updateTime="
				+ updateTime + "]";
	}
}

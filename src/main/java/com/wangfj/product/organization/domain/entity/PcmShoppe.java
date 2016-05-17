package com.wangfj.product.organization.domain.entity;

import java.util.Date;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * 
 * @Class Name PcmShoppe
 * @Author yedong
 * @Create In 2015年7月3日
 */
public class PcmShoppe extends BaseEntity {

	/**
	 * @Field long serialVersionUID
	 */
	private static final long serialVersionUID = -1569389952827112484L;

	private Long sid;

	private Long groupSid;// 所属集团sid

	private Long shopSid;/* 所属门店SID */

	private Long floorSid;/* 楼层SID */

	private Integer industryConditionSid;/* 专柜所属业态SID */

	private String shoppeCode;/* 专柜编码 */

	private String shoppeName;/* 专柜名称 */

	private Integer shoppeStatus;/* 状态 */

	private Integer goodsManageType;/* 商品管理类型 */

	private Integer businessTypeSid;/* 经营方式 */

	private String optUser;/* 最后一次修改人 */

	private Date updateTime;/* 修改时间 */

	private String createName;/* 创建人 */

	private Date createTime;/* 创建时间 */

	private String shoppeGroup;/* 柜组 */

	private String shoppeType;/* 专柜类型 */

	private String shoppeShippingPoint;/* 集货地点 */

	private String refCounter;/* 参考奥莱专柜 */

	private Integer negativeStock;/* 是否负库存销售 */

	private Integer isShippingPoint;/* 是否为集货地点 */

	public Long getSid() {
		return sid;
	}

	public Integer getIsShippingPoint() {
		return isShippingPoint;
	}

	public void setIsShippingPoint(Integer isShippingPoint) {
		this.isShippingPoint = isShippingPoint;
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
		this.createName = createName == null ? null : createName.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getShoppeGroup() {
		return shoppeGroup;
	}

	public void setShoppeGroup(String shoppeGroup) {
		this.shoppeGroup = shoppeGroup;
	}

	public String getShoppeType() {
		return shoppeType;
	}

	public void setShoppeType(String shoppeType) {
		this.shoppeType = shoppeType;
	}

	public String getShoppeShippingPoint() {
		return shoppeShippingPoint;
	}

	public void setShoppeShippingPoint(String shoppeShippingPoint) {
		this.shoppeShippingPoint = shoppeShippingPoint;
	}

	public String getRefCounter() {
		return refCounter;
	}

	public void setRefCounter(String refCounter) {
		this.refCounter = refCounter;
	}

	public Integer getNegativeStock() {
		return negativeStock;
	}

	public void setNegativeStock(Integer negativeStock) {
		this.negativeStock = negativeStock;
	}

	public Long getGroupSid() {
		return groupSid;
	}

	public void setGroupSid(Long groupSid) {
		this.groupSid = groupSid;
	}

	@Override
	public String toString() {
		return "PcmShoppe [sid=" + sid + ", groupSid=" + groupSid + ", shopSid=" + shopSid
				+ ", floorSid=" + floorSid + ", industryConditionSid=" + industryConditionSid
				+ ", shoppeCode=" + shoppeCode + ", shoppeName=" + shoppeName + ", shoppeStatus="
				+ shoppeStatus + ", goodsManageType=" + goodsManageType + ", businessTypeSid="
				+ businessTypeSid + ", optUser=" + optUser + ", updateTime=" + updateTime
				+ ", createName=" + createName + ", createTime=" + createTime + ", shoppeGroup="
				+ shoppeGroup + ", shoppeType=" + shoppeType + ", shoppeShippingPoint="
				+ shoppeShippingPoint + ", refCounter=" + refCounter + ", negativeStock="
				+ negativeStock + ", isShippingPoint=" + isShippingPoint + "]";
	}

}
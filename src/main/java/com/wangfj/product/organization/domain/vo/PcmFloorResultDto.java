package com.wangfj.product.organization.domain.vo;

import java.util.Date;

/**
 * 楼层返回参数
 * 
 * @Class Name PcmFloorResultDto
 * @Author niuzhifan
 * @Create In 2015-8-25
 */
public class PcmFloorResultDto {

	private Long sid;

	private Long shopSid; /* 所属门店 */

	private String shopName; /* 所属门店名称 */

	private String shopCode; /* 所属门店编码 */

	private String floorName; /* 楼层名称 */

	private String floorCode; /* 楼层编码 */

	private Long optUserSid; /* 最后一次修改人 */

	private Date updateTimes; /* 修改时间 */

	private String updateTimeStr; /* 修改时间 */

	private String createName; /* 创建人 */

	private Date createTimes; /* 创建时间 */

	private String createTimeStr; /* 创建时间 */

	private Integer floorStatus;// 楼层状态 默认为0，可用， 1禁用

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

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName == null ? null : floorName.trim();
	}

	public String getFloorCode() {
		return floorCode;
	}

	public void setFloorCode(String floorCode) {
		this.floorCode = floorCode == null ? null : floorCode.trim();
	}

	public Long getOptUserSid() {
		return optUserSid;
	}

	public void setOptUserSid(Long optUserSid) {
		this.optUserSid = optUserSid;
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

	public Integer getFloorStatus() {
		return floorStatus;
	}

	public void setFloorStatus(Integer floorStatus) {
		this.floorStatus = floorStatus;
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode == null ? null : shopCode.trim();
	}

}
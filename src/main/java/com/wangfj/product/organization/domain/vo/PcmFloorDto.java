package com.wangfj.product.organization.domain.vo;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * 
 * @Class Name PcmFloorDto
 * @Author yedong
 * @Create In 2015年7月3日
 */
public class PcmFloorDto extends BaseDto {

	private Long sid;

	private Long shopSid;// 门店sid

	private String storeCode;/* 门店编码 */

	private String code;/* 楼层编码 */

	private String oldCode;/* 旧的 楼层编码 */

	private String name;/* 楼层名称 */

	private String oldName;/* 旧的 楼层名称 */

	private Long optUserSid;

	private String updateTime;

	private String createName;

	private String createTime;

	private String actionCode;

	private String storeName;

	private Integer floorStatus;

	public Long getShopSid() {
		return shopSid;
	}

	public void setShopSid(Long shopSid) {
		this.shopSid = shopSid;
	}

	public Integer getFloorStatus() {
		return floorStatus;
	}

	public void setFloorStatus(Integer floorStatus) {
		this.floorStatus = floorStatus;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOldCode() {
		return oldCode;
	}

	public void setOldCode(String oldCode) {
		this.oldCode = oldCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public Long getOptUserSid() {
		return optUserSid;
	}

	public void setOptUserSid(Long optUserSid) {
		this.optUserSid = optUserSid;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	@Override
	public String toString() {
		return "PcmFloorDto [sid=" + sid + ", storeCode=" + storeCode + ", code=" + code
				+ ", oldCode=" + oldCode + ", name=" + name + ", oldName=" + oldName
				+ ", optUserSid=" + optUserSid + ", updateTime=" + updateTime + ", createName="
				+ createName + ", createTime=" + createTime + ", actionCode=" + actionCode
				+ ", storeName=" + storeName + ", floorStatus=" + floorStatus + "]";
	}

}

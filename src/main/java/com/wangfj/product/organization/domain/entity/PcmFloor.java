package com.wangfj.product.organization.domain.entity;

import java.util.Date;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * 
 * @Class Name PcmFloor
 * @Author yedong
 * @Create In 2015年7月3日
 */
public class PcmFloor extends BaseEntity {

	/**
	 * @Field long serialVersionUID
	 */
	private static final long serialVersionUID = -5532972126754855157L;

	private Long sid;

	private Long shopSid; /* 所属门店 */

	private String floorName; /* 楼层名称 */

	private String floorCode; /* 楼层编码 */

	private Long optUserSid; /* 最后一次修改人 */

	private Date updateTime; /* 修改时间 */

	private String createName; /* 创建人 */

	private Date createTime; /* 创建时间 */

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

	public Integer getFloorStatus() {
		return floorStatus;
	}

	public void setFloorStatus(Integer floorStatus) {
		this.floorStatus = floorStatus;
	}

	@Override
	public String toString() {
		return "PcmFloor [sid=" + sid + ", shopSid=" + shopSid + ", floorName=" + floorName
				+ ", floorCode=" + floorCode + ", optUserSid=" + optUserSid + ", updateTime="
				+ updateTime + ", createName=" + createName + ", createTime=" + createTime
				+ ", floorStatus=" + floorStatus + "]";
	}

}
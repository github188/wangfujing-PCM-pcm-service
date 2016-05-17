package com.wangfj.product.organization.domain.vo;

import java.util.Date;

/**
 * 查询参数
 * 
 * @Class Name SelectPcmFloorDto
 * @Author wangxuan
 * @Create In 2015-8-25
 */
public class SelectPcmFloorDto {

	private Long sid;

	private Long shopSid; /* 所属门店 */

	private String floorName; /* 楼层名称 */

	private String floorCode; /* 楼层编码 */

	private Long optUserSid; /* 最后一次修改人 */

	private Date updateTime; /* 修改时间 */

	private String createName; /* 创建人 */

	private Date createTime; /* 创建时间 */

	private Integer floorStatus;// 楼层状态 默认为0，可用， 1禁用

	private Integer currentPage = 1;// 当前页数

	private Integer pageSize = 10;// 每页大小

	/** 当前页的起始索引,从0开始 */
	private Integer start = 0;

	/** mysql 分页 */
	private Integer limit = 10;

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

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

}
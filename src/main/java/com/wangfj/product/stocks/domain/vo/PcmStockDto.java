package com.wangfj.product.stocks.domain.vo;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * 
 * @Class Name PcmStockDto
 * @Author yedong
 * @Create In 2015年7月3日
 */
public class PcmStockDto extends BaseDto {
	/**
	 * @Field long serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private Long sid;
	/*
	 * 专柜商品SID
	 */
	private String shoppeProSid;
	/*
	 * 类型SID
	 */
	private Integer stockTypeSid;
	/*
	 * 供应商商品编码
	 */
	private String supplyProductId;
	/*
	 * 库存数量
	 */
	private Long proSum;
	/*
	 * 库存数
	 */
	private Integer inventory;
	/*
	 * 渠道
	 */
	private String channelSid;
	/*
	 * 锁定数量
	 */
	private Long lockCount;

	private Integer count;
	/*
	 * sku
	 */
	private String sku;
	/*
	 * 库存地点编码
	 */
	private String location;
	/*
	 * 出货主体编号
	 */
	private String locationOwnerId;
	/*
	 * 改动类型
	 */
	private String type;
	/*
	 * 调用方
	 */
	private String source;
	/*
	 * 操作人
	 */
	private String operator;
	/*
	 * 更新状态
	 */
	private String success;
	/*
	 * 借用库存数
	 */
	private Integer borrowInventory;
	/*
	 * 残次品库存数
	 */
	private Integer defectiveInventory;
	/*
	 * 停售库存数
	 */
	private Integer stopsalesInventory;
	/*
	 * 安全库存数
	 */
	private Integer warningInventory;
	/*
	 * 错误代码
	 */
	private String errorCode;
	/*
	 * 错误原因
	 */
	private String exception;

	/**
	 * guid
	 */
	private String guid;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getShoppeProSid() {
		return shoppeProSid;
	}

	public void setShoppeProSid(String shoppeProSid) {
		this.shoppeProSid = shoppeProSid;
	}

	public Integer getStockTypeSid() {
		return stockTypeSid;
	}

	public void setStockTypeSid(Integer stockTypeSid) {
		this.stockTypeSid = stockTypeSid;
	}

	public Long getProSum() {
		return proSum;
	}

	public void setProSum(Long proSum) {
		this.proSum = proSum;
	}

	public String getChannelSid() {
		return channelSid;
	}

	public void setChannelSid(String channelSid) {
		this.channelSid = channelSid;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocationOwnerId() {
		return locationOwnerId;
	}

	public void setLocationOwnerId(String locationOwnerId) {
		this.locationOwnerId = locationOwnerId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getLockCount() {
		return lockCount;
	}

	public void setLockCount(Long lockCount) {
		this.lockCount = lockCount;
	}

	public String getSupplyProductId() {
		return supplyProductId;
	}

	public void setSupplyProductId(String supplyProductId) {
		this.supplyProductId = supplyProductId;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public Integer getBorrowInventory() {
		return borrowInventory;
	}

	public void setBorrowInventory(Integer borrowInventory) {
		this.borrowInventory = borrowInventory;
	}

	public Integer getDefectiveInventory() {
		return defectiveInventory;
	}

	public void setDefectiveInventory(Integer defectiveInventory) {
		this.defectiveInventory = defectiveInventory;
	}

	public Integer getStopsalesInventory() {
		return stopsalesInventory;
	}

	public void setStopsalesInventory(Integer stopsalesInventory) {
		this.stopsalesInventory = stopsalesInventory;
	}

	public Integer getWarningInventory() {
		return warningInventory;
	}

	public void setWarningInventory(Integer warningInventory) {
		this.warningInventory = warningInventory;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	@Override
	public String toString() {
		return "PcmStockDto [sid=" + sid + ", shoppeProSid=" + shoppeProSid + ", stockTypeSid="
				+ stockTypeSid + ", supplyProductId=" + supplyProductId + ", proSum=" + proSum
				+ ", inventory=" + inventory + ", channelSid=" + channelSid + ", lockCount="
				+ lockCount + ", count=" + count + ", sku=" + sku + ", location=" + location
				+ ", locationOwnerId=" + locationOwnerId + ", type=" + type + ", source=" + source
				+ ", operator=" + operator + ", success=" + success + ", borrowInventory="
				+ borrowInventory + ", defectiveInventory=" + defectiveInventory
				+ ", stopsalesInventory=" + stopsalesInventory + ", warningInventory="
				+ warningInventory + ", errorCode=" + errorCode + ", exception=" + exception
				+ ", guid=" + guid + "]";
	}

}

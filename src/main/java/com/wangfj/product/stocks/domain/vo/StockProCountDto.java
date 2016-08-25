package com.wangfj.product.stocks.domain.vo;

/**
 * 库存接口商品Dto
 * 
 * @Class Name StockProCountDto
 * @Author yedong
 * @Create In 2015年7月17日
 */
public class StockProCountDto {
	/**
	 * 销售订单
	 */
	private String saleNo;
	/*
	 * 销售明细编号
	 */
	private String salesItemNo;
	/*
	 * 专柜商品编号
	 */
	private String supplyProductNo;

	/**
	 * 专柜商品名称
	 */
	private String shoppeProName;

	/*
	 * 销售数量
	 */
	private Integer saleSum;
	/*
	 * 大码
	 */
	private String erpProductNo;
	/*
	 * 锁定/库存 类型
	 */
	private Integer stockType;

	private String channelSid;

	/**
	 * 是否支付减库存 0,否;1,是;
	 */
	private String isPayReduce;

	/**
	 * 门店编号
	 */
	private String storeCode;

	public String getSalesItemNo() {
		return salesItemNo;
	}

	public void setSalesItemNo(String salesItemNo) {
		this.salesItemNo = salesItemNo;
	}

	public String getSupplyProductNo() {
		return supplyProductNo;
	}

	public void setSupplyProductNo(String supplyProductNo) {
		this.supplyProductNo = supplyProductNo;
	}

	public Integer getSaleSum() {
		return saleSum;
	}

	public void setSaleSum(Integer saleSum) {
		this.saleSum = saleSum;
	}

	public String getErpProductNo() {
		return erpProductNo;
	}

	public void setErpProductNo(String erpProductNo) {
		this.erpProductNo = erpProductNo;
	}

	public String getChannelSid() {
		return channelSid;
	}

	public void setChannelSid(String channelSid) {
		this.channelSid = channelSid;
	}

	public Integer getStockType() {
		return stockType;
	}

	public void setStockType(Integer stockType) {
		this.stockType = stockType;
	}

	public String getSaleNo() {
		return saleNo;
	}

	public void setSaleNo(String saleNo) {
		this.saleNo = saleNo;
	}

	public String getShoppeProName() {
		return shoppeProName;
	}

	public void setShoppeProName(String shoppeProName) {
		this.shoppeProName = shoppeProName;
	}

	public String getIsPayReduce() {
		return isPayReduce;
	}

	public void setIsPayReduce(String isPayReduce) {
		this.isPayReduce = isPayReduce;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	@Override
	public String toString() {
		return "StockProCountDto [saleNo=" + saleNo + ", salesItemNo=" + salesItemNo
				+ ", supplyProductNo=" + supplyProductNo + ", shoppeProName=" + shoppeProName
				+ ", saleSum=" + saleSum + ", erpProductNo=" + erpProductNo + ", stockType="
				+ stockType + ", channelSid=" + channelSid + ", isPayReduce=" + isPayReduce
				+ ", storeCode=" + storeCode + "]";
	}

}

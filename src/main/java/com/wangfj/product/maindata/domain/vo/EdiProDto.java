package com.wangfj.product.maindata.domain.vo;

public class EdiProDto {
	private String shoppeProCode;// 专柜商品编码
	private String EDIProCode;// EDI商品编码
	private String channelCode;// 渠道编码
	private String isPresell;// 是否预售0否
	private String isPayReduceStock;// 是否支付减库存(1否/2是)
	private String actionCode;// 操作类型
	private String actionDate;// 操作时间
	private String actionPerson;// 操作人

	public String getShoppeProCode() {
		return shoppeProCode;
	}

	public void setShoppeProCode(String shoppeProCode) {
		this.shoppeProCode = shoppeProCode;
	}

	public String getEDIProCode() {
		return EDIProCode;
	}

	public void setEDIProCode(String eDIProCode) {
		EDIProCode = eDIProCode;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getIsPresell() {
		return isPresell;
	}

	public void setIsPresell(String isPresell) {
		this.isPresell = isPresell;
	}

	public String getIsPayReduceStock() {
		return isPayReduceStock;
	}

	public void setIsPayReduceStock(String isPayReduceStock) {
		this.isPayReduceStock = isPayReduceStock;
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
		return "EdiProPara [shoppeProCode=" + shoppeProCode + ", EDIProCode=" + EDIProCode
				+ ", channelCode=" + channelCode + ", isPresell=" + isPresell
				+ ", isPayReduceStock=" + isPayReduceStock + ", actionCode=" + actionCode
				+ ", actionDate=" + actionDate + ", actionPerson=" + actionPerson + "]";
	}

}

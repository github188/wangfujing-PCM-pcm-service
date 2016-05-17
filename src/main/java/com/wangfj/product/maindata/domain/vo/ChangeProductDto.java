package com.wangfj.product.maindata.domain.vo;

import com.wangfj.core.framework.base.dto.BaseDto;


public class ChangeProductDto extends BaseDto {
	/**
	 * @Field long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;

	private Long sid;

	private String supplySid;// 供应商编码

	private String shopPuductSid;// 专柜商品编码

	private String actionCode;

	private String actionDate;

	private String actionPerson;
	
	private Integer saleStatus; //销售状态
	
	private Long brandSid ; //门店品牌sid

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getSupplySid() {
		return supplySid;
	}

	public void setSupplySid(String supplySid) {
		this.supplySid = supplySid;
	}

	public String getShopPuductSid() {
		return shopPuductSid;
	}

	public void setShopPuductSid(String shopPuductSid) {
		this.shopPuductSid = shopPuductSid;
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
	
	

	public Integer getSaleStatus() {
		return saleStatus;
	}

	public void setSaleStatus(Integer saleStatus) {
		this.saleStatus = saleStatus;
	}
	
	public Long getBrandSid() {
		return brandSid;
	}

	public void setBrandSid(Long brandSid) {
		this.brandSid = brandSid;
	}

	@Override
	public String toString() {
		return "ChangeProductDto [sid=" + sid + ", supplySid=" + supplySid + ", shopPuductSid="
				+ shopPuductSid + ", actionCode=" + actionCode + ", actionDate=" + actionDate
				+ ", actionPerson=" + actionPerson + ", saleStatus=" + saleStatus + ", brandSid="
				+ brandSid + "]";
	}


	

}

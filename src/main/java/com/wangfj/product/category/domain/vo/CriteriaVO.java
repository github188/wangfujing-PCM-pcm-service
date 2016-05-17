package com.wangfj.product.category.domain.vo;

import java.util.Date;

public class CriteriaVO {

	private Long sid;
	
	private Long proType;
	
	private Long proSelling;
	
	private Long activityFlg;
	
	private Long presentFlg;
	
	private Long brandSid;
	
	private String queryCate;
	
	private String offprice;
	
	private String price;
	
	private Date recent;
	
	private String orderByPrice;
	
	private String orderByTime;
	
	private Long sexId;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Long getProType() {
		return proType;
	}

	public void setProType(Long proType) {
		this.proType = proType;
	}

	public Long getProSelling() {
		return proSelling;
	}

	public void setProSelling(Long proSelling) {
		this.proSelling = proSelling;
	}

	public Long getActivityFlg() {
		return activityFlg;
	}

	public void setActivityFlg(Long activityFlg) {
		this.activityFlg = activityFlg;
	}

	public Long getPresentFlg() {
		return presentFlg;
	}

	public void setPresentFlg(Long presentFlg) {
		this.presentFlg = presentFlg;
	}

	public Long getBrandSid() {
		return brandSid;
	}

	public void setBrandSid(Long brandSid) {
		this.brandSid = brandSid;
	}

	public Date getRecent() {
		return recent;
	}

	public void setRecent(Date recent) {
		this.recent = recent;
	}

	public String getOrderByPrice() {
		return orderByPrice;
	}

	public void setOrderByPrice(String orderByPrice) {
		this.orderByPrice = orderByPrice;
	}

	public String getOrderByTime() {
		return orderByTime;
	}

	public void setOrderByTime(String orderByTime) {
		this.orderByTime = orderByTime;
	}

	public Long getSexId() {
		return sexId;
	}

	public void setSexId(Long sexId) {
		this.sexId = sexId;
	}

	public String getQueryCate() {
		return queryCate;
	}

	public void setQueryCate(String queryCate) {
		this.queryCate = queryCate;
	}

	public String getOffprice() {
		return offprice;
	}

	public void setOffprice(String offprice) {
		this.offprice = offprice;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}

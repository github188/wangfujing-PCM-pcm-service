/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.maindata.domain.voErpSupInfoDto.java
 * @Create By wangc
 * @Create In 2016-3-18 下午3:43:52
 * TODO
 */
package com.wangfj.product.maindata.domain.vo;

/**
 * 扣率码信息列表封装
 * @Class Name ErpSupInfoDto
 * @Author  wangc
 * @Create In 2016-3-18
 */
public class ErpSupInfoDto {

	private String erpcode;//扣率码
	
	private String contractList;//要约号
	
	private String discountLimit;//折扣底限
	
	private String statCategory;//统计分类
	
	private String manageCategory;//管理分类

	
	public String getErpcode() {
		return erpcode;
	}

	public void setErpcode(String erpcode) {
		this.erpcode = erpcode;
	}

	public String getContractList() {
		return contractList;
	}

	public void setContractList(String contractList) {
		this.contractList = contractList;
	}

	public String getDiscountLimit() {
		return discountLimit;
	}

	public void setDiscountLimit(String discountLimit) {
		this.discountLimit = discountLimit;
	}

	public String getStatCategory() {
		return statCategory;
	}

	public void setStatCategory(String statCategory) {
		this.statCategory = statCategory;
	}

	public String getManageCategory() {
		return manageCategory;
	}

	public void setManageCategory(String manageCategory) {
		this.manageCategory = manageCategory;
	}
	
	
}

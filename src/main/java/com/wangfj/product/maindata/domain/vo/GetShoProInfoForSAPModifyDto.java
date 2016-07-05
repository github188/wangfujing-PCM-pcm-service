/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.maindata.domain.voGetShoProInfoForSAPModifyDto.java
 * @Create By wangc
 * @Create In 2016年7月5日 上午10:11:39
 * TODO
 */
package com.wangfj.product.maindata.domain.vo;

/**
 * @Class Name GetShoProInfoForSAPModifyDto
 * @Author wangc
 * @Create In 2016年7月5日
 */
public class GetShoProInfoForSAPModifyDto {
	
	private Long sid;
	
	/**
	 * 专柜商品编码
	 */
	private String shoppeProSid;
	/**
	 * 管理分类编码
	 */
	private String categorySid;
	/**
	 * 供应商商品编码
	 */
	private String supplyProductCode;
	/**
	 * 供应商编码
	 */
	private String supplyCode;
	
	
	public String getSupplyCode() {
		return supplyCode;
	}
	public void setSupplyCode(String supplyCode) {
		this.supplyCode = supplyCode;
	}
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
	public String getCategorySid() {
		return categorySid;
	}
	public void setCategorySid(String categorySid) {
		this.categorySid = categorySid;
	}
	public String getSupplyProductCode() {
		return supplyProductCode;
	}
	public void setSupplyProductCode(String supplyProductCode) {
		this.supplyProductCode = supplyProductCode;
	}
	
	
}

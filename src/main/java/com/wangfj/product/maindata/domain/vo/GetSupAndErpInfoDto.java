/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.maindata.domain.voGetSupAndErpInfoDto.java
 * @Create By wangc
 * @Create In 2016-3-18 下午3:40:07
 * TODO
 */
package com.wangfj.product.maindata.domain.vo;

import java.util.List;

/**
 * 专柜列表信息封装类
 * @Class Name GetSupAndErpInfoDto
 * @Author  wangc
 * @Create In 2016-3-18
 */
public class GetSupAndErpInfoDto {

	/**
	 * 供应商编码
	 */
	private String supplierCode;
	/**
	 * 供应商名称
	 */
	private String supplierName;
	/**
	 * 专柜列表
	 */
	private List<ErpSupInfoListDto> shoppeList;
	
	
	@Override
	public String toString() {
		return "supplierCode:"+supplierCode+",supplierName:"+supplierName+",shoppeList="+shoppeList.size();
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public List<ErpSupInfoListDto> getShoppeList() {
		return shoppeList;
	}
	public void setShoppeList(List<ErpSupInfoListDto> shoppeList) {
		this.shoppeList = shoppeList;
	}
	
	
	
}

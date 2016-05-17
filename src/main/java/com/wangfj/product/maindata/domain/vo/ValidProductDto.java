package com.wangfj.product.maindata.domain.vo;

import java.util.List;

/**
 * 验证Dto
 * 
 * @Class Name ValidProductDto
 * @Author wangsy
 * @Create In 2015年7月14日
 */
public class ValidProductDto {
	/**
	 * 供应商编码
	 */
	private String supplierCode;

	/**
	 * 专柜编码
	 */
	private String counterCode;

	/**
	 * productList对象中包含的商品行数
	 */
	private String productCount;

	/**
	 * 待校验的商品列表
	 */
	private List<ProductListDto> listProductDto;

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getCounterCode() {
		return counterCode;
	}

	public void setCounterCode(String counterCode) {
		this.counterCode = counterCode;
	}

	public String getProductCount() {
		return productCount;
	}

	public void setProductCount(String productCount) {
		this.productCount = productCount;
	}

	public List<ProductListDto> getListProductDto() {
		return listProductDto;
	}

	public void setListProductDto(List<ProductListDto> listProductDto) {
		this.listProductDto = listProductDto;
	}

	@Override
	public String toString() {
		return "ValidProductDto [supplierCode=" + supplierCode + ", counterCode=" + counterCode
				+ ", productCount=" + productCount + ", listProductDto=" + listProductDto + "]";
	}

}

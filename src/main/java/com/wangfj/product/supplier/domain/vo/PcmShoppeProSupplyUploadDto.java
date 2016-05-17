package com.wangfj.product.supplier.domain.vo;

/**
 * 一品多供应商关系上传(输入参数)
 * 
 * @Class Name PcmShoppeProSupplyUploadDto
 * @Author wangxuan
 * @Create In 2015-8-28
 */
public class PcmShoppeProSupplyUploadDto {

	private String storeCode;// 门店编号

	private String productCode;// 商品编码

	private String supplierProductCode;// 专柜商品编码

	private String supplierCode;// 商品对应的供应商编码

	private String ACTION_CODE;// 本条记录对应的操作 (A添加；U更新；D删除)

	private String ACTION_DATE;// 操作时间（格式为yyyyMMdd.HHmmssZ)

	private String ACTION_PERSON;// 操作人

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getSupplierProductCode() {
		return supplierProductCode;
	}

	public void setSupplierProductCode(String supplierProductCode) {
		this.supplierProductCode = supplierProductCode;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getACTION_CODE() {
		return ACTION_CODE;
	}

	public void setACTION_CODE(String aCTION_CODE) {
		ACTION_CODE = aCTION_CODE;
	}

	public String getACTION_DATE() {
		return ACTION_DATE;
	}

	public void setACTION_DATE(String aCTION_DATE) {
		ACTION_DATE = aCTION_DATE;
	}

	public String getACTION_PERSON() {
		return ACTION_PERSON;
	}

	public void setACTION_PERSON(String aCTION_PERSON) {
		ACTION_PERSON = aCTION_PERSON;
	}

	@Override
	public String toString() {
		return "PcmShoppeProSupplyUploadDto [storeCode=" + storeCode + ", productCode="
				+ productCode + ", supplierProductCode=" + supplierProductCode + ", supplierCode="
				+ supplierCode + ", ACTION_CODE=" + ACTION_CODE + ", ACTION_DATE=" + ACTION_DATE
				+ ", ACTION_PERSON=" + ACTION_PERSON + "]";
	}

}

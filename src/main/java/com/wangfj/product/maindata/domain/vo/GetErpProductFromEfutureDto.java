package com.wangfj.product.maindata.domain.vo;

import org.codehaus.jackson.annotate.JsonProperty;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * ERP商品信息DTO
 * 
 * @Class Name PcmErpProductDto
 * @Author zhangxy
 * @Create In 2015年7月9日
 */
public class GetErpProductFromEfutureDto extends BaseDto {
	private String storeCode;
	/**
	 * 专柜编码
	 */
	private String counterCode;
	/**
	 * 合同号
	 */
	private String contractCode;

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	/**
	 * 商品编码（大码）
	 */
	private String productCode;
	/**
	 * 大码类型:0单品码,1长期统码,2特卖统码,3促销统码,4促销扣率码,5价格码,6扣率码
	 */
	private String skuType;
	/**
	 * 门店供应商编码
	 */
	private String supplierCode;
	/**
	 * 门店品牌编码
	 */
	private String brandCode;
	/**
	 * 商品名称
	 */
	private String productName;
	/**
	 * 商品经营方式
	 */
	private String productType;
	/**
	 * 商品类别:01 自营单品,05 金银首饰,06 服务费商品,07 租赁商品,,08 联营单品,09 组包码
	 */
	private String productCategory;
	/**
	 * 规格名称（例如250ml）
	 */
	private String specName;
	/**
	 * 货号
	 */
	private String articleNum;
	/**
	 * 销售计量单位（上传的是单位的文本信息，例如瓶）
	 */
	private String salesUnit;
	/**
	 * 供应商条码
	 */
	private String supplierBarcode;
	/**
	 * 售价
	 */
	private String salesPrice;
	/**
	 * 进项税
	 */
	private String inputTax;
	/**
	 * 销项税
	 */
	private String outputTax;
	/**
	 * 消费税
	 */
	private String salesTax;
	/**
	 * 商品状态(0正常；1停售；2停货；3暂停使用；4已删除；5淘汰)
	 */
	private String status;
	/**
	 * 是否允许促销(0 允许 , 1 不允许)
	 */
	private String isPromotion;
	/**
	 * 是否允许调价(0 允许 ,1 不允许)
	 */
	private String isAdjustPrice;
	/**
	 * 所属末级管理分类的编码
	 */
	private String manageCategory;
	/**
	 * 折扣底限(0到1.00之间的小数)
	 */
	private String discountLimit;
	/**
	 * 产地（文本信息）
	 */
	private String originLand;
	/**
	 * 服务费类型
	 */
	private String serviceFeeType;
	/**
	 * 业态类型（1百货、2超市、3电商）
	 */
	private String formatType;
	/**
	 * 所属末级统计分类的编码
	 */
	private String statCategory;
	/**
	 * 标准扣率（0到100之间的百分数，最多六位小数，只传数值不传%号，比如20.123%只传20.123。）
	 */
	private String commissionRate;
	/**
	 * 操作代码
	 */
	@JsonProperty(value = "ACTION_CODE")
	private String ACTION_CODE;
	/**
	 * 操作日期
	 */
	@JsonProperty(value = "ACTION_DATE")
	private String ACTION_DATE;
	/**
	 * 操作员标示
	 */
	@JsonProperty(value = "ACTION_PERSON")
	private String ACTION_PERSON;

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getCounterCode() {
		return counterCode;
	}

	public void setCounterCode(String counterCode) {
		this.counterCode = counterCode;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getSkuType() {
		return skuType;
	}

	public void setSkuType(String skuType) {
		this.skuType = skuType;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public String getArticleNum() {
		return articleNum;
	}

	public void setArticleNum(String articleNum) {
		this.articleNum = articleNum;
	}

	public String getSalesUnit() {
		return salesUnit;
	}

	public void setSalesUnit(String salesUnit) {
		this.salesUnit = salesUnit;
	}

	public String getSupplierBarcode() {
		return supplierBarcode;
	}

	public void setSupplierBarcode(String supplierBarcode) {
		this.supplierBarcode = supplierBarcode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsPromotion() {
		return isPromotion;
	}

	public void setIsPromotion(String isPromotion) {
		this.isPromotion = isPromotion;
	}

	public String getIsAdjustPrice() {
		return isAdjustPrice;
	}

	public void setIsAdjustPrice(String isAdjustPrice) {
		this.isAdjustPrice = isAdjustPrice;
	}

	public String getManageCategory() {
		return manageCategory;
	}

	public void setManageCategory(String manageCategory) {
		this.manageCategory = manageCategory;
	}

	public String getOriginLand() {
		return originLand;
	}

	public void setOriginLand(String originLand) {
		this.originLand = originLand;
	}

	public String getServiceFeeType() {
		return serviceFeeType;
	}

	public void setServiceFeeType(String serviceFeeType) {
		this.serviceFeeType = serviceFeeType;
	}

	public String getFormatType() {
		return formatType;
	}

	public void setFormatType(String formatType) {
		this.formatType = formatType;
	}

	public String getStatCategory() {
		return statCategory;
	}

	public void setStatCategory(String statCategory) {
		this.statCategory = statCategory;
	}

	public String getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(String salesPrice) {
		this.salesPrice = salesPrice;
	}

	public String getInputTax() {
		return inputTax;
	}

	public void setInputTax(String inputTax) {
		this.inputTax = inputTax;
	}

	public String getOutputTax() {
		return outputTax;
	}

	public void setOutputTax(String outputTax) {
		this.outputTax = outputTax;
	}

	public String getSalesTax() {
		return salesTax;
	}

	public void setSalesTax(String salesTax) {
		this.salesTax = salesTax;
	}

	public String getDiscountLimit() {
		return discountLimit;
	}

	public void setDiscountLimit(String discountLimit) {
		this.discountLimit = discountLimit;
	}

	public String getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(String commissionRate) {
		this.commissionRate = commissionRate;
	}

	public String getACTION_CODE() {
		return ACTION_CODE;
	}

	public void setACTION_CODE(String ACTION_CODE) {
		this.ACTION_CODE = ACTION_CODE;
	}

	public String getACTION_DATE() {
		return ACTION_DATE;
	}

	public void setACTION_DATE(String ACTION_DATE) {
		this.ACTION_DATE = ACTION_DATE;
	}

	public String getACTION_PERSON() {
		return ACTION_PERSON;
	}

	public void setACTION_PERSON(String ACTION_PERSON) {
		this.ACTION_PERSON = ACTION_PERSON;
	}

	@Override
	public String toString() {
		return "GetErpProductFromEfutureDto [storeCode=" + storeCode + ", counterCode="
				+ counterCode + ", productCode=" + productCode + ", skuType=" + skuType
				+ ", supplierCode=" + supplierCode + ", brandCode=" + brandCode + ", productName="
				+ productName + ", productType=" + productType + ", productCategory="
				+ productCategory + ", specName=" + specName + ", articleNum=" + articleNum
				+ ", salesUnit=" + salesUnit + ", supplierBarcode=" + supplierBarcode
				+ ", salesPrice=" + salesPrice + ", inputTax=" + inputTax + ", outputTax="
				+ outputTax + ", salesTax=" + salesTax + ", status=" + status + ", isPromotion="
				+ isPromotion + ", isAdjustPrice=" + isAdjustPrice + ", manageCategory="
				+ manageCategory + ", discountLimit=" + discountLimit + ", originLand="
				+ originLand + ", serviceFeeType=" + serviceFeeType + ", formatType=" + formatType
				+ ", statCategory=" + statCategory + ", commissionRate=" + commissionRate
				+ ", ACTION_CODE=" + ACTION_CODE + ", ACTION_DATE=" + ACTION_DATE
				+ ", ACTION_PERSON=" + ACTION_PERSON + "]";
	}

}

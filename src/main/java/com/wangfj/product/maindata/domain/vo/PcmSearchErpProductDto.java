package com.wangfj.product.maindata.domain.vo;

public class PcmSearchErpProductDto {
	private String productCode;// 大码商品
	private String shoppeCode;// 专柜编码
	private String storeCode;// 门店编码
	private Integer codeType;// 大码类型0 价格码,1 长期统码,2 促销统码,3 特卖统码,4 扣率码,5 促销扣率码,6
								// 单品码
	private String bigCodePrice;// 大码价格
	private String supplierCode;// 供应商编码
	private String storeBrandCode;// 门店品牌编码
	private String productName;// 商品名称
	private String productAbbr;// 商品简称
	private Integer productType;// 商品经营方式: 0-(Z001 经销);1-(Z002 代销);2-(Z003
								// 联营);3-(Z004 平台服务);4-(Z005 租赁)
	private Integer productCategory;// 商品类别:01 自营单品,3:金额码,4:销售条码,05 金银首饰,06
									// 服务费商品,07 租赁商品,,08 联营单品,09 组包码
	private String stanName;// 规格名称（如250ml）
	private String articleNum;// 货号
	private String salesUnit;// 销售计量单位（上传的是单位的文本信息，例如瓶）
	private String supplierBarcode;// 供应商条码
	private String salesPrice;// 售价
	private Integer proStatus;// 商品状态(0正常；1停售；2停货；3暂停使用；4已删除；5淘汰)
	private Integer isPromotion;// 是否允许促销(0 允许 , 1 不允许)
	private Integer isAdjustPrice;// 是否允许调价(0 允许 ,1 不允许)
	private Double discountLimit;// 折扣底线（0-1.0）
	private String originLand;// 产地
	private String serviceFeeType;// 服务费类型
	private Integer formatType;// 业态类型（1百货、2超市、3电商）
	private String managerCateNo;// 管理分类编码

	public String getManagerCateNo() {
		return managerCateNo;
	}

	public void setManagerCateNo(String managerCateNo) {
		this.managerCateNo = managerCateNo;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getShoppeCode() {
		return shoppeCode;
	}

	public void setShoppeCode(String shoppeCode) {
		this.shoppeCode = shoppeCode;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public Integer getCodeType() {
		return codeType;
	}

	public void setCodeType(Integer codeType) {
		this.codeType = codeType;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getStoreBrandCode() {
		return storeBrandCode;
	}

	public void setStoreBrandCode(String storeBrandCode) {
		this.storeBrandCode = storeBrandCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductAbbr() {
		return productAbbr;
	}

	public void setProductAbbr(String productAbbr) {
		this.productAbbr = productAbbr;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public Integer getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(Integer productCategory) {
		this.productCategory = productCategory;
	}

	public String getStanName() {
		return stanName;
	}

	public void setStanName(String stanName) {
		this.stanName = stanName;
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

	
	public String getBigCodePrice() {
		return bigCodePrice;
	}

	public void setBigCodePrice(String bigCodePrice) {
		this.bigCodePrice = bigCodePrice;
	}

	public String getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(String salesPrice) {
		this.salesPrice = salesPrice;
	}

	public Integer getProStatus() {
		return proStatus;
	}

	public void setProStatus(Integer proStatus) {
		this.proStatus = proStatus;
	}

	public Integer getIsPromotion() {
		return isPromotion;
	}

	public void setIsPromotion(Integer isPromotion) {
		this.isPromotion = isPromotion;
	}

	public Integer getIsAdjustPrice() {
		return isAdjustPrice;
	}

	public void setIsAdjustPrice(Integer isAdjustPrice) {
		this.isAdjustPrice = isAdjustPrice;
	}

	public Double getDiscountLimit() {
		return discountLimit;
	}

	public void setDiscountLimit(Double discountLimit) {
		this.discountLimit = discountLimit;
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

	public Integer getFormatType() {
		return formatType;
	}

	public void setFormatType(Integer formatType) {
		this.formatType = formatType;
	}

	@Override
	public String toString() {
		return "PcmSearchErpProductDto [productCode=" + productCode + ", shoppeCode=" + shoppeCode
				+ ", storeCode=" + storeCode + ", codeType=" + codeType + ", bigCodePrice="
				+ bigCodePrice + ", supplierCode=" + supplierCode + ", storeBrandCode="
				+ storeBrandCode + ", productName=" + productName + ", productAbbr=" + productAbbr
				+ ", productType=" + productType + ", productCategory=" + productCategory
				+ ", stanName=" + stanName + ", articleNum=" + articleNum + ", salesUnit="
				+ salesUnit + ", supplierBarcode=" + supplierBarcode + ", salesPrice=" + salesPrice
				+ ", proStatus=" + proStatus + ", isPromotion=" + isPromotion + ", isAdjustPrice="
				+ isAdjustPrice + ", discountLimit=" + discountLimit + ", originLand=" + originLand
				+ ", serviceFeeType=" + serviceFeeType + ", formatType=" + formatType
				+ ", managerCateNo=" + managerCateNo + "]";
	}

}

package com.wangfj.product.maindata.domain.vo;

/**
 * 商品条件Dto
 * 
 * @Class Name ProductCondDto
 * @Author chengsj
 * @Create In 2015-8-4
 */
public class ProductCondDto {

	/**
	 * 产品SPU编码
	 */
	private String productSid;
	/**
	 * 专柜商品编码
	 */
	private String shoppeProSid;
	/**
	 * 商品表SKU编码
	 */
	private String productDetailSid;
	/**
	 * 专柜编码
	 */
	private String shoppeSid;
	/**
	 * 供应商编码
	 */
	private String supplySid;

	/**
	 * 工业分类sid
	 */
	private String categorySid;

	/**
	 * 扣率码
	 */
	private String rateCode;

	/**
	 * 促销扣率码
	 */
	private String promotionRateCodeSid;
	/**
	 * 开始时间
	 */
	private String beginTime;
	/**
	 * 结束时间
	 */
	private String endTime;
	/**
	 * 操作人
	 */
	private Long optUserSid;
	/**
	 * 特性
	 */
	private String features;
	/**
	 * 色码
	 */
	private String proColorName;
	/**
	 * 规码
	 */
	private String proStanSid;
	/**
	 * 款码
	 */
	private String productSku;
	/**
	 * 主属性
	 */
	private String primaryAttr;
	/**
	 * 颜色别名
	 */
	private String proColorAlias;
	/**
	 * 色系
	 */
	private String proColorSid;

	public String getProColorSid() {
		return proColorSid;
	}

	public void setProColorSid(String proColorSid) {
		this.proColorSid = proColorSid;
	}

	public String getProColorAlias() {
		return proColorAlias;
	}

	public void setProColorAlias(String proColorAlias) {
		this.proColorAlias = proColorAlias;
	}

	public String getProductSid() {
		return productSid;
	}

	public void setProductSid(String productSid) {
		this.productSid = productSid;
	}

	public String getShoppeProSid() {
		return shoppeProSid;
	}

	public void setShoppeProSid(String shoppeProSid) {
		this.shoppeProSid = shoppeProSid;
	}

	public String getProductDetailSid() {
		return productDetailSid;
	}

	public void setProductDetailSid(String productDetailSid) {
		this.productDetailSid = productDetailSid;
	}

	public String getShoppeSid() {
		return shoppeSid;
	}

	public void setShoppeSid(String shoppeSid) {
		this.shoppeSid = shoppeSid;
	}

	public String getSupplySid() {
		return supplySid;
	}

	public void setSupplySid(String supplySid) {
		this.supplySid = supplySid;
	}

	public String getCategorySid() {
		return categorySid;
	}

	public void setCategorySid(String categorySid) {
		this.categorySid = categorySid;
	}

	public String getRateCode() {
		return rateCode;
	}

	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
	}

	public String getPromotionRateCodeSid() {
		return promotionRateCodeSid;
	}

	public void setPromotionRateCodeSid(String promotionRateCodeSid) {
		this.promotionRateCodeSid = promotionRateCodeSid;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Long getOptUserSid() {
		return optUserSid;
	}

	public void setOptUserSid(Long optUserSid) {
		this.optUserSid = optUserSid;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getProColorName() {
		return proColorName;
	}

	public void setProColorName(String proColorName) {
		this.proColorName = proColorName;
	}

	public String getProStanSid() {
		return proStanSid;
	}

	public void setProStanSid(String proStanSid) {
		this.proStanSid = proStanSid;
	}

	public String getProductSku() {
		return productSku;
	}

	public void setProductSku(String productSku) {
		this.productSku = productSku;
	}

	public String getPrimaryAttr() {
		return primaryAttr;
	}

	public void setPrimaryAttr(String primaryAttr) {
		this.primaryAttr = primaryAttr;
	}

	@Override
	public String toString() {
		return "ProductCondDto [productSid=" + productSid + ", shoppeProSid=" + shoppeProSid
				+ ", productDetailSid=" + productDetailSid + ", shoppeSid=" + shoppeSid
				+ ", supplySid=" + supplySid + ", categorySid=" + categorySid + ", rateCode="
				+ rateCode + ", promotionRateCodeSid=" + promotionRateCodeSid + ", beginTime="
				+ beginTime + ", endTime=" + endTime + ", optUserSid=" + optUserSid + ", features="
				+ features + ", proColorName=" + proColorName + ", proStanSid=" + proStanSid
				+ ", productSku=" + productSku + ", primaryAttr=" + primaryAttr
				+ ", proColorAlias=" + proColorAlias + ", proColorSid=" + proColorSid + "]";
	}

}

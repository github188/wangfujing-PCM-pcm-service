package com.wangfj.product.maindata.domain.entity;

import java.math.BigDecimal;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * 照片明细表
 * 
 * @Class Name PcmPhotoDetail
 * @Author zhangxy
 * @Create In 2015年7月3日
 */
public class PcmPhotoDetail extends BaseEntity {
	private Long sid;
	/**
	 * 商品名称
	 */
	private String productName;
	/**
	 * 品牌SID
	 */
	private Long brandSid;
	/**
	 * 品牌名称
	 */
	private String brandName;
	private String proSku;
	private String proColor;
	/**
	 * 色系ID
	 */
	private Long colorSid;
	/**
     * 
     */
	private String companyName;
	/**
	 * 库存数
	 */
	private BigDecimal stockNum;
	/**
	 * 门店SID
	 */
	private Long shopSid;
	/**
	 * 门店名称
	 */
	private String shopName;
	/**
	 * 供应商SID
	 */
	private Long supplySid;
	/**
	 * 供应商名称
	 */
	private String categorySid;
	/**
	 * 原价
	 */
	private BigDecimal originalPrice;
	/**
	 * 现价
	 */
	private BigDecimal currentPrice;
	/**
	 * 折扣率
	 */
	private BigDecimal currentOffValue;
	private Long proSupplyPriceSid;
	private String proClassDesc;
	private String proClassSid;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName == null ? null : productName.trim();
	}

	public Long getBrandSid() {
		return brandSid;
	}

	public void setBrandSid(Long brandSid) {
		this.brandSid = brandSid;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName == null ? null : brandName.trim();
	}

	public String getProSku() {
		return proSku;
	}

	public void setProSku(String proSku) {
		this.proSku = proSku == null ? null : proSku.trim();
	}

	public String getProColor() {
		return proColor;
	}

	public void setProColor(String proColor) {
		this.proColor = proColor == null ? null : proColor.trim();
	}

	public Long getColorSid() {
		return colorSid;
	}

	public void setColorSid(Long colorSid) {
		this.colorSid = colorSid;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName == null ? null : companyName.trim();
	}

	public BigDecimal getStockNum() {
		return stockNum;
	}

	public void setStockNum(BigDecimal stockNum) {
		this.stockNum = stockNum;
	}

	public Long getShopSid() {
		return shopSid;
	}

	public void setShopSid(Long shopSid) {
		this.shopSid = shopSid;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName == null ? null : shopName.trim();
	}

	public Long getSupplySid() {
		return supplySid;
	}

	public void setSupplySid(Long supplySid) {
		this.supplySid = supplySid;
	}

	public String getCategorySid() {
		return categorySid;
	}

	public void setCategorySid(String categorySid) {
		this.categorySid = categorySid == null ? null : categorySid.trim();
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}

	public BigDecimal getCurrentOffValue() {
		return currentOffValue;
	}

	public void setCurrentOffValue(BigDecimal currentOffValue) {
		this.currentOffValue = currentOffValue;
	}

	public Long getProSupplyPriceSid() {
		return proSupplyPriceSid;
	}

	public void setProSupplyPriceSid(Long proSupplyPriceSid) {
		this.proSupplyPriceSid = proSupplyPriceSid;
	}

	public String getProClassDesc() {
		return proClassDesc;
	}

	public void setProClassDesc(String proClassDesc) {
		this.proClassDesc = proClassDesc == null ? null : proClassDesc.trim();
	}

	public String getProClassSid() {
		return proClassSid;
	}

	public void setProClassSid(String proClassSid) {
		this.proClassSid = proClassSid == null ? null : proClassSid.trim();
	}
}
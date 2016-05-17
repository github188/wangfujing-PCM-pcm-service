package com.wangfj.product.category.domain.vo;

import java.math.BigDecimal;
import java.util.List;

public class PhotoProDetailParam {
	
	private Long shopSid;
	
	private String shopName;
	
	private List<Long> supplySid;
	
	private List<Long> brandSid;
	
	private String brandName;
	
	private String productSku;
	
	private String proColor;
	
	private Long sexSid;
	
	private List<Long> proClassSid;
	
	private Long  proActiveBit;
	
	private Long  proSelling;
	
	private Long  proType;
	
	private Long  presentFlg;
	
    private Long maxProSum;
	
	private Long minProSum;
	
	private BigDecimal maxOffValue;
	
	private BigDecimal minOffValue;
	
	private BigDecimal maxPrice;
	
	private BigDecimal minPrice;
	
	private Long  channelSid;
	
	private Integer start;
	
	private Integer pageSize;

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
		this.shopName = shopName;
	}

	

	public List<Long> getSupplySid() {
		return supplySid;
	}

	public void setSupplySid(List<Long> supplySid) {
		this.supplySid = supplySid;
	}

	

	public List<Long> getBrandSid() {
		return brandSid;
	}

	public void setBrandSid(List<Long> brandSid) {
		this.brandSid = brandSid;
	}

	public void setProClassSid(List<Long> proClassSid) {
		this.proClassSid = proClassSid;
	}
	

	public List<Long> getProClassSid() {
		return proClassSid;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getProductSku() {
		return productSku;
	}

	public void setProductSku(String productSku) {
		this.productSku = productSku;
	}

	public String getProColor() {
		return proColor;
	}

	public void setProColor(String proColor) {
		this.proColor = proColor;
	}

	public Long getSexSid() {
		return sexSid;
	}

	public void setSexSid(Long sexSid) {
		this.sexSid = sexSid;
	}

	
	public Long getProActiveBit() {
		return proActiveBit;
	}

	public void setProActiveBit(Long proActiveBit) {
		this.proActiveBit = proActiveBit;
	}

	public Long getProSelling() {
		return proSelling;
	}

	public void setProSelling(Long proSelling) {
		this.proSelling = proSelling;
	}

	public Long getProType() {
		return proType;
	}

	public void setProType(Long proType) {
		this.proType = proType;
	}

	public Long getPresentFlg() {
		return presentFlg;
	}

	public void setPresentFlg(Long presentFlg) {
		this.presentFlg = presentFlg;
	}

	public Long getMaxProSum() {
		return maxProSum;
	}

	public void setMaxProSum(Long maxProSum) {
		this.maxProSum = maxProSum;
	}

	public Long getMinProSum() {
		return minProSum;
	}

	public void setMinProSum(Long minProSum) {
		this.minProSum = minProSum;
	}

	public BigDecimal getMaxOffValue() {
		return maxOffValue;
	}

	public void setMaxOffValue(BigDecimal maxOffValue) {
		this.maxOffValue = maxOffValue;
	}

	public BigDecimal getMinOffValue() {
		return minOffValue;
	}

	public void setMinOffValue(BigDecimal minOffValue) {
		this.minOffValue = minOffValue;
	}

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	public Long getChannelSid() {
		return channelSid;
	}

	public void setChannelSid(Long channelSid) {
		this.channelSid = channelSid;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
}

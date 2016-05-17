package com.wangfj.product.category.domain.vo;

import java.math.BigDecimal;
import java.util.List;

public class PhotoParamsVO {
	private BigDecimal shopSid;
	private List<BigDecimal> supplySid;
	private String shopName;
	private List<BigDecimal> brandSid;
	private String brandName;
	private String proSku;
	private List<BigDecimal> saleCodeSid;//供应商代码
	private String proColor;
	private BigDecimal sexSid;
	private List<BigDecimal> proClassSid;
	private BigDecimal netSaleBit;
	
	private BigDecimal minStockNum;
	private BigDecimal maxStockNum;
	private BigDecimal minPrice;
	private BigDecimal maxPrice;
	private BigDecimal minOffValue;
	private BigDecimal maxOffValue;
	
	private Integer start;
	private Integer end;
	
	private Integer pageSize;
	
		
	public List<BigDecimal> getSupplySid() {
		return supplySid;
	}
	public void setSupplySid(List<BigDecimal> supplySid) {
		this.supplySid = supplySid;
	}
	public List<BigDecimal> getBrandSid() {
		return brandSid;
	}
	public void setBrandSid(List<BigDecimal> brandSid) {
		this.brandSid = brandSid;
	}
	public List<BigDecimal> getSaleCodeSid() {
		return saleCodeSid;
	}
	public void setSaleCodeSid(List<BigDecimal> saleCodeSid) {
		this.saleCodeSid = saleCodeSid;
	}
	public List<BigDecimal> getProClassSid() {
		return proClassSid;
	}
	public void setProClassSid(List<BigDecimal> proClassSid) {
		this.proClassSid = proClassSid;
	}
	public BigDecimal getShopSid() {
		return shopSid;
	}
	public void setShopSid(BigDecimal shopSid) {
		this.shopSid = shopSid;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getProSku() {
		return proSku;
	}
	public void setProSku(String proSku) {
		this.proSku = proSku;
	}
	
	public String getProColor() {
		return proColor;
	}
	public void setProColor(String proColor) {
		this.proColor = proColor;
	}
	public BigDecimal getSexSid() {
		return sexSid;
	}
	public void setSexSid(BigDecimal sexSid) {
		this.sexSid = sexSid;
	}
	
	public BigDecimal getNetSaleBit() {
		return netSaleBit;
	}
	public void setNetSaleBit(BigDecimal netSaleBit) {
		this.netSaleBit = netSaleBit;
	}
	public BigDecimal getMinStockNum() {
		return minStockNum;
	}
	public void setMinStockNum(BigDecimal minStockNum) {
		this.minStockNum = minStockNum;
	}
	public BigDecimal getMaxStockNum() {
		return maxStockNum;
	}
	public void setMaxStockNum(BigDecimal maxStockNum) {
		this.maxStockNum = maxStockNum;
	}
	public BigDecimal getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}
	public BigDecimal getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}
	public BigDecimal getMinOffValue() {
		return minOffValue;
	}
	public void setMinOffValue(BigDecimal minOffValue) {
		this.minOffValue = minOffValue;
	}
	public BigDecimal getMaxOffValue() {
		return maxOffValue;
	}
	public void setMaxOffValue(BigDecimal maxOffValue) {
		this.maxOffValue = maxOffValue;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getEnd() {
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
	
}

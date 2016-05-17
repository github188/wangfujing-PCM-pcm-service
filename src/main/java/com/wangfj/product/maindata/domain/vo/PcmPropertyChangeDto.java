package com.wangfj.product.maindata.domain.vo;

public class PcmPropertyChangeDto {
	/**
	 * 专柜商品换品牌的Sid
	 */
	private Long sid;
	/**
	 * 门店品牌Sid
	 */
	private String brandSid;
	/**
	 * 专柜商品编码
	 */
	private String shoppeProSid;

	/**
	 * 专柜编码
	 */
	private String shoppeSid;
	/**
	 * 产品Spu的Sid
	 */
	private String productSid;
	/**
	 * 统计分类Sid
	 */
	private String categorySid;
	/**
	 * json串
	 */
	private String jsonText;
	/**
	 * 生效时间
	 */
	private String activeTime;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getBrandSid() {
		return brandSid;
	}

	public void setBrandSid(String brandSid) {
		this.brandSid = brandSid;
	}

	public String getShoppeProSid() {
		return shoppeProSid;
	}

	public void setShoppeProSid(String shoppeProSid) {
		this.shoppeProSid = shoppeProSid;
	}

	public String getShoppeSid() {
		return shoppeSid;
	}

	public void setShoppeSid(String shoppeSid) {
		this.shoppeSid = shoppeSid;
	}

	public String getProductSid() {
		return productSid;
	}

	public void setProductSid(String productSid) {
		this.productSid = productSid;
	}

	public String getCategorySid() {
		return categorySid;
	}

	public void setCategorySid(String categorySid) {
		this.categorySid = categorySid;
	}

	public String getJsonText() {
		return jsonText;
	}

	public void setJsonText(String jsonText) {
		this.jsonText = jsonText;
	}

	public String getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(String activeTime) {
		this.activeTime = activeTime;
	}

	@Override
	public String toString() {
		return "PcmPropertyChangeDto [sid=" + sid + ", brandSid=" + brandSid + ", shoppeProSid="
				+ shoppeProSid + ", shoppeSid=" + shoppeSid + ", productSid=" + productSid
				+ ", categorySid=" + categorySid + ", jsonText=" + jsonText + ", activeTime="
				+ activeTime + "]";
	}

}

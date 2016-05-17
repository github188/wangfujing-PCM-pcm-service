package com.wangfj.product.supplier.domain.vo;

public class PcmShoppeSupplierQueryDto {

	private String shopSid;// 门店sid

	private String shopCode;// 门店编码

	private String shoppeSid;// 专柜sid

	private String shoppeCode;// 专柜编码

	private String supplySid;// 供应商sid

	private String supplyCode;// 供应商编码

	public String getShopSid() {
		return shopSid;
	}

	public void setShopSid(String shopSid) {
		this.shopSid = shopSid;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getShoppeSid() {
		return shoppeSid;
	}

	public void setShoppeSid(String shoppeSid) {
		this.shoppeSid = shoppeSid;
	}

	public String getShoppeCode() {
		return shoppeCode;
	}

	public void setShoppeCode(String shoppeCode) {
		this.shoppeCode = shoppeCode;
	}

	public String getSupplySid() {
		return supplySid;
	}

	public void setSupplySid(String supplySid) {
		this.supplySid = supplySid;
	}

	public String getSupplyCode() {
		return supplyCode;
	}

	public void setSupplyCode(String supplyCode) {
		this.supplyCode = supplyCode;
	}

	@Override
	public String toString() {
		return "PcmShoppeSupplierPara [shopSid=" + shopSid + ", shopCode=" + shopCode
				+ ", shoppeSid=" + shoppeSid + ", shoppeCode=" + shoppeCode + ", supplySid="
				+ supplySid + ", supplyCode=" + supplyCode + "]";
	}

}

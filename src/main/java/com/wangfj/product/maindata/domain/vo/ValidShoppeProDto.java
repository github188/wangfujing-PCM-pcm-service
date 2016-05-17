package com.wangfj.product.maindata.domain.vo;

public class ValidShoppeProDto {
	/* SKU+专柜+供应商 */
	/* SKUSid+专柜+销售单位+供应商 */
	/**
	 * 商品表SKU编码
	 */
	private String productDetailSid;
	/**
	 * 专柜Sid
	 */
	private String shoppeSid;
	/**
	 * 专柜编码
	 */
	private String shoppeCode;
	/**
	 * 供应商Sid
	 */
	private String supplySid;
	/**
	 * 供应商编码
	 */
	// private String supplyCode;
	/**
	 * 销售单位
	 */
	private String saleUnitCode;
	/**
	 * 条码
	 */
	private String barcode;
	/**
	 * 门店编码
	 */
	private String storeCode;

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

	public String getSaleUnitCode() {
		return saleUnitCode;
	}

	public void setSaleUnitCode(String saleUnitCode) {
		this.saleUnitCode = saleUnitCode;
	}

	public String getShoppeCode() {
		return shoppeCode;
	}

	public void setShoppeCode(String shoppeCode) {
		this.shoppeCode = shoppeCode;
	}

	// public String getSupplyCode() {
	// return supplyCode;
	// }
	//
	// public void setSupplyCode(String supplyCode) {
	// this.supplyCode = supplyCode;
	// }

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	@Override
	public String toString() {
		return "ValidShoppeProDto [productDetailSid=" + productDetailSid + ", shoppeSid="
				+ shoppeSid + ", shoppeCode=" + shoppeCode + ", supplySid=" + supplySid
				+ ", saleUnitCode=" + saleUnitCode + ", barcode=" + barcode + ", storeCode="
				+ storeCode + "]";
	}

}

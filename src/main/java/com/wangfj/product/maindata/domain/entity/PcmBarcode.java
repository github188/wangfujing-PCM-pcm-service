package com.wangfj.product.maindata.domain.entity;

import java.math.BigDecimal;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * 条码表
 * 
 * @Class Name PcmBarcode
 * @Author zhangxy
 * @Create In 2015年7月9日
 */
public class PcmBarcode extends BaseEntity {
	private Long sid;
	/**
	 * 条码
	 */
	private String barcode;
	/**
	 * 条码名称
	 */
	private String barcodeName;
	/**
	 * 条码单位
	 */
	private String barcodeUnit;
	/**
	 * 倍率
	 */
	private BigDecimal barcodeRate;
	/**
	 * 条码类型:0 单条码 ，1多条码（默认0）
	 */
	private Integer codeType;
	/**
	 * 专柜商品sid
	 */
	private String shoppeProSid;
	/**
	 * 门店编码
	 */
	private String storeCode;
	/**
	 * 商品ERP编码
	 */
	private String productCode;
	/**
	 * 供应商编码
	 */
	private String supplyCode;
	/**
	 * 专柜编码
	 */
	private String shoppeCode;
	/**
	 * 多包装的含量(不多包装为1)
	 */
	private BigDecimal saleMount;
	/**
	 * 售价
	 */
	private BigDecimal salePrice;
	private String originLand;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getBarcodeName() {
		return barcodeName;
	}

	public void setBarcodeName(String barcodeName) {
		this.barcodeName = barcodeName;
	}

	public String getBarcodeUnit() {
		return barcodeUnit;
	}

	public void setBarcodeUnit(String barcodeUnit) {
		this.barcodeUnit = barcodeUnit;
	}

	public BigDecimal getBarcodeRate() {
		return barcodeRate;
	}

	public void setBarcodeRate(BigDecimal barcodeRate) {
		this.barcodeRate = barcodeRate;
	}

	public Integer getCodeType() {
		return codeType;
	}

	public void setCodeType(Integer codeType) {
		this.codeType = codeType;
	}

	public String getShoppeProSid() {
		return shoppeProSid;
	}

	public void setShoppeProSid(String shoppeProSid) {
		this.shoppeProSid = shoppeProSid;
	}

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

	public String getSupplyCode() {
		return supplyCode;
	}

	public void setSupplyCode(String supplyCode) {
		this.supplyCode = supplyCode;
	}

	public String getShoppeCode() {
		return shoppeCode;
	}

	public void setShoppeCode(String shoppeCode) {
		this.shoppeCode = shoppeCode;
	}

	public String getOriginLand() {
		return originLand;
	}

	public void setOriginLand(String originLand) {
		this.originLand = originLand;
	}

	public BigDecimal getSaleMount() {
		return saleMount;
	}

	public void setSaleMount(BigDecimal saleMount) {
		this.saleMount = saleMount;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
}
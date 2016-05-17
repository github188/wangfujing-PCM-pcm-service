package com.wangfj.product.maindata.domain.entity;

import java.math.BigDecimal;

public class PcmBarcodeHis {
    private Long sid;

    private String barcode;

    private String barcodeName;

    private String barcodeUnit;

    private BigDecimal barcodeRate;

    private Integer codeType;

    private String shoppeProSid;

    private String storeCode;

    private String productCode;

    private String supplyCode;

    private String shoppeCode;

    private BigDecimal saleMount;

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

    public String getOriginLand() {
        return originLand;
    }

    public void setOriginLand(String originLand) {
        this.originLand = originLand;
    }
}
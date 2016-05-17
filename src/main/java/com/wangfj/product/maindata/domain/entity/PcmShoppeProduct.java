package com.wangfj.product.maindata.domain.entity;

import java.math.BigDecimal;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * 专柜商品表
 * 
 * @Class Name PcmShoppeProduct
 * @Author zhangxy
 * @Create In 2015年7月2日
 */
public class PcmShoppeProduct extends BaseEntity {
	/**
	 * @Field long serialVersionUID
	 */
	private static final long serialVersionUID = 8301128110139097633L;
	private Long sid;
	/**
	 * 专柜商品编码
	 */
	private String shoppeProSid;
	/**
	 * 商品表SKU编码
	 */
	private Long productDetailSid;
	/**
	 * 专柜编码
	 */
	private Long shoppeSid;
	/**
	 * 供应商编码
	 */
	private Long supplySid;
	/**
	 * 门店品牌编码
	 */
	private Long brandSid;
	/**
	 * 管理分类编码
	 */
	private String categorySid;
	/**
	 * 扣率码
	 */
	private String rateCode;
	/**
	 * 专柜商品名称
	 */
	private String shoppeProName;
	/**
	 * 专柜商品简称(别名)
	 */
	private String shoppeProAlias;
	/**
	 * 专柜商品可售状态:0 可售，1 不可售(默认为0）
	 */
	private Integer saleStatus;
	/**
	 * 专柜商品类型:0 普通商品，1 大码商品（默认为0）
	 */
	private Integer shoppeProType;
	/**
	 * 是否负库存销售 ：0 允许，1不允许（默认为0）
	 */
	private Integer negativeStock;
	/**
	 * 是否管库存 0 管库存，1 不管库存（默认为0）
	 */
	private Integer isStock;
	/**
	 * 计量单位
	 */
	private Long measureUnitDictSid;
	/**
	 * 包装单位
	 */
	private Long packUnitDictSid;
	/**
	 * 销售单位
	 */
	private String saleUnitCode;
	/**
	 * 大码
	 */
	private String erpproductcode;
	/**
	 * 供应商商品编码
	 */
	private String supplyProductCode;
	/**
	 * 是否支持货到付款(0支持；1不支持)
	 */
	private Integer isCod;
	/**
	 * 赠品范围（缺省值为0。 0表示正常商品， 1表示可以单独销售也可以作为本专柜内的赠品， 2表示可以单独销售也可以作为本门店内的赠品，
	 * 3表示可以单独销售也可以作为全渠道的赠品， 4表示不可单独销售但是可以作为本专柜内的赠品， 5表示不可单独销售但可以作为本门店内的赠品，
	 * 6表示不可单独销售但可以作为全渠道的赠品。）
	 */
	private Integer isGift;
	/**
	 * 是否可打折( 0 是,1 否, 默认0)
	 */
	private Integer isDiscountable;
	/**
	 * 物流属性
	 */
	private Integer tmsParam;
	/**
	 * 折扣底线（0到1.00的小数）
	 */
	private String discountLimit;
	/**
	 * 是否可包装(0可以，1不可以)
	 */
	private Integer isPacking;
	/**
	 * 原价
	 */
	private BigDecimal originalPrice;
	/**
	 * 库存方式
	 */
	private Integer stockMode;
	/**
	 * 进项税
	 */
	private BigDecimal inputTax;
	/**
	 * 销项税
	 */
	private BigDecimal outputTax;
	/**
	 * 销售税
	 */
	private BigDecimal salesTax;
	/**
	 * 扣率/进价
	 */
	private BigDecimal purchasePrice;
	/**
	 * 扣率/含税进价
	 */
	private BigDecimal buyingPrice;
	/**
	 * 加工方式
	 */
	private Integer processType;
	/**
	 * 产地
	 */
	private String originLand;
	/**
	 * 原产地
	 */
	private String originLand2;
	/**
	 * 订货方式
	 */
	private Integer orderType;
	/**
	 * 是否支持ERP促销
	 */
	private Integer isPromotion;
	/**
	 * 是否支持ERP变价
	 */
	private Integer isAdjustPrice;
	/**
	 * 预留
	 */
	private String field1;
	private String field2;
	private String field3;
	private String field4;
	private String field5;

	public String getShoppeProSid() {
		return shoppeProSid;
	}

	public void setShoppeProSid(String shoppeProSid) {
		this.shoppeProSid = shoppeProSid;
	}

	public Long getProductDetailSid() {
		return productDetailSid;
	}

	public void setProductDetailSid(Long productDetailSid) {
		this.productDetailSid = productDetailSid;
	}

	public Long getShoppeSid() {
		return shoppeSid;
	}

	public void setShoppeSid(Long shoppeSid) {
		this.shoppeSid = shoppeSid;
	}

	public Long getSupplySid() {
		return supplySid;
	}

	public void setSupplySid(Long supplySid) {
		this.supplySid = supplySid;
	}

	public Long getBrandSid() {
		return brandSid;
	}

	public void setBrandSid(Long brandSid) {
		this.brandSid = brandSid;
	}

	public String getCategorySid() {
		return categorySid;
	}

	public void setCategorySid(String categorySid) {
		this.categorySid = categorySid;
	}

	public String getSupplyProductCode() {
		return supplyProductCode;
	}

	public void setSupplyProductCode(String supplyProductCode) {
		this.supplyProductCode = supplyProductCode;
	}

	public Integer getIsCod() {
		return isCod;
	}

	public void setIsCod(Integer isCod) {
		this.isCod = isCod;
	}

	public Integer getIsGift() {
		return isGift;
	}

	public void setIsGift(Integer isGift) {
		this.isGift = isGift;
	}

	public Integer getIsDiscountable() {
		return isDiscountable;
	}

	public void setIsDiscountable(Integer isDiscountable) {
		this.isDiscountable = isDiscountable;
	}

	public Integer getTmsParam() {
		return tmsParam;
	}

	public void setTmsParam(Integer tmsParam) {
		this.tmsParam = tmsParam;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getRateCode() {
		return rateCode;
	}

	public void setRateCode(String rateCode) {
		this.rateCode = rateCode == null ? null : rateCode.trim();
	}

	public String getShoppeProName() {
		return shoppeProName;
	}

	public void setShoppeProName(String shoppeProName) {
		this.shoppeProName = shoppeProName == null ? null : shoppeProName.trim();
	}

	public Integer getSaleStatus() {
		return saleStatus;
	}

	public void setSaleStatus(Integer saleStatus) {
		this.saleStatus = saleStatus;
	}

	public Integer getShoppeProType() {
		return shoppeProType;
	}

	public void setShoppeProType(Integer shoppeProType) {
		this.shoppeProType = shoppeProType;
	}

	public Integer getNegativeStock() {
		return negativeStock;
	}

	public void setNegativeStock(Integer negativeStock) {
		this.negativeStock = negativeStock;
	}

	public Integer getIsStock() {
		return isStock;
	}

	public void setIsStock(Integer isStock) {
		this.isStock = isStock;
	}

	public Long getMeasureUnitDictSid() {
		return measureUnitDictSid;
	}

	public void setMeasureUnitDictSid(Long measureUnitDictSid) {
		this.measureUnitDictSid = measureUnitDictSid;
	}

	public Long getPackUnitDictSid() {
		return packUnitDictSid;
	}

	public void setPackUnitDictSid(Long packUnitDictSid) {
		this.packUnitDictSid = packUnitDictSid;
	}

	public String getSaleUnitCode() {
		return saleUnitCode;
	}

	public void setSaleUnitCode(String saleUnitCode) {
		this.saleUnitCode = saleUnitCode == null ? null : saleUnitCode.trim();
	}

	public String getErpproductcode() {
		return erpproductcode;
	}

	public void setErpproductcode(String erpproductcode) {
		this.erpproductcode = erpproductcode == null ? null : erpproductcode.trim();
	}

	public String getDiscountLimit() {
		return discountLimit;
	}

	public void setDiscountLimit(String discountLimit) {
		this.discountLimit = discountLimit;
	}

	public Integer getIsPacking() {
		return isPacking;
	}

	public void setIsPacking(Integer isPacking) {
		this.isPacking = isPacking;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

	public String getField4() {
		return field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}

	public String getField5() {
		return field5;
	}

	public void setField5(String field5) {
		this.field5 = field5;
	}

	public Integer getStockMode() {
		return stockMode;
	}

	public void setStockMode(Integer stockMode) {
		this.stockMode = stockMode;
	}

	public BigDecimal getInputTax() {
		return inputTax;
	}

	public void setInputTax(BigDecimal inputTax) {
		this.inputTax = inputTax;
	}

	public BigDecimal getOutputTax() {
		return outputTax;
	}

	public void setOutputTax(BigDecimal outputTax) {
		this.outputTax = outputTax;
	}

	public BigDecimal getSalesTax() {
		return salesTax;
	}

	public void setSalesTax(BigDecimal salesTax) {
		this.salesTax = salesTax;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public BigDecimal getBuyingPrice() {
		return buyingPrice;
	}

	public void setBuyingPrice(BigDecimal buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	public Integer getProcessType() {
		return processType;
	}

	public void setProcessType(Integer processType) {
		this.processType = processType;
	}

	public String getOriginLand() {
		return originLand;
	}

	public void setOriginLand(String originLand) {
		this.originLand = originLand;
	}

	public String getOriginLand2() {
		return originLand2;
	}

	public void setOriginLand2(String originLand2) {
		this.originLand2 = originLand2;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
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

	public String getShoppeProAlias() {
		return shoppeProAlias;
	}

	public void setShoppeProAlias(String shoppeProAlias) {
		this.shoppeProAlias = shoppeProAlias;
	}

	@Override
	public String toString() {
		return "PcmShoppeProduct{" + "sid=" + sid + ", shoppeProSid='" + shoppeProSid + '\''
				+ ", productDetailSid='" + productDetailSid + '\'' + ", shoppeSid='" + shoppeSid
				+ '\'' + ", supplySid='" + supplySid + '\'' + ", brandSid='" + brandSid + '\''
				+ ", categorySid='" + categorySid + '\'' + ", rateCode='" + rateCode + '\''
				+ ", shoppeProName='" + shoppeProName + '\'' + ", shoppeProAlias='"
				+ shoppeProAlias + '\'' + ", saleStatus=" + saleStatus + ", shoppeProType="
				+ shoppeProType + ", negativeStock=" + negativeStock + ", isStock=" + isStock
				+ ", measureUnitDictSid=" + measureUnitDictSid + ", packUnitDictSid="
				+ packUnitDictSid + ", saleUnitCode='" + saleUnitCode + '\'' + ", erpproductcode='"
				+ erpproductcode + '\'' + ", supplyProductCode='" + supplyProductCode + '\''
				+ ", isCod=" + isCod + ", isGift=" + isGift + ", isDiscountable=" + isDiscountable
				+ ", tmsParam=" + tmsParam + ", discountLimit='" + discountLimit + '\''
				+ ", isPacking=" + isPacking + ", originalPrice=" + originalPrice + ", stockMode="
				+ stockMode + ", inputTax=" + inputTax + ", outputTax=" + outputTax + ", salesTax="
				+ salesTax + ", purchasePrice=" + purchasePrice + ", buyingPrice=" + buyingPrice
				+ ", processType=" + processType + ", originLand='" + originLand + '\''
				+ ", originLand2='" + originLand2 + '\'' + ", orderType=" + orderType
				+ ", isPromotion=" + isPromotion + ", isAdjustPrice=" + isAdjustPrice
				+ ", field1='" + field1 + '\'' + ", field2='" + field2 + '\'' + ", field3='"
				+ field3 + '\'' + ", field4='" + field4 + '\'' + ", field5='" + field5 + '\'' + '}';
	}
}
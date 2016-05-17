package com.wangfj.product.maindata.domain.vo;

import java.math.BigDecimal;
import java.util.List;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * 创建SPU
 * 
 * @Class Name CreateSpuDto
 * @Author liuhp
 * @Create In 2015-8-17
 */
public class CreateShoppePro extends BaseDto {

	/**
	 * 商品表SKUSid
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
	private String supplyCode;
	/**
	 * 门店（集团）品牌sid
	 */
	private String brandSid;
	/**
	 * 管理分类编码
	 */
	private String categorySid;
	/**
	 * 统计分类sid
	 */
	private Long categoryTJSid;
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
	 * 是否管库存 0 管库存，1 不管库存（默认为0）
	 */
	private Integer isStock;
	/**
	 * 计量单位(暂时不用)
	 */
	private Long measureUnitDictSid;
	/**
	 * 包装单位（暂时不用）
	 */
	private Long packUnitDictSid;
	/**
	 * 销售单位sid
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
	 * 标志位0百货，1超市
	 */
	private Integer flag;

	/**
	 * 经营方式0自营，1联营
	 */
	private Integer businessType;

	/**
	 * 门店编码
	 */
	private String shopCode;

	/**
	 * 标准条码
	 */
	private List<BarcodeDto> barcode;

	/**
	 * 要约号
	 */
	private String offerNo;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 售价
	 */
	private BigDecimal salePrice;
	private String field2;
	private String field3;// 货号
	private String field4;// 原系统商品编码

	public List<BarcodeDto> getBarcode() {
		return barcode;
	}

	public void setBarcode(List<BarcodeDto> barcode) {
		this.barcode = barcode;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
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

	public String getBrandSid() {
		return brandSid;
	}

	public void setBrandSid(String brandSid) {
		this.brandSid = brandSid;
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

	public String getShoppeProName() {
		return shoppeProName;
	}

	public void setShoppeProName(String shoppeProName) {
		this.shoppeProName = shoppeProName;
	}

	public String getShoppeProAlias() {
		return shoppeProAlias;
	}

	public void setShoppeProAlias(String shoppeProAlias) {
		this.shoppeProAlias = shoppeProAlias;
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
		this.saleUnitCode = saleUnitCode;
	}

	public String getErpproductcode() {
		return erpproductcode;
	}

	public void setErpproductcode(String erpproductcode) {
		this.erpproductcode = erpproductcode;
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

	public String getOfferNo() {
		return offerNo;
	}

	public void setOfferNo(String offerNo) {
		this.offerNo = offerNo;
	}

	public void setTmsParam(Integer tmsParam) {
		this.tmsParam = tmsParam;
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

	public String getShoppeCode() {
		return shoppeCode;
	}

	public void setShoppeCode(String shoppeCode) {
		this.shoppeCode = shoppeCode;
	}

	public String getSupplyCode() {
		return supplyCode;
	}

	public void setSupplyCode(String supplyCode) {
		this.supplyCode = supplyCode;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
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

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
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

	public Long getCategoryTJSid() {
		return categoryTJSid;
	}

	public void setCategoryTJSid(Long categoryTJSid) {
		this.categoryTJSid = categoryTJSid;
	}

	@Override
	public String toString() {
		return "CreateShoppePro [productDetailSid=" + productDetailSid + ", shoppeSid=" + shoppeSid
				+ ", shoppeCode=" + shoppeCode + ", supplySid=" + supplySid + ", supplyCode="
				+ supplyCode + ", brandSid=" + brandSid + ", categorySid=" + categorySid
				+ ", categoryTJSid=" + categoryTJSid + ", rateCode=" + rateCode
				+ ", shoppeProName=" + shoppeProName + ", shoppeProAlias=" + shoppeProAlias
				+ ", saleStatus=" + saleStatus + ", shoppeProType=" + shoppeProType + ", isStock="
				+ isStock + ", measureUnitDictSid=" + measureUnitDictSid + ", packUnitDictSid="
				+ packUnitDictSid + ", saleUnitCode=" + saleUnitCode + ", erpproductcode="
				+ erpproductcode + ", supplyProductCode=" + supplyProductCode + ", isCod=" + isCod
				+ ", isGift=" + isGift + ", isDiscountable=" + isDiscountable + ", tmsParam="
				+ tmsParam + ", discountLimit=" + discountLimit + ", isPacking=" + isPacking
				+ ", originalPrice=" + originalPrice + ", stockMode=" + stockMode + ", inputTax="
				+ inputTax + ", outputTax=" + outputTax + ", salesTax=" + salesTax
				+ ", purchasePrice=" + purchasePrice + ", buyingPrice=" + buyingPrice
				+ ", processType=" + processType + ", originLand=" + originLand + ", originLand2="
				+ originLand2 + ", orderType=" + orderType + ", isPromotion=" + isPromotion
				+ ", isAdjustPrice=" + isAdjustPrice + ", flag=" + flag + ", businessType="
				+ businessType + ", shopCode=" + shopCode + ", barcode=" + barcode + ", offerNo="
				+ offerNo + ", remarks=" + remarks + ", salePrice=" + salePrice + ", field2="
				+ field2 + ", field3=" + field3 + ", field4=" + field4 + "]";
	}

}

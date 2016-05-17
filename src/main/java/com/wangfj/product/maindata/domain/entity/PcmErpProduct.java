package com.wangfj.product.maindata.domain.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * ERP商品编码表(大码表)
 * 
 * @Class Name PcmErpProduct
 * @Author zhangxy
 * @Create In 2015年7月9日
 */
public class PcmErpProduct extends BaseEntity {
	private Long sid;
	/**
	 * 门店编码
	 */
	private String storeCode;
	/**
	 * 门店名称
	 */
	private String shopName;
	/**
	 * 专柜编码
	 */
	private String shoppeCode;
	/**
	 * 专柜名称
	 */
	private String shoppeName;
	/**
	 * 商品编码（大码）
	 */
	private String productCode;
	/**
	 * 大码类型:0单品码,1长期统码,2特卖统码,3促销统码,4促销扣率码,5价格码,6扣率码
	 */
	private Integer codeType;
	/**
	 * 大码价格
	 */
	private BigDecimal bigCodePrice;
	/**
	 * 门店供应商编码
	 */
	private String supplyCode;
	/**
	 * 要约号
	 */
	private String offerNo;
	/**
	 * 门店品牌编码
	 */
	private String brandCode;
	/**
	 * 集团品牌编码
	 */
	private String groupBrandCode;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 操作用户sid
	 */
	private String optUserSid;
	/**
	 * 商品名称
	 */
	private String productName;
	/**
	 * 商品经营方式
	 */
	private Integer productType;
	/**
	 * 商品类别:01 自营单品,05 金银首饰,06 服务费商品,07 租赁商品,,08 联营单品,09 组包码
	 */
	private Integer productCategory;
	/**
	 * 规格名称（例如250ml）
	 */
	private String stanName;
	/**
	 * 货号
	 */
	private String articleNum;
	/**
	 * 销售计量单位（上传的是单位的文本信息，例如瓶）
	 */
	private String salesUnit;
	/**
	 * 供应商条码
	 */
	private String supplierBarcode;
	/**
	 * 售价
	 */
	private BigDecimal salesPrice;
	/**
	 * 进项税
	 */
	private BigDecimal inputTax;
	/**
	 * 销项税
	 */
	private BigDecimal outputTax;
	/**
	 * 消费税
	 */
	private BigDecimal salesTax;
	/**
	 * 商品状态(0正常；1停售；2停货；3暂停使用；4已删除；5淘汰)
	 */
	private Integer proStatus;
	/**
	 * 是否允许促销(0 允许 , 1 不允许)
	 */
	private Integer isPromotion;
	/**
	 * 是否允许调价(0 允许 ,1 不允许)
	 */
	private Integer isAdjustPrice;
	/**
	 * 所属末级管理分类的编码
	 */
	private String manageCategory;
	/**
	 * 折扣底限(0到1.00之间的小数)
	 */
	private BigDecimal discountLimit;
	/**
	 * 产地（文本信息）
	 */
	private String originLand;
	/**
	 * 服务费类型
	 */
	private String serviceFeeType;
	/**
	 * 业态类型（1百货、2超市、3电商）
	 */
	private Integer formatType;
	/**
	 * 所属末级统计分类的编码
	 */
	private String statCategory;
	/**
	 * 标准扣率（0到100之间的百分数，最多六位小数，只传数值不传%号，比如20.123%只传20.123。）
	 */
	private BigDecimal commissionRate;
	// 商品简称
	private String productAbbr;
	// 备注
	private String notes;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShoppeCode() {
		return shoppeCode;
	}

	public void setShoppeCode(String shoppeCode) {
		this.shoppeCode = shoppeCode;
	}

	public String getShoppeName() {
		return shoppeName;
	}

	public void setShoppeName(String shoppeName) {
		this.shoppeName = shoppeName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Integer getCodeType() {
		return codeType;
	}

	public void setCodeType(Integer codeType) {
		this.codeType = codeType;
	}

	public BigDecimal getBigCodePrice() {
		return bigCodePrice;
	}

	public void setBigCodePrice(BigDecimal bigCodePrice) {
		this.bigCodePrice = bigCodePrice;
	}

	public String getSupplyCode() {
		return supplyCode;
	}

	public void setSupplyCode(String supplyCode) {
		this.supplyCode = supplyCode;
	}

	public String getOfferNo() {
		return offerNo;
	}

	public void setOfferNo(String offerNo) {
		this.offerNo = offerNo;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getOptUserSid() {
		return optUserSid;
	}

	public void setOptUserSid(String optUserSid) {
		this.optUserSid = optUserSid;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public Integer getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(Integer productCategory) {
		this.productCategory = productCategory;
	}

	public String getStanName() {
		return stanName;
	}

	public void setStanName(String stanName) {
		this.stanName = stanName;
	}

	public String getArticleNum() {
		return articleNum;
	}

	public void setArticleNum(String articleNum) {
		this.articleNum = articleNum;
	}

	public String getSalesUnit() {
		return salesUnit;
	}

	public void setSalesUnit(String salesUnit) {
		this.salesUnit = salesUnit;
	}

	public String getSupplierBarcode() {
		return supplierBarcode;
	}

	public void setSupplierBarcode(String supplierBarcode) {
		this.supplierBarcode = supplierBarcode;
	}

	public BigDecimal getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(BigDecimal salesPrice) {
		this.salesPrice = salesPrice;
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

	public Integer getProStatus() {
		return proStatus;
	}

	public void setProStatus(Integer proStatus) {
		this.proStatus = proStatus;
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

	public String getManageCategory() {
		return manageCategory;
	}

	public void setManageCategory(String manageCategory) {
		this.manageCategory = manageCategory;
	}

	public BigDecimal getDiscountLimit() {
		return discountLimit;
	}

	public void setDiscountLimit(BigDecimal discountLimit) {
		this.discountLimit = discountLimit;
	}

	public String getOriginLand() {
		return originLand;
	}

	public void setOriginLand(String originLand) {
		this.originLand = originLand;
	}

	public String getServiceFeeType() {
		return serviceFeeType;
	}

	public void setServiceFeeType(String serviceFeeType) {
		this.serviceFeeType = serviceFeeType;
	}

	public Integer getFormatType() {
		return formatType;
	}

	public void setFormatType(Integer formatType) {
		this.formatType = formatType;
	}

	public String getStatCategory() {
		return statCategory;
	}

	public void setStatCategory(String statCategory) {
		this.statCategory = statCategory;
	}

	public BigDecimal getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(BigDecimal commissionRate) {
		this.commissionRate = commissionRate;
	}

	public String getGroupBrandCode() {
		return groupBrandCode;
	}

	public void setGroupBrandCode(String groupBrandCode) {
		this.groupBrandCode = groupBrandCode;
	}

	public String getProductAbbr() {
		return productAbbr;
	}

	public void setProductAbbr(String productAbbr) {
		this.productAbbr = productAbbr;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
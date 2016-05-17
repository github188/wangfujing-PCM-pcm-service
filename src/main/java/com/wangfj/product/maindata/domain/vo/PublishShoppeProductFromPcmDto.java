package com.wangfj.product.maindata.domain.vo;

/**
 * 专柜商品下发DTO
 * 
 * @Class Name PublishShoppeProductFromPcmDto
 * @Author zhangxy
 * @Create In 2015年7月15日
 */
public class PublishShoppeProductFromPcmDto {
	private String code;// 专柜商品编码
	/**
	 * 商品表SKU 编码
	 */
	private String productCode;
	/**
	 * 产品编SPU 编码
	 */
	private String baseProductCode;
	private String productAbbr;
	private String productName;// 专柜商品名称
	/**
	 * 供应商编码
	 */
	private String supplierCode;
	/**
	 * 专柜编码
	 */
	private String counterCode;
	/**
	 * 大码
	 */
	private String erpProductCode;
	/**
	 * 门店编码
	 */
	private String storeCode;
	/**
	 * 管理分类编码
	 */
	private String manageCategory;
	/**
	 * 经营方式编码
	 */
	private String operateMode;
	/**
	 * 折扣底线
	 */
	private String maxDiscountRate;
	/**
	 * 商品状态
	 */
	private String productStatus;
	/**
	 * 尺码
	 */
	private String sizeCode;
	/**
	 * 色码
	 */
	private String styleCode;
	/**
	 * 销售单位编码
	 */
	private String unitCode;
	/**
	 * 市场价
	 */
	private String marketPrice;
	/**
	 * 零售价
	 */
	private String salesPrice;
	/**
	 * 供应商内部自编条码
	 */
	private String supplierIntBarCode;

	/**
	 * 款号
	 */
	private String modelNumber;
	/**
	 * 货号
	 */
	private String materialNum;

	/**
	 * 操作标记 0 添加 1修改 2删除
	 */
	private String actionCode;
	/**
	 * 进项税
	 */
	private String inputTax;
	/**
	 * 销项税
	 */
	private String outputTax;
	/**
	 * 消费税
	 */
	private String salesTax;
	/**
	 * 扣率/进价
	 */
	private String purchasePrice;
	/**
	 * 扣率/含税进价
	 */
	private String buyingPrice;
	/**
	 * 加工类型编码 1.单品 2.分割原材料 3.原材料 4.成品
	 */
	private String processType;
	/**
	 * 产地编码
	 */
	private String originLand;
	/**
	 * 订货类别 1.集采统配 2.集采直入 3.集采补货 4.不管库存 5.门店订货
	 */
	private String orderType;
	/**
	 * 是否允许ERP促销 (Y/N)
	 */
	private String isPromotion;
	/**
	 * 是否允许EPR调价 (Y/N)
	 */
	private String isAdjustPrice;
	/**
	 * 要约编号
	 */
	private String contractCode;
	/**
	 * 采购人员编码
	 */
	private String procurementUserCode;
	/**
	 * 录入人员编码
	 */
	private String inputUserCode;
	/**
	 * 统计分类
	 */
	private String statCategory;
	/**
	 * 先销后采
	 */
	private String isSelllPurchase;
	/**
	 * 备注
	 */
	private String notes;// 备注

	private String BrandCode;
	private String billsNo;// 单据号
	private String actionDate;
	private String actionPerson;

	public String getProductAbbr() {
		return productAbbr;
	}

	public void setProductAbbr(String productAbbr) {
		this.productAbbr = productAbbr;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getBaseProductCode() {
		return baseProductCode;
	}

	public void setBaseProductCode(String baseProductCode) {
		this.baseProductCode = baseProductCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getCounterCode() {
		return counterCode;
	}

	public void setCounterCode(String counterCode) {
		this.counterCode = counterCode;
	}

	public String getErpProductCode() {
		return erpProductCode;
	}

	public void setErpProductCode(String erpProductCode) {
		this.erpProductCode = erpProductCode;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getManageCategory() {
		return manageCategory;
	}

	public void setManageCategory(String manageCategory) {
		this.manageCategory = manageCategory;
	}

	public String getOperateMode() {
		return operateMode;
	}

	public void setOperateMode(String operateMode) {
		this.operateMode = operateMode;
	}

	public String getMaxDiscountRate() {
		return maxDiscountRate;
	}

	public void setMaxDiscountRate(String maxDiscountRate) {
		this.maxDiscountRate = maxDiscountRate;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public String getSizeCode() {
		return sizeCode;
	}

	public void setSizeCode(String sizeCode) {
		this.sizeCode = sizeCode;
	}

	public String getStyleCode() {
		return styleCode;
	}

	public void setStyleCode(String styleCode) {
		this.styleCode = styleCode;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(String salesPrice) {
		this.salesPrice = salesPrice;
	}

	public String getSupplierIntBarCode() {
		return supplierIntBarCode;
	}

	public void setSupplierIntBarCode(String supplierIntBarCode) {
		this.supplierIntBarCode = supplierIntBarCode;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getMaterialNum() {
		return materialNum;
	}

	public void setMaterialNum(String materialNum) {
		this.materialNum = materialNum;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getInputTax() {
		return inputTax;
	}

	public void setInputTax(String inputTax) {
		this.inputTax = inputTax;
	}

	public String getOutputTax() {
		return outputTax;
	}

	public void setOutputTax(String outputTax) {
		this.outputTax = outputTax;
	}

	public String getSalesTax() {
		return salesTax;
	}

	public void setSalesTax(String salesTax) {
		this.salesTax = salesTax;
	}

	public String getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getBuyingPrice() {
		return buyingPrice;
	}

	public void setBuyingPrice(String buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public String getOriginLand() {
		return originLand;
	}

	public void setOriginLand(String originLand) {
		this.originLand = originLand;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getIsPromotion() {
		return isPromotion;
	}

	public void setIsPromotion(String isPromotion) {
		this.isPromotion = isPromotion;
	}

	public String getIsAdjustPrice() {
		return isAdjustPrice;
	}

	public void setIsAdjustPrice(String isAdjustPrice) {
		this.isAdjustPrice = isAdjustPrice;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getProcurementUserCode() {
		return procurementUserCode;
	}

	public void setProcurementUserCode(String procurementUserCode) {
		this.procurementUserCode = procurementUserCode;
	}

	public String getInputUserCode() {
		return inputUserCode;
	}

	public void setInputUserCode(String inputUserCode) {
		this.inputUserCode = inputUserCode;
	}

	public String getStatCategory() {
		return statCategory;
	}

	public void setStatCategory(String statCategory) {
		this.statCategory = statCategory;
	}

	public String getIsSelllPurchase() {
		return isSelllPurchase;
	}

	public void setIsSelllPurchase(String isSelllPurchase) {
		this.isSelllPurchase = isSelllPurchase;
	}

	public String getBrandCode() {
		return BrandCode;
	}

	public void setBrandCode(String brandCode) {
		BrandCode = brandCode;
	}

	public String getActionDate() {
		return actionDate;
	}

	public void setActionDate(String actionDate) {
		this.actionDate = actionDate;
	}

	public String getActionPerson() {
		return actionPerson;
	}

	public void setActionPerson(String actionPerson) {
		this.actionPerson = actionPerson;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getBillsNo() {
		return billsNo;
	}

	public void setBillsNo(String billsNo) {
		this.billsNo = billsNo;
	}

	@Override
	public String toString() {
		return "PublishShoppeProductFromPcmDto [code=" + code + ", productCode=" + productCode
				+ ", baseProductCode=" + baseProductCode + ", productAbbr=" + productAbbr
				+ ", productName=" + productName + ", supplierCode=" + supplierCode
				+ ", counterCode=" + counterCode + ", erpProductCode=" + erpProductCode
				+ ", storeCode=" + storeCode + ", manageCategory=" + manageCategory
				+ ", operateMode=" + operateMode + ", maxDiscountRate=" + maxDiscountRate
				+ ", productStatus=" + productStatus + ", sizeCode=" + sizeCode + ", styleCode="
				+ styleCode + ", unitCode=" + unitCode + ", marketPrice=" + marketPrice
				+ ", salesPrice=" + salesPrice + ", supplierIntBarCode=" + supplierIntBarCode
				+ ", modelNumber=" + modelNumber + ", materialNum=" + materialNum + ", actionCode="
				+ actionCode + ", inputTax=" + inputTax + ", outputTax=" + outputTax
				+ ", salesTax=" + salesTax + ", purchasePrice=" + purchasePrice + ", buyingPrice="
				+ buyingPrice + ", processType=" + processType + ", originLand=" + originLand
				+ ", orderType=" + orderType + ", isPromotion=" + isPromotion + ", isAdjustPrice="
				+ isAdjustPrice + ", contractCode=" + contractCode + ", procurementUserCode="
				+ procurementUserCode + ", inputUserCode=" + inputUserCode + ", statCategory="
				+ statCategory + ", isSelllPurchase=" + isSelllPurchase + ", notes=" + notes
				+ ", BrandCode=" + BrandCode + ", billsNo=" + billsNo + ", actionDate="
				+ actionDate + ", actionPerson=" + actionPerson + "]";
	}

}

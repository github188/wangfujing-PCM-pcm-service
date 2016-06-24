package com.wangfj.product.maindata.domain.vo;

import java.util.ArrayList;
import java.util.List;

public class PcmProSearchDto {
	private String sid;// 专柜商品SID
	private String skuCode;// SKU编码
	private String productCode;// 专柜商品编码
	private String erpProductCode;// ERP编码/扣率码
	private String productName;// 专柜商品名称
	private String productType;// 商品类型
	private String supProductCode;// 供应商商品编码
	private String counterCode;// 专柜编码
	private String counterName;// 专柜名称
	private String storeCode;// 门店编码
	private String storeName;// 门店名称
	private String marketPrice;// 市场价
	private String stockMode;// 库存方式
	private String negativeStock;// 是否负库存销售
	private String stockType;// 是否管库
	private String isSale;// 是否可售
	private String colorSid;// 色系
	private String colorCode;// 色码编码
	private String colorName;// 色码名称
	private String stanCode;// 规格编码
	private String stanName;// 规格名称
	private String modelCode;// 款号
	private String articleNum;// 货号
	private String brandGroupName;// 集团品牌名称
	private String brandGroupCode;// 集团品牌编码
	private String unitCode;// 销售单位编码
	private String unitName;// 销售单位名称
	private String isSelling;// 上架状态
	private String features;// 特性
	private String primaryAttr;// 主属性
	private String industry;// 业态
	private String storeBrandCode;// 门店品牌编码
	private String storeBrandName;// 门店品牌名称
	private String managerCategoryCode;// 管理分类编码
	private String managerCategoryName;// 管理分类名称
	private String field4;// 物料号 可空
	private String xxhcflag;// 先销后采
	List<PcmProChannelSearchDto> channel = new ArrayList<PcmProChannelSearchDto>();
	List<PcmProSupplySerachDto> supplier = new ArrayList<PcmProSupplySerachDto>();

	public String getXxhcflag() {
		return xxhcflag;
	}

	public void setXxhcflag(String xxhcflag) {
		this.xxhcflag = xxhcflag;
	}

	public String getField4() {
		return field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}

	public String getStoreBrandCode() {
		return storeBrandCode;
	}

	public void setStoreBrandCode(String storeBrandCode) {
		this.storeBrandCode = storeBrandCode;
	}

	public String getStoreBrandName() {
		return storeBrandName;
	}

	public void setStoreBrandName(String storeBrandName) {
		this.storeBrandName = storeBrandName;
	}

	public String getManagerCategoryCode() {
		return managerCategoryCode;
	}

	public void setManagerCategoryCode(String managerCategoryCode) {
		this.managerCategoryCode = managerCategoryCode;
	}

	public String getManagerCategoryName() {
		return managerCategoryName;
	}

	public void setManagerCategoryName(String managerCategoryName) {
		this.managerCategoryName = managerCategoryName;
	}

	public void setChannel(List<PcmProChannelSearchDto> channel) {
		this.channel = channel;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getErpProductCode() {
		return erpProductCode;
	}

	public void setErpProductCode(String erpProductCode) {
		this.erpProductCode = erpProductCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getSupProductCode() {
		return supProductCode;
	}

	public void setSupProductCode(String supProductCode) {
		this.supProductCode = supProductCode;
	}

	public String getCounterCode() {
		return counterCode;
	}

	public void setCounterCode(String counterCode) {
		this.counterCode = counterCode;
	}

	public String getCounterName() {
		return counterName;
	}

	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getStockMode() {
		return stockMode;
	}

	public void setStockMode(String stockMode) {
		this.stockMode = stockMode;
	}

	public String getNegativeStock() {
		return negativeStock;
	}

	public void setNegativeStock(String negativeStock) {
		this.negativeStock = negativeStock;
	}

	public String getStockType() {
		return stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
	}

	public String getIsSale() {
		return isSale;
	}

	public void setIsSale(String isSale) {
		this.isSale = isSale;
	}

	public String getColorSid() {
		return colorSid;
	}

	public void setColorSid(String colorSid) {
		this.colorSid = colorSid;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getStanCode() {
		return stanCode;
	}

	public void setStanCode(String stanCode) {
		this.stanCode = stanCode;
	}

	public String getStanName() {
		return stanName;
	}

	public void setStanName(String stanName) {
		this.stanName = stanName;
	}

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getArticleNum() {
		return articleNum;
	}

	public void setArticleNum(String articleNum) {
		this.articleNum = articleNum;
	}

	public String getBrandGroupName() {
		return brandGroupName;
	}

	public void setBrandGroupName(String brandGroupName) {
		this.brandGroupName = brandGroupName;
	}

	public String getBrandGroupCode() {
		return brandGroupCode;
	}

	public void setBrandGroupCode(String brandGroupCode) {
		this.brandGroupCode = brandGroupCode;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getIsSelling() {
		return isSelling;
	}

	public void setIsSelling(String isSelling) {
		this.isSelling = isSelling;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getPrimaryAttr() {
		return primaryAttr;
	}

	public void setPrimaryAttr(String primaryAttr) {
		this.primaryAttr = primaryAttr;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public List<PcmProChannelSearchDto> getChannel() {
		return channel;
	}

	public void setChannelList(List<PcmProChannelSearchDto> channel) {
		this.channel = channel;
	}

	public List<PcmProSupplySerachDto> getSupplier() {
		return supplier;
	}

	public void setSupplier(List<PcmProSupplySerachDto> supplier) {
		this.supplier = supplier;
	}

	@Override
	public String toString() {
		return "PcmProSearchDto [sid=" + sid + ", skuCode=" + skuCode + ", productCode="
				+ productCode + ", erpProductCode=" + erpProductCode + ", productName="
				+ productName + ", productType=" + productType + ", supProductCode="
				+ supProductCode + ", counterCode=" + counterCode + ", counterName=" + counterName
				+ ", storeCode=" + storeCode + ", storeName=" + storeName + ", marketPrice="
				+ marketPrice + ", stockMode=" + stockMode + ", negativeStock=" + negativeStock
				+ ", stockType=" + stockType + ", isSale=" + isSale + ", colorSid=" + colorSid
				+ ", colorCode=" + colorCode + ", colorName=" + colorName + ", stanCode="
				+ stanCode + ", stanName=" + stanName + ", modelCode=" + modelCode
				+ ", articleNum=" + articleNum + ", brandGroupName=" + brandGroupName
				+ ", brandGroupCode=" + brandGroupCode + ", unitCode=" + unitCode + ", unitName="
				+ unitName + ", isSelling=" + isSelling + ", features=" + features
				+ ", primaryAttr=" + primaryAttr + ", industry=" + industry + ", channel="
				+ channel + ", supplier=" + supplier + "]";
	}

}

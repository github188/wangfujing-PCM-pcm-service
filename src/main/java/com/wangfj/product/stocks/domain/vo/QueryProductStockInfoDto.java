package com.wangfj.product.stocks.domain.vo;

import java.util.List;

public class QueryProductStockInfoDto {
	private Long sid;
	private String productCode;// 专柜商品编码
	private List<String> productCodeList;// 专柜商品编码List
	private String erpProductCode;// 大码
	private String skuCode;// 商品表SKU 编码
	private String productName;// 专柜商品名称
	private String productType;// 商品类型 0 普通商品（实物类），1 赠品 ，2 礼品，3 虚拟商品（充值卡，购物卡），
	// 4服务类商品 （礼品包装，购物接送服务，停车服务）（注：礼品不可卖，赠品可卖）
	private String supplierCode;// 供应商编码
	private String supplierName;// 供应商名称
	private String counterCode;// 专柜编码
	private String counterName;// 专柜名
	private String storeCode;// 门店编码
	private String storeName;// 门店名称
	private String channelSid;// 渠道sid
	private String channelName;// 渠道名称
	private String salesPrice;// 零售价
	private String marketPrice;// 市场价
	private String priceType;// 价格类型
	private String stockType;// 库存方式
	private String stockMode;// 库存方式(自库 虚库 不管库)
	private String negativeStock;// 是否负库存销售
	private String isSale;// 专柜商品可售状态
	private String saleStock;// 可售库存
	private String edefectiveStock;// 残次品库存
	private String returnStock;// 退货库存
	private String lockedStock;// 锁定库存
	private String colorCode;// 颜色编码
	private String colorName;// 颜色名称
	private String stanCode;// 规格编码
	private String stanName;// 规格名称
	private String modelCode;// 款号
	private String articleNum;// 货号
	private String brandGroupCode;// 集团品牌编码
	private String brandGroupName;// 集团品牌名称
	private String unitCode;// 销售单位编码
	private String unitName;// 销售单位名
	/**
	 * 当前页
	 */
	private Integer currentPage = 1;

	/**
	 * 单页行数 默认20
	 */
	private Integer pageSize = 20;
	private Integer start;
	private Integer limit;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
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

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
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

	public String getChannelSid() {
		return channelSid;
	}

	public void setChannelSid(String channelSid) {
		this.channelSid = channelSid;
	}

	public String getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(String salesPrice) {
		this.salesPrice = salesPrice;
	}

	public String getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String getStockType() {
		return stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
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

	public String getIsSale() {
		return isSale;
	}

	public void setIsSale(String isSale) {
		this.isSale = isSale;
	}

	public String getSaleStock() {
		return saleStock;
	}

	public void setSaleStock(String saleStock) {
		this.saleStock = saleStock;
	}

	public String getEdefectiveStock() {
		return edefectiveStock;
	}

	public void setEdefectiveStock(String edefectiveStock) {
		this.edefectiveStock = edefectiveStock;
	}

	public String getReturnStock() {
		return returnStock;
	}

	public void setReturnStock(String returnStock) {
		this.returnStock = returnStock;
	}

	public String getLockedStock() {
		return lockedStock;
	}

	public void setLockedStock(String lockedStock) {
		this.lockedStock = lockedStock;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getStart() {
		this.start = 0;
		if (this.currentPage > 1 && this.pageSize > 0) {
			this.start = (this.currentPage - 1) * this.pageSize;
		}
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		if (this.pageSize != null)
			this.limit = this.pageSize;
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getErpProductCode() {
		return erpProductCode;
	}

	public void setErpProductCode(String erpProductCode) {
		this.erpProductCode = erpProductCode;
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

	public String getBrandGroupCode() {
		return brandGroupCode;
	}

	public void setBrandGroupCode(String brandGroupCode) {
		this.brandGroupCode = brandGroupCode;
	}

	public String getBrandGroupName() {
		return brandGroupName;
	}

	public void setBrandGroupName(String brandGroupName) {
		this.brandGroupName = brandGroupName;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
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

	public List<String> getProductCodeList() {
		return productCodeList;
	}

	public void setProductCodeList(List<String> productCodeList) {
		this.productCodeList = productCodeList;
	}

	@Override
	public String toString() {
		return "QueryProductStockInfoDto{" +
				"sid=" + sid +
				", productCode='" + productCode + '\'' +
				", productCodeList=" + productCodeList +
				", erpProductCode='" + erpProductCode + '\'' +
				", skuCode='" + skuCode + '\'' +
				", productName='" + productName + '\'' +
				", productType='" + productType + '\'' +
				", supplierCode='" + supplierCode + '\'' +
				", supplierName='" + supplierName + '\'' +
				", counterCode='" + counterCode + '\'' +
				", counterName='" + counterName + '\'' +
				", storeCode='" + storeCode + '\'' +
				", storeName='" + storeName + '\'' +
				", channelSid='" + channelSid + '\'' +
				", channelName='" + channelName + '\'' +
				", salesPrice='" + salesPrice + '\'' +
				", marketPrice='" + marketPrice + '\'' +
				", priceType='" + priceType + '\'' +
				", stockType='" + stockType + '\'' +
				", stockMode='" + stockMode + '\'' +
				", negativeStock='" + negativeStock + '\'' +
				", isSale='" + isSale + '\'' +
				", saleStock='" + saleStock + '\'' +
				", edefectiveStock='" + edefectiveStock + '\'' +
				", returnStock='" + returnStock + '\'' +
				", lockedStock='" + lockedStock + '\'' +
				", colorCode='" + colorCode + '\'' +
				", colorName='" + colorName + '\'' +
				", stanCode='" + stanCode + '\'' +
				", stanName='" + stanName + '\'' +
				", modelCode='" + modelCode + '\'' +
				", articleNum='" + articleNum + '\'' +
				", brandGroupCode='" + brandGroupCode + '\'' +
				", brandGroupName='" + brandGroupName + '\'' +
				", unitCode='" + unitCode + '\'' +
				", unitName='" + unitName + '\'' +
				", currentPage=" + currentPage +
				", pageSize=" + pageSize +
				", start=" + start +
				", limit=" + limit +
				'}';
	}
}

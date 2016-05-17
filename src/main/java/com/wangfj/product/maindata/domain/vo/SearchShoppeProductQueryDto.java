package com.wangfj.product.maindata.domain.vo;

import java.util.List;

/**
 * Created by wangxuan on 2016-04-12 0012.
 * 根据搜索查询专柜商品的参数，不能随便添加
 */
public class SearchShoppeProductQueryDto {

    private String productName;//专柜商品名称
    private String productCodeStart;//专柜商品编码区间开始
    private String productCodeEnd;//专柜商品编码区间结束
    private String skuCodeStart;// SKU编码开始
    private String skuCodeEnd;//  SKU编码结束
    private String counterName;//专柜名称
    private String counterCode;// 专柜号
    private String storeName;//   门店名称
    private String storeCode;//   门店编码
    private String colorCode;//   颜色码
    private String modelCode;//   	款号
    private String supProductCode;//  供应商商品编码
    private String articleNum;//   货号
    private String isSelling;// 上架状态 0 未上架 1 已上架 2 已下架
    private String brandGroupCode;// 集团品牌名称
    private String supplierCode;// 供应商编码
    private String isSale;//是否可售
    private String sort;//排序方向，asc或desc。默认desc，非法值作desc处理
    private List<String> managerCategoryCodes;//管理分类编码列表
    private String field4;//物料号
    private Integer currentPage;// 当前页
    private Integer pageSize;//    每页数量

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCodeStart() {
        return productCodeStart;
    }

    public void setProductCodeStart(String productCodeStart) {
        this.productCodeStart = productCodeStart;
    }

    public String getProductCodeEnd() {
        return productCodeEnd;
    }

    public void setProductCodeEnd(String productCodeEnd) {
        this.productCodeEnd = productCodeEnd;
    }

    public String getSkuCodeStart() {
        return skuCodeStart;
    }

    public void setSkuCodeStart(String skuCodeStart) {
        this.skuCodeStart = skuCodeStart;
    }

    public String getSkuCodeEnd() {
        return skuCodeEnd;
    }

    public void setSkuCodeEnd(String skuCodeEnd) {
        this.skuCodeEnd = skuCodeEnd;
    }

    public String getCounterName() {
        return counterName;
    }

    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    public String getCounterCode() {
        return counterCode;
    }

    public void setCounterCode(String counterCode) {
        this.counterCode = counterCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getSupProductCode() {
        return supProductCode;
    }

    public void setSupProductCode(String supProductCode) {
        this.supProductCode = supProductCode;
    }

    public String getArticleNum() {
        return articleNum;
    }

    public void setArticleNum(String articleNum) {
        this.articleNum = articleNum;
    }

    public String getIsSelling() {
        return isSelling;
    }

    public void setIsSelling(String isSelling) {
        this.isSelling = isSelling;
    }

    public String getBrandGroupCode() {
        return brandGroupCode;
    }

    public void setBrandGroupCode(String brandGroupCode) {
        this.brandGroupCode = brandGroupCode;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
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

    public String getIsSale() {
        return isSale;
    }

    public void setIsSale(String isSale) {
        this.isSale = isSale;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public List<String> getManagerCategoryCodes() {
        return managerCategoryCodes;
    }

    public void setManagerCategoryCodes(List<String> managerCategoryCodes) {
        this.managerCategoryCodes = managerCategoryCodes;
    }

    public String getField4() {
        return field4;
    }

    public void setField4(String field4) {
        this.field4 = field4;
    }

    @Override
    public String toString() {
        return "SearchShoppeProductQueryDto{" +
                "productName='" + productName + '\'' +
                ", productCodeStart='" + productCodeStart + '\'' +
                ", productCodeEnd='" + productCodeEnd + '\'' +
                ", skuCodeStart='" + skuCodeStart + '\'' +
                ", skuCodeEnd='" + skuCodeEnd + '\'' +
                ", counterName='" + counterName + '\'' +
                ", counterCode='" + counterCode + '\'' +
                ", storeName='" + storeName + '\'' +
                ", storeCode='" + storeCode + '\'' +
                ", colorCode='" + colorCode + '\'' +
                ", modelCode='" + modelCode + '\'' +
                ", supProductCode='" + supProductCode + '\'' +
                ", articleNum='" + articleNum + '\'' +
                ", isSelling='" + isSelling + '\'' +
                ", brandGroupCode='" + brandGroupCode + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", isSale='" + isSale + '\'' +
                ", sort='" + sort + '\'' +
                ", managerCategoryCodes=" + managerCategoryCodes +
                ", field4='" + field4 + '\'' +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                '}';
    }
}

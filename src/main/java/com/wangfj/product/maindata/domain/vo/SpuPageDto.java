package com.wangfj.product.maindata.domain.vo;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * SPU分页DTO
 * 
 * @Class Name ProductPageDto
 * @Author zhangxy
 * @Create In 2015年7月15日
 */
public class SpuPageDto extends BaseDto {
	private Long sid;
	private String spuCode;// 产品表SPU 编码
	private String modelCode;// 款号
	private String primaryAttr;// 商品主属性
	private String productName;// 产品名称
	private String brandGroupCode;// 集团品牌编码
	private String brandGroupName;// 集团品牌名称
	private String spuSale;// spu上架状态
	private String category;// 工业分类
	private String statCategory;// 统计分类
	private String categoryName;// 工业分类名称
	private String statCategoryName;// 统计分类名称
	private String seasonCode;// 季节
	private String season;// 季节
	private String onMarketDate;// 上市年份
	private String activityFlg;// 活动标记
	private String proPicture;// 商品图片
	private String sexSid;// 适合性别
	private String awesome;// 点赞数量
	private String proSellingTime;// 上架时间
	private String longDes;// 长描述
	private String shortDes;// 短描述
	private String editFlag;// 二次编辑标识
	private String specialDes;// 特别说明
	private String proActiveBit;// 启用标记
	private List<Map<String, Object>> attributeList;// 属性列表
	private List<Map<String, Object>> tagList;// 标签列表
	private String industryCondition;// 业态类型(1百货 2超市 3电商)
	private Integer currentPage;// 当前页
	private Integer pageSize;// 单页行数
	private Integer start;
	private Integer limit;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	/**
	 * 产品表SPU 编码
	 */
	public String getSpuCode() {
		return spuCode;
	}

	/**
	 * 产品表SPU 编码
	 */
	public void setSpuCode(String spuCode) {
		this.spuCode = spuCode;
	}

	/**
	 * 款号
	 */
	public String getModelCode() {
		return modelCode;
	}

	/**
	 * 款号
	 */
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	/**
	 * 商品名
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * 商品名
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * 集团品牌编码
	 */
	public String getBrandGroupCode() {
		return brandGroupCode;
	}

	/**
	 * 集团品牌编码
	 */
	public void setBrandGroupCode(String brandGroupCode) {
		this.brandGroupCode = brandGroupCode;
	}

	/**
	 * 集团品牌名称
	 */
	public String getBrandGroupName() {
		return brandGroupName;
	}

	/**
	 * 集团品牌名称
	 */
	public void setBrandGroupName(String brandGroupName) {
		this.brandGroupName = brandGroupName;
	}

	public String getSpuSale() {
		return spuSale;
	}

	public void setSpuSale(String spuSale) {
		this.spuSale = spuSale;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSeasonCode() {
		return seasonCode;
	}

	public void setSeasonCode(String seasonCode) {
		this.seasonCode = seasonCode;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getOnMarketDate() {
		return onMarketDate;
	}

	public void setOnMarketDate(String onMarketDate) {
		this.onMarketDate = onMarketDate;
	}

	public String getActivityFlg() {
		return activityFlg;
	}

	public void setActivityFlg(String activityFlg) {
		this.activityFlg = activityFlg;
	}

	public String getProPicture() {
		return proPicture;
	}

	public void setProPicture(String proPicture) {
		this.proPicture = proPicture;
	}

	public String getSexSid() {
		return sexSid;
	}

	public void setSexSid(String sexSid) {
		this.sexSid = sexSid;
	}

	public String getAwesome() {
		return awesome;
	}

	public void setAwesome(String awesome) {
		this.awesome = awesome;
	}

	public String getLongDes() {
		return longDes;
	}

	public void setLongDes(String longDes) {
		this.longDes = longDes;
	}

	public String getProSellingTime() {
		return proSellingTime;
	}

	public void setProSellingTime(String proSellingTime) {
		this.proSellingTime = proSellingTime;
	}

	public String getShortDes() {
		return shortDes;
	}

	public void setShortDes(String shortDes) {
		this.shortDes = shortDes;
	}

	public String getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}

	public String getSpecialDes() {
		return specialDes;
	}

	public void setSpecialDes(String specialDes) {
		this.specialDes = specialDes;
	}

	public String getProActiveBit() {
		return proActiveBit;
	}

	public void setProActiveBit(String proActiveBit) {
		this.proActiveBit = proActiveBit;
	}

	public List<Map<String, Object>> getAttributeList() {
		return attributeList;
	}

	public void setAttributeList(List<Map<String, Object>> attributeList) {
		this.attributeList = attributeList;
	}

	public List<Map<String, Object>> getTagList() {
		return tagList;
	}

	public void setTagList(List<Map<String, Object>> tagList) {
		this.tagList = tagList;
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
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getPrimaryAttr() {
		return primaryAttr;
	}

	public void setPrimaryAttr(String primaryAttr) {
		this.primaryAttr = primaryAttr;
	}

	public String getStatCategory() {
		return statCategory;
	}

	public void setStatCategory(String statCategory) {
		this.statCategory = statCategory;
	}

	public String getIndustryCondition() {
		return industryCondition;
	}

	public void setIndustryCondition(String industryCondition) {
		this.industryCondition = industryCondition;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getStatCategoryName() {
		return statCategoryName;
	}

	public void setStatCategoryName(String statCategoryName) {
		this.statCategoryName = statCategoryName;
	}

	@Override
	public String toString() {
		return "SpuPageDto [sid=" + sid + ", spuCode=" + spuCode + ", modelCode=" + modelCode
				+ ", primaryAttr=" + primaryAttr + ", productName=" + productName
				+ ", brandGroupCode=" + brandGroupCode + ", brandGroupName=" + brandGroupName
				+ ", spuSale=" + spuSale + ", category=" + category + ", statCategory="
				+ statCategory + ", categoryName=" + categoryName + ", statCategoryName="
				+ statCategoryName + ", seasonCode=" + seasonCode + ", season=" + season
				+ ", onMarketDate=" + onMarketDate + ", activityFlg=" + activityFlg
				+ ", proPicture=" + proPicture + ", sexSid=" + sexSid + ", awesome=" + awesome
				+ ", proSellingTime=" + proSellingTime + ", longDes=" + longDes + ", shortDes="
				+ shortDes + ", editFlag=" + editFlag + ", specialDes=" + specialDes
				+ ", proActiveBit=" + proActiveBit + ", attributeList=" + attributeList
				+ ", tagList=" + tagList + ", industryCondition=" + industryCondition
				+ ", currentPage=" + currentPage + ", pageSize=" + pageSize + ", start=" + start
				+ ", limit=" + limit + "]";
	}

}

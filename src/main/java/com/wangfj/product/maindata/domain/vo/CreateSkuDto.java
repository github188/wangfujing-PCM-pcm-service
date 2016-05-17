package com.wangfj.product.maindata.domain.vo;

import java.util.Date;

/**
 * 创建SKU DTO
 * @Class Name CreateSkuDto
 * @Author liuhp
 * @Create In 2015-8-17
 */
public class CreateSkuDto {


	/**
	 * 产品表sid
	 */
	private String productSid;
	/**
	 * 规格表编码
	 */
	private String proStanSid;
	/**
	 * 商品色系SID
	 */
	private Long proColorSid;
	/**
	 * 色码
	 */
	private String proColorName;
	/**
	 * 颜色名称
	 */
	private String proColorAlias;
	/**
	 * 规格名称
	 */
	private String proStanName;
	/**
	 * 商品颜色为图片颜色
	 */
	private String memo;
	/**
	 * 录入时间
	 */
	private Date proWriTime;
	/**
	 * 送去拍照的销售编码
	 */
	private String photoSaleCodeSid;
	/**
	 * 拍照计划状态 : 0 未计划,1 已计划, 2 已拍照未上传店内,3 已上传店内未到IDC,4 已上传至IDC 完成 5表示拍照部已计划导购未确认
	 * 默认 0
	 */
	private Integer photoStatus;
	/**
	 * 操作用户SID
	 */
	private Long optUserSid;
	/**
	 * 操作用户real_name
	 */
	private String optRealName;
	/**
	 * 操作时间
	 */
	private Date optUpdateTime;
	/**
	 * 拍照计划制定人
	 */
	private String planMaker;
	/**
	 * 计划制定时间
	 */
	private Date planTime;
	/**
	 * 拍照计划表编码
	 */
	private String photoPlanSid;
	/**
	 * 是否启用 : 1 启用 0 未启用
	 */
	private Integer proActiveBit;
	/**
	 * 商品类型 : 0 普通商品（实物类），1 赠品 ，2 礼品，3 虚拟商品（充值卡，购物卡），4 服务类商品
	 * （礼品包装，购物接送服务，停车服务）（注：礼品不可卖，赠品可卖）
	 */
	private Integer proType;
	/**
	 * 上架状态 : 0 未上架，1 已上架，2 已下架
	 */
	private Integer sellingStatus;
	/**
	 * 关键字
	 */
	private String searchKey;
	/**
	 * 活动关键字
	 */
	private String keyWord;
	/**
	 * 尺寸图片表路径
	 */
	private String sizePictureUrl;
	/**
	 * 特性
	 */
	private String features;
	/**
	 * 货号
	 */
	private String articleNum;
	
	/**
	 * 标志位 0 百货，1超市
	 */
	private Integer flag;
	
	/**
	 * 门店品牌编码
	 */
	private String brandShopCode;
	
	/**
	 * 集团品牌编码
	 */
	private String brandGroupCode;
	
	/**
	 * 工业分类sid
	 */
	private Long categoryGYSid;
	
	/**
	 * 产品名称（集团品牌+款号/主属性）
	 */
	private String productName;

	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductSid() {
		return productSid;
	}

	public void setProductSid(String productSid) {
		this.productSid = productSid;
	}

	public String getProStanSid() {
		return proStanSid;
	}

	public void setProStanSid(String proStanSid) {
		this.proStanSid = proStanSid;
	}

	public Long getProColorSid() {
		return proColorSid;
	}

	public void setProColorSid(Long proColorSid) {
		this.proColorSid = proColorSid;
	}

	public String getProColorName() {
		return proColorName;
	}

	public void setProColorName(String proColorName) {
		this.proColorName = proColorName;
	}

	public String getProColorAlias() {
		return proColorAlias;
	}

	public void setProColorAlias(String proColorAlias) {
		this.proColorAlias = proColorAlias;
	}

	public String getProStanName() {
		return proStanName;
	}

	public void setProStanName(String proStanName) {
		this.proStanName = proStanName;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getProWriTime() {
		return proWriTime;
	}

	public void setProWriTime(Date proWriTime) {
		this.proWriTime = proWriTime;
	}

	public String getPhotoSaleCodeSid() {
		return photoSaleCodeSid;
	}

	public void setPhotoSaleCodeSid(String photoSaleCodeSid) {
		this.photoSaleCodeSid = photoSaleCodeSid;
	}

	public Integer getPhotoStatus() {
		return photoStatus;
	}

	public void setPhotoStatus(Integer photoStatus) {
		this.photoStatus = photoStatus;
	}

	public Long getOptUserSid() {
		return optUserSid;
	}

	public void setOptUserSid(Long optUserSid) {
		this.optUserSid = optUserSid;
	}

	public String getOptRealName() {
		return optRealName;
	}

	public void setOptRealName(String optRealName) {
		this.optRealName = optRealName;
	}

	public Date getOptUpdateTime() {
		return optUpdateTime;
	}

	public void setOptUpdateTime(Date optUpdateTime) {
		this.optUpdateTime = optUpdateTime;
	}

	public String getPlanMaker() {
		return planMaker;
	}

	public void setPlanMaker(String planMaker) {
		this.planMaker = planMaker;
	}

	public Date getPlanTime() {
		return planTime;
	}

	public void setPlanTime(Date planTime) {
		this.planTime = planTime;
	}

	public String getPhotoPlanSid() {
		return photoPlanSid;
	}

	public void setPhotoPlanSid(String photoPlanSid) {
		this.photoPlanSid = photoPlanSid;
	}

	public Integer getProActiveBit() {
		return proActiveBit;
	}

	public void setProActiveBit(Integer proActiveBit) {
		this.proActiveBit = proActiveBit;
	}

	public Integer getProType() {
		return proType;
	}

	public void setProType(Integer proType) {
		this.proType = proType;
	}

	public Integer getSellingStatus() {
		return sellingStatus;
	}

	public void setSellingStatus(Integer sellingStatus) {
		this.sellingStatus = sellingStatus;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getSizePictureUrl() {
		return sizePictureUrl;
	}

	public void setSizePictureUrl(String sizePictureUrl) {
		this.sizePictureUrl = sizePictureUrl;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getArticleNum() {
		return articleNum;
	}

	public void setArticleNum(String articleNum) {
		this.articleNum = articleNum;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getBrandShopCode() {
		return brandShopCode;
	}

	public void setBrandShopCode(String brandShopCode) {
		this.brandShopCode = brandShopCode;
	}

	public String getBrandGroupCode() {
		return brandGroupCode;
	}

	public void setBrandGroupCode(String brandGroupCode) {
		this.brandGroupCode = brandGroupCode;
	}

	public Long getCategoryGYSid() {
		return categoryGYSid;
	}

	public void setCategoryGYSid(Long categoryGYSid) {
		this.categoryGYSid = categoryGYSid;
	}
	
	
	
}

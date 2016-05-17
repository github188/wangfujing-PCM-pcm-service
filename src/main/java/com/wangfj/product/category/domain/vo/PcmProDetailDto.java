package com.wangfj.product.category.domain.vo;

import java.util.Date;

/**
 * 商品明细表(SKU)
 * 
 * @Class Name PcmProDetail
 * @Author zhangxy
 * @Create In 2015年7月2日
 */
public class PcmProDetailDto{
	private Long sid;
	/**
	 * 商品明细表编码
	 */
	private String productDetailSid;
	/**
	 * 产品表编码
	 */
	private String productSid;
	/**
	 * 规格sid(舍弃不添加数据)
	 */
	private Long proStanSid;
	/**
	 * 商品颜色SID
	 */
	private Long proColorSid;
	/**
	 * 商品颜色
	 */
	private String proColorName;
	/**
	 * 商品颜色别名
	 */
	private String proColorAlias;
	/**
	 * 商品规格（直接注入）
	 */
	private String proStanName;
	/**
	 * 商品颜色为图片颜色
	 */
	private String memo;
	/**
	 * 条码
	 */
	private String barcode;
	/**
	 * 录入时间
	 */
	private Date proWriTime;
	/**
	 * 送去拍照的销售编码编码
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

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getProductDetailSid() {
		return productDetailSid;
	}

	public void setProductDetailSid(String productDetailSid) {
		this.productDetailSid = productDetailSid;
	}

	public String getProductSid() {
		return productSid;
	}

	public void setProductSid(String productSid) {
		this.productSid = productSid;
	}

	public Long getProStanSid() {
		return proStanSid;
	}

	public void setProStanSid(Long proStanSid) {
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

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
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

}
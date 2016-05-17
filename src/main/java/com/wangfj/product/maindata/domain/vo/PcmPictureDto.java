package com.wangfj.product.maindata.domain.vo;

import java.util.Date;

public class PcmPictureDto {
	private Long sid;
	/**
	 * 商品sid
	 */
	private String skuSid;

	/**
	 * 图片sid
	 */
	private String pictureSid;

	/**
	 * 图片名称
	 */
	private String pictureName;

	/**
	 * 规格sid
	 */
	private String stanSid;

	/**
	 * 图片地址
	 */
	private String pictureUrl;

	/**
	 * 是否主图
	 */
	private Integer isPrimary;

	/**
	 * 是否模特图
	 */
	private Integer isModel;

	/**
	 * 是否缩略图
	 */
	private Integer isThumbnail;

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 是否删除
	 */
	private Integer ifDelete;

	/**
	 * 操作人
	 */
	private String optName;

	/**
	 * 操作时间
	 */
	private Date updateDate;

	private String c1;

	private String c2;

	private String colorCode;

	/**
	 * 顺序
	 */
	private Integer sort;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getSkuSid() {
		return skuSid;
	}

	public void setSkuSid(String skuSid) {
		this.skuSid = skuSid;
	}

	public String getPictureSid() {
		return pictureSid;
	}

	public void setPictureSid(String pictureSid) {
		this.pictureSid = pictureSid;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	public String getStanSid() {
		return stanSid;
	}

	public void setStanSid(String stanSid) {
		this.stanSid = stanSid;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public Integer getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(Integer isPrimary) {
		this.isPrimary = isPrimary;
	}

	public Integer getIsModel() {
		return isModel;
	}

	public void setIsModel(Integer isModel) {
		this.isModel = isModel;
	}

	public Integer getIsThumbnail() {
		return isThumbnail;
	}

	public void setIsThumbnail(Integer isThumbnail) {
		this.isThumbnail = isThumbnail;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getIfDelete() {
		return ifDelete;
	}

	public void setIfDelete(Integer ifDelete) {
		this.ifDelete = ifDelete;
	}

	public String getOptName() {
		return optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getC1() {
		return c1;
	}

	public void setC1(String c1) {
		this.c1 = c1;
	}

	public String getC2() {
		return c2;
	}

	public void setC2(String c2) {
		this.c2 = c2;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

}
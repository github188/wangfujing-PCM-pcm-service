package com.wangfj.product.maindata.domain.entity;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * 商品图片表
 * 
 * @Class Name PcmProductPicture
 * @Author zhangxy
 * @Create In 2015年7月2日
 */
public class PcmProductPicture extends BaseEntity {
	private Long sid;
	/**
	 * 商品SID
	 */
	private Long productSid;
	/**
	 * 图片文件名称
	 */
	private String proPictName;
	/**
	 * 图片存放目录
	 */
	private String proPictDir;
	/**
	 * 图片顺序号
	 */
	private Integer proPictOrder;
	/**
	 * 是否是模特 : 1 是 0不是 默认0
	 */
	private Integer pictureModelBit;
	/**
	 * 是否主图 : 1 是 0不是 默认0
	 */
	private Integer pictureMasterBit;
	/**
	 * 色码表SID
	 */
	private Long proColorSid;
	/**
	 * 商品颜色名称
	 */
	private String proColorName;
	/**
	 * 商品颜色别名
	 */
	private String proColorAlias;
	/**
	 * 图片尺寸类型表（pro_pricture_size）SID
	 */
	private Long proPictureSizeSid;
	/**
	 * 商品图片表SID
	 */
	private Long proPictureSid;
	/**
	 * 主图标志位
	 */
	private Integer colorMasterPicBit;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Long getProductSid() {
		return productSid;
	}

	public void setProductSid(Long productSid) {
		this.productSid = productSid;
	}

	public String getProPictName() {
		return proPictName;
	}

	public void setProPictName(String proPictName) {
		this.proPictName = proPictName == null ? null : proPictName.trim();
	}

	public String getProPictDir() {
		return proPictDir;
	}

	public void setProPictDir(String proPictDir) {
		this.proPictDir = proPictDir == null ? null : proPictDir.trim();
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
		this.proColorName = proColorName == null ? null : proColorName.trim();
	}

	public String getProColorAlias() {
		return proColorAlias;
	}

	public void setProColorAlias(String proColorAlias) {
		this.proColorAlias = proColorAlias == null ? null : proColorAlias.trim();
	}

	public Long getProPictureSizeSid() {
		return proPictureSizeSid;
	}

	public void setProPictureSizeSid(Long proPictureSizeSid) {
		this.proPictureSizeSid = proPictureSizeSid;
	}

	public Long getProPictureSid() {
		return proPictureSid;
	}

	public void setProPictureSid(Long proPictureSid) {
		this.proPictureSid = proPictureSid;
	}

	public Integer getProPictOrder() {
		return proPictOrder;
	}

	public void setProPictOrder(Integer proPictOrder) {
		this.proPictOrder = proPictOrder;
	}

	public Integer getPictureModelBit() {
		return pictureModelBit;
	}

	public void setPictureModelBit(Integer pictureModelBit) {
		this.pictureModelBit = pictureModelBit;
	}

	public Integer getPictureMasterBit() {
		return pictureMasterBit;
	}

	public void setPictureMasterBit(Integer pictureMasterBit) {
		this.pictureMasterBit = pictureMasterBit;
	}

	public Integer getColorMasterPicBit() {
		return colorMasterPicBit;
	}

	public void setColorMasterPicBit(Integer colorMasterPicBit) {
		this.colorMasterPicBit = colorMasterPicBit;
	}

}
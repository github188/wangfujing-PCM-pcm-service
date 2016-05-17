package com.wangfj.product.maindata.domain.vo;

public class PcmPictureUrlDto {
	private String sid;
	/**
	 * 照片URL
	 */
	private String url;
	/**
	 * 照片序号
	 */
	private String number;
	/**
	 * 是否主图（0是，1否）
	 */
	private String isMain;
	/**
	 * 是否原图(0是，1否)
	 */
	private String isModel;
	/**
	 * 是否缩略图(0是，1否)
	 */
	private String isThumbnail;
	/**
	 * 是否删除
	 */
	private String ifDelete;
	/**
	 * 图片名称
	 */
	private String photoName;
	/**
	 * 图片编码
	 */
	private String photoCode;
	/**
	 * 高度
	 */
	private String height;
	/**
	 * 宽度
	 */
	private String width;

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getIsMain() {
		return isMain;
	}

	public void setIsMain(String isMain) {
		this.isMain = isMain;
	}

	public String getIsModel() {
		return isModel;
	}

	public void setIsModel(String isModel) {
		this.isModel = isModel;
	}

	public String getIsThumbnail() {
		return isThumbnail;
	}

	public void setIsThumbnail(String isThumbnail) {
		this.isThumbnail = isThumbnail;
	}

	public String getIfDelete() {
		return ifDelete;
	}

	public void setIfDelete(String ifDelete) {
		this.ifDelete = ifDelete;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getPhotoCode() {
		return photoCode;
	}

	public void setPhotoCode(String photoCode) {
		this.photoCode = photoCode;
	}

	@Override
	public String toString() {
		return "PcmPictureUrlDto [sid=" + sid + ", url=" + url + ", number=" + number + ", isMain="
				+ isMain + ", isModel=" + isModel + ", isThumbnail=" + isThumbnail + ", ifDelete="
				+ ifDelete + ", photoName=" + photoName + ", photoCode=" + photoCode + "]";
	}

}

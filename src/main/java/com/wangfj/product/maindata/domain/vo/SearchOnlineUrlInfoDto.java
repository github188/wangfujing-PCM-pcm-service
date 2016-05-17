package com.wangfj.product.maindata.domain.vo;

public class SearchOnlineUrlInfoDto {
	/**
	 * 图片SID
	 */
	private String pictureSid;
	/**
	 * 图片URL
	 */
	private String picture;
	/**
	 * 色系编码
	 */
	private String colorId;
	/**
	 * 显示顺序
	 */
	private String order;
	/**
	 * 图片颜色别名
	 */
	private String colorAlias;
	/**
	 * 是否颜色主图
	 */
	private boolean colorMaster;
	/**
	 * 图片规格
	 */
	private String size;

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getPictureSid() {
		return pictureSid;
	}

	public void setPictureSid(String pictureSid) {
		this.pictureSid = pictureSid;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getColorId() {
		return colorId;
	}

	public void setColorId(String colorId) {
		this.colorId = colorId;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getColorAlias() {
		return colorAlias;
	}

	public void setColorAlias(String colorAlias) {
		this.colorAlias = colorAlias;
	}

	public boolean isColorMaster() {
		return colorMaster;
	}

	public void setColorMaster(boolean colorMaster) {
		this.colorMaster = colorMaster;
	}

	@Override
	public String toString() {
		return "SearchOnlineUrlInfoDto [pictureSid=" + pictureSid + ", picture=" + picture
				+ ", colorId=" + colorId + ", order=" + order + ", colorAlias=" + colorAlias
				+ ", colorMaster=" + colorMaster + "]";
	}

}

package com.wangfj.product.maindata.domain.vo;

public class MasterPictureDto {
	private String pictureSid;
	private String picture;
	private String colorId;
	private String colorAlias;

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

	public String getColorAlias() {
		return colorAlias;
	}

	public void setColorAlias(String colorAlias) {
		this.colorAlias = colorAlias;
	}

	@Override
	public String toString() {
		return "MasterPictureDto [pictureSid=" + pictureSid + ", picture=" + picture + ", colorId="
				+ colorId + ", colorAlias=" + colorAlias + "]";
	}

}

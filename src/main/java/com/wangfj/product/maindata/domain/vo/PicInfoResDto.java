package com.wangfj.product.maindata.domain.vo;

public class PicInfoResDto {
	private String imgURL;// 图片地址
	private String isMain;// 是否主图
	private String picSid;// PCM图片SID

	public String getPicSid() {
		return picSid;
	}

	public void setPicSid(String picSid) {
		this.picSid = picSid;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public String getIsMain() {
		return isMain;
	}

	public void setIsMain(String isMain) {
		this.isMain = isMain;
	}

	@Override
	public String toString() {
		return "PicInfoResDto [imgURL=" + imgURL + ", isMain=" + isMain + ", picSid=" + picSid
				+ "]";
	}
}

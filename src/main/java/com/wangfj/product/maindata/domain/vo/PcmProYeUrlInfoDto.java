package com.wangfj.product.maindata.domain.vo;

public class PcmProYeUrlInfoDto {
	private String isPrimary;// 是否主图
	private String picUrl;// 图片路径
	private String sortPic;// 图片顺序

	public String getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(String isPrimary) {
		this.isPrimary = isPrimary;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getSortPic() {
		return sortPic;
	}

	public void setSortPic(String sortPic) {
		this.sortPic = sortPic;
	}

}

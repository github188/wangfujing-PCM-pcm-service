package com.wangfj.product.maindata.domain.vo;

import java.util.ArrayList;
import java.util.List;

public class PcmProYeStanInfoDto {
	private String skuCode;// sku编码
	private String isOriginal;// 是否原图
	private String picStan;// 图片规格
	private List<PcmProYeUrlInfoDto> picList = new ArrayList<PcmProYeUrlInfoDto>();// 图片列表

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getIsOriginal() {
		return isOriginal;
	}

	public void setIsOriginal(String isOriginal) {
		this.isOriginal = isOriginal;
	}

	public String getPicStan() {
		return picStan;
	}

	public void setPicStan(String picStan) {
		this.picStan = picStan;
	}

	public List<PcmProYeUrlInfoDto> getPicList() {
		return picList;
	}

	public void setPicList(List<PcmProYeUrlInfoDto> picList) {
		this.picList = picList;
	}

	@Override
	public String toString() {
		return "PcmProYeStanInfoDto [isOriginal=" + isOriginal + ", picStan=" + picStan
				+ ", picList=" + picList + "]";
	}

}

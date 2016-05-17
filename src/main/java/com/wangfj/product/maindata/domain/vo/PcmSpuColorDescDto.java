package com.wangfj.product.maindata.domain.vo;

public class PcmSpuColorDescDto {
	private String packDesc;// 精包装
	private String shortDesc;// 短描述
	private String longDesc;// 长描述

	public String getPackDesc() {
		return packDesc;
	}

	public void setPackDesc(String packDesc) {
		this.packDesc = packDesc;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getLongDesc() {
		return longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	@Override
	public String toString() {
		return "PcmSpuColorDescDto [packDesc=" + packDesc + ", shortDesc=" + shortDesc
				+ ", longDesc=" + longDesc + "]";
	}

}

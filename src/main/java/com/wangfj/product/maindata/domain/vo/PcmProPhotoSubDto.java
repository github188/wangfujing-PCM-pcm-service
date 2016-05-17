package com.wangfj.product.maindata.domain.vo;

public class PcmProPhotoSubDto {
	/**
	 * 专柜编码
	 */
	private String specialCounterCode;
	/**
	 * 专柜名称
	 */
	private String specialCounterName;
	/**
	 * 专柜楼层
	 */
	private String levelss;

	public String getSpecialCounterCode() {
		return specialCounterCode;
	}

	public void setSpecialCounterCode(String specialCounterCode) {
		this.specialCounterCode = specialCounterCode;
	}

	public String getSpecialCounterName() {
		return specialCounterName;
	}

	public void setSpecialCounterName(String specialCounterName) {
		this.specialCounterName = specialCounterName;
	}

	public String getLevelss() {
		return levelss;
	}

	public void setLevelss(String levelss) {
		this.levelss = levelss;
	}

	@Override
	public String toString() {
		return "PcmProPhotoSubDto [specialCounterCode=" + specialCounterCode
				+ ", specialCounterName=" + specialCounterName + ", levelss=" + levelss + "]";
	}
}

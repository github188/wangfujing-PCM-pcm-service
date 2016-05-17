package com.wangfj.product.maindata.domain.vo;

import java.util.List;

public class PcmPictureInfoDto {

	/**
	 * 商品code(色码级商品编码)
	 */
	private String productCode;
	/**
	 * 色系
	 */
	private String colorCode;
	/**
	 * 操作人
	 */
	private String actionPerson;
	/**
	 * 是否二次添加（0是，1否）
	 */
	private String type;

	private List<PcmPictureUrlDto> pictureList;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getActionPerson() {
		return actionPerson;
	}

	public void setActionPerson(String actionPerson) {
		this.actionPerson = actionPerson;
	}

	public List<PcmPictureUrlDto> getPictureList() {
		return pictureList;
	}

	public void setPictureList(List<PcmPictureUrlDto> pictureList) {
		this.pictureList = pictureList;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "PcmPictureInfoDto [productCode=" + productCode + ", colorCode=" + colorCode
				+ ", actionPerson=" + actionPerson + ", type=" + type + ", pictureList="
				+ pictureList + "]";
	}

}

package com.wangfj.product.maindata.domain.vo;

import javax.validation.constraints.Pattern;

public class SkuPropDto {
	@Pattern(regexp = "[\\w\\u4e00-\\u9fa5]{0,50}", message = "{SkuPropDto.colorCode.patternError}")
	private String colorCode;/* 色码（吊牌或者外包装上的色码对应的字典编码,如果没有，必须传空） */
	@Pattern(regexp = "[\\w\\u4e00-\\u9fa5]{0,50}", message = "{SkuPropDto.colorName.patternError}")
	private String colorName;/* 颜色描述（一般情况下颜色描述=色码） */
	private String proColor;/* 色系 */
	@Pattern(regexp = "[\\w\\u4e00-\\u9fa5]{0,50}", message = "{SkuPropDto.features.patternError}")
	private String features;/* 特性 */
	private String sizeCode;/* 尺码/规格 */
	@Pattern(regexp = "\\w{0,20}", message = "{SkuPropDto.modelNum.patternError}")
	private String modelNum;/* 货号(吊牌或者外包装的货号，一般情况下货号是到款色规的编码，或者货号=款号) */

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getProColor() {
		return proColor;
	}

	public void setProColor(String proColor) {
		this.proColor = proColor;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getSizeCode() {
		return sizeCode;
	}

	public void setSizeCode(String sizeCode) {
		this.sizeCode = sizeCode;
	}

	public String getModelNum() {
		return modelNum;
	}

	public void setModelNum(String modelNum) {
		this.modelNum = modelNum;
	}

}

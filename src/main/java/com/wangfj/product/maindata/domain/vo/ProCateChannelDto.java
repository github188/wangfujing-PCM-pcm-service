package com.wangfj.product.maindata.domain.vo;

public class ProCateChannelDto {
	private String spuCode;
	private String cateName;
	private String channelName;
	private String propName;
	private String valueName;

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public String getValueName() {
		return valueName;
	}

	public void setValueName(String valueName) {
		this.valueName = valueName;
	}

	// private List<ProPropValueDto> propList = new
	// ArrayList<ProPropValueDto>();

	public String getSpuCode() {
		return spuCode;
	}

	public void setSpuCode(String spuCode) {
		this.spuCode = spuCode;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	@Override
	public String toString() {
		return "ProCateChannelDto [spuCode=" + spuCode + ", cateName=" + cateName
				+ ", channelName=" + channelName + ", propName=" + propName + ", valueName="
				+ valueName + "]";
	}

	// public List<ProPropValueDto> getPropList() {
	// return propList;
	// }
	//
	// public void setPropList(List<ProPropValueDto> propList) {
	// this.propList = propList;
	// }

}

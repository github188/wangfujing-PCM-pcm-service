package com.wangfj.product.maindata.domain.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 产品与属性管理添加使用
 * 
 * @Class Name SaveProductParametersVO
 * @Author wangsy
 * @Create In 2015年8月26日
 */
public class SaveProductParametersDTO implements Serializable {
	/**
	 * @Field long serialVersionUID
	 */
	private static final long serialVersionUID = -1100903453209742775L;
	private String spuCateSid;

	public String getSpuCateSid() {
		return spuCateSid;
	}

	public void setSpuCateSid(String spuCateSid) {
		this.spuCateSid = spuCateSid;
	}

	/*
	 * 产品SID
	 */
	private String spuSid;
	/*
	 * 渠道sid
	 */
	private String channelSid;
	/*
	 * 分类Sid
	 */
	private String categorySid;
	private String categoryName;/* 分类名 */
	private String categoryType;/* 分类类型 */
	private List<ParametersDto> parameters;/* 属性/属性值 */
	/**
	 * 面包屑路径
	 */
	private String breadUrl;

	public String getBreadUrl() {
		return breadUrl;
	}

	public void setBreadUrl(String breadUrl) {
		this.breadUrl = breadUrl;
	}

	public String getSpuSid() {
		return spuSid;
	}

	public void setSpuSid(String spuSid) {
		this.spuSid = spuSid;
	}

	public String getChannelSid() {
		return channelSid;
	}

	public void setChannelSid(String channelSid) {
		this.channelSid = channelSid;
	}

	public String getCategorySid() {
		return categorySid;
	}

	public void setCategorySid(String categorySid) {
		this.categorySid = categorySid;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "SaveProductParametersDTO [spuSid=" + spuSid + ", channelSid=" + channelSid
				+ ", categorySid=" + categorySid + ", categoryName=" + categoryName
				+ ", parameters=" + parameters + "]";
	}

	public List<ParametersDto> getParameters() {
		return parameters;
	}

	public void setParameters(List<ParametersDto> parameters) {
		this.parameters = parameters;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

}

package com.wangfj.product.category.domain.vo;

import java.util.List;

import com.wangfj.product.category.domain.entity.PcmCategoryValuesDict;

/**
 * 产品属性管理专用VO
 * 
 * @Class Name PcmProductParameters
 * @Author duanzhaole
 * @Create In 2015年7月2日
 */
public class ShowValuesDictVo {
	private Long sid;

	/**
	 * 产品SID
	 */
	private Long productSid;

	/**
	 * 渠道SID(外键)
	 */
	private Long channelSid;

	/**
	 * 品类SID(外键)
	 */
	private Long categorySid;

	/**
	 * 品类名称
	 */
	private String categoryName;

	/**
	 * 属性SID(外键)
	 */
	private Long propSid;

	/**
	 * 属性名
	 */
	private String propName;

	/**
	 * 属性值SID(外键)
	 */
	private Long valueSid;

	/**
	 * 属性值
	 */
	private String valueName;

	private List<PcmCategoryValuesDict> values;

	private String notNull;

	public String getNotNull() {
		return notNull;
	}

	public void setNotNull(String notNull) {
		this.notNull = notNull;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Long getProductSid() {
		return productSid;
	}

	public void setProductSid(Long productSid) {
		this.productSid = productSid;
	}

	public Long getChannelSid() {
		return channelSid;
	}

	public void setChannelSid(Long channelSid) {
		this.channelSid = channelSid;
	}

	public Long getCategorySid() {
		return categorySid;
	}

	public void setCategorySid(Long categorySid) {
		this.categorySid = categorySid;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName == null ? null : categoryName.trim();
	}

	public Long getPropSid() {
		return propSid;
	}

	public void setPropSid(Long propSid) {
		this.propSid = propSid;
	}

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName == null ? null : propName.trim();
	}

	public Long getValueSid() {
		return valueSid;
	}

	public void setValueSid(Long valueSid) {
		this.valueSid = valueSid;
	}

	public String getValueName() {
		return valueName;
	}

	public void setValueName(String valueName) {
		this.valueName = valueName == null ? null : valueName.trim();
	}

	public List<PcmCategoryValuesDict> getValues() {
		return values;
	}

	public void setValues(List<PcmCategoryValuesDict> values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "ShowValuesDictVo [sid=" + sid + ", productSid=" + productSid + ", channelSid="
				+ channelSid + ", categorySid=" + categorySid + ", categoryName=" + categoryName
				+ ", propSid=" + propSid + ", propName=" + propName + ", valueSid=" + valueSid
				+ ", valueName=" + valueName + ", values=" + values + "]";
	}

}
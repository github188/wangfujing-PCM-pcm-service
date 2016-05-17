package com.wangfj.product.category.domain.entity;

import com.wangfj.util.PageModel;

/**
 * 分类属性值字典表
 * 
 * @Class Name PcmCategoryValuesDict
 * @Author duanzhaole
 * @Create In 2015年7月2日
 */
public class PcmCategoryValuesDict extends PageModel<PcmCategoryValuesDict> {
	private Long sid;

	/**
	 * 属性值编码
	 */
	private Long valuesSid;

	/**
	 * 属性值
	 */
	private String valuesName;

	/**
	 * 是否可修改值
	 */
	private Long isKeyValue;

	/**
	 * 属性值描述
	 */
	private String valuesDesc;

	/**
	 * 属性值编码
	 */
	private String valuesCode;

	/**
	 * 状态
	 */
	private Long status;

	/**
	 * 属性SID(外键)
	 */
	private Long propsSid;

	/**
	 * 顺序
	 */
	private Long sortOrder;

	/**
	 * 渠道SID(外键)
	 */
	private Long channelSid;

	/**
	 * 是否erp属性值
	 */
	private Long isErpValue;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Long getValuesSid() {
		return valuesSid;
	}

	public void setValuesSid(Long valuesSid) {
		this.valuesSid = valuesSid;
	}

	public String getValuesName() {
		return valuesName;
	}

	public void setValuesName(String valuesName) {
		this.valuesName = valuesName == null ? null : valuesName.trim();
	}

	public Long getIsKeyValue() {
		return isKeyValue;
	}

	public void setIsKeyValue(Long isKeyValue) {
		this.isKeyValue = isKeyValue;
	}

	public String getValuesDesc() {
		return valuesDesc;
	}

	public void setValuesDesc(String valuesDesc) {
		this.valuesDesc = valuesDesc == null ? null : valuesDesc.trim();
	}

	public String getValuesCode() {
		return valuesCode;
	}

	public void setValuesCode(String valuesCode) {
		this.valuesCode = valuesCode == null ? null : valuesCode.trim();
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getPropsSid() {
		return propsSid;
	}

	public void setPropsSid(Long propsSid) {
		this.propsSid = propsSid;
	}

	public Long getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Long sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Long getChannelSid() {
		return channelSid;
	}

	public void setChannelSid(Long channelSid) {
		this.channelSid = channelSid;
	}

	public Long getIsErpValue() {
		return isErpValue;
	}

	public void setIsErpValue(Long isErpValue) {
		this.isErpValue = isErpValue;
	}

	@Override
	public String toString() {
		return "PcmCategoryValuesDict [sid=" + sid + ", valuesSid=" + valuesSid + ", valuesName="
				+ valuesName + ", isKeyValue=" + isKeyValue + ", valuesDesc=" + valuesDesc
				+ ", valuesCode=" + valuesCode + ", status=" + status + ", propsSid=" + propsSid
				+ ", sortOrder=" + sortOrder + ", channelSid=" + channelSid + ", isErpValue="
				+ isErpValue + "]";
	}

}
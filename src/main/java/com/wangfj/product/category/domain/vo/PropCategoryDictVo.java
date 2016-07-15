/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.category.domain.voPropCategoryDictVo.java
 * @Create By duanzhaole
 * @Create In 2015年8月25日 下午2:29:47
 *
 */
package com.wangfj.product.category.domain.vo;

/**
 * @Class Name PropCategoryDictVo
 * @Author duanzhaole
 * @Create In 2015年8月25日
 */
public class PropCategoryDictVo {

	
	private Long propsSid;
	private String propsName;
	private int notNull;
	private int isEnumProp;
	private String valueName;
	private Long valueSid;
	public Long getPropsSid() {
		return propsSid;
	}
	public void setPropsSid(Long propsSid) {
		this.propsSid = propsSid;
	}
	public String getPropsName() {
		return propsName;
	}
	public void setPropsName(String propsName) {
		this.propsName = propsName;
	}
	public int getNotNull() {
		return notNull;
	}
	public void setNotNull(int notNull) {
		this.notNull = notNull;
	}
	public int getIsEnumProp() {
		return isEnumProp;
	}
	public void setIsEnumProp(int isEnumProp) {
		this.isEnumProp = isEnumProp;
	}
	public String getValueName() {
		return valueName;
	}
	public void setValueName(String valueName) {
		this.valueName = valueName;
	}
	public Long getValueSid() {
		return valueSid;
	}
	public void setValueSid(Long valueSid) {
		this.valueSid = valueSid;
	}
	
	
}

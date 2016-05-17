/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.category.domain.voShowPropsDictVo.java
 * @Create By duanzhaole
 * @Create In 2015年8月25日 下午7:06:55
 * TODO
 */
package com.wangfj.product.category.domain.vo;

import java.util.List;

import com.wangfj.product.category.domain.entity.PcmCategoryValuesDict;

/**
 * @Class Name ShowPropsDictVo
 * @Author duanzhaole
 * @Create In 2015年8月25日
 */
public class ShowPropsDictVo {

	private Long propsSid;
	private String propsName;
	private int notNull;
	private int isEnumProp;
	//属性值集合
	private List<PcmCategoryValuesDict> values;
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
	public List<PcmCategoryValuesDict> getValues() {
		return values;
	}
	public void setValues(List<PcmCategoryValuesDict> values) {
		this.values = values;
	}
	
	
}

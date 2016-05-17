package com.wangfj.product.category.domain.vo;

import java.util.List;

public class PropsVO {

	private Long sid;

	private Long propsSid;// 属性编码

	private String propsName;// 属性名称

	private Integer isEnumProp;// 类型

	private Long categorySid;
	private int categoryType;
	private List<ValuesVO> provals;// 属性值集合

	private Integer notNull;

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

	public List<ValuesVO> getProvals() {
		return provals;
	}

	public void setProvals(List<ValuesVO> provals) {
		this.provals = provals;
	}

	public Integer getIsEnumProp() {
		return isEnumProp;
	}

	public void setIsEnumProp(Integer isEnumProp) {
		this.isEnumProp = isEnumProp;
	}

	public Integer getNotNull() {
		return notNull;
	}

	public void setNotNull(Integer notNull) {
		this.notNull = notNull;
	}

	public Long getCategorySid() {
		return categorySid;
	}

	public void setCategorySid(Long categorySid) {
		this.categorySid = categorySid;
	}

	public int getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(int categoryType) {
		this.categoryType = categoryType;
	}



	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

}

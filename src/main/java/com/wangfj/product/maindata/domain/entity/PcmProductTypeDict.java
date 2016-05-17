package com.wangfj.product.maindata.domain.entity;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * 商品类型字典表
 * 
 * @Class Name PcmProductTypeDict
 * @Author zhangxy
 * @Create In 2015年7月2日
 */
public class PcmProductTypeDict extends BaseEntity {
	private Integer sid;
	/**
	 * 商品类型名称
	 */
	private String typeName;
	/**
	 * 类型描述
	 */
	private String typeDesc;

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName == null ? null : typeName.trim();
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc == null ? null : typeDesc.trim();
	}
}
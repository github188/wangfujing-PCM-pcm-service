package com.wangfj.product.maindata.domain.entity;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * erp类型字典表
 * 
 * @Class Name PcmErpTypeDict
 * @Author zhangxy
 * @Create In 2015年7月2日
 */
public class PcmErpTypeDict extends BaseEntity {
	private Integer sid;
	/**
	 * erp类型
	 */
	private String erpType;

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getErpType() {
		return erpType;
	}

	public void setErpType(String erpType) {
		this.erpType = erpType == null ? null : erpType.trim();
	}
}
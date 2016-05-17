package com.wangfj.product.maindata.domain.entity;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * 包装单位字典表
 * 
 * @Class Name PcmPackUnitDict
 * @Author zhangxy
 * @Create In 2015年7月2日
 */
public class PcmPackUnitDict extends BaseEntity {
	private Long sid;
	/**
	 * 包装单位
	 */
	private String unitName;
	/**
	 * 包装描述
	 */
	private String unitDesc;
	private Integer status;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName == null ? null : unitName.trim();
	}

	public String getUnitDesc() {
		return unitDesc;
	}

	public void setUnitDesc(String unitDesc) {
		this.unitDesc = unitDesc == null ? null : unitDesc.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
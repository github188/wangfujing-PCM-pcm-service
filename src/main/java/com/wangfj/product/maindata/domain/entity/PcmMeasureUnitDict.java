package com.wangfj.product.maindata.domain.entity;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * 计量单位字典表
 * 
 * @Class Name PcmMeasureUnitDict
 * @Author zhangxy
 * @Create In 2015年7月2日
 */
public class PcmMeasureUnitDict extends BaseEntity {
	private Long sid;
	/**
	 * 计量单位名称
	 */
	private String unitName;
	/**
	 * 描述
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
package com.wangfj.product.maindata.domain.entity;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * 色系字典表
 * 
 * @Class Name PcmColorDict
 * @Author zhangxy
 * @Create In 2015年7月2日
 */
public class PcmColorDict extends BaseEntity {
	private Long sid;
	/**
	 * 别名
	 */
	private String colorAlias;
	/**
	 * 27种标准颜色
	 */
	private String colorName;

	private Integer status;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getColorAlias() {
		return colorAlias;
	}

	public void setColorAlias(String colorAlias) {
		this.colorAlias = colorAlias == null ? null : colorAlias.trim();
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName == null ? null : colorName.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
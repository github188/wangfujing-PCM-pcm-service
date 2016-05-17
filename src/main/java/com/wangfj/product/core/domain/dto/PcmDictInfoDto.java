package com.wangfj.product.core.domain.dto;

import java.util.List;
import java.util.Map;

/**
 * 根据父sid查询说对应所有的sid(父sid个数不等)
 * 
 * @Class Name PcmDictInfoDto
 * @Author niuzf
 * @Create In 2015-9-21
 */
public class PcmDictInfoDto {
	/* 父sid */
	private String parentCode;
	/**/
	private List<Map<String, Object>> dicList;

	public List<Map<String, Object>> getDicList() {
		return dicList;
	}

	public void setDicList(List<Map<String, Object>> dicList) {
		this.dicList = dicList;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

}

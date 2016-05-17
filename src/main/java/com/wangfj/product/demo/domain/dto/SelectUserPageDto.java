package com.wangfj.product.demo.domain.dto;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * 
 * @Comment
 * @Class Name UserPara
 * @Author kongxs
 * @Create In 2015年6月25日
 */
public class SelectUserPageDto extends BaseDto{
	
	private String name;
	private Integer currentPage;
	private Integer pageSize;
	/**
	 * @Return the String name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @Param String name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @Return the Integer currentPage
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}
	/**
	 * @Param Integer currentPage to set
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	/**
	 * @Return the Integer pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}
	/**
	 * @Param Integer pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SelectUserPageDto [name=" + name + ", currentPage=" + currentPage + ", pageSize="
				+ pageSize + "]";
	}
	
	
}

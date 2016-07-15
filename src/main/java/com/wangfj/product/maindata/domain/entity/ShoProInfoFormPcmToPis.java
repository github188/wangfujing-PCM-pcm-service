/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.maindata.domain.entityShoProInfoFormPcmToPis.java
 * @Create By wangc
 * @Create In 2016-3-29 下午4:31:02
 */
package com.wangfj.product.maindata.domain.entity;

import java.util.List;

/**
 * @Class Name ShoProInfoFormPcmToPis
 * @Author  wangc
 * @Create In 2016-3-29
 */
public class ShoProInfoFormPcmToPis {

	private List<String> itemIds;

	/**
	 * 当前页
	 */
	private Integer currentPage;
	/**
	 * 单页行数
	 */
	private Integer pageSize;
	private Integer start;
	private Integer limit;
	
	
	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public List<String> getItemIds() {
		return itemIds;
	}

	public void setItemIds(List<String> itemIds) {
		this.itemIds = itemIds;
	}
}

package com.wangfj.product.category.domain.vo;

import java.util.List;

public class PhotoProductListPage {
	
	private List<?> list;
	
	private Integer totalRows;
	
	private Integer totalPage;
	
	private Integer startRows;
	
	private Integer endRows;
	
	private Integer PageSize;
	
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	public Integer getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getStartRows() {
		return startRows;
	}
	public void setStartRows(Integer startRows) {
		this.startRows = startRows;
	}
	public Integer getEndRows() {
		return endRows;
	}
	public void setEndRows(Integer endRows) {
		this.endRows = endRows;
	}
	public Integer getPageSize() {
		return PageSize;
	}
	public void setPageSize(Integer pageSize) {
		PageSize = pageSize;
	}
	

}

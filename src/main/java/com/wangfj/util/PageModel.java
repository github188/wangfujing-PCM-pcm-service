package com.wangfj.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageModel<T> {

	private int start = 0;
	private int end;
	private int pageSize = 20;
	private List<T> rows;
	private int result;
	private Object entity;

	private String orderby;
	private boolean success;

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public Map<String, Object> toParmMap() {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("success", this.success);
		paramMap.put("rows", this.rows);
		paramMap.put("result", this.result);
		paramMap.put("pageSize", pageSize);
		paramMap.put("entity", this.entity);

		return paramMap;
	}

	public Object getEntity() {
		return entity;
	}

	public void setEntity(Object entity) {
		this.entity = entity;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
	
	

}

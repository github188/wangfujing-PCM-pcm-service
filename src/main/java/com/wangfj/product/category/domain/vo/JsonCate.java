package com.wangfj.product.category.domain.vo;

import java.util.Collection;

public class JsonCate {

	private Long id;

	private String text;

	private String state;
	
	private boolean checked;

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	private Collection<JsonCate> children;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Collection<JsonCate> getChildren() {
		return children;
	}

	public void setChildren(Collection<JsonCate> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "JsonCate [id=" + id + ", text=" + text + ", state=" + state
				+ ", checked=" + checked + ", children=" + children + "]";
	}

}

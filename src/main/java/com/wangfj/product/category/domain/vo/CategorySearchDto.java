package com.wangfj.product.category.domain.vo;

public class CategorySearchDto {
	private String categoryId;
	private String categoryName;
	private Boolean leafLevel;
	private Boolean selfBuilt;
	private String rootCategoryId;
	private String parentCategoryId;
	private Integer level;
	private Integer order;
	private String channel;

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Boolean getLeafLevel() {
		return leafLevel;
	}

	public void setLeafLevel(Boolean leafLevel) {
		this.leafLevel = leafLevel;
	}

	public Boolean getSelfBuilt() {
		return selfBuilt;
	}

	public void setSelfBuilt(Boolean selfBuilt) {
		this.selfBuilt = selfBuilt;
	}

	public String getRootCategoryId() {
		return rootCategoryId;
	}

	public void setRootCategoryId(String rootCategoryId) {
		this.rootCategoryId = rootCategoryId;
	}

	public String getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(String parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	@Override
	public String toString() {
		return "CategorySearchDto [categoryId=" + categoryId + ", categoryName=" + categoryName
				+ ", leafLevel=" + leafLevel + ", selfBuilt=" + selfBuilt + ", rootCategoryId="
				+ rootCategoryId + ", parentCategoryId=" + parentCategoryId + ", level=" + level
				+ ", order=" + order + ", channel=" + channel + "]";
	}

}

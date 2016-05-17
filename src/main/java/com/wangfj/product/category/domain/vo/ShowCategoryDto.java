/**
 * 
 */
package com.wangfj.product.category.domain.vo;

/**
 * 查询展示分类参数
 * 
 * @Class Name ShowCategoryDto
 * @Author duanzhaole
 * @Create In 2015年7月24日
 */
public class ShowCategoryDto {

	/**
	 * 分类编码
	 */
	private String code;

	/**
	 * 父级编码
	 */
	private String parentCode;
	private String name;
	/**
	 * 根节点
	 */
	private String catalogCode;

	/**
	 * 渠道编码（渠道主数据中的渠道编码）
	 */
	private String channelCode;
	/**
	 * 品类类型
	 */
	private Integer categoryType;

	/** 当前页的起始索引,从1开始 */
	protected int start = 0;

	/** mysql 分页 */
	protected int limit = 10;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCatalogCode() {
		return catalogCode;
	}

	public void setCatalogCode(String catalogCode) {
		this.catalogCode = catalogCode;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public Integer getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(Integer categoryType) {
		this.categoryType = categoryType;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}

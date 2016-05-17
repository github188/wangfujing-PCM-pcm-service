/**
 * 
 */
package com.wangfj.product.category.domain.vo;

import javax.validation.constraints.NotNull;


/**
 * 工业分类查询参数
 * @Class Name IndustryCategoryDto
 * @Author duanzhaole
 * @Create In 2015年8月4日
 */
public class IndustryCategoryDto {

	private Long sid;
	private String code;
	private String parentCode;
	private String name;
	private Integer categoryType;

	/**
	 * A(创建)，U(更新,如果不存在则创建)，D(删除)
	 */
	@NotNull(message = "{IndustryCategoryDto.actionCode.isNotNull}")
	private String actionCode;

	/**
	 * 操作发起时间戳(yyyyMMdd.HHmmssZ)，例如”20141008.101830+0800”，可以为空。
	 */
	private String actionDate;

	/**
	 * 操作员标识(英文标识)。可以为空。
	 */
	private String actionPerson;
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

	public Integer getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(Integer categoryType) {
		this.categoryType = categoryType;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getActionDate() {
		return actionDate;
	}

	public void setActionDate(String actionDate) {
		this.actionDate = actionDate;
	}

	public String getActionPerson() {
		return actionPerson;
	}

	public void setActionPerson(String actionPerson) {
		this.actionPerson = actionPerson;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

}

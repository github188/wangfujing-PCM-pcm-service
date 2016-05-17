package com.wangfj.product.demo.domain.dto;
/**
 * 
 * @Class Name UpdateStudentsDto
 * @Author wuxiong
 * @Create In 2015年7月1日
 */
public class UpdateStudentsDto {
	private Integer sid;
	private String name;
	/**
	 * 
	 * @Methods Name getId
	 * @Create In 2015年7月1日 By wuxiong
	 * @return Integer
	 */

	public String getName() {
		return name;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}

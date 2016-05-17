package com.wangfj.product.demo.domain.dto;

import java.util.Date;

import com.wangfj.core.framework.base.dto.BaseDto;
/**
 * 更改学生信息
 * @Class Name StudentsDto
 * @Author wuxiong
 * @Create In 2015年7月1日
 */
public class StudentsDto extends BaseDto{
	private String id;
	private String name;
	private int age;
	private Date birthday;
    private String idcard;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StudentsDto [id=" + id + ", name=" + name + ", age=" + age + ", birthday="
				+ birthday + ", idcard=" + idcard + "]";
	}
    
    
}

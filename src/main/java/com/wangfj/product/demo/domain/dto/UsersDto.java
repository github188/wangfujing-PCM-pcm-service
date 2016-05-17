package com.wangfj.product.demo.domain.dto;

import java.util.Date;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * user暴露给web层用的dto
 * @Class Name UsersDTO
 * @Author wangfei
 * @Create In 2014年9月29日
 */
public class UsersDto  extends BaseDto{
	private String id;
	private String name;
	private int age;
	private Date birthday;
    private String idcard;
	public String getName() {
		return name;
	}
	/**
	 * @Return the String id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @Param String id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @Return the int age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @Param int age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @Return the Date birthday
	 */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * @Param Date birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/**
	 * @Return the String idcard
	 */
	public String getIdcard() {
		return idcard;
	}
	/**
	 * @Param String idcard to set
	 */
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	/**
	 * @Param String name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UsersDto [id=" + id + ", name=" + name + ", age=" + age + ", birthday=" + birthday
				+ ", idcard=" + idcard + "]";
	}
	
	
}

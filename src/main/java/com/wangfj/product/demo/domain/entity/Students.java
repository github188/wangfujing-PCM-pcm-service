package com.wangfj.product.demo.domain.entity;

import java.util.Date;

import com.wangfj.core.framework.base.entity.BaseEntity;

public class Students extends BaseEntity {
		private Integer sid;

	    private String name;

	    private Integer age;

	    private Date birthday;

	    private String idcard;

		public Integer getSid() {
			return sid;
		}

		public void setSid(Integer sid) {
			this.sid = sid;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
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

	    
}

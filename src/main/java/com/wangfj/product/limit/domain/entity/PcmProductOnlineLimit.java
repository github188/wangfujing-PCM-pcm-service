package com.wangfj.product.limit.domain.entity;

import java.util.Date;

import com.wangfj.core.framework.base.entity.BaseEntity;

public class PcmProductOnlineLimit extends BaseEntity {

	/**
	 * @Field long serialVersionUID
	 */
	private static final long serialVersionUID = 8670818963117913858L;

	private Long sid;

	private Long brandSid;// 品牌sid

	private Long categorySid;// 品类sid

	private Integer limitValue;// 阀值

	private Integer status;// 阀值状态:0启用，1禁用

	private Date createTime;

	private String optName;

	private Date updateTime;

	private String field1;

	private String field2;

	private String field3;

	private String field4;

	private String field5;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Long getBrandSid() {
		return brandSid;
	}

	public void setBrandSid(Long brandSid) {
		this.brandSid = brandSid;
	}

	public Long getCategorySid() {
		return categorySid;
	}

	public void setCategorySid(Long categorySid) {
		this.categorySid = categorySid;
	}

	public Integer getLimitValue() {
		return limitValue;
	}

	public void setLimitValue(Integer limitValue) {
		this.limitValue = limitValue;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOptName() {
		return optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

	public String getField4() {
		return field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}

	public String getField5() {
		return field5;
	}

	public void setField5(String field5) {
		this.field5 = field5;
	}

	@Override
	public String toString() {
		return "PcmProductOnlineLimit [sid=" + sid + ", brandSid=" + brandSid + ", categorySid="
				+ categorySid + ", limitValue=" + limitValue + ", status=" + status
				+ ", createTime=" + createTime + ", optName=" + optName + ", updateTime="
				+ updateTime + ", field1=" + field1 + ", field2=" + field2 + ", field3=" + field3
				+ ", field4=" + field4 + ", field5=" + field5 + "]";
	}

}
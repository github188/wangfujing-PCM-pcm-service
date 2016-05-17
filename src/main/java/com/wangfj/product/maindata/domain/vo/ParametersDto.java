package com.wangfj.product.maindata.domain.vo;

public class ParametersDto {
	private Long propSid;
	private String propName;
	private Long valueSid;
	private String valueName;
	private String notNull;/* 是否必填 1必填 */

	public String getNotNull() {
		return notNull;
	}

	public void setNotNull(String notNull) {
		this.notNull = notNull;
	}

	public Long getPropSid() {
		return propSid;
	}

	public void setPropSid(Long propSid) {
		this.propSid = propSid;
	}

	public Long getValueSid() {
		return valueSid;
	}

	public void setValueSid(Long valueSid) {
		this.valueSid = valueSid;
	}

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public String getValueName() {
		return valueName;
	}

	public void setValueName(String valueName) {
		this.valueName = valueName;
	}

	@Override
	public String toString() {
		return "ParametersDto [propSid=" + propSid + ", propName=" + propName + ", valueSid="
				+ valueSid + ", valueName=" + valueName + "]";
	}

}

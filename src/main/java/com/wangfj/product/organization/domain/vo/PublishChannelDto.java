package com.wangfj.product.organization.domain.vo;

import com.wangfj.core.framework.base.dto.BaseDto;

public class PublishChannelDto extends BaseDto {
	private String code;
	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "PublishChannelDto [code=" + code + ", name=" + name + "]";
	}

}

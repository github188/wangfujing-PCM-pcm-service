package com.wangfj.product.maindata.domain.vo;

public class PublishCacheDto {
	private Long sid; // 对应实体的sid
	private Integer type;// 操作，0 添加 1修改 2删除
	private String code;// 专柜商品编码

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "PublishCacheDto [sid=" + sid + ", type=" + type + ", code=" + code + "]";
	}

}

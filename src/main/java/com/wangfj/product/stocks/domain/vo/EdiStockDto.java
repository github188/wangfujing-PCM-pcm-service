package com.wangfj.product.stocks.domain.vo;

public class EdiStockDto {
	private String proSid;
	private String type;// 变动类型
	private String remerks;// 变动数量

	public String getProSid() {
		return proSid;
	}

	public void setProSid(String proSid) {
		this.proSid = proSid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemerks() {
		return remerks;
	}

	public void setRemerks(String remerks) {
		this.remerks = remerks;
	}

	@Override
	public String toString() {
		return "EdiStockDto [proSid=" + proSid + ", type=" + type + ", remerks=" + remerks + "]";
	}

}

package com.wangfj.product.maindata.domain.vo;

public class PcmStanDictPadDto {

	private String code;// 尺码编码

	private String name;// 尺码名字

	private String brandCode;// 品牌编码

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode == null ? null : brandCode.trim();
	}

}

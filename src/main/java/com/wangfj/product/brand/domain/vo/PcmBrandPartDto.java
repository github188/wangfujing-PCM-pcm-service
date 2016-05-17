package com.wangfj.product.brand.domain.vo;

/**
 * Created by wangxuan on 2016-02-22 0022. 品牌部分信息
 */
public class PcmBrandPartDto {

	private String sid;

	private String brandName;// 品牌名称

	private String brandSid;// 品牌编码

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandSid() {
		return brandSid;
	}

	public void setBrandSid(String brandSid) {
		this.brandSid = brandSid;
	}

	@Override
	public String toString() {
		return "PcmBrandPartDto{" + "sid='" + sid + '\'' + ", brandName='" + brandName + '\''
				+ ", brandSid='" + brandSid + '\'' + '}';
	}
}

package com.wangfj.product.organization.domain.vo;

public class PcmRegionDto {
	private String code;/* 地区编码 */
	private String name;/* 地区名字 */
	private String superCode;/* 上级地区编码 */
	private String areaLib;/* 省市区 */
	private String pinYin;/* 区域名称拼音 */
	private String simple;/* 区域名称拼音简称 */

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

	public String getSuperCode() {
		return superCode;
	}

	public void setSuperCode(String superCode) {
		this.superCode = superCode;
	}

	public String getAreaLib() {
		return areaLib;
	}

	public void setAreaLib(String areaLib) {
		this.areaLib = areaLib;
	}

	public String getPinYin() {
		return pinYin;
	}

	public void setPinYin(String pinYin) {
		this.pinYin = pinYin;
	}

	public String getSimple() {
		return simple;
	}

	public void setSimple(String simple) {
		this.simple = simple;
	}

	@Override
	public String toString() {
		return "PcmRegionDto [code=" + code + ", name=" + name + ", superCode=" + superCode
				+ ", areaLib=" + areaLib + ", pinYin=" + pinYin + ", simple=" + simple + "]";
	}


}

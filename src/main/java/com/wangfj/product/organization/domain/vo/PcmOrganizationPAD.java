package com.wangfj.product.organization.domain.vo;

public class PcmOrganizationPAD {

	private String superCode; /* 所属上级sid */

	private String name; /* 机构名称 */

	private String code; /* 机构编码 */

	private Integer type; /* 机构类别 */
	/*
	 * 门店类型(0 北京，1 外埠 ，2 电商)
	 */
	private Integer storeType;

	/*
	 * 集货地点编码(对于门店非空，表明对应的集货仓地点编码， 例如电商对应电商百子湾集货仓的组织机构编码。 如果门店没有集货仓，则传自己的组织机构编码)
	 */
	private String shippingPoint;

	public String getSuperCode() {
		return superCode;
	}

	public void setSuperCode(String superCode) {
		this.superCode = superCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStoreType() {
		return storeType;
	}

	public void setStoreType(Integer storeType) {
		this.storeType = storeType;
	}

	public String getShippingPoint() {
		return shippingPoint;
	}

	public void setShippingPoint(String shippingPoint) {
		this.shippingPoint = shippingPoint;
	}

	@Override
	public String toString() {
		return "PcmOrganizationPAD [superCode=" + superCode + ", name=" + name + ", code=" + code
				+ ", type=" + type + ", storeType=" + storeType + ", shippingPoint="
				+ shippingPoint + "]";
	}

}

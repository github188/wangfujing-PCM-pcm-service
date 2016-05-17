package com.wangfj.product.supplier.domain.vo;

public class PcmSupplyDto {
	private String supplyCode;// 供应商编码
	private String supplyType;// 供应商类型
	private String supplyName;// 供应商名称
	private String shopSid;// 门店编码
	private Integer businessPattern;// 经营方式
									// :Z001经销，Z002代销，Z003联营，Z004平台服务，Z005租赁
	private Integer status;// 供应商状态
	private String admissionDate;// 准入日期，只到日期（yyyymmdd）
	private Integer returnSupply;// 退货至供应商
	private String joinSite;// 联营商品客退地点
	// private String biz_certificate_no;// 营业执照号
	public String getSupplyCode() {
		return supplyCode;
	}

	public void setSupplyCode(String supplyCode) {
		this.supplyCode = supplyCode;
	}

	public String getSupplyType() {
		return supplyType;
	}

	public void setSupplyType(String supplyType) {
		this.supplyType = supplyType;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public String getShopSid() {
		return shopSid;
	}

	public void setShopSid(String shopSid) {
		this.shopSid = shopSid;
	}

	public Integer getBusinessPattern() {
		return businessPattern;
	}

	public void setBusinessPattern(Integer businessPattern) {
		this.businessPattern = businessPattern;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}

	public Integer getReturnSupply() {
		return returnSupply;
	}

	public void setReturnSupply(Integer returnSupply) {
		this.returnSupply = returnSupply;
	}

	public String getJoinSite() {
		return joinSite;
	}

	public void setJoinSite(String joinSite) {
		this.joinSite = joinSite;
	}

	@Override
	public String toString() {
		return "PcmSupplyDto [supplyCode=" + supplyCode + ", supplyType=" + supplyType
				+ ", supplyName=" + supplyName + ", shopSid=" + shopSid + ", businessPattern="
				+ businessPattern + ", status=" + status + ", admissionDate=" + admissionDate
				+ ", returnSupply=" + returnSupply + ", joinSite=" + joinSite + "]";
	}

}

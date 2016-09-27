package com.wangfj.product.supplier.domain.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wangfj.core.framework.base.dto.BaseDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PcmSupplyInfoDto extends BaseDto {

	/**
	 * @Field long serialVersionUID
	 */
	private static final long serialVersionUID = -4357797420726211669L;

	private Long sid;

	private String supplyName;// 供应商名称

	private String postcode;// 邮编

	private String city;// 城市

	private String country;// 国家

	private String zone;// 地区代码

	private String shopRegion;// 门店地区（转成文字信息上传，即企业所在地区信息，例如”北京市”）

	private String address;// 企业地址

	private String phone;// 联系电话

	private String fax;// 传真

	private String email;// 邮件

	private String lastOptUser;// 操作人

	private Date lastOptDates;// 操作时间

	private String lastOptDateStr;// 操作时间

	private String shopSid;// 门店编码(组织结构编码)

	private String supplyCode;// 供应商编码

	private Integer supplyType;// 供应商类型:0，门店供应商；1，电商供应商；2，集团供应商（默认0）

	private String status;// 供应商状态

	private String shortName;// 供应商简称（别名）

	private String businessPattern;// 经营方式

	private String street;// 通讯地址

	private String orgCode;// 企业代码

	private String industry;// 所属行业（转成文字信息上传，即企业所在行业信息，例如”制造业”）

	private String bizCertificateNo;// 营业执照号

	private String taxType;// 纳税类别（门店ERP用的是：1增值税一般纳税人；2：小规模纳税人；3：交纳营业税；4：零税率；5自然人。需要转成文字上传给主数据ERP）

	private String taxNumbe;// 税号

	private String bank;// 银行（开户行名称）

	private String bankNo;// 银行帐号

	private String registeredCapital;// 注册资本

	private String enterpriseProperty;// 企业性质（转成文字信息上传）

	private String businessCategory;// 企业类别（转成文字信息上传）

	private String legalPerson;// 法人代表

	private String legalPersonIcCode;// 法人身份证号

	private String legalPersonContact;// 法人联系方式

	private String agent;// 代理人

	private String agentIcCode;// 代理人身份证号

	private String agentContact;// 代理人联系方式

	private String contact;// 联系人

	private String contactTitle;// 联系人职务

	private String contactIcCode;// 联系人身份证号

	private String contactWay;// 联系人联系方式

	private String businessScope;// 经营范围

	private Integer keySupplier;// 重点供应商 1：是 0：不是

	private BigDecimal taxRates;// 税率（如果对于17%的税率，传过来的值就是17）

	private String taxRateStr;// 税率（如果对于17%的税率，传过来的值就是17）

	private String inOutCity;// 市内外（1市内；2省内市外；3国内省外；4国外）转成文字信息上传

	private String admissionDate;// 准入日期，只到日期（yyyymmdd）

	private Integer returnSupply;// 退货至供应商0:不是 1：是

	private String joinSite;// 联营商品客退地点

	private Integer apartOrder;// 拆单标识0: 不是 1：是

	private Integer dropship;// 区分奥莱和其它虚库标识（0:不是 1：是）

	private String erpSupplierCode;// 供应商的门店ERP或者电商ERP编码

	private String zlyFlag;// 虚库标志（Y/N）

	private String zzxxhcFlag;// 先销后采(Y/N)

	private String field1;// 组织结构代码

	private String field2;// 扩展字段

	private String field3;// 扩展字段

	private String actionCode;

	private Integer zflg;// 区分数据来源

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getShopRegion() {
		return shopRegion;
	}

	public void setShopRegion(String shopRegion) {
		this.shopRegion = shopRegion;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastOptUser() {
		return lastOptUser;
	}

	public void setLastOptUser(String lastOptUser) {
		this.lastOptUser = lastOptUser;
	}

	public Date getLastOptDates() {
		return lastOptDates;
	}

	public void setLastOptDates(Date lastOptDates) {
		this.lastOptDates = lastOptDates;
	}

	public String getLastOptDateStr() {
		return lastOptDateStr;
	}

	public void setLastOptDateStr(String lastOptDateStr) {
		this.lastOptDateStr = lastOptDateStr == null ? null : lastOptDateStr.trim();
	}

	public String getShopSid() {
		return shopSid;
	}

	public void setShopSid(String shopSid) {
		this.shopSid = shopSid;
	}

	public String getSupplyCode() {
		return supplyCode;
	}

	public void setSupplyCode(String supplyCode) {
		this.supplyCode = supplyCode;
	}

	public Integer getSupplyType() {
		return supplyType;
	}

	public void setSupplyType(Integer supplyType) {
		this.supplyType = supplyType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getBusinessPattern() {
		return businessPattern;
	}

	public void setBusinessPattern(String businessPattern) {
		this.businessPattern = businessPattern;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getBizCertificateNo() {
		return bizCertificateNo;
	}

	public void setBizCertificateNo(String bizCertificateNo) {
		this.bizCertificateNo = bizCertificateNo;
	}

	public String getTaxType() {
		return taxType;
	}

	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}

	public String getTaxNumbe() {
		return taxNumbe;
	}

	public void setTaxNumbe(String taxNumbe) {
		this.taxNumbe = taxNumbe;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public String getEnterpriseProperty() {
		return enterpriseProperty;
	}

	public void setEnterpriseProperty(String enterpriseProperty) {
		this.enterpriseProperty = enterpriseProperty;
	}

	public String getBusinessCategory() {
		return businessCategory;
	}

	public void setBusinessCategory(String businessCategory) {
		this.businessCategory = businessCategory;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getLegalPersonIcCode() {
		return legalPersonIcCode;
	}

	public void setLegalPersonIcCode(String legalPersonIcCode) {
		this.legalPersonIcCode = legalPersonIcCode;
	}

	public String getLegalPersonContact() {
		return legalPersonContact;
	}

	public void setLegalPersonContact(String legalPersonContact) {
		this.legalPersonContact = legalPersonContact;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getAgentIcCode() {
		return agentIcCode;
	}

	public void setAgentIcCode(String agentIcCode) {
		this.agentIcCode = agentIcCode;
	}

	public String getAgentContact() {
		return agentContact;
	}

	public void setAgentContact(String agentContact) {
		this.agentContact = agentContact;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactTitle() {
		return contactTitle;
	}

	public void setContactTitle(String contactTitle) {
		this.contactTitle = contactTitle;
	}

	public String getContactIcCode() {
		return contactIcCode;
	}

	public void setContactIcCode(String contactIcCode) {
		this.contactIcCode = contactIcCode;
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public Integer getKeySupplier() {
		return keySupplier;
	}

	public void setKeySupplier(Integer keySupplier) {
		this.keySupplier = keySupplier;
	}

	public BigDecimal getTaxRates() {
		return taxRates;
	}

	public void setTaxRates(BigDecimal taxRates) {
		this.taxRates = taxRates;
	}

	public String getTaxRateStr() {
		return taxRateStr;
	}

	public void setTaxRateStr(String taxRateStr) {
		this.taxRateStr = taxRateStr == null ? null : taxRateStr.trim();
	}

	public String getInOutCity() {
		return inOutCity;
	}

	public void setInOutCity(String inOutCity) {
		this.inOutCity = inOutCity;
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

	public Integer getApartOrder() {
		return apartOrder;
	}

	public void setApartOrder(Integer apartOrder) {
		this.apartOrder = apartOrder;
	}

	public Integer getDropship() {
		return dropship;
	}

	public void setDropship(Integer dropship) {
		this.dropship = dropship;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public Integer getZflg() {
		return zflg;
	}

	public void setZflg(Integer zflg) {
		this.zflg = zflg;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getErpSupplierCode() {
		return erpSupplierCode;
	}

	public void setErpSupplierCode(String erpSupplierCode) {
		this.erpSupplierCode = erpSupplierCode;
	}

	public String getZlyFlag() {
		return zlyFlag;
	}

	public void setZlyFlag(String zlyFlag) {
		this.zlyFlag = zlyFlag;
	}

	public String getZzxxhcFlag() {
		return zzxxhcFlag;
	}

	public void setZzxxhcFlag(String zzxxhcFlag) {
		this.zzxxhcFlag = zzxxhcFlag;
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

	@Override
	public String toString() {
		return "PcmSupplyInfoDto{" +
				"sid=" + sid +
				", supplyName='" + supplyName + '\'' +
				", postcode='" + postcode + '\'' +
				", city='" + city + '\'' +
				", country='" + country + '\'' +
				", zone='" + zone + '\'' +
				", shopRegion='" + shopRegion + '\'' +
				", address='" + address + '\'' +
				", phone='" + phone + '\'' +
				", fax='" + fax + '\'' +
				", email='" + email + '\'' +
				", lastOptUser='" + lastOptUser + '\'' +
				", lastOptDates=" + lastOptDates +
				", lastOptDateStr='" + lastOptDateStr + '\'' +
				", shopSid='" + shopSid + '\'' +
				", supplyCode='" + supplyCode + '\'' +
				", supplyType=" + supplyType +
				", status='" + status + '\'' +
				", shortName='" + shortName + '\'' +
				", businessPattern='" + businessPattern + '\'' +
				", street='" + street + '\'' +
				", orgCode='" + orgCode + '\'' +
				", industry='" + industry + '\'' +
				", bizCertificateNo='" + bizCertificateNo + '\'' +
				", taxType='" + taxType + '\'' +
				", taxNumbe='" + taxNumbe + '\'' +
				", bank='" + bank + '\'' +
				", bankNo='" + bankNo + '\'' +
				", registeredCapital='" + registeredCapital + '\'' +
				", enterpriseProperty='" + enterpriseProperty + '\'' +
				", businessCategory='" + businessCategory + '\'' +
				", legalPerson='" + legalPerson + '\'' +
				", legalPersonIcCode='" + legalPersonIcCode + '\'' +
				", legalPersonContact='" + legalPersonContact + '\'' +
				", agent='" + agent + '\'' +
				", agentIcCode='" + agentIcCode + '\'' +
				", agentContact='" + agentContact + '\'' +
				", contact='" + contact + '\'' +
				", contactTitle='" + contactTitle + '\'' +
				", contactIcCode='" + contactIcCode + '\'' +
				", contactWay='" + contactWay + '\'' +
				", businessScope='" + businessScope + '\'' +
				", keySupplier=" + keySupplier +
				", taxRates=" + taxRates +
				", taxRateStr='" + taxRateStr + '\'' +
				", inOutCity='" + inOutCity + '\'' +
				", admissionDate='" + admissionDate + '\'' +
				", returnSupply=" + returnSupply +
				", joinSite='" + joinSite + '\'' +
				", apartOrder=" + apartOrder +
				", dropship=" + dropship +
				", erpSupplierCode='" + erpSupplierCode + '\'' +
				", zlyFlag='" + zlyFlag + '\'' +
				", zzxxhcFlag='" + zzxxhcFlag + '\'' +
				", field1='" + field1 + '\'' +
				", field2='" + field2 + '\'' +
				", field3='" + field3 + '\'' +
				", actionCode='" + actionCode + '\'' +
				", zflg=" + zflg +
				'}';
	}
}

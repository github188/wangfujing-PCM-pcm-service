package com.wangfj.product.supplier.domain.vo;

/**
 * Created by wangxuan on 2016-08-29 0029.
 */
public class PcmSupplyInfoToSupplierDto {

    private String supplyName;//供应商名称

    private String address;//注册地址

    private String postCode;//邮政编码

    private String legal;//法定代理人

    private String proxy;//委托代理人

    private String taxNo;//税务登记号

    private String bank;//开户银行

    private String bankAccount;//开户银行账号

    private String tel;//电话

    private String fax;//传真

    private String companyNature;//企业性质

    private String registerCapital;//注册资本

    private String legalID;//法人身份证号

    private String businessScope;//经营范围

    private String licenseNumber;//营业执照号

    private String organizationCode;//组织机构代码

    private String payTaxType;//纳税类别

    private String contact;//联系人

    private String contactID;//联系人身份证号

    private String contactDuty;//联系人职务

    private String contactPhone;//联系人联系方式

    private String proxyID;//代理人身份证号

    private String proxyPhone;//代理人联系方式

    public String getSupplyName() {
        return supplyName;
    }

    public void setSupplyName(String supplyName) {
        this.supplyName = supplyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getLegal() {
        return legal;
    }

    public void setLegal(String legal) {
        this.legal = legal;
    }

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCompanyNature() {
        return companyNature;
    }

    public void setCompanyNature(String companyNature) {
        this.companyNature = companyNature;
    }

    public String getRegisterCapital() {
        return registerCapital;
    }

    public void setRegisterCapital(String registerCapital) {
        this.registerCapital = registerCapital;
    }

    public String getLegalID() {
        return legalID;
    }

    public void setLegalID(String legalID) {
        this.legalID = legalID;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getPayTaxType() {
        return payTaxType;
    }

    public void setPayTaxType(String payTaxType) {
        this.payTaxType = payTaxType;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactID() {
        return contactID;
    }

    public void setContactID(String contactID) {
        this.contactID = contactID;
    }

    public String getContactDuty() {
        return contactDuty;
    }

    public void setContactDuty(String contactDuty) {
        this.contactDuty = contactDuty;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getProxyID() {
        return proxyID;
    }

    public void setProxyID(String proxyID) {
        this.proxyID = proxyID;
    }

    public String getProxyPhone() {
        return proxyPhone;
    }

    public void setProxyPhone(String proxyPhone) {
        this.proxyPhone = proxyPhone;
    }

    @Override
    public String toString() {
        return "PcmSupplyInfoToSupplierDto{" +
                "supplyName='" + supplyName + '\'' +
                ", address='" + address + '\'' +
                ", postCode='" + postCode + '\'' +
                ", legal='" + legal + '\'' +
                ", proxy='" + proxy + '\'' +
                ", taxNo='" + taxNo + '\'' +
                ", bank='" + bank + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", tel='" + tel + '\'' +
                ", fax='" + fax + '\'' +
                ", companyNature='" + companyNature + '\'' +
                ", registerCapital='" + registerCapital + '\'' +
                ", legalID='" + legalID + '\'' +
                ", businessScope='" + businessScope + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", organizationCode='" + organizationCode + '\'' +
                ", payTaxType='" + payTaxType + '\'' +
                ", contact='" + contact + '\'' +
                ", contactID='" + contactID + '\'' +
                ", contactDuty='" + contactDuty + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", proxyID='" + proxyID + '\'' +
                ", proxyPhone='" + proxyPhone + '\'' +
                '}';
    }
}

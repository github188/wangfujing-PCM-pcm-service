package com.wangfj.product.organization.domain.entity;

import com.wangfj.core.framework.base.entity.BaseEntity;

public class PcmStoreInfo extends BaseEntity {
    private Long sid;

    private Long groupSid;//所属集团的sid

    private String storeCode;//门店编码

    private String registeredAddress;//注册地址

    private String postCode;//邮编

    private String legalRepresentative;//法定代表人

    private String agent;//委托代理人

    private String taxRegistrationNumber;//税务登记号

    private String bank;//开户行

    private String bankAccount;//开户行账号

    private String telephoneNumber;//电话(区号+座机)

    private String faxNumber;//传真(区号+座机)

    private String field1;//预留字段

    private String field2;//预留字段

    private String field3;//预留字段

    private String field4;//预留字段

    private String field5;//预留字段

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getGroupSid() {
        return groupSid;
    }

    public void setGroupSid(Long groupSid) {
        this.groupSid = groupSid;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getRegisteredAddress() {
        return registeredAddress;
    }

    public void setRegisteredAddress(String registeredAddress) {
        this.registeredAddress = registeredAddress;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getTaxRegistrationNumber() {
        return taxRegistrationNumber;
    }

    public void setTaxRegistrationNumber(String taxRegistrationNumber) {
        this.taxRegistrationNumber = taxRegistrationNumber;
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

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
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

    public String getField4() {
        return field4;
    }

    public void setField4(String field4) {
        this.field4 = field4;
    }

    public String getField5() {
        return field5;
    }

    public void setField5(String field5) {
        this.field5 = field5;
    }

    @Override
    public String toString() {
        return "PcmStoreInfo{" +
                "sid=" + sid +
                ", groupSid=" + groupSid +
                ", storeCode='" + storeCode + '\'' +
                ", registeredAddress='" + registeredAddress + '\'' +
                ", postCode='" + postCode + '\'' +
                ", legalRepresentative='" + legalRepresentative + '\'' +
                ", agent='" + agent + '\'' +
                ", taxRegistrationNumber='" + taxRegistrationNumber + '\'' +
                ", bank='" + bank + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", faxNumber='" + faxNumber + '\'' +
                ", field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                ", field3='" + field3 + '\'' +
                ", field4='" + field4 + '\'' +
                ", field5='" + field5 + '\'' +
                '}';
    }
}
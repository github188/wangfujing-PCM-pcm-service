package com.wangfj.product.organization.domain.vo;

/**
 * Created by wangxuan on 2016-08-29 0029.
 */
public class PcmStoreInfoToSupplierDto {

    private String shopName;//门店名称

    private String address;//注册地址

    private String postCode;//邮政编码

    private String legal;//法定代理人

    private String proxy;//委托代理人

    private String taxNo;//税务登记号

    private String bank;//开户银行

    private String bankAccount;//开户银行账号

    private String tel;//电话

    private String fax;//传真

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
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

    @Override
    public String toString() {
        return "PcmStoreInfoToSupplierDto{" +
                "shopName='" + shopName + '\'' +
                ", address='" + address + '\'' +
                ", postCode='" + postCode + '\'' +
                ", legal='" + legal + '\'' +
                ", proxy='" + proxy + '\'' +
                ", taxNo='" + taxNo + '\'' +
                ", bank='" + bank + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", tel='" + tel + '\'' +
                ", fax='" + fax + '\'' +
                '}';
    }
}

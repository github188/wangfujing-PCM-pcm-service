package com.wangfj.product.supplier.domain.vo;

import com.wangfj.core.framework.base.dto.BaseDto;

import java.util.Date;

/**
 * 供应商信息下发DTO
 *
 * @Class Name PcmPushSupplyInfoDto
 * @Author wangxuan
 * @Create In 2015-8-31
 */
public class PcmPushSupplyInfoDto extends BaseDto {

    /**
     * @Field long serialVersionUID
     */
    private static final long serialVersionUID = -4357797420726211669L;

    private Long sid;

    private String supplyName;// 供应商名称

    private String lastOptUser;// 操作人

    private Date lastOptDates;// 操作时间

    private String shopSid;// 门店sid(组织结构编码)

    private String supplyCode;// 供应商编码

    private Integer supplyType;// 供应商类型:0，门店供应商；1，电商供应商；2，集团供应商（默认0）

    private String status;// 供应商状态

    private String businessPattern;// 经营方式

    private String businessCategory;// 企业类别（转成文字信息上传）

    private String admissionDate;// 准入日期，只到日期（yyyymmdd）

    private Integer returnSupply;// 退货至供应商0:不是 1：是

    private String joinSite;// 联营商品客退地点

    private String erpSupplierCode;// 供应商的门店ERP或者电商ERP编码

    private String actionCode;

    private Integer zflg;// 区分数据来源

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getSupplyName() {
        return supplyName;
    }

    public void setSupplyName(String supplyName) {
        this.supplyName = supplyName;
    }

    public String getLastOptUser() {
        return lastOptUser;
    }

    public void setLastOptUser(String lastOptUser) {
        this.lastOptUser = lastOptUser;
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

    public String getBusinessPattern() {
        return businessPattern;
    }

    public void setBusinessPattern(String businessPattern) {
        this.businessPattern = businessPattern;
    }

    public String getBusinessCategory() {
        return businessCategory;
    }

    public void setBusinessCategory(String businessCategory) {
        this.businessCategory = businessCategory;
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

    public String getErpSupplierCode() {
        return erpSupplierCode;
    }

    public void setErpSupplierCode(String erpSupplierCode) {
        this.erpSupplierCode = erpSupplierCode;
    }

    public Date getLastOptDates() {
        return lastOptDates;
    }

    public void setLastOptDates(Date lastOptDates) {
        this.lastOptDates = lastOptDates;
    }

    @Override
    public String toString() {
        return "PcmPushSupplyInfoDto{" +
                "sid=" + sid +
                ", supplyName='" + supplyName + '\'' +
                ", lastOptUser='" + lastOptUser + '\'' +
                ", lastOptDates=" + lastOptDates +
                ", shopSid='" + shopSid + '\'' +
                ", supplyCode='" + supplyCode + '\'' +
                ", supplyType=" + supplyType +
                ", status='" + status + '\'' +
                ", businessPattern='" + businessPattern + '\'' +
                ", businessCategory='" + businessCategory + '\'' +
                ", admissionDate='" + admissionDate + '\'' +
                ", returnSupply=" + returnSupply +
                ", joinSite='" + joinSite + '\'' +
                ", erpSupplierCode='" + erpSupplierCode + '\'' +
                ", actionCode='" + actionCode + '\'' +
                ", zflg=" + zflg +
                '}';
    }
}

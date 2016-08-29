package com.wangfj.product.organization.domain.vo;

/**
 * Created by wangxuan on 2016-08-25 0025.
 */
public class PcmStoreInfoQueryDto {

    private Long sid;// 集团的sid

    private String parentSid; /* 所属上级sid */

    private Long groupSid;// 所属集团sid

    private String organizationName; /* 机构名称 */

    private String organizationCode; /* 机构编码 */

    private Integer organizationType; /* 机构类别 */

    private Integer organizationStatus; /* 机构状态 0.可用；1禁用（默认0） */

    private Integer storeType;/* 门店类型 */

    private Integer currentPage = 1;// 当前页数

    private Integer pageSize = 10;// 每页大小

    /** 当前页的起始索引,从0开始 */
    private Integer start = 0;

    /** mysql 分页 */
    private Integer limit = 10;

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getParentSid() {
        return parentSid;
    }

    public void setParentSid(String parentSid) {
        this.parentSid = parentSid == null ? null : parentSid.trim();
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName == null ? null : organizationName.trim();
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode == null ? null : organizationCode.trim();
    }

    public Integer getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(Integer organizationType) {
        this.organizationType = organizationType;
    }

    public Integer getOrganizationStatus() {
        return organizationStatus;
    }

    public void setOrganizationStatus(Integer organizationStatus) {
        this.organizationStatus = organizationStatus;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getStoreType() {
        return storeType;
    }

    public void setStoreType(Integer storeType) {
        this.storeType = storeType;
    }

    public Long getGroupSid() {
        return groupSid;
    }

    public void setGroupSid(Long groupSid) {
        this.groupSid = groupSid;
    }

    @Override
    public String toString() {
        return "SelectPcmOrganizationDto [sid=" + sid + ", parentSid=" + parentSid + ", groupSid="
                + groupSid + ", organizationName=" + organizationName + ", organizationCode="
                + organizationCode + ", organizationType=" + organizationType
                + ", organizationStatus=" + organizationStatus + ", storeType=" + storeType
                + ", currentPage=" + currentPage + ", pageSize=" + pageSize + ", start=" + start
                + ", limit=" + limit + "]";
    }

}

package com.wangfj.product.price.domain.entity;

/**
 * 变价区间表
 * @Class Name PcmChangePriceInterval
 * @Author duanzhaole
 * @Create In 2015年7月3日
 */
public class PcmChangePriceInterval {
    private Long sid;

    /**
     * 变价单位
     */
    private String changePriceUnit;

    /**
     * 变价类型
     */
    private Integer valenceType;

    /**
     * 变价优先级
     */
    private Integer priority;

    /**
     * 变价区间下限
     */
    private Double lowerlimit;

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getChangePriceUnit() {
        return changePriceUnit;
    }

    public void setChangePriceUnit(String changePriceUnit) {
        this.changePriceUnit = changePriceUnit == null ? null : changePriceUnit.trim();
    }

    public Integer getValenceType() {
        return valenceType;
    }

    public void setValenceType(Integer valenceType) {
        this.valenceType = valenceType;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Double getLowerlimit() {
        return lowerlimit;
    }

    public void setLowerlimit(Double lowerlimit) {
        this.lowerlimit = lowerlimit;
    }
}
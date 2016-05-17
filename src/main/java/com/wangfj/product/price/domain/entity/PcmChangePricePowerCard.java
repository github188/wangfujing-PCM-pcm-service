package com.wangfj.product.price.domain.entity;

import java.util.Date;
/**
 * 变价权限卡
 * @Class Name PcmChangePricePowerCard
 * @Author duanzhaole
 * @Create In 2015年7月3日
 */
public class PcmChangePricePowerCard {
    private Long sid;

    /**
     * 卡号
     */
    private String kaNum;

    /**
     * 卡密码
     */
    private String kaPwd;

    /**
     * 卡状态
     */
    private Integer kaState;

    /**
     * 领卡人
     */
    private String kaUser;

    /**
     * 生效日期
     */
    private Date beginTime;

    /**
     * 失效日期
     */
    private Date endTime;

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
     * 变价区间上限
     */
    private Double upperlimit;

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

    public String getKaNum() {
        return kaNum;
    }

    public void setKaNum(String kaNum) {
        this.kaNum = kaNum == null ? null : kaNum.trim();
    }

    public String getKaPwd() {
        return kaPwd;
    }

    public void setKaPwd(String kaPwd) {
        this.kaPwd = kaPwd == null ? null : kaPwd.trim();
    }

    public Integer getKaState() {
        return kaState;
    }

    public void setKaState(Integer kaState) {
        this.kaState = kaState;
    }

    public String getKaUser() {
        return kaUser;
    }

    public void setKaUser(String kaUser) {
        this.kaUser = kaUser == null ? null : kaUser.trim();
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public Double getUpperlimit() {
        return upperlimit;
    }

    public void setUpperlimit(Double upperlimit) {
        this.upperlimit = upperlimit;
    }

    public Double getLowerlimit() {
        return lowerlimit;
    }

    public void setLowerlimit(Double lowerlimit) {
        this.lowerlimit = lowerlimit;
    }
}
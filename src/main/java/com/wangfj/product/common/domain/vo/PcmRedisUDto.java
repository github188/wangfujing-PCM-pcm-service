package com.wangfj.product.common.domain.vo;

import java.util.Date;

/**
 * Created by wangxuan on 2016-03-03 0003.
 */
public class PcmRedisUDto {

    private Long sid;

    private String redisffield;

    private String keyname;

    private Integer status;

    private Date createtime;

    private String filed1;

    private String filed2;

    private String filed3;

    private String value;

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getRedisffield() {
        return redisffield;
    }

    public void setRedisffield(String redisffield) {
        this.redisffield = redisffield;
    }

    public String getKeyname() {
        return keyname;
    }

    public void setKeyname(String keyname) {
        this.keyname = keyname;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getFiled1() {
        return filed1;
    }

    public void setFiled1(String filed1) {
        this.filed1 = filed1;
    }

    public String getFiled2() {
        return filed2;
    }

    public void setFiled2(String filed2) {
        this.filed2 = filed2;
    }

    public String getFiled3() {
        return filed3;
    }

    public void setFiled3(String filed3) {
        this.filed3 = filed3;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "PcmRedisUDto{" +
                "sid=" + sid +
                ", redisffield='" + redisffield + '\'' +
                ", keyname='" + keyname + '\'' +
                ", status=" + status +
                ", createtime=" + createtime +
                ", filed1='" + filed1 + '\'' +
                ", filed2='" + filed2 + '\'' +
                ", filed3='" + filed3 + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}

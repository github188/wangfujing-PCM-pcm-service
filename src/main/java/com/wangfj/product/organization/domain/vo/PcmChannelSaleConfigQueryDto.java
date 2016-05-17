package com.wangfj.product.organization.domain.vo;

public class PcmChannelSaleConfigQueryDto {

    private Long sid;

    private Long shoppeProSid; /* 专柜SID */

    private String shoppeProCode; /* 专柜商品编码 */

    private String channelSid; /* 渠道编码 */

    private Integer saleStauts; /* 状态 */

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getShoppeProSid() {
        return shoppeProSid;
    }

    public void setShoppeProSid(Long shoppeProSid) {
        this.shoppeProSid = shoppeProSid;
    }

    public String getChannelSid() {
        return channelSid;
    }

    public void setChannelSid(String channelSid) {
        this.channelSid = channelSid;
    }

    public Integer getSaleStauts() {
        return saleStauts;
    }

    public void setSaleStauts(Integer saleStauts) {
        this.saleStauts = saleStauts;
    }

    public String getShoppeProCode() {
        return shoppeProCode;
    }

    public void setShoppeProCode(String shoppeProCode) {
        this.shoppeProCode = shoppeProCode;
    }

    @Override
    public String toString() {
        return "PcmChannelSaleConfigQueryDto [sid=" + sid + ", shoppeProSid=" + shoppeProSid
                + ", shoppeProCode=" + shoppeProCode + ", channelSid=" + channelSid
                + ", saleStauts=" + saleStauts + "]";
    }

}
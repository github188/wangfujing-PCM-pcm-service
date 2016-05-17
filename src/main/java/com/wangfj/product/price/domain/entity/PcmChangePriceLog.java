package com.wangfj.product.price.domain.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 变价日志表
 * @Class Name PcmChangePriceLog
 * @Author duanzhaole
 * @Create In 2015年7月3日
 */
public class PcmChangePriceLog {
    private Long sid;

    /**
     * 专柜商品SID
     */
    private Long shoppeProSid;

    /**
     * 变价前价格
     */
    private BigDecimal changeBeforePrice;

    /**
     * 变价后价格
     */
    private BigDecimal changeAfterPrice;

    /**
     * 变价类型:0 临时变价 ，1 长期变价
     */
    private Integer valenceType;

    /**
     * 变动时间
     */
    private Date changeTime;

    /**
     * 操作者
     */
    private Long optUserSid;
/**
 * 设备号
 */
    private String deviceNo;

    /**
     * ip
     */
    private String ip;

	/**
	 * 变价号
	 */
	private String order_no;

	/**
     * 价格渠道
     */
    private String priceChannel;
    
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

    public BigDecimal getChangeBeforePrice() {
        return changeBeforePrice;
    }

    public void setChangeBeforePrice(BigDecimal changeBeforePrice) {
        this.changeBeforePrice = changeBeforePrice;
    }

    public BigDecimal getChangeAfterPrice() {
        return changeAfterPrice;
    }

    public void setChangeAfterPrice(BigDecimal changeAfterPrice) {
        this.changeAfterPrice = changeAfterPrice;
    }

    public Integer getValenceType() {
        return valenceType;
    }

    public void setValenceType(Integer valenceType) {
        this.valenceType = valenceType;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public Long getOptUserSid() {
        return optUserSid;
    }

    public void setOptUserSid(Long optUserSid) {
        this.optUserSid = optUserSid;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo == null ? null : deviceNo.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getPriceChannel() {
		return priceChannel;
	}

	public void setPriceChannel(String priceChannel) {
		this.priceChannel = priceChannel;
	}
}
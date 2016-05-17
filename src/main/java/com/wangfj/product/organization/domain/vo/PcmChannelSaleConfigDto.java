package com.wangfj.product.organization.domain.vo;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * 
 * @Class Name PcmChannelSaleConfigDto
 * @Author yedong
 * @Create In 2015年7月3日
 */
public class PcmChannelSaleConfigDto extends BaseDto {

	private Long sid;

	private Long shoppeProSid;

	private String channelSid;

	private Integer saleStauts;

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

	@Override
	public String toString() {
		return "PcmChannelSaleConfigDto [sid=" + sid + ", shoppeProSid=" + shoppeProSid
				+ ", channelSid=" + channelSid + ", saleStauts=" + saleStauts + "]";
	}

}

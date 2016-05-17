package com.wangfj.product.price.domain.vo;

import java.util.Date;
import java.util.List;

import com.wangfj.core.framework.base.dto.BaseDto;
import com.wangfj.product.price.domain.entity.PcmPrice;

public class PcmPriceChangeDto extends BaseDto {

	/**
	 * 专柜商品编码
	 */
	private String shoppeProSid;

	/**
	 * 开始日期
	 */
	private Date promotionBeginTime;

	/**
	 * 结束时间
	 */
	private Date promotionEndTime;

	private List<PcmPrice> pcmPriceListN;

	private List<PcmPrice> pcmPriceListU;

	private List<PcmPrice> pcmPriceListD;

	public String getShoppeProSid() {
		return shoppeProSid;
	}

	public void setShoppeProSid(String shoppeProSid) {
		this.shoppeProSid = shoppeProSid;
	}

	public List<PcmPrice> getPcmPriceListN() {
		return pcmPriceListN;
	}

	public void setPcmPriceListN(List<PcmPrice> pcmPriceListN) {
		this.pcmPriceListN = pcmPriceListN;
	}

	public List<PcmPrice> getPcmPriceListU() {
		return pcmPriceListU;
	}

	public void setPcmPriceListU(List<PcmPrice> pcmPriceListU) {
		this.pcmPriceListU = pcmPriceListU;
	}

	public List<PcmPrice> getPcmPriceListD() {
		return pcmPriceListD;
	}

	public void setPcmPriceListD(List<PcmPrice> pcmPriceListD) {
		this.pcmPriceListD = pcmPriceListD;
	}

	public Date getPromotionBeginTime() {
		return promotionBeginTime;
	}

	public void setPromotionBeginTime(Date promotionBeginTime) {
		this.promotionBeginTime = promotionBeginTime;
	}

	public Date getPromotionEndTime() {
		return promotionEndTime;
	}

	public void setPromotionEndTime(Date promotionEndTime) {
		this.promotionEndTime = promotionEndTime;
	}
}

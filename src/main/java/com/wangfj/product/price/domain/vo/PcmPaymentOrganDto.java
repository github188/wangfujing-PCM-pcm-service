package com.wangfj.product.price.domain.vo;

import java.util.List;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * 门店支付方式dto
 * 
 * @Class Name PcmPaymentOrganDto
 * @Author kongqf
 * @Create In 2015年8月7日
 */
public class PcmPaymentOrganDto extends BaseDto {

	/**
	 * 门店编码
	 */
	private String storeCode;
	private String storeName;
	/**
	 * 门店所对应的支付方式
	 */
	private List<PcmPaymentTypeDto> payTypeList;

	public List<PcmPaymentTypeDto> getPayTypeList() {
		return payTypeList;
	}

	public void setPayTypeList(List<PcmPaymentTypeDto> payTypeList) {
		this.payTypeList = payTypeList;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	@Override
	public String toString() {
		return "PcmPaymentOrganDto [storeCode=" + storeCode + ", storeName=" + storeName
				+ ", payTypeList=" + payTypeList + "]";
	}

}

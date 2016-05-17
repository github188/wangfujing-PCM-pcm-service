package com.wangfj.product.price.domain.vo;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * 门店支付方式dto
 * 
 * @Class Name PcmPaymentOrganDto
 * @Author kongqf
 * @Create In 2015年8月7日
 */
public class PcmPaymentOrganInfoDto extends BaseDto {

	/**
	 * sid
	 */
	private String sid;

	/**
	 * 门店编号
	 */
	private String shopSid;

	/**
	 * 银行识别码
	 */
	private String bankBin;

	/**
	 * 支付方式编码
	 */
	private String code;

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getShopSid() {
		return shopSid;
	}

	public void setShopSid(String shopSid) {
		this.shopSid = shopSid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBankBin() {
		return bankBin;
	}

	public void setBankBin(String bankBin) {
		this.bankBin = bankBin;
	}

	@Override
	public String toString() {
		return "PcmPaymentOrganInfoDto [sid=" + sid + ", shopSid=" + shopSid + ", bankBin="
				+ bankBin + ", code=" + code + "]";
	}

}

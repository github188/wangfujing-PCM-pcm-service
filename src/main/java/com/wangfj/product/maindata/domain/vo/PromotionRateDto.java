package com.wangfj.product.maindata.domain.vo;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * 促销扣率码DTO
 * 
 * @Class Name PcmPromotionRateProduct
 * @Author zhangxy
 * @Create In 2015年7月2日
 */
public class PromotionRateDto extends BaseDto {
	private String counterCode;// 专柜编码
	private String supplierCode;// 供应商编码
	private String popCode;// 促销扣率码
	private String beginTime;// 开始时间
	private String endTime;// 结束时间

	public String getCounterCode() {
		return counterCode;
	}

	public void setCounterCode(String counterCode) {
		this.counterCode = counterCode;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getPopCode() {
		return popCode;
	}

	public void setPopCode(String popCode) {
		this.popCode = popCode;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "PromotionRateDto [counterCode=" + counterCode + ", supplierCode=" + supplierCode
				+ ", popCode=" + popCode + ", beginTime=" + beginTime + ", endTime=" + endTime
				+ "]";
	}

}
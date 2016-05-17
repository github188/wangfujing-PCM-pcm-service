package com.wangfj.product.price.domain.vo;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * 支付方式下发模型
 * 
 * @Class Name PcmPaymentTypeDto
 * @Author kongqf
 * @Create In 2015年8月7日
 */
public class PcmPaymentTypeDto extends BaseDto {
	/**
	 * 中台门店编码
	 */
	public String payCode;
	/**
	 * 付款方式编码
	 */
	public String name;
	/**
	 * 付款方式名称
	 */
	public String parentCode;
	/**
	 * 上级名称
	 */
	public String parentName;

	/**
	 * 银行识别码
	 */
	public String bankBIN;
	/**
	 * 本条记录对应的操作 (A添加；U更新；D删除)
	 */
	public String actionCode;
	/**
	 * 操作时间（格式为yyyyMMdd.HHmmssZ，结果形如” 20141008.101603+0800”）
	 */
	public String actionDate;
	/**
	 * 操作人
	 */
	public String actionPerson;

	public String getPayCode() {
		return payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getBankBIN() {
		return bankBIN;
	}

	public void setBankBIN(String bankBIN) {
		this.bankBIN = bankBIN;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getActionDate() {
		return actionDate;
	}

	public void setActionDate(String actionDate) {
		this.actionDate = actionDate;
	}

	public String getActionPerson() {
		return actionPerson;
	}

	public void setActionPerson(String actionPerson) {
		this.actionPerson = actionPerson;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	@Override
	public String toString() {
		return "PcmPaymentTypeDto [payCode=" + payCode + ", name=" + name + ", parentCode="
				+ parentCode + ", parentName=" + parentName + ", bankBIN=" + bankBIN
				+ ", actionCode=" + actionCode + ", actionDate=" + actionDate + ", actionPerson="
				+ actionPerson + "]";
	}

}

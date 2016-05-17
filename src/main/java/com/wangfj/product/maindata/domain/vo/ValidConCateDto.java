package com.wangfj.product.maindata.domain.vo;

import com.wangfj.core.framework.base.dto.BaseDto;

public class ValidConCateDto extends BaseDto {
	private String supplierCode;/* 供应商代码(门店ERP的供应商编码) */
	private String counterCode; /* 专柜编码（中台的专柜编码） */
	private String offerNumber;/* 要约号 */
	private String operateMode;/* 经营方式（Z001经销，Z002代销，Z003联营，Z004平台服务，Z005租赁） */
	private String manageCateGory;/* 管理分类 */

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getCounterCode() {
		return counterCode;
	}

	public void setCounterCode(String counterCode) {
		this.counterCode = counterCode;
	}

	public String getOfferNumber() {
		return offerNumber;
	}

	public void setOfferNumber(String offerNumber) {
		this.offerNumber = offerNumber;
	}

	public String getManageCateGory() {
		return manageCateGory;
	}

	public void setManageCateGory(String manageCateGory) {
		this.manageCateGory = manageCateGory;
	}

	public String getOperateMode() {
		return operateMode;
	}

	public void setOperateMode(String operateMode) {
		this.operateMode = operateMode;
	}

	@Override
	public String toString() {
		return "ValidConCateDto [supplierCode=" + supplierCode + ", counterCode=" + counterCode
				+ ", offerNumber=" + offerNumber + ", manageCateGory=" + manageCateGory
				+ ", operateMode=" + operateMode + "]";
	}

}

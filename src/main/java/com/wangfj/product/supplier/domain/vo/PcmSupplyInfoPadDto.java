package com.wangfj.product.supplier.domain.vo;

/**
 * 移动工作台调用主数据获取供应商信息(输出参数DTO)
 * 
 * @Class Name PcmSupplyInfoPadDto
 * @Author wangxuan
 * @Create In 2015-8-31
 */
public class PcmSupplyInfoPadDto {

	/*
	 * 中台供应商编码 N
	 */
	private String code;

	/*
	 * 供应商级别(1门店；2电商；3集团)。 Y
	 */
	private String supplierLevel;

	/*
	 * 中台供应商名称 N
	 */
	private String supplierName;

	/*
	 * 如果supplierLevel不是3， superCode是code对应的中台集团供应商编码。否则的话，superCode为空。 Y
	 */
	private String superCode;

	/*
	 * 门店编码（对于集团供应商的编码为空） N
	 */
	private String storeCode;

	/*
	 * 供应商的门店ERP或者电商ERP编码（中台‘参考供应商编码’字段） N
	 */
	private String erpSupplierCode;

	/*
	 * 经营方式（Z001经销，Z002代销，Z003联营，Z004平台服务，Z005租赁）0经销，1代销，2联营，3平台服务，4租赁 Y
	 */
	private String operateMode;

	/*
	 * 经营状态（门店ERP内部的供应商状态。Y正常；T未批准；N终止；L待审批；3淘汰；4停货；5停款；6冻结。传的是文本而不是编码） Y
	 */
	private String operateStatus;

	/*
	 * 供应商注册日期（准入日期,格式为yyyymmdd） Y
	 */
	private String approvalDate;

	/*
	 * 退货至供应商(Y N) 0:不是  1：是 Y
	 */
	private String ZZRETURNV;

	/*
	 * 联营商品客退地点（仅仅针对电商供应商。 如果ZZRETURNV为Y，这个字段保存客户的退货地址，不超过200个中文字符。否则为空） Y
	 */
	private String ZZJOIN_SITE;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public String getSupplierLevel() {
		return supplierLevel;
	}

	public void setSupplierLevel(String supplierLevel) {
		this.supplierLevel = supplierLevel == null ? null : supplierLevel.trim();
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName == null ? null : supplierName.trim();
	}

	public String getSuperCode() {
		return superCode;
	}

	public void setSuperCode(String superCode) {
		this.superCode = superCode == null ? null : superCode.trim();
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode == null ? null : storeCode.trim();
	}

	public String getErpSupplierCode() {
		return erpSupplierCode;
	}

	public void setErpSupplierCode(String erpSupplierCode) {
		this.erpSupplierCode = erpSupplierCode == null ? null : erpSupplierCode.trim();
	}

	public String getOperateMode() {
		return operateMode;
	}

	public void setOperateMode(String operateMode) {
		this.operateMode = operateMode == null ? null : operateMode.trim();
	}

	public String getOperateStatus() {
		return operateStatus;
	}

	public void setOperateStatus(String operateStatus) {
		this.operateStatus = operateStatus == null ? null : operateStatus.trim();
	}

	public String getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(String approvalDate) {
		this.approvalDate = approvalDate == null ? null : approvalDate.trim();
	}

	public String getZZRETURNV() {
		return ZZRETURNV;
	}

	public void setZZRETURNV(String zZRETURNV) {
		ZZRETURNV = zZRETURNV == null ? null : zZRETURNV.trim();
	}

	public String getZZJOIN_SITE() {
		return ZZJOIN_SITE;
	}

	public void setZZJOIN_SITE(String zZJOIN_SITE) {
		ZZJOIN_SITE = zZJOIN_SITE == null ? null : zZJOIN_SITE.trim();
	}

}

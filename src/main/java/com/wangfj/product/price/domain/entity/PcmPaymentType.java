package com.wangfj.product.price.domain.entity;

import java.util.Date;

/**
 * 支付方式
 * 
 * @Class Name PcmPaymentType
 * @Author duanzhaole
 * @Create In 2015年7月7日
 */
public class PcmPaymentType {
	private Long sid;

	/**
	 * 付款方式编码
	 */
	private String code;

	/**
	 * 付款方式名称
	 */
	private String name;

	/**
	 * 上一级编码
	 */
	private String parentCode;

	/**
	 * 银行识别码
	 */
	private String bankBin;

	/**
	 * 支付类型
	 */
	private String dealTime;

	/**
	 * 能否开发票(Y或N)
	 */
	private String isAllowInvoice;

	/**
	 * 最后修改人
	 */
	private String lastUpdBy;

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 创建人
	 */
	private String createBy;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 是否删除：0否，1是
	 */
	private Integer ifdel;

	public Integer getIfdel() {
		return ifdel;
	}

	public void setIfdel(Integer ifdel) {
		this.ifdel = ifdel;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode == null ? null : parentCode.trim();
	}

	public String getBankBin() {
		return bankBin;
	}

	public void setBankBin(String bankBin) {
		this.bankBin = bankBin == null ? null : bankBin.trim();
	}

	public String getDealTime() {
		return dealTime;
	}

	public void setDealTime(String dealTime) {
		this.dealTime = dealTime == null ? null : dealTime.trim();
	}

	public String getLastUpdBy() {
		return lastUpdBy;
	}

	public void setLastUpdBy(String lastUpdBy) {
		this.lastUpdBy = lastUpdBy == null ? null : lastUpdBy.trim();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy == null ? null : createBy.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getIsAllowInvoice() {
		return isAllowInvoice;
	}

	public void setIsAllowInvoice(String isAllowInvoice) {
		this.isAllowInvoice = isAllowInvoice == null ? null : isAllowInvoice.trim();
	}

}
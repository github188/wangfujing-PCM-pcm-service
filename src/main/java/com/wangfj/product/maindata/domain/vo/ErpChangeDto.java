package com.wangfj.product.maindata.domain.vo;

/**
 * ERP商品改基本信息DTO
 * 
 * @Class Name ErpChangeDto
 * @Author zhangxy
 * @Create In 2015年7月16日
 */
public class ErpChangeDto {
	/**
	 * 门店
	 */
	private String STORE;
	/**
	 * 单据号
	 */
	private String SEQNO;
	/**
	 * 行号
	 */
	private String ROWNO;
	/**
	 * 单据类别
	 */
	private String XGLB;
	/**
	 * 商品编码
	 */
	private String PRODUCT;
	/**
	 * 新值
	 */
	private String VALUE;
	/**
	 * 生效日期
	 */
	private String DATE;
	private String actionCode;
	private String actionDate;
	private String actionPerson;
	private String contractCode;

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getSTORE() {
		return STORE;
	}

	public void setSTORE(String sTORE) {
		STORE = sTORE;
	}

	public String getSEQNO() {
		return SEQNO;
	}

	public void setSEQNO(String sEQNO) {
		SEQNO = sEQNO;
	}

	public String getROWNO() {
		return ROWNO;
	}

	public void setROWNO(String rOWNO) {
		ROWNO = rOWNO;
	}

	public String getXGLB() {
		return XGLB;
	}

	public void setXGLB(String xGLB) {
		XGLB = xGLB;
	}

	public String getPRODUCT() {
		return PRODUCT;
	}

	public void setPRODUCT(String pRODUCT) {
		PRODUCT = pRODUCT;
	}

	public String getVALUE() {
		return VALUE;
	}

	public void setVALUE(String vALUE) {
		VALUE = vALUE;
	}

	public String getDATE() {
		return DATE;
	}

	public void setDATE(String dATE) {
		DATE = dATE;
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

	@Override
	public String toString() {
		return "ErpChangeDto [STORE=" + STORE + ", SEQNO=" + SEQNO + ", ROWNO=" + ROWNO + ", XGLB="
				+ XGLB + ", PRODUCT=" + PRODUCT + ", VALUE=" + VALUE + ", DATE=" + DATE
				+ ", actionCode=" + actionCode + ", actionDate=" + actionDate + ", actionPerson="
				+ actionPerson + ", contractCode=" + contractCode + "]";
	}

}

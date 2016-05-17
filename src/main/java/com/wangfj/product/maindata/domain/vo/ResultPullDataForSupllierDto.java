package com.wangfj.product.maindata.domain.vo;

/**
 * 供应商商品上传返回错误信息
 * @Class Name ResultPullDataForSupllierDto
 * @Author  wangc
 * @Create In 2016-4-1
 */
public class ResultPullDataForSupllierDto {
	/**
	 * 行号
	 */
	private String lineNumber;
	/**
	 * 消息状态
	 */
	private Integer messageCode;
	/**
	 * 返回状态信息
	 */
	private String messageName;
	/**
	 * 专柜商品编码
	 */
	private String productCode;
	/**
	 * 供应商商品批次号
	 */
	private String importNo;
	
	
	public String getImportNo() {
		return importNo;
	}

	public void setImportNo(String importNo) {
		this.importNo = importNo;
	}

	public String getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	public Integer getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(Integer messageCode) {
		this.messageCode = messageCode;
	}

	public String getMessageName() {
		return messageName;
	}

	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	@Override
	public String toString() {
		return "ResultPullDataDto [lineNumber=" + lineNumber + ", messageCode=" + messageCode
				+ ", messageName=" + messageName + ", productCode=" + productCode + "]";
	}

}

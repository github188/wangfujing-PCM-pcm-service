package com.wangfj.product.maindata.domain.vo;

public class UpdateProductInfoDto {
	private String productCode;
	private String productName;
	private String unit;
	private String originLand;
	private String articleNum;
	private String remark;
	private Integer status;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getOriginLand() {
		return originLand;
	}

	public void setOriginLand(String originLand) {
		this.originLand = originLand;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getArticleNum() {
		return articleNum;
	}

	public void setArticleNum(String articleNum) {
		this.articleNum = articleNum;
	}

	@Override
	public String toString() {
		return "UpdateProductInfoDto [productCode=" + productCode + ", productName=" + productName
				+ ", unit=" + unit + ", originLand=" + originLand + ", articleNum=" + articleNum
				+ ", remark=" + remark + ", status=" + status + "]";
	}

}

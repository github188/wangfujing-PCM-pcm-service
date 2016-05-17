package com.wangfj.product.maindata.domain.vo;


/**
 * 回传商品编辑信息DTO
 * 
 * @Class Name ProductEditDto
 * @Author zhangxy
 * @Create In 2015年7月2日
 */
public class PackDescDto {
	private String productCode;// 产品表编码
	private String colorCode;// 色系编码
	private String packDesc;// 文本描述

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public String getPackDesc() {
		return packDesc;
	}

	public void setPackDesc(String packDesc) {
		this.packDesc = packDesc;
	}

	@Override
	public String toString() {
		return "PackDescDto [productCode=" + productCode + ", colorCode=" + colorCode
				+ ", packDesc=" + packDesc + "]";
	}

}
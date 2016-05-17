package com.wangfj.product.maindata.domain.vo;

/**
 * erp编码/品牌/款/色/尺寸\规格 DTO
 * 
 * @Class Name ProductListDto
 * @Author wangsy
 * @Create In 2015年7月14日
 */
public class ProductListDto {
	/**
	 * 商品大码=erp编码
	 */
	private String erpProductCode;
	/**
	 * 品牌编码
	 */
	private String brandCode;
	/**
	 * 款号
	 */
	private String productNum;
	/**
	 * 色码
	 */
	private String colorCode;
	/**
	 * 尺码/规格
	 * <p>
	 * 如果导入模板中的管理类型(manageType)为1表示为大码商品。sizeCode字段可以为空，否则不可为空。
	 */
	private String sizeCode;

	public String getErpProductCode() {
		return erpProductCode;
	}

	public void setErpProductCode(String erpProductCode) {
		this.erpProductCode = erpProductCode;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getProductNum() {
		return productNum;
	}

	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public String getSizeCode() {
		return sizeCode;
	}

	public void setSizeCode(String sizeCode) {
		this.sizeCode = sizeCode;
	}

	@Override
	public String toString() {
		return "ProductListDto [erpProductCode=" + erpProductCode + ", brandCode=" + brandCode
				+ ", productNum=" + productNum + ", colorCode=" + colorCode + ", sizeCode="
				+ sizeCode + "]";
	}

}

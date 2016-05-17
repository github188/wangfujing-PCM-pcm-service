package com.wangfj.product.brand.domain.vo;


public class PcmBrandCateDto {
	/*
	 * 品牌编码
	 */
	private String brandCode;
	/*
	 * 分类编码
	 */
	private String cateCode;
	/*
	 * 尺码图片路径
	 */
	private String sizePictureUrl;
	/*
	 * 状态
	 */
	private Integer status;
	/*
	 * 操作人
	 */
	private String optUser;
	/*
	 * 操作时间
	 */
	private String optDate;

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getCateCode() {
		return cateCode;
	}

	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
	}

	public String getSizePictureUrl() {
		return sizePictureUrl;
	}

	public void setSizePictureUrl(String sizePictureUrl) {
		this.sizePictureUrl = sizePictureUrl;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOptUser() {
		return optUser;
	}

	public void setOptUser(String optUser) {
		this.optUser = optUser;
	}

	public String getOptDate() {
		return optDate;
	}

	public void setOptDate(String optDate) {
		this.optDate = optDate;
	}

	@Override
	public String toString() {
		return "PcmBrandCateDto [brandCode=" + brandCode + ", cateCode=" + cateCode
				+ ", sizePictureUrl=" + sizePictureUrl + ", status=" + status + ", optUser="
				+ optUser + ", optDate=" + optDate + "]";
	}

}

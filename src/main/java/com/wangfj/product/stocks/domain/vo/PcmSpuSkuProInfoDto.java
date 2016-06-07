package com.wangfj.product.stocks.domain.vo;

public class PcmSpuSkuProInfoDto {
	private String proCode;// spu编码
	private String height;// 高
	private String width;// 宽

	public String getHeight() {
		return height;
	}

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	@Override
	public String toString() {
		return "PcmSpuSkuProInfoPara [proCode=" + proCode + ", height=" + height + ", width="
				+ width + "]";
	}

}

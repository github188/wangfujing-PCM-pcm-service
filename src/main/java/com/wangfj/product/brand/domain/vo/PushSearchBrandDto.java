package com.wangfj.product.brand.domain.vo;

/**
 * 下发给搜索的数据（修改品牌时）DTO
 * 
 * @Class Name PushSearchBrandDto
 * @Author wangxuan
 * @Create In 2015-10-9
 */
public class PushSearchBrandDto {

	private String storeCode;// 门店编码

	private String storeBrandCode;// 门店品牌编码

	private String storeBrandName;// 门店品牌名称

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode == null ? null : storeCode.trim();
	}

	public String getStoreBrandCode() {
		return storeBrandCode;
	}

	public void setStoreBrandCode(String storeBrandCode) {
		this.storeBrandCode = storeBrandCode == null ? null : storeBrandCode.trim();
	}

	public String getStoreBrandName() {
		return storeBrandName;
	}

	public void setStoreBrandName(String storeBrandName) {
		this.storeBrandName = storeBrandName;
	}

	@Override
	public String toString() {
		return "PushSearchBrandDto [storeCode=" + storeCode + ", storeBrandCode=" + storeBrandCode
				+ ", storeBrandName=" + storeBrandName + "]";
	}

}

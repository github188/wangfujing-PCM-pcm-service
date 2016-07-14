/**
 * @Probject Name: pcm-service
 * @Path: PcmSupInfoForSupResultDtoPcmSupInfoForSupDto.java
 * @Create By wangc
 * @Create In 2016年6月24日 上午10:56:37
 */
package com.wangfj.product.supplier.domain.vo;

import java.util.List;
import java.util.Map;

/**
 * @Class Name PcmSupInfoForSupDto
 * @Author wangc
 * @Create In 2016年6月24日
 */
public class PcmSupInfoForSupDto {

	
	private String storeCode;//门店编码
	
	private String storeName;//门店名称
	
	private List<Map<String,Object>> supplyList;//供应商信息

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public List<Map<String, Object>> getSupplyList() {
		return supplyList;
	}

	public void setSupplyList(List<Map<String, Object>> supplyList) {
		this.supplyList = supplyList;
	}
	
	
}

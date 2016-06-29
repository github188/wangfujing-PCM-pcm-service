/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.supplier.domain.voGetSupCodeByShopCodesResultDto.java
 * @Create By wangc
 * @Create In 2016-4-12 下午3:42:53
 * TODO
 */
package com.wangfj.product.supplier.domain.vo;

import java.util.List;
import java.util.Map;

/**
 * @Class Name GetSupCodeByShopCodesResultDto
 * @Author wangc
 * @Create In 2016-4-12
 */
public class GetSupCodeByShopCodesDto {

    private String shopCode;//门店编码
	
	private List<Map<String,String>> managerList;//管理分类编码列表
	
	private String manageType;//要约上的经营方式
	
	

	public String getManageType() {
		return manageType;
	}

	public void setManageType(String manageType) {
		this.manageType = manageType;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public List<Map<String, String>> getManagerList() {
		return managerList;
	}

	public void setManagerList(List<Map<String, String>> managerList) {
		this.managerList = managerList;
	}
	
	
	
}

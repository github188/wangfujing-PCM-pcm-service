/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.maindata.domain.voErpSupInfoListDto.java
 * @Create By wangc
 * @Create In 2016-3-18 下午4:25:05
 */
package com.wangfj.product.maindata.domain.vo;

import java.util.List;

/**
 * @Class Name ErpSupInfoListDto
 * @Author  wangc
 * @Create In 2016-3-18
 */
public class ErpSupInfoListDto {

	/**
	 * 专柜编码
	 */
	private String shoppeCode;
	/**
	 * 扣率码列表
	 */
	private List<ErpSupInfoDto> erplist ;

	
	

	public String getShoppeCode() {
		return shoppeCode;
	}

	public void setShoppeCode(String shoppeCode) {
		this.shoppeCode = shoppeCode;
	}

	public List<ErpSupInfoDto> getErplist() {
		return erplist;
	}

	public void setErplist(List<ErpSupInfoDto> erplist) {
		this.erplist = erplist;
	}
	
	
}

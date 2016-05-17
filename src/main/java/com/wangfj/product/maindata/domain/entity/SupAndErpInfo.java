/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.maindata.domain.entitySupAndErpInfo.java
 * @Create By wangc
 * @Create In 2016-3-18 下午4:52:51
 * TODO
 */
package com.wangfj.product.maindata.domain.entity;

import java.util.List;

import com.wangfj.product.maindata.domain.vo.ErpSupInfoListDto;

/**
 * @Class Name SupAndErpInfo
 * @Author  wangc
 * @Create In 2016-3-18
 */
public class SupAndErpInfo {

	private Long sid;
	/**
	 * 供应商编码
	 */
	private String supplierCode;
	/**
	 * 供应商名称
	 */
	private String supplierName;
	/**
	 * 专柜列表
	 */
	private List<ErpSupInfoListDto> shoppeList;
	
}

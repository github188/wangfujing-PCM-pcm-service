/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.maindata.domain.voContractLogFromPcmToSupDto.java
 * @Create By wangc
 * @Create In 2016-4-6 上午11:35:45
 * TODO
 */
package com.wangfj.product.maindata.domain.vo;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * 供应商根据时间获取合同信息参数接受类
 * 参数：开始时间结束时间门店及对应管理分类编码
 * @Class Name ContractLogFromPcmToSupDto
 * @Author  wangc
 * @Create In 2016-4-6
 */
public class ContractLogFromPcmToSupDto {

	private String storeCode;//门店编码
	
	private String manageCategoryCode;//管理分类编码
	
	private List<Map<String,String>> managerList;//管理分类编码列表
	
	private Timestamp beginDate;//开始时间
	
	private Timestamp endDate;//结束时间

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getManageCategoryCode() {
		return manageCategoryCode;
	}

	public void setManageCategoryCode(String manageCategoryCode) {
		this.manageCategoryCode = manageCategoryCode;
	}

	public List<Map<String, String>> getManagerList() {
		return managerList;
	}

	public void setManagerList(List<Map<String, String>> managerList) {
		this.managerList = managerList;
	}

	public Timestamp getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Timestamp beginDate) {
		this.beginDate = beginDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	
	
}

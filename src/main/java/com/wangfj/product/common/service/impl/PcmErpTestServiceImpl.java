package com.wangfj.product.common.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.common.domain.entity.PcmErpTest;
import com.wangfj.product.common.persistence.PcmErpTestMapper;
import com.wangfj.product.common.service.intf.IPcmErpTestService;
import com.wangfj.product.maindata.domain.vo.GetErpProductFromEfutureDto;
import com.wangfj.util.HttpUtil;
import com.wangfj.util.mq.MqRequestDataPara;
import com.wangfj.util.mq.RequestHeader;

@Service
public class PcmErpTestServiceImpl implements IPcmErpTestService {
	@Autowired
	PcmErpTestMapper erpTestMapper;

	public void erpDataMigration(int start, int limit) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("start", start);
		paramMap.put("limit", limit);
		List<PcmErpTest> erpTestList = erpTestMapper.selectPageListByParam(paramMap);
		List<GetErpProductFromEfutureDto> erpDtoList = new ArrayList<GetErpProductFromEfutureDto>();
		for (PcmErpTest erp : erpTestList) {
			GetErpProductFromEfutureDto pcm = new GetErpProductFromEfutureDto();
			pcm.setACTION_CODE(erp.getActCode());
			pcm.setACTION_DATE(erp.getActionDate());
			pcm.setACTION_PERSON(erp.getActPerson());
			pcm.setArticleNum(erp.getArticlenum());
			pcm.setBrandCode(erp.getBrandcode());
			pcm.setCommissionRate(erp.getCommissionrate().toString());
			pcm.setCounterCode(erp.getCountercode());
			pcm.setDiscountLimit(erp.getDiscountlimit().toString());
			pcm.setFormatType(erp.getFormattype());
			pcm.setInputTax(erp.getInputtax());
			pcm.setIsAdjustPrice(erp.getIsadjustprice());
			pcm.setIsPromotion(erp.getIspromotion());
			pcm.setManageCategory(erp.getManagecategory());
			pcm.setOriginLand(erp.getOriginland());
			pcm.setOutputTax(erp.getOutputtax());
			pcm.setProductCategory(erp.getProductcategory());
			pcm.setProductCode(erp.getProductcode());
			pcm.setProductName(erp.getProductname());
			pcm.setProductType(erp.getProducttype());
			pcm.setSalesPrice(erp.getSalesprice().toString());
			pcm.setSalesTax(erp.getSalestax());
			pcm.setSalesUnit(erp.getSalesunit());
			pcm.setServiceFeeType(erp.getServicefeetype());
			pcm.setSkuType(erp.getSkutype());
			pcm.setSpecName(erp.getSpecname());
			pcm.setStatCategory(erp.getStatcategory());
			pcm.setStatus(erp.getStatus());
			pcm.setStoreCode(erp.getStorecode());
			pcm.setSupplierBarcode(erp.getSupplierbarcode());
			pcm.setContractCode(erp.getContractCode());
			pcm.setSupplierCode(erp.getSuppliercode());
			erpDtoList.add(pcm);
		}
		Map<String, Object> headerMap = new HashMap<String, Object>();
		headerMap.put("count", "10");
		headerMap.put("reset", false);
		headerMap.put("titile", "消息描述");

		Map<String, Object> mqMap = new HashMap<String, Object>();
		mqMap.put("data", erpDtoList);
		mqMap.put("header", headerMap);
		mqMap.put("version", "1");

		// Map<String, Object> mqPara = new HashMap<String, Object>();
		// mqPara.put("data", JsonUtil.getJSONString(mqMap));
		// mqPara.put("header", "");

		MqRequestDataPara mqPara = new MqRequestDataPara();
		mqPara.setData(JsonUtil.getJSONString(mqMap));
		RequestHeader header = new RequestHeader();
		mqPara.setHeader(header);

		// System.out.println(mqPara);
		// System.out.println(JsonUtil.getJSONString(mqPara));
		HttpUtil.doPost(
				"http://10.6.3.14:8085/pcm-import/erpProductEfuture/uploadErpProductFromEFuture2.htm",
				JsonUtil.getJSONString(mqPara));
	}
}

/**
 * @Probject Name: pcm-service-outer
 * @Path: com.wangfj.product.supplier.service.implPcmSupShoProductServiceImpl.java
 * @Create By wangc
 * @Create In 2016-3-7 下午6:48:08
 * TODO
 */
package com.wangfj.product.supplier.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.supplier.domain.entity.GetShoProductSup;
import com.wangfj.product.supplier.domain.vo.PcmShoProductSupDataDto;
import com.wangfj.product.supplier.domain.vo.PcmShoppeProductForSupDto;
import com.wangfj.product.supplier.persistence.PcmSupShoProductMapper;
import com.wangfj.product.supplier.service.intf.IPcmSupShoProductService;

/**
 * 供应商商品service
 * @Class Name PcmSupShoProductServiceImpl
 * @Author wangc
 * @Create In 2016-3-7
 */
@Service
public class PcmSupShoProductServiceImpl implements IPcmSupShoProductService {

	private static final Logger logger = LoggerFactory
			.getLogger(PcmSupShoProductServiceImpl.class);
	@Autowired
	private PcmSupShoProductMapper supShoProductMapper;
	/**
	 * 供应商获取商品信息
	 */
	@Override
	public Page<PcmShoProductSupDataDto> getShoProductByParams(GetShoProductSup proDto) {
		logger.info("start getShoProductByParams() para="+ proDto.toString());
		Page<PcmShoProductSupDataDto> resultPage = new Page<PcmShoProductSupDataDto>();
		resultPage.setCurrentPage(proDto.getCurrentPage());//当前页
		resultPage.setPageSize(proDto.getPageSize());//每页条数
		Integer count = supShoProductMapper.getShoProductCount(proDto);
		if(count != null && count != 0){
			List<PcmShoProductSupDataDto> list = supShoProductMapper.getShoProductByParams(proDto);
			resultPage.setList(list);//如果有结果，设置结果集
		}
		resultPage.setCount(count);//设置结果数
		logger.info("end getShoProductByParams() result= "+resultPage.toString());
		return resultPage;
	}
	/**
	 * 供应商获取专柜商品详情
	 */
	@Override
	public List<PcmShoppeProductForSupDto> getShoppeProductForSupDto(GetShoProductSup proDto) {
		List<PcmShoppeProductForSupDto> resultList = supShoProductMapper.getShoppeProductForSupDto(proDto);
		return resultList;
	}

}

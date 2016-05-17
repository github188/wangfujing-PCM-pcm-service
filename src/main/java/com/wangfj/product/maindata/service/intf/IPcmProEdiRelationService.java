package com.wangfj.product.maindata.service.intf;

import java.util.List;

import com.wangfj.product.maindata.domain.vo.EdiProDto;
import com.wangfj.product.maindata.domain.vo.PcmEdiProductStockDto;
import com.wangfj.product.maindata.domain.vo.QueryEdiProductStockDto;
import com.wangfj.product.maindata.domain.vo.ResultDto;

public interface IPcmProEdiRelationService {
	ResultDto addShoppeProFromEdi(List<EdiProDto> dtoList);

	/**
	 * 根据专柜商品查询渠道商品的库存信息
	 * 
	 * @Methods Name selectEdiProStockInfo
	 * @Create In 2016年1月21日 By kongqf
	 * @param dto
	 * @return List<PcmEdiProductStockDto>
	 */
	List<PcmEdiProductStockDto> selectEdiProStockInfo(QueryEdiProductStockDto dto);
}

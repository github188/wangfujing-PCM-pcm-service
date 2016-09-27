package com.wangfj.product.maindata.service.intf;

import java.util.List;

import com.wangfj.product.maindata.domain.entity.PcmShoppeProductEdiRelation;
import com.wangfj.product.maindata.domain.vo.EdiProDto;
import com.wangfj.product.maindata.domain.vo.PcmEdiProductStockDto;
import com.wangfj.product.maindata.domain.vo.QueryEdiProductStockDto;
import com.wangfj.product.maindata.domain.vo.ResultDto;

public interface IPcmProEdiRelationService {
	/**
	 * edi查询信息基础service
	 * 
	 * @Methods Name selectListByParam
	 * @Create In 2016年7月19日 By yedong
	 * @param entity
	 * @return List<PcmShoppeProductEdiRelation>
	 */
	public List<PcmShoppeProductEdiRelation> selectListByParam(PcmShoppeProductEdiRelation entity);

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

	/**
	 * edi查询渠道库存
	 * 
	 * @Methods Name selectEdiProStockInfoByChannelId
	 * @Create In 2016年9月27日 By kongqf
	 * @param dto
	 * @return List<PcmEdiProductStockDto>
	 */
	List<PcmEdiProductStockDto> selectEdiProStockInfoByChannelId(QueryEdiProductStockDto dto);
}

package com.wangfj.product.maindata.persistence;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProductEdiRelation;
import com.wangfj.product.maindata.domain.vo.PcmEdiProductStockDto;
import com.wangfj.product.maindata.domain.vo.QueryEdiProductStockDto;

public interface PcmShoppeProductEdiRelationMapper extends BaseMapper<PcmShoppeProductEdiRelation> {
	int deleteByPrimaryKey(Long sid);

	Integer insert(PcmShoppeProductEdiRelation record);

	int insertSelective(PcmShoppeProductEdiRelation record);

	PcmShoppeProductEdiRelation selectByPrimaryKey(Long sid);

	List<PcmShoppeProductEdiRelation> getProIsPayReduceInfo(Map<String, Object> paramMap);

	int updateByPrimaryKeySelective(PcmShoppeProductEdiRelation record);

	int updateByPrimaryKey(PcmShoppeProductEdiRelation record);

	PcmEdiProductStockDto selectProStoctListByParam(QueryEdiProductStockDto dto);

	List<PcmEdiProductStockDto> selectProStoctListByParam2(QueryEdiProductStockDto dto);

	List<PcmEdiProductStockDto> selectEdiProtListByProCode(QueryEdiProductStockDto dto);
}
package com.wangfj.product.maindata.persistence;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.entity.PcmErpProduct;
import com.wangfj.product.maindata.domain.vo.ErpProPageDto;
import com.wangfj.product.maindata.domain.vo.GetSupAndErpInfoDto;

public interface PcmErpProductMapper extends BaseMapper<PcmErpProduct> {
	int deleteByPrimaryKey(Long sid);

	int insertSelective(PcmErpProduct record);

	PcmErpProduct selectByPrimaryKey(Long sid);

	List<Map<String, Object>> getBrandShopInfo(Map<String, Object> paramMap);

	/**
	 * 查询扣率码（含促销扣率码）
	 * 
	 * @Methods Name selectRateCodeByParam
	 * @Create In 2016年2月23日 By yedong
	 * @param record
	 * @return PcmErpProduct
	 */
	List<PcmErpProduct> selectRateCodeByParam(PcmErpProduct record);

	PcmErpProduct selectByProCode(String productCode);

	int updateByPrimaryKeySelective(PcmErpProduct record);

	int updateByPrimaryKey(PcmErpProduct record);

	int updateByCodeSelective(PcmErpProduct record);

	List<PcmErpProduct> selectErpPageByParam(Map<String, Object> paramMap);

	List<PcmErpProduct> selectPageListByParam1newPrice(Map<String, Object> paramMap);
	
	List<ErpProPageDto> selectPageByParamPage(Map<String, Object> paramMap);

	Integer getCountByParam(Map<String, Object> paramMap);
	/**
	 * 查询专柜信息
	 */
	GetSupAndErpInfoDto GetShoppeInfoAndErp(Map<String,String> paraMap);
}
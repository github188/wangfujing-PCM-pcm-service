package com.wangfj.product.category.persistence;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.category.domain.entity.PcmProductParameters;
import com.wangfj.product.category.domain.vo.PcmProductParaDto;
import com.wangfj.product.category.domain.vo.PcmProductParametersVo;
import com.wangfj.product.maindata.domain.vo.SaveProductParametersDTO;

public interface PcmProductParametersMapper extends BaseMapper<PcmProductParameters> {
	List<PcmProductParameters> getPropInfoBySpuCode(Map<String, Object> paramMap);

	int deleteByPrimaryKey(Long sid);

	int insertSelective(PcmProductParameters record);

	PcmProductParameters selectByPrimaryKey(Long sid);

	List<PcmProductParameters> selectCateBySpuCode(PcmProductParameters record);

	int updateByPrimaryKeySelective(PcmProductParameters record);

	List<SaveProductParametersDTO> cateListSelect(Map<String, Object> paramMap);

	int updateByPrimaryKey(PcmProductParameters record);

	int deleteByProductSid(Long productSid, Long channelSid, Long categorySid);

	int deleteByProductSid_1(PcmProductParameters ppp_1);

	List<PcmProductParameters> selectList(PcmProductParameters record);

	List<PcmProductParameters> selectProps(PcmProductParameters record);

	List<String> selectValues(PcmProductParameters record);

	List<String> getShopName(Long productSid);

	int insertBatch(List<PcmProductParameters> list);

	/**
	 * 查询商品属性列表
	 * 
	 * @Methods Name selectParasList
	 * @Create In 2015-3-18 By chengsj
	 * @param record
	 * @return List<Map<String,Object>>
	 */
	List<Map<String, Object>> selectParasList(PcmProductParameters record);

	/**
	 * 根据spusid 查询产品属性信息
	 * 
	 * @Methods Name selectListBySpuSid
	 * @Create In 2015年8月25日 By duanzhaole
	 * @param record
	 * @return List<PcmProductParametersVo>
	 */
	List<PcmProductParametersVo> selectListBySpuSid(PcmProductParameters record);

	List<PcmProductParametersVo> selectProductBySpuSid(PcmProductParameters record);

	List<PcmProductParaDto> selectListSelect(PcmProductParameters record);

}
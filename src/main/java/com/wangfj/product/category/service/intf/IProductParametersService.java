package com.wangfj.product.category.service.intf;

import java.util.List;
import java.util.Map;

import com.wangfj.product.category.domain.entity.PcmProductParameters;
import com.wangfj.product.category.domain.vo.PcmProductParaDto;
import com.wangfj.product.maindata.domain.vo.SaveProductParametersDTO;

public interface IProductParametersService {

	int delete(Long sid);

	int deleteByCPSid(String productSid, String channelSid, String categorySid);

	int save(PcmProductParameters record);

	int saveorupdate(PcmProductParameters record);

	PcmProductParameters get(Long sid);

	int update(PcmProductParameters record);

	List<PcmProductParameters> selectList(PcmProductParameters record);

	public List<PcmProductParaDto> selectListSelect(PcmProductParameters record);

	/**
	 * 根据SPUSID查询属性及属性值
	 * 
	 * @Methods Name cateListSelect
	 * @Create In 2015年10月24日 By yedong
	 * @param paramMap
	 * @return List<SaveProductParametersDTO>
	 */
	public List<SaveProductParametersDTO> cateListSelect(Map<String, Object> paramMap);

	/**
	 * 批量插入
	 * 
	 * @Methods Name insertBatch
	 * @Create In 2015-3-11 By chengsj
	 * @param list
	 * @return int
	 */
	int insertBatch(List<PcmProductParameters> list);

	int insertProductParamter(SaveProductParametersDTO sppd);

	int insertProductParamter1(SaveProductParametersDTO sppd);
	public int productCatePropValue(List<SaveProductParametersDTO> sppdList);

	/**
	 * 查询工业分类属性、属性值BySpuSid
	 * 
	 * @Methods Name getGYCatePropValueBySpuSid
	 * @Create In 2015年11月27日 By yedong
	 * @param paramMap
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> getGYCatePropValueBySpuSid(Map<String, Object> paramMap);

}

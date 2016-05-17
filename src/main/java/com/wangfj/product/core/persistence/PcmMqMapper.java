package com.wangfj.product.core.persistence;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.core.domain.dto.PcmMqDto;
import com.wangfj.product.core.domain.entity.PcmMq;

public interface PcmMqMapper extends BaseMapper<PcmMq> {

	List<PcmMq> pcmMqInfoSendAgainJob();

	List<PcmMq> selectPageListByParam(PcmMqDto pcmMqDto);

	Integer getCountByParam(PcmMqDto pcmMqDto);

	int deleteByPrimaryKey(Long sid);

	Integer insert(PcmMq record);

	int insertSelective(PcmMq record);

	PcmMq selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmMq record);

	int updateByPrimaryKeyWithBLOBs(PcmMq record);

	int updateByPrimaryKey(PcmMq record);

	/**
	 * 分页查询
	 * 
	 * @Methods Name selectPageByParam
	 * @Create In 2015-9-10 By chenhu
	 * @param paramMap
	 * @return List<PcmMq>
	 */
	List<PcmMq> selectPageByParam(Map<String, Object> paramMap);

	/**
	 * 分页查询总数量
	 * 
	 * @Methods Name getPageCountByParam
	 * @Create In 2015-9-10 By chenhu
	 * @param paramMap
	 * @return Integer
	 */
	Integer getPageCountByParam(Map<String, Object> paramMap);

}
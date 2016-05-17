package com.wangfj.product.maindata.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.entity.PcmProductDesc;
import com.wangfj.product.maindata.domain.vo.PcmProductDescDto;
import com.wangfj.product.maindata.domain.vo.PcmProductDescQueryDto;

import java.util.List;

public interface PcmProductDescMapper extends BaseMapper<PcmProductDesc> {
	int deleteByPrimaryKey(Long sid);

	int insertSelective(PcmProductDesc record);

	PcmProductDesc selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmProductDesc record);

	int updateByPrimaryKeyWithBLOBs(PcmProductDesc record);

	/**
	 * 查询List
	 * 
	 * @param dto
	 * @return
	 */
	List<PcmProductDescDto> findListByParam(PcmProductDescQueryDto dto);

	/**
	 * 分页查询
	 * 
	 * @param dto
	 * @return
	 */
	List<PcmProductDescDto> findPageByParam(PcmProductDescQueryDto dto);

	/**
	 * 分页查询数量
	 * 
	 * @param dto
	 * @return
	 */
	Integer findCountByParam(PcmProductDescQueryDto dto);

	/**
	 * 修改商品描述
	 * 
	 * @param record
	 * @return
	 */
	int updateContentByPrimaryKey(PcmProductDesc record);

}
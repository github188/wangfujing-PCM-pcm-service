package com.wangfj.product.organization.persistence;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.organization.domain.entity.PcmRegion;
import com.wangfj.product.organization.domain.vo.PcmRegionQueryDto;
import com.wangfj.product.organization.domain.vo.PcmRegionResultDto;

/**
 * 行政区域 Mapper
 * 
 * @Class Name PcmRegionMapper
 * @Author yedong
 * @Create In 2015年7月14日
 */
public interface PcmRegionMapper extends BaseMapper<PcmRegion> {
	int deleteByPrimaryKey(Long sid);

	Integer insert(PcmRegion record);

	int insertSelective(PcmRegion record);

	PcmRegion selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmRegion record);

	int updateByPrimaryKey(PcmRegion record);

	List<Map<String, Object>> pushRegionData(Map<String, Object> paramMap);

	List<PcmRegion> selectListByParam(Map<String, Object> paramMap);

	Integer getPageCountByParam(PcmRegionQueryDto dto);

	List<PcmRegionResultDto> findPageRegionByParam(PcmRegionQueryDto dto);

	/**
	 * 根据编码查询所有的下级
	 * 
	 * @Methods Name getRegionChildrenListByCode
	 * @Create In 2015-12-14 By wangxuan
	 * @param dto
	 * @return List<PcmRegionResultDto>
	 */
	List<PcmRegionResultDto> getRegionChildrenListByCode(PcmRegionQueryDto dto);

}
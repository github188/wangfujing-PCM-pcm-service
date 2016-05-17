package com.wangfj.product.organization.persistence;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.organization.domain.entity.PcmChannelSaleConfig;
import com.wangfj.product.organization.domain.vo.PcmChannelSaleConfigQueryDto;
import com.wangfj.product.organization.domain.vo.PcmChannelSaleConfigResultDto;

/**
 * 
 * @Class Name PcmChannelSaleConfigMapper
 * @Author yedong
 * @Create In 2015年7月3日
 */
public interface PcmChannelSaleConfigMapper extends BaseMapper<PcmChannelSaleConfig> {
	/**
	 * 删除
	 * 
	 * @Methods Name deleteByPrimaryKey
	 * @Create In 2015年7月3日 By yedong
	 * @param sid
	 * @return int
	 */
	int deleteByPrimaryKey(Long sid);

	/**
	 * 添加
	 * 
	 * @Methods Name insertSelective
	 * @Create In 2015年7月3日 By yedong
	 * @param record
	 * @return int
	 */
	int insertSelective(PcmChannelSaleConfig record);

	/**
	 * 修改
	 * 
	 * @Methods Name updateByPrimaryKeySelective
	 * @Create In 2015年7月3日 By yedong
	 * @param record
	 * @return int
	 */
	int updateByPrimaryKeySelective(PcmChannelSaleConfig record);

	List<String> getChannelSaleByParam(PcmChannelSaleConfig record);

	List<PcmChannelSaleConfig> selectListByParam(Map<String, Object> paramMap);

	/**
	 * 根据专柜sid查询渠道可售
	 * 
	 * @Methods Name findListByShoppeParam
	 * @Create In 2015-11-25 By wangxuan
	 * @param dto
	 * @return List<PcmChannelSaleConfigResultDto>
	 */
	List<PcmChannelSaleConfigResultDto> findListByShoppeParam(PcmChannelSaleConfigQueryDto dto);

	List<PcmChannelSaleConfigResultDto> findListByShoppeProParam(PcmChannelSaleConfigQueryDto dto);

}
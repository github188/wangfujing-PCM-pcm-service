package com.wangfj.product.organization.persistence;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.organization.domain.entity.PcmChannel;
import com.wangfj.product.organization.domain.vo.PcmChannelDto;

/**
 * 
 * @Class Name PcmChannelMapper
 * @Author yedong
 * @Create In 2015年7月3日
 */
public interface PcmChannelMapper extends BaseMapper<PcmChannel> {
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
	int insertSelective(PcmChannel record);

	/**
	 * 修改
	 * 
	 * @Methods Name updateByPrimaryKeySelective
	 * @Create In 2015年7月3日 By yedong
	 * @param record
	 * @return int
	 */
	int updateByPrimaryKeySelective(PcmChannel record);

	/**
	 * 查询渠道信息
	 * 
	 * @Methods Name PcmChannel
	 * @Create In 2015年7月31日 By duanzhaole
	 * @param record
	 * @return List<PcmChannel>
	 */
	List<PcmChannel> selectList(PcmChannel record);

	/**
	 * 分页查询
	 * 
	 * @Methods Name selectPageByPara
	 * @Create In 2015-8-21 By wangxuan
	 * @param paramMap
	 * @return List<PcmChannel>
	 */
	List<PcmChannel> selectPageByPara(Map<String, Object> paramMap);

	/**
	 * 分页总数查询
	 * 
	 * @Methods Name getPageCountByPara
	 * @Create In 2015-8-21 By wangxuan
	 * @param paramMap
	 * @return Integer
	 */
	Integer getPageCountByPara(Map<String, Object> paramMap);

	List<Map<String, Object>> pushChannelData(Map<String, Object> paramMap);

    /**
     * 根据SPU（编码）查询渠道
     *
     * @Methods Name findChannelBySPUPara
     * @Create In 2016-3-1 By wangxuan
     * @param paramMap
     * @return List<PcmChannelDto>
     */
	List<PcmChannelDto> findChannelBySPUPara(Map<String, Object> paramMap);
}
package com.wangfj.product.category.service.intf;

import java.util.List;

import com.wangfj.product.category.domain.entity.PcmCategoryValuesDict;
import com.wangfj.product.category.domain.vo.PcmCategoryValuesDictDto;

public interface ICategoryValuesDictService {

	int delete(Long sid);

	int save(PcmCategoryValuesDict record);

	int saveorupdate(PcmCategoryValuesDict record);

	int saveorupdate(List<PcmCategoryValuesDict> list);

	PcmCategoryValuesDict get(Long sid);

	PcmCategoryValuesDict getByValuesSidAndChannelSid(Long valuesSid, Long channelSid);

	List<PcmCategoryValuesDict> getByPropsSidAndChannelSid(Long propsSid, Long channelSid);

	int update(PcmCategoryValuesDict record);

	int updateStatus(Long propsSid, Long channelSid);

	List<PcmCategoryValuesDict> selectList(PcmCategoryValuesDict record);

	List<PcmCategoryValuesDictDto> selectPage(PcmCategoryValuesDict record);

	int selectPageTotal(PcmCategoryValuesDict record);

	Long getMaxSortOrder(Long channelSid, Long propsSid);

	List<PcmCategoryValuesDict> selectListByCategorySid(PcmCategoryValuesDictDto pros);

	/**
	 * 通过属性SID获取属性值信息
	 * 
	 * @Methods Name selectValueDictByPropSid
	 * @Create In 2015年11月24日 By yedong
	 * @param propSid
	 * @return List<PcmCategoryValuesDict>
	 */
	public List<PcmCategoryValuesDict> selectValueDictByPropSid(String propSid);
}

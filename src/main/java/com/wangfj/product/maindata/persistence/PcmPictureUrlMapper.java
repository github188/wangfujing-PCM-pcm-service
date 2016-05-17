package com.wangfj.product.maindata.persistence;

import java.util.List;
import java.util.Map;

import com.wangfj.product.maindata.domain.entity.PcmPictureUrl;

public interface PcmPictureUrlMapper {
	int deleteByPrimaryKey(Long sid);

	int insert(PcmPictureUrl record);

	int insertSelective(PcmPictureUrl record);

	PcmPictureUrl selectByPrimaryKey(Long sid);

	int updateMainBySpuColor(PcmPictureUrl record);

	int updateDeleteBySpuColor(PcmPictureUrl record);

	int updateByPrimaryKeySelective(PcmPictureUrl record);

	int updateByPrimaryKey(PcmPictureUrl record);

	List<PcmPictureUrl> getSidAndPhoneSid(Map<String, Object> paramMap);

	List<PcmPictureUrl> selectListByParam(PcmPictureUrl record);

	/**
	 * 根据条件查询商品图片信息
	 * 
	 * @Methods Name queryProductPictureInfoByPara
	 * @Create In 2015年9月21日 By kongqf
	 * @param record
	 * @return List<PcmPictureUrl>
	 */
	List<PcmPictureUrl> queryProductPictureInfoByPara(PcmPictureUrl record);

	Integer getSortByOara(PcmPictureUrl record);

	List<Map<String, Object>> selectColorBySpu(String spuCode);
}
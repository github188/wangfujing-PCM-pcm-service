package com.wangfj.product.maindata.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.entity.PcmPictureStan;

public interface PcmPictureStanMapper extends BaseMapper<PcmPictureStan> {
	int deleteByPrimaryKey(Long sid);

	Integer insert(PcmPictureStan record);

	int insertSelective(PcmPictureStan record);

	PcmPictureStan selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmPictureStan record);

	int updateByPrimaryKey(PcmPictureStan record);
}
package com.wangfj.product.maindata.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.entity.PcmPhotoDetail;

public interface PcmPhotoDetailMapper extends BaseMapper<PcmPhotoDetail> {
	int deleteByPrimaryKey(Long sid);

	int insertSelective(PcmPhotoDetail record);

	PcmPhotoDetail selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmPhotoDetail record);

	int updateByPrimaryKey(PcmPhotoDetail record);
}
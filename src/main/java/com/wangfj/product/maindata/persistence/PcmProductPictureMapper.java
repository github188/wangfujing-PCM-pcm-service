package com.wangfj.product.maindata.persistence;

import java.util.List;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.entity.PcmProductPicture;
import com.wangfj.product.maindata.domain.vo.PcmSapResUrlDto;
import com.wangfj.product.stocks.domain.vo.PcmSpuSkuProInfoDto;

public interface PcmProductPictureMapper extends BaseMapper<PcmProductPicture> {
	int deleteByPrimaryKey(Long sid);

	int insertSelective(PcmProductPicture record);

	PcmProductPicture selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmProductPicture record);

	int updateByPrimaryKey(PcmProductPicture record);

	List<PcmSapResUrlDto> getUrlBySpuCodeAndColor(PcmSpuSkuProInfoDto record);
}
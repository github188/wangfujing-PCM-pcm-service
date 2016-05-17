package com.wangfj.product.maindata.persistence;

import java.util.List;

import com.wangfj.product.maindata.domain.entity.PcmProDesc;
import com.wangfj.product.maindata.domain.entity.PcmProductMemo;

public interface PcmProductMemoMapper {
	int deleteByPrimaryKey(Integer sid);

	int insert(PcmProductMemo record);

	int insertSelective(PcmProductMemo record);

	PcmProductMemo selectByPrimaryKey(String skuSid);

	int updateByPrimaryKeySelective(PcmProductMemo record);

	int updateByPrimaryKey(PcmProductMemo record);
	
	List<PcmProDesc> selectBySKUCode(String SkuCode);
}
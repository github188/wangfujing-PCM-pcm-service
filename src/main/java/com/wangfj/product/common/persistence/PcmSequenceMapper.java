package com.wangfj.product.common.persistence;

import com.wangfj.product.common.domain.entity.PcmSequence;
import com.wangfj.product.common.domain.vo.PcmSequenceDto;

public interface PcmSequenceMapper {
	int deleteByPrimaryKey(Long seq);

	int insert(PcmSequence record);

	int insertSelective(PcmSequence record);

	PcmSequence selectByPrimaryKey(Long seq);

	int updateByPrimaryKeySelective(PcmSequence record);

	int updateByPrimaryKey(PcmSequence record);

	/**
	 * 新增序列号
	 * 
	 * @Methods Name generateSeq
	 * @Create In 2015年9月14日 By kongqf
	 * @param record
	 * @return int
	 */
	int generateSeq(PcmSequenceDto record);
}
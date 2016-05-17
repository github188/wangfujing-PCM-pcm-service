package com.wangfj.product.common.service.intf;

import com.wangfj.product.common.domain.vo.PcmSequenceDto;

public interface IPcmSequenceService {

	/**
	 * 自动生成序列号
	 * 
	 * @Methods Name GenerateSeq
	 * @Create In 2015年9月14日 By kongqf
	 * @param seqDto
	 * @return String
	 */
	public String GenerateSeq(PcmSequenceDto seqDto);

}

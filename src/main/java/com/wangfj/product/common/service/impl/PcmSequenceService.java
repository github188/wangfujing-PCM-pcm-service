package com.wangfj.product.common.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.product.common.domain.vo.PcmSequenceDto;
import com.wangfj.product.common.persistence.PcmSequenceMapper;
import com.wangfj.product.common.service.intf.IPcmSequenceService;

@Service
public class PcmSequenceService implements IPcmSequenceService {
	@Autowired
	private PcmSequenceMapper seqMapper;

	/**
	 * 自动生成序列号 ; 返回结果"0"标识生成失败
	 * 
	 * @Methods Name GenerateSeq
	 * @Create In 2015年9月14日 By kongqf
	 * @param seqDto
	 * @return String
	 */
	@Override
	public String GenerateSeq(PcmSequenceDto seqDto) {
		int count = seqMapper.generateSeq(seqDto);
		if (count == 1) {
			if (StringUtils.isNotBlank(seqDto.getPrefix()))
				return seqDto.getPrefix() + StringUtils.leftPad(seqDto.getSeq().toString(),
						seqDto.getSeqLength(), "0");
			else {
				return StringUtils.leftPad(seqDto.getSeq().toString(), seqDto.getSeqLength(), "0");
			}
		} else {
			return "0";
		}
	}

}

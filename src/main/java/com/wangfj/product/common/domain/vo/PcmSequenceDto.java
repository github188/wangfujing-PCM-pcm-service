package com.wangfj.product.common.domain.vo;

public class PcmSequenceDto {
	/**
	 * 自增序列
	 */
	private Long seq;
	/**
	 * 序列名称
	 */
	private String name;

	/**
	 * 当前值
	 */
	private Integer currentValue;

	/**
	 * 增加值
	 */
	private Integer increment;

	/**
	 * 序列号长度
	 */
	private int seqLength;

	/**
	 * 序列号前缀
	 */
	private String prefix;

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(Integer currentValue) {
		this.currentValue = currentValue;
	}

	public int getSeqLength() {
		return seqLength;
	}

	public void setSeqLength(int seqLength) {
		this.seqLength = seqLength;
	}

	public Integer getIncrement() {
		return increment;
	}

	public void setIncrement(Integer increment) {
		this.increment = increment;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public String toString() {
		return "PcmSequenceDto [seq=" + seq + ", name=" + name + ", currentValue=" + currentValue
				+ ", increment=" + increment + ", seqLength=" + seqLength + ", prefix=" + prefix
				+ "]";
	}

}
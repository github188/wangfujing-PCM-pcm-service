package com.wangfj.product.stocks.domain.vo;

/**
 * 用作库存操作后的返回结果
 * 
 * @Class Name StockResultDto
 * @Author yedong
 * @Create In 2015年7月17日
 */
public class StockResultDto {
	/*
	 * 成功失败表示(1:成功; 0:失败)
	 */
	private String resultFlag;
	/*
	 * 返回结果描述
	 */
	private String resultMsg;

	public String getResultFlag() {
		return resultFlag;
	}

	public void setResultFlag(String resultFlag) {
		this.resultFlag = resultFlag;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	@Override
	public String toString() {
		return "StockResultDto [resultFlag=" + resultFlag + ", resultMsg=" + resultMsg + "]";
	}

}

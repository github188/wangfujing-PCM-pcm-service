package com.wangfj.product.stocks.domain.vo;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * 
 * @Class Name PcmStockTypeDictDto
 * @Author yedong
 * @Create In 2015年7月3日
 */
public class PcmStockTypeDictDto extends BaseDto {
	private Integer sid;

	private String stockName; /* 库类型名称 */

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName == null ? null : stockName.trim();
	}

	@Override
	public String toString() {
		return "PcmStockTypeDictDto [sid=" + sid + ", stockName=" + stockName + "]";
	}
}

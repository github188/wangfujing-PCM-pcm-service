package com.wangfj.product.stocks.domain.entity;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * 
 * @Class Name PcmStockTypeDict
 * @Author yedong
 * @Create In 2015年7月3日
 */
public class PcmStockTypeDict extends BaseEntity {/* 库类型 */
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
}
package com.wangfj.product.price.domain.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 批量查询价格
 * 
 * @Class Name QueryPriceDto
 * @Author kongqf
 * @Create In 2015年7月28日
 */
public class QueryPriceListDto {

	/**
	 * 专柜商品sid
	 */
	private List<String> shoppeProSids=new ArrayList<String>();

	public List<String> getShoppeProSids() {
		return shoppeProSids;
	}

	public void setShoppeProSids(List<String> shoppeProSids) {
		this.shoppeProSids = shoppeProSids;
	}

	@Override
	public String toString() {
		return "QueryPriceListDto [shoppeProSids=" + shoppeProSids + "]";
	}

}

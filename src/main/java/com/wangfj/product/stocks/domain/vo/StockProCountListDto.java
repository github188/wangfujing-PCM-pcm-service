package com.wangfj.product.stocks.domain.vo;

import java.util.ArrayList;
import java.util.List;

import com.wangfj.core.framework.base.dto.BaseDto;
import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * 库存接口Dto
 * 
 * @Class Name StockProCountListDto
 * @Author yedong
 * @Create In 2015年7月17日
 */
public class StockProCountListDto extends BaseEntity {
	/*
	 * 销售单号
	 */
	private String saleNo;
	/*
	 * 脱机标示（1：脱机 0：联网）
	 */
	private Integer isOfferLine;
	/*
	 * 商品列表
	 */
	private List<StockProCountDto> products = new ArrayList<StockProCountDto>();
	/*
	 * 操作类型
	 */
	private int czType;

	private String fromSystem;

	public int getCzType() {
		return czType;
	}

	public void setCzType(int czType) {
		this.czType = czType;
	}

	public String getSaleNo() {
		return saleNo;
	}

	public void setSaleNo(String saleNo) {
		this.saleNo = saleNo;
	}

	public Integer getIsOfferLine() {
		return isOfferLine;
	}

	public void setIsOfferLine(Integer isOfferLine) {
		this.isOfferLine = isOfferLine;
	}

	public List<StockProCountDto> getProducts() {
		return products;
	}

	public void setProducts(List<StockProCountDto> products) {
		this.products = products;
	}

	public String getFromSystem() {
		return fromSystem;
	}

	public void setFromSystem(String fromSystem) {
		this.fromSystem = fromSystem;
	}

	@Override
	public String toString() {
		return "StockProCountListDto [saleNo=" + saleNo + ", isOfferLine=" + isOfferLine
				+ ", products=" + products + ", czType=" + czType + ", fromSystem=" + fromSystem
				+ "]";
	}

}

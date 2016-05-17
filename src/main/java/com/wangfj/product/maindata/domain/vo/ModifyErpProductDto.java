package com.wangfj.product.maindata.domain.vo;

import java.util.List;

import com.wangfj.product.maindata.domain.entity.PcmShoppeProduct;

public class ModifyErpProductDto {
	private List<PcmShoppeProduct> list;
	private Long sid;

	public List<PcmShoppeProduct> getList() {
		return list;
	}

	public void setList(List<PcmShoppeProduct> list) {
		this.list = list;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	@Override
	public String toString() {
		return "ModifyErpProductDto{" + "list=" + list + ", sid=" + sid + '}';
	}
}

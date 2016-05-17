package com.wangfj.product.maindata.domain.vo;

import com.wangfj.core.framework.base.page.Page;

public class ProPageDto {
	private Page<ShoppeProductDto> page;

	public Page<ShoppeProductDto> getPage() {
		return page;
	}

	public void setPage(Page<ShoppeProductDto> page) {
		this.page = page;
	}

}

package com.wangfj.product.maindata.domain.vo;

import com.wangfj.core.framework.base.page.Page;

public class SkuPageCacheDto {
	private Page<SkuPageDto> page;

	public Page<SkuPageDto> getPage() {
		return page;
	}

	public void setPage(Page<SkuPageDto> page) {
		this.page = page;
	}

}

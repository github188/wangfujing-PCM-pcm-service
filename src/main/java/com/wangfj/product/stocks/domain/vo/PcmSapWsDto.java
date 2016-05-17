package com.wangfj.product.stocks.domain.vo;

import java.util.List;

import com.wangfj.product.maindata.domain.vo.PcmSapResUrlDto;

public class PcmSapWsDto {
	private List<PcmSapResUrlDto> urlBySpuCodeAndColor;
	private boolean falg;

	public List<PcmSapResUrlDto> getUrlBySpuCodeAndColor() {
		return urlBySpuCodeAndColor;
	}

	public void setUrlBySpuCodeAndColor(List<PcmSapResUrlDto> urlBySpuCodeAndColor) {
		this.urlBySpuCodeAndColor = urlBySpuCodeAndColor;
	}

	public boolean isFalg() {
		return falg;
	}

	public void setFalg(boolean falg) {
		this.falg = falg;
	}

}

package com.wangfj.product.maindata.domain.vo;

import java.util.ArrayList;
import java.util.List;

import com.wangfj.util.mq.PublishDTO;

public class PcmSjQyDto {
	List<PublishDTO> publishList = new ArrayList<PublishDTO>();
	private String pcmEfutureERP; // 门店
	private String pcmSapErp; // SAP
	private String pcmEfuturePromotion; // 富基
	private String pcmSearcherOffline; // 搜索线下
	private String pcmSearcherOnline; // 搜索线上上架
	private String pcmSearcherOnline2; // 搜索线上下架
	private String pcmProSearch;

	public List<PublishDTO> getPublishList() {
		return publishList;
	}

	public void setPublishList(List<PublishDTO> publishList) {
		this.publishList = publishList;
	}

	public String getPcmEfutureERP() {
		return pcmEfutureERP;
	}

	public void setPcmEfutureERP(String pcmEfutureERP) {
		this.pcmEfutureERP = pcmEfutureERP;
	}

	public String getPcmSapErp() {
		return pcmSapErp;
	}

	public void setPcmSapErp(String pcmSapErp) {
		this.pcmSapErp = pcmSapErp;
	}

	public String getPcmEfuturePromotion() {
		return pcmEfuturePromotion;
	}

	public void setPcmEfuturePromotion(String pcmEfuturePromotion) {
		this.pcmEfuturePromotion = pcmEfuturePromotion;
	}

	public String getPcmSearcherOffline() {
		return pcmSearcherOffline;
	}

	public void setPcmSearcherOffline(String pcmSearcherOffline) {
		this.pcmSearcherOffline = pcmSearcherOffline;
	}

	public String getPcmSearcherOnline() {
		return pcmSearcherOnline;
	}

	public void setPcmSearcherOnline(String pcmSearcherOnline) {
		this.pcmSearcherOnline = pcmSearcherOnline;
	}

	public String getPcmSearcherOnline2() {
		return pcmSearcherOnline2;
	}

	public void setPcmSearcherOnline2(String pcmSearcherOnline2) {
		this.pcmSearcherOnline2 = pcmSearcherOnline2;
	}

	public String getPcmProSearch() {
		return pcmProSearch;
	}

	public void setPcmProSearch(String pcmProSearch) {
		this.pcmProSearch = pcmProSearch;
	}

}

package com.wangfj.product.maindata.domain.vo;

import java.util.ArrayList;
import java.util.List;

import com.wangfj.util.mq.PublishDTO;

public class ProSkuSpuPublishDto {
	private List<PublishDTO> proList = new ArrayList<PublishDTO>();
	private List<PublishDTO> skuList = new ArrayList<PublishDTO>();
	private List<PublishDTO> spuList = new ArrayList<PublishDTO>();
	private String proType;// 上架0 or 下架1

	public String getProType() {
		return proType;
	}

	public void setProType(String proType) {
		this.proType = proType;
	}

	public List<PublishDTO> getProList() {
		return proList;
	}

	public void setProList(List<PublishDTO> proList) {
		this.proList = proList;
	}

	public List<PublishDTO> getSkuList() {
		return skuList;
	}

	public void setSkuList(List<PublishDTO> skuList) {
		this.skuList = skuList;
	}

	public List<PublishDTO> getSpuList() {
		return spuList;
	}

	public void setSpuList(List<PublishDTO> spuList) {
		this.spuList = spuList;
	}

}

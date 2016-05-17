package com.wangfj.product.maindata.domain.vo;

import java.util.ArrayList;
import java.util.List;

public class PcmSearchOnlineProDto {
	/*
	 * 专柜商品编码
	 */
	private String itemId;
	/*
	 * SKU编码
	 */
	private String skuId;
	/*
	 * 供应商编码
	 */
	private String supplierId;
	/*
	 * 库存方式
	 */
	private String stockMode;
	/*
	 * 可售库存
	 */
	private String inventory;
	/*
	 * 原价
	 */
	private String originalPrice;
	/*
	 * 现价
	 */
	private String currentPrice;
	/*
	 * 渠道可售
	 */
	private List<String> channel;

	/*
	 * 标签List
	 */
	private List<PcmChannelSearchDto> active = new ArrayList<PcmChannelSearchDto>();

	public List<PcmChannelSearchDto> getActive() {
		return active;
	}

	public void setActive(List<PcmChannelSearchDto> active) {
		this.active = active;
	}

	public List<String> getChannel() {
		return channel;
	}

	public void setChannel(List<String> channel) {
		this.channel = channel;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getStockMode() {
		return stockMode;
	}

	public void setStockMode(String stockMode) {
		this.stockMode = stockMode;
	}

	public String getInventory() {
		return inventory;
	}

	public void setInventory(String inventory) {
		this.inventory = inventory;
	}

	public String getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(String currentPrice) {
		this.currentPrice = currentPrice;
	}

	@Override
	public String toString() {
		return "PcmSearchOnlineProDto [itemId=" + itemId + ", skuId=" + skuId + ", supplierId="
				+ supplierId + ", stockMode=" + stockMode + ", inventory=" + inventory
				+ ", originalPrice=" + originalPrice + ", currentPrice=" + currentPrice
				+ ", channel=" + channel + ", active=" + active + "]";
	}

}

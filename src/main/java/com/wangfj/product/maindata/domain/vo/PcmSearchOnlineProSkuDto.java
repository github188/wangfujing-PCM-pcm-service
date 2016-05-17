package com.wangfj.product.maindata.domain.vo;

import java.util.ArrayList;
import java.util.List;

public class PcmSearchOnlineProSkuDto {
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
	/*
	 * 图片Sid
	 */
	private String pictureSid;
	/*
	 * sku上架时间
	 */
	private String upTime;
	/*
	 * 长标题
	 */
	private String title;
	/*
	 * 短标题
	 */
	private String subTitle;
	/*
	 * 活动关键字
	 */
	private List<String> activeKeywords;
	/**
	 * SPU编码
	 */
	private String spuId;
	/**
	 * 商品类型
	 */
	private String type;
	/**
	 * 可售状态
	 */
	private boolean onSell;
	/**
	 * 色系编码
	 */
	private String colorId;
	/**
	 * 色系名称
	 */
	private String colorName;
	/**
	 * 商品颜色别名、色码
	 */
	private String colorAlias;
	/**
	 * 规格编码
	 */
	private String standardId;
	/**
	 * 规格名称
	 */
	private String standardName;
	/**
	 * 图片信息
	 */
	List<SearchOnlineUrlInfoDto> pictures = new ArrayList<SearchOnlineUrlInfoDto>();
}

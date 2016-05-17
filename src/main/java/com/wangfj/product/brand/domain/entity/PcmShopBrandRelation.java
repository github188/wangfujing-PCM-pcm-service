package com.wangfj.product.brand.domain.entity;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * 门店与品牌关系实体
 * 
 * @Class Name PcmShopBrandRelation
 * @Author wangxuan
 * @Create In 2015-8-19
 */
public class PcmShopBrandRelation extends BaseEntity {

	/**
	 * @Field long serialVersionUID
	 */
	private static final long serialVersionUID = -8265822255597066345L;

	private Long sid;

	private String shopSid;// 门店sid

	private String brandSid;// 门店品牌sid

	private String brandGroupCode;// 集团品牌编码(暂时不用)

	private String brandName;// 品牌名称

	private Integer status;// 有效标记（0：有效，1：无效，默认0）

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getShopSid() {
		return shopSid;
	}

	public void setShopSid(String shopSid) {
		this.shopSid = shopSid == null ? null : shopSid.trim();
	}

	public String getBrandSid() {
		return brandSid;
	}

	public void setBrandSid(String brandSid) {
		this.brandSid = brandSid == null ? null : brandSid.trim();
	}

	public String getBrandGroupCode() {
		return brandGroupCode;
	}

	public void setBrandGroupCode(String brandGroupCode) {
		this.brandGroupCode = brandGroupCode == null ? null : brandGroupCode.trim();
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName == null ? null : brandName.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PcmShopBrandRelation [sid=" + sid + ", shopSid=" + shopSid + ", brandSid="
				+ brandSid + ", brandGroupCode=" + brandGroupCode + ", brandName=" + brandName
				+ ", status=" + status + "]";
	}

}
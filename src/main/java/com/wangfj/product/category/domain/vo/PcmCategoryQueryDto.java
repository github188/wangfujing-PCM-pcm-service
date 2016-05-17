package com.wangfj.product.category.domain.vo;

/**
 * Created by wangxuan on 2016-02-29 0029.
 */
public class PcmCategoryQueryDto {
	private String sid;

	private String categorySid;// 分类编码

	private String parentSid;// 父类节点sid

	private String categoryType;// 品类类型标识:0 工业分类，1 管理分类，2 统计分类，3 展示分类（默认为0）

	private String isDisplay;// 是否展示（1:是 0 不是）

	private String shopCode;// 门店编码

	private String categoryCode;// 手工录入编码

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getCategorySid() {
		return categorySid;
	}

	public void setCategorySid(String categorySid) {
		this.categorySid = categorySid;
	}

	public String getParentSid() {
		return parentSid;
	}

	public void setParentSid(String parentSid) {
		this.parentSid = parentSid;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public String getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(String isDisplay) {
		this.isDisplay = isDisplay;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	@Override
	public String toString() {
		return "PcmCategoryQueryDto{" + "sid='" + sid + '\'' + ", categorySid='" + categorySid
				+ '\'' + ", parentSid='" + parentSid + '\'' + ", categoryType='" + categoryType
				+ '\'' + ", isDisplay='" + isDisplay + '\'' + ", shopCode='" + shopCode + '\''
				+ ", categoryCode='" + categoryCode + '\'' + '}';
	}
}

package com.wangfj.product.maindata.domain.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wangfj.core.utils.DateUtil;

public class SearchSpuDto {
	private String spuId;
	private String spuName;
	private String model;
	private String brandId;
	private String activeBit;
	private Boolean onSell;
	private String pageDescription;
	private String onSellSince;
	private List<String> aliases = new ArrayList<String>();
	private List<String> categoryIds = new ArrayList<String>();
	private List<TagsDto> tags = new ArrayList<TagsDto>();
	private List<SearchPropDto> propertyValues = new ArrayList<SearchPropDto>();
	private String longDesc;
	private String shortDesc;

	// private MasterPictureDto masterPicture;

	public String getSpuId() {
		return spuId;
	}

	public String getLongDesc() {
		return longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public void setSpuId(String spuId) {
		this.spuId = spuId;
	}

	public String getSpuName() {
		return spuName;
	}

	public void setSpuName(String spuName) {
		this.spuName = spuName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getActiveBit() {
		return activeBit;
	}

	public void setActiveBit(String activeBit) {
		this.activeBit = activeBit;
	}

	public Boolean getOnSell() {
		return onSell;
	}

	public void setOnSell(Boolean onSell) {
		this.onSell = onSell;
	}

	public String getPageDescription() {
		return pageDescription;
	}

	public void setPageDescription(String pageDescription) {
		this.pageDescription = pageDescription;
	}

	public List<String> getAliases() {
		return aliases;
	}

	public void setAliases(List<String> aliases) {
		this.aliases = aliases;
	}

	public String getOnSellSince() {
		return onSellSince;
	}

	public void setOnSellSince(Date onSellSince) {
		this.onSellSince = DateUtil.formatToStr(onSellSince, "yyyy-MM-dd HH:mm:ss");
	}

	public List<String> getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(List<String> categoryIds) {
		this.categoryIds = categoryIds;
	}

	public List<TagsDto> getTags() {
		return tags;
	}

	public void setTags(List<TagsDto> tags) {
		this.tags = tags;
	}

	public List<SearchPropDto> getPropertyValues() {
		return propertyValues;
	}

	public void setPropertyValues(List<SearchPropDto> propertyValues) {
		this.propertyValues = propertyValues;
	}

	@Override
	public String toString() {
		return "SearchSpuDto [spuId=" + spuId + ", spuName=" + spuName + ", model=" + model
				+ ", brandId=" + brandId + ", activeBit=" + activeBit + ", onSell=" + onSell
				+ ", pageDescription=" + pageDescription + ", onSellSince=" + onSellSince
				+ ", aliases=" + aliases + ", categoryIds=" + categoryIds + ", tags=" + tags
				+ ", propertyValues=" + propertyValues + "]";
	}

	// public MasterPictureDto getMasterPicture() {
	// return masterPicture;
	// }
	//
	// public void setMasterPicture(MasterPictureDto masterPicture) {
	// this.masterPicture = masterPicture;
	// }

}

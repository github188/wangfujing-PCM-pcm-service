package com.wangfj.product.maindata.domain.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PcmProYeInfoDto {
	private String skuCode;// sku编码
	private String skuName;// sku标准品名
	private String originalPrice;// 市场价
	private String colorSid;// 色系名称
	private String colorName;// 色系编码
	private String stanName;// 规格名称
	private String brandName;// 品牌名称
	private String brandCode;// 品牌编码
	private String brandDesc;// 品牌描述
	private String brandLogo;// 品牌logo
	private String sizePicture;// 尺码对照表
	private String isSoldOut;// 是否售罄 0有货，1无货
	private List<PcmProYeStanInfoDto> stanPicList = new ArrayList<PcmProYeStanInfoDto>();// 图片规格列表
	private List<Map<String, Object>> skuList = new ArrayList<Map<String, Object>>();// 商品列表
	private List<Map<String, Object>> colorList = new ArrayList<Map<String, Object>>();// 色系列表
	private List<Map<String, Object>> stanList = new ArrayList<Map<String, Object>>();// 规格列表
	List<Map<String, Object>> cateList = new ArrayList<Map<String, Object>>();// 展示分类
	List<Map<String, Object>> propList = new ArrayList<Map<String, Object>>();// 属性Info列表
	private String customerServices;// 售后服务
	private String thumbnailUrl;// 缩略图

	public String getIsSoldOut() {
		return isSoldOut;
	}

	public void setIsSoldOut(String isSoldOut) {
		this.isSoldOut = isSoldOut;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getCustomerServices() {
		return customerServices;
	}

	public void setCustomerServices(String customerServices) {
		this.customerServices = customerServices;
	}

	public List<Map<String, Object>> getPropList() {
		return propList;
	}

	public void setPropList(List<Map<String, Object>> propList) {
		this.propList = propList;
	}

	public List<Map<String, Object>> getCateList() {
		return cateList;
	}

	public void setCateList(List<Map<String, Object>> cateList) {
		this.cateList = cateList;
	}

	public List<Map<String, Object>> getSkuList() {
		return skuList;
	}

	public void setSkuList(List<Map<String, Object>> skuList) {
		this.skuList = skuList;
	}

	public List<Map<String, Object>> getColorList() {
		return colorList;
	}

	public void setColorList(List<Map<String, Object>> colorList) {
		this.colorList = colorList;
	}

	public List<Map<String, Object>> getStanList() {
		return stanList;
	}

	public void setStanList(List<Map<String, Object>> stanList) {
		this.stanList = stanList;
	}

	public List<PcmProYeStanInfoDto> getStanPicList() {
		return stanPicList;
	}

	public void setStanPicList(List<PcmProYeStanInfoDto> stanPicList) {
		this.stanPicList = stanPicList;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public String getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getColorSid() {
		return colorSid;
	}

	public void setColorSid(String colorSid) {
		this.colorSid = colorSid;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getStanName() {
		return stanName;
	}

	public void setStanName(String stanName) {
		this.stanName = stanName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getBrandDesc() {
		return brandDesc;
	}

	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}

	public String getBrandLogo() {
		return brandLogo;
	}

	public void setBrandLogo(String brandLogo) {
		this.brandLogo = brandLogo;
	}

	public String getSizePicture() {
		return sizePicture;
	}

	public void setSizePicture(String sizePicture) {
		this.sizePicture = sizePicture;
	}

	@Override
	public String toString() {
		return "PcmProYeInfoDto [skuCode=" + skuCode + ", skuName=" + skuName + ", originalPrice="
				+ originalPrice + ", colorSid=" + colorSid + ", colorName=" + colorName
				+ ", stanName=" + stanName + ", brandName=" + brandName + ", brandCode="
				+ brandCode + ", brandDesc=" + brandDesc + ", brandLogo=" + brandLogo
				+ ", sizePicture=" + sizePicture + ", stanPicList=" + stanPicList + ", skuList="
				+ skuList + ", colorList=" + colorList + ", stanList=" + stanList + ", cateList="
				+ cateList + ", propList=" + propList + "]";
	}

}

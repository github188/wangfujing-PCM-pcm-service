package com.wangfj.product.maindata.domain.vo;

import java.util.List;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * 商品拍照信息DTO
 * 
 * @Class Name PcmProDetail
 * @Author zhangxy
 * @Create In 2015年7月2日
 */
public class ProductPhotoDto extends BaseDto {
	private String spuSid;// 产品SID
	private String product_sid;// 产品表编码
	private String product_name;// 产品名(spu标准品名)
	private String colorSid;// 色系编码
	private String colorName;// 色系名称
	private String modelCode;// 款号
	private String brandCode;// 集团品牌编码
	private String brandName;// 集团品牌名称
	private String category;// 工业分类
	private String editFlag;// 二次编辑标识
	private String sex;// 适合性别
	private String specialDes;// 特别说明
	private List<CategoryDto> categoryChannels;// 展示分类
	private List<AttributeDto> attrList;// 编辑好的属性对象List
	private String specialCounterCode;// 专柜编码

	public String getProduct_sid() {
		return product_sid;
	}

	public void setProduct_sid(String product_sid) {
		this.product_sid = product_sid;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
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

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSpecialDes() {
		return specialDes;
	}

	public void setSpecialDes(String specialDes) {
		this.specialDes = specialDes;
	}

	public List<CategoryDto> getCategoryChannels() {
		return categoryChannels;
	}

	public void setCategoryChannels(List<CategoryDto> categoryChannels) {
		this.categoryChannels = categoryChannels;
	}

	public List<AttributeDto> getAttrList() {
		return attrList;
	}

	public void setAttrList(List<AttributeDto> attrList) {
		this.attrList = attrList;
	}

	public String getSpuSid() {
		return spuSid;
	}

	public void setSpuSid(String spuSid) {
		this.spuSid = spuSid;
	}

	public String getSpecialCounterCode() {
		return specialCounterCode;
	}

	public void setSpecialCounterCode(String specialCounterCode) {
		this.specialCounterCode = specialCounterCode;
	}

	@Override
	public String toString() {
		return "ProductPhotoDto [spuSid=" + spuSid + ", product_sid=" + product_sid
				+ ", product_name=" + product_name + ", colorSid=" + colorSid + ", colorName="
				+ colorName + ", modelCode=" + modelCode + ", brandCode=" + brandCode
				+ ", brandName=" + brandName + ", category=" + category + ", editFlag=" + editFlag
				+ ", sex=" + sex + ", specialDes=" + specialDes + ", categoryChannels="
				+ categoryChannels + ", attrList=" + attrList + ", specialCounterCode="
				+ specialCounterCode + "]";
	}

}
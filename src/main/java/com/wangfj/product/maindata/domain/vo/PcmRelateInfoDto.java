/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.maindata.domain.voPcmRelateInfoDto.java
 * @Create By wangc
 * @Create In 2016年6月15日 上午11:36:42
 * TODO
 */
package com.wangfj.product.maindata.domain.vo;

import java.util.List;

import com.wangfj.product.brand.domain.entity.PcmBrand;
import com.wangfj.product.category.domain.entity.PcmCategory;
import com.wangfj.product.maindata.domain.entity.PcmColorDict;
import com.wangfj.product.maindata.domain.entity.PcmErpProduct;
import com.wangfj.product.maindata.domain.entity.PcmSeasonDict;
import com.wangfj.product.organization.domain.entity.PcmOrganization;
import com.wangfj.product.organization.domain.entity.PcmShoppe;
import com.wangfj.product.supplier.domain.entity.PcmSupplyInfo;

/**
 * @Class Name PcmRelateInfoDto
 * @Author wangc
 * @Create In 2016年6月15日
 */
public class PcmRelateInfoDto {

	private List<PcmShoppe> shoppeList;//专柜列表信息
	
	private PcmOrganization org;//门店信息
	
	private PcmBrand brand;//品牌信息
	
	private List<PcmSupplyInfo> supplyInfoList;//供应商信息
	
	private List<PcmCategory> tjcateList;//统计分类信息
	
	private List<PcmCategory> cateList;//工业分类信息
	
	private List<PcmCategory> glcateList;//管理分类信息
	
	private List<PcmErpProduct> erpList;//扣率码信息
	
	private List<PcmSeasonDict> seasonList;//季节信息
	
	private List<PcmColorDict> colorList;//色系信息

	private List<PcmBrand> brandList;//集团品牌信息
	
	
	public List<PcmBrand> getBrandList() {
		return brandList;
	}

	public void setBrandList(List<PcmBrand> brandList) {
		this.brandList = brandList;
	}

	public List<PcmShoppe> getShoppeList() {
		return shoppeList;
	}

	public void setShoppeList(List<PcmShoppe> shoppeList) {
		this.shoppeList = shoppeList;
	}

	public PcmOrganization getOrg() {
		return org;
	}

	public void setOrg(PcmOrganization org) {
		this.org = org;
	}

	public PcmBrand getBrand() {
		return brand;
	}

	public void setBrand(PcmBrand brand) {
		this.brand = brand;
	}

	public List<PcmSupplyInfo> getSupplyInfoList() {
		return supplyInfoList;
	}

	public void setSupplyInfoList(List<PcmSupplyInfo> supplyInfoList) {
		this.supplyInfoList = supplyInfoList;
	}

	public List<PcmCategory> getTjcateList() {
		return tjcateList;
	}

	public void setTjcateList(List<PcmCategory> tjcateList) {
		this.tjcateList = tjcateList;
	}

	public List<PcmCategory> getCateList() {
		return cateList;
	}

	public void setCateList(List<PcmCategory> cateList) {
		this.cateList = cateList;
	}

	public List<PcmCategory> getGlcateList() {
		return glcateList;
	}

	public void setGlcateList(List<PcmCategory> glcateList) {
		this.glcateList = glcateList;
	}

	public List<PcmErpProduct> getErpList() {
		return erpList;
	}

	public void setErpList(List<PcmErpProduct> erpList) {
		this.erpList = erpList;
	}

	public List<PcmSeasonDict> getSeasonList() {
		return seasonList;
	}

	public void setSeasonList(List<PcmSeasonDict> seasonList) {
		this.seasonList = seasonList;
	}

	public List<PcmColorDict> getColorList() {
		return colorList;
	}

	public void setColorList(List<PcmColorDict> colorList) {
		this.colorList = colorList;
	}
	
	
}

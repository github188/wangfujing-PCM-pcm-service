package com.wangfj.product.maindata.domain.vo;

import java.util.Date;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * 创建SPU
 * 
 * @Class Name CreateSpuDto
 * @Author liuhp
 * @Create In 2015-8-17
 */
public class CreateSpuDto extends BaseDto {

	/**
	 * 产品名(spu标准品名)
	 */
	private String productName;
	/**
	 * 产品sku
	 */
	private String productSku;
	/**
	 * 集团品牌编码
	 */
	private String brandSid;
	/**
	 * 集团品牌编码
	 */
	private String brandRootSid;
	/**
	 * 集团品牌名称
	 */
	private String brandName;
	/**
	 * 是否启用
	 */
	private Integer proActiveBit;
	/**
	 * 上架 : 1上架 0未上架
	 */
	private Integer proSelling;
	/**
	 * 活动标志
	 */
	private Integer activityFlg;
	/**
	 * 商品图片
	 */
	private String proPicture;
	/**
	 * 品类sid
	 */
	private Long categorySid;
	/**
	 * 适合性别
	 */
	private Integer sexSid;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 赞的数量
	 */
	private Long awesome;
	/**
	 * 商品页描述sid
	 */
	private Long proDescSid;
	/**
	 * 商品别名
	 */
	private String productNameAlias;
	/**
	 * 商品上架时间
	 */
	private Date proSellingTime;
	/**
	 * 上市时间
	 */
	private String yearToMarket;
	/**
	 * 短描述
	 */
	private String shortDes;
	/**
	 * 二次编辑表示
	 */
	private Integer editFlag;
	/**
	 * 特别说明
	 */
	private String specialDes;

	/**
	 * 季节sid
	 */
	private String seasonCode;
	/**
	 * 主属性
	 */
	private String primaryAttr;

	/**
	 * 工业分类sid
	 */
	private Long categoryGYSid;

	/**
	 * 标志位 0百货， 1超市
	 */
	private Integer flag;

	/**
	 * 操作人
	 */
	private String optUser;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductSku() {
		return productSku;
	}

	public void setProductSku(String productSku) {
		this.productSku = productSku;
	}

	public String getBrandSid() {
		return brandSid;
	}

	public void setBrandSid(String brandSid) {
		this.brandSid = brandSid;
	}

	public String getBrandRootSid() {
		return brandRootSid;
	}

	public void setBrandRootSid(String brandRootSid) {
		this.brandRootSid = brandRootSid;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Integer getProActiveBit() {
		return proActiveBit;
	}

	public void setProActiveBit(Integer proActiveBit) {
		this.proActiveBit = proActiveBit;
	}

	public Integer getProSelling() {
		return proSelling;
	}

	public void setProSelling(Integer proSelling) {
		this.proSelling = proSelling;
	}

	public Integer getActivityFlg() {
		return activityFlg;
	}

	public void setActivityFlg(Integer activityFlg) {
		this.activityFlg = activityFlg;
	}

	public String getProPicture() {
		return proPicture;
	}

	public void setProPicture(String proPicture) {
		this.proPicture = proPicture;
	}

	public Long getCategorySid() {
		return categorySid;
	}

	public void setCategorySid(Long categorySid) {
		this.categorySid = categorySid;
	}

	public Integer getSexSid() {
		return sexSid;
	}

	public void setSexSid(Integer sexSid) {
		this.sexSid = sexSid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getAwesome() {
		return awesome;
	}

	public void setAwesome(Long awesome) {
		this.awesome = awesome;
	}

	public Long getProDescSid() {
		return proDescSid;
	}

	public void setProDescSid(Long proDescSid) {
		this.proDescSid = proDescSid;
	}

	public String getProductNameAlias() {
		return productNameAlias;
	}

	public void setProductNameAlias(String productNameAlias) {
		this.productNameAlias = productNameAlias;
	}

	public Date getProSellingTime() {
		return proSellingTime;
	}

	public void setProSellingTime(Date proSellingTime) {
		this.proSellingTime = proSellingTime;
	}

	public String getYearToMarket() {
		return yearToMarket;
	}

	public void setYearToMarket(String yearToMarket) {
		this.yearToMarket = yearToMarket;
	}

	public String getShortDes() {
		return shortDes;
	}

	public void setShortDes(String shortDes) {
		this.shortDes = shortDes;
	}

	public Integer getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(Integer editFlag) {
		this.editFlag = editFlag;
	}

	public String getSpecialDes() {
		return specialDes;
	}

	public void setSpecialDes(String specialDes) {
		this.specialDes = specialDes;
	}

	public String getSeasonCode() {
		return seasonCode;
	}

	public void setSeasonCode(String seasonCode) {
		this.seasonCode = seasonCode;
	}

	public String getPrimaryAttr() {
		return primaryAttr;
	}

	public void setPrimaryAttr(String primaryAttr) {
		this.primaryAttr = primaryAttr;
	}

	public Long getCategoryGYSid() {
		return categoryGYSid;
	}

	public void setCategoryGYSid(Long categoryGYSid) {
		this.categoryGYSid = categoryGYSid;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getOptUser() {
		return optUser;
	}

	public void setOptUser(String optUser) {
		this.optUser = optUser;
	}

	@Override
	public String toString() {
		return "CreateSpuDto [productName=" + productName + ", productSku=" + productSku
				+ ", brandSid=" + brandSid + ", brandRootSid=" + brandRootSid + ", brandName="
				+ brandName + ", proActiveBit=" + proActiveBit + ", proSelling=" + proSelling
				+ ", activityFlg=" + activityFlg + ", proPicture=" + proPicture + ", categorySid="
				+ categorySid + ", sexSid=" + sexSid + ", createTime=" + createTime + ", awesome="
				+ awesome + ", proDescSid=" + proDescSid + ", productNameAlias=" + productNameAlias
				+ ", proSellingTime=" + proSellingTime + ", yearToMarket=" + yearToMarket
				+ ", shortDes=" + shortDes + ", editFlag=" + editFlag + ", specialDes="
				+ specialDes + ", seasonCode=" + seasonCode + ", primaryAttr=" + primaryAttr
				+ ", categoryGYSid=" + categoryGYSid + ", flag=" + flag + ", optUser=" + optUser
				+ "]";
	}

}

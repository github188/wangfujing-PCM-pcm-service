package com.wangfj.product.category.domain.vo;

import java.io.Serializable;

public class BrandVO
  implements Serializable
{
  private String sid;
  private String brandName;
  private String pictureUrl;
  private String brandNameSecond;
  private String brandActiveBit;
  private String brandno;
  private String brandcorp;
  private String brandpic1;
  private String brandpic2;
  private String brandPict;
  private String parentSid;
  private String endBit;
  private String spell;
  private String brandDesc;
  private String brandCreateCountry;
  private String brandCreateTime;
  private String brandSpecialty;
  private String brandSuitability;
  private String productCount;
  private int isFactoryStore;
  private String factoryBicPic;
  private Integer parentFactoryStore;
  private String factorySmallPic;
  private String activityBigPic;
  private String activitySmallPic;
  private int factoryOrder;
  private String factoryName;

  public Integer getParentFactoryStore()
  {
    return this.parentFactoryStore;
  }

  public void setParentFactoryStore(Integer parentFactoryStore) {
    this.parentFactoryStore = parentFactoryStore;
  }

  public int getFactoryStore() {
    return this.isFactoryStore;
  }

  public void setFactoryStore(int factoryStore) {
    this.isFactoryStore = factoryStore;
  }

  public String getFactoryBicPic() {
    return this.factoryBicPic;
  }

  public void setFactoryBicPic(String factoryBicPic) {
    this.factoryBicPic = factoryBicPic;
  }

  public String getFactorySmallPic() {
    return this.factorySmallPic;
  }

  public void setFactorySmallPic(String factorySmallPic) {
    this.factorySmallPic = factorySmallPic;
  }

  public String getActivityBigPic() {
    return this.activityBigPic;
  }

  public void setActivityBigPic(String activityBigPic) {
    this.activityBigPic = activityBigPic;
  }

  public String getActivitySmallPic() {
    return this.activitySmallPic;
  }

  public void setActivitySmallPic(String activitySmallPic) {
    this.activitySmallPic = activitySmallPic;
  }

  public int getFactoryOrder() {
    return this.factoryOrder;
  }

  public void setFactoryOrder(int factoryOrder) {
    this.factoryOrder = factoryOrder;
  }

  public String getSid()
  {
    return this.sid;
  }

  public void setSid(String sid) {
    this.sid = sid;
  }

  public String getBrandName() {
    return this.brandName;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }

  public String getPictureUrl() {
    return this.pictureUrl;
  }

  public void setPictureUrl(String pictureUrl) {
    this.pictureUrl = pictureUrl;
  }

  public String getBrandNameSecond() {
    return this.brandNameSecond;
  }

  public void setBrandNameSecond(String brandNameSecond) {
    this.brandNameSecond = brandNameSecond;
  }

  public String getBrandActiveBit() {
    return this.brandActiveBit;
  }

  public void setBrandActiveBit(String brandActiveBit) {
    this.brandActiveBit = brandActiveBit;
  }

  public String getBrandno() {
    return this.brandno;
  }

  public void setBrandno(String brandno) {
    this.brandno = brandno;
  }

  public String getBrandcorp() {
    return this.brandcorp;
  }

  public void setBrandcorp(String brandcorp) {
    this.brandcorp = brandcorp;
  }

  public String getBrandpic1() {
    return this.brandpic1;
  }

  public void setBrandpic1(String brandpic1) {
    this.brandpic1 = brandpic1;
  }

  public String getBrandpic2() {
    return this.brandpic2 == null ? "/defBrand.jpg" : this.brandpic2;
  }

  public void setBrandpic2(String brandpic2) {
    this.brandpic2 = brandpic2;
  }

  public String getBrandPict() {
    return this.brandPict;
  }

  public void setBrandPict(String brandPict) {
    this.brandPict = brandPict;
  }

  public String getParentSid() {
    return this.parentSid;
  }

  public void setParentSid(String parentSid) {
    this.parentSid = parentSid;
  }

  public String getEndBit() {
    return this.endBit;
  }

  public void setEndBit(String endBit) {
    this.endBit = endBit;
  }

  public String getSpell() {
    return this.spell;
  }

  public void setSpell(String spell) {
    this.spell = spell;
  }

  public String getBrandDesc() {
    return this.brandDesc;
  }

  public void setBrandDesc(String brandDesc) {
    this.brandDesc = brandDesc;
  }

  public String getProductCount() {
    return this.productCount;
  }

  public void setProductCount(String productCount) {
    this.productCount = productCount;
  }

  public String getBrandCreateCountry() {
    return this.brandCreateCountry;
  }

  public void setBrandCreateCountry(String brandCreateCountry) {
    this.brandCreateCountry = brandCreateCountry;
  }

  public String getBrandCreateTime() {
    return this.brandCreateTime;
  }

  public void setBrandCreateTime(String brandCreateTime) {
    this.brandCreateTime = brandCreateTime;
  }

  public String getBrandSpecialty() {
    return this.brandSpecialty;
  }

  public void setBrandSpecialty(String brandSpecialty) {
    this.brandSpecialty = brandSpecialty;
  }

  public String getBrandSuitability() {
    return this.brandSuitability;
  }

  public void setBrandSuitability(String brandSuitability) {
    this.brandSuitability = brandSuitability;
  }

  public String toString()
  {
    return "BrandVO{sid='" + this.sid + '\'' + ", brandName='" + this.brandName + '\'' + ", pictureUrl='" + this.pictureUrl + '\'' + ", brandNameSecond='" + this.brandNameSecond + '\'' + ", brandActiveBit='" + this.brandActiveBit + '\'' + ", brandno='" + this.brandno + '\'' + ", brandcorp='" + this.brandcorp + '\'' + ", brandpic1='" + this.brandpic1 + '\'' + ", brandpic2='" + this.brandpic2 + '\'' + ", parentSid='" + this.parentSid + '\'' + ", endBit='" + this.endBit + '\'' + ", spell='" + this.spell + '\'' + ", brandDesc='" + this.brandDesc + '\'' + ", productCount='" + this.productCount + '\'' + '}';
  }

  public String getFactoryName()
  {
    return this.factoryName;
  }

  public void setFactoryName(String factoryName) {
    this.factoryName = factoryName;
  }
}
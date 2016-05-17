package com.wangfj.product.category.domain.vo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;

public class ProductQueryVO
  implements Serializable
{
  private String proBrand;
  private String proCate;
  private String soffprice;
  private String eoffprice;
  private String sprice;
  private String eprice;
  private String recent;
  private String orderByPrice;
  private String orderByTime = "1";
  private String style;
  private String[] querys;

  public ProductQueryVO()
  {
  }

  public ProductQueryVO(String queryUrl)
  {
    this.querys = StringUtils.splitPreserveAllTokens(queryUrl, "-");
    mapArrayToFeilds(this.querys);
  }

  public String getProBrand() {
    return this.proBrand;
  }

  public void setProBrand(String proBrand) {
    this.proBrand = proBrand;
    setQuerysArrayElement(proBrand, 0);
  }

  public String getProCate() {
    return this.proCate;
  }

  public void setProCate(String proCate) {
    this.proCate = proCate;
    setQuerysArrayElement(proCate, 1);
  }

  public String getSoffprice() {
    return this.soffprice == null ? "" : this.soffprice;
  }

  public void setSoffprice(String soffprice) {
    this.soffprice = soffprice;
    setQuerysArrayElement(soffprice, 2);
  }

  public String getEoffprice() {
    return this.eoffprice == null ? "" : this.eoffprice;
  }

  public void setEoffprice(String eoffprice) {
    this.eoffprice = eoffprice;
    setQuerysArrayElement(eoffprice, 3);
  }

  public String getSprice() {
    return this.sprice == null ? "" : this.sprice;
  }

  public void setSprice(String sprice) {
    this.sprice = sprice;
    setQuerysArrayElement(sprice, 4);
  }

  public String getEprice() {
    return this.eprice == null ? "" : this.eprice;
  }

  public void setEprice(String eprice) {
    this.eprice = eprice;
    setQuerysArrayElement(eprice, 5);
  }

  public String[] getQuerys() {
    return this.querys;
  }

  public void setQuerys(String[] querys) {
    this.querys = querys;
    mapArrayToFeilds(querys);
  }

  public String getRecent() {
    return this.recent;
  }

  public void setRecent(String recent) {
    this.recent = recent;
    setQuerysArrayElement(recent, 6);
  }

  public String getOrderByPrice() {
    return this.orderByPrice;
  }

  public void setOrderByPrice(String orderByPrice) {
    this.orderByPrice = orderByPrice;
    setQuerysArrayElement(orderByPrice, 7);
  }

  public String getOrderByTime() {
    return this.orderByTime;
  }

  public void setOrderByTime(String orderByTime) {
    this.orderByTime = orderByTime;
    setQuerysArrayElement(orderByTime, 8);
  }

  public String getStyle() {
    return this.style;
  }

  public void setStyle(String style) {
    this.style = style;
    setQuerysArrayElement(style, 9);
  }

  public String getQueryString()
  {
    if ((this.querys == null) || (this.querys.length == 0)) {
      return null;
    }
    return arrayToString(this.querys, "-");
  }

  public Integer[] getProCates()
  {
    if ((this.proCate == null) || ("".equals(this.proCate))) {
      return null;
    }
    String[] strArray = this.proCate.split(",");
    Integer[] cates = new Integer[strArray.length];
    for (int i = 0; i < strArray.length; i++) {
      cates[i] = Integer.valueOf(strArray[i]);
    }
    return cates;
  }

  public void setProCates(Integer[] array)
  {
    if ((array == null) || (array.length == 0)) {
      throw new IllegalArgumentException(new StringBuilder().append("array=").append(array).toString());
    }
    String str = arrayToString(array, ",");

    setProCate(str);
  }

  public void setProCatesByCollection(Collection<Integer> cates)
  {
    if ((cates == null) || (cates.isEmpty())) {
      throw new IllegalArgumentException(new StringBuilder().append("cates=").append(cates).toString());
    }
    Integer[] array = (Integer[])cates.toArray(new Integer[cates.size()]);
    setProCates(array);
  }

  private void setQuerysArrayElement(String str, int index)
  {
    if ((index < 0) || (index > 8)) {
      throw new IllegalArgumentException(new StringBuilder().append("str =").append(str).append(", index =").append(index).toString());
    }

    if (this.querys == null) {
      this.querys = new String[index + 1];
      Arrays.fill(this.querys, "");
    } else if (this.querys.length < index + 1) {
      String[] querysNew = new String[index + 1];
      System.arraycopy(this.querys, 0, querysNew, 0, this.querys.length);
      this.querys = querysNew;
    }

    this.querys[index] = str;
  }

  private void mapArrayToFeilds(String[] array)
  {
    this.proBrand = null;
    this.proCate = null;
    this.soffprice = null;
    this.eoffprice = null;
    this.sprice = null;
    this.eprice = null;
    this.recent = null;
    this.orderByPrice = null;
    this.orderByTime = "1";
    if ((array == null) || (array.length == 0)) {
      return;
    }

    for (int i = 0; i < array.length; i++)
      switch (i) {
      case 0:
        this.proBrand = array[i];
        break;
      case 1:
        this.proCate = array[i];
        break;
      case 2:
        this.soffprice = array[i];
        break;
      case 3:
        this.eoffprice = array[i];
        break;
      case 4:
        this.sprice = array[i];
        break;
      case 5:
        this.eprice = array[i];
        break;
      case 6:
        this.recent = array[i];
        break;
      case 7:
        this.orderByPrice = array[i];
        break;
      case 8:
        this.orderByTime = array[i];
        break;
      case 9:
        this.style = array[i];
      }
  }

  private String arrayToString(Object[] array, String c)
  {
    String str = "";
    for (int i = 0; i < array.length; i++) {
      str = new StringBuilder().append(str).append(array[i]).toString();
      if (i != array.length - 1) {
        str = new StringBuilder().append(str).append(c).toString();
      }
    }
    return str;
  }

  public String toString()
  {
    return new StringBuilder().append("ProductQueryVO{proBrand='").append(this.proBrand).append('\'').append(", proCate='").append(this.proCate).append('\'').append(", soffprice='").append(this.soffprice).append('\'').append(", eoffprice='").append(this.eoffprice).append('\'').append(", sprice='").append(this.sprice).append('\'').append(", eprice='").append(this.eprice).append('\'').append(", recent='").append(this.recent).append('\'').append(", orderByPrice='").append(this.orderByPrice).append('\'').append(", orderByTime='").append(this.orderByTime).append('\'').append(", style='").append(this.style).append('\'').append(", querys=").append(this.querys == null ? null : Arrays.asList(this.querys)).append('}').toString();
  }
}
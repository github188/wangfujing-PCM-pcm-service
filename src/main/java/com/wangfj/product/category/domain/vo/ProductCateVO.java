package com.wangfj.product.category.domain.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class ProductCateVO
  implements Serializable
{
  private String sid;
  private String serialNumber;
  private String nodeName;
  private String fatherNodeSid;
  private String rootNodeSid;
  private String nodeSeq;
  private String nodeLevel;
  private String isDisplay;
  private String memo;
  private int productCount;
  private ProductCateVO parentCate;
  private Map<String, ProductCateVO> childCatesMap = new TreeMap();
  private Collection<ProductCateVO> childCates;

  public String getSid()
  {
    return this.sid;
  }

  public void setSid(String sid) {
    this.sid = sid;
  }

  public String getSerialNumber() {
    return this.serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public String getNodeName() {
    return this.nodeName;
  }

  public void setNodeName(String nodeName) {
    this.nodeName = nodeName;
  }

  public String getFatherNodeSid() {
    return this.fatherNodeSid;
  }

  public void setFatherNodeSid(String fatherNodeSid) {
    this.fatherNodeSid = fatherNodeSid;
  }

  public String getRootNodeSid() {
    return this.rootNodeSid;
  }

  public void setRootNodeSid(String rootNodeSid) {
    this.rootNodeSid = rootNodeSid;
  }

  public String getNodeLevel() {
    return this.nodeLevel;
  }

  public void setNodeLevel(String nodeLevel) {
    this.nodeLevel = nodeLevel;
  }

  public String getNodeSeq() {
    return this.nodeSeq;
  }

  public void setNodeSeq(String nodeSeq) {
    this.nodeSeq = nodeSeq;
  }

  public String getDisplay() {
    return this.isDisplay;
  }

  public void setDisplay(String display) {
    this.isDisplay = display;
  }

  public String getMemo() {
    return this.memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public String toString()
  {
    return "ProductCateVO{sid='" + this.sid + '\'' + ", nodeName='" + this.nodeName + '\'' + ", productCount='" + this.productCount + '\'' + ", cateList=" + this.childCates + '}';
  }

  public int getProductCount()
  {
    return this.productCount;
  }

  public void setProductCount(int productCount) {
    this.productCount = productCount;
  }

  public ProductCateVO getParentCate() {
    return this.parentCate;
  }

  public void setParentCate(ProductCateVO parentCate) {
    this.parentCate = parentCate;
  }

  public Map<String, ProductCateVO> getChildCatesMap() {
    return this.childCatesMap;
  }

  public void setChildCatesMap(Map<String, ProductCateVO> childCatesMap) {
    this.childCatesMap = childCatesMap;
  }

  public Collection<ProductCateVO> getChildCates() {
    return this.childCatesMap.values();
  }

  public void setChildCates(Collection<ProductCateVO> childCates) {
    this.childCates = this.childCatesMap.values();
  }
}
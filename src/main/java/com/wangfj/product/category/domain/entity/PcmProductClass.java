package com.wangfj.product.category.domain.entity;

import java.math.BigDecimal;

/**
 * 网站分类导航信息表
 * @Class Name PcmProductClass
 * @Author duanzhaole
 * @Create In 2015年7月2日
 */
public class PcmProductClass {
    private Integer sid;

    /**
     * 序号
     */
    private String serialNumber;

    /**
     * 节点名称
     */
    private String nodeName;

    /**
     * 父节点
     */
    private BigDecimal fatherNodeSid;

    /**
     * 根节点
     */
    private Integer rootNodeSid;

    /**
     * 是否展示
     */
    private Integer isDisplay;

    /**
     * 备注
     */
    private String memo;

    /**
     *节点等级 
     */
    private Integer nodeLevel;

    /**
     * 是否叶子节点
     */
    private Integer isLeaf;

    /**
     * 顺序
     */
    private Integer nodeSeq;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber == null ? null : serialNumber.trim();
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }

    public BigDecimal getFatherNodeSid() {
        return fatherNodeSid;
    }

    public void setFatherNodeSid(BigDecimal fatherNodeSid) {
        this.fatherNodeSid = fatherNodeSid;
    }

    public Integer getRootNodeSid() {
        return rootNodeSid;
    }

    public void setRootNodeSid(Integer rootNodeSid) {
        this.rootNodeSid = rootNodeSid;
    }

    public Integer getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Integer isDisplay) {
        this.isDisplay = isDisplay;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Integer getNodeLevel() {
        return nodeLevel;
    }

    public void setNodeLevel(Integer nodeLevel) {
        this.nodeLevel = nodeLevel;
    }

    public Integer getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
    }

    public Integer getNodeSeq() {
        return nodeSeq;
    }

    public void setNodeSeq(Integer nodeSeq) {
        this.nodeSeq = nodeSeq;
    }
}
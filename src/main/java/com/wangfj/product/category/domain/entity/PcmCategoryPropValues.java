package com.wangfj.product.category.domain.entity;

import java.util.Date;

import com.wangfj.util.PageModel;

/**
 * 分类属性关联表
 * @Class Name PcmCategoryPropValues
 * @Author duanzhaole
 * @Create In 2015年7月2日
 */
public class PcmCategoryPropValues extends PageModel<PcmCategoryPropValues> {
    private Long sid;

    /**
     * 分类SID(外键)
     */
	private String categorySid;
    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 属性SID(外键)
     */
    private Long propsSid;

    /**
     * 属性名
     */
    private String propsName;

    /**
     * 属性值SID(外键)
     */
    private Long valuesSid;
	/**
	 * 属性值
	 */
    private String valuesName;

    /**
     * 渠道SID(外键)
     */
    private Long channelSid;

    /**
     * 操作人
     */
    private String optUser;

    /**
     * 操作日期
     */
    private Date optDate;

    /**
     * 是否必填属性
     */
    private Integer notNull;

    /**
     * 是否erp同步
     */
    private Integer isErpSyn;

    /**
     * 创建时间
     */
    private Date createTime;

	/**
	 * 文本还是枚举
	 */
	private String isEnumProp;
    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

	public String getCategorySid() {
        return categorySid;
    }

	public void setCategorySid(String categorySid) {
        this.categorySid = categorySid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public Long getPropsSid() {
        return propsSid;
    }

    public void setPropsSid(Long propsSid) {
        this.propsSid = propsSid;
    }

    public String getPropsName() {
        return propsName;
    }

    public void setPropsName(String propsName) {
        this.propsName = propsName == null ? null : propsName.trim();
    }

    public Long getValuesSid() {
        return valuesSid;
    }

    public void setValuesSid(Long valuesSid) {
        this.valuesSid = valuesSid;
    }

    public String getValuesName() {
        return valuesName;
    }

    public void setValuesName(String valuesName) {
        this.valuesName = valuesName == null ? null : valuesName.trim();
    }

    public Long getChannelSid() {
        return channelSid;
    }

    public void setChannelSid(Long channelSid) {
        this.channelSid = channelSid;
    }

    public String getOptUser() {
        return optUser;
    }

    public void setOptUser(String optUser) {
        this.optUser = optUser == null ? null : optUser.trim();
    }

    public Date getOptDate() {
        return optDate;
    }

    public void setOptDate(Date optDate) {
        this.optDate = optDate;
    }

    public Integer getNotNull() {
        return notNull;
    }

    public void setNotNull(Integer notNull) {
        this.notNull = notNull;
    }

    public Integer getIsErpSyn() {
        return isErpSyn;
    }

    public void setIsErpSyn(Integer isErpSyn) {
        this.isErpSyn = isErpSyn;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getIsEnumProp() {
		return isEnumProp;
	}

	public void setIsEnumProp(String isEnumProp) {
		this.isEnumProp = isEnumProp;
	}

}
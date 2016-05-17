package com.wangfj.product.category.domain.vo;

public class PcmCategoryPropsDictPara {

	private Long sid;

    private Long propsSid;

    private String propsName;

    private String propsDesc;

    private String propsCode;

    private Long status;

	private Long isKeyProp;

	private Long isErpProp;

	private int isEnumProp;

    private Long sortOrder;

    private Long channelSid;

	private String channelName;

	private Long categorySid;
	
	private int categoryType;
	
	
	public int getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(int categoryType) {
		this.categoryType = categoryType;
	}

	public Long getCategorySid() {
		return categorySid;
	}

	public void setCategorySid(Long categorySid) {
		this.categorySid = categorySid;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
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

    public String getPropsDesc() {
        return propsDesc;
    }

    public void setPropsDesc(String propsDesc) {
        this.propsDesc = propsDesc == null ? null : propsDesc.trim();
    }

    public String getPropsCode() {
        return propsCode;
    }

    public void setPropsCode(String propsCode) {
        this.propsCode = propsCode == null ? null : propsCode.trim();
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

	public Long getIsKeyProp() {
		return isKeyProp;
	}

	public void setIsKeyProp(Long isKeyProp) {
		this.isKeyProp = isKeyProp;
	}

	public Long getIsErpProp() {
		return isErpProp;
	}

	public void setIsErpProp(Long isErpProp) {
		this.isErpProp = isErpProp;
	}

	public Long getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Long sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Long getChannelSid() {
        return channelSid;
    }

    public void setChannelSid(Long channelSid) {
        this.channelSid = channelSid;
    }

	public int getIsEnumProp() {
		return isEnumProp;
	}

	public void setIsEnumProp(int isEnumProp) {
		this.isEnumProp = isEnumProp;
	}

}

package com.wangfj.product.category.domain.vo;

public class PcmCategoryValuesDictDto {

	private Long sid;

    private Long propsSid;

    private String propsName;

    private String propsDesc;

    private String propsCode;

    private Long status;

	private Long isKeyProp;

	private Long isErpProp;

    private Long sortOrder;

    private Long channelSid;

	private String channelName;

	/**
	 * 属性值编码
	 */
	private Long valuesSid;

	/**
	 * 属性值
	 */
	private String valuesName;

	/**
	 * 是否可修改值
	 */
	private Long isKeyValue;

	/**
	 * 属性值描述
	 */
	private String valuesDesc;

	/**
	 * 属性值编码
	 */
	private String valuesCode;

	/**
	 * 是否erp属性值
	 */
	private Long isErpValue;
	
	private Long categorySid;

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

	public String getValuesName() {
		return valuesName;
	}

	public void setValuesName(String valuesName) {
		this.valuesName = valuesName;
	}

	public Long getValuesSid() {
		return valuesSid;
	}

	public void setValuesSid(Long valuesSid) {
		this.valuesSid = valuesSid;
	}

	public Long getIsKeyValue() {
		return isKeyValue;
	}

	public void setIsKeyValue(Long isKeyValue) {
		this.isKeyValue = isKeyValue;
	}

	public String getValuesDesc() {
		return valuesDesc;
	}

	public void setValuesDesc(String valuesDesc) {
		this.valuesDesc = valuesDesc;
	}

	public String getValuesCode() {
		return valuesCode;
	}

	public void setValuesCode(String valuesCode) {
		this.valuesCode = valuesCode;
	}

	public Long getIsErpValue() {
		return isErpValue;
	}

	public void setIsErpValue(Long isErpValue) {
		this.isErpValue = isErpValue;
	}

	public Long getCategorySid() {
		return categorySid;
	}

	public void setCategorySid(Long categorySid) {
		this.categorySid = categorySid;
	}

}

package com.wangfj.product.category.domain.vo;

import java.util.Date;

import com.wangfj.util.PageModel;

/**
 * 分类属性字典表
 * @Class Name PcmCategoryPropsDict
 * @Author duanzhaole
 * @Create In 2015年7月2日
 */
public class PcmPropsDictsDto extends PageModel<PcmPropsDictsDto> {
    private Long sid;

    /**
     * 属性编码
     */
    private Long propsSid;

    /**
     * 属性名
     */
    private String propsName;

    /**
     * 是否修改
     */
    private Integer isKeyProp;

    /**
     * 属性类型：1 文本型；2 布尔型；0枚举型 （默认为0）
     */
    private Integer isEnumProp;

    /**
     * 属性描述
     */
    private String propsDesc;

    /**
     * 属性编码
     */
    private String propsCode;

    /**
     * 状态
     */
    private Long status;

    /**
     * 顺序
     */
    private Long sortOrder;

    /**
     * 渠道SID
     */
    private Long channelSid;

    /**
     * 是否ERP属性
     */
    private Integer isErpProp;

    /**
     * ERP类型
     */
    private Integer erpType;
	/**
	 * erp属性编码
	 */
    private String erpPropCode;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 品类父sid
     */
    private String parentSid;

    /**
     * 操作人
     */
    private Long optUserSid;
    
    private Long categorySid;
    
    /**
     * spusid
     */
    private String productSid;
    
    private String isnotnull;
    
    
	/** 当前页的起始索引,从1开始 */
	protected int start = 1;

	/** mysql 分页 */
	protected int limit = 10;
	
	/** 当前页的起始索引,从1开始 */
	protected int currenPage = 1;

	/** mysql 分页 */
	protected int pageSize = 10;

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

    public Integer getIsKeyProp() {
        return isKeyProp;
    }

    public void setIsKeyProp(Integer isKeyProp) {
        this.isKeyProp = isKeyProp;
    }

    public Integer getIsEnumProp() {
        return isEnumProp;
    }

    public void setIsEnumProp(Integer isEnumProp) {
        this.isEnumProp = isEnumProp;
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

    public Integer getIsErpProp() {
        return isErpProp;
    }

    public void setIsErpProp(Integer isErpProp) {
        this.isErpProp = isErpProp;
    }

    public Integer getErpType() {
        return erpType;
    }

    public void setErpType(Integer erpType) {
        this.erpType = erpType;
    }

    public String getErpPropCode() {
        return erpPropCode;
    }

    public void setErpPropCode(String erpPropCode) {
        this.erpPropCode = erpPropCode == null ? null : erpPropCode.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getOptUserSid() {
        return optUserSid;
    }

    public void setOptUserSid(Long optUserSid) {
        this.optUserSid = optUserSid;
    }

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getCurrenPage() {
		return currenPage;
	}

	public void setCurrenPage(int currenPage) {
		this.currenPage = currenPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getParentSid() {
		return parentSid;
	}

	public void setParentSid(String parentSid) {
		this.parentSid = parentSid;
	}

	public Long getCategorySid() {
		return categorySid;
	}

	public void setCategorySid(Long categorySid) {
		this.categorySid = categorySid;
	}

	public String getProductSid() {
		return productSid;
	}

	public void setProductSid(String productSid) {
		this.productSid = productSid;
	}

	public String getIsnotnull() {
		return isnotnull;
	}

	public void setIsnotnull(String isnotnull) {
		this.isnotnull = isnotnull;
	}

    
    
}
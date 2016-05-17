package com.wangfj.product.category.domain.vo;

import com.wangfj.core.framework.base.dto.BaseDto;


/**
 * 产品分类表dto
 * @Class Name PcmProductCategoryDto
 * @Author liuhp
 * @Create In 2015-8-24
 */
public class PcmProductCategoryDto extends BaseDto{
	private Long sid;

    /**
     * 产品SID(外键)
     */
	private Long productSid;

    /**
     * 渠道SID(外键)
     */
    private Long channelSid;

    /**
     * 品类sid(外键)
     */
	private Long categorySid;

	/**
	 * 分类；类型
	 */
	private Integer categoryType;

	public Long getSid() {
		return sid;
	}
	
	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Long getProductSid() {
		return productSid;
	}

	public void setProductSid(Long productSid) {
		this.productSid = productSid;
	}

	public Long getChannelSid() {
		return channelSid;
	}

	public void setChannelSid(Long channelSid) {
		this.channelSid = channelSid;
	}

	public Long getCategorySid() {
		return categorySid;
	}

	public void setCategorySid(Long categorySid) {
		this.categorySid = categorySid;
	}

	
	
	public Integer getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(Integer categoryType) {
		this.categoryType = categoryType;
	}

	@Override
	public String toString() {
		return "PcmProductCategoryDto [sid=" + sid + ", productSid=" + productSid + ", channelSid="
				+ channelSid + ", categorySid=" + categorySid + ", categoryType=" + categoryType
				+ "]";
	}


}
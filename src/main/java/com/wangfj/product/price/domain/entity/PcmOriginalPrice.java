package com.wangfj.product.price.domain.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 *商品原价表
 * @Class Name PcmOriginalPrice
 * @Author duanzhaole
 * @Create In 2015年7月2日
 */
public class PcmOriginalPrice {
	/**
	 * 商品表SID
	 */
	private String productDetailSid;

    /**
     *原价
     */
	private BigDecimal originalPrice;

     /**
      *创建时间
      */
    private Date createTime;

    /**
     * 操作人
     */
    private String optUser;

	public String getProductDetailSid() {
        return productDetailSid;
    }

	public void setProductDetailSid(String productDetailSid) {
        this.productDetailSid = productDetailSid;
    }

	public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

	public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOptUser() {
        return optUser;
    }

    public void setOptUser(String optUser) {
        this.optUser = optUser == null ? null : optUser.trim();
    }
}
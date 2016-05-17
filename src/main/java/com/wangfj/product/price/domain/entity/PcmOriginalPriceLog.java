package com.wangfj.product.price.domain.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 *商品原价日志表
 * @Class Name PcmOriginalPriceLog
 * @Author duanzhaole
 * @Create In 2015年7月2日
 */
public class PcmOriginalPriceLog {
    private Long sid;

   /**
    * 商品表SID
    */
	private String productDetailSid;

    /**
     * old原价
     */
	private BigDecimal oldOriginalPrice;

    /**
     *new原价
     */
	private BigDecimal newOriginalPrice;

	  /**
	   * 创建时间
	   */
    private Date createTime;

    /**
     * 操作人
     */
    private String optUser;

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

	public String getProductDetailSid() {
        return productDetailSid;
    }

	public void setProductDetailSid(String productDetailSid) {
        this.productDetailSid = productDetailSid;
    }

	public BigDecimal getOldOriginalPrice() {
        return oldOriginalPrice;
    }

	public void setOldOriginalPrice(BigDecimal oldOriginalPrice) {
        this.oldOriginalPrice = oldOriginalPrice;
    }

	public BigDecimal getNewOriginalPrice() {
        return newOriginalPrice;
    }

	public void setNewOriginalPrice(BigDecimal newOriginalPrice) {
        this.newOriginalPrice = newOriginalPrice;
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
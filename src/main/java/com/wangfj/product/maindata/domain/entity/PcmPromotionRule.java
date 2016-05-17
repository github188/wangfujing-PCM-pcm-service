package com.wangfj.product.maindata.domain.entity;

import java.util.Date;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * 促销活动规则
 * 
 * @Class Name PcmPromotionRule
 * @Author zhangxy
 * @Create In 2015年7月2日
 */
public class PcmPromotionRule extends BaseEntity {
	private Long sid;
	/**
	 * 活动标题
	 */
	private String promotionTitle;
	/**
	 * 品牌SID
	 */
	private Long brandSid;
	/**
	 * 分类SID
	 */
	private Long categorySid;
	/**
	 * 专柜商品sid
	 */
	private Long shoppeProSid;
	/**
	 * 模式
	 */
	private String mode;
	/**
	 * 涉及价格
	 */
	private String referPrice;
	/**
	 * 操作(+ -)
	 */
	private String opt;
	/**
	 * 折扣
	 */
	private Long offValue;
	/**
	 * 金额
	 */
	private Long money;
	/**
	 * 开始时间
	 */
	private Date beginTime;
	/**
	 * 结束时间
	 */
	private Date endTime;
	/**
	 * 创建者
	 */
	private String createUser;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 操作者
	 */
	private String optUser;
	/**
	 * 操作时间
	 */
	private Date optTime;
	/**
	 * 恢复sql
	 */
	private String restoreSql;
	/**
	 * 变化sql
	 */
	private String changeSql;
	/**
	 * 活动号
	 */
	private Long promotionNumber;
	/**
	 * 优先级
	 */
	private Long priority;
	/**
	 * 标记
	 */
	private Long flag;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getPromotionTitle() {
		return promotionTitle;
	}

	public void setPromotionTitle(String promotionTitle) {
		this.promotionTitle = promotionTitle == null ? null : promotionTitle.trim();
	}

	public Long getBrandSid() {
		return brandSid;
	}

	public void setBrandSid(Long brandSid) {
		this.brandSid = brandSid;
	}

	public Long getCategorySid() {
		return categorySid;
	}

	public void setCategorySid(Long categorySid) {
		this.categorySid = categorySid;
	}

	public Long getShoppeProSid() {
		return shoppeProSid;
	}

	public void setShoppeProSid(Long shoppeProSid) {
		this.shoppeProSid = shoppeProSid;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode == null ? null : mode.trim();
	}

	public String getReferPrice() {
		return referPrice;
	}

	public void setReferPrice(String referPrice) {
		this.referPrice = referPrice == null ? null : referPrice.trim();
	}

	public String getOpt() {
		return opt;
	}

	public void setOpt(String opt) {
		this.opt = opt == null ? null : opt.trim();
	}

	public Long getOffValue() {
		return offValue;
	}

	public void setOffValue(Long offValue) {
		this.offValue = offValue;
	}

	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser == null ? null : createUser.trim();
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

	public Date getOptTime() {
		return optTime;
	}

	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}

	public String getRestoreSql() {
		return restoreSql;
	}

	public void setRestoreSql(String restoreSql) {
		this.restoreSql = restoreSql == null ? null : restoreSql.trim();
	}

	public String getChangeSql() {
		return changeSql;
	}

	public void setChangeSql(String changeSql) {
		this.changeSql = changeSql == null ? null : changeSql.trim();
	}

	public Long getPromotionNumber() {
		return promotionNumber;
	}

	public void setPromotionNumber(Long promotionNumber) {
		this.promotionNumber = promotionNumber;
	}

	public Long getPriority() {
		return priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}

	public Long getFlag() {
		return flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}
}
package com.wangfj.product.maindata.domain.entity;

import java.util.Date;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * 标签表
 * 
 * @Class Name PcmTag
 * @Author zhangxy
 * @Create In 2015年7月2日
 */
public class PcmTag extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long sid;
	/**
	 * 标签
	 */
	private String tagName;
	/**
	 * 标签编码
	 */
	private String tagCode;
	/**
	 * 标签类型
	 */
	private Integer tagType;
	/**
	 * 状态：0 有效 ，1 禁用 （默认为0）
	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 操作人
	 */
	private Long optUserSid;
	/**
	 * 开始时间
	 */
	private Date beginDate;
	/**
	 * 结束时间
	 */
	private Date endDate;
	/**
	 * 最后更改人
	 */
	private String operaterName;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName == null ? null : tagName.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getOptUserSid() {
		return optUserSid;
	}

	public void setOptUserSid(Long optUserSid) {
		this.optUserSid = optUserSid;
	}

	public String getTagCode() {
		return tagCode;
	}

	public void setTagCode(String tagCode) {
		this.tagCode = tagCode;
	}

	public Integer getTagType() {
		return tagType;
	}

	public void setTagType(Integer tagType) {
		this.tagType = tagType;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getOperaterName() {
		return operaterName;
	}

	public void setOperaterName(String operaterName) {
		this.operaterName = operaterName;
	}

	@Override
	public String toString() {
		return "PcmTag [sid=" + sid + ", tagName=" + tagName + ", tagCode=" + tagCode
				+ ", tagType=" + tagType + ", status=" + status + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", optUserSid=" + optUserSid + ", beginDate="
				+ beginDate + ", endDate=" + endDate + ", operaterName=" + operaterName + "]";
	}

}
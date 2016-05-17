package com.wangfj.product.organization.domain.vo;

import java.util.Date;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * 
 * @Class Name PcmIndustryConditionDictDto
 * @Author yedong
 * @Create In 2015年7月3日
 */
public class PcmIndustryConditionDictDto extends BaseDto {
	private Long sid;

	private String industryName; /* 业态名称 */

	private Date createTime; /* 创建时间 */

	private Date updateTime; /* 修改时间 */

	private Long optUserSid; /* 操作人 */

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName == null ? null : industryName.trim();
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

	@Override
	public String toString() {
		return "PcmIndustryConditionDictDto [sid=" + sid + ", industryName=" + industryName
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", optUserSid="
				+ optUserSid + "]";
	}

}

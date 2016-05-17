package com.wangfj.product.stocks.domain.entity;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * 
 * @Class Name PcmLockTypeDict
 * @Author yedong
 * @Create In 2015年7月3日
 */
public class PcmLockTypeDict extends BaseEntity {
	private Integer sid; /* 锁定类型SID */

	private String lockTypeName; /* 锁定类型名称 */

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getLockTypeName() {
		return lockTypeName;
	}

	public void setLockTypeName(String lockTypeName) {
		this.lockTypeName = lockTypeName == null ? null : lockTypeName.trim();
	}
}
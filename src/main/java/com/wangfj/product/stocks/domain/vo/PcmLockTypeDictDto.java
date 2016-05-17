package com.wangfj.product.stocks.domain.vo;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * 
 * @Class Name PcmLockTypeDictDto
 * @Author yedong
 * @Create In 2015年7月3日
 */
public class PcmLockTypeDictDto extends BaseDto {
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

	@Override
	public String toString() {
		return "PcmLockTypeDictDto [sid=" + sid + ", lockTypeName=" + lockTypeName + "]";
	}

}

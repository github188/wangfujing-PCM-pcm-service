package com.wangfj.product.stocks.domain.vo;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * 
 * @Class Name PcmChangeTypeDictDto
 * @Author yedong
 * @Create In 2015年7月3日
 */
public class PcmChangeTypeDictDto extends BaseDto {
	private Integer sid; /* 变动SID */

	private String changeName; /* 变动类型名称 */

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName == null ? null : changeName.trim();
	}

	@Override
	public String toString() {
		return "PcmChangeTypeDictDto [sid=" + sid + ", changeName=" + changeName + "]";
	}

}

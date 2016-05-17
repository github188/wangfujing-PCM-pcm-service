package com.wangfj.product.demo.domain.dto;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * 
 * @Comment
 * @Class Name UserPara
 * @Author kongxs
 * @Create In 2015年6月25日
 */
public class GetUsersDto extends BaseDto{

	private Integer sid;
	
	/**
	 * @Return the Integer sid
	 */
	public Integer getSid() {
		return sid;
	}
	/**
	 * @Param Integer sid to set
	 */
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	
}

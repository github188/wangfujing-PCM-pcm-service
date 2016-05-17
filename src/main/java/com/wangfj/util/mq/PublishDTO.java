package com.wangfj.util.mq;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * 下发实体类
 * @Class Name PublishDTO
 * @Author liuhp
 * @Create In 2015-8-20
 */
public class PublishDTO extends BaseDto  {
	private Long sid; //对应实体的sid
	private Integer type;//操作，0 添加  1修改 2删除
	public Long getSid() {
		return sid;
	}
	public void setSid(Long sid) {
		this.sid = sid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "PublishDTO [sid=" + sid + ", type=" + type + "]";
	}	
	
}

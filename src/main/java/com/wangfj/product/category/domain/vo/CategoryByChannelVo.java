/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.category.domain.voCategoryByChannelVo.java
 * @Create By duanzhaole
 * @Create In 2015年10月12日 下午4:27:17
 * TODO
 */
package com.wangfj.product.category.domain.vo;

/**
 * @Class Name CategoryByChannelVo
 * @Author duanzhaole
 * @Create In 2015年10月12日
 */
public class CategoryByChannelVo {

	private Long sid;
	private String code;
	private String parentCode;
	private String 	name;
	private String catalogCode;
	private String channelSid;
	private String actionCode;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCatalogCode() {
		return catalogCode;
	}
	public void setCatalogCode(String catalogCode) {
		this.catalogCode = catalogCode;
	}
	
	public String getChannelSid() {
		return channelSid;
	}
	public void setChannelSid(String channelSid) {
		this.channelSid = channelSid;
	}
	public String getActionCode() {
		return actionCode;
	}
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
	public Long getSid() {
		return sid;
	}
	public void setSid(Long sid) {
		this.sid = sid;
	}
	
	
	
}

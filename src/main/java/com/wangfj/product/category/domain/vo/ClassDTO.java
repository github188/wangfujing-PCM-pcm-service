/**
 * @Probject Name: search_proj
 * @Path: net.web.search.domain.dtoCategoryDTO.java
 * @Create By Shan
 * @Create In 2012-9-18 下午5:34:31
 *
 */
package com.wangfj.product.category.domain.vo;

import java.io.Serializable;

/**
 * @Class Name CategoryDTO
 * @Author Shan
 * @Create In 2012-9-18
 */
public class ClassDTO implements Serializable{
	private Integer sid;
	private String nodeName;
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	
}

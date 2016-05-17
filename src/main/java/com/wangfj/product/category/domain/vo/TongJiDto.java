/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.category.domain.voTongJiDto.java
 * @Create By duanzhaole
 * @Create In 2015年9月8日 下午1:58:17
 * TODO
 */
package com.wangfj.product.category.domain.vo;

/**
 * 统计分类下发dto
 * @Class Name TongJiDto
 * @Author duanzhaole
 * @Create In 2015年9月8日
 */
public class TongJiDto {
	
	private String CODE;
	private String NAME;
	private String SJCODE;
	private String FLAG;
	private String TYPE;
	private String STATUS;
	private String actionCode;
	private String actionDate;
	private String actionPerson;
	
	
	
	public String getCODE() {
		return CODE;
	}
	public void setCODE(String cODE) {
		CODE = cODE;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getSJCODE() {
		return SJCODE;
	}
	public void setSJCODE(String sJCODE) {
		SJCODE = sJCODE;
	}
	public String getFLAG() {
		return FLAG;
	}
	public void setFLAG(String fLAG) {
		FLAG = fLAG;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public String getActionCode() {
		return actionCode;
	}
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
	public String getActionDate() {
		return actionDate;
	}
	public void setActionDate(String actionDate) {
		this.actionDate = actionDate;
	}
	public String getActionPerson() {
		return actionPerson;
	}
	public void setActionPerson(String actionPerson) {
		this.actionPerson = actionPerson;
	}
	
	

}

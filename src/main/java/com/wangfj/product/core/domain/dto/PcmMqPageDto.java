package com.wangfj.product.core.domain.dto;

import java.util.Date;

import com.wangfj.core.framework.base.dto.BaseDto;

public class PcmMqPageDto extends BaseDto {

	/**
	 * @Field long serialVersionUID
	 */
	private static final long serialVersionUID = 167161937460396158L;

	private Long sid;

	private String messageid;

	private String url;

	private String desturl;

	private String callbackurl;

	private String serviceid;

	private Integer count;

	private String sourcesysid;

	private Date createdates;

	private String createdateStr;

	private Integer status;

	private String bizdesc;

	private String bizcode;

	private String c1;

	private Integer c2;

	private Integer c3;

	private String c4;

	private String c5;

	private String data;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getMessageid() {
		return messageid;
	}

	public void setMessageid(String messageid) {
		this.messageid = messageid == null ? null : messageid.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public String getDesturl() {
		return desturl;
	}

	public void setDesturl(String desturl) {
		this.desturl = desturl == null ? null : desturl.trim();
	}

	public String getCallbackurl() {
		return callbackurl;
	}

	public void setCallbackurl(String callbackurl) {
		this.callbackurl = callbackurl == null ? null : callbackurl.trim();
	}

	public String getServiceid() {
		return serviceid;
	}

	public void setServiceid(String serviceid) {
		this.serviceid = serviceid == null ? null : serviceid.trim();
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getSourcesysid() {
		return sourcesysid;
	}

	public void setSourcesysid(String sourcesysid) {
		this.sourcesysid = sourcesysid == null ? null : sourcesysid.trim();
	}

	public Date getCreatedates() {
		return createdates;
	}

	public void setCreatedates(Date createdates) {
		this.createdates = createdates;
	}

	public String getCreatedateStr() {
		return createdateStr == null ? null : createdateStr.trim();
	}

	public void setCreatedateStr(String createdateStr) {
		this.createdateStr = createdateStr;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getBizdesc() {
		return bizdesc;
	}

	public void setBizdesc(String bizdesc) {
		this.bizdesc = bizdesc == null ? null : bizdesc.trim();
	}

	public String getBizcode() {
		return bizcode;
	}

	public void setBizcode(String bizcode) {
		this.bizcode = bizcode == null ? null : bizcode.trim();
	}

	public String getC1() {
		return c1;
	}

	public void setC1(String c1) {
		this.c1 = c1 == null ? null : c1.trim();
	}

	public Integer getC2() {
		return c2;
	}

	public void setC2(Integer c2) {
		this.c2 = c2;
	}

	public Integer getC3() {
		return c3;
	}

	public void setC3(Integer c3) {
		this.c3 = c3;
	}

	public String getC4() {
		return c4;
	}

	public void setC4(String c4) {
		this.c4 = c4 == null ? null : c4.trim();
	}

	public String getC5() {
		return c5;
	}

	public void setC5(String c5) {
		this.c5 = c5 == null ? null : c5.trim();
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data == null ? null : data.trim();
	}

}
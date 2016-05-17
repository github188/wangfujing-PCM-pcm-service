package com.wangfj.product.brand.domain.entity;

import java.util.Date;

public class PcmBrandCategory {
	private Long sid;
	/*
	 * 品牌SID
	 */
	private Long brandSid;
	/*
	 * 分类SID
	 */
	private Long categorySid;
	/*
	 * 尺码图片路径
	 */
	private String sizePictureUrl;
	/*
	 * 状态
	 */
	private Integer status;
	/*
	 * 操作人
	 */
	private String optUser;
	/*
	 * 操作时间
	 */
	private Date optDate;

	private String field1;

	private String field2;

	private String field3;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Long getBrandSid() {
		return brandSid;
	}

	public void setBrandSid(Long brandSid) {
		this.brandSid = brandSid;
	}

	public Long getCategorySid() {
		return categorySid;
	}

	public void setCategorySid(Long categorySid) {
		this.categorySid = categorySid;
	}

	public String getSizePictureUrl() {
		return sizePictureUrl;
	}

	public void setSizePictureUrl(String sizePictureUrl) {
		this.sizePictureUrl = sizePictureUrl;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOptUser() {
		return optUser;
	}

	public void setOptUser(String optUser) {
		this.optUser = optUser;
	}

	public Date getOptDate() {
		return optDate;
	}

	public void setOptDate(Date optDate) {
		this.optDate = optDate;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}
}
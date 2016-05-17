package com.wangfj.product.maindata.domain.entity;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * 规格字典表
 * 
 * @Class Name PcmStanDict
 * @Author zhangxy
 * @Create In 2015年7月2日
 */
public class PcmStanDict extends BaseEntity {
	private Long sid;
	/**
	 * 规格名称
	 */
	private String proStanName;
	/**
	 * 规格编码
	 */
	private String proStanSid;
	/**
	 * 品牌名称
	 */
	private String brandName;
	/**
	 * 集团品牌sid
	 */
	private String brandRootSid;
	/**
	 * 品牌sid
	 */
	private String brandSid;

	private String atinn;
	/**
	 * 分类sid
	 */
	private Long categorySid;

	private String clint;

	private String fileName;

	private String fileSid;
	/**
	 * 分类名称
	 */
	private String categoryName;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getProStanName() {
		return proStanName;
	}

	public void setProStanName(String proStanName) {
		this.proStanName = proStanName == null ? null : proStanName.trim();
	}

	public String getProStanSid() {
		return proStanSid;
	}

	public void setProStanSid(String proStanSid) {
		this.proStanSid = proStanSid == null ? null : proStanSid.trim();
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName == null ? null : brandName.trim();
	}

	public String getBrandRootSid() {
		return brandRootSid;
	}

	public void setBrandRootSid(String brandRootSid) {
		this.brandRootSid = brandRootSid == null ? null : brandRootSid.trim();
	}

	public String getBrandSid() {
		return brandSid;
	}

	public void setBrandSid(String brandSid) {
		this.brandSid = brandSid;
	}

	public String getAtinn() {
		return atinn;
	}

	public void setAtinn(String atinn) {
		this.atinn = atinn == null ? null : atinn.trim();
	}

	public Long getCategorySid() {
		return categorySid;
	}

	public void setCategorySid(Long categorySid) {
		this.categorySid = categorySid;
	}

	public String getClint() {
		return clint;
	}

	public void setClint(String clint) {
		this.clint = clint == null ? null : clint.trim();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName == null ? null : fileName.trim();
	}

	public String getFileSid() {
		return fileSid;
	}

	public void setFileSid(String fileSid) {
		this.fileSid = fileSid == null ? null : fileSid.trim();
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName == null ? null : categoryName.trim();
	}
}
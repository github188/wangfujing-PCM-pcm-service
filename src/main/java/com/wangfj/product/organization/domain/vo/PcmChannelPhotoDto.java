package com.wangfj.product.organization.domain.vo;

/**
 * 拍照系统返回参数
 * 
 * @Class Name PcmChannelPhotoDto
 * @Author wangxuan
 * @Create In 2015-10-14
 */
public class PcmChannelPhotoDto {

	private String sid;// 主键

	private String Code;// 渠道编码

	private String Name;// 渠道名称

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid == null ? null : sid.trim();
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String Code) {
		this.Code = Code == null ? null : Code.trim();
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name == null ? null : Name.trim();
	}

	@Override
	public String toString() {
		return "PcmChannelPhotoDto [Code=" + Code + ", Name=" + Name + "]";
	}

}

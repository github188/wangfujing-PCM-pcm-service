package com.wangfj.product.maindata.domain.vo;

import com.wangfj.core.framework.base.dto.BaseDto;

public class PcmDictVersionDto extends BaseDto {
	private Long sid;
    /*
     * 版本号
     */
    private Long version;
    /*
     * 0工业分类  1统计分类
     */
    private Integer type;
    
    private Long versionMax;
    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

	public Long getVersionMax() {
		return versionMax;
	}

	public void setVersionMax(Long versionMax) {
		this.versionMax = versionMax;
	}

	@Override
	public String toString() {
		return "PcmDictVersionDto [sid=" + sid + ", version=" + version + ", type=" + type
				+ ", versionMax=" + versionMax + "]";
	}
    
}

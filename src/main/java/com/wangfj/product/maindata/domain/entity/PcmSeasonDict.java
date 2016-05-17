package com.wangfj.product.maindata.domain.entity;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * 季节表
 * 
 * @Class Name PcmSeasonDict
 * @Author zhangxy
 * @Create In 2015年7月14日
 */
public class PcmSeasonDict extends BaseEntity {

    private Integer sid;

	private String seasonCode;

    private String season;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

	public String getSeasonCode() {
		return seasonCode;
	}

	public void setSeasonCode(String seasonCode) {
		this.seasonCode = seasonCode;
	}

	public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season == null ? null : season.trim();
    }

	@Override
	public String toString() {
		return "PcmSeasonDict [sid=" + sid + ", seasonCode=" + seasonCode + ", season=" + season
				+ "]";
	}

}
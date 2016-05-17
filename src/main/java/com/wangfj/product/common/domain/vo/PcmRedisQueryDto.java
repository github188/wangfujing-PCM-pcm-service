package com.wangfj.product.common.domain.vo;

/**
 * Created by wangxuan on 2016-03-03 0003.
 */
public class PcmRedisQueryDto {

	private Long sid;

	private String redisffield;

	private String keyname;

	private Integer status;

	private String filed1;

	private String filed2;

	private String filed3;

	private String value;

	private Integer currentPage;

	private Integer pageSize;

	private Integer start;

	private Integer limit;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getRedisffield() {
		return redisffield;
	}

	public void setRedisffield(String redisffield) {
		this.redisffield = redisffield;
	}

    public String getKeyname() {
        return keyname;
    }

    public void setKeyname(String keyname) {
        this.keyname = keyname;
    }

    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getFiled1() {
		return filed1;
	}

	public void setFiled1(String filed1) {
		this.filed1 = filed1;
	}

	public String getFiled2() {
		return filed2;
	}

	public void setFiled2(String filed2) {
		this.filed2 = filed2;
	}

	public String getFiled3() {
		return filed3;
	}

	public void setFiled3(String filed3) {
		this.filed3 = filed3;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

    @Override
    public String toString() {
        return "PcmRedisQueryDto{" +
                "sid=" + sid +
                ", redisffield='" + redisffield + '\'' +
                ", keyname='" + keyname + '\'' +
                ", status=" + status +
                ", filed1='" + filed1 + '\'' +
                ", filed2='" + filed2 + '\'' +
                ", filed3='" + filed3 + '\'' +
                ", value='" + value + '\'' +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", start=" + start +
                ", limit=" + limit +
                '}';
    }
}

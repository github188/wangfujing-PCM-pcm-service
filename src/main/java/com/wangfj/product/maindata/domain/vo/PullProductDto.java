package com.wangfj.product.maindata.domain.vo;

import java.util.List;

/**
 * 上传使用Dto
 * 
 * @Class Name PullProductDto
 * @Author wangsy
 * @Create In 2015年7月15日
 */
public class PullProductDto {

	private String fromSystem;

	/**
	 * 版本
	 */
	private String version;

	/**
	 * PullHeaderPara
	 */
	private PullHeaderDto header;

	/**
	 * PullDataPara
	 */
	private List<PullDataDto> data;

	public String getFromSystem() {
		return fromSystem;
	}

	public void setFromSystem(String fromSystem) {
		this.fromSystem = fromSystem;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public PullHeaderDto getHeader() {
		return header;
	}

	public void setHeader(PullHeaderDto header) {
		this.header = header;
	}

	public List<PullDataDto> getData() {
		return data;
	}

	public void setData(List<PullDataDto> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "PullProductDto [fromSystem=" + fromSystem + ", version=" + version + ", header="
				+ header + ", data=" + data + "]";
	}
}

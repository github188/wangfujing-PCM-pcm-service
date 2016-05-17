package com.wangfj.product.maindata.domain.vo;

/**
 * 移动工作台调用主数据获取单位信息(输出参数)
 * 
 * @Class Name PcmSaleUinitDictPadDto
 * @Author wangxuan
 * @Create In 2015-8-31
 */
public class PcmSaleUinitDictPadDto {

	private String code;// 单位编码

	private String name;// 单位名字

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

}

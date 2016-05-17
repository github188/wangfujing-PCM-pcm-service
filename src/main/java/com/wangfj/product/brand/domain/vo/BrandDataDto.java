package com.wangfj.product.brand.domain.vo;

/**
 * 商品导入终端--由主数据获取品牌信息(输出参数DTO)
 * 
 * @Class Name BrandDataDto
 * @Author wangx
 * @Create In 2015-8-10
 */
public class BrandDataDto {

	private String superCode;// 集团商标(中台的主品牌统一编码) 集团品牌编码

	private String code;// 门店品牌编码（对于历史遗留数据，门店品牌编码和中台品牌编码是不一致的。但是对于中台新准入品牌，两者是相同的）

	private String name;// 中台品牌名称

	private String storeType;// 门店类型（0-全局，1-电商、2-北京，3-其他门店）0 为集团品牌

	private String Name2;// 中台品牌第二名称

	public String getSuperCode() {
		return superCode;
	}

	public void setSuperCode(String superCode) {
		this.superCode = superCode == null ? null : superCode.trim();
	}

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

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType == null ? null : storeType.trim();
	}

	public String getName2() {
		return Name2;
	}

	public void setName2(String name2) {
		Name2 = name2 == null ? null : name2.trim();
	}

}

/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.category.domain.voCategoryParamDto.java
 * @Create By duanzhaole
 * @Create In 2015年7月24日 下午8:33:18
 *
 */
package com.wangfj.product.category.domain.vo;

import java.util.Date;

/**
 * @Class Name CategoryParamDto
 * @Author duanzhaole
 * @Create In 2015年7月24日
 */
public class CategoryParamDto {


	/**
	 * 编码
	 */
	private String code;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 上级编码（对于根节点为null）
	 */
	private String sjcode;
	/**
	 * 是否未级
	 */
	private String flag;
	/**
	 * 级别（1/2/3/4/5)。分别对应统计分类的层级。
	 */
	private Integer type;
	/**
	 * 状态（Y/N）
	 */
	private String status;



	/**
	 * 门店编码
	 */
	private String storeCode;
	



	/**
	 * 超市百货标志（N百货Y超市）
	 */
	private String iszg;
	/**
	 * 品类类型标识:0 工业分类，1 管理分类，2 统计分类，3 展示分类（默认为0）
	 */
	private Integer categoryType;

	/**
	 * 根节点（S0表示通用展示分类；非零表示旗舰店的展示分类，值应当等于基础组织架构中的门店编码，例如21011表示王府井百货大楼，21012
	 * 表示双安商城，等等。这在展示分类准备数据的时候应当考虑，因为云店需要这个值来决定云店及其旗舰店对应的展示分类）
	 */
	private String catalogCode;

	/**
	 * 渠道编码（渠道主数据中的渠道编码）
	 */
	private String channelCode;

	/**
	 * 是否父节点
	 */
	private Integer isParent;

	/**
	 * 生效时间
	 */
	private Date successTime;
	/** 当前页的起始索引,从1开始 */
	protected int start = 0;

	/** mysql 分页 */
	protected int limit = 10;

	/**
	 * A(创建)，U(更新,如果不存在则创建)，D(删除)
	 */
	private String actionCode;

	/**
	 * 操作发起时间戳(yyyyMMdd.HHmmssZ)，例如”20141008.101830+0800”，可以为空。
	 */
	private String actionDate;

	/**
	 * 操作员标识(英文标识)。可以为空。
	 */
	private String actionPerson;





	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSjcode() {
		return sjcode;
	}

	public void setSjcode(String sjcode) {
		this.sjcode = sjcode;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIszg() {
		return iszg;
	}

	public void setIszg(String iszg) {
		this.iszg = iszg;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}


	public Date getSuccessTime() {
		return successTime;
	}

	public void setSuccessTime(Date successTime) {
		this.successTime = successTime;
	}

	public Integer getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(Integer categoryType) {
		this.categoryType = categoryType;
	}

	public String getCatalogCode() {
		return catalogCode;
	}

	public void setCatalogCode(String catalogCode) {
		this.catalogCode = catalogCode;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public Integer getIsParent() {
		return isParent;
	}

	public void setIsParent(Integer isParent) {
		this.isParent = isParent;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getActionDate() {
		return actionDate;
	}

	public void setActionDate(String actionDate) {
		this.actionDate = actionDate;
	}

	public String getActionPerson() {
		return actionPerson;
	}

	public void setActionPerson(String actionPerson) {
		this.actionPerson = actionPerson;
	}

}

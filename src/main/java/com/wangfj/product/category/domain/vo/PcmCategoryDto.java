package com.wangfj.product.category.domain.vo;


/**
 * 品类信息表
 * 
 * @Class Name PcmCategory
 * @Author duanzhaole
 * @Create In 2015年7月2日
 */
public class PcmCategoryDto {
	private Long sid;

	/**
	 * 品类编码
	 */
	private String categorySid;

	/**
	 * 分类父节点
	 */
	private String parentSid;
	/**
	 * 品类名称
	 */
	private String name;

	/**
	 * 是否末级
	 */
	private Integer isLeaf;

	/**
	 * 状态
	 */
	private Integer status;

	/**
	 * 等级
	 */
	private Integer level;

	/**
	 * 中台门店编码
	 */
	private String shopSid;

	/**
	 * 品类类别
	 */
	private Integer categoryType;
	/**
	 * 根节点
	 */
	private String rootSid;

	private Integer isMarket;

	/**
	 * 渠道id
	 */
	private Long channelSid;

	/** 当前页的起始索引,从1开始 */
	protected int start = 0;

	/** mysql 分页 */
	protected int limit = 10;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getCategorySid() {
		return categorySid;
	}

	public void setCategorySid(String categorySid) {
		this.categorySid = categorySid;
	}

	public String getParentSid() {
		return parentSid;
	}

	public void setParentSid(String parentSid) {
		this.parentSid = parentSid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
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

	public Integer getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(Integer categoryType) {
		this.categoryType = categoryType;
	}

	public Integer getIsMarket() {
		return isMarket;
	}

	public void setIsMarket(Integer isMarket) {
		this.isMarket = isMarket;
	}

	public String getShopSid() {
		return shopSid;
	}

	public void setShopSid(String shopSid) {
		this.shopSid = shopSid;
	}


	public String getRootSid() {
		return rootSid;
	}

	public void setRootSid(String rootSid) {
		this.rootSid = rootSid;
	}

	public Long getChannelSid() {
		return channelSid;
	}

	public void setChannelSid(Long channelSid) {
		this.channelSid = channelSid;
	}

}
package com.wangfj.product.organization.domain.vo;

/**
 * 查询参数
 * 
 * @Class Name SelectPcmShoppeDto
 * @Author wangxuan
 * @Create In 2015-8-24
 */
public class SelectPcmShoppeDto {

	private Long sid;

	private Long groupSid;// 所属集团sid

	/* 所属门店SID */
	private Long shopSid;

	/* 所属门店编码 */
	private String shopCode;

	private String supplySid;// 供应商的sid

    private String supplyCode;// 供应商编码

	/* 楼层SID */
	private Long floorSid;

	private String floorCode;// 楼层编码

	private String floorName;// 楼层名称

	/* 专柜所属业态SID */
	private Integer industryConditionSid;

	/* 专柜编码 */
	private String shoppeCode;

	/* 专柜名称 */
	private String shoppeName;

	/* 状态 */
	private Integer shoppeStatus;

	/* 专柜库存管理类型 */
	@Deprecated
	private Integer goodsManageType;// 废弃

	/* 经营方式 */
	@Deprecated
	private Integer businessTypeSid;// 废弃

	/**
	 * 商品经营方式: 0-(Z001 经销); 1-(Z002 代销); 2-(Z003 联营); 3-(Z004 平台服务); 4-(Z005
	 * 租赁);
	 */
	private String businessPattern;

	/* 柜组 */
	private String shoppeGroup;

	/* 专柜类型 */
	private String shoppeType;

	/* 集货地点 */
	private String shoppeShippingPoint;

	/* 参考奥莱专柜 */
	private String refCounter;

	/* 是否负库存销售 */
	private Integer negativeStock;

	/* 是否为集货地点 */
	private Integer isShippingPoint;

	/* 当前页数 */
	private Integer currentPage;

	/* 每页显示数量 */
	private Integer pageSize;

	/* MySQL索引 */
	private Integer start;

	/* MySQL查询条数 */
	private Integer limit;

	public Integer getIsShippingPoint() {
		return isShippingPoint;
	}

	public void setIsShippingPoint(Integer isShippingPoint) {
		this.isShippingPoint = isShippingPoint;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Long getShopSid() {
		return shopSid;
	}

	public void setShopSid(Long shopSid) {
		this.shopSid = shopSid;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode == null ? null : shopCode.trim();
	}

	public Long getFloorSid() {
		return floorSid;
	}

	public void setFloorSid(Long floorSid) {
		this.floorSid = floorSid;
	}

	public Integer getIndustryConditionSid() {
		return industryConditionSid;
	}

	public void setIndustryConditionSid(Integer industryConditionSid) {
		this.industryConditionSid = industryConditionSid;
	}

	public String getShoppeCode() {
		return shoppeCode;
	}

	public void setShoppeCode(String shoppeCode) {
		this.shoppeCode = shoppeCode == null ? null : shoppeCode.trim();
	}

	public String getShoppeName() {
		return shoppeName;
	}

	public void setShoppeName(String shoppeName) {
		this.shoppeName = shoppeName == null ? null : shoppeName.trim();
	}

	public Integer getShoppeStatus() {
		return shoppeStatus;
	}

	public void setShoppeStatus(Integer shoppeStatus) {
		this.shoppeStatus = shoppeStatus;
	}

	public Integer getGoodsManageType() {
		return goodsManageType;
	}

	public void setGoodsManageType(Integer goodsManageType) {
		this.goodsManageType = goodsManageType;
	}

	public Integer getBusinessTypeSid() {
		return businessTypeSid;
	}

	public void setBusinessTypeSid(Integer businessTypeSid) {
		this.businessTypeSid = businessTypeSid;
	}

	public String getShoppeGroup() {
		return shoppeGroup;
	}

	public void setShoppeGroup(String shoppeGroup) {
		this.shoppeGroup = shoppeGroup == null ? null : shoppeGroup.trim();
	}

	public String getShoppeType() {
		return shoppeType;
	}

	public void setShoppeType(String shoppeType) {
		this.shoppeType = shoppeType == null ? null : shoppeType.trim();
	}

	public String getShoppeShippingPoint() {
		return shoppeShippingPoint;
	}

	public void setShoppeShippingPoint(String shoppeShippingPoint) {
		this.shoppeShippingPoint = shoppeShippingPoint == null ? null : shoppeShippingPoint.trim();
	}

	public String getRefCounter() {
		return refCounter;
	}

	public void setRefCounter(String refCounter) {
		this.refCounter = refCounter == null ? null : refCounter.trim();
	}

	public Integer getNegativeStock() {
		return negativeStock;
	}

	public void setNegativeStock(Integer negativeStock) {
		this.negativeStock = negativeStock;
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

	public Long getGroupSid() {
		return groupSid;
	}

	public void setGroupSid(Long groupSid) {
		this.groupSid = groupSid;
	}

	public String getFloorCode() {
		return floorCode;
	}

	public void setFloorCode(String floorCode) {
		this.floorCode = floorCode;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public String getSupplySid() {
		return supplySid;
	}

	public void setSupplySid(String supplySid) {
		this.supplySid = supplySid;
	}

	public String getBusinessPattern() {
		return businessPattern;
	}

	public void setBusinessPattern(String businessPattern) {
		this.businessPattern = businessPattern;
	}

    public String getSupplyCode() {
        return supplyCode;
    }

    public void setSupplyCode(String supplyCode) {
        this.supplyCode = supplyCode;
    }

    @Override
    public String toString() {
        return "SelectPcmShoppeDto{" +
                "sid=" + sid +
                ", groupSid=" + groupSid +
                ", shopSid=" + shopSid +
                ", shopCode='" + shopCode + '\'' +
                ", supplySid='" + supplySid + '\'' +
                ", supplyCode='" + supplyCode + '\'' +
                ", floorSid=" + floorSid +
                ", floorCode='" + floorCode + '\'' +
                ", floorName='" + floorName + '\'' +
                ", industryConditionSid=" + industryConditionSid +
                ", shoppeCode='" + shoppeCode + '\'' +
                ", shoppeName='" + shoppeName + '\'' +
                ", shoppeStatus=" + shoppeStatus +
                ", goodsManageType=" + goodsManageType +
                ", businessTypeSid=" + businessTypeSid +
                ", businessPattern='" + businessPattern + '\'' +
                ", shoppeGroup='" + shoppeGroup + '\'' +
                ", shoppeType='" + shoppeType + '\'' +
                ", shoppeShippingPoint='" + shoppeShippingPoint + '\'' +
                ", refCounter='" + refCounter + '\'' +
                ", negativeStock=" + negativeStock +
                ", isShippingPoint=" + isShippingPoint +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", start=" + start +
                ", limit=" + limit +
                '}';
    }
}

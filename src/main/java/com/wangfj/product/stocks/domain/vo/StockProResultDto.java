package com.wangfj.product.stocks.domain.vo;

/**
 * 库存接口商品Dto
 * 
 * @Class Name StockProCountDto
 * @Author yedong
 * @Create In 2015年7月17日
 */
public class StockProResultDto {

	/**
	 * 专柜商品编号
	 */
	private String supplyProductNo;

	/**
	 * 商品名称
	 */
	private String productName;

	/**
	 * 缺货数量
	 */
	private Integer outOfStock;

	/**
	 * 错误信息
	 */
	private String resultMsg;

	/**
	 * 成功失败表示(1:成功; 0:失败)
	 */
	private String resultFlag;

	/**
	 * 可售数
	 */
	private Integer proSum;

	/**
	 * 渠道
	 */
	private String channelSid;

	public String getSupplyProductNo() {
		return supplyProductNo;
	}

	public void setSupplyProductNo(String supplyProductNo) {
		this.supplyProductNo = supplyProductNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getOutOfStock() {
		return outOfStock;
	}

	public void setOutOfStock(Integer outOfStock) {
		this.outOfStock = outOfStock;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public String getResultFlag() {
		return resultFlag;
	}

	public void setResultFlag(String resultFlag) {
		this.resultFlag = resultFlag;
	}

	public Integer getProSum() {
		return proSum;
	}

	public void setProSum(Integer proSum) {
		this.proSum = proSum;
	}

	public String getChannelSid() {
		return channelSid;
	}

	public void setChannelSid(String channelSid) {
		this.channelSid = channelSid;
	}

	@Override
	public String toString() {
		return "StockProResultDto [supplyProductNo=" + supplyProductNo + ", productName="
				+ productName + ", outOfStock=" + outOfStock + ", resultMsg=" + resultMsg
				+ ", resultFlag=" + resultFlag + ", proSum=" + proSum + ", channelSid=" + channelSid
				+ "]";
	}

}

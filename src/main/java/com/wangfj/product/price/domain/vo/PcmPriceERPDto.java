package com.wangfj.product.price.domain.vo;

public class PcmPriceERPDto {

	/**
	 * 门店(中台的门店编码)
	 */
	private String storecode;

	/**
	 * 商品编码 电商ERP上传电商商品编码 门店ERP上传的是门店ERP自己的商品编码 导入终端为空
	 */
	private String matnr;

	/**
	 * 中台专柜商品编码 （电商ERP，门店ERP和导入终端上传的是中台供应商商品编码） 电商和导入终端不为空，门店ERP可能传空
	 */
	private String supplierprodcode;

	/**
	 * 售价
	 */
	private String zsprice;

	/**
	 * 中台专柜编码
	 */
	private String sitecode;

	/**
	 * 货币单位 对于门店ERP是固定值RMB 对于电商ERP则根据系统当前设置提供
	 */
	private String waers;

	/**
	 * 开始日期（包含当天。只到日期，不到时分秒）yyyymmdd
	 */
	private String bdate;

	/**
	 * 结束日期（包含当天。只到日期，不到时分秒。对于门店ERP则为固定值99991231）
	 */
	private String edate;

	/**
	 * 变价号（调价单号，主要用于BI统计分析用）电商传值，导入终端传空
	 */
	private String changecode;

	/**
	 * 本条记录对应的操作 (A添加；U更新；D删除)
	 */
	private String action_code;

	/**
	 * 操作时间：yyyyMMdd.HHmmssZ
	 */
	private String action_date;

	/**
	 * 操作人
	 */
	private String action_persion;

	/**
	 * 价格渠道 1:全渠道;2:电商;3:微信;4:APP;5:线下
	 */
	private String pricechannel;

	/**
	 * 渠道id
	 */
	private String channelsid;

	/**
	 * 创建时间
	 */
	private String createtime;

	/**
	 * 价格类型 1标识零售价 2 短期价
	 */
	private String pricetype;

	/**
	 * 是否是大码变价单 ：是:1;
	 */
	private String attribute5;

	public String getStorecode() {
		return storecode;
	}

	public void setStorecode(String storecode) {
		this.storecode = storecode;
	}

	public String getMatnr() {
		return matnr;
	}

	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}

	public String getSupplierprodcode() {
		return supplierprodcode;
	}

	public void setSupplierprodcode(String supplierprodcode) {
		this.supplierprodcode = supplierprodcode;
	}

	public String getZsprice() {
		return zsprice;
	}

	public void setZsprice(String zsprice) {
		this.zsprice = zsprice;
	}

	public String getSitecode() {
		return sitecode;
	}

	public void setSitecode(String sitecode) {
		this.sitecode = sitecode;
	}

	public String getWaers() {
		return waers;
	}

	public void setWaers(String waers) {
		this.waers = waers;
	}

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public String getChangecode() {
		return changecode;
	}

	public void setChangecode(String changecode) {
		this.changecode = changecode;
	}

	public String getAction_code() {
		return action_code;
	}

	public void setAction_code(String action_code) {
		this.action_code = action_code;
	}

	public String getAction_date() {
		return action_date;
	}

	public void setAction_date(String action_date) {
		this.action_date = action_date;
	}

	public String getAction_persion() {
		return action_persion;
	}

	public void setAction_persion(String action_persion) {
		this.action_persion = action_persion;
	}

	public String getPricechannel() {
		return pricechannel;
	}

	public void setPricechannel(String pricechannel) {
		this.pricechannel = pricechannel;
	}

	public String getChannelsid() {
		return channelsid;
	}

	public void setChannelsid(String channelsid) {
		this.channelsid = channelsid;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getPricetype() {
		return pricetype;
	}

	public void setPricetype(String pricetype) {
		this.pricetype = pricetype;
	}

	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	@Override
	public String toString() {
		return "PcmPriceERPDto [storecode=" + storecode + ", matnr=" + matnr + ", supplierprodcode="
				+ supplierprodcode + ", zsprice=" + zsprice + ", sitecode=" + sitecode + ", waers="
				+ waers + ", bdate=" + bdate + ", edate=" + edate + ", changecode=" + changecode
				+ ", action_code=" + action_code + ", action_date=" + action_date
				+ ", action_persion=" + action_persion + ", pricechannel=" + pricechannel
				+ ", channelsid=" + channelsid + ", createtime=" + createtime + ", pricetype="
				+ pricetype + ", attribute5=" + attribute5 + "]";
	}

}

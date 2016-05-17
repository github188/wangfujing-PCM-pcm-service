package com.wangfj.product.constants;

public class RquestType {

	/**
	 * 正常订单下发报文
	 */
	public static final String SEND_ORDER_REQUEST = "S201";
	/**
	 * 改单下发报文
	 */
	public static final String CHANGE_ORDER_REQUEST = "S202";
	/**
	 * 取消订单通知报文
	 */
	public static final String CANCEL_ORDER_REQUEST = "S203";
	/**
	 * 退货申请下发报文
	 */
	public static final String REFUND_APPLY_REQUEST = "S204";
	/**
	 * 顾客自提完成报文
	 */
	public static final String TIHUO_REQUEST = "S205";
	/**
	 * 订单状态修改报文
	 */
	public static final String CHANGE_STATUS_REQUEST = "S101";
	/**
	 * 订单取消应答报文
	 */
	public static final String RETURN_REQUEST = "S102";
	/**
	 * 退货入库通知报文
	 */
	public static final String RUKU_REQUEST = "S103";
	/**
	 * 订单拒收报文
	 */
	public static final String REFUSE_ORDER_REQUEST = "S104";
	/**
	 * 缺货返回订单报文
	 */
	public static final String QUEHUO_RETURN_REQUEST = "S105";
	
	/**
	 * 投递员投递包裹到自提柜中
	 */
	public static final String POSTMAN_DELIVER_PACKAGE = "S106";
	
	/**
	 * 包裹被用户或者投递员从快递柜中取出
	 */
	public static final String POSTMAN_TAKEN_PACKAGE = "S107";
	
	/**
	 * 重新发送投递取件密码短信
	 */
	public static final String POSTMAN_SEND_PACKAGEMS = "S108";
	
	/**
	 * 销售确认单下发
	 */
	public static final String SALE_ORDER="S211";
	/**
	 * 退货入库下发MIS
	 */
	public static final String MIS_ORDER_REFUND = "S109";
	/**
	 * 蔬菜随心订下发
	 */
	public static final String CREATE_VAT_ORDER="S206";
	/**
	 * 蔬菜随心订改单下发
	 */
	public static final String CHANGE_VAT_ORDER="S207";
	/**
	 * 发货库存明细回传
	 */
	public static final String CHUNAN_NO="S110";
	/**
	 * 蔬菜随心订状态同步
	 */
	public static final String VAT_ORDER_SYN = "S111";
}

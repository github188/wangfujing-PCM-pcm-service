package com.wangfj.product.stocks.service.intf;

import com.wangfj.product.stocks.domain.vo.StockProCountDto;

public interface IPcmStockLockRecordService {
	/**
	 * 查询锁定数量
	 * 
	 * @Methods Name selectLockCountByShoppePro
	 * @Create In 2015年8月12日 By yedong
	 * @param record
	 * @return Integer
	 */
	public Integer selectLockCountByShoppePro(StockProCountDto stockProCountDto);

	/**
	 * 添加锁定记录
	 * 
	 * @Methods Name lockStock
	 * @Create In 2015年7月28日 By yedong
	 * @param stockProCountDto
	 * @return Integer
	 * @throws Exception
	 */
	public Integer lockStock(StockProCountDto stockProCountDto);

	/**
	 * 添加解锁记录
	 * 
	 * @Methods Name unlockStock
	 * @Create In 2015年7月28日 By yedong
	 * @param stockProCountDto
	 * @return Integer
	 * @throws Exception
	 */
	public boolean unlockStock(StockProCountDto stockProCountDto);

	/**
	 * 判断是否已锁库
	 * 
	 * @Methods Name lockStockStatus
	 * @Create In 2015年7月28日 By yedong
	 * @param stockProCountDto
	 * @return Integer
	 */
	public Integer lockStockStatus(StockProCountDto stockProCountDto);

	/**
	 * 根据销售单号删除锁库记录信息
	 * 
	 * @Methods Name delStockRecordBySalesItemNo
	 * @Create In 2015年8月24日 By kongqf
	 * @param saleNo
	 * @return boolean
	 */
	public boolean delStockRecordBySalesItemNo(String saleNo);
}

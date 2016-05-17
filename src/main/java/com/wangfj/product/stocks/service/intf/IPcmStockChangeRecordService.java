package com.wangfj.product.stocks.service.intf;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.stocks.domain.vo.PcmStockChangeRecordHisDto;
import com.wangfj.product.stocks.domain.vo.PcmStockDto;
import com.wangfj.product.stocks.domain.vo.StockProCountDto;

public interface IPcmStockChangeRecordService {
	public void changRecord(PcmStockDto pcmStockDto, String billsno, Integer proSum);

	/**
	 * 添加库存变动记录
	 * 
	 * @Methods Name addChangeRecord
	 * @Create In 2015年7月28日 By yedong
	 * @param stockProCountDto
	 * @param recordId
	 * @return Integer
	 */
	public boolean addChangeRecord(StockProCountDto stockProCountDto, Integer recordId,
			Integer proSum, String operator);

	/**
	 * 查询库位变更历史记录
	 * 
	 * @Methods Name selectStockChangeHisRecordbyStockType
	 * @Create In 2015年11月4日 By kongqf
	 * @param dto
	 * @return List<PcmStockChangeRecordHisDto>
	 */
	public Page<PcmStockChangeRecordHisDto> selectStockChangeHisRecordbyStockType(
			PcmStockChangeRecordHisDto dto);
}

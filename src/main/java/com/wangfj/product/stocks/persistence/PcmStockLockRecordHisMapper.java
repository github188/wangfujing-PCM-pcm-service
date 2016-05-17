package com.wangfj.product.stocks.persistence;

import com.wangfj.product.stocks.domain.entity.PcmStockLockRecord;
import com.wangfj.product.stocks.domain.entity.PcmStockLockRecordHis;

public interface PcmStockLockRecordHisMapper {
	int deleteByPrimaryKey(Long sid);

	int insert(PcmStockLockRecordHis record);

	int insertSelective(PcmStockLockRecordHis record);

	int insertBybillsNo(PcmStockLockRecord record);

	PcmStockLockRecordHis selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmStockLockRecordHis record);

	int updateByPrimaryKey(PcmStockLockRecordHis record);
}
package com.wangfj.product.stocks.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangfj.product.stocks.domain.entity.PcmStockLockRecord;
import com.wangfj.product.stocks.domain.vo.StockProCountDto;
import com.wangfj.product.stocks.persistence.PcmStockLockRecordHisMapper;
import com.wangfj.product.stocks.persistence.PcmStockLockRecordMapper;
import com.wangfj.product.stocks.service.intf.IPcmStockLockRecordService;
import com.wangfj.util.Constants;

@Service
@Transactional
public class PcmStockLockRecordServiceImpl implements IPcmStockLockRecordService {
	@Autowired
	public PcmStockLockRecordMapper pcmStockLockRecordMapper;

	@Autowired
	public PcmStockLockRecordHisMapper pcmStockLockRecordHisMapper;

	/**
	 * 查询锁定数量
	 * 
	 * @Methods Name selectLockCountByShoppePro
	 * @Create In 2015年8月12日 By yedong
	 * @param record
	 * @return Integer
	 */
	public Integer selectLockCountByShoppePro(StockProCountDto stockProCountDto) {
		PcmStockLockRecord record = new PcmStockLockRecord();
		record.setBillsNo(stockProCountDto.getSalesItemNo());
		record.setShoppeProSid(stockProCountDto.getSupplyProductNo());
		List<PcmStockLockRecord> dtoList = pcmStockLockRecordMapper
				.selectLockCountByShoppePro(record);
		int lockCount = Constants.PUBLIC_0;
		if (dtoList.size() > Constants.PUBLIC_0) {
			lockCount = dtoList.get(Constants.PUBLIC_0).getLockSum().intValue();
		}
		return lockCount;
	}

	/**
	 * 添加锁定记录
	 * 
	 * @Methods Name lockStock
	 * @Create In 2015年7月28日 By yedong
	 * @param stockProCountDto
	 * @return Integer
	 * @throws Exception
	 */
	public Integer lockStock(StockProCountDto stockProCountDto) {
		/* 判断锁库状态 */
		PcmStockLockRecord record = new PcmStockLockRecord();
		record.setBillsNo(stockProCountDto.getSalesItemNo());
		record.setShoppeProSid(stockProCountDto.getSupplyProductNo());
		Integer is_lock = pcmStockLockRecordMapper.selectLockByBillsNo(record);
		/* lock =(0未锁库)(1已锁库)(2已解锁) */
		if (is_lock == 0) {
			Date date = new Date();
			PcmStockLockRecord recordLock = new PcmStockLockRecord();
			recordLock.setSaleNo(stockProCountDto.getSaleNo());
			recordLock.setBillsNo(stockProCountDto.getSalesItemNo());
			recordLock.setShoppeProSid(stockProCountDto.getSupplyProductNo());
			recordLock.setLockSum((long) stockProCountDto.getSaleSum());
			recordLock.setLockTime(date);
			recordLock.setLockTypeSid(stockProCountDto.getStockType());
			recordLock.setNote("专柜商品" + stockProCountDto.getSupplyProductNo() + "已锁定");
			int insertSelective = pcmStockLockRecordMapper.insertSelective(recordLock);
			if (insertSelective == 0) {
				return 2;
			}
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * 添加解锁记录
	 * 
	 * @Methods Name unlockStock
	 * @Create In 2015年7月28日 By yedong
	 * @param stockProCountDto
	 * @return Integer
	 * @throws Exception
	 */
	public boolean unlockStock(StockProCountDto stockProCountDto) {
		/* 判断锁库状态 */
		PcmStockLockRecord record = new PcmStockLockRecord();
		record.setBillsNo(stockProCountDto.getSalesItemNo());
		record.setShoppeProSid(stockProCountDto.getSupplyProductNo());
		Integer is_lock = pcmStockLockRecordMapper.selectLockByBillsNo(record);
		/* lock =(0未锁库)(1已锁库)(2已解锁) */
		boolean flag = false;
		if (is_lock == Constants.PUBLIC_1) {
			Date date = new Date();
			PcmStockLockRecord recordLock = new PcmStockLockRecord();
			recordLock.setSaleNo(stockProCountDto.getSaleNo());
			recordLock.setBillsNo(stockProCountDto.getSalesItemNo());
			recordLock.setShoppeProSid(stockProCountDto.getSupplyProductNo());
			recordLock.setLockSum((long) stockProCountDto.getSaleSum());
			recordLock.setLockTime(date);
			recordLock.setLockTypeSid(stockProCountDto.getStockType());
			if (Constants.PCMSTOCK_NO_UNLOCK == stockProCountDto.getStockType()) {
				recordLock.setNote("专柜商品" + stockProCountDto.getSupplyProductNo() + "已解锁");
			}
			if (Constants.PCMSTOCK_YES_UNLOCK == stockProCountDto.getStockType()) {
				recordLock.setNote("专柜商品" + stockProCountDto.getSupplyProductNo() + "已减库");
			}
			int insertSelective = pcmStockLockRecordMapper.insertSelective(recordLock);
			if (insertSelective > 0) {
				flag = true;
			}
		}

		return flag;
	}

	/**
	 * 判断是否已锁库
	 * 
	 * @Methods Name lockStockStatus
	 * @Create In 2015年7月28日 By yedong
	 * @param stockProCountDto
	 * @return Integer
	 */
	public Integer lockStockStatus(StockProCountDto stockProCountDto) {
		/* 判断是否已锁库 count(*) =0 未锁库 =1 已锁库 =2已解锁 */
		PcmStockLockRecord record = new PcmStockLockRecord();
		record.setBillsNo(stockProCountDto.getSalesItemNo());
		record.setShoppeProSid(stockProCountDto.getSupplyProductNo());
		Integer is_lock = pcmStockLockRecordMapper.selectLockByBillsNo(record);
		return is_lock;
	}

	/**
	 * 根据销售单号删除锁库记录信息
	 * 
	 * @Methods Name delStockRecordBySalesItemNo
	 * @Create In 2015年8月24日 By kongqf
	 * @param saleNo
	 * @return boolean
	 */
	public boolean delStockRecordBySalesItemNo(String saleNo) {
		List<PcmStockLockRecord> pcmStockLockRecords = new ArrayList<PcmStockLockRecord>();
		pcmStockLockRecords = pcmStockLockRecordMapper.selectLockReocrdBySaleNo(saleNo);
		boolean flag = true;
		int count = 0;
		for (PcmStockLockRecord psr : pcmStockLockRecords) {
			count = pcmStockLockRecordHisMapper.insertBybillsNo(psr);
			if (2 == count) {
				count = pcmStockLockRecordMapper.deleteByBillsNo(psr.getBillsNo());
				if (2 != count) {
					flag = false;
					break;
				}
			} else {
				flag = false;
				break;
			}
		}
		return flag;

	}
}

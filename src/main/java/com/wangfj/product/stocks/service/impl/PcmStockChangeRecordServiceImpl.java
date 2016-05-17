package com.wangfj.product.stocks.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.product.stocks.domain.entity.PcmStockChangeRecord;
import com.wangfj.product.stocks.domain.vo.PcmStockChangeRecordHisDto;
import com.wangfj.product.stocks.domain.vo.PcmStockDto;
import com.wangfj.product.stocks.domain.vo.StockProCountDto;
import com.wangfj.product.stocks.persistence.PcmStockChangeRecordMapper;
import com.wangfj.product.stocks.service.intf.IPcmStockChangeRecordService;
import com.wangfj.util.Constants;

@Service
@Transactional
public class PcmStockChangeRecordServiceImpl implements IPcmStockChangeRecordService {
	@Autowired
	public PcmStockChangeRecordMapper pcmStockChangeRecordMapper;

	/**
	 * 添加纪录
	 */
	public void changRecord(PcmStockDto pcmStockDto, String billsno, Integer proSum) {
		Date date = new Date();
		PcmStockChangeRecord pscr = new PcmStockChangeRecord();
		if (StringUtils.isNotBlank(billsno)) {
			pscr.setBillsNo(billsno);
		}
		pscr.setStockSid((Long) pcmStockDto.getSid());
		pscr.setChangeSum(pcmStockDto.getProSum());
		pscr.setChangeTime(date);
		pscr.setChangeTypeSid((long) pcmStockDto.getStockTypeSid());
		pscr.setShoppeProSid(pcmStockDto.getShoppeProSid());
		// pscr.setNote("库存导入:" + pcmStockDto.getSource() + "," +
		// pcmStockDto.getOperator());
		pscr.setNote(getImpotNote(pcmStockDto.getType(), pcmStockDto.getSource(),
				pcmStockDto.getOperator()));
		pscr.setProSum((long) proSum);
		int count = pcmStockChangeRecordMapper.insertSelective(pscr);
		if (count == 0) {
			throw new BleException(ErrorCode.STOCK_IMPORT_FAILED.getErrorCode(),
					ErrorCode.STOCK_IMPORT_FAILED.getMemo());
		}
	}

	/**
	 * 导入库存说明
	 * 
	 * @Methods Name getImpotNote
	 * @Create In 2016年3月29日 By kongqf
	 * @param type
	 * @return String
	 */
	public String getImpotNote(String type, String source, String operator) {
		String note = "库存导入:" + source + "," + operator;
		if (Constants.PCMSTOCK_TYPE_ALL.equals(type)) {
			note = "全量" + note;
		}
		if (Constants.PCMSTOCK_TYPE_DELTA.equals(type)) {
			note = "增量" + note;
		}
		return note;
	}

	/**
	 * 添加库存变动纪录
	 * 
	 * @Methods Name addChangeRecord
	 * @Create In 2015年11月3日 By kongqf
	 * @param stockProCountDto
	 * @param recordId
	 * @param proSum
	 * @return boolean
	 */
	public boolean addChangeRecord(StockProCountDto stockProCountDto, Integer recordId,
			Integer proSum, String operator) {
		Date date = new Date();
		boolean flag = false;
		PcmStockChangeRecord recordChange = new PcmStockChangeRecord();
		recordChange.setBillsNo(stockProCountDto.getSalesItemNo());
		recordChange.setChangeTypeSid((long) stockProCountDto.getStockType());
		recordChange.setNote(getStockChangeNote(stockProCountDto.getStockType(), operator));
		recordChange.setChangeSum((long) stockProCountDto.getSaleSum());
		recordChange.setShoppeProSid(stockProCountDto.getSupplyProductNo());
		recordChange.setStockSid((long) recordId);
		recordChange.setChangeTime(date);
		recordChange.setProSum((long) proSum);// 原库存数
		int insertSelective = pcmStockChangeRecordMapper.insertSelective(recordChange);
		if (insertSelective > 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 库位变动备注信息
	 * 
	 * @Methods Name getStockChangeNote
	 * @Create In 2015年8月27日 By kongqf
	 * @param stockType
	 * @return String
	 */
	private String getStockChangeNote(Integer stockType, String operator) {
		String note = StringUtils.EMPTY;

		switch (stockType) {
		case Constants.PCMSTOCK_NO_LOCK:
			note = "锁库变动";
			break;
		case Constants.PCMSTOCK_NO_UNLOCK:
			note = "未支付解锁变动";
			break;
		case Constants.PCMSTOCK_TYPE_SALE:
			note = "可售库变动";
			break;
		case Constants.PCMSTOCK_TYPE_RETURN:
			note = "退货库变动";
			break;
		case Constants.PCMSTOCK_TYPE_DEFECTIVE:
			note = "残次品变动";
			break;
		case Constants.PCMSTOCK_OUT_TRANSFER:
			note = "调出变动";
			break;
		case Constants.PCMSTOCK_IN_TRANSFER:
			note = "调入变动";
			break;
		case Constants.PCMSTOCK_TYPE_BORROW:
			note = "借出变动";
			break;
		case Constants.PCMSTOCK_TYPE_REBORROW:
			note = "归还变动";
			break;
		default:
			note = "库位变动";
		}
		if (StringUtils.isNotBlank(operator)) {
			note += ":" + operator;
		}
		return note;
	}

	/**
	 * 查询库位变更历史记录
	 * 
	 * @Methods Name selectStockChangeHisRecordbyStockType
	 * @Create In 2015年11月4日 By kongqf
	 * @param dto
	 * @return List<PcmStockChangeRecordHisDto>
	 */
	public Page<PcmStockChangeRecordHisDto> selectStockChangeHisRecordbyStockType(
			PcmStockChangeRecordHisDto dto) {
		Page<PcmStockChangeRecordHisDto> productPageDto = new Page<PcmStockChangeRecordHisDto>();
		List<PcmStockChangeRecordHisDto> recordList = new ArrayList<PcmStockChangeRecordHisDto>();
		Integer count = pcmStockChangeRecordMapper.selectStockChangeHisRecordbyStockTypeCount(dto);
		if (count != null && count != 0) {
			recordList = pcmStockChangeRecordMapper.selectStockChangeHisRecordbyStockType(dto);
			productPageDto.setList(recordList);
		}
		productPageDto.setCurrentPage(dto.getCurrentPage());
		productPageDto.setPageSize(dto.getPageSize());
		productPageDto.setCount(count);
		return productPageDto;
	}

}

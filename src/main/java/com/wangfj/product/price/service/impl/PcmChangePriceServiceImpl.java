package com.wangfj.product.price.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.product.price.domain.entity.PcmChangePriceRecord;
import com.wangfj.product.price.persistence.PcmChangePriceRecordHisMapper;
import com.wangfj.product.price.persistence.PcmChangePriceRecordMapper;
import com.wangfj.product.price.service.intf.IPcmChangePriceService;
import com.wangfj.util.BasePage;
import com.wangfj.util.Constants;

@Service
public class PcmChangePriceServiceImpl implements IPcmChangePriceService {
	private static final Logger logger = LoggerFactory.getLogger(PcmPriceServiceImpl.class);
	
	@Autowired
	private PcmChangePriceRecordMapper pcmChangePriceRecordMapper;
	@Autowired
	private PcmChangePriceRecordHisMapper pcmChangePriceRecordHisMapper;
	
	/**
	 * 查询过期变价信息
	 * @author zhangdl
	 * @return
	 */
	@Override
	public List<PcmChangePriceRecord> queryExpireChangePriceInfoList(BasePage basePage) {
		logger.info("start queryExpireChangePriceInfoList()");
		List<PcmChangePriceRecord> pcmhangePriceRecordList = new ArrayList<PcmChangePriceRecord>();
		pcmhangePriceRecordList = pcmChangePriceRecordMapper.queryExpireChangePriceInfoList(basePage);
		logger.info("start queryExpireChangePriceInfoList(),result:" + pcmhangePriceRecordList.toString());
		return pcmhangePriceRecordList;
	}
	
	/**
	 * 过期变价信息移到变价历史表
	 * @author zhangdl
	 * @return
	 */
	@Override
	public boolean saveExpireChangePriceToChangePriceHis(PcmChangePriceRecord changePriceRecord) {
		logger.info("start saveExpireChangePriceToChangePriceHis(),para:" + changePriceRecord.toString());
		boolean flag = false;
		int count = pcmChangePriceRecordHisMapper.insertSelective(changePriceRecord);
		if (Constants.PUBLIC_1 == count) {
			count = 0;
			count = pcmChangePriceRecordMapper.deleteByPrimaryKey(changePriceRecord.getSid());
			if (Constants.PUBLIC_1 == count) {
				flag = true;
			} else {
				throw new RuntimeException("delete pcmchangerecordprice failed,sid:" + changePriceRecord.getSid());
			}
		}
		return flag;
	}

}

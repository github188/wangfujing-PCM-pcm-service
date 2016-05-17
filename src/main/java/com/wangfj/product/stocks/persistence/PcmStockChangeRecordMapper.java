package com.wangfj.product.stocks.persistence;

import java.util.List;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.stocks.domain.entity.PcmStockChangeRecord;
import com.wangfj.product.stocks.domain.vo.PcmStockChangeRecordHisDto;

/**
 * 
 * @Class Name PcmStockChangeRecordMapper
 * @Author yedong
 * @Create In 2015年7月3日
 */
public interface PcmStockChangeRecordMapper extends BaseMapper<PcmStockChangeRecord> {
	/**
	 * 删除
	 * 
	 * @Methods Name deleteByPrimaryKey
	 * @Create In 2015年7月3日 By yedong
	 * @param sid
	 * @return int
	 */
	int deleteByPrimaryKey(Long sid);

	Integer insert(PcmStockChangeRecord record);

	/**
	 * 添加
	 * 
	 * @Methods Name insertSelective
	 * @Create In 2015年7月3日 By yedong
	 * @param record
	 * @return int
	 */
	int insertSelective(PcmStockChangeRecord record);

	/**
	 * 修改
	 * 
	 * @Methods Name updateByPrimaryKeySelective
	 * @Create In 2015年7月3日 By yedong
	 * @param record
	 * @return int
	 */
	int updateByPrimaryKeySelective(PcmStockChangeRecord record);

	Integer updateByPrimaryKey(PcmStockChangeRecord record);

	/**
	 * 查询库位变动历史记录
	 * 
	 * @Methods Name selectStockChangeHisRecordbyStockType
	 * @Create In 2015年11月4日 By kongqf
	 * @param record
	 * @return List<PcmStockChangeRecordHisDto>
	 */
	List<PcmStockChangeRecordHisDto> selectStockChangeHisRecordbyStockType(
			PcmStockChangeRecordHisDto record);

	/**
	 * 查询库位变动历史记录总数
	 * 
	 * @Methods Name selectStockChangeHisRecordbyStockTypeCount
	 * @Create In 2015年11月6日 By kongqf
	 * @param record
	 * @return Integer
	 */
	Integer selectStockChangeHisRecordbyStockTypeCount(PcmStockChangeRecordHisDto record);
}
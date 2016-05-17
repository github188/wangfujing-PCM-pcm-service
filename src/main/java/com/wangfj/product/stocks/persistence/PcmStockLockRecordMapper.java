package com.wangfj.product.stocks.persistence;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.stocks.domain.entity.PcmStockLockRecord;

/**
 * 
 * @Class Name PcmStockLockRecordMapper
 * @Author yedong
 * @Create In 2015年7月3日
 */
public interface PcmStockLockRecordMapper extends BaseMapper<PcmStockLockRecord> {
	/**
	 * 删除
	 * 
	 * @Methods Name deleteByPrimaryKey
	 * @Create In 2015年7月3日 By yedong
	 * @param sid
	 * @return int
	 */
	int deleteByPrimaryKey(Long sid);

	Integer insert(PcmStockLockRecord record);

	/**
	 * 添加
	 * 
	 * @Methods Name insertSelective
	 * @Create In 2015年7月3日 By yedong
	 * @param record
	 * @return int
	 */
	int insertSelective(PcmStockLockRecord record);

	/**
	 * 修改
	 * 
	 * @Methods Name updateByPrimaryKeySelective
	 * @Create In 2015年7月3日 By yedong
	 * @param record
	 * @return int
	 */
	int updateByPrimaryKeySelective(PcmStockLockRecord record);

	int updateByPrimaryKey(PcmStockLockRecord record);

	/**
	 * 根据单据号判断是否锁定
	 * 
	 * @Methods Name selectLockByBillsNo
	 * @Create In 2015年7月17日 By yedong
	 * @return int
	 */
	Integer selectLockByBillsNo(PcmStockLockRecord record);

	/**
	 * 根据单据号和专柜商品编号获得锁定数量
	 * 
	 * @Methods Name selectLockCountByBillsNo
	 * @Create In 2015年7月21日 By yedong
	 * @param billsNo
	 * @return Integee
	 */
	Integer selectLockCountByBillsNo(PcmStockLockRecord record);

	/**
	 * 根据专柜商品编码查询锁定数量
	 * 
	 * @Methods Name selectLockCountByShoppePro
	 * @Create In 2015年7月28日 By yedong
	 * @param shoppeProSid
	 * @return Integer
	 */
	List<PcmStockLockRecord> selectLockCountByShoppePro(PcmStockLockRecord record);

	/**
	 * 更改锁定类型
	 * 
	 * @Methods Name updateLockTypeByShoppePro
	 * @Create In 2015年7月30日 By yedong
	 * @param paramMap
	 * @return Integer
	 */
	Integer updateLockTypeByShoppePro(Map<String, Object> paramMap);

	/**
	 * 根据销售单号查询锁库信息
	 * 
	 * @Methods Name selectLockReocrdBySaleNo
	 * @Create In 2015年8月24日 By kongqf
	 * @param record
	 * @return List<PcmStockLockRecord>
	 */
	List<PcmStockLockRecord> selectLockReocrdBySaleNo(String saleNo);

	/**
	 * 根据销售单号删除锁库信息
	 * 
	 * @Methods Name deleteBySaleNo
	 * @Create In 2015年8月24日 By kongqf
	 * @param saleNo
	 * @return int
	 */
	int deleteByBillsNo(String saleNo);
}
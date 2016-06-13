package com.wangfj.product.price.persistence;

import java.util.List;

import com.wangfj.product.price.domain.entity.PcmChangePriceRecord;
import com.wangfj.product.price.domain.vo.PcmPriceERPDto;
import com.wangfj.product.price.domain.vo.PcmPricePISDto;
import com.wangfj.product.price.domain.vo.SelectChangePriceRecordDto;
import com.wangfj.util.BasePage;

public interface PcmChangePriceRecordMapper {
	int deleteByPrimaryKey(Long sid);

	int insert(PcmChangePriceRecord record);

	int insertSelective(PcmChangePriceRecord record);

	/**
	 * 新增变价请求记录
	 * 
	 * @Methods Name insertSelectiveByERPDto
	 * @Create In 2015年8月14日 By kongqf
	 * @param pcmPriceERPDto
	 * @return int
	 */
	int insertSelectiveByERPDto(PcmPriceERPDto pcmPriceERPDto);

	/**
	 * 批量变价记录
	 * 
	 * @Methods Name insertSelectiveByPISDto
	 * @Create In 2015年8月19日 By kongqf
	 * @param pcmPricePISDto
	 * @return int
	 */
	int insertSelectiveByPISDto(PcmPricePISDto pcmPricePISDto);

	PcmChangePriceRecord selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmChangePriceRecord record);

	int updateByPrimaryKey(PcmChangePriceRecord record);

	/**
	 * 查询当前生效变价单总数
	 * 
	 * @Methods Name queryCurChangePriceRecordCount
	 * @Create In 2015年9月23日 By kongqf
	 * @return Integer
	 */
	Integer queryCurChangePriceRecordCount();

	/**
	 * 查询当前生效变价单
	 * 
	 * @Methods Name queryCurChangePriceRecordByPara
	 * @Create In 2015年9月23日 By kongqf
	 * @param record
	 * @return List<PcmChangePriceRecord>
	 */
	List<PcmChangePriceRecord> queryCurChangePriceRecordByPara(SelectChangePriceRecordDto record);
	
	/**
	 * 查询过期变价信息
	 * @param basePage
	 * @Create In 2016年6月13日 By zhangdl
	 * @return
	 */
	List<PcmChangePriceRecord> queryExpireChangePriceInfoList(BasePage basePage);
}
package com.wangfj.product.price.service.intf;

import java.math.BigDecimal;
import java.util.Map;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.price.domain.vo.PcmChangePriceLimitDto;
import com.wangfj.product.price.domain.vo.PublishPriceLimitDto;

public interface IPcmChangePriceLimitService {

	/**
	 * 根据sid删除阀值
	 * 
	 * @param sid
	 *            主健
	 * @return
	 */
	// public boolean deleteByPrimaryKey(Long sid);

	/**
	 * 添加/修改门店阀值
	 * 
	 * @param record
	 * @return
	 */
	public String saveOrUpdateChangePriceLimit(PublishPriceLimitDto dto);

	/**
	 * 分页查询
	 * 
	 * @Methods Name PcmChannel
	 * @Create In 2015年7月31日 By zhangdongliang
	 * @param record
	 * @return List<PcmChangePriceLimit>
	 */
	public Page<PcmChangePriceLimitDto> selectPageList(Map<String, Object> dto);

	/**
	 * 得到所有已经添加过阀值的门店号
	 * 
	 * @return
	 */
	public String selectAllShopSidFromPriceLimit();

	/**
	 * 根据门店编号获取门店阀值
	 * 
	 * @param shopCode
	 * @return
	 */
	public PcmChangePriceLimitDto selectPriceLimitByShopCode(String shopCode);

	/**
	 * 获取上下线阀值
	 * 
	 * @Methods Name getPriceLimit
	 * @Create In 2015年11月18日 By kongqf
	 * @param limitDto
	 * @param flag
	 * @return BigDecimal
	 */
	public BigDecimal getPriceLimit(PcmChangePriceLimitDto limitDto, boolean flag);
}

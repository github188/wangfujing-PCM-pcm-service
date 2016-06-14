package com.wangfj.product.price.service.intf;

import java.util.List;

import com.wangfj.product.price.domain.entity.PcmChangePriceRecord;
import com.wangfj.util.BasePage;

/**
 * 
 * @author zhangdl
 *
 */
public interface IPcmChangePriceService {

	/**
	 * 过期变价信息移到变价历史表
	 * @author zhangdl
	 * @return
	 */
	public List<PcmChangePriceRecord> queryExpireChangePriceInfoList(BasePage basePage);

	/**
	 * 保存过期变价到变价历史表
	 * @param changePriceRecord
	 * @return
	 */
	public boolean saveExpireChangePriceToChangePriceHis(
			PcmChangePriceRecord changePriceRecord);
}

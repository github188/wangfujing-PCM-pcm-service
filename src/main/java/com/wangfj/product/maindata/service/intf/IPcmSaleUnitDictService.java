package com.wangfj.product.maindata.service.intf;

import java.util.List;
import java.util.Map;

/**
 * 
 * @Class Name IPcmSaleUnitDictService
 * @Author wangxiang
 * @Create In 2015年7月20日
 */
public interface IPcmSaleUnitDictService {
	/**
	 * 查询单位信息
	 * 
	 * @Methods Name pushUnitFromPcm
	 * @Create In 2015年7月20日 By wangxiang
	 * @param map
	 * @return List<PcmSaleUnitDict>
	 */
	public List<Map<String, Object>> pushUnitFromPcm();

}

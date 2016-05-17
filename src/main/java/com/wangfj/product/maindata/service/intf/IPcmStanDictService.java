package com.wangfj.product.maindata.service.intf;

import java.util.Map;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.maindata.domain.entity.PcmStanDict;

/**
 * @class Name IPcmStanDictService
 * @author wangxiang
 * @create in 2015年7月16
 */
public interface IPcmStanDictService {
	/**
	 * 将尺码字典从主数据ERP同步至其他系统
	 * 
	 * @Method Name pushSizeFromMdErp
	 * @create in 2015年7月17
	 * @return 尺码信息
	 */
	public Page<PcmStanDict> pushSizeFromPCM(Map<String, Object> map);

	/**
	 * 新增尺码/规格字典
	 * <p>
	 * <b> 标识号49</b>
	 * 
	 * @Methods Name insertPcmStanDict
	 * @Create In 2015年7月24日 By wangsy
	 * @param map
	 * @return int 0新增失败,1新增成功,2以存在
	 */
	@SuppressWarnings("rawtypes")
	public int insertPcmStanDict(Map map);

}

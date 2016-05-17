package com.wangfj.product.maindata.service.intf;

import java.util.Map;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.maindata.domain.entity.PcmColorCodeDict;

/**
 * 色码字典service
 * 
 * @Class Name IPcmColorCodeService
 * @Author zhangxy
 * @Create In 2015年7月21日
 */
public interface IPcmColorCodeService {
	/**
	 * 色码字典下发
	 * 
	 * @Methods Name selectColorCode
	 * @Create In 2015年7月21日 By zhangxy
	 * @param paramMap
	 * @return Page<PcmColorCodeDict>
	 * @throws Exception
	 */
	public Page<PcmColorCodeDict> selectColorCode(Map<String, Object> paramMap) throws Exception;

	/**
	 * 新增色码字典表
	 * 
	 * @Methods Name insertColorCodeDict
	 * @Create In 2015年7月24日 By wangsy
	 * @param map
	 * @return int 0新增失败,1新增成功,2以存在
	 */
	public int insertColorCodeDict(Map<String, Object> map);
}

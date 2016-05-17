package com.wangfj.product.organization.service.intf;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.organization.domain.entity.PcmBusinessTypeDict;
import com.wangfj.product.organization.domain.vo.PcmBusinessTypeDictDto;

/**
 * 经营方式字典service
 * 
 * @Class Name IPcmBusinessTypeDictService
 * @Author zhangxy
 * @Create In 2015年7月29日
 */
public interface IPcmBusinessTypeDictService {
	/**
	 * 新增一条经营方式
	 * 
	 * @Methods Name saveBusinessTypeDict
	 * @Create In 2015年7月29日 By zhangxy
	 * @param PcmBusinessTypeDict
	 * @return int 0失败 1成功 2已存在
	 * @throws Exception
	 */
	public int saveBusinessTypeDict(PcmBusinessTypeDict entity);

	/**
	 * 修改一条经营方式
	 * 
	 * @Methods Name updateBusinessTypeDict
	 * @Create In 2015年7月29日 By zhangxy
	 * @param PcmBusinessTypeDict
	 * @return int 0失败 1成功 2已存在
	 * @throws Exception
	 */
	public int updateBusinessTypeDict(PcmBusinessTypeDict entity);

	/**
	 * 删除一条经营方式
	 * 
	 * @Methods Name deleteBusinessTypeDict
	 * @Create In 2015年7月29日 By zhangxy
	 * @param PcmBusinessTypeDict
	 * @return int 0失败 1成功 2已存在关联
	 * @throws Exception
	 */
	public int deleteBusinessTypeDict(PcmBusinessTypeDict entity);

	/**
	 * 查询经营方式
	 * 
	 * @Methods Name selectBusinessTypeDict
	 * @Create In 2015年8月3日 By zhangxy
	 * @param dto
	 * @return List<PcmBusinessTypeDict>
	 * @throws Exception
	 */
	public Page<PcmBusinessTypeDict> selectBusinessTypeDict(PcmBusinessTypeDictDto dto);
}

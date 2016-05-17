package com.wangfj.product.maindata.service.intf;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.maindata.domain.entity.PcmColorDict;
import com.wangfj.product.maindata.domain.vo.ColorDictDto;

/**
 * 色系字典service
 * 
 * @Class Name IPcmColorDictService
 * @Author zhangxy
 * @Create In 2015年7月29日
 */
public interface IPcmColorDictService {
	/**
	 * 新增一条色系
	 * 
	 * @Methods Name saveColorDict
	 * @Create In 2015年7月29日 By zhangxy
	 * @param PcmColorDict
	 * @return int 0失败 1成功 2已存在
	 * @throws Exception
	 */
	public int saveColorDict(PcmColorDict entity);

	/**
	 * 修改一条色系
	 * 
	 * @Methods Name updateColorDict
	 * @Create In 2015年7月29日 By zhangxy
	 * @param PcmColorDict
	 * @return int 0失败 1成功 2已存在
	 * @throws Exception
	 */
	public int updateColorDict(PcmColorDict entity);

	/**
	 * 删除一条色系
	 * 
	 * @Methods Name deleteColorDict
	 * @Create In 2015年7月29日 By zhangxy
	 * @param PcmColorDict
	 * @return int 0失败 1成功 2已存在关联
	 * @throws Exception
	 */
	public int deleteColorDict(PcmColorDict entity);

	/**
	 * 查询色系
	 * 
	 * @Methods Name selectColorDict
	 * @Create In 2015年8月3日 By zhangxy
	 * @param dto
	 * @return List<PcmColorDict>
	 * @throws Exception
	 */
	public Page<PcmColorDict> selectColorDict(ColorDictDto dto);
}

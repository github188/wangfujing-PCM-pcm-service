package com.wangfj.product.maindata.service.intf;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.maindata.domain.entity.PcmPackUnitDict;
import com.wangfj.product.maindata.domain.vo.PcmPackUnitDto;

/**
 * 包装单位service
 * 
 * @Class Name IPcmPackUnitDictService
 * @Author zhangxy
 * @Create In 2015年7月29日
 */
public interface IPcmPackUnitDictService {
	/**
	 * 新增一条包装单位
	 * 
	 * @Methods Name savePackUnit
	 * @Create In 2015年7月29日 By zhangxy
	 * @param PcmPackUnitDto
	 * @return int 0失败 1成功 2已存在
	 * @throws Exception
	 */
	public int savePackUnit(PcmPackUnitDict entity);

	/**
	 * 修改一条包装单位
	 * 
	 * @Methods Name updatePackUnit
	 * @Create In 2015年7月29日 By zhangxy
	 * @param PcmPackUnitDto
	 * @return int 0失败 1成功 2已存在
	 * @throws Exception
	 */
	public int updatePackUnit(PcmPackUnitDict entity);

	/**
	 * 删除一条包装单位
	 * 
	 * @Methods Name deletePackUnit
	 * @Create In 2015年7月29日 By zhangxy
	 * @param PcmPackUnitDto
	 * @return int 0失败 1成功 2已存在关联
	 * @throws Exception
	 */
	public int deletePackUnit(PcmPackUnitDict entity);

	/**
	 * 查询包装单位
	 * 
	 * @Methods Name selectPackUnitDict
	 * @Create In 2015年8月3日 By zhangxy
	 * @param dto
	 * @return List<PcmPackUnitDict>
	 * @throws Exception
	 */
	public Page<PcmPackUnitDict> selectPackUnit(PcmPackUnitDto dto);
}

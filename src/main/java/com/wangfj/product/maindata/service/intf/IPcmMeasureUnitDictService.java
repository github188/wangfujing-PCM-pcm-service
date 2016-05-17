package com.wangfj.product.maindata.service.intf;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.maindata.domain.entity.PcmMeasureUnitDict;
import com.wangfj.product.maindata.domain.vo.PcmMeasureUnitDto;

/**
 * 计量单位service
 * 
 * @Class Name IPcmMeasureUnitDictService
 * @Author zhangxy
 * @Create In 2015年7月21日
 */
public interface IPcmMeasureUnitDictService {
	/**
	 * 新增一条计量单位
	 * 
	 * @Methods Name saveMeasureUnit
	 * @Create In 2015年7月29日 By zhangxy
	 * @param PcmMeasureUnitDto
	 * @return int 0失败 1成功 2已存在
	 * @throws Exception
	 */
	public int saveMeasureUnit(PcmMeasureUnitDict entity);

	/**
	 * 修改一条计量单位
	 * 
	 * @Methods Name updateMeasureUnit
	 * @Create In 2015年7月29日 By zhangxy
	 * @param PcmMeasureUnitDto
	 * @return int 0失败 1成功 2已存在
	 * @throws Exception
	 */
	public int updateMeasureUnit(PcmMeasureUnitDict entity);

	/**
	 * 删除一条计量单位
	 * 
	 * @Methods Name deleteMeasureUnit
	 * @Create In 2015年7月29日 By zhangxy
	 * @param PcmMeasureUnitDto
	 * @return int 0失败 1成功 2已存在关联
	 * @throws Exception
	 */
	public int deleteMeasureUnit(PcmMeasureUnitDict entity);

	/**
	 * 查询计量单位
	 * 
	 * @Methods Name selectMeasureUnit
	 * @Create In 2015年8月3日 By zhangxy
	 * @param dto
	 * @return List<PcmMeasureUnitDict>
	 * @throws Exception
	 */
	public Page<PcmMeasureUnitDict> selectMeasureUnit(PcmMeasureUnitDto dto);

}

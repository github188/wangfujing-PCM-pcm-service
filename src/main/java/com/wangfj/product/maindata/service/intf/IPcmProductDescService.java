package com.wangfj.product.maindata.service.intf;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.maindata.domain.entity.PcmProductDesc;
import com.wangfj.product.maindata.domain.vo.PcmProductDescDto;
import com.wangfj.product.maindata.domain.vo.PcmProductDescQueryDto;
import com.wangfj.product.maindata.domain.vo.PcmSpuColorDescDto;

public interface IPcmProductDescService {

	public PcmSpuColorDescDto getContentByKuanAndColor(PcmProductDesc entity) throws Exception;

	/**
	 * 查询List
	 * 
	 * @param dto
	 * @return
	 */
	public List<PcmProductDescDto> findListByParam(PcmProductDescQueryDto dto);

	/**
	 * 分页查询
	 * 
	 * @param dto
	 * @return
	 */
	public Page<PcmProductDescDto> findPageByParam(PcmProductDescQueryDto dto);

	/**
	 * 添加
	 * 
	 * @param entity
	 * @return
	 */
	public Integer addProductDesc(PcmProductDesc entity);

	/**
	 * 修改商品描述
	 * 
	 * @param entity
	 * @return
	 */
	public Integer modifyProductDesc(PcmProductDesc entity);

	/**
	 * 添加或修改
	 *
	 * @param entity
	 * @return
	 */
	public Integer addOrModifyProductDesc(PcmProductDesc entity);

	List<Map<String, Object>> selectSpuByCateAndBrand(Map<String, Object> param);
}

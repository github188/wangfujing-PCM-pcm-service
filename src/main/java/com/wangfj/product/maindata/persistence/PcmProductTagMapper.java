package com.wangfj.product.maindata.persistence;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.entity.PcmProductTag;
import com.wangfj.product.maindata.domain.vo.LabelSkuPageQueryDto;
import com.wangfj.product.maindata.domain.vo.PcmShoppeProductQueryDto;
import com.wangfj.product.maindata.domain.vo.PcmSkuQueryDto;
import com.wangfj.product.maindata.domain.vo.SkuPageDto;

public interface PcmProductTagMapper extends BaseMapper<PcmProductTag> {
	/**
	 * 
	 * @param sid
	 * @return
	 */
	int deleteByPrimaryKey(Long sid);

	/**
	 * 
	 * @param record
	 * @return
	 */
	int insertSelective(PcmProductTag record);

	PcmProductTag selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmProductTag record);

	int updateByPrimaryKey(PcmProductTag record);

	/**
	 * 按条件分页查询专柜商品信息
	 * 
	 * @Methods Name selectProductPageByPara
	 * @Create In 2015年12月11日 By dongliang
	 * @param para
	 * @return List<Map<String,Object>>
	 */
	List<Map<String, Object>> selectProductPageByPara(Map<String, Object> para);

	/**
	 * 按条件查询专柜商品信息计数
	 * 
	 * @Methods Name getProductCountByPara
	 * @Create In 2015年12月11日 By dongliang
	 * @param para
	 * @return Integer
	 */
	Integer getProductCountByPara(Map<String, Object> para);

	/**
	 * 按条件查询商品(SKU)信息计数
	 * 
	 * @Methods Name getProDetialCountByPara
	 * @Create In 2015-12-17 By wangxuan
	 * @param dto
	 * @return Integer
	 */
	Integer getProDetialCountByPara(LabelSkuPageQueryDto dto);

	/**
	 * 按条件分页查询商品(SKU)信息
	 * 
	 * @Methods Name selectProDetialPageByPara
	 * @Create In 2015-12-17 By wangxuan
	 * @param dto
	 * @return List<SkuPageDto>
	 */
	List<SkuPageDto> selectProDetialPageByPara(LabelSkuPageQueryDto dto);

	/**
	 * 批量导入专柜商品和标签的关系查询专柜商品
	 * 
	 * @param dto
	 * @return
	 */
	List<Map<String, Object>> findShoppeProductByParamForTag(PcmShoppeProductQueryDto dto);

	/**
	 * 批量导入商品(SKU)与关键字的关系查询商品（SKU）
	 *
	 * @param dto
	 * @return
	 */
	List<Map<String, Object>> findSkuByParamForTag(PcmSkuQueryDto dto);

}
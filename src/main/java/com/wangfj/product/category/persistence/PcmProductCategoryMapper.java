package com.wangfj.product.category.persistence;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.category.domain.entity.PcmProductCategory;
import com.wangfj.product.category.domain.vo.PcmProDetailDto;
import com.wangfj.product.category.domain.vo.PcmProductCategoryDto;
import com.wangfj.product.category.domain.vo.PcmProductDto;

public interface PcmProductCategoryMapper extends BaseMapper<PcmProductCategory> {
	int deleteByPrimaryKey(Long sid);

	int insertSelective(PcmProductCategory record);

	PcmProductCategory selectByPrimaryKey(Long sid);

	/**
	 * 查询统计分类-专柜商品关联关系
	 * 
	 * @Methods Name selectUpdateCate
	 * @Create In 2015年9月10日 By yedong
	 * @param record
	 * @return PcmProductCategory
	 */
	PcmProductCategory selectUpdateCate(Map<String, Object> paramMap);

	int deleteByProductSid(Long productSid);

	int updateByPrimaryKeySelective(PcmProductCategory record);

	int updateByPrimaryKey(PcmProductCategory record);

	List<PcmProductCategory> selectList(PcmProductCategory record);

	int deleteByRecord(PcmProductCategory record);

	int deleteZSCateByRecord(PcmProductCategory record);

	int deleteGYCateByRecord(PcmProductCategory record);

	/**
	 * 通过工业分类sid查询spu产品信息
	 * 
	 * @Methods Name selectSPUByCateSid
	 * @Create In 2015年8月4日 By duanzhaole
	 * @return List<PcmProductDto>
	 */
	List<PcmProductDto> selectSPUByCateSid(Long sid);

	/**
	 * 通过工业分类sid查询spu产品信息条数
	 * 
	 * @Methods Name getCountSPUByCateSid
	 * @Create In 2015年8月4日 By duanzhaole
	 * @return int
	 */
	Long getCountSPUByCateSid(Long sid);

	/**
	 * 通过工业分类sid查询sku产品信息
	 * 
	 * @Methods Name selectSKUByCateSid
	 * @Create In 2015年8月4日 By duanzhaole
	 * @param sid
	 * @return List<PcmProDetailDto>
	 */
	List<PcmProDetailDto> selectSKUByCateSid(Long sid);

	/**
	 * 通过工业分类sid查询spu产品信息条数
	 * 
	 * @Methods Name getCountSPUByCateSid
	 * @Create In 2015年8月4日 By duanzhaole
	 * @return int
	 */
	Long getCountSKUByCateSid(Long sid);

	int updateCateByProSid(PcmProductCategory record);

	/**
	 * 根据产品id查询所有的关联分类信息
	 * 
	 * @Methods Name getProCategoryByProSid
	 * @Create In 2015-8-24 By liuhp
	 * @param sid
	 * @return List<PcmProductCategoryDto>
	 */
	List<PcmProductCategoryDto> getProCategoryByProSid(Long sid);

	/**
	 * 根据品类编码和渠道查询展示分类下对应的商品品类关系
	 * 
	 * @Methods Name selectByCateSidAndChannel
	 * @Create In 2015年9月17日 By duanzhaole
	 * @param productCate
	 * @return List<PcmProductCategory>
	 */
	// List<PcmProductCategory> selectByCateSidAndChannel(PcmProductCategory
	// productCate);
}
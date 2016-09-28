package com.wangfj.product.brand.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.brand.domain.entity.PcmShopBrandRelation;
import com.wangfj.product.brand.domain.vo.PcmShopBrandResultDto;

/**
 * 门店品牌关系mapper
 * 
 * @Class Name PcmShopBrandRelationMapper
 * @Author wangxuan
 * @Create In 2015-8-19
 */
public interface PcmShopBrandRelationMapper extends BaseMapper<PcmShopBrandRelation> {
	int deleteByPrimaryKey(Long sid);

	Integer insert(PcmShopBrandRelation record);

	int insertSelective(PcmShopBrandRelation record);

	PcmShopBrandRelation selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmShopBrandRelation record);

	int updateByPrimaryKey(PcmShopBrandRelation record);

	/**
	 * 门店与门店品牌关系上传时的查询所需数据
	 * 
	 * @Methods Name getShopBrandRelationUpload
	 * @Create In 2015-9-21 By wangxuan
	 * @param relationPara
	 * @return List<PcmShopBrandRelation>
	 */
	List<PcmShopBrandRelation> getShopBrandRelationUpload(Map<String, Object> relationPara);

    /**
     * 门店类型联合门店的门店类型查询门店品牌
     *
     * @param paramMap
     * @return
     */
    List<HashMap<String, Object>> getShopBrandRelation(Map<String, Object> paramMap);

	List<PcmShopBrandResultDto> getListShopBrandFromRelation(Map<String, Object> paramMap);

}
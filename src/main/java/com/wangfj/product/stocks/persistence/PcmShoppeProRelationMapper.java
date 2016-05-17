package com.wangfj.product.stocks.persistence;

import java.util.List;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.stocks.domain.entity.PcmShoppeProRelation;

public interface PcmShoppeProRelationMapper extends BaseMapper<PcmShoppeProRelation> {
    int deleteByPrimaryKey(Long sid);

	Integer insert(PcmShoppeProRelation record);

    int insertSelective(PcmShoppeProRelation record);

    PcmShoppeProRelation selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(PcmShoppeProRelation record);

    int updateByPrimaryKey(PcmShoppeProRelation record);

	/**
	 * 查询子商品
	 * 
	 * @Methods Name getSubPro
	 * @Create In 2015年8月3日 By yedong
	 * @return List<PcmShoppeProRelation>
	 */
	List<PcmShoppeProRelation> getSubPro(PcmShoppeProRelation record);
}
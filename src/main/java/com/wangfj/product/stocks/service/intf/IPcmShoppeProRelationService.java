package com.wangfj.product.stocks.service.intf;

import java.util.List;

import com.wangfj.product.stocks.domain.entity.PcmShoppeProRelation;

/**
 * 子母商品关系
 * 
 * @Class Name IPcmShoppeProRelationService
 * @Author yedong
 * @Create In 2015年8月5日
 */
public interface IPcmShoppeProRelationService {
	/**
	 * 获得自商品列表
	 * 
	 * @Methods Name getSubPro
	 * @Create In 2015年8月5日 By yedong
	 * @param parent
	 * @return List<PcmShoppeProRelation>
	 */
	public List<PcmShoppeProRelation> getSubPro(Long parent);
}

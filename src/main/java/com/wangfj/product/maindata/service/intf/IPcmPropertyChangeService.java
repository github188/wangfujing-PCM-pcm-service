package com.wangfj.product.maindata.service.intf;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.exception.BleException;
import com.wangfj.product.maindata.domain.entity.PcmPropertyChange;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProduct;
import com.wangfj.product.maindata.domain.vo.ChangeProductDto;
import com.wangfj.product.maindata.domain.vo.PcmPropertyChangeDto;
import com.wangfj.product.maindata.domain.vo.ProductCondDto;

public interface IPcmPropertyChangeService {
	public PcmPropertyChange insertProperty(PcmPropertyChangeDto changeDto) throws BleException;

	/**
	 * 专柜商品换品牌
	 * 
	 * @Methods Name changeGroupBrand
	 * @Create In 2015-8-8 By liuhp
	 * @param changeProductDto
	 * @throws Exception
	 */
	public void changeGroupBrands(ChangeProductDto changeProductDto) throws BleException;

	/**
	 * 专柜商品换品牌
	 * 
	 * @Methods Name changeGroupShoppe
	 * @Create In 2015-8-8 By liuhp
	 * @param changeProductDto
	 * @throws Exception
	 */
	public void changeGroupShoppe(ProductCondDto condDto) throws BleException;

	/**
	 * 产品更换统计分类
	 * 
	 * @Methods Name updateStatCategory
	 * @Create In 2015-8-4 By chengsj
	 * @param pc
	 * @return int
	 * @throws Exception
	 */
	public void changeGroupCategory(ProductCondDto condDto) throws BleException;

	public List<PcmPropertyChange> selectList();

	public void updateState(PcmPropertyChange ppc);

	public Map<String, Object> getStroeCodeByShoppePro(PcmShoppeProduct entity);
}

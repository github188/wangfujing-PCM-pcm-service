package com.wangfj.product.maindata.service.intf;

import java.util.List;

import com.wangfj.core.framework.exception.BleException;
import com.wangfj.product.maindata.domain.entity.PcmProDetail;
import com.wangfj.product.maindata.domain.entity.PcmProduct;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProduct;
import com.wangfj.product.maindata.domain.vo.ValidProDetailDto;
import com.wangfj.product.maindata.domain.vo.ValidShoppeProDto;

public interface IValidProDrtailService {
	/**
	 * SPU判重
	 * 
	 * @Methods Name ValidSpuBh
	 * @Create In 2015年8月17日 By yedong
	 * @param dto
	 * @return boolean
	 */
	public PcmProduct validSpuBh(ValidProDetailDto dto);

	/**
	 * SKU判重
	 * 
	 * @Methods Name validSku
	 * @Create In 2015年8月17日 By yedong
	 * @param dto
	 * @return boolean
	 */
	public List<PcmProDetail> validSku(ValidProDetailDto dto);

	/**
	 * 专柜商品判重
	 * 
	 * @Methods Name ValidShoppeProBh
	 * @Create In 2015年8月17日 By yedong
	 * @param dto
	 * @return boolean
	 * @throws Exception
	 */
	public PcmShoppeProduct validShoppeProBh(ValidShoppeProDto dto) throws BleException;

	/**
	 * 专柜商品判重(非联营)
	 * 
	 * @Methods Name ValidShoppeProBh
	 * @Create In 2015年8月17日 By yedong
	 * @param dto
	 * @return boolean
	 * @throws Exception
	 */
	public PcmShoppeProduct validShoppePro(ValidShoppeProDto dto) throws BleException;

}

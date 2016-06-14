package com.wangfj.product.maindata.service.intf;

import com.wangfj.core.framework.exception.BleException;
import com.wangfj.product.maindata.domain.entity.PcmProDetail;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProduct;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProductExtend;
import com.wangfj.product.maindata.domain.vo.PcmAllSysPullDataDto;
import com.wangfj.product.maindata.domain.vo.PullDataDto;
import com.wangfj.product.maindata.domain.vo.SaveShoppeProductDto;
import com.wangfj.product.maindata.domain.vo.SaveShoppeProductDtoDs;
import com.wangfj.product.maindata.domain.vo.SaveSkuDto;
import com.wangfj.product.maindata.domain.vo.ValidProductDto;
import com.wangfj.product.maindata.domain.vo.ValidResultDto;

/**
 * 验证Serivce接口
 * 
 * @Class Name IValidProductService
 * @Author wangsy
 * @Create In 2015年7月14日
 */
public interface IValidProductService {

	/**
	 * 验证spu/sku/专柜pro
	 * 
	 * @Methods Name getPISValidProductFromEfuture
	 * @Create In 2015年7月14日 By wangsy
	 * @param validProductDto
	 * @return ValidResultDto
	 * @throws Exception
	 */
	public ValidResultDto getPISValidProductFromEfuture(ValidProductDto validProductDto)
			throws Exception;

	/**
	 * 商品准入导入终端上传商品到主数据ERP
	 * 
	 * @Methods Name pullProductFromEFuture
	 * @Create In 2015年7月15日 By wangsy
	 * @param PullProductDto
	 *            dto
	 * @return PcmShoppeProduct entity
	 * @throws Exception
	 */
	public PcmShoppeProduct savePullProductFromEFuture(PullDataDto dataDto) throws BleException;

	/**
	 * 电商商品导入
	 * 
	 * @Methods Name saveProductFromSAPERP
	 * @Create In 2015年11月18日 By zhangxy
	 * @param PullProductDto
	 * @return PcmShoppeProduct entity
	 * @throws Exception
	 */
	public PcmShoppeProduct saveProductFromSAPERP(PullDataDto dataDto,
			PcmShoppeProductExtend extendDto) throws BleException;

	/**
	 * 插入一条商品基本信息(SPU-SKU)
	 * 
	 * @Methods Name saveProduct
	 * @Create In 2015年8月24日 By zhangxy
	 * @param SaveSkuDto
	 * @return PcmProDetail
	 * @throws Exception
	 */
	public PcmProDetail saveProduct(SaveSkuDto dto) throws BleException;

	/**
	 * 插入一条专柜商品
	 * 
	 * @Methods Name saveShoppeProduct
	 * @Create In 2015年8月24日 By zhangxy
	 * @param SaveShoppeProductDto
	 * @return PcmShoppeProduct
	 * @throws Exception
	 */
	public PcmShoppeProduct saveShoppeProduct(SaveShoppeProductDto dto) throws BleException;

	/**
	 * 插入一条专柜商品(电商)
	 * 
	 * @Methods Name saveShoppeProductDs
	 * @Create In 2015年8月24日 By zhangxy
	 * @param SaveShoppeProductDtoDs
	 * @return PcmShoppeProduct
	 * @throws Exception
	 */
	public PcmShoppeProduct saveShoppeProductDs(SaveShoppeProductDtoDs dto) throws BleException;

	/**
	 * 插入一条商品基本信息(SPU-SKU) for PIS
	 * 
	 * @Methods Name saveProductPis
	 * @Create In 2015年8月24日 By zhangxy
	 * @param SaveSkuDto
	 * @throws Exception
	 */
	PcmShoppeProduct saveProductPis(SaveSkuDto dataDto, SaveShoppeProductDto dto)
			throws BleException;

	/**
	 * 修改合同
	 * 
	 * @Methods Name updateProductContract
	 * @Create In 2015年8月24日 By zhangxy
	 * @param productCode
	 *            ,contract,rate
	 * @param contract
	 * @throws BleException
	 */
	void updateProductContract(String productCode, String matrn, String contract)
			throws BleException;
	/**
	 * 供应商批量数据导入
	 * @Methods Name savePullProductFromSupllier
	 * @Create In 2016-3-30 By wangc
	 * @param dataDto
	 * @return
	 * @throws Exception PcmShoppeProduct
	 */
	public PcmShoppeProduct savePullProductFromSupllier(PullDataDto dataDto) throws BleException;
}

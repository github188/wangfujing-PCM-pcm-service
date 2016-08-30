package com.wangfj.product.maindata.service.intf;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.product.maindata.domain.entity.PcmErpProduct;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProduct;
import com.wangfj.product.maindata.domain.vo.ErpChangeDto;
import com.wangfj.product.maindata.domain.vo.ErpProPageDto;
import com.wangfj.product.maindata.domain.vo.ErpProductDto;
import com.wangfj.product.maindata.domain.vo.GetErpProductFromEfutureDto;
import com.wangfj.product.maindata.domain.vo.GetSupAndErpInfoDto;
import com.wangfj.product.maindata.domain.vo.ModifyErpProductDto;
import com.wangfj.product.maindata.domain.vo.PcmSearchErpProductDto;
import com.wangfj.util.mq.PublishDTO;

/**
 * 
 * @Class Name IPcmErpProductService
 * @Author zhangxy
 * @Create In 2015年7月9日
 */
public interface IPcmErpProductService {
	/**
	 * 搜索查询ERP信息Count
	 * 
	 * @Methods Name getErpProductInfoByStoreCodePage
	 * @Create In 2015年12月11日 By yedong
	 * @param entity
	 * @return List<PcmSearchErpProductDto>
	 */
	public Integer getCountByStoreCodePage(Map<String, Object> entity);

	/**
	 * 搜索ERP信息下发
	 * 
	 * @Methods Name getErpProductInfoByStoreCodePage
	 * @Create In 2015年12月11日 By yedong
	 * @param entity
	 * @return List<PcmSearchErpProductDto>
	 */
	public List<PcmSearchErpProductDto> getErpProductInfoBySidList(Map<String, Object> paramMap);

	/**
	 * 搜索查询ERP信息
	 * 
	 * @Methods Name getErpProductInfoByStoreCodePage
	 * @Create In 2015年12月11日 By yedong
	 * @param entity
	 * @return List<PcmSearchErpProductDto>
	 */
	public List<PcmSearchErpProductDto> getErpProductInfoByStoreCodePage(PcmErpProduct entity);

	/**
	 * 从门店ERP上传到PCM
	 * 
	 * @Methods Name getErpProductFromEFuture
	 * @Create In 2015年7月13日 By zhangxy
	 * @param list
	 * @return
	 */
	public PublishDTO saveErpProductFromEFuture(GetErpProductFromEfutureDto dto)
			throws BleException;

	/**
	 * 从门店ERP上传到PCM
	 * 
	 * @Methods Name getErpProductFromEFuture2
	 * @Create In 2015年7月13日 By zhangxy
	 * @param list
	 * @return
	 */
	public PublishDTO saveErpProductFromEFuture2(GetErpProductFromEfutureDto dto)
			throws BleException;

	/**
	 * ERP商品换品牌、专柜、供应商（添加单据）
	 *
	 * @Methods Name modifyErpChangeFromEFutureForBill
	 * @Create In 2016年2月25日 By wangxuan
	 * @param dto
	 */
	Integer modifyErpChangeFromEFutureForBill(ErpChangeDto dto) throws BleException;

	/**
	 * ERP商品换品牌、专柜、供应商(查询当天需要执行的单据)
	 *
	 * @Methods Name modifyErpChangeFromEFuture
	 * @Create In 2016年2月26日 By wangxuan
	 */
	List<ErpChangeDto> modifyErpChangeFromEFutureActive() throws BleException;

	/**
	 * ERP商品换品牌、专柜、供应商
	 * 
	 * @Methods Name modifyErpChangeFromEFuture
	 * @Create In 2015年7月16日 By zhangxy
	 * @param dto
	 */
	public ModifyErpProductDto modifyErpChangeFromEFuture(ErpChangeDto dto) throws BleException;

	/**
	 * ERP商品下发
	 * 
	 * @Methods Name selectErpProduct
	 * @Create In 2015年7月16日 By zhangxy
	 * @return List<Map<String,Object>>
	 * @throws Exception
	 */
	public Page<ErpProductDto> selectErpProduct(Map<String, Object> paramMap) throws BleException;

	public List<Map<String, Object>> pushErpProduct(List<ErpProductDto> dto);

	/**
	 * ERP商品基本信息修改-扣率码修改时修改对应专柜商品信息
	 * 
	 * @Methods Name modifyShoppeProduct
	 * @Create In 2015年9月21日 By zhangxy
	 * @param entity
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public Boolean modifyShoppeProduct(PcmShoppeProduct entity, ErpChangeDto dto)
			throws BleException;

	/**
	 * ERP商品查询
	 * 
	 * @Methods Name selectErpProductPage
	 * @Create In 2015年7月16日 By zhangxy
	 * @return List<Map<String,Object>>
	 * @throws Exception
	 */
	public Page<ErpProPageDto> selectErpProductPage(Map<String, Object> paramMap);

	/**
	 * 根据ERP编码查询详细信息
	 * 
	 * @Methods Name selectErpProductByProCode
	 * @Create In 2016年1月25日 By kongqf
	 * @param productCode
	 * @return PcmErpProduct
	 */
	public PcmErpProduct selectErpProductByProCode(String productCode, String storeCode);

	/**
	 * 根据供应商门店查询专柜列表（扣率码信息）
	 * 
	 * @Methods Name GetShoppeInfoAndErp
	 * @Create In 2016-3-18 By wangc
	 * @param para
	 * @return GetSupAndErpInfoDto
	 */
	public GetSupAndErpInfoDto getShoppeInfoAndErp(Map<String, String> para) throws Exception;
}

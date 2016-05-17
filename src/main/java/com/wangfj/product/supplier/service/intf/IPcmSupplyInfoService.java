package com.wangfj.product.supplier.service.intf;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.supplier.domain.entity.PcmSupplyInfo;
import com.wangfj.product.supplier.domain.vo.GetSupCodeResultDto;
import com.wangfj.product.supplier.domain.vo.PcmPushSupplyInfoDto;
import com.wangfj.product.supplier.domain.vo.PcmSupInfoForSupResultDto;
import com.wangfj.product.supplier.domain.vo.PcmSupplyInfoDto;
import com.wangfj.product.supplier.domain.vo.PcmSupplyInfoPartDto;
import com.wangfj.product.supplier.domain.vo.PcmSupplyInfoQueryDto;
import com.wangfj.product.supplier.domain.vo.PcmSupplyInfoResultDto;

public interface IPcmSupplyInfoService {

	/**
	 * 供应商修改
	 * 
	 * @Methods Name updateSupplyInfo
	 * @Create In 2015年7月30日 By wangxiang
	 * @param updateSupplyInfo
	 * @return Integer
	 */
	Integer updateSupplyInfo(PcmSupplyInfo updateSupplyInfo);

	/**
	 * 添加单个供应商
	 * 
	 * @Methods Name addSupplyInfo
	 * @Create In 2015-8-8 By wangx
	 * @param pcmSupplyInfo
	 * @return Integer
	 */
	Integer addSupplyInfo(PcmSupplyInfo pcmSupplyInfo);

	/**
	 * 供应商信息删除(状态的变更)
	 * 
	 * @Methods Name updateSupplyInfoStatus
	 * @Create In 2015-8-10 By wangx
	 * @param paramMap
	 * @return Integer
	 */
	Integer updateSupplyInfoStatus(Map<String, Object> paramMap);

	/**
	 * 供应商查询分页（非模糊）
	 * 
	 * @Methods Name findPageSullyInfo
	 * @Create In 2015-8-14 By wangx
	 * @param paramMap
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 *             Page<PcmSupplyInfoDto>
	 */
	Page<PcmSupplyInfoDto> findPageSullyInfo(Map<String, Object> paramMap)
			throws IllegalAccessException, InvocationTargetException;

	/**
	 * 分页查询供应商(模糊)
	 * 
	 * @Methods Name findPageSullyInfoFuzzy
	 * @Create In 2015-8-14 By wangx
	 * @param paramMap
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 *             Page<PcmSupplyInfoDto>
	 */
	Page<PcmSupplyInfoDto> findPageSullyInfoFuzzy(Map<String, Object> paramMap)
			throws IllegalAccessException, InvocationTargetException;

	/**
	 * 供应商信息查询（非分页,非模糊）
	 * 
	 * @Methods Name findListSullyInfo
	 * @Create In 2015-8-16 By wangx
	 * @param paramMap
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 *             List<PcmSupplyInfoDto>
	 */
	List<PcmSupplyInfoDto> findListSullyInfo(Map<String, Object> paramMap)
			throws IllegalAccessException, InvocationTargetException;

	/**
	 * 查询供应商（条件可以加门店的sid）
	 * 
	 * @Methods Name findListSullyInfoFuzzy
	 * @Create In 2015-8-21 By wangxuan
	 * @param map
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 *             List<PcmSupplyInfoDto>
	 */
	List<PcmSupplyInfoDto> findListSullyInfoFuzzy(Map<String, Object> map)
			throws IllegalAccessException, InvocationTargetException;

	/**
	 * 根据编码修改信息 供应商上传
	 * 
	 * @Methods Name updateSupplyInfoBySupplyCode
	 * @Create In 2015-8-26 By wangxuan
	 * @param pcmSupplyInfo
	 * @return Integer
	 */
	Integer updateSupplyInfoBySupplyCode(PcmSupplyInfo pcmSupplyInfo);

	/**
	 * 供应商信息下发
	 * 
	 * @Methods Name pushPageSullyInfo
	 * @Create In 2015-8-31 By wangxuan
	 * @param paramMap
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 *             List<PcmPushSupplyInfoDto>
	 */
	List<PcmPushSupplyInfoDto> pushPageSullyInfo(Map<String, Object> paramMap)
			throws IllegalAccessException, InvocationTargetException;

	/**
	 * 多条件查询(模糊)
	 * 
	 * @Methods Name findListSupplyInfo
	 * @Create In 2015-9-8 By wangxuan
	 * @param paramMap
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 *             List<PcmSupplyInfoDto>
	 */
	List<PcmSupplyInfoDto> findListSupplyInfo(Map<String, Object> paramMap)
			throws IllegalAccessException, InvocationTargetException;

	/**
	 * 供应商信息下发（增量）
	 * 
	 * @Methods Name pushSupplyInfo
	 * @Create In 2015-9-9 By wangxuan
	 * @param paramMap
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 *             List<PcmPushSupplyInfoDto>
	 */
	List<PcmPushSupplyInfoDto> pushSupplyInfo(Map<String, Object> paramMap)
			throws IllegalAccessException, InvocationTargetException;

	/**
	 * 电商上传供应商
	 * 
	 * @Methods Name uploadSupplierFromEBusiness
	 * @Create In 2015-12-1 By wangxuan
	 * @param supplyInfo
	 * @return Map<String, Object>
	 */
	Map<String, Object> uploadSupplierFromEBusiness(PcmSupplyInfo supplyInfo);

	/**
	 * 门店上传供应商
	 *
	 * @Methods Name uploadSupplierFromEFutureERP
	 * @Create In 2016-2-18 By wangxuan
	 * @param supplyInfo
	 * @return Map<String, Object>
	 */
	Map<String, Object> uploadSupplierFromEFutureERP(PcmSupplyInfo supplyInfo);

	/**
	 * 查询供应商(与组织机构连表)
	 * 
	 * @Methods Name findListSupplier
	 * @Create In 2015-12-8 By wangxuan
	 * @param dto
	 * @return List<PcmSupplyInfoPartDto>
	 */
	List<PcmSupplyInfoPartDto> findListSupplier(PcmSupplyInfoQueryDto dto);

	/**
	 * 查询专柜的供应商信息
	 * 
	 * @Methods Name findListSupplierByShoppeParam
	 * @Create In 2015-12-11 By wangxuan
	 * @param dto
	 * @return List<PcmSupplyInfoResultDto>
	 */
	List<PcmSupplyInfoResultDto> findListSupplierByShoppeParam(PcmSupplyInfoQueryDto dto);

	/**
	 * 根据门店编码及管理分类编码列表获取供应商信息
	 * @Methods Name getSupInfoFromPcmByShopCodesAndManagerCodes
	 * @Create In 2016-4-12 By wangc
	 * @param para
	 * @return List<GetSupCodeResultDto>
	 */
	List<GetSupCodeResultDto> getSupInfoFromPcmByShopCodesAndManagerCodes(Map<String,Object> para);
	/**
	 * 根据营业执照号及税号获取供应商信息
	 * @Methods Name getSupInfoByLicenseNoAndTaxNo
	 * @Create In 2016-4-13 By wangc
	 * @param para
	 * @return List<PcmSupInfoForSupResultDto>
	 */
	List<PcmSupInfoForSupResultDto> getSupInfoByLicenseNoAndTaxNo(Map<String,String> para);
}

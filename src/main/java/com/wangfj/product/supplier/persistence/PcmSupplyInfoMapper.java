package com.wangfj.product.supplier.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.supplier.domain.entity.PcmSupplyInfo;
import com.wangfj.product.supplier.domain.vo.GetSupCodeResultDto;
import com.wangfj.product.supplier.domain.vo.PcmSupInfoForSupDto;
import com.wangfj.product.supplier.domain.vo.PcmSupInfoForSupResultDto;
import com.wangfj.product.supplier.domain.vo.PcmSupplyDto;
import com.wangfj.product.supplier.domain.vo.PcmSupplyInfoPartDto;
import com.wangfj.product.supplier.domain.vo.PcmSupplyInfoQueryDto;
import com.wangfj.product.supplier.domain.vo.PcmSupplyInfoResultDto;

public interface PcmSupplyInfoMapper extends BaseMapper<PcmSupplyInfo> {
	int deleteByPrimaryKey(Long sid);

	Integer insert(PcmSupplyInfo record);

	int insertSelective(PcmSupplyInfo record);

	PcmSupplyInfo selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmSupplyInfo record);

	int updateByPrimaryKey(PcmSupplyInfo record);

	List<PcmSupplyDto> selectBySupply(Map<String, Object> map);

	List<HashMap<String, Object>> selectBySupplys(Map<String, Object> map);

	Integer selectCountBySupply(Map<String, Object> map);

	/**
	 * 分页查询
	 */
	List<PcmSupplyInfo> selectPageListByParam(Map<String, Object> map);

	/**
	 * 模糊查询
	 */
	List<PcmSupplyInfo> indistinctSelect(Map<String, Object> map);

	/**
	 * 分页查询总数量(模糊)
	 * 
	 * @Methods Name getPageCountByPara
	 * @Create In 2015-8-14 By wangx
	 * @param paramMap
	 * @return Integer
	 */
	Integer getPageCountByPara(Map<String, Object> paramMap);

	/**
	 * 分页查询(模糊)
	 * 
	 * @Methods Name selectPageByPara
	 * @Create In 2015-8-14 By wangx
	 * @param paramMap
	 * @return List<PcmSupplyInfo>
	 */
	List<PcmSupplyInfo> selectPageByPara(Map<String, Object> paramMap);

	/**
	 * 多条件查询
	 * 
	 * @Methods Name selectListByParam
	 * @Create In 2015-8-25 By wangx
	 * @param paramMap
	 * @return List<PcmSupplyInfo>
	 */
	List<PcmSupplyInfo> selectListByParam(Map<String, Object> paramMap);

	/**
	 * 多条件查询(模糊)
	 * 
	 * @Methods Name selectListByParamFuzzy
	 * @Create In 2015-9-8 By wangxuan
	 * @param paramMap
	 * @return List<PcmSupplyInfo>
	 */
	List<PcmSupplyInfo> selectListByParamFuzzy(Map<String, Object> paramMap);

	/**
	 * 根据编码修改信息 供应商上传
	 * 
	 * @Methods Name updateBySupplyCodeSelective
	 * @Create In 2015-8-26 By wangxuan
	 * @param supplyInfo
	 * @return int
	 */
	int updateBySupplyCodeSelective(PcmSupplyInfo supplyInfo);

	/**
	 * 一品多供应商关系上传 查询供应商信息
	 * 
	 * @Methods Name selectSupplyByShopCodeAndSupplyCode
	 * @Create In 2015-8-28 By wangxuan
	 * @param paramMap
	 * @return List<PcmSupplyInfo>
	 */
	List<PcmSupplyInfo> selectSupplyByShopCodeAndSupplyCode(Map<String, Object> paramMap);

	/**
	 * 导入终端
	 * 
	 * @Methods Name selectListByParamForData
	 * @Create In 2015-8-31 By wangxuan
	 * @param paramMap
	 * @return List<PcmSupplyInfo>
	 */
	List<PcmSupplyInfo> selectListByParamForData(Map<String, Object> paramMap);

	/**
	 * 移动工作台调用主数据获取供应商信息
	 * 
	 * @Methods Name selectListByParamForPad
	 * @Create In 2015-8-31 By wangxuan
	 * @param para
	 * @return List<PcmSupplyInfo>
	 */
	List<PcmSupplyInfo> selectListByParamForPad(Map<String, Object> para);

	/**
	 * 供应商信息下发(全量)
	 * 
	 * @Methods Name pushPageSupplyInfo
	 * @Create In 2015-8-31 By wangxuan
	 * @param paramMap
	 * @return List<PcmSupplyInfo>
	 */
	List<PcmSupplyInfo> pushPageSupplyInfo(Map<String, Object> paramMap);

	/**
	 * 供应商信息下发(增量)
	 * 
	 * @Methods Name pushSupplyInfo
	 * @Create In 2015-9-9 By wangxuan
	 * @param paramMap
	 * @return List<PcmSupplyInfo>
	 */
	List<PcmSupplyInfo> pushSupplyInfo(Map<String, Object> paramMap);

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

	 /*
	  * 根据门店编码及管理分类编码获取供应商信息
	  */
	 List<GetSupCodeResultDto> getSupCodeFromPcmByShopCode(Map<String,Object> para);
	 /**
	  * 根据营业执照号及税号获取供应商信息
	  * @Methods Name getSupInfoFromPcmByLicenseNoAndTaxNo
	  * @Create In 2016-4-13 By wangc
	  * @param para
	  * @return List<PcmSupInfoForSupResultDto>
	  */
	 List<PcmSupInfoForSupResultDto> getSupInfoFromPcmByLicenseNoAndTaxNo(Map<String,String> para);
	 /**
	  * 根据营业执照号及税号获取供应商信息返回结果修改
	  * @Methods Name getSupInfoFromPcmByLicenseNoAndTaxNoT
	  * @Create In 2016年6月24日 By wangc
	  * @param para
	  * @return List<PcmSupInfoForSupResultDto>
	  */
	 List<PcmSupInfoForSupDto> getSupInfoFromPcmByLicenseNoAndTaxNoT(Map<String,String> para);
}
package com.wangfj.product.price.persistence;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.price.domain.entity.PcmPaymentType;
import com.wangfj.product.price.domain.vo.PcmPaymentInfoDto;
import com.wangfj.product.price.domain.vo.PcmPaymentOrganDto;
import com.wangfj.product.price.domain.vo.PcmPushPaymentToERPDto;
import com.wangfj.product.price.domain.vo.PcmShopPaymentInfoDto;
import com.wangfj.product.price.domain.vo.SelectPaymentDto;
import com.wangfj.product.price.domain.vo.SelectPaymentTypeDto;

public interface PcmPaymentTypeMapper extends BaseMapper<PcmPaymentType> {

	List<PcmPaymentType> selectListByParam(Map<String, Object> paramMap);

	/**
	 * 根据门店信息查询门店与支付方式的关系表
	 * 
	 * @Methods Name selectShopPaymentTypeListByParam
	 * @Create In 2015年8月7日 By kongqf
	 * @param selectPaymentDto
	 * @return List<PcmPaymentOrganDto>
	 */
	List<PcmPaymentOrganDto> selectShopPaymentTypeListByParam(SelectPaymentDto selectPaymentDto);

	/**
	 * 根据条件查询支付方式
	 * 
	 * @Methods Name selectPaymentTypeListByParam
	 * @Create In 2015年8月8日 By kongqf
	 * @param selectPaymentTypeDto
	 * @return List<PcmPaymentInfoDto>
	 */
	List<PcmPaymentInfoDto> selectPaymentTypeListByParam(SelectPaymentTypeDto selectPaymentTypeDto);

	/**
	 * 根据条件查询支付方式 总数
	 * 
	 * @Methods Name selectPaymentTypeListCount
	 * @Create In 2015年9月10日 By kongqf
	 * @param selectPaymentTypeDto
	 * @return Integer
	 */
	Integer selectPaymentTypeListCount(SelectPaymentTypeDto selectPaymentTypeDto);

	/**
	 * 根据门店信息查询门店所关联的支付方式列表（分页）
	 * 
	 * @Methods Name selectPaymentTypeListByShopSid
	 * @Create In 2015年8月8日 By kongqf
	 * @param selectPaymentTypeDto
	 * @return List<PcmPaymentInfoDto>
	 */
	List<PcmShopPaymentInfoDto> selectPaymentTypeListByShopSid(
			SelectPaymentTypeDto selectPaymentTypeDto);

	/**
	 * 查询门店未添加的支付方式
	 * 
	 * @Methods Name selecNottPaymentTypeListByShopSid
	 * @Create In 2015年9月10日 By kongqf
	 * @param selectPaymentTypeDto
	 * @return List<PcmPaymentInfoDto>
	 */
	List<PcmPaymentInfoDto> selecNotPaymentTypeListByShopSid(
			SelectPaymentTypeDto selectPaymentTypeDto);

	/**
	 * 查询门店未添加的支付方式总数
	 * 
	 * @Methods Name selecNotPaymentTypeListByShopSidCount
	 * @Create In 2015年9月10日 By kongqf
	 * @param selectPaymentTypeDto
	 * @return Integer
	 */
	Integer selecNotPaymentTypeListByShopSidCount(SelectPaymentTypeDto selectPaymentTypeDto);

	/**
	 * 根据查询条件查询总行数
	 * 
	 * @Methods Name selectPaymentTypeCount
	 * @Create In 2015年8月8日 By kongqf
	 * @param selectPaymentTypeDto
	 * @return int
	 */
	Integer selectPaymentTypeCount(SelectPaymentTypeDto selectPaymentTypeDto);

	/**
	 * 查询门店下支付方式总数
	 * 
	 * @Methods Name selectPaymentTypeByShopSidCount
	 * @Create In 2015年8月8日 By kongqf
	 * @param selectPaymentTypeDto
	 * @return Integer
	 */
	Integer selectPaymentTypeByShopSidCount(SelectPaymentTypeDto selectPaymentTypeDto);

	/**
	 * 新增支付方式
	 * 
	 * @Methods Name insertSelective
	 * @Create In 2015年8月8日 By kongqf
	 * @param pcmPaymentType
	 * @return Integer
	 */
	Integer insertSelective(PcmPaymentType pcmPaymentType);

	/**
	 * 修改支付方式
	 * 
	 * @Methods Name updateByPrimaryKeySelective
	 * @Create In 2015年8月8日 By kongqf
	 * @param pcmPaymentType
	 * @return Integer
	 */
	Integer updateByPrimaryKeySelective(PcmPaymentType pcmPaymentType);

	/**
	 * 修改支付方式名称
	 * 
	 * @Methods Name updatePayNameBySid
	 * @Create In 2015年10月8日 By kongqf
	 * @param pcmPaymentType
	 * @return Integer
	 */
	Integer updatePayNameBySid(PcmPaymentType pcmPaymentType);

	Integer selectShopPaymentTypeListCount(SelectPaymentDto selectPaymentDto);

	/**
	 * 根据门店查询一级支付方式
	 * 
	 * @Methods Name selec1PaymentTypeListByShopSid
	 * @Create In 2015年9月28日 By kongqf
	 * @param dto
	 * @return List<PcmPaymentInfoDto>
	 */
	List<PcmPaymentInfoDto> selec1PaymentTypeListByShopSid(SelectPaymentTypeDto dto);

	/**
	 * 根据门店查询一级支付方式总数
	 * 
	 * @Methods Name selec1PaymentTypeListByShopSid
	 * @Create In 2015年9月28日 By kongqf
	 * @param dto
	 * @return List<PcmPaymentInfoDto>
	 */
	Integer selec1PaymentTypeCountByShopSid(SelectPaymentTypeDto dto);

	/**
	 * 根据门店查询二级支付方式
	 * 
	 * @Methods Name selec2PaymentTypeListByShopSid
	 * @Create In 2015年9月28日 By kongqf
	 * @param dto
	 * @return List<PcmPaymentInfoDto>
	 */
	List<PcmPaymentInfoDto> selec2PaymentTypeListByShopSid(SelectPaymentTypeDto dto);

	/**
	 * 根据门店查询二级支付方式总数
	 * 
	 * @Methods Name selec2PaymentTypeCountByShopSid
	 * @Create In 2015年9月28日 By kongqf
	 * @param dto
	 * @return List<PcmPaymentInfoDto>
	 */
	Integer selec2PaymentTypeCountByShopSid(SelectPaymentTypeDto dto);

	/**
	 * 查询需要下发支付方式的数据（修改支付方式）（增量）
	 * 
	 * @Methods Name selectPushPaymentByPaycode
	 * @Create In 2015-10-9 By wangxuan
	 * @param para
	 * @return List<PcmPushPaymentToERPDto>
	 */
	List<PcmPushPaymentToERPDto> selectPushPaymentByPaycode(Map<String, Object> para);

	/**
	 * 查询下发支付方式的数据
	 * 
	 * @Methods Name pushPaymentTypeData
	 * @Create In 2015-10-28 By wangxuan
	 * @param paramMap
	 * @return List<Map<String,Object>>
	 */
	List<Map<String, Object>> pushPaymentTypeData(Map<String, Object> paramMap);

}
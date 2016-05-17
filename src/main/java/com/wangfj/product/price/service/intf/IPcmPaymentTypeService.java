package com.wangfj.product.price.service.intf;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.price.domain.vo.PcmPaymentInfoDto;
import com.wangfj.product.price.domain.vo.PcmPaymentOrganDto;
import com.wangfj.product.price.domain.vo.PcmPaymentOrganInfoDto;
import com.wangfj.product.price.domain.vo.PcmPushPaymentToERPDto;
import com.wangfj.product.price.domain.vo.PcmShopPaymentInfoDto;
import com.wangfj.product.price.domain.vo.SelectPaymentDto;
import com.wangfj.product.price.domain.vo.SelectPaymentTypeDto;

/**
 * 支付方式管理
 * 
 * @Class Name IPcmPaymentTypeService
 * @Author kongqf
 * @Create In 2015年8月7日
 */
public interface IPcmPaymentTypeService {

	/**
	 * 根据门店信息查询门店与支付方式的关系表
	 * 
	 * @Methods Name selectPaymentTypeList
	 * @Create In 2015年8月7日 By kongqf
	 * @param selectPaymentDto
	 * @return List<PcmPaymentOrganDto>
	 */
	public Page<PcmPaymentOrganDto> selectShopPaymentTypeList(SelectPaymentDto selectPaymentDto);

	/**
	 * 根据条件查询支付方式
	 * 
	 * @Methods Name selectPaymentTypeListByParam
	 * @Create In 2015年8月8日 By kongqf
	 * @param selectPaymentTypeDto
	 * @return List<PcmPaymentInfoDto>
	 */
	public Page<PcmPaymentInfoDto> selectPaymentTypeListByParam(
			SelectPaymentTypeDto selectPaymentTypeDto);

	/**
	 * 查询门店未添加的支付方式
	 * 
	 * @Methods Name selecNotPaymentTypeListByShopSid
	 * @Create In 2015年9月10日 By kongqf
	 * @param selectPaymentTypeDto
	 * @return Page<PcmPaymentInfoDto>
	 */
	public Page<PcmPaymentInfoDto> selecNotPaymentTypeListByShopSid(
			SelectPaymentTypeDto selectPaymentTypeDto);

	/**
	 * 根据门店信息查询门店所关联的支付方式列表（分页）
	 * 
	 * @Methods Name selectPaymentTypeListByShopSid
	 * @Create In 2015年8月8日 By kongqf
	 * @param selectPaymentTypeDto
	 * @return Page<PcmShopPaymentInfoDto>
	 */
	public Page<PcmShopPaymentInfoDto> selectPaymentTypeListByShopSid(
			SelectPaymentTypeDto selectPaymentTypeDto);

	/**
	 * 根据查询条件查询总行数
	 * 
	 * @Methods Name selectPaymentTypeCount
	 * @Create In 2015年8月8日 By kongqf
	 * @param selectPaymentTypeDto
	 * @return int
	 */
	public Integer selectPaymentTypeCount(SelectPaymentTypeDto selectPaymentTypeDto);

	/**
	 * 查询门店下支付方式总数
	 * 
	 * @Methods Name selectPaymentTypeByShopSidCount
	 * @Create In 2015年8月8日 By kongqf
	 * @param selectPaymentTypeDto
	 * @return Integer
	 */
	public Integer selectPaymentTypeByShopSidCount(SelectPaymentTypeDto selectPaymentTypeDto);

	/**
	 * 新增支付方式
	 * 
	 * @Methods Name savePcmPaymentType
	 * @Create In 2015年8月8日 By kongqf
	 * @param pcmPaymentInfoDto
	 * @return boolean
	 */
	boolean savePcmPaymentType(PcmPaymentInfoDto pcmPaymentInfoDto);

	/**
	 * 删除支付方式
	 * 
	 * @Methods Name delPcmPaymentType
	 * @Create In 2015年8月8日 By kongqf
	 * @param pcmPaymentInfoDto
	 * @return boolean
	 */
	boolean delPcmPaymentType(PcmPaymentInfoDto pcmPaymentInfoDto);

	/**
	 * 修改支付方式名称
	 * 
	 * @Methods Name updatePcmPaymentType
	 * @Create In 2015年10月8日 By kongqf
	 * @param pcmPaymentInfoDto
	 * @return boolean
	 */
	public boolean updatePcmPaymentType(PcmPaymentInfoDto pcmPaymentInfoDto);

	/**
	 * 门店添加支付方式
	 * 
	 * @Methods Name savePcmPaymentOrgan
	 * @Create In 2015年8月8日 By kongqf
	 * @param pcmPaymentOrganInfoDtoList
	 * @return String
	 */
	String savePcmPaymentOrgan(List<PcmPaymentOrganInfoDto> pcmPaymentOrganInfoDtoList);

	/**
	 * 门店删除支付方式
	 * 
	 * @Methods Name delPcmPaymentOrgan
	 * @Create In 2015年8月10日 By kongqf
	 * @param pcmPaymentOrganInfoDto
	 * @return boolean
	 */
	boolean delPcmPaymentOrgan(PcmPaymentOrganInfoDto pcmPaymentOrganInfoDto);

	/**
	 * 根据门店查询一级支付方式
	 * 
	 * @Methods Name select1PaymentTypeList
	 * @Create In 2015年9月28日 By kongqf
	 * @param selectPaymentDto
	 * @return Page<PcmPaymentInfoDto>
	 */
	public Page<PcmPaymentInfoDto> select1PaymentTypeList(SelectPaymentTypeDto selectPaymentDto);

	/**
	 * 根据门店查询二级支付方式
	 * 
	 * @Methods Name select2PaymentTypeList
	 * @Create In 2015年9月28日 By kongqf
	 * @param selectPaymentDto
	 * @return Page<PcmPaymentInfoDto>
	 */
	public Page<PcmPaymentInfoDto> select2PaymentTypeList(SelectPaymentTypeDto selectPaymentDto);

	/**
	 * 查询需要下发支付方式的数据（修改支付方式）（增量）
	 * 
	 * @Methods Name selectPushPaymentByPaycode
	 * @Create In 2015-10-9 By wangxuan
	 * @param paraMap
	 * @return List<PcmPushPaymentToERPDto>
	 */
	public List<PcmPushPaymentToERPDto> selectPushPaymentByPaycode(Map<String, Object> paraMap);

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

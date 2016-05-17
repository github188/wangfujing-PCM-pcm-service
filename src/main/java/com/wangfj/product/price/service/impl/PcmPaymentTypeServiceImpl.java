package com.wangfj.product.price.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.DateUtil;
import com.wangfj.core.utils.StringUtils;
import com.wangfj.product.price.domain.entity.PcmPaymentOrgan;
import com.wangfj.product.price.domain.entity.PcmPaymentType;
import com.wangfj.product.price.domain.vo.PcmPaymentInfoDto;
import com.wangfj.product.price.domain.vo.PcmPaymentOrganDto;
import com.wangfj.product.price.domain.vo.PcmPaymentOrganInfoDto;
import com.wangfj.product.price.domain.vo.PcmPushPaymentToERPDto;
import com.wangfj.product.price.domain.vo.PcmShopPaymentInfoDto;
import com.wangfj.product.price.domain.vo.SelectPaymentDto;
import com.wangfj.product.price.domain.vo.SelectPaymentTypeDto;
import com.wangfj.product.price.persistence.PcmPaymentOrganMapper;
import com.wangfj.product.price.persistence.PcmPaymentTypeMapper;
import com.wangfj.product.price.service.intf.IPcmPaymentTypeService;
import com.wangfj.util.Constants;

/**
 * 支付方式
 * 
 * @Class Name PcmPaymentTypeServiceImpl
 * @Author kongqf
 * @Create In 2015年8月7日
 */
@Service
public class PcmPaymentTypeServiceImpl implements IPcmPaymentTypeService {
	private static final Logger logger = LoggerFactory.getLogger(PcmPaymentTypeServiceImpl.class);

	@Autowired
	private PcmPaymentTypeMapper pcmPaymentTypeMapper;
	@Autowired
	private PcmPaymentOrganMapper pcmPaymentOrganMapper;

	/**
	 * 根据门店信息查询门店与支付方式的关系表
	 * 
	 * @Methods Name selectPaymentTypeList
	 * @Create In 2015年8月7日 By kongqf
	 * @param selectPaymentDto
	 * @return List<PcmPaymentOrganDto>
	 */
	@Override
	public Page<PcmPaymentOrganDto> selectShopPaymentTypeList(SelectPaymentDto selectPaymentDto) {
		logger.info("start selectShopPaymentTypeList(),param:" + selectPaymentDto.toString());
		List<PcmPaymentOrganDto> pcmPaymentOrganDtoList = new ArrayList<PcmPaymentOrganDto>();
		Page<PcmPaymentOrganDto> pageDto = new Page<PcmPaymentOrganDto>();
		int count = pcmPaymentTypeMapper.selectShopPaymentTypeListCount(selectPaymentDto);
		if (count != 0) {
			pcmPaymentOrganDtoList = pcmPaymentTypeMapper
					.selectShopPaymentTypeListByParam(selectPaymentDto);
		}
		pageDto.setList(pcmPaymentOrganDtoList);
		pageDto.setCurrentPage(selectPaymentDto.getCurrentPage());
		pageDto.setPageSize(selectPaymentDto.getPageSize());
		pageDto.setCount(count);
		logger.info("selectShopPaymentTypeList(),result:" + pcmPaymentOrganDtoList.toString());
		return pageDto;
	}

	/**
	 * 根据门店查询一级支付方式
	 * 
	 * @Methods Name select1PaymentTypeList
	 * @Create In 2015年9月28日 By kongqf
	 * @param selectPaymentDto
	 * @return Page<PcmPaymentInfoDto>
	 */
	@Override
	public Page<PcmPaymentInfoDto> select1PaymentTypeList(SelectPaymentTypeDto selectPaymentDto) {
		logger.info("start select1PaymentTypeList(),param:" + selectPaymentDto.toString());
		List<PcmPaymentInfoDto> pcmPaymentOrganDtoList = new ArrayList<PcmPaymentInfoDto>();
		Page<PcmPaymentInfoDto> pageDto = new Page<PcmPaymentInfoDto>();
		Integer count = pcmPaymentTypeMapper.selec1PaymentTypeCountByShopSid(selectPaymentDto);
		if (count != 0) {
			pcmPaymentOrganDtoList = pcmPaymentTypeMapper
					.selec1PaymentTypeListByShopSid(selectPaymentDto);
		}
		pageDto.setList(pcmPaymentOrganDtoList);
		pageDto.setCurrentPage(selectPaymentDto.getCurrentPage());
		pageDto.setPageSize(selectPaymentDto.getPageSize());
		pageDto.setCount(count);
		logger.info("select1PaymentTypeList(),result:" + pcmPaymentOrganDtoList.toString());
		return pageDto;
	}

	/**
	 * 根据门店查询二级级支付方式
	 * 
	 * @Methods Name select2PaymentTypeList
	 * @Create In 2015年9月28日 By kongqf
	 * @param selectPaymentDto
	 * @return Page<PcmPaymentInfoDto>
	 */
	@Override
	public Page<PcmPaymentInfoDto> select2PaymentTypeList(SelectPaymentTypeDto selectPaymentDto) {
		logger.info("start select2PaymentTypeList(),param:" + selectPaymentDto.toString());
		List<PcmPaymentInfoDto> pcmPaymentOrganDtoList = new ArrayList<PcmPaymentInfoDto>();
		Page<PcmPaymentInfoDto> pageDto = new Page<PcmPaymentInfoDto>();
		Integer count = pcmPaymentTypeMapper.selec2PaymentTypeCountByShopSid(selectPaymentDto);
		if (count != 0) {
			pcmPaymentOrganDtoList = pcmPaymentTypeMapper
					.selec2PaymentTypeListByShopSid(selectPaymentDto);
		}
		pageDto.setList(pcmPaymentOrganDtoList);
		pageDto.setCurrentPage(selectPaymentDto.getCurrentPage());
		pageDto.setPageSize(selectPaymentDto.getPageSize());
		pageDto.setCount(count);
		logger.info("select2PaymentTypeList(),result:" + pcmPaymentOrganDtoList.toString());
		return pageDto;
	}

	/**
	 * 查询支付方式 （分页）
	 * 
	 * @Methods Name selectPaymentTypeListByParam
	 * @Create In 2015年8月8日 By kongqf
	 * @param selectPaymentTypeDto
	 * @return List<PcmPaymentInfoDto>
	 */
	@Override
	public Page<PcmPaymentInfoDto> selectPaymentTypeListByParam(
			SelectPaymentTypeDto selectPaymentTypeDto) {
		logger.info("start selectPaymentTypeListByParam(),param:" + selectPaymentTypeDto.toString());
		// if (selectPaymentTypeDto.getParentCode() == null
		// || selectPaymentTypeDto.getParentCode().isEmpty()) {
		// selectPaymentTypeDto.setParentCode(Constants.DEFAULT_PARENT_CODE);
		// }

		List<PcmPaymentInfoDto> pcmPaymentInfoDtos = new ArrayList<PcmPaymentInfoDto>();
		Page<PcmPaymentInfoDto> PcmPaymentInfoDtoList = new Page<PcmPaymentInfoDto>();
		Integer count = pcmPaymentTypeMapper.selectPaymentTypeListCount(selectPaymentTypeDto);
		if (count != 0) {
			pcmPaymentInfoDtos = pcmPaymentTypeMapper
					.selectPaymentTypeListByParam(selectPaymentTypeDto);
		}
		PcmPaymentInfoDtoList.setList(pcmPaymentInfoDtos);
		PcmPaymentInfoDtoList.setCurrentPage(selectPaymentTypeDto.getCurrentPage());
		PcmPaymentInfoDtoList.setPageSize(selectPaymentTypeDto.getPageSize());
		PcmPaymentInfoDtoList.setCount(count);
		logger.info("selectPaymentTypeListByParam(),result:" + pcmPaymentInfoDtos.toString());

		return PcmPaymentInfoDtoList;
	}

	/**
	 * 根据查询条件查询总行数
	 * 
	 * @Methods Name selectPaymentTypeCount
	 * @Create In 2015年8月8日 By kongqf
	 * @param selectPaymentTypeDto
	 * @return int
	 */
	@Override
	public Integer selectPaymentTypeCount(SelectPaymentTypeDto selectPaymentTypeDto) {
		return pcmPaymentTypeMapper.selectPaymentTypeCount(selectPaymentTypeDto);
	}

	/**
	 * 根据门店信息查询门店所关联的支付方式列表（分页）
	 * 
	 * @Methods Name selectPaymentTypeListByShopSid
	 * @Create In 2015年8月8日 By kongqf
	 * @param selectPaymentTypeDto
	 * @return Page<PcmShopPaymentInfoDto>
	 */
	@Override
	public Page<PcmShopPaymentInfoDto> selectPaymentTypeListByShopSid(
			SelectPaymentTypeDto selectPaymentTypeDto) {
		logger.info("start selectPaymentTypeListByShopSid(),param:"
				+ selectPaymentTypeDto.toString());

		List<PcmShopPaymentInfoDto> pcmPaymentInfoDtos = new ArrayList<PcmShopPaymentInfoDto>();
		Page<PcmShopPaymentInfoDto> PcmPaymentInfoDtoList = new Page<PcmShopPaymentInfoDto>();
		Integer count = selectPaymentTypeByShopSidCount(selectPaymentTypeDto);
		if (count != 0) {
			pcmPaymentInfoDtos = pcmPaymentTypeMapper
					.selectPaymentTypeListByShopSid(selectPaymentTypeDto);
		}
		PcmPaymentInfoDtoList.setList(pcmPaymentInfoDtos);
		PcmPaymentInfoDtoList.setCurrentPage(selectPaymentTypeDto.getCurrentPage());
		PcmPaymentInfoDtoList.setPageSize(selectPaymentTypeDto.getPageSize());
		PcmPaymentInfoDtoList.setCount(count);
		logger.info("selectPaymentTypeListByParam(),result:" + pcmPaymentInfoDtos.toString());

		return PcmPaymentInfoDtoList;
	}

	/**
	 * 根据查询条件查询支付方式有效总行数
	 * 
	 * @Methods Name selectPaymentTypeByShopSidCount
	 * @Create In 2015年8月8日 By kongqf
	 * @param selectPaymentTypeDto
	 * @return int
	 */
	@Override
	public Integer selectPaymentTypeByShopSidCount(SelectPaymentTypeDto selectPaymentTypeDto) {
		return pcmPaymentTypeMapper.selectPaymentTypeByShopSidCount(selectPaymentTypeDto);
	}

	/**
	 * 新增支付方式
	 * 
	 * @Methods Name savePcmPaymentType
	 * @Create In 2015年8月8日 By kongqf
	 * @param pcmPaymentInfoDto
	 * @return boolean
	 */
	@Override
	public boolean savePcmPaymentType(PcmPaymentInfoDto pcmPaymentInfoDto) {
		logger.info("start savePcmPaymentType(),param:" + pcmPaymentInfoDto.toString());

		SelectPaymentTypeDto selectPaymentTypeDto = new SelectPaymentTypeDto();
		selectPaymentTypeDto.setPayCode(pcmPaymentInfoDto.getPayCode());
		int isPayCode = selectPaymentTypeCount(selectPaymentTypeDto);
		if (isPayCode != 0) {
			throw new BleException(ErrorCode.PAYMENT_PAYCODE_EXISTS.getErrorCode(),
					ErrorCode.PAYMENT_PAYCODE_EXISTS.getMemo());
		} else {
			selectPaymentTypeDto.setPayCode(null);
		}
		selectPaymentTypeDto.setName(pcmPaymentInfoDto.getName());
		int isPayName = selectPaymentTypeCount(selectPaymentTypeDto);
		if (isPayName != 0) {
			throw new BleException(ErrorCode.PAYMENT_PAYNAME_EXISTS.getErrorCode(),
					ErrorCode.PAYMENT_PAYNAME_EXISTS.getMemo());
		}
		boolean flag = false;
		PcmPaymentType pcmPaymentType = new PcmPaymentType();
		pcmPaymentType = getPcmPaymentTypeEntity(pcmPaymentInfoDto);
		int count = pcmPaymentTypeMapper.insertSelective(pcmPaymentType);
		if (1 == count) {
			flag = true;
		}
		logger.info("start savePcmPaymentType(),reuslt:" + flag);

		return flag;
	}

	/**
	 * 删除支付方式
	 * 
	 * @Methods Name delPcmPaymentType
	 * @Create In 2015年8月8日 By kongqf
	 * @param pcmPaymentInfoDto
	 * @return boolean
	 */
	@Override
	public boolean delPcmPaymentType(PcmPaymentInfoDto pcmPaymentInfoDto) {
		logger.info("start delPcmPaymentType(),param:" + pcmPaymentInfoDto.toString());

		boolean flag = false;
		boolean isDel = true;
		if (pcmPaymentInfoDto.getParentCode() == Constants.DEFAULT_PARENT_CODE) {
			SelectPaymentTypeDto selectPaymentTypeDto = new SelectPaymentTypeDto();
			int isZero = selectPaymentTypeCount(selectPaymentTypeDto);
			logger.info("start delPcmPaymentType(),isZero:" + isZero);
			if (0 != isZero) {
				isDel = false;
			}
		}
		if (isDel) {
			PcmPaymentType pcmPaymentType = new PcmPaymentType();
			pcmPaymentType = getPcmPaymentTypeEntity(pcmPaymentInfoDto);
			pcmPaymentType.setIfdel(Constants.PAYMENT_DEL_FLAG);
			int count = pcmPaymentTypeMapper.updateByPrimaryKeySelective(pcmPaymentType);
			if (1 == count) {
				flag = true;
			}
			logger.info("start delPcmPaymentType(),reuslt:" + flag);
		}
		return flag;
	}

	/**
	 * 修改支付方式名称
	 * 
	 * @Methods Name updatePcmPaymentType
	 * @Create In 2015年10月8日 By kongqf
	 * @param pcmPaymentInfoDto
	 * @return boolean
	 */
	@Override
	public boolean updatePcmPaymentType(PcmPaymentInfoDto pcmPaymentInfoDto) {
		logger.info("start updatePcmPaymentType(),param:" + pcmPaymentInfoDto.toString());

		boolean flag = false;

		Map<String, Object> paramMap = new HashMap<String, Object>();
		String sid = pcmPaymentInfoDto.getSid();
		if (StringUtils.isNotEmpty(sid)) {
			paramMap.clear();
			paramMap.put("sid", sid.trim());
			List<PcmPaymentType> list = pcmPaymentTypeMapper.selectListByParam(paramMap);
			if (list != null && list.size() == 1) {

				String name = pcmPaymentInfoDto.getName();
				if (StringUtils.isNotEmpty(name)) {
					if (!name.trim().equals(list.get(0).getName())) {
						paramMap.clear();
						paramMap.put("name", name);
						List<PcmPaymentType> nameList = pcmPaymentTypeMapper
								.selectListByParam(paramMap);
						if (nameList != null && nameList.size() > 0) {
							throw new BleException(ErrorCode.PAYMENT_PAYNAME_EXISTS.getErrorCode(),
									ErrorCode.PAYMENT_PAYNAME_EXISTS.getMemo());
						}
					}
				}

				PcmPaymentType pcmPaymentType = new PcmPaymentType();
				pcmPaymentType = getPcmPaymentTypeEntity(pcmPaymentInfoDto);
				int count = pcmPaymentTypeMapper.updateByPrimaryKeySelective(pcmPaymentType);
				if (1 == count) {
					flag = true;
				}
			}
		}

		logger.info("start updatePcmPaymentType(),reuslt:" + flag);

		return flag;
	}

	/**
	 * 门店添加支付方式
	 * 
	 * @Methods Name savePcmPaymentOrgan
	 * @Create In 2015年8月8日 By kongqf
	 * @param pcmPaymentOrganInfoDtoList
	 * @return String
	 */
	@Override
	public String savePcmPaymentOrgan(List<PcmPaymentOrganInfoDto> pcmPaymentOrganInfoDtoList) {
		logger.info("start savePcmPaymentOrgan(),Para:" + pcmPaymentOrganInfoDtoList.toString());
		StringBuilder sbMessage = new StringBuilder();
		SelectPaymentTypeDto searchDto = null;
		for (PcmPaymentOrganInfoDto pcmPaymentOrganInfoDto : pcmPaymentOrganInfoDtoList) {
			PcmPaymentOrgan pcmPaymentOrgan = new PcmPaymentOrgan();
			pcmPaymentOrgan = getPcmPaymentOrganEntity(pcmPaymentOrganInfoDto);
			searchDto = new SelectPaymentTypeDto();
			searchDto.setStoreCode(pcmPaymentOrganInfoDto.getShopSid());
			searchDto.setPayCode(pcmPaymentOrganInfoDto.getCode());
			Integer count = selectPaymentTypeByShopSidCount(searchDto);
			if (0 == count) {
				try {
					pcmPaymentOrgan.setStatus(Constants.PUBLIC_0);
					count = pcmPaymentOrganMapper.insertSelective(pcmPaymentOrgan);
					if (0 == count) {
						if (StringUtils.isNotBlank(sbMessage.toString())) {
							sbMessage.append(pcmPaymentOrganInfoDto.getCode());
						} else {
							sbMessage.append("," + pcmPaymentOrganInfoDto.getCode());
						}
					}
				} catch (Exception e) {
					throw new BleException(ErrorCode.PAYMENT_STORE_ADD_FAILED.getErrorCode(),
							ErrorCode.PAYMENT_STORE_ADD_FAILED.getMemo());
				}
			}
		}
		if (StringUtils.isNotBlank(sbMessage.toString())) {
			throw new BleException(ErrorCode.PAYMENT_STORE_ADD_FAILED.getErrorCode(),
					ErrorCode.PAYMENT_STORE_ADD_FAILED.getMemo() + ":" + sbMessage.toString());
		}
		logger.info("start savePcmPaymentOrgan(),reuslt:" + sbMessage);

		return sbMessage.toString();
	}

	/**
	 * 门店删除支付方式
	 * 
	 * @Methods Name delPcmPaymentOrgan
	 * @Create In 2015年8月10日 By kongqf
	 * @param pcmPaymentOrganInfoDto
	 * @return boolean
	 */
	@Override
	public boolean delPcmPaymentOrgan(PcmPaymentOrganInfoDto pcmPaymentOrganInfoDto) {
		boolean flag = false;
		PcmPaymentOrgan pcmPaymentOrgan = new PcmPaymentOrgan();
		pcmPaymentOrgan = getPcmPaymentOrganEntity(pcmPaymentOrganInfoDto);
		pcmPaymentOrgan.setStatus(Constants.PAYMENT_DEL_FLAG);
		int count = pcmPaymentOrganMapper.updateByPrimaryKeySelective(pcmPaymentOrgan);
		if (1 == count) {
			flag = true;
		}
		logger.info("start delPcmPaymentType(),reuslt:" + flag);

		return flag;
	}

	/**
	 * 获取支付方式实体类
	 * 
	 * @Methods Name getPcmPaymentTypeEntity
	 * @Create In 2015年8月8日 By kongqf
	 * @param pcmPaymentInfoDto
	 * @return PcmPaymentType
	 */
	private PcmPaymentType getPcmPaymentTypeEntity(PcmPaymentInfoDto pcmPaymentInfoDto) {
		PcmPaymentType pcmPaymentType = new PcmPaymentType();
		if (pcmPaymentInfoDto.getSid() != null) {
			pcmPaymentType.setSid(Long.parseLong(pcmPaymentInfoDto.getSid()));
		}
		pcmPaymentType.setCode(pcmPaymentInfoDto.getPayCode());
		pcmPaymentType.setName(pcmPaymentInfoDto.getName());
		pcmPaymentType.setParentCode(pcmPaymentInfoDto.getParentCode());
		pcmPaymentType.setBankBin(pcmPaymentInfoDto.getBankBIN());
		pcmPaymentType.setDealTime(pcmPaymentInfoDto.getDealTime());
		pcmPaymentType.setIsAllowInvoice(pcmPaymentInfoDto.getIsAllowInvoice());
		pcmPaymentType.setLastUpdBy(pcmPaymentInfoDto.getLastUpdBy());
		pcmPaymentType.setCreateBy(pcmPaymentInfoDto.getCreateBy());
		pcmPaymentType.setRemark(pcmPaymentInfoDto.getRemark());

		return pcmPaymentType;
	}

	/**
	 * 获取门店支付方式实体类
	 * 
	 * @Methods Name getPcmPaymentOrganEntity
	 * @Create In 2015年8月8日 By kongqf
	 * @param pcmPaymentOrganInfoDto
	 * @return PcmPaymentOrgan
	 */
	private PcmPaymentOrgan getPcmPaymentOrganEntity(PcmPaymentOrganInfoDto pcmPaymentOrganInfoDto) {
		PcmPaymentOrgan pcmPaymentOrgan = new PcmPaymentOrgan();
		if (pcmPaymentOrganInfoDto.getSid() != null) {
			pcmPaymentOrgan.setSid(Long.parseLong(pcmPaymentOrganInfoDto.getSid()));
		}
		pcmPaymentOrgan.setShopSid(pcmPaymentOrganInfoDto.getShopSid());
		pcmPaymentOrgan.setCode(pcmPaymentOrganInfoDto.getCode());
		pcmPaymentOrgan.setBankBin(pcmPaymentOrganInfoDto.getBankBin());
		return pcmPaymentOrgan;
	}

	/**
	 * 查询门店未添加的支付方式
	 */
	@Override
	public Page<PcmPaymentInfoDto> selecNotPaymentTypeListByShopSid(
			SelectPaymentTypeDto selectPaymentTypeDto) {
		logger.info("start selecNotPaymentTypeListByShopSid(),param:"
				+ selectPaymentTypeDto.toString());
		List<PcmPaymentInfoDto> pcmPaymentInfoDtos = new ArrayList<PcmPaymentInfoDto>();
		Page<PcmPaymentInfoDto> PcmPaymentInfoDtoList = new Page<PcmPaymentInfoDto>();
		Integer count = pcmPaymentTypeMapper
				.selecNotPaymentTypeListByShopSidCount(selectPaymentTypeDto);
		if (count != 0) {
			pcmPaymentInfoDtos = pcmPaymentTypeMapper
					.selecNotPaymentTypeListByShopSid(selectPaymentTypeDto);
		}
		PcmPaymentInfoDtoList.setList(pcmPaymentInfoDtos);
		PcmPaymentInfoDtoList.setCurrentPage(selectPaymentTypeDto.getCurrentPage());
		PcmPaymentInfoDtoList.setPageSize(selectPaymentTypeDto.getPageSize());
		PcmPaymentInfoDtoList.setCount(count);
		logger.info("selecNotPaymentTypeListByShopSid(),result:" + pcmPaymentInfoDtos.toString());

		return PcmPaymentInfoDtoList;
	}

	/**
	 * 查询需要下发支付方式的数据（修改支付方式）（增量）
	 * 
	 * @Methods Name selectPushPaymentByPaycode
	 * @Create In 2015-10-9 By wangxuan
	 * @param paraMap
	 * @return List<PcmPushPaymentToERPDto>
	 */
	@Override
	public List<PcmPushPaymentToERPDto> selectPushPaymentByPaycode(Map<String, Object> paraMap) {

		logger.info("start selectPushPaymentByPaycode(),param:" + paraMap.toString());

		String code = paraMap.get("code") + "";

		Map<String, Object> para = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(code)) {
			para.put("code", code);
		}

		List<PcmPushPaymentToERPDto> pushList = pcmPaymentTypeMapper
				.selectPushPaymentByPaycode(para);

		logger.info("end selectPushPaymentByPaycode(),return:" + pushList.toString());
		return pushList;
	}

	/**
	 * 查询下发支付方式的数据
	 * 
	 * @Methods Name pushPaymentTypeData
	 * @Create In 2015-10-28 By wangxuan
	 * @param paramMap
	 * @return List<Map<String,Object>>
	 */
	@Override
	public List<Map<String, Object>> pushPaymentTypeData(Map<String, Object> paramMap) {

		logger.info("start pushPaymentTypeData(),param:" + paramMap.toString());

		List<Map<String, Object>> list = pcmPaymentTypeMapper.pushPaymentTypeData(paramMap);

		String actionCode = paramMap.get("actionCode") + "";
		for (Map<String, Object> map : list) {
			if (StringUtils.isNotEmpty(actionCode)) {
				map.put("actionCode", actionCode.trim());
			}
			map.put("actionDate", DateUtil.formatToStr(new Date(), "yyyyMMdd.HHmmssZ"));
		}

		logger.info("end pushPaymentTypeData(),return:" + list.toString());
		return list;
	}
}

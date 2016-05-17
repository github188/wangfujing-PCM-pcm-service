package com.wangfj.product.supplier.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.core.utils.StringUtils;
import com.wangfj.product.supplier.domain.entity.PcmSupplyInfo;
import com.wangfj.product.supplier.domain.vo.PcmSupplyInfoDataDto;
import com.wangfj.product.supplier.domain.vo.PcmSupplyInfoPadDto;
import com.wangfj.product.supplier.persistence.PcmSupplyInfoMapper;
import com.wangfj.product.supplier.service.intf.IPcmSupplyInfoDataService;
import com.wangfj.util.Constants;

/**
 * 导入终端
 * 
 * @Class Name PcmSupplyInfoDataServiceImpl
 * @Author wangxuan
 * @Create In 2015-8-31
 */
@Service
public class PcmSupplyInfoDataServiceImpl implements IPcmSupplyInfoDataService {

	private static final Logger logger = LoggerFactory
			.getLogger(PcmSupplyInfoDataServiceImpl.class);

	@Autowired
	private PcmSupplyInfoMapper supplyInfoMapper;

	/**
	 * 由主数据获取供应商信息
	 * 
	 * @Methods Name getSupplyInfoData
	 * @Create In 2015-8-31 By wangxuan
	 * @param para
	 * @return List<PcmSupplyInfoDataDto>
	 */
	@Override
	public List<PcmSupplyInfoDataDto> getSupplyInfoData(Map<String, Object> para) {

		logger.info("start getSupplyInfoData(),para:" + para.toString());

		// 查询
		List<PcmSupplyInfo> supplyInfoList = supplyInfoMapper.selectListByParamForData(para);

		// 封装返回参数
		List<PcmSupplyInfoDataDto> supplyInfoDataDtoList = new ArrayList<PcmSupplyInfoDataDto>();
		if (supplyInfoList != null && !supplyInfoList.isEmpty()) {

			for (PcmSupplyInfo supplyInfo : supplyInfoList) {
				PcmSupplyInfoDataDto supplyInfoDataDto = new PcmSupplyInfoDataDto();
				supplyInfoDataDto.setCode(supplyInfo.getSupplyCode());
				supplyInfoDataDto.setSupplierName(supplyInfo.getSupplyName());
				supplyInfoDataDto.setSuperCode(supplyInfo.getSupplyCode());
				supplyInfoDataDto.setStoreCode(supplyInfo.getShopSid());
				Integer supplyType = supplyInfo.getSupplyType();
				if (supplyType != null) {
					if (supplyType == Constants.PCMSUPPLYINFO_SUPPLYTYPE_SHOP) {
						supplyInfoDataDto
								.setSupplierLevel(Constants.PCMSUPPLYINFO_SUPPLIERLEVEL_SHOP);
					}
					if (supplyType == Constants.PCMSUPPLYINFO_SUPPLYTYPE_EBUSINESS) {
						supplyInfoDataDto
								.setSupplierLevel(Constants.PCMSUPPLYINFO_SUPPLIERLEVEL_EBUSINESS);
					}
					if (supplyType == Constants.PCMSUPPLYINFO_SUPPLYTYPE_GROUP) {
						supplyInfoDataDto
								.setSupplierLevel(Constants.PCMSUPPLYINFO_SUPPLIERLEVEL_GROUP);
						supplyInfoDataDto.setSuperCode(null);
						supplyInfoDataDto.setStoreCode(null);
					}
				}
				supplyInfoDataDto.setErpSupplierCode(supplyInfo.getErpSupplierCode());
				supplyInfoDataDto.setOperateMode(supplyInfo.getBusinessPattern() + "");
				String status = supplyInfo.getStatus();
				if (StringUtils.isNotEmpty(status)) {

					if (Constants.PCMSUPPLYINFO_STATUS_Y_CODE.equals(status)) {
						supplyInfoDataDto.setOperateStatus(Constants.PCMSUPPLYINFO_STATUS_Y_TXT);
					}
					if (Constants.PCMSUPPLYINFO_STATUS_T_CODE.equals(status)) {
						supplyInfoDataDto.setOperateStatus(Constants.PCMSUPPLYINFO_STATUS_T_TXT);
					}
					if (Constants.PCMSUPPLYINFO_STATUS_N_CODE.equals(status)) {
						supplyInfoDataDto.setOperateStatus(Constants.PCMSUPPLYINFO_STATUS_N_TXT);
					}
					if (Constants.PCMSUPPLYINFO_STATUS_L_CODE.equals(status)) {
						supplyInfoDataDto.setOperateStatus(Constants.PCMSUPPLYINFO_STATUS_L_TXT);
					}
					if (Constants.PCMSUPPLYINFO_STATUS_3_CODE.equals(status)) {
						supplyInfoDataDto.setOperateStatus(Constants.PCMSUPPLYINFO_STATUS_3_TXT);
					}
					if (Constants.PCMSUPPLYINFO_STATUS_4_CODE.equals(status)) {
						supplyInfoDataDto.setOperateStatus(Constants.PCMSUPPLYINFO_STATUS_4_TXT);
					}
					if (Constants.PCMSUPPLYINFO_STATUS_5_CODE.equals(status)) {
						supplyInfoDataDto.setOperateStatus(Constants.PCMSUPPLYINFO_STATUS_5_TXT);
					}
					if (Constants.PCMSUPPLYINFO_STATUS_6_CODE.equals(status)) {
						supplyInfoDataDto.setOperateStatus(Constants.PCMSUPPLYINFO_STATUS_6_TXT);
					}

				} else {
					supplyInfoDataDto.setOperateStatus(null);
				}
				supplyInfoDataDto.setApprovalDate(supplyInfo.getAdmissionDate());
				supplyInfoDataDto.setZZRETURNV(supplyInfo.getReturnSupply() + "");
				supplyInfoDataDto.setZZJOIN_SITE(supplyInfo.getJoinSite());

				supplyInfoDataDtoList.add(supplyInfoDataDto);
			}

		}

		logger.info("end getSupplyInfoData()");

		return supplyInfoDataDtoList;
	}

	/**
	 * 移动工作台调用主数据获取供应商信息
	 * 
	 * @Methods Name getSupplyInfoForPad
	 * @Create In 2015-8-31 By wangxuan
	 * @param para
	 * @return List<PcmSupplyInfoPadDto>
	 */
	@Override
	public List<PcmSupplyInfoPadDto> getSupplyInfoForPad(Map<String, Object> para) {

		logger.info("start getSupplyInfoForPad(),para:" + para.toString());

		// 查询
		List<PcmSupplyInfo> supplyInfoList = supplyInfoMapper.selectListByParamForPad(para);

		// 封装返回参数
		List<PcmSupplyInfoPadDto> supplyInfoPadDtoList = new ArrayList<PcmSupplyInfoPadDto>();
		if (supplyInfoList != null && !supplyInfoList.isEmpty()) {

			for (PcmSupplyInfo supplyInfo : supplyInfoList) {
				PcmSupplyInfoPadDto supplyInfoPadDto = new PcmSupplyInfoPadDto();
				supplyInfoPadDto.setCode(supplyInfo.getSupplyCode());
				supplyInfoPadDto.setSupplierName(supplyInfo.getSupplyName());
				supplyInfoPadDto.setSuperCode(supplyInfo.getSupplyCode());
				supplyInfoPadDto.setStoreCode(supplyInfo.getShopSid());
				Integer supplyType = supplyInfo.getSupplyType();
				if (supplyType != null) {
					if (supplyType == Constants.PCMSUPPLYINFO_SUPPLYTYPE_SHOP) {
						supplyInfoPadDto
								.setSupplierLevel(Constants.PCMSUPPLYINFO_SUPPLIERLEVEL_SHOP);
					}
					if (supplyType == Constants.PCMSUPPLYINFO_SUPPLYTYPE_EBUSINESS) {
						supplyInfoPadDto
								.setSupplierLevel(Constants.PCMSUPPLYINFO_SUPPLIERLEVEL_EBUSINESS);
					}
					if (supplyType == Constants.PCMSUPPLYINFO_SUPPLYTYPE_GROUP) {
						supplyInfoPadDto
								.setSupplierLevel(Constants.PCMSUPPLYINFO_SUPPLIERLEVEL_GROUP);
						supplyInfoPadDto.setSuperCode(null);
						supplyInfoPadDto.setStoreCode(null);
					}
				}
				supplyInfoPadDto.setErpSupplierCode(supplyInfo.getErpSupplierCode());
				supplyInfoPadDto.setOperateMode(supplyInfo.getBusinessPattern() + "");
				String status = supplyInfo.getStatus();
				if (StringUtils.isNotEmpty(status)) {

					if (Constants.PCMSUPPLYINFO_STATUS_Y_CODE.equals(status)) {
						supplyInfoPadDto.setOperateStatus(Constants.PCMSUPPLYINFO_STATUS_Y_TXT);
					}
					if (Constants.PCMSUPPLYINFO_STATUS_T_CODE.equals(status)) {
						supplyInfoPadDto.setOperateStatus(Constants.PCMSUPPLYINFO_STATUS_T_TXT);
					}
					if (Constants.PCMSUPPLYINFO_STATUS_N_CODE.equals(status)) {
						supplyInfoPadDto.setOperateStatus(Constants.PCMSUPPLYINFO_STATUS_N_TXT);
					}
					if (Constants.PCMSUPPLYINFO_STATUS_L_CODE.equals(status)) {
						supplyInfoPadDto.setOperateStatus(Constants.PCMSUPPLYINFO_STATUS_L_TXT);
					}
					if (Constants.PCMSUPPLYINFO_STATUS_3_CODE.equals(status)) {
						supplyInfoPadDto.setOperateStatus(Constants.PCMSUPPLYINFO_STATUS_3_TXT);
					}
					if (Constants.PCMSUPPLYINFO_STATUS_4_CODE.equals(status)) {
						supplyInfoPadDto.setOperateStatus(Constants.PCMSUPPLYINFO_STATUS_4_TXT);
					}
					if (Constants.PCMSUPPLYINFO_STATUS_5_CODE.equals(status)) {
						supplyInfoPadDto.setOperateStatus(Constants.PCMSUPPLYINFO_STATUS_5_TXT);
					}
					if (Constants.PCMSUPPLYINFO_STATUS_6_CODE.equals(status)) {
						supplyInfoPadDto.setOperateStatus(Constants.PCMSUPPLYINFO_STATUS_6_TXT);
					}

				} else {
					supplyInfoPadDto.setOperateStatus(null);
				}
				supplyInfoPadDto.setApprovalDate(supplyInfo.getAdmissionDate());
				supplyInfoPadDto.setZZRETURNV(supplyInfo.getReturnSupply() + "");
				supplyInfoPadDto.setZZJOIN_SITE(supplyInfo.getJoinSite());

				supplyInfoPadDtoList.add(supplyInfoPadDto);
			}

		}

		logger.info("end getSupplyInfoForPad()");

		return supplyInfoPadDtoList;
	}

}

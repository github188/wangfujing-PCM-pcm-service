package com.wangfj.product.maindata.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.product.category.domain.entity.PcmCategory;
import com.wangfj.product.category.persistence.PcmCategoryMapper;
import com.wangfj.product.maindata.domain.entity.PcmContractLog;
import com.wangfj.product.maindata.domain.vo.ValidConCateDto;
import com.wangfj.product.maindata.persistence.PcmContractLogMapper;
import com.wangfj.product.maindata.service.intf.IValidConCateService;
import com.wangfj.product.organization.domain.entity.PcmOrganization;
import com.wangfj.product.organization.domain.entity.PcmShoppe;
import com.wangfj.product.organization.persistence.PcmOrganizationMapper;
import com.wangfj.product.organization.persistence.PcmShoppeMapper;

/**
 * 信息校验接口
 * 
 * @Class Name ValidConCateServiceImpl
 * @Author yedong
 * @Create In 2015年8月24日
 */
@Service
public class ValidConCateServiceImpl implements IValidConCateService {
	private static final Logger logger = LoggerFactory.getLogger(ValidConCateServiceImpl.class);

	@Autowired
	PcmShoppeMapper shoppeMapper;

	@Autowired
	PcmOrganizationMapper orgMapper;

	@Autowired
	PcmContractLogMapper cLogMapper;

	@Autowired
	PcmCategoryMapper cateMapper;

	/**
	 * 校验合同、管理分类
	 * 
	 * @Methods Name validConCate
	 * @Create In 2015年8月21日 By yedong
	 * @return Integer
	 */
	public Integer validConCate(ValidConCateDto dto) {
		logger.info("start validConCate(),param:" + dto);
		Integer falg = 0;
		PcmShoppe psDto = new PcmShoppe();
		psDto.setShoppeCode(dto.getCounterCode());
		psDto.setShoppeStatus(1);
		List<PcmShoppe> psList = shoppeMapper.selectListByParam(psDto);
		if (psList != null && psList.size() > 0) {
			PcmOrganization poDto = new PcmOrganization();
			poDto.setSid(psList.get(0).getShopSid());
			poDto.setOrganizationStatus(0);
			List<PcmOrganization> orgList = orgMapper.selectListByParam(poDto);
			if (orgList != null && orgList.size() > 0) {
				PcmContractLog clogDto = new PcmContractLog();
				clogDto.setStoreCode(orgList.get(0).getOrganizationCode());
				clogDto.setSupplyCode(dto.getSupplierCode());
				clogDto.setContractCode(dto.getOfferNumber());
				if (dto.getOperateMode() != null && dto.getOperateMode() != "") {
					clogDto.setManageType(Integer.parseInt(dto.getOperateMode()));
				}
				List<PcmContractLog> colgList = cLogMapper.selectListByParam(clogDto);
				if (colgList == null || colgList.size() == 0) {
					falg = 1;
				}
			} else {
				logger.error("专柜不存在");
				falg = 1;
			}
		} else {
			logger.error("门店不存在");
			falg = 1;
		}
		if (dto.getManageCateGory() != null && dto.getManageCateGory() != "") {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("categorySid", dto.getManageCateGory());
			paramMap.put("categoryType", 1);
			paramMap.put("isLeaf", "Y");
			paramMap.put("status", "Y");
			paramMap.put("isDisplay", 1);
			List<PcmCategory> cateList = cateMapper.selectListByParam(paramMap);
			if (cateList == null || cateList.size() == 0) {
				falg = 2;
			}
		}
		return falg;
	}
}

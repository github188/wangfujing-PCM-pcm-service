package com.wangfj.product.maindata.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.core.constants.ComErrorCodeConstants;
import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.common.domain.vo.PcmExceptionLogDto;
import com.wangfj.product.common.service.intf.IPcmExceptionLogService;
import com.wangfj.product.constants.StatusCodeConstants.StatusCode;
import com.wangfj.product.maindata.domain.entity.PcmContractLog;
import com.wangfj.product.maindata.domain.entity.PcmErpProduct;
import com.wangfj.product.maindata.domain.vo.ContractERPDto;
import com.wangfj.product.maindata.domain.vo.ContractInfoDto;
import com.wangfj.product.maindata.domain.vo.ContractLogFromPcmToSupDto;
import com.wangfj.product.maindata.domain.vo.GetContractLogForSupDto;
import com.wangfj.product.maindata.domain.vo.GetContractLogResultForSupDto;
import com.wangfj.product.maindata.domain.vo.PcmContractLogDto;
import com.wangfj.product.maindata.domain.vo.PcmContractLogPartDto;
import com.wangfj.product.maindata.domain.vo.PcmContractLogQueryDto;
import com.wangfj.product.maindata.domain.vo.ShoppeErpDto;
import com.wangfj.product.maindata.persistence.PcmContractLogMapper;
import com.wangfj.product.maindata.persistence.PcmErpProductMapper;
import com.wangfj.product.maindata.service.intf.IPcmContractLogService;
import com.wangfj.product.organization.persistence.PcmShoppeMapper;
import com.wangfj.product.supplier.domain.entity.PcmSupplyInfo;
import com.wangfj.product.supplier.persistence.PcmSupplyInfoMapper;
import com.wangfj.util.Constants;

/**
 * 合同表service实现类
 * 
 * @Class Name PcmContractLogServiceImpl
 * @Author liuhp
 * @Create In 2015-8-21
 */
@Service
public class PcmContractLogServiceImpl implements IPcmContractLogService {
	private static final Logger logger = LoggerFactory.getLogger(PcmContractLogServiceImpl.class);

	@Autowired
	PcmContractLogMapper contractLogMapper;
	@Autowired
	PcmSupplyInfoMapper supMapper;
	@Autowired
	PcmShoppeMapper shoppeMapper;
	@Autowired
	private IPcmExceptionLogService exceptionLogService;
	@Autowired
	PcmErpProductMapper erpMapper;
//	@Autowired
//	private PcmCategoryMapper categoryMapper;//分类

	/**
	 * 门店erp上传要约信息到合同表
	 * 
	 * @Methods Name uploadContractLogBatch
	 * @Create In 2015-8-21 By liuhp
	 * @param contracts
	 */
	@Override
	public void uploadContractLogBatch(List<PcmContractLogDto> contracts) {

		for (PcmContractLogDto contractLogDto : contracts) {
			PcmContractLog contractLog = new PcmContractLog();
			BeanUtils.copyProperties(contractLogDto, contractLog);
			// 验证供应商经营方式和合同经营方式是否一致
			Map<String, Object> parasMap = new HashMap<String, Object>();
			parasMap.put("supplyCode", contractLogDto.getSupplyCode());
			List<PcmSupplyInfo> sups = supMapper.selectListByParam(parasMap);
			if (sups != null && sups.size() != 0) {
				if (!sups.get(0).getBusinessPattern().equals(contractLogDto.getManageType())) {
					logger.info("要约信息上传失败-供应商经营方式与合同经营方式不一致:" + JsonUtil.getJSONString(contractLog));
					throw new BleException(ErrorCode.CONTRACT_MANGERTYPE_ERROR.getErrorCode(),
							ErrorCode.CONTRACT_MANGERTYPE_ERROR.getMemo());
				}
			} else {
				logger.info("要约信息上传失败-供应商不存在:" + JsonUtil.getJSONString(contractLog));
				throw new BleException(ErrorCode.SUPPLYINFO_NULL.getErrorCode(),
						ErrorCode.SUPPLYINFO_NULL.getMemo());
			}
			// 验证管理分类是否存在
			/*if(StringUtils.isNotBlank(contractLogDto.getCol1())){//管理分类不为空的时候验证有效性
				parasMap.clear();
				parasMap.put("categoryCode", contractLogDto.getCol1());// 管理分类编码
				parasMap.put("categoryType", Constants.MANAGECATEGORY);// 分类类型为 1 管理分类
				parasMap.put("isLeaf", Constants.Y);// 是否为叶子节点
				parasMap.put("status", Constants.Y);// 是否启用
				List<PcmCategory> managecateList = categoryMapper.selectListByParam(parasMap);
				if (managecateList == null || managecateList.size() != 1) {
					logger.info("管理分类不存在");
					throw new BleException(ErrorCode.CATEGORY_GL_NULL.getErrorCode(),
							ErrorCode.CATEGORY_GL_NULL.getMemo());
				}
			}*/
			if (contractLogDto.getFlag().equals(0)) {
				PcmContractLog valid = new PcmContractLog();
				valid.setContractCode(contractLog.getContractCode());
				List<PcmContractLog> list = contractLogMapper.selectListByParam(valid);
				if (list != null && list.size() != 0) {
					logger.info("要约信息上传失败-合同号已存在:" + JsonUtil.getJSONString(contractLog));
					throw new BleException(ErrorCode.CONTRACT_IS_EXIST.getErrorCode(),
							ErrorCode.CONTRACT_IS_EXIST.getMemo());
				}
			}
			try {
				// 执行添加操作
				if (contractLogDto.getFlag().equals(0)) {
					contractLogMapper.insertSelective(contractLog);
				}
				// 执行修改操作
				if (contractLogDto.getFlag().equals(1)) {
					contractLogMapper.updateByParam(contractLog);
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				logger.info("要约信息上传失败：" + JsonUtil.getJSONString(contractLog));
				PcmExceptionLogDto exceptionLogdto = new PcmExceptionLogDto();
				exceptionLogdto.setInterfaceName("uploadContractLog");
				exceptionLogdto.setExceptionType(StatusCode.EXCEPTION_CONTRACT.getStatus());
				exceptionLogdto
						.setErrorMessage(ComErrorCodeConstants.ErrorCode.CONTRACT_UPLOAD_ERROR
								.getMemo() + "." + JsonUtil.getJSONString(contractLogDto));
				exceptionLogService.saveExceptionLogInfo(exceptionLogdto);
				logger.info(ErrorCode.DATA_OPER_ERROR.getErrorCode()
						+ ErrorCode.DATA_OPER_ERROR.getMemo() + ":"
						+ JsonUtil.getJSONString(contractLog));
				throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
						ErrorCode.DATA_OPER_ERROR.getMemo());
			}
		}
	}

	/**
	 * 根据门店编码、供应商编码、经营方式查询要约信息
	 * 
	 * @Methods Name selectContractLogByParam
	 * @Create In 2015年9月9日 By yedong
	 * @param dto
	 * @return List<PcmContractLog>
	 */
	public ContractERPDto selectContractLogByParam(PcmContractLogDto dto) {
		PcmContractLog entity = new PcmContractLog();
		entity.setContractCode(dto.getContractCode());
		entity.setSupplyCode(dto.getSupplyCode());
		entity.setStoreCode(dto.getStoreCode());
		entity.setManageType(dto.getManageType());
		List<PcmContractLog> contractList = contractLogMapper.selectListByParam(entity);
		PcmErpProduct erp = new PcmErpProduct();
		erp.setShoppeCode(dto.getShoppeCode());
		erp.setSupplyCode(dto.getSupplyCode());
		erp.setCodeType(Constants.PCMERPPRODUCT_CODE_TYPE_DISCOUNT);
		List<PcmErpProduct> erplist = erpMapper.selectRateCodeByParam(erp);
		ContractERPDto res = new ContractERPDto();
		res.setContractList(contractList);
		res.setErpList(erplist);
		return res;
	}

	/**
	 * 根据门店编码、供应商编码、经营方式查询要约等信息(PIS)
	 * 
	 * @Methods Name selectContractInfo
	 * @Create In 2015年11月10日 By zhangxy
	 * @param dto
	 * @return ContractERPDto
	 */
	@Override
	public ContractInfoDto selectContractInfo(PcmContractLogDto dto) {
		ContractInfoDto res = new ContractInfoDto();
		PcmSupplyInfo supEntity = new PcmSupplyInfo();
		if("2".equals(String.valueOf(dto.getBusinessType()))){//如果业态是电商
			supEntity.setShopSid(dto.getStoreCode());//用门店+先销后采标识查供应商
			supEntity.setZzxxhcFlag("Y");//先销后采
			supEntity.setStatus("Y");
			supEntity.setSupplyCode(dto.getSupplyCode());//供应商编码
		}else {
			supEntity.setShopSid(dto.getStoreCode());
			supEntity.setSupplyCode(dto.getSupplyCode());
			supEntity.setBusinessPattern(dto.getManageType());
			supEntity.setStatus("Y");
		}
		List<PcmSupplyInfo> supList = supMapper.selectListByParam(supEntity);
		if (supList != null && supList.size() != 0) {
			PcmContractLog entity = new PcmContractLog();
			entity.setSupplyCode(dto.getSupplyCode());
			entity.setStoreCode(dto.getStoreCode());
			entity.setManageType(dto.getManageType());
			List<ShoppeErpDto> shoppeList = shoppeMapper.selectShoppeListForPis(dto);
			List<PcmContractLog> contractList = contractLogMapper.selectListByParam(entity);
			List<String> contractList1 = new ArrayList<String>();
			for (PcmContractLog en : contractList) {
				contractList1.add(en.getContractCode());
			}
			res.setSupplierName(supList.get(0).getSupplyName());
			res.setContractList(contractList1);
			res.setShoppeList(shoppeList);
		}
		return res;
	}

	/**
	 * 查询要约的部分信息
	 */
	@Override
	public List<PcmContractLogPartDto> findContractLogList(PcmContractLogQueryDto dto) {
		logger.info("start findContractLogList(),param:" + dto.toString());
		List<PcmContractLogPartDto> dtoList = contractLogMapper.findContractLogList(dto);
		logger.info("end findContractLogList(),return:" + dtoList);
		return dtoList;
	}

	/**
	 * 分页查询要约信息
	 * 
	 * @param dto
	 * @return
	 */
	@Override
	public Page<PcmContractLogDto> findPageContract(PcmContractLogQueryDto dto) {
		logger.info("start findPageContract(),param:" + dto.toString());
		Page<PcmContractLogDto> page = new Page<PcmContractLogDto>();
		Integer currentPage = dto.getCurrentPage();
		if (currentPage != null) {
			page.setCurrentPage(currentPage);
		}
		Integer pageSize = dto.getPageSize();
		if (pageSize != null) {
			page.setPageSize(pageSize);
		}
		Integer count = contractLogMapper.findPageCountContractLog(dto);
		page.setCount(count);

		dto.setStart(page.getStart());
		dto.setLimit(page.getLimit());
		List<PcmContractLogDto> dtoList = contractLogMapper.findPageContractLog(dto);
		page.setList(dtoList);
		logger.info("end findPageContract(),return:" + dtoList.toString());
		return page;
	}

	/**
	 * 供应商获取要约信息
	 */
	@Override
	public Page<GetContractLogResultForSupDto> getContractLogFromPcmToSup(
			GetContractLogForSupDto dto) {
		logger.info("start GetContractLogFromPcmToSup(),param:" + dto.toString());
		Page<GetContractLogResultForSupDto> resultPage = new Page<GetContractLogResultForSupDto>();
		Integer currentPage = dto.getCurrentPage();
		if (currentPage != null && currentPage != 0) {
			resultPage.setCurrentPage(currentPage);
		}
		Integer pageSize = dto.getPageSize();
		if (pageSize != null && pageSize != 0) {
			resultPage.setPageSize(pageSize);
		}
		Integer count = contractLogMapper.getContractLogCountFromPcmToSup(dto);//获取要约数量
		resultPage.setCount(count);
		dto.setLimit(resultPage.getLimit());
		dto.setStart(resultPage.getStart());
		List<GetContractLogResultForSupDto> reusltList = contractLogMapper.getContractLogFromPcmToSup(dto);
		resultPage.setList(reusltList);
		logger.info("end GetContractLogFromPcmToSup(),result:" + resultPage.getList().size());
		return resultPage;
	}

	/**
	 * 供应商根据时间段信息获取要约数量
	 */
	@Override
	public Integer getContractLogFromPcmToSupByTime(ContractLogFromPcmToSupDto paraDto){
		Integer count = contractLogMapper.getContractLogCountFromPcmToSupByTime(paraDto);
		return count;
	}

	
}

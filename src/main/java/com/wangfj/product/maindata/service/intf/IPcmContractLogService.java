package com.wangfj.product.maindata.service.intf;

import java.util.List;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.maindata.domain.vo.ContractERPDto;
import com.wangfj.product.maindata.domain.vo.ContractInfoDto;
import com.wangfj.product.maindata.domain.vo.ContractLogFromPcmToSupDto;
import com.wangfj.product.maindata.domain.vo.GetContractLogForSupDto;
import com.wangfj.product.maindata.domain.vo.GetContractLogResultForSupDto;
import com.wangfj.product.maindata.domain.vo.PcmContractLogDto;
import com.wangfj.product.maindata.domain.vo.PcmContractLogPartDto;
import com.wangfj.product.maindata.domain.vo.PcmContractLogQueryDto;

/**
 * 合同表service
 * 
 * @Class Name IPcmContractLogService
 * @Author liuhp
 * @Create In 2015-8-21
 */
public interface IPcmContractLogService {
	/**
	 * 门店erp上传要约信息到合同表
	 * 
	 * @Methods Name uploadContractLogBatch
	 * @Create In 2015-8-21 By liuhp
	 * @param contracts
	 *            void
	 */
	public void uploadContractLogBatch(List<PcmContractLogDto> contracts);

	/**
	 * 根据门店编码、供应商编码、经营方式查询要约信息
	 * 
	 * @Methods Name selectContractLogByParam
	 * @Create In 2015年9月9日 By yedong
	 * @param dto
	 * @return List<PcmContractLog>
	 */
	public ContractERPDto selectContractLogByParam(PcmContractLogDto dto);

	/**
	 * 根据门店编码、供应商编码、经营方式查询要约等信息(PIS)
	 * 
	 * @Methods Name selectContractInfo
	 * @Create In 2015年11月10日 By zhangxy
	 * @param dto
	 * @return ContractInfoDto
	 */
	public ContractInfoDto selectContractInfo(PcmContractLogDto dto);

	/**
	 * 查询要约的部分信息
	 * 
	 * @Methods Name findContractLogList
	 * @Create In 2015-12-8 By wangxuan
	 * @param dto
	 * @return List<PcmContractLogPartDto>
	 */
	public List<PcmContractLogPartDto> findContractLogList(PcmContractLogQueryDto dto);

	/**
	 * 分页查询要约信息
	 * 
	 * @param dto
	 * @return
	 */
	Page<PcmContractLogDto> findPageContract(PcmContractLogQueryDto dto);
	/**
	 * 供应商获取要约信息
	 * @Methods Name GetContractLogFromPcmToSup
	 * @Create In 2016-3-30 By wangc
	 * @param dto
	 * @return Page<GetContractLogResultForSupDto>
	 */
	Page<GetContractLogResultForSupDto> getContractLogFromPcmToSup(GetContractLogForSupDto dto);
	/**
	 * 供应商根据时间段获取要约信息
	 * @Methods Name getContractLogFromPcmToSupByTime
	 * @Create In 2016-4-6 By wangc
	 * @param paraDto
	 * @return Integer
	 */
	Integer getContractLogFromPcmToSupByTime(ContractLogFromPcmToSupDto paraDto);
}

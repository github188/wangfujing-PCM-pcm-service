package com.wangfj.product.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.core.constants.ComErrorCodeConstants;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.StringUtils;
import com.wangfj.product.core.domain.dto.PcmDictDto;
import com.wangfj.product.core.domain.dto.PcmDictInfoDto;
import com.wangfj.product.core.domain.dto.PcmGetDictDto;
import com.wangfj.product.core.domain.dto.PcmSelectDictDto;
import com.wangfj.product.core.domain.entity.PcmDict;
import com.wangfj.product.core.persistence.PcmDictMapper;
import com.wangfj.product.core.service.intf.IPcmDictService;
import com.wangfj.util.Constants;

/**
 * 字典表
 * 
 * @author nzf
 * @Create In 2015年9月15日
 */
@Service
public class PcmDictServiceImpl implements IPcmDictService {

	@Autowired
	public PcmDictMapper pcmDictMapper;

	private static final Logger logger = LoggerFactory.getLogger(PcmDictServiceImpl.class);

	@Override
	public Boolean isExistence(Map<String, Object> para) {

		logger.info("start isExistence(),para:" + para.toString());

		Boolean flag = false;
		String name = para.get("name") + "";
		String code = para.get("code") + "";
		String pid = para.get("pid") + "";

		Map<String, Object> paramMap = new HashMap<String, Object>();

		// 判重编码
		if (StringUtils.isNotEmpty(code)) {
			paramMap.clear();
            paramMap.put("status", Constants.PUBLIC_0);
			paramMap.put("code", code);
			
			if (StringUtils.isNotEmpty(pid)) {
				paramMap.put("pid", Long.parseLong(pid));
			}
			
			List<PcmDict> codeList = pcmDictMapper.selectListByParam(paramMap);
			if (codeList != null && codeList.size() > 0) {
				flag = true;
				throw new BleException(
						ComErrorCodeConstants.ErrorCode.DICT_CODE_NOT_ONLY.getErrorCode(),
						ComErrorCodeConstants.ErrorCode.DICT_CODE_NOT_ONLY.getMemo());
			}
		}

		// 判重名称
		if (StringUtils.isNotEmpty(name)) {
			paramMap.clear();
			paramMap.put("status", Constants.PUBLIC_0);
			paramMap.put("name", name);

			if (StringUtils.isNotEmpty(pid)) {
				paramMap.put("pid", Long.parseLong(pid));
			}

			List<PcmDict> nameList = pcmDictMapper.selectListByParam(paramMap);
			if (nameList != null && nameList.size() > 0) {
				flag = true;
				throw new BleException(
						ComErrorCodeConstants.ErrorCode.DICT_NAME_NOT_ONLY.getErrorCode(),
						ComErrorCodeConstants.ErrorCode.DICT_NAME_NOT_ONLY.getMemo());
			}
		}

		logger.info("end isExistence()");

		return flag;
	}

	/**
	 * @author nzf
	 * @create in 2015-9-16
	 * @param dto
	 * @return Integer
	 */
	public Integer saveDict(PcmDictDto dto) {

		logger.info("start saveDict(),para:" + dto.toString());

		Map<String, Object> para = new HashMap<String, Object>();
		para.put("code", dto.getCode());
		para.put("name", dto.getName());
		para.put("pid", dto.getPid());
		Boolean existence = isExistence(para);

		int count = Constants.PUBLIC_0;
		PcmDict pcmDict = new PcmDict();
		if (!existence) {
			pcmDict.setCode(dto.getCode());
			pcmDict.setName(dto.getName());
			pcmDict.setSort(dto.getSort());
			pcmDict.setStatus(Constants.PUBLIC_0);

			Long pid = dto.getPid();
			if (pid != null) {
				pcmDict.setPid(pid);
			} else {
				pcmDict.setPid(Long.parseLong(Constants.PUBLIC_0 + ""));
			}

			count = pcmDictMapper.insertSelective(pcmDict);

		}

		logger.info("end saveDict()");

		return count;
	}

	/**
	 * 字典修改功能 只能修改名称和编码
	 */
	public String updateDict(PcmDictDto dto) throws BleException {
		String message = "";
		logger.info("数据字典开始修改");

		PcmDict pcmDict = new PcmDict();
		Integer countCode = 0;
		Integer countName = 0;

		PcmDictDto dictDto = new PcmDictDto();
		dictDto.setSid(dto.getSid());
		List<PcmDict> pcmDictList = pcmDictMapper.selectPcmDict(dictDto);
		// 判断字典是否存在
		if (pcmDictList.size() == Constants.PUBLIC_1) {
			// 判断字典编码是否为空
			if (dto.getCode() != null) {
				// 判断字典编码和欲修改后编码是否一致
				if (pcmDictList.get(0).getCode().equals(dto.getCode())) {
					pcmDict.setCode(dto.getCode());
				} else {
					pcmDict.setCode(dto.getCode());
					pcmDict.setPid(pcmDictList.get(0).getPid());
					countCode = pcmDictMapper.getCountByParamForCheck(pcmDict);
					// 判重（名称和编码）
					if (countCode > 0) {
						throw new BleException(
								ComErrorCodeConstants.ErrorCode.DICT_CODE_NOT_ONLY.getErrorCode(),
								ComErrorCodeConstants.ErrorCode.DICT_CODE_NOT_ONLY.getMemo());
					}
				}
			} else {
				pcmDict.setCode(null);
			}

			// 同编码一样
			if (dto.getName() != null) {
				if (pcmDictList.get(0).getName().equals(dto.getName())) {
					pcmDict.setName(dto.getName());
				} else {
					pcmDict.setName(dto.getName());
					pcmDict.setPid(pcmDictList.get(0).getPid());
					countName = pcmDictMapper.getCountByParamForCheck(pcmDict);
					if (countName > 0) {
						throw new BleException(
								ComErrorCodeConstants.ErrorCode.DICT_NAME_NOT_ONLY.getErrorCode(),
								ComErrorCodeConstants.ErrorCode.DICT_NAME_NOT_ONLY.getMemo());
					}
				}
			} else {
				pcmDict.setName(null);
			}

			pcmDict.setSid(dto.getSid());
			int updateByPrimaryKeySelective = pcmDictMapper.updateByPrimaryKeySelective(pcmDict);
			if (updateByPrimaryKeySelective == Constants.PUBLIC_1) {
				logger.info("修改成功");
				message = "修改成功";
			}

		} else {
			throw new BleException(ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
		}
		return message;
	}

	/**
	 * 根据dto查找字典
	 */
	@Override
	public List<PcmDict> selectBySid(PcmDictDto dto) throws BleException {
		List<PcmDict> selectPcmDict = pcmDictMapper.selectPcmDict(dto);
		if (selectPcmDict.size() > 0) {
			logger.info("查找成功");
		} else {
			logger.error("查询失败");
			throw new BleException(ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
		}
		return selectPcmDict;
	}

	/**
	 * 根据sid删除字典
	 */
	@Override
	public String deleteBySid(PcmDictDto dto) throws BleException {
		String message = "";
		// 先查
		List<PcmDict> selectBySid = selectBySid(dto);
		PcmDict dict = new PcmDict();
		if (selectBySid.size() > 0) {
			dict.setSid(dto.getSid());
			dict.setStatus(1);
			// 再改
			int updateByPrimaryKeySelective = pcmDictMapper.updateByPrimaryKeySelective(dict);
			if (updateByPrimaryKeySelective > 0) {
				logger.info("删除成功");
				message = "删除成功";
			}
		} else {
			throw new BleException(ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
		}
		return message;
	}

	/**
	 * 分页查询
	 */
	@Override
	public Page<PcmSelectDictDto> getDictList(PcmGetDictDto dto) throws BleException {
		Page<PcmSelectDictDto> page = new Page<PcmSelectDictDto>();
		if (dto.getCurrentPage() != null) {
			page.setCurrentPage(dto.getCurrentPage());
		}
		if (dto.getPageSize() != null) {
			page.setPageSize(dto.getPageSize());
		}

		// 查询总记录数
		Integer count = pcmDictMapper.getCountDictInfo(dto);
		page.setCount(count);

		// 分页查询
		dto.setStart(page.getStart());
		dto.setLimit(page.getLimit());
		List<PcmSelectDictDto> selectPcmDictInfo = pcmDictMapper.selectPcmDictInfo(dto);
		if (selectPcmDictInfo != null) {
			page.setList(selectPcmDictInfo);
		}

		return page;
	}

	@Override
	public List<HashMap<String, Object>> findDictLitByPid(PcmDictInfoDto dto) throws BleException {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		if (dto != null) {
			String pid = dto.getParentCode();
			String[] pidList = pid.split(",");
			for (int i = 0; i < pidList.length; i++) {

				PcmDictDto dtoDict = new PcmDictDto();
				PcmDictDto dtoDi = new PcmDictDto();

				HashMap<String, Object> map = new HashMap<String, Object>();
				dtoDict.setCode(pidList[i]);

				List<PcmDict> selectByCode = null;
				List<PcmDict> selectSid = null;
				try {
					// 先找到对应的dict
					selectByCode = selectBySid(dtoDict);
					// 再 找子dict
					if (selectByCode.size() != 0) {
						dtoDi.setPid(selectByCode.get(0).getSid());
						selectSid = selectBySid(dtoDi);
					}
				} catch (Exception e) {
					map.put(pidList[i], null);
				}

				map.put(pidList[i], selectSid);
				list.add(map);
			}
		} else {
			throw new BleException(
					ComErrorCodeConstants.ErrorCode.DICT_VALUES_IS_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DICT_VALUES_IS_ERROR.getMemo());
		}
		return list;
	}
}

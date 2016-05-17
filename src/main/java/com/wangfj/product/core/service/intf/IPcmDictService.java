package com.wangfj.product.core.service.intf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.product.core.domain.dto.PcmDictDto;
import com.wangfj.product.core.domain.dto.PcmDictInfoDto;
import com.wangfj.product.core.domain.dto.PcmGetDictDto;
import com.wangfj.product.core.domain.dto.PcmSelectDictDto;
import com.wangfj.product.core.domain.entity.PcmDict;

public interface IPcmDictService {
	// 增加
	public Integer saveDict(PcmDictDto dto);

	// 修改
	public String updateDict(PcmDictDto dto) throws BleException;

	// 查询
	public List<PcmDict> selectBySid(PcmDictDto dto);

	// 删除
	public String deleteBySid(PcmDictDto dto);

	// 分页查询
	public Page<PcmSelectDictDto> getDictList(PcmGetDictDto dto);

	// 根据个数不等的父sid查询所对应的子sid属性值
	public List<HashMap<String, Object>> findDictLitByPid(PcmDictInfoDto dto);

	Boolean isExistence(Map<String, Object> para);
}

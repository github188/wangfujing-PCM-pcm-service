package com.wangfj.product.organization.service.intf;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.organization.domain.entity.PcmRegion;
import com.wangfj.product.organization.domain.vo.PcmRegionQueryDto;
import com.wangfj.product.organization.domain.vo.PcmRegionResultDto;

/**
 * 行政区域管理
 * 
 * @Class Name IPcmRegionService
 * @Author yedong
 * @Create In 2015年7月14日
 */
public interface IPcmRegionService {

	List<Map<String, Object>> pushRegionData(Map<String, Object> para);

	Boolean isExistence(Map<String, Object> para);

	Integer addRegion(PcmRegion region);

	Integer modifyRegion(PcmRegion region);

	Page<PcmRegionResultDto> findPageRegion(PcmRegionQueryDto dto);

	List<PcmRegionResultDto> findListRegion(PcmRegionQueryDto dto);

	List<PcmRegionResultDto> getRegionChildrenListByCode(PcmRegionQueryDto dto);
}

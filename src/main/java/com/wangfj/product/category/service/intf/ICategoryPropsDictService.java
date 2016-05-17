package com.wangfj.product.category.service.intf;

import java.util.List;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.category.domain.entity.PcmCategoryPropsDict;
import com.wangfj.product.category.domain.vo.CategoryPropsDictVO;
import com.wangfj.product.category.domain.vo.PcmCategoryPropsDictDto;
import com.wangfj.product.category.domain.vo.PcmPropsDictsDto;

public interface ICategoryPropsDictService {

	int delete(Long sid);

	int save(PcmCategoryPropsDict record);
    
	int saveorupdate(PcmCategoryPropsDict record);

	PcmCategoryPropsDict get(Long sid);
    
	PcmCategoryPropsDict getByPropSidAndChannelSid(Long propsSid, Long channelSid);

	int update(PcmCategoryPropsDict record);
    
	List<PcmCategoryPropsDict> selectList(PcmCategoryPropsDict record);

	/**
	 * 查询属性值列表
	 * <p>
	 * 因为需要根据渠道查询所以改为in()
	 * 
	 * @Methods Name selectList
	 * @Create In 2015年8月31日 By wangsy
	 * @param record
	 * @return List<PcmCategoryPropsDict>
	 */
	List<PcmCategoryPropsDict> selectListInChannelSid(CategoryPropsDictVO record);
    
	List<PcmCategoryPropsDictDto> selectPage(PcmCategoryPropsDict record);
    
	int selectPageTotal(PcmCategoryPropsDict record);
	
	int selectPageLikeTotal(PcmCategoryPropsDict record);
    
    Long getMaxSortOrder(Long channelSid);
    
    /**
     * 根据父sid 查询属性字典
     * @Methods Name selectPropsDictByParentSid
     * @Create In 2015年8月13日 By duanzhaole
     * @return List<PcmCategoryPropsDict>
     */
    Page<PcmCategoryPropsDict> selectPropsDictByParentSid(PcmPropsDictsDto propdDto,Page<PcmPropsDictsDto> pagedto);
    
   
    
}

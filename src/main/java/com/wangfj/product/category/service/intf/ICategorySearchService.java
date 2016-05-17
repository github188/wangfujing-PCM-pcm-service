/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.core.service.intfICategoryService.java
 * @Create By duanzhaole
 * @Create In 2015年7月2日 上午11:12:11
 * TODO
 */
package com.wangfj.product.category.service.intf;

import java.util.List;

import com.wangfj.product.category.domain.vo.CategorySearchDto;
import com.wangfj.util.mq.PublishDTO;

/**
 * 搜索接口service
 * 
 * @Class Name ICategorySearchService
 * @Author zhangxy
 * @Create In 2015年11月11日
 */
public interface ICategorySearchService {
	/**
	 * 根据展示分类编码查询展示分类信息
	 * 
	 * @Methods Name getCateByCode
	 * @Create In 2015年11月11日 By zhangxy
	 * @param categorySid
	 * @return PcmCategory
	 */
	CategorySearchDto getCateByCode(String code);

	/**
	 * 根据展示分类Sid查询展示分类信息下发
	 * 
	 * @Methods Name selectCateBySidList
	 * @Create In 2015年11月11日 By zhangxy
	 * @param List <PublishDTO>
	 * @return PcmCategory
	 */
	List<CategorySearchDto> selectCateBySidList(List<PublishDTO> list);

	/**
	 * 根据展示分类编码查询子节点
	 * 
	 * @Methods Name getByParentSid
	 * @Create In 2015年11月11日 By zhangxy
	 * @param parentSid
	 * @return List<String>
	 */
	List<String> getByParentSid(String parentSid);
}

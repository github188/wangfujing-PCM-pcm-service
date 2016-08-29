/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.core.service.intfICategoryService.java
 * @Create By duanzhaole
 * @Create In 2015年7月2日 上午11:12:11
 */
package com.wangfj.product.category.service.intf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.category.domain.entity.PcmCategory;
import com.wangfj.product.category.domain.vo.CategoryByChannelVo;
import com.wangfj.product.category.domain.vo.PcmAddCategoryDto;
import com.wangfj.product.category.domain.vo.PcmCategoryPropsDictPara;
import com.wangfj.product.category.domain.vo.PcmCategoryQueryDto;
import com.wangfj.product.category.domain.vo.PcmProDetailDto;
import com.wangfj.product.category.domain.vo.PcmProductDto;
import com.wangfj.product.category.domain.vo.PcmPropsDictsDto;
import com.wangfj.product.category.domain.vo.PropsVO;
import com.wangfj.product.category.domain.vo.PublishCategoryDto;
import com.wangfj.product.category.domain.vo.SelectCategoryParamDto;
import com.wangfj.product.maindata.domain.entity.PcmProduct;

/**
 * @Class Name ICategoryService
 * @Author duanzhaole
 * @Create In 2015年7月2日
 */
public interface ICategoryService {

	/**
	 * 工业分类、统计分类、展示分类准入和修改
	 * 
	 * @Methods Name uploadeIndustrytCategory
	 * @Create In 2015年7月21日 By duanzhaole
	 * @param cateDto
	 *            void
	 */
	String uploadeCategory(PcmAddCategoryDto cateDto);

	/**
	 * 查询品类信息
	 * 
	 * @return
	 */
	Page<SelectCategoryParamDto> selectListByParam(SelectCategoryParamDto catedto,
			Page<SelectCategoryParamDto> pagedto);

	/**
	 * 查询所有分类
	 * 
	 * @Methods Name publishCategoryFromPCM
	 * @Create In 2015年7月14日 By yedong
	 * @return List<HashMap<String,Object>>
	 */
	List<PcmCategory> publishCategoryFromPCM(Map<String, Object> maps);

	// List<PcmCategory> selectCategoryList(PcmCategory cate){
	//
	// }

	/**
	 * 根据sid查询品类信息
	 * 
	 * @Methods Name getCategoryBySid
	 * @Create In 2015年7月27日 By duanzhaole
	 * @return PcmCategory
	 */
	PcmAddCategoryDto getCategoryBySid(Long sid);

	/**
	 * 根据品类编码查询
	 * 
	 * @Methods Name getCateByCatesid
	 * @Create In 2015年8月7日 By duanzhaole
	 * @param categorySid
	 * @return PcmCategory
	 */
	public PcmCategory getCateByCatesid(String categorySid);

	/**
	 * 通过管理分类的sid查询专柜商品信息
	 * 
	 * @Methods Name selectShoppeByCateSid
	 * @Create In 2015年7月29日 By duanzhaole
	 * @param sid
	 * @return Page<HashMap<String,Object>>
	 */
	Page<HashMap<String, Object>> selectShoppeByCateSid(SelectCategoryParamDto paramDto,
			Page<HashMap<String, Object>> pagedto);

	/**
	 * 根据工业分类的sid查询spu
	 * 
	 * @Methods Name selectSPUByCategorySid
	 * @Create In 2015年8月4日 By duanzhaole
	 * @param sid
	 * @return Page<PcmProductDto>
	 */
	Page<PcmProductDto> selectSPUByCategorySid(Long sid);

	/**
	 * 根据工业分类的sid查询sku
	 * 
	 * @Methods Name selectSKUByCategorySid
	 * @Create In 2015年8月4日 By duanzhaole
	 * @param sid
	 * @return Page<PcmProDetailDto>
	 */
	Page<PcmProDetailDto> selectSKUByCategorySid(Long sid);

	/**
	 * 根据id从数据库删除品类信息
	 * 
	 * @Methods Name deleteByPrimaryKey
	 * @Create In 2015年7月30日 By duanzhaole
	 * @param sid
	 * @return int
	 */
	int deleteByPrimaryKey(Long sid);

	/**
	 * 向数据库插入品类信息
	 * 
	 * @Methods Name insert
	 * @Create In 2015年7月30日 By duanzhaole
	 * @param record
	 * @return int
	 */
	int insert(PcmCategory record);

	/**
	 * 查找父类下的所有子类
	 * 
	 * @Methods Name getByParentSid
	 * @Create In 2015年7月30日 By duanzhaole
	 * @param parentSid
	 * @return List<PcmCategory>
	 */
	List<PcmCategory> getByParentSid(String parentSid);

	/**
	 * 根据指定的ID找出相应的品类
	 * 
	 * @Methods Name selectListIn
	 * @Create In 2015年7月30日 By duanzhaole
	 * @param cateIds
	 * @return List<PcmCategory>
	 */
	public List<PcmCategory> selectListIn(List<Long> cateIds);

	/**
	 * 门店系统,pad系统专用接口 返回当前节点的父节点或子节点
	 * 
	 * @Methods Name getSubOrPaNode
	 * @Create In 2015年7月30日 By duanzhaole
	 * @param categorySid
	 * @param channelSid
	 * @param flag
	 * @return List<PcmCategory>
	 */
	public List<PcmCategory> getSubOrPaNode(String categorySid, Long channelSid, String flag);

	/**
	 * 获取所有分类 根据参数获取 一二三级
	 * 
	 * @Methods Name getProductStanCategorys
	 * @Create In 2015年7月30日 By duanzhaole
	 * @param map
	 * @return List<Map<String,Object>>
	 */
	List<Map<String, Object>> getProductStanCategorys(Map map);

	/**
	 * 根据id 查询品类信息
	 * 
	 * @Methods Name get
	 * @Create In 2015年8月3日 By duanzhaole
	 * @param sid
	 * @return PcmCategory
	 */
	PcmCategory get(Long sid);

	/**
	 * 修改品类信息
	 * 
	 * @Methods Name update
	 * @Create In 2015年8月6日 By duanzhaole
	 * @param record
	 * @return int
	 */
	public int update(PcmCategory record);

	/**
	 * 通过品类叶子节点查询spu信息
	 * 
	 * @Methods Name selectSpuByIsLeaf
	 * @Create In 2015年8月11日 By duanzhaole
	 * @param catedto
	 * @param pagedto
	 * @return Page<PcmProductDto>
	 */
	Page<PcmProduct> selectSpuByIsLeaf(SelectCategoryParamDto catedto,
			Page<SelectCategoryParamDto> pagedto);

	/**
	 * 获取所有工业分类
	 * 
	 * @Methods Name getIndustryCategorys
	 * @Create In 2015-8-10 By chengsj
	 * @return List<Map<String,Object>>
	 */
	List<Map<String, Object>> getIndustryCategorys();

	/**
	 * 获取最大sortorder
	 * 
	 * @Methods Name getMaxSortOrder
	 * @Create In 2015年8月12日 By duanzhaole
	 * @param parentSid
	 * @return Integer
	 */
	public Integer getMaxSortOrder(String parentSid);

	/**
	 * 通过父级节点查询叶子节点
	 * 
	 * @Methods Name selectLeafByParentSid
	 * @Create In 2015年8月12日 By duanzhaole
	 * @param cate
	 * @return List<PcmCategory>
	 */
	public List<PcmCategory> selectLeafByParentSid(Map<String, Object> mapParam);

	/**
	 * 拖拽功能
	 * 
	 * @Methods Name categoryBeforeDrop
	 * @Create In 2015年8月13日 By duanzhaole
	 * @param sid
	 * @param moveType
	 * @return Integer
	 */
	public Map<String, Object> categoryBeforeDrop(Long sid, String parentSid, int sortOrder,
			int categoryType, String targetSid, String isParent, String rootSid, String moveType);

	/**
	 * 根据叶子节点查询属性字典(无分页)
	 * 
	 * @Methods Name selectPropsDictByCateSid
	 * @Create In 2015年8月20日 By duanzhaole
	 * @param cateprop
	 * @param pageParam
	 * @return Page<PcmCategoryPropsDict>
	 */
	List<PropsVO> selectPropsDictByCateSid(PcmPropsDictsDto cateprop);

	/**
	 * 根据工业分类叶子节点查询属性和对应的属性值（用于搜索）
	 * 
	 * @Methods Name selectPropsDictByCategorySid
	 * @Create In 2015年8月31日 By duanzhaole
	 * @param catedict
	 * @return List<PropsVO>
	 */
	List<PropsVO> selectPropsDictByCategorySid(PcmCategoryPropsDictPara para);

	/**
	 * 根据末级节点查询spu count
	 * 
	 * @Methods Name selectCountByIsLeaf
	 * @Create In 2015年9月7日 By duanzhaole
	 * @return int
	 */
	public int selectCountByIsLeaf(SelectCategoryParamDto catedto);

	/**
	 * 根据品类类型查询
	 * 
	 * @Methods Name getCountByCategoryType
	 * @Create In 2015年9月18日 By duanzhaole
	 * @param categoryType
	 * @return Integer
	 */
	public Integer getCountByCategoryType();

	/**
	 * 根据渠道信息查询品类
	 * 
	 * @Methods Name selectByChannel
	 * @Create In 2015年10月12日 By duanzhaole
	 * @param pc
	 * @return List<PcmCategory>
	 */
	public List<CategoryByChannelVo> selectByChannel(Map<String, Object> mapParams);

	List<PcmCategory> selectListOrParam(Map<String, Object> mapParams);

	/**
	 * 查询所有商品品类（展示分类）(CMS)
	 * 
	 * @Methods Name selectCateListCms
	 * @Create In 2015年10月29日 By zhangxy
	 * @return List<PcmCategory>
	 */
	List<PcmCategory> selectCateListCms();

	Integer getCategoryButton();

	public List<PcmCategory> selectCateInfoToSap(Long cateSid);

	List<PcmCategory> selectListByParam(PcmCategory entity);

	PcmCategory insertGlCategory(PcmCategory record);

	List<PublishCategoryDto> selectCategoryForPublish(Map<String, Object> maps);

	public List<PcmCategory> selectShowCatesByChannelSid(Long channelSid);

	String uploadeManagerCategory(PcmAddCategoryDto cateDto);

	/**
	 * 根据当前分类参数查询分类的所有上级
	 *
	 * @return List<Map<String, Object>>
	 * @Methods Name findAllParentCategoryByParam
	 * @Create In 2016年2月29日 By wangxuan
	 */
	List<Map<String, Object>> findAllParentCategoryByParam(PcmCategoryQueryDto dto);

	void deleteCateCache(String key);

	/**
	 * 根据管理分类编码获取子节点编码
	 * 
	 * @Methods Name getChildNodeCode
	 * @Create In 2016-4-12 By wangc
	 * @param paraMap
	 * @return List<Map<String,Object>>
	 */
	List<Map<String, String>> getChildNodeCode(Map<String, Object> paraMap);

	String uploadeManagerCategoryDS(PcmAddCategoryDto cateDto);
	/**
	 * 根据专柜商品编码获取对应的展示分类属性属性值
	 * @Methods Name getPropNameAndValueByShoprosid
	 * @Create In 2016年6月7日 By wangc
	 * @param paraMap
	 * @return List<Map<String,String>>
	 */
	List<Map<String ,String>> getPropNameAndValueByShoprosid(Map<String,Object> paraMap);

	String uploadManagerCateFromErp(PcmAddCategoryDto cateDto);

	String uploadManagerCateFromSap(PcmAddCategoryDto cateDto);

	List<PcmCategory> getCategoryByParam(SelectCategoryParamDto pcmcatedto);
}

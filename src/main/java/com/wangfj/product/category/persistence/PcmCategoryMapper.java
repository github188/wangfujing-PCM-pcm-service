package com.wangfj.product.category.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.category.domain.entity.PcmCategory;
import com.wangfj.product.category.domain.vo.CategoryByChannelVo;
import com.wangfj.product.category.domain.vo.CategoryVO;
import com.wangfj.product.category.domain.vo.ClassDTO;
import com.wangfj.product.category.domain.vo.CriteriaVO;
import com.wangfj.product.category.domain.vo.PcmAddCategoryDto;
import com.wangfj.product.category.domain.vo.PcmCategoryQueryDto;
import com.wangfj.product.category.domain.vo.ProductCateVO;
import com.wangfj.product.category.domain.vo.PublishCategoryDto;
import com.wangfj.product.category.domain.vo.SelectCategoryParamDto;
import com.wangfj.util.mq.PublishDTO;

/**
 * 品类信息mapper
 * 
 * @Class Name PcmCategoryMapper
 * @Author duanzhaole
 * @Create In 2015年7月3日
 */
public interface PcmCategoryMapper extends BaseMapper<PcmCategory> {
	List<PcmCategory> getCateInfoBySpuCode(Map<String, Object> para);

	int deleteByPrimaryKey(Long sid);

	int insertSelective(PcmCategory record);

	PcmCategory selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmCategory record);

	int updateByListSelective(Map<String, Object> para);

	int updateByPrimaryKey(PcmCategory record);

	/**
	 * 通过品类编码查询
	 * 
	 * @Methods Name selectByCategorySid
	 * @Create In 2015年8月7日 By duanzhaole
	 * @param categorySid
	 * @return PcmCategory
	 */
	PcmCategory selectByCategorySid(Long categorySid);

	PcmCategory selectByGLCategorySid(PcmAddCategoryDto cateDto);

	/**
	 * 关联查询品类信息
	 * 
	 * @Methods Name selectListByParam
	 * @Create In 2015年7月2日 By duanzhaole
	 * @param paramMap
	 * @return List<PcmCategory>
	 */
	List<HashMap<String, Object>> selectListInnerByParam(Map<String, Object> paramMap);

	/**
	 * 查询分类下发
	 * 
	 * @Methods Name publishCategoryFromPCM
	 * @Create In 2015年7月14日 By yedong
	 * @return List<HashMap<String,Object>>
	 */
	List<PublishCategoryDto> publishCategoryFromPCM(Map<String, Object> paramMap);

	/**
	 * 查询分类下发记录数
	 * 
	 * @Methods Name getCountCategoryFromPCM
	 * @Create In 2015年7月29日 By duanzhaole
	 * @param paramMap
	 * @return int
	 */
	int getCountCategoryFromPCM(Map<String, Object> paramMap);

	/**
	 * 查询分类count以dto为参数
	 * 
	 * @Methods Name getCategpryCount
	 * @Create In 2015年7月15日 By duanzhaole
	 * @param paramMap
	 * @return int
	 */
	int getCategpryCount(SelectCategoryParamDto pcmcate);

	/**
	 * 查询管理分类
	 * 
	 * @Methods Name publishManageCategoryFromPCM
	 * @Create In 2015年7月20日 By duanzhaole
	 * @param paramMap
	 * @return List<HashMap<String,Object>>
	 */

	/**
	 * 根据父节点查询
	 * 
	 * @Methods Name selectByCategoryCode
	 * @Create In 2015年7月21日 By duanzhaole
	 * @param categoryCode
	 * @return PcmCategory
	 */
	List<PcmCategory> selectByParentSid(Map<String, Object> mapParam);

	/**
	 * 根据类别sid查询品类信息
	 * 
	 * @Methods Name selectByCategorySid
	 * @Create In 2015年7月27日 By duanzhaole
	 * @param sid
	 * @return PcmCategory
	 */
	PcmCategory selectByCategorySid(String sid);

	/**
	 * 通过参数查询品类列表
	 * 
	 * @Methods Name selectCategoryListByParam
	 * @Create In 2015年7月29日 By duanzhaole
	 * @param mapPrams
	 * @return List<HashMap<String,Object>>
	 */
	List<PcmCategory> selectCategoryListByParam(SelectCategoryParamDto pcmcatedto);

	/**
	 * 通过管理分类sid查询专柜商品信息
	 * 
	 * @Methods Name selectShoppeByCateSid
	 * @Create In 2015年7月29日 By duanzhaole
	 * @param sid
	 * @return List<HashMap<String,Object>>
	 */
	List<HashMap<String, Object>> selectShoppeByCateSid(SelectCategoryParamDto categoryParam);

	/**
	 * 通过管理分类sid查询专柜商品信息count
	 * 
	 * @Methods Name getShoppeCountBySid
	 * @Create In 2015年7月29日 By duanzhaole
	 * @param sid
	 * @return int
	 */
	int getShoppeCountBySid(Long sid);

	/**
	 * 根据指定的ID找出相应的品类
	 * 
	 * @Methods Name selectListIn
	 * @Create In 2015年7月30日 By duanzhaole
	 * @param cateIds
	 * @return List<PcmCategory>
	 */
	List<PcmCategory> selectListIn(List<Long> cateIds);

	/**
	 * 根据条件查询参数为实体类
	 * 
	 * @Methods Name selectPageListByParam
	 * @Create In 2015年7月30日 By duanzhaole
	 * @param cate
	 * @return List<PcmCategory>
	 */
	List<PcmCategory> selectPageListByParam(PcmCategory cate);

	/**
	 * 查询分类
	 * <p>
	 * 查询分类的类型用in()
	 * 
	 * @Methods Name selectPageListByCategoryVO
	 * @Create In 2015年8月31日 By wangsy
	 * @param cate
	 * @return List<PcmCategory>
	 */
	List<PcmCategory> selectPageListByCategoryVO(CategoryVO cate);

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
	 * 跟节点父节点
	 * 
	 * @Methods Name selectRootParentName
	 * @Create In 2015年7月30日 By duanzhaole
	 * @param categorySid
	 * @return PcmCategory
	 */
	PcmCategory selectRootParentName(Long categorySid);

	/**
	 * 查询所有品类
	 * 
	 * @Methods Name getAll
	 * @Create In 2015年7月31日 By duanzhaole
	 * @return List<PcmCategory>
	 */
	List<PcmCategory> getAll();

	List<ProductCateVO> selectCateVOList(CriteriaVO record);

	List<PcmCategory> selectByProductSid(long productSid);

	String selectProCount(PcmCategory record);

	/**
	 * 根据name查询
	 * 
	 * @return
	 */
	PcmCategory selectCategoryByName(HashMap<String, Object> mapparam);

	/**
	 * 按父产品分类SID查询所有子分类
	 * 
	 * @param record
	 * @return
	 */
	List<ClassDTO> selectCategory(PcmCategory record);

	/**
	 * 批量添加
	 * 
	 * @Methods Name insertBatch
	 * @Create In 2015年7月31日 By duanzhaole
	 * @param list
	 * @return int
	 */
	int insertBatch(List<PcmCategory> list);

	int deleteCate(@Param("p1") String categorySid, @Param("p2") Long channelSid);

	int updateCate(PcmCategory record);

	List<PcmCategory> selectByRootSid(PcmCategory category);

	int selectProCount(Long categorySid);

	List<Map<String, Object>> getIndustryCategorys();

	/**
	 * 根据品类类型查询
	 * 
	 * @Methods Name getCountByCategoryType
	 * @Create In 2015年9月18日 By duanzhaole
	 * @param categoryType
	 * @return int
	 */
	int getCountByCategoryType();

	/**
	 * 通过条件查询实体对象
	 * 
	 * @Methods Name getByParentSid
	 * @Create In 2015年9月29日 By duanzhaole
	 * @param cateparm
	 * @return PcmCategory
	 */
	PcmCategory getByParams(PcmCategory cateparm);

	/**
	 * 通过渠道信息查询品类
	 * 
	 * @Methods Name selectByChannel
	 * @Create In 2015年10月12日 By duanzhaole
	 * @param pc
	 * @return List<PcmCategory>
	 */
	List<CategoryByChannelVo> selectByChannel(Map<String, Object> mapParams);

	/**
	 * or条件查询
	 * 
	 * @Methods Name selectListOrParam
	 * @Create In 2015年10月26日 By duanzhaole
	 * @param mapParams
	 * @return List<PcmCategory>
	 */
	List<PcmCategory> selectListOrParam(Map<String, Object> mapParams);

	/**
	 * 通过cateSid查询所有子节点
	 * 
	 * @Methods Name selectAllSubNodeByCateSid
	 * @Create In 2015年11月4日 By yedong
	 * @param paramMap
	 * @return List<String>
	 */
	List<PcmCategory> selectAllSubNodeByCateSid(Map<String, Object> paramMap);

	/**
	 * 根据展示分类Sid查询展示分类信息下发
	 * 
	 * @Methods Name selectCateBySidList
	 * @Create In 2015年11月11日 By zhangxy
	 * @param List
	 * @return List<PcmCategory>
	 */
	List<PcmCategory> selectCateBySidList(List<PublishDTO> list);

	/**
	 * 根据参数查询分类的所有上级
	 *
	 * @Methods Name selectCateBySidList
	 * @Create In 2016年2月29日 By wangxuan
	 * @param dto
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> findCategoryByParam(PcmCategoryQueryDto dto);

	/**
	 * 根据管理分类编码获取子节点编码
	 * 
	 * @Methods Name getChildNodeCodeByParentCode
	 * @Create In 2016-4-12 By wangc
	 * @param paraMap
	 * @return List<Map<String,Object>>
	 */
	List<Map<String, String>> getChildNodeCodeByParentCode(Map<String, Object> paraMap);
	/**
	 * 拍照系统根据专柜商品编码获取展示分类及展示分类属性值
	 * @Methods Name getPropNameAndValueByShoprosid
	 * @Create In 2016年6月7日 By wangc
	 * @param paraMap
	 * @return List<Map<String,String>>
	 */
	List<Map<String,String>> getPropNameAndValueByShoprosid(Map<String,Object> paraMap);
}
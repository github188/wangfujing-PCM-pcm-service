package com.wangfj.product.category.service.intf;

import java.util.List;
import java.util.Map;

import com.wangfj.product.category.domain.entity.PcmCategory;
import com.wangfj.product.category.domain.vo.ClassDTO;
import com.wangfj.product.category.domain.vo.JsonCate;
import com.wangfj.product.category.domain.vo.MwCateVO;
import com.wangfj.product.category.domain.vo.NavigationVO;
import com.wangfj.product.category.domain.vo.ProductCateVO;
import com.wangfj.product.category.domain.vo.ProductQueryVO;

public interface ISCategoryService {

	// 查询所有品类
	List<PcmCategory> getAll();

	int delete(Long sid);

	int deleteCate(PcmCategory sc);

	int insert(PcmCategory record);

	int insertBatch(List<PcmCategory> list);

	int saveorupdate(PcmCategory record);

	int save(List<PcmCategory> list);

	PcmCategory get(Long sid);

	int update(PcmCategory record);

	int modify(PcmCategory record);

	// int getProCount(Long categorySid, Long channelSid);

	NavigationVO getSubCateList(String sid, String categorySid, String channelSid);

	Integer getMaxSortOrder(String parentSid, Long channelSid);

	List<PcmCategory> selectList(PcmCategory record);

	List<PcmCategory> selectByRootSid(PcmCategory record);

	List<PcmCategory> getByParentSid(String parentSid);

	List<PcmCategory> selectListIn(List<Long> cateIds);

	List<PcmCategory> selectByProductSid(long productSid);

	String getCateString(Long productSid);

	ProductCateVO findCurrentCateList(ProductQueryVO queryVO);

	PcmCategory getByCategorySidAndChannelSid(String categorySid, Long channelSid);

	Map<Long, JsonCate> getCateJSON(Long productSid);

	// Map<Long, JsonCate> getCateJSON2(Long brandSid);

	/**
	 * 按父产品分类SID查询所有子分类
	 * 
	 * @param record
	 * @return
	 */
	List<ClassDTO> selectCategory(PcmCategory record);

	int updateCate(PcmCategory record);

	List<PcmCategory> getByParentSidChannelSid(PcmCategory record);

	PcmCategory getByCategorySidChannelSid(PcmCategory record);

	List<PcmCategory> selectCategoryList(Long channelSid);

	/**
	 * moblie 5
	 * 
	 * @param sid
	 * @return
	 */
	public List<MwCateVO> selectMwCategory(Long sid);

	/**
	 * 门店系统,Pad系统专用接口 返回当前节点的父节点或子节点
	 * 
	 * @param categorySid
	 *            当前节点的ID
	 * @param channelSid
	 * @param flag
	 *            0返回父节点 1返回子节点
	 * @return
	 */
	List<PcmCategory> getSubOrPaNode(String categorySid, String channelSid, String flag);

	/**
	 * 获取所有分类 根据参数获取 一二三级
	 * 
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getProductStanCategorys(Map map);

	List<PcmCategory> getByParentSidAndChannelSid(String parentSid, Long channelSid,
			String categoryType, String shopSid);

	List<PcmCategory> getByParentSidAndChannelSidCache(String parentSid, Long channelSid,
			String categoryType, String shopSid);

}

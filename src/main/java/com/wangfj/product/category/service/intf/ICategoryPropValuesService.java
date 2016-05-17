package com.wangfj.product.category.service.intf;

import java.util.List;
import java.util.Map;

import com.wangfj.product.category.domain.entity.PcmCategory;
import com.wangfj.product.category.domain.entity.PcmCategoryPropValues;
import com.wangfj.product.category.domain.vo.PcmPropsDictsDto;
import com.wangfj.product.category.domain.vo.ValuesVO;

public interface ICategoryPropValuesService {

	int delete(Long sid);

	int deleteByProperties(String categorySid, Long propSid, Long channelSid);

	int deleteByProperties(String categorySid, Long channelSid);

	int deleteByPropAndChan(Long propsSid, Long channelSid);

	int deleteByValueAndChan(Long valueSid, Long channelSid);

	int save(PcmCategoryPropValues record);

	int saveorupdate(PcmCategoryPropValues record);

	int saveorupdate(List<PcmCategoryPropValues> list);

	int save(List<PcmCategoryPropValues> list);

	int deleteorupdate(PcmCategoryPropValues record);

	PcmCategoryPropValues get(Long sid);

	int update(PcmCategoryPropValues record);

	List<PcmCategoryPropValues> selectList(PcmCategoryPropValues record);

	List<PcmCategoryPropValues> selectPage(PcmCategoryPropValues record);

	int selectPageTotal(PcmCategoryPropValues record);

	List<PcmCategoryPropValues> selectPropsVOList(PcmCategoryPropValues record);

	List<PcmCategoryPropValues> selectPropsVO(PcmCategoryPropValues record);

	int selectPropsVOTotal(PcmCategoryPropValues record);

	List<PcmCategoryPropValues> selectCateVO(PcmCategoryPropValues record);

	List<PcmCategoryPropValues> selectPropsValueList(String categorySid, Long channelSid);

	/**
	 * 根据品类Id与属性Id获取属性值
	 * 
	 * @Methods Name getAllCategoryPropsVOs
	 * @Create In 2015-3-5 By chengsj
	 * @param record
	 * @return List<ValuesVO>
	 */
	List<ValuesVO> getAllCategoryValuesVOs(Map map);

	public PcmCategoryPropValues selectByPropsSid(PcmPropsDictsDto PropsSid);

	/**
	 * 属性继承
	 * 
	 * @Methods Name propInheritance
	 * @Create In 2015年11月4日 By yedong
	 * @param cateSid
	 * @param list
	 * @throws Exception
	 *             void
	 */
	public void propInheritance(String cateSid, List<PcmCategoryPropValues> list) throws Exception;

	/**
	 * 按分类SID查询分类属性信息
	 * 
	 * @Methods Name propSelect
	 * @Create In 2015年11月4日 By yedong
	 * @param cateSid
	 * @return List<PcmCategoryPropValues>
	 */
	public List<PcmCategoryPropValues> propSelect(String cateSid);

	/**
	 * 查询所有子节点
	 * 
	 * @Methods Name selectAllSubNodeByCateSid
	 * @Create In 2015年11月4日 By yedong
	 * @param cateSid
	 *            void
	 */
	public List<PcmCategory> selectAllSubNodeByCateSid(String cateSid);

	public void selectList_1();

	/**
	 * 删除所有子节点关联关系
	 * 
	 * @Methods Name propInheritanceDelete
	 * @Create In 2015年11月4日 By yedong
	 * @param cateSid
	 *            void
	 */
	public void propInheritanceDelete(String cateSid, List<PcmCategoryPropValues> list);

	/**
	 * 拖拽继承属性
	 * 
	 * @Methods Name DragInheritance
	 * @Create In 2015年11月5日 By yedong
	 * @param parentSid
	 * @param cateSid
	 *            void
	 */
	public void dragInheritance(String parentSid, String cateSid, String oldCateSid);

	/**
	 * 清空递归列表
	 * 
	 * @Methods Name clearSubList
	 * @Create In 2015年11月19日 By zhangxy void
	 */
	void clearSubList();

	/**
	 * 查询所有父节点(不包括本身)
	 * 
	 * @Methods Name selectAllSupNodeByCateSid
	 * @Create In 2015年11月20日 By yedong
	 * @param cateSid
	 *            void
	 */
	public List<PcmCategory> selectAllSupNodeByCateSid(String cateSid);

	List<PcmCategory> selectAllSupNodeBySpuCode(String key);
}

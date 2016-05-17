package com.wangfj.product.category.persistence;

import java.util.List;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.category.domain.entity.PcmCategoryPropsDict;
import com.wangfj.product.category.domain.vo.CategoryPropsDictVO;
import com.wangfj.product.category.domain.vo.PcmCategoryPropsDictDto;
import com.wangfj.product.category.domain.vo.PcmCategoryPropsDictPara;
import com.wangfj.product.category.domain.vo.PcmPropsDictsDto;
import com.wangfj.product.category.domain.vo.PropsVO;

/**
 * 分类属性字典mapper
 * @Class Name PcmCategoryPropsDictMapper
 * @Author duanzhaole
 * @Create In 2015年7月3日
 */
public interface PcmCategoryPropsDictMapper extends BaseMapper<PcmCategoryPropsDict> {
    int deleteByPrimaryKey(Long sid);

    int insertSelective(PcmCategoryPropsDict record);

    PcmCategoryPropsDict selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(PcmCategoryPropsDict record);

    int updateByPrimaryKey(PcmCategoryPropsDict record);

	List<PcmCategoryPropsDict> selectList(PcmCategoryPropsDict record);

	/**
	 * 查询属性值列表
	 * <p>
	 * 因为需要根据渠道查询所以改为in()
	 * 
	 * @Methods Name selectListInChannelSid
	 * @Create In 2015年8月31日 By wangsy
	 * @param record
	 * @return List<PcmCategoryPropsDict>
	 */
	List<PcmCategoryPropsDict> selectListInChannelSid(CategoryPropsDictVO record);

	List<PcmCategoryPropsDictDto> selectPage(PcmCategoryPropsDict record);

	int selectPageTotal(PcmCategoryPropsDict record);
	
	/**
	 * 根据品类父id查询属性字典
	 * @Methods Name selectPropsDictByParentSid
	 * @Create In 2015年8月13日 By duanzhaole
	 * @return List<PcmCategoryPropsDict>
	 */
	List<PcmCategoryPropsDict> selectPropsDictByParentSid(PcmPropsDictsDto propdDto);
	
	int selectCountPropsDictByParentSid(PcmPropsDictsDto propdDto);
	
	/**
	 * 根据分类叶子节点查询所有属性字典
	 * @Methods Name selectPropsDictByCateSid
	 * @Create In 2015年8月20日 By duanzhaole
	 * @param Sid
	 * @return List<PcmCategoryPropsDict>
	 */
	List<PropsVO> selectPropsDictByCateSid(Long Sid);
	
	/**
	 * 针对查询所有展示分类属性
	 * 
	 * @Methods Name selectPropsDictByCateSidZSFL
	 * @Create In 2015年10月12日 By wangsy
	 * @param Sid
	 * @return List<PropsVO>
	 */
	List<PropsVO> selectPropsDictByCateSidZSFL(Long Sid);

	/**
	 * 根据分类叶子节点查询所有属性字典条数
	 * 
	 * @Methods Name selectPropsDictByCateSid
	 * @Create In 2015年8月20日 By duanzhaole
	 * @param Sid
	 * @return List<PcmCategoryPropsDict>
	 */
	int selectCountPropsDictByCateSid(Long sid);
	
	/**
	 * 根据品类sid 查询所有属性和属性值（用于搜索）
	 * @Methods Name selectPropsDictByCategorySid
	 * @Create In 2015年8月31日 By duanzhaole
	 * @param propdict
	 * @return List<PcmCategoryPropsDict>
	 */
	List<PropsVO> selectPropsDictByCategorySid(PcmCategoryPropsDictPara para);

	/**
	 * @Methods Name selectPageLikeTotal
	 * @Create In 2016-1-4 By wangc
	 * @param record
	 * @return int
	 */
	int selectPageLikeTotal(PcmCategoryPropsDict record);
}
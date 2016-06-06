/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.core.service.implCategoryService.java
 * @Create By duanzhaole
 * @Create In 2015年7月2日 下午2:40:35
 * TODO
 */
package com.wangfj.product.category.service.impl;

//import com.wangfj.core.cache.RedisVo;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangfj.core.constants.ComErrorCodeConstants;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.core.utils.RedisUtil;
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
import com.wangfj.product.category.persistence.PcmCategoryMapper;
import com.wangfj.product.category.persistence.PcmCategoryPropValuesMapper;
import com.wangfj.product.category.persistence.PcmCategoryPropsDictMapper;
import com.wangfj.product.category.persistence.PcmProductCategoryMapper;
import com.wangfj.product.category.service.intf.ICategoryPropValuesService;
import com.wangfj.product.category.service.intf.ICategoryService;
import com.wangfj.product.category.service.intf.ISCategoryService;
import com.wangfj.product.common.domain.entity.PcmRedis;
import com.wangfj.product.common.service.intf.IPcmRedisService;
import com.wangfj.product.constants.DomainName;
import com.wangfj.product.maindata.domain.entity.PcmProduct;
import com.wangfj.product.maindata.persistence.PcmProductMapper;
import com.wangfj.product.stocks.domain.entity.PcmLockAttribute;
import com.wangfj.product.stocks.persistence.PcmLockAttributeMapper;
import com.wangfj.util.Constants;
//import com.wangfj.core.utils.CacheUtils;
//import net.sf.json.JSONArray;

/**
 * 品类管理service
 *
 * @Class Name CategoryService
 * @Author duanzhaole
 * @Create In 2015年7月2日
 */
@Service
public class CategoryServiceImpl implements ICategoryService {

	private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Autowired
	public PcmCategoryMapper cateMapper;
	@Autowired
	private PcmCategoryPropValuesMapper pcmcatepropvalueMapper;
	@Autowired
	private PcmProductCategoryMapper pcmprocategoryMapper;
	@Autowired
	private PcmProductMapper productMapper;
	@Autowired
	private PcmLockAttributeMapper lockMapper;
	@Autowired
	private ICategoryPropValuesService icpvs;
	@Autowired
	private PcmCategoryPropsDictMapper catePropDictMapper;
	@Autowired
	private ISCategoryService categoryService;
	@Autowired
	private IPcmRedisService redisService;
	@Autowired
	private RedisUtil redisUtil;
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wangfj.product.category.service.intf.ICategoryService#selectList()
	 */
	@Override
	public Page<SelectCategoryParamDto> selectListByParam(SelectCategoryParamDto catedto,
			Page<SelectCategoryParamDto> pagedto) {
		logger.info("start selectList()");
		List<PcmCategory> listcate = new ArrayList<PcmCategory>();
		Page<SelectCategoryParamDto> pagelist = new Page<SelectCategoryParamDto>();
		int count = cateMapper.getCategpryCount(catedto);
		pagedto.setCount(count);
		catedto.setStart(pagedto.getStart());
		catedto.setLimit(pagedto.getLimit());
		listcate = cateMapper.selectCategoryListByParam(catedto);

		if (!listcate.isEmpty()) {
			List<SelectCategoryParamDto> cateList = new ArrayList<SelectCategoryParamDto>();
			for (PcmCategory cate : listcate) {
				SelectCategoryParamDto dto = new SelectCategoryParamDto();
				BeanUtils.copyProperties(cate, dto);
				cateList.add(dto);
			}
			pagelist.setCount(count);
			pagelist.setList(cateList);
			pagelist.setPageSize(catedto.getPageSize());
			pagelist.setCurrentPage(catedto.getCurrenPage());

		} else {
			logger.error("select fail");
			throw new BleException(
					ComErrorCodeConstants.ErrorCode.CATEGORY_NOT_EXIST.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.CATEGORY_NOT_EXIST.getMemo());
		}
		return pagelist;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wangfj.product.category.service.intf.ICategoryService#
	 * publishCategoryFromPCM()
	 */
	public List<PcmCategory> publishCategoryFromPCM(Map<String, Object> maps) {
		logger.info("start pushStatCategoryFromMdErp()，params:" + maps.toString());
		List<PcmCategory> listmap = new ArrayList<PcmCategory>();
		listmap = cateMapper.selectListByParam(maps);
		if (!listmap.isEmpty()) {
			logger.info("select success,return:" + listmap.toString());
		} else {
			logger.error("select fail");
			throw new BleException(ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
		}
		return listmap;
	}

	@Override
	public List<PublishCategoryDto> selectCategoryForPublish(Map<String, Object> maps) {
		List<PublishCategoryDto> listmap = cateMapper.publishCategoryFromPCM(maps);
		return listmap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wangfj.product.category.service.intf.ICategoryService#
	 * uploadeIndustrytCategory(java.util.List)
	 */

	@Override
	@Transactional
	public String uploadeCategory(PcmAddCategoryDto cateDto) {
		logger.info("start uploadeIndustrytCategory(),param:" + cateDto.toString());
		PcmCategory pcmcate = new PcmCategory();
		pcmcate.setIsSelfBuilt(Constants.PUBLIC_1);// 默认设为自建
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		HashMap<String, Object> paramMapcate = new HashMap<String, Object>();
//		Date curren = new Date();
		String message = "";
		int result = 0;
		// 首先在系统中查询需新增工业分类的上级分类，再进行本级工业分类的新增。若未查询到本级分类的上级分类，应先对上级分类依次新增
		// 如果actionCode=A

		if (Constants.A.equals(cateDto.getActionCode())) {
			// 判断新增的分类是不是父级分类（既不是父节点，也没有对应的父类节点）
			if (Constants.PUBLIC_0 == cateDto.getIsParent() || !cateDto.getParentSid().equals("0")) {
				// 如果不是父级分类并且没有对应的上级分类
				if ("".equals(cateDto.getParentSid()) || null == cateDto.getParentSid()) {
					logger.error("该工业分类没有对应的上级分类，需先添加上级分类");
					message = "该工业分类没有对应的上级分类，需先添加上级分类";
					throw new BleException(
							ComErrorCodeConstants.ErrorCode.CATEGORY_SUPERIOR_NOT_EXIST
									.getErrorCode(),
							ComErrorCodeConstants.ErrorCode.CATEGORY_SUPERIOR_NOT_EXIST.getMemo());
				}
				PcmCategory cateType = new PcmCategory();
				// 根据当前传入的parent_sid 判断它的上级状态是不是可用
				if (cateDto.getCategoryType() == 1) {
					cateType = cateMapper.selectByGLCategorySid(cateDto);
				} else {
					cateType = cateMapper.selectByCategorySid(cateDto.getParentSid());
				}
				// 如果状态=N时，状态不可用
				if (cateType == null || Constants.N.equals(cateType.getStatus())) {
					logger.error("上级分类状态为不可用");
					message = "上级分类状态为不可用";
					throw new BleException(
							ComErrorCodeConstants.ErrorCode.CATEGORY_NO_STATUS.getErrorCode(),
							ComErrorCodeConstants.ErrorCode.CATEGORY_NO_STATUS.getMemo());
				}
				// 手工录入编码位数判断
				if (cateDto.getCategoryType() == 2 || cateDto.getCategoryType() == 0) {// 工业或统计分类时
					if (cateType.getCategoryCode() == null) {
						logger.error(ComErrorCodeConstants.ErrorCode.PARENT_CODE_IS_EXIST.getMemo());
						throw new BleException(
								ComErrorCodeConstants.ErrorCode.PARENT_CODE_IS_EXIST.getErrorCode(),
								ComErrorCodeConstants.ErrorCode.PARENT_CODE_IS_EXIST.getMemo());
					}
					if (!cateType.getCategoryCode().equals("0")) {
						if (cateDto.getCategoryCode().length() != (cateType.getCategoryCode()
								.length() + (cateDto.getCategoryType() == 0 ? 2 : 3))) {
							logger.error(ComErrorCodeConstants.ErrorCode.CATE_CODE_LENGTH_ERROR
									.getMemo());
							throw new BleException(
									ComErrorCodeConstants.ErrorCode.CATE_CODE_LENGTH_ERROR
											.getErrorCode(),
									ComErrorCodeConstants.ErrorCode.CATE_CODE_LENGTH_ERROR
											.getMemo());
						}
					} else {
						if (cateDto.getCategoryCode().length() != (cateDto.getCategoryType() == 0 ? 2
								: 3)) {
							logger.error(ComErrorCodeConstants.ErrorCode.CATE_CODE_LENGTH_ERROR
									.getMemo());
							throw new BleException(
									ComErrorCodeConstants.ErrorCode.CATE_CODE_LENGTH_ERROR
											.getErrorCode(),
									ComErrorCodeConstants.ErrorCode.CATE_CODE_LENGTH_ERROR
											.getMemo());
						}
					}
					// 手工录入编码判重
					if (StringUtils.isNotBlank(cateDto.getCategoryCode())) {
						paramMap.clear();
						paramMap.put("categoryCode", cateDto.getCategoryCode());
						paramMap.put("isDisplay", 1);
						paramMap.put("rootSid", cateType.getRootSid());
						List<PcmCategory> cList = cateMapper.selectListByParam(paramMap);
						if (cList != null && cList.size() != 0) {
							logger.error(ComErrorCodeConstants.ErrorCode.CATE_CODE_IS_EXIST
									.getMemo());
							throw new BleException(
									ComErrorCodeConstants.ErrorCode.CATE_CODE_IS_EXIST
											.getErrorCode(),
									ComErrorCodeConstants.ErrorCode.CATE_CODE_IS_EXIST.getMemo());
						}
					}
				}
				// 根据编码判重（针对管理分类）
				if (Constants.PUBLIC_1 == cateDto.getCategoryType()) {
					paramMapcate.put("categorySid", cateDto.getCategorySid());
					paramMapcate.put("isDisplay", 1);
					paramMapcate.put("status", "Y");
					paramMapcate.put("categoryType", cateDto.getCategoryType());
					paramMapcate.put("shopSid", cateDto.getShopSid());
					Integer count2 = cateMapper.getCountByParam(paramMapcate);
					if (count2 > 0) {
						logger.error("分类编码重复");
						message = "分类编码重复";
						throw new BleException(
								ComErrorCodeConstants.ErrorCode.CATEGORY_CATEGORYSID_HAVA
										.getErrorCode(),
								ComErrorCodeConstants.ErrorCode.CATEGORY_CATEGORYSID_HAVA.getMemo());
					}
				}
				// 如果状态可用并且不是父级节点但有对应的上级分类（即叶子节点），则需进行添加
				paramMap.clear();
				paramMap.put("name", cateDto.getName());
				paramMap.put("isDisplay", 1);
				paramMap.put("parentSid", cateDto.getParentSid());
				Integer count = cateMapper.getCountByParam(paramMap);
				// 一个父下没重名节点并且不跟父节点同名
				if (count == Constants.PUBLIC_0 /*
												 * && !cateDto.getName().equals(
												 * cateType.getName())
												 */) {
					// 添加工业分类
					BeanUtils.copyProperties(cateDto, pcmcate);
					String cateName = cateDto.getName();
					pcmcate.setName(cateName.trim());
					// 工业分类
					if (Constants.PUBLIC_0 == cateDto.getCategoryType()) {
						pcmcate.setCategoryType(Constants.INDUSTRYTCATEGORY);
						// 工业分类是全渠道
						pcmcate.setChannelSid((Long.parseLong(Constants.PUBLIC_0 + "")));
						// 清空shopsid
						String shopsid = cateDto.getShopSid();
						shopsid = "";
						pcmcate.setShopSid(shopsid);
						// 管理分类
					} else if (Constants.PUBLIC_1 == cateDto.getCategoryType()) {
						pcmcate.setCategoryType(Constants.MANAGECATEGORY);
						// 添加管理分类非根部节点继承门店sid
						pcmcate.setShopSid(cateDto.getShopSid());
						// 统计分类是全渠道
						pcmcate.setChannelSid((Long.parseLong(Constants.PUBLIC_0 + "")));
						// 统计分类
					} else if (Constants.PUBLIC_2 == cateDto.getCategoryType()) {
						// 统计分类是全渠道
						pcmcate.setChannelSid((Long.parseLong(Constants.PUBLIC_0 + "")));
						pcmcate.setCategoryType(Constants.STATISTICSCATEGORY);
						// 清空shopsid
						String shopsid = cateDto.getShopSid();
						shopsid = "";
						pcmcate.setShopSid(shopsid);
					} else if (Constants.PUBLIC_3 == cateDto.getCategoryType()) {
						// 展示分类
						pcmcate.setCategoryType(Constants.SHOWCATEGORY);
						// 清空shopsid
						String shopsid = cateDto.getShopSid();
						shopsid = "";
						pcmcate.setShopSid(shopsid);
					} else {
						// 默认是工业分类
						pcmcate.setCategoryType(Constants.INDUSTRYTCATEGORY);
						// 清空shopsid
						String shopsid = cateDto.getShopSid();
						shopsid = "";
						pcmcate.setShopSid(shopsid);
					}
					// getCategorycode(cateDto, pcmcate);
					// pcmcate.setCategoryType(Constants.INDUSTRYTCATEGORY);
					pcmcate.setIsParent(Constants.PUBLIC_0);
					// 赋值到is_leaf字段
					pcmcate.setIsLeaf(Constants.Y);

					pcmcate.setRootSid(cateDto.getRootSid());
					// 获取同一父类和渠道的排序最大值
					Integer sortorder = this.getMaxSortOrder(cateDto.getParentSid());
					pcmcate.setSortOrder(sortorder + 1);

					// 如果生效时间为空则默认立即生效，修改分类状态
					// if (null == cateDto.getSuccessTime() ||
					// "".equals(cateDto.getSuccessTime())) {
					// pcmcate.setStatus(Constants.Y);
					// // 如果当前时间在生成时间之后或等于当前时间状态改变为Y，生效
					// } else if (curren.after(cateDto.getSuccessTime())
					// || curren.equals(cateDto.getSuccessTime())) {
					// // 如果当前时间在生成时间之后，则改变类别状态
					// pcmcate.setStatus(Constants.Y);
					// // 如果当前时间在生效时间之前，则状态为N，未生效
					// } else if (curren.before(cateDto.getSuccessTime())) {
					// pcmcate.setStatus(Constants.N);
					// }
					pcmcate.setStatus(cateDto.getStatus());
					SelectCategoryParamDto paramcate = new SelectCategoryParamDto();
					if (cateDto.getRootSid() == null) {
						pcmcate.setRootSid(cateType.getRootSid());
					} else {
						pcmcate.setRootSid(cateDto.getRootSid());
					}
					pcmcate.setIsDisplay(Constants.PUBLIC_1);// 默认为显示
					pcmcate.setIsSelfBuilt(Constants.PUBLIC_1);
					Integer results = cateMapper.insertSelective(pcmcate);
					paramcate.setSid(Long.parseLong(pcmcate.getParentSid()));
					// 通过返回当前获取下一级条数
					// Integer nexCount =
					// cateMapper.getCategpryCount(paramcate);
					// 修改父级字段
					PcmAddCategoryDto p = new PcmAddCategoryDto();
					String parentsid = pcmcate.getParentSid();
					p.setParentSid(parentsid);
					p.setShopSid(pcmcate.getShopSid());
					PcmCategory uppcm = new PcmCategory();
					if (cateDto.getCategoryType() == 1) {
						uppcm = cateMapper.selectByGLCategorySid(cateDto);
					} else {
						uppcm = cateMapper.selectByCategorySid(cateDto.getParentSid());
					}
					// PcmCategory uppcm = new PcmCategory();
					if (uppcm != null) {
						// uppcm =
						// cateMapper.selectByCategorySid(Long.parseLong(p.getCategorySid()));
						// uppcm =
						// cateMapper.selectByCategorySid(p.getCategorySid());
						uppcm.setIsParent(Constants.PUBLIC_1);
						uppcm.setIsLeaf(Constants.N);
						cateMapper.updateByPrimaryKey(uppcm);
//						RedisVo vo2 = new RedisVo();
//						vo2.setKey(uppcm.getParentSid());
//						vo2.setField(DomainName.selectCateGory);
//						vo2.setType(CacheUtils.HDEL);
//						CacheUtils.setRedisData(vo2);
						deleteCateCache(uppcm.getSid()+"");
					}
					// 生成编码
					// 如果传入的是管理分类增，编码不自动生成
					if (cateDto.getCategoryType() == Constants.PUBLIC_1) {
						pcmcate.setCategorySid(cateDto.getCategorySid());
					} else {
						pcmcate.setCategorySid(pcmcate.getSid() + "");
					}
					if (StringUtils.isNotBlank(cateDto.getCategoryCode())) {
						pcmcate.setCategoryCode(cateDto.getCategoryCode());// 手工录入编码
					} else {
						pcmcate.setCategoryCode(pcmcate.getCategorySid());// 没有传入时取SID
					}
					pcmcate.setChannelSid(uppcm.getChannelSid());
					pcmcate.setCategoryType(uppcm.getCategoryType());
					if (Constants.PUBLIC_1 == uppcm.getCategoryType()) {
						pcmcate.setShopSid(uppcm.getShopSid());
					}
					pcmcate.setParentSid(cateType.getSid().toString());
					cateMapper.updateByPrimaryKeySelective(pcmcate);
					icpvs.dragInheritance(pcmcate.getParentSid(), String.valueOf(pcmcate.getSid()),
							"0");
					if (results.intValue() != Constants.PUBLIC_1) {
						logger.error("添加失败");
						// throw new BleException("02", "添加失败");
						message = "添加失败";
						throw new BleException(
								ComErrorCodeConstants.ErrorCode.CATEGORY_ADD_ERROR.getErrorCode(),
								ComErrorCodeConstants.ErrorCode.CATEGORY_ADD_ERROR.getMemo());
					}
					logger.info("add success");
					message = "添加成功";
					// 缓存删除
//					RedisVo vo2 = new RedisVo();
//					vo2.setKey(pcmcate.getParentSid());
//					vo2.setField(DomainName.selectCateGory);
//					vo2.setType(CacheUtils.HDEL);
//					CacheUtils.setRedisData(vo2);
					deleteCateCache(uppcm.getParentSid());
					return message;
				} else {
					logger.info("该分类已经存在");
					message = "该分类已经存在";
					throw new BleException(
							ComErrorCodeConstants.ErrorCode.CATEGORY_IS_HAVA.getErrorCode(),
							ComErrorCodeConstants.ErrorCode.CATEGORY_IS_HAVA.getMemo());
				}
				// 直接添加分类的根部节点
			} else if (Constants.PUBLIC_1 == cateDto.getIsParent()
					|| cateDto.getParentSid().equals("0")) {
				// 判断是否为工业分类或统计分类,工业分类或统计分类只能有一套有效的
				if (Constants.PUBLIC_0 == cateDto.getCategoryType()
						|| Constants.PUBLIC_2 == cateDto.getCategoryType()) {
					paramMap.clear();
					paramMap.put("categoryType", cateDto.getCategoryType());
					paramMap.put("status", "Y");
					paramMap.put("parentSid", 0);
					Integer count = cateMapper.getCountByParam(paramMap);
					if (count > Constants.PUBLIC_0) {
						logger.error("只能有一套有效的工业分类或统计分类");
						message = "只能有一套有效的工业分类或统计分类";
						throw new BleException(
								ComErrorCodeConstants.ErrorCode.CATEGORY_ONE.getErrorCode(),
								ComErrorCodeConstants.ErrorCode.CATEGORY_ONE.getMemo());
					}
				}
				// 根据编码判重(针对管理分类)
				if (Constants.PUBLIC_1 == cateDto.getCategoryType()) {
					paramMapcate.put("categorySid", cateDto.getCategorySid());
					paramMapcate.put("isDisplay", 1);
					paramMapcate.put("status", "Y");
					Integer count2 = cateMapper.getCountByParam(paramMapcate);
					if (count2 > 0) {
						logger.error("编码重复");
						message = "编码重复";
						throw new BleException(
								ComErrorCodeConstants.ErrorCode.CATEGORY_CATEGORYSID_HAVA
										.getErrorCode(),
								ComErrorCodeConstants.ErrorCode.CATEGORY_CATEGORYSID_HAVA.getMemo());
					}
				}
				// 如果没有需先添加该分类的上级分类,添加之前对上级工业分类判重,如果有上级分类，则需要通过名称来对下级分类判重
				paramMap.clear();
				paramMap.put("name", cateDto.getName());
				paramMap.put("isDisplay", 1);
				paramMap.put("parentSid", Constants.PUBLIC_0);
				Integer count1 = cateMapper.getCountByParam(paramMap);
				if (count1 == Constants.PUBLIC_0) {
					// 添加上级工业分类
					BeanUtils.copyProperties(cateDto, pcmcate);
					// 工业分类
					if (Constants.PUBLIC_0 == cateDto.getCategoryType()) {
						// 工业分类是全渠道
						pcmcate.setChannelSid((Long.parseLong(Constants.PUBLIC_0 + "")));
						pcmcate.setCategoryType(Constants.INDUSTRYTCATEGORY);
						// 清空shopsid
						String shopsid = cateDto.getShopSid();
						shopsid = "";
						pcmcate.setShopSid(shopsid);
						// 管理分类
					} else if (Constants.PUBLIC_1 == cateDto.getCategoryType()) {
						pcmcate.setCategoryType(Constants.MANAGECATEGORY);
						// 管理分类默认为全渠道
						pcmcate.setChannelSid((Long.parseLong(Constants.PUBLIC_0 + "")));
						// 统计分类
					} else if (Constants.PUBLIC_2 == cateDto.getCategoryType()) {
						// 统计分类是全渠道
						pcmcate.setChannelSid((Long.parseLong(Constants.PUBLIC_0 + "")));
						pcmcate.setCategoryType(Constants.STATISTICSCATEGORY);
						// 清空shopsid
						String shopsid = cateDto.getShopSid();
						shopsid = "";
						pcmcate.setShopSid(shopsid);
					} else if (Constants.PUBLIC_3 == cateDto.getCategoryType()) {
						// 展示分类
						pcmcate.setCategoryType(Constants.SHOWCATEGORY);
						// 清空shopsid
						String shopsid = cateDto.getShopSid();
						shopsid = "";
						pcmcate.setShopSid(shopsid);
					} else {
						// 默认是工业分类
						pcmcate.setCategoryType(Constants.INDUSTRYTCATEGORY);
						pcmcate.setChannelSid((Long.parseLong(Constants.PUBLIC_0 + "")));
						pcmcate.setCategoryType(Constants.INDUSTRYTCATEGORY);
						// 清空shopsid
						String shopsid = cateDto.getShopSid();
						shopsid = "";
						pcmcate.setShopSid(shopsid);
					}
					Map<String, Object> mapParams = new HashMap<String, Object>();
					mapParams.put("parentSid", cateDto.getParentSid());
					// 根据parentsid查询品类信息
					List<PcmCategory> listcate = cateMapper.selectListByParam(mapParams);
					// 赋值到sortorder字段
					pcmcate.setSortOrder(listcate.size() + Constants.PUBLIC_1);
					pcmcate.setIsParent(Constants.PUBLIC_1);
					// getCategorycode(cateDto, pcmcate);
					pcmcate.setParentSid(Constants.PUBLIC_0 + "");
					pcmcate.setIsLeaf(Constants.N);
					// pcmcate.setRootSid(0L);
					if (cateDto.getCategoryType() == 0 || cateDto.getCategoryType() == 2) {
						pcmcate.setLevel(Constants.PUBLIC_0);
					} else if (cateDto.getCategoryType() == 3) {
						pcmcate.setLevel(Constants.PUBLIC_1);
					}
					// 如果生效时间为空则默认立即生效，修改分类状态
					// if (null == cateDto.getSuccessTime() ||
					// "".equals(cateDto.getSuccessTime())) {
					// pcmcate.setStatus(Constants.Y);
					// // 如果当前时间在生成时间之后或等于当前时间状态改变
					// } else if (curren.after(cateDto.getSuccessTime())
					// || curren.equals(cateDto.getSuccessTime())) {
					// // 如果当前时间在生成时间之后，则改变类别状态
					// pcmcate.setStatus(Constants.Y);
					// // 如果当前时间在生效时间之前，则状态为2，未生效
					// } else if (curren.before(cateDto.getSuccessTime())) {
					// pcmcate.setStatus(Constants.N);
					// }
					pcmcate.setStatus(cateDto.getStatus());
					pcmcate.setIsDisplay(Constants.PUBLIC_1);
					pcmcate.setIsSelfBuilt(Constants.PUBLIC_1);
					Integer results = cateMapper.insertSelective(pcmcate);
					// 如果传入的是管理分类增，编码不自动生成
					if (cateDto.getCategoryType() == Constants.PUBLIC_1) {
						pcmcate.setCategorySid(cateDto.getCategorySid());
						pcmcate.setRootSid(pcmcate.getSid());
					} else {
						pcmcate.setCategorySid(pcmcate.getSid() + "");
						pcmcate.setRootSid(pcmcate.getSid());
					}
					if (cateDto.getCategoryType() == 1) {
						pcmcate.setCategoryCode(pcmcate.getCategorySid());// 没有传入时取SID
					}
					if (cateDto.getCategoryType() == 3) {
						pcmcate.setCategoryCode("0");
					}
					cateMapper.updateByPrimaryKeySelective(pcmcate);
					if (results.intValue() != Constants.PUBLIC_1) {
						logger.error("添加失败");
						message = "添加失败";
						throw new BleException(
								ComErrorCodeConstants.ErrorCode.CATEGORY_ADD_ERROR.getErrorCode(),
								ComErrorCodeConstants.ErrorCode.CATEGORY_ADD_ERROR.getMemo());
					}
					logger.info("add success");
					message = "添加成功";
					// 缓存删除
//					RedisVo vo2 = new RedisVo();
//					vo2.setKey("0");
//					vo2.setField(DomainName.selectCateGory);
//					vo2.setType(CacheUtils.HDEL);
//					CacheUtils.setRedisData(vo2);
					deleteCateCache("0");
					return message;
				} else {
					logger.error("添加失败、该上级分类已经存在");
					message = "该分类已经存在";
					throw new BleException(
							ComErrorCodeConstants.ErrorCode.CATEGORY_IS_HAVA.getErrorCode(),
							ComErrorCodeConstants.ErrorCode.CATEGORY_IS_HAVA.getMemo());
				}
			}
			// 如果actioncode==U则执行修改
		} else if (Constants.U.equals(cateDto.getActionCode())) {
			// 根据名称判重
			HashMap<String, Object> paramMaps2 = new HashMap<String, Object>();
			PcmCategory cate1 = null;
			if (cateDto.getCategoryType() == 1) {
				cate1 = cateMapper.selectByGLCategorySid(cateDto);
			} else {
				cate1 = cateMapper.selectByCategorySid(cateDto.getSid());
			}

			if (cate1 == null) {
				throw new BleException(
						ComErrorCodeConstants.ErrorCode.CATE_NOT_IS_EXIST.getErrorCode(),
						ComErrorCodeConstants.ErrorCode.CATE_NOT_IS_EXIST.getMemo());
			}

			paramMaps2.put("name", cateDto.getName());
			paramMaps2.put("isDisplay", 1);
			paramMaps2.put("parentSid", cateDto.getParentSid());
			PcmCategory cate2 = cateMapper.selectByCategorySid(cate1.getParentSid());
			if (StringUtils.isNotBlank(cateDto.getCategoryCode())) {
				// 手工录入编码位数判断
				if (cateDto.getCategoryType() == 2 || cateDto.getCategoryType() == 0) {// 工业或统计分类时
					if (cate1.getLevel() == 1) {
						if (cateDto.getCategoryCode().length() != (cateDto.getCategoryType() == 0 ? 2
								: 3)) {
							throw new BleException(
									ComErrorCodeConstants.ErrorCode.CATE_CODE_LENGTH_ERROR
											.getErrorCode(),
									ComErrorCodeConstants.ErrorCode.CATE_CODE_LENGTH_ERROR
											.getMemo());
						}
					} else {
						if (cate2.getCategoryCode() == null) {
							throw new BleException(
									ComErrorCodeConstants.ErrorCode.PARENT_CODE_IS_EXIST
											.getErrorCode(),
									ComErrorCodeConstants.ErrorCode.PARENT_CODE_IS_EXIST.getMemo());
						}
						if (!cate2.getCategoryCode().equals("0")) {
							if (cateDto.getCategoryCode().length() != (cate2.getCategoryCode()
									.length() + (cateDto.getCategoryType() == 0 ? 2 : 3))) {
								throw new BleException(
										ComErrorCodeConstants.ErrorCode.CATE_CODE_LENGTH_ERROR
												.getErrorCode(),
										ComErrorCodeConstants.ErrorCode.CATE_CODE_LENGTH_ERROR
												.getMemo());
							}
						} else {
							if (cateDto.getCategoryCode().length() != (cateDto.getCategoryType() == 0 ? 2
									: 3)) {
								throw new BleException(
										ComErrorCodeConstants.ErrorCode.CATE_CODE_LENGTH_ERROR
												.getErrorCode(),
										ComErrorCodeConstants.ErrorCode.CATE_CODE_LENGTH_ERROR
												.getMemo());
							}
						}
						// 手工录入编码判重
						if (StringUtils.isNotBlank(cateDto.getCategoryCode())) {
							paramMap.clear();
							paramMap.put("categoryCode", cateDto.getCategoryCode());
							paramMap.put("isDisplay", 1);
							paramMap.put("rootSid", cate2.getRootSid());
							List<PcmCategory> cList = cateMapper.selectListByParam(paramMap);
							if (cList != null && cList.size() != 0) {
								throw new BleException(
										ComErrorCodeConstants.ErrorCode.CATE_CODE_IS_EXIST
												.getErrorCode(),
										ComErrorCodeConstants.ErrorCode.CATE_CODE_IS_EXIST
												.getMemo());
							}
						}
					}
				}
			}
			BeanUtils.copyProperties(cateDto, pcmcate);
			pcmcate.setName(cateDto.getName().trim());
			// 判断超市百货标志（Y/N）
			String isMarket = cateDto.getIsMarket();
			if (Constants.Y.equals(isMarket)) {
				pcmcate.setIsMarket(Constants.PUBLIC_1.toString());
			} else if (Constants.N.equals(isMarket)) {
				pcmcate.setIsMarket(Constants.PUBLIC_0.toString());
			} else if ("".equals(isMarket) || null == isMarket) {
				// 默认是百货
				pcmcate.setIsMarket(Constants.PUBLIC_0.toString());
			}
			pcmcate.setCategorySid(cateDto.getCategorySid());
			pcmcate.setParentSid(cateDto.getParentSid());
			if (StringUtils.isNotBlank(cateDto.getCategoryCode())) {
				pcmcate.setCategoryCode(cateDto.getCategoryCode());// 手工录入编码
			}
			PcmCategory cate = cateMapper.selectCategoryByName(paramMaps2);

			// 根据编码判重(针对管理分类)
			if (Constants.PUBLIC_1 == cateDto.getCategoryType()) {
				HashMap<String, Object> paramMapscate = new HashMap<String, Object>();
				paramMapscate.put("categorySid", cateDto.getCategorySid());
				paramMapcate.put("isDisplay", 1);
				paramMapcate.put("status", "Y");
				PcmCategory pcmcates = cateMapper.selectCategoryByName(paramMapscate);
				if (pcmcates != null) {
					logger.info("分类编码重复");
					message = "分类编码重复";
					throw new BleException(
							ComErrorCodeConstants.ErrorCode.CATEGORY_CATEGORYSID_HAVA
									.getErrorCode(),
							ComErrorCodeConstants.ErrorCode.CATEGORY_CATEGORYSID_HAVA.getMemo());
				}
			}
			if (cate != null) {
				if (cate.getSid().equals(cateDto.getSid())) {
					result = cateMapper.updateByPrimaryKeySelective(pcmcate);
					// 修改品类属性关联表中的品类名称
				} else {
					logger.info("该分类已经存在");
					message = ComErrorCodeConstants.ErrorCode.CATENAME_IS_EXIST.getMemo();
					throw new BleException(
							ComErrorCodeConstants.ErrorCode.CATENAME_IS_EXIST.getErrorCode(),
							ComErrorCodeConstants.ErrorCode.CATENAME_IS_EXIST.getMemo());
				}
			} else {
				result = cateMapper.updateByPrimaryKeySelective(pcmcate);
			}
			/*
			 * if(Constants.PUBLIC_3 == cateDto.getCategoryType() &&
			 * cateDto.getParentSid() == null &&
			 * cateDto.getStatus().equals("Y")){ paramMap.clear();
			 * paramMap.put("channelSid", pcmcate.getChannelSid());
			 * paramMap.put("parentSid", 0); paramMap.put("isDisplay", 1);
			 * paramMap.put("categoryType", 3); List<PcmCategory> cList =
			 * cateMapper.selectListByParam(paramMap); for(PcmCategory pcmCate :
			 * cList){ if(pcmCate.getStatus().equals("Y") &&
			 * !pcmCate.getSid().equals(pcmcate.getSid())){
			 * pcmCate.setStatus("N");
			 * cateMapper.updateByPrimaryKeySelective(pcmCate); } } }
			 */
			paramMap.clear();
			paramMap.put("categorySid", pcmcate.getSid());
			paramMap.put("categoryName", pcmcate.getName());
			pcmcatepropvalueMapper.updateByCategorySid(paramMap);
			if (result == Constants.PUBLIC_0) {
				logger.error("修改失败");
				message = "修改失败";
				throw new BleException(
						ComErrorCodeConstants.ErrorCode.CATEGORY_UPDATE_ERROR.getErrorCode(),
						ComErrorCodeConstants.ErrorCode.CATEGORY_UPDATE_ERROR.getMemo());
			}
			logger.info("update success");
			message = "修改成功";
//			RedisVo vo2 = new RedisVo();
			if (StringUtils.isNotBlank(pcmcate.getParentSid())) {
//				vo2.setKey(pcmcate.getParentSid());
				deleteCateCache(pcmcate.getParentSid());
			} else {
//				vo2.setKey("0");
				deleteCateCache("0");
			}
//			vo2.setField(DomainName.selectCateGory);
//			vo2.setType(CacheUtils.HDEL);
//			CacheUtils.setRedisData(vo2);
//			RedisVo vo = new RedisVo();
			if (StringUtils.isNotBlank(cate1.getParentSid())) {
//				vo.setKey(cate1.getParentSid());
				deleteCateCache(cate1.getParentSid());
			} else {
//				vo.setKey("0");
				deleteCateCache("0");
			}
//			vo.setField(DomainName.selectCateGory);
//			vo.setType(CacheUtils.HDEL);
//			CacheUtils.setRedisData(vo);
			return message;
		} else if (Constants.D.equals(cateDto.getActionCode())) {
			PcmCategory cate1 = null;
			if (cateDto.getCategoryType() == 1) {
				cate1 = cateMapper.selectByGLCategorySid(cateDto);
			} else {
				cate1 = cateMapper.selectByCategorySid(cateDto.getSid());
			}
			pcmcate.setSid(cate1.getSid());
			pcmcate.setStatus(cate1.getStatus());
			if ("Y".equals(cateDto.getStatus())) {
				pcmcate.setIsDisplay(1);
			} else if ("N".equals(cateDto.getStatus())) {
				pcmcate.setIsDisplay(0);
			}
			result = cateMapper.updateByPrimaryKeySelective(pcmcate);
//			RedisVo vo = new RedisVo();
			if (StringUtils.isNotBlank(cate1.getParentSid())) {
//				vo.setKey(cate1.getParentSid());
				deleteCateCache(cate1.getParentSid());
			} else {
//				vo.setKey("0");
				deleteCateCache("0");
			}
//			vo.setField(DomainName.selectCateGory);
//			vo.setType(CacheUtils.HDEL);
//			CacheUtils.setRedisData(vo);
			if (result == Constants.PUBLIC_0) {
				logger.error("修改状态失败");
				message = "修改失败";
				throw new BleException(
						ComErrorCodeConstants.ErrorCode.CATEGORY_UPDATE_ERROR.getErrorCode(),
						ComErrorCodeConstants.ErrorCode.CATEGORY_UPDATE_ERROR.getMemo());
			}
			logger.info("update success");
			message = "修改成功";
			return message;
		}
		return message;
	}

	// 管理分类上传
	@Override
	@Transactional
	public String uploadeManagerCategory(PcmAddCategoryDto cateDto) {
		logger.info("start uploadeManagerCategory(),param:" + cateDto.toString());
		PcmCategory pcmcate = new PcmCategory();
		pcmcate.setIsSelfBuilt(Constants.PUBLIC_1);// 默认设为自建
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		HashMap<String, Object> paramMapcate = new HashMap<String, Object>();
//		Date curren = new Date();
		String message = "";
		int result = 0;
		// 首先在系统中查询需新增工业分类的上级分类，再进行本级工业分类的新增。若未查询到本级分类的上级分类，应先对上级分类依次新增
		// 如果actionCode=A

		if (Constants.A.equals(cateDto.getActionCode())) {
			// 判断新增的分类是不是父级分类（既不是父节点，也没有对应的父类节点）
			if (Constants.PUBLIC_0 == cateDto.getIsParent() || !cateDto.getParentSid().equals("0")) {
				// 如果不是父级分类并且没有对应的上级分类
				if ("".equals(cateDto.getParentSid()) || null == cateDto.getParentSid()) {
					logger.error("该工业分类没有对应的上级分类，需先添加上级分类");
					message = "该工业分类没有对应的上级分类，需先添加上级分类";
					throw new BleException(
							ComErrorCodeConstants.ErrorCode.CATEGORY_SUPERIOR_NOT_EXIST
									.getErrorCode(),
							ComErrorCodeConstants.ErrorCode.CATEGORY_SUPERIOR_NOT_EXIST.getMemo());
				}
				PcmCategory cateType = new PcmCategory();
				// 根据当前传入的parent_sid 判断它的上级状态是不是可用
				if (cateDto.getCategoryType() == 1) {
					cateType = cateMapper.selectByGLCategorySid(cateDto);
				} else {
					cateType = cateMapper.selectByCategorySid(cateDto.getParentSid());
				}
				// 如果状态=N时，状态不可用
				if (cateType == null || Constants.N.equals(cateType.getStatus())) {
					logger.error("上级分类状态为不可用");
					message = "上级分类状态为不可用";
					throw new BleException(
							ComErrorCodeConstants.ErrorCode.CATEGORY_NO_STATUS.getErrorCode(),
							ComErrorCodeConstants.ErrorCode.CATEGORY_NO_STATUS.getMemo());
				}
				// 手工录入编码位数判断
				if (cateDto.getCategoryType() == 2 || cateDto.getCategoryType() == 0) {// 工业或统计分类时
					if (cateType.getCategoryCode() == null) {
						logger.error(ComErrorCodeConstants.ErrorCode.PARENT_CODE_IS_EXIST.getMemo());
						throw new BleException(
								ComErrorCodeConstants.ErrorCode.PARENT_CODE_IS_EXIST.getErrorCode(),
								ComErrorCodeConstants.ErrorCode.PARENT_CODE_IS_EXIST.getMemo());
					}
					if (!cateType.getCategoryCode().equals("0")) {
						if (cateDto.getCategoryCode().length() != (cateType.getCategoryCode()
								.length() + (cateDto.getCategoryType() == 0 ? 2 : 3))) {
							logger.error(ComErrorCodeConstants.ErrorCode.CATE_CODE_LENGTH_ERROR
									.getMemo());
							throw new BleException(
									ComErrorCodeConstants.ErrorCode.CATE_CODE_LENGTH_ERROR
											.getErrorCode(),
									ComErrorCodeConstants.ErrorCode.CATE_CODE_LENGTH_ERROR
											.getMemo());
						}
					} else {
						if (cateDto.getCategoryCode().length() != (cateDto.getCategoryType() == 0 ? 2
								: 3)) {
							logger.error(ComErrorCodeConstants.ErrorCode.CATE_CODE_LENGTH_ERROR
									.getMemo());
							throw new BleException(
									ComErrorCodeConstants.ErrorCode.CATE_CODE_LENGTH_ERROR
											.getErrorCode(),
									ComErrorCodeConstants.ErrorCode.CATE_CODE_LENGTH_ERROR
											.getMemo());
						}
					}
					// 手工录入编码判重
					if (StringUtils.isNotBlank(cateDto.getCategoryCode())) {
						paramMap.clear();
						paramMap.put("categoryCode", cateDto.getCategoryCode());
						paramMap.put("isDisplay", 1);
						paramMap.put("rootSid", cateType.getRootSid());
						List<PcmCategory> cList = cateMapper.selectListByParam(paramMap);
						if (cList != null && cList.size() != 0) {
							logger.error(ComErrorCodeConstants.ErrorCode.CATE_CODE_IS_EXIST
									.getMemo());
							throw new BleException(
									ComErrorCodeConstants.ErrorCode.CATE_CODE_IS_EXIST
											.getErrorCode(),
									ComErrorCodeConstants.ErrorCode.CATE_CODE_IS_EXIST.getMemo());
						}
					}
				}
				// 根据编码判重（针对管理分类）
				if (Constants.PUBLIC_1 == cateDto.getCategoryType()) {
					paramMapcate.put("categorySid", cateDto.getCategorySid());
					paramMapcate.put("isDisplay", 1);
					paramMapcate.put("status", "Y");
					paramMapcate.put("categoryType", cateDto.getCategoryType());
					paramMapcate.put("shopSid", cateDto.getShopSid());
					Integer count2 = cateMapper.getCountByParam(paramMapcate);
					if (count2 > 0) {
						logger.error("分类编码重复");
						message = "分类编码重复";
						throw new BleException(
								ComErrorCodeConstants.ErrorCode.CATEGORY_CATEGORYSID_HAVA
										.getErrorCode(),
								ComErrorCodeConstants.ErrorCode.CATEGORY_CATEGORYSID_HAVA.getMemo());
					}
				}
				// 如果状态可用并且不是父级节点但有对应的上级分类（即叶子节点），则需进行添加
				paramMap.clear();
				paramMap.put("name", cateDto.getName());
				paramMap.put("isDisplay", 1);
				paramMap.put("parentSid", cateDto.getParentSid());
				Integer count = cateMapper.getCountByParam(paramMap);
				// 一个父下没重名节点并且不跟父节点同名
				if (count == Constants.PUBLIC_0 /*
												 * && !cateDto.getName().equals(
												 * cateType.getName())
												 */) {
					// 添加工业分类
					BeanUtils.copyProperties(cateDto, pcmcate);
					String cateName = cateDto.getName();
					pcmcate.setName(cateName.trim());
					// 工业分类
					if (Constants.PUBLIC_0 == cateDto.getCategoryType()) {
						pcmcate.setCategoryType(Constants.INDUSTRYTCATEGORY);
						// 工业分类是全渠道
						pcmcate.setChannelSid((Long.parseLong(Constants.PUBLIC_0 + "")));
						// 清空shopsid
						String shopsid = cateDto.getShopSid();
						shopsid = "";
						pcmcate.setShopSid(shopsid);
						// 管理分类
					} else if (Constants.PUBLIC_1 == cateDto.getCategoryType()) {
						pcmcate.setCategoryType(Constants.MANAGECATEGORY);
						// 添加管理分类非根部节点继承门店sid
						pcmcate.setShopSid(cateDto.getShopSid());
						// 统计分类是全渠道
						pcmcate.setChannelSid((Long.parseLong(Constants.PUBLIC_0 + "")));
						// 统计分类
					} else if (Constants.PUBLIC_2 == cateDto.getCategoryType()) {
						// 统计分类是全渠道
						pcmcate.setChannelSid((Long.parseLong(Constants.PUBLIC_0 + "")));
						pcmcate.setCategoryType(Constants.STATISTICSCATEGORY);
						// 清空shopsid
						String shopsid = cateDto.getShopSid();
						shopsid = "";
						pcmcate.setShopSid(shopsid);
					} else if (Constants.PUBLIC_3 == cateDto.getCategoryType()) {
						// 展示分类
						pcmcate.setCategoryType(Constants.SHOWCATEGORY);
						// 清空shopsid
						String shopsid = cateDto.getShopSid();
						shopsid = "";
						pcmcate.setShopSid(shopsid);
					} else {
						// 默认是工业分类
						pcmcate.setCategoryType(Constants.INDUSTRYTCATEGORY);
						// 清空shopsid
						String shopsid = cateDto.getShopSid();
						shopsid = "";
						pcmcate.setShopSid(shopsid);
					}
					// getCategorycode(cateDto, pcmcate);
					// pcmcate.setCategoryType(Constants.INDUSTRYTCATEGORY);
					pcmcate.setIsParent(Constants.PUBLIC_0);
					// 赋值到is_leaf字段
					pcmcate.setIsLeaf(Constants.Y);

					pcmcate.setRootSid(cateDto.getRootSid());
					// 获取同一父类和渠道的排序最大值
					Integer sortorder = this.getMaxSortOrder(cateDto.getParentSid());
					pcmcate.setSortOrder(sortorder + 1);

					pcmcate.setStatus(cateDto.getStatus());
					SelectCategoryParamDto paramcate = new SelectCategoryParamDto();
					if (cateDto.getRootSid() == null) {
						pcmcate.setRootSid(cateType.getRootSid());
					} else {
						pcmcate.setRootSid(cateDto.getRootSid());
					}
					pcmcate.setIsDisplay(Constants.PUBLIC_1);// 默认为显示
					pcmcate.setIsSelfBuilt(Constants.PUBLIC_1);
					Integer results = cateMapper.insertSelective(pcmcate);
					paramcate.setSid(Long.parseLong(pcmcate.getParentSid()));
					// 通过返回当前获取下一级条数
					// Integer nexCount =
					// cateMapper.getCategpryCount(paramcate);
					// 修改父级字段
					PcmAddCategoryDto p = new PcmAddCategoryDto();
					String parentsid = pcmcate.getParentSid();
					p.setParentSid(parentsid);
					p.setShopSid(pcmcate.getShopSid());
					PcmCategory uppcm = new PcmCategory();
					if (cateDto.getCategoryType() == 1) {
						uppcm = cateMapper.selectByGLCategorySid(cateDto);
					} else {
						uppcm = cateMapper.selectByCategorySid(cateDto.getParentSid());
					}
					// PcmCategory uppcm = new PcmCategory();
					if (uppcm != null) {
						// uppcm =
						// cateMapper.selectByCategorySid(Long.parseLong(p.getCategorySid()));
						// uppcm =
						// cateMapper.selectByCategorySid(p.getCategorySid());
						uppcm.setIsParent(Constants.PUBLIC_1);
						uppcm.setIsLeaf(Constants.N);
						cateMapper.updateByPrimaryKey(uppcm);
//						RedisVo vo2 = new RedisVo();
//						vo2.setKey(uppcm.getParentSid());
//						vo2.setField(DomainName.selectCateGory);
//						vo2.setType(CacheUtils.HDEL);
//						CacheUtils.setRedisData(vo2);
						deleteCateCache(uppcm.getParentSid());
					}
					// 生成编码
					// 如果传入的是管理分类增，编码不自动生成
					if (cateDto.getCategoryType() == Constants.PUBLIC_1) {
						pcmcate.setCategorySid(cateDto.getCategorySid());
					} else {
						pcmcate.setCategorySid(pcmcate.getSid() + "");
					}
					if (StringUtils.isNotBlank(cateDto.getCategoryCode())) {
						pcmcate.setCategoryCode(cateDto.getCategoryCode());// 手工录入编码
					} else {
						pcmcate.setCategoryCode(pcmcate.getCategorySid());// 没有传入时取SID
					}
					pcmcate.setChannelSid(uppcm.getChannelSid());
					pcmcate.setCategoryType(uppcm.getCategoryType());
					if (Constants.PUBLIC_1 == uppcm.getCategoryType()) {
						pcmcate.setShopSid(uppcm.getShopSid());
					}
					pcmcate.setParentSid(cateType.getSid().toString());
					cateMapper.updateByPrimaryKeySelective(pcmcate);
					icpvs.dragInheritance(pcmcate.getParentSid(), String.valueOf(pcmcate.getSid()),
							"0");
					if (results.intValue() != Constants.PUBLIC_1) {
						logger.error("添加失败");
						// throw new BleException("02", "添加失败");
						message = "添加失败";
						throw new BleException(
								ComErrorCodeConstants.ErrorCode.CATEGORY_ADD_ERROR.getErrorCode(),
								ComErrorCodeConstants.ErrorCode.CATEGORY_ADD_ERROR.getMemo());
					}

					Map<String, Object> paramMap1 = new HashMap<String, Object>();
					paramMap1.put("parent_sid", pcmcate.getShopSid() + pcmcate.getCategorySid());
					List<PcmCategory> cateList = cateMapper.selectByParentSid(paramMap1);
					paramMap1.clear();
					if (cateList != null && cateList.size() != 0) {
						pcmcate.setIsParent(Constants.PUBLIC_1);
						pcmcate.setIsLeaf(Constants.N);
						cateMapper.updateByPrimaryKeySelective(pcmcate);
						paramMap1.put("parentSid", pcmcate.getSid());
						paramMap1.put("list", cateList);
						cateMapper.updateByListSelective(paramMap1);
//						RedisVo vo = new RedisVo();
//						vo.setKey(pcmcate.getSid() + "");
//						vo.setField(DomainName.selectCateGory);
//						vo.setType(CacheUtils.HDEL);
//						CacheUtils.setRedisData(vo);
						deleteCateCache(pcmcate.getSid() + "");
					}

					logger.info("add success");
					message = "添加成功";
					// 缓存删除
//					RedisVo vo2 = new RedisVo();
//					vo2.setKey(pcmcate.getParentSid());
//					vo2.setField(DomainName.selectCateGory);
//					vo2.setType(CacheUtils.HDEL);
//					CacheUtils.setRedisData(vo2);
					deleteCateCache(pcmcate.getParentSid());
					return message;
				} else {
					logger.info("该分类已经存在");
					message = "该分类已经存在";
					throw new BleException(
							ComErrorCodeConstants.ErrorCode.CATEGORY_IS_HAVA.getErrorCode(),
							ComErrorCodeConstants.ErrorCode.CATEGORY_IS_HAVA.getMemo());
				}
				// 直接添加分类的根部节点
			} else if (Constants.PUBLIC_1 == cateDto.getIsParent()
					|| cateDto.getParentSid().equals("0")) {
				// 判断是否为工业分类或统计分类,工业分类或统计分类只能有一套有效的
				if (Constants.PUBLIC_0 == cateDto.getCategoryType()
						|| Constants.PUBLIC_2 == cateDto.getCategoryType()) {
					paramMap.clear();
					paramMap.put("categoryType", cateDto.getCategoryType());
					paramMap.put("status", "Y");
					paramMap.put("parentSid", 0);
					Integer count = cateMapper.getCountByParam(paramMap);
					if (count > Constants.PUBLIC_0) {
						logger.error("只能有一套有效的工业分类或统计分类");
						message = "只能有一套有效的工业分类或统计分类";
						throw new BleException(
								ComErrorCodeConstants.ErrorCode.CATEGORY_ONE.getErrorCode(),
								ComErrorCodeConstants.ErrorCode.CATEGORY_ONE.getMemo());
					}
				}
				// 根据编码判重(针对管理分类)
				if (Constants.PUBLIC_1 == cateDto.getCategoryType()) {
					paramMapcate.put("categorySid", cateDto.getCategorySid());
					paramMapcate.put("isDisplay", 1);
					paramMapcate.put("status", "Y");
					Integer count2 = cateMapper.getCountByParam(paramMapcate);
					if (count2 > 0) {
						logger.error("编码重复");
						message = "编码重复";
						throw new BleException(
								ComErrorCodeConstants.ErrorCode.CATEGORY_CATEGORYSID_HAVA
										.getErrorCode(),
								ComErrorCodeConstants.ErrorCode.CATEGORY_CATEGORYSID_HAVA.getMemo());
					}
				}
				// 如果没有需先添加该分类的上级分类,添加之前对上级工业分类判重,如果有上级分类，则需要通过名称来对下级分类判重
				paramMap.clear();
				paramMap.put("name", cateDto.getName());
				paramMap.put("isDisplay", 1);
				paramMap.put("parentSid", Constants.PUBLIC_0);
				Integer count1 = cateMapper.getCountByParam(paramMap);
				if (count1 == Constants.PUBLIC_0) {
					// 添加上级工业分类
					BeanUtils.copyProperties(cateDto, pcmcate);
					// 工业分类
					if (Constants.PUBLIC_0 == cateDto.getCategoryType()) {
						// 工业分类是全渠道
						pcmcate.setChannelSid((Long.parseLong(Constants.PUBLIC_0 + "")));
						pcmcate.setCategoryType(Constants.INDUSTRYTCATEGORY);
						// 清空shopsid
						String shopsid = cateDto.getShopSid();
						shopsid = "";
						pcmcate.setShopSid(shopsid);
						// 管理分类
					} else if (Constants.PUBLIC_1 == cateDto.getCategoryType()) {
						pcmcate.setCategoryType(Constants.MANAGECATEGORY);
						// 管理分类默认为全渠道
						pcmcate.setChannelSid((Long.parseLong(Constants.PUBLIC_0 + "")));
						// 统计分类
					} else if (Constants.PUBLIC_2 == cateDto.getCategoryType()) {
						// 统计分类是全渠道
						pcmcate.setChannelSid((Long.parseLong(Constants.PUBLIC_0 + "")));
						pcmcate.setCategoryType(Constants.STATISTICSCATEGORY);
						// 清空shopsid
						String shopsid = cateDto.getShopSid();
						shopsid = "";
						pcmcate.setShopSid(shopsid);
					} else if (Constants.PUBLIC_3 == cateDto.getCategoryType()) {
						// 展示分类
						pcmcate.setCategoryType(Constants.SHOWCATEGORY);
						// 清空shopsid
						String shopsid = cateDto.getShopSid();
						shopsid = "";
						pcmcate.setShopSid(shopsid);
					} else {
						// 默认是工业分类
						pcmcate.setCategoryType(Constants.INDUSTRYTCATEGORY);
						pcmcate.setChannelSid((Long.parseLong(Constants.PUBLIC_0 + "")));
						pcmcate.setCategoryType(Constants.INDUSTRYTCATEGORY);
						// 清空shopsid
						String shopsid = cateDto.getShopSid();
						shopsid = "";
						pcmcate.setShopSid(shopsid);
					}
					Map<String, Object> mapParams = new HashMap<String, Object>();
					mapParams.put("parentSid", cateDto.getParentSid());
					// 根据parentsid查询品类信息
					List<PcmCategory> listcate = cateMapper.selectListByParam(mapParams);
					// 赋值到sortorder字段
					pcmcate.setSortOrder(listcate.size() + Constants.PUBLIC_1);
					pcmcate.setIsParent(Constants.PUBLIC_1);
					// getCategorycode(cateDto, pcmcate);
					pcmcate.setParentSid(Constants.PUBLIC_0 + "");
					pcmcate.setIsLeaf(Constants.N);
					// pcmcate.setRootSid(0L);
					if (cateDto.getCategoryType() == 0 || cateDto.getCategoryType() == 2) {
						pcmcate.setLevel(Constants.PUBLIC_0);
					} else if (cateDto.getCategoryType() == 3) {
						pcmcate.setLevel(Constants.PUBLIC_1);
					}

					pcmcate.setStatus(cateDto.getStatus());
					pcmcate.setIsDisplay(Constants.PUBLIC_1);
					pcmcate.setIsSelfBuilt(Constants.PUBLIC_1);
					Integer results = cateMapper.insertSelective(pcmcate);
					// 如果传入的是管理分类增，编码不自动生成
					if (cateDto.getCategoryType() == Constants.PUBLIC_1) {
						pcmcate.setCategorySid(cateDto.getCategorySid());
						pcmcate.setRootSid(pcmcate.getSid());
					} else {
						pcmcate.setCategorySid(pcmcate.getSid() + "");
						pcmcate.setRootSid(pcmcate.getSid());
					}
					if (cateDto.getCategoryType() == 1) {
						pcmcate.setCategoryCode(pcmcate.getCategorySid());// 没有传入时取SID
					}
					if (cateDto.getCategoryType() == 3) {
						pcmcate.setCategoryCode("0");
					}
					cateMapper.updateByPrimaryKeySelective(pcmcate);
					if (results.intValue() != Constants.PUBLIC_1) {
						logger.error("添加失败");
						message = "添加失败";
						throw new BleException(
								ComErrorCodeConstants.ErrorCode.CATEGORY_ADD_ERROR.getErrorCode(),
								ComErrorCodeConstants.ErrorCode.CATEGORY_ADD_ERROR.getMemo());
					}
					logger.info("add success");
					message = "添加成功";
					// 缓存删除
//					RedisVo vo2 = new RedisVo();
//					vo2.setKey("0");
//					vo2.setField(DomainName.selectCateGory);
//					vo2.setType(CacheUtils.HDEL);
//					CacheUtils.setRedisData(vo2);
					deleteCateCache("0");
					return message;
				} else {
					logger.error("添加失败、该上级分类已经存在");
					message = "该分类已经存在";
					throw new BleException(
							ComErrorCodeConstants.ErrorCode.CATEGORY_IS_HAVA.getErrorCode(),
							ComErrorCodeConstants.ErrorCode.CATEGORY_IS_HAVA.getMemo());
				}
			}
			// 如果actioncode==D则执行修改
		} else if (Constants.D.equals(cateDto.getActionCode())) {
			PcmCategory cate1 = null;
			PcmCategory pcmCate = new PcmCategory();
			pcmCate.setCategoryCode(cateDto.getCategoryCode());
			pcmCate.setShopSid(cateDto.getShopSid());
			pcmCate.setIsDisplay(1);
			cate1 = cateMapper.getByParams(pcmCate);
			pcmcate.setSid(cate1.getSid());
			pcmcate.setStatus(cate1.getStatus());
			pcmcate.setIsDisplay(0);
			result = cateMapper.updateByPrimaryKeySelective(pcmcate);

			Map<String, Object> paramMap1 = new HashMap<String, Object>();
			paramMap1.put("parent_sid", cate1.getSid());
			List<PcmCategory> cateList = cateMapper.selectByParentSid(paramMap1);
			paramMap1.clear();
			if (cateList != null && cateList.size() != 0) {
				paramMap1.put("parentSid", cate1.getShopSid() + cate1.getCategorySid());
				paramMap1.put("list", cateList);
				cateMapper.updateByListSelective(paramMap1);
//				RedisVo vo = new RedisVo();
//				vo.setKey(cate1.getSid() + "");
//				vo.setField(DomainName.selectCateGory);
//				vo.setType(CacheUtils.HDEL);
//				CacheUtils.setRedisData(vo);
				deleteCateCache(cate1.getSid() + "");
			}

//			RedisVo vo = new RedisVo();
			if (StringUtils.isNotBlank(cate1.getParentSid())) {
//				vo.setKey(cate1.getParentSid());
				deleteCateCache(cate1.getParentSid());
			} else {
//				vo.setKey("0");
				deleteCateCache("0");
			}
//			vo.setField(DomainName.selectCateGory);
//			vo.setType(CacheUtils.HDEL);
//			CacheUtils.setRedisData(vo);
			if (result == Constants.PUBLIC_0) {
				logger.error("修改状态失败");
				message = "修改失败";
				throw new BleException(
						ComErrorCodeConstants.ErrorCode.CATEGORY_UPDATE_ERROR.getErrorCode(),
						ComErrorCodeConstants.ErrorCode.CATEGORY_UPDATE_ERROR.getMemo());
			}
			logger.info("update success");
			message = "修改成功";
			return message;
		}
		return message;
	}

	// 电商管理分类上传
	@Override
	@Transactional
	public String uploadeManagerCategoryDS(PcmAddCategoryDto cateDto) {
		logger.info("start uploadeManagerCategory(),param:" + cateDto.toString());
		PcmCategory pcmcate = new PcmCategory();
		pcmcate.setIsSelfBuilt(Constants.PUBLIC_1);// 默认设为自建
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		HashMap<String, Object> paramMapcate = new HashMap<String, Object>();
		String message = "";
		int result = 0;
		// 首先在系统中查询需新增工业分类的上级分类，再进行本级工业分类的新增。若未查询到本级分类的上级分类，应先对上级分类依次新增
		
		paramMap.put("categoryCode", cateDto.getCategoryCode());
		paramMap.put("isDisplay", 1);
		paramMap.put("shopSid", cateDto.getShopSid());
		List<PcmCategory> cateListes = cateMapper.selectListByParam(paramMap);
		
		if(cateListes == null || cateListes.size() == 0){
			// 判断新增的分类是不是父级分类（既不是父节点，也没有对应的父类节点）
			if (Constants.PUBLIC_0 == cateDto.getIsParent() || !cateDto.getParentSid().equals("0")) {
				// 如果不是父级分类并且没有对应的上级分类
				if ("".equals(cateDto.getParentSid()) || null == cateDto.getParentSid()) {
					logger.error("该工业分类没有对应的上级分类，需先添加上级分类");
					message = "该工业分类没有对应的上级分类，需先添加上级分类";
					throw new BleException(
							ComErrorCodeConstants.ErrorCode.CATEGORY_SUPERIOR_NOT_EXIST
									.getErrorCode(),
							ComErrorCodeConstants.ErrorCode.CATEGORY_SUPERIOR_NOT_EXIST.getMemo());
				}
				PcmCategory cateType = new PcmCategory();
				// 根据当前传入的parent_sid 判断它的上级状态是不是可用
				if (cateDto.getCategoryType() == 1) {
					cateType = cateMapper.selectByGLCategorySid(cateDto);
				} else {
					cateType = cateMapper.selectByCategorySid(cateDto.getParentSid());
				}
				// 如果状态=N时，状态不可用
				if (cateType == null || Constants.N.equals(cateType.getStatus())) {
					logger.error("上级分类状态为不可用");
					message = "上级分类状态为不可用";
					throw new BleException(
							ComErrorCodeConstants.ErrorCode.CATEGORY_NO_STATUS.getErrorCode(),
							ComErrorCodeConstants.ErrorCode.CATEGORY_NO_STATUS.getMemo());
				}
				// 手工录入编码位数判断
				if (cateDto.getCategoryType() == 2 || cateDto.getCategoryType() == 0) {// 工业或统计分类时
					if (cateType.getCategoryCode() == null) {
						logger.error(ComErrorCodeConstants.ErrorCode.PARENT_CODE_IS_EXIST.getMemo());
						throw new BleException(
								ComErrorCodeConstants.ErrorCode.PARENT_CODE_IS_EXIST.getErrorCode(),
								ComErrorCodeConstants.ErrorCode.PARENT_CODE_IS_EXIST.getMemo());
					}
					if (!cateType.getCategoryCode().equals("0")) {
						if (cateDto.getCategoryCode().length() != (cateType.getCategoryCode()
								.length() + (cateDto.getCategoryType() == 0 ? 2 : 3))) {
							logger.error(ComErrorCodeConstants.ErrorCode.CATE_CODE_LENGTH_ERROR
									.getMemo());
							throw new BleException(
									ComErrorCodeConstants.ErrorCode.CATE_CODE_LENGTH_ERROR
											.getErrorCode(),
									ComErrorCodeConstants.ErrorCode.CATE_CODE_LENGTH_ERROR
											.getMemo());
						}
					} else {
						if (cateDto.getCategoryCode().length() != (cateDto.getCategoryType() == 0 ? 2
								: 3)) {
							logger.error(ComErrorCodeConstants.ErrorCode.CATE_CODE_LENGTH_ERROR
									.getMemo());
							throw new BleException(
									ComErrorCodeConstants.ErrorCode.CATE_CODE_LENGTH_ERROR
											.getErrorCode(),
									ComErrorCodeConstants.ErrorCode.CATE_CODE_LENGTH_ERROR
											.getMemo());
						}
					}
					// 手工录入编码判重
					if (StringUtils.isNotBlank(cateDto.getCategoryCode())) {
						paramMap.clear();
						paramMap.put("categoryCode", cateDto.getCategoryCode());
						paramMap.put("isDisplay", 1);
						paramMap.put("rootSid", cateType.getRootSid());
						List<PcmCategory> cList = cateMapper.selectListByParam(paramMap);
						if (cList != null && cList.size() != 0) {
							logger.error(ComErrorCodeConstants.ErrorCode.CATE_CODE_IS_EXIST
									.getMemo());
							throw new BleException(
									ComErrorCodeConstants.ErrorCode.CATE_CODE_IS_EXIST
											.getErrorCode(),
									ComErrorCodeConstants.ErrorCode.CATE_CODE_IS_EXIST.getMemo());
						}
					}
				}
				// 根据编码判重（针对管理分类）
				if (Constants.PUBLIC_1 == cateDto.getCategoryType()) {
					paramMapcate.put("categorySid", cateDto.getCategorySid());
					paramMapcate.put("isDisplay", 1);
					paramMapcate.put("status", "Y");
					paramMapcate.put("categoryType", cateDto.getCategoryType());
					paramMapcate.put("shopSid", cateDto.getShopSid());
					Integer count2 = cateMapper.getCountByParam(paramMapcate);
					if (count2 > 0) {
						logger.error("分类编码重复");
						message = "分类编码重复";
						throw new BleException(
								ComErrorCodeConstants.ErrorCode.CATEGORY_CATEGORYSID_HAVA
										.getErrorCode(),
								ComErrorCodeConstants.ErrorCode.CATEGORY_CATEGORYSID_HAVA.getMemo());
					}
				}
				// 如果状态可用并且不是父级节点但有对应的上级分类（即叶子节点），则需进行添加
				paramMap.clear();
				paramMap.put("name", cateDto.getName());
				paramMap.put("isDisplay", 1);
				paramMap.put("parentSid", cateDto.getParentSid());
				Integer count = cateMapper.getCountByParam(paramMap);
				// 一个父下没重名节点并且不跟父节点同名
				if (count == Constants.PUBLIC_0 /*
												 * && !cateDto.getName().equals(
												 * cateType.getName())
												 */) {
					// 添加工业分类
					BeanUtils.copyProperties(cateDto, pcmcate);
					String cateName = cateDto.getName();
					pcmcate.setName(cateName.trim());
					// 工业分类
					if (Constants.PUBLIC_0 == cateDto.getCategoryType()) {
						pcmcate.setCategoryType(Constants.INDUSTRYTCATEGORY);
						// 工业分类是全渠道
						pcmcate.setChannelSid((Long.parseLong(Constants.PUBLIC_0 + "")));
						// 清空shopsid
						String shopsid = cateDto.getShopSid();
						shopsid = "";
						pcmcate.setShopSid(shopsid);
						// 管理分类
					} else if (Constants.PUBLIC_1 == cateDto.getCategoryType()) {
						pcmcate.setCategoryType(Constants.MANAGECATEGORY);
						// 添加管理分类非根部节点继承门店sid
						pcmcate.setShopSid(cateDto.getShopSid());
						// 统计分类是全渠道
						pcmcate.setChannelSid((Long.parseLong(Constants.PUBLIC_0 + "")));
						// 统计分类
					} else if (Constants.PUBLIC_2 == cateDto.getCategoryType()) {
						// 统计分类是全渠道
						pcmcate.setChannelSid((Long.parseLong(Constants.PUBLIC_0 + "")));
						pcmcate.setCategoryType(Constants.STATISTICSCATEGORY);
						// 清空shopsid
						String shopsid = cateDto.getShopSid();
						shopsid = "";
						pcmcate.setShopSid(shopsid);
					} else if (Constants.PUBLIC_3 == cateDto.getCategoryType()) {
						// 展示分类
						pcmcate.setCategoryType(Constants.SHOWCATEGORY);
						// 清空shopsid
						String shopsid = cateDto.getShopSid();
						shopsid = "";
						pcmcate.setShopSid(shopsid);
					} else {
						// 默认是工业分类
						pcmcate.setCategoryType(Constants.INDUSTRYTCATEGORY);
						// 清空shopsid
						String shopsid = cateDto.getShopSid();
						shopsid = "";
						pcmcate.setShopSid(shopsid);
					}
					// getCategorycode(cateDto, pcmcate);
					// pcmcate.setCategoryType(Constants.INDUSTRYTCATEGORY);
					pcmcate.setIsParent(Constants.PUBLIC_0);
					// 赋值到is_leaf字段
					pcmcate.setIsLeaf(Constants.Y);

					pcmcate.setRootSid(cateDto.getRootSid());
					// 获取同一父类和渠道的排序最大值
					Integer sortorder = this.getMaxSortOrder(cateDto.getParentSid());
					pcmcate.setSortOrder(sortorder + 1);

					pcmcate.setStatus(cateDto.getStatus());
					SelectCategoryParamDto paramcate = new SelectCategoryParamDto();
					if (cateDto.getRootSid() == null) {
						pcmcate.setRootSid(cateType.getRootSid());
					} else {
						pcmcate.setRootSid(cateDto.getRootSid());
					}
					pcmcate.setIsDisplay(Constants.PUBLIC_1);// 默认为显示
					pcmcate.setIsSelfBuilt(Constants.PUBLIC_1);
					Integer results = cateMapper.insertSelective(pcmcate);
					paramcate.setSid(Long.parseLong(pcmcate.getParentSid()));
					// 通过返回当前获取下一级条数
					// Integer nexCount =
					// cateMapper.getCategpryCount(paramcate);
					// 修改父级字段
					PcmAddCategoryDto p = new PcmAddCategoryDto();
					String parentsid = pcmcate.getParentSid();
					p.setParentSid(parentsid);
					p.setShopSid(pcmcate.getShopSid());
					PcmCategory uppcm = new PcmCategory();
					if (cateDto.getCategoryType() == 1) {
						uppcm = cateMapper.selectByGLCategorySid(cateDto);
					} else {
						uppcm = cateMapper.selectByCategorySid(cateDto.getParentSid());
					}
					// PcmCategory uppcm = new PcmCategory();
					if (uppcm != null) {
						// uppcm =
						// cateMapper.selectByCategorySid(Long.parseLong(p.getCategorySid()));
						// uppcm =
						// cateMapper.selectByCategorySid(p.getCategorySid());
						uppcm.setIsParent(Constants.PUBLIC_1);
						uppcm.setIsLeaf(Constants.N);
						cateMapper.updateByPrimaryKey(uppcm);
						deleteCateCache(uppcm.getParentSid());
					}
					// 生成编码
					// 如果传入的是管理分类增，编码不自动生成
					if (cateDto.getCategoryType() == Constants.PUBLIC_1) {
						pcmcate.setCategorySid(cateDto.getCategorySid());
					} else {
						pcmcate.setCategorySid(pcmcate.getSid() + "");
					}
					if (StringUtils.isNotBlank(cateDto.getCategoryCode())) {
						pcmcate.setCategoryCode(cateDto.getCategoryCode());// 手工录入编码
					} else {
						pcmcate.setCategoryCode(pcmcate.getCategorySid());// 没有传入时取SID
					}
					pcmcate.setChannelSid(uppcm.getChannelSid());
					pcmcate.setCategoryType(uppcm.getCategoryType());
					if (Constants.PUBLIC_1 == uppcm.getCategoryType()) {
						pcmcate.setShopSid(uppcm.getShopSid());
					}
					pcmcate.setParentSid(cateType.getSid().toString());
					cateMapper.updateByPrimaryKeySelective(pcmcate);
					icpvs.dragInheritance(pcmcate.getParentSid(), String.valueOf(pcmcate.getSid()),
							"0");
					if (results.intValue() != Constants.PUBLIC_1) {
						logger.error("添加失败");
						// throw new BleException("02", "添加失败");
						message = "添加失败";
						throw new BleException(
								ComErrorCodeConstants.ErrorCode.CATEGORY_ADD_ERROR.getErrorCode(),
								ComErrorCodeConstants.ErrorCode.CATEGORY_ADD_ERROR.getMemo());
					}

					Map<String, Object> paramMap1 = new HashMap<String, Object>();
					paramMap1.put("parent_sid", pcmcate.getShopSid() + pcmcate.getCategorySid());
					List<PcmCategory> cateList = cateMapper.selectByParentSid(paramMap1);
					paramMap1.clear();
					if (cateList != null && cateList.size() != 0) {
						pcmcate.setIsParent(Constants.PUBLIC_1);
						pcmcate.setIsLeaf(Constants.N);
						cateMapper.updateByPrimaryKeySelective(pcmcate);
						paramMap1.put("parentSid", pcmcate.getSid());
						paramMap1.put("list", cateList);
						cateMapper.updateByListSelective(paramMap1);
						deleteCateCache(pcmcate.getSid() + "");
					}

					logger.info("add success");
					message = "添加成功";
					// 缓存删除
					deleteCateCache(pcmcate.getParentSid());
					return message;
				} else {
					logger.info("该分类已经存在");
					message = "该分类已经存在";
					throw new BleException(
							ComErrorCodeConstants.ErrorCode.CATEGORY_IS_HAVA.getErrorCode(),
							ComErrorCodeConstants.ErrorCode.CATEGORY_IS_HAVA.getMemo());
				}
				// 直接添加分类的根部节点
			} else if (Constants.PUBLIC_1 == cateDto.getIsParent()
					|| cateDto.getParentSid().equals("0")) {
				// 判断是否为工业分类或统计分类,工业分类或统计分类只能有一套有效的
				if (Constants.PUBLIC_0 == cateDto.getCategoryType()
						|| Constants.PUBLIC_2 == cateDto.getCategoryType()) {
					paramMap.clear();
					paramMap.put("categoryType", cateDto.getCategoryType());
					paramMap.put("status", "Y");
					paramMap.put("parentSid", 0);
					Integer count = cateMapper.getCountByParam(paramMap);
					if (count > Constants.PUBLIC_0) {
						logger.error("只能有一套有效的工业分类或统计分类");
						message = "只能有一套有效的工业分类或统计分类";
						throw new BleException(
								ComErrorCodeConstants.ErrorCode.CATEGORY_ONE.getErrorCode(),
								ComErrorCodeConstants.ErrorCode.CATEGORY_ONE.getMemo());
					}
				}
				// 根据编码判重(针对管理分类)
				if (Constants.PUBLIC_1 == cateDto.getCategoryType()) {
					paramMapcate.put("categorySid", cateDto.getCategorySid());
					paramMapcate.put("isDisplay", 1);
					paramMapcate.put("status", "Y");
					Integer count2 = cateMapper.getCountByParam(paramMapcate);
					if (count2 > 0) {
						logger.error("编码重复");
						message = "编码重复";
						throw new BleException(
								ComErrorCodeConstants.ErrorCode.CATEGORY_CATEGORYSID_HAVA
										.getErrorCode(),
								ComErrorCodeConstants.ErrorCode.CATEGORY_CATEGORYSID_HAVA.getMemo());
					}
				}
				// 如果没有需先添加该分类的上级分类,添加之前对上级工业分类判重,如果有上级分类，则需要通过名称来对下级分类判重
				paramMap.clear();
				paramMap.put("name", cateDto.getName());
				paramMap.put("isDisplay", 1);
				paramMap.put("parentSid", Constants.PUBLIC_0);
				Integer count1 = cateMapper.getCountByParam(paramMap);
				if (count1 == Constants.PUBLIC_0) {
					// 添加上级工业分类
					BeanUtils.copyProperties(cateDto, pcmcate);
					// 工业分类
					if (Constants.PUBLIC_0 == cateDto.getCategoryType()) {
						// 工业分类是全渠道
						pcmcate.setChannelSid((Long.parseLong(Constants.PUBLIC_0 + "")));
						pcmcate.setCategoryType(Constants.INDUSTRYTCATEGORY);
						// 清空shopsid
						String shopsid = cateDto.getShopSid();
						shopsid = "";
						pcmcate.setShopSid(shopsid);
						// 管理分类
					} else if (Constants.PUBLIC_1 == cateDto.getCategoryType()) {
						pcmcate.setCategoryType(Constants.MANAGECATEGORY);
						// 管理分类默认为全渠道
						pcmcate.setChannelSid((Long.parseLong(Constants.PUBLIC_0 + "")));
						// 统计分类
					} else if (Constants.PUBLIC_2 == cateDto.getCategoryType()) {
						// 统计分类是全渠道
						pcmcate.setChannelSid((Long.parseLong(Constants.PUBLIC_0 + "")));
						pcmcate.setCategoryType(Constants.STATISTICSCATEGORY);
						// 清空shopsid
						String shopsid = cateDto.getShopSid();
						shopsid = "";
						pcmcate.setShopSid(shopsid);
					} else if (Constants.PUBLIC_3 == cateDto.getCategoryType()) {
						// 展示分类
						pcmcate.setCategoryType(Constants.SHOWCATEGORY);
						// 清空shopsid
						String shopsid = cateDto.getShopSid();
						shopsid = "";
						pcmcate.setShopSid(shopsid);
					} else {
						// 默认是工业分类
						pcmcate.setCategoryType(Constants.INDUSTRYTCATEGORY);
						pcmcate.setChannelSid((Long.parseLong(Constants.PUBLIC_0 + "")));
						pcmcate.setCategoryType(Constants.INDUSTRYTCATEGORY);
						// 清空shopsid
						String shopsid = cateDto.getShopSid();
						shopsid = "";
						pcmcate.setShopSid(shopsid);
					}
					Map<String, Object> mapParams = new HashMap<String, Object>();
					mapParams.put("parentSid", cateDto.getParentSid());
					// 根据parentsid查询品类信息
					List<PcmCategory> listcate = cateMapper.selectListByParam(mapParams);
					// 赋值到sortorder字段
					pcmcate.setSortOrder(listcate.size() + Constants.PUBLIC_1);
					pcmcate.setIsParent(Constants.PUBLIC_1);
					// getCategorycode(cateDto, pcmcate);
					pcmcate.setParentSid(Constants.PUBLIC_0 + "");
					pcmcate.setIsLeaf(Constants.N);
					// pcmcate.setRootSid(0L);
					if (cateDto.getCategoryType() == 0 || cateDto.getCategoryType() == 2) {
						pcmcate.setLevel(Constants.PUBLIC_0);
					} else if (cateDto.getCategoryType() == 3) {
						pcmcate.setLevel(Constants.PUBLIC_1);
					}

					pcmcate.setStatus(cateDto.getStatus());
					pcmcate.setIsDisplay(Constants.PUBLIC_1);
					pcmcate.setIsSelfBuilt(Constants.PUBLIC_1);
					Integer results = cateMapper.insertSelective(pcmcate);
					// 如果传入的是管理分类增，编码不自动生成
					if (cateDto.getCategoryType() == Constants.PUBLIC_1) {
						pcmcate.setCategorySid(cateDto.getCategorySid());
						pcmcate.setRootSid(pcmcate.getSid());
					} else {
						pcmcate.setCategorySid(pcmcate.getSid() + "");
						pcmcate.setRootSid(pcmcate.getSid());
					}
					if (cateDto.getCategoryType() == 1) {
						pcmcate.setCategoryCode(pcmcate.getCategorySid());// 没有传入时取SID
					}
					if (cateDto.getCategoryType() == 3) {
						pcmcate.setCategoryCode("0");
					}
					cateMapper.updateByPrimaryKeySelective(pcmcate);
					if (results.intValue() != Constants.PUBLIC_1) {
						logger.error("添加失败");
						message = "添加失败";
						throw new BleException(
								ComErrorCodeConstants.ErrorCode.CATEGORY_ADD_ERROR.getErrorCode(),
								ComErrorCodeConstants.ErrorCode.CATEGORY_ADD_ERROR.getMemo());
					}
					logger.info("add success");
					message = "添加成功";
					// 缓存删除
					deleteCateCache("0");
					return message;
				} else {
					logger.error("添加失败、该上级分类已经存在");
					message = "该分类已经存在";
					throw new BleException(
							ComErrorCodeConstants.ErrorCode.CATEGORY_IS_HAVA.getErrorCode(),
							ComErrorCodeConstants.ErrorCode.CATEGORY_IS_HAVA.getMemo());
				}
			}
		} else {
			// 根据名称判重
			PcmCategory cate1 = cateMapper.selectByGLCategorySid(cateDto);
			if (cate1 == null) {
				throw new BleException(
						ComErrorCodeConstants.ErrorCode.CATE_NOT_IS_EXIST.getErrorCode(),
						ComErrorCodeConstants.ErrorCode.CATE_NOT_IS_EXIST.getMemo());
			}

			BeanUtils.copyProperties(cateDto, pcmcate);
			pcmcate.setName(cateDto.getName().trim());
			pcmcate.setIsMarket(cateDto.getIsMarket());
			pcmcate.setCategorySid(cateDto.getCategorySid());
			pcmcate.setParentSid(cateDto.getParentSid());
			if (StringUtils.isNotBlank(cateDto.getCategoryCode())) {
				pcmcate.setCategoryCode(cateDto.getCategoryCode());// 手工录入编码
			}
			
			HashMap<String, Object> paramMaps2 = new HashMap<String, Object>();
			paramMaps2.put("name", cateDto.getName());
			paramMaps2.put("isDisplay", 1);
			paramMaps2.put("parentSid", cateDto.getParentSid());
			paramMaps2.put("category_sid", cateDto.getCategorySid());
			PcmCategory cate = cateListes.get(0);
			
			pcmcate.setSid(cate.getSid());
			result = cateMapper.updateByPrimaryKeySelective(pcmcate);
			paramMap.clear();
			paramMap.put("categorySid", pcmcate.getSid());
			paramMap.put("categoryName", pcmcate.getName());
			pcmcatepropvalueMapper.updateByCategorySid(paramMap);
			if (result == Constants.PUBLIC_0) {
				logger.error("修改失败");
				message = "修改失败";
				throw new BleException(
						ComErrorCodeConstants.ErrorCode.CATEGORY_UPDATE_ERROR.getErrorCode(),
						ComErrorCodeConstants.ErrorCode.CATEGORY_UPDATE_ERROR.getMemo());
			}
			logger.info("update success");
			message = "修改成功";
			//删除缓存
			deleteCateCache(cate.getParentSid());
			if(!pcmcate.getParentSid().equals(cate.getParentSid()) ){
				deleteCateCache(pcmcate.getParentSid());
			}
			return message;
		}
		return message;
	}
	
	/**
	 * 返回同一父类下的子类最大的序列号
	 *
	 * @param parentSid
	 * @param channelSid
	 * @return Integer
	 * @Methods Name getMaxSortOrder
	 * @Create In 2015年8月12日 By duanzhaole
	 */
	public Integer getMaxSortOrder(String parentSid) {
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("parent_sid", parentSid);
		List<PcmCategory> list = cateMapper.selectByParentSid(mapParam);
		Integer max = 0;
		if (list.size() > 0) {
			for (PcmCategory cat : list) {
				if (max < cat.getSortOrder()) {
					max = cat.getSortOrder();
				}
			}
		}
		return max;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wangfj.product.category.service.intf.ICategoryService#
	 * getCategoryBySid ()
	 */
	@Override
	public PcmAddCategoryDto getCategoryBySid(Long sid) {
		PcmCategory pcmCategory = cateMapper.selectByPrimaryKey(sid);
		PcmAddCategoryDto pcmdto = new PcmAddCategoryDto();

		// Date catedate = pcmCategory.getCreateTime();
		// 将时间格式转换成符合Timestamp要求的格式
		// String nowTime = new
		// SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(catedate);
		// java.sql.Timestamp ts_date = java.sql.Timestamp.valueOf(nowTime);
		// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// String str = df.format(ts_date);
		// String tem = str.substring(0, str.length() - 1);
		// System.out.println(tem + "格式化后的时间");
		// Timestamp ts = Timestamp.valueOf(tem);
		// pcmdto.setCreateTime(ts);
		BeanUtils.copyProperties(pcmCategory, pcmdto);
		return pcmdto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wangfj.product.category.service.intf.ICategoryService#
	 * selectShoppeByCateSid(java.lang.Long)
	 */
	@Override
	public Page<HashMap<String, Object>> selectShoppeByCateSid(SelectCategoryParamDto paramDto,
			Page<HashMap<String, Object>> pagedto) {

		Page<HashMap<String, Object>> pagelist = new Page<HashMap<String, Object>>();
		int count = cateMapper.getShoppeCountBySid(paramDto.getSid());
		pagedto.setCount(count);
		paramDto.setStart(pagedto.getStart());
		paramDto.setLimit(pagedto.getLimit());
		List<HashMap<String, Object>> listShop = cateMapper.selectShoppeByCateSid(paramDto);
		pagelist.setList(listShop);
		pagelist.setCurrentPage(paramDto.getCurrenPage());
		pagelist.setCount(count);
		return pagelist;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wangfj.product.category.service.intf.ICategoryService#
	 * deleteByPrimaryKey (java.lang.Long)
	 */
	@Transactional
	public int deleteByPrimaryKey(Long sid) {
		return this.cateMapper.deleteByPrimaryKey(sid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wangfj.product.category.service.intf.ICategoryService#insert(com.
	 * wangfj.product.category.domain.entity.PcmCategory)
	 */
	@Transactional
	public int insert(PcmCategory record) {
		return this.cateMapper.insertSelective(record);
	}

	@Transactional
	@Override
	public PcmCategory insertGlCategory(PcmCategory record) {
		record.setCategoryCode("0");
		record.setChannelSid(0l);
		record.setCreateTime(new Date());
		record.setIsLeaf("N");
		record.setIsParent(1);
		record.setParentSid("0");
		Integer maxSortOrder = getMaxSortOrder("0");
		record.setSortOrder(maxSortOrder + 1);
		int i = this.cateMapper.insertSelective(record);
		if (i == 1) {
			record.setRootSid(record.getSid());
			record.setCategorySid(record.getSid().toString());
			record.setCategoryCode(record.getSid().toString());
			cateMapper.updateByPrimaryKeySelective(record);
			return record;
		}
		return record;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wangfj.product.category.service.intf.ICategoryService#getByParentSid
	 * (java.lang.Long)
	 */
	@Override
	public List<PcmCategory> getByParentSid(String parentSid) {
		PcmCategory category = new PcmCategory();
		category.setParentSid(parentSid);
		category.setIsDisplay(1);
		SelectCategoryParamDto cateDto = new SelectCategoryParamDto();
		BeanUtils.copyProperties(category, cateDto);
		return this.cateMapper.selectCategoryListByParam(cateDto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wangfj.product.category.service.intf.ICategoryService#selectListIn
	 * (java.util.List)
	 */
	@Override
	public List<PcmCategory> selectListIn(List<Long> cateIds) {
		return this.cateMapper.selectListIn(cateIds);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wangfj.product.category.service.intf.ICategoryService#getSubOrPaNode
	 * (java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<PcmCategory> getSubOrPaNode(String categorySid, Long channelSid, String flag) {
		if (Constants.PUBLIC_0.toString().equals(flag)) {
			PcmCategory sc = new PcmCategory();
			sc.setCategorySid(categorySid);
			sc.setChannelSid(Long.valueOf(channelSid));

			List<PcmCategory> ts = this.cateMapper.selectPageListByParam(sc);
			if (ts != null && ts.size() > Constants.PUBLIC_0) {
				PcmCategory sctemp = ts.get(0);
				if (sctemp.getLevel().longValue() >= Constants.PUBLIC_2) {
					PcmCategory param = new PcmCategory();
					param.setCategorySid(sctemp.getParentSid());
					param.setChannelSid(Long.valueOf(channelSid));
					return this.cateMapper.selectPageListByParam(param);
				}
				return null;
			} else {
				return null;
			}
		} else if (Constants.PUBLIC_1.toString().equals(flag)) {
			PcmCategory sc = new PcmCategory();
			sc.setParentSid(categorySid);
			sc.setChannelSid(Long.valueOf(channelSid));
			return this.cateMapper.selectPageListByParam(sc);
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wangfj.product.category.service.intf.ICategoryService#
	 * getProductStanCategorys(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> getProductStanCategorys(Map map) {
		return this.cateMapper.getProductStanCategorys(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wangfj.product.category.service.intf.ICategoryService#get(java.lang
	 * .Long)
	 */
	@Override
	public PcmCategory get(Long sid) {
		return this.cateMapper.selectByPrimaryKey(sid);
	}

	public int update(PcmCategory record) {
		return this.cateMapper.updateByPrimaryKeySelective(record);
	}

	public PcmCategory getCateByCatesid(String categorySid) {
		return this.cateMapper.selectByCategorySid(categorySid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wangfj.product.category.service.intf.ICategoryService#
	 * selectSPUByCategorySid(java.lang.Long)
	 */
	@Override
	public Page<PcmProductDto> selectSPUByCategorySid(Long sid) {
		List<PcmProductDto> listProduct = pcmprocategoryMapper.selectSPUByCateSid(sid);
		Page<PcmProductDto> pagelist = new Page<PcmProductDto>();
		if (!listProduct.isEmpty()) {
			pagelist.setList(listProduct);
			pagelist.setCount(pcmprocategoryMapper.getCountSPUByCateSid(sid));
		}
		return pagelist;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wangfj.product.category.service.intf.ICategoryService#
	 * selectSKUByCategorySid(java.lang.Long)
	 */
	@Override
	public Page<PcmProDetailDto> selectSKUByCategorySid(Long sid) {
		List<PcmProDetailDto> listProduct = pcmprocategoryMapper.selectSKUByCateSid(sid);
		Page<PcmProDetailDto> pagelist = new Page<PcmProDetailDto>();
		if (!listProduct.isEmpty()) {
			pagelist.setList(listProduct);
			pagelist.setCount(pcmprocategoryMapper.getCountSKUByCateSid(sid));
		}
		return pagelist;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wangfj.product.category.service.intf.ICategoryService#
	 * selectSpuByIsLeaf
	 * (com.wangfj.product.category.domain.vo.SelectCategoryParamDto,
	 * com.wangfj.core.framework.base.page.Page)
	 */
	@Override
	public Page<PcmProduct> selectSpuByIsLeaf(SelectCategoryParamDto catedto,
			Page<SelectCategoryParamDto> pagedto) {
		Page<PcmProduct> pagelist = new Page<PcmProduct>();
		int count = productMapper.selectSpuCountByIsLeaf(catedto);
		pagedto.setCount(count);
		catedto.setStart(pagedto.getStart());
		catedto.setLimit(pagedto.getLimit());
		List<PcmProduct> listShop = productMapper.selectSpuByIsLeaf(catedto);
		if (!listShop.isEmpty()) {
			pagelist.setList(listShop);
			pagelist.setCurrentPage(catedto.getCurrenPage());
			pagelist.setCount(count);
		} /*
		 * else { throw new BleException("001", "通过末级节点查询spu信息为空"); }
		 */
		return pagelist;
	}

	public int selectCountByIsLeaf(SelectCategoryParamDto catedto) {
		return productMapper.selectSpuCountByIsLeaf(catedto);
	}

	@Override
	public List<Map<String, Object>> getIndustryCategorys() {
		return this.cateMapper.getIndustryCategorys();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wangfj.product.category.service.intf.ICategoryService#
	 * selectLeafByParentSid
	 * (com.wangfj.product.category.domain.entity.PcmCategory)
	 */
	@Override
	public List<PcmCategory> selectLeafByParentSid(Map<String, Object> mapParam) {
		return this.cateMapper.selectListByParam(mapParam);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wangfj.product.category.service.intf.ICategoryService#
	 * categoryBeforeDrop (java.lang.Long, java.lang.String)
	 */
	@Override
	@Transactional
	public Map<String, Object> categoryBeforeDrop(Long sid, String parentSid, int sortOrder,
			int categoryType, String targetSid, String isParent, String rootSid, String moveType) {
		List<PcmCategory> publishList = new ArrayList<PcmCategory>();
		int result = 0;
		int counts = 0;
		int count2 = 0;
		// 获取拖拽的节点对象
		PcmCategory targetCategory = cateMapper.selectByPrimaryKey(Long.valueOf(targetSid));
		if (targetCategory == null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("res", 6);
			return map;
		}
		PcmCategory category = cateMapper.selectByPrimaryKey(sid);
		// 如果被拖节点放在某个父级节点上,isParent 为目标节点的是否为父节点字段
		PcmCategory categoryName = new PcmCategory();
		if (!category.getParentSid().equals(parentSid)) {
			if ("inner".equals(moveType)) {
				if ("Y".equals(targetCategory.getIsLeaf())) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("res", 5);
					return map;
				}
				// if (StringUtils.isNotBlank(rootSid)) {// 同分类树下拖拽不传入rootSid,
				categoryName.setName(category.getName());
				categoryName.setIsDisplay(1);
				categoryName.setParentSid(targetSid);
				// 获取被拖拽节点的品类名称，判重
				int countName = cateMapper.getCountByParam(categoryName);
				if (countName > 0 || category.getName().equals(targetCategory.getName())) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("res", 3);
					return map;
				}
				// }
//				Map<String, Object> paramMap = new HashMap<String, Object>();
				// 拖拽后修改当前品类的信息
				PcmCategory upcate = new PcmCategory();
				// paramMap.put("parentSid", parentSid);
				// paramMap.put("categoryType", categoryType);
				// List<PcmCategory> listp =
				// cateMapper.selectListByParam(paramMap);
				PcmCategory cate = new PcmCategory();
				cate.setParentSid(parentSid);

				upcate.setSid(category.getSid());
				upcate.setParentSid(targetSid);
				upcate.setChannelSid(targetCategory.getChannelSid());
				upcate.setLevel(targetCategory.getLevel() + 1);
				upcate.setRootSid(targetCategory.getRootSid());
				count2 = cateMapper.getCountByParam(upcate);

				// 修改属性、属性值关联表的渠道
				Map<String, Object> mappram = new HashMap<String, Object>();
				mappram.put("categorySid", category.getCategorySid());
				mappram.put("channelSid", upcate.getChannelSid());
				pcmcatepropvalueMapper.updateByCategorySid(mappram);

				// sortorder++;
				count2++;
				upcate.setSortOrder(count2);
				counts = cateMapper.updateByPrimaryKeySelective(upcate);
				// 下发LIST
				upcate.setCategoryType(category.getCategoryType());
				publishList.add(upcate);
				icpvs.dragInheritance(upcate.getParentSid(), String.valueOf(upcate.getSid()),
						category.getParentSid());
				// 修改所有子节点的属性
				icpvs.clearSubList();
				List<PcmCategory> cateList = icpvs.selectAllSubNodeByCateSid(upcate.getSid()
						.toString());
				if (cateList != null && cateList.size() != 0) {
					int levelDis = upcate.getLevel() - category.getLevel();
					Map<String, Object> para = new HashMap<String, Object>();
					para.put("levelDis", levelDis);
					para.put("rootSid", upcate.getRootSid());
					para.put("channelSid", upcate.getChannelSid());
					para.put("list", cateList);
					cateMapper.updateByListSelective(para);
					publishList.addAll(cateList);
				}
				// 如果拖拽前的父节点下 没有节点,将父节点变为叶子节点
				List<PcmCategory> list2 = getByParentSid(category.getParentSid());
				if (list2.size() == 0) {
					PcmCategory parent = new PcmCategory();
					parent.setSid(Long.valueOf(category.getParentSid()));
					parent.setIsLeaf("Y");
					parent.setIsParent(0);
					update(parent);
					publishList.add(parent);
				}
				// 缓存
//				RedisVo vo = new RedisVo();
//				vo.setKey(category.getParentSid());
//				vo.setField(DomainName.selectCateGory);
//				vo.setType(CacheUtils.HDEL);
//				CacheUtils.setRedisData(vo);
				deleteCateCache(category.getParentSid());
//				RedisVo vo2 = new RedisVo();
//				vo2.setKey(upcate.getParentSid());
//				vo2.setField(DomainName.selectCateGory);
//				vo2.setType(CacheUtils.HDEL);
//				CacheUtils.setRedisData(vo2);
				deleteCateCache(upcate.getParentSid());
			} else {
				if (targetCategory.getParentSid().equals(0)) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("res", 7);
					return map;
				}
				Map<String, Object> paramMap = new HashMap<String, Object>();
				// 拖拽后修改当前品类的信息
				PcmCategory upcate = new PcmCategory();
				paramMap.put("parentSid", parentSid);
				paramMap.put("categoryType", categoryType);
				paramMap.put("isDisplay", 1);
				List<PcmCategory> listp = cateMapper.selectListByParam(paramMap);
				categoryName.setName(category.getName());
				categoryName.setIsDisplay(1);
				categoryName.setParentSid(targetCategory.getParentSid());
				// 获取被拖拽节点的品类名称，判重
				int countName = cateMapper.getCountByParam(categoryName);
				if (countName > 0 || category.getName().equals(listp.get(0).getName())) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("res", 3);
					return map;
				}
				PcmCategory cate = new PcmCategory();
				cate.setParentSid(parentSid);
				upcate.setSid(category.getSid());
				upcate.setParentSid(parentSid);
				upcate.setChannelSid(targetCategory.getChannelSid());
				upcate.setLevel(targetCategory.getLevel());
				upcate.setRootSid(targetCategory.getRootSid());
				// count2 = cateMapper.getCountByParam(upcate);

				// 修改属性、属性值关联表的渠道
				Map<String, Object> mappram = new HashMap<String, Object>();
				mappram.put("categorySid", category.getCategorySid());
				mappram.put("channelSid", upcate.getChannelSid());
				pcmcatepropvalueMapper.updateByCategorySid(mappram);

				// sortorder++;
				// count2++;
				// upcate.setSortOrder(count2);

				// int sortOrders = category.getSortOrder();
				// 拖拽释放 根据parentsid查询该父级分类的子级分类
				Map<String, Object> mapPram = new HashMap<String, Object>();
				mapPram.put("parentSid", parentSid);
				List<PcmCategory> listcate = cateMapper.selectListByParam(mapPram);
				Integer sortOrder2 = targetCategory.getSortOrder();
				if ("next".equals(moveType)) {
					sortOrder2++;
				}
				upcate.setSortOrder(sortOrder2);
				for (int i = 0; i < listcate.size(); i++) {
					int count = 2;
					// 循环修改sortorder
					PcmCategory category_update = listcate.get(i);
					if (category_update.getSortOrder() >= sortOrder2) {
						category_update.setSortOrder(i + count);
						counts = cateMapper.updateByPrimaryKey(category_update);
					}
				}
				counts = cateMapper.updateByPrimaryKeySelective(upcate);
				// 下发LIST
				upcate.setCategoryType(category.getCategoryType());
				publishList.add(upcate);
				icpvs.dragInheritance(upcate.getParentSid(), String.valueOf(upcate.getSid()),
						category.getParentSid());
				// 修改所有子节点的属性
				icpvs.clearSubList();
				List<PcmCategory> cateList = icpvs.selectAllSubNodeByCateSid(upcate.getSid()
						.toString());
				if (cateList != null && cateList.size() != 0) {
					int levelDis = upcate.getLevel() - category.getLevel();
					Map<String, Object> para = new HashMap<String, Object>();
					para.put("levelDis", levelDis);
					para.put("rootSid", upcate.getRootSid());
					para.put("channelSid", upcate.getChannelSid());
					para.put("list", cateList);
					cateMapper.updateByListSelective(para);
					publishList.addAll(cateList);// 下发LIST
				}
				// 如果拖拽前的父节点下 没有节点,将父节点变为叶子节点
				List<PcmCategory> list2 = getByParentSid(category.getParentSid());
				if (list2.size() == 0) {
					PcmCategory parent = new PcmCategory();
					parent.setSid(Long.valueOf(category.getParentSid()));
					parent.setIsLeaf("Y");
					parent.setIsParent(0);
					update(parent);
					publishList.add(parent);
				}
				// 缓存
//				RedisVo vo = new RedisVo();
//				vo.setKey(category.getParentSid());
//				vo.setField(DomainName.selectCateGory);
//				vo.setType(CacheUtils.HDEL);
//				CacheUtils.setRedisData(vo);
				deleteCateCache(category.getParentSid());
//				RedisVo vo2 = new RedisVo();
//				vo2.setKey(upcate.getParentSid());
//				vo2.setField(DomainName.selectCateGory);
//				vo2.setType(CacheUtils.HDEL);
//				CacheUtils.setRedisData(vo2);
				deleteCateCache(upcate.getParentSid());
			}
		} else if (isParent.equals("true")) {
			if ("Y".equals(targetCategory.getIsLeaf())) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("res", 5);
				return map;
			}
			// Map<String, Object> countParam = new HashMap<String, Object>();
			// countParam.put("sid", targetSid);
			// List<PcmCategory> countlist =
			// cateMapper.selectListByParam(countParam);
			// categoryName.setCategoryType(countlist.get(0).getCategoryType());
			// if (StringUtils.isNotBlank(rootSid)) {// 同分类树下拖拽不传入rootSid,
			categoryName.setIsDisplay(1);
			categoryName.setName(category.getName());
			categoryName.setParentSid(targetSid);
			// 获取被拖拽节点的品类名称，判重
			int countName2 = cateMapper.getCountByParam(categoryName);
			if (countName2 > 0 || category.getName().equals(targetCategory.getName())) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("res", 4);
				return map;
			}
			// }
			Map<String, Object> upParam = new HashMap<String, Object>();
			upParam.put("sid", targetSid);
			upParam.put("categoryType", categoryType);
			List<PcmCategory> lists = cateMapper.selectListByParam(upParam);
			PcmCategory catecount = new PcmCategory();
			catecount.setSid(category.getSid());
			catecount.setParentSid(targetSid);
			catecount.setChannelSid(lists.get(0).getChannelSid());
			catecount.setLevel(lists.get(0).getLevel() + 1);
			catecount.setRootSid(lists.get(0).getRootSid());
			count2 = cateMapper.getCountByParam(catecount);
			count2++;
			catecount.setSortOrder(count2);
			// 修改属性、属性值关联表的渠道
			Map<String, Object> mappram = new HashMap<String, Object>();
			mappram.put("categorySid", category.getCategorySid());
			mappram.put("channelSid", catecount.getChannelSid());
			pcmcatepropvalueMapper.updateByCategorySid(mappram);
			counts = cateMapper.updateByPrimaryKeySelective(catecount);
			// 下发LIST
			catecount.setCategoryType(category.getCategoryType());
			publishList.add(catecount);
			icpvs.dragInheritance(catecount.getParentSid(), String.valueOf(catecount.getSid()),
					category.getParentSid());
			// 修改所有子节点的属性
			icpvs.clearSubList();
			List<PcmCategory> cateList = icpvs.selectAllSubNodeByCateSid(catecount.getSid()
					.toString());
			if (cateList != null && cateList.size() != 0) {
				int levelDis = catecount.getLevel() - category.getLevel();
				Map<String, Object> para = new HashMap<String, Object>();
				para.put("levelDis", levelDis);
				para.put("rootSid", catecount.getRootSid());
				para.put("channelSid", catecount.getChannelSid());
				para.put("list", cateList);
				cateMapper.updateByListSelective(para);
				publishList.addAll(cateList);// 下发LIST
			}
			// 如果拖拽前的父节点下 没有节点,将父节点变为叶子节点
			List<PcmCategory> list2 = getByParentSid(category.getParentSid());
			if (list2.size() == 0) {
				PcmCategory parent = new PcmCategory();
				parent.setSid(Long.valueOf(category.getParentSid()));
				parent.setIsLeaf("Y");
				parent.setIsParent(0);
				update(parent);
				publishList.add(parent);
			}
			// 缓存
//			RedisVo vo = new RedisVo();
//			vo.setKey(category.getParentSid());
//			vo.setField(DomainName.selectCateGory);
//			vo.setType(CacheUtils.HDEL);
//			CacheUtils.setRedisData(vo);
			deleteCateCache(category.getParentSid());
//			RedisVo vo2 = new RedisVo();
//			vo2.setKey(catecount.getParentSid());
//			vo2.setField(DomainName.selectCateGory);
//			vo2.setType(CacheUtils.HDEL);
//			CacheUtils.setRedisData(vo2);
			deleteCateCache(catecount.getParentSid());
		} else {
			// 非跨级拖拽
			// 获取拖拽的sortorder
			int sortOrders = category.getSortOrder();
			// 拖拽释放 根据parentsid查询该父级分类的子级分类
			Map<String, Object> mapPram = new HashMap<String, Object>();
			mapPram.put("parentSid", parentSid);
			List<PcmCategory> listcate = cateMapper.selectListByParam(mapPram);
			// 原序号-1 是否大于目标地址
			// 向上拖拽是成立的
			if (sortOrders - 1 > sortOrder) {
				listcate.add(sortOrder, category);
				listcate.remove(sortOrders);
			} else {
				listcate.add(sortOrder, category);
				listcate.remove(sortOrders - 1);
			}
			for (int i = 0; i < listcate.size(); i++) {
				int count = 1;
				// 循环修改sortorder
				PcmCategory category_update = listcate.get(i);
				category_update.setSortOrder(i + count);
				counts = cateMapper.updateByPrimaryKey(category_update);
			}
			publishList.addAll(listcate);// 下发LIST
			// 缓存
//			RedisVo vo = new RedisVo();
//			vo.setKey(parentSid);
//			vo.setField(DomainName.selectCateGory);
//			vo.setType(CacheUtils.HDEL);
//			CacheUtils.setRedisData(vo);
			deleteCateCache(parentSid);
		}

		if (counts == Constants.PUBLIC_0) {
			logger.error("拖拽失败");
			result = 2;
		} else {
			logger.info("拖拽成功");
			result = 1;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("res", result);
		map.put("list", publishList);
		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wangfj.product.category.service.intf.ICategoryService#
	 * selectPropsDictByCateSid
	 * (com.wangfj.product.category.domain.vo.PcmPropsDictsDto)
	 */
	@Override
	public List<PropsVO> selectPropsDictByCateSid(PcmPropsDictsDto cateprop) {
		List<PropsVO> listShop = new ArrayList<PropsVO>();
		if (cateprop.getIsnotnull() == null) {
			listShop = catePropDictMapper.selectPropsDictByCateSid(cateprop.getSid());
		} else {
			listShop = catePropDictMapper.selectPropsDictByCateSidZSFL(null);
		}
		return listShop;
	}

	public List<PropsVO> selectPropsDictByCategorySid(PcmCategoryPropsDictPara para) {
		List<PropsVO> listPropDict = catePropDictMapper.selectPropsDictByCategorySid(para);
		return listPropDict;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wangfj.product.category.service.intf.ICategoryService#
	 * getCountByCategoryType(int)
	 */
	@Override
	public Integer getCountByCategoryType() {
		return cateMapper.getCountByCategoryType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wangfj.product.category.service.intf.ICategoryService#selectByChannel
	 * (com.wangfj.product.organization.domain.entity.PcmChannel)
	 */
	@Override
	public List<CategoryByChannelVo> selectByChannel(Map<String, Object> mapParams) {
		List<CategoryByChannelVo> listcate = cateMapper.selectByChannel(mapParams);
		if (listcate.size() == 0) {
			logger.error("查询结果为空");
			throw new BleException(ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
		}
		return listcate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wangfj.product.category.service.intf.ICategoryService#
	 * selectListOrParam (java.util.Map)
	 */
	@Override
	public List<PcmCategory> selectListOrParam(Map<String, Object> mapParams) {
		// TODO Auto-generated method stub
		return cateMapper.selectListOrParam(mapParams);
	}

	/**
	 * 查询所有商品品类（展示分类）(CMS)
	 *
	 * @return List<PcmCategory>
	 * @Methods Name selectCateListCms
	 * @Create In 2015年10月29日 By zhangxy
	 */
	@Override
	public List<PcmCategory> selectCateListCms() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categoryType", "3");
		map.put("status", "Y");
		map.put("isDisplay", 1);
		List<PcmCategory> list = cateMapper.selectListByParam(map);
		return list;
	}

	@Override
	public List<PcmCategory> selectListByParam(PcmCategory entity) {
		List<PcmCategory> list = cateMapper.selectListByParam(entity);
		return list;
	}

	@Override
	public Integer getCategoryButton() {
		PcmLockAttribute entity = new PcmLockAttribute();
		entity.setDistributedLock("productCategory");
		List<PcmLockAttribute> li = lockMapper.selectListByParam(entity);
		Integer res = null;
		if (li != null && li.size() > 0) {
			res = li.get(0).getButton();
		}
		return res;
	}

	public List<PcmCategory> selectCateInfoToSap(Long cateSid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sid", cateSid);
		List<PcmCategory> list = cateMapper.selectListByParam(map);
		return list;
	}

	public List<PcmCategory> selectShowCatesByChannelSid(Long channelSid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("channelSid", channelSid);
		map.put("categoryType", 3);
		map.put("parentSid", 0);
		map.put("status", 0);
		map.put("isDisplay", 1);
		List<PcmCategory> list = cateMapper.selectListByParam(map);
		return list;
	}

	/**
	 * 根据当前分类参数查询分类的所有上级
	 *
	 * @return List<Map<String, Object>>
	 * @Methods Name findAllParentCategoryByParam
	 * @Create In 2016年2月29日 By wangxuan
	 */
	@Override
	public List<Map<String, Object>> findAllParentCategoryByParam(PcmCategoryQueryDto dto) {
		logger.info("start findAllParentCategoryByParam(),param:" + dto.toString());
		dto.setIsDisplay(Constants.PUBLIC_1 + "");
		List<Map<String, Object>> childList = cateMapper.findCategoryByParam(dto);
		if (childList != null && childList.size() == 1) {
			Map<String, Object> childMap = childList.get(0);
			String parentSid = childMap.get("parentSid") + "";
			if (com.wangfj.core.utils.StringUtils.isNotEmpty(parentSid)) {
				boolean flag = Constants.PUBLIC_0 == Integer.parseInt(parentSid);
				while (!flag) {
					PcmCategoryQueryDto tempDto = new PcmCategoryQueryDto();
					tempDto.setSid(parentSid);
					tempDto.setIsDisplay(Constants.PUBLIC_1 + "");
					List<Map<String, Object>> parentList = cateMapper.findCategoryByParam(tempDto);
					if (parentList != null && parentList.size() == 1) {
						Map<String, Object> parentMap = parentList.get(0);
						childList.add(parentMap);
						parentSid = parentMap.get("parentSid") + "";
						if (com.wangfj.core.utils.StringUtils.isNotEmpty(parentSid)) {
							flag = Constants.PUBLIC_0 == Integer.parseInt(parentSid);
						} else {
							flag = false;
						}
					} else {
						flag = false;
					}
				}
			}
		}
		logger.info("end findAllParentCategoryByParam(),return:" + childList.toString());
		return childList;
	}
	
	@Override
	public void deleteCateCache(String key){
		boolean b = redisUtil.del(DomainName.selectCateGory+key);
		if(!b){
			PcmCategory cate = cateMapper.get(Long.valueOf(key));
//			String id = cate.getSid().toString();
			// 查询一级节点
			List<PcmCategory> list = null;
			if (cate == null || "".equals(cate)) {
				list = this.categoryService.getByParentSidAndChannelSid("0", null,
						null, null);
			} else {
//				PcmCategory s = this.categoryService.get(Long.valueOf(id));
				list = this.categoryService.getByParentSidAndChannelSid(cate.getCategorySid(),
						cate.getChannelSid(), null, null);
			}
			PcmRedis redis = new PcmRedis();
			redis.setRedisffield(DomainName.selectCateGory);
			redis.setKeyname(DomainName.selectCateGory+cate.getSid());
			redis.setValue(JsonUtil.getJSONString(list));
//			redis.setStatus(0);
			redisService.savePcmRedis(redis);
		}
	}

	/**
	 * 根据管理分类编码获取子节点编码 
	 */
	@Override
	public List<Map<String, String>> getChildNodeCode(Map<String, Object> paraMap) {
		List<Map<String,String>> resultList = cateMapper.getChildNodeCodeByParentCode(paraMap);
		return resultList;
	}
}

package com.wangfj.product.maindata.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangfj.core.cache.RedisCacheGet;
import com.wangfj.core.cache.RedisVo;
import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.CacheUtils;
import com.wangfj.core.utils.DateUtil;
import com.wangfj.product.category.domain.entity.PcmCategory;
import com.wangfj.product.category.domain.entity.PcmProductCategory;
import com.wangfj.product.category.domain.entity.PcmProductParameters;
import com.wangfj.product.category.domain.vo.PcmProductParametersVo;
import com.wangfj.product.category.persistence.PcmCategoryMapper;
import com.wangfj.product.category.persistence.PcmCategoryPropsDictMapper;
import com.wangfj.product.category.persistence.PcmProductCategoryMapper;
import com.wangfj.product.category.persistence.PcmProductParametersMapper;
import com.wangfj.product.category.service.intf.ICategoryPropValuesService;
import com.wangfj.product.category.service.intf.IProductParametersService;
import com.wangfj.product.constants.DomainName;
import com.wangfj.product.limit.domain.vo.PcmProductOnlineLimitQueryDto;
import com.wangfj.product.limit.persistence.PcmProductOnlineLimitMapper;
import com.wangfj.product.maindata.domain.entity.PcmPictureUrl;
import com.wangfj.product.maindata.domain.entity.PcmProDetail;
import com.wangfj.product.maindata.domain.entity.PcmProduct;
import com.wangfj.product.maindata.domain.entity.PcmProductDesc;
import com.wangfj.product.maindata.domain.entity.PcmProductTag;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProduct;
import com.wangfj.product.maindata.domain.entity.PcmTag;
import com.wangfj.product.maindata.domain.vo.AttributeDto;
import com.wangfj.product.maindata.domain.vo.CategoryDto;
import com.wangfj.product.maindata.domain.vo.CmsProductDto;
import com.wangfj.product.maindata.domain.vo.CmsProductResultDto;
import com.wangfj.product.maindata.domain.vo.PackDescDto;
import com.wangfj.product.maindata.domain.vo.PcmPhotoShoppeDto;
import com.wangfj.product.maindata.domain.vo.PcmProDetailDto;
import com.wangfj.product.maindata.domain.vo.PcmProPhotoDto;
import com.wangfj.product.maindata.domain.vo.PcmProPhotoSubDto;
import com.wangfj.product.maindata.domain.vo.PcmProPhotoTwoDto;
import com.wangfj.product.maindata.domain.vo.PcmProRetPhotoDto;
import com.wangfj.product.maindata.domain.vo.PcmProYeInfoDto;
import com.wangfj.product.maindata.domain.vo.ProSkuSpuPublishDto;
import com.wangfj.product.maindata.domain.vo.ProStanDto;
import com.wangfj.product.maindata.domain.vo.ProductEditDto;
import com.wangfj.product.maindata.domain.vo.ProductOnSellDto;
import com.wangfj.product.maindata.domain.vo.ProductPhotoDto;
import com.wangfj.product.maindata.domain.vo.ProductPicInfoResDto;
import com.wangfj.product.maindata.domain.vo.PublishCacheDto;
import com.wangfj.product.maindata.domain.vo.PushPromotionDto;
import com.wangfj.product.maindata.domain.vo.SaveProductParametersDTO;
import com.wangfj.product.maindata.domain.vo.SkuPageCacheDto;
import com.wangfj.product.maindata.domain.vo.SkuPageDto;
import com.wangfj.product.maindata.domain.vo.TagsPriceStockDto;
import com.wangfj.product.maindata.domain.vo.ValidProDetailDto;
import com.wangfj.product.maindata.persistence.PcmPictureUrlMapper;
import com.wangfj.product.maindata.persistence.PcmProDetailMapper;
import com.wangfj.product.maindata.persistence.PcmProductDescMapper;
import com.wangfj.product.maindata.persistence.PcmProductMapper;
import com.wangfj.product.maindata.persistence.PcmProductTagMapper;
import com.wangfj.product.maindata.persistence.PcmShoppeProductMapper;
import com.wangfj.product.maindata.persistence.PcmTagMapper;
import com.wangfj.product.maindata.service.intf.IPcmProDetailService;
import com.wangfj.product.maindata.service.intf.IPcmProductPictureService;
import com.wangfj.product.maindata.service.intf.IPcmProductService;
import com.wangfj.product.maindata.service.intf.IPcmShoppeProductService;
import com.wangfj.product.maindata.service.intf.IValidProDrtailService;
import com.wangfj.product.stocks.domain.entity.PcmLockAttribute;
import com.wangfj.product.stocks.persistence.PcmLockAttributeMapper;
import com.wangfj.product.stocks.service.intf.IPcmStockService;
import com.wangfj.util.Constants;
import com.wangfj.util.mq.PublishDTO;

@Service
public class PcmProDetailServiceImpl implements IPcmProDetailService {
	private static final Logger logger = LoggerFactory.getLogger(PcmProDetailServiceImpl.class);
	@Autowired
	PcmProductMapper productMapper;
	@Autowired
	private PcmProductOnlineLimitMapper productOnlineLimitMapper;
	@Autowired
	PcmProductDescMapper descMapper;
	@Autowired
	PcmProDetailMapper proDetailMapper;
	@Autowired
	PcmCategoryMapper cateMapper;
	@Autowired
	PcmCategoryPropsDictMapper propsMapper;
	@Autowired
	PcmProductParametersMapper parMapper;
	@Autowired
	PcmShoppeProductMapper spMapper;
	@Autowired
	IPcmShoppeProductService spService;
	@Autowired
	PcmProductCategoryMapper productCategoryMapper;
	@Autowired
	private PcmLockAttributeMapper lockMapper;
	@Autowired
	IProductParametersService parametersService;
	@Autowired
	IPcmStockService stockService;
	@Autowired
	private PcmTagMapper tagMapper;
	@Autowired
	private ICategoryPropValuesService catePropService;
	@Autowired
	private PcmProductTagMapper ppTagMapper;
	@Autowired
	private PcmProductParametersMapper pppMapper;
	@Autowired
	private PcmPictureUrlMapper urlMappper;
	@Autowired
	private IPcmProductService spuService;
	@Autowired
	private IPcmProductPictureService picService;

	List<PublishDTO> sidList = null;

	List<PublishDTO> skuList = null;

	List<PublishDTO> spuList = null;

	@Autowired
	IValidProDrtailService validService;

	@Override
	public List<ProductPicInfoResDto> getProductInfoBySpuAndColor(Map<String, Object> paramMap) {
		List<ProductPicInfoResDto> productInfoList = spMapper.getProductInfoBySpuAndColor(paramMap);
		return productInfoList;
	}

	/**
	 * sku信息基础查询
	 * 
	 * @Methods Name selectListByParam
	 * @Create In 2016年3月10日 By yedong
	 * @param entity
	 * @return List<PcmProDetail>
	 */
	public List<PcmProDetail> selectListByParam(PcmProDetail entity) {
		List<PcmProDetail> skuList = proDetailMapper.selectListByParam(entity);
		return skuList;
	}

	@Override
	public Map<String, Object> getProYeBySkuOrSpu(Map<String, Object> paramMap) {
		String spuCode = (String) paramMap.get("spuCode");
		if (spuCode.length() == 13) {
			PcmProDetail sku = new PcmProDetail();
			sku.setProductDetailSid(spuCode);
			List<PcmProDetail> selectListByParam = proDetailMapper.selectListByParam(sku);
			PcmProduct spu = new PcmProduct();
			spu.setSid(selectListByParam.get(0).getProductSid());
			List<PcmProduct> selectListByParam2 = productMapper.selectListByParam(spu);
			paramMap.put("skuCode", spuCode);
			paramMap.put("spuCode", selectListByParam2.get(0).getProductSid());
		}
		return paramMap;
	}

	/**
	 * 单品页接口
	 * 
	 * @Methods Name getProYeInfoBySpuCode
	 * @Create In 2015年12月21日 By yedong
	 * @param paramMap
	 * @return PcmProYeInfoDto
	 */
	@Override
	@RedisCacheGet(redisName = DomainName.getCMSSHopperInfo, returnObj = "com.wangfj.product.maindata.domain.vo.PcmProYeInfoDto")
	public PcmProYeInfoDto getProYeInfoBySpuCode(String key, Map<String, Object> paramMap) {

		return null;
	}

	public Page<SkuPageDto> selectSkuPage(SkuPageDto dto) {
		logger.info("start selectSkuPage()----param:" + dto.toString());
		Page<SkuPageDto> page = new Page<SkuPageDto>();
		if (dto.getCurrentPage() != null && dto.getCurrentPage() != 0) {
			page.setCurrentPage(dto.getCurrentPage());
		}
		if (dto.getPageSize() != null && dto.getPageSize() != 0) {
			page.setPageSize(dto.getPageSize());
		} else {
			// 无pageSize参数时最大不超过20条商品信息数据
			page.setPageSize(20);
		}
		// 查询总数量
		Integer count = proDetailMapper.getProductCountByPara(dto);
		if (count > 0) {
			page.setCount(count);
			if (dto.getStart() == null && dto.getLimit() == null) {
				dto.setStart(page.getStart());
				dto.setLimit(page.getLimit());
			} else {
				page.setStart(dto.getStart());
				page.setLimit(dto.getLimit());
			}
			List<String> getskuSidList = proDetailMapper.getskuSidList(dto);
			dto.setSidList(getskuSidList);
			List<SkuPageDto> list = proDetailMapper.selectProductPageByPara2(dto);

			page.setList(list);
		} else {
			logger.info("查询结果为空----param:" + dto.toString());
			page.setList(null);
		}

		logger.info("end selectSkuPage()----param:" + page.toString());
		return page;
	}

	// @RedisCacheGet(redisName = DomainName.getShoppeInfo, returnObj =
	// "com.wangfj.product.maindata.domain.vo.SkuPageCacheDto")
	public SkuPageCacheDto selectSkuPageCache(String skuPage, SkuPageDto dto) {
		logger.info("start selectSkuPage()----param:" + dto.toString());
		Page<SkuPageDto> page = new Page<SkuPageDto>();
		if (dto.getCurrentPage() != null && dto.getCurrentPage() != 0) {
			page.setCurrentPage(dto.getCurrentPage());
		}
		if (dto.getPageSize() != null && dto.getPageSize() != 0) {
			page.setPageSize(dto.getPageSize());
		} else {
			// 无pageSize参数时最大不超过20条商品信息数据
			page.setPageSize(20);
		}
		// 查询总数量
		Integer count = proDetailMapper.getProductCountByPara(dto);
		if (count > 0) {
			page.setCount(count);
			if (dto.getStart() == null && dto.getLimit() == null) {
				dto.setStart(page.getStart());
				dto.setLimit(page.getLimit());
			} else {
				page.setStart(dto.getStart());
				page.setLimit(dto.getLimit());
			}

			List<String> getskuSidList = proDetailMapper.getskuSidList(dto);
			dto.setSidList(getskuSidList);
			List<SkuPageDto> list = proDetailMapper.selectProductPageByPara2(dto);

			page.setList(list);
		} else {
			logger.info("查询结果为空----param:" + dto.toString());
			page.setList(null);
		}
		SkuPageCacheDto spcd = new SkuPageCacheDto();
		spcd.setPage(page);
		logger.info("end selectSkuPage()----param:" + page.toString());
		return spcd;
	}

	/**
	 * 由主数据获取待拍照商品信息 由主数据获取尺码信息
	 * 
	 * @Methods Name selectProductPhotoByPara
	 * @Create In 2015年9月30日 By yedong
	 * @param dto
	 * @return ProductPhotoDto
	 */
	public List<ProductPhotoDto> selectProductPhotoByPara(ProductPhotoDto dto) {
		logger.info("start selectProductPhotoByPara()----param:" + dto.toString());
		List<ProductPhotoDto> dtoList = proDetailMapper.selectProductPhotoByPara(dto);
		if (dtoList != null && dtoList.size() != 0) {
			for (ProductPhotoDto photo : dtoList) {
				photo.setSpuSid(null);
				PcmProductParameters record = new PcmProductParameters();
				record.setProductSid(Long.parseLong(photo.getSpuSid()));
				List<PcmProductParametersVo> attrList = parMapper.selectProductBySpuSid(record);
				List<AttributeDto> attrsList = new ArrayList<AttributeDto>();
				if (attrList != null && attrList.size() != 0) {
					for (PcmProductParametersVo attr : attrList) {
						AttributeDto attrDto = new AttributeDto();
						attrDto.setAttrCode(attr.getPropSid().toString());
						attrDto.setAttrValue(attr.getValueName());
						attrDto.setAttrType(attr.getIsEnumProp().toString());
						attrsList.add(attrDto);
					}
				}
				photo.setAttrList(attrsList);
			}
		} else {
			logger.info("找不到对应的专柜商品信息");
			throw new BleException(ErrorCode.SHOPPEPRODUCT_NULL.getErrorCode(),
					ErrorCode.SHOPPEPRODUCT_NULL.getMemo());
		}
		logger.info("end selectProductPhotoByPara()----return:" + dtoList.toString());
		return dtoList;
	}

	/**
	 * 由主数据获取尺码信息
	 * 
	 * @Methods Name selectStan
	 * @Create In 2015年10月8日 By yedong
	 * @param dto
	 * @return List<ProStanDto>
	 */
	public List<ProStanDto> selectStan(List<ProStanDto> dtoList) {
		List<ProStanDto> dtoLists = new ArrayList<ProStanDto>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (ProStanDto dto : dtoList) {
			list = proDetailMapper.selectStanByPara(dto);
			ProStanDto dto_1 = new ProStanDto();
			dto_1.setStoreCode(dto.getStoreCode());
			dto_1.setColorCode(dto.getColorCode());
			dto_1.setProduct_sid(dto.getProduct_sid());
			dto_1.setShoppeCode(dto.getShoppeCode());
			dto_1.setSize(list);
			dtoLists.add(dto_1);
		}
		return dtoLists;
	}

	/**
	 * 回传商品编辑到主数据
	 * 
	 * @Methods Name updateProductEdit
	 * @Create In 2015年10月8日 By zhangxueyi
	 * @param dto
	 */
	@Override
	@Transactional
	public void updateProductEdit(ProductEditDto dto) throws BleException {
		logger.info("updateProductEdit() start,para:" + dto.toString());
		// 根据产品编码,色系 查询sku列表
		List<PcmProDetail> list = proDetailMapper.selectProductEditByPara(dto);
		if (list == null || list.size() == 0) {
			logger.error(ErrorCode.PRODUCT_IS_NULL.getErrorCode(),
					ErrorCode.PRODUCT_IS_NULL.getMemo());
			throw new BleException(ErrorCode.PRODUCT_IS_NULL.getErrorCode(),
					ErrorCode.PRODUCT_IS_NULL.getMemo());
		}
		// 修改SPU产品信息
		PcmProduct pp = new PcmProduct();
		pp.setSid(list.get(0).getProductSid());
		pp.setShortDes(dto.getShortDescription());// 短描述
		pp.setSpecialDes(StringUtils.isNotBlank(dto.getSpecialDescription()) ? dto
				.getSpecialDescription() : null);// 特别说明
		pp.setProductNameAlias(StringUtils.isNotBlank(dto.getDisplayName()) ? dto.getDisplayName()
				: null);// 商品展示名称
		pp.setLongDesc(dto.getLongDescription());
		int i2 = productMapper.updateByPrimaryKeySelective(pp);
		if (i2 != 1) {
			logger.error(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
					ErrorCode.DATA_OPER_ERROR.getMemo());
			throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
					ErrorCode.DATA_OPER_ERROR.getMemo());
		}
		// 删除之前的品类关联
		PcmProductCategory ppc = new PcmProductCategory();
		ppc.setProductSid(pp.getSid());
		productCategoryMapper.deleteZSCateByRecord(ppc);
		// 关联展示分类
		for (CategoryDto cate : dto.getChannelCategory()) {
			PcmCategory cateEntity = cateMapper.selectByCategorySid(cate.getCategoryCode());
			if (cateEntity == null) {
				logger.error("test111", cate.getCategoryCode() + " 展示分类不存在");
				throw new BleException("test111", cate.getCategoryCode() + " 展示分类不存在");
			}
			PcmProductCategory ppc1 = new PcmProductCategory();
			ppc1.setCategorySid(cateEntity.getSid());// 统计分类
			ppc1.setProductSid(pp.getSid());// SPU产品表sid
			ppc1.setChannelSid(cateEntity.getChannelSid());
			// ppcTJ.setOptUser("拍照");// 操作人
			ppc1.setOptDate(new Date());// 时间
			int i = productCategoryMapper.insertSelective(ppc1);
			if (i == Constants.PUBLIC_0) {
				logger.error(ErrorCode.ADD_CATE_ERROR.getErrorCode(),
						ErrorCode.ADD_CATE_ERROR.getMemo());
				throw new BleException(ErrorCode.ADD_CATE_ERROR.getErrorCode(),
						ErrorCode.ADD_CATE_ERROR.getMemo());
			}
			SaveProductParametersDTO sppDto = new SaveProductParametersDTO();
			sppDto.setParameters(cate.getAttributeList());
			sppDto.setCategoryName(cateEntity.getName());
			sppDto.setCategorySid(String.valueOf(cateEntity.getSid()));
			sppDto.setCategoryType(String.valueOf(cateEntity.getCategoryType()));
			sppDto.setChannelSid(String.valueOf(cateEntity.getChannelSid()));
			sppDto.setSpuSid(String.valueOf(pp.getSid()));
			parametersService.insertProductParamter(sppDto);
		}

		if (dto.getKeyWords() == null || dto.getKeyWords() == "" || dto.getSearchKey() == null
				|| dto.getSearchKey() == "") {
			return;
		} else {
			String endDate = "9999-12-31 00:00:00";
			PcmTag tag_1 = new PcmTag();// 关键字
			tag_1.setTagName(dto.getKeyWords());
			tag_1.setTagType(2);
			List<PcmTag> tagList_1 = tagMapper.selectListByParam(tag_1);
			if (tagList_1 != null && tagList_1.size() > 0) {
				tag_1.setSid(tagList_1.get(0).getSid());
			} else {
				tag_1.setBeginDate(new Date());
				tag_1.setEndDate(DateUtil.formatDate(endDate, "yyyy-MM-dd HH:mm:ss"));
				tagMapper.insertSelective(tag_1);
			}
			PcmTag tag_2 = new PcmTag();// 活动关键词
			tag_2.setTagName(dto.getSearchKey());
			tag_2.setTagType(3);
			List<PcmTag> tagList_2 = tagMapper.selectListByParam(tag_2);
			if (tagList_2 != null && tagList_2.size() > 0) {
				tag_2.setSid(tagList_2.get(0).getSid());
			} else {
				tag_2.setBeginDate(new Date());
				tag_2.setEndDate(DateUtil.formatDate(endDate, "yyyy-MM-dd HH:mm:ss"));
				tagMapper.insertSelective(tag_2);
			}
			if (list != null && list.size() > 0) {
				// 修改SKU信息
				for (PcmProDetail entity : list) {
					PcmProductTag ppt = new PcmProductTag();
					ppt.setTagSid(tag_1.getSid());
					ppt.setProductSid((entity.getProductDetailSid()));
					List<PcmProductTag> pptList_1 = ppTagMapper.selectListByParam(ppt);
					if (pptList_1 != null && pptList_1.size() > 0) {
					} else {
						int i3 = ppTagMapper.insertSelective(ppt);
						if (i3 != 1) {
							logger.error("SKU-标签关系添加失败");
							throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
									ErrorCode.DATA_OPER_ERROR.getMemo());
						}
					}
					ppt.setTagSid(tag_2.getSid());
					List<PcmProductTag> pptList_2 = ppTagMapper.selectListByParam(ppt);
					if (pptList_2 != null && pptList_2.size() > 0) {
					} else {
						int i4 = ppTagMapper.insertSelective(ppt);
						if (i4 != 1) {
							logger.error("SKU-标签关系添加失败");
							throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
									ErrorCode.DATA_OPER_ERROR.getMemo());
						}
					}
				}
			}
		}
		logger.info("updateProductEdit() end");
	}

	/**
	 * 由拍照系统上传精包装信息
	 * 
	 * @Methods Name updatePackDesc
	 * @Create In 2015年10月16日 By zhangxueyi
	 * @param dto
	 * @return
	 */
	@Override
	@Transactional
	public void updatePackDesc(PackDescDto para) throws BleException {
		logger.info("updatePackDesc() start,para:" + para.toString());
		PcmProduct pp = new PcmProduct();
		pp.setProductSid(para.getProductCode());
		List<PcmProduct> list = productMapper.selectListByParam(pp);
		if (list != null & list.size() == 1) {
			// 根据产品编码,色系 查询sku列表
			ProductEditDto dto = new ProductEditDto();
			dto.setProduct_sid(para.getProductCode());
			dto.setColorCode(para.getColorCode());
			List<PcmProDetail> list1 = proDetailMapper.selectProductEditByPara(dto);
			if (list1 == null || list1.size() == 0) {
				throw new BleException(ErrorCode.PRODUCT_IS_NULL.getErrorCode(),
						ErrorCode.PRODUCT_IS_NULL.getMemo());
			}
			// 写入文本描述表
			PcmProductDesc desc = new PcmProductDesc();
			desc.setProductSid(para.getProductCode());
			desc.setColor(para.getColorCode());
			List<PcmProductDesc> list2 = descMapper.selectListByParam(desc);
			byte[] content = para.getPackDesc().getBytes();
			desc.setContent(content);
			if (list2 != null & list2.size() > 0) {
				desc.setSid(list2.get(0).getSid());
				int u1 = descMapper.updateByPrimaryKeySelective(desc);
				if (u1 != 1) {
					logger.error(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
							ErrorCode.DATA_OPER_ERROR.getMemo());
					throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
							ErrorCode.DATA_OPER_ERROR.getMemo());
				}
			} else {
				int i1 = descMapper.insertSelective(desc);
				if (i1 != 1) {
					logger.error(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
							ErrorCode.DATA_OPER_ERROR.getMemo());
					throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
							ErrorCode.DATA_OPER_ERROR.getMemo());
				}
			}
		} else {
			throw new BleException(ErrorCode.PRODUCT_IS_NULL.getErrorCode(),
					ErrorCode.PRODUCT_IS_NULL.getMemo());
		}
		logger.info("updatePackDesc() end");
	}

	/**
	 * 由主数据获取待拍照商品信息上线计划
	 * 
	 * @Methods Name itemsListByPhotoPlan
	 * @Create In 2015年9月30日 By yedong
	 * @param dto
	 * @return ProductPhotoDto
	 * @throws Exception
	 */
	public Page<PcmProPhotoDto> itemsListByPhotoPlan(PcmProRetPhotoDto dto) throws Exception {
		logger.info("start itemsListByPhotoPlan()----param:" + dto.toString());
		Page<PcmProPhotoDto> page = new Page<PcmProPhotoDto>();
		page.setPageSize(dto.getPageSize());
		page.setCurrentPage(dto.getCurrentPage());
		Integer count = spMapper.itemsCountByPhotoPlan(dto);
		if (count != 0) {
			page.setCount(count);
			dto.setStart(page.getStart());
			dto.setLimit(page.getLimit());
			List<PcmProPhotoDto> dtoList = spMapper.itemsListByPhotoPlan(dto);
			if (dtoList != null && dtoList.size() != 0) {
				for (PcmProPhotoDto pcmProPhotoDto : dtoList) {
					if (pcmProPhotoDto.getContent() != null
							&& pcmProPhotoDto.getContent().length != 0) {
						byte[] content = pcmProPhotoDto.getContent();
						String contents = new String(content, "UTF-8");
						pcmProPhotoDto.setContents(contents);
					}
				}
			}
			page.setList(dtoList);
		}
		logger.info("end itemsListByPhotoPlan()----return:" + page.toString());
		return page;
	}

	/**
	 * 由主数据获取待拍照商品信息
	 * 
	 * @Methods Name selectProductPhotoByPara
	 * @Create In 2015年9月30日 By yedong
	 * @param dto
	 * @return ProductPhotoDto
	 * @throws Exception
	 */
	public List<PcmProPhotoDto> selectProPhotoByPara(PcmProRetPhotoDto dto) throws Exception {
		logger.info("start selectProductPhotoByPara()----param:" + dto.toString());
		if (dto.getBrandCode() == "") {
			dto.setBrandCode(null);
		}
		if (dto.getCategory() == "") {
			dto.setCategory(null);
		}
		if (dto.getColorName() == "") {
			dto.setColorName(null);
		}
		if (dto.getModelCode() == "") {
			dto.setModelCode(null);
		}
		if (dto.getSpecialCounterName() == "") {
			dto.setSpecialCounterName(null);
		}
		if (dto.getPhotoStatus() == "") {
			dto.setPhotoStatus(null);
		}
		if (dto.getProductCode() == "") {
			dto.setProductCode(null);
		}
		if (dto.getStoreCode() == "") {
			dto.setStoreCode(null);
		}
		if (dto.getPlanStatus() == "") {
			dto.setPlanStatus(null);
		}
		List<PcmProPhotoDto> dtoList = spMapper.itemsListSelect(dto);
		if (dtoList != null && dtoList.size() != 0) {
			for (PcmProPhotoDto pcmProPhotoDto : dtoList) {
				if (pcmProPhotoDto.getContent() != null && pcmProPhotoDto.getContent().length != 0) {
					byte[] content = pcmProPhotoDto.getContent();
					String contents = new String(content, "UTF-8");
					pcmProPhotoDto.setContents(contents);
				}
			}
		} else {
			logger.info("找不到对应的专柜商品信息");
			throw new BleException(ErrorCode.SHOPPEPRODUCT_NULL.getErrorCode(),
					ErrorCode.SHOPPEPRODUCT_NULL.getMemo());
		}
		logger.info("end selectProductPhotoByPara()----return:" + dtoList.toString());
		return dtoList;
	}

	/**
	 * 由主数据获取待拍照商品信息2
	 * 
	 * @Methods Name selectProPhotoByParaTwo
	 * @Create In 2016-1-13 By wangc
	 * @param dto
	 * @return
	 * @throws Exception
	 *             List<PcmProPhotoDto>
	 */
	public List<PcmProPhotoTwoDto> selectProPhotoByParaTwo(PcmProRetPhotoDto dto) throws Exception {
		logger.info("start selectProductPhotoByParaTwo()----param:" + dto.toString());
		if (dto.getBrandCode() == "") {
			dto.setBrandCode(null);
		}
		if (dto.getCategory() == "") {
			dto.setCategory(null);
		}
		if (dto.getColorName() == "") {
			dto.setColorName(null);
		}
		if (dto.getModelCode() == "") {
			dto.setModelCode(null);
		}
		if (dto.getSpecialCounterName() == "") {
			dto.setSpecialCounterName(null);
		}
		if (dto.getPhotoStatus() == "") {
			dto.setPhotoStatus(null);
		}
		if (dto.getProductCode() == "") {
			dto.setProductCode(null);
		}
		if (dto.getStoreCode() == "") {
			dto.setStoreCode(null);
		}
		if (dto.getPlanStatus() == "") {
			dto.setPlanStatus(null);
		}
		if (dto.getSellingStatus() == "") {
			dto.setSellingStatus(null);
		}
		if (dto.getShoProCode() == "") {
			dto.setShoProCode(null);
		}
		if (dto.getSapShoProCode() == "") {
			dto.setSapShoProCode(null);
		}
		if (dto.getSapShoProCode() != null && dto.getSapShoProCode() != "") {
			String[] split = dto.getSapShoProCode().split(",");
			List<String> sapCodeList = new ArrayList<String>();
			for (String string : split) {
				sapCodeList.add(string);
			}
			dto.setSapCodeList(sapCodeList);
		}
		List<PcmProPhotoTwoDto> dtoList = spMapper.itemsListSelectTwo(dto);
		if (dtoList != null && dtoList.size() != 0) {
			for (PcmProPhotoTwoDto pcmProPhotoTwoDto : dtoList) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				pcmProPhotoTwoDto.setProAccessTime(sdf.format(pcmProPhotoTwoDto.getAccessTime()));
				if (pcmProPhotoTwoDto.getContent() != null
						&& pcmProPhotoTwoDto.getContent().length != 0) {
					byte[] content = pcmProPhotoTwoDto.getContent();
					String contents = new String(content, "UTF-8");
					pcmProPhotoTwoDto.setContents(contents);
				}
			}
		} else {
			logger.info("找不到对应的专柜商品信息");
			throw new BleException(ErrorCode.SHOPPEPRODUCT_NULL.getErrorCode(),
					ErrorCode.SHOPPEPRODUCT_NULL.getMemo());
		}
		logger.info("end selectProductPhotoByParaTwo()----return:" + dtoList.toString());
		return dtoList;
	}

	public List<PcmProPhotoSubDto> shoppePhotoByParam(PcmPhotoShoppeDto dto) {
		List<PcmProPhotoSubDto> list = new ArrayList<PcmProPhotoSubDto>();
		list = proDetailMapper.selectShoppeByPara(dto);
		return list;
	}

	public List<CmsProductResultDto> cmsSelectProductInfo(CmsProductDto dto) {
		List<CmsProductResultDto> infoList = proDetailMapper.cmsProductInfo(dto);
		return infoList;
	}

	public List<CmsProductResultDto> cmsSelectProductInfoBySid(List<String> list) {
		List<CmsProductResultDto> infoList = proDetailMapper.cmsProductInfoBySid(list);
		return infoList;
	}

	/**
	 * SKU 启用状态修改
	 * 
	 * @Methods Name proDetailDisable
	 * @Create In 2015年11月3日 By yedong
	 * @param entity
	 * @return boolean
	 */
	@Transactional
	public List<PublishDTO> updateProDetailDisable(List<PcmProDetail> list) {
		List<PublishDTO> pushDto = new ArrayList<PublishDTO>();
		List<String> skuSidList = new ArrayList<String>();
		for (PcmProDetail entity : list) {
			skuSidList.add(entity.getSid().toString());
			// if (entity.getProActiveBit() == 0) {
			// entity.setSellingStatus(2);
			// }
			int disable = proDetailMapper.updateByPrimaryKeySelective(entity);
			if (disable != 1) {
				logger.error("数据库异常");
				throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
						ErrorCode.DATA_OPER_ERROR.getMemo());
			}
			PcmShoppeProduct psp = new PcmShoppeProduct();
			psp.setProductDetailSid((entity.getSid()));
			if (entity.getProActiveBit() == 1) {
				psp.setSaleStatus(0);
			} else {
				psp.setSaleStatus(1);
			}
			int i = spMapper.updateBySku(psp);
			psp.setSaleStatus(null);
			List<PcmShoppeProduct> list2 = spMapper.selectListByParam(psp);
			for (PcmShoppeProduct sp : list2) {
				PublishDTO dto = new PublishDTO();
				dto.setSid(sp.getSid());
				dto.setType(1);
				pushDto.add(dto);
				RedisVo vo2 = new RedisVo();
				vo2.setKey(sp.getShoppeProSid());
				vo2.setField(DomainName.getShoppeInfo);
				vo2.setType(CacheUtils.HDEL);
				CacheUtils.setRedisData(vo2);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", skuSidList);
		List<Map<String, Object>> spuColorList = proDetailMapper.getSpuColorBySkuSids(map);
		for (Map<String, Object> map2 : spuColorList) {
			int parseInt = Integer.parseInt(map2.get("spuSid").toString());
			long aa = parseInt + Constants.SPU_CODE;
			String spuCode = aa + "";
			picService.redisSpuCMSSHopperInfo(spuCode);
		}

		return pushDto;
	}

	// /**
	// * SKU上架状态修改
	// *
	// * @Methods Name proDetailDisable
	// * @Create In 2015年11月3日 By yedong
	// * @param entity
	// * @return boolean
	// */
	// @Transactional
	// @Override
	// public PcmProDetail updateProDetailSellAdmin(PcmProDetail entity) throws
	// BleException {
	// PcmProDetail record =
	// proDetailMapper.selectByPrimaryKey(entity.getSid());
	// String msg = "";
	// if (entity.getSellingStatus() == 1) {// 上架
	// msg = "上架";
	// // 拍照状态判断
	// if (4 != record.getPhotoStatus()) {
	// logger.error(ErrorCode.PRO_PHOTO_STUATS_ERROR.getMemo());
	// throw new BleException(ErrorCode.PRO_PHOTO_STUATS_ERROR.getErrorCode(),
	// "商品("
	// + record.getProductDetailSid() + ")" + msg + "失败:"
	// + ErrorCode.PRO_PHOTO_STUATS_ERROR.getMemo());
	// }
	// // 库存判断
	// PcmProductStockInfoDto dto = new PcmProductStockInfoDto();
	// dto.setSkuSid(entity.getSid().toString());
	// PcmProductStockInfoDto resDto = stockService.SelectSkuStockSumBySku(dto);
	// PcmLockAttribute lock = new PcmLockAttribute();
	// lock.setDistributedLock("onSellInventory");
	// List<PcmLockAttribute> li = lockMapper.selectListByParam(lock);
	// if (li == null || li.size() == 0) {
	// logger.error(ErrorCode.INVENTORY_MISS.getMemo());
	// throw new BleException(ErrorCode.INVENTORY_MISS.getErrorCode(), "商品("
	// + record.getProductDetailSid() + ")" + msg + "失败:"
	// + ErrorCode.INVENTORY_MISS.getMemo());
	// }
	// if (resDto.getSaleSum() < li.get(0).getButton()) {
	// logger.error(ErrorCode.INVENTORY_SHORT.getMemo());
	// throw new BleException(ErrorCode.INVENTORY_SHORT.getErrorCode(), "商品("
	// + record.getProductDetailSid() + ")" + msg + "失败:"
	// + ErrorCode.INVENTORY_SHORT.getMemo());
	// }
	// } else {// 下架
	// msg = "下架";
	// }
	// int disable = proDetailMapper.updateByPrimaryKeySelective(entity);
	// if (disable != 1) {
	// logger.error("数据库异常");
	// throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(), "商品("
	// + record.getProductDetailSid() + ")" + msg + "失败:"
	// + ErrorCode.DATA_OPER_ERROR.getMemo());
	// }
	// PcmProduct product = null;
	// if (entity.getSellingStatus() == 1) {// 上架
	// product =
	// productMapper.selectByPrimaryKey(Long.parseLong(record.getProductSid()));
	// if (product.getProSelling() == 0 || product.getProSelling() == 2) {
	// product.setProSelling(1);
	// product.setProSellingTime(new Date());
	// } else {
	// product = null;
	// }
	// } else {// 下架
	// PcmProDetail ppd = new PcmProDetail();
	// ppd.setProductSid(record.getProductSid());
	// ppd.setSellingStatus(1);
	// List<PcmProDetail> list = proDetailMapper.selectListByParam(ppd);
	// if (list == null || list.size() == 0) {
	// product = new PcmProduct();
	// product.setSid(Long.parseLong(record.getProductSid()));
	// product.setProSelling(2);
	// }
	// }
	// if (product != null) {
	// int i2 = productMapper.updateByPrimaryKeySelective(product);
	// if (i2 != 1) {
	// logger.error("数据库异常");
	// throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(), "商品("
	// + record.getProductDetailSid() + ")" + msg + "失败:"
	// + ErrorCode.DATA_OPER_ERROR.getMemo());
	// }
	// record.setBarcode("PUSHSPU");
	// }
	// return record;
	// }

	/**
	 * SKU上架状态修改
	 * 
	 * @Methods Name proDetailDisable
	 * @Create In 2015年11月3日 By zhangxy
	 * @param entity
	 * @return boolean
	 */
	@Transactional
	@Override
	public ProSkuSpuPublishDto updateProDetailSell(ProductOnSellDto entity) throws BleException {
		ProSkuSpuPublishDto publishDto = new ProSkuSpuPublishDto();
		List<PublishDTO> spuList1 = new ArrayList<PublishDTO>();
		List<PublishDTO> skuList1 = new ArrayList<PublishDTO>();
		List<PublishDTO> proList1 = new ArrayList<PublishDTO>();
		if (entity.getStatus() == 1) {// 上架
			// 商品可售状态
			if (entity.getSkuSell().equals("0")) {
				logger.error(ErrorCode.SKU_NOT_SALE.getMemo());
				throw new BleException(ErrorCode.SKU_NOT_SALE.getErrorCode(),
						ErrorCode.SKU_NOT_SALE.getMemo());
			}
			// 拍照状态判断
			if (4 != entity.getPhotoStatus() && 3 != entity.getPhotoStatus()) {
				logger.error(ErrorCode.PRO_PHOTO_STUATS_ERROR.getMemo());
				throw new BleException(ErrorCode.PRO_PHOTO_STUATS_ERROR.getErrorCode(),
						ErrorCode.PRO_PHOTO_STUATS_ERROR.getMemo());
			}
			// 非赠品商品判断价格是否大于0
			if (!entity.getProType().equals("2")) {
				if (entity.getOrgPrice() <= 0) {
					logger.error(ErrorCode.PRICE_GT_LING.getMemo());
					throw new BleException(ErrorCode.PRICE_GT_LING.getErrorCode(),
							ErrorCode.PRICE_GT_LING.getMemo());
				}
			}
			// 判断是否有展示分类
			PcmProductParameters ppp = new PcmProductParameters();
			ppp.setProductSid(entity.getSpuSid());
			ppp.setCategoryType(Constants.PCM_CATEGORY_TYPE_ZS);
			List<PcmProductParameters> selectList = pppMapper.selectCateBySpuCode(ppp);
			if (selectList == null || selectList.size() == 0) {
				logger.error(ErrorCode.PRO_ZS_CATEGORY_NULL.getMemo());
				throw new BleException(ErrorCode.PRO_ZS_CATEGORY_NULL.getErrorCode(),
						ErrorCode.PRO_ZS_CATEGORY_NULL.getMemo());
			}
			// 判断是否存在主图
			PcmPictureUrl urlEntity = new PcmPictureUrl();
			urlEntity.setSkuSid(entity.getSpuCode());
			urlEntity.setColorCode(entity.getColor().toString());
			urlEntity.setIsPrimary(Constants.PUBLIC_0);
			urlEntity.setIfDelete(Constants.PUBLIC_0);
			List<PcmPictureUrl> selectUrl = urlMappper.selectListByParam(urlEntity);
			if (selectUrl == null || selectUrl.size() == 0) {
				logger.error(ErrorCode.PRO_PRIMARY_URL_NULL.getMemo());
				throw new BleException(ErrorCode.PRO_PRIMARY_URL_NULL.getErrorCode(),
						ErrorCode.PRO_PRIMARY_URL_NULL.getMemo());
			}
			// 库存判断
			// 查询阀值
			PcmProductOnlineLimitQueryDto ppolqDto = new PcmProductOnlineLimitQueryDto();
			ppolqDto.setBrandSid(Long.parseLong(entity.getBrandSid()));
			ppolqDto.setCategorySid(Long.parseLong(entity.getCategorySid()));
			Integer limitValue = proDetailMapper.selectLimitValueByBrandCategory(entity);
			if (limitValue != null && limitValue != 0) {// 有阀值信息
				if (entity.getSaleSum() == null || entity.getSaleSum() < limitValue) {
					logger.error(ErrorCode.INVENTORY_SHORT.getMemo());
					throw new BleException(ErrorCode.INVENTORY_SHORT.getErrorCode(),
							ErrorCode.INVENTORY_SHORT.getMemo());
				}
			} else {// 无阀值信息时 取默认阀值
				PcmLockAttribute lock = new PcmLockAttribute();
				lock.setDistributedLock("onSellInventory");
				List<PcmLockAttribute> li = lockMapper.selectListByParam(lock);
				if (li == null || li.size() == 0) {
					logger.error(ErrorCode.INVENTORY_MISS.getMemo());
					throw new BleException(ErrorCode.INVENTORY_MISS.getErrorCode(),
							ErrorCode.INVENTORY_MISS.getMemo());
				}
				if (entity.getSaleSum() == null || entity.getSaleSum() < li.get(0).getButton()) {
					logger.error(ErrorCode.INVENTORY_SHORT.getMemo());
					throw new BleException(ErrorCode.INVENTORY_SHORT.getErrorCode(),
							ErrorCode.INVENTORY_SHORT.getMemo());
				}
			}
		} else {// 下架

		}
		PcmProDetail sku = new PcmProDetail();
		sku.setProductSid(entity.getSpuSid());
		sku.setProColorSid(entity.getColor());
		if (entity.getStatus() == 1) {
			sku.setProActiveBit(1);
		}
		List<PcmProDetail> list3 = proDetailMapper.selectListByParam(sku);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list3);
		map.put("sellingStatus", entity.getStatus());
		map.put("planTime", new Date());
		int disable = proDetailMapper.updateSelectiveByList(map);
		if (disable == 0) {
			logger.error("数据库异常");
			throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
					ErrorCode.DATA_OPER_ERROR.getMemo());
		}
		for (PcmProDetail publish : list3) {
			PublishDTO dto = new PublishDTO();
			dto.setSid(publish.getSid());
			dto.setType(1);
			skuList1.add(dto);
			List<PublishDTO> proList = spService.selectSidsBySku(publish.getSid());
			proList1.addAll(proList);
		}
		PcmProduct product = null;
		if (entity.getStatus() == 1) {// 上架
			product = productMapper.selectByPrimaryKey(entity.getSpuSid());
			if (product.getProSelling() == 0 || product.getProSelling() == 2) {
				product.setProSelling(1);
				if (product.getProSellingTime() == null) {
					product.setProSellingTime(new Date());
				}
			}
			publishDto.setProType("0");
		} else {// 下架
			PcmProDetail ppd = new PcmProDetail();
			ppd.setProductSid(entity.getSpuSid());
			ppd.setSellingStatus(1);
			ppd.setProActiveBit(1);
			List<PcmProDetail> list2 = proDetailMapper.selectListByParam(ppd);
			if (list2 == null || list2.size() == 0) {
				product = new PcmProduct();
				product.setSid(entity.getSpuSid());
				product.setProSelling(2);
			}
			publishDto.setProType("1");
		}
		if (product != null) {
			int i2 = productMapper.updateByPrimaryKeySelective(product);
			PublishDTO dto = new PublishDTO();
			dto.setSid(product.getSid());
			dto.setType(1);
			spuList1.add(dto);
			if (i2 != 1) {
				logger.error("数据库异常");
				throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
						ErrorCode.DATA_OPER_ERROR.getMemo());
			}
		}
		publishDto.setSkuList(skuList1);
		publishDto.setSpuList(spuList1);
		publishDto.setProList(proList1);
		return publishDto;
	}

	/**
	 * 下发SKU至促销
	 */
	@Override
	public List<PushPromotionDto> getPushPromotionDtoBySid(Map<String, Object> paramMap) {
		List<PushPromotionDto> dto = proDetailMapper.getPushPromotionDtoBySid(paramMap);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd.HHmmssZ");
		String str = sdf.format(date);
		for (int i = 0; i < dto.size(); i++) {
			dto.get(i).setActionCode("A");
			dto.get(i).setActionDate(str);
			if (dto.get(i).getPackDesE() != null) {
				dto.get(i).setPackDes(new String(dto.get(i).getPackDesE()));
			}
			dto.get(i).setMgmtType("1");
			dto.get(i).setErpProductCategoryLib("6");
			dto.get(i).setProductType("02");
		}
		return dto;
	}

	/**
	 * 根据SKU编码查询所有同SPU下的所有SKU
	 * 
	 * @Methods Name selectSkuItemByCode
	 * @Create In 2015年11月16日 By yedong
	 * @param paramMap
	 * @return List<PcmProDetail>
	 */
	public List<PcmProDetail> selectSkuItemByCode(Map<String, Object> paramMap) {
		List<PcmProDetail> list = proDetailMapper.selectSkuItemByCode(paramMap);
		return list;
	}

	/**
	 * SKU换色码规码/添加SKU
	 * 
	 * @Methods Name insertOrUpdateSku
	 * @Create In 2015年11月16日 By yedong
	 * @param skuList
	 *            void
	 */
	public ProSkuSpuPublishDto insertOrUpdateSku(List<PcmProDetailDto> skuList) {
		this.sidList = new ArrayList<PublishDTO>();
		this.skuList = new ArrayList<PublishDTO>();
		this.spuList = new ArrayList<PublishDTO>();
		ProSkuSpuPublishDto publistDto = new ProSkuSpuPublishDto();
		if (skuList.get(0).getFeatures().equals("") || skuList.get(0).getFeatures() == null) {
			for (int i = 0; i < skuList.size(); i++) {
				for (int j = i + 1; j < skuList.size(); j++) {
					PcmProDetailDto dto_1 = skuList.get(i);
					PcmProDetailDto dto_2 = skuList.get(j);
					if (dto_1.getProColorSid().equals(dto_2.getProColorSid())
							|| dto_1.getProColorName().equals(dto_2.getProColorName())
							&& dto_1.getProStanSid().equals(dto_2.getProStanSid())) {
						logger.info("商品已存在");
						throw new BleException(ErrorCode.NEW_SKU_EXIST.getErrorCode(),
								ErrorCode.NEW_SKU_EXIST.getMemo());
					}
				}
			}
		} else {
			for (int i = 0; i < skuList.size(); i++) {
				for (int j = i + 1; j < skuList.size(); j++) {
					PcmProDetailDto dto_1 = skuList.get(i);
					PcmProDetailDto dto_2 = skuList.get(j);
					if (dto_1.getFeatures().equals(dto_2.getFeatures())
							|| dto_1.getProColorName().equals(dto_2.getProColorName())
							&& dto_1.getProStanSid().equals(dto_2.getProStanSid())) {
						logger.info("商品已存在");
						throw new BleException(ErrorCode.NEW_SKU_EXIST.getErrorCode(),
								dto_1.getProductDetailSid() + ErrorCode.NEW_SKU_EXIST.getMemo());
					}
				}
			}
		}
		// 查询SPU信息
		PcmProduct spuEntity = new PcmProduct();
		spuEntity.setSid(Long.parseLong(skuList.get(0).getProductSid()));
		List<PcmProduct> spuList = productMapper.selectListByParam(spuEntity);
		// 操作类型(0未修改1修改2添加)
		for (PcmProDetailDto dto : skuList) {
			if (dto.getType().equals("1")) {
				// update
				PcmProDetail entity = new PcmProDetail();
				entity.setSid(dto.getSid());
				entity.setProColorName(dto.getProColorName());
				entity.setProColorAlias(dto.getProColorName());
				entity.setProColorSid(dto.getProColorSid());
				entity.setProStanName(dto.getProStanSid());
				entity.setProStanSid(dto.getProStanSid());
				entity.setFeatures(dto.getFeatures());
				if (dto.getFeatures() == null || dto.getFeatures() == "") {
					entity.setProDetailName(spuList.get(0).getProductName() + dto.getProColorName()
							+ dto.getProStanSid());
				} else {
					entity.setProDetailName(spuList.get(0).getProductName() + dto.getFeatures()
							+ dto.getProStanSid());
				}
				// 修改SKU
				proDetailMapper.updateByPrimaryKeySelective(entity);
				// 下发该SKU下的所有专柜商品
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("skuSid", entity.getSid());
				List<PublishCacheDto> pcPro = productMapper.pubLishPro(map);
				if (pcPro != null && pcPro.size() > 0) {
					for (PublishCacheDto pcDto : pcPro) {
						PublishDTO pDto = new PublishDTO();
						pDto.setSid(pcDto.getSid());
						pDto.setType(pcDto.getType());
						this.sidList.add(pDto);// 下发 pro
						PublishDTO pDto_1 = new PublishDTO();
						pDto_1.setSid(entity.getSid());
						pDto_1.setType(pcDto.getType());
						this.skuList.add(pDto_1);// 下发 sku
					}
				}
			} else if (dto.getType().equals("2")) {
				// insert
				PcmProDetail entity = new PcmProDetail();
				entity.setProductSid(Long.parseLong(dto.getProductSid()));
				entity.setProColorName(dto.getProColorName());
				entity.setProColorAlias(dto.getProColorName());
				entity.setProColorSid(dto.getProColorSid());
				entity.setProStanName(dto.getProStanSid());
				entity.setProStanSid(dto.getProStanSid());
				entity.setFeatures(dto.getFeatures());
				if (dto.getFeatures() == null || dto.getFeatures() == "") {
					entity.setProDetailName(spuList.get(0).getProductName() + dto.getProColorName()
							+ dto.getProStanSid());
				} else {
					entity.setProDetailName(spuList.get(0).getProductName() + dto.getFeatures()
							+ dto.getProStanSid());
				}
				proDetailMapper.insertSelective(entity);
				PublishDTO pDto_1 = new PublishDTO();
				pDto_1.setSid(entity.getSid());
				pDto_1.setType(0);
				this.skuList.add(pDto_1);// 下发 sku
			} else {

			}
		}
		publistDto.setProList(sidList);
		publistDto.setSkuList(this.skuList);
		publistDto.setSpuList(this.spuList);
		return publistDto;
	}

	/**
	 * sku添加
	 * 
	 * @Methods Name insertSkuInfo
	 * @Create In 2015年11月17日 By yedong
	 * @param skuList
	 * @return Map<String,Object>
	 */
	public Map<String, Object> insertSkuInfo(List<PcmProDetailDto> skuList) {
		this.skuList = new ArrayList<PublishDTO>();
		PcmProduct spuEntity = new PcmProduct();
		spuEntity.setSid(Long.parseLong(skuList.get(0).getProductSid()));
		List<PcmProduct> spuList = productMapper.selectListByParam(spuEntity);
		List<Map<String, Object>> listInfo = new ArrayList<Map<String, Object>>();
		Map<String, Object> paramRes = new HashMap<String, Object>();
		for (PcmProDetailDto dto : skuList) {
			PcmProDetail entity = new PcmProDetail();
			entity.setProductSid(Long.parseLong(dto.getProductSid()));
			entity.setProColorSid(dto.getProColorSid());
			List<PcmProDetail> oldSkuList = proDetailMapper.selectListByParam(entity);
			if (oldSkuList != null && oldSkuList.size() > 0) {
				entity.setPhotoPlanSid(oldSkuList.get(0).getPhotoPlanSid());
				entity.setPhotoStatus(oldSkuList.get(0).getPhotoStatus());
				entity.setSellingStatus(oldSkuList.get(0).getSellingStatus());
			}
			entity.setSizePictureUrl("1");
			entity.setProColorName(dto.getProColorName());
			entity.setProColorAlias(dto.getProColorName());
			entity.setProStanName(dto.getProStanSid());
			entity.setProStanSid(dto.getProStanSid());
			entity.setFeatures(dto.getFeatures());
			if (dto.getFeatures() == null || dto.getFeatures() == "") {
				entity.setProDetailName(spuList.get(0).getProductName() + Constants.SEPARATOR
						+ dto.getProColorName() + Constants.SEPARATOR + dto.getProStanSid());
			} else {
				entity.setProDetailName(spuList.get(0).getProductName() + Constants.SEPARATOR
						+ dto.getFeatures() + Constants.SEPARATOR + dto.getProStanSid());
			}
			ValidProDetailDto skuDto = new ValidProDetailDto();
			if (dto.getProColorSid() != null) {
				skuDto.setProColorSid(dto.getProColorSid().toString());
			}
			if (dto.getFeatures() != null && dto.getFeatures() != "") {
				skuDto.setFeatures(dto.getFeatures());
			} else {
				skuDto.setProColorName(dto.getProColorName());
			}
			skuDto.setProStanSid(dto.getProStanSid());
			skuDto.setProductSid(dto.getProductSid());
			List<PcmProDetail> validSku = validService.validSku(skuDto);
			int insertSelective = 0;
			if (validSku == null || validSku.size() == 0) {// sku不存在
				Map<String, Object> spuColor = new HashMap<String, Object>();
				spuColor.put("spuSid", dto.getProductSid());
				spuColor.put("colorSid", dto.getProColorSid());
				List<Map<String, Object>> spuColorList = proDetailMapper
						.selectPhotoBySpuColor(spuColor);
				if (spuColorList != null && spuColorList.size() > 0) {
					entity.setPhotoPlanSid((String) spuColorList.get(0).get("photoPlanSid"));
					entity.setPhotoStatus((Integer) spuColorList.get(0).get("photoStatus"));
					entity.setSellingStatus((Integer) spuColorList.get(0).get("sellingStatus"));
				}
				insertSelective = proDetailMapper.insertSelective(entity);
				PcmProDetail entity_1 = new PcmProDetail();
				entity_1.setSid(entity.getSid());
				entity_1.setProductDetailSid(entity.getSid() + Constants.SKU_CODE + "");
				proDetailMapper.updateByPrimaryKeySelective(entity_1);
			} else {
				Map<String, Object> paramSku = new HashMap<String, Object>();
				String Info = null;
				if (dto.getFeatures() == null || dto.getFeatures() == "") {
					Info = "色码:" + dto.getProColorName() + "规格:" + dto.getProStanSid() + "色系:"
							+ dto.getProColorSid() + "已存在";
				} else {
					Info = "特性:" + dto.getFeatures() + "规格:" + dto.getProStanSid() + "色系:"
							+ dto.getProColorSid() + "已存在";
				}
				paramSku.put("Info", Info);
				listInfo.add(paramSku);
			}
			if (insertSelective != 0) {
				String spuCode = (Integer.parseInt(dto.getProductSid()) + Constants.SPU_CODE) + "";
				picService.redisSpuCMSSHopperInfo(spuCode);
				PublishDTO pDto_1 = new PublishDTO();
				pDto_1.setSid(entity.getSid());
				pDto_1.setType(1);
				this.skuList.add(pDto_1);// 下发 sku
			}
		}
		paramRes.put("listInfo", listInfo);
		paramRes.put("skuList", this.skuList);
		return paramRes;
	}

	/**
	 * 根据(色系,产品编码)or skuList 查询SKU
	 */
	@Override
	public List<ProductOnSellDto> selectSkuBySpuColor(String spuCode, String color, List sids) {
		List<ProductOnSellDto> spuColor = null;
		if (spuCode != null && color != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("spuCode", spuCode);
			map.put("color", color);
			spuColor = proDetailMapper.selectColorProBySpuColor(map);
			if (spuColor == null || spuColor.size() == 0 || spuColor.get(0).getBrandSid() == null) {
				return null;
			}
		} else if (sids != null && sids.size() > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", sids);
			List<Map<String, Object>> spuColorList = proDetailMapper.getSpuColorBySkuSids(map);
			List<String> spuList = new ArrayList<String>();
			List<String> colorList = new ArrayList<String>();
			for (Map<String, Object> map2 : spuColorList) {
				spuList.add(map2.get("spuSid").toString());
				colorList.add(map2.get("colorSid").toString());
			}
			map.clear();
			map.put("spuList", spuList);
			map.put("colorList", colorList);
			spuColor = proDetailMapper.selectColorProBySpuColor(map);
			if (spuColor == null || spuColor.size() == 0 || spuColor.get(0).getBrandSid() == null) {
				return null;
			}
		}
		if (spuColor != null) {
			for (ProductOnSellDto productOnSellDto : spuColor) {
				String spuCodes = productOnSellDto.getSpuSid() + Constants.SPU_CODE + "";
				picService.redisSpuCMSSHopperInfo(spuCodes);
				picService.redisOmsDelInfo(spuCodes);
			}
		}
		return spuColor;
	}

	/**
	 * 根据SKUSID修改SKU信息
	 * 
	 * @Methods Name updateSkuInfoBySid
	 * @Create In 2015年11月25日 By yedong
	 * @param dto
	 *            void
	 */
	public void updateSkuInfoBySid(PcmProDetailDto dto) {
		PcmProDetail record = new PcmProDetail();
		record.setSid(dto.getSid());
		if (dto.getKeyWord() != null) {
			record.setKeyWord(dto.getKeyWord());
		}
		if (dto.getSearchKey() != null) {
			record.setSearchKey(dto.getSearchKey());
		}
		int updateSelective = proDetailMapper.updateByPrimaryKeySelective(record);
		if (updateSelective == 0) {
			logger.info("数据库异常");
			throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
					ErrorCode.DATA_OPER_ERROR.getMemo());
		}
	}

	/**
	 * 单品页商品漏出
	 * 
	 * @Methods Name selectTagsPriceStockBySku
	 * @Create In 2015年12月1日 By zhangxy
	 * @param map
	 * @return TagsPriceStockDto
	 */
	@Override
	public TagsPriceStockDto selectTagsPriceStockBySku(Map<String, Object> map) {
		TagsPriceStockDto res = proDetailMapper.selectTagsPriceStockBySku(map);
		String[] split = res.getTags().split("\\|");
		Set<String> set = new HashSet<String>();
		for (int i = 0; i < split.length; i++) {
			set.add(split[i]);
		}
		StringBuffer stag = new StringBuffer();
		for (String s : set) {
			stag.append(s + ",");
		}
		res.setTags(stag.toString().substring(0, stag.length() - 1));
		return res;
	}

	/**
	 * 单品页查询价格库存 BY SKUCODE
	 * 
	 * @Methods Name selectStockAndPriceByProDetail
	 * @Create In 2015-12-21 By wangc
	 * @param map
	 * @return Map<String,Object>
	 */
	@Override
	public HashMap<String, Object> selectStockAndPriceByProDetail(Map<String, Object> map) {
		List<HashMap<String, Object>> result = proDetailMapper.selectStockAndPriceBySkuCode(map);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if (result == null || result.size() == Constants.PUBLIC_0) {
			return null;
		} else if (result.size() == Constants.PUBLIC_0) {
			return result.get(Constants.PUBLIC_0);
		}
		if (result.size() > Constants.PUBLIC_0) {
			resultMap = selectResult(result);
		}
		return resultMap;
	}

	/**
	 * 商品漏出
	 * 
	 * @Methods Name selectResult
	 * @Create In 2015-12-21 By wangc
	 * @param map
	 * @return List<HashMap<String,Object>>
	 */
	public HashMap<String, Object> selectResult(List<HashMap<String, Object>> listMap) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		// 按价格排序
		Collections.sort(listMap, new Comparator<Map<String, Object>>() {
			public int compare(Map<String, Object> m1, Map<String, Object> m2) {
				Double price = Double.parseDouble(m1.get("price").toString());
				Double price2 = Double.parseDouble(m2.get("price").toString());
				if (price - price2 > Constants.PUBLIC_0) { // 按价格 低的在前
					return 1;
				}
				if (price - price2 < Constants.PUBLIC_0) {
					return -1;
				} // 价格相同 按活动数 多的在前
				Integer tag1 = ((ArrayList<HashMap<String, Object>>) m1.get("tags")).size();
				Integer tag2 = ((ArrayList<HashMap<String, Object>>) m2.get("tags")).size();
				if (tag1 - tag2 > Constants.PUBLIC_0) {
					return -1;
				}
				if (tag1 - tag2 < Constants.PUBLIC_0) {
					return 1;
				}// 活动数相同，看库存，库存多的在前
				Long stock1 = (Long) m1.get("stock");
				Long stock2 = (Long) m2.get("stock");
				if (stock1 - stock2 > Constants.PUBLIC_0) {
					return -1;
				}
				if (stock1 - stock2 < Constants.PUBLIC_0) {
					return 1;
				}
				if (stock1 == stock2) {
					return 1;
				} else {
					return 1;
				}
			}
		});
		resultMap = listMap.get(Constants.PUBLIC_0);
		return resultMap;
	}

	/**
	 * 回传商品编辑到主数据2
	 */
	@Override
	@Transactional
	public void updateProductEditTwo(ProductEditDto dto) throws BleException {
		logger.info("updateProductEdit() start,para:" + dto.toString());
		// 根据产品编码,色系 查询sku列表
		List<PcmProDetail> list = proDetailMapper.selectProductEditByPara(dto);
		if (list == null || list.size() == 0) {
			logger.error(ErrorCode.PRODUCT_IS_NULL.getErrorCode(),
					ErrorCode.PRODUCT_IS_NULL.getMemo());
			throw new BleException(ErrorCode.PRODUCT_IS_NULL.getErrorCode(),
					ErrorCode.PRODUCT_IS_NULL.getMemo());
		}
		// 修改SPU产品信息
		PcmProduct pp = new PcmProduct();
		pp.setSid(list.get(0).getProductSid());
		// 删除之前的品类关联
		PcmProductCategory ppc = new PcmProductCategory();
		ppc.setProductSid(pp.getSid());
		productCategoryMapper.deleteZSCateByRecord(ppc);
		PcmProductParameters ppp_1 = new PcmProductParameters();
		ppp_1.setProductSid(Long.valueOf(pp.getSid()));
		ppp_1.setCategoryType(3);
		parMapper.deleteByProductSid_1(ppp_1);
		// 关联展示分类
		for (CategoryDto cate : dto.getChannelCategory()) {
			PcmCategory cateEntity = cateMapper.selectByCategorySid(cate.getCategoryCode());
			if (cateEntity == null) {
				logger.error("updateProductEditTwo", cate.getCategoryCode() + " 展示分类不存在");
				throw new BleException("updateProductEditTwo", cate.getCategoryCode() + " 展示分类不存在");
			}
			PcmProductCategory ppc1 = new PcmProductCategory();
			ppc1.setCategorySid(cateEntity.getSid());// 统计分类
			ppc1.setProductSid(pp.getSid());// SPU产品表sid
			ppc1.setChannelSid(cateEntity.getChannelSid());
			// ppcTJ.setOptUser("拍照");// 操作人
			ppc1.setOptDate(new Date());// 时间
			int i = productCategoryMapper.insertSelective(ppc1);
			if (i == Constants.PUBLIC_0) {
				logger.error(ErrorCode.ADD_CATE_ERROR.getErrorCode(),
						ErrorCode.ADD_CATE_ERROR.getMemo());
				throw new BleException(ErrorCode.ADD_CATE_ERROR.getErrorCode(),
						ErrorCode.ADD_CATE_ERROR.getMemo());
			}
			SaveProductParametersDTO sppDto = new SaveProductParametersDTO();
			sppDto.setParameters(cate.getAttributeList());
			sppDto.setCategoryName(cateEntity.getName());
			sppDto.setCategorySid(String.valueOf(cateEntity.getSid()));
			sppDto.setCategoryType(String.valueOf(cateEntity.getCategoryType()));
			sppDto.setChannelSid(String.valueOf(cateEntity.getChannelSid()));
			sppDto.setSpuSid(String.valueOf(pp.getSid()));
			parametersService.insertProductParamter1(sppDto);
		}
		logger.info("updateProductEdit() end");
	}

	@Override
	public List<PcmProDetail> selectSkuListByParam(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return proDetailMapper.selectListByParam(paramMap);
	}

}

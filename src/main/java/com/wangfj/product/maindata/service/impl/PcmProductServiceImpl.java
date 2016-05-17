package com.wangfj.product.maindata.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangfj.core.cache.RedisVo;
import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.CacheUtils;
import com.wangfj.core.utils.DateUtil;
import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.core.utils.PropertyUtil;
import com.wangfj.core.utils.RedisUtil;
import com.wangfj.product.category.domain.entity.PcmCategory;
import com.wangfj.product.category.domain.entity.PcmProductCategory;
import com.wangfj.product.category.persistence.PcmCategoryMapper;
import com.wangfj.product.category.persistence.PcmProductCategoryMapper;
import com.wangfj.product.constants.DomainName;
import com.wangfj.product.maindata.domain.entity.PcmErpProduct;
import com.wangfj.product.maindata.domain.entity.PcmProDetail;
import com.wangfj.product.maindata.domain.entity.PcmProduct;
import com.wangfj.product.maindata.domain.entity.PcmPromotionRateProduct;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProduct;
import com.wangfj.product.maindata.domain.vo.PcmProductDto;
import com.wangfj.product.maindata.domain.vo.ProSkuSpuPublishDto;
import com.wangfj.product.maindata.domain.vo.ProductCondDto;
import com.wangfj.product.maindata.domain.vo.PromotionRateDto;
import com.wangfj.product.maindata.domain.vo.PublishCacheDto;
import com.wangfj.product.maindata.domain.vo.PushPromotionDto;
import com.wangfj.product.maindata.domain.vo.SpuPageDto;
import com.wangfj.product.maindata.domain.vo.ValidProDetailDto;
import com.wangfj.product.maindata.domain.vo.ValidShoppeProDto;
import com.wangfj.product.maindata.persistence.PcmErpProductMapper;
import com.wangfj.product.maindata.persistence.PcmProDetailMapper;
import com.wangfj.product.maindata.persistence.PcmProductMapper;
import com.wangfj.product.maindata.persistence.PcmPromotionRateProductMapper;
import com.wangfj.product.maindata.persistence.PcmSaleCodeMapper;
import com.wangfj.product.maindata.persistence.PcmShoppeProductMapper;
import com.wangfj.product.maindata.service.intf.IPcmCreateProductService;
import com.wangfj.product.maindata.service.intf.IPcmProductPictureService;
import com.wangfj.product.maindata.service.intf.IPcmProductService;
import com.wangfj.product.maindata.service.intf.IPcmShoppeProductService;
import com.wangfj.product.maindata.service.intf.IValidProDrtailService;
import com.wangfj.product.organization.domain.entity.PcmShoppe;
import com.wangfj.product.organization.persistence.PcmShoppeMapper;
import com.wangfj.product.supplier.domain.entity.PcmSupplyInfo;
import com.wangfj.product.supplier.domain.entity.PcmSupplyShopCode;
import com.wangfj.product.supplier.persistence.PcmSupplyInfoMapper;
import com.wangfj.product.supplier.persistence.PcmSupplyShopCodeMapper;
import com.wangfj.util.Constants;
import com.wangfj.util.mq.PublishDTO;

@Service
@Transactional
public class PcmProductServiceImpl implements IPcmProductService {

	private static final Logger logger = LoggerFactory.getLogger(PcmProductServiceImpl.class);

	@Autowired
	PcmCategoryMapper categoryMapper;
	@Autowired
	PcmProductCategoryMapper productCategoryMapper;
	@Autowired
	PcmProductMapper productMapper;
	@Autowired
	PcmShoppeProductMapper spMapper;
	@Autowired
	PcmShoppeMapper shoppeMapper;
	@Autowired
	PcmPromotionRateProductMapper proRateMapper;
	@Autowired
	PcmSaleCodeMapper saleCodeMapper;
	@Autowired
	PcmSupplyShopCodeMapper supplyShopCodeMapper;
	@Autowired
	PcmProDetailMapper proDetailMapper;
	@Autowired
	IValidProDrtailService validProDrtailService;
	@Autowired
	IPcmCreateProductService createProductService;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	@Autowired
	private PcmErpProductMapper erpProMapper;
	@Autowired
	private PcmSupplyInfoMapper supplyMapper;
	@Autowired
	private IPcmProductPictureService picService;
	@Autowired
	private IPcmShoppeProductService proService;

	List<PublishDTO> sidList = null;

	List<PublishDTO> skuList = null;

	List<PublishDTO> spuList = null;

	/**
	 * 产品更换工业分类
	 * 
	 * @Methods Name updateProductCategory
	 * @Create In 2015-8-4 By chengsj
	 * @param pc
	 * @return int
	 */
	public int updateProductCategory(ProductCondDto condDto) {
		logger.info("start updateProductCategory(), param:" + condDto.toString());
		int i = 0;
		// 判断产品是否存在
		PcmProduct pro = new PcmProduct();
		pro.setProductSid(condDto.getProductSid());
		List<PcmProduct> listPro = productMapper.selectListByParam(pro);
		if (listPro != null && listPro.size() > 0) {
			// 判断工业分类是否存在
			PcmCategory cate = new PcmCategory();
			cate.setCategorySid(condDto.getCategorySid());
			cate.setCategoryType(0);
			cate.setIsLeaf(Constants.Y);// 是否叶子节点
			cate.setStatus(Constants.Y);// 是否启用w
			List<PcmCategory> list = categoryMapper.selectListByParam(cate);
			if (list != null && list.size() > 0) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("productSid", listPro.get(0).getSid());
				paramMap.put("categoryType", cate.getCategoryType());
				PcmProductCategory dto = productCategoryMapper.selectUpdateCate(paramMap);

				PcmProductCategory procate = new PcmProductCategory();
				procate.setSid(dto.getSid());// SID
				procate.setCategorySid(list.get(0).getSid());// 工业分类SID
				int u = productCategoryMapper.updateCateByProSid(procate);
				if (u != 1) {
					logger.info("数据错误");
					throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
							ErrorCode.DATA_OPER_ERROR.getMemo());
				}
				// 下发新SPU下的所有商品
				Map<String, Object> publishMap = new HashMap<String, Object>();
				publishMap.put("spuSid", dto.getProductSid());
				List<PublishCacheDto> pcPro = productMapper.pubLishPro(publishMap);
				sidList = new ArrayList<PublishDTO>();
				if (pcPro != null && pcPro.size() > 0) {
					for (PublishCacheDto pcDto : pcPro) {
						PublishDTO pDto = new PublishDTO();
						pDto.setSid(pcDto.getSid());
						pDto.setType(pcDto.getType());
						sidList.add(pDto);// 下发LIST
						proService.cacheDelete(pcDto.getCode());// 删除缓存
					}
				}
				if (sidList != null && sidList.size() != 0) {
					taskExecutor.execute(new Runnable() {
						@Override
						public void run() {
							HttpUtil.doPost(PropertyUtil.getSystemUrl("product.pushShoppeProduct"),
									JsonUtil.getJSONString(sidList));
						}
					});
				}
			} else {
				// 待更换的工业分类不存在
				logger.info("待更换的工业分类不存在");
				throw new BleException(ErrorCode.NEW_PRODUCT_CATEGORY_NO_EXIST.getErrorCode(),
						ErrorCode.NEW_PRODUCT_CATEGORY_NO_EXIST.getMemo());
			}
		} else {
			// 待更换的产品不存在
			logger.info("待更换的产品不存在");
			throw new BleException(ErrorCode.NEW_PRODUCT_NO_EXIST.getErrorCode(),
					ErrorCode.NEW_PRODUCT_NO_EXIST.getMemo());
		}
		logger.info("start updateProductCategory(), param:" + i);
		return i;
	}

	/**
	 * 专柜商品更换统计分类
	 * 
	 * @Methods Name updateStatCategory
	 * @Create In 2015-8-4 By chengsj
	 * @param pc
	 * @return int
	 */
	@Transactional
	public List<PublishDTO> updateStatCategory(ProductCondDto condDto) {
		logger.info("start updateProductCategory(), param:" + condDto.toString());
		// 判断专柜商品是否存在
		PcmShoppeProduct pro = new PcmShoppeProduct();
		pro.setShoppeProSid(condDto.getProductSid());
		List<PcmShoppeProduct> proList = spMapper.selectListByParam(pro);
		if (proList != null && proList.size() > 0) {
			// 判断统计分类是否存在
			PcmCategory cate = new PcmCategory();
			cate.setSid(Long.parseLong(condDto.getCategorySid()));
			cate.setCategoryType(2);
			cate.setIsLeaf(Constants.Y);// 是否叶子节点
			cate.setStatus(Constants.Y);// 是否启用
			List<PcmCategory> list = categoryMapper.selectListByParam(cate);
			if (list != null && list.size() > 0) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("productSid", proList.get(0).getShoppeProSid());
				paramMap.put("categoryType", cate.getCategoryType());
				PcmProductCategory dto = productCategoryMapper.selectUpdateCate(paramMap);

				PcmProductCategory procate = new PcmProductCategory();
				procate.setSid(dto.getSid());// SID
				procate.setCategorySid(list.get(0).getSid());// 统计分类SID
				// procate.setOptUser(""); //操作人
				int u = productCategoryMapper.updateCateByProSid(procate);
				if (u != 1) {
					logger.info("数据错误");
					throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
							ErrorCode.DATA_OPER_ERROR.getMemo());
				}
				proService.cacheDelete(proList.get(0).getShoppeProSid());// 删除缓存
			} else {
				// 待更换的统计分类不存在
				logger.info("待更换的统计分类不存在");
				throw new BleException(ErrorCode.NEW_STAT_CATEGORY_NO_EXIST.getErrorCode(),
						ErrorCode.NEW_STAT_CATEGORY_NO_EXIST.getMemo());
			}
		} else {
			// 待更换的产品不存在
			logger.info("待更换的产品不存在");
			throw new BleException(ErrorCode.NEW_PRODUCT_NO_EXIST.getErrorCode(),
					ErrorCode.NEW_PRODUCT_NO_EXIST.getMemo());
		}
		logger.info("start updateProductCategory(), param:" + sidList);
		return sidList;
	}

	/**
	 * 专柜商品换管理分类
	 * 
	 * @Methods Name updateManagerCategory
	 * @Create In 2015年8月20日 By yedong
	 * @param condDto
	 * @return int
	 */
	public List<PublishDTO> updateManagerCategory(ProductCondDto condDto) {
		logger.info("start updateProductCategory2(), param:" + condDto.toString());
		// 判断装柜商品是否存在
		PcmShoppeProduct pro = new PcmShoppeProduct();
		pro.setShoppeProSid(condDto.getShoppeProSid());
		List<PcmShoppeProduct> listPro = spMapper.selectListByParam(pro);
		if (listPro != null && listPro.size() > 0) {
			// 判断管理分类是否存在、可用
			PcmCategory cate = new PcmCategory();
			cate.setCategorySid(condDto.getCategorySid());
			cate.setCategoryType(1);
			cate.setIsLeaf(Constants.Y);// 是否叶子节点
			cate.setStatus(Constants.Y);// 是否启用
			List<PcmCategory> list = categoryMapper.selectListByParam(cate);
			if (list != null && list.size() > 0) {
				PcmShoppeProduct pro1 = new PcmShoppeProduct();
				pro1.setCategorySid(condDto.getCategorySid());
				pro1.setSid(listPro.get(0).getSid());
				int u = spMapper.updateByPrimaryKeySelective(pro1);
				if (u != 1) {
					logger.info("数据错误");
					throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
							ErrorCode.DATA_OPER_ERROR.getMemo());
				}
				// 下发新SPU下的所有商品
				Map<String, Object> publishMap = new HashMap<String, Object>();
				publishMap.put("proSid", listPro.get(0).getSid());
				List<PublishCacheDto> pcPro = productMapper.pubLishPro(publishMap);
				sidList = new ArrayList<PublishDTO>();
				if (pcPro != null && pcPro.size() > 0) {
					for (PublishCacheDto pcDto : pcPro) {
						PublishDTO pDto = new PublishDTO();
						pDto.setSid(pcDto.getSid());
						pDto.setType(pcDto.getType());
						sidList.add(pDto);// 下发LIST
						proService.cacheDelete(pcDto.getCode());// 删除缓存
					}
				}
			} else {
				// 待更换的管理分类不存在
				logger.info("待更换的管理分类不存在");
				throw new BleException(ErrorCode.NEW_MANAGER_CATEGORY_NO_EXIST.getErrorCode(),
						ErrorCode.NEW_MANAGER_CATEGORY_NO_EXIST.getMemo());
			}
		} else {
			// 待更换的产品不存在
			logger.info("待更换的产品不存在");
			throw new BleException(ErrorCode.NEW_PRODUCT_NO_EXIST.getErrorCode(),
					ErrorCode.NEW_PRODUCT_NO_EXIST.getMemo());
		}
		logger.info("start updateManagerCategory(), param:" + sidList);
		return sidList;
	}

	/**
	 * 专柜商品换专柜
	 * 
	 * @Methods Name updateProductShoppe
	 * @Create In 2015年8月5日 By zhangxy
	 * @param condDto
	 * @return int
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<PublishDTO> updateProductShoppe(ProductCondDto condDto) throws BleException {
		logger.info("start updateProductShoppe(), param:" + condDto.toString());
		sidList = new ArrayList<PublishDTO>();
		// 判断专柜商品是否存在
		PcmShoppeProduct pro = new PcmShoppeProduct();
		pro.setShoppeProSid(condDto.getShoppeProSid());
		List<PcmShoppeProduct> listPro = spMapper.selectListByParam(pro);
		if (listPro != null && listPro.size() > 0) {
			// 判断专柜是否正常（状态，是否存在）
			PcmShoppe shoppe = new PcmShoppe();
			shoppe.setShoppeCode(condDto.getShoppeSid());
			shoppe.setShoppeStatus(1);
			List<PcmShoppe> list = shoppeMapper.selectListByParam(shoppe);
			if (list != null && list.size() > 0) {
				// 新专柜商品判重
				PcmShoppeProduct newPro = new PcmShoppeProduct();
				newPro.setShoppeSid((list.get(0).getSid()));
				newPro.setProductDetailSid(listPro.get(0).getProductDetailSid());
				newPro.setSupplySid(listPro.get(0).getSupplySid());
				List<PcmShoppeProduct> newListPro = spMapper.selectListByParam(newPro);
				if (newListPro != null && newListPro.size() > 0) {
					// 判重失败
					logger.info("新的专柜商品已存在");
					throw new BleException(ErrorCode.NEW_SHOPPEPRODUCT_EXIST.getErrorCode(),
							ErrorCode.NEW_SHOPPEPRODUCT_EXIST.getMemo());
				} else {
					PcmShoppe shoppe1 = new PcmShoppe();
					shoppe1.setSid((listPro.get(0).getShoppeSid()));
					List<PcmShoppe> list1 = shoppeMapper.selectListByParam(shoppe1);
					if (list1 != null && list1.size() > 0) {
						/* 判断新旧专柜类型是否一致 */
						String shoppeType = list1.get(0).getShoppeType();
						if (shoppeType.equals(list.get(0).getShoppeType())) {
							// Integer business =
							// list1.get(0).getBusinessTypeSid();
							// if
							// (business.equals(list.get(0).getBusinessTypeSid()))
							// {
							PcmShoppeProduct pro1 = new PcmShoppeProduct();
							pro1.setSid(listPro.get(0).getSid());
							pro1.setShoppeSid((list.get(0).getSid()));// 专柜SID
							spMapper.updateByPrimaryKeySelective(pro1);
							PublishDTO publishDto = new PublishDTO();
							publishDto.setSid(pro1.getSid());
							publishDto.setType(1);
							sidList.add(publishDto);// 下发LIST
							proService.cacheDelete(pro.getShoppeProSid());// 删除缓存
							// } else {
							// logger.info("新旧经营方式不一致");
							// throw new BleException(
							// ErrorCode.NEW_OLD_SHOPPE_TYPE.getErrorCode(),
							// ErrorCode.NEW_OLD_SHOPPE_TYPE.getMemo());
							// }
						} else {
							logger.info("新旧专柜类型不一致");
							throw new BleException(ErrorCode.NEW_OLD_SHOPPE_TYPE.getErrorCode(),
									ErrorCode.NEW_OLD_SHOPPE_TYPE.getMemo());
						}
					} else {
						logger.info("旧专柜的信息不存在");
						throw new BleException(ErrorCode.OLD_SHOPPE_NO_INFO.getErrorCode(),
								ErrorCode.OLD_SHOPPE_NO_INFO.getMemo());
					}
				}
			} else {
				// 待更换的专柜不存在
				logger.info("待更换的专柜不存在");
				throw new BleException(ErrorCode.NEW_SHOPPE_NO_EXIST.getErrorCode(),
						ErrorCode.NEW_SHOPPE_NO_EXIST.getMemo());
			}
		} else {
			// 待更换的专柜商品不存在
			logger.info("待更换的专柜商品不存在");
			throw new BleException(ErrorCode.SHOPPEPRODUCT_NULL.getErrorCode(),
					ErrorCode.SHOPPEPRODUCT_NULL.getMemo());
		}
		logger.info("end updateProductShoppe(), return:" + sidList);
		return sidList;
	}

	/**
	 * SAP 商品换色规
	 * 
	 * @Methods Name updateSapColorStan
	 * @Create In 2015年12月17日 By yedong
	 * @param condDto
	 * @return ProSkuSpuPublishDto
	 */
	@Override
	public ProSkuSpuPublishDto updateSapColorStan(ProductCondDto condDto) {
		ProSkuSpuPublishDto publistDto = new ProSkuSpuPublishDto();
		this.sidList = new ArrayList<PublishDTO>();
		this.skuList = new ArrayList<PublishDTO>();
		this.spuList = new ArrayList<PublishDTO>();
		PcmShoppeProduct pro = new PcmShoppeProduct();
		pro.setShoppeProSid(condDto.getShoppeProSid());
		List<PcmShoppeProduct> listPro = spMapper.selectListByParam(pro);
		if (listPro != null && listPro.size() > 0) {
			PcmProDetail sku = new PcmProDetail();
			sku.setSid((listPro.get(0).getProductDetailSid()));
			// skuList 原SKU信息
			List<PcmProDetail> skuList = proDetailMapper.selectListByParam(sku);
			PcmProduct spu = new PcmProduct();
			spu.setSid(skuList.get(0).getProductSid());
			List<PcmProduct> spuList = productMapper.selectListByParam(spu);
			PcmProDetail sku_old = skuList.get(0);
			sku_old.setProDetailName(spuList.get(0).getProductName() + Constants.SEPARATOR
					+ condDto.getProColorName() + Constants.SEPARATOR + condDto.getProStanSid());
			sku_old.setProColorName(condDto.getProColorName());
			sku_old.setProColorAlias(condDto.getProColorAlias());
			sku_old.setProStanName(condDto.getProStanSid());
			sku_old.setProStanSid(condDto.getProStanSid());
			int update = proDetailMapper.updateByPrimaryKeySelective(sku_old);
			if (update != 1) {
				throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
						ErrorCode.DATA_OPER_ERROR.getMemo());
			}
			PublishDTO publishPro = new PublishDTO();
			publishPro.setSid(listPro.get(0).getSid());
			publishPro.setType(1);
			sidList.add(publishPro);
			PublishDTO publishSku = new PublishDTO();
			publishSku.setSid(skuList.get(0).getSid());
			publishSku.setType(1);
			this.skuList.add(publishSku);
			picService.redisSpuCMSSHopperInfo(spuList.get(0).getProductSid());
		}
		publistDto.setProList(sidList);
		publistDto.setSkuList(this.skuList);
		publistDto.setSpuList(this.spuList);
		return publistDto;
	}

	/**
	 * 专柜商品换色码（特性）/尺寸码（规格）色系规则说明
	 * 
	 * @Methods Name updateColorStan
	 * @Create In 2015年8月19日 By yedong
	 * @param condDto
	 * @return boolean
	 * @throws Exception
	 */
	public ProSkuSpuPublishDto updateColorStan(ProductCondDto condDto) throws BleException {
		ProSkuSpuPublishDto publistDto = new ProSkuSpuPublishDto();
		if (condDto.getShoppeProSid() == null || condDto.getShoppeProSid().equals("")) {
			logger.info("专柜商品编码不能为空");
			throw new BleException(ErrorCode.PARA_NORULE_ERROR.getErrorCode(),
					ErrorCode.PARA_NORULE_ERROR.getMemo());
		} else if ((condDto.getProColorName() == null || condDto.getProColorName().equals(""))
				&& (condDto.getProStanSid() == null || condDto.getProStanSid().equals(""))
				&& (condDto.getFeatures() == null || condDto.getFeatures().equals(""))) {
			logger.info("色码（特性）/尺寸码（规格）不能为空");
			throw new BleException(ErrorCode.PARA_NORULE_ERROR.getErrorCode(),
					ErrorCode.PARA_NORULE_ERROR.getMemo());
		}
		this.sidList = new ArrayList<PublishDTO>();
		this.skuList = new ArrayList<PublishDTO>();
		this.spuList = new ArrayList<PublishDTO>();
		PcmShoppeProduct pro = new PcmShoppeProduct();
		pro.setShoppeProSid(condDto.getShoppeProSid());
		List<PcmShoppeProduct> listPro = spMapper.selectListByParam(pro);
		if (listPro != null && listPro.size() > 0) {
			PcmProDetail sku = new PcmProDetail();
			sku.setSid((listPro.get(0).getProductDetailSid()));
			// skuList 原SKU信息
			List<PcmProDetail> skuList = proDetailMapper.selectListByParam(sku);
			PcmProduct spu = new PcmProduct();
			spu.setSid(skuList.get(0).getProductSid());
			// spuList 原SPU信息
			List<PcmProduct> spuList = productMapper.selectListByParam(spu);

			PcmProDetail prod = new PcmProDetail();
			prod.setSid((listPro.get(0).getProductDetailSid()));
			List<PcmProDetail> listSku = proDetailMapper.selectListByParam(prod);
			ValidProDetailDto record = new ValidProDetailDto();
			record.setProductSid(spuList.get(0).getSid().toString());
			/* 规码 */
			if (condDto.getProStanSid() != null && !condDto.getProStanSid().equals("")) {
				record.setProStanSid(condDto.getProStanSid());
			} else {
				record.setProStanSid(listSku.get(0).getProStanSid() + "");
			}
			/* 色码 */
			if (condDto.getProColorName() != null && !condDto.getProColorName().equals("")) {
				record.setProColorName(condDto.getProColorName());
			} else {
				record.setProColorName(listSku.get(0).getProColorName());
			}
			/* 特性 */
			if (condDto.getFeatures() != null && !condDto.getFeatures().equals("")) {
				record.setFeatures(condDto.getFeatures());
			} else {
				record.setFeatures(listSku.get(0).getFeatures());
			}
			if (condDto.getProColorSid() != null && !condDto.getProColorSid().equals("")) {
				record.setProColorSid(condDto.getProColorSid());
			} else {
				record.setProColorSid(String.valueOf(listSku.get(0).getProColorSid()));
			}
			List<PcmProDetail> validSku = validProDrtailService.validSku(record);
			if (validSku == null || validSku.size() == 0) {
				picService.redisSpuCMSSHopperInfo(spuList.get(0).getProductSid());
				/* 新建SKU */
				// SPU：品牌名+款+主属性
				// SKU：SPU名+特性/色+规
				PcmProDetail skuDto = new PcmProDetail();
				BeanUtils.copyProperties(listSku.get(0), skuDto);
				skuDto.setSid(null);
				if (record.getFeatures() == null) {
					skuDto.setProDetailName(spuList.get(0).getProductName() + Constants.SEPARATOR
							+ record.getProColorName() + Constants.SEPARATOR
							+ record.getProStanSid());
				} else {
					skuDto.setProDetailName(spuList.get(0).getProductName() + Constants.SEPARATOR
							+ record.getFeatures() + Constants.SEPARATOR + record.getProStanSid());
				}
				skuDto.setProColorName(record.getProColorName());
				if (condDto.getProColorAlias() != null) {
					skuDto.setProColorAlias(condDto.getProColorAlias());
				}
				if (condDto.getProColorSid() != null && !condDto.getProColorSid().equals("")) {
					skuDto.setProColorSid(Long.parseLong(condDto.getProColorSid()));
				} else {
					skuDto.setProColorSid(listSku.get(0).getProColorSid());
				}
				skuDto.setProColorAlias(record.getProColorName());
				skuDto.setProStanSid(record.getProStanSid());
				skuDto.setProStanName(record.getProStanSid());
				skuDto.setFeatures(record.getFeatures());

				int insertSKU = proDetailMapper.insertSelective(skuDto);

				if (insertSKU == 1) {
					// 新SKU生成编码
					Long new_Sid = Constants.SKU_CODE + skuDto.getSid();
					PcmProDetail skuDto1 = new PcmProDetail();
					skuDto1.setSid(skuDto.getSid());
					skuDto1.setProductDetailSid(new_Sid.toString());
					int updateSku = proDetailMapper.updateByPrimaryKeySelective(skuDto1);

					if (updateSku == 1) {
						PcmShoppeProduct spDto = new PcmShoppeProduct();
						spDto.setSid(listPro.get(0).getSid());
						spDto.setProductDetailSid(skuDto.getSid());
						int count = spMapper.updateByPrimaryKeySelective(spDto);
						if (count == 1) {
							skuStutasVail(skuList.get(0), skuDto);
							// 修改专柜商品与SKU关联关系成功
							PublishDTO publishDto = new PublishDTO();
							publishDto.setSid(listPro.get(0).getSid());
							publishDto.setType(1);
							sidList.add(publishDto);// 下发LIST
							proService.cacheDelete(listPro.get(0).getShoppeProSid());// 删除缓存

							PublishDTO publishDto_1 = new PublishDTO();
							publishDto_1.setSid(skuDto.getSid());
							publishDto_1.setType(0);
							this.skuList.add(publishDto_1);// 下发LIST
						} else {
							// 修改专柜商品与SKU关联关系失败
							throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
									ErrorCode.DATA_OPER_ERROR.getMemo());
						}
					} else {
						throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
								ErrorCode.DATA_OPER_ERROR.getMemo());
					}
				} else {
					throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
							ErrorCode.DATA_OPER_ERROR.getMemo());
				}
			} else if (validSku != null && validSku.size() == 1) {
				/* 不生成新的SKU，判断新的专柜商品上是否存在 */
				ValidShoppeProDto dto = new ValidShoppeProDto();
				dto.setProductDetailSid(listPro.get(0).getProductDetailSid() + "");
				dto.setSaleUnitCode(listPro.get(0).getSaleUnitCode());
				dto.setShoppeSid(listPro.get(0).getShoppeSid().toString());
				dto.setSupplySid(listPro.get(0).getSupplySid().toString());
				PcmShoppeProduct validShoppeProBh = validProDrtailService.validShoppeProBh(dto);
				if (validShoppeProBh == null) {
					/* 更新专柜商品-SKU关联关系 */
					PcmShoppeProduct spDto = new PcmShoppeProduct();
					spDto.setSid(listPro.get(0).getSid());
					spDto.setProductDetailSid(listPro.get(0).getProductDetailSid());
					int count = spMapper.updateByPrimaryKeySelective(spDto);
					if (count == 1) {
						PublishDTO publishDto = new PublishDTO();
						publishDto.setSid(listPro.get(0).getSid());
						publishDto.setType(1);
						this.sidList.add(publishDto);// 下发LIST

						// PublishDTO publishDto_1 = new PublishDTO();
						// publishDto_1.setSid(listSku.get(0).getSid());
						// publishDto_1.setType(1);
						// this.skuList.add(publishDto_1);
						proService.cacheDelete(listPro.get(0).getShoppeProSid());// 删除缓存
					} else {
						throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
								ErrorCode.DATA_OPER_ERROR.getMemo());
					}
				} else {
					// 新专柜商品已存在
					throw new BleException(ErrorCode.NEW_SHOPPEPRODUCT_EXIST.getErrorCode(),
							ErrorCode.NEW_SHOPPEPRODUCT_EXIST.getMemo());
				}
			} else {
				throw new BleException(ErrorCode.SKU_IS_EXIST1.getErrorCode(),
						ErrorCode.SKU_IS_EXIST1.getMemo());
			}
		} else {
			// 专柜商品不存在
			throw new BleException(ErrorCode.SHOPPEPRODUCT_NULL.getErrorCode(),
					ErrorCode.SHOPPEPRODUCT_NULL.getMemo());
		}
		publistDto.setProList(sidList);
		publistDto.setSkuList(this.skuList);
		publistDto.setSpuList(this.spuList);
		return publistDto;
	}

	/**
	 * sku换色码、规码----------------------------------------------------------------
	 * -----
	 * 
	 * @Methods Name updateSkuColorStan
	 * @Create In 2015年8月21日 By yedong
	 * @param condDto
	 * @return boolean
	 */
	public ProSkuSpuPublishDto updateSkuColorStan(ProductCondDto condDto) {
		ProSkuSpuPublishDto publistDto = new ProSkuSpuPublishDto();
		if (condDto.getProductDetailSid() == null || condDto.getProductDetailSid().equals("")) {
			logger.info("SKU编码不能为空");
			throw new BleException(ErrorCode.PARA_NORULE_ERROR.getErrorCode(),
					ErrorCode.PARA_NORULE_ERROR.getMemo());
		} else if ((condDto.getProColorName() == null || condDto.getProColorName().equals(""))
				&& (condDto.getProStanSid() == null || condDto.getProStanSid().equals(""))
				&& (condDto.getFeatures() == null || condDto.getFeatures().equals(""))) {
			logger.info("色码（特性）/尺寸码（规格）不能为空");
			throw new BleException(ErrorCode.PARA_NORULE_ERROR.getErrorCode(),
					ErrorCode.PARA_NORULE_ERROR.getMemo());
		}
		PcmProDetail sku = new PcmProDetail();
		this.sidList = new ArrayList<PublishDTO>();
		this.skuList = new ArrayList<PublishDTO>();
		this.spuList = new ArrayList<PublishDTO>();
		sku.setProductDetailSid(condDto.getProductDetailSid());
		List<PcmProDetail> listSku = proDetailMapper.selectListByParam(sku);
		if (listSku.size() > 0 && listSku != null) {
			ValidProDetailDto record = new ValidProDetailDto();
			/* 规码 */
			if (condDto.getProStanSid() != null && !condDto.getProStanSid().equals("")) {
				record.setProStanSid(condDto.getProStanSid());
			} else {
				record.setProStanSid(listSku.get(0).getProStanSid() + "");
			}
			/* 色码 */
			if (condDto.getProColorName() != null && !condDto.getProColorName().equals("")) {
				record.setProColorName(condDto.getProColorName());
			} else {
				record.setProColorName(listSku.get(0).getProColorName());
			}
			/* 特性 */
			if (condDto.getFeatures() != null && !condDto.getFeatures().equals("")) {
				record.setFeatures(condDto.getFeatures());
			} else {
				record.setFeatures(listSku.get(0).getFeatures());
			}
			// record.setProColorSid(String.valueOf(listSku.get(0).getProColorSid()));
			List<PcmProDetail> validSku = validProDrtailService.validSku(record);

			PcmProduct spu = new PcmProduct();
			spu.setSid((listSku.get(0).getProductSid()));
			// spuList 原SPU信息
			List<PcmProduct> spuList = productMapper.selectListByParam(spu);

			if (validSku == null || validSku.size() == 0) {
				/* 更新 */
				logger.info("生成新的SKU");
				PcmProDetail sku1 = new PcmProDetail();
				sku1.setSid(listSku.get(0).getSid());
				if (condDto.getProColorName() != null && !condDto.getProColorName().equals("")) {
					sku1.setProColorName(condDto.getProColorName());
					sku1.setProColorAlias(condDto.getProColorName());
				}
				if (condDto.getProStanSid() != null && !condDto.getProStanSid().equals("")) {
					sku1.setProStanSid(condDto.getProStanSid());
					sku1.setProStanName(condDto.getProStanSid());
				}
				if (condDto.getFeatures() != null && !condDto.getFeatures().equals("")) {
					sku1.setFeatures(condDto.getFeatures());
				}

				if (record.getFeatures() == null || record.getFeatures().equals("")) {
					sku1.setProDetailName(spuList.get(0).getProductName() + Constants.SEPARATOR
							+ record.getProColorName() + Constants.SEPARATOR
							+ record.getProStanSid());
				} else {
					sku1.setProDetailName(spuList.get(0).getProductName() + Constants.SEPARATOR
							+ record.getFeatures() + Constants.SEPARATOR + record.getProStanSid());
				}

				int updateSku = proDetailMapper.updateByPrimaryKeySelective(sku1);
				if (updateSku != 1) {
					logger.info("SKU更换失败");
					throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
							ErrorCode.DATA_OPER_ERROR.getMemo());
				}
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("skuSid", listSku.get(0).getSid());
				List<PublishCacheDto> pcPro = productMapper.pubLishPro(map);
				if (pcPro != null && pcPro.size() > 0) {
					for (PublishCacheDto pcDto : pcPro) {
						PublishDTO pDto = new PublishDTO();
						pDto.setSid(pcDto.getSid());
						pDto.setType(pcDto.getType());
						sidList.add(pDto);// 下发LIST
						PublishDTO pDto_1 = new PublishDTO();
						pDto_1.setSid(listSku.get(0).getSid());
						pDto_1.setType(pcDto.getType());
						this.skuList.add(pDto_1);
						proService.cacheDelete(pcDto.getCode());// 删除缓存
					}
				} else {
					RedisVo vo2 = new RedisVo();
					vo2.setKey("skuPage");
					vo2.setField(DomainName.getShoppeInfo);
					vo2.setType(CacheUtils.HDEL);
					CacheUtils.setRedisData(vo2);
				}
			} else {
				logger.equals("SKU已存在");
				throw new BleException(ErrorCode.NEW_SKU_EXIST.getErrorCode(),
						ErrorCode.NEW_SKU_EXIST.getMemo());
			}
		} else {
			logger.equals("待更换SKU不存在");
			throw new BleException(ErrorCode.OLD_SKU_NO_EXIST.getErrorCode(),
					ErrorCode.OLD_SKU_NO_EXIST.getMemo());
		}
		publistDto.setProList(sidList);
		publistDto.setSkuList(this.skuList);
		publistDto.setSpuList(this.spuList);
		return publistDto;
	}

	/**
	 * sku换色码、规码 校验
	 * 
	 * @Methods Name updateSkuColorStan
	 * @Create In 2015年8月21日 By yedong
	 * @param condDto
	 * @return boolean
	 */
	public List<PublishDTO> validUpdateSkuColorStan(ProductCondDto condDto) {
		if (condDto.getProductDetailSid() == null || condDto.getProductDetailSid().equals("")) {
			logger.info("SKU编码不能为空");
			throw new BleException(ErrorCode.PARA_NORULE_ERROR.getErrorCode(),
					ErrorCode.PARA_NORULE_ERROR.getMemo());
		} else if ((condDto.getProColorName() == null || condDto.getProColorName().equals(""))
				&& (condDto.getProStanSid() == null || condDto.getProStanSid().equals(""))
				&& (condDto.getFeatures() == null || condDto.getFeatures().equals(""))) {
			logger.info("色码（特性）/尺寸码（规格）不能为空");
			throw new BleException(ErrorCode.PARA_NORULE_ERROR.getErrorCode(),
					ErrorCode.PARA_NORULE_ERROR.getMemo());
		}
		PcmProDetail sku = new PcmProDetail();
		sidList = new ArrayList<PublishDTO>();
		sku.setProductDetailSid(condDto.getProductDetailSid());
		List<PcmProDetail> listSku = proDetailMapper.selectListByParam(sku);
		if (listSku.size() > 0 && listSku != null) {
			ValidProDetailDto record = new ValidProDetailDto();
			/* 规码 */
			if (condDto.getProStanSid() != null && !condDto.getProStanSid().equals("")) {
				record.setProStanSid(condDto.getProStanSid());
			} else {
				record.setProStanSid(listSku.get(0).getProStanSid() + "");
			}
			/* 色码 */
			if (condDto.getProColorName() != null && !condDto.getProColorName().equals("")) {
				record.setProColorName(condDto.getProColorName());
			} else {
				record.setProColorName(listSku.get(0).getProColorName());
			}
			/* 特性 */
			if (condDto.getFeatures() != null && !condDto.getFeatures().equals("")) {
				record.setFeatures(condDto.getFeatures());
			} else {
				record.setFeatures(listSku.get(0).getFeatures());
			}
			// record.setProColorSid(String.valueOf(listSku.get(0).getProColorSid()));
			List<PcmProDetail> validSku = validProDrtailService.validSku(record);

			PcmProduct spu = new PcmProduct();
			spu.setSid((listSku.get(0).getProductSid()));
			// spuList 原SPU信息
			List<PcmProduct> spuList = productMapper.selectListByParam(spu);

			if (validSku == null || validSku.size() == 0) {
				/* 更新 */
				logger.info("生成新的SKU");
				PcmProDetail sku1 = new PcmProDetail();
				sku1.setSid(listSku.get(0).getSid());
				if (condDto.getProColorName() != null && !condDto.getProColorName().equals("")) {
					sku1.setProColorName(condDto.getProColorName());
					sku1.setProColorAlias(condDto.getProColorName());
				}
				if (condDto.getProStanSid() != null && !condDto.getProStanSid().equals("")) {
					sku1.setProStanSid(condDto.getProStanSid());
					sku1.setProStanName(condDto.getProStanSid());
				}
				if (condDto.getFeatures() != null && !condDto.getFeatures().equals("")) {
					sku1.setFeatures(condDto.getFeatures());
				}

				if (record.getFeatures() == null || record.getFeatures().equals("")) {
					sku1.setProDetailName(spuList.get(0).getProductName()
							+ record.getProColorName() + record.getProStanSid());
				} else {
					sku1.setProDetailName(spuList.get(0).getProductName() + record.getFeatures()
							+ record.getProStanSid());
				}

				int updateSku = proDetailMapper.updateByPrimaryKeySelective(sku1);
				if (updateSku != 1) {
					logger.info("SKU更换失败");
					throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
							ErrorCode.DATA_OPER_ERROR.getMemo());
				}
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("skuSid", listSku.get(0).getSid());
				List<PublishCacheDto> pcPro = productMapper.pubLishPro(map);
				if (pcPro != null && pcPro.size() > 0) {
					for (PublishCacheDto pcDto : pcPro) {
						PublishDTO pDto = new PublishDTO();
						pDto.setSid(pcDto.getSid());
						pDto.setType(pcDto.getType());
						sidList.add(pDto);// 下发LIST
						proService.cacheDelete(pcDto.getCode());// 删除缓存
					}
				} else {
					RedisVo vo2 = new RedisVo();
					vo2.setKey("skuPage");
					vo2.setField(DomainName.getShoppeInfo);
					vo2.setType(CacheUtils.HDEL);
					CacheUtils.setRedisData(vo2);
				}
			} else {
				logger.equals("SKU已存在");
				throw new BleException(ErrorCode.NEW_SKU_EXIST.getErrorCode(),
						ErrorCode.NEW_SKU_EXIST.getMemo());
			}
		} else {
			logger.equals("待更换SKU不存在");
			throw new BleException(ErrorCode.OLD_SKU_NO_EXIST.getErrorCode(),
					ErrorCode.OLD_SKU_NO_EXIST.getMemo());
		}
		throw new BleException("1");
	}

	/**
	 * 专柜商品换扣率码
	 * 
	 * @Methods Name updateProductRateCode
	 * @Create In 2015年8月5日 By zhangxy
	 * @param condDto
	 * @return int
	 * @throws Exception
	 */
	public List<PublishDTO> updateProductRateCode(ProductCondDto condDto) throws BleException {
		/**
		 * 扣率码是否存在 新专柜商品判重 skuSid + 供应商 + 专柜 新旧扣率码是否同专柜 新旧扣率码是否同供应商
		 */
		logger.info("start updateProductRateCode(), param:" + condDto.toString());
		sidList = new ArrayList<PublishDTO>();
		// listPro 专柜商品信息
		PcmShoppeProduct pro = new PcmShoppeProduct();
		pro.setShoppeProSid(condDto.getShoppeProSid());
		List<PcmShoppeProduct> proList = spMapper.selectListByParam(pro);
		// 判断专柜商品是否存在
		if (proList != null && proList.size() > 0) {
			// erpList 新扣率码信息
			PcmErpProduct erp = new PcmErpProduct();
			erp.setProductCode(condDto.getRateCode());
			List<PcmErpProduct> erpList = erpProMapper.selectListByParam(erp);
			// erpList_old 旧扣率码信息
			PcmErpProduct old_erp = new PcmErpProduct();
			old_erp.setProductCode(proList.get(0).getRateCode());
			List<PcmErpProduct> erpList_old = erpProMapper.selectListByParam(old_erp);
			// 新扣率码是否存在
			if (erpList != null && erpList.size() > 0) {
				// supplyList 新扣率码的供应商信息
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("supplyCode", erpList.get(0).getSupplyCode());
				paramMap.put("shopSid", erpList.get(0).getStoreCode());
				List<PcmSupplyInfo> supplyList = supplyMapper.selectListByParam(paramMap);
				if (supplyList == null || supplyList.size() == 0) {
					logger.info("供应商不存在");
					throw new BleException(ErrorCode.SUPPLYINFO_NULL.getErrorCode(),
							ErrorCode.SUPPLYINFO_NULL.getMemo());
				}
				// 专柜商品判重
				ValidShoppeProDto valid = new ValidShoppeProDto();
				valid.setProductDetailSid(proList.get(0).getProductDetailSid() + "");
				valid.setSupplySid(supplyList.get(0).getSid().toString());
				valid.setShoppeCode(erpList.get(0).getShoppeCode());
				PcmShoppeProduct proValid = validProDrtailService.validShoppeProBh(valid);
				if (erpList.get(0).getShoppeCode().equals(erpList_old.get(0).getShoppeCode())) {
					// 判断新/旧扣率码是否同供应商
					if (erpList.get(0).getSupplyCode().equals(erpList_old.get(0).getSupplyCode())) {
						proValid = null;
					}
				}
				if (proValid == null) {
					// 判断新/旧扣率码是否同专柜
					if (erpList.get(0).getShoppeCode().equals(erpList_old.get(0).getShoppeCode())) {
						// 判断新/旧扣率码是否同供应商
						if (erpList.get(0).getSupplyCode()
								.equals(erpList_old.get(0).getSupplyCode())) {
							logger.info("同专柜，同供应商 - 更新专柜商品与扣率码关系");
							// 同专柜，同供应商 - 更新专柜商品与扣率码关系 OK
							PcmShoppeProduct pro_new = new PcmShoppeProduct();
							pro_new.setSid(proList.get(0).getSid());
							pro_new.setRateCode(condDto.getRateCode());
							pro_new.setErpproductcode(condDto.getRateCode());
							int updatePro = spMapper.updateByPrimaryKeySelective(pro_new);
							if (updatePro != 1) {
								logger.info("同专柜，同供应商 - 更新专柜商品与扣率码关系失败");
								throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
										ErrorCode.DATA_OPER_ERROR.getMemo());
							}
						} else {
							// 同专柜，不同供应商
							// 判断新扣率码的供应商是否正常
							if (Constants.Y.equals(supplyList.get(0).getStatus())) {
								logger.info("同专柜，不同供应商");
								// 供应商正常 - 更新专柜商品与扣率码关系；更新专柜商品供应商信息 OK
								PcmShoppeProduct pro_new = new PcmShoppeProduct();
								pro_new.setSid(proList.get(0).getSid());
								pro_new.setRateCode(condDto.getRateCode());
								pro_new.setErpproductcode(condDto.getRateCode());
								pro_new.setSupplySid(supplyList.get(0).getSid());
								int updatePro = spMapper.updateByPrimaryKeySelective(pro_new);
								if (updatePro != 1) {
									logger.info("更新专柜商品与扣率码关系；更新专柜商品供应商信息失败");
									throw new BleException(
											ErrorCode.DATA_OPER_ERROR.getErrorCode(),
											ErrorCode.DATA_OPER_ERROR.getMemo());
								}
							} else {
								// 供应商不正常 - 提示新扣率供应商存在错误，不可更换（供应商不存在/供应商状态为停售等）
								logger.info("新扣率供应商存在错误，不可更换（供应商不存在/供应商状态为停售等）");
								throw new BleException(
										ErrorCode.NEW_SUPPLY_STATUS_ERROR.getErrorCode(),
										ErrorCode.NEW_SUPPLY_STATUS_ERROR.getMemo());
							}
						}
					} else {
						// 判断新/旧扣率码是否同供应商
						if (erpList.get(0).getSupplyCode()
								.equals(erpList_old.get(0).getSupplyCode())) {
							logger.info("不同专柜，同供应商");
							// 不同专柜，同供应商 - 更新专柜商品与扣率码关系；更新专柜商品与专柜信息
							PcmShoppeProduct pro_new = new PcmShoppeProduct();
							pro_new.setSid(proList.get(0).getSid());
							pro_new.setRateCode(condDto.getRateCode());
							pro_new.setErpproductcode(condDto.getRateCode());
							int updatePro = spMapper.updateByPrimaryKeySelective(pro_new);
							if (updatePro != 1) {
								logger.info("更新专柜商品与扣率码关系失败");
								throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
										ErrorCode.DATA_OPER_ERROR.getMemo());
							}

							// ProductCondDto condPro = new ProductCondDto();
							// condPro.setShoppeProSid(condDto.getShoppeProSid());
							// condPro.setShoppeSid(erpList.get(0).getShoppeCode());
							// List<PublishDTO> list =
							// updateProductShoppe(condPro);
							// if (list.size() == 0 || list == null) {
							// logger.info("更新专柜商品与专柜信息失败");
							// throw new
							// BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
							// ErrorCode.DATA_OPER_ERROR.getMemo());
							// }

							PcmShoppe shoppe = new PcmShoppe();
							shoppe.setShoppeCode(erpList.get(0).getShoppeCode());
							List<PcmShoppe> list = shoppeMapper.selectListByParam(shoppe);
							if (list == null || list.size() == 0) {
								logger.info("待更换的专柜不存在");
								throw new BleException(
										ErrorCode.NEW_SHOPPE_NO_EXIST.getErrorCode(),
										ErrorCode.NEW_SHOPPE_NO_EXIST.getMemo());
							}
							PcmShoppeProduct pro_1 = new PcmShoppeProduct();
							pro_1.setShoppeSid(list.get(0).getSid());
							pro_1.setShoppeProSid(condDto.getShoppeProSid());
							int update_1 = spMapper.updateByPrimaryKeySelective(pro_1);
							if (update_1 != 1) {
								logger.info("更新专柜商品与专柜关系失败");
								throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
										ErrorCode.DATA_OPER_ERROR.getMemo());
							}
						} else {
							// 不同专柜，不同供应商
							// 判断新扣率码的供应商是否正常
							if (Constants.Y.equals(supplyList.get(0).getStatus())) {
								logger.info("不同专柜，不同供应商");
								// 供应商正常 - 更新专柜商品与扣率码关系；更新专柜商品与供应商信息；更新专柜商品与专柜信息
								PcmShoppeProduct pro_new = new PcmShoppeProduct();
								pro_new.setSid(proList.get(0).getSid());
								pro_new.setRateCode(condDto.getRateCode());
								pro_new.setErpproductcode(condDto.getRateCode());
								pro_new.setSupplySid(supplyList.get(0).getSid());
								int updatePro = spMapper.updateByPrimaryKeySelective(pro_new);
								if (updatePro != 1) {
									logger.info("更新专柜商品与扣率码关系；更新专柜商品与供应商信息失败");
									throw new BleException(
											ErrorCode.DATA_OPER_ERROR.getErrorCode(),
											ErrorCode.DATA_OPER_ERROR.getMemo());
								}
								// ProductCondDto condPro = new
								// ProductCondDto();
								// condPro.setShoppeProSid(condDto.getShoppeProSid());
								// condPro.setShoppeSid(erpList.get(0).getShoppeCode());
								// List<PublishDTO> list =
								// updateProductShoppe(condPro);
								// if (list.size() == 0 || list == null) {
								// logger.info("更新专柜商品与专柜信息失败");
								// throw new BleException(
								// ErrorCode.DATA_OPER_ERROR.getErrorCode(),
								// ErrorCode.DATA_OPER_ERROR.getMemo());
								// }

								PcmShoppe shoppe = new PcmShoppe();
								shoppe.setShoppeCode(erpList.get(0).getShoppeCode());
								List<PcmShoppe> list = shoppeMapper.selectListByParam(shoppe);
								if (list == null || list.size() == 0) {
									logger.info("待更换的专柜不存在");
									throw new BleException(
											ErrorCode.NEW_SHOPPE_NO_EXIST.getErrorCode(),
											ErrorCode.NEW_SHOPPE_NO_EXIST.getMemo());
								}
								PcmShoppeProduct pro_1 = new PcmShoppeProduct();
								pro_1.setShoppeSid(list.get(0).getSid());
								pro_1.setShoppeProSid(condDto.getShoppeProSid());
								int update_1 = spMapper.updateByPrimaryKeySelective(pro_1);
								if (update_1 != 1) {
									logger.info("更新专柜商品与专柜关系失败");
									throw new BleException(
											ErrorCode.DATA_OPER_ERROR.getErrorCode(),
											ErrorCode.DATA_OPER_ERROR.getMemo());
								}
							} else {
								// 供应商不正常 - 提示新扣率供应商存在错误，不可更换（供应商不存在/供应商状态为停售等）
								logger.info("新扣率供应商存在错误，不可更换（供应商不存在/供应商状态为停售等）");
								throw new BleException(
										ErrorCode.NEW_SUPPLY_STATUS_ERROR.getErrorCode(),
										ErrorCode.NEW_SUPPLY_STATUS_ERROR.getMemo());
							}
						}
					}
				} else {
					logger.info("新专柜商品已存在");
					throw new BleException(ErrorCode.NEW_SHOPPEPRODUCT_EXIST.getErrorCode(),
							ErrorCode.NEW_SHOPPEPRODUCT_EXIST.getMemo());
				}
			} else {
				// 新扣率码不存在
				logger.info("新扣率码不存在");
				throw new BleException(ErrorCode.NEW_ERP_NO_EXIST.getErrorCode(),
						ErrorCode.NEW_ERP_NO_EXIST.getMemo());
			}
		} else {
			// 待更换的专柜商品不存在
			logger.info("专柜商品不存在");
			throw new BleException(ErrorCode.SHOPPEPRODUCT_NULL.getErrorCode(),
					ErrorCode.SHOPPEPRODUCT_NULL.getMemo());
		}
		PublishDTO publishDto = new PublishDTO();
		publishDto.setSid(proList.get(0).getSid());
		publishDto.setType(1);
		sidList.add(publishDto);// 下发LIST
		proService.cacheDelete(proList.get(0).getShoppeProSid());// 删除缓存
		logger.info("end updateProductRateCode()");
		return sidList;
	}

	/**
	 * 专柜商品挂促销扣率码(不用了)
	 * 
	 * @Methods Name savePromotionRate
	 * @Create In 2015年8月5日 By zhangxy
	 * @param condDto
	 * @return int
	 */
	@Override
	@Transactional
	public int savePromotionRate(ProductCondDto condDto) {
		logger.info("start savePromotionRate(), param:" + condDto.toString());
		int i = 0;
		String format = "yyyy-MM-dd HH:mm:ss"; // 日期格式
		// 判断专柜商品是否存在
		PcmShoppeProduct pro = new PcmShoppeProduct();
		pro.setShoppeProSid(condDto.getShoppeProSid());
		List<PcmShoppeProduct> listPro = spMapper.selectListByParam(pro);
		if (listPro != null && listPro.size() > 0) {
			// 判断促销扣率码是否存在并且在有效期内
			PcmSupplyShopCode scode = new PcmSupplyShopCode();
			scode.setPromoSaleCode(condDto.getPromotionRateCodeSid());
			scode.setBeginTime(DateUtil.formatDate(condDto.getBeginTime(), format));
			scode.setEndTime(DateUtil.formatDate(condDto.getEndTime(), format));
			List<PcmSupplyShopCode> listScode = supplyShopCodeMapper.validProRateCode(scode);
			if (listScode != null && listScode.size() > 0) {
				PcmPromotionRateProduct ratePro = new PcmPromotionRateProduct();
				ratePro.setPromotionRateCodeSid(listScode.get(0).getSid());
				ratePro.setShoppeProSid(listPro.get(0).getSid());
				ratePro.setBeginTime(DateUtil.formatDate(condDto.getBeginTime(), format));
				ratePro.setEndTime(DateUtil.formatDate(condDto.getEndTime(), format));
				List<PcmPromotionRateProduct> list = proRateMapper.selectListByParam(ratePro);
				if (list != null && list.size() > 0) {
					// 促销扣率码已存在
					i = 4;
				} else {
					ratePro.setOptUserSid(condDto.getOptUserSid());
					ratePro.setCreateTime(new Date());
					i = proRateMapper.insertSelective(ratePro);
				}

			} else {
				// 促销扣率码不存在或不在有效期
				i = 3;
			}
		} else {
			// 专柜商品不存在
			i = 2;
		}
		logger.info("start savePromotionRate(), param:" + i);
		return i;
	}

	public int getTotalCount(PcmProductDto record) {
		return this.productMapper.selectCount(record);
	}

	@Override
	public List<Map<String, Object>> selectPage1(PcmProductDto record) {

		return this.productMapper.selectPage1(record);
	}

	/**
	 * 多条件分页查询SPU信息
	 * 
	 * @Methods Name selectSpuPage
	 * @Create In 2015年8月12日 By zhangxy
	 * @param dto
	 * @return Page<SpuPageDto>
	 */
	@Override
	public Page<SpuPageDto> selectSpuPage(SpuPageDto dto) {
		logger.info("start selectSpuPage()----param:" + dto.toString());
		Page<SpuPageDto> page = new Page<SpuPageDto>();
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
		Integer count = productMapper.getProductCountByPara(dto);
		if (count > 0) {
			page.setCount(count);
			if (dto.getStart() == null && dto.getLimit() == null) {
				dto.setStart(page.getStart());
				dto.setLimit(page.getLimit());
			} else {
				page.setStart(dto.getStart());
				page.setLimit(dto.getLimit());
			}
			List<SpuPageDto> list = productMapper.selectProductPageByPara(dto);
			page.setList(list);
		} else {
			logger.info("查询结果为空----param:" + dto.toString());
			page.setList(null);
		}

		logger.info("end selectSpuPage()----param:" + page.toString());
		return page;
	}

	/**
	 * 14.1 移动工作台调用主数据获取促销扣率码
	 * 
	 * @Methods Name selectPromotionRteDto
	 * @Create In 2015年8月27日 By zhangxy
	 * @param PromotionRateDto
	 * @return List<PromotionRateDto>
	 */
	@Override
	public List<PromotionRateDto> selectPromotionRteDto(PromotionRateDto dto) {
		List<PromotionRateDto> resList = supplyShopCodeMapper.selectProRateCode(dto);
		return resList;
	}

	/**
	 * 专柜商品换款
	 * 
	 * @Methods Name changeProductSku
	 * @Create In 2015年9月10日 By yedong
	 * @param condDto
	 * @throws Exception
	 *             void
	 */
	public ProSkuSpuPublishDto changeProductSku(ProductCondDto condDto) throws BleException {
		/**
		 * 根据专柜商品编码查询专柜商品信息 根据skuSid查询SKU信息 根据spuSid查询SPU信息
		 * 
		 * 判断是否已存在新款/主属性+品牌的SPU 判断是否已存在新款+品牌+色码+规码的SKU或新主属性+品牌+特性+规码的SKU
		 * SPU/SKU不存在时新增 判断是否已存在SKU+专柜编码+供应商编码+销售单位的专柜商品
		 * 
		 */

		this.sidList = new ArrayList<PublishDTO>();
		this.skuList = new ArrayList<PublishDTO>();
		this.spuList = new ArrayList<PublishDTO>();
		ProSkuSpuPublishDto publistDto = new ProSkuSpuPublishDto();
		if (condDto.getShoppeProSid() == null || condDto.getShoppeProSid().equals("")) {
			logger.info("专柜商品编码不能为空");
			throw new BleException(ErrorCode.PARA_NORULE_ERROR.getErrorCode(),
					ErrorCode.PARA_NORULE_ERROR.getMemo());
		} else if ((condDto.getProductSku() == null || condDto.getProductSku().equals(""))
				&& (condDto.getPrimaryAttr() == null || condDto.getPrimaryAttr().equals(""))) {
			logger.info("款/主属性不能为空");
			throw new BleException(ErrorCode.PARA_NORULE_ERROR.getErrorCode(),
					ErrorCode.PARA_NORULE_ERROR.getMemo());
		}
		PcmShoppeProduct spro = new PcmShoppeProduct();
		spro.setShoppeProSid(condDto.getShoppeProSid());
		// sproList 原专柜商品信息
		List<PcmShoppeProduct> sproList = spMapper.selectListByParam(spro);
		if (sproList.size() > 0 && sproList != null) {
			PcmProDetail sku = new PcmProDetail();
			sku.setSid((sproList.get(0).getProductDetailSid()));
			// skuList 原SKU信息
			List<PcmProDetail> skuList = proDetailMapper.selectListByParam(sku);
			PcmProduct spu = new PcmProduct();
			spu.setSid((skuList.get(0).getProductSid()));
			// spuList 原SPU信息
			List<PcmProduct> spuList = productMapper.selectListByParam(spu);
			// SPU判重
			ValidProDetailDto spuDto = new ValidProDetailDto();
			spuDto.setBrandSid(spuList.get(0).getBrandSid());
			spuDto.setPrimaryAttr(condDto.getPrimaryAttr());
			spuDto.setProductSku(condDto.getProductSku());
			PcmProduct isSpu = validProDrtailService.validSpuBh(spuDto);
			// 判断是否已存在新款/主属性+品牌的SPU
			if (isSpu != null) {
				// SKU判重
				ValidProDetailDto skuDto = new ValidProDetailDto();
				skuDto.setProductSid(isSpu.getSid().toString());
				skuDto.setProColorName(skuList.get(0).getProColorName());
				skuDto.setProStanSid(skuList.get(0).getProStanSid());
				skuDto.setFeatures(skuList.get(0).getFeatures());
				skuDto.setProColorSid(String.valueOf(skuList.get(0).getProColorSid()));
				List<PcmProDetail> isSku = validProDrtailService.validSku(skuDto);
				// 判断是否已存在SPU编码（SID）+色码+规码的SKU 或 SPU编码（SID）+特性+规码的SKU
				if (isSku != null && isSku.size() == 1) {
					// 专柜商品判重
					ValidShoppeProDto proDto = new ValidShoppeProDto();
					proDto.setProductDetailSid(isSku.get(0).getSid() + "");
					proDto.setShoppeSid(sproList.get(0).getShoppeSid().toString());
					proDto.setSupplySid(sproList.get(0).getSupplySid().toString());
					if (spuList.get(0).getIndustryCondition() == 1) {
						proDto.setSaleUnitCode(sproList.get(0).getSaleUnitCode());
					}
					PcmShoppeProduct isPro = validProDrtailService.validShoppeProBh(proDto);
					// 判断是否已存在SKU+专柜编码+供应商编码+销售单位的专柜商品
					if (isPro != null) {
						// 新的专柜商品已存在
						logger.info("新的专柜商品已存在");
						throw new BleException(ErrorCode.NEW_SHOPPEPRODUCT_EXIST.getErrorCode(),
								ErrorCode.NEW_SHOPPEPRODUCT_EXIST.getMemo());
					} else {
						// 修改 专柜商品-SKU 关联关系
						PcmShoppeProduct sproUpdate = new PcmShoppeProduct();
						sproUpdate.setSid(sproList.get(0).getSid());
						sproUpdate.setProductDetailSid(isSku.get(0).getSid());
						int proFlag = spMapper.updateByPrimaryKeySelective(sproUpdate);
						if (proFlag != 1) {// 数据库错误
							throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
									ErrorCode.DATA_OPER_ERROR.getMemo());
						}
						// 修改后下发专柜商品
						sidList = new ArrayList<PublishDTO>();
						PublishDTO sid = new PublishDTO();
						sid.setSid(sproList.get(0).getSid());
						sid.setType(Constants.PUBLIC_1);
						sidList.add(sid);// 下发LIST

						proService.cacheDelete(sproList.get(0).getShoppeProSid());// 删除缓存
					}
				} else if (isSku != null && isSku.size() == 0) {
					picService.redisSpuCMSSHopperInfo(spuList.get(0).getProductSid());
					// 新建SKU-修改专柜商品SKU编码
					// 新建SKU
					PcmProDetail skuNew_1 = new PcmProDetail();
					BeanUtils.copyProperties(skuList.get(0), skuNew_1);
					skuNew_1.setSid(null);
					skuNew_1.setProductDetailSid(null);
					skuNew_1.setProductSid(isSpu.getSid());
					if (skuNew_1.getFeatures() == null) {
						skuNew_1.setProDetailName(isSpu.getProductName() + Constants.SEPARATOR
								+ skuNew_1.getProColorName() + Constants.SEPARATOR
								+ skuNew_1.getProStanSid());
					} else {
						skuNew_1.setProDetailName(isSpu.getProductName() + Constants.SEPARATOR
								+ skuNew_1.getFeatures() + Constants.SEPARATOR
								+ skuNew_1.getProStanSid());
					}
					int insertSku = proDetailMapper.insertSelective(skuNew_1);
					if (insertSku != 1) {// 数据库错误
						throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
								ErrorCode.DATA_OPER_ERROR.getMemo());
					}
					// 生成新的SKU编码
					Long new_Sid = skuNew_1.getSid() + Constants.SKU_CODE;
					PcmProDetail skuNew_2 = new PcmProDetail();
					skuNew_2.setSid(skuNew_1.getSid());
					skuNew_2.setProductDetailSid(new_Sid.toString());
					int updateSku = proDetailMapper.updateByPrimaryKeySelective(skuNew_2);
					if (updateSku != 1) {// 数据库错误
						throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
								ErrorCode.DATA_OPER_ERROR.getMemo());
					}
					// 修改专柜商品-SKU关联关系
					PcmShoppeProduct sproUpdate = new PcmShoppeProduct();
					sproUpdate.setSid(sproList.get(0).getSid());
					sproUpdate.setProductDetailSid(skuNew_1.getSid());
					int proFlag = spMapper.updateByPrimaryKeySelective(sproUpdate);
					if (proFlag != 1) {// 数据库错误
						throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
								ErrorCode.DATA_OPER_ERROR.getMemo());
					}
					// 修改后下发专柜商品
					sidList = new ArrayList<PublishDTO>();
					PublishDTO sid = new PublishDTO();
					sid.setSid(sproList.get(0).getSid());
					sid.setType(Constants.PUBLIC_1);
					sidList.add(sid);// 下发LIST
					PublishDTO sku_1 = new PublishDTO();
					sku_1.setSid(skuNew_1.getSid());
					sku_1.setType(Constants.PUBLIC_1);
					this.skuList.add(sku_1);// 下发LIST

					proService.cacheDelete(sproList.get(0).getShoppeProSid());// 删除缓存
				} else {
					throw new BleException(ErrorCode.SKU_IS_EXIST1.getErrorCode(),
							ErrorCode.SKU_IS_EXIST1.getMemo());
				}
			} else {
				// 新建SPU-SKU-修改专柜商品SKU编码
				// 新建SPU
				PcmProduct spuNew_1 = new PcmProduct();
				BeanUtils.copyProperties(spuList.get(0), spuNew_1);
				spuNew_1.setSid(null);
				spuNew_1.setProductSid(null);
				spuNew_1.setProductSku(condDto.getProductSku());
				spuNew_1.setPrimaryAttr(condDto.getPrimaryAttr());
				if (spuNew_1.getPrimaryAttr() != null) {
					spuNew_1.setProductName(spuNew_1.getBrandName() + Constants.SEPARATOR
							+ spuNew_1.getPrimaryAttr());
				} else if (spuNew_1.getProductSku() != null) {
					spuNew_1.setProductName(spuNew_1.getBrandName() + Constants.SEPARATOR
							+ spuNew_1.getProductSku());
				}
				int insertSpu = productMapper.insertSelective(spuNew_1);
				if (insertSpu != 1) {// 数据库错误
					throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
							ErrorCode.DATA_OPER_ERROR.getMemo());
				}
				// 修改SPU编码
				Long new_Sid = spuNew_1.getSid() + Constants.SPU_CODE;
				PcmProduct spuNew_2 = new PcmProduct();
				spuNew_2.setSid(spuNew_1.getSid());
				spuNew_2.setProductSid(new_Sid.toString());
				int updateSpu = productMapper.updateByPrimaryKeySelective(spuNew_2);
				if (updateSpu != 1) {// 数据库错误
					throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
							ErrorCode.DATA_OPER_ERROR.getMemo());
				}
				// 修改SPU-分类关联关系
				PcmProductCategory proCate_1 = new PcmProductCategory();
				proCate_1.setProductSid(spuList.get(0).getSid());
				List<PcmProductCategory> cateList = productCategoryMapper
						.selectListByParam(proCate_1);
				for (PcmProductCategory cate : cateList) {
					PcmProductCategory proCate_2 = new PcmProductCategory();
					BeanUtils.copyProperties(cate, proCate_2);
					proCate_2.setProductSid(spuNew_1.getSid());
					proCate_2.setSid(null);
					int insertCate = productCategoryMapper.insertSelective(proCate_2);
					if (insertCate != 1) {// 数据库错误
						throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
								ErrorCode.DATA_OPER_ERROR.getMemo());
					}
				}
				// 新建SKU
				PcmProDetail skuNew_1 = new PcmProDetail();
				BeanUtils.copyProperties(skuList.get(0), skuNew_1);
				skuNew_1.setSid(null);
				skuNew_1.setProductDetailSid(null);
				skuNew_1.setProductSid(spuNew_1.getSid());
				if (skuNew_1.getFeatures() == null) {
					skuNew_1.setProDetailName(spuNew_1.getProductName() + Constants.SEPARATOR
							+ skuNew_1.getProColorName() + Constants.SEPARATOR
							+ skuNew_1.getProStanSid());
				} else {
					skuNew_1.setProDetailName(spuNew_1.getProductName() + Constants.SEPARATOR
							+ skuNew_1.getFeatures() + Constants.SEPARATOR
							+ skuNew_1.getProStanSid());
				}
				skuNew_1.setPhotoPlanSid(null);
				skuNew_1.setPhotoStatus(null);
				skuNew_1.setSellingStatus(null);
				int insertSku = proDetailMapper.insertSelective(skuNew_1);
				if (insertSku != 1) {// 数据库错误
					throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
							ErrorCode.DATA_OPER_ERROR.getMemo());
				}
				// 生成新的SKU编码
				Long new_skuSid = skuNew_1.getSid() + Constants.SKU_CODE;
				PcmProDetail skuNew_2 = new PcmProDetail();
				skuNew_2.setSid(skuNew_1.getSid());
				skuNew_2.setProductDetailSid(new_skuSid.toString());
				int updateSku = proDetailMapper.updateByPrimaryKeySelective(skuNew_2);
				if (updateSku != 1) {// 数据库错误
					throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
							ErrorCode.DATA_OPER_ERROR.getMemo());
				}
				// 修改专柜商品-SKU关联关系
				PcmShoppeProduct sproUpdate = new PcmShoppeProduct();
				sproUpdate.setSid(sproList.get(0).getSid());
				sproUpdate.setProductDetailSid(skuNew_1.getSid());
				int proFlag = spMapper.updateByPrimaryKeySelective(sproUpdate);
				if (proFlag != 1) {// 数据库错误
					throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
							ErrorCode.DATA_OPER_ERROR.getMemo());
				}
				sidList = new ArrayList<PublishDTO>();
				PublishDTO sid = new PublishDTO();
				sid.setSid(sproList.get(0).getSid());
				sid.setType(Constants.PUBLIC_1);
				sidList.add(sid);// 下发LIST
				PublishDTO spu_1 = new PublishDTO();
				spu_1.setSid(spuNew_1.getSid());
				spu_1.setType(Constants.PUBLIC_1);
				this.spuList.add(spu_1);// 下发LIST
				PublishDTO sku_1 = new PublishDTO();
				sku_1.setSid(skuNew_1.getSid());
				sku_1.setType(Constants.PUBLIC_1);
				this.skuList.add(sku_1);// 下发LIST
				proService.cacheDelete(sproList.get(0).getShoppeProSid());// 删除缓存
			}
		} else {
			// 原专柜商品不存在
			throw new BleException(ErrorCode.SHOPPEPRODUCT_NULL.getErrorCode(),
					ErrorCode.SHOPPEPRODUCT_NULL.getMemo());
		}
		publistDto.setProList(sidList);
		publistDto.setSkuList(this.skuList);
		publistDto.setSpuList(this.spuList);
		return publistDto;
	}

	/**
	 * SKU换款/主属性
	 * 
	 * @Methods Name changeProductSku
	 * @Create In 2015年9月10日 By yedong
	 * @param condDto
	 * @throws Exception
	 *             void
	 */
	public ProSkuSpuPublishDto changeProductSkuBySKU(ProductCondDto condDto) throws BleException {
		/**
		 * 根据skuSid查询SKU信息 根据spuSid查询SPU信息
		 * 
		 * 判断是否已存在新款/主属性+品牌的SPU SPU存在时 判断是否已存在新款+品牌+色码+规码的SKU或新主属性+品牌+特性+规码的SKU
		 * SKU存在时 更新SKU下所有的专柜商品-SKU关联关系。 SKU不存在 新增SKU SPU不存在 新增SPU、SKU
		 * 
		 * 
		 */
		logger.info("start changeProductSkuBySKU,param : " + condDto);
		ProSkuSpuPublishDto publistDto = new ProSkuSpuPublishDto();
		if (condDto.getProductDetailSid() == null || condDto.getProductDetailSid().equals("")) {
			logger.info("SKU编码不能为空");
			throw new BleException(ErrorCode.PARA_NORULE_ERROR.getErrorCode(),
					ErrorCode.PARA_NORULE_ERROR.getMemo());
		} else if ((condDto.getProductSku() == null || condDto.getProductSku().equals(""))
				&& (condDto.getPrimaryAttr() == null || condDto.getPrimaryAttr().equals(""))) {
			logger.info("款/主属性不能为空");
			throw new BleException(ErrorCode.PARA_NORULE_ERROR.getErrorCode(),
					ErrorCode.PARA_NORULE_ERROR.getMemo());
		}
		this.sidList = new ArrayList<PublishDTO>();
		this.skuList = new ArrayList<PublishDTO>();
		this.spuList = new ArrayList<PublishDTO>();
		PcmProDetail sku = new PcmProDetail();
		sku.setProductDetailSid(condDto.getProductDetailSid());
		// skuList 原SKU信息
		List<PcmProDetail> skuList = proDetailMapper.selectListByParam(sku);
		if (skuList.size() > 0 && skuList != null) {
			PcmProduct spu = new PcmProduct();
			spu.setSid(skuList.get(0).getProductSid());
			// spuList 原SPU信息
			List<PcmProduct> spuList = productMapper.selectListByParam(spu);
			// SPU判重
			ValidProDetailDto spuDto = new ValidProDetailDto();
			spuDto.setBrandSid(spuList.get(0).getBrandSid());
			spuDto.setPrimaryAttr(condDto.getPrimaryAttr());
			spuDto.setProductSku(condDto.getProductSku());
			PcmProduct isSpu = validProDrtailService.validSpuBh(spuDto);
			if (isSpu != null) {
				// SKU判重
				ValidProDetailDto skuDto = new ValidProDetailDto();
				skuDto.setProductSid(isSpu.getSid().toString());
				skuDto.setProColorName(skuList.get(0).getProColorName());
				skuDto.setProStanSid(skuList.get(0).getProStanSid());
				skuDto.setFeatures(skuList.get(0).getFeatures());
				skuDto.setProColorSid(String.valueOf(skuList.get(0).getProColorSid()));
				List<PcmProDetail> isSku = validProDrtailService.validSku(skuDto);
				// 判断是否已存在SPU编码（SID）+色码+规码的SKU 或 SPU编码（SID）+特性+规码的SKU
				if (isSku != null && isSku.size() > 0) {
					logger.info("SKU重复，不可更换");
					throw new BleException(ErrorCode.NEW_SHOPPEPRODUCT_EXIST.getErrorCode(),
							ErrorCode.NEW_SHOPPEPRODUCT_EXIST.getMemo());
				} else {
					logger.info("SKU不重复");
					// 修改SKU
					PcmProDetail skuNew_1 = new PcmProDetail();
					skuNew_1.setSid(skuList.get(0).getSid());
					if (skuList.get(0).getFeatures() != null
							&& !skuList.get(0).getFeatures().equals("")) {
						skuNew_1.setProDetailName(isSpu.getProductName()
								+ skuList.get(0).getFeatures() + skuList.get(0).getProStanSid());
					} else {
						skuNew_1.setProDetailName(isSpu.getProductName()
								+ skuList.get(0).getProColorName() + skuList.get(0).getProStanSid());
					}
					skuNew_1.setProductSid(isSpu.getSid());
					proDetailMapper.updateByPrimaryKeySelective(skuNew_1);

					Map<String, Object> publishMap = new HashMap<String, Object>();
					publishMap.put("skuSid", skuNew_1.getSid());
					List<PublishCacheDto> pcPro = productMapper.pubLishPro(publishMap);
					if (pcPro != null && pcPro.size() > 0) {
						for (PublishCacheDto pcDto : pcPro) {
							PublishDTO pDto = new PublishDTO();
							pDto.setSid(pcDto.getSid());
							pDto.setType(pcDto.getType());
							sidList.add(pDto);// 下发LIST

							PublishDTO pDto_1 = new PublishDTO();
							pDto_1.setSid(skuList.get(0).getSid());
							pDto_1.setType(pcDto.getType());
							this.skuList.add(pDto_1);// 下发LIST
							proService.cacheDelete(pcDto.getCode());// 删除缓存
						}
					} else {
						RedisVo vo2 = new RedisVo();
						vo2.setKey("skuPage");
						vo2.setField(DomainName.getShoppeInfo);
						vo2.setType(CacheUtils.HDEL);
						CacheUtils.setRedisData(vo2);
					}
				}
			} else {
				logger.info("SPU不重复");
				// 新建SPU 新建SKU 更新新旧SKU-专柜商品的关系
				// 新建SPU
				PcmProduct spuNew_1 = new PcmProduct();
				BeanUtils.copyProperties(spuList.get(0), spuNew_1);
				spuNew_1.setSid(null);
				spuNew_1.setProductSid(null);
				spuNew_1.setProductSku(condDto.getProductSku());
				spuNew_1.setPrimaryAttr(condDto.getPrimaryAttr());
				if (spuNew_1.getPrimaryAttr() != null && !spuNew_1.getPrimaryAttr().equals("")) {
					spuNew_1.setProductName(spuNew_1.getBrandName() + Constants.SEPARATOR
							+ spuNew_1.getPrimaryAttr());
				} else if (spuNew_1.getProductSku() != null && !spuNew_1.getProductSku().equals("")) {
					spuNew_1.setProductName(spuNew_1.getBrandName() + Constants.SEPARATOR
							+ spuNew_1.getProductSku());
				}
				int insertSpu = productMapper.insertSelective(spuNew_1);
				if (insertSpu != 1) {// 数据库错误
					logger.info("数据库错误4");
					throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
							ErrorCode.DATA_OPER_ERROR.getMemo());
				}
				// 修改SPU编码
				Long new_Sid = spuNew_1.getSid() + Constants.SPU_CODE;
				PcmProduct spuNew_2 = new PcmProduct();
				spuNew_2.setSid(spuNew_1.getSid());
				spuNew_2.setProductSid(new_Sid.toString());
				if (spuNew_1.getPrimaryAttr() != null && !spuNew_1.getPrimaryAttr().equals("")) {
					spuNew_2.setProductName(spuNew_1.getBrandName() + Constants.SEPARATOR
							+ spuNew_1.getPrimaryAttr());
				} else {
					spuNew_2.setProductName(spuNew_1.getBrandName() + Constants.SEPARATOR
							+ spuNew_1.getProductSku());
				}
				int updateSpu = productMapper.updateByPrimaryKeySelective(spuNew_2);
				if (updateSpu != 1) {// 数据库错误
					logger.info("数据库错误5");
					throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
							ErrorCode.DATA_OPER_ERROR.getMemo());
				}
				// 修改SPU-分类关联关系
				PcmProductCategory proCate_1 = new PcmProductCategory();
				proCate_1.setProductSid(spuList.get(0).getSid());
				List<PcmProductCategory> cateList = productCategoryMapper
						.selectListByParam(proCate_1);
				for (PcmProductCategory cate : cateList) {
					PcmProductCategory proCate_2 = new PcmProductCategory();
					BeanUtils.copyProperties(cate, proCate_2);
					proCate_2.setSid(null);
					proCate_2.setProductSid(spuNew_1.getSid());
					int insertCate = productCategoryMapper.insertSelective(proCate_2);
					if (insertCate != 1) {// 数据库错误
						logger.info("数据库错误6");
						throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
								ErrorCode.DATA_OPER_ERROR.getMemo());
					}
				}

				PcmProDetail skuNew_1 = new PcmProDetail();
				skuNew_1.setSid(skuList.get(0).getSid());
				skuNew_1.setProductSid(spuNew_1.getSid());
				if (skuList.get(0).getFeatures() != null
						&& !skuList.get(0).getFeatures().equals("")) {
					skuNew_1.setProDetailName(spuNew_2.getProductName() + Constants.SEPARATOR
							+ skuList.get(0).getFeatures() + Constants.SEPARATOR
							+ skuList.get(0).getProStanSid());
				} else {
					skuNew_1.setProDetailName(spuNew_2.getProductName() + Constants.SEPARATOR
							+ skuList.get(0).getProColorName() + Constants.SEPARATOR
							+ skuList.get(0).getProStanSid());
				}
				skuNew_1.setPhotoPlanSid("0");
				skuNew_1.setPhotoStatus(0);
				skuNew_1.setSellingStatus(0);
				proDetailMapper.updateByPrimaryKeySelective(skuNew_1);

				Map<String, Object> publishMap = new HashMap<String, Object>();
				publishMap.put("skuSid", skuNew_1.getSid());
				List<PublishCacheDto> pcPro = productMapper.pubLishPro(publishMap);
				if (pcPro != null && pcPro.size() > 0) {
					for (PublishCacheDto pcDto : pcPro) {
						PublishDTO pDto = new PublishDTO();
						pDto.setSid(pcDto.getSid());
						pDto.setType(pcDto.getType());
						this.sidList.add(pDto);// 下发LIST

						PublishDTO pDto_1 = new PublishDTO();
						pDto_1.setSid(skuList.get(0).getSid());
						pDto_1.setType(pcDto.getType());
						this.skuList.add(pDto_1);// 下发LIST

						PublishDTO pDto_2 = new PublishDTO();
						pDto_2.setSid(spuNew_1.getSid());
						pDto_2.setType(pcDto.getType());
						this.spuList.add(pDto_2);// 下发LIST
						proService.cacheDelete(pcDto.getCode());// 删除缓存
					}
				} else {
					RedisVo vo2 = new RedisVo();
					vo2.setKey("skuPage");
					vo2.setField(DomainName.getShoppeInfo);
					vo2.setType(CacheUtils.HDEL);
					CacheUtils.setRedisData(vo2);
				}
			}
		} else {
			logger.info("原SKU信息不存在");
			throw new BleException(ErrorCode.OLD_SKU_NO_EXIST.getErrorCode(),
					ErrorCode.OLD_SKU_NO_EXIST.getMemo());
		}
		logger.info("end changeProductSkuBySKU");
		publistDto.setProList(sidList);
		publistDto.setSkuList(this.skuList);
		publistDto.setSpuList(this.spuList);
		return publistDto;
	}

	/**
	 * SKU换款/主属性 校验
	 * 
	 * @Methods Name changeProductSku
	 * @Create In 2015年9月10日 By yedong
	 * @param condDto
	 * @throws Exception
	 *             void
	 */
	public List<PublishDTO> validChangeProductSkuBySKU(ProductCondDto condDto) throws BleException {
		/**
		 * 根据skuSid查询SKU信息 根据spuSid查询SPU信息
		 * 
		 * 判断是否已存在新款/主属性+品牌的SPU SPU存在时 判断是否已存在新款+品牌+色码+规码的SKU或新主属性+品牌+特性+规码的SKU
		 * SKU存在时 更新SKU下所有的专柜商品-SKU关联关系。 SKU不存在 新增SKU SPU不存在 新增SPU、SKU
		 * 
		 * 
		 */
		logger.info("start ValidChangeProductSkuBySKU,param : " + condDto);
		if (condDto.getProductDetailSid() == null || condDto.getProductDetailSid().equals("")) {
			logger.info("SKU编码不能为空");
			throw new BleException(ErrorCode.PARA_NORULE_ERROR.getErrorCode(),
					ErrorCode.PARA_NORULE_ERROR.getMemo());
		} else if ((condDto.getProductSku() == null || condDto.getProductSku().equals(""))
				&& (condDto.getPrimaryAttr() == null || condDto.getPrimaryAttr().equals(""))) {
			logger.info("款/主属性不能为空");
			throw new BleException(ErrorCode.PARA_NORULE_ERROR.getErrorCode(),
					ErrorCode.PARA_NORULE_ERROR.getMemo());
		}
		sidList = new ArrayList<PublishDTO>();
		PcmProDetail sku = new PcmProDetail();
		sku.setProductDetailSid(condDto.getProductDetailSid());
		// skuList 原SKU信息
		List<PcmProDetail> skuList = proDetailMapper.selectListByParam(sku);
		if (skuList.size() > 0 && skuList != null) {
			PcmProduct spu = new PcmProduct();
			spu.setSid((skuList.get(0).getProductSid()));
			// spuList 原SPU信息
			List<PcmProduct> spuList = productMapper.selectListByParam(spu);
			// SPU判重
			ValidProDetailDto spuDto = new ValidProDetailDto();
			spuDto.setBrandSid(spuList.get(0).getBrandSid());
			spuDto.setPrimaryAttr(condDto.getPrimaryAttr());
			spuDto.setProductSku(condDto.getProductSku());
			PcmProduct isSpu = validProDrtailService.validSpuBh(spuDto);
			if (isSpu != null) {
				// SKU判重
				ValidProDetailDto skuDto = new ValidProDetailDto();
				skuDto.setProductSid(isSpu.getSid().toString());
				skuDto.setProColorName(skuList.get(0).getProColorName());
				skuDto.setProStanSid(skuList.get(0).getProStanSid());
				skuDto.setFeatures(skuList.get(0).getFeatures());
				skuDto.setProColorSid(String.valueOf(skuList.get(0).getProColorSid()));
				List<PcmProDetail> isSku = validProDrtailService.validSku(skuDto);
				// 判断是否已存在SPU编码（SID）+色码+规码的SKU 或 SPU编码（SID）+特性+规码的SKU
				if (isSku != null && isSku.size() > 0) {
					logger.info("SKU重复，不可更换");
					throw new BleException(ErrorCode.NEW_SHOPPEPRODUCT_EXIST.getErrorCode(),
							ErrorCode.NEW_SHOPPEPRODUCT_EXIST.getMemo());
				} else {
					logger.info("SKU不重复");
					// 修改SKU
					PcmProDetail skuNew_1 = new PcmProDetail();
					skuNew_1.setSid(skuList.get(0).getSid());
					if (skuList.get(0).getFeatures() != null
							&& !skuList.get(0).getFeatures().equals("")) {
						skuNew_1.setProDetailName(isSpu.getProductName()
								+ skuList.get(0).getFeatures() + skuList.get(0).getProStanSid());
					} else {
						skuNew_1.setProDetailName(isSpu.getProductName()
								+ skuList.get(0).getProColorName() + skuList.get(0).getProStanSid());
					}
					skuNew_1.setProductSid(isSpu.getSid());
					proDetailMapper.updateByPrimaryKeySelective(skuNew_1);

					Map<String, Object> publishMap = new HashMap<String, Object>();
					publishMap.put("skuSid", skuNew_1.getSid());
					List<PublishCacheDto> pcPro = productMapper.pubLishPro(publishMap);
					if (pcPro != null && pcPro.size() > 0) {
						for (PublishCacheDto pcDto : pcPro) {
							PublishDTO pDto = new PublishDTO();
							pDto.setSid(pcDto.getSid());
							pDto.setType(pcDto.getType());
							sidList.add(pDto);// 下发LIST
							proService.cacheDelete(pcDto.getCode());// 删除缓存
						}
					} else {
						RedisVo vo2 = new RedisVo();
						vo2.setKey("skuPage");
						vo2.setField(DomainName.getShoppeInfo);
						vo2.setType(CacheUtils.HDEL);
						CacheUtils.setRedisData(vo2);
					}
				}
			} else {
				logger.info("SPU不重复");
				// 新建SPU 新建SKU 更新新旧SKU-专柜商品的关系
				// 新建SPU
				PcmProduct spuNew_1 = new PcmProduct();
				BeanUtils.copyProperties(spuList.get(0), spuNew_1);
				spuNew_1.setSid(null);
				spuNew_1.setProductSid(null);
				spuNew_1.setProductSku(condDto.getProductSku());
				spuNew_1.setPrimaryAttr(condDto.getPrimaryAttr());
				if (spuNew_1.getPrimaryAttr() != null) {
					spuNew_1.setProductName(spuNew_1.getBrandName() + spuNew_1.getPrimaryAttr());
				} else if (spuNew_1.getProductSku() != null) {
					spuNew_1.setProductName(spuNew_1.getBrandName() + spuNew_1.getProductSku());
				}
				int insertSpu = productMapper.insertSelective(spuNew_1);
				if (insertSpu != 1) {// 数据库错误
					logger.info("数据库错误4");
					throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
							ErrorCode.DATA_OPER_ERROR.getMemo());
				}
				// 修改SPU编码
				Long new_Sid = spuNew_1.getSid() + Constants.SPU_CODE;
				PcmProduct spuNew_2 = new PcmProduct();
				spuNew_2.setSid(spuNew_1.getSid());
				spuNew_2.setProductSid(new_Sid.toString());
				if (spuNew_1.getPrimaryAttr() != null && !spuNew_1.getPrimaryAttr().equals("")) {
					spuNew_2.setProductName(spuNew_1.getBrandName() + spuNew_1.getPrimaryAttr());
				} else {
					spuNew_2.setProductName(spuNew_1.getBrandName() + spuNew_1.getProductSku());
				}
				int updateSpu = productMapper.updateByPrimaryKeySelective(spuNew_2);
				if (updateSpu != 1) {// 数据库错误
					logger.info("数据库错误5");
					throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
							ErrorCode.DATA_OPER_ERROR.getMemo());
				}
				// 修改SPU-分类关联关系
				PcmProductCategory proCate_1 = new PcmProductCategory();
				proCate_1.setProductSid(spuList.get(0).getSid());
				List<PcmProductCategory> cateList = productCategoryMapper
						.selectListByParam(proCate_1);
				for (PcmProductCategory cate : cateList) {
					PcmProductCategory proCate_2 = new PcmProductCategory();
					BeanUtils.copyProperties(cate, proCate_2);
					proCate_2.setSid(null);
					proCate_2.setProductSid(spuNew_1.getSid());
					int insertCate = productCategoryMapper.insertSelective(proCate_2);
					if (insertCate != 1) {// 数据库错误
						logger.info("数据库错误6");
						throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
								ErrorCode.DATA_OPER_ERROR.getMemo());
					}
				}

				PcmProDetail skuNew_1 = new PcmProDetail();
				skuNew_1.setSid(skuList.get(0).getSid());
				skuNew_1.setProductSid(spuNew_1.getSid());
				if (skuList.get(0).getFeatures() != null
						&& !skuList.get(0).getFeatures().equals("")) {
					skuNew_1.setProDetailName(spuNew_1.getProductName()
							+ skuList.get(0).getFeatures() + skuList.get(0).getProStanSid());
				} else {
					skuNew_1.setProDetailName(spuNew_1.getProductName()
							+ skuList.get(0).getProColorName() + skuList.get(0).getProStanSid());
				}
				proDetailMapper.updateByPrimaryKeySelective(skuNew_1);

				Map<String, Object> publishMap = new HashMap<String, Object>();
				publishMap.put("skuSid", skuNew_1.getSid());
				List<PublishCacheDto> pcPro = productMapper.pubLishPro(publishMap);
				if (pcPro != null && pcPro.size() > 0) {
					for (PublishCacheDto pcDto : pcPro) {
						PublishDTO pDto = new PublishDTO();
						pDto.setSid(pcDto.getSid());
						pDto.setType(pcDto.getType());
						sidList.add(pDto);// 下发LIST
						proService.cacheDelete(pcDto.getCode());// 删除缓存
					}
				} else {
					RedisVo vo2 = new RedisVo();
					vo2.setKey("skuPage");
					vo2.setField(DomainName.getShoppeInfo);
					vo2.setType(CacheUtils.HDEL);
					CacheUtils.setRedisData(vo2);
				}
			}
		} else {
			logger.info("原SKU信息不存在");
			throw new BleException(ErrorCode.OLD_SKU_NO_EXIST.getErrorCode(),
					ErrorCode.OLD_SKU_NO_EXIST.getMemo());
		}
		logger.info("end ValidChangeProductSkuBySKU");
		throw new BleException("1");
	}

	/**
	 * 色系相同修改各种状态
	 * 
	 * @Methods Name skuStutasVail
	 * @Create In 2016年1月20日 By yedong
	 * @param oldSku
	 * @param newSku
	 *            void
	 */
	@Override
	public void skuStutasVail(PcmProDetail oldSku, PcmProDetail newSku) {
		if (oldSku.getProColorSid() == newSku.getProColorSid()) {
			newSku.setPhotoPlanSid(oldSku.getPhotoPlanSid());
			newSku.setPhotoStatus(oldSku.getPhotoStatus());
			newSku.setSellingStatus(oldSku.getSellingStatus());
			proDetailMapper.updateByPrimaryKeySelective(newSku);
		}
	}

	@Autowired
	private RedisUtil redisUtil;

	/**
	 * 删除缓存
	 * 
	 * @Methods Name proService.cacheDelete
	 * @Create In 2015年10月8日 By zhangxy
	 * @param code
	 */
	private void cacheDelete(String code) {
		// RedisVo vo = new RedisVo();
		// vo.setKey(code);
		// vo.setField(DomainName.getShoppeInfo);
		// vo.setType(CacheUtils.HDEL);
		// CacheUtils.setRedisData(vo);
		redisUtil.del(DomainName.getShoppeInfo + code);
		RedisVo vo2 = new RedisVo();
		vo2.setKey("skuPage");
		vo2.setField(DomainName.getShoppeInfo);
		vo2.setType(CacheUtils.HDEL);
		CacheUtils.setRedisData(vo2);
	}

	/**
	 * 下发SPU至促销
	 */
	@Override
	public List<PushPromotionDto> getPushPromotionDtoBySid(Map<String, Object> paramMap) {
		List<SpuPageDto> list = productMapper.supPublishFj(paramMap);
		List<PushPromotionDto> resList = new ArrayList<PushPromotionDto>();
		for (int i = 0; i < list.size(); i++) {
			PushPromotionDto res = new PushPromotionDto();
			res.setBaseNumber("");
			res.setMgmtType("1");
			res.setErpProductCategoryLib("6");
			res.setProductType("01");
			res.setBrandCode(list.get(i).getBrandGroupCode());
			res.setIsPublished(list.get(i).getSpuSale());
			res.setListing(list.get(i).getOnMarketDate());
			res.setLongDes(list.get(i).getLongDes());
			res.setModelNumber(list.get(i).getModelCode());
			res.setProductCategory(list.get(i).getCategory());
			res.setProductCode(list.get(i).getSpuCode());
			res.setProductName(list.get(i).getProductName());
			res.setShortDes(list.get(i).getShortDes());
			res.setSpecialDes(list.get(i).getSpecialDes());
			res.setActionCode("A");
			resList.add(res);
		}
		return resList;
	}

	/**
	 * 根据spu编码查询spu
	 * 
	 * @return
	 */
	@Override
	public List<PcmProduct> getListProByParam(Map<String, Object> param) {
		List<PcmProduct> list = productMapper.selectListByParam(param);
		return list;
	}

	/**
	 * 根据spu编码修改spu长短描述
	 * 
	 * @return
	 */
	@Override
	public String updateProByParam(PcmProduct param) {
		String message = "";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("productSid", param.getProductSid());
		List<PcmProduct> list = productMapper.selectListByParam(paramMap);
		if (list != null && list.size() != 0) {
			PcmProduct pcm = new PcmProduct();
			pcm.setSid(list.get(0).getSid());
			pcm.setLongDesc(param.getLongDesc());
			pcm.setShortDes(param.getShortDes());
			int a = productMapper.updateByPrimaryKeySelective(pcm);
			message = a <= 0 ? "修改失败" : "修改成功";
		} else {
			message = "修改失败";
		}
		return message;
	}
}

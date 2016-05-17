package com.wangfj.product.maindata.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.brand.domain.entity.PcmBrand;
import com.wangfj.product.brand.persistence.PcmBrandMapper;
import com.wangfj.product.category.domain.entity.PcmCategory;
import com.wangfj.product.category.domain.entity.PcmProductCategory;
import com.wangfj.product.category.domain.vo.PcmProductCategoryDto;
import com.wangfj.product.category.persistence.PcmCategoryMapper;
import com.wangfj.product.category.persistence.PcmProductCategoryMapper;
import com.wangfj.product.maindata.domain.entity.PcmProDetail;
import com.wangfj.product.maindata.domain.entity.PcmProduct;
import com.wangfj.product.maindata.domain.entity.PcmPropertyChange;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProduct;
import com.wangfj.product.maindata.domain.vo.ChangeProductDto;
import com.wangfj.product.maindata.domain.vo.PcmPropertyChangeDto;
import com.wangfj.product.maindata.domain.vo.ProductCondDto;
import com.wangfj.product.maindata.domain.vo.ValidProDetailDto;
import com.wangfj.product.maindata.domain.vo.ValidShoppeProDto;
import com.wangfj.product.maindata.persistence.PcmBarcodeMapper;
import com.wangfj.product.maindata.persistence.PcmProDetailMapper;
import com.wangfj.product.maindata.persistence.PcmProductMapper;
import com.wangfj.product.maindata.persistence.PcmPropertyChangeMapper;
import com.wangfj.product.maindata.persistence.PcmShoppeProductMapper;
import com.wangfj.product.maindata.service.intf.IPcmPropertyChangeService;
import com.wangfj.product.maindata.service.intf.IValidProDrtailService;
import com.wangfj.product.organization.domain.entity.PcmOrganization;
import com.wangfj.product.organization.domain.entity.PcmShoppe;
import com.wangfj.product.organization.persistence.PcmOrganizationMapper;
import com.wangfj.product.organization.persistence.PcmShoppeMapper;
import com.wangfj.product.stocks.persistence.PcmStockMapper;
import com.wangfj.product.stocks.service.intf.IPcmStockService;
import com.wangfj.util.Constants;

@Service
@Transactional
public class PcmPropertyChangeServiceImp implements IPcmPropertyChangeService {
	private static final Logger logger = LoggerFactory.getLogger(PcmPropertyChangeServiceImp.class);
	@Autowired
	private PcmPropertyChangeMapper propertyMapper;
	@Autowired
	private PcmBarcodeMapper barcodeMapper;
	@Autowired
	private PcmShoppeProductMapper proMapper;
	@Autowired
	private PcmProductMapper spuMapper;
	@Autowired
	private PcmShoppeMapper shoppeMapper;
	@Autowired
	private PcmCategoryMapper cateMapper;

	@Autowired
	PcmShoppeProductMapper shoppeProMapper;
	@Autowired
	PcmStockMapper stockMapper;
	@Autowired
	PcmProDetailMapper proDetailMapper;
	@Autowired
	PcmProductMapper productMapper;
	@Autowired
	PcmBrandMapper brandMapper;
	@Autowired
	IPcmStockService pcmStockServiceImpl;
	@Autowired
	private PcmShoppeMapper pcmShoppeMapper;
	@Autowired
	private IValidProDrtailService validService;
	// SPU与工业分类关联表
	@Autowired
	private PcmProductCategoryMapper productCategoryMapper;
	@Autowired
	private PcmOrganizationMapper organizationMapper;
	@Autowired
	PcmShoppeProductMapper spMapper;

	public PcmPropertyChange insertProperty(PcmPropertyChangeDto changeDto) throws BleException {
		PcmPropertyChange record = new PcmPropertyChange();
		Date createDate = new Date();
		record.setJsonText(changeDto.getJsonText());
		Date actionDate = null;
		try {
			actionDate = new SimpleDateFormat("yyyy-MM-dd").parse(changeDto.getActiveTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		record.setCreateTime(createDate);
		record.setActiveTime(actionDate);

		if (changeDto.getSid() != null) {// 换品牌
			record.setProductCode(changeDto.getSid().toString());
			PcmShoppeProduct pro = proMapper.selectByPrimaryKey(changeDto.getSid());
			if (pro == null) {

			}
			PcmBrand old_barcode = brandMapper.selectByPrimaryKey((pro.getBrandSid()));
			PcmBrand new_barcode = brandMapper.selectByPrimaryKey(Long.parseLong(changeDto
					.getBrandSid()));
			record.setNewValue(new_barcode.getBrandSid());
			record.setOldValue(old_barcode.getBrandSid());
			record.setBillType(2);
		}
		if (changeDto.getShoppeProSid() != null) {// 换专柜
			record.setProductCode(changeDto.getShoppeProSid());
			PcmShoppeProduct entity = new PcmShoppeProduct();
			entity.setShoppeProSid(changeDto.getShoppeProSid());
			List<PcmShoppeProduct> proList = proMapper.selectListByParam(entity);

			PcmShoppe old_shoppe = shoppeMapper.selectByPrimaryKey((proList.get(0).getShoppeSid()));
			PcmShoppe shoppe = new PcmShoppe();
			shoppe.setShoppeCode(changeDto.getShoppeSid());
			List<PcmShoppe> shoppeList = shoppeMapper.selectListByParam(shoppe);

			record.setNewValue(shoppeList.get(0).getShoppeCode());
			record.setOldValue(old_shoppe.getShoppeCode());
			record.setBillType(3);
		}
		if (changeDto.getProductSid() != null) {// 换统计分类
			record.setProductCode(changeDto.getProductSid());
			PcmCategory new_cate = cateMapper.selectByPrimaryKey(Long.parseLong(changeDto
					.getCategorySid()));
			record.setNewValue(new_cate.getCategorySid());
			// record.setOldValue(dtoList.get(0).getStatCategory());
			record.setBillType(1);
		}
		propertyMapper.insertSelective(record);
		PcmPropertyChange update_record = new PcmPropertyChange();
		update_record.setSid(record.getSid());
		update_record.setBillNo(record.getSid().toString());
		propertyMapper.updateByPrimaryKeySelective(update_record);
		Long billSid = record.getSid();
		billSid = 10000000l + billSid;
		record.setBillNo("Z" + billSid.toString());
		System.out.println(JsonUtil.getJSONString(record) + "===============================");
		return record;
	}

	public Map<String, Object> getStroeCodeByShoppePro(PcmShoppeProduct entity) {
		Map<String, Object> stroeCodeByShoppePro = proMapper.getStroeCodeByShoppePro(entity);
		return stroeCodeByShoppePro;
	}

	/**
	 * 专柜商品换品牌
	 * 
	 * @Methods Name changeGroupBrand
	 * @Create In 2015-8-8 By liuhp
	 * @param changeProductDto
	 * @throws Exception
	 */
	public void changeGroupBrands(ChangeProductDto changeProductDto) throws BleException {
		// 查询原有专柜商品
		PcmShoppeProduct shoppeProductOld = shoppeProMapper.selectByPrimaryKey(changeProductDto
				.getSid());
		if (shoppeProductOld == null) {
			throw new BleException(ErrorCode.SHOPPEPRODUCT_NULL.getErrorCode(),
					ErrorCode.SHOPPEPRODUCT_NULL.getMemo());
		}

		// 查询新门店品牌信息
		PcmBrand brand = brandMapper.selectByPrimaryKey(changeProductDto.getBrandSid());

		if (brand == null) {
			throw new BleException(ErrorCode.NEW_SHOPBRAND_NULL.getErrorCode(),
					ErrorCode.NEW_SHOPBRAND_NULL.getMemo());
		}

		if (brand.getStatus() == 1) {
			throw new BleException(ErrorCode.NEW_SHOPBRAND_INVALID.getErrorCode(),
					ErrorCode.NEW_SHOPBRAND_INVALID.getMemo());
		}

		// 判断新换品牌是否等于原来品牌
		if (changeProductDto.getBrandSid().equals(shoppeProductOld.getBrandSid())) {
			throw new BleException("新更换品牌与原品牌不能相同");
		}

		// 查询新品牌对应的集团品牌信息
		PcmBrand brandGroup = brandMapper.selectByPrimaryKey(brand.getParentSid());

		if (brandGroup == null) {
			throw new BleException("集团品牌不存在!");
		}

		if (brandGroup.getStatus() == 1) {
			throw new BleException("集团品牌已失效!");
		}

		// 查询原有的商品
		PcmProDetail proDetailOld = proDetailMapper.selectByPrimaryKey(Long
				.valueOf(shoppeProductOld.getProductDetailSid()));

		// 查询原有的产品
		PcmProduct productOld = productMapper.selectByPrimaryKey(Long.valueOf(proDetailOld
				.getProductSid()));

		// 判断是否属于同一集团品牌
		if (Long.valueOf(productOld.getBrandRootSid()).equals(brandGroup.getSid())) {
			// 在同一集团品牌下，直接换
			PcmShoppeProduct newShoppeProduct = new PcmShoppeProduct();
			newShoppeProduct.setSid(shoppeProductOld.getSid());
			newShoppeProduct.setBrandSid((brand.getSid()));
			int i = shoppeProMapper.updateByPrimaryKeySelective(newShoppeProduct);
			if (i == Constants.PUBLIC_0) {
				throw new RuntimeException("专柜商品修改品牌失败！");
			}
		} else {
			// 通过专柜获取业态
			PcmShoppe shoppe = pcmShoppeMapper.selectByPrimaryKey(Long.valueOf(shoppeProductOld
					.getShoppeSid()));
			if (shoppe == null) {

				throw new BleException(ErrorCode.SHOPPE_NULL.getErrorCode(),
						ErrorCode.SHOPPE_NULL.getMemo());
			}
			// 获取业态
			Integer industryCondition = shoppe.getIndustryConditionSid();
			ValidProDetailDto validSpuDto = new ValidProDetailDto();
			validSpuDto.setBrandSid(String.valueOf(brandGroup.getBrandSid()));// 品牌SID
			if (industryCondition.equals(Constants.PUBLIC_0)
					|| industryCondition.equals(Constants.PUBLIC_2)) {
				// 百货字段
				validSpuDto.setProductSku(productOld.getProductSku());// 款号
			} else {
				// 超市字段
				validSpuDto.setPrimaryAttr(productOld.getPrimaryAttr());// 主属性
			}
			PcmProduct product = validService.validSpuBh(validSpuDto);

			if (product == null) {
				// 没有对应的spu，创建spu、sku专柜商品

				// 创建spu
				PcmProduct productNew = productOld;
				productNew.setSid(null);
				productNew.setProductSid(null);
				productNew.setBrandSid(brandGroup.getBrandSid());
				productNew.setBrandRootSid(String.valueOf(brandGroup.getSid()));
				productNew.setBrandName(brandGroup.getBrandName());
				// spu名称 ①百货（品牌+款号） ②超市（品牌+主属性）
				if (industryCondition.equals(Constants.PUBLIC_0)
						|| industryCondition.equals(Constants.PUBLIC_2)) {
					productNew.setProductName(productNew.getBrandName()
							+ productNew.getProductSku());
				}
				if (Constants.PUBLIC_1.equals(industryCondition)) {
					productNew.setProductName(productNew.getBrandName()
							+ productNew.getPrimaryAttr());
				}

				int i = productMapper.insertSelective(productNew);
				if (i == Constants.PUBLIC_0) {
					throw new BleException(ErrorCode.ADD_PRODUCT_ERROR.getErrorCode(),
							ErrorCode.ADD_PRODUCT_ERROR.getMemo());
				}
				// Ⅰ.Ⅰ 修改SPU编码
				productNew.setProductSid(String.valueOf(productNew.getSid()));
				i = productMapper.updateByPrimaryKeySelective(productNew);
				if (i == Constants.PUBLIC_0) {
					throw new BleException(ErrorCode.UPDATE_PRODUCTCODE_ERROR.getErrorCode(),
							ErrorCode.UPDATE_PRODUCTCODE_ERROR.getMemo());
				}
				// spu关联表数据修改逻辑（分类：工业分类、统计分类、展示分类）

				// 获取原品牌关联的分类
				List<PcmProductCategoryDto> categories = productCategoryMapper
						.getProCategoryByProSid(productOld.getSid());
				for (PcmProductCategoryDto pcmProductCategoryDto : categories) {
					// 关联工业分类
					PcmProductCategory ppc = new PcmProductCategory();

					ppc.setCategorySid(pcmProductCategoryDto.getCategorySid());// 分类id
					ppc.setProductSid(productNew.getSid());// SPU产品表sid
					ppc.setChannelSid(pcmProductCategoryDto.getChannelSid());
					ppc.setOptUser(changeProductDto.getActionPerson());// 操作人
					ppc.setOptDate(new Date());// 时间
					i = productCategoryMapper.insertSelective(ppc);
					if (i == Constants.PUBLIC_0) {
						throw new BleException(
								ErrorCode.ADD_CATEGORY_GY_PRODUCT_ERROR.getErrorCode(),
								ErrorCode.ADD_CATEGORY_GY_PRODUCT_ERROR.getMemo());
					}
				}

				// 创建sku
				PcmProDetail proDetailNew = proDetailOld;
				proDetailOld.setProductSid(productNew.getSid());
				proDetailNew.setSid(null);
				proDetailNew.setProductDetailSid(null);
				if (industryCondition.equals(Constants.PUBLIC_0)
						|| industryCondition.equals(Constants.PUBLIC_2)) {
					// sku名称（百货）
					proDetailNew.setProDetailName(productNew.getProductName()
							+ proDetailNew.getProColorAlias() + proDetailNew.getProStanName());
				}
				if (Constants.PUBLIC_1.equals(industryCondition)) {
					// sku名称(超市)
					proDetailNew.setProDetailName(productNew.getProductName()
							+ proDetailNew.getFeatures() + proDetailNew.getProStanName());
				}
				i = proDetailMapper.insertSelective(proDetailNew);
				if (i == Constants.PUBLIC_0) {
					throw new BleException(ErrorCode.ADD_PRODUCT_DETAIL_ERROR.getErrorCode(),
							ErrorCode.ADD_PRODUCT_DETAIL_ERROR.getMemo());
				}
				// Ⅰ.Ⅰ 修改SKU编码
				proDetailNew.setProductDetailSid(String.valueOf(proDetailNew.getSid()));
				i = proDetailMapper.updateByPrimaryKeySelective(proDetailNew);
				if (i == Constants.PUBLIC_0) {
					throw new BleException(ErrorCode.ADD_PRODUCT_DETAIL_CODE_ERROR.getErrorCode(),
							ErrorCode.ADD_PRODUCT_DETAIL_CODE_ERROR.getMemo());
				}

				// sku关联表逻辑

				// 修改专柜商品的sku
				PcmShoppeProduct newShoppeProduct = new PcmShoppeProduct();
				newShoppeProduct.setSid(shoppeProductOld.getSid());
				newShoppeProduct.setProductDetailSid(proDetailNew.getSid());
				newShoppeProduct.setBrandSid((brand.getSid()));
				i = shoppeProMapper.updateByPrimaryKeySelective(newShoppeProduct);
				if (i == Constants.PUBLIC_0) {
					throw new BleException(ErrorCode.MODIFY_SHOP_BRAND_ERROR.getErrorCode(),
							ErrorCode.MODIFY_SHOP_BRAND_ERROR.getMemo());
				}
			} else {
				PcmProDetail proDetail = new PcmProDetail();
				// sku验证
				validSpuDto.setProductSid(String.valueOf(product.getSid()));
				validSpuDto.setFeatures(proDetailOld.getFeatures());
				validSpuDto.setProColorName(proDetailOld.getProColorName());
				validSpuDto.setProStanSid(proDetailOld.getProStanSid());
				validSpuDto.setProColorSid(String.valueOf(proDetailOld.getProColorSid()));
				List<PcmProDetail> proDetailList = validService.validSku(validSpuDto);
				if (proDetailList != null && proDetailList.size() == 1) {
					// spu存在
					proDetail = proDetailList.get(0);
					// 根据专柜查询门店
					PcmOrganization shop = organizationMapper.selectByPrimaryKey(shoppe
							.getShopSid());
					// 验证专柜商品是否存在
					ValidShoppeProDto validShoppeProDto = new ValidShoppeProDto();
					validShoppeProDto.setProductDetailSid(String.valueOf(proDetail.getSid()));
					validShoppeProDto.setShoppeCode(shoppe.getShoppeCode());
					validShoppeProDto.setShoppeSid(shop.getOrganizationCode());
					validShoppeProDto.setBarcode(shoppeProductOld.getSupplyProductCode());// 条码
					validShoppeProDto.setSupplySid(shoppeProductOld.getSupplySid().toString());// 供应商
					validShoppeProDto.setSaleUnitCode(shoppeProductOld.getSaleUnitCode());// 销售单位
					PcmShoppeProduct shoppeProduct = new PcmShoppeProduct();
					try {
						shoppeProduct = validService.validShoppeProBh(validShoppeProDto);
					} catch (Exception e) {
						logger.error(e.getMessage());
						throw new BleException(ErrorCode.CHECK_SHOPPE_PRODUCT.getErrorCode(),
								ErrorCode.CHECK_SHOPPE_PRODUCT.getMemo());
					}

					if (shoppeProduct == null) {
						// 新品牌下如果不存在相同的专柜章商品，直接更换sku
						// 修改专柜商品的sku、门店品牌
						PcmShoppeProduct newShoppeProduct = new PcmShoppeProduct();
						newShoppeProduct.setSid(shoppeProductOld.getSid());
						newShoppeProduct.setProductDetailSid((proDetail.getSid()));
						newShoppeProduct.setBrandSid((brand.getSid()));
						int i = shoppeProMapper.updateByPrimaryKeySelective(newShoppeProduct);
						if (i == Constants.PUBLIC_0) {
							throw new BleException(
									ErrorCode.MODIFY_SHOP_BRAND_ERROR.getErrorCode(),
									ErrorCode.MODIFY_SHOP_BRAND_ERROR.getMemo());
						}
					} else {
						// 存在相同的专柜商品不允许更换
						throw new BleException(ErrorCode.SKU_EXIST_SHOPPE_PRODUCT.getErrorCode(),
								ErrorCode.SKU_EXIST_SHOPPE_PRODUCT.getMemo());
					}

				} else if (proDetailList != null && proDetailList.size() == 0) {
					// 不存在sku，创建sku以及专柜商品

					// 创建sku
					PcmProDetail proDetailNew = proDetailOld;
					proDetailOld.setProductSid(product.getSid());
					proDetailNew.setSid(null);
					proDetailNew.setProductDetailSid(null);
					if (Constants.PUBLIC_0.equals(industryCondition)) {
						// sku名称（百货）
						proDetailNew.setProDetailName(product.getProductName()
								+ proDetailNew.getProColorAlias() + proDetailNew.getProStanName());
					}
					if (Constants.PUBLIC_1.equals(industryCondition)) {
						// sku名称(超市)
						proDetailNew.setProDetailName(product.getProductName()
								+ proDetailNew.getFeatures() + proDetailNew.getProStanName());
					}
					int i = proDetailMapper.insertSelective(proDetailNew);
					if (i == Constants.PUBLIC_0) {
						throw new BleException(ErrorCode.ADD_PRODUCT_DETAIL_ERROR.getErrorCode(),
								ErrorCode.ADD_PRODUCT_DETAIL_ERROR.getMemo());
					}
					// Ⅰ.Ⅰ 修改SKU编码
					proDetailNew.setProductDetailSid(String.valueOf(proDetailNew.getSid()));
					i = proDetailMapper.updateByPrimaryKeySelective(proDetailNew);
					if (i == Constants.PUBLIC_0) {
						throw new BleException(
								ErrorCode.ADD_PRODUCT_DETAIL_CODE_ERROR.getErrorCode(),
								ErrorCode.ADD_PRODUCT_DETAIL_CODE_ERROR.getMemo());
					}

					// sku关联表逻辑

					// 修改专柜商品的sku
					PcmShoppeProduct newShoppeProduct = new PcmShoppeProduct();
					newShoppeProduct.setSid(shoppeProductOld.getSid());
					newShoppeProduct.setProductDetailSid((proDetailNew.getSid()));
					newShoppeProduct.setBrandSid((brand.getSid()));
					i = shoppeProMapper.updateByPrimaryKeySelective(newShoppeProduct);
					if (i == Constants.PUBLIC_0) {
						throw new BleException(ErrorCode.MODIFY_SHOP_BRAND_ERROR.getErrorCode(),
								ErrorCode.MODIFY_SHOP_BRAND_ERROR.getMemo());
					}
				} else {
					throw new BleException(ErrorCode.SKU_IS_EXIST1.getErrorCode(),
							ErrorCode.SKU_IS_EXIST1.getMemo());
				}
			}
		}
		throw new BleException("0");
	}

	/**
	 * 专柜商品换品牌
	 * 
	 * @Methods Name changeGroupShoppe
	 * @Create In 2015-8-8 By liuhp
	 * @param changeProductDto
	 * @throws Exception
	 */
	public void changeGroupShoppe(ProductCondDto condDto) throws BleException {
		// 判断专柜商品是否存在
		PcmShoppeProduct pro = new PcmShoppeProduct();
		pro.setShoppeProSid(condDto.getShoppeProSid());
		List<PcmShoppeProduct> listPro = proMapper.selectListByParam(pro);
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
				List<PcmShoppeProduct> newListPro = proMapper.selectListByParam(newPro);
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
							proMapper.updateByPrimaryKeySelective(pro1);
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
		throw new BleException("0");
	}

	/**
	 * 产品更换统计分类
	 * 
	 * @Methods Name updateStatCategory
	 * @Create In 2015-8-4 By chengsj
	 * @param pc
	 * @return int
	 * @throws Exception
	 */
	@Transactional
	public void changeGroupCategory(ProductCondDto condDto) throws BleException {
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
			List<PcmCategory> list = cateMapper.selectListByParam(cate);
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
		throw new BleException("0");
	}

	/**
	 * 查询生效的单据
	 */
	@Override
	public List<PcmPropertyChange> selectList() {
		return propertyMapper.selectList();
	}

	/**
	 * 更改单据的操作状态
	 */
	@Override
	public void updateState(PcmPropertyChange entity) {
		propertyMapper.updateByPrimaryKey(entity);
	}
}

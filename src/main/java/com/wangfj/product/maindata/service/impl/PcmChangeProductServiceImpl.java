package com.wangfj.product.maindata.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangfj.core.cache.RedisVo;
import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.CacheUtils;
import com.wangfj.core.utils.RedisUtil;
import com.wangfj.product.brand.domain.entity.PcmBrand;
import com.wangfj.product.brand.persistence.PcmBrandMapper;
import com.wangfj.product.category.domain.entity.PcmProductCategory;
import com.wangfj.product.category.domain.vo.PcmProductCategoryDto;
import com.wangfj.product.category.persistence.PcmProductCategoryMapper;
import com.wangfj.product.constants.DomainName;
import com.wangfj.product.maindata.domain.entity.PcmProDetail;
import com.wangfj.product.maindata.domain.entity.PcmProduct;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProduct;
import com.wangfj.product.maindata.domain.vo.ChangeProductDto;
import com.wangfj.product.maindata.domain.vo.ProSkuSpuPublishDto;
import com.wangfj.product.maindata.domain.vo.ValidProDetailDto;
import com.wangfj.product.maindata.domain.vo.ValidShoppeProDto;
import com.wangfj.product.maindata.persistence.PcmProDetailMapper;
import com.wangfj.product.maindata.persistence.PcmProductMapper;
import com.wangfj.product.maindata.persistence.PcmShoppeProductMapper;
import com.wangfj.product.maindata.service.intf.IPcmChangeProductService;
import com.wangfj.product.maindata.service.intf.IPcmShoppeProductService;
import com.wangfj.product.maindata.service.intf.IValidProDrtailService;
import com.wangfj.product.organization.domain.entity.PcmOrganization;
import com.wangfj.product.organization.domain.entity.PcmShoppe;
import com.wangfj.product.organization.persistence.PcmOrganizationMapper;
import com.wangfj.product.organization.persistence.PcmShoppeMapper;
import com.wangfj.product.stocks.persistence.PcmStockMapper;
import com.wangfj.product.stocks.service.intf.IPcmStockService;
import com.wangfj.util.Constants;
import com.wangfj.util.mq.PublishDTO;

/**
 * 
 * @Class Name PcmChangeProductServiceImpl
 * @Author liuhp
 * @Create In 2015-8-3
 */
@Service
public class PcmChangeProductServiceImpl implements IPcmChangeProductService {

	private static final Logger logger = LoggerFactory.getLogger(PcmChangeProductServiceImpl.class);

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
	PcmShoppeMapper shoppeMapper;
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
	private IPcmShoppeProductService proService;

	List<PublishDTO> sidList = null;

	List<PublishDTO> skuList = null;

	List<PublishDTO> spuList = null;

	/**
	 * 变更商品状态，置为不可售，所有库存清零
	 * 
	 * @Methods Name frozeProduct
	 * @Create In 2015-8-3 By liuhp
	 * @param shoppeProduct
	 * @return
	 */
	@Override
	@Transactional
	public void prohibiteShoppeProduct(ChangeProductDto changeProductDto) {
		// 商品库存清零
		pcmStockServiceImpl.stockEmpty(String.valueOf(changeProductDto.getSid()));

		// 更改商品状态
		PcmShoppeProduct shoppeProduct = new PcmShoppeProduct();
		shoppeProduct.setSid(changeProductDto.getSid());
		shoppeProduct.setSaleStatus(Constants.PCMSHOPPEPRODECT_SALE_STATUS_NO);

		shoppeProMapper.updateByPrimaryKeySelective(shoppeProduct);
	}

	/**
	 * 禁售商品、库存不清零
	 * 
	 * @Methods Name freezeShoppeProduct
	 * @Create In 2015-8-4 By liuhp
	 * @param shoppeProductDto
	 * @return
	 */
	@Override
	public void freezeShoppeProduct(ChangeProductDto changeProductDto) {

		// 更改商品状态为不可售
		PcmShoppeProduct shoppeProduct = new PcmShoppeProduct();
		shoppeProduct.setSid(changeProductDto.getSid());
		shoppeProduct.setSaleStatus(Constants.PCMSHOPPEPRODECT_SALE_STATUS_NO);

		shoppeProMapper.updateByPrimaryKeySelective(shoppeProduct);
	}

	/**
	 * 专柜商品换品牌
	 * 
	 * @Methods Name changeGroupBrand
	 * @Create In 2015-8-8 By liuhp
	 * @param changeProductDto
	 */
	@Override
	@Transactional
	public ProSkuSpuPublishDto changeGroupBrand(ChangeProductDto changeProductDto) {
		RedisVo vo2 = new RedisVo();
		String newShoppeProductSid = (changeProductDto.getSid() + Constants.PRO_CODE) + "";
		vo2.setKey(newShoppeProductSid);
		vo2.setField(DomainName.getShoppeInfo);
		vo2.setType(CacheUtils.HDEL);
		CacheUtils.setRedisData(vo2);
		ProSkuSpuPublishDto publistDto = new ProSkuSpuPublishDto();
		this.sidList = new ArrayList<PublishDTO>();
		this.skuList = new ArrayList<PublishDTO>();
		this.spuList = new ArrayList<PublishDTO>();
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

		// 新旧品牌必须同门店

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
		Long productOldSid = productOld.getSid();

		// 判断是否属于同一集团品牌
		if (Long.valueOf(productOld.getBrandRootSid()).equals(brandGroup.getSid())) {
			// 在同一集团品牌下，直接换
			PcmShoppeProduct newShoppeProduct = new PcmShoppeProduct();
			newShoppeProduct.setSid(shoppeProductOld.getSid());
			newShoppeProduct.setBrandSid((brand.getSid()));
			int i = shoppeProMapper.updateByPrimaryKeySelective(newShoppeProduct);
			if (i == Constants.PUBLIC_0) {
				throw new BleException("专柜商品修改品牌失败！");
			}
			PublishDTO publishDto = new PublishDTO();
			publishDto.setSid(shoppeProductOld.getSid());
			publishDto.setType(1);
			sidList.add(publishDto);// 下发LIST
			proService.cacheDelete(shoppeProductOld.getSid().toString());// 删除缓存
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
				if (Constants.PUBLIC_0.equals(industryCondition)
						|| Constants.PUBLIC_2.equals(industryCondition)) {
					productNew.setProductName(productNew.getBrandName()
							+ productNew.getProductSku());
				}
				if (Constants.PUBLIC_1.equals(industryCondition)) {
					productNew.setProductName(productNew.getBrandName()
							+ productNew.getPrimaryAttr());
				}

				int i = productMapper.insertSelective(productNew);
				PublishDTO publishDto = new PublishDTO();
				publishDto.setSid(productNew.getSid());
				publishDto.setType(0);
				this.spuList.add(publishDto);// 下发LIST
				if (i == Constants.PUBLIC_0) {
					throw new BleException(ErrorCode.ADD_PRODUCT_ERROR.getErrorCode(),
							ErrorCode.ADD_PRODUCT_ERROR.getMemo());
				}
				// Ⅰ.Ⅰ 修改SPU编码
				productNew.setProductSid(String.valueOf(productNew.getSid() + Constants.SPU_CODE));
				i = productMapper.updateByPrimaryKeySelective(productNew);
				if (i == Constants.PUBLIC_0) {
					throw new BleException(ErrorCode.UPDATE_PRODUCTCODE_ERROR.getErrorCode(),
							ErrorCode.UPDATE_PRODUCTCODE_ERROR.getMemo());
				}
				// spu关联表数据修改逻辑（分类：工业分类、统计分类、展示分类）

				// 获取原品牌关联的分类
				List<PcmProductCategoryDto> categories = productCategoryMapper
						.getProCategoryByProSid(productOldSid);
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
				if (Constants.PUBLIC_0.equals(industryCondition)
						|| Constants.PUBLIC_2.equals(industryCondition)) {
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
				PublishDTO publishDto_sku = new PublishDTO();
				publishDto_sku.setSid(proDetailNew.getSid());
				publishDto_sku.setType(0);
				this.skuList.add(publishDto_sku);// 下发LIST
				// Ⅰ.Ⅰ 修改SKU编码
				proDetailNew.setProductDetailSid(String.valueOf(proDetailNew.getSid()
						+ Constants.SKU_CODE));
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
				PublishDTO publishDto_pro = new PublishDTO();
				publishDto_pro.setSid(shoppeProductOld.getSid());
				publishDto_pro.setType(1);
				this.sidList.add(publishDto_pro);// 下发LIST
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
						newShoppeProduct.setProductDetailSid(proDetail.getSid());
						newShoppeProduct.setBrandSid((brand.getSid()));
						int i = shoppeProMapper.updateByPrimaryKeySelective(newShoppeProduct);
						if (i == Constants.PUBLIC_0) {
							throw new BleException(
									ErrorCode.MODIFY_SHOP_BRAND_ERROR.getErrorCode(),
									ErrorCode.MODIFY_SHOP_BRAND_ERROR.getMemo());
						}
						PublishDTO publishDto_pro = new PublishDTO();
						publishDto_pro.setSid(shoppeProductOld.getSid());
						publishDto_pro.setType(1);
						this.sidList.add(publishDto_pro);// 下发LIST
					} else {
						// 存在相同的专柜商品不允许更换
						throw new BleException(ErrorCode.SKU_EXIST_SHOPPE_PRODUCT.getErrorCode(),
								ErrorCode.SKU_EXIST_SHOPPE_PRODUCT.getMemo());
					}

				} else if (proDetailList != null && proDetailList.size() == 0) {
					// 不存在sku，创建sku以及专柜商品

					RedisVo vo = new RedisVo();
					vo.setKey(String.valueOf(product.getSid()));
					vo.setField(DomainName.getCMSSHopperInfo);
					vo.setType(CacheUtils.HDEL);
					CacheUtils.setRedisData(vo);
					// 创建sku
					PcmProDetail proDetailNew = proDetailOld;
					proDetailOld.setProductSid(product.getSid());
					proDetailNew.setSid(null);
					proDetailNew.setProductDetailSid(null);
					if (Constants.PUBLIC_0.equals(industryCondition)
							|| Constants.PUBLIC_2.equals(industryCondition)) {
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
					PublishDTO publishDto_sku = new PublishDTO();
					publishDto_sku.setSid(proDetailNew.getSid());
					publishDto_sku.setType(1);
					this.skuList.add(publishDto_sku);// 下发LIST
					// Ⅰ.Ⅰ 修改SKU编码
					proDetailNew.setProductDetailSid(String.valueOf(proDetailNew.getSid()
							+ Constants.SKU_CODE));
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
					PublishDTO publishDto_pro = new PublishDTO();
					publishDto_pro.setSid(shoppeProductOld.getSid());
					publishDto_pro.setType(1);
					this.sidList.add(publishDto_pro);// 下发LIST
				} else {
					throw new BleException(ErrorCode.SKU_IS_EXIST1.getErrorCode(),
							ErrorCode.SKU_IS_EXIST1.getMemo());
				}

			}
		}
		publistDto.setProList(sidList);
		publistDto.setSkuList(this.skuList);
		publistDto.setSpuList(this.spuList);
		return publistDto;
	}

	@Autowired
	private RedisUtil redisUtil;

	/**
	 * 删除缓存
	 * 
	 * @Methods Name cacheDelete
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
}

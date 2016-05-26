package com.wangfj.product.maindata.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.DateUtil;
import com.wangfj.product.brand.domain.entity.PcmBrand;
import com.wangfj.product.brand.domain.entity.PcmShopBrandRelation;
import com.wangfj.product.brand.persistence.PcmBrandMapper;
import com.wangfj.product.brand.persistence.PcmShopBrandRelationMapper;
import com.wangfj.product.category.domain.entity.PcmProductCategory;
import com.wangfj.product.category.persistence.PcmCategoryMapper;
import com.wangfj.product.category.persistence.PcmProductCategoryMapper;
import com.wangfj.product.maindata.domain.entity.PcmBarcode;
import com.wangfj.product.maindata.domain.entity.PcmProDetail;
import com.wangfj.product.maindata.domain.entity.PcmProInput;
import com.wangfj.product.maindata.domain.entity.PcmProduct;
import com.wangfj.product.maindata.domain.entity.PcmProductTag;
import com.wangfj.product.maindata.domain.entity.PcmSaleUnitDict;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProduct;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProductExtend;
import com.wangfj.product.maindata.domain.entity.PcmTag;
import com.wangfj.product.maindata.domain.vo.BarcodeDto;
import com.wangfj.product.maindata.domain.vo.CreateShoppePro;
import com.wangfj.product.maindata.domain.vo.CreateSkuDto;
import com.wangfj.product.maindata.domain.vo.CreateSpuDto;
import com.wangfj.product.maindata.domain.vo.PullDataDto;
import com.wangfj.product.maindata.persistence.PcmBarcodeMapper;
import com.wangfj.product.maindata.persistence.PcmContractLogMapper;
import com.wangfj.product.maindata.persistence.PcmProDetailMapper;
import com.wangfj.product.maindata.persistence.PcmProInputMapper;
import com.wangfj.product.maindata.persistence.PcmProductMapper;
import com.wangfj.product.maindata.persistence.PcmProductTagMapper;
import com.wangfj.product.maindata.persistence.PcmSaleUnitDictMapper;
import com.wangfj.product.maindata.persistence.PcmSeasonDictMapper;
import com.wangfj.product.maindata.persistence.PcmShoppeProductExtendMapper;
import com.wangfj.product.maindata.persistence.PcmShoppeProductMapper;
import com.wangfj.product.maindata.persistence.PcmTagMapper;
import com.wangfj.product.maindata.service.intf.IPcmCreateProductService;
import com.wangfj.product.maindata.service.intf.IPcmStanDictService;
import com.wangfj.product.organization.domain.entity.PcmOrganization;
import com.wangfj.product.organization.domain.entity.PcmShoppe;
import com.wangfj.product.organization.persistence.PcmOrganizationMapper;
import com.wangfj.product.organization.persistence.PcmShoppeMapper;
import com.wangfj.product.price.domain.entity.PcmPrice;
import com.wangfj.product.price.service.intf.IPcmPriceService;
import com.wangfj.product.stocks.service.intf.IPcmStockService;
import com.wangfj.product.supplier.domain.entity.PcmSupplyInfo;
import com.wangfj.product.supplier.domain.entity.PcmSupplyShoppeRelation;
import com.wangfj.product.supplier.domain.vo.PcmShoppeSupplierQueryDto;
import com.wangfj.product.supplier.domain.vo.PcmShoppeSupplierResultDto;
import com.wangfj.product.supplier.persistence.PcmSupplyInfoMapper;
import com.wangfj.product.supplier.persistence.PcmSupplyShoppeRelationMapper;
import com.wangfj.util.Constants;

/**
 * 创建商品service
 * 
 * @Class Name PcmCreateProductServiceImpl
 * @Author liuhp
 * @Create In 2015-8-17
 */
@Service
public class PcmCreateProductServiceImpl implements IPcmCreateProductService {

	private static final Logger logger = LoggerFactory.getLogger(PcmCreateProductServiceImpl.class);

	@Autowired
	PcmShoppeProductExtendMapper dsProMapper;
	@Autowired
	PcmTagMapper tagMapper;
	@Autowired
	PcmProductTagMapper proTagMapper;
	@Autowired
	PcmShoppeProductMapper shoppeProMapper;
	@Autowired
	PcmProDetailMapper proDetailMapper;
	@Autowired
	PcmProductMapper productMapper;
	@Autowired
	PcmBrandMapper brandMapper;
	@Autowired
	PcmShoppeMapper shoppeMapper;
	@Autowired
	PcmProInputMapper ppiMapper;
	@Autowired
	PcmSeasonDictMapper seasonDictMapper;
	@Autowired
	PcmSupplyShoppeRelationMapper pssrDictMapper;
	// 组织机构
	@Autowired
	private PcmOrganizationMapper organizationMapper;
	@Autowired
	IPcmStockService pcmStockServiceImpl;
	@Autowired
	private PcmSupplyInfoMapper supplyInfoMapper;
	// 工业分类
	@Autowired
	private PcmCategoryMapper categoryMapper;
	// 合同表
	@Autowired
	private PcmContractLogMapper contractLogMapper;
	// SPU与工业分类关联表
	@Autowired
	private PcmProductCategoryMapper productCategoryMapper;

	// 色码字典表
	@Autowired
	private PcmColorCodeServiceImpl colorCodeServiceImpl;

	// 销售单位字典表
	@Autowired
	private PcmSaleUnitDictMapper saleUnitMapper;

	// 规格字典service
	@Autowired
	private IPcmStanDictService stanDictService;

	// 价格service
	@Autowired
	private IPcmPriceService priceService;

	// 条码表
	@Autowired
	private PcmBarcodeMapper barcodeMapper;

	// 专柜供应商关系表
	@Autowired
	private PcmSupplyShoppeRelationMapper supplyShoppeMapper;

	// 门店-门店品牌关系表
	@Autowired
	private PcmShopBrandRelationMapper psbrMapper;

	/**
	 * 创建SPU
	 * 
	 * @Methods Name insertSPU
	 * @Create In 2015-8-17 By liuhp
	 * @param createSpuDto
	 * @return PcmProduct
	 */
	@Transactional
	public PcmProduct insertSPU(CreateSpuDto createSpuDto) {
		logger.info("start insertSPU(),param:" + createSpuDto.toString());

		PcmProduct pro = new PcmProduct();

		pro.setBrandSid(createSpuDto.getBrandSid());// 集团品牌编码
		pro.setBrandRootSid(createSpuDto.getBrandRootSid());// 集团品牌sid
		pro.setBrandName(createSpuDto.getBrandName());// 集团品牌名称
		pro.setProductSku(createSpuDto.getProductSku());// 款号
		pro.setPrimaryAttr(createSpuDto.getPrimaryAttr());// 主属性

		// spu名称 ①百货（品牌+款号） ②超市（品牌+主属性）
		if (!Constants.PUBLIC_1.equals(createSpuDto.getFlag())) {
			// pro.setProductName(pro.getBrandName() + Constants.SEPARATOR +
			// createSpuDto.getProductSku());
			List<String> paras = new ArrayList<String>();//
			paras.add(pro.getBrandName());
			paras.add(createSpuDto.getProductSku());
			String productName = generateStandardName(paras, Constants.SEPARATOR);// 生成标准品名
			pro.setProductName(productName);
		} else {
			// pro.setProductName(pro.getBrandName() + Constants.SEPARATOR +
			// createSpuDto.getPrimaryAttr());
			List<String> paras = new ArrayList<String>();//
			paras.add(pro.getBrandName());
			paras.add(createSpuDto.getPrimaryAttr());
			String productName = generateStandardName(paras, Constants.SEPARATOR);// 生成标准品名
			pro.setProductName(productName);
		}

		// 上市年份
		if (StringUtils.isNotBlank(createSpuDto.getYearToMarket())) {
			String date = createSpuDto.getYearToMarket().substring(Constants.PUBLIC_0,
					Constants.PUBLIC_4);
			pro.setYearToMarket(date);
		}
		if (StringUtils.isNotBlank(createSpuDto.getSeasonCode())) {
			pro.setSeasonCode(createSpuDto.getSeasonCode());// 季节表sid
		}
		pro.setSexSid(createSpuDto.getSexSid());// 适合人群
		pro.setIndustryCondition(createSpuDto.getFlag());// 业态
		pro.setShortDes(createSpuDto.getShortDes());// 短描述
		// 设置默认属性
		pro.setAwesome(Constants.PUBLIC_0.longValue());// 赞
		pro.setCreateTime(new Date());
		pro.setProActiveBit(Constants.PCMPRODUCT_PRO_ACTIVE_BIT_ENABLE);// 是否启用
		pro.setProductNameAlias("");// 商品别名
		pro.setProSelling(Constants.PCMPRODUCT_PRO_SELLING_NOSHELVES);// 是否上架
		pro.setEditFlag(Constants.PUBLIC_0);// 二次编辑标识
		// pro.setActivityFlg(1);// 活动标志
		// pro.setProDescSid(11111l);// 商品页描述id
		// pro.setProPicture("/img/111.jpg");// 商品图片
		// pro.setProSellingTime(new Date());
		// pro.setSpecialDes("spec");// 特别说明

		// 创建SPU
		int i = productMapper.insertSelective(pro);
		if (i == Constants.PUBLIC_0) {
			throw new BleException(ErrorCode.ADD_PRODUCT_ERROR.getErrorCode(),
					ErrorCode.ADD_PRODUCT_ERROR.getMemo());
		}

		// 插入spu编码 //9位流水
		Long code = Constants.SPU_CODE + pro.getSid();
		pro.setProductSid(String.valueOf(code));
		i = productMapper.updateByPrimaryKeySelective(pro);
		if (i == Constants.PUBLIC_0) {
			throw new BleException(ErrorCode.UPDATE_PRODUCTCODE_ERROR.getErrorCode(),
					ErrorCode.UPDATE_PRODUCTCODE_ERROR.getMemo());
		}

		// 关联工业分类
		PcmProductCategory ppc = new PcmProductCategory();

		ppc.setCategorySid(createSpuDto.getCategoryGYSid());// 工业分类
		ppc.setProductSid(pro.getSid());// SPU产品表sid
		ppc.setChannelSid(0l);
		ppc.setOptUser(createSpuDto.getOptUser());// 操作人
		ppc.setOptDate(new Date());// 时间
		i = productCategoryMapper.insertSelective(ppc);
		if (i == Constants.PUBLIC_0) {
			throw new BleException(ErrorCode.ADD_CATEGORY_GY_PRODUCT_ERROR.getErrorCode(),
					ErrorCode.ADD_CATEGORY_GY_PRODUCT_ERROR.getMemo());
		}
		logger.info("end insertSPU()");

		return pro;
	}

	/**
	 * 创建SKU
	 * 
	 * @Methods Name insertSKU
	 * @Create In 2015-8-17 By liuhp
	 * @param dataDto
	 * @param ppd
	 *            void
	 */
	@Transactional
	public PcmProDetail insertSKU(CreateSkuDto createSkuDto) {
		logger.info("start insertSKU(),param:" + createSkuDto.toString());

		PcmProDetail ppd = new PcmProDetail();
		Map map = new HashMap();
		ppd.setProductSid(Long.parseLong(createSkuDto.getProductSid()));
		ppd.setOptUserSid(createSkuDto.getOptUserSid());
		ppd.setOptRealName(createSkuDto.getOptRealName());// 操作用户real_name
		// ppd.setArticleNum(createSkuDto.getArticleNum());// 货号
		ppd.setProStanName(createSkuDto.getProStanName());// 尺码名称
		ppd.setProStanSid(createSkuDto.getProStanSid());// 尺码
		if (createSkuDto.getProType() != null) {
			ppd.setProType(createSkuDto.getProType());// 商品类型(默认为普通商品)
		} else {
			ppd.setProType(Constants.PCMPRODETAIL_PROTYPE_GENERALGOODS);// 商品类型(默认为普通商品)
		}

		// 没有用到的的字段
		ppd.setMemo(createSkuDto.getMemo());// 商品颜色为图片颜色
		ppd.setPhotoPlanSid(createSkuDto.getPhotoPlanSid());// 拍照计划表编码
		ppd.setPhotoSaleCodeSid(createSkuDto.getPhotoSaleCodeSid());// 送去拍照的销售编码SID
		ppd.setPlanMaker(createSkuDto.getPlanMaker());// 拍照计划制定人
		ppd.setPlanTime(createSkuDto.getPlanTime());// 计划制定时间
		ppd.setSearchKey(createSkuDto.getSearchKey());// 关键字
		ppd.setKeyWord(createSkuDto.getKeyWord());// 活动关键字
		ppd.setSizePictureUrl(createSkuDto.getSizePictureUrl());// 尺码图片路径
		// 失效字段
		// ppd.setBarcode("");// 条码

		// 关联尺码表
		map.clear();
		// map.put("brandSid", createSkuDto.getBrandShopCode());// 门店品牌编码
		map.put("proStanName", createSkuDto.getProStanName());// 规格名称
		map.put("proStanSid", createSkuDto.getProStanSid());// 规格编码
		map.put("brandRootSid", createSkuDto.getBrandGroupCode());// 集团编码
		map.put("categorySid", createSkuDto.getCategoryGYSid());// 工业分类编码
		int i = stanDictService.insertPcmStanDict(map);
		if (i == Constants.PUBLIC_0) {
			throw new BleException(ErrorCode.ADD_STAN_ERROR.getErrorCode(),
					ErrorCode.ADD_STAN_ERROR.getMemo());
		}

		// 关联色系
		ppd.setProColorSid(createSkuDto.getProColorSid());

		ppd.setProColorName(createSkuDto.getProColorName());// 色码
		ppd.setProColorAlias(createSkuDto.getProColorAlias());// 色码名称
		// 百货添加色码，超市添加特性，并生成sku名称
		if (!Constants.PUBLIC_1.equals(createSkuDto.getFlag())) {
			// 百货关联色码表
			map.clear();
			map.put("colorCode", createSkuDto.getProColorName());// 色码编码
			// map.put("brandCode", createSkuDto.getBrandShopCode());// 门店品牌编码
			map.put("colorName", createSkuDto.getProColorAlias());// 色码名称
			i = colorCodeServiceImpl.insertColorCodeDict(map);
			if (i == Constants.PUBLIC_0) {
				throw new BleException(ErrorCode.ADD_COlOR_ERROR.getErrorCode(),
						ErrorCode.ADD_COlOR_ERROR.getMemo());
			}

			// sku名称
			/*
			 * ppd.setProDetailName(createSkuDto.getProductName() +
			 * createSkuDto.getProColorAlias() + createSkuDto.getProStanName());
			 */
			List<String> paras = new ArrayList<String>();//
			paras.add(createSkuDto.getProductName());
			paras.add(createSkuDto.getProColorAlias());
			paras.add(createSkuDto.getProStanName());
			String proDetailName = generateStandardName(paras, Constants.SEPARATOR);// sku名称
			ppd.setProDetailName(proDetailName);
		} else {
			// 超市添加特性
			ppd.setFeatures(createSkuDto.getFeatures());
			// sku名称
			/*
			 * ppd.setProDetailName(createSkuDto.getProductName() +
			 * createSkuDto.getFeatures() + createSkuDto.getProStanName());
			 */
			List<String> paras = new ArrayList<String>();//
			paras.add(createSkuDto.getProductName());
			paras.add(createSkuDto.getFeatures());
			paras.add(createSkuDto.getProStanName());
			String proDetailName = generateStandardName(paras, Constants.SEPARATOR);// sku名称
			ppd.setProDetailName(proDetailName);
		}

		// 默认属性设置
		ppd.setOptUpdateTime(new Date());// 操作时间
		ppd.setProWriTime(new Date());// 录入时间
		ppd.setPhotoStatus(Constants.PCMPRODETAIL_PHOTO_STATUS_NOPLAN);// 拍照计划
		ppd.setProActiveBit(Constants.PCMPRODETAIL_ACTIVE_BIT_ENABLE);// 是否启用
		ppd.setSellingStatus(Constants.PCMPRODETAIL_PRO_SELLING_NOSHELVES);// 上架状态

		// 创建SKU
		i = proDetailMapper.insertSelective(ppd);
		if (i == Constants.PUBLIC_0) {
			throw new BleException(ErrorCode.ADD_PRODUCT_DETAIL_ERROR.getErrorCode(),
					ErrorCode.ADD_PRODUCT_DETAIL_ERROR.getMemo());
		}
		// 判断色系上架状态
		if (ppd.getProColorSid() != null) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("productSid", ppd.getProductSid());
			paramMap.put("proColorSid", ppd.getProColorSid());
			paramMap.put("proActiveBit", 1);
			paramMap.put("sellingStatus", 1);
			List<PcmProDetail> list = proDetailMapper.selectListByParam(paramMap);
			if (list != null && list.size() != 0) {
				ppd.setSellingStatus(1);
				ppd.setSizePictureUrl("1");
				ppd.setPhotoStatus(4);
				ppd.setPhotoPlanSid("2");
				ppd.setSizePictureUrl("1");
			}
		}
		// 创建SKU编码 //13位流水
		Long code = Constants.SKU_CODE + ppd.getSid();
		ppd.setProductDetailSid(String.valueOf(code));
		i = proDetailMapper.updateByPrimaryKeySelective(ppd);
		if (i == Constants.PUBLIC_0) {
			throw new BleException(ErrorCode.ADD_PRODUCT_DETAIL_CODE_ERROR.getErrorCode(),
					ErrorCode.ADD_PRODUCT_DETAIL_CODE_ERROR.getMemo());
		}

		logger.info("end insertSKU()");
		return ppd;
	}

	/**
	 * 创建专柜商品
	 * 
	 * @Methods Name insertShoppePro
	 * @Create In 2015-8-18 By liuhp
	 * @param dataDto
	 * @param psp
	 * @param spuCode
	 *            void
	 */
	@Transactional
	public PcmShoppeProduct insertShoppePro(CreateShoppePro createShoppePro) {
		logger.info("start insertShoppePro(),param:" + createShoppePro.toString());
		PcmShoppeProduct psp = new PcmShoppeProduct();
		psp.setProductDetailSid(Long.parseLong(createShoppePro.getProductDetailSid()));// 商品sid
		psp.setBrandSid(Long.parseLong(createShoppePro.getBrandSid()));// 门店品牌SID
		psp.setCategorySid(createShoppePro.getCategorySid());// 管理分类编码
		psp.setErpproductcode(createShoppePro.getErpproductcode());// ERP商品编码
		psp.setShoppeProName(createShoppePro.getShoppeProName());// 专柜商品名称
		psp.setShoppeProAlias(createShoppePro.getShoppeProAlias());// 专柜商品简称
		psp.setShoppeSid(Long.parseLong(createShoppePro.getShoppeSid()));// 专柜SID
		psp.setSupplySid(Long.parseLong(createShoppePro.getSupplySid()));// 供应商sid
		psp.setOriginalPrice(createShoppePro.getOriginalPrice());// 原价
		psp.setRateCode(createShoppePro.getRateCode());// 扣率码
		psp.setInputTax(createShoppePro.getInputTax());// 进项税
		psp.setOutputTax(createShoppePro.getOutputTax());// 销项税
		psp.setSalesTax(createShoppePro.getSalesTax());// 消费税
		psp.setPurchasePrice(createShoppePro.getPurchasePrice());// 扣率/进价
		psp.setBuyingPrice(createShoppePro.getBuyingPrice());// 扣率/含税进价
		psp.setOriginLand(createShoppePro.getOriginLand());// 产地
		psp.setOriginLand2(createShoppePro.getOriginLand2());// 原产地
		psp.setProcessType(createShoppePro.getProcessType());// 加工类型
		psp.setDiscountLimit(createShoppePro.getDiscountLimit());// 折扣底限
		psp.setOrderType(createShoppePro.getOrderType());// 订货类型
		psp.setField3(createShoppePro.getField3());// 货号
		psp.setField4(createShoppePro.getField4());// 原系统商品编码
		if (createShoppePro.getIsPromotion() != null) {
			psp.setIsPromotion(createShoppePro.getIsPromotion());// 是否允许ERP促销
		} else {
			psp.setIsPromotion(Constants.PUBLIC_0);// 是否允许ERP促销
		}
		if (createShoppePro.getIsAdjustPrice() != null) {
			psp.setIsAdjustPrice(createShoppePro.getIsAdjustPrice());// 是否允许ERP调价
		} else {
			psp.setIsAdjustPrice(Constants.PUBLIC_0);// 是否允许ERP促销
		}
		psp.setField1(createShoppePro.getRemarks());// 备注
		psp.setStockMode(createShoppePro.getStockMode());// 库存方式 1.ZK 自库 2.XK 虚库
															// 3.BG 不管库存
		psp.setIsStock(createShoppePro.getIsStock());// 是否管库存
		psp.setField2(createShoppePro.getField2());
		// ，自营的默认为管库存，联营的不管库存
		// if (Constants.PUBLIC_0.equals(createShoppePro.getBusinessType())) {
		// psp.setIsStock(0);
		// } else {
		// psp.setIsStock(1);
		// }
		// 联营 创建专柜供应商关系
		/*
		 * if (Constants.PUBLIC_1.equals(createShoppePro.getBusinessType())) {
		 * insertSupplyShoppeRelation(psp.getSupplySid(), psp.getShoppeSid()); }
		 */
		// 添加 销售单位编码
		int i = 0;
		psp.setSaleUnitCode(createShoppePro.getSaleUnitCode());// 销售单位Sid
		Map map = new HashMap();
		map.put("unitCode", createShoppePro.getSaleUnitCode());
		List<PcmSaleUnitDict> lsu = saleUnitMapper.selectListByParam(map);
		if (lsu == null || lsu.size() < Constants.PUBLIC_1) {
			// 字典表没有则创建
			PcmSaleUnitDict psud = new PcmSaleUnitDict();
			psud.setUnitCode(createShoppePro.getSaleUnitCode());// 销售单位编码
			psud.setUnitName(createShoppePro.getSaleUnitCode());// 销售单位名称
			i = saleUnitMapper.insertSelective(psud);
			if (i == Constants.PUBLIC_0) {
				throw new BleException(ErrorCode.ADD_SALE_UNIT_CODE_ERROR.getErrorCode(),
						ErrorCode.ADD_SALE_UNIT_CODE_ERROR.getMemo());
			}
		}

		// 默认值设定
		if (createShoppePro.getSaleStatus() != null) {
			psp.setSaleStatus(createShoppePro.getSaleStatus());// 专柜商品可售状态
		} else {
			psp.setSaleStatus(Constants.PCMSHOPPEPRODECT_SALE_STATUS_YES);// 专柜商品可售状态
		}
		psp.setShoppeProType(Constants.PCMSHOPPEPRODECT_PRO_TYPE_ORDINARY);// 专柜商品类型

		// 没有用到的字段的设置
		psp.setMeasureUnitDictSid(createShoppePro.getMeasureUnitDictSid());// 计量单位
		psp.setPackUnitDictSid(createShoppePro.getPackUnitDictSid());// 包装单位
		psp.setIsCod(createShoppePro.getIsCod());// 是否支持货到付款
		psp.setTmsParam(createShoppePro.getTmsParam());// 物流属性
		psp.setIsGift(createShoppePro.getIsGift());// 赠品范围
		psp.setIsDiscountable(createShoppePro.getIsDiscountable());// 是否可打折
		psp.setSupplyProductCode(createShoppePro.getSupplyProductCode());// 供应商商品编码
		psp.setIsPacking(createShoppePro.getIsPacking());// 可否包装

		// 无效字段
		// psp.setNegativeStock(1);// 是否负库存销售

		// 创建专柜商品
		i = shoppeProMapper.insertSelective(psp);
		if (i == Constants.PUBLIC_0) {
			throw new BleException(ErrorCode.ADD_PRODUCT_SHOPPRO_ERROR.getErrorCode(),
					ErrorCode.ADD_PRODUCT_SHOPPRO_ERROR.getMemo());
		}
		// 插入专柜商品编码 //8位流水
		if("2".equals(String.valueOf(createShoppePro.getFlag()))){//如果传入电商商品，则专柜商品编码为原系统商品编码，shoppe_pro_sid = field4
			if(StringUtils.isNotBlank(createShoppePro.getField4())){
				psp.setShoppeProSid(createShoppePro.getField4());
			}else{
				String sapsidStr =Constants.SAPPRO_CODE + String.format("%011d", psp.getSid());
				psp.setShoppeProSid(sapsidStr);
			}
		}else{
			Long code = Constants.PRO_CODE + psp.getSid();
			psp.setShoppeProSid(String.valueOf(code));
		}
		i = shoppeProMapper.updateByPrimaryKeySelective(psp);
		if (i == Constants.PUBLIC_0) {
			throw new BleException(ErrorCode.ADD_PRODUCT_SHOPPRO_CODE_ERROR.getErrorCode(),
					ErrorCode.ADD_PRODUCT_SHOPPRO_CODE_ERROR.getMemo());
		}
		// 关联统计分类
		PcmProductCategory ppcTJ = new PcmProductCategory();
		ppcTJ.setCategorySid(createShoppePro.getCategoryTJSid());// 统计分类
		ppcTJ.setProductSid(Long.valueOf(psp.getSid()));// 专柜商品表编码
		ppcTJ.setChannelSid(0l);
		// ppcTJ.setOptUser(createShoppePro.getOptUser());// 操作人
		ppcTJ.setOptDate(new Date());// 时间
		i = productCategoryMapper.insertSelective(ppcTJ);
		if (i == Constants.PUBLIC_0) {
			throw new BleException(ErrorCode.ADD_CATEGORY_TJ_PRODUCT_ERROR.getErrorCode(),
					ErrorCode.ADD_CATEGORY_TJ_PRODUCT_ERROR.getMemo());
		}
		// 添加价格
		PcmPrice pp = new PcmPrice();
		pp.setShoppeProSid(psp.getShoppeProSid());// 专柜商品编码
		pp.setAttribute2(createShoppePro.getShopCode());//门店编码
		// pp.setProductSid(Long.valueOf(spuCode));// SPU编码
		// 价格开始,结束时间
		pp.setPromotionBeginTime(DateUtil.formatDate(DateUtil.formatToStr(new Date(), "yyyyMMdd"),
				"yyyyMMdd")); // 开始时间
		pp.setPromotionEndTime(DateUtil.formatDate("99991231235959", "yyyyMMddHHmmss"));// 结束时间
		pp.setPriceType(Constants.PRICE_TYPE_1);// 永久变价
		pp.setAttribute1(Constants.DEFAULT_CHANGE_CODE);// 变价号
		pp.setChannelSid(Constants.DEFAULT_CHANNEL_SID); // 渠道
		if (createShoppePro.getSalePrice() != null) {
			pp.setCurrentPrice(createShoppePro.getSalePrice());// 现价
			pp.setPromotionPrice(createShoppePro.getSalePrice());// 促销价格
			pp.setOriginalPrice(createShoppePro.getOriginalPrice());// 原价
		} else {
			pp.setCurrentPrice(createShoppePro.getOriginalPrice());// 现价
			pp.setPromotionPrice(createShoppePro.getOriginalPrice());// 促销价格
			pp.setOriginalPrice(createShoppePro.getOriginalPrice());// 原价
		}
		pp.setOffValue(new BigDecimal(1));// 折扣值，后台程序计算
		pp.setUnit("RMB");// 货币单位
		i = priceService.initProductPriceInfo(pp);
		if (i == Constants.PUBLIC_0) {
			throw new BleException(ErrorCode.ADD_PRODUCT_PRICE_ERROR.getErrorCode(),
					ErrorCode.ADD_PRODUCT_PRICE_ERROR.getMemo());
		}
		// 创建条码
		if (createShoppePro.getBarcode() != null && createShoppePro.getBarcode().size() != 0) {
			for (BarcodeDto dto : createShoppePro.getBarcode()) {
				PcmBarcode pb = new PcmBarcode();
				pb.setShoppeProSid(psp.getShoppeProSid());// 专柜商品编码
				pb.setStoreCode(createShoppePro.getShopCode());// 门店编码
				pb.setSupplyCode(createShoppePro.getSupplyCode());// 供应商编码
				pb.setProductCode(createShoppePro.getErpproductcode());// 商品的ERP编码
				pb.setShoppeCode(createShoppePro.getShoppeCode());// 专柜编码（中台的专柜编码）
				pb.setBarcodeUnit(createShoppePro.getSaleUnitCode());// 销售单位（文字信息，如，瓶）
				// pb.setSaleMount(null);// 多包装的含量
				// pb.setBarcodeRate(null);// 倍率
				if (StringUtils.isNotBlank(dto.getOriginLand())) {
					pb.setOriginLand(dto.getOriginLand());// 产地
				} else {
					pb.setOriginLand(createShoppePro.getOriginLand());
				}
				pb.setBarcode(dto.getBarcode());// 条码
				pb.setBarcodeName(dto.getBarcode());// 条码名称
				pb.setCodeType(dto.getType());// 条码类型
				i = barcodeMapper.insertSelective(pb);
				if (i == Constants.PUBLIC_0) {
					throw new BleException(ErrorCode.ADD_PRODUCT_STAN_BARCODE_ERROR.getErrorCode(),
							ErrorCode.ADD_PRODUCT_STAN_BARCODE_ERROR.getMemo());
				}
			}
		} else {
			PcmBarcode pb = new PcmBarcode();
			pb.setShoppeProSid(psp.getShoppeProSid());// 专柜商品编码
			pb.setStoreCode(createShoppePro.getShopCode());// 门店编码
			pb.setSupplyCode(createShoppePro.getSupplyCode());// 供应商编码
			pb.setProductCode(createShoppePro.getErpproductcode());// 商品的ERP编码
			pb.setShoppeCode(createShoppePro.getShoppeCode());// 专柜编码（中台的专柜编码）
			pb.setBarcodeUnit(createShoppePro.getSaleUnitCode());// 销售单位（文字信息，如，瓶）
			// pb.setSaleMount(null);// 多包装的含量
			// pb.setBarcodeRate(null);// 倍率
			pb.setOriginLand(createShoppePro.getOriginLand());// 产地
			pb.setBarcode(psp.getShoppeProSid());// 条码
			pb.setBarcodeName(psp.getShoppeProSid());// 条码名称
			pb.setCodeType(0);// 条码类型
			i = barcodeMapper.insertSelective(pb);
			if (i == Constants.PUBLIC_0) {
				throw new BleException(ErrorCode.ADD_PRODUCT_STAN_BARCODE_ERROR.getErrorCode(),
						ErrorCode.ADD_PRODUCT_STAN_BARCODE_ERROR.getMemo());
			}
		}
		// 要约号的处理逻辑（待定）

		logger.info("end insertShoppePro()");
		return psp;
	}

	/**
	 * 插入供应商专柜关系
	 * 
	 * @Methods Name insertSupplyShoppeRelation
	 * @Create In 2015年9月9日 By zhangxy
	 * @param supplySid
	 * @param shoppeSid
	 */
	public void insertSupplyShoppeRelation(String supplySid, String shoppeSid) {
		PcmSupplyShoppeRelation pssr = new PcmSupplyShoppeRelation();
		pssr.setSupplySid(supplySid);
		pssr.setShoppeSid(shoppeSid);
		pssr.setStatus(Constants.PUBLIC_0);
		// 判重
		List<PcmSupplyShoppeRelation> li = pssrDictMapper.selectListByParam(pssr);
		if (li.size() == 0) {
			pssrDictMapper.insertSelective(pssr);
		}
	}

	/**
	 * 插入临时表信息
	 * 
	 * @Methods Name insertProductInput
	 * @Create In 2015年8月24日 By zhangxy
	 * @param dataDto
	 * @return int
	 */
	public void insertProductInput(Long spSid, String offerNum, String entryNumber, String ppNum,
			PcmShoppeProductExtend dsPro) throws BleException {
		PcmProInput ppi = new PcmProInput();
		ppi.setContractCode(offerNum);
		ppi.setInputUserCode(entryNumber);
		ppi.setProcurementUserCode(ppNum);
		ppi.setShoppeProSid(spSid);
		int i = ppiMapper.insertSelective(ppi);
		if (i != 1) {
			throw new BleException(ErrorCode.PRO_INPUT_ERROR.getErrorCode(),
					ErrorCode.PRO_INPUT_ERROR.getMemo());
		}
		ppi.setOrderNo("Z" + StringUtils.leftPad(String.valueOf(ppi.getSid()), 8, "0"));
		i = ppiMapper.updateByPrimaryKeySelective(ppi);
		if (i != 1) {
			throw new BleException(ErrorCode.PRO_INPUT_ERROR.getErrorCode(),
					ErrorCode.PRO_INPUT_ERROR.getMemo());
		}
		if (dsPro != null) {
			dsPro.setShoppeProSid(String.valueOf(spSid));
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String newtime = sdf.format(date);
			dsPro.setField9(newtime);// 创建时间
			dsPro.setField10(newtime);// 更新时间
			int i2 = dsProMapper.insertSelective(dsPro);
			if (i2 != 1) {
				throw new BleException(ErrorCode.PRO_INPUT_ERROR.getErrorCode(),
						ErrorCode.PRO_INPUT_ERROR.getMemo());
			}
		}
	}

	/**
	 * 插入标签
	 * 
	 * @Methods Name insertProductTags
	 * @Create In 2015年11月25日 By zhangxy
	 * @param tags
	 */
	@Override
	public void insertProductTags(Long sid, List<String> tags) {
		for (String tag : tags) {
			if (tag == null || tag.trim().equals("")) {
				continue;
			}
			PcmTag t = new PcmTag();
			t.setStatus(0);
			t.setTagName(tag);
			t.setTagType(1);
			List<PcmTag> li = tagMapper.selectListByParam(t);
			if (li == null || li.size() == 0) {
				t.setCreateTime(new Date());
				int i = tagMapper.insertSelective(t);
				if (i != 1) {
					continue;
				}
				t.setTagCode(t.getSid().toString());
				i = tagMapper.updateByPrimaryKeySelective(t);
				if (i != 1) {
					continue;
				}
			} else {
				t = li.get(0);
			}
			PcmProductTag ppt = new PcmProductTag();
			ppt.setProductSid(sid.toString());
			ppt.setTagSid(t.getSid());
			ppt.setFlag(0);
			proTagMapper.insertSelective(ppt);
		}
	}

	@Override
	@Transactional
	/**
	 * 电商上传商品时有中台专柜商品编码，修改中台专柜商品数据
	 */
	public PcmShoppeProduct updateSProductBySProductCode(PullDataDto dataDto,
			PcmShoppeProductExtend extendDto, String shoppeProSid) throws BleException {
		// 修改专柜商品信息
		NotEmptyCheck(dataDto);// 修改参数验证
		Map<String, Object> map = new HashMap<String, Object>();
		// 专柜数据校验
		map.clear();
		map.put("shoppeCode", dataDto.getCounterCode());
		map.put("shoppeType", "01");// 查找单品专柜
		List<PcmShoppe> shoppeList = shoppeMapper.selectListByParam(map);
		if (shoppeList == null || shoppeList.size() != 1) {
			throw new BleException(ErrorCode.SHOPPE_NULL.getErrorCode(),
					ErrorCode.SHOPPE_NULL.getMemo());
		}
		// 判断专柜状态
		if (shoppeList.get(0).getShoppeStatus() == null
				|| shoppeList.get(0).getShoppeStatus() != Constants.PUBLIC_1
				|| shoppeList.get(0).getIndustryConditionSid() != Integer.parseInt(dataDto
						.getType())) {
			throw new BleException(ErrorCode.SHOPPE_STATUS_ERROR.getErrorCode(),
					ErrorCode.SHOPPE_STATUS_ERROR.getMemo());
		}
		// 获取门店信息
		PcmOrganization org = organizationMapper.get(shoppeList.get(0).getShopSid());
		if (org == null) {
			throw new BleException(ErrorCode.SHOPPE_STATUS_ERROR.getErrorCode(),
					ErrorCode.SHOPPE_STATUS_ERROR.getMemo());
		}
		// 供应商数据校验
		map.clear();
		map.put("shopSid", org.getOrganizationCode());
		map.put("supplyCode", dataDto.getSupplierCode());
		List<PcmSupplyInfo> supplyInfoList = supplyInfoMapper.selectListByParam(map);
		if (supplyInfoList == null || supplyInfoList.size() != 1) {
			throw new BleException(ErrorCode.SUPPLYINFO_NULL.getErrorCode(),
					ErrorCode.SUPPLYINFO_NULL.getMemo());
		}
		// 判断供应商状态
		if (supplyInfoList.get(0).getStatus() == null
				|| !supplyInfoList.get(0).getStatus().equals(Constants.PCMSUPPLYINFO_STATUS_Y_CODE)) {
			throw new BleException(ErrorCode.SUPPLYINFO_STATUS_ERROR.getErrorCode(),
					ErrorCode.SUPPLYINFO_STATUS_ERROR.getMemo());
		}
		// 判断供应商经营方式
		if (supplyInfoList.get(0).getBusinessPattern() == null
				|| !String.valueOf(supplyInfoList.get(0).getBusinessPattern()).equals(
						dataDto.getOperateMode())) {
			throw new BleException(ErrorCode.SUPPLYINFO_BUSSINESS_ERROR.getErrorCode(),
					ErrorCode.SUPPLYINFO_BUSSINESS_ERROR.getMemo());
		}
		// 判断供应商专柜关系
		/*
		 * if (dataDto.getOperateMode()
		 * .equals(String.valueOf(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3))) {//
		 * 联营 PcmShoppeSupplierQueryDto dto = new PcmShoppeSupplierQueryDto();
		 * dto.setShoppeSid(String.valueOf(shoppeList.get(0).getSid()));//
		 * 专柜表SID
		 * dto.setSupplySid(String.valueOf(supplyInfoList.get(0).getSid()));//
		 * 供应商表SID List<PcmShoppeSupplierResultDto> ResultDtos =
		 * supplyShoppeMapper .findShoppeSupplierInfoByParam(dto); if
		 * (ResultDtos == null || ResultDtos.size() != Constants.PUBLIC_1) {
		 * throw new
		 * BleException(ErrorCode.ADD_PRODUCT_SUPPLY_SHOPPE_ERROR.getErrorCode
		 * (), ErrorCode.ADD_PRODUCT_SUPPLY_SHOPPE_ERROR.getMemo()); } }
		 */
		// 电商-虚库时判断供应商专柜关系
		if (dataDto.getStockMode().equals(Constants.PUBLIC_2)) {// 虚库
			PcmShoppeSupplierQueryDto dto = new PcmShoppeSupplierQueryDto();
			dto.setShoppeSid(String.valueOf(shoppeList.get(0).getSid()));// 专柜表SID
			dto.setSupplySid(String.valueOf(supplyInfoList.get(0).getSid()));// 供应商表SID
			List<PcmShoppeSupplierResultDto> ResultDtos = supplyShoppeMapper
					.findShoppeSupplierInfoByParam(dto);
			if (ResultDtos == null || ResultDtos.size() != Constants.PUBLIC_1) {
				throw new BleException(ErrorCode.ADD_PRODUCT_SUPPLY_SHOPPE_ERROR.getErrorCode(),
						ErrorCode.ADD_PRODUCT_SUPPLY_SHOPPE_ERROR.getMemo());
			}
		}
		if (supplyInfoList.get(0).getApartOrder().equals("1")) {// 判断拆单标识
			PcmShoppeSupplierQueryDto dto = new PcmShoppeSupplierQueryDto();
			dto.setShoppeSid(String.valueOf(shoppeList.get(0).getSid()));// 专柜表SID
			dto.setSupplySid(String.valueOf(supplyInfoList.get(0).getSid()));// 供应商表SID
			List<PcmShoppeSupplierResultDto> ResultDtos = supplyShoppeMapper
					.findShoppeSupplierInfoByParam(dto);
			if (ResultDtos == null || ResultDtos.size() != Constants.PUBLIC_1) {
				throw new BleException(ErrorCode.ADD_PRODUCT_SUPPLY_SHOPPE_ERROR.getErrorCode(),
						ErrorCode.ADD_PRODUCT_SUPPLY_SHOPPE_ERROR.getMemo());
			}
		}
		/*
		 * // 判断要约经营方式 map.clear(); map.put("contractCode",
		 * dataDto.getOfferNumber()); List<PcmContractLog> PcmContractLogs =
		 * contractLogMapper.selectListByParam(map); if (PcmContractLogs == null
		 * || PcmContractLogs.size() != 1) { throw new
		 * BleException(ErrorCode.CONTRACT_VALID_ERROR.getErrorCode(),
		 * ErrorCode.CONTRACT_VALID_ERROR.getMemo()); } if
		 * (PcmContractLogs.get(0).getManageType() !=
		 * Integer.valueOf(dataDto.getOperateMode())) { throw new
		 * BleException(ErrorCode.SAPERP_PCM_ERROR_CONTRACTLOG.getErrorCode(),
		 * ErrorCode.SAPERP_PCM_ERROR_CONTRACTLOG.getMemo()); }
		 */
		// 品牌数据校验
		map.clear();
		map.put("brandSid", dataDto.getBrandCode());
		map.put("shopType", org.getStoreType());// 门店类型
		map.put("brandType", 1); // 品牌类型（门店）
		map.put("status", 0); // 状态为有效
		PcmBrand brand = null;
		List<PcmBrand> brandList = brandMapper.selectListByParam(map);// 品牌信息
		if (brandList == null || brandList.size() != 1) {
			// 门店品牌不存在
			throw new BleException(ErrorCode.BRANDGROUP_NULL.getErrorCode(),
					ErrorCode.BRANDGROUP_NULL.getMemo());
		} else {
			// 门店品牌查询到了，查询集团品牌
			brand = brandList.get(0); // 门店品牌信息
			map.clear();
			map.put("sid", brandList.get(Constants.PUBLIC_0).getParentSid());// 集团品牌SID
			map.put("brandType", 0); // 品牌类型（集团）
			map.put("status", 0); // 状态为有效
			brandList = brandMapper.selectListByParam(map);
			if (brandList == null || brandList.size() != 1) {
				throw new BleException(ErrorCode.BRANDGROUP_NULL.getErrorCode(),
						ErrorCode.BRANDGROUP_NULL.getMemo());
			}
			// 查询门店-门店品牌关系
			map.clear();
			map.put("brandSid", brand.getSid());
			map.put("shopSid", org.getSid());
			List<PcmShopBrandRelation> relations = psbrMapper.selectListByParam(map);
			if (relations == null || relations.size() != 1) {
				throw new BleException(ErrorCode.BRAND_SHOP_RELATION_IS_NULL.getErrorCode(),
						ErrorCode.BRAND_SHOP_RELATION_IS_NULL.getMemo());
			}
		}
		PcmShoppeProduct psp = new PcmShoppeProduct();
		psp.setShoppeProSid(shoppeProSid);// 专柜商品编码
		String shoppeProName = brand.getBrandName() + dataDto.getProductDesc()
				+ dataDto.getColorCode() + dataDto.getSizeCode();
		/*
		 * List<String> paras = new ArrayList<String>();
		 * paras.add(brand.getBrandName()); paras.add(dataDto.getProductDesc());
		 * paras.add(dataDto.getColorCode()); paras.add(dataDto.getSizeCode());
		 * String shoppeProName =
		 * generateStandardName(paras,Constants.SEPARATOR);
		 */
		psp.setShoppeProName(shoppeProName);// 专柜商品名称
		psp.setShoppeSid(shoppeList.get(0).getSid()); // 专柜SID
		// psp.setSupplySid(supplyInfoList.get(0).getSid().toString());//供应商sid
		psp.setSupplyProductCode(dataDto.getSupplyInnerCode());// 供应商商品编码
		psp.setField3(dataDto.getModelNum()); // 货号
		psp.setOriginalPrice(new BigDecimal(dataDto.getMarketPrice()));// 原价吊牌价
		psp.setIsCod(Integer.valueOf(dataDto.getIsCOD()));// 是否货到付款
		psp.setIsPacking(Integer.valueOf(dataDto.getIsPacking())); // 是否可包装
		psp.setSaleStatus(Integer.valueOf(dataDto.getIsSale()));// 可售状态
		psp.setInputTax(new BigDecimal(dataDto.getInputTax()));// 进项税
		psp.setOutputTax(new BigDecimal(dataDto.getOutputTax()));// 销项税
		psp.setOriginLand2(dataDto.getCountryOfOrigin());// 原产地
		int impact = shoppeProMapper.updateByCodeSelective(psp);
		if (impact != 1) {
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_UPDATESHOPPRODUCT.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_UPDATESHOPPRODUCT.getMemo());
		}
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("shoppeProSid", shoppeProSid);
		List<PcmShoppeProduct> result = shoppeProMapper.selectListByParam(paraMap);
		// 获取扩展表sid
		Long sid = dsProMapper.getSidByShopProSid(result.get(0).getSid());
		// 修改扩展表信息
		PcmShoppeProductExtend pspe = new PcmShoppeProductExtend();
		pspe.setXxhcFlag(extendDto.getXxhcFlag());// 先销后采标签
		pspe.setIsCard(extendDto.getIsCard());// 是否可贺卡
		pspe.setOriginCountry(extendDto.getOriginCountry());// 原产国
		pspe.setShoppeProSid(result.get(0).getSid().toString());// 专柜商品sid
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String newtime = sdf.format(date);
		pspe.setField10(newtime);// 修改时间，当前时间
		pspe.setSid(sid);// 扩展表sid
		dsProMapper.updateByPrimaryKeySelective(pspe);
		return result.get(0);
	}

	/**
	 * 商品属性更新参数校验
	 * 
	 * @Methods Name NotEmptyCheck
	 * @Create In 2015-12-23 By wangc
	 * @param pcmShoppeProduct
	 *            void
	 */
	public void NotEmptyCheck(PullDataDto dataDto) {
		/*
		 * // 供应商编码 if (!StringUtils.isNotBlank(dataDto.getSupplierCode()) ||
		 * !dataDto.getSupplierCode().matches("\\w+")) {
		 * logger.info("供应商编码参数不符合规则"); throw new
		 * BleException(ErrorCode.SAPERP_PCM_ERROR_SUPPLIERCODE.getErrorCode(),
		 * ErrorCode.SAPERP_PCM_ERROR_SUPPLIERCODE.getMemo()); }
		 */
		// 供应商编码-非空
		if (!StringUtils.isNotBlank(dataDto.getSupplierCode())) {
			logger.info("供应商编码参数不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_SUPPLIERCODE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_SUPPLIERCODE.getMemo());
		}
		// 供应商编码-规则
		if (!dataDto.getSupplierCode().matches("\\w+")) {
			logger.info("供应商编码只能包含大小写字母、数字及下划线");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_SUPPLIERCODE1.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_SUPPLIERCODE1.getMemo());
		}
		/*
		 * // 专柜编码 if (!StringUtils.isNotBlank(dataDto.getCounterCode()) ||
		 * !dataDto.getCounterCode().matches("\\w+")) {
		 * logger.info("专柜编码参数不符合规则"); throw new
		 * BleException(ErrorCode.SAPERP_PCM_ERROR_COUNTERCODE.getErrorCode(),
		 * ErrorCode.SAPERP_PCM_ERROR_COUNTERCODE.getMemo()); }
		 */
		// 专柜编码-非空
		if (!StringUtils.isNotBlank(dataDto.getCounterCode())) {
			logger.info("专柜编码参数不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_COUNTERCODE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_COUNTERCODE.getMemo());
		}
		// 专柜编码-规则
		if (!dataDto.getCounterCode().matches("\\w+")) {
			logger.info("专柜编码只能包含大小写字母、数字及下划线");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_COUNTERCODE1.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_COUNTERCODE1.getMemo());
		}
		/*
		 * // 经营方式 if (!StringUtils.isNotBlank(dataDto.getOperateMode()) ||
		 * !dataDto.getOperateMode().matches("[012345]")) {
		 * logger.info("经营方式参数不符合规则"); throw new
		 * BleException(ErrorCode.SAPERP_PCM_ERROR_OPERATEMODE.getErrorCode(),
		 * ErrorCode.SAPERP_PCM_ERROR_OPERATEMODE.getMemo()); }
		 */
		// 经营方式-非空
		if (!StringUtils.isNotBlank(dataDto.getOperateMode())) {
			logger.info("经营方式参数不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_OPERATEMODE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_OPERATEMODE.getMemo());
		}
		// 经营方式-规则
		if (!dataDto.getOperateMode().matches("[01234]")) {
			logger.info("经营方式应为0-4的数字(0经销1代销2联营3平台服务4租赁)");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_OPERATEMODE1.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_OPERATEMODE1.getMemo());
		}
		// 验证货号
		if ("2".equals(dataDto.getOperateMode()) && StringUtils.isBlank(dataDto.getModelNum())) {// 联营
			logger.info("电商联营货号不能为空");
			throw new BleException(ErrorCode.PRO_MODELNUM_NULL.getErrorCode(),
					ErrorCode.PRO_MODELNUM_NULL.getMemo());
		}
		// 吊牌价
		if (!StringUtils.isNotBlank(dataDto.getMarketPrice())) {
			logger.info("吊牌价不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_MARKETPRICE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_MARKETPRICE.getMemo());
		} else if (!dataDto.getMarketPrice().matches("\\d+.{0,1}\\d*")) {
			logger.info("吊牌价参数规则不正确");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_MARKETPRICE1.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_MARKETPRICE1.getMemo());
		}
		// 进项税
		if (StringUtils.isNotBlank(dataDto.getInputTax())) {
			String temp = dataDto.getInputTax();
			if (temp.indexOf("%") != -1) {
				temp = temp.substring(0, temp.length() - 1);
				Double db = Double.parseDouble(temp);
				db = (double) (db / 100);
				dataDto.setInputTax(String.valueOf(db));
			}
			if (!dataDto.getInputTax().matches("\\d+.{0,1}\\d*")) {
				logger.info("进项税参数不符合规则");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_INPUTTAX.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_INPUTTAX.getMemo());
			}
		}
		// 销项税
		if (StringUtils.isNotBlank(dataDto.getOutputTax())) {
			String temp = dataDto.getOutputTax();
			if (temp.indexOf("%") != -1) {
				temp = temp.substring(0, temp.length() - 1);
				Double db = Double.parseDouble(temp);
				db = (double) (db / 100);
				dataDto.setOutputTax(String.valueOf(db));
			}
			if (!dataDto.getOutputTax().matches("\\d+.{0,1}\\d*")) {
				logger.info("销项税参数不符合规则");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_OUTPUTTAX.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_OUTPUTTAX.getMemo());
			}
		}
		// 消费税
		/*
		 * if (StringUtils.isNotBlank(dataDto.getConsumptionTax())) { String
		 * temp = dataDto.getConsumptionTax(); if (temp.indexOf("%") != -1) {
		 * temp = temp.substring(0, temp.length() - 1); Double db =
		 * Double.parseDouble(temp); db = (double) (db / 100);
		 * dataDto.setConsumptionTax(String.valueOf(db)); } if
		 * (!dataDto.getConsumptionTax().matches("\\d+.{0,1}\\d*")) {
		 * logger.info("消费税参数不符合规则"); throw new
		 * BleException(ErrorCode.SAPERP_PCM_ERROR_TAX.getErrorCode(),
		 * ErrorCode.SAPERP_PCM_ERROR_TAX.getMemo()); } }
		 */
		// 要约号
		if (!StringUtils.isNotBlank(dataDto.getOfferNumber())) {
			logger.info("要约号参数不符合规则");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_OFFERNUMBER.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_OFFERNUMBER.getMemo());
		}
	}

	/**
	 * 生成标准品名
	 * 
	 * @Methods Name generateStandardName
	 * @Create In 2016-3-2 By wangc
	 * @param paras
	 * @param cons
	 * @return String
	 */
	public String generateStandardName(List<String> paras, String cons) {
		String result = "";
		for (int i = 0; i < paras.size(); i++) {
			String str = paras.get(i).toString();
			if (i == paras.size() - 1) {
				result = result + str;
			} else {
				result = result + str + cons;
			}
		}
		if (result != null) {
			result = result.trim();
		}
		return result;
	}

}

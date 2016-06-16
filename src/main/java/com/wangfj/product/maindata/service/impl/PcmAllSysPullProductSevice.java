/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.maindata.service.implPcmAllSysPullProductSevice.java
 * @Create By wangc
 * @Create In 2016年6月14日 下午4:39:01
 * TODO
 */
package com.wangfj.product.maindata.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.RealLiteral;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.StringUtils;
import com.wangfj.product.brand.domain.entity.PcmBrand;
import com.wangfj.product.brand.domain.entity.PcmShopBrandRelation;
import com.wangfj.product.brand.persistence.PcmBrandMapper;
import com.wangfj.product.brand.persistence.PcmShopBrandRelationMapper;
import com.wangfj.product.category.domain.entity.PcmCategory;
import com.wangfj.product.category.persistence.PcmCategoryMapper;
import com.wangfj.product.category.persistence.PcmProductCategoryMapper;
import com.wangfj.product.category.persistence.PcmProductParametersMapper;
import com.wangfj.product.maindata.domain.entity.PcmBarcode;
import com.wangfj.product.maindata.domain.entity.PcmColorDict;
import com.wangfj.product.maindata.domain.entity.PcmContractLog;
import com.wangfj.product.maindata.domain.entity.PcmErpProduct;
import com.wangfj.product.maindata.domain.entity.PcmProDetail;
import com.wangfj.product.maindata.domain.entity.PcmProduct;
import com.wangfj.product.maindata.domain.entity.PcmSeasonDict;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProduct;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProductExtend;
import com.wangfj.product.maindata.domain.vo.BarcodeDto;
import com.wangfj.product.maindata.domain.vo.CreateShoppePro;
import com.wangfj.product.maindata.domain.vo.CreateSkuDto;
import com.wangfj.product.maindata.domain.vo.CreateSpuDto;
import com.wangfj.product.maindata.domain.vo.PcmAllSysPullDataDto;
import com.wangfj.product.maindata.domain.vo.PcmRelateInfoDto;
import com.wangfj.product.maindata.domain.vo.ValidProDetailDto;
import com.wangfj.product.maindata.domain.vo.ValidShoppeProDto;
import com.wangfj.product.maindata.persistence.PcmBarcodeMapper;
import com.wangfj.product.maindata.persistence.PcmColorDictMapper;
import com.wangfj.product.maindata.persistence.PcmContractLogMapper;
import com.wangfj.product.maindata.persistence.PcmErpProductMapper;
import com.wangfj.product.maindata.persistence.PcmProDetailMapper;
import com.wangfj.product.maindata.persistence.PcmProInputMapper;
import com.wangfj.product.maindata.persistence.PcmProductMapper;
import com.wangfj.product.maindata.persistence.PcmSaleUnitDictMapper;
import com.wangfj.product.maindata.persistence.PcmSeasonDictMapper;
import com.wangfj.product.maindata.persistence.PcmShoppeProductMapper;
import com.wangfj.product.maindata.persistence.PcmStanDictMapper;
import com.wangfj.product.maindata.service.intf.IPcmAllSysPullProductSevice;
import com.wangfj.product.maindata.service.intf.IPcmCreateProductService;
import com.wangfj.product.maindata.service.intf.IPcmStanDictService;
import com.wangfj.product.maindata.service.intf.IValidProDrtailService;
import com.wangfj.product.organization.domain.entity.PcmOrganization;
import com.wangfj.product.organization.domain.entity.PcmShoppe;
import com.wangfj.product.organization.domain.vo.PcmShoppeResultDto;
import com.wangfj.product.organization.domain.vo.SelectPcmShoppeDto;
import com.wangfj.product.organization.persistence.PcmOrganizationMapper;
import com.wangfj.product.organization.persistence.PcmShoppeMapper;
import com.wangfj.product.organization.service.intf.IPcmShoppeService;
import com.wangfj.product.price.persistence.PcmOriginalPriceLogMapper;
import com.wangfj.product.price.persistence.PcmOriginalPriceMapper;
import com.wangfj.product.price.persistence.PcmPriceMapper;
import com.wangfj.product.price.service.intf.IPcmPriceService;
import com.wangfj.product.stocks.domain.entity.PcmStock;
import com.wangfj.product.stocks.domain.vo.PcmProductStockInfoDto;
import com.wangfj.product.stocks.domain.vo.PcmStockDto;
import com.wangfj.product.stocks.persistence.PcmStockMapper;
import com.wangfj.product.stocks.service.intf.IPcmStockChangeRecordService;
import com.wangfj.product.stocks.service.intf.IPcmStockService;
import com.wangfj.product.supplier.domain.entity.PcmShoppeProductSupply;
import com.wangfj.product.supplier.domain.entity.PcmSupplyInfo;
import com.wangfj.product.supplier.domain.vo.PcmShoppeSupplierQueryDto;
import com.wangfj.product.supplier.domain.vo.PcmShoppeSupplierResultDto;
import com.wangfj.product.supplier.persistence.PcmShoppeProductSupplyMapper;
import com.wangfj.product.supplier.persistence.PcmSupplyInfoMapper;
import com.wangfj.product.supplier.persistence.PcmSupplyShoppeRelationMapper;
import com.wangfj.util.Constants;

/**
 * @Class Name PcmAllSysPullProductSevice
 * @Author wangc
 * @Create In 2016年6月14日
 */
@Service
public class PcmAllSysPullProductSevice implements IPcmAllSysPullProductSevice {

	private static final Logger logger = LoggerFactory.getLogger(ValidProductServiceImpl.class);

	@Autowired
	private IPcmShoppeService pcmShoppeService;
	@Autowired
	private IPcmCreateProductService createService;
	@Autowired
	private IValidProDrtailService validService;
	@Autowired
	private PcmProductMapper pcmProductMapper;
	@Autowired
	private PcmProInputMapper inputMapper;
	@Autowired
	private PcmProDetailMapper pcmProDetailMapper;
	@Autowired
	private PcmShoppeProductMapper pcmShoppeProductMapper;
	@Autowired
	private PcmShoppeMapper pcmShoppeMapper;
	@Autowired
	private PcmSupplyInfoMapper supplyInfoMapper;
	// 品牌表
	@Autowired
	private PcmBrandMapper brandMapper;
	// 工业分类
	@Autowired
	private PcmCategoryMapper categoryMapper;
	// 季节
	@Autowired
	private PcmSeasonDictMapper seasonDictMapper;
	// SPU与工业分类关联表
	@Autowired
	private PcmProductCategoryMapper productCategoryMapper;
	// 规格字典service
	@Autowired
	private IPcmStanDictService stanDictService;
	// 规格字典表
	@Autowired
	private PcmStanDictMapper stanDictMapper;
	// 价格
	@Autowired
	private PcmPriceMapper priceMapper;
	// 价格service
	@Autowired
	private IPcmPriceService priceService;
	// 大码表
	@Autowired
	private PcmErpProductMapper erpProductMapper;
	// 条码表
	@Autowired
	private PcmBarcodeMapper barcodeMapper;
	// 原价表
	@Autowired
	private PcmOriginalPriceMapper originalPriceMapper;
	// 原价表日志表
	@Autowired
	private PcmOriginalPriceLogMapper originalPriceLogMapper;
	// 组织机构
	@Autowired
	private PcmOrganizationMapper organizationMapper;
	// 销售单位字典表
	@Autowired
	private PcmSaleUnitDictMapper saleUnitMapper;
	// 色系字典表
	@Autowired
	private PcmColorDictMapper colorDictMapper;
	// 产品属性表
	@Autowired
	PcmProductParametersMapper ppMapper;
	// 库存表
	@Autowired
	PcmStockMapper stockMapper;
	// 库存表
	@Autowired
	IPcmStockService stockService;
	@Autowired
	IPcmStockChangeRecordService scrsService;
	// 一品多供应商关联表
	@Autowired
	PcmShoppeProductSupplyMapper pspsMapper;
	// 门店-门店品牌关系表
	@Autowired
	PcmShopBrandRelationMapper psbrMapper;
	// 合同表
	@Autowired
	private PcmContractLogMapper contractLogMapper;
	// 专柜供应商关系表
	@Autowired
	private PcmSupplyShoppeRelationMapper supplyShoppeMapper;
	
	
	/**
	 * 多系统商品上传
	 */
	@Override
	@Transactional
	public PcmShoppeProduct allSysSaveProduct(PcmAllSysPullDataDto dataDto,PcmShoppeProductExtend extendDto) throws BleException {
		logger.info("start allSysSaveProduct()");
		validAllSysPullData(dataDto);//多系统商品上传公共字段非空校验
		if("PIS".equals(dataDto.getSource())){//导入终端上传
			validPISData(dataDto);
		}else if("SAP".equals(dataDto.getSource())){
			validSAPData(dataDto);
		}if("SUP".equals(dataDto.getSource())){
			validPISData(dataDto);
		}
		PcmRelateInfoDto relateValidate = relateValidate(dataDto);//数据关联校验
		CreateShoppePro cProDto = createShoPro(dataDto,relateValidate);//创建专柜商品DTo
		CreateSkuDto cSkuDto = createSkuDto(dataDto, relateValidate);//创建skuDto
		PcmShoppeProductExtend dsPro = null;
		if(!"SAP".equals(dataDto.getSource())){//非SAP商品 创建扩展表DTO
			dsPro = createExtendDto(dataDto, cProDto);//创建扩展表DTO
		}else{//电商商品controller已创建扩展表DTO
			dsPro = extendDto;
		}
		PcmShoppeProduct shoppePro = null;//专柜商品添加返回结果封装
		ValidProDetailDto validSpuDto = createValidSpuDto(dataDto,relateValidate);//封装SPU判重实体
		PcmProduct pro = validService.validSpuBh(validSpuDto);//SPU判重
		Long spuFlag = 0l;
		Long skuFlag = 0l;
		if(pro !=null){//SPU存在
			validSpuDto.setProductSid(String.valueOf(pro.getSid()));// 产品表SID
			List<PcmProDetail> proDList = validService.validSku(validSpuDto);//SKU判重
			if (proDList != null && proDList.size() == 1){//SKU存在
				PcmProDetail proD = proDList.get(0);
				ValidShoppeProDto validShoppeProdto = createValidShoPro(dataDto, relateValidate, proDList);//封装专柜商品判重实体
				if (dataDto.getOperateMode().equals(
						String.valueOf(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3))){//联营时专柜商品判重
					PcmShoppeProduct shopp = validService.validShoppeProBh(validShoppeProdto);
					if (shopp != null) {// 专柜商品存在
						throw new BleException(ErrorCode.SHOPP_IS_EXIST.getErrorCode(),
								ErrorCode.SHOPP_IS_EXIST.getMemo());
					}else{//专柜商品不存在,创建专柜商品
						cProDto.setProductDetailSid(String.valueOf(proD.getSid()));// sku表SID
						if (!StringUtils.isNotBlank(cProDto.getShoppeProName())) {// 专柜商品名为空时
							if("SAP".equals(dataDto.getSource())){//----------------------------------------电商SAP上传专柜商品名为空时
								PcmBrand brand = relateValidate.getBrand();//品牌信息
								String shoppeProName = brand.getBrandName() + dataDto.getProductDesc()
								+ dataDto.getColorCode() + dataDto.getSizeCode();
								cProDto.setShoppeProName(shoppeProName);// 专柜商品名称
								cProDto.setShoppeProAlias(proD.getProDetailName());
							}else{
								cProDto.setShoppeProName(proD.getProDetailName());
							}
						}
						shoppePro = insertShoPro(cProDto, relateValidate, dataDto, dsPro);//创建专柜商品
					}
				}else{
					//非联营专柜商品判重
					PcmShoppeProduct shopp = validService.validShoppePro(validShoppeProdto);
					if(shopp != null){//专柜商品存在  判断是否存在一品多商关系
						List<PcmSupplyInfo> supplyInfoList = relateValidate.getSupplyInfoList();//供应商信息
						PcmOrganization org = relateValidate.getOrg();//门店信息
						PcmShoppeProductSupply psps = new PcmShoppeProductSupply();
						psps.setShoppeProductSid((shopp.getSid()));// 专柜商品SID
						psps.setSupplySid((supplyInfoList.get(0).getSid()));// 供应商SID
						psps.setStatus(0);
						List<PcmShoppeProductSupply> pspsList = pspsMapper.selectListByParam(psps);
						if (pspsList != null && pspsList.size() > 0) {
							// 一品多供应商关系已存在
							throw new BleException(ErrorCode.SHOPP_IS_EXIST.getErrorCode(),
									ErrorCode.SHOPP_IS_EXIST.getMemo());
						} else {
							// 一品多供应商关系不存在--添加关系
							psps.setShopSid(org.getOrganizationCode());//一品多商加门店编码
							int res = pspsMapper.insertSelective(psps);
							if (res != 1) {
								throw new BleException(
										ErrorCode.SHOPPEPRODUCT_SUPPLY_RELATION_ERROR
												.getErrorCode(),
										ErrorCode.SHOPPEPRODUCT_SUPPLY_RELATION_ERROR.getMemo());
							}
						}
						shoppePro = shopp;
					}else{//专柜商品不存在  创建专柜商品
						cProDto.setProductDetailSid(String.valueOf(proD.getSid()));// sku表SID
						if (!StringUtils.isNotBlank(cProDto.getShoppeProName())) {// 专柜商品名为空时
							if("SAP".equals(dataDto.getSource())){//----------------------------------------电商SAP上传专柜商品名为空时
								PcmBrand brand = relateValidate.getBrand();//品牌信息
								String shoppeProName = brand.getBrandName() + dataDto.getProductDesc()
								+ dataDto.getColorCode() + dataDto.getSizeCode();
								cProDto.setShoppeProName(shoppeProName);// 专柜商品名称
								cProDto.setShoppeProAlias(proD.getProDetailName());//简称=SKU标准品名
							}else{
								cProDto.setShoppeProName(proD.getProDetailName());
							}
						}
						shoppePro = insertShoPro(cProDto, relateValidate, dataDto, dsPro);//创建专柜商品
					}
				}
			}else if (proDList != null && proDList.size() == 0){//SKU不存在 创建SKU-专柜商品
				cSkuDto.setProductSid(String.valueOf(pro.getSid()));// 产品表SID SPU
				cSkuDto.setProductName(pro.getProductName());
				PcmProDetail sku = createService.insertSKU(cSkuDto);//创建SKU
				skuFlag = sku.getSid();
				cProDto.setProductDetailSid(String.valueOf(sku.getSid()));
				if (!StringUtils.isNotBlank(cProDto.getShoppeProName())) {// 专柜商品名为空
					if("SAP".equals(dataDto.getSource())){//----------------------------------------电商SAP上传专柜商品名为空时
						PcmBrand brand = relateValidate.getBrand();//品牌信息
						String shoppeProName = brand.getBrandName() + dataDto.getProductDesc()
						+ dataDto.getColorCode() + dataDto.getSizeCode();
						cProDto.setShoppeProName(shoppeProName);// 专柜商品名称
						//cProDto.setShoppeProAlias(sku.getProDetailName());
					}else{
						cProDto.setShoppeProName(sku.getProDetailName());
					}
				}
				shoppePro = insertShoPro(cProDto, relateValidate, dataDto, dsPro);//创建专柜商品
				if("SAP".equals(dataDto.getSource())){//电商SAP新加SKU和专柜商品时, 初始化库存,  在SKU基础上加专柜上时不添加库存 ?????
					if (StringUtils.isNotBlank(dataDto.getInventory())) {
						saveInventory(shoppePro.getShoppeProSid(), dataDto.getInventory(), dataDto.getSource(),
								dataDto.getEntryNumber());
					} else {
						saveInventory(shoppePro.getShoppeProSid(), "0", dataDto.getSource(),
								dataDto.getEntryNumber());
					}
				}
			}else {
				throw new BleException(ErrorCode.SKU_IS_EXIST1.getErrorCode(),
						ErrorCode.SKU_IS_EXIST1.getMemo());
			}
		}else{//SPU不存在 创建SPU-SKu-专柜商品
			CreateSpuDto cSpuDto = createSpuDto(dataDto, relateValidate);//创建SPU实体
			PcmProduct spu = createService.insertSPU(cSpuDto);//插入SPU
			spuFlag = spu.getSid();
			cSkuDto.setProductSid(String.valueOf(spu.getSid()));
			cSkuDto.setProductName(spu.getProductName());
			PcmProDetail sku = createService.insertSKU(cSkuDto);//插入SKU
			skuFlag = sku.getSid();
			cProDto.setProductDetailSid(String.valueOf(sku.getSid()));
			if (!StringUtils.isNotBlank(cProDto.getShoppeProName())) {// 专柜商品名为空
				if("SAP".equals(dataDto.getSource())){
					PcmBrand brand = relateValidate.getBrand();//品牌信息
					String shoppeProName = brand.getBrandName() + dataDto.getProductDesc()
					+ dataDto.getColorCode() + dataDto.getSizeCode();
					cProDto.setShoppeProName(shoppeProName);// 专柜商品名称
				}
				cProDto.setShoppeProName(sku.getProDetailName());
			}
			shoppePro = insertShoPro(cProDto, relateValidate, dataDto, dsPro);//创建专柜商品
			if("SAP".equals(dataDto.getSource())){//电商SAP新加SKU和专柜商品时, 初始化库存,  在SKU基础上加专柜上时不添加库存 ?????
				if (StringUtils.isNotBlank(dataDto.getInventory())) {
					saveInventory(shoppePro.getShoppeProSid(), dataDto.getInventory(), dataDto.getSource(),
							dataDto.getEntryNumber());
				} else {
					saveInventory(shoppePro.getShoppeProSid(), "0", dataDto.getSource(),
							dataDto.getEntryNumber());
				}
			}
		}
		logger.info("end allSysSaveProduct()");
		if (shoppePro != null) {
			shoppePro.setPackUnitDictSid(spuFlag);
			shoppePro.setMeasureUnitDictSid(skuFlag);
		}
		return shoppePro;
	}
	/**
	 * 创建SPU实体
	 * @Methods Name createSpuDto
	 * @Create In 2016年6月15日 By wangc
	 * @param dataDto
	 * @param relateInfo
	 * @return CreateSpuDto
	 */
	private CreateSpuDto createSpuDto(PcmAllSysPullDataDto dataDto,PcmRelateInfoDto relateInfo){
		List<PcmBrand> brandList = relateInfo.getBrandList();//集团品牌信息
		List<PcmCategory> cateList = relateInfo.getCateList();//工业分类信息
		List<PcmSeasonDict> seasonList = relateInfo.getSeasonList();//季节信息
		CreateSpuDto cSpuDto = new CreateSpuDto();
		cSpuDto.setBrandName(brandList.get(0).getBrandName());// 集团品牌名称
		cSpuDto.setBrandRootSid(String.valueOf(brandList.get(0).getSid()));// 集团品牌表SID
		cSpuDto.setBrandSid(brandList.get(0).getBrandSid());// 集团品牌编码
		cSpuDto.setCategoryGYSid(cateList.get(0).getSid());// 工业分类SID
		cSpuDto.setFlag(Integer.parseInt(dataDto.getType()));// 超市百货标识_0百货_1超市
		if("SAP".equals(dataDto.getSource())){
			cSpuDto.setShortDes(dataDto.getProductDesc());// 商品描述
		}else{
			cSpuDto.setPrimaryAttr(dataDto.getMainAttribute());// 主属性
		}
		cSpuDto.setProductSku(dataDto.getProductNum());// 款号
		if (seasonList != null) {
			cSpuDto.setSeasonCode(String.valueOf(seasonList.get(0).getSid()));// 季节表SID
		}
		if ("男".equals(dataDto.getCrowdUser())) {
			cSpuDto.setSexSid(0);// 适合人群
		}
		if ("女".equals(dataDto.getCrowdUser())) {
			cSpuDto.setSexSid(1);// 适合人群
		}
		if ("中性".equals(dataDto.getCrowdUser())) {
			cSpuDto.setSexSid(2);// 适合人群
		}
		if ("儿童".equals(dataDto.getCrowdUser())) {
			cSpuDto.setSexSid(3);// 适合人群
		}
		cSpuDto.setYearToMarket(dataDto.getYearToMarket());// 上市年份
		return cSpuDto;
	}
	/**
	 * 创建专柜商品
	 * @Methods Name insertShoPro
	 * @Create In 2016年6月15日 By wangc
	 * @param cProDto
	 * @param relateInfo
	 * @param dataDto
	 * @param dsPro
	 * @return PcmShoppeProduct
	 */
	private PcmShoppeProduct insertShoPro(CreateShoppePro cProDto,PcmRelateInfoDto relateInfo,PcmAllSysPullDataDto dataDto,PcmShoppeProductExtend dsPro){
		PcmShoppeProduct shoppePro = new PcmShoppeProduct();
		shoppePro = createService.insertShoppePro(cProDto);
		List<PcmSupplyInfo> supplyInfoList = relateInfo.getSupplyInfoList();//供应商信息
		PcmOrganization org = relateInfo.getOrg();//门店信息
		String source = dataDto.getSource();
		if(!"SAP".equals(source)){//电商SAP上传没有期初库存
			// 写入库存
			if (StringUtils.isNotBlank(dataDto.getInventory())) {
				saveInventory(shoppePro.getShoppeProSid(), dataDto.getInventory(),
						source, dataDto.getEntryNumber());
			} else {
				saveInventory(shoppePro.getShoppeProSid(), "0", source,
						dataDto.getEntryNumber());
			}
		}
		// 写入临时表
		if (StringUtils.isNotBlank(dataDto.getEntryNumber())
				&& StringUtils.isNotBlank(dataDto.getProcurementPersonnelNumber())
				&& StringUtils.isNotBlank(dataDto.getOfferNumber())) {
			createService.insertProductInput(shoppePro.getSid(),
					dataDto.getOfferNumber(), dataDto.getEntryNumber(),
					dataDto.getProcurementPersonnelNumber(), dsPro);
		}
		// 写入一品多供应商关系表
		PcmShoppeProductSupply psps = new PcmShoppeProductSupply();
		psps.setShoppeProductSid((shoppePro.getSid()));// 专柜商品SID
		psps.setSupplySid((supplyInfoList.get(0).getSid()));// 供应商SID
		psps.setShopSid(org.getOrganizationCode());//一品多商加门店编码
		insertShoppeProductSupply(psps);
		return shoppePro;
	}
	/**
	 * 封装专柜商品判重实体
	 * @Methods Name createValidShoPro
	 * @Create In 2016年6月15日 By wangc
	 * @param dataDto
	 * @param relateInfo
	 * @param proDList
	 * @return ValidShoppeProDto
	 */
	private ValidShoppeProDto createValidShoPro(PcmAllSysPullDataDto dataDto,PcmRelateInfoDto relateInfo,List<PcmProDetail> proDList){
		ValidShoppeProDto validShoppeProdto = new ValidShoppeProDto();
		List<PcmShoppe> shoppeList = relateInfo.getShoppeList();//专柜信息
		List<PcmSupplyInfo> supplyInfoList = relateInfo.getSupplyInfoList();//供应商信息
		PcmOrganization org = relateInfo.getOrg();//门店信息
		PcmProDetail proD = proDList.get(0);
		validShoppeProdto.setProductDetailSid(String.valueOf(proD.getSid()));// sku表SID
		validShoppeProdto.setShoppeSid(String.valueOf(shoppeList.get(0).getSid()));// 专柜表SID
		validShoppeProdto.setSupplySid(String.valueOf(supplyInfoList.get(0).getSid()));// 供应商表SID
		if (StringUtils.isNotBlank(dataDto.getStandardBarCode())) {
			validShoppeProdto.setBarcode(dataDto.getStandardBarCode());// 条码
			validShoppeProdto.setStoreCode(org.getOrganizationCode());// 门店编码
		}
		// 判断百货或超市 超市判重加入销售单位
		if (dataDto.getType().equals(String.valueOf(Constants.PUBLIC_1))) {
			validShoppeProdto.setSaleUnitCode(dataDto.getUnitCode());// 销售单位
		}
		return validShoppeProdto;
	}
	/**
	 * 封装SPU判重实体
	 * @Methods Name createValidSpuDto
	 * @Create In 2016年6月15日 By wangc
	 * @param dataDto
	 * @param relateInfo
	 * @return ValidProDetailDto
	 */
	private ValidProDetailDto createValidSpuDto(PcmAllSysPullDataDto dataDto,PcmRelateInfoDto relateInfo){
		// 1.spu验证:
		// 判断超市或百货
		ValidProDetailDto validSpuDto = new ValidProDetailDto();
		List<PcmBrand> brandList = relateInfo.getBrandList();//集团品牌信息
		List<PcmColorDict> colorList = relateInfo.getColorList();//色系信息
		
		validSpuDto.setBrandSid(String.valueOf(brandList.get(0).getBrandSid()));// 集团品牌SID
		validSpuDto.setProStanSid(dataDto.getSizeCode());// 规格/尺码
		if (!dataDto.getType().equals(String.valueOf(Constants.PUBLIC_1))) {// 百货 电商商品
			validSpuDto.setProductSku(dataDto.getProductNum());// 款号
			validSpuDto.setProColorName(dataDto.getColorCode());// 色码
			validSpuDto.setProColorSid(colorList.get(0).getSid().toString());// 色系SID
		} else {// 超市字段
			validSpuDto.setPrimaryAttr(dataDto.getMainAttribute());// 主属性
			validSpuDto.setFeatures(dataDto.getFeatures());// 特性
		}
		return validSpuDto;
	}
	/**
	 * 创建扩展表DTO
	 * 
	 * @Methods Name createExtendDto
	 * @Create In 2016年6月15日 By wangc
	 * @param dataDto
	 * @param cProDto
	 * @return
	 * @throws BleException PcmShoppeProductExtend
	 */
	private PcmShoppeProductExtend createExtendDto(PcmAllSysPullDataDto dataDto,CreateShoppePro cProDto)  throws BleException{
		PcmShoppeProductExtend dsPro = new PcmShoppeProductExtend();
		if (cProDto.getBusinessType() == Constants.PUBLIC_1) {// 联营时，扩展表商品类别为8联营单品
			dsPro.setField1("8");
		}
		if (cProDto.getBusinessType() == Constants.PUBLIC_0) {// 自营时，扩展表商品类别为1自营单品
			dsPro.setField1("1");
		}
		dsPro.setField2(cProDto.getOriginalPrice().toString());// 扩展表原价
		if("2".equals(dataDto.getType())){
			dsPro.setField4(dataDto.getSapProType());//扩展表 电商商品类型
			dsPro.setField5(dataDto.getShelfLife());//总货架寿命
			dsPro.setField6(dataDto.getRemainShelLife());//剩余货架寿命
			dsPro.setField7(dataDto.getField());//统比销
			dsPro.setField8(dataDto.getSupplyOriginLand());//货源地
			dsPro.setField11(dataDto.getPurStatus());//采购状态
			dsPro.setField12(dataDto.getSalesStatus());//销售状态
			dsPro.setZcolor(dataDto.getZcolor());//特性色码
			dsPro.setZsize(dataDto.getZsize());//特性尺码
		}
		return dsPro;
	}
	/**
	 * 创建skuDto
	 * @Methods Name createSkuDto
	 * @Create In 2016年6月15日 By wangc
	 * @param dataDto
	 * @param relateInfo
	 * @return
	 * @throws BleException CreateSkuDto
	 */
	private CreateSkuDto createSkuDto(PcmAllSysPullDataDto dataDto,PcmRelateInfoDto relateInfo) throws BleException{
		CreateSkuDto cSkuDto = new CreateSkuDto();
		List<PcmCategory> cateList = relateInfo.getCateList();//工业分类信息
		List<PcmBrand> brandList = relateInfo.getBrandList();//集团品牌信息
		List<PcmColorDict> colorList = relateInfo.getColorList();//色系信息
		PcmBrand brand = relateInfo.getBrand();//门店品牌信息
		if(!"SAP".equals(dataDto.getSource())){//非SAP上传商品
			cSkuDto.setCategoryGYSid(cateList.get(0).getSid());// 工业分类编码
			cSkuDto.setFeatures(dataDto.getFeatures());// 特性
		}
		cSkuDto.setBrandGroupCode(brandList.get(0).getBrandSid());// 集团品牌编码
		cSkuDto.setBrandShopCode(brand.getBrandSid());// 门店品牌编码
		cSkuDto.setFlag(Integer.parseInt(dataDto.getType()));// 超市百货标记_0百货_1超市
		cSkuDto.setProColorAlias(dataDto.getColorName());// 色码名称
		cSkuDto.setProColorName(dataDto.getColorCode());// 色码
		if (colorList != null) {
			cSkuDto.setProColorSid(colorList.get(0).getSid());// 色系表SID
		}
		cSkuDto.setProStanName(dataDto.getSizeCode());// 规格/尺码名称
		cSkuDto.setProStanSid(dataDto.getSizeCode());// 规格/尺码
		cSkuDto.setProType(1);
		return cSkuDto;
	}
	/**
	 * 创建专柜商品Dto
	 * @Methods Name createShoPro
	 * @Create In 2016年6月15日 By wangc
	 * @param dataDto
	 * @return
	 * @throws BleException CreateShoppePro
	 */
	private CreateShoppePro createShoPro(PcmAllSysPullDataDto dataDto,PcmRelateInfoDto relateInfo) throws BleException{
		CreateShoppePro cProDto = new CreateShoppePro();
		List<PcmErpProduct> erpList =  relateInfo.getErpList();//扣率码信息
		List<PcmSupplyInfo> supplyInfoList = relateInfo.getSupplyInfoList();//供应商信息
		PcmOrganization org = relateInfo.getOrg();//门店信息
		List<PcmShoppe> shoppeList = relateInfo.getShoppeList();//专柜信息
		List<PcmCategory> tjcateList = relateInfo.getCateList();//统计分类信息
		PcmBrand brand = relateInfo.getBrand();//门店品牌信息
		if(!"SAP".equals(dataDto.getSource())){//非电商SAP上传商品
			cProDto.setBrandSid(String.valueOf(brand.getSid()));
			// 判断是否联营
			if (dataDto.getOperateMode()
					.equals(String.valueOf(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3))) {// 联营
				cProDto.setBusinessType(Constants.PUBLIC_1);// 自定义标记自营0,联营1
				cProDto.setOrderType(Constants.PUBLIC_4);// 订货类型
				cProDto.setRateCode(dataDto.getErpProductCode());// 扣率码
				cProDto.setStockMode(Constants.PUBLIC_2);// 库存方式//修改 联营为 2XK虚库
				// 供应商SID
				if (dataDto.getType().equals(String.valueOf(Constants.PUBLIC_1))) {// 业态（0百货，1超市）
					cProDto.setInputTax(new BigDecimal(dataDto.getInputTax()));// 进项税
					cProDto.setOutputTax(new BigDecimal(dataDto.getOutputTax()));// 销项税
					cProDto.setPurchasePrice(new BigDecimal(dataDto.getRate_price()));// 扣率/进价
					cProDto.setBuyingPrice(new BigDecimal(dataDto.getPurchasePrice_taxRebate()));// 扣率/含税进价
				} else {
					cProDto.setOutputTax(erpList.get(0).getOutputTax());// 销项税
					cProDto.setSalesTax(erpList.get(0).getSalesTax());// 消费税
					cProDto.setInputTax(erpList.get(0).getInputTax());// 进项税
				}
			} else {// 其他
				cProDto.setBusinessType(Constants.PUBLIC_0);// 自定义标记自营0,联营1
				cProDto.setOrderType(Constants.PUBLIC_5);// 订货类型
				cProDto.setStockMode(Constants.PUBLIC_1);// 库存方式??????1.ZK自库
				cProDto.setInputTax(new BigDecimal(dataDto.getInputTax()));// 进项税
				cProDto.setOutputTax(new BigDecimal(dataDto.getOutputTax()));// 销项税
				cProDto.setPurchasePrice(new BigDecimal(dataDto.getRate_price()));// 扣率/进价
				cProDto.setBuyingPrice(new BigDecimal(dataDto.getPurchasePrice_taxRebate()));// 扣率/含税进价
				cProDto.setField2("WFJ");
			}
			cProDto.setSupplySid(String.valueOf(supplyInfoList.get(0).getSid()));// 供应商SID
			cProDto.setIsStock(Constants.PUBLIC_0);// 是否管库库存 0是
			cProDto.setCategorySid(dataDto.getManageCateGory());// 品牌专柜（管理分类编码）
			cProDto.setDiscountLimit(dataDto.getDiscountLimit());// 折扣底限
			cProDto.setErpproductcode(dataDto.getErpProductCode());// 大码
			cProDto.setFlag(Integer.parseInt(dataDto.getType()));// 百货超市标记,0百货,1超市
			cProDto.setOfferNo(dataDto.getOfferNumber());// 要约号
			cProDto.setOriginalPrice(new BigDecimal(dataDto.getMarketPrice()));// 吊牌价
			cProDto.setOriginLand(dataDto.getPlaceOfOrigin());// 产地
			cProDto.setOriginLand2(dataDto.getCountryOfOrigin());// 原产地
			if (StringUtils.isNotBlank(dataDto.getProcessingType())) {
				cProDto.setProcessType(Integer.parseInt(dataDto.getProcessingType()));// 加工类型
			}
			if (dataDto.getConsumptionTax() != null && dataDto.getConsumptionTax() != ""
					&& dataDto.getConsumptionTax() != "null") {
				cProDto.setSalesTax(new BigDecimal(dataDto.getConsumptionTax()));// 消费税
			}
			cProDto.setSaleUnitCode(dataDto.getUnitCode());// 销售单位
			cProDto.setShopCode(org.getOrganizationCode());// 门店编码
			cProDto.setShoppeCode(dataDto.getCounterCode());// 专柜编码
			cProDto.setShoppeProAlias(dataDto.getProductAbbr());// 商品简称
			if (dataDto.getType().equals(String.valueOf(Constants.PUBLIC_0))) {// 百货
				cProDto.setShoppeProName(dataDto.getProductName());// 专柜商品名称
			} else {// 超市
				cProDto.setShoppeProName(dataDto.getRegisteredTradeName());// 专柜商品名称
			}
			cProDto.setShoppeSid(String.valueOf(shoppeList.get(0).getSid()));// 专柜SID
			// 条码
			if (StringUtils.isNotBlank(dataDto.getStandardBarCode())) {
				List<BarcodeDto> barcodeList = new ArrayList<BarcodeDto>();
				BarcodeDto barcodeDto = new BarcodeDto();
				barcodeDto.setBarcode(dataDto.getStandardBarCode());
				barcodeDto.setType(0);// 标准条码
				barcodeDto.setOriginLand(dataDto.getPlaceOfOrigin());
				barcodeList.add(barcodeDto);
				cProDto.setBarcode(barcodeList);
			} else if (dataDto.getStandardBarCodes() != null
					&& dataDto.getStandardBarCodes().size() != 0) {
				List<BarcodeDto> barcodeList = new ArrayList<BarcodeDto>();
				for (Map<String, Object> ba : dataDto.getStandardBarCodes()) {
					BarcodeDto barcodeDto = new BarcodeDto();
					barcodeDto.setBarcode(ba.get("barcode").toString());// 条码
					barcodeDto.setType(Integer.valueOf(ba.get("barCodeType").toString()));// 条码类型
					barcodeDto.setOriginLand(ba.get("originLand").toString());// 产地
					barcodeList.add(barcodeDto);
				}
				cProDto.setBarcode(barcodeList);
			}
			cProDto.setSupplyCode(dataDto.getSupplierCode());// 供应商编码
			cProDto.setSupplyProductCode(dataDto.getStandardBarCode());// 供应商商品编码??????????
			cProDto.setRemarks(dataDto.getRemarks());// 备注
			if (dataDto.getIsAdjustPrice() != null && dataDto.getIsAdjustPrice().equals(Constants.Y)) {
				cProDto.setIsAdjustPrice(Constants.PUBLIC_0);// 是否允许ERP调价
			} else if (dataDto.getIsAdjustPrice() != null
					&& dataDto.getIsAdjustPrice().equals(Constants.N)) {
				cProDto.setIsAdjustPrice(Constants.PUBLIC_1);// 是否允许ERP调价
			}
			if (dataDto.getIsPromotion() != null && dataDto.getIsPromotion().equals(Constants.Y)) {
				cProDto.setIsPromotion(Constants.PUBLIC_0);// 是否允许ERP促销
			} else if (dataDto.getIsPromotion() != null && dataDto.getIsPromotion().equals(Constants.N)) {
				cProDto.setIsPromotion(Constants.PUBLIC_1);// 是否允许ERP促销
			}
			cProDto.setField3(dataDto.getModelNum());// 货号
			cProDto.setCategoryTJSid(tjcateList.get(0).getSid());// 统计分类SID
			if("2".equals(dataDto.getType())){
				cProDto.setIsCod(Integer.parseInt(dataDto.getIsCOD()));// 是否支持货到付款
				cProDto.setSaleStatus(Integer.parseInt(dataDto.getIsSale()));// 商品可售状态
			}
		}else{//电商SAP上传商品
			// 品牌表SID
			cProDto.setBrandSid(String.valueOf(brand.getSid()));
			cProDto.setStockMode(Integer.parseInt(dataDto.getStockMode()));// 库存方式
			// 判断是否联营
			if (dataDto.getOperateMode()
					.equals(String.valueOf(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3))) {// 联营
				cProDto.setBusinessType(Constants.PUBLIC_1);// 自定义标记自营0,联营1
				cProDto.setOrderType(Constants.PUBLIC_4);// 订货类型
			} else {// 其他
				cProDto.setBusinessType(Constants.PUBLIC_0);// 自定义标记自营0,联营1
				cProDto.setOrderType(Constants.PUBLIC_5);// 订货类型
				cProDto.setField2("WFJ");
			}
			if (StringUtils.isNotBlank(dataDto.getInputTax())) {
				cProDto.setInputTax(new BigDecimal(dataDto.getInputTax()));// 进项税
			}
			if (StringUtils.isNotBlank(dataDto.getOutputTax())) {
				cProDto.setOutputTax(new BigDecimal(dataDto.getOutputTax()));// 销项税
			}
			cProDto.setSupplySid(String.valueOf(supplyInfoList.get(0).getSid()));// 供应商SID
			cProDto.setIsStock(Constants.PUBLIC_0);// 是否管库库存 0是
			cProDto.setCategoryTJSid(tjcateList.get(0).getSid());// 统计分类SID
		    cProDto.setCategorySid(dataDto.getManageCateGory());// 品牌专柜（管理分类编码）
			cProDto.setFlag(Integer.parseInt(dataDto.getType()));// 百货超市标记,0百货,1超市,2电商
			cProDto.setOfferNo(dataDto.getOfferNumber());// 要约号
			cProDto.setSalePrice(new BigDecimal(dataDto.getSalePrice()));// 售价
			cProDto.setOriginalPrice(new BigDecimal(dataDto.getMarketPrice()));// 吊牌价
			cProDto.setOriginLand2(dataDto.getCountryOfOrigin());// 原产地
			if (StringUtils.isNotBlank(dataDto.getProcessingType())) {
				cProDto.setProcessType(Integer.parseInt(dataDto.getProcessingType()));// 加工类型
			}
			if (dataDto.getConsumptionTax() != null && dataDto.getConsumptionTax() != ""
					&& dataDto.getConsumptionTax() != "null") {
				cProDto.setSalesTax(new BigDecimal(dataDto.getConsumptionTax()));// 消费税
			}
			cProDto.setSaleUnitCode(dataDto.getUnitCode());// 销售单位
			cProDto.setShopCode(org.getOrganizationCode());// 门店编码
			cProDto.setShoppeCode(dataDto.getCounterCode());// 专柜编码
			cProDto.setShoppeProAlias(dataDto.getProductAbbr());// 商品简称
			cProDto.setShoppeSid(String.valueOf(shoppeList.get(0).getSid()));// 专柜SID
			// 条码
			if (StringUtils.isNotBlank(dataDto.getStandardBarCode())) {
				List<BarcodeDto> barcodeList = new ArrayList<BarcodeDto>();
				BarcodeDto barcodeDto = new BarcodeDto();
				barcodeDto.setBarcode(dataDto.getStandardBarCode());
				barcodeDto.setType(0);// 标准条码
				barcodeDto.setOriginLand(dataDto.getPlaceOfOrigin());
				barcodeList.add(barcodeDto);
				cProDto.setBarcode(barcodeList);
			}
			cProDto.setSupplyCode(dataDto.getSupplierCode());// 供应商编码
			cProDto.setSupplyProductCode(dataDto.getSupplyInnerCode());// 供应商商品编码
			if (StringUtils.isNotBlank(dataDto.getRate_price())) {
				cProDto.setBuyingPrice(new BigDecimal(dataDto.getRate_price()));// 扣率/进价
			}
			cProDto.setField3(dataDto.getModelNum());// 货号
			cProDto.setIsCod(Integer.parseInt(dataDto.getIsCOD()));// 是否支持货到付款
			cProDto.setSaleStatus(Integer.parseInt(dataDto.getIsSale()));// 商品可售状态
			if (StringUtils.isNotBlank(dataDto.getTmsParam()))
				cProDto.setTmsParam(Integer.parseInt(dataDto.getTmsParam()));// 物流属性
			if (StringUtils.isNotBlank(dataDto.getIsPacking()))
				cProDto.setIsPacking(Integer.parseInt(dataDto.getIsPacking()));// 是否可包装
			cProDto.setField4(dataDto.getProductCode());// 原系统商品编码
		}
		return cProDto;
	}
	/**
	 * 数据关联校验
	 * @Methods Name relateValidate
	 * @Create In 2016年6月14日 By wangc
	 * @param dataDto
	 * @throws BleException void
	 */
	private PcmRelateInfoDto relateValidate(PcmAllSysPullDataDto dataDto) throws BleException{
		PcmRelateInfoDto resultDto = new PcmRelateInfoDto(); //校验返回结果
		String source = dataDto.getSource();//数据源系统
		String type = dataDto.getType();//商品类型 0百货 1超市 2电商
		Map<String, Object> map = new HashMap<String, Object>();
		List<PcmShoppe> shoppeList = new ArrayList<PcmShoppe>();//专柜列表
		if("2".equals(type) && !"SAP".equals(source)){
			//获取专柜                                   导入终端+供应商平台上传 电商商品不传专柜编码,需要用供应商编码和门店编码获取对应专柜
			PcmShoppe pshoppe = new PcmShoppe();
			SelectPcmShoppeDto shoDto = new SelectPcmShoppeDto();
			shoDto.setShopCode(dataDto.getShopCode());//门店编码
			shoDto.setSupplyCode(dataDto.getSupplierCode());//供应商编码
			PcmShoppeResultDto shopResult = pcmShoppeService.findShoppeForSAPERPImport(shoDto);
			if(shopResult == null){
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_SHOPERROR.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_SHOPERROR.getMemo());
			}
			try {
				BeanUtils.copyProperties(pshoppe, shopResult);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			shoppeList.add(pshoppe);
			//电商商品不传要约, 如果要约为空,则对应专柜商品状态为不可售
			if(StringUtils.isBlank(dataDto.getOfferNumber())){
				dataDto.setIsSale(String.valueOf(Constants.PCMSHOPPEPRODECT_SALE_STATUS_NO));
			}
		}else{
			// 专柜数据校验
			map.clear();
			map.put("shoppeCode", dataDto.getCounterCode());
			map.put("shoppeType", "01"); // 查找单品专柜
			shoppeList = pcmShoppeMapper.selectListByParam(map);
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
		}
		resultDto.setShoppeList(shoppeList);//专柜列表
		// 获取门店信息
		PcmOrganization org = organizationMapper.get(shoppeList.get(0).getShopSid());
		if (org == null) {
			throw new BleException(ErrorCode.SHOPPE_STATUS_ERROR.getErrorCode(),
					ErrorCode.SHOPPE_STATUS_ERROR.getMemo());
		}
		resultDto.setOrg(org);//门店信息
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
			resultDto.setBrandList(brandList);//集团品牌信息
			if (brandList == null || brandList.size() != 1) {
				throw new BleException(ErrorCode.BRANDGROUP_NULL.getErrorCode(),
						ErrorCode.BRANDGROUP_NULL.getMemo());
			}
			// 门店品牌及门店关系校验
			map.clear();
			map.put("brandSid", brand.getSid());
			map.put("shopSid", org.getSid());
			List<PcmShopBrandRelation> relations = psbrMapper.selectListByParam(map);
			if (relations == null || relations.size() != 1) {
				throw new BleException(ErrorCode.BRAND_SHOP_RELATION_IS_NULL.getErrorCode(),
						ErrorCode.BRAND_SHOP_RELATION_IS_NULL.getMemo());
			}
		}
		resultDto.setBrand(brand);//门店品牌信息
		//供应商平台与导入终端上传的电商商品可能无要约,如果要约不为空,则验证要约有效性
		if(StringUtils.isNotBlank(dataDto.getOfferNumber())){
			// 验证要约
			map.clear();
			map.put("contractCode", dataDto.getOfferNumber());
			List<PcmContractLog> PcmContractLogs = contractLogMapper.selectListByParam(map);
			if (PcmContractLogs == null || PcmContractLogs.size() != 1) {
				throw new BleException(ErrorCode.CONTRACT_IS_NULL.getErrorCode(),
						ErrorCode.CONTRACT_IS_NULL.getMemo());
			}
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
		//供应商平台上传的电商商品不传经营方式, 非电商SAP来源的电商商品,经营方式与供应商一致
		if("2".equals(dataDto.getType()) && !"SAP".equals(source)){
			//电商不传经营方式, 所传经营方式与对应供应商一致
			if(StringUtils.isBlank(dataDto.getOperateMode())){
				dataDto.setOperateMode(supplyInfoList.get(0).getBusinessPattern().toString());
			}
			
		}else{
			// 判断供应商经营方式
			if (supplyInfoList.get(0).getBusinessPattern() == null
					|| !String.valueOf(supplyInfoList.get(0).getBusinessPattern()).equals(
							dataDto.getOperateMode())) {
				throw new BleException(ErrorCode.SUPPLYINFO_BUSSINESS_ERROR.getErrorCode(),
						ErrorCode.SUPPLYINFO_BUSSINESS_ERROR.getMemo());
			}
		}
		//数据源是SAP的电商商品,根据拆单标识和虚库标识判断是否判断供应商与专柜关系
		if("2".equals(type) && "SAP".equals(source)){
			if ("1".equals(supplyInfoList.get(0).getApartOrder())) {// 判断拆单标识
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
			// 电商-虚库时判断供应商专柜关系
			if (Constants.PUBLIC_2.equals(dataDto.getStockMode())) {// 虚库
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
		}
		resultDto.setSupplyInfoList(supplyInfoList);//供应商信息
		// 统计分类字典校验
		map.clear();
		map.put("categoryCode", dataDto.getFinalClassiFicationCode());
		map.put("categoryType", Constants.STATISTICSCATEGORY);// 分类类型为 2 统计分类
		map.put("isLeaf", Constants.Y);// 是否为叶子节点
		map.put("status", Constants.Y);// 是否启用
		List<PcmCategory> tjcateList = categoryMapper.selectListByParam(map);
		if (tjcateList == null || tjcateList.size() != 1) {
			throw new BleException(ErrorCode.CATEGORY_TJ_NULL.getErrorCode(),
					ErrorCode.CATEGORY_TJ_NULL.getMemo());
		}
		resultDto.setTjcateList(tjcateList);//统计分类信息
		// 工业分类字典校验
		map.clear();
		map.put("categorySid", dataDto.getProdCategoryCode());
		map.put("categoryType", Constants.INDUSTRYTCATEGORY);// 分类类型为 0 工业分类
		map.put("isLeaf", Constants.Y);// 是否为叶子节点
		map.put("status", Constants.Y);// 是否启用
		List<PcmCategory> cateList = categoryMapper.selectListByParam(map);
		if (cateList == null || cateList.size() != 1) {
			throw new BleException(ErrorCode.CATEGORY_GY_NULL.getErrorCode(),
					ErrorCode.CATEGORY_GY_NULL.getMemo());
		}
		resultDto.setCateList(cateList);//工业分类信息
		// 管理分类字典校验
		map.clear();
		map.put("categorySid", dataDto.getManageCateGory());
		map.put("categoryType", Constants.MANAGECATEGORY);// 分类类型为 1管理分类
		map.put("isLeaf", Constants.Y);// 是否为叶子节点
		map.put("status", Constants.Y);// 是否启用
		List<PcmCategory> glcateList = categoryMapper.selectListByParam(map);
		if (glcateList == null || glcateList.size() != 1) {
			throw new BleException(ErrorCode.CATEGORY_GL_NULL.getErrorCode(),
					ErrorCode.CATEGORY_GL_NULL.getMemo());
		}
		resultDto.setGlcateList(glcateList);//管理分类信息
		// 联营百货扣率码数据校验
		List<PcmErpProduct> erpList = null;
		if (StringUtils.isNotBlank(dataDto.getErpProductCode())) {
			PcmErpProduct erpEntity = new PcmErpProduct();
			erpEntity.setProductCode(dataDto.getErpProductCode());
			erpEntity.setStoreCode(org.getOrganizationCode());
			erpList = erpProductMapper.selectListByParam(erpEntity);
		}
		if (dataDto.getType().equals(String.valueOf(Constants.PUBLIC_0))
				&& dataDto.getOperateMode().equals(
						String.valueOf(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3))) {
			
			if (erpList == null || erpList.size() != 1) {
				throw new BleException(ErrorCode.DISCOUNTCODE_NULL.getErrorCode(),
						ErrorCode.DISCOUNTCODE_NULL.getMemo());
			}
			if (!erpList.get(0).getShoppeCode().equals(dataDto.getCounterCode())) {
				throw new BleException(
						ErrorCode.DISCOUNTCODE_INFO_ERROR_COUNTERCODE.getErrorCode(),
						ErrorCode.DISCOUNTCODE_INFO_ERROR_COUNTERCODE.getMemo());
			}
			if (!erpList.get(0).getSupplyCode().equals(dataDto.getSupplierCode())) {
				throw new BleException(ErrorCode.DISCOUNTCODE_INFO_ERROR_SUPPLIER.getErrorCode(),
						ErrorCode.DISCOUNTCODE_INFO_ERROR_SUPPLIER.getMemo());
			}
		}
		resultDto.setErpList(erpList);//扣率码信息
		// 季节字典数据缺失
		List<PcmSeasonDict> seasonList = null;
		if (dataDto.getType().equals(String.valueOf(Constants.PUBLIC_0))) {
			map.clear();
			map.put("seasonCode", dataDto.getSeasonCode());
			seasonList = seasonDictMapper.selectListByParam(map);
			if (seasonList == null || seasonList.size() != 1) {
				throw new BleException(ErrorCode.SEASON_NULL.getErrorCode(),
						ErrorCode.SEASON_NULL.getMemo());
			}

		}
		resultDto.setSeasonList(seasonList);//季节信息;
		// 色系
		List<PcmColorDict> colorList = null;
		if (dataDto.getType().equals(String.valueOf(Constants.PUBLIC_0))) {
			// 色系字典数据缺失
			map.clear();
			map.put("colorName", dataDto.getProColor());
			colorList = colorDictMapper.selectListByParam(map);
			if (colorList == null || colorList.size() != 1) {
				throw new BleException(ErrorCode.COLOR_NULL.getErrorCode(),
						ErrorCode.COLOR_NULL.getMemo());
			}
		}
		resultDto.setColorList(colorList);//色系信息
		if (StringUtils.isNotBlank(dataDto.getStandardBarCode())) {
			if ("2".equals(dataDto.getType())) {// 电商条码校验
				validBarcodeSap(org.getOrganizationCode(), dataDto.getStandardBarCode());
			} else {// 非电商条码校验
				validBarcode(org.getOrganizationCode(), dataDto.getStandardBarCode());
			}
		} else if (dataDto.getStandardBarCodes() != null
				&& dataDto.getStandardBarCodes().size() != 0) {
			if ("2".equals(dataDto.getType())) {// 电商条码校验
				validBarcodeSap(dataDto.getStandardBarCodes(), org.getOrganizationCode());
			} else {// 非电商条码校验
				validBarcode(dataDto.getStandardBarCodes(), org.getOrganizationCode());
			}
		}
		return resultDto;
	}
	/**
	 * 电商SAP商品字段非空校验
	 * @Methods Name validSAPData
	 * @Create In 2016年6月14日 By wangc
	 * @param dataDto
	 * @throws BleException void
	 */
	private void validSAPData(PcmAllSysPullDataDto dataDto) throws BleException{
		// 验证电商货号
		if ("2".equals(dataDto.getOperateMode())
				&& StringUtils.isBlank(dataDto.getModelNum())) {
			logger.info("电商联营货号不能为空");
			throw new BleException(ErrorCode.PRO_MODELNUM_NULL.getErrorCode(),
					ErrorCode.PRO_MODELNUM_NULL.getMemo());
		}
		if (dataDto.getOperateMode().equals(
				String.valueOf(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3))) {
			//待定
		} else {
			// 当经营方式不为联营时 扣率/进价 与 扣率/含税进价 字段不能同时空
			if (!StringUtils.isNotBlank(dataDto.getRate_price())
					&& !StringUtils.isNotBlank(dataDto.getPurchasePrice_taxRebate())) {
				logger.error(ErrorCode.FLY_PARAS_ERROR.getErrorCode() + "----"
						+ ErrorCode.FLY_PARAS_ERROR.getMemo() + "----" + dataDto.toString());
				throw new BleException(ErrorCode.FLY_PARAS_ERROR.getErrorCode(),
						ErrorCode.FLY_PARAS_ERROR.getMemo());
			}
		}
		// 扣率/进价
		if (StringUtils.isNotBlank(dataDto.getRate_price())) {
			String temp = dataDto.getRate_price();
			if (temp.indexOf("%") != -1) {
				temp = temp.substring(0, temp.length() - 1);
				Double db = Double.parseDouble(temp);
				db = (double) (db / 100);
				dataDto.setRate_price(String.valueOf(db));
			}
			if (!dataDto.getRate_price().matches("\\d+.{0,1}\\d*")) {
				logger.info("扣率/进价参数不符合规则");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_RATEPRICE.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_RATEPRICE.getMemo());
			}
		}
		// 扣率/含税进价
		if (StringUtils.isNotBlank(dataDto.getPurchasePrice_taxRebate())) {
			String temp = dataDto.getPurchasePrice_taxRebate();
			if (temp.indexOf("%") != -1) {
				temp = temp.substring(0, temp.length() - 1);
				Double db = Double.parseDouble(temp);
				db = (double) (db / 100);
				dataDto.setPurchasePrice_taxRebate(String.valueOf(db));
			}
			if (!dataDto.getPurchasePrice_taxRebate().matches("\\d+.{0,1}\\d*")) {
				logger.info("扣率/含税进价参数不符合规则");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_RATEPRICE.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_RATEPRICE.getMemo());
			}
		}
		// 消费税
		if (StringUtils.isNotBlank(dataDto.getConsumptionTax())) {
			String temp = dataDto.getConsumptionTax();
			if (temp.indexOf("%") != -1) {
				temp = temp.substring(0, temp.length() - 1);
				Double db = Double.parseDouble(temp);
				db = (double) (db / 100);
				dataDto.setConsumptionTax(String.valueOf(db));
			}
			if (!dataDto.getConsumptionTax().matches("\\d+.{0,1}\\d*")) {
				logger.info("消费税参数不符合规则");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_SALESTAX.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_SALESTAX.getMemo());
			}
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
		// 经营方式-非空
		if (!StringUtils.isNotBlank(dataDto.getOperateMode())) {
			logger.info("经营方式参数不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_OPERATEMODE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_OPERATEMODE.getMemo());
		}
		// 经营方式-规则
		if (!dataDto.getOperateMode().matches(Constants.SUPPLIER_OPERATEMODE_TYPE)) {
			logger.info("经营方式应为0-4的数字(0经销1代销2联营3平台服务4租赁)");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_OPERATEMODE1.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_OPERATEMODE1.getMemo());
		}
		// 要约号
		if (!StringUtils.isNotBlank(dataDto.getOfferNumber())) {
			logger.info("要约号参数不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_OFFERNUMBER.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_OFFERNUMBER.getMemo());
		}
		// 销售单位编码
		if (!StringUtils.isNotBlank(dataDto.getUnitCode())) {
			logger.info("销售单位参数不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_UNITCODE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_UNITCODE.getMemo());
		}
	}
	
	/**
	 * 导入终端+供应商商品字段非空校验
	 * @Methods Name validPISData
	 * @Create In 2016年6月14日 By wangc
	 * @param dataDto
	 * @throws BleException void
	 */
	private void validPISData(PcmAllSysPullDataDto dataDto) throws BleException{
		//电商商品-------------------------------------------------------------------------------------电商商品
		if (dataDto.getType().equals(String.valueOf(Constants.PUBLIC_2))) {
			if (dataDto.getOperateMode().equals(
					String.valueOf(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3))) {
				//待定
			} else {
				// 当经营方式不为联营时 扣率/进价 与 扣率/含税进价 字段不能同时空
				if (!StringUtils.isNotBlank(dataDto.getRate_price())
						&& !StringUtils.isNotBlank(dataDto.getPurchasePrice_taxRebate())) {
					logger.error(ErrorCode.FLY_PARAS_ERROR.getErrorCode() + "----"
							+ ErrorCode.FLY_PARAS_ERROR.getMemo() + "----" + dataDto.toString());
					throw new BleException(ErrorCode.FLY_PARAS_ERROR.getErrorCode(),
							ErrorCode.FLY_PARAS_ERROR.getMemo());
				}
			}
			//电商商品类型1
			if(StringUtils.isBlank(dataDto.getSapProType())){
				logger.info("商品类型不能为空");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_SAPPROTYPE.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_SAPPROTYPE.getMemo());
			}
			//电商商品名称1
			if(StringUtils.isBlank(dataDto.getProductName())){
				logger.info("商品名称不能为空");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_SAPPRODESC.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_SAPPRODESC.getMemo());
			}
			// 条码/国标码1
			if(StringUtils.isBlank(dataDto.getStandardBarCode())){
				logger.info("供应商条码/国标码不能为空");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_SAPSTANDARDBARCODE.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_SAPSTANDARDBARCODE.getMemo());
			}
			// 基本计量单位1
			if(StringUtils.isBlank(dataDto.getBaseUnitCode())){
				logger.info("基本计量单位不能为空");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_BASEUNITCODE.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_BASEUNITCODE.getMemo());
			}
			//售价 -非空1
			if(StringUtils.isBlank(dataDto.getSalePrice())){
				logger.info("售价不能为空");
				throw new BleException(ErrorCode.SUPPLIER_PCM_SALEPRICE_ISNULL.getErrorCode(),
						ErrorCode.SUPPLIER_PCM_SALEPRICE_ISNULL.getMemo());
			}
			//售价-规则1
			if (!dataDto.getSalePrice().matches("\\d+.{0,1}\\d*")) {
				logger.info("售价参数只能为整数数字或小数数字");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_SALESPRICE.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_SALESPRICE.getMemo());
			}
			//原产国1
			if(StringUtils.isBlank(dataDto.getOriginCountry())){
				logger.info("原产国不能为空");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_ORIGINCOUNTRY.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_ORIGINCOUNTRY.getMemo());
			}
			
			//统比销 - 电商
			if(StringUtils.isBlank(dataDto.getField())){
					logger.info("统比销不能为空");
					throw new BleException(ErrorCode.SAPERP_PCM_ERROR_FIELD.getErrorCode(),
							ErrorCode.SAPERP_PCM_ERROR_FIELD.getMemo());
			}
			//COD - 电商
			if(StringUtils.isBlank(dataDto.getIsCOD())){
					logger.info("电商COD字段不能为空");
					throw new BleException(ErrorCode.SAPERP_PCM_ERROR_ISCOD.getErrorCode(),
							ErrorCode.SAPERP_PCM_ERROR_ISCOD.getMemo());
			}
			//原厂包装 -电商
			if(StringUtils.isBlank(dataDto.getIsOriginPackage())){
				logger.info("是否原厂包装字段不能为空");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_ISORIGINPACKAGE.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_ISORIGINPACKAGE.getMemo());
			}
			// 电商+联营货号不能为空
			if ("2".equals(dataDto.getOperateMode())
					&& StringUtils.isBlank(dataDto.getModelNum())) {
				logger.info("电商联营货号不能为空");

				throw new BleException(ErrorCode.PRO_MODELNUM_NULL.getErrorCode(),
						ErrorCode.PRO_MODELNUM_NULL.getMemo());
			}
			// 电商商品款号不能为空
			if (!StringUtils.isNotBlank(dataDto.getProductNum())) {
				logger.error(ErrorCode.DS_PARA_ERROR.getErrorCode() + "----"
						+ ErrorCode.DS_PARA_ERROR.getMemo() + "：款号----" + dataDto.toString());
				throw new BleException(ErrorCode.DS_PARA_ERROR.getErrorCode(),
						ErrorCode.DS_PARA_ERROR.getMemo() + "：款号");
			}
			// 款号-规则
			if (!dataDto.getProductNum().matches("[^\\u4E00-\\u9FA5]+")) {
				logger.info("款号参数不能包含汉字");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_KUANCODE1.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_KUANCODE1.getMemo());
			}
			// 电商商品色系不能为空
			if (!StringUtils.isNotBlank(dataDto.getProColor())) {
				logger.error(ErrorCode.DS_PARA_ERROR.getErrorCode() + "----"
						+ ErrorCode.DS_PARA_ERROR.getMemo() + "：色系----" + dataDto.toString());
				throw new BleException(ErrorCode.DS_PARA_ERROR.getErrorCode(),
						ErrorCode.DS_PARA_ERROR.getMemo() + "：色系");
			}
			// 电商商品色码不能为空
			if (!StringUtils.isNotBlank(dataDto.getColorCode())) {
				logger.error(ErrorCode.DS_PARA_ERROR.getErrorCode() + "----"
						+ ErrorCode.DS_PARA_ERROR.getMemo() + "：色码----" + dataDto.toString());
				throw new BleException(ErrorCode.DS_PARA_ERROR.getErrorCode(),
						ErrorCode.DS_PARA_ERROR.getMemo() + "：色码");
			}
		}else{//非电商商品--------------------------------------------------------------------------------非电商商品
			if("0".equals(dataDto.getType())){//百货商品
				// 百货字段 商品名不能为空
				if (!StringUtils.isNotBlank(dataDto.getProductName())) {
					logger.error(ErrorCode.BH_PARAS_ERROR.getErrorCode() + "----"
							+ ErrorCode.BH_PARAS_ERROR.getMemo() + ":商品名称----" + dataDto.toString());
					throw new BleException(ErrorCode.BH_PARAS_ERROR.getErrorCode(),
							ErrorCode.BH_PARAS_ERROR.getMemo() + ":商品名称");
				}
				// 百货字段 款号不能为空
				if (!StringUtils.isNotBlank(dataDto.getProductNum())) {
					logger.error(ErrorCode.BH_PARAS_ERROR.getErrorCode() + "----"
							+ ErrorCode.BH_PARAS_ERROR.getMemo() + ":款号----" + dataDto.toString());
					throw new BleException(ErrorCode.BH_PARAS_ERROR.getErrorCode(),
							ErrorCode.BH_PARAS_ERROR.getMemo() + ":款号");
				}
				// 款号-规则
				if (!dataDto.getProductNum().matches("[^\\u4E00-\\u9FA5]+")) {
					logger.info("款号参数不能包含汉字");
					throw new BleException(ErrorCode.SAPERP_PCM_ERROR_KUANCODE1.getErrorCode(),
							ErrorCode.SAPERP_PCM_ERROR_KUANCODE1.getMemo());
				}
				// 百货字段 色系不能为空
				if (!StringUtils.isNotBlank(dataDto.getProColor())) {
					logger.error(ErrorCode.BH_PARAS_ERROR.getErrorCode() + "----"
							+ ErrorCode.BH_PARAS_ERROR.getMemo() + ":色系----" + dataDto.toString());
					throw new BleException(ErrorCode.BH_PARAS_ERROR.getErrorCode(),
							ErrorCode.BH_PARAS_ERROR.getMemo() + ":色系");
				}
				// 色码不能为空
				if (!StringUtils.isNotBlank(dataDto.getColorCode())) {
					logger.error(ErrorCode.BH_PARAS_ERROR.getErrorCode() + "----"
							+ ErrorCode.BH_PARAS_ERROR.getMemo() + ":色码----" + dataDto.toString());
					throw new BleException(ErrorCode.BH_PARAS_ERROR.getErrorCode(),
							ErrorCode.BH_PARAS_ERROR.getMemo() + ":色码");
				}// 百货字段 年份不能为空
				if (!StringUtils.isNotBlank(dataDto.getYearToMarket())) {
					logger.error(ErrorCode.BH_PARAS_ERROR.getErrorCode() + "----"
							+ ErrorCode.BH_PARAS_ERROR.getMemo() + ":上市年份----" + dataDto.toString());
					throw new BleException(ErrorCode.BH_PARAS_ERROR.getErrorCode(),
							ErrorCode.BH_PARAS_ERROR.getMemo() + ":上市年份");
				}
				// 百货字段 季节不能为空
				if (!StringUtils.isNotBlank(dataDto.getSeasonCode())) {
					logger.error(ErrorCode.BH_PARAS_ERROR.getErrorCode() + "----"
							+ ErrorCode.BH_PARAS_ERROR.getMemo() + "：季节----" + dataDto.toString());
					throw new BleException(ErrorCode.BH_PARAS_ERROR.getErrorCode(),
							ErrorCode.BH_PARAS_ERROR.getMemo() + "：季节");
				}
				// 百货非联营 
				if (!dataDto.getOperateMode().equals(
						String.valueOf(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3))) {
					//销项税 进项税 不能为空
					if (StringUtils.isBlank(dataDto.getInputTax())
							|| StringUtils.isBlank(dataDto.getOutputTax())) {
						logger.error(ErrorCode.BH_PARAS_ERROR1.getErrorCode() + "----"
								+ ErrorCode.BH_PARAS_ERROR1.getMemo() + "----" + dataDto.toString());
						throw new BleException(ErrorCode.BH_PARAS_ERROR1.getErrorCode(),
								ErrorCode.BH_PARAS_ERROR1.getMemo());
					}
				}else{//百货联营
					// 验证货号
					if ("2".equals(dataDto.getOperateMode())
							&& StringUtils.isBlank(dataDto.getModelNum())) {
						logger.info("百货联营货号不能为空");
						throw new BleException(ErrorCode.PRO_MODELNUM1_NULL.getErrorCode(),
								ErrorCode.PRO_MODELNUM1_NULL.getMemo());
					}
				}
			}else{//超市------------------------------------------------------------------------------超市商品
				// 超市字段 主属性不能为空
				if (StringUtils.isBlank(dataDto.getMainAttribute())) {
					logger.error(ErrorCode.CS_PARAS_ERROR.getErrorCode() + "----"
							+ ErrorCode.CS_PARAS_ERROR.getMemo() + "：主属性----" + dataDto.toString());
					throw new BleException(ErrorCode.CS_PARAS_ERROR.getErrorCode(),
							ErrorCode.CS_PARAS_ERROR.getMemo() + "：主属性");
				}
				// 超市字段 特性不能为空
				if (StringUtils.isBlank(dataDto.getFeatures())) {
					logger.error(ErrorCode.CS_PARAS_ERROR.getErrorCode() + "----"
							+ ErrorCode.CS_PARAS_ERROR.getMemo() + "：特性----" + dataDto.toString());
					throw new BleException(ErrorCode.CS_PARAS_ERROR.getErrorCode(),
							ErrorCode.CS_PARAS_ERROR.getMemo() + "：特性");
				}
				// 超市字段 注册商标名不能为空
				if (StringUtils.isBlank(dataDto.getRegisteredTradeName())) {
					logger.error(ErrorCode.CS_PARAS_ERROR.getErrorCode() + "----"
							+ ErrorCode.CS_PARAS_ERROR.getMemo() + "：商品注册商标名----" + dataDto.toString());
					throw new BleException(ErrorCode.CS_PARAS_ERROR.getErrorCode(),
							ErrorCode.CS_PARAS_ERROR.getMemo() + "：商品注册商标名 ");
				}
				// 超市字段进项税 不能为空
				if (StringUtils.isBlank(dataDto.getInputTax())) {
					logger.error(ErrorCode.CS_PARAS_ERROR.getErrorCode() + "----"
							+ ErrorCode.CS_PARAS_ERROR.getMemo() + "：进项税----" + dataDto.toString());
					throw new BleException(ErrorCode.CS_PARAS_ERROR.getErrorCode(),
							ErrorCode.CS_PARAS_ERROR.getMemo() + "：进项税");
				}
				// 超市字段销项税不能为空
				if (StringUtils.isBlank(dataDto.getOutputTax())) {
					logger.error(ErrorCode.CS_PARAS_ERROR.getErrorCode() + "----"
							+ ErrorCode.CS_PARAS_ERROR.getMemo() + "：销项税----" + dataDto.toString());
					throw new BleException(ErrorCode.CS_PARAS_ERROR.getErrorCode(),
							ErrorCode.CS_PARAS_ERROR.getMemo() + "：销项税");
				}
				
			}
			//非电商商品都需要验证的字段
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
			// 经营方式-非空
			if (!StringUtils.isNotBlank(dataDto.getOperateMode())) {
				logger.info("经营方式参数不能为空");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_OPERATEMODE.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_OPERATEMODE.getMemo());
			}
			// 经营方式-规则
			if (!dataDto.getOperateMode().matches(Constants.SUPPLIER_OPERATEMODE_TYPE)) {
				logger.info("经营方式应为0-4的数字(0经销1代销2联营3平台服务4租赁)");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_OPERATEMODE1.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_OPERATEMODE1.getMemo());
			}
			// 销售单位编码
			if (!StringUtils.isNotBlank(dataDto.getUnitCode())) {
				logger.info("销售单位参数不能为空");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_UNITCODE.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_UNITCODE.getMemo());
			}
			// 要约号
			if (!StringUtils.isNotBlank(dataDto.getOfferNumber())) {
				logger.info("要约号参数不能为空");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_OFFERNUMBER.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_OFFERNUMBER.getMemo());
			}
			// 折扣底限
			if (!StringUtils.isNotBlank(dataDto.getDiscountLimit())) {
				logger.info("折扣底限参数不能为空");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_DISCOUNTLIMIT.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_DISCOUNTLIMIT.getMemo());
			}
			//期初库存-非空+规则
			if (StringUtils.isNotBlank(dataDto.getInventory())
				&& !dataDto.getInventory().matches("\\d*")) {
				logger.info("期初库存参数不符合规则");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_INVENTORY1.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_INVENTORY1.getMemo());
			}
			//采购人员编号
			if (StringUtils.isBlank(dataDto.getProcurementPersonnelNumber())) {
				logger.info("采购人员编号不能为空");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_PROCUREMEN.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_PROCUREMEN.getMemo());
			}
			//录入人员编号
			if (StringUtils.isBlank(dataDto.getEntryNumber())) {
				logger.info("录入人员编号不能为空");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_ENTRY.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_ENTRY.getMemo());
			}
		}
		//产地1
		if (!StringUtils.isNotBlank(dataDto.getPlaceOfOrigin())) {
				logger.info("产地参数不能为空");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_ORIGIN.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_ORIGIN.getMemo());
		}
		// 扣率/进价
		if (StringUtils.isNotBlank(dataDto.getRate_price())) {
			String temp = dataDto.getRate_price();
			if (temp.indexOf("%") != -1) {
				temp = temp.substring(0, temp.length() - 1);
				Double db = Double.parseDouble(temp);
				db = (double) (db / 100);
				dataDto.setRate_price(String.valueOf(db));
			}
			if (!dataDto.getRate_price().matches("\\d+.{0,1}\\d*")) {
				logger.info("扣率/进价参数不符合规则");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_RATEPRICE.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_RATEPRICE.getMemo());
			}
		}
		// 扣率/含税进价
		if (StringUtils.isNotBlank(dataDto.getPurchasePrice_taxRebate())) {
			String temp = dataDto.getPurchasePrice_taxRebate();
			if (temp.indexOf("%") != -1) {
				temp = temp.substring(0, temp.length() - 1);
				Double db = Double.parseDouble(temp);
				db = (double) (db / 100);
				dataDto.setPurchasePrice_taxRebate(String.valueOf(db));
			}
			if (!dataDto.getPurchasePrice_taxRebate().matches("\\d+.{0,1}\\d*")) {
				logger.info("扣率/含税进价参数不符合规则");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_RATEPRICE.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_RATEPRICE.getMemo());
			}
		}
		// 消费税
		if (StringUtils.isNotBlank(dataDto.getConsumptionTax())) {
			String temp = dataDto.getConsumptionTax();
			if (temp.indexOf("%") != -1) {
				temp = temp.substring(0, temp.length() - 1);
				Double db = Double.parseDouble(temp);
				db = (double) (db / 100);
				dataDto.setConsumptionTax(String.valueOf(db));
			}
			if (!dataDto.getConsumptionTax().matches("\\d+.{0,1}\\d*")) {
				logger.info("消费税参数不符合规则");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_SALESTAX.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_SALESTAX.getMemo());
			}
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
		
		
	}
	
	/*
	 * 多系统商品上传公共字段校验
	 */
	private void validAllSysPullData(PcmAllSysPullDataDto dataDto) throws BleException{
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
		// 品牌编码-非空
		if (!StringUtils.isNotBlank(dataDto.getBrandCode())) {
			logger.info("品牌编码参数不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_BRANDCODE1.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_BRANDCODE1.getMemo());
		}
		// 品牌编码-规则
		if (!dataDto.getBrandCode().matches("\\d+")) {
			logger.info("品牌编码参数只能为数字");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_BRANDCODE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_BRANDCODE.getMemo());
		}
		// 尺码
		if (!StringUtils.isNotBlank(dataDto.getSizeCode())) {
			logger.info("规格/尺码参数不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_SIZECODE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_SIZECODE.getMemo());
		}
		// 末级工业分类代码-非空
		if (!StringUtils.isNotBlank(dataDto.getProdCategoryCode())) {
			logger.info("末级工业分类代码参数不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_PRODCATEGORYCODE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_PRODCATEGORYCODE.getMemo());
		}
		// 末级工业分类编码-规则
		if (!dataDto.getProdCategoryCode().matches("\\d+")) {
			logger.info("末级工业分类编码只能为数字");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_PRODCATEGORYCODE1.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_PRODCATEGORYCODE1.getMemo());
		}
		// 末级统计分类代码-非空
		if (!StringUtils.isNotBlank(dataDto.getFinalClassiFicationCode())) {
			logger.info("末级统计分类代码参数不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_STATCATEGORYCODE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_STATCATEGORYCODE.getMemo());
		}
		// 末级统计分类编码-规则
		if (!dataDto.getFinalClassiFicationCode().matches("\\d+")) {
			logger.info("末级统计分类编码只能为数字");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_STATCATEGORYCODE1.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_STATCATEGORYCODE1.getMemo());
		}
		// 品牌专柜(管理分类编码)-非空
		if (!StringUtils.isNotBlank(dataDto.getManageCateGory())) {
			logger.info("品牌专柜(管理分类)编码参数不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_MANAGECATEGORY.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_MANAGECATEGORY.getMemo());
		}
		// 品牌专柜（管理分类编码）-规则
		if (StringUtils.isNotBlank(dataDto.getManageCateGory())) {
			if (!dataDto.getManageCateGory().matches("\\d+")) {
				logger.info("品牌专柜(管理分类)编码参数只能为数字");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_MANAGECATEGORY1.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_MANAGECATEGORY1.getMemo());
			}
		}
		// 吊牌价-非空
		if (!StringUtils.isNotBlank(dataDto.getMarketPrice())) {
			logger.info("市场价参数不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_MARKETPRICE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_MARKETPRICE.getMemo());
		}
		// 吊牌价-规则
		if (!dataDto.getMarketPrice().matches("\\d+.{0,1}\\d*")) {
			logger.info("市场价参数只能为整数数字或小数数字");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_MARKETPRICE1.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_MARKETPRICE1.getMemo());
		}
		// 类型标识--非空
		if (!StringUtils.isNotBlank(dataDto.getType())) {
			logger.info("类型标识参数不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_TYPE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_TYPE.getMemo());
		}
		// 类型标识--规则
		if (!dataDto.getType().matches("[012]")) {
			logger.info("类型标识参数不符合规则");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_TYPE1.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_TYPE1.getMemo());
		}
	}
	
	/**
	 * 条码校验,同一门店下条码不能重复
	 * @Methods Name validBarcode
	 * @Create In 2016年6月14日 By wangc
	 * @param storeCode
	 * @param barcode void
	 */
	void validBarcode(String storeCode, String barcode) {
		if (!barcode.matches("\\d+")) {
			logger.error("条码格式错误");
			throw new BleException(ErrorCode.PARA_NORULE_ERROR.getErrorCode(),
					ErrorCode.PARA_NORULE_ERROR.getMemo());
		}
		// 条码校验 同一门店下条码不能重复
		PcmBarcode barcodeEntity = new PcmBarcode();
		barcodeEntity.setBarcode(barcode);// 条码
		barcodeEntity.setStoreCode(storeCode);// 门店编码
		List<PcmBarcode> barcodeList = barcodeMapper.selectListByParam(barcodeEntity);
		if (barcodeList != null && barcodeList.size() > 0) {
			throw new BleException(ErrorCode.BARCODE_IS_EXIST.getErrorCode(),
					ErrorCode.BARCODE_IS_EXIST.getMemo());
		}
	}

	/**
	 * 电商条码校验 不验证格式 只判重
	 * @Methods Name validBarcodeSap
	 * @Create In 2016年6月14日 By wangc
	 * @param storeCode
	 * @param barcode void
	 */
	void validBarcodeSap(String storeCode, String barcode) {
		// 条码校验 同一门店下条码不能重复
		PcmBarcode barcodeEntity = new PcmBarcode();
		barcodeEntity.setBarcode(barcode);// 条码
		barcodeEntity.setStoreCode(storeCode);// 门店编码
		List<PcmBarcode> barcodeList = barcodeMapper.selectListByParam(barcodeEntity);
		if (barcodeList != null && barcodeList.size() > 0) {
			throw new BleException(ErrorCode.BARCODE_IS_EXIST.getErrorCode(),
					ErrorCode.BARCODE_IS_EXIST.getMemo());
		}
	}
     /*
	 * 电商条码验证，不验证格式，只判重
	 * 
	 * @Methods Name validBarcodeSap
	 * @Create In 2016-3-1 By wangc
	 * @param barcodes
	 * @param storeCode
	 *            void
	 */
	void validBarcodeSap(List<Map<String, Object>> barcodes, String storeCode) {
		PcmBarcode barcodeEntity = new PcmBarcode();
		barcodeEntity.setStoreCode(storeCode);// 门店编码
		for (Map<String, Object> barcode : barcodes) {
			barcodeEntity.setBarcode(barcode.get("barcode").toString());// 条码
			List<PcmBarcode> barcodeList = barcodeMapper.selectListByParam(barcodeEntity);
			if (barcodeList != null && barcodeList.size() > 0) {
				throw new BleException(ErrorCode.BARCODE_IS_EXIST.getErrorCode(),
						ErrorCode.BARCODE_IS_EXIST.getMemo() + ":" + barcodeEntity.getBarcode());
			}
		}
	}
	/**
	 * 条码校验 同一门店下条码不能重复
	 * 
	 * @Methods Name validBarcode
	 * @Create In 2016-3-1 By wangc
	 * @param barcodes
	 * @param storeCode
	 *            void
	 */
	void validBarcode(List<Map<String, Object>> barcodes, String storeCode) {
		for (Map<String, Object> barcode : barcodes) {
			if (!barcode.get("barcode").toString().matches("\\d+")) {
				logger.error("条码格式错误");
				throw new BleException(ErrorCode.PARA_NORULE_ERROR.getErrorCode(),
						ErrorCode.PARA_NORULE_ERROR.getMemo());
			}
		}
		PcmBarcode barcodeEntity = new PcmBarcode();
		barcodeEntity.setStoreCode(storeCode);// 门店编码
		for (Map<String, Object> barcode : barcodes) {
			barcodeEntity.setBarcode(barcode.get("barcode").toString());// 条码
			List<PcmBarcode> barcodeList = barcodeMapper.selectListByParam(barcodeEntity);
			if (barcodeList != null && barcodeList.size() > 0) {
				throw new BleException(ErrorCode.BARCODE_IS_EXIST.getErrorCode(),
						ErrorCode.BARCODE_IS_EXIST.getMemo() + ":" + barcodeEntity.getBarcode());
			}
		}
	}
	/**
	 * 写入库存
	 * @Methods Name saveInventory
	 * @Create In 2016年6月15日 By wangc
	 * @param code
	 * @param inventory
	 * @param source
	 * @param operator void
	 */
	private void saveInventory(String code, String inventory, String source, String operator) {
		PcmStock record = new PcmStock();
		record.setShoppeProSid(code);
		record.setStockTypeSid(Constants.PCMSTOCK_TYPE_SALE);
		record.setProSum(Long.parseLong(inventory));
		int i = stockMapper.insertSelective(record);
		if (i != 1) {
			throw new BleException(ErrorCode.STOCK_INPUT_ERROR.getErrorCode(),
					ErrorCode.STOCK_INPUT_ERROR.getMemo());
		}
		if (!"0".equals(inventory)) {
			PcmStockDto pcmStockDto = new PcmStockDto();
			pcmStockDto.setProSum(Long.parseLong(inventory));
			pcmStockDto.setSid(record.getSid());
			pcmStockDto.setShoppeProSid(code);
			pcmStockDto.setStockTypeSid(1001);
			pcmStockDto.setSource(source);
			pcmStockDto.setOperator(operator);
			scrsService.changRecord(pcmStockDto, null, 0);
			PcmProductStockInfoDto dto = new PcmProductStockInfoDto();
			dto.setShoppeProSid(code);
			stockService.SelectSkuStockSumBySku(dto);
		}
	}
	/**
	 * 写入一品多供应商关系表
	 * 
	 * @Methods Name insertShoppeProductSupply
	 * @Create In 2015年9月16日 By zhangxy
	 * @param proSid
	 *            supplySid
	 */
	void insertShoppeProductSupply(PcmShoppeProductSupply psps) {
		psps.setStatus(0);
		List<PcmShoppeProductSupply> pspsList = pspsMapper.selectListByParam(psps);
		if (pspsList != null && pspsList.size() > 0) {
			// 一品多供应商关系已存在
			throw new BleException(ErrorCode.SHOPP_IS_EXIST.getErrorCode(),
					ErrorCode.SHOPP_IS_EXIST.getMemo());
		} else {
			// 一品多供应商关系不存在--添加关系
			int res = pspsMapper.insertSelective(psps);
			if (res != 1) {
				throw new BleException(
						ErrorCode.SHOPPEPRODUCT_SUPPLY_RELATION_ERROR.getErrorCode(),
						ErrorCode.SHOPPEPRODUCT_SUPPLY_RELATION_ERROR.getMemo());
			}
		}
	}
}

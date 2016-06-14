/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.maindata.service.implPcmAllSysPullProductSevice.java
 * @Create By wangc
 * @Create In 2016年6月14日 下午4:39:01
 * TODO
 */
package com.wangfj.product.maindata.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.wangfj.product.maindata.domain.entity.PcmErpProduct;
import com.wangfj.product.maindata.domain.entity.PcmSeasonDict;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProduct;
import com.wangfj.product.maindata.domain.vo.PcmAllSysPullDataDto;
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
import com.wangfj.product.stocks.persistence.PcmStockMapper;
import com.wangfj.product.stocks.service.intf.IPcmStockChangeRecordService;
import com.wangfj.product.stocks.service.intf.IPcmStockService;
import com.wangfj.product.supplier.domain.entity.PcmSupplyInfo;
import com.wangfj.product.supplier.persistence.PcmShoppeProductSupplyMapper;
import com.wangfj.product.supplier.persistence.PcmSupplyInfoMapper;
import com.wangfj.product.supplier.persistence.PcmSupplyShoppeRelationMapper;
import com.wangfj.util.Constants;

/**
 * @Class Name PcmAllSysPullProductSevice
 * @Author wangc
 * @Create In 2016年6月14日
 */
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
	public PcmShoppeProduct allSysSaveProduct(PcmAllSysPullDataDto dataDto) throws BleException {
		validAllSysPullData(dataDto);//多系统商品上传公共字段非空校验
		if("PIS".equals(dataDto.getSource())){//导入终端上传
			validPISData(dataDto);
		}else if("SAP".equals(dataDto.getSource())){
			validSAPData(dataDto);
		}if("SUP".equals(dataDto.getSource())){
			validPISData(dataDto);
		}
		relateValidate(dataDto);//数据关联校验
		
		return null;
	}
	/**
	 * 数据关联校验
	 * @Methods Name relateValidate
	 * @Create In 2016年6月14日 By wangc
	 * @param dataDto
	 * @throws BleException void
	 */
	private void relateValidate(PcmAllSysPullDataDto dataDto) throws BleException{
		String source = dataDto.getSource();//数据源系统
		String type = dataDto.getType();//商品类型 0百货 1超市 2电商
		Map<String, Object> map = new HashMap<String, Object>();
		List<PcmShoppe> shoppeList = new ArrayList<PcmShoppe>();
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
		// 获取门店信息
		PcmOrganization org = organizationMapper.get(shoppeList.get(0).getShopSid());
		if (org == null) {
			throw new BleException(ErrorCode.SHOPPE_STATUS_ERROR.getErrorCode(),
					ErrorCode.SHOPPE_STATUS_ERROR.getMemo());
		}
		List<PcmErpProduct> erpList = null;
		if (StringUtils.isNotBlank(dataDto.getErpProductCode())) {
			PcmErpProduct erpEntity = new PcmErpProduct();
			erpEntity.setProductCode(dataDto.getErpProductCode());
			erpEntity.setStoreCode(org.getOrganizationCode());
			erpList = erpProductMapper.selectListByParam(erpEntity);
		}
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
		
		if("2".equals(dataDto.getType())){
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
		// 联营百货扣率码数据校验
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
		// 条码校验 同一门店下条码不能重复
		if (StringUtils.isNotBlank(dataDto.getStandardBarCode())) {
			validBarcode(org.getOrganizationCode(), dataDto.getStandardBarCode());
		}
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

}

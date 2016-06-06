package com.wangfj.product.maindata.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sap.tc.logging.schema.ErrorCategory;
import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.product.brand.domain.entity.PcmBrand;
import com.wangfj.product.brand.domain.entity.PcmShopBrandRelation;
import com.wangfj.product.brand.persistence.PcmBrandMapper;
import com.wangfj.product.brand.persistence.PcmShopBrandRelationMapper;
import com.wangfj.product.category.domain.entity.PcmCategory;
import com.wangfj.product.category.domain.entity.PcmProductParameters;
import com.wangfj.product.category.persistence.PcmCategoryMapper;
import com.wangfj.product.category.persistence.PcmProductCategoryMapper;
import com.wangfj.product.category.persistence.PcmProductParametersMapper;
import com.wangfj.product.maindata.domain.entity.PcmBarcode;
import com.wangfj.product.maindata.domain.entity.PcmColorDict;
import com.wangfj.product.maindata.domain.entity.PcmContractLog;
import com.wangfj.product.maindata.domain.entity.PcmErpProduct;
import com.wangfj.product.maindata.domain.entity.PcmProDetail;
import com.wangfj.product.maindata.domain.entity.PcmProInput;
import com.wangfj.product.maindata.domain.entity.PcmProduct;
import com.wangfj.product.maindata.domain.entity.PcmSeasonDict;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProduct;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProductExtend;
import com.wangfj.product.maindata.domain.vo.BarcodeDto;
import com.wangfj.product.maindata.domain.vo.CreateShoppePro;
import com.wangfj.product.maindata.domain.vo.CreateSkuDto;
import com.wangfj.product.maindata.domain.vo.CreateSpuDto;
import com.wangfj.product.maindata.domain.vo.ParametersDto;
import com.wangfj.product.maindata.domain.vo.PullDataDto;
import com.wangfj.product.maindata.domain.vo.SaveShoppeProductDto;
import com.wangfj.product.maindata.domain.vo.SaveShoppeProductDtoDs;
import com.wangfj.product.maindata.domain.vo.SaveSkuDto;
import com.wangfj.product.maindata.domain.vo.ValidProDetailDto;
import com.wangfj.product.maindata.domain.vo.ValidProductDto;
import com.wangfj.product.maindata.domain.vo.ValidResultDto;
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
import com.wangfj.product.maindata.service.intf.IPcmCreateProductService;
import com.wangfj.product.maindata.service.intf.IPcmStanDictService;
import com.wangfj.product.maindata.service.intf.IValidProDrtailService;
import com.wangfj.product.maindata.service.intf.IValidProductService;
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
 * 验证serivce实现
 * 
 * @Class Name ValidProductServiceImpl
 * @Author wangsy
 * @Create In 2015年7月14日
 */
@Service
public class ValidProductServiceImpl implements IValidProductService {

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
	 * 验证spu/sku/专柜pro
	 * <p>
	 * 层级验证SPU ↓ SKU ↓ 专柜商品
	 */
	@Override
	public ValidResultDto getPISValidProductFromEfuture(ValidProductDto validProductDto)
			throws Exception {
		logger.info("start getPISValidProductFromEfuture(),param:" + validProductDto.toString());
		ValidResultDto vrVo = new ValidResultDto();
		// List<ValidResProMsgDto> vrpmVoList = new
		// ArrayList<ValidResProMsgDto>();
		// Map map = new HashMap();
		// // 专柜编码存在
		// map.put("shoppeCode", validProductDto.getCounterCode());
		// List<PcmShoppe> shoppeList = pcmShoppeMapper.selectListByParam(map);
		// // //循环取出待验证商品列表
		// if (shoppeList != null && shoppeList.size() != Constants.PUBLIC_0) {
		// for (ProductListDto plDto : validProductDto.getListProductDto()) {
		// ValidResProMsgDto vrpmVo = new ValidResProMsgDto();
		// map.clear();
		// // 1.spu验证:根据传递的品牌和款
		// PcmProduct pro = validSPUIsExists(plDto.getBrandCode(),
		// plDto.getProductNum());
		// PcmProDetail proD = new PcmProDetail();
		// PcmShoppeProduct shopp = new PcmShoppeProduct();
		// if (pro != null) {
		// map.clear();
		// // 2.sku验证:spu编码+色+规
		// proD = validSKUIsExists(pro.getProductSid(), plDto.getColorCode(),
		// plDto.getSizeCode());
		// if (proD != null) {
		// map.clear();
		// // 3.专柜pro验证:sku编码+专柜编码+供应商编码
		// shopp = validShoppeProIsExists(proD.getProductDetailSid(),
		// validProductDto.getCounterCode(), validProductDto.getSupplierCode());
		// // 专柜商品验证是否通过
		// if (shopp != null) {
		// vrpmVo.setProductNum(plDto.getProductNum());
		// vrpmVo.setColorCode(plDto.getColorCode());
		// vrpmVo.setSizeCode(plDto.getSizeCode());
		// vrpmVo.setProductCode(proD.getProductDetailSid());/* SKU编码 */
		// vrpmVo.setSupplierProductCode(shopp.getShoppeProSid());/* 专柜商品编码 */
		// vrpmVo.setResCode(String.valueOf(Constants.PUBLIC_1));
		// } else {
		// vrpmVo.setProductNum(plDto.getProductNum());
		// vrpmVo.setColorCode(plDto.getColorCode());
		// vrpmVo.setSizeCode(plDto.getSizeCode());
		// vrpmVo.setProductCode(proD.getProductDetailSid());/* SKU编码 */
		// vrpmVo.setResCode(String.valueOf(Constants.PUBLIC_2));
		// }
		// } else {
		// // SKU不存在
		// vrpmVo.setProductNum(plDto.getProductNum());
		// vrpmVo.setColorCode(plDto.getColorCode());
		// vrpmVo.setSizeCode(plDto.getSizeCode());
		// vrpmVo.setResCode(String.valueOf(Constants.PUBLIC_3));
		// }
		// } else {
		// // spu不存在
		// vrpmVo.setProductNum(plDto.getProductNum());
		// vrpmVo.setColorCode(plDto.getColorCode());
		// vrpmVo.setSizeCode(plDto.getSizeCode());
		// vrpmVo.setResCode(String.valueOf(Constants.PUBLIC_3));
		// }
		// vrpmVoList.add(vrpmVo);
		// }
		// vrVo.setResCode(String.valueOf(Constants.PUBLIC_1));
		// vrVo.setResMessage("成功(详细结果见返回的对象列表)");
		// vrVo.setResCount(String.valueOf(vrpmVoList.size()));
		// vrVo.setResList(vrpmVoList);
		// } else {
		// vrVo.setResCode(String.valueOf(Constants.PUBLIC_0));
		// vrVo.setResMessage("专柜编码在中台不存在");
		// vrVo.setResCount(String.valueOf(Constants.PUBLIC_0));
		// vrVo.setResList(vrpmVoList);
		// }
		// logger.info("end getPISValidProductFromEfuture()");
		return vrVo;
	}

	/**
	 * 商品准入导入终端上传商品到主数据ERP（PIS批量）
	 * 
	 * @throws Exception
	 */
	@Override
	@Transactional
	public PcmShoppeProduct savePullProductFromEFuture(PullDataDto dataDto) throws BleException {
		logger.info("start savePullProductFromEFuture(),param:" + dataDto.toString());
		// 非空与格式参数校验
		if("2".equals(dataDto.getType())){
			validPullDataDtoIsExistsSupShoPro(dataDto, true);
		}else{
			validPullDataDtoIsExists2(dataDto, false);
		}
		// 特殊字段校验
		validPullDataDtoIsExists(dataDto);
		String source = "PIS";
		Map<String, Object> map = new HashMap<String, Object>();
		// 专柜数据校验
		map.clear();
		map.put("shoppeCode", dataDto.getCounterCode());
		map.put("shoppeType", "01"); // 查找单品专柜
		List<PcmShoppe> shoppeList = pcmShoppeMapper.selectListByParam(map);
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
		// 判断供应商经营方式
		if (supplyInfoList.get(0).getBusinessPattern() == null
				|| !String.valueOf(supplyInfoList.get(0).getBusinessPattern()).equals(
						dataDto.getOperateMode())) {
			throw new BleException(ErrorCode.SUPPLYINFO_BUSSINESS_ERROR.getErrorCode(),
					ErrorCode.SUPPLYINFO_BUSSINESS_ERROR.getMemo());
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

		// 创建专柜商品DTO
		CreateShoppePro cProDto = new CreateShoppePro();
		if("2".equals(dataDto.getType())){
			//电商商品 专柜商品表field4 = 原系统商品编码
			cProDto.setField4(dataDto.getProductCode());
		}
		// 品牌表SID
		if (brand != null) {
			cProDto.setBrandSid(String.valueOf(brand.getSid()));
		} else {
			cProDto.setBrandSid(String.valueOf(brandList.get(0).getSid()));
		}
		// 判断是否联营
		if (dataDto.getOperateMode()
				.equals(String.valueOf(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3))) {// 联营
			cProDto.setBusinessType(Constants.PUBLIC_1);// 自定义标记自营0,联营1
			cProDto.setOrderType(Constants.PUBLIC_4);// 订货类型
			cProDto.setRateCode(dataDto.getErpProductCode());// 扣率码
			// cProDto.setIsStock(Constants.PUBLIC_1);// 是否管库库存
			cProDto.setStockMode(Constants.PUBLIC_2);// 库存方式//修改 联营为 2XK虚库
			// cProDto.setSupplySid(String.valueOf(supplyInfoList.get(0).getSid()));//
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
				// cProDto.setPurchasePrice(erpList.get(0).get);// 扣率/进价
				// cProDto.setBuyingPrice(erpList.get(0).get);// 扣率/含税进价
			}
		} else {// 其他
			cProDto.setBusinessType(Constants.PUBLIC_0);// 自定义标记自营0,联营1
			cProDto.setOrderType(Constants.PUBLIC_5);// 订货类型
			cProDto.setStockMode(Constants.PUBLIC_1);// 库存方式??????1.ZK自库
			cProDto.setInputTax(new BigDecimal(dataDto.getInputTax()));// 进项税
			cProDto.setOutputTax(new BigDecimal(dataDto.getOutputTax()));// 销项税
			cProDto.setPurchasePrice(new BigDecimal(dataDto.getRate_price()));// 扣率/进价
			cProDto.setBuyingPrice(new BigDecimal(dataDto.getPurchasePrice_taxRebate()));// 扣率/含税进价
			// cProDto.setSupplySid("WFJ");// 供应商SID 非联营商品默认填WFJ
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
		// cProDto.setIsCod();//是否支持货到付款
		// cProDto.setIsDiscountable();//是否支持打折
		// cProDto.setIsGift();//赠品类型
		// cProDto.setIsPacking();//是否可包装
		// cProDto.setMeasureUnitDictSid();//计量单位
		// cProDto.setPackUnitDictSid();//包装单位
		// cProDto.setSaleStatus();//商品可售状态
		// cProDto.setShoppeProType();//专柜商品类型:0 普通商品，1 大码商品（默认为0）
		// cProDto.setTmsParam();// 物流属性

		CreateSkuDto cSkuDto = new CreateSkuDto();
		// cSkuDto.setArticleNum(dataDto.getModelNum());// 货号
		cSkuDto.setBrandGroupCode(brandList.get(0).getBrandSid());// 集团品牌编码
		cSkuDto.setBrandShopCode(brand.getBrandSid());// 门店品牌编码
		cSkuDto.setCategoryGYSid(cateList.get(0).getSid());// 管理分类编码
		cSkuDto.setFeatures(dataDto.getFeatures());// 特性
		cSkuDto.setFlag(Integer.parseInt(dataDto.getType()));// 超市百货标记_0百货_1超市
		cSkuDto.setProColorAlias(dataDto.getColorName());// 色码名称
		cSkuDto.setProColorName(dataDto.getColorCode());// 色码
		if (colorList != null) {
			cSkuDto.setProColorSid(colorList.get(0).getSid());// 色系表SID
		}
		cSkuDto.setProStanName(dataDto.getSizeCode());// 规格/尺码名称
		cSkuDto.setProStanSid(dataDto.getSizeCode());// 规格/尺码
		cSkuDto.setProType(1);
		// 扩展表DTO
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
		// cSkuDto.setProductName();
		// cSkuDto.setKeyWord();
		// cSkuDto.setMemo();
		// cSkuDto.setOptRealName();
		// cSkuDto.setOptUpdateTime();
		// cSkuDto.setOptUserSid();
		// cSkuDto.setPhotoPlanSid();
		// cSkuDto.setPhotoSaleCodeSid();
		// cSkuDto.setPhotoStatus();
		// cSkuDto.setPlanMaker();
		// cSkuDto.setPlanTime();
		// cSkuDto.setProActiveBit();
		// cSkuDto.setProWriTime();
		// cSkuDto.setSearchKey();
		// cSkuDto.setSellingStatus();
		// cSkuDto.setSizePictureUrl();

		PcmShoppeProduct shoppePro = null;
		// 1.spu验证:
		// 判断超市或百货
		ValidProDetailDto validSpuDto = new ValidProDetailDto();
		validSpuDto.setBrandSid(String.valueOf(brandList.get(0).getBrandSid()));// 品牌SID
		validSpuDto.setProStanSid(dataDto.getSizeCode());// 规格/尺码
		if (dataDto.getType().equals(String.valueOf(Constants.PUBLIC_0))) {// 百货字段
			validSpuDto.setProductSku(dataDto.getProductNum());// 款号
			validSpuDto.setProColorName(dataDto.getColorCode());// 色码
			// validSpuDto.setColorName(dataDto.getProColor());// 色系
			validSpuDto.setProColorSid(colorList.get(0).getSid().toString());// 色系SID
		} else {// 超市字段
			validSpuDto.setPrimaryAttr(dataDto.getMainAttribute());// 主属性
			validSpuDto.setFeatures(dataDto.getFeatures());// 特性
			// validSpuDto.setProColorName(dataDto.getColorCode());// 颜色
		}
		PcmProduct pro = validService.validSpuBh(validSpuDto);
		Long spuFlag = 0l;
		Long skuFlag = 0l;
		if (pro != null) {// SPU存在
			// 2.sku验证
			validSpuDto.setProductSid(String.valueOf(pro.getSid()));// 产品表SID
			List<PcmProDetail> proDList = validService.validSku(validSpuDto);
			if (proDList != null && proDList.size() == 1) {// SKU存在
				PcmProDetail proD = proDList.get(0);
				// 3.专柜pro验证
				ValidShoppeProDto validShoppeProdto = new ValidShoppeProDto();
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
				if (dataDto.getOperateMode().equals(
						String.valueOf(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3))) {
					// 联营时专柜商品 判重-生成 逻辑
					PcmShoppeProduct shopp = validService.validShoppeProBh(validShoppeProdto);
					if (shopp != null) {// 专柜商品存在
						throw new BleException(ErrorCode.SHOPP_IS_EXIST.getErrorCode(),
								ErrorCode.SHOPP_IS_EXIST.getMemo());
					} else {// 专柜商品不存在/创建SHOPPRO
						cProDto.setProductDetailSid(String.valueOf(proD.getSid()));// sku表SID
						if (!StringUtils.isNotBlank(cProDto.getShoppeProName())) {// 专柜商品名为空
							cProDto.setShoppeProName(proD.getProDetailName());
						}
						shoppePro = createService.insertShoppePro(cProDto);
						// 写入库存
						if (StringUtils.isNotBlank(dataDto.getInventory())) {
							saveInventory(shoppePro.getShoppeProSid(), dataDto.getInventory(),
									source, dataDto.getEntryNumber());
						} else {
							saveInventory(shoppePro.getShoppeProSid(), "0", source,
									dataDto.getEntryNumber());
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
					}
				} else {
					// 非联营时专柜商品 判重-生成 逻辑
					PcmShoppeProduct shopp = validService.validShoppePro(validShoppeProdto);
					if (shopp != null) {// 专柜商品存在--判断是否存在一品多供应商关系
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
					} else {// 专柜商品不存在/创建SHOPPRO
						cProDto.setProductDetailSid(String.valueOf(proD.getSid()));// sku表SID
						if (!StringUtils.isNotBlank(cProDto.getShoppeProName())) {// 专柜商品名为空
							cProDto.setShoppeProName(proD.getProDetailName());
						}
						shoppePro = createService.insertShoppePro(cProDto);
						// 写入库存
						if (StringUtils.isNotBlank(dataDto.getInventory())) {
							saveInventory(shoppePro.getShoppeProSid(), dataDto.getInventory(),
									source, dataDto.getEntryNumber());
						} else {
							saveInventory(shoppePro.getShoppeProSid(), "0", source,
									dataDto.getEntryNumber());
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
					}
				}
			} else if (proDList != null && proDList.size() == 0) {// SKU没有/创建SKU-SHOPPRO
				cSkuDto.setProductSid(String.valueOf(pro.getSid()));// 产品表SID
				cSkuDto.setProductName(pro.getProductName());
				PcmProDetail sku = createService.insertSKU(cSkuDto);
				skuFlag = sku.getSid();
				cProDto.setProductDetailSid(String.valueOf(sku.getSid()));
				if (!StringUtils.isNotBlank(cProDto.getShoppeProName())) {// 专柜商品名为空
					cProDto.setShoppeProName(sku.getProDetailName());
				}
				shoppePro = createService.insertShoppePro(cProDto);
				// 写入库存
				if (StringUtils.isNotBlank(dataDto.getInventory())) {
					saveInventory(shoppePro.getShoppeProSid(), dataDto.getInventory(), source,
							dataDto.getEntryNumber());
				} else {
					saveInventory(shoppePro.getShoppeProSid(), "0", source,
							dataDto.getEntryNumber());
				}
				// 写入临时表
				if (StringUtils.isNotBlank(dataDto.getEntryNumber())
						&& StringUtils.isNotBlank(dataDto.getProcurementPersonnelNumber())
						&& StringUtils.isNotBlank(dataDto.getOfferNumber())) {
					createService.insertProductInput(shoppePro.getSid(), dataDto.getOfferNumber(),
							dataDto.getEntryNumber(), dataDto.getProcurementPersonnelNumber(),
							dsPro);
				}
				// 写入一品多供应商关系表
				PcmShoppeProductSupply psps = new PcmShoppeProductSupply();
				psps.setShoppeProductSid((shoppePro.getSid()));// 专柜商品SID
				psps.setSupplySid((supplyInfoList.get(0).getSid()));// 供应商SID
				psps.setShopSid(org.getOrganizationCode());//一品多商加门店编码
				insertShoppeProductSupply(psps);
			} else {
				throw new BleException(ErrorCode.SKU_IS_EXIST1.getErrorCode(),
						ErrorCode.SKU_IS_EXIST1.getMemo());
			}
		} else {// SPU没有/创建SPU-SKU-SHOPPRO
			CreateSpuDto cSpuDto = new CreateSpuDto();
			cSpuDto.setBrandName(brandList.get(0).getBrandName());// 集团品牌名称
			cSpuDto.setBrandRootSid(String.valueOf(brandList.get(0).getSid()));// 集团品牌表SID
			cSpuDto.setBrandSid(brandList.get(0).getBrandSid());// 集团品牌编码
			cSpuDto.setCategoryGYSid(cateList.get(0).getSid());// 工业分类SID
			cSpuDto.setFlag(Integer.parseInt(dataDto.getType()));// 超市百货标识_0百货_1超市
			cSpuDto.setPrimaryAttr(dataDto.getMainAttribute());// 主属性
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
			// cSpuDto.setActivityFlg();
			// cSpuDto.setAwesome();
			// cSpuDto.setCategorySid();
			// cSpuDto.setCreateTime();
			// cSpuDto.setEditFlag();
			// cSpuDto.setOptUser();
			// cSpuDto.setProActiveBit();
			// cSpuDto.setProDescSid();
			// cSpuDto.setProductName();
			// cSpuDto.setProductNameAlias();
			// cSpuDto.setProPicture();
			// cSpuDto.setProSelling();
			// cSpuDto.setProSellingTime();
			// cSpuDto.setShortDes();
			// cSpuDto.setSpecialDes();
			PcmProduct spu = createService.insertSPU(cSpuDto);
			spuFlag = spu.getSid();
			cSkuDto.setProductSid(String.valueOf(spu.getSid()));
			cSkuDto.setProductName(spu.getProductName());
			PcmProDetail sku = createService.insertSKU(cSkuDto);
			skuFlag = sku.getSid();
			cProDto.setProductDetailSid(String.valueOf(sku.getSid()));
			if (!StringUtils.isNotBlank(cProDto.getShoppeProName())) {// 专柜商品名为空
				cProDto.setShoppeProName(sku.getProDetailName());
			}
			shoppePro = createService.insertShoppePro(cProDto);
			// 写入库存
			if (StringUtils.isNotBlank(dataDto.getInventory())) {
				saveInventory(shoppePro.getShoppeProSid(), dataDto.getInventory(), source,
						dataDto.getEntryNumber());
			} else {
				saveInventory(shoppePro.getShoppeProSid(), "0", source, dataDto.getEntryNumber());
			}
			// 写入临时表
			if (StringUtils.isNotBlank(dataDto.getEntryNumber())
					&& StringUtils.isNotBlank(dataDto.getProcurementPersonnelNumber())
					&& StringUtils.isNotBlank(dataDto.getOfferNumber())) {
				createService.insertProductInput(shoppePro.getSid(), dataDto.getOfferNumber(),
						dataDto.getEntryNumber(), dataDto.getProcurementPersonnelNumber(), dsPro);
			}
			// 写入一品多供应商关系表
			PcmShoppeProductSupply psps = new PcmShoppeProductSupply();
			psps.setShoppeProductSid((shoppePro.getSid()));// 专柜商品SID
			psps.setSupplySid((supplyInfoList.get(0).getSid()));// 供应商SID
			psps.setShopSid(org.getOrganizationCode());//一品多商加门店编码
			insertShoppeProductSupply(psps);
		}
		logger.info("end savePullProductFromEFuture()");
		if (shoppePro != null) {
			shoppePro.setPackUnitDictSid(spuFlag);
			shoppePro.setMeasureUnitDictSid(skuFlag);
		}
		return shoppePro;
	}

	/**
	 * 写入库存表
	 * 
	 * @Methods Name saveInventory
	 * @Create In 2015年9月7日 By zhangxy
	 * @param sid
	 * @param inventory
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
	 * 特殊字段校验
	 * 
	 * @Methods Name validPullDataDtoIsExists
	 * @Create In 2015年7月21日 By wangsy
	 * @param dataDto
	 * @return boolean
	 */
	public void validPullDataDtoIsExists(PullDataDto dataDto) throws BleException {
		logger.info("start validPullDataDtoIsExists(),param:" + dataDto.toString());
		if (dataDto.getType().equals(String.valueOf(Constants.PUBLIC_0))) {
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
			}
			// 百货字段 年份不能为空
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
			// 百货非联营 销项税 进项税 不能为空
			if (!dataDto.getOperateMode().equals(
					String.valueOf(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3))) {
				if (StringUtils.isBlank(dataDto.getInputTax())
						|| StringUtils.isBlank(dataDto.getOutputTax())) {
					logger.error(ErrorCode.BH_PARAS_ERROR1.getErrorCode() + "----"
							+ ErrorCode.BH_PARAS_ERROR1.getMemo() + "----" + dataDto.toString());
					throw new BleException(ErrorCode.BH_PARAS_ERROR1.getErrorCode(),
							ErrorCode.BH_PARAS_ERROR1.getMemo());
				}
			}
		} else if (dataDto.getType().equals(String.valueOf(Constants.PUBLIC_1))) {
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
		} else if (dataDto.getType().equals(String.valueOf(Constants.PUBLIC_2))) {
			if (!StringUtils.isNotBlank(dataDto.getProductNum())) {// 电商商品款号不能为空
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
			if (!StringUtils.isNotBlank(dataDto.getProColor())) {// 电商商品色系不能为空
				logger.error(ErrorCode.DS_PARA_ERROR.getErrorCode() + "----"
						+ ErrorCode.DS_PARA_ERROR.getMemo() + "：色系----" + dataDto.toString());
				throw new BleException(ErrorCode.DS_PARA_ERROR.getErrorCode(),
						ErrorCode.DS_PARA_ERROR.getMemo() + "：色系");
			}
			if (!StringUtils.isNotBlank(dataDto.getColorCode())) {// 电商商品色码不能为空
				logger.error(ErrorCode.DS_PARA_ERROR.getErrorCode() + "----"
						+ ErrorCode.DS_PARA_ERROR.getMemo() + "：色码----" + dataDto.toString());
				throw new BleException(ErrorCode.DS_PARA_ERROR.getErrorCode(),
						ErrorCode.DS_PARA_ERROR.getMemo() + "：色码");
			}
		}
		if (!dataDto.getType().equals(String.valueOf(Constants.PUBLIC_2))) {
			if (dataDto.getOperateMode().equals(
					String.valueOf(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3))) {
				// // 当经营方式为联营时 扣率/进价 字段不能为空
				// if (!StringUtils.isNotBlank(dataDto.getRate_price())) {
				// throw new
				// BleException(ErrorCode.LY_PARA_ERROR.getErrorCode(),
				// ErrorCode.LY_PARA_ERROR.getMemo());
				// }
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
		}
		logger.info("end validPullDataDtoIsExists()");
	}

	/**
	 * 非空字段校验
	 * 
	 * @Methods Name validPullDataDtoIsExists2
	 * @Create In 2015年8月20日 By zhangxy
	 * @param dataDto
	 * @return boolean
	 */
	public boolean validPullDataDtoIsExists2(PullDataDto dataDto, Boolean sapErp)
			throws BleException {
		logger.info("start validPullDataDtoIsExists(),param:" + dataDto.toString());
		// 款号--非空
		/*
		 * if("0".equals(dataDto.getType())){ //百货款号不能为空 if
		 * (!StringUtils.isNotBlank(dataDto.getProductNum())) {
		 * logger.info("款号参数不能为空"); throw new
		 * BleException(ErrorCode.SAPERP_PCM_ERROR_KUANCODE.getErrorCode(),
		 * ErrorCode.SAPERP_PCM_ERROR_KUANCODE.getMemo()); } //款号-规则 if
		 * (!dataDto.getProductNum().matches("[^\\u4E00-\\u9FA5]+")) {
		 * logger.info("款号参数不能包含汉字"); throw new
		 * BleException(ErrorCode.SAPERP_PCM_ERROR_KUANCODE1.getErrorCode(),
		 * ErrorCode.SAPERP_PCM_ERROR_KUANCODE1.getMemo()); } }
		 * if("1".equals(dataDto.getType())){ //超市主属性不能为空 if
		 * (!StringUtils.isNotBlank(dataDto.getMainAttribute())) { throw new
		 * BleException(ErrorCode.CS_PARA_ERROR.getErrorCode(),
		 * ErrorCode.CS_PARA_ERROR.getMemo() + "：主属性"); } }
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
		// 验证百货货号
		if ("0".equals(dataDto.getType()) && "2".equals(dataDto.getOperateMode())
				&& StringUtils.isBlank(dataDto.getModelNum())) {
			logger.info("百货联营货号不能为空");
			throw new BleException(ErrorCode.PRO_MODELNUM1_NULL.getErrorCode(),
					ErrorCode.PRO_MODELNUM1_NULL.getMemo());
		}
		// 验证电商货号
		if ("2".equals(dataDto.getType()) && "2".equals(dataDto.getOperateMode())
				&& StringUtils.isBlank(dataDto.getModelNum())) {
			logger.info("电商联营货号不能为空");

			throw new BleException(ErrorCode.PRO_MODELNUM_NULL.getErrorCode(),
					ErrorCode.PRO_MODELNUM_NULL.getMemo());
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
		// 销售单位编码
		if (!StringUtils.isNotBlank(dataDto.getUnitCode())) {
			logger.info("销售单位参数不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_UNITCODE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_UNITCODE.getMemo());
		}
		// 尺码数据
		if (!StringUtils.isNotBlank(dataDto.getSizeCode())) {
			logger.info("规格/尺码参数不符合规则");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_SIZECODE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_SIZECODE.getMemo());
		}
		// 品牌专柜(管理分类编码)-非空
		if (!StringUtils.isNotBlank(dataDto.getManageCateGory())) {
//			if (!sapErp) {
//				logger.info("品牌专柜(管理分类)编码参数不能为空");
//				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_MANAGECATEGORY.getErrorCode(),
//						ErrorCode.SAPERP_PCM_ERROR_MANAGECATEGORY.getMemo());
//			}
			logger.info("品牌专柜(管理分类)编码参数不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_MANAGECATEGORY.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_MANAGECATEGORY.getMemo());
		}
		// 品牌专柜（管理分类编码）-规则
		if (StringUtils.isNotBlank(dataDto.getManageCateGory())) {
//			if (!dataDto.getManageCateGory().matches("\\d+") && !sapErp) {
//				logger.info("品牌专柜(管理分类)编码参数只能为数字");
//				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_MANAGECATEGORY1.getErrorCode(),
//						ErrorCode.SAPERP_PCM_ERROR_MANAGECATEGORY1.getMemo());
//			}
			if (!dataDto.getManageCateGory().matches("\\d+")) {
				logger.info("品牌专柜(管理分类)编码参数只能为数字");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_MANAGECATEGORY1.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_MANAGECATEGORY1.getMemo());
			}
		}
		// 要约号
		if (!StringUtils.isNotBlank(dataDto.getOfferNumber())) {
			logger.info("要约号参数不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_OFFERNUMBER.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_OFFERNUMBER.getMemo());
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
		// 折扣底限
		if (!StringUtils.isNotBlank(dataDto.getDiscountLimit())) {
			if (!sapErp) {
				logger.info("折扣底限参数不能为空");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_DISCOUNTLIMIT.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_DISCOUNTLIMIT.getMemo());
			}
		}
		// 产地
		if (!StringUtils.isNotBlank(dataDto.getPlaceOfOrigin())) {
			if (!sapErp) {
				logger.info("产地参数不能为空");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_ORIGIN.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_ORIGIN.getMemo());
			}
		}
		// 加工类型
		// if (!StringUtils.isNotBlank(dataDto.getProcessingType())
		// || !dataDto.getProcessingType().matches("[1234]")) {
		// logger.info("加工类型参数不符合规则");
		// return false;
		// }
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
		// 吊牌价-非空
		if (!StringUtils.isNotBlank(dataDto.getMarketPrice())) {
			logger.info("吊牌价参数不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_MARKETPRICE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_MARKETPRICE.getMemo());
		}
		// 吊牌价-规则
		if (!dataDto.getMarketPrice().matches("\\d+.{0,1}\\d*")) {
			logger.info("吊牌价参数只能为整数数字或小数数字");
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
		// 物流类型
		// if (!StringUtils.isNotBlank(dataDto.getTmsParam())
		// || !dataDto.getTmsParam().matches("[1234]")) {
		// if (sapErp) {
		// logger.info("物流类型参数不符合规则");
		// return false;
		// }
		// }
		// 赠品范围
		// if (!StringUtils.isNotBlank(dataDto.getIsGift())
		// || !dataDto.getTmsParam().matches("[0123456789]")) {
		// if (sapErp) {
		// logger.info("赠品范围参数不符合规则");
		// return false;
		// }
		// }
		// 期初库存-非空 百货联营判断期初库存
		/*
		 * if("0".equals(dataDto.getType()) &&
		 * "2".equals(dataDto.getOperateMode())){ if
		 * (!StringUtils.isNotBlank(dataDto.getInventory())) { if (!sapErp) {
		 * logger.info("期初库存参数不能为空"); throw new
		 * BleException(ErrorCode.SAPERP_PCM_ERROR_INVENTORY.getErrorCode(),
		 * ErrorCode.SAPERP_PCM_ERROR_INVENTORY.getMemo()); } } //期初库存-规则 if
		 * (StringUtils.isNotBlank(dataDto.getInventory()) ) { if (!sapErp &&
		 * !dataDto.getInventory().matches("\\d*")) {
		 * logger.info("期初库存参数不符合规则"); throw new
		 * BleException(ErrorCode.SAPERP_PCM_ERROR_INVENTORY1.getErrorCode(),
		 * ErrorCode.SAPERP_PCM_ERROR_INVENTORY1.getMemo()); } } }
		 */
		if (StringUtils.isNotBlank(dataDto.getInventory())
				&& !dataDto.getInventory().matches("\\d*")) {
			if (!sapErp) {
				logger.info("期初库存参数不符合规则");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_INVENTORY1.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_INVENTORY1.getMemo());
			}
		}
		if (StringUtils.isBlank(dataDto.getProcurementPersonnelNumber())) {
			if (!sapErp) {
				logger.info("采购人员编号不能为空");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_PROCUREMEN.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_PROCUREMEN.getMemo());
			}
		}
		if (StringUtils.isBlank(dataDto.getEntryNumber())) {
			if (!sapErp) {
				logger.info("录入人员编号不能为空");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_ENTRY.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_ENTRY.getMemo());
			}
		}
		logger.info("end validPullDataDtoIsExists()");
		return true;
	}

	/**
	 * 插入一条商品基本信息(SKU)
	 * 
	 * @Methods Name saveProduct
	 * @Create In 2015年8月24日 By zhangxy
	 * @param dto
	 * @return PcmProDetail
	 * @throws Exception
	 */
	@Override
	@Transactional
	public PcmProDetail saveProduct(SaveSkuDto dataDto) throws BleException {
		// 特殊字段校验
		if (dataDto.getType().equals("0")) {
			/*
			 * // 款号 -非空 if (!StringUtils.isNotBlank(dataDto.getProductNum())) {
			 * logger.info("款号参数不能为空"); throw new
			 * BleException(ErrorCode.SAPERP_PCM_ERROR_KUANCODE.getErrorCode(),
			 * ErrorCode.SAPERP_PCM_ERROR_KUANCODE.getMemo()); }
			 */

			// 百货字段 款号不能为空
			if (!StringUtils.isNotBlank(dataDto.getProductNum())) {
				throw new BleException(ErrorCode.BH_PARA_ERROR.getErrorCode(),
						ErrorCode.BH_PARA_ERROR.getMemo() + ":款号");
			}
			// 款号-规则
			if (!dataDto.getProductNum().matches("[^\\u4E00-\\u9FA5]+")) {
				logger.info("款号参数不能包含汉字");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_KUANCODE1.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_KUANCODE1.getMemo());
			}
			// 百货字段色系不能为空
			if (!StringUtils.isNotBlank(dataDto.getProColor())) {
				throw new BleException(ErrorCode.BH_PARA_ERROR.getErrorCode(),
						ErrorCode.BH_PARA_ERROR.getMemo() + "：色系");
			}
			// 百货字段色码不能为空
			if (!StringUtils.isNotBlank(dataDto.getColorCode())) {
				throw new BleException(ErrorCode.BH_PARA_ERROR.getErrorCode(),
						ErrorCode.BH_PARA_ERROR.getMemo() + "：色码");
			}
		} else if (dataDto.getType().equals("1")) {
			// 超市字段 主属性不能为空
			if (!StringUtils.isNotBlank(dataDto.getMainAttribute())) {
				throw new BleException(ErrorCode.CS_PARA_ERROR.getErrorCode(),
						ErrorCode.CS_PARA_ERROR.getMemo() + "：主属性");
			}
			// 超市字段 特性不能为空
			if (!StringUtils.isNotBlank(dataDto.getFeatures())) {
				throw new BleException(ErrorCode.CS_PARA_ERROR.getErrorCode(),
						ErrorCode.CS_PARA_ERROR.getMemo() + "：特性");
			}
		}
		PcmBrand groupBrand = null;
		if (dataDto.isFromPis()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("brandSid", dataDto.getBrandSid());
			map.put("brandType", 1); // 品牌类型（门店）
			map.put("status", 0); // 状态为有效
			List<PcmBrand> brandList = brandMapper.selectListByParam(map);// 门店品牌信息
			if (brandList == null || brandList.size() != 1) {
				// 品牌不存在
				throw new BleException(ErrorCode.BRANDGROUP_NULL.getErrorCode(),
						ErrorCode.BRANDGROUP_NULL.getMemo());
			} else {
				// 查询集团品牌
				map.clear();
				map.put("sid", brandList.get(0).getParentSid());
				map.put("brandType", 0); // 品牌类型（集团）
				map.put("status", 0); // 状态为有效
				List<PcmBrand> gbrandList = brandMapper.selectListByParam(map);// 集团品牌信息
				if (gbrandList == null || gbrandList.size() != 1) {
					// 品牌不存在
					throw new BleException(ErrorCode.BRANDGROUP_NULL.getErrorCode(),
							ErrorCode.BRANDGROUP_NULL.getMemo());
				} else {
					groupBrand = gbrandList.get(0);
				}
			}
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sid", dataDto.getBrandSid());
			map.put("brandType", 0); // 品牌类型（集团）
			map.put("status", 0); // 状态为有效
			List<PcmBrand> gbrandList = brandMapper.selectListByParam(map);// 集团品牌信息
			if (gbrandList == null || gbrandList.size() != 1) {
				// 品牌不存在
				throw new BleException(ErrorCode.BRANDGROUP_NULL.getErrorCode(),
						ErrorCode.BRANDGROUP_NULL.getMemo());
			} else {
				groupBrand = gbrandList.get(0);
			}
		}
		CreateSkuDto cSkuDto = new CreateSkuDto();
		// cSkuDto.setArticleNum(dataDto.getModelNum());// 货号
		cSkuDto.setBrandGroupCode(groupBrand.getBrandSid());// 集团品牌编码
		cSkuDto.setCategoryGYSid(Long.parseLong(dataDto.getProdCategoryCode()));// 工业分类SID
		if (dataDto.getType().equals("1")) {
			cSkuDto.setFeatures(dataDto.getFeatures());// 特性
		}
		cSkuDto.setFlag(Integer.parseInt(dataDto.getType()));// 超市百货标记_0百货_1超市
		cSkuDto.setProColorAlias(dataDto.getColorName());// 色码名称
		cSkuDto.setProColorName(dataDto.getColorCode());// 色码
		if (StringUtils.isNotBlank(dataDto.getProColor())) {
			cSkuDto.setProColorSid(Long.parseLong(dataDto.getProColor()));// 色系表SID
		}
		cSkuDto.setProStanName(dataDto.getSizeCode());// 规格/尺码名称
		cSkuDto.setProStanSid(dataDto.getSizeCode());// 规格/尺码
		if (StringUtils.isNotBlank(dataDto.getProTypeSid())) {
			cSkuDto.setProType(Integer.parseInt(dataDto.getProTypeSid()));// 商品类型
		} else {
			cSkuDto.setProType(1);
		}
		// cSkuDto.setProductName();
		// cSkuDto.setKeyWord();
		// cSkuDto.setMemo();
		// cSkuDto.setOptRealName();
		// cSkuDto.setOptUpdateTime();
		// cSkuDto.setOptUserSid();
		// cSkuDto.setPhotoPlanSid();
		// cSkuDto.setPhotoSaleCodeSid();
		// cSkuDto.setPhotoStatus();
		// cSkuDto.setPlanMaker();
		// cSkuDto.setPlanTime();
		// cSkuDto.setProActiveBit();
		// cSkuDto.setProWriTime();
		// cSkuDto.setSearchKey();
		// cSkuDto.setSellingStatus();
		// cSkuDto.setSizePictureUrl();
		PcmProDetail sku = null;
		// 1.spu验证:
		// 判断超市或百货
		ValidProDetailDto validSpuDto = new ValidProDetailDto();
		validSpuDto.setBrandSid(groupBrand.getBrandSid());// 品牌SID
		validSpuDto.setProStanSid(dataDto.getSizeCode());// 规格/尺码
		if (dataDto.getType().equals(String.valueOf(Constants.PUBLIC_0))) {// 百货字段
			validSpuDto.setProductSku(dataDto.getProductNum());// 款号
			validSpuDto.setProColorName(dataDto.getColorCode());// 色码
			validSpuDto.setProColorSid(dataDto.getProColor());// 色系
		} else {// 超市字段
			validSpuDto.setPrimaryAttr(dataDto.getMainAttribute());// 主属性
			validSpuDto.setFeatures(dataDto.getFeatures());// 特性
			// validSpuDto.setProColorName(dataDto.getColorCode());// 颜色
		}
		PcmProduct pro = validService.validSpuBh(validSpuDto);
		Long spuFlag = 0l;
		if (pro != null) {// SPU存在
			// 2.sku验证
			validSpuDto.setProductSid(String.valueOf(pro.getSid()));// 产品表SID
			List<PcmProDetail> proDList = validService.validSku(validSpuDto);

			if (proDList != null && proDList.size() == 1) {// SKU存在
				throw new BleException(ErrorCode.SKU_IS_EXIST.getErrorCode(),
						ErrorCode.SKU_IS_EXIST.getMemo());
			} else if (proDList != null && proDList.size() == 0) {// SKU没有/创建SKU-SHOPPRO
				cSkuDto.setProductSid(String.valueOf(pro.getSid()));// 产品表SID
				cSkuDto.setProductName(pro.getProductName());
				sku = createService.insertSKU(cSkuDto);
			} else {
				throw new BleException(ErrorCode.SKU_IS_EXIST1.getErrorCode(),
						ErrorCode.SKU_IS_EXIST1.getMemo());
			}
			/*
			 * if (proDList != null && proDList.size() != 0) {// SKU存在 throw new
			 * BleException(ErrorCode.SKU_IS_EXIST.getErrorCode(),
			 * ErrorCode.SKU_IS_EXIST.getMemo()); } else {// SKU没有/创建SKU-SHOPPRO
			 * cSkuDto.setProductSid(String.valueOf(pro.getSid()));// 产品表SID
			 * cSkuDto.setProductName(pro.getProductName()); sku =
			 * createService.insertSKU(cSkuDto); }
			 */
			sku.setProductSid(Long.parseLong(pro.getProductSid()));
		} else {// SPU没有/创建SPU-SKU-SHOPPRO
			CreateSpuDto cSpuDto = new CreateSpuDto();
			cSpuDto.setBrandName(groupBrand.getBrandName());// 集团品牌名称
			cSpuDto.setBrandRootSid(String.valueOf(groupBrand.getSid()));// 集团品牌表SID
			cSpuDto.setBrandSid(groupBrand.getBrandSid());// 集团品牌编码
			cSpuDto.setCategoryGYSid(Long.parseLong(dataDto.getProdCategoryCode()));// 工业分类SID
			cSpuDto.setFlag(Integer.parseInt(dataDto.getType()));// 超市百货标识_0百货_1超市
			if (dataDto.getType().equals("0")) {
				cSpuDto.setProductSku(dataDto.getProductNum());// 款号
			} else {
				cSpuDto.setPrimaryAttr(dataDto.getMainAttribute());// 主属性
			}
			cSpuDto.setSeasonCode(dataDto.getSeasonCode());// 季节表SID
			if (dataDto.getCrowdUser() != null && dataDto.getCrowdUser() != ""
					&& dataDto.getCrowdUser() != "null") {
				cSpuDto.setSexSid(Integer.parseInt(dataDto.getCrowdUser()));// 适合人群
			}
			if (StringUtils.isNotBlank(dataDto.getYearToMarket())) {
				cSpuDto.setYearToMarket(dataDto.getYearToMarket());// 上市年份
			}
			// cSpuDto.setActivityFlg();
			// cSpuDto.setAwesome();
			// cSpuDto.setCategorySid();
			// cSpuDto.setCreateTime();
			// cSpuDto.setEditFlag();
			// cSpuDto.setOptUser();
			// cSpuDto.setProActiveBit();
			// cSpuDto.setProDescSid();
			// cSpuDto.setProductName();
			// cSpuDto.setProductNameAlias();
			// cSpuDto.setProPicture();
			// cSpuDto.setProSelling();
			// cSpuDto.setProSellingTime();
			// cSpuDto.setShortDes();
			// cSpuDto.setSpecialDes();
			PcmProduct spu = createService.insertSPU(cSpuDto);
			insertParameter(spu.getSid(), dataDto);
			spuFlag = spu.getSid();
			cSkuDto.setProductSid(String.valueOf(spu.getSid()));
			cSkuDto.setProductName(spu.getProductName());
			sku = createService.insertSKU(cSkuDto);
			sku.setProductSid(Long.parseLong(spu.getProductSid()));
		}
		if (sku != null) {
			sku.setOptUserSid(spuFlag);
		}
		return sku;
	}

	/**
	 * 插入一条商品基本信息(SKU)
	 * 
	 * @Methods Name saveProductPis
	 * @Create In 2015年8月24日 By zhangxy
	 * @param dto
	 * @return PcmProDetail
	 * @throws Exception
	 */
	@Override
	@Transactional
	public PcmShoppeProduct saveProductPis(SaveSkuDto dataDto, SaveShoppeProductDto dto)
			throws BleException {
		// 特殊字段校验
		if (dataDto.getType().equals("0")) {
			// 百货字段 款号不能为空
			/*
			 * if (!StringUtils.isNotBlank(dataDto.getProductNum()) ||
			 * !StringUtils.isNotBlank(dataDto.getProColor()) ||
			 * !StringUtils.isNotBlank(dataDto.getColorCode())) { throw new
			 * BleException(ErrorCode.BH_PARA_ERROR.getErrorCode(),
			 * ErrorCode.BH_PARA_ERROR.getMemo()); }
			 */
			if (!StringUtils.isNotBlank(dataDto.getProductNum())) {
				throw new BleException(ErrorCode.BH_PARA_ERROR.getErrorCode(),
						ErrorCode.BH_PARA_ERROR.getMemo() + ":款号");
			}
			// 款号-规则
			if (!dataDto.getProductNum().matches("[^\\u4E00-\\u9FA5]+")) {
				logger.info("款号参数不能包含汉字");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_KUANCODE1.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_KUANCODE1.getMemo());
			}
			// 百货字段色系不能为空
			if (!StringUtils.isNotBlank(dataDto.getProColor())) {
				throw new BleException(ErrorCode.BH_PARA_ERROR.getErrorCode(),
						ErrorCode.BH_PARA_ERROR.getMemo() + "：色系");
			}
			// 百货字段色码不能为空
			if (!StringUtils.isNotBlank(dataDto.getColorCode())) {
				throw new BleException(ErrorCode.BH_PARA_ERROR.getErrorCode(),
						ErrorCode.BH_PARA_ERROR.getMemo() + "：色码");
			}
			// 款号
			if (!dataDto.getProductNum().matches("[^\\u4E00-\\u9FA5]+")) {
				logger.info("款号参数不符合规则");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_KUANCODE1.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_KUANCODE1.getMemo());
			}
		} else if (dataDto.getType().equals("1")) {
			// 超市字段 主属性不能为空
			/*
			 * if (!StringUtils.isNotBlank(dataDto.getMainAttribute()) ||
			 * !StringUtils.isNotBlank(dataDto.getFeatures())) { throw new
			 * BleException(ErrorCode.CS_PARA_ERROR.getErrorCode(),
			 * ErrorCode.CS_PARA_ERROR.getMemo()); }
			 */
			if (!StringUtils.isNotBlank(dataDto.getMainAttribute())) {
				throw new BleException(ErrorCode.CS_PARA_ERROR.getErrorCode(),
						ErrorCode.CS_PARA_ERROR.getMemo() + "：主属性");
			}
			// 超市字段 特性不能为空
			if (!StringUtils.isNotBlank(dataDto.getFeatures())) {
				throw new BleException(ErrorCode.CS_PARA_ERROR.getErrorCode(),
						ErrorCode.CS_PARA_ERROR.getMemo() + "：特性");
			}
		}
		PcmBrand groupBrand = null;
		if (dataDto.isFromPis()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("brandSid", dataDto.getBrandSid());
			map.put("brandType", 1); // 品牌类型（门店）
			map.put("status", 0); // 状态为有效
			List<PcmBrand> brandList = brandMapper.selectListByParam(map);// 门店品牌信息
			if (brandList == null || brandList.size() != 1) {
				// 品牌不存在
				throw new BleException(ErrorCode.BRANDGROUP_NULL.getErrorCode(),
						ErrorCode.BRANDGROUP_NULL.getMemo());
			} else {
				// 查询集团品牌
				map.clear();
				map.put("sid", brandList.get(0).getParentSid());
				map.put("brandType", 0); // 品牌类型（集团）
				map.put("status", 0); // 状态为有效
				List<PcmBrand> gbrandList = brandMapper.selectListByParam(map);// 集团品牌信息
				if (gbrandList == null || gbrandList.size() != 1) {
					// 品牌不存在
					throw new BleException(ErrorCode.BRANDGROUP_NULL.getErrorCode(),
							ErrorCode.BRANDGROUP_NULL.getMemo());
				} else {
					groupBrand = gbrandList.get(0);
				}
			}
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sid", dataDto.getBrandSid());
			map.put("brandType", 0); // 品牌类型（集团）
			map.put("status", 0); // 状态为有效
			List<PcmBrand> gbrandList = brandMapper.selectListByParam(map);// 集团品牌信息
			if (gbrandList == null || gbrandList.size() != 1) {
				// 品牌不存在
				throw new BleException(ErrorCode.BRANDGROUP_NULL.getErrorCode(),
						ErrorCode.BRANDGROUP_NULL.getMemo());
			} else {
				groupBrand = gbrandList.get(0);
			}
		}
		CreateSkuDto cSkuDto = new CreateSkuDto();
		// cSkuDto.setArticleNum(dataDto.getModelNum());// 货号
		cSkuDto.setBrandGroupCode(groupBrand.getBrandSid());// 集团品牌编码
		cSkuDto.setCategoryGYSid(Long.parseLong(dataDto.getProdCategoryCode()));// 工业分类SID
		if (dataDto.getType().equals("1")) {
			cSkuDto.setFeatures(dataDto.getFeatures());// 特性
		}
		cSkuDto.setFlag(Integer.parseInt(dataDto.getType()));// 超市百货标记_0百货_1超市
		cSkuDto.setProColorAlias(dataDto.getColorName());// 色码名称
		cSkuDto.setProColorName(dataDto.getColorCode());// 色码
		if (StringUtils.isNotBlank(dataDto.getProColor())) {
			cSkuDto.setProColorSid(Long.parseLong(dataDto.getProColor()));// 色系表SID
		}
		cSkuDto.setProStanName(dataDto.getSizeCode());// 规格/尺码名称
		cSkuDto.setProStanSid(dataDto.getSizeCode());// 规格/尺码
		if (StringUtils.isNotBlank(dataDto.getProTypeSid())) {
			cSkuDto.setProType(Integer.parseInt(dataDto.getProTypeSid()));// 商品类型
		} else {
			cSkuDto.setProType(1);
		}
		// cSkuDto.setProductName();
		// cSkuDto.setKeyWord();
		// cSkuDto.setMemo();
		// cSkuDto.setOptRealName();
		// cSkuDto.setOptUpdateTime();
		// cSkuDto.setOptUserSid();
		// cSkuDto.setPhotoPlanSid();
		// cSkuDto.setPhotoSaleCodeSid();
		// cSkuDto.setPhotoStatus();
		// cSkuDto.setPlanMaker();
		// cSkuDto.setPlanTime();
		// cSkuDto.setProActiveBit();
		// cSkuDto.setProWriTime();
		// cSkuDto.setSearchKey();
		// cSkuDto.setSellingStatus();
		// cSkuDto.setSizePictureUrl();
		PcmProDetail sku = null;
		// 1.spu验证:
		// 判断超市或百货
		ValidProDetailDto validSpuDto = new ValidProDetailDto();
		validSpuDto.setBrandSid(groupBrand.getBrandSid());// 品牌SID
		validSpuDto.setProStanSid(dataDto.getSizeCode());// 规格/尺码
		if (dataDto.getType().equals(String.valueOf(Constants.PUBLIC_0))) {// 百货字段
			validSpuDto.setProductSku(dataDto.getProductNum());// 款号
			validSpuDto.setProColorName(dataDto.getColorCode());// 色码
			validSpuDto.setProColorSid(dataDto.getProColor());// 色系
		} else {// 超市字段
			validSpuDto.setPrimaryAttr(dataDto.getMainAttribute());// 主属性
			validSpuDto.setFeatures(dataDto.getFeatures());// 特性
			// validSpuDto.setProColorName(dataDto.getColorCode());// 颜色
		}
		PcmProduct pro = validService.validSpuBh(validSpuDto);

		Long spuFlag = 0l;
		Long skuFlag = 0l;
		if (pro != null) {// SPU存在
			// 2.sku验证
			validSpuDto.setProductSid(String.valueOf(pro.getSid()));// 产品表SID
			List<PcmProDetail> proDList = validService.validSku(validSpuDto);
			if (proDList != null && proDList.size() == 1) {// SKU存在
				sku = proDList.get(0);
			} else if (proDList != null && proDList.size() == 0) {// SKU没有/创建SKU-SHOPPRO
				cSkuDto.setProductSid(String.valueOf(pro.getSid()));// 产品表SID
				cSkuDto.setProductName(pro.getProductName());
				sku = createService.insertSKU(cSkuDto);
				skuFlag = sku.getSid();
			} else {
				throw new BleException(ErrorCode.SKU_IS_EXIST1.getErrorCode(),
						ErrorCode.SKU_IS_EXIST1.getMemo());
			}
			// sku.setProductSid(pro.getProductSid());
		} else {// SPU没有/创建SPU-SKU-SHOPPRO
			CreateSpuDto cSpuDto = new CreateSpuDto();
			cSpuDto.setBrandName(groupBrand.getBrandName());// 集团品牌名称
			cSpuDto.setBrandRootSid(String.valueOf(groupBrand.getSid()));// 集团品牌表SID
			cSpuDto.setBrandSid(groupBrand.getBrandSid());// 集团品牌编码
			cSpuDto.setCategoryGYSid(Long.parseLong(dataDto.getProdCategoryCode()));// 工业分类SID
			cSpuDto.setFlag(Integer.parseInt(dataDto.getType()));// 超市百货标识_0百货_1超市
			if (dataDto.getType().equals("0")) {
				cSpuDto.setProductSku(dataDto.getProductNum());// 款号
			} else {
				cSpuDto.setPrimaryAttr(dataDto.getMainAttribute());// 主属性
			}
			cSpuDto.setSeasonCode(dataDto.getSeasonCode());// 季节表SID
			if (dataDto.getCrowdUser() != null && dataDto.getCrowdUser() != ""
					&& dataDto.getCrowdUser() != "null") {
				cSpuDto.setSexSid(Integer.parseInt(dataDto.getCrowdUser()));// 适合人群
			}
			if (StringUtils.isNotBlank(dataDto.getYearToMarket())) {
				cSpuDto.setYearToMarket(dataDto.getYearToMarket());// 上市年份
			}
			// cSpuDto.setActivityFlg();
			// cSpuDto.setAwesome();
			// cSpuDto.setCategorySid();
			// cSpuDto.setCreateTime();
			// cSpuDto.setEditFlag();
			// cSpuDto.setOptUser();
			// cSpuDto.setProActiveBit();
			// cSpuDto.setProDescSid();
			// cSpuDto.setProductName();
			// cSpuDto.setProductNameAlias();
			// cSpuDto.setProPicture();
			// cSpuDto.setProSelling();
			// cSpuDto.setProSellingTime();
			// cSpuDto.setShortDes();
			// cSpuDto.setSpecialDes();
			PcmProduct spu = createService.insertSPU(cSpuDto);
			insertParameter(spu.getSid(), dataDto);
			spuFlag = spu.getSid();
			cSkuDto.setProductSid(String.valueOf(spu.getSid()));
			cSkuDto.setProductName(spu.getProductName());
			sku = createService.insertSKU(cSkuDto);
			skuFlag = sku.getSid();
			// sku.setProductSid(spu.getProductSid());
		}

		// 添加专柜商品
		if (sku != null) {
			dto.setSkuSid(String.valueOf(sku.getSid()));
			dto.setSkuName(sku.getProDetailName());
			dto.setFromPis(true);
			PcmShoppeProduct product = saveShoppeProduct(dto);
			if (product != null) {
				product.setPackUnitDictSid(spuFlag);// 下发用
				product.setMeasureUnitDictSid(skuFlag);// 下发用
			}
			return product;
		} else {
			throw new BleException(ErrorCode.SKU_IS_EXIST.getErrorCode(),
					ErrorCode.SKU_IS_EXIST.getMemo());
		}
	}

	// void insertParameter(String paraJson, Long spuSid, Long cateSid, String
	// cateName)
	// throws Exception {
	// JSONArray jsona = JSONArray.fromObject(paraJson);
	// List list = (List) JSONArray.toList(jsona, HashMap.class);
	// Iterator it = list.iterator();
	// while (it.hasNext()) {
	// Map<String, Object> para = (Map<String, Object>) it.next();
	// PcmProductParameters ppp = new PcmProductParameters();
	// ppp.setProductSid(spuSid);
	// ppp.setCategorySid(cateSid);
	// ppp.setCategoryName(cateName);
	// ppp.setChannelSid(0l);
	// ppp.setPropSid(Long.parseLong((String) para.get("propSid")));
	// ppp.setPropName((String) para.get("propName"));
	// ppp.setValueSid(Long.parseLong((String) para.get("valueSid")));
	// ppp.setValueName((String) para.get("valueName"));
	// ppMapper.insert(ppp);
	// }
	// }
	/**
	 * 商品属性
	 * 
	 * @Methods Name insertParameter
	 * @Create In 2015年9月9日 By zhangxy
	 * @param spuSid
	 * @param dataDto
	 * @throws Exception
	 */
	void insertParameter(Long spuSid, SaveSkuDto dataDto) throws BleException {
		for (ParametersDto dto : dataDto.getParameters()) {
			PcmProductParameters ppp = new PcmProductParameters();
			ppp.setProductSid(spuSid);
			ppp.setCategorySid(Long.valueOf(dataDto.getProdCategoryCode()));
			ppp.setCategoryName(dataDto.getCategoryName());
			ppp.setChannelSid(0l);
			ppp.setCategoryType(0);
			ppp.setPropSid(dto.getPropSid());
			ppp.setPropName(dto.getPropName());
			ppp.setValueSid(dto.getValueSid());
			ppp.setValueName(dto.getValueName());
			Integer i = ppMapper.insertSelective(ppp);
			if (i != 1) {
				throw new BleException(ErrorCode.PARAMETER_INPUT_ERROR.getErrorCode(),
						ErrorCode.PARAMETER_INPUT_ERROR.getMemo());
			}
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

	/**
	 * 条码校验 同一门店下条码不能重复
	 * 
	 * @Methods Name validBarcode
	 * @Create In 2015年9月9日 By zhangxy
	 * @param storeCode
	 * @param barcode
	 * @param type
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
	 * 电商条码校验 （不验证条码格式，只做判重）
	 * 
	 * @Methods Name validBarcodeSap
	 * @Create In 2015年12月10日 By wangc
	 * @param String
	 *            storeCode, String barcode
	 * @return Map<String, Object>
	 * @throws Exception
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

	/**
	 * 插入一条专柜商品 (PIS . OSP)
	 * 
	 * @Methods Name saveShoppeProduct
	 * @Create In 2015年8月24日 By zhangxy
	 * @param SaveShoppeProductPara
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	@Override
	@Transactional
	public PcmShoppeProduct saveShoppeProduct(SaveShoppeProductDto dataDto) throws BleException {

		// 要约
		if (StringUtils.isBlank(dataDto.getOfferNumber())) {
			logger.info("要约不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_OFFERNUMBER.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_OFFERNUMBER.getMemo());
		}
		// 采购
		if (StringUtils.isBlank(dataDto.getProcurementPersonnelNumber())) {
			logger.info("采购人员编号不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_PROCUREMEN.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_PROCUREMEN.getMemo());
		}
		// 录入人员
		if (StringUtils.isBlank(dataDto.getEntryNumber())) {
			logger.info("录入人员编号不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_ENTRY.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_ENTRY.getMemo());
		}
		String source = "OSS";
		if (dataDto.isFromPis()) {
			source = "PIS";
		}

		// 专柜数据校验
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sid", dataDto.getCounterCode());
		map.put("shoppeType", "01");// 查询单品专柜
		List<PcmShoppe> shoppeList = pcmShoppeMapper.selectListByParam(map);
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
		dataDto.setCounterCode(shoppeList.get(0).getShoppeCode());
		// 门店信息
		map.clear();
		map.put("sid", dataDto.getShopCode());
		List<PcmOrganization> orgList = organizationMapper.selectListByParam(map);
		if (orgList == null || orgList.size() != 1) {
			throw new BleException(ErrorCode.SHOP_NULL.getErrorCode(),
					ErrorCode.SHOP_NULL.getMemo());
		}
		// 获取门店品牌
		List<PcmBrand> brandList = null;
		if (StringUtils.isNotBlank(dataDto.getBrandSid())) {
			map.clear();
			map.put("brandSid", dataDto.getBrandSid());
			map.put("brandType", 1); // 品牌类型（门店）
			map.put("status", 0); // 状态为有效
			brandList = brandMapper.selectListByParam(map);
			if (brandList == null || brandList.size() == 0) {
				throw new BleException(ErrorCode.BRAND_NOT_EXIST.getErrorCode(),
						ErrorCode.BRAND_NOT_EXIST.getMemo());
			}
		} else {
			map.clear();
			map.put("skuSid", dataDto.getSkuSid());// skuSid
			map.put("shopSid", orgList.get(0).getSid());// 门店
			brandList = brandMapper.selectBrandByShopSku(map);
			if (brandList == null || brandList.size() == 0) {
				throw new BleException(ErrorCode.BRAND_NOT_EXIST.getErrorCode(),
						ErrorCode.BRAND_NOT_EXIST.getMemo());
			}
		}
		// 门店-门店品牌校验
		if (brandList != null) {
			PcmBrand brand = brandList.get(0);
			map.clear();
			map.put("brandSid", brand.getSid());
			map.put("shopSid", orgList.get(0).getSid());
			List<PcmShopBrandRelation> relations = psbrMapper.selectListByParam(map);
			if (relations == null || relations.size() != 1) {
				throw new BleException(ErrorCode.BRAND_SHOP_RELATION_IS_NULL.getErrorCode(),
						ErrorCode.BRAND_SHOP_RELATION_IS_NULL.getMemo());
			}
		}
		// 供应商数据校验
		map.clear();
		map.put("sid", dataDto.getSupplierCode());
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
		// 验证百货货号
		if ("0".equals(dataDto.getType())
				&& supplyInfoList.get(0).getBusinessPattern() == Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3
				&& StringUtils.isBlank(dataDto.getModelNum())) {
			logger.info("百货联营货号不能为空");
			throw new BleException(ErrorCode.PRO_MODELNUM1_NULL.getErrorCode(),
					ErrorCode.PRO_MODELNUM1_NULL.getMemo());
		}
		// 验证电商货号
		if ("2".equals(dataDto.getType())
				&& supplyInfoList.get(0).getBusinessPattern() == Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3
				&& StringUtils.isBlank(dataDto.getModelNum())) {
			logger.info("电商联营货号不能为空");
			throw new BleException(ErrorCode.PRO_MODELNUM_NULL.getErrorCode(),
					ErrorCode.PRO_MODELNUM_NULL.getMemo());
		}
		// if (supplyInfoList.get(0).getBusinessPattern() ==
		// Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3) {
		// // 当经营方式为联营时 扣率/进价 字段不能为空
		// if (!StringUtils.isNotBlank(dataDto.getRate_price())) {
		// throw new BleException(ErrorCode.LY_PARA_ERROR.getErrorCode(),
		// ErrorCode.LY_PARA_ERROR.getMemo());
		// }
		// } else {
		// // 当经营方式不为联营时 扣率/进价 与 扣率/含税进价 字段不能同时空
		// if (!StringUtils.isNotBlank(dataDto.getRate_price())
		// && !StringUtils.isNotBlank(dataDto.getPurchasePrice_taxRebate()))
		// {
		// throw new BleException(ErrorCode.FLY_PARA_ERROR.getErrorCode(),
		// ErrorCode.FLY_PARA_ERROR.getMemo());
		// }
		// }
		// 获取大码信息
		List<PcmErpProduct> erpList = null;
		if (StringUtils.isNotBlank(dataDto.getErpProductCode())) {
			PcmErpProduct erpEntity = new PcmErpProduct();
			erpEntity.setProductCode(dataDto.getErpProductCode());
			erpEntity.setStoreCode(orgList.get(0).getOrganizationCode());
			erpList = erpProductMapper.selectListByParam(erpEntity);
		}
		// 联营百货扣率码数据校验
		if (dataDto.getType().equals(String.valueOf(Constants.PUBLIC_0))
				&& supplyInfoList.get(0).getBusinessPattern() == Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3) {// 联营百货时
			if (StringUtils.isNotBlank(dataDto.getErpProductCode())) {
				if (erpList == null || erpList.size() != 1) {
					throw new BleException(ErrorCode.DISCOUNTCODE_NULL.getErrorCode(),
							ErrorCode.DISCOUNTCODE_NULL.getMemo());
				}
				if (!erpList.get(0).getShoppeCode().equals(shoppeList.get(0).getShoppeCode())) {
					throw new BleException(
							ErrorCode.DISCOUNTCODE_INFO_ERROR_COUNTERCODE.getErrorCode(),
							ErrorCode.DISCOUNTCODE_INFO_ERROR_COUNTERCODE.getMemo());
				}
				if (!erpList.get(0).getSupplyCode().equals(supplyInfoList.get(0).getSupplyCode())) {
					throw new BleException(
							ErrorCode.DISCOUNTCODE_INFO_ERROR_SUPPLIER.getErrorCode(),
							ErrorCode.DISCOUNTCODE_INFO_ERROR_SUPPLIER.getMemo());
				}
			}
		}
		// 条码验证
		if (dataDto.getBarcodes() != null) {
			for (int index = 0; index < dataDto.getBarcodes().size(); index++) {
				BarcodeDto barcode = dataDto.getBarcodes().get(index);
				validBarcode(orgList.get(0).getOrganizationCode(), barcode.getBarcode());
			}
		}

		// 创建专柜商品DTO
		CreateShoppePro cProDto = new CreateShoppePro();
		cProDto.setField3(dataDto.getModelNum());// 货号
		cProDto.setBrandSid(String.valueOf(brandList.get(0).getSid()));// 品牌表SID
		cProDto.setSupplySid(String.valueOf(supplyInfoList.get(0).getSid()));// 供应商SID
		cProDto.setIsStock(Constants.PUBLIC_0);// 是否管库库存 0 是
		cProDto.setCategorySid(dataDto.getManageCateGory());// 品牌专柜（管理分类编码）
		cProDto.setCategoryTJSid(Long.parseLong(dataDto.getFinalClassiFicationCode()));// 统计分类SID
		cProDto.setDiscountLimit(dataDto.getDiscountLimit());// 折扣底限
		cProDto.setErpproductcode(dataDto.getErpProductCode());// 大码
		cProDto.setFlag(Integer.parseInt(dataDto.getType()));// 百货超市标记,0百货,1超市
		if (StringUtils.isNotBlank(dataDto.getInputTax())) {
			cProDto.setInputTax(new BigDecimal(dataDto.getInputTax()));// 进项税
		}
		if (StringUtils.isNotBlank(dataDto.getMarketPrice())) {
			cProDto.setOriginalPrice(new BigDecimal(dataDto.getMarketPrice()));// 吊牌价
		}
		if (StringUtils.isNotBlank(dataDto.getSalePrice())) {
			cProDto.setSalePrice(new BigDecimal(dataDto.getSalePrice()));// 吊牌价
		}
		if (StringUtils.isNotBlank(dataDto.getOutputTax())) {
			cProDto.setOutputTax(new BigDecimal(dataDto.getOutputTax()));// 销项税
		}
		if (StringUtils.isNotBlank(dataDto.getRate_price())) {
			cProDto.setPurchasePrice(new BigDecimal(dataDto.getRate_price()));// 扣率/进价
		}
		if (StringUtils.isNotBlank(dataDto.getPurchasePrice_taxRebate())) {
			cProDto.setBuyingPrice(new BigDecimal(dataDto.getPurchasePrice_taxRebate()));// 扣率/含税进价
		}
		if (StringUtils.isNotBlank(dataDto.getConsumptionTax())) {
			cProDto.setSalesTax(new BigDecimal(dataDto.getConsumptionTax()));// 消费税
		}

		cProDto.setOriginLand2(dataDto.getCountryOfOrigin());// 原产地
		if (StringUtils.isNotBlank(dataDto.getProcessingType())) {
			cProDto.setProcessType(Integer.parseInt(dataDto.getProcessingType()));// 加工类型
		}
		cProDto.setSaleUnitCode(dataDto.getUnitCode());// 销售单位
		cProDto.setShopCode(orgList.get(0).getOrganizationCode());// 门店编码
		cProDto.setShoppeCode(dataDto.getCounterCode());// 专柜编码
		cProDto.setShoppeProAlias(dataDto.getProductAbbr());// 商品简称
		cProDto.setShoppeProName(dataDto.getProductName());// 专柜商品名称
		cProDto.setShoppeSid(String.valueOf(shoppeList.get(0).getSid()));// 专柜SID
		cProDto.setSupplyCode(supplyInfoList.get(0).getSupplyCode());// 供应商编码

		cProDto.setBarcode(dataDto.getBarcodes());// 条码
		List<BarcodeDto> barcodes = dataDto.getBarcodes();
		if (barcodes != null && barcodes.size() > 0) {
			cProDto.setOriginLand(barcodes.get(0).getOriginLand());// 产地
		}

		// ===================================================================================???????????
		// cProDto.setStanBarcode(dataDto.getStandardBarCode());// 条码
		// cProDto.setSupplyProductCode(dataDto.getStandardBarCode());//供应商商品编码

		cProDto.setRemarks(dataDto.getRemarks());// 备注
		cProDto.setIsAdjustPrice(Integer.parseInt(dataDto.getIsAdjustPrice()));// 是否允许ERP调价
		cProDto.setIsPromotion(Integer.parseInt(dataDto.getIsPromotion()));// 是否允许ERP促销
		cProDto.setProductDetailSid(dataDto.getSkuSid());// sku表SID
		// 判断是否联营
		if (supplyInfoList.get(0).getBusinessPattern() == Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3) {// 联营
			cProDto.setBusinessType(Constants.PUBLIC_1);// 自定义标记自营0,联营1
			cProDto.setOrderType(Constants.PUBLIC_4);// 订货类型
			cProDto.setRateCode(dataDto.getErpProductCode());// 扣率码
			cProDto.setStockMode(Constants.PUBLIC_2);// 库存方式修改为 2 Xk虚库
			if ("0".equals(dataDto.getType())) {
				cProDto.setInputTax(erpList.get(0).getInputTax());// 进项税
				cProDto.setOutputTax(erpList.get(0).getOutputTax());// 销项税
				cProDto.setSalesTax(erpList.get(0).getSalesTax());// 消费税
			}
			// cProDto.setSupplySid(String.valueOf(supplyInfoList.get(0).getSid()));//
			// 供应商SID
		} else {// 其他
			cProDto.setBusinessType(Constants.PUBLIC_0);// 自定义标记自营0,联营1
			cProDto.setOrderType(Constants.PUBLIC_5);// 订货类型
			cProDto.setStockMode(Constants.PUBLIC_1);// 库存方式??????1.ZK自库
			// cProDto.setSupplySid("WFJ");// 供应商SID 非联营商品默认填WFJ
			cProDto.setField2("WFJ");
		}
		// cProDto.setIsCod();//是否支持货到付款
		// cProDto.setIsDiscountable();//是否支持打折
		// cProDto.setIsGift();//赠品类型
		// cProDto.setIsPacking();//是否可包装
		// cProDto.setMeasureUnitDictSid();//计量单位
		// cProDto.setPackUnitDictSid();//包装单位
		// cProDto.setSaleStatus();//商品可售状态
		// cProDto.setShoppeProType();//专柜商品类型:0 普通商品，1 大码商品（默认为0）
		// cProDto.setTmsParam();// 物流属性
		// if (!StringUtils.isNotBlank(cProDto.getShoppeProName())) {//
		// 专柜商品名为空
		// cProDto.setShoppeProName(proD.getProDetailName());
		// }

		// 创建扩展表DTO
		PcmShoppeProductExtend dsPro = new PcmShoppeProductExtend();
		if (cProDto.getBusinessType() == Constants.PUBLIC_1) {// 联营时，扩展表商品类别为8联营单品
			dsPro.setField1("8");
		}
		if (cProDto.getBusinessType() == Constants.PUBLIC_0) {// 自营时，扩展表商品类别为1自营单品
			dsPro.setField1("1");
		}
		dsPro.setField2(cProDto.getOriginalPrice().toString());// 扩展表原价

		PcmShoppeProduct shoppePro = null;
		// 3.专柜pro验证
		ValidShoppeProDto validShoppeProdto = new ValidShoppeProDto();
		validShoppeProdto.setProductDetailSid(dataDto.getSkuSid());// sku表SID
		validShoppeProdto.setShoppeSid(String.valueOf(shoppeList.get(0).getSid()));// 专柜表SID
		validShoppeProdto.setSupplySid(String.valueOf(supplyInfoList.get(0).getSid()));// 供应商表SID
		// 判断百货或超市 超市判重加入销售单位
		if (dataDto.getType().equals(String.valueOf(Constants.PUBLIC_1))) {
			validShoppeProdto.setSaleUnitCode(dataDto.getUnitCode());// 销售单位
		}
		if (supplyInfoList.get(0).getBusinessPattern() == Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3) {
			// 联营时专柜商品 判重-生成 逻辑
			PcmShoppeProduct shopp = validService.validShoppeProBh(validShoppeProdto);
			if (shopp != null) {// 专柜商品存在
				throw new BleException(ErrorCode.SHOPP_IS_EXIST.getErrorCode(),
						ErrorCode.SHOPP_IS_EXIST.getMemo());
			} else {// 专柜商品不存在/创建SHOPPRO
				cProDto.setProductDetailSid(dataDto.getSkuSid());// sku表SID
				if (!StringUtils.isNotBlank(cProDto.getShoppeProName())) {// 专柜商品名为空
					cProDto.setShoppeProName(dataDto.getSkuName());
				}
				shoppePro = createService.insertShoppePro(cProDto);
				// 写入库存
				if (StringUtils.isNotBlank(dataDto.getInventory())) {
					saveInventory(shoppePro.getShoppeProSid(), dataDto.getInventory(), source,
							dataDto.getEntryNumber());
				} else {
					saveInventory(shoppePro.getShoppeProSid(), "0", source,
							dataDto.getEntryNumber());
				}
				// 写入临时表
				if (StringUtils.isNotBlank(dataDto.getEntryNumber())
						&& StringUtils.isNotBlank(dataDto.getProcurementPersonnelNumber())
						&& StringUtils.isNotBlank(dataDto.getOfferNumber())) {
					createService.insertProductInput(shoppePro.getSid(), dataDto.getOfferNumber(),
							dataDto.getEntryNumber(), dataDto.getProcurementPersonnelNumber(),
							dsPro);
				}
				// // 写入标签
				// if (dataDto.getTags() != null && dataDto.getTags().size() !=
				// 0) {
				// createService.insertProductTags(shoppePro.getSid(),
				// dataDto.getTags());
				// }
				// 写入一品多供应商关系表
				PcmShoppeProductSupply psps = new PcmShoppeProductSupply();
				psps.setShoppeProductSid((shoppePro.getSid()));// 专柜商品SID
				psps.setSupplySid((supplyInfoList.get(0).getSid()));// 供应商SID
				insertShoppeProductSupply(psps);
			}
		} else {
			// 非联营时专柜商品 判重-生成 逻辑
			PcmShoppeProduct shopp = validService.validShoppePro(validShoppeProdto);
			if (shopp != null) {// 专柜商品存在--判断是否存在一品多供应商关系
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
					int res = pspsMapper.insertSelective(psps);
					if (res != 1) {
						throw new BleException(
								ErrorCode.SHOPPEPRODUCT_SUPPLY_RELATION_ERROR.getErrorCode(),
								ErrorCode.SHOPPEPRODUCT_SUPPLY_RELATION_ERROR.getMemo());
					}
					shoppePro = shopp;
				}
			} else {// 专柜商品不存在/创建SHOPPRO
				cProDto.setProductDetailSid(String.valueOf(dataDto.getSkuSid()));// sku表SID
				if (!StringUtils.isNotBlank(cProDto.getShoppeProName())) {// 专柜商品名为空
					cProDto.setShoppeProName(dataDto.getSkuName());
				}
				shoppePro = createService.insertShoppePro(cProDto);
				// 写入库存
				if (StringUtils.isNotBlank(dataDto.getInventory())) {
					saveInventory(shoppePro.getShoppeProSid(), dataDto.getInventory(), source,
							dataDto.getEntryNumber());
				} else {
					saveInventory(shoppePro.getShoppeProSid(), "0", source,
							dataDto.getEntryNumber());
				}
				// 写入临时表
				createService.insertProductInput(shoppePro.getSid(), dataDto.getOfferNumber(),
						dataDto.getEntryNumber(), dataDto.getProcurementPersonnelNumber(), dsPro);
				// // 写入标签
				// if (dataDto.getTags() != null && dataDto.getTags().size() !=
				// 0) {
				// createService.insertProductTags(shoppePro.getSid(),
				// dataDto.getTags());
				// }
				// 写入一品多供应商关系表
				PcmShoppeProductSupply psps = new PcmShoppeProductSupply();
				psps.setShoppeProductSid((shoppePro.getSid()));// 专柜商品SID
				psps.setSupplySid((supplyInfoList.get(0).getSid()));// 供应商SID
				insertShoppeProductSupply(psps);
			}
		}
		return shoppePro;
	}

	/**
	 * 插入一条专柜商品(电商)(OSP)
	 * 
	 * @Methods Name saveShoppeProductDs
	 * @Create In 2015年8月24日 By zhangxy
	 * @param SaveShoppeProductPara
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	@Override
	@Transactional
	public PcmShoppeProduct saveShoppeProductDs(SaveShoppeProductDtoDs dataDto) throws BleException {
		// 专柜数据校验
		String source = "OSS";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sid", dataDto.getCounterCode());
		map.put("shoppeType", "01");// 查找单品专柜
		List<PcmShoppe> shoppeList = pcmShoppeMapper.selectListByParam(map);
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
		dataDto.setCounterCode(shoppeList.get(0).getShoppeCode());
		// 门店信息
		map.clear();
		map.put("sid", dataDto.getShopCode());
		List<PcmOrganization> orgList = organizationMapper.selectListByParam(map);
		if (orgList == null || orgList.size() != 1) {
			throw new BleException(ErrorCode.SHOP_NULL.getErrorCode(),
					ErrorCode.SHOP_NULL.getMemo());
		}
		// 获取门店品牌
		List<PcmBrand> brandList = null;
		if (StringUtils.isNotBlank(dataDto.getBrandSid())) {
			map.clear();
			map.put("brandSid", dataDto.getBrandSid());
			map.put("brandType", 1); // 品牌类型（门店）
			map.put("status", 0); // 状态为有效
			brandList = brandMapper.selectListByParam(map);
			if (brandList == null || brandList.size() == 0) {
				throw new BleException(ErrorCode.BRAND_NOT_EXIST.getErrorCode(),
						ErrorCode.BRAND_NOT_EXIST.getMemo());
			}
		} else {
			map.clear();
			map.put("skuSid", dataDto.getSkuSid());// skuSid
			map.put("shopSid", orgList.get(0).getSid());// 门店
			brandList = brandMapper.selectBrandByShopSku(map);
			if (brandList == null || brandList.size() == 0) {
				throw new BleException(ErrorCode.BRAND_NOT_EXIST.getErrorCode(),
						ErrorCode.BRAND_NOT_EXIST.getMemo());
			}
		}
		// 门店-门店品牌关系校验
		if (brandList != null) {
			PcmBrand brand = brandList.get(0);
			map.clear();
			map.put("brandSid", brand.getSid());
			map.put("shopSid", orgList.get(0).getSid());
			List<PcmShopBrandRelation> relations = psbrMapper.selectListByParam(map);
			if (relations == null || relations.size() != 1) {
				throw new BleException(ErrorCode.BRAND_SHOP_RELATION_IS_NULL.getErrorCode(),
						ErrorCode.BRAND_SHOP_RELATION_IS_NULL.getMemo());
			}
		}
		// 供应商数据校验
		map.clear();
		map.put("sid", dataDto.getSupplierCode());
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
		// 验证百货货号
		if ("0".equals(dataDto.getType())
				&& supplyInfoList.get(0).getBusinessPattern() == Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3
				&& StringUtils.isBlank(dataDto.getModelNum())) {
			logger.info("百货联营货号不能为空");
			throw new BleException(ErrorCode.PRO_MODELNUM1_NULL.getErrorCode(),
					ErrorCode.PRO_MODELNUM1_NULL.getMemo());
		}
		// 验证电商货号
		if ("2".equals(dataDto.getType())
				&& supplyInfoList.get(0).getBusinessPattern() == Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3
				&& StringUtils.isBlank(dataDto.getModelNum())) {
			logger.info("电商联营货号不能为空");
			throw new BleException(ErrorCode.PRO_MODELNUM_NULL.getErrorCode(),
					ErrorCode.PRO_MODELNUM_NULL.getMemo());
		}
		// 电商-虚库时判断供应商关系
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
		// 条码验证
		if (dataDto.getBarcodes() != null) {
			for (int index = 0; index < dataDto.getBarcodes().size(); index++) {
				BarcodeDto barcode = dataDto.getBarcodes().get(index);
				validBarcodeSap(orgList.get(0).getOrganizationCode(), barcode.getBarcode());
			}
		}
		// 创建专柜商品DTO
		CreateShoppePro cProDto = new CreateShoppePro();
		cProDto.setField3(dataDto.getModelNum());// 货号
		cProDto.setBrandSid(String.valueOf(brandList.get(0).getSid()));// 品牌表SID
		cProDto.setSupplySid(String.valueOf(supplyInfoList.get(0).getSid()));// 供应商SID
		cProDto.setIsStock(Constants.PUBLIC_0);// 是否管库库存 0 是
		cProDto.setCategorySid(dataDto.getManageCateGory());// 品牌专柜（管理分类编码）
		cProDto.setCategoryTJSid(Long.parseLong(dataDto.getFinalClassiFicationCode()));// 统计分类SID
		cProDto.setDiscountLimit(dataDto.getDiscountLimit());// 折扣底限
		cProDto.setErpproductcode(dataDto.getErpProductCode());// 大码
		cProDto.setFlag(Integer.parseInt(dataDto.getType()));// 百货超市标记,0百货,1超市,2电商
		if (StringUtils.isNotBlank(dataDto.getInputTax())) {
			cProDto.setInputTax(new BigDecimal(dataDto.getInputTax()));// 进项税
		}
		if (StringUtils.isNotBlank(dataDto.getMarketPrice())) {
			cProDto.setOriginalPrice(new BigDecimal(dataDto.getMarketPrice()));// 吊牌价
		}
		if (StringUtils.isNotBlank(dataDto.getSalePrice())) {
			cProDto.setSalePrice(new BigDecimal(dataDto.getSalePrice()));// 吊牌价
		}
		if (StringUtils.isNotBlank(dataDto.getOutputTax())) {
			cProDto.setOutputTax(new BigDecimal(dataDto.getOutputTax()));// 销项税
		}
		if (StringUtils.isNotBlank(dataDto.getRate_price())) {
			cProDto.setBuyingPrice(new BigDecimal(dataDto.getRate_price()));// 扣率/进价
		}
		if (StringUtils.isNotBlank(dataDto.getPurchasePrice_taxRebate())) {
			cProDto.setPurchasePrice(new BigDecimal(dataDto.getPurchasePrice_taxRebate()));// 扣率/含税进价
		}
		if (StringUtils.isNotBlank(dataDto.getConsumptionTax())) {
			cProDto.setSalesTax(new BigDecimal(dataDto.getConsumptionTax()));// 消费税
		}
		// cProDto.setOriginLand(dataDto.getPlaceOfOrigin());// 产地
		cProDto.setOriginLand2(dataDto.getCountryOfOrigin());// 原产地
		if (StringUtils.isNotBlank(dataDto.getProcessingType())) {
			cProDto.setProcessType(Integer.parseInt(dataDto.getProcessingType()));// 加工类型
		}
		cProDto.setSaleUnitCode(dataDto.getUnitCode());// 销售单位
		cProDto.setShopCode(orgList.get(0).getOrganizationCode());// 门店编码
		cProDto.setShoppeCode(dataDto.getCounterCode());// 专柜编码
		cProDto.setShoppeProAlias(dataDto.getProductAbbr());// 商品简称
		cProDto.setShoppeProName(dataDto.getProductName());// 专柜商品名称
		cProDto.setShoppeSid(String.valueOf(shoppeList.get(0).getSid()));// 专柜SID
		cProDto.setSupplyCode(supplyInfoList.get(0).getSupplyCode());// 供应商编码

		cProDto.setBarcode(dataDto.getBarcodes());// 条码

		List<BarcodeDto> barcodes = dataDto.getBarcodes();
		if (barcodes != null && barcodes.size() > 0) {
			cProDto.setOriginLand(barcodes.get(0).getOriginLand());// 产地
		}
		// ===================================================================================???????????
		// cProDto.setStanBarcode(dataDto.getStandardBarCode());// 条码

		cProDto.setRemarks(dataDto.getRemarks());// 备注
		cProDto.setIsAdjustPrice(Integer.parseInt(dataDto.getIsAdjustPrice()));// 是否允许ERP调价
		cProDto.setIsPromotion(Integer.parseInt(dataDto.getIsPromotion()));// 是否允许ERP促销
		cProDto.setProductDetailSid(dataDto.getSkuSid());// sku表SID
		// 判断是否联营
		if (supplyInfoList.get(0).getBusinessPattern() == Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3) {// 联营
			cProDto.setBusinessType(Constants.PUBLIC_1);// 自定义标记自营0,联营1
			cProDto.setOrderType(Constants.PUBLIC_4);// 订货类型
			// cProDto.setRateCode(dataDto.getErpProductCode());// 扣率码
			// cProDto.setStockMode(Constants.PUBLIC_3);// 库存方式??????3.BG不管库存
			// if ("0".equals(dataDto.getType())) {
			// cProDto.setInputTax(erpList.get(0).getInputTax());// 进项税
			// cProDto.setOutputTax(erpList.get(0).getOutputTax());// 销项税
			// cProDto.setSalesTax(erpList.get(0).getSalesTax());// 消费税
			// }
			// cProDto.setSupplySid(String.valueOf(supplyInfoList.get(0).getSid()));//
			// 供应商SID
		} else {// 其他
			cProDto.setBusinessType(Constants.PUBLIC_0);// 自定义标记自营0,联营1
			cProDto.setOrderType(Constants.PUBLIC_5);// 订货类型
			// cProDto.setStockMode(Constants.PUBLIC_1);// 库存方式??????1.ZK自库
			// cProDto.setSupplySid("WFJ");// 供应商SID 非联营商品默认填WFJ
			cProDto.setField2("WFJ");
		}
		try {
			cProDto.setIsCod(Integer.parseInt(dataDto.getIsCod()));// 是否支持货到付款
			cProDto.setTmsParam(Integer.parseInt(dataDto.getTmsParam()));// 物流属性
			if (StringUtils.isNotBlank(dataDto.getIsGift())) {
				cProDto.setIsGift(Integer.parseInt(dataDto.getIsGift()));// 赠品类型
			} else {
				cProDto.setIsGift(0);// 赠品类型
			}
			cProDto.setIsPacking(Integer.parseInt(dataDto.getIsPacking()));// 是否可包装
			cProDto.setStockMode(Integer.parseInt(dataDto.getStockMode()));// 库存方式??????1.ZK自库
		} catch (NumberFormatException e) {
			throw new BleException(ErrorCode.PARA_NORULE_ERROR.getErrorCode(),
					ErrorCode.PARA_NORULE_ERROR.getMemo());
		}
		cProDto.setSupplyProductCode(dataDto.getSupplyProductCode());// 供应商商品编码

		// 电商临时表
		PcmShoppeProductExtend dsPro = new PcmShoppeProductExtend();
		dsPro.setBaseUnitCode(dataDto.getBaseUnitCode());
		dsPro.setIsCard(dataDto.getIsCard());
		dsPro.setIsOriginPackage(dataDto.getIsOriginPackage());
		dsPro.setOriginCountry(dataDto.getOriginCountry());
		dsPro.setXxhcFlag(dataDto.getXxhcFlag());
		if (cProDto.getBusinessType() == Constants.PUBLIC_1) {// 联营时，扩展表商品类别为8联营单品
			dsPro.setField1("8");
		}
		if (cProDto.getBusinessType() == Constants.PUBLIC_0) {// 自营时，扩展表商品类别为1自营单品
			dsPro.setField1("1");
		}
		dsPro.setField2(cProDto.getOriginalPrice().toString());// 扩展表原价
		
		dsPro.setZcolor(dataDto.getZzColorCode());
		dsPro.setZsize(dataDto.getZzSizeCode());
		dsPro.setField4(dataDto.getShoppeProType());
		dsPro.setField5(dataDto.getShelfLife());
		dsPro.setField6(dataDto.getRemainShelLife());
		
		PcmShoppeProduct shoppePro = null;
		// 3.专柜pro验证
		ValidShoppeProDto validShoppeProdto = new ValidShoppeProDto();
		validShoppeProdto.setProductDetailSid(dataDto.getSkuSid());// sku表SID
		validShoppeProdto.setShoppeSid(String.valueOf(shoppeList.get(0).getSid()));// 专柜表SID
		validShoppeProdto.setSupplySid(String.valueOf(supplyInfoList.get(0).getSid()));// 供应商表SID
		// 判断百货或超市 超市判重加入销售单位
		if (dataDto.getType().equals(String.valueOf(Constants.PUBLIC_1))) {
			validShoppeProdto.setSaleUnitCode(dataDto.getUnitCode());// 销售单位
		}
		if (supplyInfoList.get(0).getBusinessPattern() == Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3) {
			// 联营时专柜商品 判重-生成 逻辑
			PcmShoppeProduct shopp = validService.validShoppeProBh(validShoppeProdto);
			if (shopp != null) {// 专柜商品存在
				throw new BleException(ErrorCode.SHOPP_IS_EXIST.getErrorCode(),
						ErrorCode.SHOPP_IS_EXIST.getMemo());
			} else {// 专柜商品不存在/创建SHOPPRO
				cProDto.setProductDetailSid(dataDto.getSkuSid());// sku表SID
				if (!StringUtils.isNotBlank(cProDto.getShoppeProName())) {// 专柜商品名为空
					cProDto.setShoppeProName(dataDto.getSkuName());
				}
				shoppePro = createService.insertShoppePro(cProDto);
				// 写入库存
				if (StringUtils.isNotBlank(dataDto.getInventory())) {
					saveInventory(shoppePro.getShoppeProSid(), dataDto.getInventory(), source,
							dataDto.getEntryNumber());
				} else {
					saveInventory(shoppePro.getShoppeProSid(), "0", source,
							dataDto.getEntryNumber());
				}
				// 写入临时表
				createService.insertProductInput(shoppePro.getSid(), dataDto.getOfferNumber(),
						dataDto.getEntryNumber(), dataDto.getProcurementPersonnelNumber(), dsPro);
				// 写入一品多供应商关系表
				PcmShoppeProductSupply psps = new PcmShoppeProductSupply();
				psps.setShoppeProductSid((shoppePro.getSid()));// 专柜商品SID
				psps.setSupplySid((supplyInfoList.get(0).getSid()));// 供应商SID
				insertShoppeProductSupply(psps);
			}
		} else {
			// 非联营时专柜商品 判重-生成 逻辑
			PcmShoppeProduct shopp = validService.validShoppePro(validShoppeProdto);
			if (shopp != null) {// 专柜商品存在--判断是否存在一品多供应商关系
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
					int res = pspsMapper.insertSelective(psps);
					if (res != 1) {
						throw new BleException(
								ErrorCode.SHOPPEPRODUCT_SUPPLY_RELATION_ERROR.getErrorCode(),
								ErrorCode.SHOPPEPRODUCT_SUPPLY_RELATION_ERROR.getMemo());
					}
					shoppePro = shopp;
				}
			} else {// 专柜商品不存在/创建SHOPPRO
				cProDto.setProductDetailSid(String.valueOf(dataDto.getSkuSid()));// sku表SID
				if (!StringUtils.isNotBlank(cProDto.getShoppeProName())) {// 专柜商品名为空
					cProDto.setShoppeProName(dataDto.getSkuName());
				}
				shoppePro = createService.insertShoppePro(cProDto);
				// 写入库存
				if (StringUtils.isNotBlank(dataDto.getInventory())) {
					saveInventory(shoppePro.getShoppeProSid(), dataDto.getInventory(), source,
							dataDto.getEntryNumber());
				} else {
					saveInventory(shoppePro.getShoppeProSid(), "0", source,
							dataDto.getEntryNumber());
				}
				// 写入临时表
				createService.insertProductInput(shoppePro.getSid(), dataDto.getOfferNumber(),
						dataDto.getEntryNumber(), dataDto.getProcurementPersonnelNumber(), dsPro);
				// 写入一品多供应商关系表
				PcmShoppeProductSupply psps = new PcmShoppeProductSupply();
				psps.setShoppeProductSid((shoppePro.getSid()));// 专柜商品SID
				psps.setSupplySid((supplyInfoList.get(0).getSid()));// 供应商SID
				insertShoppeProductSupply(psps);
			}
		}
		return shoppePro;
	}

	/**
	 * 电商商品导入(SAP批量)
	 * 
	 * @Methods Name saveProductFromSAPERP
	 * @Create In 2015年11月18日 By zhangxy
	 * @param PullProductDto
	 * @return PcmShoppeProduct entity
	 * @throws Exception
	 */
	@Override
	public PcmShoppeProduct saveProductFromSAPERP(PullDataDto dataDto,
			PcmShoppeProductExtend extendDto) throws BleException {
		logger.info("start saveProductFromSAPERP(),param:" + dataDto.toString());
		// 非空与格式参数校验
		validPullDataDtoIsExists2(dataDto, true);
		// 特殊字段校验
		validPullDataDtoIsExists(dataDto);
		if(!StringUtils.isNotBlank(dataDto.getProductCode())){//电商原系统商品编码不能为空
			throw new BleException(ErrorCode.DS_PARA_ERROR.getErrorCode(),
					ErrorCode.DS_PARA_ERROR.getMemo()+"原系统商品编码");
		}
		String source = "SAPERP";
		Map<String, Object> map = new HashMap<String, Object>();
		// 专柜数据校验
		map.clear();
		map.put("shoppeCode", dataDto.getCounterCode());
		map.put("shoppeType", "01");// 判断单品专柜
		List<PcmShoppe> shoppeList = pcmShoppeMapper.selectListByParam(map);
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
		// 验证要约
		map.clear();
		map.put("contractCode", dataDto.getOfferNumber());
		List<PcmContractLog> PcmContractLogs = contractLogMapper.selectListByParam(map);
		if (PcmContractLogs == null || PcmContractLogs.size() != 1) {
			throw new BleException(ErrorCode.CONTRACT_IS_NULL.getErrorCode(),
					ErrorCode.CONTRACT_IS_NULL.getMemo());
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
		// if (dataDto.getOperateMode()
		// .equals(String.valueOf(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3))) {//
		// 联营
		// PcmShoppeSupplierQueryDto dto = new PcmShoppeSupplierQueryDto();
		// dto.setShoppeSid(String.valueOf(shoppeList.get(0).getSid()));//
		// 专柜表SID
		// dto.setSupplySid(String.valueOf(supplyInfoList.get(0).getSid()));//
		// 供应商表SID
		// List<PcmShoppeSupplierResultDto> ResultDtos =
		// supplyShoppeMapper.findShoppeSupplierInfoByParam(dto);
		// if(ResultDtos == null || ResultDtos.size() != Constants.PUBLIC_1){
		// throw new
		// BleException(ErrorCode.ADD_PRODUCT_SUPPLY_SHOPPE_ERROR.getErrorCode(),
		// ErrorCode.ADD_PRODUCT_SUPPLY_SHOPPE_ERROR.getMemo());
		// }
		// }
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
			// 门店-门店品牌关系验证
			map.clear();
			map.put("brandSid", brand.getSid());
			map.put("shopSid", org.getSid());
			List<PcmShopBrandRelation> relations = psbrMapper.selectListByParam(map);
			if (relations == null || relations.size() != 1) {
				throw new BleException(ErrorCode.BRAND_SHOP_RELATION_IS_NULL.getErrorCode(),
						ErrorCode.BRAND_SHOP_RELATION_IS_NULL.getMemo());
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
		// 管理分类字典校验
		map.clear();
		map.put("categoryCode", dataDto.getManageCateGory());
		map.put("categoryType", Constants.MANAGECATEGORY);// 分类类型为 1 管理分类
		//map.put("isLeaf", Constants.Y);// 是否为叶子节点
		map.put("isDisplay", "1");//是否展示
		map.put("status", Constants.Y);// 是否启用
		List<PcmCategory> managecateList = categoryMapper.selectListByParam(map);
		if (managecateList == null || managecateList.size() != 1) {
			throw new BleException(ErrorCode.CATEGORY_GL_NULL.getErrorCode(),
					ErrorCode.CATEGORY_GL_NULL.getMemo());
		}
		// 工业分类字典校验
		map.clear();
		map.put("categoryCode", dataDto.getProdCategoryCode());
		map.put("categoryType", Constants.INDUSTRYTCATEGORY);// 分类类型为 0 工业分类
		map.put("isLeaf", Constants.Y);// 是否为叶子节点
		map.put("status", Constants.Y);// 是否启用
		List<PcmCategory> cateList = categoryMapper.selectListByParam(map);
		if (cateList == null || cateList.size() != 1) {
			throw new BleException(ErrorCode.CATEGORY_GY_NULL.getErrorCode(),
					ErrorCode.CATEGORY_GY_NULL.getMemo());
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
		if (!dataDto.getType().equals(String.valueOf(Constants.PUBLIC_1))) {
			// 色系字典数据缺失
			map.clear();
			map.put("sid", dataDto.getProColor());
			colorList = colorDictMapper.selectListByParam(map);
			if (colorList == null || colorList.size() != 1) {
				throw new BleException(ErrorCode.COLOR_NULL.getErrorCode(),
						ErrorCode.COLOR_NULL.getMemo());
			}
		}
		// 条码校验 同一门店下条码不能重复
		if (StringUtils.isNotBlank(dataDto.getStandardBarCode())) {
			validBarcodeSap(org.getOrganizationCode(), dataDto.getStandardBarCode());
		}

		// 创建专柜商品DTO
		CreateShoppePro cProDto = new CreateShoppePro();
		// 品牌表SID
		if (brand != null) {
			cProDto.setBrandSid(String.valueOf(brand.getSid()));
		} else {
			cProDto.setBrandSid(String.valueOf(brandList.get(0).getSid()));
		}
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
		// cProDto.setDiscountLimit(dataDto.getDiscountLimit());// 折扣底限
		// cProDto.setErpproductcode(dataDto.getErpProductCode());// 大码
		cProDto.setFlag(Integer.parseInt(dataDto.getType()));// 百货超市标记,0百货,1超市,2电商
		cProDto.setOfferNo(dataDto.getOfferNumber());// 要约号
		cProDto.setSalePrice(new BigDecimal(dataDto.getSalePrice()));// 售价
		cProDto.setOriginalPrice(new BigDecimal(dataDto.getMarketPrice()));// 吊牌价
		// cProDto.setOriginLand(dataDto.getPlaceOfOrigin());// 产地
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
		/*
		 * if (dataDto.getType().equals(String.valueOf(Constants.PUBLIC_0))) {//
		 * 百货 cProDto.setShoppeProName(dataDto.getProductName());// 专柜商品名称 }
		 * else {// 超市
		 * cProDto.setShoppeProName(dataDto.getRegisteredTradeName());// 专柜商品名称
		 * }
		 */
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
		// cProDto.setRemarks(dataDto.getRemarks());// 备注
		// if (dataDto.getIsAdjustPrice() != null &&
		// dataDto.getIsAdjustPrice().equals(Constants.Y)) {
		// cProDto.setIsAdjustPrice(Constants.PUBLIC_0);// 是否允许ERP调价
		// } else if (dataDto.getIsAdjustPrice() != null
		// && dataDto.getIsAdjustPrice().equals(Constants.N)) {
		// cProDto.setIsAdjustPrice(Constants.PUBLIC_1);// 是否允许ERP调价
		// }
		// if (dataDto.getIsPromotion() != null &&
		// dataDto.getIsPromotion().equals(Constants.Y)) {
		// cProDto.setIsPromotion(Constants.PUBLIC_0);// 是否允许ERP促销
		// } else if (dataDto.getIsPromotion() != null &&
		// dataDto.getIsPromotion().equals(Constants.N)) {
		// cProDto.setIsPromotion(Constants.PUBLIC_1);// 是否允许ERP促销
		// }
		if (StringUtils.isNotBlank(dataDto.getRate_price())) {
			cProDto.setBuyingPrice(new BigDecimal(dataDto.getRate_price()));// 扣率/进价
		}
		cProDto.setField3(dataDto.getModelNum());// 货号
		cProDto.setIsCod(Integer.parseInt(dataDto.getIsCOD()));// 是否支持货到付款
		// cProDto.setIsGift(Integer.parseInt(dataDto.getIsGift()));// 赠品类型
		cProDto.setSaleStatus(Integer.parseInt(dataDto.getIsSale()));// 商品可售状态
		if (StringUtils.isNotBlank(dataDto.getTmsParam()))
			cProDto.setTmsParam(Integer.parseInt(dataDto.getTmsParam()));// 物流属性
		if (StringUtils.isNotBlank(dataDto.getIsPacking()))
			cProDto.setIsPacking(Integer.parseInt(dataDto.getIsPacking()));// 是否可包装
		cProDto.setField4(dataDto.getProductCode());// 原系统商品编码
		// cProDto.setIsDiscountable();//是否支持打折
		// cProDto.setMeasureUnitDictSid();//计量单位
		// cProDto.setPackUnitDictSid();//包装单位
		// cProDto.setShoppeProType();//专柜商品类型:0 普通商品，1 大码商品（默认为0）

		CreateSkuDto cSkuDto = new CreateSkuDto();
		// cSkuDto.setArticleNum(dataDto.getModelNum());// 货号
		cSkuDto.setBrandGroupCode(brandList.get(0).getBrandSid());// 集团品牌编码
		cSkuDto.setBrandShopCode(brand.getBrandSid());// 门店品牌编码
		// cSkuDto.setCategoryGYSid(cateList.get(0).getSid());// 管理分类编码
		// cSkuDto.setFeatures(dataDto.getFeatures());// 特性
		cSkuDto.setFlag(Integer.parseInt(dataDto.getType()));// 超市百货标记_0百货_1超市_2电商
		cSkuDto.setProColorAlias(dataDto.getColorCode());// 色码名称
		cSkuDto.setProColorName(dataDto.getColorCode());// 色码
		if (colorList != null) {
			cSkuDto.setProColorSid(colorList.get(0).getSid());// 色系表SID
		}
		cSkuDto.setProStanName(dataDto.getSizeCode());// 规格/尺码名称
		cSkuDto.setProStanSid(dataDto.getSizeCode());// 规格/尺码
		cSkuDto.setProType(1);
		// cSkuDto.setProductName();
		// cSkuDto.setKeyWord();
		// cSkuDto.setMemo();
		// cSkuDto.setOptRealName();
		// cSkuDto.setOptUpdateTime();
		// cSkuDto.setOptUserSid();
		// cSkuDto.setPhotoPlanSid();
		// cSkuDto.setPhotoSaleCodeSid();
		// cSkuDto.setPhotoStatus();
		// cSkuDto.setPlanMaker();
		// cSkuDto.setPlanTime();
		// cSkuDto.setProActiveBit();
		// cSkuDto.setProWriTime();
		// cSkuDto.setSearchKey();
		// cSkuDto.setSellingStatus();
		// cSkuDto.setSizePictureUrl();

		PcmShoppeProduct shoppePro = null;
		// 1.spu验证:
		// 判断超市或百货
		ValidProDetailDto validSpuDto = new ValidProDetailDto();
		validSpuDto.setBrandSid(String.valueOf(brandList.get(0).getBrandSid()));// 品牌SID
		validSpuDto.setProStanSid(dataDto.getSizeCode());// 规格/尺码
		if (!dataDto.getType().equals(String.valueOf(Constants.PUBLIC_1))) {// 非超市商品
			validSpuDto.setProductSku(dataDto.getProductNum());// 款号
			validSpuDto.setProColorName(dataDto.getColorCode());// 色码
			validSpuDto.setProColorSid(dataDto.getProColor());// 色系
		} else {// 超市商品
			validSpuDto.setPrimaryAttr(dataDto.getMainAttribute());// 主属性
			validSpuDto.setFeatures(dataDto.getFeatures());// 特性
			// validSpuDto.setProColorName(dataDto.getColorCode());// 颜色
		}
		PcmProduct pro = validService.validSpuBh(validSpuDto);
		Long spuFlag = 0l;
		Long skuFlag = 0l;
		if (pro != null) {// SPU存在
			// 2.sku验证
			validSpuDto.setProductSid(String.valueOf(pro.getSid()));// 产品表SID
			List<PcmProDetail> proDList = validService.validSku(validSpuDto);
			if (proDList != null && proDList.size() == 1) {// SKU存在
				PcmProDetail proD = proDList.get(0);
				// 3.专柜pro验证
				ValidShoppeProDto validShoppeProdto = new ValidShoppeProDto();
				validShoppeProdto.setProductDetailSid(String.valueOf(proD.getSid()));// sku表SID
				validShoppeProdto.setShoppeSid(String.valueOf(shoppeList.get(0).getSid()));// 专柜表SID
				validShoppeProdto.setSupplySid(String.valueOf(supplyInfoList.get(0).getSid()));// 供应商表SID
				if (StringUtils.isNotBlank(dataDto.getStandardBarCode())) {
					validShoppeProdto.setBarcode(dataDto.getStandardBarCode());// 条码
					validShoppeProdto.setStoreCode(org.getOrganizationCode());// 门店编码
				}
				if (dataDto.getOperateMode().equals(
						String.valueOf(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3))) {
					// 联营时专柜商品 判重-生成 逻辑
					PcmShoppeProduct shopp = validService.validShoppeProBh(validShoppeProdto);
					if (shopp != null) {// 专柜商品存在
						throw new BleException(ErrorCode.SHOPP_IS_EXIST.getErrorCode(),
								ErrorCode.SHOPP_IS_EXIST.getMemo());
					} else {// 专柜商品不存在/创建SHOPPRO
						cProDto.setProductDetailSid(String.valueOf(proD.getSid()));// sku表SID
						if (!StringUtils.isNotBlank(cProDto.getShoppeProName())) {// 专柜商品名为空
							String shoppeProName = brand.getBrandName() + dataDto.getProductDesc()
									+ dataDto.getColorCode() + dataDto.getSizeCode();
							/*
							 * List<String> paras = new ArrayList<String>();//
							 * paras.add(brand.getBrandName());
							 * paras.add(dataDto.getProductDesc());
							 * paras.add(dataDto.getColorCode());
							 * paras.add(dataDto.getSizeCode()); String
							 * shoppeProName =
							 * generateStandardName(paras,Constants
							 * .SEPARATOR);//生成标准品名
							 */cProDto.setShoppeProName(shoppeProName);// 专柜商品名称
							// cProDto.setShoppeProName(proD.getProDetailName());
							cProDto.setShoppeProAlias(proD.getProDetailName());
						}
						shoppePro = createService.insertShoppePro(cProDto);
						// // 写入库存
						// if (StringUtils.isNotBlank(dataDto.getInventory())) {
						// saveInventory(shoppePro.getShoppeProSid(),
						// dataDto.getInventory(),
						// source, dataDto.getEntryNumber());
						// } else {
						// saveInventory(shoppePro.getShoppeProSid(), "0",
						// source,
						// dataDto.getEntryNumber());
						// }
						// 写入临时表
						createService.insertProductInput(shoppePro.getSid(),
								dataDto.getOfferNumber(), dataDto.getEntryNumber(),
								dataDto.getProcurementPersonnelNumber(), extendDto);
						// 写入一品多供应商关系表
						PcmShoppeProductSupply psps = new PcmShoppeProductSupply();
						psps.setShoppeProductSid((shoppePro.getSid()));// 专柜商品SID
						psps.setSupplySid(supplyInfoList.get(0).getSid());// 供应商SID
						psps.setShopSid(org.getOrganizationCode());//一品多商加门店编码
						insertShoppeProductSupply(psps);
					}
				} else {
					// 非联营时专柜商品 判重-生成 逻辑
					PcmShoppeProduct shopp = validService.validShoppePro(validShoppeProdto);
					if (shopp != null) {// 专柜商品存在--判断是否存在一品多供应商关系
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
					} else {// 专柜商品不存在/创建SHOPPRO
						cProDto.setProductDetailSid(String.valueOf(proD.getSid()));// sku表SID
						if (!StringUtils.isNotBlank(cProDto.getShoppeProName())) {// 专柜商品名为空
							String shoppeProName = brand.getBrandName() + dataDto.getProductDesc()
									+ dataDto.getColorCode() + dataDto.getSizeCode();
							/*
							 * List<String> paras = new ArrayList<String>();//
							 * paras.add(brand.getBrandName());
							 * paras.add(dataDto.getProductDesc());
							 * paras.add(dataDto.getColorCode());
							 * paras.add(dataDto.getSizeCode()); String
							 * shoppeProName =
							 * generateStandardName(paras,Constants
							 * .SEPARATOR);//生成标准品名
							 */cProDto.setShoppeProName(shoppeProName);// 专柜商品名称
							// cProDto.setShoppeProName(proD.getProDetailName());
							cProDto.setShoppeProAlias(proD.getProDetailName());
						}
						shoppePro = createService.insertShoppePro(cProDto);
						// 写入库存
						// if (StringUtils.isNotBlank(dataDto.getInventory())) {
						// saveInventory(shoppePro.getShoppeProSid(),
						// dataDto.getInventory(),
						// source, dataDto.getEntryNumber());
						// } else {
						// saveInventory(shoppePro.getShoppeProSid(), "0",
						// source,
						// dataDto.getEntryNumber());
						// }
						// 写入临时表
						createService.insertProductInput(shoppePro.getSid(),
								dataDto.getOfferNumber(), dataDto.getEntryNumber(),
								dataDto.getProcurementPersonnelNumber(), extendDto);
						// 写入一品多供应商关系表
						PcmShoppeProductSupply psps = new PcmShoppeProductSupply();
						psps.setShoppeProductSid((shoppePro.getSid()));// 专柜商品SID
						psps.setSupplySid((supplyInfoList.get(0).getSid()));// 供应商SID
						psps.setShopSid(org.getOrganizationCode());//一品多商加门店编码
						insertShoppeProductSupply(psps);
					}
				}
			} else if (proDList != null && proDList.size() == 0) {// SKU没有/创建SKU-SHOPPRO
				cSkuDto.setProductSid(String.valueOf(pro.getSid()));// 产品表SID
				cSkuDto.setProductName(pro.getProductName());
				PcmProDetail sku = createService.insertSKU(cSkuDto);
				skuFlag = sku.getSid();
				cProDto.setProductDetailSid(String.valueOf(sku.getSid()));
				if (!StringUtils.isNotBlank(cProDto.getShoppeProName())) {// 专柜商品名为空
					String shoppeProName = brand.getBrandName() + dataDto.getProductDesc()
							+ dataDto.getColorCode() + dataDto.getSizeCode();
					/*
					 * List<String> paras = new ArrayList<String>();//
					 * paras.add(brand.getBrandName());
					 * paras.add(dataDto.getProductDesc());
					 * paras.add(dataDto.getColorCode());
					 * paras.add(dataDto.getSizeCode()); String shoppeProName =
					 * generateStandardName(paras,Constants.SEPARATOR);//生成标准品名
					 */cProDto.setShoppeProName(shoppeProName);// 专柜商品名称
					// cProDto.setShoppeProName(sku.getProDetailName());
				}
				shoppePro = createService.insertShoppePro(cProDto);
				// 写入库存
				if (StringUtils.isNotBlank(dataDto.getInventory())) {
					saveInventory(shoppePro.getShoppeProSid(), dataDto.getInventory(), source,
							dataDto.getEntryNumber());
				} else {
					saveInventory(shoppePro.getShoppeProSid(), "0", source,
							dataDto.getEntryNumber());
				}
				// 写入临时表
				createService.insertProductInput(shoppePro.getSid(), dataDto.getOfferNumber(),
						dataDto.getEntryNumber(), dataDto.getProcurementPersonnelNumber(),
						extendDto);
				// 写入一品多供应商关系表
				PcmShoppeProductSupply psps = new PcmShoppeProductSupply();
				psps.setShoppeProductSid((shoppePro.getSid()));// 专柜商品SID
				psps.setSupplySid((supplyInfoList.get(0).getSid()));// 供应商SID
				psps.setShopSid(org.getOrganizationCode());//一品多商加门店编码
				insertShoppeProductSupply(psps);
			} else {
				throw new BleException(ErrorCode.SKU_IS_EXIST1.getErrorCode(),
						ErrorCode.SKU_IS_EXIST1.getMemo());
			}
		} else {// SPU没有/创建SPU-SKU-SHOPPRO
			CreateSpuDto cSpuDto = new CreateSpuDto();
			cSpuDto.setBrandName(brandList.get(0).getBrandName());// 集团品牌名称
			cSpuDto.setBrandRootSid(String.valueOf(brandList.get(0).getSid()));// 集团品牌表SID
			cSpuDto.setBrandSid(brandList.get(0).getBrandSid());// 集团品牌编码
			cSpuDto.setCategoryGYSid(cateList.get(0).getSid());// 工业分类SID
			cSpuDto.setFlag(Integer.parseInt(dataDto.getType()));// 超市百货标识_0百货_1超市
			// cSpuDto.setPrimaryAttr(dataDto.getMainAttribute());// 主属性
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
			cSpuDto.setShortDes(dataDto.getProductDesc());// 商品描述
			// cSpuDto.setActivityFlg();
			// cSpuDto.setAwesome();
			// cSpuDto.setCategorySid();
			// cSpuDto.setCreateTime();
			// cSpuDto.setEditFlag();
			// cSpuDto.setOptUser();
			// cSpuDto.setProActiveBit();
			// cSpuDto.setProDescSid();
			// cSpuDto.setProductName();
			// cSpuDto.setProductNameAlias();
			// cSpuDto.setProPicture();
			// cSpuDto.setProSelling();
			// cSpuDto.setProSellingTime();
			// cSpuDto.setShortDes();
			// cSpuDto.setSpecialDes();
			PcmProduct spu = createService.insertSPU(cSpuDto);
			spuFlag = spu.getSid();
			cSkuDto.setProductSid(String.valueOf(spu.getSid()));
			cSkuDto.setProductName(spu.getProductName());
			PcmProDetail sku = createService.insertSKU(cSkuDto);
			skuFlag = sku.getSid();
			cProDto.setProductDetailSid(String.valueOf(sku.getSid()));
			if (!StringUtils.isNotBlank(cProDto.getShoppeProName())) {// 专柜商品名为空
				String shoppeProName = brand.getBrandName() + dataDto.getProductDesc()
						+ dataDto.getColorCode() + dataDto.getSizeCode();
				/*
				 * List<String> paras = new ArrayList<String>();//
				 * paras.add(brand.getBrandName());
				 * paras.add(dataDto.getProductDesc());
				 * paras.add(dataDto.getColorCode());
				 * paras.add(dataDto.getSizeCode()); String shoppeProName =
				 * generateStandardName(paras,Constants.SEPARATOR);//生成标准品名
				 */cProDto.setShoppeProName(shoppeProName);// 专柜商品名称
				// cProDto.setShoppeProName(sku.getProDetailName());
			}
			shoppePro = createService.insertShoppePro(cProDto);
			// 写入库存
			if (StringUtils.isNotBlank(dataDto.getInventory())) {
				saveInventory(shoppePro.getShoppeProSid(), dataDto.getInventory(), source,
						dataDto.getEntryNumber());
			} else {
				saveInventory(shoppePro.getShoppeProSid(), "0", source, dataDto.getEntryNumber());
			}
			// 写入临时表
			createService.insertProductInput(shoppePro.getSid(), dataDto.getOfferNumber(),
					dataDto.getEntryNumber(), dataDto.getProcurementPersonnelNumber(), extendDto);
			// 写入一品多供应商关系表
			PcmShoppeProductSupply psps = new PcmShoppeProductSupply();
			psps.setShoppeProductSid((shoppePro.getSid()));// 专柜商品SID
			psps.setSupplySid((supplyInfoList.get(0).getSid()));// 供应商SID
			psps.setShopSid(org.getOrganizationCode());//一品多商加门店编码
			insertShoppeProductSupply(psps);
		}
		if (shoppePro != null) {
			shoppePro.setPackUnitDictSid(spuFlag);
			shoppePro.setMeasureUnitDictSid(skuFlag);
		}
		logger.info("end saveProductFromSAPERP()");
		return shoppePro;
	}

	/**
	 * 修改合同
	 */
	@Override
	public void updateProductContract(String productCode, String matrn, String contract)
			throws BleException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("contractCode", contract);
		paramMap.put("businessType", 2);
		List<PcmContractLog> list = contractLogMapper.selectListByParam(paramMap);
		if (list == null || list.size() == 0) {
			logger.error(ErrorCode.CONTRACT_RESULT_NULL_ERROR.getMemo());
			throw new BleException(ErrorCode.CONTRACT_RESULT_NULL_ERROR.getErrorCode(),
					ErrorCode.CONTRACT_RESULT_NULL_ERROR.getMemo());
		}
		paramMap.put("shoppeProSid", productCode);
		List<PcmShoppeProduct> list2 = pcmShoppeProductMapper.selectListByParam(paramMap);
		if (list2 == null || list2.size() == 0) {
			logger.error(ErrorCode.PRO_INFO_NO_EXIST.getMemo());
			throw new BleException(ErrorCode.PRO_INFO_NO_EXIST.getErrorCode(),
					ErrorCode.PRO_INFO_NO_EXIST.getMemo());
		}
		PcmShoppeProduct psp = list2.get(0);
		if (StringUtils.isBlank(psp.getField4())) {
			logger.error(ErrorCode.NOT_SAP_PRODUCT.getMemo());
			throw new BleException(ErrorCode.NOT_SAP_PRODUCT.getErrorCode(),
					ErrorCode.NOT_SAP_PRODUCT.getMemo());
		}
		if (!matrn.equals(psp.getField4())) {
			logger.error(ErrorCode.CONTRACT_PRODUCT_ERROR.getMemo());
			throw new BleException(ErrorCode.CONTRACT_PRODUCT_ERROR.getErrorCode(),
					ErrorCode.CONTRACT_PRODUCT_ERROR.getMemo());
		}
		PcmProInput ppi = new PcmProInput();
		ppi.setContractCode(contract);
		ppi.setShoppeProSid(psp.getSid());
		int i = inputMapper.updateByProSidSelective(ppi);
		if (i == 0) {
			logger.error(ErrorCode.CONTRACT_UPDATE_ERROR.getMemo());
			throw new BleException(ErrorCode.CONTRACT_UPDATE_ERROR.getErrorCode(),
					ErrorCode.CONTRACT_UPDATE_ERROR.getMemo());
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

	/**
	 * 供应商批量数据导入
	 */
	@Override
	@Transactional
	public PcmShoppeProduct savePullProductFromSupllier(PullDataDto dataDto) throws BleException {
		logger.info("start savePullProductFromSupllier(),param:" + dataDto.toString());
		// 非空与格式参数校验
		if(dataDto.getType().equals(String.valueOf(Constants.PCMBRAND_SHOP_TYPE_EBUSINESS))){//电商商品
			validPullDataDtoIsExistsSupShoPro(dataDto, true);
		}else{//非电商商品
			validPullDataDtoIsExists2(dataDto, false);
		}
		// 特殊字段校验
		validPullDataDtoIsExists(dataDto);
		String source = "SUP";
		Map<String, Object> map = new HashMap<String, Object>();
		List<PcmShoppe> shoppeList = new ArrayList<PcmShoppe>();
		if(dataDto.getType().equals(String.valueOf(Constants.PCMBRAND_SHOP_TYPE_EBUSINESS))){
			//获取专柜 电商商品不传专柜编码,需要用供应商编码和门店编码获取对应专柜
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
			// map.put("shoppeType", "01"); // 查找单品专柜
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
		//电商不传经营方式, 所传经营方式与对应供应商一致
		if(StringUtils.isBlank(dataDto.getOperateMode())){
			dataDto.setOperateMode(supplyInfoList.get(0).getBusinessPattern().toString());
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
		/*if (StringUtils.isNotBlank(dataDto.getStandardBarCode())) {
			validBarcode(org.getOrganizationCode(), dataDto.getStandardBarCode());
		}*/
		// 条码校验 同一门店下条码不能重复
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
		// 创建专柜商品DTO
		CreateShoppePro cProDto = new CreateShoppePro();
		// 品牌表SID
		if (brand != null) {
			cProDto.setBrandSid(String.valueOf(brand.getSid()));
		} else {
			cProDto.setBrandSid(String.valueOf(brandList.get(0).getSid()));
		}
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
		cProDto.setIsCod(Integer.parseInt(dataDto.getIsCOD()));// 是否支持货到付款
		cProDto.setCategoryTJSid(tjcateList.get(0).getSid());// 统计分类SID
		cProDto.setSaleStatus(Integer.parseInt(dataDto.getIsSale()));// 商品可售状态
		
		CreateSkuDto cSkuDto = new CreateSkuDto();
		// cSkuDto.setArticleNum(dataDto.getModelNum());// 货号
		cSkuDto.setBrandGroupCode(brandList.get(0).getBrandSid());// 集团品牌编码
		cSkuDto.setBrandShopCode(brand.getBrandSid());// 门店品牌编码
		cSkuDto.setCategoryGYSid(cateList.get(0).getSid());// 管理分类编码
		cSkuDto.setFeatures(dataDto.getFeatures());// 特性
		cSkuDto.setFlag(Integer.parseInt(dataDto.getType()));// 超市百货标记_0百货_1超市
		cSkuDto.setProColorAlias(dataDto.getColorName());// 色码名称
		cSkuDto.setProColorName(dataDto.getColorCode());// 色码
		if (colorList != null) {
			cSkuDto.setProColorSid(colorList.get(0).getSid());// 色系表SID
		}
		cSkuDto.setProStanName(dataDto.getSizeCode());// 规格/尺码名称
		cSkuDto.setProStanSid(dataDto.getSizeCode());// 规格/尺码
		cSkuDto.setProType(1);
		// 扩展表DTO
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
			//dsPro.setField12(dataDto.getSalesStatus());//销售状态
			dsPro.setZcolor(dataDto.getZcolor());//特性色码
			dsPro.setZsize(dataDto.getZsize());//特性尺码
		}
		PcmShoppeProduct shoppePro = null;
		// 1.spu验证:
		// 判断超市或百货
		ValidProDetailDto validSpuDto = new ValidProDetailDto();
		validSpuDto.setBrandSid(String.valueOf(brandList.get(0).getBrandSid()));// 品牌SID
		validSpuDto.setProStanSid(dataDto.getSizeCode());// 规格/尺码
		if (dataDto.getType().equals(String.valueOf(Constants.PUBLIC_0))) {// 百货字段
			validSpuDto.setProductSku(dataDto.getProductNum());// 款号
			validSpuDto.setProColorName(dataDto.getColorCode());// 色码
			validSpuDto.setProColorSid(colorList.get(0).getSid().toString());// 色系SID
		} else {// 超市字段
			validSpuDto.setPrimaryAttr(dataDto.getMainAttribute());// 主属性
			validSpuDto.setFeatures(dataDto.getFeatures());// 特性
		}
		PcmProduct pro = validService.validSpuBh(validSpuDto);
		Long spuFlag = 0l;
		Long skuFlag = 0l;
		if (pro != null) {// SPU存在
			// 2.sku验证
			validSpuDto.setProductSid(String.valueOf(pro.getSid()));// 产品表SID
			List<PcmProDetail> proDList = validService.validSku(validSpuDto);
			if (proDList != null && proDList.size() == 1) {// SKU存在
				PcmProDetail proD = proDList.get(0);
				// 3.专柜pro验证
				ValidShoppeProDto validShoppeProdto = new ValidShoppeProDto();
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
				if (dataDto.getOperateMode().equals(
						String.valueOf(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3))) {
					// 联营时专柜商品 判重-生成 逻辑
					PcmShoppeProduct shopp = validService.validShoppeProBh(validShoppeProdto);
					if (shopp != null) {// 专柜商品存在
						throw new BleException(ErrorCode.SHOPP_IS_EXIST.getErrorCode(),
								ErrorCode.SHOPP_IS_EXIST.getMemo());
					} else {// 专柜商品不存在/创建SHOPPRO
						cProDto.setProductDetailSid(String.valueOf(proD.getSid()));// sku表SID
						if (!StringUtils.isNotBlank(cProDto.getShoppeProName())) {// 专柜商品名为空
							cProDto.setShoppeProName(proD.getProDetailName());
						}
						shoppePro = createService.insertShoppePro(cProDto);
						// 写入库存
						if (StringUtils.isNotBlank(dataDto.getInventory())) {
							saveInventory(shoppePro.getShoppeProSid(), dataDto.getInventory(),
									source, dataDto.getEntryNumber());
						} else {
							saveInventory(shoppePro.getShoppeProSid(), "0", source,
									dataDto.getEntryNumber());
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
						insertShoppeProductSupply(psps);
					}
				} else {
					// 非联营时专柜商品 判重-生成 逻辑
					PcmShoppeProduct shopp = validService.validShoppePro(validShoppeProdto);
					if (shopp != null) {// 专柜商品存在--判断是否存在一品多供应商关系
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
							int res = pspsMapper.insertSelective(psps);
							if (res != 1) {
								throw new BleException(
										ErrorCode.SHOPPEPRODUCT_SUPPLY_RELATION_ERROR
												.getErrorCode(),
										ErrorCode.SHOPPEPRODUCT_SUPPLY_RELATION_ERROR.getMemo());
							}
						}
						shoppePro = shopp;
					} else {// 专柜商品不存在/创建SHOPPRO
						cProDto.setProductDetailSid(String.valueOf(proD.getSid()));// sku表SID
						if (!StringUtils.isNotBlank(cProDto.getShoppeProName())) {// 专柜商品名为空
							cProDto.setShoppeProName(proD.getProDetailName());
						}
						shoppePro = createService.insertShoppePro(cProDto);
						// 写入库存
						if (StringUtils.isNotBlank(dataDto.getInventory())) {
							saveInventory(shoppePro.getShoppeProSid(), dataDto.getInventory(),
									source, dataDto.getEntryNumber());
						} else {
							saveInventory(shoppePro.getShoppeProSid(), "0", source,
									dataDto.getEntryNumber());
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
						insertShoppeProductSupply(psps);
					}
				}
			} else if (proDList != null && proDList.size() == 0) {// SKU没有/创建SKU-SHOPPRO
				cSkuDto.setProductSid(String.valueOf(pro.getSid()));// 产品表SID
				cSkuDto.setProductName(pro.getProductName());
				PcmProDetail sku = createService.insertSKU(cSkuDto);
				skuFlag = sku.getSid();
				cProDto.setProductDetailSid(String.valueOf(sku.getSid()));
				if (!StringUtils.isNotBlank(cProDto.getShoppeProName())) {// 专柜商品名为空
					cProDto.setShoppeProName(sku.getProDetailName());
				}
				shoppePro = createService.insertShoppePro(cProDto);
				// 写入库存
				if (StringUtils.isNotBlank(dataDto.getInventory())) {
					saveInventory(shoppePro.getShoppeProSid(), dataDto.getInventory(), source,
							dataDto.getEntryNumber());
				} else {
					saveInventory(shoppePro.getShoppeProSid(), "0", source,
							dataDto.getEntryNumber());
				}
				// 写入临时表
				if (StringUtils.isNotBlank(dataDto.getEntryNumber())
						&& StringUtils.isNotBlank(dataDto.getProcurementPersonnelNumber())
						&& StringUtils.isNotBlank(dataDto.getOfferNumber())) {
					createService.insertProductInput(shoppePro.getSid(), dataDto.getOfferNumber(),
							dataDto.getEntryNumber(), dataDto.getProcurementPersonnelNumber(),
							dsPro);
				}
				// 写入一品多供应商关系表
				PcmShoppeProductSupply psps = new PcmShoppeProductSupply();
				psps.setShoppeProductSid((shoppePro.getSid()));// 专柜商品SID
				psps.setSupplySid((supplyInfoList.get(0).getSid()));// 供应商SID
				insertShoppeProductSupply(psps);
			} else {
				throw new BleException(ErrorCode.SKU_IS_EXIST1.getErrorCode(),
						ErrorCode.SKU_IS_EXIST1.getMemo());
			}
		} else {// SPU没有/创建SPU-SKU-SHOPPRO
			CreateSpuDto cSpuDto = new CreateSpuDto();
			cSpuDto.setBrandName(brandList.get(0).getBrandName());// 集团品牌名称
			cSpuDto.setBrandRootSid(String.valueOf(brandList.get(0).getSid()));// 集团品牌表SID
			cSpuDto.setBrandSid(brandList.get(0).getBrandSid());// 集团品牌编码
			cSpuDto.setCategoryGYSid(cateList.get(0).getSid());// 工业分类SID
			cSpuDto.setFlag(Integer.parseInt(dataDto.getType()));// 超市百货标识_0百货_1超市
			cSpuDto.setPrimaryAttr(dataDto.getMainAttribute());// 主属性
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
			PcmProduct spu = createService.insertSPU(cSpuDto);
			spuFlag = spu.getSid();
			cSkuDto.setProductSid(String.valueOf(spu.getSid()));
			cSkuDto.setProductName(spu.getProductName());
			PcmProDetail sku = createService.insertSKU(cSkuDto);
			skuFlag = sku.getSid();
			cProDto.setProductDetailSid(String.valueOf(sku.getSid()));
			if (!StringUtils.isNotBlank(cProDto.getShoppeProName())) {// 专柜商品名为空
				cProDto.setShoppeProName(sku.getProDetailName());
			}
			shoppePro = createService.insertShoppePro(cProDto);
			// 写入库存
			if (StringUtils.isNotBlank(dataDto.getInventory())) {
				saveInventory(shoppePro.getShoppeProSid(), dataDto.getInventory(), source,
						dataDto.getEntryNumber());
			} else {
				saveInventory(shoppePro.getShoppeProSid(), "0", source, dataDto.getEntryNumber());
			}
			// 写入临时表
			if (StringUtils.isNotBlank(dataDto.getEntryNumber())
					&& StringUtils.isNotBlank(dataDto.getProcurementPersonnelNumber())
					&& StringUtils.isNotBlank(dataDto.getOfferNumber())) {
				createService.insertProductInput(shoppePro.getSid(), dataDto.getOfferNumber(),
						dataDto.getEntryNumber(), dataDto.getProcurementPersonnelNumber(), dsPro);
			}
			// 写入一品多供应商关系表
			PcmShoppeProductSupply psps = new PcmShoppeProductSupply();
			psps.setShoppeProductSid((shoppePro.getSid()));// 专柜商品SID
			psps.setSupplySid((supplyInfoList.get(0).getSid()));// 供应商SID
			insertShoppeProductSupply(psps);
		}
		logger.info("end savePullProductFromEFuture()");
		if (shoppePro != null) {
			shoppePro.setPackUnitDictSid(spuFlag);
			shoppePro.setMeasureUnitDictSid(skuFlag);
		}
		return shoppePro;

	}

	/**
	 * 导入终端电商商品上传参数验证
	 * @Methods Name validPullDataDtoIsExistsSupShoPro
	 * @Create In 2016年5月23日 By wangc
	 * @param dataDto
	 * @param sapErp
	 * @return
	 * @throws BleException boolean
	 */
	public boolean validPullDataDtoIsExistsSupShoPro(PullDataDto dataDto, Boolean sapErp)
			throws BleException {
		logger.info("start validPullDataDtoIsExistsSupShoPro(),param:" + dataDto.toString());
		/**
		 * sapErp = true 电商商品
		 * sapErp = false 非电商商品
		 */
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
		// 供应商编码-非空1
		if (!StringUtils.isNotBlank(dataDto.getSupplierCode())) {
			logger.info("供应商编码参数不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_SUPPLIERCODE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_SUPPLIERCODE.getMemo());
		}
		// 供应商编码-规则1
		if (!dataDto.getSupplierCode().matches("\\w+")) {
			logger.info("供应商编码只能包含大小写字母、数字及下划线");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_SUPPLIERCODE1.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_SUPPLIERCODE1.getMemo());
		}
		// 品牌编码-非空1
		if (!StringUtils.isNotBlank(dataDto.getBrandCode())) {
			logger.info("品牌编码参数不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_BRANDCODE1.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_BRANDCODE1.getMemo());
		}
		// 品牌编码-规则1
		if (!dataDto.getBrandCode().matches("\\d+")) {
			logger.info("品牌编码参数只能为数字");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_BRANDCODE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_BRANDCODE.getMemo());
		}
		// 基本计量单位1
		if(StringUtils.isBlank(dataDto.getBaseUnitCode())){
			logger.info("基本计量单位不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_BASEUNITCODE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_BASEUNITCODE.getMemo());
		}
		// 尺码1
		if (!StringUtils.isNotBlank(dataDto.getSizeCode())) {
			logger.info("规格/尺码参数不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_SIZECODE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_SIZECODE.getMemo());
		}
		/*//颜色码
		if(StringUtils.isBlank(dataDto.getZzColorCode())){
			logger.info("颜色码不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_ZZCOLORCODE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_ZZCOLORCODE.getMemo());
		}
		//尺寸码
		if(StringUtils.isBlank(dataDto.getZzSizeCode())){
			logger.info("尺寸码不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_ZZSIZECODE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_ZZSIZECODE.getMemo());
		}*/
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
		// 扣率/进价1
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
		// 扣率/含税进价1
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
		// 消费税1
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
		// 进项税1
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
		// 销项税1
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
		// 末级工业分类代码-非空1
		if (!StringUtils.isNotBlank(dataDto.getProdCategoryCode())) {
			logger.info("末级工业分类代码参数不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_PRODCATEGORYCODE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_PRODCATEGORYCODE.getMemo());
		}
		// 末级工业分类编码-规则1
		if (!dataDto.getProdCategoryCode().matches("\\d+")) {
			logger.info("末级工业分类编码只能为数字");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_PRODCATEGORYCODE1.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_PRODCATEGORYCODE1.getMemo());
		}
		// 末级统计分类代码-非空1
		if (!StringUtils.isNotBlank(dataDto.getFinalClassiFicationCode())) {
			logger.info("末级统计分类代码参数不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_STATCATEGORYCODE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_STATCATEGORYCODE.getMemo());
		}
		// 末级统计分类编码-规则1
		if (!dataDto.getFinalClassiFicationCode().matches("\\d+")) {
			logger.info("末级统计分类编码只能为数字");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_STATCATEGORYCODE1.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_STATCATEGORYCODE1.getMemo());
		}
		// 吊牌价-非空1
		if (!StringUtils.isNotBlank(dataDto.getMarketPrice())) {
			logger.info("市场价参数不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_MARKETPRICE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_MARKETPRICE.getMemo());
		}
		// 吊牌价-规则1
		if (!dataDto.getMarketPrice().matches("\\d+.{0,1}\\d*")) {
			logger.info("市场价参数只能为整数数字或小数数字");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_MARKETPRICE1.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_MARKETPRICE1.getMemo());
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
		//原产国1
		if(StringUtils.isBlank(dataDto.getOriginCountry())){
			logger.info("原产国不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_ORIGINCOUNTRY.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_ORIGINCOUNTRY.getMemo());
		}
		//产地1
		if (!StringUtils.isNotBlank(dataDto.getPlaceOfOrigin())) {
				logger.info("产地参数不能为空");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_ORIGIN.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_ORIGIN.getMemo());
		}
		/*// 经营方式-非空 电商不验证
		if (!StringUtils.isNotBlank(dataDto.getOperateMode()) && !sapErp) {
			logger.info("经营方式参数不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_OPERATEMODE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_OPERATEMODE.getMemo());
		}
		// 经营方式-规则 电商不验证
		if (!dataDto.getOperateMode().matches(Constants.SUPPLIER_OPERATEMODE_TYPE) && !sapErp) {
			logger.info("经营方式应为0-4的数字(0经销1代销2联营3平台服务4租赁)");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_OPERATEMODE1.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_OPERATEMODE1.getMemo());
		}*/
		// 品牌专柜(管理分类编码)-非空1
		if (!StringUtils.isNotBlank(dataDto.getManageCateGory())) {
			logger.info("品牌专柜(管理分类)编码参数不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_MANAGECATEGORY.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_MANAGECATEGORY.getMemo());
		}
		// 品牌专柜（管理分类编码）-规则1
		if (StringUtils.isNotBlank(dataDto.getManageCateGory())) {
			if (!dataDto.getManageCateGory().matches("\\d+")) {
				logger.info("品牌专柜(管理分类)编码参数只能为数字");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_MANAGECATEGORY1.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_MANAGECATEGORY1.getMemo());
			}
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
		/*// 要约号 电商不验证
		if (!StringUtils.isNotBlank(dataDto.getOfferNumber()) && !sapErp) {
			logger.info("要约号参数不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_OFFERNUMBER.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_OFFERNUMBER.getMemo());
		}
		// 折扣底限 电商不验证
		if (!StringUtils.isNotBlank(dataDto.getDiscountLimit())) {
			if (!sapErp) {
				logger.info("折扣底限参数不能为空");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_DISCOUNTLIMIT.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_DISCOUNTLIMIT.getMemo());
			}
		}*/
		/*//期初库存 电商不验证
		if (StringUtils.isNotBlank(dataDto.getInventory())
				&& !dataDto.getInventory().matches("\\d*")) {
			if (!sapErp) {
				logger.info("期初库存参数不符合规则");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_INVENTORY1.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_INVENTORY1.getMemo());
			}
		}
		//采购人员编码 电商不验证
		if (StringUtils.isBlank(dataDto.getProcurementPersonnelNumber())) {
			if (!sapErp) {
				logger.info("采购人员编号不能为空");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_PROCUREMEN.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_PROCUREMEN.getMemo());
			}
		}
		//录入人员编号,电商不验证
		if (StringUtils.isBlank(dataDto.getEntryNumber())) {
			if (!sapErp) {
				logger.info("录入人员编号不能为空");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_ENTRY.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_ENTRY.getMemo());
			}
		}*/
		logger.info("end validPullDataDtoIsExists()");
		return true;
		
	}
 
	/**
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
}

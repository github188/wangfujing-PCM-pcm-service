package com.wangfj.product.maindata.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wangfj.product.category.domain.entity.PcmCategory;
import com.wangfj.product.category.persistence.PcmCategoryMapper;
import com.wangfj.product.maindata.domain.vo.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangfj.core.cache.RedisCacheGet;
import com.wangfj.core.cache.RedisVo;
import com.wangfj.core.constants.ComErrorCodeConstants;
import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.CacheUtils;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.core.utils.RedisUtil;
import com.wangfj.product.brand.domain.entity.PcmBrand;
import com.wangfj.product.brand.persistence.PcmBrandMapper;
import com.wangfj.product.common.domain.entity.PcmRedis;
import com.wangfj.product.common.service.intf.IPcmRedisService;
import com.wangfj.product.constants.DomainName;
import com.wangfj.product.maindata.domain.entity.PcmBarcode;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProduct;
import com.wangfj.product.maindata.domain.entity.ShoProInfoFormPcmToPis;
import com.wangfj.product.maindata.persistence.PcmBarcodeMapper;
import com.wangfj.product.maindata.persistence.PcmPromotionRateProductMapper;
import com.wangfj.product.maindata.persistence.PcmShoppeProductMapper;
import com.wangfj.product.maindata.service.intf.IPcmShoppeProductService;
import com.wangfj.product.organization.domain.entity.PcmOrganization;
import com.wangfj.product.organization.domain.entity.PcmShoppe;
import com.wangfj.product.organization.persistence.PcmOrganizationMapper;
import com.wangfj.product.organization.persistence.PcmShoppeMapper;
import com.wangfj.product.price.persistence.PcmPriceMapper;
import com.wangfj.product.supplier.domain.entity.PcmSupplyInfo;
import com.wangfj.product.supplier.persistence.PcmSupplyInfoMapper;
import com.wangfj.util.Constants;
import com.wangfj.util.mq.PublishDTO;

/**
 * 专柜商品信息service实现类
 *
 * @Class Name PcmShoppeProductServiceImpl
 * @Author zhangxy
 * @Create In 2015年7月14日
 */
@Service
public class PcmShoppeProductServiceImpl implements IPcmShoppeProductService {

    private static final Logger logger = LoggerFactory.getLogger(PcmShoppeProductServiceImpl.class);

    @Autowired
    PcmShoppeProductMapper shoppeProMapper;
    @Autowired
    PcmBarcodeMapper barcodeMapper;
    @Autowired
    PcmBrandMapper brandMapper;
    @Autowired
    PcmPriceMapper priceMapper;
    @Autowired
    PcmSupplyInfoMapper supplyMapper;
    @Autowired
    PcmPromotionRateProductMapper rateMapper;
    @Autowired
    PcmShoppeMapper shoppeMapper;
    @Autowired
    PcmOrganizationMapper orgMapper;
    @Autowired
    private PcmCategoryMapper categoryMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private IPcmRedisService redisService;

    /**
     * 搜索下发商品信息
     *
     * @param paramMap
     * @return List<PcmProSearchDto>
     * @Methods Name getProInfoPulishSearch
     * @Create In 2016年3月8日 By yedong
     */
    @Override
    public List<PcmProSearchDto> getProInfoPulishSearch(Map<String, Object> paramMap) {
        return shoppeProMapper.getProInfoPulishSearch(paramMap);
    }

    /**
     * 专柜商品查询--查询参数处理
     *
     * @param pageDto
     * @return Map<String, Object>
     * @throws Exception
     * @Methods Name selectParamprocess
     * @Create In 2015年7月22日 By zhangxy
     */
    public Map<String, Object> paramProcess(ProductPageDto pageDto) throws BleException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sid", pageDto.getSid());
        paramMap.put("sidList", pageDto.getSidList());
        paramMap.put("productCode", pageDto.getProductCode());
        paramMap.put("skuCode", pageDto.getSkuCode());
        paramMap.put("spuCode", pageDto.getSpuCode());
        paramMap.put("erpProductCode", pageDto.getErpProductCode());
        paramMap.put("modelCode", pageDto.getModelCode());
        paramMap.put("articleNum", pageDto.getArticleNum());
        paramMap.put("colorCode", pageDto.getColorCode());
        paramMap.put("stanCode", pageDto.getStanCode());
        paramMap.put("storeCode", pageDto.getStoreCode());
        paramMap.put("counterCode", pageDto.getCounterCode());
        paramMap.put("supplierCode", pageDto.getSupplierCode());
        if (StringUtils.isNotEmpty(pageDto.getBrandCode())) {
            // 品牌编码处理
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("brandSid", pageDto.getBrandCode());// 品牌编码
            map.put("brandType", 0); // 品牌类型（集团）
            map.put("status", 0); // 状态为有效
            List<PcmBrand> lpbg = brandMapper.selectListByParam(map);
            if (lpbg == null || lpbg.size() < 1) {// 集团品牌查询不到，查询门店品牌
                PcmOrganization org = null;
                if (StringUtils.isNotEmpty(pageDto.getStoreCode())) {
                    map.clear();
                    map.put("organizationCode", pageDto.getStoreCode());// 门店编码
                    map.put("organizationType", 3);// 门店
                    map.put("organizationStatus", 0);// 有效状态
                    List<PcmOrganization> orgList = orgMapper.selectListByParam(map);
                    if (orgList != null && orgList.size() == 1) {
                        org = orgList.get(0);
                    }
                } else if (StringUtils.isNotEmpty(pageDto.getCounterCode())) {
                    map.clear();
                    map.put("shoppeCode", pageDto.getCounterCode());// 专柜编码
                    map.put("shoppeStatus", 1);
                    List<PcmShoppe> shoppeList = shoppeMapper.selectListByParam(map);
                    if (shoppeList != null && shoppeList.size() == 1) {
                        org = orgMapper.get(shoppeList.get(0).getShopSid());
                    }
                } else {
                    logger.error(ErrorCode.BRAND_NOSTORE.getMemo() + "--para:" + pageDto.toString());
                    throw new BleException(ErrorCode.BRAND_NOSTORE.getErrorCode(),
                            ErrorCode.BRAND_NOSTORE.getMemo());
                }
                if (org == null) {
                    logger.error(ErrorCode.SHOPPE_NULL.getErrorCode(),
                            ErrorCode.SHOPPE_NULL.getMemo());
                    throw new BleException(ErrorCode.SHOPPE_NULL.getErrorCode(),
                            ErrorCode.SHOPPE_NULL.getMemo());
                }
                map.clear();
                map.put("brandSid", pageDto.getBrandCode());// 品牌编码
                map.put("shopType", org.getStoreType());// 门店类型
                map.put("brandType", 1); // 品牌类型（门店）
                map.put("status", 0); // 状态为有效
                lpbg = brandMapper.selectListByParam(map);
                if (lpbg == null || lpbg.size() < 1) {
                    logger.error(ErrorCode.BRANDGROUP_NOT_EXIST.getErrorCode(),
                            ErrorCode.BRANDGROUP_NOT_EXIST.getMemo());
                    throw new BleException(ErrorCode.BRANDGROUP_NOT_EXIST.getErrorCode(),
                            ErrorCode.BRANDGROUP_NOT_EXIST.getMemo());
                } else {
                    paramMap.put("brandCode", lpbg.get(0).getSid());
                }
            } else {
                paramMap.put("brandCode", lpbg.get(0).getSid());
            }
        }
        paramMap.put("brandGroupCode", pageDto.getBrandGroupCode());
        // paramMap.put("erpSkuType", pageDto.getErpSkuType());
        // paramMap.put("manageType", pageDto.getManageType());
        paramMap.put("channelSid", pageDto.getChannelSid());
        if (StringUtils.isNotBlank(pageDto.getSupplierCode())) {
            if (StringUtils.isNotEmpty(pageDto.getStoreCode())) {
                PcmSupplyInfo entity = new PcmSupplyInfo();
                entity.setSupplyCode(pageDto.getSupplierCode());
                entity.setShopSid(pageDto.getStoreCode());
                List<PcmSupplyInfo> list = supplyMapper.selectListByParam(entity);
                if (list != null && list.size() > 0) {
                    paramMap.put("supplierCode", list.get(0).getSid());
                }
            } else {
                throw new BleException(ErrorCode.SUPPLY_NOSTORE.getErrorCode(),
                        ErrorCode.SUPPLY_NOSTORE.getMemo());
            }
        }
        if (Constants.Y.equals(pageDto.getIsSale())) {
            paramMap.put("proSelling", Constants.PCMSHOPPEPRODECT_SALE_STATUS_YES);
        } else if (Constants.N.equals(pageDto.getIsSale())) {
            paramMap.put("proSelling", Constants.PCMSHOPPEPRODECT_SALE_STATUS_NO);
        }
        paramMap.put("skuSelling", pageDto.getSkuSale());
        paramMap.put("spuSelling", pageDto.getSpuSale());
        paramMap.put("negativeStock", pageDto.getNegativeStock());
        if (Constants.PCMSHOPPEPRODECT_IS_STOCK_Y_STR.equals(pageDto.getStockType())) {
            paramMap.put("stockType", Constants.PCMSHOPPEPRODECT_IS_STOCK_YE);
        } else if (Constants.PCMSHOPPEPRODECT_IS_STOCK_N_STR.equals(pageDto.getStockType())) {
            paramMap.put("stockType", Constants.PCMSHOPPEPRODECT_IS_STOCK_NE);
        }
        paramMap.put("stockMode", pageDto.getStockMode());
        paramMap.put("season", pageDto.getSeason());
        paramMap.put("marketPrice", pageDto.getMarketPrice());
        paramMap.put("stockMode", pageDto.getStockMode());
        paramMap.put("processType", pageDto.getProcessType());
        paramMap.put("originLand", pageDto.getOriginLand());
        paramMap.put("orderType", pageDto.getOrderType());
        paramMap.put("isPromotion", pageDto.getIsPromotion());
        paramMap.put("isAdjustPrice", pageDto.getIsAdjustPrice());
        paramMap.put("primaryAttr", pageDto.getPrimaryAttr());
        paramMap.put("sexSid", pageDto.getSexSid());
        paramMap.put("awesome", pageDto.getAwesome());
        paramMap.put("features", pageDto.getFeatures());
        paramMap.put("manageCategory", pageDto.getManageCategory());
        paramMap.put("productName", pageDto.getProductName());
        if (StringUtils.isNotBlank(pageDto.getSupplierSid())) {
            paramMap.put("supplierCode", pageDto.getSupplierSid());
        }
        if (pageDto.getSupplierIntBarCode() != null && pageDto.getSupplierIntBarCode().size() > 0) {
            paramMap.put("supplyProCode", String.valueOf(pageDto.getSupplierIntBarCode().get(0)));
        }
        return paramMap;
    }

    /**
     * 专柜商品查询--查询参数处理
     *
     * @param pageDto
     * @return Map<String, Object>
     * @throws Exception
     * @Methods Name selectParamprocess
     * @Create In 2015年7月22日 By zhangxy
     */
    public Map<String, Object> paramProcess1(ProductPageProCodesDto pageDto) throws BleException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sid", pageDto.getSid());
        paramMap.put("sidList", pageDto.getSidList());
        paramMap.put("itemIds", pageDto.getItemIds());
        paramMap.put("productCode", pageDto.getProductCode());
        paramMap.put("skuCode", pageDto.getSkuCode());
        paramMap.put("spuCode", pageDto.getSpuCode());
        paramMap.put("erpProductCode", pageDto.getErpProductCode());
        paramMap.put("modelCode", pageDto.getModelCode());
        paramMap.put("articleNum", pageDto.getArticleNum());
        paramMap.put("colorCode", pageDto.getColorCode());
        paramMap.put("stanCode", pageDto.getStanCode());
        paramMap.put("storeCode", pageDto.getStoreCode());
        paramMap.put("counterCode", pageDto.getCounterCode());
        if (StringUtils.isNotEmpty(pageDto.getBrandCode())) {
            // 品牌编码处理
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("brandSid", pageDto.getBrandCode());// 品牌编码
            map.put("brandType", 0); // 品牌类型（集团）
            map.put("status", 0); // 状态为有效
            List<PcmBrand> lpbg = brandMapper.selectListByParam(map);
            if (lpbg == null || lpbg.size() < 1) {// 集团品牌查询不到，查询门店品牌
                PcmOrganization org = null;
                if (StringUtils.isNotEmpty(pageDto.getStoreCode())) {
                    map.clear();
                    map.put("organizationCode", pageDto.getStoreCode());// 门店编码
                    map.put("organizationType", 3);// 门店
                    map.put("organizationStatus", 0);// 有效状态
                    List<PcmOrganization> orgList = orgMapper.selectListByParam(map);
                    if (orgList != null && orgList.size() == 1) {
                        org = orgList.get(0);
                    }
                } else if (StringUtils.isNotEmpty(pageDto.getCounterCode())) {
                    map.clear();
                    map.put("shoppeCode", pageDto.getCounterCode());// 专柜编码
                    map.put("shoppeStatus", 1);
                    List<PcmShoppe> shoppeList = shoppeMapper.selectListByParam(map);
                    if (shoppeList != null && shoppeList.size() == 1) {
                        org = orgMapper.get(shoppeList.get(0).getShopSid());
                    }
                } else {
                    logger.error(ErrorCode.BRAND_NOSTORE.getMemo() + "--para:" + pageDto.toString());
                    throw new BleException(ErrorCode.BRAND_NOSTORE.getErrorCode(),
                            ErrorCode.BRAND_NOSTORE.getMemo());
                }
                if (org == null) {
                    logger.error(ErrorCode.SHOPPE_NULL.getErrorCode(),
                            ErrorCode.SHOPPE_NULL.getMemo());
                    throw new BleException(ErrorCode.SHOPPE_NULL.getErrorCode(),
                            ErrorCode.SHOPPE_NULL.getMemo());
                }
                map.clear();
                map.put("brandSid", pageDto.getBrandCode());// 品牌编码
                map.put("shopType", org.getStoreType());// 门店类型
                map.put("brandType", 1); // 品牌类型（门店）
                map.put("status", 0); // 状态为有效
                lpbg = brandMapper.selectListByParam(map);
                if (lpbg == null || lpbg.size() < 1) {
                    logger.error(ErrorCode.BRANDGROUP_NOT_EXIST.getErrorCode(),
                            ErrorCode.BRANDGROUP_NOT_EXIST.getMemo());
                    throw new BleException(ErrorCode.BRANDGROUP_NOT_EXIST.getErrorCode(),
                            ErrorCode.BRANDGROUP_NOT_EXIST.getMemo());
                } else {
                    paramMap.put("brandCode", lpbg.get(0).getSid());
                }
            } else {
                paramMap.put("brandCode", lpbg.get(0).getSid());
            }
        }
        paramMap.put("brandGroupCode", pageDto.getBrandGroupCode());
        // paramMap.put("erpSkuType", pageDto.getErpSkuType());
        // paramMap.put("manageType", pageDto.getManageType());
        paramMap.put("channelSid", pageDto.getChannelSid());
        if (StringUtils.isNotBlank(pageDto.getSupplierCode())) {
            if (StringUtils.isNotEmpty(pageDto.getStoreCode())) {
                PcmSupplyInfo entity = new PcmSupplyInfo();
                entity.setSupplyCode(pageDto.getSupplierCode());
                entity.setShopSid(pageDto.getStoreCode());
                List<PcmSupplyInfo> list = supplyMapper.selectListByParam(entity);
                if (list != null && list.size() > 0) {
                    paramMap.put("supplierCode", list.get(0).getSid());
                }
            } else {
                throw new BleException(ErrorCode.SUPPLY_NOSTORE.getErrorCode(),
                        ErrorCode.SUPPLY_NOSTORE.getMemo());
            }
        }
        if (Constants.Y.equals(pageDto.getIsSale())) {
            paramMap.put("proSelling", Constants.PCMSHOPPEPRODECT_SALE_STATUS_YES);
        } else if (Constants.N.equals(pageDto.getIsSale())) {
            paramMap.put("proSelling", Constants.PCMSHOPPEPRODECT_SALE_STATUS_NO);
        }
        paramMap.put("skuSelling", pageDto.getSkuSale());
        paramMap.put("spuSelling", pageDto.getSpuSale());
        paramMap.put("negativeStock", pageDto.getNegativeStock());
        if (Constants.PCMSHOPPEPRODECT_IS_STOCK_Y_STR.equals(pageDto.getStockType())) {
            paramMap.put("stockType", Constants.PCMSHOPPEPRODECT_IS_STOCK_YE);
        } else if (Constants.PCMSHOPPEPRODECT_IS_STOCK_N_STR.equals(pageDto.getStockType())) {
            paramMap.put("stockType", Constants.PCMSHOPPEPRODECT_IS_STOCK_NE);
        }
        paramMap.put("stockMode", pageDto.getStockMode());
        paramMap.put("season", pageDto.getSeason());
        paramMap.put("marketPrice", pageDto.getMarketPrice());
        paramMap.put("stockMode", pageDto.getStockMode());
        paramMap.put("processType", pageDto.getProcessType());
        paramMap.put("originLand", pageDto.getOriginLand());
        paramMap.put("orderType", pageDto.getOrderType());
        paramMap.put("isPromotion", pageDto.getIsPromotion());
        paramMap.put("isAdjustPrice", pageDto.getIsAdjustPrice());
        paramMap.put("primaryAttr", pageDto.getPrimaryAttr());
        paramMap.put("sexSid", pageDto.getSexSid());
        paramMap.put("awesome", pageDto.getAwesome());
        paramMap.put("features", pageDto.getFeatures());
        paramMap.put("manageCategory", pageDto.getManageCategory());
        paramMap.put("productName", pageDto.getProductName());
        if (StringUtils.isNotBlank(pageDto.getSupplierSid())) {
            paramMap.put("supplierCode", pageDto.getSupplierSid());
        }
        if (pageDto.getSupplierIntBarCode() != null && pageDto.getSupplierIntBarCode().size() > 0) {
            paramMap.put("supplyProCode", String.valueOf(pageDto.getSupplierIntBarCode().get(0)));
        }
        return paramMap;
    }

    /**
     * 专柜商品查询--查询结果 单条记录处理
     *
     * @param map
     * @return Map<String,Object>
     * @throws Exception
     * @Methods Name resultProcess
     * @Create In 2015年7月22日 By zhangxy
     */
    public ProductPageDto resultProcess(Map<String, Object> map) {
        if (map.get("isDiscountable") != null) {
            if (Constants.PCMERPPRODUCT_IS_PROMOTION_YES == (Integer) map.get("isDiscountable")) {
                map.put("isDiscountable", Constants.Y);
            } else if (Constants.PCMERPPRODUCT_IS_PROMOTION_NO == (Integer) map
                    .get("isDiscountable")) {
                map.put("isDiscountable", Constants.N);
            }
        }
        if (map.get("productStatus") != null) {
            if (Constants.PCMERPPRODUCT_PRO_STATUS_NORMAL == (Integer) map.get("productStatus")) {
                map.put("productStatus", Constants.PCMERPPRODUCT_PRO_STATUS_NORMAL_STR);
            } else if (Constants.PCMERPPRODUCT_PRO_STATUS_STOPSALE == (Integer) map
                    .get("productStatus")) {
                map.put("productStatus", Constants.PCMERPPRODUCT_PRO_STATUS_STOPSALE_STR);
            } else if (Constants.PCMERPPRODUCT_PRO_STATUS_STOP == (Integer) map
                    .get("productStatus")) {
                map.put("productStatus", Constants.PCMERPPRODUCT_PRO_STATUS_STOP_STR);
            } else if (Constants.PCMERPPRODUCT_PRO_STATUS_DELETE == (Integer) map
                    .get("productStatus")) {
                map.put("productStatus", Constants.PCMERPPRODUCT_PRO_STATUS_DELETE_STR);
            } else if (Constants.PCMERPPRODUCT_PRO_STATUS_PASS == (Integer) map
                    .get("productStatus")) {
                map.put("productStatus", Constants.PCMERPPRODUCT_PRO_STATUS_PASS_STR);
            } else if (Constants.PCMERPPRODUCT_PRO_STATUS_PAUSE == (Integer) map
                    .get("productStatus")) {
                map.put("productStatus", Constants.PCMERPPRODUCT_PRO_STATUS_PAUSE_STR);
            }
        }
        if (map.get("isCOD") != null) {
            if (Constants.PCMSHOPPEPRODECT_IS_COD_YES == (Integer) map.get("isCOD")) {
                map.put("isCOD", Constants.Y);
            } else if (Constants.PCMSHOPPEPRODECT_IS_COD_NO == (Integer) map.get("isCOD")) {
                map.put("isCOD", Constants.N);
            }
        }
        if (map.get("isSale") != null) {
            if (Constants.PCMSHOPPEPRODECT_SALE_STATUS_YES == (Integer) map.get("isSale")) {
                map.put("isSale", Constants.Y);
            } else if (Constants.PCMSHOPPEPRODECT_SALE_STATUS_NO == (Integer) map.get("isSale")) {
                map.put("isSale", Constants.N);
            }
        }
        if (map.get("stockType") != null) {
            if (Constants.PCMSHOPPEPRODECT_IS_STOCK_YE == (Integer) map.get("stockType")) {
                map.put("stockType", Constants.PCMSHOPPEPRODECT_IS_STOCK_Y_STR);
            } else if (Constants.PCMSHOPPEPRODECT_IS_STOCK_NE == (Integer) map.get("stockType")) {
                map.put("stockType", Constants.PCMSHOPPEPRODECT_IS_STOCK_N_STR);
            }
        }
        if (map.get("operateMode") != null) {
            if (Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z1 == (Integer) map.get("operateMode")) {
                map.put("operateMode", Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z1_STR);
            } else if (Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z2 == (Integer) map.get("operateMode")) {
                map.put("operateMode", Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z2_STR);
            } else if (Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3 == (Integer) map.get("operateMode")) {
                map.put("operateMode", Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3_STR);
            } else if (Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z4 == (Integer) map.get("operateMode")) {
                map.put("operateMode", Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z4_STR);
            } else if (Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z5 == (Integer) map.get("operateMode")) {
                map.put("operateMode", Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z5_STR);
            }
        }
        if (map.get("tmsParam") != null) {
            if (Constants.PCMSHOPPEPRODECT_TMS_TYPE_Z1 == (Integer) map.get("tmsParam")) {
                map.put("tmsParam", Constants.PCMSHOPPEPRODECT_TMS_TYPE_Z1_STR);
            } else if (Constants.PCMSHOPPEPRODECT_TMS_TYPE_Z2 == (Integer) map.get("tmsParam")) {
                map.put("tmsParam", Constants.PCMSHOPPEPRODECT_TMS_TYPE_Z2_STR);
            } else if (Constants.PCMSHOPPEPRODECT_TMS_TYPE_Z3 == (Integer) map.get("tmsParam")) {
                map.put("tmsParam", Constants.PCMSHOPPEPRODECT_TMS_TYPE_Z3_STR);
            } else if (Constants.PCMSHOPPEPRODECT_TMS_TYPE_Z4 == (Integer) map.get("tmsParam")) {
                map.put("tmsParam", Constants.PCMSHOPPEPRODECT_TMS_TYPE_Z4_STR);
            }
        }
        if (StringUtils.isBlank((String) map.get("channelName"))) {
            map.put("channelName", "全渠道");
        }
        ProductPageDto dto = new ProductPageDto();
        try {
            BeanUtils.copyProperties(dto, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    /**
     * 下发DTO数据处理 -- 门店ERP
     *
     * @param list
     * @return List<PublishShoppeProductFromPcmDto>
     * @Methods Name pushDtoProcess
     * @Create In 2015年8月19日 By zhangxy
     */
    public List<PublishShoppeProductFromPcmDto> pushDtoProcess(List<ProductPageDto> list,
                                                               Integer type) {
        List<PublishShoppeProductFromPcmDto> pushList = new ArrayList<PublishShoppeProductFromPcmDto>();
        for (ProductPageDto resDto : list) {
            PublishShoppeProductFromPcmDto pushDto = new PublishShoppeProductFromPcmDto();
            if (type != null) {
                if (type == 0) {
                    pushDto.setActionCode("A");
                } else if (type == 1) {
                    pushDto.setActionCode("U");
                } else {
                    pushDto.setActionCode("D");
                }
            }
            // pushDto.setType(type);
            pushDto.setBillsNo(resDto.getBillsNo());
            pushDto.setNotes(resDto.getNotes());
            pushDto.setCode(resDto.getProductCode());
            pushDto.setProductCode(resDto.getSkuCode());
            pushDto.setBaseProductCode(resDto.getSpuCode());
            pushDto.setProductName(resDto.getProductName());
            pushDto.setSupplierCode(resDto.getSupplierCode());
            pushDto.setCounterCode(resDto.getCounterCode());
            if (resDto.getDiscountCode() == null || resDto.getDiscountCode() == "") {
                pushDto.setErpProductCode("");
            } else {
                pushDto.setErpProductCode(resDto.getDiscountCode());
            }
            pushDto.setStoreCode(resDto.getStoreCode());
            pushDto.setManageCategory(resDto.getManageCategory());
            pushDto.setOperateMode(resDto.getOperateMode());
            pushDto.setMaxDiscountRate(resDto.getMaxDiscountRate());
            pushDto.setProductStatus(resDto.getIsSale());
            pushDto.setSizeCode(resDto.getStanCode());
            pushDto.setStyleCode(resDto.getColorCode());
            pushDto.setUnitCode(resDto.getUnitCode());
            pushDto.setMarketPrice(resDto.getMarketPrice());
            pushDto.setSalesPrice(resDto.getPromotionPrice());
            if (resDto.getSupplyProCode() == null || resDto.getSupplyProCode() == "") {
                pushDto.setSupplierIntBarCode("");
            } else {
                pushDto.setSupplierIntBarCode(resDto.getSupplyProCode());
            }
            pushDto.setModelNumber(resDto.getModelCode());
            pushDto.setMaterialNum(resDto.getArticleNum());
            if (resDto.getInputTax() == null || resDto.getInputTax() == "") {
                pushDto.setInputTax("0");
            } else {
                pushDto.setInputTax(resDto.getInputTax());
            }
            if (resDto.getOutputTax() == null || resDto.getOutputTax() == "") {
                pushDto.setOutputTax("0");
            } else {
                pushDto.setOutputTax(resDto.getOutputTax());
            }
            if (resDto.getSalesTax() == null || resDto.getSalesTax() == "") {
                pushDto.setSalesTax("0");
            } else {
                pushDto.setSalesTax(resDto.getSalesTax());
            }
            if (resDto.getPurchasePrice() == null || resDto.getPurchasePrice() == "") {
                pushDto.setPurchasePrice("0");
            } else {
                pushDto.setPurchasePrice(resDto.getPurchasePrice());
            }
            if (resDto.getBuyingPrice() == null || resDto.getBuyingPrice() == "") {
                pushDto.setBuyingPrice("0");
            } else {
                pushDto.setBuyingPrice(resDto.getBuyingPrice());
            }
            pushDto.setProcessType(resDto.getProcessType());
            pushDto.setOriginLand(resDto.getOriginLand());
            pushDto.setOrderType(resDto.getOrderType());
            if ("0".equals(resDto.getIsPromotion())) {
                pushDto.setIsPromotion(Constants.Y);
            } else {
                pushDto.setIsPromotion(Constants.N);
            }
            if ("0".equals(resDto.getIsAdjustPrice())) {
                pushDto.setIsAdjustPrice(Constants.Y);
            } else {
                pushDto.setIsAdjustPrice(Constants.N);
            }
            pushDto.setContractCode(resDto.getContractCode());
            pushDto.setProcurementUserCode(resDto.getProcurementUserCode());
            pushDto.setInputUserCode(resDto.getInputUserCode());
            pushDto.setStatCategory(resDto.getStatCategoryCode());
            pushDto.setIsSelllPurchase("");
            pushDto.setBrandCode(resDto.getBrandCode());
            pushDto.setProductAbbr(resDto.getProductAbbr());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd.HHmmssZ");
            pushDto.setActionDate(formatter.format(new Date()));
            pushDto.setActionPerson("WFJ");
            pushList.add(pushDto);
        }
        return pushList;
    }

    /**
     * 下发 大码->专柜商品数据处理 --促销
     *
     * @param list
     * @return List<PublishShoppeProductFromPcmDto>
     * @Methods Name pushErpDtoProcess
     * @Create In 2015年8月19日 By zhangxy
     */
    public List<PushShoppeProFromPcmDto> pushErpDtoProcess(List<ErpProductDto> list, Integer type) {
        List<PushShoppeProFromPcmDto> pushList = new ArrayList<PushShoppeProFromPcmDto>();
        for (ErpProductDto resDto : list) {
            PushShoppeProFromPcmDto pushDto = new PushShoppeProFromPcmDto();

            if (type != null) {
                if (type == 0) {
                    pushDto.setActionCode("A");
                } else if (type == 1) {
                    pushDto.setActionCode("U");
                } else {
                    pushDto.setActionCode("D");
                }
            }
            pushDto.setCode(resDto.getProductCode());
            pushDto.setProductCode(resDto.getProductCode());
            pushDto.setBaseProductCode(resDto.getProductCode());
            pushDto.setProductName(resDto.getProductName());
            pushDto.setSupplierCode(resDto.getSupplyCode());
            pushDto.setCounterCode(resDto.getShoppeCode());
            pushDto.setErpProductCode(resDto.getProductCode());
            pushDto.setStoreCode(resDto.getStoreCode());
            pushDto.setManageCategory(resDto.getManageCategory());
            pushDto.setOperateMode(resDto.getProductType());
            pushDto.setIsDiscountable(resDto.getDiscountLimit());
            pushDto.setMaxDiscountRate(resDto.getDiscountLimit());
            pushDto.setProductStatus(resDto.getProStatus());
            pushDto.setSizeCode(resDto.getStanName());
            pushDto.setStyleCode("通用");
            pushDto.setUnitCode(resDto.getSalesUnit());
            pushDto.setMarketPrice(resDto.getSalesPrice());
            pushDto.setSalesPrice(resDto.getSalesPrice());
            pushDto.setSupplierIntBarCode(resDto.getSupplierBarcode());
            pushDto.setIsGift("");
            pushDto.setCommissionRate(resDto.getCommissionRate());
            pushDto.setOriginSalesUnit(resDto.getSalesUnit());
            pushDto.setIsCOD(null);
            pushDto.setStockTypeLib("BG");
            pushDto.setErpSkuType(resDto.getCodeType() + "");
            pushDto.setModelNumber(null);
            pushDto.setMaterialNum(resDto.getArticleNum());
            pushDto.setProductAbbr(resDto.getProductName());
            pushDto.setRate("");
            pushDto.setIsSelllPurchase("");
            pushDto.setManageType("1");
            pushDto.setActionDate("");
            pushDto.setActionPersin("");
            pushList.add(pushDto);
        }
        return pushList;
    }

    /**
     * 下发 专柜商品数据处理 --促销
     *
     * @param list
     * @return List<PublishShoppeProductFromPcmDto>
     * @Methods Name pushDtoProcess
     * @Create In 2015年8月19日 By zhangxy
     */
    public List<PushShoppeProFromPcmDto> pushDtoProcessToCx(List<String> sidList, Integer type) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sidList", sidList);
        paramMap.put("num", sidList.size());
        List<ProductPageDto> proInfoToFj = shoppeProMapper.getProInfoToFj(paramMap);
        List<PushShoppeProFromPcmDto> pushList = new ArrayList<PushShoppeProFromPcmDto>();
        for (ProductPageDto resDto : proInfoToFj) {
            PushShoppeProFromPcmDto pushDto = new PushShoppeProFromPcmDto();

            if (type != null) {
                if (type == 0) {
                    pushDto.setActionCode("A");
                } else if (type == 1) {
                    pushDto.setActionCode("U");
                } else {
                    pushDto.setActionCode("D");
                }
            }
            pushDto.setCode(resDto.getProductCode());
            pushDto.setProductCode(resDto.getSkuCode());
            pushDto.setBaseProductCode(resDto.getSpuCode());
            pushDto.setProductName(resDto.getProductName());

            pushDto.setSupplierCode(resDto.getSupplierCode());
            pushDto.setCounterCode(resDto.getCounterCode());
            pushDto.setErpProductCode(resDto.getDiscountCode());
            pushDto.setStoreCode(resDto.getStoreCode());
            if (resDto.getManageCategory() == null || resDto.getManageCategory() == "") {
                pushDto.setManageCategory("");
            } else {
                pushDto.setManageCategory(resDto.getManageCategory());
            }
            // if (resDto.getOperateMode().equals("0")) {
            // pushDto.setOperateMode("Z001");
            // } else if (resDto.getOperateMode().equals("1")) {
            // pushDto.setOperateMode("Z002");
            // } else if (resDto.getOperateMode().equals("2")) {
            // pushDto.setOperateMode("Z003");
            // } else if (resDto.getOperateMode().equals("3")) {
            // pushDto.setOperateMode("Z004");
            // } else if (resDto.getOperateMode().equals("4")) {
            // pushDto.setOperateMode("Z005");
            // }
            pushDto.setOperateMode(resDto.getOperateMode());
            pushDto.setIsDiscountable(resDto.getIsDiscountable());
            pushDto.setMaxDiscountRate(resDto.getMaxDiscountRate());
            if (resDto.getIsSale().equals("Y")) {
                pushDto.setProductStatus("0");
            } else {
                pushDto.setProductStatus("1");
            }
            pushDto.setSizeCode(resDto.getStanCode());
            pushDto.setStyleCode(resDto.getColorCode());
            pushDto.setUnitCode(resDto.getUnitCode());
            pushDto.setMarketPrice(resDto.getMarketPrice());
            pushDto.setSalesPrice(resDto.getPromotionPrice());
            pushDto.setSupplierIntBarCode(resDto.getSupplyProCode());
            pushDto.setIsGift(resDto.getIsGift());
            pushDto.setCommissionRate(resDto.getCommissionRate());
            pushDto.setOriginSalesUnit(resDto.getOriginSalesUnit());
            pushDto.setIsCOD(resDto.getIsCOD());
            pushDto.setStockTypeLib("BG");
            pushDto.setErpSkuType(resDto.getErpSkuType() + "");
            pushDto.setModelNumber(resDto.getModelCode());
            pushDto.setMaterialNum(resDto.getArticleNum());
            pushDto.setRate("");
            if (resDto.getPrimaryAttr() == null || resDto.getPrimaryAttr() == "") {
                pushDto.setProductAbbr("");
            } else {
                pushDto.setProductAbbr(resDto.getProductAbbr());
            }
            pushDto.setOriginSalesUnit("");
            pushDto.setCommissionRate("");
            pushDto.setIsSelllPurchase("");
            pushDto.setManageType(resDto.getManageType());
            pushDto.setActionDate("");
            pushDto.setActionPersin("");
            pushList.add(pushDto);
        }
        return pushList;
    }

    /**
     * 按条件 分页 查询专柜商品基础信息
     *
     * @param pageDto
     * @return Page<ProductPageDto>
     * @throws Exception
     * @Methods Name selectBaseProPageByPara
     * @Create In 2015年8月3日 By zhangxy
     */

    public Page<ProductPageDto> selectBaseProPageByPara(ProductPageDto pageDto) throws BleException {
        Page<ProductPageDto> page = new Page<ProductPageDto>();
        if (pageDto.getCurrentPage() != null && pageDto.getCurrentPage() != 0) {
            page.setCurrentPage(pageDto.getCurrentPage());
        }
        if (pageDto.getPageSize() != null && pageDto.getPageSize() != 0) {
            page.setPageSize(pageDto.getPageSize());
        } else if (pageDto.getLimit() != null) {
            page.setPageSize(pageDto.getLimit());
        } else {
            // 无pageSize参数时最大不超过20条商品信息数据
            page.setPageSize(20);
        }
        // 查询参数处理
        Map<String, Object> paramMap = paramProcess(pageDto);
        // 查询总数量
        Integer count = shoppeProMapper.getProductCountByPara(paramMap);
        if (count != 0) {
            page.setCount(count);
        }
        if (pageDto.getStart() != null && pageDto.getLimit() != null) {
            paramMap.put("start", pageDto.getStart());
            paramMap.put("limit", pageDto.getLimit());
            page.setStart(pageDto.getStart());
            page.setLimit(pageDto.getLimit());
        } else if (pageDto.getPageSize() != null && pageDto.getPageSize() != 0) {
            paramMap.put("start", page.getStart());
            paramMap.put("limit", page.getLimit());
        }
        List<Map<String, Object>> list = shoppeProMapper.selectProductPageByPara(paramMap);
        if (!list.isEmpty()) {
            List<ProductPageDto> finalList = new ArrayList<ProductPageDto>();
            for (Map<String, Object> map : list) {
                ProductPageDto dto = resultProcess(map);
                finalList.add(dto);
            }
            page.setList(finalList);
        } else {
            page.setList(null);
        }
        return page;
    }

    /**
     * 专柜商品导出Excel 查询总数量
     *
     * @param pageDto
     * @return Page<ProductPageDto>
     * @throws Exception
     * @Methods Name getShoppeProductToExcelCount
     * @Create In 2016年04月05日 By wangxuan
     */
    @Override
    public Page<ProductPageDto> getShoppeProductToExcelCount(ProductPageDto pageDto)
            throws BleException {
        logger.info("start getShoppeProductToExcelCount(),param:" + pageDto.toString());
        Page<ProductPageDto> page = new Page<ProductPageDto>();
        // 查询总数量
        Integer count = shoppeProMapper.findProductCountByPara(pageDto);
        if (count != 0) {
            page.setCount(count);
        } else {
            page.setCount(0);
        }
        logger.info("end getShoppeProductToExcelCount(),return:" + count.toString());
        return page;
    }

    /**
     * 专柜商品导出Excel 查询
     *
     * @param pageDto
     * @return Page<ProductPageDto>
     * @throws Exception
     * @Methods Name getShoppeProductToExcel
     * @Create In 2016年04月05日 By wangxuan
     */
    @Override
    public Page<ProductPageDto> getShoppeProductToExcel(ProductPageDto pageDto)
            throws BleException {
        logger.info("start getShoppeProductToExcel(),param:" + pageDto.toString());
        Page<ProductPageDto> page = new Page<ProductPageDto>();
        pageDto.setStart(null);
        pageDto.setLimit(null);
        List<Map<String, Object>> list = shoppeProMapper.findProductPageByPara(pageDto);
        if (!list.isEmpty()) {
            List<ProductPageDto> finalList = new ArrayList<ProductPageDto>();
            for (Map<String, Object> map : list) {
                // 结果处理
                ProductPageDto dto = resultProcess(map);
                finalList.add(dto);
            }
            page.setList(finalList);
        } else {
            page.setList(null);
        }
        logger.info("end getShoppeProductToExcel(),return:" + list.toString());
        return page;
    }

    /**
     * 按条件 分页 查询专柜商品基础信息(优化)
     *
     * @param pageDto
     * @return Page<ProductPageDto>
     * @throws Exception
     * @Methods Name selectBaseProPageByParaOptimization
     * @Create In 2016年03月26日 By wangxuan
     */
    @Override
    public Page<ProductPageDto> selectBaseProPageByParaOptimization(ProductPageDto pageDto)
            throws BleException {
        logger.info("start selectBaseProPageByParaOptimization(),param:" + pageDto.toString());
        Page<ProductPageDto> page = new Page<ProductPageDto>();
        if (pageDto.getCurrentPage() != null && pageDto.getCurrentPage() != 0) {
            page.setCurrentPage(pageDto.getCurrentPage());
        }
        if (pageDto.getPageSize() != null && pageDto.getPageSize() != 0) {
            page.setPageSize(pageDto.getPageSize());
        } else if (pageDto.getLimit() != null) {
            page.setPageSize(pageDto.getLimit());
        } else {
            // 无pageSize参数时最大不超过20条商品信息数据
            page.setPageSize(20);
        }
        // 查询总数量
        Integer count = shoppeProMapper.findProductCountByPara(pageDto);
        if (count != 0) {
            page.setCount(count);
        }
        pageDto.setStart(page.getStart());
        pageDto.setLimit(page.getLimit());
        List<Map<String, Object>> list = shoppeProMapper.findProductPageByPara(pageDto);
        if (!list.isEmpty()) {
            List<ProductPageDto> finalList = new ArrayList<ProductPageDto>();
            for (Map<String, Object> map : list) {
                // 结果处理
                ProductPageDto dto = resultProcess(map);
                finalList.add(dto);
            }
            page.setList(finalList);
        } else {
            page.setList(null);
        }
        logger.info("end selectBaseProPageByParaOptimization(),return:" + list.toString());
        return page;
    }

    public ProPadDto proInfoToPad(Map<String, Object> paramMap) {
        ProPadDto proInfoToPad = shoppeProMapper.proInfoToPad(paramMap);
        return proInfoToPad;
    }

    /**
     * 查询单个专柜商品
     *
     * @param pageDto
     * @return ProductPageDto
     * @throws Exception
     * @Methods Name getBaseProPageByPara
     * @Create In 2015年8月24日 By zhangxy
     */
    public ProductPageDto getBaseProPageByPara(String productCode, ProductPageDto pageDto)
            throws BleException {
        // 查询参数处理
        Map<String, Object> paramMap = paramProcess(pageDto);
        List<Map<String, Object>> list = shoppeProMapper.selectProductPageByPara(paramMap);
        ProductPageDto dto = null;
        if (!list.isEmpty() & list.size() == 1) {
            dto = resultProcess(list.get(0));
        }
        return dto;
    }

    /**
     * 按条件查询专柜商品(销售)
     *
     * @param pageDto
     * @return ProductPageDto
     * @throws Exception
     * @Methods Name selectProByParaForOms
     * @Create In 2015年8月24日 By zhangxy
     */
    @Override
    public List<ProductPageDto> selectProByParaForOms(ProductPageDto pageDto) throws BleException {
        // 查询参数处理
        Map<String, Object> paramMap = paramProcess(pageDto);
        List<Map<String, Object>> list = shoppeProMapper.selectProductPageByParaForOms(paramMap);
        List<ProductPageDto> finalList = new ArrayList<ProductPageDto>();
        if (!list.isEmpty()) {
            for (Map<String, Object> map : list) {
                ProductPageDto dto = resultProcess(map);
                finalList.add(dto);
            }
        }
        return finalList;
    }

    /**
     * 按条件查询专柜商品基本信息及统计分类
     *
     * @param pageDto
     * @return List<ProductPageDto>
     * @throws Exception
     * @Methods Name findShoppeProductAndCategoryByPara
     * @Create In 2016年04月14日 By yedong
     */
    @Override
    public List<ProductPageDto> findShoppeProductAndCategoryByPara(ProductPageDto pageDto) {
        logger.info("start findShoppeProductAndCategoryByPara(),param:" + pageDto.toString());
        List<ProductPageDto> pageDtoList = shoppeProMapper.findShoppeProductAndCategoryByPara(pageDto);
        logger.info("end findShoppeProductAndCategoryByPara(),return:" + pageDtoList.toString());
        return pageDtoList;
    }

    /**
     * 按条件查询单个专柜商品信息(含条码)-优化
     *
     * @param pageDto
     * @return ProductPageDto
     * @throws Exception
     * @Methods Name selectShoppeProductByPara
     * @Create In 2015年7月15日 By yedong
     */
    @Override
    public ProductPageDto selectShoppeProductByPara(ProductPageDto pageDto) throws BleException {
        logger.info("start selectShoppeProductByPara() 按sid专柜商品信息");
        List<ProductPageDto> baseList = shoppeProMapper.findShoppeProductBaseInfoByPara(pageDto);
        if (baseList != null && baseList.size() > 0) {
            ProductPageDto resultDto = baseList.get(0);
            List<ProductPageDto> basicData = shoppeProMapper
                    .findShoppeProductBasicDataByPara(resultDto);
            if (basicData != null && basicData.size() > 0) {
                ProductPageDto tempDto = basicData.get(0);
                resultDto.setStoreCode(tempDto.getStoreCode());
                resultDto.setStoreName(tempDto.getStoreName());
                resultDto.setCounterName(tempDto.getCounterName());
                resultDto.setIndustrySid(tempDto.getIndustrySid());
                resultDto.setSupplierName(tempDto.getSupplierName());
                resultDto.setBrandName(tempDto.getBrandName());
                resultDto.setStatCategory(tempDto.getStatCategory());
            }
            List<ProductPageDto> contractInfo = shoppeProMapper
                    .findShoppeProductContractInfoByPara(resultDto);
            if (contractInfo != null && contractInfo.size() > 0) {
                ProductPageDto tempDto = contractInfo.get(0);
                resultDto.setContractCode(tempDto.getContractCode());
                resultDto.setOperateMode(tempDto.getOperateMode());
                resultDto.setInputUserCode(tempDto.getInputUserCode());
                resultDto.setProcurementUserCode(tempDto.getProcurementUserCode());
            }
            List<ProductPageDto> skuSpuInfo = shoppeProMapper
                    .findShoppeProductSkuSpuInfoByPara(resultDto);
            if (skuSpuInfo != null && skuSpuInfo.size() > 0) {
                ProductPageDto tempDto = skuSpuInfo.get(0);
                resultDto.setModelCode(tempDto.getModelCode());
                resultDto.setPrimaryAttr(tempDto.getPrimaryAttr());
                resultDto.setProColor(tempDto.getProColor());
                resultDto.setColorCode(tempDto.getColorCode());
                resultDto.setColorName(tempDto.getColorName());
                resultDto.setFeatures(tempDto.getFeatures());
                resultDto.setStanName(tempDto.getStanName());
            }
            String productCode = resultDto.getProductCode();
            if (com.wangfj.core.utils.StringUtils.isNotEmpty(productCode)) {
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("shoppeProSid", productCode);
                List<PcmBarcode> barcodeList = barcodeMapper.selectListByParam(paramMap);
                resultDto.setBarcodeList(barcodeList);
            }
            logger.info("end selectShoppeProductByPara(),result:" + resultDto.toString());
            return resultDto;
        } else {
            logger.error("selectShoppeProductByPara() 按sid专柜商品信息失败，查询结果为空");
            throw new BleException(ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
        }
    }

    @Override
    public List<PcmPublishSapErpDto> pcmPublishSapErpMap(Map<String, Object> paramMap) {
        List<PcmPublishSapErpDto> sapInfo = shoppeProMapper.pcmPublishSapErpMap(paramMap);
        return sapInfo;
    }

    /**
     * 导入终端上传电商商品下发电商
     *
     * @param paraMap
     * @return List<PcmPublishSapErpDto>
     * @Methods Name pcmPublishSapErpSourcePis
     * @Create In 2016年5月24日 By wangc
     */
    public List<PcmSapInfoToSapSourcePis> pcmPublishSapErpSourcePis(Map<String, Object> paraMap) {
        List<PcmSapInfoToSapSourcePis> sapInfo = shoppeProMapper.pcmPublishSapErpSourcePis(paraMap);
        List<String> spuSidList = new ArrayList<String>();//spusid列表,查询工业分类用
        List<String> proCodeList = new ArrayList<String>();//专柜商品编码列表,查询统计分类用
        List<Map<String, Object>> gyCateResult = new ArrayList<Map<String, Object>>();//工业分类结果列表
        List<Map<String, Object>> tjCateResult = new ArrayList<Map<String, Object>>();//统计分类结果列表
        if (sapInfo != null && sapInfo.size() != 0) {
            for (PcmSapInfoToSapSourcePis pis : sapInfo) {
                spuSidList.add(pis.getMATKL());
                proCodeList.add(pis.getSid());
            }
            Map<String, Object> paraMap1 = new HashMap<String, Object>();
            Map<String, Object> paraMap2 = new HashMap<String, Object>();
            paraMap1.put("spuSidList", spuSidList);
            paraMap2.put("proCodeList", proCodeList);
            gyCateResult = shoppeProMapper.getGyCateToSapInfo(paraMap1);
            tjCateResult = shoppeProMapper.getTjCateToSapInfo(paraMap2);
            for (PcmSapInfoToSapSourcePis pis1 : sapInfo) {
                String spuSid = pis1.getMATKL();
                String proCode = pis1.getSid();
                String gyCate = null;
                String glCate = null;
                for (Map<String, Object> m : gyCateResult) {
                    if (spuSid.equals(m.get("spuSid").toString())) {
                        gyCate = m.get("cateCode").toString();
                    }
                }
                pis1.setMATKL(gyCate);
                for (Map<String, Object> m : tjCateResult) {
                    if (proCode.equals(m.get("proCode").toString())) {
                        glCate = m.get("cateCode").toString();
                    }
                }
                pis1.setZZTJFL(glCate);
            }
        }
        return sapInfo;
    }

    /**
     * 查询单个专柜商品信息(含条码)
     *
     * @param pageDto
     * @return Page<ProductPageDto>
     * @throws Exception
     * @Methods Name selectProductPageByPara
     * @Create In 2015年7月15日 By zhangxy
     */
    @Override
    public OmsResProInfoDto getProductPageByPara(String productCode, ProductPageDto pageDto)
            throws BleException {
        logger.info("start getProductPageByPara() 按条件分页下发专柜商品信息");
        String omsResProInfoDto = redisUtil.get(DomainName.getShoppeInfo + productCode, "0000");
        if (!"0000".equals(omsResProInfoDto)) {
            OmsResProInfoDto dto = JsonUtil.getDTO(omsResProInfoDto, OmsResProInfoDto.class);
            logger.info("end getProductPageByPara(),result:" + dto);
            return dto;
        } else {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("proCode", pageDto.getProductCode());
            OmsResProInfoDto omsGetResProInfo = shoppeProMapper.omsGetResProInfo(paramMap);
            logger.info("end getProductPageByPara(),result:" + omsGetResProInfo);
            if (omsGetResProInfo != null) {
                redisUtil.set(DomainName.getShoppeInfo + productCode,
                        JsonUtil.getJSONString(omsGetResProInfo));
            }
            return omsGetResProInfo;
        }
    }

    /**
     * 查询专柜商品信息(名称、是否可售、是否可包装、是否管库存、是否负库存销售)
     *
     * @param shoppeProSid
     * @return Map<String,Object>
     * @Methods Name selectStockInfo
     * @Create In 2015年8月3日 By yedong
     */
    public Map<String, Object> selectStockInfo(String shoppeProSid) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("shoppeProSid", shoppeProSid);
        List<Map<String, Object>> list = shoppeProMapper.selectStockInFo(paramMap);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public Map<String, Object> selectStockInfo(String shoppeProSid, String sapProductCode) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("shoppeProSid", shoppeProSid);
        paramMap.put("sapProductCode", sapProductCode);
        List<Map<String, Object>> list = shoppeProMapper.selectStockInFo(paramMap);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 根据SKUSID查询专柜商品
     *
     * @param dto
     * @return PcmShoppeProduct
     * @Methods Name selectProPageBySku
     * @Create In 2015年9月21日 By zhangxy
     */
    public Page<ShoppeProductDto> selectProPageBySku(ShoppeProductDto dto) {
        Page<ShoppeProductDto> page = new Page<ShoppeProductDto>();
        if (dto.getCurrentPage() != null && dto.getCurrentPage() != 0) {
            page.setCurrentPage(dto.getCurrentPage());
        } else {
            page.setCurrentPage(1);
        }
        if (dto.getPageSize() != null && dto.getPageSize() != 0) {
            page.setPageSize(dto.getPageSize());
        } else if (dto.getLimit() != null) {
            page.setPageSize(dto.getLimit());
        } else {
            // 无pageSize参数时最大不超过20条商品信息数据
            page.setPageSize(20);
        }
        // 查询总数量
        Integer count = shoppeProMapper.getCountProPageBySku(dto);
        if (count != 0) {
            page.setCount(count);
        }
        if (dto.getCurrentPage() != null && dto.getPageSize() != null) {
            dto.setStart(page.getStart());
            dto.setLimit(page.getLimit());
        }
        if (dto.getStart() != null && dto.getLimit() != null) {
            page.setStart(dto.getStart());
            page.setLimit(dto.getLimit());
        }
        List<ShoppeProductDto> list = shoppeProMapper.selectProPageBySku(dto);
        if (list != null && list.size() > 0) {
            for (ShoppeProductDto res : list) {
                if ("0".equals(res.getIsSale())) {
                    res.setIsSale("Y");
                } else if ("1".equals(res.getIsSale())) {
                    res.setIsSale("N");
                }
                if ("WFJ".equals(res.getField2())) {
                    res.setSupplierName("WFJ");
                    res.setSupplierCode("WFJ");
                }
            }
        }
        page.setList(list);
        return page;
    }

    /**
     * 根据SKUSID查询专柜商品(缓存)
     *
     * @param dto
     * @return PcmShoppeProduct
     * @Methods Name selectProPageCache
     * @Create In 2015年9月21日 By zhangxy
     */
    @RedisCacheGet(redisName = DomainName.getShoppeInfo, returnObj = "com.wangfj.product.maindata.domain.vo.ProPageDto")
    public ProPageDto selectProPageCache(String proPage, ShoppeProductDto dto) {
        ProPageDto proDto = new ProPageDto();
        Page<ShoppeProductDto> page = new Page<ShoppeProductDto>();
        if (dto.getCurrentPage() != null && dto.getCurrentPage() != 0) {
            page.setCurrentPage(dto.getCurrentPage());
        }
        if (dto.getPageSize() != null && dto.getPageSize() != 0) {
            page.setPageSize(dto.getPageSize());
        } else if (dto.getLimit() != null) {
            page.setPageSize(dto.getLimit());
        } else {
            // 无pageSize参数时最大不超过20条商品信息数据
            page.setPageSize(20);
        }
        // 查询总数量
        Integer count = shoppeProMapper.getCountProPageBySku(dto);
        if (count != 0) {
            page.setCount(count);
        }
        if (dto.getStart() != null && dto.getLimit() != null) {
            page.setStart(dto.getStart());
            page.setLimit(dto.getLimit());
        } else if (dto.getCurrentPage() != null && dto.getCurrentPage() != 0
                && dto.getPageSize() != null && dto.getPageSize() != 0) {
            dto.setStart(page.getStart());
            dto.setLimit(page.getLimit());
        }
        List<ShoppeProductDto> list = shoppeProMapper.selectProPageBySku(dto);
        if (list != null && list.size() > 0) {
            for (ShoppeProductDto res : list) {
                if ("0".equals(res.getIsSale())) {
                    res.setIsSale("Y");
                } else if ("1".equals(res.getIsSale())) {
                    res.setIsSale("N");
                }
                if ("WFJ".equals(res.getField2())) {
                    res.setSupplierName("WFJ");
                    res.setSupplierCode("WFJ");
                }
            }
        }
        page.setList(list);
        proDto.setPage(page);
        return proDto;
    }

    /**
     * 根据专柜商品编码查询扣率码
     *
     * @param shoppeProSid
     * @return PcmShoppeProduct
     * @Methods Name selectRateCodeByProSid
     * @Create In 2015年10月13日 By kongqf
     */
    @Override
    public PcmShoppeProduct selectRateCodeByProSid(String shoppeProSid) {
        return shoppeProMapper.selectRateCodeByProSid(shoppeProSid);
    }

    /**
     * 修改商品基本属性
     *
     * @param dto
     * @Methods Name updateProductInfo
     * @Create In 2015年10月20日 By zhangxy
     */
    @Override
    public void updateProductInfo(UpdateProductInfoDto dto) throws BleException {
        PcmShoppeProduct record = new PcmShoppeProduct();
        record.setShoppeProSid(dto.getProductCode());
        record.setShoppeProName(dto.getProductName());
        record.setSaleUnitCode(dto.getUnit());
        record.setOriginLand(dto.getOriginLand());
        record.setField1(dto.getRemark());
        record.setSaleStatus(dto.getStatus());
        record.setField3(dto.getArticleNum());
        int i = shoppeProMapper.updateByCodeSelective(record);
        if (i == 0) {
            throw new BleException(ErrorCode.PRO_INFO_NO_EXIST.getErrorCode(),
                    ErrorCode.PRO_INFO_NO_EXIST.getMemo());
        }
        cacheDelete(record.getShoppeProSid());// 删除缓存
    }

    /**
     * 查询专柜商品信息By专柜商品
     *
     * @param proDto
     * @return List<PcmShoppeProduct>
     * @Methods Name selectShoppeProductInfo
     * @Create In 2016年2月17日 By yedong
     */
    public List<PcmShoppeProduct> selectShoppeProductInfo(PcmShoppeProduct proDto) {
        List<PcmShoppeProduct> proList = shoppeProMapper.selectListByParam(proDto);
        return proList;
    }

    /**
     * 专柜商品启用状态修改
     *
     * @param list
     * @return
     * @Methods Name updateProductStatusInfo
     * @Create In 2015年12月8日 By zhangdongliang
     */
    @Transactional
    public List<PublishDTO> updateProductStatusInfo(List<UpdateProductInfoDto> list) {
        List<PublishDTO> pushDto = new ArrayList<PublishDTO>();
        for (UpdateProductInfoDto dto : list) {
            PcmShoppeProduct record = new PcmShoppeProduct();
            record.setShoppeProSid(dto.getProductCode());
            record.setSaleStatus(dto.getStatus());
            int i = shoppeProMapper.updateByCodeSelective(record);
            if (i == 0) {
                throw new BleException(ErrorCode.PRO_INFO_NO_EXIST.getErrorCode(),
                        ErrorCode.PRO_INFO_NO_EXIST.getMemo());
            }
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("proCode", dto.getProductCode());
            List<Map<String, Object>> storeCodeByProCode = shoppeProMapper
                    .getStoreCodeByProCode(paramMap);
            PublishDTO pbDto = new PublishDTO();
            pbDto.setSid((Long) storeCodeByProCode.get(0).get("proSid"));
            pbDto.setType(1);
            pushDto.add(pbDto);
        }
        return pushDto;
    }

    /**
     * 根据skusid 或 专柜商品sid 查询下发用的SPU SKU
     *
     * @param sid
     * @Methods Name selectSidsByPro
     * @Create In 2015年10月30日 By zhangxy
     */
    @Override
    public Map<String, Long> selectSidsByPro(Long skuSid, Long proSid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("skuSid", skuSid);
        map.put("proSid", proSid);
        return shoppeProMapper.selectSidsByPro(map);
    }

    /**
     * 根据sku查询下发用的专柜商品Sid
     *
     * @param sid
     * @Methods Name selectSidsBySku
     * @Create In 2015年10月30日 By zhangxy
     */
    @Override
    public List<PublishDTO> selectSidsBySku(Long skuSid) {
        PcmShoppeProduct psp = new PcmShoppeProduct();
        psp.setProductDetailSid(skuSid);
        List<PcmShoppeProduct> list2 = shoppeProMapper.selectListByParam(psp);
        List<PublishDTO> pushDto = new ArrayList<PublishDTO>();
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
        return pushDto;
    }

    /**
     * 根据Code查询专柜商品及品牌Sid
     *
     * @param paramMap
     * @return Map<String,Object>
     * @Methods Name getProAndBrandSidByCode
     * @Create In 2015年11月18日 By yedong
     */
    public Map<String, Object> getProAndBrandSidByCode(Map<String, Object> paramMap) {
        Map<String, Object> sidMap = shoppeProMapper.getProAndBrandSidByCode(paramMap);
        return sidMap;
    }

    /**
     * 按专柜商品编码 分页 查询专柜商品基础信息
     */
    public Page<ProductPageDto> selectBaseProPageByProCodes(ProductPageProCodesDto pageDto)
            throws BleException {
        Page<ProductPageDto> page = new Page<ProductPageDto>();
        if (pageDto.getCurrentPage() != null && pageDto.getCurrentPage() != 0) {
            page.setCurrentPage(pageDto.getCurrentPage());
        }
        if (pageDto.getPageSize() != null && pageDto.getPageSize() != 0) {
            page.setPageSize(pageDto.getPageSize());
        } else if (pageDto.getLimit() != null) {
            page.setPageSize(pageDto.getLimit());
        } else {
            // 无pageSize参数时最大不超过20条商品信息数据
            page.setPageSize(20);
        }
        // 查询参数处理
        Map<String, Object> paramMap = paramProcess1(pageDto);
        // 查询总数量
        Integer count = shoppeProMapper.getProductCountByPara2(paramMap);
        if (count != 0) {
            page.setCount(count);
        }
        if (pageDto.getStart() != null && pageDto.getLimit() != null) {
            paramMap.put("start", pageDto.getStart());
            paramMap.put("limit", pageDto.getLimit());
            page.setStart(pageDto.getStart());
            page.setLimit(pageDto.getLimit());
        } else if (pageDto.getPageSize() != null && pageDto.getPageSize() != 0) {
            paramMap.put("start", page.getStart());
            paramMap.put("limit", page.getLimit());
        }
        List<Map<String, Object>> list = shoppeProMapper.selectProductPageByPara(paramMap);
        if (!list.isEmpty()) {
            List<ProductPageDto> finalList = new ArrayList<ProductPageDto>();
            for (Map<String, Object> map : list) {
                // 结果处理
                ProductPageDto dto = resultProcess(map);
                finalList.add(dto);
            }
            page.setList(finalList);
        } else {
            page.setList(null);
        }
        return page;
    }

    /**
     * 删除缓存
     *
     * @param code
     * @Methods Name cacheDelete
     * @Create In 2015年10月8日 By zhangxy
     */
    public void cacheDelete(String code) {
        boolean del = redisUtil.del(DomainName.getShoppeInfo + code);
        if (!del) {
            ProductPageDto pageDto = new ProductPageDto();
            pageDto.setProductCode(code);
            OmsResProInfoDto page = getProductPageByPara(code, pageDto);
            String pageJson = JsonUtil.getJSONString(page);
            PcmRedis redis = new PcmRedis();
            redis.setCreatetime(new Date());
            redis.setValue(pageJson);
            redis.setRedisffield(DomainName.getCMSSHopperInfo);
            redis.setKeyname(code);
            redisService.savePcmRedis(redis);
        }

        RedisVo vo2 = new RedisVo();
        vo2.setKey("skuPage");
        vo2.setField(DomainName.getShoppeInfo);
        vo2.setType(CacheUtils.HDEL);
        CacheUtils.setRedisData(vo2);
    }

    public Page<ProductPageDto> selectSAPProList(Map<String, Object> paramMap) {
        Page<ProductPageDto> page = new Page<ProductPageDto>();
        List<Map<String, Object>> list = shoppeProMapper.selectSAPProList(paramMap);
        if (!list.isEmpty()) {
            List<ProductPageDto> finalList = new ArrayList<ProductPageDto>();
            for (Map<String, Object> map : list) {
                // 结果处理
                ProductPageDto dto = resultProcess(map);
                finalList.add(dto);
            }
            page.setList(finalList);
        } else {
            page.setList(null);
        }
        return page;
    }

    /**
     * 查询需要下发门店系统的专柜商品信息
     */
    public List<PushToPcmEfutureERPFromPcmDto> pushDtoProcess1(List<String> list, Integer type) {
        Map<String, Object> para = new HashMap<String, Object>();// 封装专柜商品sid列表
        para.put("sidList", list);
        if (list.size() != 0) {
            para.put("limit", list.size());
        }
        List<PushToPcmEfutureERPFromPcmDto> resultList = shoppeProMapper
                .selectProPageByParamForPis(para);
        List<PushToPcmEfutureERPFromPcmDto> resultList1 = new ArrayList<PushToPcmEfutureERPFromPcmDto>();
        for (PushToPcmEfutureERPFromPcmDto pc : resultList) {
            if (pc.getOperateMode().equals("0")) {
                pc.setOperateMode("Z001");
            } else if (pc.getOperateMode().equals("1")) {
                pc.setOperateMode("Z002");
            } else if (pc.getOperateMode().equals("2")) {
                pc.setOperateMode("Z003");
            } else if (pc.getOperateMode().equals("3")) {
                pc.setOperateMode("Z004");
            } else if (pc.getOperateMode().equals("4")) {
                pc.setOperateMode("Z005");
            }
            if (type != null) {
                if (type == 0) {
                    pc.setActionCode("A");
                } else if (type == 1) {
                    pc.setActionCode("U");
                } else {
                    pc.setActionCode("D");
                }
            }
            if ("0".equals(pc.getIsPromotion())) {
                pc.setIsPromotion(Constants.Y);
            } else {
                pc.setIsPromotion(Constants.N);
            }
            if ("0".equals(pc.getIsAdjustPrice())) {
                pc.setIsAdjustPrice(Constants.Y);
            } else {
                pc.setIsAdjustPrice(Constants.N);
            }
            pc.setIsSelllPurchase("");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd.HHmmssZ");
            pc.setActionDate(formatter.format(new Date()));
            pc.setActionPerson("WFJ");
            resultList1.add(pc);
        }
        return resultList1;
    }

    public Integer searchSpCountByStore(Map<String, Object> paramMap) {
        return shoppeProMapper.searchSpCountByStore(paramMap);
    }

    public List<PcmProByOrgCodeDto> searchSpByStore(Map<String, Object> paramMap) {
        Integer num = (Integer) paramMap.get("num");
        List<Map<String, Object>> sidMap = shoppeProMapper.getStoreCodeByProCode(paramMap);
        List<String> sidList = new ArrayList<String>();
        for (Map<String, Object> map : sidMap) {
            sidList.add(map.get("proSid").toString());
        }
        if (sidList != null && sidList.size() > 0) {
            paramMap.clear();
            paramMap.put("sidList", sidList);
            paramMap.put("num", num);
            List<PcmProByOrgCodeDto> searchSpByStore = shoppeProMapper.searchSpByStore(paramMap);
            return searchSpByStore;
        }
        return null;
    }

    /**
     * 按专柜商品编码 分页 查询专柜商品基础信息 修改SQL20160329
     */
    public Page<ShoProInfoFormPcmToPisDto> selectBaseProPageByProCodes1(
            ShoProInfoFormPcmToPis pageDto) throws BleException {
        Page<ShoProInfoFormPcmToPisDto> page = new Page<ShoProInfoFormPcmToPisDto>();
        if (pageDto.getCurrentPage() != null && pageDto.getCurrentPage() != 0) {
            page.setCurrentPage(pageDto.getCurrentPage());
        }
        if (pageDto.getPageSize() != null && pageDto.getPageSize() != 0) {
            page.setPageSize(pageDto.getPageSize());
        } else if (pageDto.getLimit() != null) {
            page.setPageSize(pageDto.getLimit());
        } else {
            // 无pageSize参数时最大不超过20条商品信息数据
            page.setPageSize(20);
        }
        // 查询参数处理
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("itemIds", pageDto.getItemIds());
        // 查询总数量
        Integer count = shoppeProMapper.getShoProCountFormPcmToPis1(paramMap);
        if (count != 0) {
            page.setCount(count);
        }
        if (pageDto.getStart() != null && pageDto.getLimit() != null) {
            paramMap.put("start", pageDto.getStart());
            paramMap.put("limit", pageDto.getLimit());
            page.setStart(pageDto.getStart());
            page.setLimit(pageDto.getLimit());
        } else if (pageDto.getPageSize() != null && pageDto.getPageSize() != 0) {
            paramMap.put("start", page.getStart());
            paramMap.put("limit", page.getLimit());
        }
        List<ShoProInfoFormPcmToPisDto> list = shoppeProMapper.getShoProInfoFormPcmToPis(paramMap);
        if (!list.isEmpty()) {
            page.setList(list);
        } else {
            page.setList(null);
        }
        return page;
    }

    /**
     * Oms根据专柜商品编码查询指定级别的工业分类
     *
     * @param dto
     * @return
     */
    @Override
    public OmsShoppeProductResultDto findIndustryCategoryByParaForOms(OmsShoppeProductDto dto) {
        logger.debug("findIndustryCategoryByParaForOms(),param:" + dto.toString());
        String shoppeProSid = dto.getShoppeProSid();
        if (com.wangfj.core.utils.StringUtils.isEmpty(shoppeProSid)) {
            throw new BleException(ErrorCode.PRODUCT_NULL.getErrorCode(), "Oms查询没有传专柜商品编码!");
        }

        String industryCategoryLevel = dto.getIndustryCategoryLevel();
        if (com.wangfj.core.utils.StringUtils.isEmpty(industryCategoryLevel)) {
            industryCategoryLevel = "2";
        } else {
            if (Integer.parseInt(industryCategoryLevel) < 1) {
                throw new BleException(ErrorCode.PRODUCT_NULL.getErrorCode(), "Oms查询传入的工业分类的级别不合法!");
            }
        }

        OmsShoppeProductResultDto resultDto = shoppeProMapper.findIndustryCategoryByParaForOms(dto);

        String resultDtoIndustryCategoryLevel = resultDto.getIndustryCategoryLevel();//专柜商品对应的SPU上工业分类级数
        if (!industryCategoryLevel.equals(resultDtoIndustryCategoryLevel)) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("sid", resultDto.getCategoryParentSid());
            List<PcmCategory> categoryList = null;
            categoryList = categoryMapper.selectListByParam(paramMap);
            PcmCategory category = null;
            Integer level = null;
            if (categoryList.size() > 0) {
                category = categoryList.get(0);
                level = category.getLevel();
                boolean queryFlag = industryCategoryLevel.equals(level.toString());
                while (!queryFlag) {
                    paramMap.clear();
                    paramMap.put("sid", category.getParentSid());
                    categoryList = categoryMapper.selectListByParam(paramMap);
                    if (categoryList.size() > 0) {
                        category = categoryList.get(0);
                        level = category.getLevel();
                        queryFlag = industryCategoryLevel.equals(level.toString());
                    } else {
                        queryFlag = true;
                    }
                }
                resultDto.setIndustryCategoryCode(category.getCategoryCode());
                resultDto.setIndustryCategoryLevel(level.toString());
            }
        }

        logger.debug("end findIndustryCategoryByParaForOms(),return:" + resultDto.toString());
        return resultDto;
    }
}

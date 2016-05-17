package com.wangfj.product.supplier.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangfj.core.constants.ComErrorCodeConstants;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.StringUtils;
import com.wangfj.product.maindata.domain.entity.PcmErpProduct;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProduct;
import com.wangfj.product.maindata.persistence.PcmErpProductMapper;
import com.wangfj.product.maindata.persistence.PcmShoppeProductMapper;
import com.wangfj.product.supplier.domain.entity.PcmShoppeProductSupply;
import com.wangfj.product.supplier.domain.entity.PcmSupplyInfo;
import com.wangfj.product.supplier.domain.vo.PcmShoppeProSupplyUploadDto;
import com.wangfj.product.supplier.persistence.PcmShoppeProductSupplyMapper;
import com.wangfj.product.supplier.persistence.PcmSupplyInfoMapper;
import com.wangfj.product.supplier.service.intf.IPcmShoppeProductSupplyService;
import com.wangfj.util.Constants;

/**
 * 专柜商品对应多个供应商
 *
 * @Class Name PcmShoppeProductSupplyServiceImpl
 * @Author wuxiong
 * @Create In 2015年7月13日
 */
@Service
public class PcmShoppeProductSupplyServiceImpl implements IPcmShoppeProductSupplyService {

    private static final Logger logger = LoggerFactory
            .getLogger(PcmShoppeProductSupplyServiceImpl.class);

    @Autowired
    private PcmShoppeProductSupplyMapper shoppeProductSupplyMapper;
    @Autowired
    private PcmSupplyInfoMapper supplyInfoMapper;
    @Autowired
    private PcmShoppeProductMapper shoppeProductMapper;
    @Autowired
    private PcmErpProductMapper erpProductMapper;

    /**
     * 判重
     *
     * @param shoppeProductSupply
     * @return Boolean
     * @Methods Name isExist
     * @Create In 2015-9-17 By wangxuan
     */
    @Override
    public Boolean isExist(PcmShoppeProductSupply shoppeProductSupply) {
        logger.info("start isExist(),param:" + shoppeProductSupply.toString());

        Boolean flag = false;// 标记是否存在，默认不存在

        Map<String, Object> paramMap = new HashMap<String, Object>();

        Long supplySid = shoppeProductSupply.getSupplySid();// 供应商sid
        Long shoppeProductSid = shoppeProductSupply.getShoppeProductSid();// 专柜商品sid
        String productSid = shoppeProductSupply.getProductSid();// 大码sid
        if (supplySid != null) {
            paramMap.put("supplySid", supplySid);
        }
        if (shoppeProductSid != null) {
            paramMap.put("shoppeProductSid", shoppeProductSid);
        }
        if (StringUtils.isNotEmpty(productSid)) {
            paramMap.put("productSid", productSid);
        }
        paramMap.put("status", Constants.PUBLIC_0);// 有效标记，未删除的

        List<PcmShoppeProductSupply> list = shoppeProductSupplyMapper.getListByParam(paramMap);
        if (list != null && !list.isEmpty()) {
            flag = true;// 修改为存在
        }
        logger.info("end isExist(),return:" + flag);
        return flag;

    }

    /**
     * 一品多供应商关系上传
     *
     * @param dto
     * @return Map<String, Object>
     * @Methods Name uploadShoppeProSupply
     * @Create In 2015-8-28 By wangxuan
     */
    @Override
    @Transactional
    public Map<String, Object> uploadShoppeProSupply(PcmShoppeProSupplyUploadDto dto) {
        logger.info("start uploadShoppeProSupply(),param:" + dto.toString());

        Map<String, Object> resultMap = new HashMap<String, Object>();
        Integer flag = Constants.PUBLIC_0;

        String storeCode = dto.getStoreCode();// 门店编码
        String supplierCode = dto.getSupplierCode();// 供应商编码
        String supplierProductCode = dto.getSupplierProductCode();// 专柜商品编码
        String productCode = dto.getProductCode();// 大码编码
        String ACTION_CODE = dto.getACTION_CODE();// 本条记录对应的操作 (A添加；U更新；D删除)

        PcmShoppeProductSupply shoppeProductSupply = new PcmShoppeProductSupply();
        // 封装供应商sid
        if (StringUtils.isNotEmpty(storeCode) && StringUtils.isNotEmpty(supplierCode)) {
            Map<String, Object> paramSupply = new HashMap<String, Object>();
            paramSupply.put("shopSid", storeCode);
            paramSupply.put("supplyCode", supplierCode);
            List<PcmSupplyInfo> supplyInfoList = supplyInfoMapper
                    .selectSupplyByShopCodeAndSupplyCode(paramSupply);
            if (supplyInfoList != null && supplyInfoList.size() == 1) {
                shoppeProductSupply.setSupplySid(supplyInfoList.get(0).getSid());
            } else {
                throw new BleException(
                        ComErrorCodeConstants.ErrorCode.SUPPLYINFO_NOT_EXISTENCE.getErrorCode(),
                        ComErrorCodeConstants.ErrorCode.SUPPLYINFO_NOT_EXISTENCE.getMemo());
            }
        }
        Map<String, Object> shoppeProductMap = null;
        if (StringUtils.isNotEmpty(supplierProductCode)) {
            // 封装专柜商品sid
            Map<String, Object> paramShoppePro = new HashMap<String, Object>();
            paramShoppePro.put("shoppeProSid", supplierProductCode);
            List<PcmShoppeProduct> shoppeProductList = shoppeProductMapper
                    .selectShoppeProductByShoppeProCode(paramShoppePro);
            if (shoppeProductList != null && shoppeProductList.size() == 1) {
                Long sid = shoppeProductList.get(0).getSid();
                shoppeProductSupply.setShoppeProductSid(sid);
                //下发专柜商品的sid
                if (ACTION_CODE.trim().toUpperCase().equals(Constants.D)) {
                    shoppeProductMap = new HashMap<String, Object>();
                    shoppeProductMap.put("sid", sid);
                    shoppeProductMap.put("type", 1);
                }
            }
        } else {
            // 封装大码sid
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("productCode", productCode);
            List<PcmErpProduct> list = erpProductMapper.selectListByParam(paramMap);
            if (list != null && list.size() == 1) {
                shoppeProductSupply.setProductSid(list.get(0).getSid() + "");
            }
        }

        if (StringUtils.isNotEmpty(ACTION_CODE)) {
            shoppeProductSupply.setStatus(Constants.PUBLIC_0);// 有效标记
            if (ACTION_CODE.trim().toUpperCase().equals(Constants.A)) {
                if (!isExist(shoppeProductSupply)) {// 判重
                    flag = shoppeProductSupplyMapper.insertSelective(shoppeProductSupply);
                } else {
                    throw new BleException(
                            ComErrorCodeConstants.ErrorCode.PCMSHOPPEPRODUCTSUPPLY_RELATION_EXISTENCE
                                    .getErrorCode(),
                            ComErrorCodeConstants.ErrorCode.PCMSHOPPEPRODUCTSUPPLY_RELATION_EXISTENCE
                                    .getMemo());
                }
            }

            if (ACTION_CODE.trim().toUpperCase().equals(Constants.D)) {
                // Map<String, Object> paramMap = new HashMap<String, Object>();
                // paramMap.put("supplySid",
                // shoppeProductSupply.getSupplySid());
                // paramMap.put("shoppeProductSid",
                // shoppeProductSupply.getShoppeProductSid());
                // // 有效标记，未删除的
                // paramMap.put("status", Constants.PUBLIC_0);
                // List<PcmShoppeProductSupply> list =
                // shoppeProductSupplyMapper.getListByParam(paramMap);
                List<PcmShoppeProductSupply> list = shoppeProductSupplyMapper.selectListByParam(shoppeProductSupply);
                if (list != null && !list.isEmpty()) {
                    list.get(0).setStatus(Constants.PUBLIC_1);// 删除标记
                    flag = shoppeProductSupplyMapper.updateByPrimaryKeySelective(list.get(0));
                }
            }

            resultMap.put("result", flag);
            resultMap.put("shoppeProductMap", shoppeProductMap);
        }
        logger.info("end uploadShoppeProSupply(),return:" + resultMap);
        return resultMap;
    }
}

package com.wangfj.product.organization.service.impl;

import com.wangfj.core.utils.StringUtils;
import com.wangfj.product.organization.domain.entity.PcmChannelSaleConfig;
import com.wangfj.product.organization.domain.vo.PcmChannelSaleConfigQueryDto;
import com.wangfj.product.organization.domain.vo.PcmChannelSaleConfigResultDto;
import com.wangfj.product.organization.persistence.PcmChannelSaleConfigMapper;
import com.wangfj.product.organization.service.intf.IPcmChannelSaleConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PcmChannelSaleConfigServiceImpl implements IPcmChannelSaleConfigService {

    private static final Logger logger = LoggerFactory.getLogger(PcmShoppeServiceImpl.class);

    @Autowired
    public PcmChannelSaleConfigMapper channelSaleConfigMapper;

    @Override
    public List<PcmChannelSaleConfig> findListByParam(Map<String, Object> paramMap) {

        logger.info("start findListByParam(),param:" + paramMap.toString());

        List<PcmChannelSaleConfig> list = channelSaleConfigMapper.selectListByParam(paramMap);

        logger.info("end findListByParam(),return:" + list.toString());
        return list;

    }

    @Override
    public List<PcmChannelSaleConfigResultDto> findListByShoppeParam(
            PcmChannelSaleConfigQueryDto dto) {

        logger.info("start findListByShoppeParam(),param:" + dto.toString());

        List<PcmChannelSaleConfigResultDto> list = channelSaleConfigMapper
                .findListByShoppeParam(dto);

        logger.info("end findListByShoppeParam(),return:" + list.toString());
        return list;

    }

    /**
     * 根据专柜商品编码查询渠道可售
     * @param dto
     * @return
     */
    @Override
    public List<PcmChannelSaleConfigResultDto> findListByShoppeProParam(
            PcmChannelSaleConfigQueryDto dto) {

        logger.info("start findListByShoppeProParam(),param:" + dto.toString());

        List<PcmChannelSaleConfigResultDto> list = new ArrayList<PcmChannelSaleConfigResultDto>();
        String shoppeProCode = dto.getShoppeProCode();
        if (StringUtils.isNotEmpty(shoppeProCode)) {
            list = channelSaleConfigMapper.findListByShoppeProParam(dto);
        }

        logger.info("end findListByShoppeProParam(),return:" + list.toString());
        return list;

    }

}

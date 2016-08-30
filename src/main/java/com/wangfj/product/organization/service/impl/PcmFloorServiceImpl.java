package com.wangfj.product.organization.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangfj.core.constants.ComErrorCodeConstants;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.DateUtil;
import com.wangfj.core.utils.StringUtils;
import com.wangfj.product.organization.domain.entity.PcmFloor;
import com.wangfj.product.organization.domain.entity.PcmOrganization;
import com.wangfj.product.organization.domain.vo.PcmFloorDto;
import com.wangfj.product.organization.domain.vo.PcmFloorResultDto;
import com.wangfj.product.organization.domain.vo.SelectPcmFloorDto;
import com.wangfj.product.organization.persistence.PcmFloorMapper;
import com.wangfj.product.organization.persistence.PcmOrganizationMapper;
import com.wangfj.product.organization.service.intf.IPcmFloorService;
import com.wangfj.util.Constants;

@Service
public class PcmFloorServiceImpl implements IPcmFloorService {

    private static final Logger logger = LoggerFactory.getLogger(PcmFloorServiceImpl.class);

    @Autowired
    public PcmFloorMapper pcmFloorMapper;

    @Autowired
    public PcmOrganizationMapper pcmOrganizationMapper;

    public List<PcmFloorDto> findFloorByParamFromPcm(Map<String, Object> paramMap)
            throws IllegalAccessException, InvocationTargetException {
        List<Map<String, Object>> findFloor = pcmFloorMapper.findFloorByParamFromPcm(paramMap);
        List<PcmFloorDto> listFloorDto = new ArrayList<PcmFloorDto>();
        for (int i = Constants.PUBLIC_0; i < findFloor.size(); i++) {
            PcmFloorDto pcmFloorDto = new PcmFloorDto();
            BeanUtils.copyProperties(pcmFloorDto, findFloor.get(i));
            listFloorDto.add(pcmFloorDto);
        }
        return listFloorDto;
    }

    @Override
    public Integer getCountByParam(Map<String, Object> map) {
        Integer count = pcmFloorMapper.getCountByParam(map);
        return count;
    }

    @Override
    public boolean isExistence(Map<String, Object> para) {

        logger.info("start isExistence(),param:" + para.toString());
        boolean flag = false;
        /* 同一门店编码下不能有相同的楼层名称，也不能有相同的楼层编码 */
        Map<String, Object> paramMap = new HashMap<String, Object>();

        String floorCode = para.get("floorCode") + "";
        if (StringUtils.isNotEmpty(floorCode)) {
            paramMap.clear();
            paramMap.put("shopSid", para.get("shopSid"));
            paramMap.put("floorStatus", Constants.PUBLIC_0);
            paramMap.put("floorCode", floorCode);
            List<PcmFloor> codeList = pcmFloorMapper.selectListByParam(paramMap);
            if (codeList != null && codeList.size() > 0) {
                flag = true;
                throw new BleException(
                        ComErrorCodeConstants.ErrorCode.FLOOR_CODE_NOT_ONLY.getErrorCode(),
                        ComErrorCodeConstants.ErrorCode.FLOOR_CODE_NOT_ONLY.getMemo());
            }
        }

        String floorName = para.get("floorName") + "";
        if (StringUtils.isNotEmpty(floorName)) {
            paramMap.clear();
            paramMap.put("shopSid", para.get("shopSid"));
            paramMap.put("floorStatus", Constants.PUBLIC_0);
            paramMap.put("floorName", floorName);
            List<PcmFloor> nameList = pcmFloorMapper.selectListByParam(paramMap);
            if (nameList != null && nameList.size() > 0) {
                flag = true;
                throw new BleException(
                        ComErrorCodeConstants.ErrorCode.FLOOR_NAME_NOT_ONLY.getErrorCode(),
                        ComErrorCodeConstants.ErrorCode.FLOOR_NAME_NOT_ONLY.getMemo());
            }
        }

        logger.info("end isExistence(),return:" + flag);
        return flag;
    }

    /**
     * 添加楼层
     */
    @Override
    @Transactional
    public Map<String, Object> addFloor(PcmFloorDto dto) {

        logger.info("start addFloor(),param:" + dto.toString());

        Map<String, Object> resultMap = new HashMap<String, Object>();

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("organizationType", Constants.PUBLIC_3);
        paramMap.put("organizationStatus", Constants.PUBLIC_0);
        // 门店sid
        paramMap.put("sid", dto.getShopSid());
        List<PcmOrganization> shopList = pcmOrganizationMapper.selectListByParam(paramMap);
        if (shopList != null && shopList.size() == 1) {
            /* 同一门店编码下不能有相同的楼层名称，也不能有相同的楼层编码 */
            paramMap.clear();
            Long shopSid = shopList.get(0).getSid();
            paramMap.put("shopSid", shopSid);
            paramMap.put("floorName", dto.getName());
            paramMap.put("floorCode", dto.getCode());
            boolean existence = isExistence(paramMap);
            if (!existence) {
                PcmFloor floor = new PcmFloor();
                floor.setShopSid(shopSid);
                floor.setFloorName(dto.getName());
                floor.setFloorCode(dto.getCode());
                floor.setFloorStatus(Constants.PUBLIC_0);
                floor.setCreateTime(new Date());
                floor.setCreateName(dto.getCreateName());
                int result = pcmFloorMapper.insertSelective(floor);
                if (result == 1) {
                    resultMap.put("result", result);
                    resultMap.put("sid", floor.getSid());
                }
            }
        } else {
            throw new BleException(
                    ComErrorCodeConstants.ErrorCode.FLOOR_SHOP_NOT_ERROR.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.FLOOR_SHOP_NOT_ERROR.getMemo());
        }

        logger.info("end addFloor(),reutrn:" + resultMap.toString());
        return resultMap;
    }

    /**
     * 修改楼层
     */
    @Override
    @Transactional
    public Map<String, Object> modifyFloor(PcmFloorDto dto) {

        logger.info("start modifyFloor(),param:" + dto.toString());

        Map<String, Object> resultMap = new HashMap<String, Object>();
        Integer result = Constants.PUBLIC_0;

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("organizationType", Constants.PUBLIC_3);
        paramMap.put("organizationStatus", Constants.PUBLIC_0);
        paramMap.put("sid", dto.getShopSid());
        List<PcmOrganization> shopList = pcmOrganizationMapper.selectListByParam(paramMap);
        if (shopList != null && shopList.size() == 1) {

            paramMap.clear();
            paramMap.put("sid", dto.getSid());
            paramMap.put("floorStatus", Constants.PUBLIC_0);
            List<PcmFloor> floorList = pcmFloorMapper.selectListByParam(paramMap);
            if (floorList != null && floorList.size() == 1) {
                String floorName = floorList.get(0).getFloorName();
                if (!floorName.equals(dto.getName())) {
                    paramMap.clear();
                    paramMap.put("shopSid", dto.getShopSid());
                    paramMap.put("floorName", dto.getName());
                    boolean existence = isExistence(paramMap);
                    if (existence) {
                        throw new BleException(
                                ComErrorCodeConstants.ErrorCode.FLOOR_NAME_NOT_ONLY.getErrorCode(),
                                ComErrorCodeConstants.ErrorCode.FLOOR_NAME_NOT_ONLY.getMemo());
                    }
                }

                PcmFloor floor = new PcmFloor();
                floor.setSid(dto.getSid());
                floor.setFloorCode(dto.getCode());
                floor.setFloorName(dto.getName());
                floor.setUpdateTime(new Date());
                floor.setShopSid(dto.getShopSid());
                result = pcmFloorMapper.updateByPrimaryKeySelective(floor);

            }
        } else {
            throw new BleException(
                    ComErrorCodeConstants.ErrorCode.FLOOR_SHOP_NOT_ERROR.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.FLOOR_SHOP_NOT_ERROR.getMemo());
        }

        resultMap.put("result", result);
        logger.info("end modifyFloor(),return:" + resultMap.toString());
        return resultMap;

    }

    /*
     * (non-Javadoc)分页查询楼层信息
     *
     * @see com.wangfj.product.organization.service.intf.IPcmFloorService#
     * findFloorFromPCM(com.wangfj.product.organization.domain.vo.PcmFloorDto,
     * com.wangfj.core.framework.base.page.Page)
     */
    @Override
    public Page<HashMap<String, Object>> findFloorFromPCM(PcmFloorDto floordto,
                                                          Page<PcmFloorDto> pageorg) {
        logger.info("start findFloorFromPCM()");
        // 按条件查询
        Map<String, Object> map = new HashMap<String, Object>();
        // map.put("storeName", floordto.getStoreName());
        map.put("name", floordto.getName());
        map.put("code", floordto.getCode());
        map.put("storeCode", floordto.getStoreCode());
        map.put("floorStatus", floordto.getFloorStatus());
        map.put("floorSid", floordto.getSid());
        Integer count = pcmFloorMapper.getFloorCount(map);
        pageorg.setCount(count);
        int count1 = count / pageorg.getPageSize();// 整数
        int count2 = count % pageorg.getPageSize();// 余数
        int start = (pageorg.getCurrentPage() - 1) * pageorg.getPageSize();
        if (count2 == Constants.PUBLIC_0) {// 正好是整页
            if (count1 < pageorg.getCurrentPage()) {
                throw new BleException(
                        ComErrorCodeConstants.ErrorCode.SHOPPE_PAGE_ERROR.getErrorCode(),
                        ComErrorCodeConstants.ErrorCode.SHOPPE_PAGE_ERROR.getMemo());
            } else {
                map.put("start", start);
                map.put("limit", pageorg.getPageSize());
            }
        } else {
            if ((count1 + 1) < pageorg.getCurrentPage()) {
                throw new BleException(
                        ComErrorCodeConstants.ErrorCode.SHOPPE_PAGE_ERROR.getErrorCode(),
                        ComErrorCodeConstants.ErrorCode.SHOPPE_PAGE_ERROR.getMemo());
            } else if ((count1 + 1) == pageorg.getCurrentPage()) {
                map.put("start", start);
                map.put("limit", count2);
            } else {
                map.put("start", start);
                map.put("limit", pageorg.getPageSize());
            }
        }
        map.put("start", pageorg.getCurrentPage());
        map.put("limit", pageorg.getPageSize());
        if (floordto.getName() != null) {
            map.put("start", 0);
            map.put("limit", count);
        }
        if (floordto.getCode() != null) {
            map.put("start", 0);
            map.put("limit", count);
        }
        if (floordto.getCode() != null && floordto.getName() != null) {
            map.put("start", 0);
            map.put("limit", count);
        }
        List<HashMap<String, Object>> floorlList = pcmFloorMapper.findFloorByParam(map);
        if (floorlList.size() == 0) {
            throw new BleException(ComErrorCodeConstants.ErrorCode.FLOOR_PAGE_ERROR.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.FLOOR_PAGE_ERROR.getMemo());
        }
        Page<HashMap<String, Object>> page = new Page<HashMap<String, Object>>();
        if (!floorlList.isEmpty()) {
            page.setList(floorlList);
            page.setCurrentPage(pageorg.getCurrentPage());
            page.setPageSize(pageorg.getPageSize());
            page.setCount(count);
            return page;
        } else {
            logger.error("查询结果为空");
            throw new BleException(ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
        }

    }

    /*
     * (non-Javadoc)根据sid查询楼层信息
     *
     * @see com.wangfj.product.organization.service.intf.IPcmFloorService#
     * selectByFloorSid(java.lang.Long)
     */
    @Override
    public PcmFloorDto selectByFloorSid(Long sid) {
        logger.info("start selectByFloorSid()");
        PcmFloorDto floorDto = pcmFloorMapper.findFloorBySid(sid);
        logger.info("end selectByFloorSid()");
        return floorDto;
    }

    /**
     * 删除楼层
     */
    @Override
    public Integer deleteFloor(PcmFloorDto dto) {
        logger.info("开始删除");
		/* 门店编码和楼层名称都不能重复 */
        PcmFloorDto floor = new PcmFloorDto();
        Integer count = 0;
        Integer count1 = 0;
        floor.setStoreCode(dto.getStoreCode());
        count = pcmFloorMapper.selectFloorByParam(floor);
        if (Constants.PUBLIC_1 == count) {
            floor.setSid(dto.getSid());
            floor.setName(dto.getName());
            count = pcmFloorMapper.selectFloorByParam(floor);
            // 楼层编码判重
            if (dto.getCode() != null) {
                floor.setCode(dto.getCode());
                count1 = pcmFloorMapper.selectFloorByParam(floor);
                if (count > Constants.PUBLIC_0 || count1 > Constants.PUBLIC_0) {
                    logger.error("数据已存在");
                    throw new BleException(
                            ComErrorCodeConstants.ErrorCode.UPDATE_HAVE_ERROR.getErrorCode(),
                            ComErrorCodeConstants.ErrorCode.UPDATE_HAVE_ERROR.getMemo());
                } else {
                    PcmFloor pcm = new PcmFloor();
                    pcm.setFloorStatus(1);
                    pcm.setSid(dto.getSid());
                    count = pcmFloorMapper.updateByPrimaryKeySelective(pcm);
                    if (count != Constants.PUBLIC_1) {
                        throw new RuntimeException("删除楼层信息失败");
                    } else {
                        logger.info("删除楼层信息成功");
                    }
                }
            }

        } else {
            logger.error("没有找到唯一的门店");
            throw new RuntimeException("没有找到唯一的门店");
        }
        return count;
    }

    @Override
    public List<Map<String, Object>> getFloorsByShopSid(Long shopSid) {

        return this.pcmFloorMapper.getFloorsByShopSid(shopSid);
    }

    /**
     * 分页查询
     *
     * @param dto
     * @return Page<PcmFloorResultDto>
     * @Methods Name findPageFloor
     * @Create In 2015-8-25 By niuzhifan
     */
    @Override
    public Page<PcmFloorResultDto> findPageFloor(SelectPcmFloorDto dto) {

        logger.info("start findPageFloor(),para:" + dto.toString());

        Page<PcmFloorResultDto> page = new Page<PcmFloorResultDto>();
        page.setCurrentPage(dto.getCurrentPage());
        page.setPageSize(dto.getPageSize());

        // 默认查询有效
        if (dto.getFloorStatus() == null) {
            dto.setFloorStatus(Constants.PUBLIC_0);
        }

        // 查询总记录数
        Integer count = pcmFloorMapper.getPageCountByPara(dto);
        page.setCount(count);

        // 分页查询
        dto.setStart(page.getStart());
        dto.setLimit(page.getLimit());
        List<PcmFloorResultDto> floorDtoList = pcmFloorMapper.selectPageByPara(dto);

        if (!floorDtoList.isEmpty()) {
            page.setList(floorDtoList);
        }

        logger.info("end findPageFloor()，return:" + floorDtoList.toString());
        return page;

    }

    /**
     * 查询楼层List
     *
     * @param dto
     * @return
     */
    @Override
    public List<PcmFloorResultDto> selectFloorListByParam(SelectPcmFloorDto dto) {

        logger.info("start findPageFloor(),para:" + dto.toString());
        List<PcmFloorResultDto> floorDtoList = pcmFloorMapper.selectFloorListByParam(dto);
        logger.info("end findPageFloor()，return:" + floorDtoList.toString());
        return floorDtoList;

    }

    /**
     * 供应商平台根据门店编码查询楼层
     *
     * @param paramMap
     * @return
     */
    @Override
    public List<PcmFloorResultDto> findFloorByParaToSupplier(Map<String, Object> paramMap) {

        logger.info("start findFloorByParaToSupplier(),para:" + paramMap.toString());
        List<PcmFloorResultDto> floorDtoList = pcmFloorMapper.findFloorByParaToSupplier(paramMap);
        logger.info("end findFloorByParaToSupplier()，return:" + floorDtoList.toString());
        return floorDtoList;

    }

    /**
     * 移动工作台调用主数据获取楼层信息列表 门店编码不可为空
     *
     * @create 2015-8-31 nzf
     */
    @Override
    public List<Map<String, Object>> selectFloorByShopCode(PcmFloorDto dto) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> mapShop = new ArrayList<Map<String, Object>>();
        map.put("organizationType", 3);
        map.put("organizationCode", dto.getStoreCode());
        List<PcmOrganization> list = pcmOrganizationMapper.selectListByParam(map);
        if (list.size() == 1) {
            map.put("storeCode", dto.getStoreCode());
            mapShop = pcmFloorMapper.getFloorsByShopCode(map);
            if (mapShop.size() > 0) {
                logger.info("查询成功");
            } else {
                throw new BleException(
                        ComErrorCodeConstants.ErrorCode.FLOOR_SHOP_NOT_RELATIVE.getErrorCode(),
                        ComErrorCodeConstants.ErrorCode.FLOOR_SHOP_NOT_RELATIVE.getMemo());
            }
        } else {
            throw new BleException(
                    ComErrorCodeConstants.ErrorCode.FLOOR_SHOP_NOT_ERROR.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.FLOOR_SHOP_NOT_ERROR.getMemo());
        }
        return mapShop;
    }

    /**
     * 移动工作台调用主数据获取门店对应楼层下所有专柜信 门店编码不可为空
     */
    @Override
    public List<Map<String, Object>> selectFloorsByShopAndFloorCode(PcmFloorDto dto) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        List<Map<String, Object>> mapShop = new ArrayList<Map<String, Object>>();
        map.put("organizationType", 3);
        map.put("organizationCode", dto.getStoreCode());
        List<PcmOrganization> list = pcmOrganizationMapper.selectListByParam(map);
        if (list.size() == 1) {
            map.put("storeCode", dto.getStoreCode());
            String floorCode = dto.getCode();// 楼层编码
            map1.put("floorCode", floorCode);
            List<PcmFloor> floorList = pcmFloorMapper.selectListByParam(map1);
            if (floorList.size() == Constants.PUBLIC_1) {
                map.put("fool", floorList.get(0).getSid());
            } else {
                logger.error("根据楼层编码未查询到楼层信息或者楼层编码为空");
                map.put("fool", null);
            }
            mapShop = pcmFloorMapper.getFloorsByShopAndFloorCode(map);
            if (mapShop.size() > 0) {
                logger.info("查询成功");
            } else {
                throw new BleException(
                        ComErrorCodeConstants.ErrorCode.FLOOR_SHOP_NOT_RELATIVE.getErrorCode(),
                        ComErrorCodeConstants.ErrorCode.FLOOR_SHOP_NOT_RELATIVE.getMemo());
            }
        } else {
            throw new BleException(
                    ComErrorCodeConstants.ErrorCode.FLOOR_SHOP_NOT_ERROR.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.FLOOR_SHOP_NOT_ERROR.getMemo());
        }
        return mapShop;
    }

    /**
     * 主数据ERP发送楼层字典到其他系统(增量下发)
     */
    @Override
    public List<Map<String, Object>> selectFloorsANDOrg(Map<String, Object> dtoMap) {
        return pcmFloorMapper.getFloorsAndOrg(dtoMap);
    }

    /**
     * 下发查询
     */
    @Override
    public List<Map<String, Object>> pushFloorData(Map<String, Object> para) {

        logger.info("start pushFloorData(),para:" + para.toString());

        String sid = para.get("sid") + "";
        String actionCode = para.get("actionCode") + "";

        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtils.isNotEmpty(sid)) {
            paramMap.put("sid", Long.parseLong(sid.trim()));
        }

        List<Map<String, Object>> list = pcmFloorMapper.pushFloorData(paramMap);
        if (list != null && !list.isEmpty()) {
            for (Map<String, Object> map : list) {
                map.put("actionCode", actionCode);
                map.put("actionDate", DateUtil.formatToStr(new Date(), "yyyyMMdd.HHmmssZ"));
            }
        }

        logger.info("end pushFloorData()");
        return list;
    }

}

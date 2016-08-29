package com.wangfj.product.organization.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.organization.domain.entity.PcmFloor;
import com.wangfj.product.organization.domain.vo.PcmFloorDto;
import com.wangfj.product.organization.domain.vo.PcmFloorResultDto;
import com.wangfj.product.organization.domain.vo.SelectPcmFloorDto;

/**
 * @Class Name PcmFloorMapper
 * @Author yedong
 * @Create In 2015年7月3日
 */
public interface PcmFloorMapper extends BaseMapper<PcmFloor> {
    /**
     * 删除
     *
     * @param sid
     * @return int
     * @Methods Name deleteByPrimaryKey
     * @Create In 2015年7月3日 By yedong
     */
    int deleteByPrimaryKey(Long sid);

    /**
     * 添加
     *
     * @param record
     * @return int
     * @Methods Name insertSelective
     * @Create In 2015年7月3日 By yedong
     */
    int insertSelective(PcmFloor record);

    /**
     * 修改
     *
     * @param record
     * @return int
     * @Methods Name updateByPrimaryKeySelective
     * @Create In 2015年7月3日 By yedong
     */
    int updateByPrimaryKeySelective(PcmFloor record);

    /**
     * 楼层信息发布
     *
     * @param paramMap
     * @return List<PcmFloorDto>
     * @Methods Name findFloorByParamFromPcm
     * @Create In 2015年7月16日 By yedong
     */
    List<Map<String, Object>> findFloorByParamFromPcm(Map<String, Object> paramMap);

    /**
     * 判重门店编码和楼层名
     *
     * @param paramMap
     * @return Integer
     * @Methods Name selectCountByFloor
     * @Create In 2015年7月24日 By wuxiong
     */
    Integer selectCountByFloor(Map<String, Object> paramMap);

    Integer selectCountByFloors(Map<String, Object> paramMap);

    /**
     * 分页查询门店名称，楼层名称，楼层编码
     *
     * @param paramMap
     * @return List<Map<String,Object>>
     * @Methods Name findFloorByParam
     * @Create In 2015年7月27日 By wuxiong
     */
    List<HashMap<String, Object>> findFloorByParam(Map<String, Object> paramMap);

    Integer getFloorCount(Map<String, Object> paramMap);

    PcmFloorDto findFloorBySid(Long sid);

    /**
     * 测试数据
     *
     * @param dto
     * @return
     */
    Integer selectFloorByParam(PcmFloorDto dto);

    /**
     * 根据ShopSid查询此门店下的楼层列表信息
     *
     * @param shopSid
     * @return List<PcmFloor>
     * @Methods Name getFloorsByShopSid
     * @Create In 2015-8-24 By chengsj
     */
    List<Map<String, Object>> getFloorsByShopSid(Long shopSid);

    /**
     * 根据sid查询
     *
     * @param sid
     * @return PcmFloor
     * @Methods Name selectByPrimaryKey
     * @Create In 2015-8-24 By wangxuan
     */
    PcmFloor selectByPrimaryKey(Long sid);

    /**
     * 查询楼层List
     *
     * @param dto
     * @return
     */
    List<PcmFloorResultDto> selectFloorListByParam(SelectPcmFloorDto dto);

    /**
     * 供应商平台查询楼层
     *
     * @param paramMap
     * @return
     */
    List<PcmFloorResultDto> findFloorByParaToSupplier(Map<String, Object> paramMap);

    /**
     * 分页查询
     *
     * @param dto
     * @return List<PcmFloorResultDto>
     * @Methods Name selectPageByPara
     * @Create In 2015-8-25 By niuzhifan
     */
    List<PcmFloorResultDto> selectPageByPara(SelectPcmFloorDto dto);

    /**
     * 查询总数量
     *
     * @param dto
     * @return Integer
     * @Methods Name getPageCountByPara
     * @Create In 2015-8-25 By niuzhifan
     */
    Integer getPageCountByPara(SelectPcmFloorDto dto);

    /**
     * 根据门店编码查询楼层的信息，并按指定格式输出 (移动工作台调用主数据获取楼层信息列表)
     *
     * @param map
     * @return
     */
    List<Map<String, Object>> getFloorsByShopCode(Map<String, Object> map);

    /**
     * 根据门店编码和楼层编码（可为空）查询专柜的信息，并按指定格式输出 (移动工作台调用主数据获取门店对应楼层下所有专柜信)
     *
     * @param map
     * @return
     */
    List<Map<String, Object>> getFloorsByShopAndFloorCode(Map<String, Object> map);

    /**
     * 楼层信息发布
     *
     * @param paramMap
     * @return List<PcmFloorDto>
     * @Methods Name getFloorsAndOrg
     * @Create In 2015/9/8 By nzf
     */
    List<Map<String, Object>> getFloorsAndOrg(Map<String, Object> map);

    List<Map<String, Object>> pushFloorData(Map<String, Object> paramMap);

    List<PcmFloor> selectListByParam(Map<String, Object> paramMap);

}
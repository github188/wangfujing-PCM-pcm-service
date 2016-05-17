package com.wangfj.product.organization.service.intf;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.organization.domain.entity.PcmChannel;
import com.wangfj.product.organization.domain.vo.PcmChannelDto;
import com.wangfj.product.organization.domain.vo.PcmChannelPhotoDto;

public interface IPcmChannelService {
    public List<PcmChannel> publishChannelFromPCM(Map<String, Object> paramMap);

    /**
     * 查询渠道集合
     *
     * @param channel
     * @return List<PcmChannel>
     * @Methods Name selectList
     * @Create In 2015年7月31日 By duanzhaole
     */
    public List<PcmChannel> selectList(PcmChannel channel);

    /**
     * 判重
     *
     * @param channel
     * @return Boolean
     * @Methods Name isExistence
     * @Create In 2015-8-20 By wangxuan
     */
    Boolean isExistence(PcmChannel channel);

    /**
     * 添加渠道
     *
     * @param channel
     * @return Integer
     * @Methods Name addChannel
     * @Create In 2015-8-20 By wangxuan
     */
    Integer addChannel(PcmChannel channel);

    /**
     * 修改渠道
     *
     * @param channel
     * @return Integer
     * @Methods Name updateChannel
     * @Create In 2015-8-20 By wangxuan
     */
    Integer updateChannel(PcmChannel channel);

    /**
     * 根据sid查询渠道
     *
     * @param sid
     * @return PcmChannelDto
     * @Methods Name findChannelBySid
     * @Create In 2015-8-20 By wangxuan
     */
    PcmChannelDto findChannelBySid(Long sid);

    /**
     * 分页查询
     *
     * @param para
     * @return Page<PcmChannelDto>
     * @Methods Name findPageChannel
     * @Create In 2015-8-20 By wangxuan
     */
    Page<PcmChannelDto> findPageChannel(Map<String, Object> para);

    /**
     * 查询渠道
     *
     * @param para
     * @return List<PcmChannel>
     * @Methods Name selectListByParam
     * @Create In 2016-04-13 By wangxuan
     */
    List<PcmChannel> selectListByParam(Map<String, Object> para);

    /**
     * 查询渠道
     *
     * @param para
     * @return List<PcmChannelDto>
     * @Methods Name findListChannel
     * @Create In 2015-9-8 By wangxuan
     */
    List<PcmChannelDto> findListChannel(Map<String, Object> para);

    /**
     * 由主数据获取渠道信息(主数据>拍照系统)
     *
     * @param para
     * @return List<PcmChannelPhotoDto>
     * @Methods Name findListChannelForPhoto
     * @Create In 2015-10-14 By wangxuan
     */
    List<PcmChannelPhotoDto> findListChannelForPhoto(Map<String, Object> para);

    List<Map<String, Object>> pushChannelData(Map<String, Object> para);

    /**
     * 根据SPU（编码）查询渠道
     *
     * @param para
     * @return List<PcmChannelDto>
     * @Methods Name findChannelBySPUPara
     * @Create In 2016-3-1 By wangxuan
     */
    List<PcmChannelDto> findChannelBySPUPara(Map<String, Object> para);

    /**
     * 根据SPU（编码）查询渠道(拍照)
     *
     * @param para
     * @return List<PcmChannelPhotoDto>
     * @Methods Name findChannelBySPUParaForPhoto
     * @Create In 2016-3-10 By wangxuan
     */
    List<PcmChannelPhotoDto> findChannelBySPUParaForPhoto(Map<String, Object> para);
}

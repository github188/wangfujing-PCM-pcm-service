package com.wangfj.product.organization.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.wangfj.product.organization.domain.entity.PcmChannel;
import com.wangfj.product.organization.domain.vo.PcmChannelDto;
import com.wangfj.product.organization.domain.vo.PcmChannelPhotoDto;
import com.wangfj.product.organization.persistence.PcmChannelMapper;
import com.wangfj.product.organization.service.intf.IPcmChannelService;
import com.wangfj.util.Constants;

/**
 * 渠道
 *
 * @Class Name PcmChannelServiceImpl
 * @Author yedong
 * @Create In 2015年7月22日
 */
@Service
public class PcmChannelServiceImpl implements IPcmChannelService {

    @Autowired
    public PcmChannelMapper channelMapper;

    private static final Logger logger = LoggerFactory.getLogger(PcmChannelServiceImpl.class);

    @Override
    public List<PcmChannel> publishChannelFromPCM(Map<String, Object> paramMap) {
        logger.info("start publishChannelFromPCM(),param :" + paramMap.toString());
        List<PcmChannel> map = channelMapper.selectListByParam(paramMap);
        logger.info("end publishChannelFromPCM(),return :" + map.toString());
        return map;
    }

    /**
     * 下发查询
     */
    @Override
    public List<Map<String, Object>> pushChannelData(Map<String, Object> para) {

        logger.info("start pushChannelData(),param :" + para.toString());

        String sid = para.get("sid") + "";
        String actionCode = para.get("actionCode") + "";

        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtils.isNotEmpty(sid)) {
            paramMap.put("sid", Long.parseLong(sid.trim()));
        }

        List<Map<String, Object>> list = channelMapper.pushChannelData(paramMap);
        if (list != null && list.size() > 0) {
            for (Map<String, Object> map : list) {
                map.put("actionCode", actionCode);
                map.put("actionDate", DateUtil.formatToStr(new Date(), "yyyyMMdd.HHmmssZ"));
            }
        }

        logger.info("end pushChannelData(),return :" + list.toString());
        return list;
    }

    @Override
    public List<PcmChannel> selectList(PcmChannel channel) {
        return channelMapper.selectList(channel);
    }

    /**
     * 判重
     *
     * @param channel
     * @return Boolean
     * @Methods Name isExistence
     * @Create In 2015-8-20 By wangxuan
     */
    @Override
    public Boolean isExistence(PcmChannel channel) {

        logger.info("start isExistence(),para:" + channel.toString());
        Boolean flag = false;

        // 名称判重
        String channelName = channel.getChannelName();
        if (StringUtils.isNotEmpty(channelName)) {
            Map<String, Object> paramName = new HashMap<String, Object>();
            paramName.put("channelName", channelName);
            List<PcmChannel> channelList = channelMapper.selectListByParam(paramName);

            if (channelList != null && !channelList.isEmpty()) {
                flag = true;
                throw new BleException(
                        ComErrorCodeConstants.ErrorCode.CHANNEL_CHANNELNAME_EXIST.getErrorCode(),
                        ComErrorCodeConstants.ErrorCode.CHANNEL_CHANNELNAME_EXIST.getMemo());
            }

        }

        // 编码判重
        String channelCode = channel.getChannelCode();
        if (StringUtils.isNotEmpty(channelCode)) {
            Map<String, Object> paramCode = new HashMap<String, Object>();
            paramCode.put("channelCode", channelCode);
            List<PcmChannel> channelList = channelMapper.selectListByParam(paramCode);

            if (channelList != null && !channelList.isEmpty()) {
                flag = true;
                throw new BleException(
                        ComErrorCodeConstants.ErrorCode.CHANNEL_CHANNELCODE_EXIST.getErrorCode(),
                        ComErrorCodeConstants.ErrorCode.CHANNEL_CHANNELCODE_EXIST.getMemo());
            }

        }

        logger.info("end isExistence()");
        return flag;

    }

    /**
     * 添加渠道
     *
     * @param channel
     * @return Integer
     * @Methods Name addChannel
     * @Create In 2015-8-20 By wangxuan
     */
    @Override
    @Transactional
    public Integer addChannel(PcmChannel channel) {

        logger.info("start addChannel(),para:" + channel.toString());
        Integer flag = Constants.PUBLIC_0;
        if (!isExistence(channel)) {
            channel.setOptDate(new Date());
            flag = channelMapper.insertSelective(channel);
        }

        logger.info("end addChannel()");
        return flag;
    }

    /**
     * 修改渠道
     *
     * @param channel
     * @return Integer
     * @Methods Name updateChannel
     * @Create In 2015-8-20 By wangxuan
     */
    @Override
    @Transactional
    public Integer updateChannel(PcmChannel channel) {

        logger.info("start updateChannel(),para:" + channel.toString());
        Integer flag = Constants.PUBLIC_0;

        Long sid = channel.getSid();

        if (sid != null) {
            Map<String, Object> paramSid = new HashMap<String, Object>();
            paramSid.put("sid", sid);
            List<PcmChannel> channelSidList = channelMapper.selectListByParam(paramSid);
            if (channelSidList != null && channelSidList.size() == 1) {

                channel.setOptDate(new Date());
                String channelName = channel.getChannelName();
                if (StringUtils.isNotEmpty(channelName)) {
                    // 如果修改了名称,必须进行判重！
                    if (!channelName.equals(channelSidList.get(0).getChannelName())) {
                        Map<String, Object> paramName = new HashMap<String, Object>();
                        paramName.put("channelName", channelName);
                        List<PcmChannel> channelNameList = channelMapper
                                .selectListByParam(paramName);

                        if (channelNameList != null && channelNameList.size() > 0) {

                            logger.error("渠道名称已经存在。");
                            throw new BleException(
                                    ComErrorCodeConstants.ErrorCode.CHANNEL_CHANNELNAME_EXIST
                                            .getErrorCode(),
                                    ComErrorCodeConstants.ErrorCode.CHANNEL_CHANNELNAME_EXIST
                                            .getMemo());
                        }
                    }
                }

                flag = channelMapper.updateByPrimaryKeySelective(channel);

            }
        }

        logger.info("end updateChannel()");
        return flag;
    }

    /**
     * 根据sid查询渠道
     *
     * @param sid
     * @return PcmChannelDto
     * @Methods Name findChannelBySid
     * @Create In 2015-8-20 By wangxuan
     */
    @Override
    public PcmChannelDto findChannelBySid(Long sid) {

        logger.info("start findChannelBySid(),para:" + sid);

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sid", sid);
        List<PcmChannel> channelList = channelMapper.selectListByParam(paramMap);

        PcmChannelDto channelDto = new PcmChannelDto();
        if (channelList != null && channelList.size() == 1) {
            channelDto.setSid(channelList.get(0).getSid());
            channelDto.setChannelCode(channelList.get(0).getChannelCode());
            channelDto.setChannelName(channelList.get(0).getChannelName());
            channelDto.setStatus(channelList.get(0).getStatus());
            channelDto.setOptUser(channelList.get(0).getOptUser());
            Date optDate = channelList.get(0).getOptDate();
            channelDto.setOptDates(optDate);
            channelDto.setOptDateStr(DateUtil.formatToStr(optDate, "yyyy-MM-dd"));
        }

        logger.info("end findChannelBySid()");
        return channelDto;

    }

    /**
     * 分页查询
     *
     * @param para
     * @return Page<PcmChannelDto>
     * @Methods Name findPageChannel
     * @Create In 2015-8-20 By wangxuan
     */
    @Override
    public Page<PcmChannelDto> findPageChannel(Map<String, Object> para) {

        logger.info("start findPageChannel(),para:" + para.toString());

        Page<PcmChannelDto> page = new Page<PcmChannelDto>();

        String currentPage = para.get("currentPage") + "";
        String pageSize = para.get("pageSize") + "";
        if (StringUtils.isNotEmpty(currentPage)) {
            page.setCurrentPage(Integer.valueOf(currentPage));
        }
        if (StringUtils.isNotEmpty(pageSize)) {
            page.setPageSize(Integer.valueOf(pageSize));
        }

        Integer count = channelMapper.getPageCountByPara(para);
        page.setCount(count);

        para.put("start", page.getStart());
        para.put("limit", page.getLimit());
        para.put("orderBy", "sid");
        List<PcmChannel> channelList = channelMapper.selectPageByPara(para);

        List<PcmChannelDto> channelDtoList = new ArrayList<PcmChannelDto>();
        if (channelList != null && !channelList.isEmpty()) {

            for (int i = 0; i < channelList.size(); i++) {
                PcmChannelDto channelDto = new PcmChannelDto();

                channelDto.setSid(channelList.get(i).getSid());
                channelDto.setChannelCode(channelList.get(i).getChannelCode());
                channelDto.setChannelName(channelList.get(i).getChannelName());
                channelDto.setStatus(channelList.get(i).getStatus());
                channelDto.setOptUser(channelList.get(i).getOptUser());
                Date optDate = channelList.get(i).getOptDate();
                channelDto.setOptDates(optDate);
                channelDto.setOptDateStr(DateUtil.formatToStr(optDate, "yyyy-MM-dd"));

                channelDtoList.add(channelDto);
            }

        }
        page.setList(channelDtoList);

        logger.info("end findPageChannel()");
        return page;

    }

    /**
     * 查询渠道
     *
     * @param para
     * @return List<PcmChannel>
     * @Methods Name selectListByParam
     * @Create In 2016-04-13 By wangxuan
     */
    @Override
    public List<PcmChannel> selectListByParam(Map<String, Object> para) {

        logger.info("start selectListByParam(),para:" + para.toString());
        List<PcmChannel> channelList = channelMapper.selectListByParam(para);
        logger.info("end selectListByParam(),return:" + channelList.toString());
        return channelList;

    }


    /**
     * 查询渠道
     *
     * @param para
     * @return List<PcmChannelDto>
     * @Methods Name findListChannel
     * @Create In 2015-9-8 By wangxuan
     */
    @Override
    public List<PcmChannelDto> findListChannel(Map<String, Object> para) {

        logger.info("start findListChannel(),para:" + para.toString());

        // 非分页
        para.put("start", null);
        para.put("limit", null);
//        para.put("orderBy", "channelName");
        List<PcmChannel> channelList = channelMapper.selectPageByPara(para);

        List<PcmChannelDto> channelDtoList = new ArrayList<PcmChannelDto>();
        if (channelList != null && !channelList.isEmpty()) {

            for (int i = 0; i < channelList.size(); i++) {
                PcmChannelDto channelDto = new PcmChannelDto();

                channelDto.setSid(channelList.get(i).getSid());
                channelDto.setChannelCode(channelList.get(i).getChannelCode());
                channelDto.setChannelName(channelList.get(i).getChannelName());
                channelDto.setStatus(channelList.get(i).getStatus());
                channelDto.setOptUser(channelList.get(i).getOptUser());
                Date optDate = channelList.get(i).getOptDate();
                channelDto.setOptDates(optDate);
                channelDto.setOptDateStr(DateUtil.formatToStr(optDate, "yyyy-MM-dd"));

                channelDtoList.add(channelDto);
            }

        }

        logger.info("end findListChannel()");
        return channelDtoList;

    }

    /**
     * 由主数据获取渠道信息
     *
     * @param para
     * @return List<PcmChannelPhotoDto>
     * @Methods Name findListChannelForPhoto
     * @Create In 2015-10-14 By wangxuan
     */
    @Override
    public List<PcmChannelPhotoDto> findListChannelForPhoto(Map<String, Object> para) {
        logger.info("start findListChannelForPhoto(),para:" + para.toString());
        // 非分页
        para.put("start", null);
        para.put("limit", null);
        // 有效
        para.put("status", Constants.PUBLIC_0);
        List<PcmChannel> channelList = channelMapper.selectPageByPara(para);

        List<PcmChannelPhotoDto> channelDtoList = new ArrayList<PcmChannelPhotoDto>();
        if (channelList != null && !channelList.isEmpty()) {
            for (int i = 0; i < channelList.size(); i++) {
                PcmChannelPhotoDto channelDto = new PcmChannelPhotoDto();

                channelDto.setSid(channelList.get(i).getSid() + "");
                channelDto.setCode(channelList.get(i).getChannelCode());
                channelDto.setName(channelList.get(i).getChannelName());

                channelDtoList.add(channelDto);
            }
        }
        logger.info("end findListChannelForPhoto(),return:" + channelDtoList.toString());
        return channelDtoList;
    }

    /**
     * 根据SPU（编码）查询渠道
     *
     * @param para
     * @return List<PcmChannelDto>
     * @Methods Name findChannelBySPUPara
     * @Create In 2016-3-1 By wangxuan
     */
    @Override
    public List<PcmChannelDto> findChannelBySPUPara(Map<String, Object> para) {
        logger.info("start findChannelBySPUPara(),param:" + para.toString());
        List<PcmChannelDto> list = channelMapper.findChannelBySPUPara(para);
        logger.info("end findChannelBySPUPara(),return:" + list.toString());
        return list;
    }

    /**
     * 根据SPU（编码）查询渠道(拍照)
     *
     * @param para
     * @return List<PcmChannelPhotoDto>
     * @Methods Name findChannelBySPUParaForPhoto
     * @Create In 2016-3-10 By wangxuan
     */
    @Override
    public List<PcmChannelPhotoDto> findChannelBySPUParaForPhoto(Map<String, Object> para) {
        logger.info("start findChannelBySPUParaForPhoto(),param:" + para.toString());
        List<PcmChannelDto> list = channelMapper.findChannelBySPUPara(para);
        List<PcmChannelPhotoDto> resultList = new ArrayList<PcmChannelPhotoDto>();
        for (PcmChannelDto tempDto : list) {
            PcmChannelPhotoDto photoDto = new PcmChannelPhotoDto();
            photoDto.setSid(tempDto.getSid() + "");
            photoDto.setCode(tempDto.getChannelCode());
            photoDto.setName(tempDto.getChannelName());
            resultList.add(photoDto);
        }
        logger.info("end findChannelBySPUParaForPhoto(),return:" + resultList.toString());
        return resultList;
    }
}

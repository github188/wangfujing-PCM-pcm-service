package com.wangfj.product.maindata.persistence;

import java.util.List;

import com.wangfj.product.maindata.domain.entity.pcmPropackimgUrl;

public interface pcmPropackimgUrlMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(pcmPropackimgUrl record);

    int insertSelective(pcmPropackimgUrl record);

    pcmPropackimgUrl selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(pcmPropackimgUrl record);

    int updateByPrimaryKey(pcmPropackimgUrl record);
    
    List<pcmPropackimgUrl> selectListBySpuAndColor(pcmPropackimgUrl record);
}
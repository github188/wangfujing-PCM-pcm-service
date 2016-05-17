package com.wangfj.product.stocks.service.intf;


import com.wangfj.product.stocks.domain.entity.PcmLockAttribute;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface IPcmLockAttributeService {

    /**
     * 自动执行判断分布式锁开关
     *
     * @Methods Name getButton
     * @Create In 2015年8月14日 By yedong void
     */
    public void resetAttribute();

    /**
     * 添加锁定类型
     *
     * @param record
     * @return int
     * @Methods Name insertSelective
     * @Create In 2015年7月31日 By yedong
     */
    int insertSelective(PcmLockAttribute record);

    /**
     * 修改
     *
     * @param record
     * @return int
     * @Methods Name updateSelective
     * @Create In 2015年7月31日 By yedong
     */
    int updateSelective(PcmLockAttribute record);

    /**
     * 修改开关
     *
     * @param entity
     * @return
     */
    boolean modifyButton(PcmLockAttribute entity);

    /**
     * 查询
     *
     * @param paramMap
     * @return List<PcmLockAttribute>
     * @Methods Name selectListByParam
     * @Create In 2015年7月31日 By yedong
     */
    List<PcmLockAttribute> selectListByParam(Map<String, Object> paramMap);
}

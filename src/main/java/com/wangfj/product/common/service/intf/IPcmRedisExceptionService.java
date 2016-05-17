package com.wangfj.product.common.service.intf;

import com.wangfj.product.common.domain.entity.PcmInstance;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wangxuan on 2016-03-08 0008.
 */
public interface IPcmRedisExceptionService {

    /**
     * Redis服务器挂了异常处理
     *
     * @return
     */
    List<PcmInstance> redisExceptionHandler();
}

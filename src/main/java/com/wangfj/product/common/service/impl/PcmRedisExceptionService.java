package com.wangfj.product.common.service.impl;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.utils.CacheUtils;
import com.wangfj.core.utils.PropertyUtil;
import com.wangfj.core.utils.RedisUtil;
import com.wangfj.core.utils.StringUtils;
import com.wangfj.product.common.domain.entity.PcmInstance;
import com.wangfj.product.common.domain.entity.PcmRedis;
import com.wangfj.product.common.domain.vo.PcmRedisQueryDto;
import com.wangfj.product.common.domain.vo.PcmRedisUDto;
import com.wangfj.product.common.persistence.PcmInstanceMapper;
import com.wangfj.product.common.service.intf.IPcmRedisExceptionService;
import com.wangfj.product.common.service.intf.IPcmRedisService;
import com.wangfj.product.constants.DomainName;
import com.wangfj.product.stocks.domain.entity.PcmLockAttribute;
import com.wangfj.product.stocks.service.intf.IPcmLockAttributeService;
import com.wangfj.util.Constants;
import com.wangfj.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxuan on 2016-03-08 0008.
 */
@Service
public class PcmRedisExceptionService implements IPcmRedisExceptionService {

    private static final Logger logger = LoggerFactory.getLogger(PcmRedisExceptionService.class);

    @Autowired
    private IPcmLockAttributeService lockAttributeService;

    @Autowired
    private IPcmRedisService redisService;

    @Autowired
    private PcmInstanceMapper instanceMapper;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * Redis服务器挂了异常处理
     *
     * @return
     */
    @Override
    @Transactional
    public List<PcmInstance> redisExceptionHandler() {
        logger.info("start redisExceptionHandler(),param:");
        boolean redisFlag = redisUtil.setIsOK("Hello", "World");
//        boolean redisFlag = redisUtil.set("Hello", "World");
//        redisFlag = false;
        List<PcmInstance> instanceList = null;
        if (redisFlag) {
            // Redis服务器正常
            boolean cacheFlag = CacheUtils.cacheFlag;
            if (!cacheFlag) {
                //修改缓存表的redis状态为0
                PcmLockAttribute entity = new PcmLockAttribute();
                entity.setDistributedLock(Constants.PCM_LOCK_REDIS);
                entity.setButton(Constants.PUBLIC_0);
                boolean buttonResult = lockAttributeService.modifyButton(entity);

                //执行PCM-SYN缓存切换接口
                final String synCacheFlagUrl = PropertyUtil.getSystemUrl("pcm-syn") + PropertyUtil.getSystemUrl("resetCacheFlag");
                if (StringUtils.isNotEmpty(synCacheFlagUrl)) {
                    taskExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            HttpUtil.doPost(synCacheFlagUrl, "");
                        }
                    });
                }

                //根据异常信息进行数据初始化
                PcmRedisQueryDto redisQueryDto = new PcmRedisQueryDto();
                Integer currentPage = Constants.REDIS_CURRENTPAGE;
                Integer pageSize = Constants.REDIS_PAGESIZE;
                redisQueryDto.setCurrentPage(currentPage);
                redisQueryDto.setPageSize(pageSize);
                Page<PcmRedis> redisPage = redisService.findPageRedisUndone(redisQueryDto);
                List<PcmRedis> list = redisPage.getList();
                while (list != null && list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        PcmRedis redis = list.get(i);
                        if (!DomainName.getPrice.equals(redis.getRedisffield())) {
                            boolean setResult = redisUtil.setIsOK(redis.getKeyname(), redis.getValue());
//                            boolean setResult = redisUtil.set(redis.getKeyname(), redis.getValue());
                            if (setResult) {
                                PcmRedisUDto redisDto = new PcmRedisUDto();
                                redisDto.setSid(redis.getSid());
                                redisDto.setStatus(Constants.PUBLIC_1);
                                redisService.updatePcmRedisStatus(redisDto);
                            }
                        } else {
                            boolean setResult = redisUtil.del(redis.getKeyname());
                            if (setResult) {
                                PcmRedisUDto redisDto = new PcmRedisUDto();
                                redisDto.setSid(redis.getSid());
                                redisDto.setStatus(Constants.PUBLIC_1);
                                redisService.updatePcmRedisStatus(redisDto);
                            }
                        }
                    }
                    redisPage = redisService.findPageRedisUndone(redisQueryDto);
                    list = redisPage.getList();
                }
                Map<String, Object> paramMap = new HashMap<String, Object>();
                instanceList = instanceMapper.selectListByParam(paramMap);
            }
        } else {
            // Redis服务器挂了
            boolean cacheFlag = CacheUtils.cacheFlag;
            if (cacheFlag) {
                PcmLockAttribute entity = new PcmLockAttribute();
                entity.setDistributedLock(Constants.PCM_LOCK_REDIS);
                entity.setButton(Constants.PUBLIC_1);
                boolean buttonResult = lockAttributeService.modifyButton(entity);
                Map<String, Object> paramMap = new HashMap<String, Object>();
                instanceList = instanceMapper.selectListByParam(paramMap);
            }
        }


        logger.info("end redisExceptionHandler(),return:" + instanceList.toString());
        return instanceList;
    }

}

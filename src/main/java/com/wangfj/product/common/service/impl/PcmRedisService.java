package com.wangfj.product.common.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.core.utils.PropertyUtil;
import com.wangfj.product.common.domain.entity.PcmRedis;
import com.wangfj.product.common.domain.vo.PcmRedisQueryDto;
import com.wangfj.product.common.domain.vo.PcmRedisUDto;
import com.wangfj.product.common.persistence.PcmRedisMapper;
import com.wangfj.product.common.service.intf.IPcmRedisService;
import com.wangfj.util.Constants;
import com.wangfj.util.HttpUtil;

@Service
public class PcmRedisService implements IPcmRedisService {

	private static final Logger logger = LoggerFactory.getLogger(PcmRedisService.class);

	@Autowired
	private PcmRedisMapper redisMapper;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	/**
	 * 添加
	 *
	 * @param entity
	 * @return
	 */
	@Override
	@Transactional
	public void savePcmRedis(PcmRedis entity) {
		logger.info("start savePcmRedis(),param:" + entity.toString());
		final PcmRedis dto = entity;
		taskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					String redislLogUrl = PropertyUtil.getSystemUrl("pcm-redisLog");
					String response = HttpUtil.doPost(redislLogUrl, JsonUtil.getJSONString(dto));
					logger.info("respons savePcmRedis:" + response);
				} catch (Exception e) {
					logger.error("respons savePcmRedis:" + e.getMessage());
				}
			}
		});
	}

	@Override
	public int insertPcmRedis(PcmRedis entity) {
		logger.info("start savePcmRedis(),param:" + entity.toString());
		entity.setCreatetime(new Date());
		int result = redisMapper.insertSelective(entity);
		logger.info("end savePcmRedis(),reutrn:" + result);
		return result;
	}

	/**
	 * 修改处理状态
	 *
	 * @param dto
	 * @return
	 */
	@Override
	@Transactional
	public int updatePcmRedisStatus(PcmRedisUDto dto) {
		logger.info("start updatePcmRedisStatus(),param:" + dto.toString());
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sid", dto.getSid());
		List<PcmRedis> redisList = redisMapper.selectListByParam(paramMap);
		int result = Constants.PUBLIC_0;
		if (redisList != null && redisList.size() == 1) {
			PcmRedis redis = new PcmRedis();
			redis.setSid(dto.getSid());
			redis.setStatus(dto.getStatus());
			result = redisMapper.updateByPrimaryKeySelective(redis);
		}
		logger.info("end updatePcmRedisStatus(),reutrn:" + result);
		return result;
	}

	/**
	 * 分页查询
	 *
	 * @param dto
	 * @return
	 */
	@Override
	public Page<PcmRedis> findPageRedis(PcmRedisQueryDto dto) {
		logger.info("start findPageRedis(),param:" + dto.toString());
		Page<PcmRedis> page = new Page<PcmRedis>();
		Integer currentPage = dto.getCurrentPage();
		if (currentPage != null) {
			page.setCurrentPage(currentPage);
		}
		Integer pageSize = dto.getPageSize();
		if (pageSize != null) {
			page.setPageSize(pageSize);
		}
		Integer count = redisMapper.findPageCountRedis(dto);

		page.setCount(count);

		dto.setStart(page.getStart());
		dto.setLimit(page.getLimit());

		List<PcmRedis> list = redisMapper.findPageRedis(dto);
		page.setList(list);

		logger.info("end findPageRedis(),return:" + list.toString());
		return page;
	}

	/**
	 * 分页查询未处理的
	 *
	 * @param dto
	 * @return
	 */
	@Override
	public Page<PcmRedis> findPageRedisUndone(PcmRedisQueryDto dto) {
		logger.info("start findPageRedisUndone(),param:" + dto.toString());
		dto.setStatus(Constants.PUBLIC_0);
		Page<PcmRedis> page = findPageRedis(dto);
		logger.info("end findPageRedisUndone(),return:" + page.getList().toString());
		return page;
	}

}

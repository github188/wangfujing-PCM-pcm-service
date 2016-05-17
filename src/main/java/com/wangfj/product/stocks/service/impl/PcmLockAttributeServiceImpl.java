package com.wangfj.product.stocks.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangfj.core.utils.CacheUtils;
import com.wangfj.core.utils.PropertyConfigurer;
import com.wangfj.product.constants.FlagType;
import com.wangfj.product.stocks.domain.entity.PcmLockAttribute;
import com.wangfj.product.stocks.persistence.PcmLockAttributeMapper;
import com.wangfj.product.stocks.service.intf.IPcmLockAttributeService;
import com.wangfj.util.Constants;
import com.wfj.exception.client.util.PropertiesLoad;

@Service
public class PcmLockAttributeServiceImpl implements IPcmLockAttributeService {

	private static final Logger logger = LoggerFactory.getLogger(PcmLockAttributeServiceImpl.class);

	@Autowired
	private PcmLockAttributeMapper pcmLockAttributeMapper;

	/**
	 * 自动执行判断分布式锁开关
	 *
	 * @Methods Name getButton
	 * @Create In 2015年8月14日 By yedong void
	 */
	@Override
	@PostConstruct
	public void resetAttribute() {
		PcmLockAttribute entity = new PcmLockAttribute();
		List<PcmLockAttribute> list = pcmLockAttributeMapper.selectListByParam(entity);
		if (list.size() != 0) {
			for (PcmLockAttribute dto : list) {
				if (dto.getDistributedLock().equals(Constants.PCM_LOCK_ZOOKEEPER)) {
					FlagType.setZookeeper_lock(dto.getButton());
				}
				if (dto.getDistributedLock().equals(Constants.PCM_LOCK_REDIS)) {
					FlagType.setRedis_falg(dto.getButton());
					if (dto.getButton() == Constants.FLAG_YES) {
						CacheUtils.cacheFlag = true;
					} else {
						CacheUtils.cacheFlag = false;
					}
				}
				if (dto.getDistributedLock().equals(Constants.PCM_PUBLISH)) {
					FlagType.setPublish_info(dto.getButton());
				}
			}
		}
		// 初始化异常配置
		PropertiesLoad.putProperties("exception.brokerList",
				PropertyConfigurer.getContextProperty("exception.brokerList"));
		PropertiesLoad.putProperties("exception.topic",
				PropertyConfigurer.getContextProperty("exception.topic"));
		PropertiesLoad.putProperties("exception.sysCode",
				PropertyConfigurer.getContextProperty("system.excption.code"));
	}

	/**
	 * 添加锁定类型
	 *
	 * @param record
	 * @return int
	 * @Methods Name insertSelective
	 * @Create In 2015年7月31日 By yedong
	 */
	@Override
	@Transactional
	public int insertSelective(PcmLockAttribute record) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("changeName", record.getDistributedLock());
		int status = 0;
		List<PcmLockAttribute> list = pcmLockAttributeMapper.selectListByParam(record);
		if (list.size() > 0 && list.get(0).getDistributedLock() != null) {
			status = pcmLockAttributeMapper.insertSelective(record);
		}
		return status;
	}

	/**
	 * 修改
	 *
	 * @param record
	 * @return int
	 * @Methods Name updateSelective
	 * @Create In 2015年7月31日 By yedong
	 */
	@Override
	@Transactional
	public int updateSelective(PcmLockAttribute record) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sid", record.getSid());
		paramMap.put("distributedLock", record.getDistributedLock());
		List<PcmLockAttribute> list = pcmLockAttributeMapper.selectListByParam(paramMap);
		int status = 0;
		if (list.size() == 0) {
			status = pcmLockAttributeMapper.updateByPrimaryKeySelective(record);
		}
		return status;
	}

	/**
	 * 修改开关
	 *
	 * @param entity
	 * @return
	 */
	@Override
	@Transactional
	public boolean modifyButton(PcmLockAttribute entity) {
		// logger.info("start modifyButton(),param:" + entity.toString());
		boolean flag = false;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("distributedLock", entity.getDistributedLock());
		List<PcmLockAttribute> list = pcmLockAttributeMapper.findListByParam(paramMap);
		if (list != null && list.size() == 1) {
			entity.setSid(list.get(0).getSid());
			int result = pcmLockAttributeMapper.updateByPrimaryKeySelective(entity);
			if (result == 1) {
				flag = true;
			}
		}
		// logger.info("end modifyButton(),return:" + flag);
		return flag;
	}

	/**
	 * 查询
	 *
	 * @param paramMap
	 * @return List<PcmLockAttribute>
	 * @Methods Name selectListByParam
	 * @Create In 2015年7月31日 By yedong
	 */
	@Override
	public List<PcmLockAttribute> selectListByParam(Map<String, Object> paramMap) {
		List<PcmLockAttribute> list = pcmLockAttributeMapper.selectListByParam(paramMap);
		return list;
	}
}

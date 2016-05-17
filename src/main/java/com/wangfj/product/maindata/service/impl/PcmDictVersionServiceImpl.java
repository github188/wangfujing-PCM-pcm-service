package com.wangfj.product.maindata.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.product.maindata.domain.entity.PcmDictVersion;
import com.wangfj.product.maindata.domain.vo.PcmDictVersionDto;
import com.wangfj.product.maindata.persistence.PcmDictVersionMapper;
import com.wangfj.product.maindata.service.intf.IPcmDictVersionService;

@Service
class PcmDictVersionServiceImpl implements IPcmDictVersionService {
	@Autowired
	private PcmDictVersionMapper pcmDictVersionMapper;
	
	public Integer selectVersionByType(PcmDictVersionDto record){
		List<Map<String, Object>> verList = pcmDictVersionMapper.selectVersionByType(record);
		Integer varsionMax = 0;
		if(verList.get(0) != null && verList.size() > 0){
			varsionMax = ((Long)verList.get(0).get("versionMax")).intValue();			
		}
		return varsionMax;
	}
	
	public boolean saveOrUpdateVersion(PcmDictVersionDto record) throws Exception{
		PcmDictVersion entity = new PcmDictVersion();
		entity.setType(record.getType());
		List<PcmDictVersion> verList = pcmDictVersionMapper.selectListByParam(entity);
		if(verList.size() > 0 && verList != null){
			/*存在  修改*/
			entity.setSid(verList.get(0).getSid());
			entity.setVersion(verList.get(0).getVersion() + 1);
			int count = pcmDictVersionMapper.updateByPrimaryKeySelective(entity);
			if(count == 0){
				throw new Exception("系统错误");
			}
		}else{
			/*不存在 添加*/
			entity.setVersion(1L);
			int count = pcmDictVersionMapper.insertSelective(entity);
			if(count == 0){
				throw new Exception("系统错误");
			}
		}
		return true;
	}
}

package com.wangfj.product.brand.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.product.brand.domain.entity.PcmBrandCategory;
import com.wangfj.product.brand.domain.vo.PcmBrandCateDto;
import com.wangfj.product.brand.persistence.PcmBrandCategoryMapper;
import com.wangfj.product.brand.service.intf.IPcmBrandCategoryService;
import com.wangfj.product.maindata.service.intf.IPcmProductPictureService;

@Service
public class PcmBrandCategoryServiceImpl implements IPcmBrandCategoryService {

	@Autowired
	PcmBrandCategoryMapper brandCateMapper;

	@Override
	public List<Map<String, Object>> getBrandCateInfo(PcmBrandCateDto dto) {
		List<Map<String, Object>> brandCateInfoList = brandCateMapper.getBrandCateInfo(dto);
		return brandCateInfoList;
	}

	/**
	 * 添加or修改分类品牌关联关系
	 * 
	 * @Methods Name addBrandCateInfo
	 * @Create In 2015-11-25 By yedong
	 * @param PcmBrandCateDto
	 * @return void
	 */
	@Override
	@Transactional
	public void addBrandCateInfo(List<PcmBrandCateDto> dtoList) {
		for (PcmBrandCateDto dto : dtoList) {
			Map<String, Object> map = brandCateMapper.getCateAndBrandSid(dto);
			if (map == null) {
				throw new BleException(ErrorCode.BRAND_CATE_NO_EXIST.getErrorCode(),
						ErrorCode.BRAND_CATE_NO_EXIST.getMemo());
			}
			PcmBrandCategory entity = new PcmBrandCategory();
			entity.setBrandSid((Long) map.get("brandSid"));
			entity.setCategorySid((Long) map.get("cateSid"));
			List<PcmBrandCategory> countList = brandCateMapper.selectListByParam(entity);
			entity.setSizePictureUrl(dto.getSizePictureUrl());
			entity.setStatus(dto.getStatus());
			if (dto.getOptUser() != null) {
				entity.setOptUser(dto.getOptUser());
			}
			if (dto.getOptDate() != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 小写的mm表示的是分钟
				Date date = null;
				try {
					date = sdf.parse(dto.getOptDate());
				} catch (ParseException e) {

				}
				entity.setOptDate(date);
			}
			if (countList != null && countList.size() > 0) {
				entity.setSid(countList.get(0).getSid());
				int updateSelective = brandCateMapper.updateByPrimaryKeySelective(entity);
				if (updateSelective == 0) {
					throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
							ErrorCode.DATA_OPER_ERROR.getMemo());
				}
			} else {
				int insertSelective = brandCateMapper.insertSelective(entity);
				if (insertSelective == 0) {
					throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
							ErrorCode.DATA_OPER_ERROR.getMemo());
				}
			}
		}
	}

}

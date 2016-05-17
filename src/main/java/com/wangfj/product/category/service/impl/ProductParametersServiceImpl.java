package com.wangfj.product.category.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangfj.core.cache.RedisVo;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.CacheUtils;
import com.wangfj.product.category.domain.entity.PcmCategory;
import com.wangfj.product.category.domain.entity.PcmProductCategory;
import com.wangfj.product.category.domain.entity.PcmProductParameters;
import com.wangfj.product.category.domain.vo.PcmProductParaDto;
import com.wangfj.product.category.persistence.PcmProductCategoryMapper;
import com.wangfj.product.category.persistence.PcmProductParametersMapper;
import com.wangfj.product.category.service.intf.ICategoryPropValuesService;
import com.wangfj.product.category.service.intf.IProductParametersService;
import com.wangfj.product.constants.DomainName;
import com.wangfj.product.maindata.domain.entity.PcmProduct;
import com.wangfj.product.maindata.domain.vo.ParametersDto;
import com.wangfj.product.maindata.domain.vo.SaveProductParametersDTO;
import com.wangfj.product.maindata.persistence.PcmProductMapper;
import com.wangfj.product.maindata.service.intf.IPcmProductPictureService;

/**
 * 商品属性关联表Service
 * 
 * @Class Name ProductParametersServiceImpl
 * @Author wangsy
 * @Create In 2015年8月26日
 */
@Service
public class ProductParametersServiceImpl implements IProductParametersService {

	@Autowired
	private PcmProductParametersMapper ssdProductParametersMapper;

	@Autowired
	PcmProductParametersMapper ppMapper;

	@Autowired
	private PcmProductCategoryMapper proCateMapper;

	@Autowired
	private ICategoryPropValuesService cateService;

	@Autowired
	private PcmProductMapper spuMapper;

	@Autowired
	private IPcmProductPictureService picService;

	public int delete(Long sid) {
		return this.ssdProductParametersMapper.deleteByPrimaryKey(sid);
	}

	public int save(PcmProductParameters record) {

		return this.ssdProductParametersMapper.insertSelective(record);
	}

	public PcmProductParameters get(Long sid) {
		return this.ssdProductParametersMapper.selectByPrimaryKey(sid);
	}

	public int update(PcmProductParameters record) {
		return this.ssdProductParametersMapper.updateByPrimaryKeySelective(record);
	}

	public List<PcmProductParameters> selectList(PcmProductParameters record) {
		return this.ssdProductParametersMapper.selectList(record);
	}

	public List<PcmProductParaDto> selectListSelect(PcmProductParameters record) {
		return this.ssdProductParametersMapper.selectListSelect(record);
	}

	@Transactional
	public int saveorupdate(PcmProductParameters record) {
		PcmProductParameters spp = new PcmProductParameters();
		spp.setCategorySid(record.getCategorySid());
		spp.setChannelSid(record.getChannelSid());
		spp.setProductSid(record.getProductSid());
		spp.setPropSid(record.getPropSid());
		spp.setValueSid(record.getValueSid());
		List<PcmProductParameters> list = this.ssdProductParametersMapper.selectList(spp);
		spp.setCategoryName(record.getCategoryName());
		spp.setPropName(record.getPropName());
		spp.setValueName(record.getValueName());
		if (list == null || list.size() == 0) {
			return this.ssdProductParametersMapper.insertSelective(spp);
		} else {
			Long sid = list.get(0).getSid();
			spp.setSid(sid);
			return this.ssdProductParametersMapper.updateByPrimaryKeySelective(spp);
		}
	}

	public int deleteByCPSid(String productSid, String channelSid, String categorySid) {
		return this.ssdProductParametersMapper.deleteByProductSid(Long.valueOf(productSid),
				Long.valueOf(channelSid), Long.valueOf(categorySid));
	}

	@Override
	@Transactional
	public int insertBatch(List<PcmProductParameters> list) {

		return this.ssdProductParametersMapper.insertBatch(list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wangfj.product.category.service.intf.IProductParametersService#
	 * insertProductParamter
	 * (com.wangfj.product.category.domain.entity.PcmProductParameters)
	 */
	@Override
	public int insertProductParamter(SaveProductParametersDTO sppd) {
		// 添加之前删除表中数据
		PcmProductParameters ppp_1 = new PcmProductParameters();
		// ppp_1.setCategorySid(Long.valueOf(sppd.getCategorySid()));
		// ppp_1.setChannelSid(Long.valueOf(sppd.getChannelSid()));
		ppp_1.setProductSid(Long.valueOf(sppd.getSpuSid()));
		if (sppd.getCategoryType() != null) {
			ppp_1.setCategoryType(Integer.valueOf(sppd.getCategoryType()));
		} else {
			ppp_1.setCategoryType(3);
		}
		ppMapper.deleteByProductSid_1(ppp_1);
		int i = 0;
		if (sppd.getParameters() != null && sppd.getParameters().size() != 0) {
			for (ParametersDto dto : sppd.getParameters()) {
				PcmProductParameters ppp = new PcmProductParameters();
				ppp.setProductSid(Long.valueOf(sppd.getSpuSid()));
				ppp.setCategorySid(Long.valueOf(sppd.getCategorySid()));
				ppp.setCategoryName(sppd.getCategoryName());
				if (sppd.getCategoryType() != null) {
					ppp.setCategoryType(Integer.valueOf(sppd.getCategoryType()));
				}
				ppp.setChannelSid(Long.valueOf(sppd.getChannelSid()));
				ppp.setPropSid(dto.getPropSid());
				ppp.setPropName(dto.getPropName());
				ppp.setValueSid(dto.getValueSid());
				ppp.setValueName(dto.getValueName());
				i = ppMapper.insertSelective(ppp);
				if (i != 1) {
					throw new BleException("插入产品属性失败");
				}
			}
		} else {
			i = 1;
		}
		return i;
	}

	public int insertProductParamter1(SaveProductParametersDTO sppd) {
		int i = 0;
		if (sppd.getParameters() != null && sppd.getParameters().size() != 0) {
			for (ParametersDto dto : sppd.getParameters()) {
				PcmProductParameters ppp = new PcmProductParameters();
				ppp.setProductSid(Long.valueOf(sppd.getSpuSid()));
				ppp.setCategorySid(Long.valueOf(sppd.getCategorySid()));
				ppp.setCategoryName(sppd.getCategoryName());
				if (sppd.getCategoryType() != null) {
					ppp.setCategoryType(Integer.valueOf(sppd.getCategoryType()));
				}
				ppp.setChannelSid(Long.valueOf(sppd.getChannelSid()));
				ppp.setPropSid(dto.getPropSid());
				ppp.setPropName(dto.getPropName());
				ppp.setValueSid(dto.getValueSid());
				ppp.setValueName(dto.getValueName());
				i = ppMapper.insertSelective(ppp);
				if (i != 1) {
					throw new BleException("插入产品属性失败");
				}
			}
		} else {
			i = 1;
		}
		return i;
	}

	/**
	 * 删除单品页缓存
	 * 
	 * @Methods Name redisSpuCMSCategory
	 * @Create In 2015年12月23日 By yedong
	 * @param spuCode
	 *            void
	 */
	public void redisSpuCMSCategory(String spuCode) {
		RedisVo vo = new RedisVo();
		vo.setKey(spuCode);
		vo.setField(DomainName.getCMSCategory);
		vo.setType(CacheUtils.HDEL);
		CacheUtils.setRedisData(vo);

	}

	@Transactional
	public int productCatePropValue(List<SaveProductParametersDTO> sppdList) {
		PcmProduct spu = new PcmProduct();
		spu.setSid(Long.parseLong(sppdList.get(0).getSpuSid()));
		List<PcmProduct> spuList = spuMapper.selectListByParam(spu);
		redisSpuCMSCategory(spuList.get(0).getProductSid());
		picService.redisSpuCMSSHopperInfo(spuList.get(0).getProductSid());
		int i = 0;
		if (sppdList.get(0).getCategoryType().equals("3")) {
			// 删除产品分类的关系
			PcmProductCategory record = new PcmProductCategory();
			record.setProductSid((sppdList.get(0).getSpuSid()));
			proCateMapper.deleteZSCateByRecord(record);
		}
		if (sppdList.get(0).getCategoryType().equals("0")) {
			// 删除产品分类的关系
			PcmProductCategory record = new PcmProductCategory();
			record.setProductSid((sppdList.get(0).getSpuSid()));
			proCateMapper.deleteGYCateByRecord(record);
		}
		// 添加之前删除表中数据
		PcmProductParameters ppp_1 = new PcmProductParameters();
		// ppp_1.setCategorySid(Long.valueOf(sppd.getCategorySid()));
		// ppp_1.setChannelSid(Long.valueOf(sppdList.get(0).getChannelSid()));
		ppp_1.setProductSid(Long.valueOf(sppdList.get(0).getSpuSid()));
		if (sppdList.get(0).getCategoryType() != null) {
			ppp_1.setCategoryType(Integer.valueOf(sppdList.get(0).getCategoryType()));
		} else {
			ppp_1.setCategoryType(3);
		}
		ppMapper.deleteByProductSid_1(ppp_1);
		for (SaveProductParametersDTO sppd : sppdList) {
			PcmProductCategory record_2 = new PcmProductCategory();
			if (sppd.getCategorySid() != null && sppd.getCategorySid() != "") {
				record_2.setCategorySid(Long.parseLong(sppd.getCategorySid()));
			}
			if (sppd.getSpuSid() != null && sppd.getSpuSid() != "") {
				record_2.setProductSid((sppd.getSpuSid()));
			}
			if (sppd.getChannelSid() != null && sppd.getChannelSid() != "") {
				record_2.setChannelSid(Long.parseLong(sppd.getChannelSid()));
			}
			if (sppdList.get(0).getCategoryType().equals("3")) {
				proCateMapper.insertSelective(record_2);
			}
			if (sppd.getParameters() != null && sppd.getParameters().size() != 0) {

				PcmProductCategory record1 = new PcmProductCategory();
				record1.setProductSid((sppd.getSpuSid()));
				record1.setCategorySid(Long.valueOf(sppd.getCategorySid()));
				record1.setChannelSid(Long.valueOf(sppd.getChannelSid()));
				int ppci = proCateMapper.insertSelective(record1);
				if (ppci != 1) {
					throw new BleException("插入产品属性失败");
				}
				for (int j = 0; j < sppd.getParameters().size(); j++) {
					PcmProductParameters ppp = new PcmProductParameters();
					ppp.setProductSid(Long.valueOf(sppd.getSpuSid()));
					ppp.setCategorySid(Long.valueOf(sppd.getCategorySid()));
					ppp.setCategoryName(sppd.getCategoryName());
					if (sppd.getCategoryType() != null) {
						ppp.setCategoryType(Integer.valueOf(sppd.getCategoryType()));
					}
					ppp.setChannelSid(Long.valueOf(sppd.getChannelSid()));
					ParametersDto dto = new ParametersDto();
					try {
						BeanUtils.copyProperties(dto, sppd.getParameters().get(j));
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
					ppp.setPropSid(dto.getPropSid());
					ppp.setPropName(dto.getPropName());
					ppp.setValueSid(dto.getValueSid());
					ppp.setValueName(dto.getValueName());
					i = ppMapper.insertSelective(ppp);
					if (i != 1) {
						throw new BleException("插入产品属性失败");
					}
					picService.redisSpuCMSSHopperInfo(sppd.getSpuSid());
					redisSpuCMSCategory(sppd.getSpuSid());
				}
				/*
				 * for (ParametersDto dto : sppd.getParameters()) {
				 * PcmProductParameters ppp = new PcmProductParameters();
				 * ppp.setProductSid(Long.valueOf(sppd.getSpuSid()));
				 * ppp.setCategorySid(Long.valueOf(sppd.getCategorySid()));
				 * ppp.setCategoryName(sppd.getCategoryName()); if
				 * (sppd.getCategoryType() != null) {
				 * ppp.setCategoryType(Integer.valueOf(sppd.getCategoryType()));
				 * } ppp.setChannelSid(Long.valueOf(sppd.getChannelSid()));
				 * ppp.setPropSid(dto.getPropSid());
				 * ppp.setPropName(dto.getPropName());
				 * ppp.setValueSid(dto.getValueSid());
				 * ppp.setValueName(dto.getValueName()); i =
				 * ppMapper.insertSelective(ppp); if (i != 1) { throw new
				 * BleException("插入产品属性失败"); } }
				 */
			} else {
				i = 1;
			}
		}

		return i;
	}

	/**
	 * 根据SPUSID查询属性及属性值
	 * 
	 * @Methods Name cateListSelect
	 * @Create In 2015年10月24日 By yedong
	 * @param paramMap
	 * @return List<SaveProductParametersDTO>
	 */
	public List<SaveProductParametersDTO> cateListSelect(Map<String, Object> paramMap) {
		List<SaveProductParametersDTO> cateList = ssdProductParametersMapper
				.cateListSelect(paramMap);
		for (SaveProductParametersDTO dto : cateList) {
			cateService.clearSubList();
			List<PcmCategory> list = cateService.selectAllSupNodeByCateSid(dto.getCategorySid());
			StringBuffer url = new StringBuffer();
			for (int i = list.size() - 1; i >= 0; i--) {
				if (i == 0) {
					url.append(list.get(i).getName());
				} else {
					url.append(list.get(i).getName() + " > ");
				}
			}
			dto.setBreadUrl(url.toString());
		}
		return cateList;
	}

	/**
	 * 查询工业分类属性、属性值BySpuSid
	 * 
	 * @Methods Name getGYCatePropValueBySpuSid
	 * @Create In 2015年11月27日 By yedong
	 * @param paramMap
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> getGYCatePropValueBySpuSid(Map<String, Object> paramMap) {
		List<PcmProductParameters> list = ssdProductParametersMapper.selectListByParam(paramMap);
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (PcmProductParameters ppp : list) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("propName", ppp.getPropName());
			param.put("valueName", ppp.getValueName());
			mapList.add(param);
		}
		return mapList;
	}
}

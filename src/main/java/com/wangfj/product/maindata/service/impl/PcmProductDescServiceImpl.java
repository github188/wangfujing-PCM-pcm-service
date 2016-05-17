package com.wangfj.product.maindata.service.impl;

import java.io.UnsupportedEncodingException;
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
import com.wangfj.product.maindata.domain.entity.PcmProduct;
import com.wangfj.product.maindata.domain.entity.PcmProductDesc;
import com.wangfj.product.maindata.domain.vo.PcmProductDescDto;
import com.wangfj.product.maindata.domain.vo.PcmProductDescQueryDto;
import com.wangfj.product.maindata.domain.vo.PcmSpuColorDescDto;
import com.wangfj.product.maindata.persistence.PcmProductDescMapper;
import com.wangfj.product.maindata.persistence.PcmProductMapper;
import com.wangfj.product.maindata.service.intf.IPcmProductDescService;
import com.wangfj.util.Constants;

/**
 * 精包装
 */
@Service
public class PcmProductDescServiceImpl implements IPcmProductDescService {

	private static final Logger logger = LoggerFactory.getLogger(PcmProductDescServiceImpl.class);

	@Autowired
	private PcmProductDescMapper ppdMapper;
	@Autowired
	private PcmProductMapper spuMapper;

	/**
	 * 根据spu编码和色系查询精包装
	 * 
	 * @Methods Name getContentByKuanAndColor
	 * @Create In 2015年12月4日 By yedong
	 * @param entity
	 * @return
	 * @throws Exception
	 *             String
	 */
	public PcmSpuColorDescDto getContentByKuanAndColor(PcmProductDesc entity) throws Exception {
		PcmSpuColorDescDto desc = new PcmSpuColorDescDto();
		List<PcmProductDesc> ppdList = ppdMapper.selectListByParam(entity);
		String packDesc = "";
		if (ppdList != null && ppdList.size() > 0) {
			PcmProductDesc pcmProductDesc = ppdList.get(0);
			byte[] content = pcmProductDesc.getContent();
			String contents = new String(content, "UTF-8");
			packDesc = contents;
		}
		desc.setPackDesc(packDesc);
		PcmProduct spu = new PcmProduct();
		spu.setProductSid(entity.getProductSid());
		List<PcmProduct> spuList = spuMapper.selectListByParam(spu);
		if (spuList != null && spuList.size() > 0) {
			desc.setLongDesc(spuList.get(0).getLongDesc());
			desc.setShortDesc(spuList.get(0).getShortDes());
		}
		return desc;
	}

	/**
	 * 查询List
	 * 
	 * @param dto
	 * @return
	 */
	@Override
	public List<PcmProductDescDto> findListByParam(PcmProductDescQueryDto dto) {
		logger.info("start findListByParam(),param:" + dto.toString());
		List<PcmProductDescDto> list = ppdMapper.findListByParam(dto);
		if (list.size() > 0) {
			for (PcmProductDescDto tempDto : list) {
				String contents = "";
				try {
					contents = new String(tempDto.getContent(), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				tempDto.setContents(contents);
				tempDto.setContent(null);
			}
		}
		logger.info("end findListByParam(),return:" + list.toString());
		return list;
	}

	/**
	 * 分页查询
	 * 
	 * @param dto
	 * @return
	 */
	@Override
	public Page<PcmProductDescDto> findPageByParam(PcmProductDescQueryDto dto) {
		logger.info("start findPageByParam(),param:" + dto.toString());
		Page<PcmProductDescDto> page = new Page<PcmProductDescDto>();
		Integer currentPage = dto.getCurrentPage();
		Integer pageSize = dto.getPageSize();
		if (currentPage != null) {
			page.setCurrentPage(currentPage);
		}
		if (pageSize != null) {
			page.setPageSize(pageSize);
		}
		Integer count = ppdMapper.findCountByParam(dto);
		page.setCount(count);

		dto.setStart(page.getStart());
		dto.setLimit(page.getLimit());
		List<PcmProductDescDto> list = ppdMapper.findListByParam(dto);
		if (list.size() > 0) {
			for (PcmProductDescDto tempDto : list) {
				String contents = "";
				try {
					contents = new String(tempDto.getContent(), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				tempDto.setContents(contents);
				tempDto.setContent(null);
			}
		}
		page.setList(list);
		logger.info("end findPageByParam(),return:" + list.toString());
		return page;
	}

	/**
	 * 添加
	 * 
	 * @param entity
	 * @return
	 */
	@Override
	@Transactional
	public Integer addProductDesc(PcmProductDesc entity) {
		logger.info("start addProductDesc(),param:" + entity.toString());
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("productSid", entity.getProductSid());
		paramMap.put("color", entity.getColor());
		List<PcmProductDesc> list = ppdMapper.selectListByParam(paramMap);
		int result;
		if (list.size() == 0) {
			result = ppdMapper.insertSelective(entity);
		} else {
			throw new BleException(
					ComErrorCodeConstants.ErrorCode.PRO_DESC_EXISTENCE.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.PRO_DESC_EXISTENCE.getMemo());
		}
		logger.info("end addProductDesc(),return:" + result);
		return result;
	}

	/**
	 * 修改商品描述
	 * 
	 * @param entity
	 * @return
	 */
	@Override
	@Transactional
	public Integer modifyProductDesc(PcmProductDesc entity) {
		logger.info("start addProductDesc(),param:" + entity.toString());
		int result = ppdMapper.updateContentByPrimaryKey(entity);
		logger.info("end addProductDesc(),return:" + result);
		return result;
	}

	/**
	 * 添加或修改
	 *
	 * @param entity
	 * @return
	 */
	@Override
	@Transactional
	public Integer addOrModifyProductDesc(PcmProductDesc entity) {
		logger.info("start addOrModifyProductDesc(),param:" + entity.toString());
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("productSid", entity.getProductSid());
		paramMap.put("color", entity.getColor());
		List<PcmProductDesc> list = ppdMapper.selectListByParam(paramMap);
		int result = Constants.PUBLIC_0;
		if (list.size() == 0) {
			result = ppdMapper.insertSelective(entity);
		} else if (list.size() == 1) {
			entity.setSid(list.get(0).getSid());
			result = ppdMapper.updateContentByPrimaryKey(entity);
		}
		logger.info("end addOrModifyProductDesc(),return:" + result);
		return result;
	}

	@Override
	public List<Map<String, Object>> selectSpuByCateAndBrand(Map<String, Object> param) {

		return spuMapper.selectSpuByCateAndBrand(param);
	}

}

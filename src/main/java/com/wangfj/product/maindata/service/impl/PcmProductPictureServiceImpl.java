package com.wangfj.product.maindata.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.core.utils.NumberUtils;
import com.wangfj.core.utils.PropertyUtil;
import com.wangfj.core.utils.RedisUtil;
import com.wangfj.product.category.domain.entity.PcmCategory;
import com.wangfj.product.category.service.intf.ICategoryPropValuesService;
import com.wangfj.product.common.domain.entity.PcmRedis;
import com.wangfj.product.common.service.intf.IPcmRedisService;
import com.wangfj.product.constants.DomainName;
import com.wangfj.product.maindata.domain.entity.PcmPictureStan;
import com.wangfj.product.maindata.domain.entity.PcmPictureUrl;
import com.wangfj.product.maindata.domain.entity.PcmProDesc;
import com.wangfj.product.maindata.domain.entity.PcmProDetail;
import com.wangfj.product.maindata.domain.entity.PcmProductMemo;
import com.wangfj.product.maindata.domain.vo.OmsResProInfoDto;
import com.wangfj.product.maindata.domain.vo.PcmPictureDto;
import com.wangfj.product.maindata.domain.vo.PcmPictureInfoDto;
import com.wangfj.product.maindata.domain.vo.PcmPictureUrlDto;
import com.wangfj.product.maindata.domain.vo.PcmSapResUrlDto;
import com.wangfj.product.maindata.domain.vo.PhotoStatusDto;
import com.wangfj.product.maindata.domain.vo.ProCateChannelDto;
import com.wangfj.product.maindata.domain.vo.ProSkuSpuPublishDto;
import com.wangfj.product.maindata.domain.vo.ProductPageDto;
import com.wangfj.product.maindata.persistence.PcmPictureStanMapper;
import com.wangfj.product.maindata.persistence.PcmPictureUrlMapper;
import com.wangfj.product.maindata.persistence.PcmProDetailMapper;
import com.wangfj.product.maindata.persistence.PcmProductMapper;
import com.wangfj.product.maindata.persistence.PcmProductMemoMapper;
import com.wangfj.product.maindata.persistence.PcmProductPictureMapper;
import com.wangfj.product.maindata.persistence.PcmShoppeProductMapper;
import com.wangfj.product.maindata.service.intf.IPcmProductPictureService;
import com.wangfj.product.maindata.service.intf.IPcmShoppeProductService;
import com.wangfj.product.stocks.domain.vo.PcmSapWsDto;
import com.wangfj.product.stocks.domain.vo.PcmSpuSkuProInfoDto;
import com.wangfj.util.Constants;
import com.wangfj.util.mq.PublishDTO;

@Service
@Transactional
public class PcmProductPictureServiceImpl implements IPcmProductPictureService {
	private static final Logger logger = LoggerFactory
			.getLogger(PcmProductPictureServiceImpl.class);
	@Autowired
	private PcmProductMemoMapper pcmProductMemoMapper;

	@Autowired
	private PcmPictureUrlMapper pcmPictureUrlMapper;
	@Autowired
	private PcmProDetailMapper skuMapper;
	@Autowired
	private PcmShoppeProductMapper spMapper;
	@Autowired
	private PcmPictureStanMapper psMapper;
	@Autowired
	private PcmProductMapper spuMapper;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private IPcmRedisService redisService;
	@Autowired
	private PcmProductPictureMapper picMapper;
	@Autowired
	private IPcmShoppeProductService proService;
	@Autowired
	private ICategoryPropValuesService cateService;

	public List<Map<String, Object>> picOldToNew(Map<String, Object> paramMap) {
		List<Map<String, Object>> picOldToNew = spMapper.picOldToNew(paramMap);
		return picOldToNew;
	}

	/**
	 * SAP获取图片
	 * 
	 * @Methods Name getUrlBySpuCodeAndColor
	 * @Create In 2016年3月16日 By yedong
	 * @param spuSkuProPara
	 * @return List<PcmSapResUrlDto>
	 */
	public PcmSapWsDto getUrlBySpuCodeAndColor(PcmSpuSkuProInfoDto spuSkuProPara) {
		PcmSapWsDto reDto = new PcmSapWsDto();
		List<PcmSapResUrlDto> urlBySpuCodeAndColor = picMapper
				.getUrlBySpuCodeAndColor(spuSkuProPara);
		if (urlBySpuCodeAndColor == null || urlBySpuCodeAndColor.size() == 0) {
			reDto.setFalg(false);
		} else {
			reDto.setUrlBySpuCodeAndColor(urlBySpuCodeAndColor);
			reDto.setFalg(true);
		}
		return reDto;
	}

	public List<ProCateChannelDto> getCatePropBySpuCode(PhotoStatusDto para) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("spuCode", para.getProductCode());
		List<ProCateChannelDto> catePropBySpuCode = spuMapper.getCatePropBySpuCode(paramMap);
		for (ProCateChannelDto dto : catePropBySpuCode) {
			cateService.clearSubList();
			List<PcmCategory> list = cateService.selectAllSupNodeByCateSid(dto.getCateSid());
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
		return catePropBySpuCode;
	}

	/**
	 * 根据SKU编码查询文描信息//废
	 * 
	 * @Methods Name selectProductMemoBySKUCode
	 * @Create In 2015-12-18 By wangc
	 * @param skuCode
	 * @return PcmProductMemo
	 */
	@Override
	public HashMap<String, Object> selectProductMemoBySKUCode(String skuCode) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			List<PcmProDesc> list = pcmProductMemoMapper.selectBySKUCode(skuCode);
			if (list.get(0).getSid() != null) {
				result.put("sid", list.get(0).getSid());
			} else {
				result.put("sid", "");
			}
			result.put("skuCode", list.get(0).getSkuCode());
			byte[] content = list.get(0).getContent();
			if (content != null) {
				String contents = new String(content, "UTF-8");
				result.put("content", contents);
			} else {
				result.put("content", "");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 商品描述添加
	 * 
	 * @Methods Name insertSelective
	 * @Create In 2015年9月18日 By kongqf
	 * @param record
	 * @return boolean
	 */
	@Override
	public boolean insertSelective(PcmProductMemo record) {
		PcmProductMemo pcmProductMemo = new PcmProductMemo();
		pcmProductMemo = pcmProductMemoMapper.selectByPrimaryKey(record.getSkuSid());
		int count = 0;
		if (pcmProductMemo != null) {
			count = pcmProductMemoMapper.updateByPrimaryKeySelective(record);
		} else {
			count = pcmProductMemoMapper.insertSelective(record);
		}
		if (count == 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean updateByPrimaryKey(PcmProductMemo record) {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * 保存商品图片信息
	 * 
	 * @Methods Name SaveProductPictureInfo
	 * @Create In 2015年9月21日 By kongqf
	 * @param dto
	 * @return boolean
	 */
	@Override
	public List<Map<String, Object>> SaveProductPictureInfo(PcmPictureInfoDto dto) {
		redisSpuCMSSHopperInfo(dto.getProductCode());
		boolean flag = false;
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("productCode", dto.getProductCode());
		paramMap.put("colorCode", dto.getColorCode());
		String photoStatus = spMapper.getPhotoStatusBySpuColor(paramMap);
		/* 判断是否已经拍照 */
		if (photoStatus != null && photoStatus != "") {
			if (dto.getType().equals("1")) {
				if (photoStatus.equals("3")) {
					logger.info("已经拍照");
					throw new BleException(ErrorCode.PICTURE_NOT_ONLY.getErrorCode(),
							ErrorCode.PICTURE_NOT_ONLY.getMemo());
				} else {
					PcmProDetail record = new PcmProDetail();
					record.setProductSid(Long.parseLong(dto.getProductCode()));
					record.setProColorSid(Long.valueOf(dto.getColorCode()));
					record.setPhotoStatus(3);
					int i = skuMapper.updatePhotoStatusByProSidColorSid(record);
					if (i == 0) {
						throw new BleException(ErrorCode.PRODUCT_IS_NULL.getErrorCode(), "产品:"
								+ dto.getProductCode() + ",色系:" + dto.getColorCode() + "-----"
								+ ErrorCode.PRODUCT_IS_NULL.getMemo());
					}
				}
			}
		} else {
			logger.info("该产品不存在");
			throw new BleException(ErrorCode.NEW_PRODUCT_NO_EXIST.getErrorCode(),
					ErrorCode.NEW_PRODUCT_NO_EXIST.getMemo());
		}
		for (PcmPictureUrlDto urlDto : dto.getPictureList()) {
			String errorMsg = validPictureRequestData(urlDto, dto);
			if (StringUtils.isBlank(errorMsg)) {
				PcmPictureUrl pcmPictureUrl = new PcmPictureUrl();
				pcmPictureUrl = getPcmPictureUrlEntity(urlDto, dto);
				int count = pcmPictureUrlMapper.insertSelective(pcmPictureUrl);
				if (count == 1) {
					flag = true;
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("photoCode", pcmPictureUrl.getPictureSid());
					map.put("pcmSid", pcmPictureUrl.getSid());
					mapList.add(map);
				}
				if (urlDto.getIsMain().equals("0") && urlDto.getIsModel().equals("0")) {
					pcmPictureUrl.setSid(null);
					pcmPictureUrl.setIsPrimary(1);// 不是主图
					pcmPictureUrl.setIsModel(1);// 不是原图
					pcmPictureUrl.setIsThumbnail(0);
					pcmPictureUrl.setStanSid(Constants.PIC_STANSID);
					String pictureName = urlDto.getPhotoName();
					String[] pictureNameBySplit = pictureName.split("\\.");
					pictureName = pictureNameBySplit[0] + "_sl." + pictureNameBySplit[1];
					pcmPictureUrl.setPictureName(pictureName);
					String pictureUrl = urlDto.getUrl();
					pictureUrl = pictureUrl.substring(0, pictureUrl.lastIndexOf("/") + 1)
							+ pictureName;
					pcmPictureUrl.setPictureUrl(pictureUrl);
					Date date = new Date();
					pcmPictureUrl.setUpdateDate(date);
					pcmPictureUrlMapper.insertSelective(pcmPictureUrl);
				}
			} else {
				throw new BleException(ErrorCode.PICTURE_SAVE_SUCCEED.getErrorCode(), errorMsg);
			}
			if (!flag) {
				throw new BleException(ErrorCode.PICTURE_SAVE_FAILED.getErrorCode(),
						ErrorCode.PICTURE_SAVE_FAILED.getMemo());
			}
		}

		return mapList;
	}

	public PcmPictureUrl getPcmPictureUrlEntity(PcmPictureUrlDto dto, PcmPictureInfoDto infoDto) {
		PcmPictureUrl pcmPictureUrl = new PcmPictureUrl();
		PcmPictureStan picStan = new PcmPictureStan();
		picStan.setPictureHeight(dto.getHeight());
		picStan.setPictureWidth(dto.getWidth());
		List<PcmPictureStan> picStanList = psMapper.selectListByParam(picStan);
		if (picStanList != null && picStanList.size() > 0) {
			pcmPictureUrl.setStanSid(picStanList.get(0).getSid().toString());
		} else {
			int insertSelective = psMapper.insertSelective(picStan);
			if (insertSelective == 0) {

			}
			pcmPictureUrl.setStanSid(picStan.getSid().toString());
		}
		pcmPictureUrl.setSkuSid(infoDto.getProductCode());
		pcmPictureUrl.setColorCode(infoDto.getColorCode());
		pcmPictureUrl.setPictureUrl(dto.getUrl());
		pcmPictureUrl.setPictureSid(dto.getPhotoCode());
		pcmPictureUrl.setPictureName(dto.getPhotoName());
		if (StringUtils.isNotBlank(dto.getNumber())) {
			pcmPictureUrl.setSort(Integer.parseInt(dto.getNumber()));
		}
		if (StringUtils.isNotBlank(dto.getIsMain())) {
			pcmPictureUrl.setIsPrimary(Integer.parseInt(dto.getIsMain()));
		}
		if (StringUtils.isNotBlank(dto.getIsModel())) {
			pcmPictureUrl.setIsModel(Integer.parseInt(dto.getIsModel()));
		}
		if (StringUtils.isNotBlank(dto.getIsThumbnail())) {
			pcmPictureUrl.setIsThumbnail(Integer.parseInt(dto.getIsThumbnail()));
		}
		pcmPictureUrl.setOptName(infoDto.getActionPerson());
		return pcmPictureUrl;
	}

	/**
	 * 请求数据校验
	 * 
	 * @Methods Name validPictureRequestData
	 * @Create In 2015年9月21日 By kongqf
	 * @param dto
	 * @return String
	 */
	public String validPictureRequestData(PcmPictureUrlDto dto, PcmPictureInfoDto infoDto) {
		StringBuilder errorMsg = new StringBuilder();
		if (StringUtils.isBlank(infoDto.getProductCode())) {
			errorMsg.append(ErrorCode.PICTURE_PRODUCTCODE_IS_NULL.getMemo());
		}
		if (StringUtils.isBlank(infoDto.getColorCode())) {
			errorMsg.append(ErrorCode.PICTURE_COLORCODE_IS_NULL.getMemo());
		}
		if (StringUtils.isBlank(infoDto.getType())) {
			errorMsg.append(ErrorCode.PICTURE_TYPE_IS_NULL.getMemo());
		}
		if (StringUtils.isBlank(dto.getPhotoCode())) {
			errorMsg.append(ErrorCode.PICTURE_PHOTOCODE_IS_NULL.getMemo());
		}
		if (StringUtils.isBlank(dto.getPhotoName())) {
			errorMsg.append(ErrorCode.PICTURE_PHOTONAME_IS_NULL.getMemo());
		}
		if (StringUtils.isBlank(dto.getUrl())) {
			errorMsg.append(ErrorCode.PICTURE_URL_IS_NULL.getMemo());
		}
		if (StringUtils.isBlank(dto.getNumber())) {
			errorMsg.append(ErrorCode.PICTURE_NUMBER_IS_NULL.getMemo());
		} else {
			if (!NumberUtils.isNumeric(dto.getNumber())) {
				errorMsg.append(ErrorCode.PICTURE_NUMBER_IS_ERROR.getMemo());
			}
		}
		if (StringUtils.isNotBlank(dto.getIsMain()) && !NumberUtils.isNumeric(dto.getIsMain())) {
			errorMsg.append(ErrorCode.PICTURE_ISMAIN_IS_ERROR.getMemo());
		}
		if (StringUtils.isNotBlank(dto.getIsModel()) && !NumberUtils.isNumeric(dto.getIsModel())) {
			errorMsg.append(ErrorCode.PICTURE_ISMODEL_IS_ERROR.getMemo());
		}
		if (StringUtils.isNotBlank(dto.getIsThumbnail())
				&& !NumberUtils.isNumeric(dto.getIsThumbnail())) {
			errorMsg.append(ErrorCode.PICTURE_ISTHUMBNAIL_IS_ERROR.getMemo());
		}
		return errorMsg.toString();
	}

	/**
	 * 根据产品编码,色系查询商品图片
	 * 
	 * @Methods Name queryPrctureInfoByPara
	 * @Create In 2015年10月14日 By zhangxy
	 * @param spuCode
	 * @param colorSid
	 * @return List<PcmPictureUrl>
	 */
	public List<PcmPictureUrl> queryPrctureInfoByPara(PcmPictureDto dto) {
		PcmPictureUrl entity = new PcmPictureUrl();
		BeanUtils.copyProperties(dto, entity);
		if (dto.getIfDelete() == null) {
			entity.setIfDelete(0);
		}
		List<PcmPictureUrl> list = pcmPictureUrlMapper.selectListByParam(entity);
		return list;
	}

	@Override
	public List<Map<String, Object>> queryColorBySpu(String spuCode) {
		List<Map<String, Object>> list = pcmPictureUrlMapper.selectColorBySpu(spuCode);
		return list;
	}

	@Override
	public Integer getSortByOara(PcmPictureDto dto) {
		PcmPictureUrl entity = new PcmPictureUrl();
		BeanUtils.copyProperties(dto, entity);
		if (dto.getIfDelete() == null) {
			entity.setIfDelete(0);
		}
		Integer sort = pcmPictureUrlMapper.getSortByOara(entity);
		if (sort == null) {
			return 0;
		}
		return sort;
	}

	/**
	 * 根据SID,修改商品图片信息
	 * 
	 * @Methods Name updatePrctureInfoBySid
	 * @Create In 2015年10月15日 By yedong
	 * @param dto
	 * @return Boolean
	 */
	public ProSkuSpuPublishDto updatePrctureInfoBySid(List<PcmPictureUrlDto> dtoList) {
		ProSkuSpuPublishDto proSkuSpu = new ProSkuSpuPublishDto();
		String falg = "3";
		for (PcmPictureUrlDto dto : dtoList) {
			PcmPictureUrl entity = pcmPictureUrlMapper.selectByPrimaryKey(Long.parseLong(dto
					.getSid()));
			redisSpuCMSSHopperInfo(entity.getSkuSid());
			if (dto.getUrl() != null && dto.getUrl() != "") {// 如果url存在，设置主图，修改其他主图标记，修改缩略图为无效，生成新缩略图
				try {
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("spuCode", entity.getSkuSid());
					paramMap.put("colorSid", entity.getColorCode());
					List<PublishDTO> skuList = skuMapper.selectSkuSidBySpuCodeColor(paramMap);
					proSkuSpu.setSkuList(skuList);
					// 修改其他主图
					PcmPictureUrl entity_1 = new PcmPictureUrl();
					entity_1.setIsPrimary(1);
					entity_1.setSkuSid(entity.getSkuSid());
					entity_1.setColorCode(entity.getColorCode());
					entity_1.setIsModel(entity.getIsModel());
					if (dto.getIsModel().equals("1")) {
						entity_1.setStanSid(entity.getStanSid());
					}
					pcmPictureUrlMapper.updateMainBySpuColor(entity_1);

					// 设置主图
					entity.setIsPrimary(0);
					pcmPictureUrlMapper.updateByPrimaryKeySelective(entity);
					// 生成新的缩略图
					if (dto.getIsModel().equals("0")) {
						// 修改其他缩略图为无效
						PcmPictureUrl entity_2 = new PcmPictureUrl();
						entity_2.setSkuSid(entity.getSkuSid());
						entity_2.setColorCode(entity.getColorCode());
						pcmPictureUrlMapper.updateDeleteBySpuColor(entity_2);
						entity.setSid(null);
						entity.setIsPrimary(1);// 不是主图
						entity.setIsModel(1);// 不是原图
						entity.setIsThumbnail(0);
						entity.setStanSid(Constants.PIC_STANSID);
						String pictureName = entity.getPictureName();
						String[] pictureNameBySplit = pictureName.split("\\.");
						pictureName = pictureNameBySplit[0] + "_sl." + pictureNameBySplit[1];
						entity.setPictureName(pictureName);
						String pictureUrl = dto.getUrl();
						pictureUrl = pictureUrl.substring(0, pictureUrl.lastIndexOf("/") + 1)
								+ pictureName;
						entity.setPictureUrl(pictureUrl);
						Date date = new Date();
						entity.setUpdateDate(date);
						pcmPictureUrlMapper.insertSelective(entity);
					}
					logger.info("修改成功");
					// falg = true;
					falg = "4";
				} catch (Exception e) {
					// falg = false;
					// return falg;
					falg = "3";
				}
			} else {
				if (StringUtils.isNotBlank(dto.getHeight())
						&& StringUtils.isNotBlank(dto.getWidth())) {
					PcmPictureStan picStan = new PcmPictureStan();
					picStan.setPictureHeight(dto.getHeight());
					picStan.setPictureWidth(dto.getWidth());
					List<PcmPictureStan> picStanList = psMapper.selectListByParam(picStan);
					if (picStanList != null && picStanList.size() > 0) {
						entity.setStanSid(picStanList.get(0).getSid().toString());
					} else {
						int insertSelective = psMapper.insertSelective(picStan);
						if (insertSelective == 0) {

						}
						entity.setStanSid(picStan.getSid().toString());
					}
				}

				if (StringUtils.isNotBlank(dto.getIfDelete())) {
					entity.setIfDelete(Integer.parseInt(dto.getIfDelete()));
				}
				if (StringUtils.isNotBlank(dto.getIsModel())) {
					entity.setIsModel(Integer.parseInt(dto.getIsModel()));
				}
				if (StringUtils.isNotBlank(dto.getIsThumbnail())) {
					entity.setIsThumbnail(Integer.parseInt(dto.getIsThumbnail()));
				}
				if (StringUtils.isNotBlank(dto.getNumber())) {
					entity.setSort(Integer.parseInt(dto.getNumber()));
				}
				try {
					pcmPictureUrlMapper.updateByPrimaryKeySelective(entity);
					logger.info("修改成功");
					// falg = true;
					falg = "4";
				} catch (Exception e) {
					// falg = false;
					// return falg;
					falg = "3";
				}
			}
		}
		proSkuSpu.setProType(falg);
		return proSkuSpu;
	}

	/**
	 * sku基础查询
	 * 
	 * @Methods Name selectListByParam
	 * @Create In 2016年3月3日 By yedong
	 * @param record
	 * @return List<PcmProDetail>
	 */
	public List<PcmProDetail> selectListByParam(PcmProDetail record) {
		List<PcmProDetail> skuList = skuMapper.selectListByParam(record);
		return skuList;
	}

	/**
	 * 根据产品编码,色系修改商品拍照状态
	 * 
	 * @Methods Name updatePhotoStatus
	 * @Create In 2015年10月21日 By zhangxy void
	 */
	@Override
	@Transactional
	public void updatePhotoStatus(List<PhotoStatusDto> list) throws BleException {
		for (PhotoStatusDto dto : list) {
			PcmProDetail record = new PcmProDetail();
			record.setProductSid((Long.parseLong(dto.getProductCode()) - Constants.SPU_CODE));
			record.setProColorSid(Long.valueOf(dto.getColor()));
			List<PcmProDetail> skuList = skuMapper.selectListByParam(record);
			record.setProductSid(Long.parseLong(dto.getProductCode()));
			if (skuList != null && skuList.size() > 0) {
				if (skuList.get(0).getPhotoStatus() != 4) {
					if (dto.getStatus() != null) {
						record.setPhotoStatus(dto.getStatus());
					}
					if (dto.getPhotoPlanSid() != null && dto.getPhotoPlanSid() != "") {
						record.setPhotoPlanSid(dto.getPhotoPlanSid());
					}
					int i = skuMapper.updatePhotoStatusByProSidColorSid(record);
					if (i == 0) {
						throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
								ErrorCode.DATA_OPER_ERROR.getMemo());
					}
				}
			} else {
				throw new BleException(ErrorCode.PRODUCT_IS_NULL.getErrorCode(), "产品:"
						+ dto.getProductCode() + ",色系:" + dto.getColor() + "-----"
						+ ErrorCode.PRODUCT_IS_NULL.getMemo());
			}
		}
	}

	/**
	 * 
	 * @Methods Name redisOmsDelInfo
	 * @Create In 2016年4月8日 By yedong
	 * @param spuCode
	 *            void
	 */
	public void redisOmsDelInfo(String spuCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("spuCode", spuCode);
		List<String> proSids = spMapper.getProSidBySkuOrSpu(paramMap);
		for (String code : proSids) {
			boolean del = redisUtil.del(DomainName.getShoppeInfo + code);
			if (!del) {
				ProductPageDto pageDto = new ProductPageDto();
				pageDto.setProductCode(code);
				OmsResProInfoDto page = proService.getProductPageByPara(code, pageDto);
				String pageJson = JsonUtil.getJSONString(page);
				PcmRedis redis = new PcmRedis();
				redis.setCreatetime(new Date());
				redis.setValue(pageJson);
				redis.setRedisffield(DomainName.getCMSSHopperInfo);
				redis.setKeyname(code);
				redisService.savePcmRedis(redis);
			}
		}
	}

	/**
	 * 删除单品页缓存
	 * 
	 * @Methods Name redisSpuCMSSHopperInfo
	 * @Create In 2015年12月23日 By yedong
	 * @param spuCode
	 *            void
	 */
	public void redisSpuCMSSHopperInfo(String spuCode) {
		List<String> skuList = spuMapper.selectSkuCodeBySpuCode(spuCode);
		for (String skuCode : skuList) {
			// RedisVo vo = new RedisVo();
			// vo.setKey(DomainName.getCMSSHopperInfo + skuCode);
			// // vo.setField(DomainName.getCMSSHopperInfo);
			// vo.setType(CacheUtils.HDEL);
			// CacheUtils.setRedisData(vo);
			boolean del = redisUtil.del(DomainName.getCMSSHopperInfo + skuCode);
			if (!del) {// 如果删除失败
				PcmRedis redis = new PcmRedis();
				String proYeUrl = PropertyUtil.getSystemUrl("pcm-outer-sdc")
						+ "product/getProYeInfoBySpuCode.htm";
				String proYeJson = HttpUtil.doPost(proYeUrl, "{\"spuCode\":\"" + skuCode + "\"}");
				redis.setCreatetime(new Date());
				redis.setValue(proYeJson);
				redis.setRedisffield(DomainName.getCMSSHopperInfo);
				redis.setKeyname(skuCode);
				redisService.savePcmRedis(redis);
			}
		}

	}
}

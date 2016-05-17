/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.price.service.implPcmPriceImpl.java
 * @Create By duanzhaole
 * @Create In 2015年7月10日 上午10:41:08
 * TODO
 */
package com.wangfj.product.price.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.CacheUtils;
import com.wangfj.core.utils.DateUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.core.utils.RedisUtil;
import com.wangfj.product.common.domain.entity.PcmRedis;
import com.wangfj.product.common.domain.vo.PcmExceptionLogDto;
import com.wangfj.product.common.service.intf.IPcmExceptionLogService;
import com.wangfj.product.common.service.intf.IPcmRedisService;
import com.wangfj.product.constants.DomainName;
import com.wangfj.product.constants.StatusCodeConstants.StatusCode;
import com.wangfj.product.maindata.domain.entity.PcmErpProduct;
import com.wangfj.product.maindata.persistence.PcmErpProductMapper;
import com.wangfj.product.organization.domain.entity.PcmOrganization;
import com.wangfj.product.organization.persistence.PcmOrganizationMapper;
import com.wangfj.product.price.domain.entity.PcmChangePriceRecord;
import com.wangfj.product.price.domain.entity.PcmPrice;
import com.wangfj.product.price.domain.vo.PcmPriceChangeDto;
import com.wangfj.product.price.domain.vo.PcmPriceERPDto;
import com.wangfj.product.price.domain.vo.PcmPricePISDto;
import com.wangfj.product.price.domain.vo.PcmPriceToERPPDto;
import com.wangfj.product.price.domain.vo.QueryPriceDto;
import com.wangfj.product.price.domain.vo.QueryPriceInfoDto;
import com.wangfj.product.price.domain.vo.QueryPriceListDto;
import com.wangfj.product.price.domain.vo.QueryProductPriceInfoDto;
import com.wangfj.product.price.domain.vo.QueryShoppeProSidDto;
import com.wangfj.product.price.domain.vo.SelectChangePriceRecordDto;
import com.wangfj.product.price.domain.vo.SelectPcmPriceToERPPDto;
import com.wangfj.product.price.domain.vo.SelectPriceDto;
import com.wangfj.product.price.domain.vo.SelectProductPriceInfoDto;
import com.wangfj.product.price.domain.vo.SelectShoppeProPriceInfoDto;
import com.wangfj.product.price.persistence.PcmChangePriceRecordMapper;
import com.wangfj.product.price.persistence.PcmPriceHisMapper;
import com.wangfj.product.price.persistence.PcmPriceMapper;
import com.wangfj.product.price.service.intf.IPcmPriceService;
import com.wangfj.util.Constants;

/**
 * 价格信息管理
 *
 * @Class Name PcmPriceServiceImpl
 * @Author kongqf
 * @Create In 2015年7月28日
 */
@Service
public class PcmPriceServiceImpl implements IPcmPriceService {
	private static final Logger logger = LoggerFactory.getLogger(PcmPriceServiceImpl.class);

	@Autowired
	private PcmPriceMapper pcmPriceMapper;
	@Autowired
	private PcmErpProductMapper pcmErpProductMapper;
	@Autowired
	private PcmPriceHisMapper pcmPriceHisMapper;
	@Autowired
	private PcmChangePriceRecordMapper pcmChangePriceRecordMapper;
	// 组织机构
	@Autowired
	private PcmOrganizationMapper organizationMapper;
	@Autowired
	private IPcmExceptionLogService pcmExceptionLogService;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private IPcmRedisService redisService;

	/**
	 * 商品价格初始化
	 *
	 * @param pcmPrice
	 * @return int
	 * @Methods Name initProductPriceInfo
	 * @Create In 2015年7月27日 By kongqf
	 */
	public int initProductPriceInfo(PcmPrice pcmPrice) {
		logger.info("start initProductPriceInfo(),param:" + pcmPrice.toString());
		int returnValue = Constants.PUBLIC_0;

		returnValue = pcmPriceMapper.insertSelective(pcmPrice);
		if (Constants.PUBLIC_0 == returnValue) {
			logger.info(pcmPrice.getShoppeProSid() + ",priceType:" + pcmPrice.getPriceChannel()
					+ ",result:" + Constants.FAILURE);
			return returnValue;
		}
		logger.info(pcmPrice.getShoppeProSid() + ",priceType:" + pcmPrice.getPriceChannel()
				+ ",result:" + Constants.SUCCESS);

		return returnValue;
	}

	/**
	 * 商品价格查询
	 *
	 * @param priceDto
	 * @return Map<String,Object>
	 * @Methods Name queryPriceInfoByPara
	 * @Create In 2015年7月28日 By kongqf
	 */
	// @RedisCacheGet(redisName = DomainName.getPrice, returnObj =
	// "com.wangfj.product.price.domain.vo.SelectPriceDto")
	public SelectPriceDto queryPriceInfoByPara(String key, QueryPriceDto queryPriceDto) {
		logger.info("start queryPriceInfoByPara(),param:" + queryPriceDto.toString());

		SelectPriceDto selectPriceDto = new SelectPriceDto();
		// 默认查询全渠道价格
		if (StringUtils.isBlank(queryPriceDto.getChannelSid())) {
			queryPriceDto.setChannelSid(Constants.DEFAULT_CHANNEL_SID);
			selectPriceDto = pcmPriceMapper.queryPriceInfoByPara(queryPriceDto);
		} else {
			selectPriceDto = pcmPriceMapper.queryPriceInfoByPara(queryPriceDto);
			// 有效价格信息不存在，返回全渠道价格信息
			if (selectPriceDto == null) {
				queryPriceDto.setChannelSid(Constants.DEFAULT_CHANNEL_SID);
				selectPriceDto = pcmPriceMapper.queryPriceInfoByPara(queryPriceDto);
			}
		}

		// logger.info("queryPriceInfoByPara," + selectPriceDto.toString());
		return selectPriceDto;

	}

	public List<SelectPriceDto> queryPriceInfoListByPara(QueryPriceListDto queryPriceListDto) {
		logger.info("start queryPriceInfoListByPara(),param:" + queryPriceListDto.toString());
		List<SelectPriceDto> listDto = new ArrayList<SelectPriceDto>();
		SelectPriceDto dto = null;
		QueryPriceDto queryPriceDto = null;
		String storeCode = StringUtils.EMPTY;
		if (StringUtils.isNotBlank(queryPriceListDto.getStoreCode())) {
			storeCode = queryPriceListDto.getStoreCode();
		}
		for (String key : queryPriceListDto.getShoppeProSids()) {
			dto = new SelectPriceDto();
			String salePrice = redisUtil.get(DomainName.getPrice + storeCode + key, "0000");
			if (!"0000".equals(salePrice)) {
				dto.setShoppeProSid(key);
				dto.setSalePrice(new BigDecimal(salePrice));
			} else {
				queryPriceDto = new QueryPriceDto();
				queryPriceDto.setShoppeProSid(key);
				queryPriceDto.setStoreCode(storeCode);
				dto = queryPriceInfoByPara(key, queryPriceDto);

				if (dto != null) {
					if (dto.getExpireDate() > new Long(Constants.expireDate)) {
						redisUtil.set(DomainName.getPrice + storeCode + key,
								String.valueOf(dto.getSalePrice()), Constants.expireDate);
					} else {
						redisUtil.set(DomainName.getPrice + storeCode + key,
								String.valueOf(dto.getSalePrice()), dto.getExpireDate().intValue());
					}
				}
			}
			if (dto != null) {
				listDto.add(dto);
			}
		}

		logger.info("queryPriceInfoListByPara," + listDto.toString());
		return listDto;

	}

	/**
	 * 保存单一变价请求信息
	 *
	 * @param dto
	 * @return boolean
	 * @Methods Name saveChangePriceRecord
	 * @Create In 2015年8月14日 By kongqf
	 */
	public boolean saveChangePriceRecord(PcmPriceERPDto pcmpricedto, String fromSystem) {
		logger.info("start saveChangePriceRecord(),param:" + pcmpricedto.toString());
		boolean flag = true;
		// 数据校验
		validPriceRequestData(pcmpricedto, fromSystem);
		if (fromSystem.equals(Constants.EFUTUREERP)
				&& StringUtils.isBlank(pcmpricedto.getSupplierprodcode())) {
			pcmpricedto.setAttribute5("1");
		}
		pcmpricedto.setAction_persion(pcmpricedto.getAction_persion() + ";" + fromSystem);

		// if (StringUtils.isEmpty(errorMsg)) {
		int count = pcmChangePriceRecordMapper.insertSelectiveByERPDto(pcmpricedto);
		if (0 == count) {
			flag = false;
		}
		logger.info("start saveChangePriceRecord(),result:" + flag);
		// if (!flag) {
		// throw new
		// BleException(ErrorCode.PRICE_CHANGERECORD_ERROR.getErrorCode(),
		// ErrorCode.PRICE_CHANGERECORD_ERROR.getMemo());
		// }
		// } else {
		// throw new BleException(ErrorCode.PRICE_REQUEST_VALID.getErrorCode(),
		// errorMsg);
		// }
		return flag;
	}

	/**
	 * 单一变价
	 *
	 * @param pcmpricedto
	 * @return boolean
	 * @Methods Name saveOrUpdatePrice
	 * @Create In 2015年7月31日 By kongqf
	 */
	public boolean saveOrUpdatePrice(PcmPriceERPDto pcmpricedto, String fromSystem,
			BigDecimal upperLimit, BigDecimal lowerLimit) {
		logger.info("start saveOrUpdatePrice(),param:" + pcmpricedto.toString());
		boolean flag = true;
		boolean isShoppePro = true;
		int count = 0;

		if (Constants.EFUTUREERP.equals(fromSystem)) {
			if (StringUtils.isBlank(pcmpricedto.getSupplierprodcode())) {
				isShoppePro = false;
			}
		}

		// if (isShoppePro) {
		PcmPriceChangeDto pcmPriceChangeDto = new PcmPriceChangeDto();
		PcmPrice queryPcmPrice = new PcmPrice();
		queryPcmPrice = getPcmPriceEntityV3(pcmpricedto, fromSystem);
		pcmPriceChangeDto = getChangePcmPriceERPDtoListV2(queryPcmPrice, upperLimit, lowerLimit);
		logger.info("start pcmPriceChangeDto,param:" + pcmPriceChangeDto.toString());

		if (StringUtils.isNotEmpty(pcmPriceChangeDto.getShoppeProSid())
				&& pcmPriceChangeDto.getPromotionBeginTime() != null
				&& pcmPriceChangeDto.getPromotionEndTime() != null) {
			count = pcmPriceMapper.deletePriceInfoByPara(pcmPriceChangeDto);
			if (0 == count) {
				flag = false;
			}
			logger.info("start deletePriceInfoByPara(),result:" + flag);
		}
		if (flag && pcmPriceChangeDto.getPcmPriceListN() != null
				&& pcmPriceChangeDto.getPcmPriceListN().size() > 0) {
			String redisKey = "";
			for (PcmPrice pp : pcmPriceChangeDto.getPcmPriceListN()) {
				count = pcmPriceMapper.insertSelective(pp);
				redisKey = pp.getShoppeProSid();
				if (0 == count) {
					flag = false;
				}
			}
			if (flag) {
				String key = "";
				if (isShoppePro) {
					key = DomainName.getPrice + redisKey;
				} else {
					key = DomainName.getPrice + queryPcmPrice.getAttribute2() + redisKey;
				}
				redisUtil.del(key);
				if (!CacheUtils.cacheFlag) {
					PcmRedis pcmRedisDto = new PcmRedis();
					pcmRedisDto.setRedisffield(DomainName.getPrice);
					pcmRedisDto.setKeyname(key);
					redisService.savePcmRedis(pcmRedisDto);
				}
			}
		}
		/*
		 * } else { Map<String, Object> erpMap = new HashMap<String, Object>();
		 * erpMap.put("productCode", pcmpricedto.getMatnr());
		 * erpMap.put("storeCode", pcmpricedto.getStorecode()); Integer rowNum =
		 * pcmErpProductMapper.getCountByParam(erpMap); if (1 == rowNum) {
		 * PcmErpProduct erpProductInfo = new PcmErpProduct();
		 * erpProductInfo.setBigCodePrice(new
		 * BigDecimal(pcmpricedto.getZsprice()));
		 * erpProductInfo.setSalesPrice(erpProductInfo.getBigCodePrice());
		 * erpProductInfo.setProductCode(pcmpricedto.getMatnr());
		 * erpProductInfo.setStoreCode(pcmpricedto.getStorecode());
		 * 
		 * count = pcmErpProductMapper.updateByCodeSelective(erpProductInfo); if
		 * (0 == count) { flag = false; } } else { throw new
		 * BleException(ErrorCode.PRICE_ERPPROCODE_NOT_EXISTS.getErrorCode(),
		 * ErrorCode.PRICE_ERPPROCODE_NOT_EXISTS.getMemo()); } }
		 */
		if (!flag) {
			throw new BleException(ErrorCode.ADD_CHANGE_PRICE_ERROR.getErrorCode(),
					ErrorCode.ADD_CHANGE_PRICE_ERROR.getMemo());
		}

		return flag;
	}

	/**
	 * 修改大码价格
	 *
	 * @param priceRecords
	 * @return List<PcmChangePriceRecord>
	 * @Methods Name UpdateERPProPrice
	 * @Create In 2015年9月23日 By kongqf
	 */
	@Override
	public List<PcmChangePriceRecord> UpdateERPProPrice(List<PcmChangePriceRecord> priceRecords) {
		List<PcmChangePriceRecord> errorList = new ArrayList<PcmChangePriceRecord>();
		PcmErpProduct erpProductInfo = null;
		for (PcmChangePriceRecord record : priceRecords) {
			erpProductInfo = new PcmErpProduct();
			erpProductInfo.setBigCodePrice(new BigDecimal(record.getZsprice()));
			erpProductInfo.setSalesPrice(erpProductInfo.getBigCodePrice());
			erpProductInfo.setProductCode(record.getMatnr());
			erpProductInfo.setStoreCode(record.getStoreCode());

			int count = pcmErpProductMapper.updateByCodeSelective(erpProductInfo);
			if (0 == count) {
				errorList.add(record);
			}
		}
		return errorList;
	}

	/**
	 * 保存批量变价请求信息
	 *
	 * @param dto
	 * @return boolean
	 * @Methods Name saveChangePriceRecord
	 * @Create In 2015年8月14日 By kongqf
	 */
	public boolean saveBatchChangePriceRecord(PcmPricePISDto pcmPricePISDto) {
		logger.info("start saveBatchChangePriceRecord(),param:" + pcmPricePISDto.toString());
		boolean flag = true;
		validBatchPriceRequestData(pcmPricePISDto);

		int count = pcmChangePriceRecordMapper.insertSelectiveByPISDto(pcmPricePISDto);
		if (Constants.PUBLIC_0 == count) {
			flag = false;
		}
		logger.info("start saveBatchChangePriceRecord(),result:" + flag);
		if (!flag) {
			PcmExceptionLogDto pcmExceptionLogDto = new PcmExceptionLogDto();
			pcmExceptionLogDto.setInterfaceName("saveBatchChangePriceRecord");
			pcmExceptionLogDto.setExceptionType(StatusCode.EXCEPTION_PRICE.getStatus());
			pcmExceptionLogDto.setErrorMessage(ErrorCode.PRICE_CHANGERECORD_ERROR.getErrorCode()
					+ ":" + ErrorCode.PRICE_CHANGERECORD_ERROR.getMemo());
			pcmExceptionLogDto.setDataContent(JsonUtil.getJSONString(pcmPricePISDto));
			pcmExceptionLogDto.setUuid(UUID.randomUUID().toString());
			pcmExceptionLogService.saveExceptionLogInfo(pcmExceptionLogDto);
		}
		return flag;
	}

	/**
	 * 根据条件查询所有专柜商品编码 管理分类、供应商编码、专柜编码
	 *
	 * @param pcmpricedto
	 * @return boolean
	 * @Methods Name saveOrUpdatePriceBatch
	 * @Create In 2015年8月17日 By kongqf
	 */
	public Page<QueryShoppeProSidDto> queryBatchChangePriceInfo(PcmPricePISDto pcmPricePISDto) {
		logger.info("start queryBatchChangePriceInfo(),param:" + pcmPricePISDto.toString());
		Page<QueryShoppeProSidDto> productPageDto = new Page<QueryShoppeProSidDto>();
		List<QueryShoppeProSidDto> queryShoppeProSidDtos = new ArrayList<QueryShoppeProSidDto>();
		queryShoppeProSidDtos = pcmPriceMapper.SelectShoppeProSidInfoByPara(pcmPricePISDto);
		productPageDto.setList(queryShoppeProSidDtos);
		logger.info("start queryBatchChangePriceInfo(),result:" + queryShoppeProSidDtos.toString());
		return productPageDto;
	}

	/**
	 * 根据条件和pagesize查询所有专柜商品编码总页数
	 *
	 * @param pcmPricePISDto
	 * @return int
	 * @Methods Name queryBatchChangePriceInfoPages
	 * @Create In 2015年8月21日 By kongqf
	 */
	public int queryBatchChangePriceInfoPages(PcmPricePISDto pcmPricePISDto) {
		logger.info("start queryBatchChangePriceInfoPages(),param:" + pcmPricePISDto.toString());
		Integer count = pcmPriceMapper.SelectShoppeProSidCountByPara(pcmPricePISDto);
		logger.info("start queryBatchChangePriceInfoPages(),count:" + count);
		Integer pages = 0;
		if (count != Constants.PUBLIC_0) {
			pages = (int) count / pcmPricePISDto.getPageSize();
			if (count % pcmPricePISDto.getPageSize() > 0) {
				pages++;
			}
		}
		logger.info("start queryBatchChangePriceInfoPages(),pages:" + pages);
		return pages;
	}

	/**
	 * 批量变价
	 *
	 * @param pcmpricedto
	 * @return boolean
	 * @Methods Name saveOrUpdatePrice
	 * @Create In 2015年7月31日 By kongqf
	 */
	public boolean saveOrUpdateBatchPrice(PcmPricePISDto pcmPricePISDto, BigDecimal upperLimit,
			BigDecimal lowerLimit) {
		logger.info("start saveOrUpdatePrice(),param:" + pcmPricePISDto.toString());
		boolean flag = true;
		int count = 0;

		PcmPriceChangeDto pcmPriceChangeDto = new PcmPriceChangeDto();
		PcmPrice queryPcmPrice = new PcmPrice();
		queryPcmPrice = getPcmPriceEntityV4(pcmPricePISDto);
		BigDecimal salePriceValue = new BigDecimal(pcmPricePISDto.getSalepricevalue());

		pcmPriceChangeDto = getBatchChangePcmPriceERPDtoListV2(queryPcmPrice,
				pcmPricePISDto.getSalepricetype(), salePriceValue, upperLimit, lowerLimit);
		logger.info("start pcmPriceChangeDto,param:" + pcmPriceChangeDto.toString());

		if (StringUtils.isNotEmpty(pcmPriceChangeDto.getShoppeProSid())
				&& pcmPriceChangeDto.getPromotionBeginTime() != null
				&& pcmPriceChangeDto.getPromotionEndTime() != null) {
			count = pcmPriceMapper.deletePriceInfoByPara(pcmPriceChangeDto);
			if (0 == count) {
				flag = false;
			}
			logger.info("start deletePriceInfoByPara(),result:" + flag);
		}
		if (flag && pcmPriceChangeDto.getPcmPriceListN() != null
				&& pcmPriceChangeDto.getPcmPriceListN().size() > 0) {
			String redisKey = "";
			for (PcmPrice pp : pcmPriceChangeDto.getPcmPriceListN()) {
				count = pcmPriceMapper.insertSelective(pp);
				redisKey = pp.getShoppeProSid();
				if (0 == count) {
					flag = false;
				}
			}
			if (flag) {
				redisUtil.del(DomainName.getPrice + redisKey);
				if (!CacheUtils.cacheFlag) {
					PcmRedis pcmRedisDto = new PcmRedis();
					pcmRedisDto.setRedisffield(DomainName.getPrice);
					pcmRedisDto.setKeyname(DomainName.getPrice + redisKey);
					redisService.savePcmRedis(pcmRedisDto);
				}
			}
		}

		if (!flag) {
			throw new BleException(ErrorCode.ADD_CHANGE_PRICE_ERROR.getErrorCode(),
					ErrorCode.ADD_CHANGE_PRICE_ERROR.getMemo());
		}

		return flag;
	}

	// [start] 单一变价按天拆分

	/**
	 * 单一变价信息拆分
	 *
	 * @param pcmPrice
	 * @return PcmPriceChangeDto
	 * @Methods Name getChangePcmPriceERPDtoList3
	 * @Create In 2015年8月18日 By kongqf
	 */
	public PcmPriceChangeDto getChangePcmPriceERPDtoList(PcmPrice pcmPrice, BigDecimal upperLimit,
			BigDecimal lowerLimit) {
		List<PcmPrice> pcmPriceListN = new ArrayList<PcmPrice>();
		PcmPriceChangeDto pcmPriceChangeDto = new PcmPriceChangeDto();
		PcmPrice pcmPriceB = new PcmPrice();
		PcmPrice pcmPriceE = new PcmPrice();

		Date beginDate = pcmPrice.getPromotionBeginTime();
		Date endDate = pcmPrice.getPromotionEndTime();

		QueryPriceInfoDto queryPriceInfoDto = new QueryPriceInfoDto();
		queryPriceInfoDto = getQueryPriceInfoDto(pcmPrice);
		pcmPriceB = pcmPriceMapper.selectActivePriceInfo(queryPriceInfoDto);
		queryPriceInfoDto.setPromotionBeginTime(endDate);
		pcmPriceE = pcmPriceMapper.selectActivePriceInfo(queryPriceInfoDto);
		BigDecimal currPrice = pcmPrice.getCurrentPrice();
		// BigDecimal originalPrice = pcmPrice.getOriginalPrice();
		// BigDecimal PromotionPrice = pcmPrice.getPromotionPrice();
		Integer PriceType = pcmPrice.getPriceType();
		String ChangeCode = pcmPrice.getAttribute1();
		PcmPrice pcmPriceNew = new PcmPrice();
		int result = 0;

		if (pcmPriceB != null && pcmPriceE != null) {
			pcmPriceNew = getPcmPriceNew(pcmPriceB);

			if (pcmPriceB.getSid().equals(pcmPriceE.getSid())) {
				result = compareDate(beginDate, pcmPriceB.getPromotionBeginTime());
				// [start] result=1
				if (result == 1) {
					if (pcmPriceB.getPriceType() == 1) {
						pcmPriceNew.setPromotionEndTime(addDate(beginDate, Calendar.SECOND, -1));
						pcmPriceListN.add(pcmPriceNew);

						PcmPrice pcmPriceNew2 = getPcmPriceNew(pcmPriceB);
						pcmPriceNew2.setPromotionBeginTime(beginDate);
						pcmPriceNew2.setPromotionEndTime(endDate);
						pcmPriceNew2.setPriceType(PriceType);
						pcmPriceNew2.setAttribute1(ChangeCode);

						if (PriceType == 2) {
							pcmPriceNew2.setPromotionEndTime(
									addDate(endDate, Calendar.HOUR, 24, Calendar.SECOND, -1));
							result = compareDate(pcmPriceNew2.getPromotionEndTime(),
									pcmPriceB.getPromotionEndTime());
							if (result == -1) {
								PcmPrice pcmPriceNew3 = getPcmPriceNew(pcmPriceB);
								pcmPriceNew3
										.setPromotionBeginTime(addDate(endDate, Calendar.HOUR, 24));
								pcmPriceListN.add(pcmPriceNew3);
							}
						}

						pcmPriceListN.add(pcmPriceNew2);
					}
					if (pcmPriceB.getPriceType() == 2) {
						if (PriceType == 1) {
							pcmPriceListN.add(pcmPriceNew);

							PcmPrice pcmPriceNew2 = getPcmPriceNew(pcmPriceB);
							pcmPriceNew2.setPromotionBeginTime(addDate(
									pcmPriceB.getPromotionEndTime(), Calendar.MILLISECOND, 1000));
							pcmPriceNew2.setPromotionEndTime(endDate);
							pcmPriceNew2.setPriceType(PriceType);
							pcmPriceNew2.setAttribute1(ChangeCode);
							pcmPriceListN.add(pcmPriceNew2);
						}
						if (PriceType == 2) {
							pcmPriceNew
									.setPromotionEndTime(addDate(beginDate, Calendar.SECOND, -1));
							pcmPriceListN.add(pcmPriceNew);

							PcmPrice pcmPriceNew2 = getPcmPriceNew(pcmPriceB);
							pcmPriceNew2.setPromotionBeginTime(beginDate);
							pcmPriceNew2.setPromotionEndTime(
									addDate(endDate, Calendar.HOUR, 24, Calendar.SECOND, -1));
							pcmPriceNew2.setPriceType(PriceType);
							pcmPriceNew2.setAttribute1(ChangeCode);
							pcmPriceListN.add(pcmPriceNew2);

							result = compareDate(pcmPriceNew2.getPromotionEndTime(),
									pcmPriceB.getPromotionEndTime());
							if (result == -1) {
								PcmPrice pcmPriceNew3 = getPcmPriceNew(pcmPriceB);
								pcmPriceNew3
										.setPromotionBeginTime(addDate(endDate, Calendar.HOUR, 24));
								pcmPriceNew3.setPriceType(PriceType);
								pcmPriceListN.add(pcmPriceNew3);
							}
						}
					}
				}
				// [end]
				// [start] result=0
				else if (result == 0) {
					if (pcmPriceB.getPriceType() == 1) {
						pcmPriceNew.setPromotionEndTime(endDate);
						pcmPriceNew.setAttribute1(ChangeCode);
						pcmPriceNew.setPriceType(PriceType);
						if (PriceType == 2) {
							pcmPriceNew.setPromotionEndTime(
									addDate(endDate, Calendar.HOUR, 24, Calendar.SECOND, -1));

							result = compareDate(pcmPriceNew.getPromotionEndTime(),
									pcmPriceB.getPromotionEndTime());
							if (result == -1) {
								PcmPrice pcmPriceNew2 = getPcmPriceNew(pcmPriceB);
								pcmPriceNew2
										.setPromotionBeginTime(addDate(endDate, Calendar.HOUR, 24));
								pcmPriceListN.add(pcmPriceNew2);
							}
						}
						pcmPriceListN.add(pcmPriceNew);
					}
					if (pcmPriceB.getPriceType() == 2) {
						if (PriceType == 1) {
							pcmPriceListN.add(pcmPriceNew);

							PcmPrice pcmPriceNew2 = getPcmPriceNew(pcmPriceB);
							pcmPriceNew2.setPromotionBeginTime(addDate(
									pcmPriceB.getPromotionEndTime(), Calendar.MILLISECOND, 1000));
							pcmPriceNew2.setPromotionEndTime(endDate);
							pcmPriceNew2.setPriceType(PriceType);
							pcmPriceListN.add(pcmPriceNew2);
						}
						if (PriceType == 2) {
							pcmPriceNew.setPromotionEndTime(
									addDate(endDate, Calendar.HOUR, 24, Calendar.SECOND, -1));
							pcmPriceNew.setPriceType(PriceType);
							pcmPriceNew.setAttribute1(ChangeCode);
							pcmPriceListN.add(pcmPriceNew);

							result = compareDate(pcmPriceNew.getPromotionEndTime(),
									pcmPriceB.getPromotionEndTime());
							if (result != 0) {
								PcmPrice pcmPriceNew2 = getPcmPriceNew(pcmPriceB);
								pcmPriceNew2
										.setPromotionBeginTime(addDate(endDate, Calendar.HOUR, 24));
								pcmPriceNew2.setPriceType(PriceType);
								pcmPriceListN.add(pcmPriceNew2);
							}
						}
					}
				} // [end]
			} else {
				// [start] PriceType:2
				if (PriceType == 2) {
					result = compareDate(beginDate, pcmPriceB.getPromotionBeginTime());
					if (result == 0) {
						pcmPriceNew.setPromotionEndTime(
								addDate(endDate, Calendar.HOUR, 24, Calendar.SECOND, -1));
						pcmPriceNew.setPriceType(PriceType);
						pcmPriceNew.setAttribute1(ChangeCode);
						pcmPriceListN.add(pcmPriceNew);

						result = compareDate(pcmPriceNew.getPromotionEndTime(),
								pcmPriceB.getPromotionEndTime());
						if (result == -1) {
							PcmPrice pcmPriceNew2 = getPcmPriceNew(pcmPriceE);
							pcmPriceNew2.setPromotionBeginTime(addDate(endDate, Calendar.HOUR, 24));
							pcmPriceNew2.setPriceType(PriceType);
							pcmPriceListN.add(pcmPriceNew2);
						}
					} else if (result == 1) {
						pcmPriceNew.setPromotionEndTime(addDate(beginDate, Calendar.SECOND, -1));
						pcmPriceListN.add(pcmPriceNew);

						PcmPrice pcmPriceNew2 = getPcmPriceNew(pcmPriceB);
						pcmPriceNew2.setPromotionBeginTime(beginDate);
						pcmPriceNew2.setPromotionEndTime(
								addDate(endDate, Calendar.HOUR, 24, Calendar.SECOND, -1));
						pcmPriceNew2.setPriceType(PriceType);
						pcmPriceNew2.setAttribute1(ChangeCode);
						pcmPriceListN.add(pcmPriceNew2);

						result = compareDate(pcmPriceNew2.getPromotionEndTime(),
								pcmPriceE.getPromotionEndTime());
						if (result == -1) {
							PcmPrice pcmPriceNew3 = getPcmPriceNew(pcmPriceE);
							pcmPriceNew3.setPromotionBeginTime(addDate(endDate, Calendar.HOUR, 24));
							pcmPriceListN.add(pcmPriceNew3);
						}
					}
				}
				// [end]
				// [start] pcmPriceB:2;PriceType:1
				else if (pcmPriceB.getPriceType() == 2 && PriceType == 1) {
					pcmPriceListN.add(pcmPriceNew);

					PcmPrice pcmPriceNew2 = getPcmPriceNew(pcmPriceE);
					pcmPriceNew2.setAttribute1(ChangeCode);
					pcmPriceListN.add(pcmPriceNew2);

					result = compareDate(
							addDate(pcmPriceB.getPromotionEndTime(), Calendar.MILLISECOND, 1000),
							pcmPriceE.getPromotionEndTime());

					if (result != 0) {
						queryPriceInfoDto.setPromotionBeginTime(pcmPriceB.getPromotionEndTime());
						queryPriceInfoDto.setPromotionEndTime(pcmPriceE.getPromotionBeginTime());
						List<PcmPrice> pcmPrices = new ArrayList<PcmPrice>();
						pcmPrices = pcmPriceMapper.selectMiddleActivePriceInfo(queryPriceInfoDto);
						if (pcmPrices != null && pcmPrices.size() > 0) {
							for (PcmPrice pp : pcmPrices) {
								PcmPrice pcmPriceNew4 = getPcmPriceNew(pp);
								if (2 == pp.getPriceType()) {
									pcmPriceListN.add(pcmPriceNew4);
								} else if (1 == pp.getPriceType()) {
									pcmPriceNew4.setPriceType(PriceType);
									pcmPriceNew4.setAttribute1(ChangeCode);
									pcmPriceListN.add(pcmPriceNew4);
								}
							}
						}
					}
				}
				// [end]
				// [start] pcmPriceB:1;PriceType:1
				else if (pcmPriceB.getPriceType() == 1 && PriceType == 1) {
					result = compareDate(beginDate, pcmPriceB.getPromotionBeginTime());
					if (result == 1) {
						pcmPriceNew.setPromotionEndTime(addDate(beginDate, Calendar.SECOND, -1));
						pcmPriceListN.add(pcmPriceNew);

						PcmPrice pcmPriceNew2 = getPcmPriceNew(pcmPriceB);
						pcmPriceNew2.setPromotionBeginTime(beginDate);
						pcmPriceNew2.setAttribute1(ChangeCode);
						pcmPriceListN.add(pcmPriceNew2);

						PcmPrice pcmPriceNew3 = getPcmPriceNew(pcmPriceE);
						pcmPriceNew3.setAttribute1(ChangeCode);
						pcmPriceListN.add(pcmPriceNew3);

					} else if (result == 0) {
						pcmPriceNew.setAttribute1(ChangeCode);
						pcmPriceListN.add(pcmPriceNew);

						PcmPrice pcmPriceNew2 = getPcmPriceNew(pcmPriceE);
						pcmPriceNew2.setPriceType(PriceType);
						pcmPriceNew2.setAttribute1(ChangeCode);
						pcmPriceListN.add(pcmPriceNew2);
					}

					result = compareDate(
							addDate(pcmPriceB.getPromotionEndTime(), Calendar.MILLISECOND, 1000),
							pcmPriceE.getPromotionEndTime());

					if (result != 0) {
						queryPriceInfoDto.setPromotionBeginTime(pcmPriceB.getPromotionEndTime());
						queryPriceInfoDto.setPromotionEndTime(pcmPriceE.getPromotionBeginTime());
						List<PcmPrice> pcmPrices = new ArrayList<PcmPrice>();
						pcmPrices = pcmPriceMapper.selectMiddleActivePriceInfo(queryPriceInfoDto);
						if (pcmPrices != null && pcmPrices.size() > 0) {
							for (PcmPrice pp : pcmPrices) {
								PcmPrice pcmPriceNew4 = getPcmPriceNew(pp);
								if (2 == pp.getPriceType()) {
									pcmPriceListN.add(pcmPriceNew4);
								} else if (1 == pp.getPriceType()) {
									pcmPriceNew4.setPriceType(PriceType);
									pcmPriceNew4.setAttribute1(ChangeCode);
									pcmPriceListN.add(pcmPriceNew4);
								}
							}
						}
					}
				}
			}
			// [end]
			if (pcmPriceListN != null && pcmPriceListN.size() > 0) {
				boolean isActice = false;
				for (PcmPrice pp : pcmPriceListN) {
					if (ChangeCode.equals(pp.getAttribute1())) {
						if (checkIsActivePrice(currPrice, pp.getOriginalPrice(), upperLimit,
								lowerLimit)) {
							pp.setCurrentPrice(currPrice);
							pp.setOriginalPrice(currPrice);
							pp.setPromotionPrice(currPrice);
							pp.setSystemTime(null);
						} else {
							isActice = true;
							break;
						}
					}
				}
				if (isActice) {
					throw new BleException(ErrorCode.PRICE_THRESHOLD_ERROR.getErrorCode(),
							ErrorCode.PRICE_THRESHOLD_ERROR.getMemo());
				}
			}
			pcmPriceChangeDto.setShoppeProSid(pcmPriceB.getShoppeProSid());
			pcmPriceChangeDto.setPromotionBeginTime(pcmPriceB.getPromotionBeginTime());
			pcmPriceChangeDto.setPromotionEndTime(pcmPriceB.getPromotionEndTime());
			if (pcmPriceE != null) {
				pcmPriceChangeDto.setPromotionEndTime(pcmPriceE.getPromotionEndTime());
			}
		} else if (pcmPriceB == null) {
			// pcmPriceListN.add(pcmPrice);
			// pcmPriceChangeDto.setShoppeProSid(pcmPrice.getShoppeProSid());
			throw new BleException(ErrorCode.PRICE_SHOPPEPRO_NOT_EXIST.getErrorCode(),
					ErrorCode.PRICE_SHOPPEPRO_NOT_EXIST.getMemo());

		}
		pcmPriceChangeDto.setPcmPriceListN(pcmPriceListN);

		return pcmPriceChangeDto;
	}

	/**
	 * 获取pcmprice实体对象
	 *
	 * @param pcmPriceDto
	 * @return PcmPrice
	 * @Methods Name getPcmPriceEntityV2
	 * @Create In 2015年8月18日 By kongqf
	 */
	public PcmPrice getPcmPriceEntityV2(PcmPriceERPDto pcmPriceDto) {
		PcmPrice pcmPrice = new PcmPrice();
		pcmPrice.setShoppeProSid(pcmPriceDto.getSupplierprodcode());
		// 渠道
		if (StringUtils.isNotBlank(pcmPriceDto.getChannelsid())) {
			pcmPrice.setChannelSid(pcmPriceDto.getChannelsid());
		} else {
			pcmPrice.setChannelSid(Constants.DEFAULT_CHANNEL_SID);
		}
		if (StringUtils.isNotBlank(pcmPriceDto.getMatnr())) {
			pcmPrice.setProductSid(new Long(pcmPriceDto.getMatnr()));// 商品SID
		}
		pcmPrice.setCurrentPrice(new BigDecimal(pcmPriceDto.getZsprice()));
		pcmPrice.setPromotionPrice(pcmPrice.getCurrentPrice()); // 促销价格
		pcmPrice.setOriginalPrice(pcmPrice.getCurrentPrice());// 原价
		pcmPrice.setOffValue(new BigDecimal(1));
		pcmPrice.setPromotionBeginTime(DateUtil.formatDate(pcmPriceDto.getBdate(), "yyyyMMdd"));// 促销开始时间
		pcmPrice.setPromotionEndTime(DateUtil.formatDate(pcmPriceDto.getEdate(), "yyyyMMdd"));// 促销结束时间
		pcmPrice.setAttribute1(pcmPriceDto.getChangecode());// 变价编号
		pcmPrice.setUnit(pcmPriceDto.getWaers());// 货币单位
		pcmPrice.setPriceType(Constants.PRICE_TYPE_2);
		if (Constants.PRICE_RETAIL_DATE.equals(pcmPriceDto.getEdate())) {
			pcmPrice.setPriceType(Constants.PRICE_TYPE_1);
		}

		return pcmPrice;
	}

	/**
	 * 批量变价
	 *
	 * @param pcmPrice
	 * @param salePriceType
	 * @param salePriceValue
	 * @param upperLimit
	 * @param lowerLimit
	 * @return PcmPriceChangeDto
	 * @Methods Name getBatchChangePcmPriceERPDtoList
	 * @Create In 2015年11月5日 By kongqf
	 */
	private PcmPriceChangeDto getBatchChangePcmPriceERPDtoList(PcmPrice pcmPrice,
			String salePriceType, BigDecimal salePriceValue, BigDecimal upperLimit,
			BigDecimal lowerLimit) {
		List<PcmPrice> pcmPriceListN = new ArrayList<PcmPrice>();
		PcmPriceChangeDto pcmPriceChangeDto = new PcmPriceChangeDto();
		PcmPrice pcmPriceB = new PcmPrice();
		PcmPrice pcmPriceE = new PcmPrice();

		Date beginDate = pcmPrice.getPromotionBeginTime();
		Date endDate = pcmPrice.getPromotionEndTime();

		QueryPriceInfoDto queryPriceInfoDto = new QueryPriceInfoDto();
		queryPriceInfoDto = getQueryPriceInfoDto(pcmPrice);
		pcmPriceB = pcmPriceMapper.selectActivePriceInfo(queryPriceInfoDto);
		queryPriceInfoDto.setPromotionBeginTime(endDate);
		pcmPriceE = pcmPriceMapper.selectActivePriceInfo(queryPriceInfoDto);
		Integer PriceType = pcmPrice.getPriceType();
		String ChangeCode = pcmPrice.getAttribute1();
		PcmPrice pcmPriceNew = new PcmPrice();
		int result = 0;

		// BigDecimal upperLimit = new BigDecimal("0");
		// BigDecimal lowerLimit = "";

		if (pcmPriceB != null && pcmPriceE != null) {
			pcmPriceNew = getPcmPriceNew(pcmPriceB);

			// [start]只有一条变价记录
			if (pcmPriceB.getSid().equals(pcmPriceE.getSid())) {
				result = compareDate(beginDate, pcmPriceB.getPromotionBeginTime());
				// [start]result == 1
				if (result == 1) {
					if (pcmPriceB.getPriceType() == 1) {
						pcmPriceNew.setPromotionEndTime(addDate(beginDate, Calendar.SECOND, -1));
						pcmPriceListN.add(pcmPriceNew);

						PcmPrice pcmPriceNew2 = getPcmPriceNew(pcmPriceB);
						pcmPriceNew2.setPromotionBeginTime(beginDate);
						pcmPriceNew2.setPromotionEndTime(endDate);
						pcmPriceNew2.setPriceType(PriceType);
						pcmPriceNew2.setAttribute1(ChangeCode);

						if (PriceType == 2) {
							pcmPriceNew2.setPromotionEndTime(
									addDate(endDate, Calendar.HOUR, 24, Calendar.SECOND, -1));
							result = compareDate(pcmPriceNew.getPromotionEndTime(),
									pcmPriceB.getPromotionEndTime());
							if (result == -1) {
								PcmPrice pcmPriceNew3 = getPcmPriceNew(pcmPriceB);
								pcmPriceNew3
										.setPromotionBeginTime(addDate(endDate, Calendar.HOUR, 24));
								pcmPriceListN.add(pcmPriceNew3);
							}
						}

						pcmPriceListN.add(pcmPriceNew2);
					}
					if (pcmPriceB.getPriceType() == 2) {
						if (PriceType == 1) {
							pcmPriceListN.add(pcmPriceNew);

							PcmPrice pcmPriceNew2 = getPcmPriceNew(pcmPriceB);
							pcmPriceNew2.setPromotionBeginTime(addDate(
									pcmPriceB.getPromotionEndTime(), Calendar.MILLISECOND, 1000));
							pcmPriceNew2.setPromotionEndTime(endDate);
							pcmPriceNew2.setPriceType(PriceType);
							pcmPriceNew2.setAttribute1(ChangeCode);
							pcmPriceListN.add(pcmPriceNew2);
						}
						if (PriceType == 2) {
							pcmPriceNew
									.setPromotionEndTime(addDate(beginDate, Calendar.SECOND, -1));
							pcmPriceListN.add(pcmPriceNew);

							PcmPrice pcmPriceNew2 = getPcmPriceNew(pcmPriceB);
							pcmPriceNew2.setPromotionBeginTime(beginDate);
							pcmPriceNew2.setPromotionEndTime(
									addDate(endDate, Calendar.HOUR, 24, Calendar.SECOND, -1));
							pcmPriceNew2.setPriceType(PriceType);
							pcmPriceNew2.setAttribute1(ChangeCode);
							pcmPriceListN.add(pcmPriceNew2);

							result = compareDate(pcmPriceNew2.getPromotionEndTime(),
									pcmPriceB.getPromotionEndTime());
							if (result == -1) {
								PcmPrice pcmPriceNew3 = getPcmPriceNew(pcmPriceB);
								pcmPriceNew3
										.setPromotionBeginTime(addDate(endDate, Calendar.HOUR, 24));
								pcmPriceNew3.setPriceType(PriceType);
								pcmPriceListN.add(pcmPriceNew3);
							}
						}
					}
				}
				// [end]
				// [start] result ==0
				else if (result == 0) {
					if (pcmPriceB.getPriceType() == 1) {
						pcmPriceNew.setPromotionEndTime(endDate);
						pcmPriceNew.setAttribute1(ChangeCode);
						pcmPriceNew.setPriceType(PriceType);
						if (PriceType == 2) {
							pcmPriceNew.setPromotionEndTime(
									addDate(endDate, Calendar.HOUR, 24, Calendar.SECOND, -1));

							result = compareDate(pcmPriceNew.getPromotionEndTime(),
									pcmPriceB.getPromotionEndTime());
							if (result == -1) {
								PcmPrice pcmPriceNew2 = getPcmPriceNew(pcmPriceB);
								pcmPriceNew2
										.setPromotionBeginTime(addDate(endDate, Calendar.HOUR, 24));
								pcmPriceListN.add(pcmPriceNew2);
							}
						}
						pcmPriceListN.add(pcmPriceNew);
					}
					if (pcmPriceB.getPriceType() == 2) {
						if (PriceType == 1) {
							pcmPriceListN.add(pcmPriceNew);

							PcmPrice pcmPriceNew2 = getPcmPriceNew(pcmPriceB);
							pcmPriceNew2.setPromotionBeginTime(addDate(
									pcmPriceB.getPromotionEndTime(), Calendar.MILLISECOND, 1000));
							pcmPriceNew2.setPromotionEndTime(endDate);
							pcmPriceNew2.setPriceType(PriceType);
							pcmPriceListN.add(pcmPriceNew2);
						}
						if (PriceType == 2) {
							pcmPriceNew.setPromotionEndTime(
									addDate(endDate, Calendar.HOUR, 24, Calendar.SECOND, -1));
							pcmPriceNew.setPriceType(PriceType);
							pcmPriceNew.setAttribute1(ChangeCode);
							pcmPriceListN.add(pcmPriceNew);

							result = compareDate(pcmPriceNew.getPromotionEndTime(),
									pcmPriceB.getPromotionEndTime());
							if (result != 0) {
								PcmPrice pcmPriceNew2 = getPcmPriceNew(pcmPriceB);
								pcmPriceNew2
										.setPromotionBeginTime(addDate(endDate, Calendar.HOUR, 24));
								pcmPriceNew2.setPriceType(PriceType);
								pcmPriceListN.add(pcmPriceNew2);
							}
						}
					}
				}
				// [end]
			}
			// [end]
			// [start]
			else {
				// [start]PriceType:2
				if (PriceType == 2) {
					result = compareDate(beginDate, pcmPriceB.getPromotionBeginTime());
					if (result == 0) {
						pcmPriceNew.setAttribute1(ChangeCode);
						if (pcmPriceB.getPriceType() == 1) {
							pcmPriceNew.setPriceType(PriceType);
						}
						pcmPriceListN.add(pcmPriceNew);
					}
					if (result == 1) {
						pcmPriceNew.setPromotionEndTime(addDate(beginDate, Calendar.SECOND, -1));
						pcmPriceListN.add(pcmPriceNew);

						PcmPrice pcmPriceNew2 = getPcmPriceNew(pcmPriceB);
						pcmPriceNew2.setPromotionBeginTime(beginDate);
						pcmPriceNew2.setPriceType(PriceType);
						pcmPriceNew2.setAttribute1(ChangeCode);
						pcmPriceListN.add(pcmPriceNew2);
					}
					result = compareDate(addDate(endDate, Calendar.HOUR, 24, Calendar.SECOND, -1),
							pcmPriceE.getPromotionEndTime());
					PcmPrice pcmPriceNew3 = getPcmPriceNew(pcmPriceE);
					if (result == -1) {
						pcmPriceNew3.setPromotionEndTime(
								addDate(endDate, Calendar.HOUR, 24, Calendar.SECOND, -1));
						pcmPriceNew3.setPriceType(PriceType);
						pcmPriceNew3.setAttribute1(ChangeCode);
						pcmPriceListN.add(pcmPriceNew3);

						PcmPrice pcmPriceNew4 = getPcmPriceNew(pcmPriceE);
						pcmPriceNew4.setPromotionBeginTime(addDate(endDate, Calendar.HOUR, 24));
						pcmPriceListN.add(pcmPriceNew4);

					} else if (result == 0) {
						pcmPriceNew3.setAttribute1(ChangeCode);
						pcmPriceNew3.setPriceType(PriceType);
						pcmPriceListN.add(pcmPriceNew3);
					}

					queryPriceInfoDto.setPromotionBeginTime(pcmPriceB.getPromotionEndTime());
					queryPriceInfoDto.setPromotionEndTime(pcmPriceE.getPromotionBeginTime());
					List<PcmPrice> pcmPrices = new ArrayList<PcmPrice>();
					pcmPrices = pcmPriceMapper.selectMiddleActivePriceInfo(queryPriceInfoDto);
					if (pcmPrices != null && pcmPrices.size() > 0) {
						for (PcmPrice pp : pcmPrices) {
							PcmPrice pcmPriceNew5 = getPcmPriceNew(pp);
							pcmPriceNew5.setAttribute1(ChangeCode);
							pcmPriceListN.add(pcmPriceNew5);
						}
					}
				}
				// [end]
				// [start]pcmPriceB:2;pcmPriceE:1;PriceType:1
				else if (pcmPriceB.getPriceType() == 2 && PriceType == 1) {
					pcmPriceListN.add(pcmPriceNew);

					PcmPrice pcmPriceNew2 = getPcmPriceNew(pcmPriceE);
					pcmPriceNew2.setAttribute1(ChangeCode);
					pcmPriceListN.add(pcmPriceNew2);

					result = compareDate(
							addDate(pcmPriceB.getPromotionEndTime(), Calendar.MILLISECOND, 1000),
							pcmPriceE.getPromotionBeginTime());

					if (result != 0) {
						queryPriceInfoDto.setPromotionBeginTime(pcmPriceB.getPromotionEndTime());
						queryPriceInfoDto.setPromotionEndTime(pcmPriceE.getPromotionBeginTime());
						List<PcmPrice> pcmPrices = new ArrayList<PcmPrice>();
						pcmPrices = pcmPriceMapper.selectMiddleActivePriceInfo(queryPriceInfoDto);
						if (pcmPrices != null && pcmPrices.size() > 0) {
							for (PcmPrice pp : pcmPrices) {
								PcmPrice pcmPriceNew4 = getPcmPriceNew(pp);
								if (2 == pp.getPriceType()) {
									pcmPriceListN.add(pcmPriceNew4);
								}
								if (1 == pp.getPriceType()) {
									pcmPriceNew4.setAttribute1(ChangeCode);
									pcmPriceListN.add(pcmPriceNew4);
								}
							}
						}
					}
				}
				// [end]
				// [start] pcmPriceB:1;pcmPriceE:1;PriceType:1
				else if (pcmPriceB.getPriceType() == 1 && PriceType == 1) {
					result = compareDate(beginDate, pcmPriceB.getPromotionBeginTime());
					if (result == 1) {
						pcmPriceNew.setPromotionEndTime(addDate(beginDate, Calendar.SECOND, -1));
						pcmPriceListN.add(pcmPriceNew);

						PcmPrice pcmPriceNew2 = getPcmPriceNew(pcmPriceB);
						pcmPriceNew2.setPromotionBeginTime(beginDate);
						pcmPriceNew2.setAttribute1(ChangeCode);
						pcmPriceListN.add(pcmPriceNew2);
					} else if (result == 0) {
						pcmPriceNew.setAttribute1(ChangeCode);
						pcmPriceListN.add(pcmPriceNew);
					}

					PcmPrice pcmPriceNew3 = getPcmPriceNew(pcmPriceE);
					pcmPriceNew3.setAttribute1(ChangeCode);
					pcmPriceListN.add(pcmPriceNew3);

					result = compareDate(
							addDate(pcmPriceB.getPromotionEndTime(), Calendar.MILLISECOND, 1000),
							pcmPriceE.getPromotionBeginTime());

					if (result != 0) {
						queryPriceInfoDto.setPromotionBeginTime(pcmPriceB.getPromotionEndTime());
						queryPriceInfoDto.setPromotionEndTime(pcmPriceE.getPromotionBeginTime());
						List<PcmPrice> pcmPrices = new ArrayList<PcmPrice>();
						pcmPrices = pcmPriceMapper.selectMiddleActivePriceInfo(queryPriceInfoDto);
						if (pcmPrices != null && pcmPrices.size() > 0) {
							for (PcmPrice pp : pcmPrices) {
								PcmPrice pcmPriceNew4 = getPcmPriceNew(pp);
								if (2 == pp.getPriceType()) {
									pcmPriceListN.add(pcmPriceNew4);
								}
								if (1 == pp.getPriceType()) {
									pcmPriceNew4.setPriceType(PriceType);
									pcmPriceNew4.setAttribute1(ChangeCode);
									pcmPriceListN.add(pcmPriceNew4);
								}
							}
						}
					}
				}
				// [end]
			} // [end]
			pcmPriceChangeDto.setShoppeProSid(pcmPriceB.getShoppeProSid());
			pcmPriceChangeDto.setPromotionBeginTime(pcmPriceB.getPromotionBeginTime());
			pcmPriceChangeDto.setPromotionEndTime(pcmPriceB.getPromotionEndTime());
			if (pcmPriceE != null) {
				pcmPriceChangeDto.setPromotionEndTime(pcmPriceE.getPromotionEndTime());
			}
			if (pcmPriceListN != null && pcmPriceListN.size() > 0) {
				BigDecimal currPrice = pcmPrice.getCurrentPrice();
				// BigDecimal originalPrice = currPrice;
				// BigDecimal PromotionPrice = currPrice;
				boolean isActice = false;

				if (Constants.PRICE_CHANGE_TYPE1.equals(salePriceType)) {
					for (PcmPrice pp : pcmPriceListN) {
						if (ChangeCode.equals(pp.getAttribute1())) {
							currPrice = pp.getCurrentPrice().subtract(salePriceValue);
							if (checkIsActivePrice(currPrice, pp.getOriginalPrice(), upperLimit,
									lowerLimit)) {
								pp.setCurrentPrice(currPrice);
								pp.setOriginalPrice(currPrice);
								pp.setPromotionPrice(currPrice);
								pp.setSystemTime(null);
							} else {
								isActice = true;
								break;
							}
						}
					}
				} else if (Constants.PRICE_CHANGE_TYPE2.equals(salePriceType)) {
					salePriceValue = salePriceValue.divide(new BigDecimal("100"), 2,
							BigDecimal.ROUND_DOWN);
					for (PcmPrice pp : pcmPriceListN) {
						if (ChangeCode.equals(pp.getAttribute1())) {
							currPrice = pp.getCurrentPrice()
									.subtract(pp.getCurrentPrice().multiply(salePriceValue));
							if (checkIsActivePrice(currPrice, pp.getOriginalPrice(), upperLimit,
									lowerLimit)) {
								pp.setCurrentPrice(currPrice);
								pp.setOriginalPrice(currPrice);
								pp.setPromotionPrice(currPrice);
								pp.setSystemTime(null);
							} else {
								isActice = true;
								break;
							}
						}
					}
				} else if (Constants.PRICE_CHANGE_TYPE0.equals(salePriceType)) {
					for (PcmPrice pp : pcmPriceListN) {
						if (ChangeCode.equals(pp.getAttribute1())) {
							if (checkIsActivePrice(currPrice, pp.getOriginalPrice(), upperLimit,
									lowerLimit)) {
								pp.setCurrentPrice(currPrice);
								pp.setOriginalPrice(currPrice);
								pp.setPromotionPrice(currPrice);
								pp.setSystemTime(null);
							} else {
								isActice = true;
								break;
							}
						}
					}
				}
				if (isActice) {
					throw new BleException(ErrorCode.PRICE_THRESHOLD_ERROR.getErrorCode(),
							ErrorCode.PRICE_THRESHOLD_ERROR.getMemo());
				}
			}
		} else if (pcmPriceB == null) {
			// pcmPriceListN.add(pcmPrice);
			// pcmPriceChangeDto.setShoppeProSid(pcmPrice.getShoppeProSid());
			throw new BleException(ErrorCode.PRICE_SHOPPEPRO_NOT_EXIST.getErrorCode(),
					ErrorCode.PRICE_SHOPPEPRO_NOT_EXIST.getMemo());

		}
		pcmPriceChangeDto.setPcmPriceListN(pcmPriceListN);

		return pcmPriceChangeDto;
	}

	// [end] 单一变价按天拆分

	// [start]价格按时分秒进行拆分

	/**
	 * 按时分秒进行拆分
	 *
	 * @param pcmPrice
	 * @param upperLimit
	 * @param lowerLimit
	 * @return PcmPriceChangeDto
	 * @Methods Name getChangePcmPriceERPDtoListV2
	 * @Create In 2015年11月4日 By kongqf
	 */
	public PcmPriceChangeDto getChangePcmPriceERPDtoListV2(PcmPrice pcmPrice, BigDecimal upperLimit,
			BigDecimal lowerLimit) {
		List<PcmPrice> pcmPriceListN = new ArrayList<PcmPrice>();
		PcmPriceChangeDto pcmPriceChangeDto = new PcmPriceChangeDto();
		PcmPrice pcmPriceB = new PcmPrice();
		PcmPrice pcmPriceE = new PcmPrice();

		Date beginDate = pcmPrice.getPromotionBeginTime();
		Date endDate = pcmPrice.getPromotionEndTime();

		QueryPriceInfoDto queryPriceInfoDto = new QueryPriceInfoDto();
		queryPriceInfoDto = getQueryPriceInfoDto(pcmPrice);
		pcmPriceB = pcmPriceMapper.selectActivePriceInfo(queryPriceInfoDto);
		queryPriceInfoDto.setPromotionBeginTime(endDate);
		pcmPriceE = pcmPriceMapper.selectActivePriceInfo(queryPriceInfoDto);
		BigDecimal currPrice = pcmPrice.getCurrentPrice();
		Integer PriceType = pcmPrice.getPriceType();
		String ChangeCode = pcmPrice.getAttribute1();
		PcmPrice pcmPriceNew = new PcmPrice();
		int result = 0;

		if (pcmPriceB != null && pcmPriceE != null) {
			pcmPriceNew = getPcmPriceNew(pcmPriceB);

			if (pcmPriceB.getSid().equals(pcmPriceE.getSid())) {
				result = compareDate(beginDate, pcmPriceB.getPromotionBeginTime());
				// [start] result=1
				if (result == 1) {
					pcmPriceNew
							.setPromotionEndTime(addDate(beginDate, Calendar.MILLISECOND, -1000));
					pcmPriceListN.add(pcmPriceNew);

					PcmPrice pcmPriceNew2 = getPcmPriceNew(pcmPriceB);
					pcmPriceNew2.setPromotionBeginTime(beginDate);
					pcmPriceNew2.setPromotionEndTime(endDate);
					pcmPriceNew2.setPriceType(PriceType);
					pcmPriceNew2.setAttribute1(ChangeCode);

					result = compareDate(pcmPriceNew2.getPromotionEndTime(),
							pcmPriceB.getPromotionEndTime());
					if (result == -1) {
						PcmPrice pcmPriceNew3 = getPcmPriceNew(pcmPriceB);
						pcmPriceNew3.setPromotionBeginTime(
								addDate(endDate, Calendar.MILLISECOND, 1000));
						pcmPriceListN.add(pcmPriceNew3);
					}

					pcmPriceListN.add(pcmPriceNew2);
				}
				// [end]
				// [start] result=0
				else if (result == 0) {
					pcmPriceNew.setPromotionEndTime(endDate);
					pcmPriceNew.setAttribute1(ChangeCode);
					pcmPriceNew.setPriceType(PriceType);
					if (PriceType == 2) {
						result = compareDate(pcmPriceNew.getPromotionEndTime(),
								pcmPriceB.getPromotionEndTime());
						if (result == -1) {
							PcmPrice pcmPriceNew2 = getPcmPriceNew(pcmPriceB);
							pcmPriceNew2.setPromotionBeginTime(
									addDate(endDate, Calendar.MILLISECOND, 1000));
							pcmPriceListN.add(pcmPriceNew2);
						}
					}
					pcmPriceListN.add(pcmPriceNew);
				} // [end]
			} else {
				// [start] PriceType:2
				if (PriceType == 2) {
					result = compareDate(beginDate, pcmPriceB.getPromotionBeginTime());
					if (result == 0) {
						pcmPriceNew.setPromotionEndTime(endDate);
						pcmPriceNew.setPriceType(PriceType);
						pcmPriceNew.setAttribute1(ChangeCode);
						pcmPriceListN.add(pcmPriceNew);

						result = compareDate(pcmPriceNew.getPromotionEndTime(),
								pcmPriceE.getPromotionEndTime());
						if (result == -1) {
							PcmPrice pcmPriceNew2 = getPcmPriceNew(pcmPriceE);
							pcmPriceNew2.setPromotionBeginTime(
									addDate(endDate, Calendar.MILLISECOND, 1000));
							pcmPriceNew2.setPriceType(PriceType);
							pcmPriceListN.add(pcmPriceNew2);
						}
					} else if (result == 1) {
						pcmPriceNew.setPromotionEndTime(
								addDate(beginDate, Calendar.MILLISECOND, -1000));
						pcmPriceListN.add(pcmPriceNew);

						PcmPrice pcmPriceNew2 = getPcmPriceNew(pcmPriceB);
						pcmPriceNew2.setPromotionBeginTime(beginDate);
						pcmPriceNew2.setPromotionEndTime(endDate);
						pcmPriceNew2.setPriceType(PriceType);
						pcmPriceNew2.setAttribute1(ChangeCode);
						pcmPriceListN.add(pcmPriceNew2);

						result = compareDate(pcmPriceNew2.getPromotionEndTime(),
								pcmPriceE.getPromotionEndTime());
						if (result == -1) {
							PcmPrice pcmPriceNew3 = getPcmPriceNew(pcmPriceE);
							pcmPriceNew3.setPromotionBeginTime(
									addDate(endDate, Calendar.MILLISECOND, 1000));
							pcmPriceListN.add(pcmPriceNew3);
						}
					}
				}
				// [end]
				// [start] pcmPriceB:2;PriceType:1
				else if (pcmPriceB.getPriceType() == 2 && PriceType == 1) {
					pcmPriceListN.add(pcmPriceNew);

					PcmPrice pcmPriceNew2 = getPcmPriceNew(pcmPriceE);
					pcmPriceNew2.setAttribute1(ChangeCode);
					pcmPriceListN.add(pcmPriceNew2);

					result = compareDate(
							addDate(pcmPriceB.getPromotionEndTime(), Calendar.MILLISECOND, 1000),
							pcmPriceE.getPromotionBeginTime());

					if (result != 0) {
						queryPriceInfoDto.setPromotionBeginTime(pcmPriceB.getPromotionEndTime());
						queryPriceInfoDto.setPromotionEndTime(pcmPriceE.getPromotionBeginTime());
						List<PcmPrice> pcmPrices = new ArrayList<PcmPrice>();
						pcmPrices = pcmPriceMapper.selectMiddleActivePriceInfo(queryPriceInfoDto);
						if (pcmPrices != null && pcmPrices.size() > 0) {
							for (PcmPrice pp : pcmPrices) {
								PcmPrice pcmPriceNew4 = getPcmPriceNew(pp);
								if (2 == pp.getPriceType()) {
									pcmPriceListN.add(pcmPriceNew4);
								} else if (1 == pp.getPriceType()) {
									pcmPriceNew4.setPriceType(PriceType);
									pcmPriceNew4.setAttribute1(ChangeCode);
									pcmPriceListN.add(pcmPriceNew4);
								}
							}
						}
					}
				}
				// [end]
				// [start] pcmPriceB:1;PriceType:1
				else if (pcmPriceB.getPriceType() == 1 && PriceType == 1) {
					result = compareDate(beginDate, pcmPriceB.getPromotionBeginTime());
					if (result == 1) {
						pcmPriceNew.setPromotionEndTime(
								addDate(beginDate, Calendar.MILLISECOND, -1000));
						pcmPriceListN.add(pcmPriceNew);

						PcmPrice pcmPriceNew2 = getPcmPriceNew(pcmPriceB);
						pcmPriceNew2.setPromotionBeginTime(beginDate);
						pcmPriceNew2.setAttribute1(ChangeCode);
						pcmPriceListN.add(pcmPriceNew2);

						PcmPrice pcmPriceNew3 = getPcmPriceNew(pcmPriceE);
						pcmPriceNew3.setAttribute1(ChangeCode);
						pcmPriceListN.add(pcmPriceNew3);

					} else if (result == 0) {
						pcmPriceNew.setAttribute1(ChangeCode);
						pcmPriceListN.add(pcmPriceNew);

						PcmPrice pcmPriceNew2 = getPcmPriceNew(pcmPriceE);
						pcmPriceNew2.setPriceType(PriceType);
						pcmPriceNew2.setAttribute1(ChangeCode);
						pcmPriceListN.add(pcmPriceNew2);
					}

					result = compareDate(
							addDate(pcmPriceB.getPromotionEndTime(), Calendar.MILLISECOND, 1000),
							pcmPriceE.getPromotionBeginTime());

					if (result != 0) {
						queryPriceInfoDto.setPromotionBeginTime(pcmPriceB.getPromotionEndTime());
						queryPriceInfoDto.setPromotionEndTime(pcmPriceE.getPromotionBeginTime());
						List<PcmPrice> pcmPrices = new ArrayList<PcmPrice>();
						pcmPrices = pcmPriceMapper.selectMiddleActivePriceInfo(queryPriceInfoDto);
						if (pcmPrices != null && pcmPrices.size() > 0) {
							for (PcmPrice pp : pcmPrices) {
								PcmPrice pcmPriceNew4 = getPcmPriceNew(pp);
								if (2 == pp.getPriceType()) {
									pcmPriceListN.add(pcmPriceNew4);
								} else if (1 == pp.getPriceType()) {
									pcmPriceNew4.setPriceType(PriceType);
									pcmPriceNew4.setAttribute1(ChangeCode);
									pcmPriceListN.add(pcmPriceNew4);
								}
							}
						}
					}
				}
			}
			// [end]
			if (pcmPriceListN != null && pcmPriceListN.size() > 0) {
				boolean isActice = false;
				for (PcmPrice pp : pcmPriceListN) {
					if (ChangeCode.equals(pp.getAttribute1())) {
						if (checkIsActivePrice(currPrice, pp.getOriginalPrice(), upperLimit,
								lowerLimit)) {
							// pp.setOriginalPrice(currPrice);
							if (PriceType == 1) {
								pp.setCurrentPrice(currPrice);
							}
							pp.setPromotionPrice(currPrice);
							pp.setSystemTime(null);
						} else {
							isActice = true;
							break;
						}
					}
				}
				if (isActice) {
					throw new BleException(ErrorCode.PRICE_THRESHOLD_ERROR.getErrorCode(),
							ErrorCode.PRICE_THRESHOLD_ERROR.getMemo());
				}
			}
			pcmPriceChangeDto.setShoppeProSid(pcmPriceB.getShoppeProSid());
			pcmPriceChangeDto.setPromotionBeginTime(pcmPriceB.getPromotionBeginTime());
			pcmPriceChangeDto.setPromotionEndTime(pcmPriceB.getPromotionEndTime());
			if (pcmPriceE != null) {
				pcmPriceChangeDto.setPromotionEndTime(pcmPriceE.getPromotionEndTime());
			}
		} else if (pcmPriceB == null) {
			// pcmPriceListN.add(pcmPrice);
			// pcmPriceChangeDto.setShoppeProSid(pcmPrice.getShoppeProSid());
			throw new BleException(ErrorCode.PRICE_SHOPPEPRO_NOT_EXIST.getErrorCode(),
					ErrorCode.PRICE_SHOPPEPRO_NOT_EXIST.getMemo());

		}
		pcmPriceChangeDto.setPcmPriceListN(pcmPriceListN);

		return pcmPriceChangeDto;
	}

	/**
	 * 获取pcmprice实体对象 (时分秒)
	 *
	 * @param pcmPriceDto
	 * @return PcmPrice
	 * @Methods Name getPcmPriceEntityV3
	 * @Create In 2015年11月4日 By kongqf
	 */
	public PcmPrice getPcmPriceEntityV3(PcmPriceERPDto pcmPriceDto, String fromSystem) {
		PcmPrice pcmPrice = new PcmPrice();
		pcmPrice.setShoppeProSid(pcmPriceDto.getSupplierprodcode());
		if (Constants.EFUTUREERP.equals(fromSystem)) {
			if (StringUtils.isBlank(pcmPriceDto.getSupplierprodcode())) {
				pcmPrice.setShoppeProSid(pcmPriceDto.getMatnr());
			}
		}
		// 渠道
		if (StringUtils.isNotBlank(pcmPriceDto.getChannelsid())) {
			pcmPrice.setChannelSid(pcmPriceDto.getChannelsid());
		} else {
			pcmPrice.setChannelSid(Constants.DEFAULT_CHANNEL_SID);
		}
		if (StringUtils.isNotBlank(pcmPriceDto.getMatnr())) {
			pcmPrice.setProductSid(new Long(pcmPriceDto.getMatnr()));// 商品SID
		}
		pcmPrice.setCurrentPrice(new BigDecimal(pcmPriceDto.getZsprice()));
		pcmPrice.setPromotionPrice(pcmPrice.getCurrentPrice()); // 促销价格
		pcmPrice.setOriginalPrice(pcmPrice.getCurrentPrice());// 原价
		pcmPrice.setOffValue(new BigDecimal(1));
		// 促销开始时间
		if (DateUtil.isValidDate(pcmPriceDto.getBdate(), Constants.yyyyMMddHHmmss)) {
			pcmPrice.setPromotionBeginTime(
					DateUtil.formatDate(pcmPriceDto.getBdate(), Constants.yyyyMMddHHmmss));
		} else if (DateUtil.isValidDate(pcmPriceDto.getBdate(), Constants.yyyyMMdd)) {
			pcmPrice.setPromotionBeginTime(
					DateUtil.formatDate(pcmPriceDto.getBdate(), Constants.yyyyMMdd));
		}
		// 促销结束时间
		if (DateUtil.isValidDate(pcmPriceDto.getEdate(), Constants.yyyyMMddHHmmss)) {
			pcmPrice.setPromotionEndTime(
					DateUtil.formatDate(pcmPriceDto.getEdate(), Constants.yyyyMMddHHmmss));
		} else if (DateUtil.isValidDate(pcmPriceDto.getEdate(), Constants.yyyyMMdd)) {
			pcmPrice.setPromotionEndTime(
					addDate((DateUtil.formatDate(pcmPriceDto.getEdate(), Constants.yyyyMMdd)),
							Calendar.HOUR, 24, Calendar.SECOND, -1));
		}
		pcmPrice.setAttribute1(pcmPriceDto.getChangecode());// 变价编号
		pcmPrice.setUnit(pcmPriceDto.getWaers());// 货币单位
		pcmPrice.setPriceType(Constants.PRICE_TYPE_2);
		if (Constants.PRICE_RETAIL_DATETIME.equals(pcmPriceDto.getEdate())
				|| Constants.PRICE_RETAIL_DATE.equals(pcmPriceDto.getEdate())) {
			pcmPrice.setPriceType(Constants.PRICE_TYPE_1);
		}
		pcmPrice.setAttribute2(pcmPriceDto.getStorecode());

		return pcmPrice;
	}

	/**
	 * 批量变价按时分秒拆分
	 *
	 * @param pcmPrice
	 * @param salePriceType
	 * @param salePriceValue
	 * @param upperLimit
	 * @param lowerLimit
	 * @return PcmPriceChangeDto
	 * @Methods Name getBatchChangePcmPriceERPDtoList
	 * @Create In 2015年11月5日 By kongqf
	 */
	private PcmPriceChangeDto getBatchChangePcmPriceERPDtoListV2(PcmPrice pcmPrice,
			String salePriceType, BigDecimal salePriceValue, BigDecimal upperLimit,
			BigDecimal lowerLimit) {
		List<PcmPrice> pcmPriceListN = new ArrayList<PcmPrice>();
		PcmPriceChangeDto pcmPriceChangeDto = new PcmPriceChangeDto();
		PcmPrice pcmPriceB = new PcmPrice();
		PcmPrice pcmPriceE = new PcmPrice();

		Date beginDate = pcmPrice.getPromotionBeginTime();
		Date endDate = pcmPrice.getPromotionEndTime();

		QueryPriceInfoDto queryPriceInfoDto = new QueryPriceInfoDto();
		queryPriceInfoDto = getQueryPriceInfoDto(pcmPrice);
		pcmPriceB = pcmPriceMapper.selectActivePriceInfo(queryPriceInfoDto);
		queryPriceInfoDto.setPromotionBeginTime(endDate);
		pcmPriceE = pcmPriceMapper.selectActivePriceInfo(queryPriceInfoDto);
		Integer PriceType = pcmPrice.getPriceType();
		String ChangeCode = pcmPrice.getAttribute1();
		PcmPrice pcmPriceNew = new PcmPrice();
		int result = 0;

		if (pcmPriceB != null && pcmPriceE != null) {
			pcmPriceNew = getPcmPriceNew(pcmPriceB);

			// [start]只有一条变价记录
			if (pcmPriceB.getSid().equals(pcmPriceE.getSid())) {
				result = compareDate(beginDate, pcmPriceB.getPromotionBeginTime());
				// [start]result == 1
				if (result == 1) {
					pcmPriceNew
							.setPromotionEndTime(addDate(beginDate, Calendar.MILLISECOND, -1000));
					pcmPriceListN.add(pcmPriceNew);

					PcmPrice pcmPriceNew2 = getPcmPriceNew(pcmPriceB);
					pcmPriceNew2.setPromotionBeginTime(beginDate);
					pcmPriceNew2.setPromotionEndTime(endDate);
					pcmPriceNew2.setPriceType(PriceType);
					pcmPriceNew2.setAttribute1(ChangeCode);

					result = compareDate(pcmPriceNew2.getPromotionEndTime(),
							pcmPriceB.getPromotionEndTime());
					if (result == -1) {
						PcmPrice pcmPriceNew3 = getPcmPriceNew(pcmPriceB);
						pcmPriceNew3.setPromotionBeginTime(
								addDate(endDate, Calendar.MILLISECOND, 1000));
						pcmPriceListN.add(pcmPriceNew3);
					}

					pcmPriceListN.add(pcmPriceNew2);
				}
				// [end]
				// [start] result ==0
				else if (result == 0) {
					pcmPriceNew.setPromotionEndTime(endDate);
					pcmPriceNew.setAttribute1(ChangeCode);
					pcmPriceNew.setPriceType(PriceType);
					if (PriceType == 2) {
						result = compareDate(pcmPriceNew.getPromotionEndTime(),
								pcmPriceB.getPromotionEndTime());
						if (result == -1) {
							PcmPrice pcmPriceNew2 = getPcmPriceNew(pcmPriceB);
							pcmPriceNew2.setPromotionBeginTime(
									addDate(endDate, Calendar.MILLISECOND, 1000));
							pcmPriceListN.add(pcmPriceNew2);
						}
					}
					pcmPriceListN.add(pcmPriceNew);
				}
				// [end]
			}
			// [end]
			// [start]
			else {
				// [start]PriceType:2
				if (PriceType == 2) {
					result = compareDate(beginDate, pcmPriceB.getPromotionBeginTime());
					if (result == 0) {
						pcmPriceNew.setAttribute1(ChangeCode);
						pcmPriceNew.setPriceType(PriceType);
						pcmPriceListN.add(pcmPriceNew);
					}
					if (result == 1) {
						pcmPriceNew.setPromotionEndTime(
								addDate(beginDate, Calendar.MILLISECOND, -1000));
						pcmPriceListN.add(pcmPriceNew);

						PcmPrice pcmPriceNew2 = getPcmPriceNew(pcmPriceB);
						pcmPriceNew2.setPromotionBeginTime(beginDate);
						pcmPriceNew2.setPriceType(PriceType);
						pcmPriceNew2.setAttribute1(ChangeCode);
						pcmPriceListN.add(pcmPriceNew2);
					}
					result = compareDate(endDate, pcmPriceE.getPromotionEndTime());
					PcmPrice pcmPriceNew3 = getPcmPriceNew(pcmPriceE);
					if (result == -1) {
						pcmPriceNew3.setPromotionEndTime(endDate);
						pcmPriceNew3.setPriceType(PriceType);
						pcmPriceNew3.setAttribute1(ChangeCode);
						pcmPriceListN.add(pcmPriceNew3);

						PcmPrice pcmPriceNew4 = getPcmPriceNew(pcmPriceE);
						pcmPriceNew4.setPromotionBeginTime(
								addDate(endDate, Calendar.MILLISECOND, 1000));
						pcmPriceListN.add(pcmPriceNew4);

					} else if (result == 0) {
						pcmPriceNew3.setAttribute1(ChangeCode);
						pcmPriceNew3.setPriceType(PriceType);
						pcmPriceListN.add(pcmPriceNew3);
					}

					queryPriceInfoDto.setPromotionBeginTime(pcmPriceB.getPromotionEndTime());
					queryPriceInfoDto.setPromotionEndTime(pcmPriceE.getPromotionBeginTime());
					List<PcmPrice> pcmPrices = new ArrayList<PcmPrice>();
					pcmPrices = pcmPriceMapper.selectMiddleActivePriceInfo(queryPriceInfoDto);
					if (pcmPrices != null && pcmPrices.size() > 0) {
						for (PcmPrice pp : pcmPrices) {
							PcmPrice pcmPriceNew5 = getPcmPriceNew(pp);
							pcmPriceNew5.setAttribute1(ChangeCode);
							pcmPriceListN.add(pcmPriceNew5);
						}
					}
				}
				// [end]
				// [start]pcmPriceB:2;pcmPriceE:1;PriceType:1
				else if (pcmPriceB.getPriceType() == 2 && PriceType == 1) {
					pcmPriceListN.add(pcmPriceNew);

					PcmPrice pcmPriceNew2 = getPcmPriceNew(pcmPriceE);
					pcmPriceNew2.setAttribute1(ChangeCode);
					pcmPriceListN.add(pcmPriceNew2);

					result = compareDate(
							addDate(pcmPriceB.getPromotionEndTime(), Calendar.MILLISECOND, 1000),
							pcmPriceE.getPromotionBeginTime());

					if (result != 0) {
						queryPriceInfoDto.setPromotionBeginTime(pcmPriceB.getPromotionEndTime());
						queryPriceInfoDto.setPromotionEndTime(pcmPriceE.getPromotionBeginTime());
						List<PcmPrice> pcmPrices = new ArrayList<PcmPrice>();
						pcmPrices = pcmPriceMapper.selectMiddleActivePriceInfo(queryPriceInfoDto);
						if (pcmPrices != null && pcmPrices.size() > 0) {
							for (PcmPrice pp : pcmPrices) {
								PcmPrice pcmPriceNew4 = getPcmPriceNew(pp);
								if (2 == pp.getPriceType()) {
									pcmPriceListN.add(pcmPriceNew4);
								}
								if (1 == pp.getPriceType()) {
									pcmPriceNew4.setAttribute1(ChangeCode);
									pcmPriceListN.add(pcmPriceNew4);
								}
							}
						}
					}
				}
				// [end]
				// [start] pcmPriceB:1;pcmPriceE:1;PriceType:1
				else if (pcmPriceB.getPriceType() == 1 && PriceType == 1) {
					result = compareDate(beginDate, pcmPriceB.getPromotionBeginTime());
					if (result == 1) {
						pcmPriceNew.setPromotionEndTime(
								addDate(beginDate, Calendar.MILLISECOND, -1000));
						pcmPriceListN.add(pcmPriceNew);

						PcmPrice pcmPriceNew2 = getPcmPriceNew(pcmPriceB);
						pcmPriceNew2.setPromotionBeginTime(beginDate);
						pcmPriceNew2.setAttribute1(ChangeCode);
						pcmPriceListN.add(pcmPriceNew2);
					} else if (result == 0) {
						pcmPriceNew.setAttribute1(ChangeCode);
						pcmPriceListN.add(pcmPriceNew);
					}

					PcmPrice pcmPriceNew3 = getPcmPriceNew(pcmPriceE);
					pcmPriceNew3.setAttribute1(ChangeCode);
					pcmPriceListN.add(pcmPriceNew3);

					result = compareDate(
							addDate(pcmPriceB.getPromotionEndTime(), Calendar.MILLISECOND, 1000),
							pcmPriceE.getPromotionBeginTime());

					if (result != 0) {
						queryPriceInfoDto.setPromotionBeginTime(pcmPriceB.getPromotionEndTime());
						queryPriceInfoDto.setPromotionEndTime(pcmPriceE.getPromotionBeginTime());
						List<PcmPrice> pcmPrices = new ArrayList<PcmPrice>();
						pcmPrices = pcmPriceMapper.selectMiddleActivePriceInfo(queryPriceInfoDto);
						if (pcmPrices != null && pcmPrices.size() > 0) {
							for (PcmPrice pp : pcmPrices) {
								PcmPrice pcmPriceNew4 = getPcmPriceNew(pp);
								if (2 == pp.getPriceType()) {
									pcmPriceListN.add(pcmPriceNew4);
								}
								if (1 == pp.getPriceType()) {
									pcmPriceNew4.setPriceType(PriceType);
									pcmPriceNew4.setAttribute1(ChangeCode);
									pcmPriceListN.add(pcmPriceNew4);
								}
							}
						}
					}
				}
				// [end]
			} // [end]
			pcmPriceChangeDto.setShoppeProSid(pcmPriceB.getShoppeProSid());
			pcmPriceChangeDto.setPromotionBeginTime(pcmPriceB.getPromotionBeginTime());
			pcmPriceChangeDto.setPromotionEndTime(pcmPriceB.getPromotionEndTime());
			if (pcmPriceE != null) {
				pcmPriceChangeDto.setPromotionEndTime(pcmPriceE.getPromotionEndTime());
			}
			if (pcmPriceListN != null && pcmPriceListN.size() > 0) {
				BigDecimal currPrice = pcmPrice.getCurrentPrice();
				// BigDecimal originalPrice = currPrice;
				// BigDecimal PromotionPrice = currPrice;
				boolean isActice = false;

				if (Constants.PRICE_CHANGE_TYPE1.equals(salePriceType)) {
					for (PcmPrice pp : pcmPriceListN) {
						if (ChangeCode.equals(pp.getAttribute1())) {
							currPrice = pp.getCurrentPrice().subtract(salePriceValue);
							if (checkIsActivePrice(currPrice, pp.getOriginalPrice(), upperLimit,
									lowerLimit)) {
								if (PriceType == 1) {
									pp.setCurrentPrice(currPrice);
								}
								// pp.setOriginalPrice(currPrice);
								pp.setPromotionPrice(currPrice);
								pp.setSystemTime(null);
							} else {
								isActice = true;
								break;
							}
						}
					}
				} else if (Constants.PRICE_CHANGE_TYPE2.equals(salePriceType)) {
					salePriceValue = salePriceValue.divide(new BigDecimal("100"), 2,
							BigDecimal.ROUND_DOWN);
					for (PcmPrice pp : pcmPriceListN) {
						if (ChangeCode.equals(pp.getAttribute1())) {
							currPrice = pp.getCurrentPrice()
									.subtract(pp.getCurrentPrice().multiply(salePriceValue));
							if (checkIsActivePrice(currPrice, pp.getOriginalPrice(), upperLimit,
									lowerLimit)) {
								if (PriceType == 1) {
									pp.setCurrentPrice(currPrice);
								}
								// pp.setOriginalPrice(currPrice);
								pp.setPromotionPrice(currPrice);
								pp.setSystemTime(null);
							} else {
								isActice = true;
								break;
							}
						}
					}
				} else if (Constants.PRICE_CHANGE_TYPE0.equals(salePriceType)) {
					for (PcmPrice pp : pcmPriceListN) {
						if (ChangeCode.equals(pp.getAttribute1())) {
							if (checkIsActivePrice(currPrice, pp.getOriginalPrice(), upperLimit,
									lowerLimit)) {
								if (PriceType == 1) {
									pp.setCurrentPrice(currPrice);
								}
								// pp.setOriginalPrice(currPrice);
								pp.setPromotionPrice(currPrice);
								pp.setSystemTime(null);
							} else {
								isActice = true;
								break;
							}
						}
					}
				}
				if (isActice) {
					throw new BleException(ErrorCode.PRICE_THRESHOLD_ERROR.getErrorCode(),
							ErrorCode.PRICE_THRESHOLD_ERROR.getMemo());
				}
			}
		} else if (pcmPriceB == null) {
			// pcmPriceListN.add(pcmPrice);
			// pcmPriceChangeDto.setShoppeProSid(pcmPrice.getShoppeProSid());
			throw new BleException(ErrorCode.PRICE_SHOPPEPRO_NOT_EXIST.getErrorCode(),
					ErrorCode.PRICE_SHOPPEPRO_NOT_EXIST.getMemo());

		}
		pcmPriceChangeDto.setPcmPriceListN(pcmPriceListN);

		return pcmPriceChangeDto;
	}

	// [end] 价格按时分秒进行拆分

	/**
	 * @param pcmPriceDto
	 * @return PcmPrice
	 * @Methods Name getPcmPriceEntityV4
	 * @Create In 2015年8月20日 By kongqf
	 */
	public PcmPrice getPcmPriceEntityV4(PcmPricePISDto pcmPricePISDto) {
		PcmPrice pcmPrice = new PcmPrice();
		pcmPrice.setShoppeProSid(pcmPricePISDto.getSupplierprodcode());
		// 渠道
		if (StringUtils.isNotBlank(pcmPricePISDto.getChannelsid())) {
			pcmPrice.setChannelSid(pcmPricePISDto.getChannelsid());
		} else {
			pcmPrice.setChannelSid(Constants.DEFAULT_CHANNEL_SID);
		}
		pcmPrice.setPromotionBeginTime(DateUtil.formatDate(pcmPricePISDto.getBdate(), "yyyyMMdd"));// 促销开始时间
		pcmPrice.setPromotionEndTime(DateUtil.formatDate(pcmPricePISDto.getEdate(), "yyyyMMdd"));// 促销结束时间
		pcmPrice.setAttribute1(pcmPricePISDto.getChangecode());// 变价编号
		pcmPrice.setPriceType(Constants.PRICE_TYPE_2);
		if (Constants.PRICE_RETAIL_DATE.equals(pcmPricePISDto.getEdate())) {
			pcmPrice.setPriceType(Constants.PRICE_TYPE_1);
		}
		pcmPrice.setAttribute2(pcmPricePISDto.getStorecode());
		return pcmPrice;
	}

	/**
	 * 单个变价请求数据有效性验证
	 *
	 * @param pcmPriceDto
	 * @param fromSystem
	 * @return String
	 * @Methods Name validPriceRequestData
	 * @Create In 2015年8月19日 By kongqf
	 */
	public void validPriceRequestData(PcmPriceERPDto pcmPriceDto, String fromSystem) {
		if (StringUtils.isBlank(pcmPriceDto.getStorecode())) {
			throw new BleException(ErrorCode.PRICE_STORECODE_IS_NULL.getErrorCode(),
					ErrorCode.PRICE_STORECODE_IS_NULL.getMemo());
		}
		if (Constants.EFUTUREERP.equals(fromSystem)
				&& StringUtils.isBlank(pcmPriceDto.getSupplierprodcode())) {
			if (StringUtils.isBlank(pcmPriceDto.getMatnr())) {
				throw new BleException(ErrorCode.PRICE_MATNR_ERROR.getErrorCode(),
						ErrorCode.PRICE_MATNR_ERROR.getMemo());
			} else {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizationCode", pcmPriceDto.getStorecode());
				List<PcmOrganization> orgList = organizationMapper.selectListByParam(map);
				if (orgList == null || orgList.size() != 1) {
					throw new BleException(ErrorCode.SHOP_NULL.getErrorCode(),
							ErrorCode.SHOP_NULL.getMemo());
				}
			}
			// } else if (Constants.PIS.equals(fromSystem)) {
		} else {
			if (StringUtils.isBlank(pcmPriceDto.getSupplierprodcode())) {
				throw new BleException(ErrorCode.PRICE_SUPPLIERPRODCODE_IS_NULL.getErrorCode(),
						ErrorCode.PRICE_SUPPLIERPRODCODE_IS_NULL.getMemo());
			} else {
				Map<String, Object> map = new HashMap<String, Object>();
				if (StringUtils.isNotBlank(pcmPriceDto.getSitecode())) {
					map.put("shoppeCode", pcmPriceDto.getSitecode());
				}
				map.put("organizationCode", pcmPriceDto.getStorecode());
				map.put("shoppeProSid", pcmPriceDto.getSupplierprodcode());
				Map<String, Object> resultMap = pcmPriceMapper.validShoppeProInfo(map);
				if (resultMap != null) {
					/*
					 * if (resultMap.get("sale_status") != "0") { // 专柜商品不存在或不可售
					 * } else
					 */
					if (!resultMap.containsKey("shoppe_status")
							|| !resultMap.get("shoppe_status").toString().equals("1")) {
						// 专柜不存在或状态不可用
						throw new BleException(ErrorCode.SHOPPE_NULL.getErrorCode(),
								ErrorCode.SHOPPE_NULL.getMemo());
					} else if (!resultMap.containsKey("organization_status")
							|| !resultMap.get("organization_status").toString().equals("0")) {
						// 门店不存在或状态不可用
						throw new BleException(ErrorCode.SHOP_NULL.getErrorCode(),
								ErrorCode.SHOP_NULL.getMemo());
					}
				} else {
					throw new BleException(ErrorCode.STOCK_SHOPPEPROSID_IS_NOT_EXITS.getErrorCode(),
							ErrorCode.STOCK_SHOPPEPROSID_IS_NOT_EXITS.getMemo());
				}
			}
		}

		if (StringUtils.isBlank(pcmPriceDto.getZsprice())) {
			throw new BleException(ErrorCode.PRICE_ZSPRICE_ERROR.getErrorCode(),
					ErrorCode.PRICE_ZSPRICE_ERROR.getMemo());
		} else {
			if (!NumberUtils.isNumber(pcmPriceDto.getZsprice())) {
				throw new BleException(ErrorCode.PRICE_ZSPRICE_ERROR.getErrorCode(),
						ErrorCode.PRICE_ZSPRICE_ERROR.getMemo());
			}
		}
		if (StringUtils.isBlank(pcmPriceDto.getWaers())) {
			throw new BleException(ErrorCode.PRICE_WAERS_IS_NULL.getErrorCode(),
					ErrorCode.PRICE_WAERS_IS_NULL.getMemo());
		}
		if (StringUtils.isNotBlank(pcmPriceDto.getBdate())) {
			if (!DateUtil.isValidDate(pcmPriceDto.getBdate(), Constants.yyyyMMdd)
					&& !DateUtil.isValidDate(pcmPriceDto.getBdate(), Constants.yyyyMMddHHmmss)) {
				throw new BleException(ErrorCode.PRICE_BDATE_ERROR.getErrorCode(),
						ErrorCode.PRICE_BDATE_ERROR.getMemo());
			}
		} else {
			throw new BleException(ErrorCode.PRICE_BDATE_IS_NULL.getErrorCode(),
					ErrorCode.PRICE_BDATE_IS_NULL.getMemo());
		}
		if (StringUtils.isNotBlank(pcmPriceDto.getEdate())) {
			if (!DateUtil.isValidDate(pcmPriceDto.getEdate(), Constants.yyyyMMdd)
					&& !DateUtil.isValidDate(pcmPriceDto.getEdate(), Constants.yyyyMMddHHmmss)) {
				throw new BleException(ErrorCode.PRICE_EDATEE_ERROR.getErrorCode(),
						ErrorCode.PRICE_EDATEE_ERROR.getMemo());
			}
		} else {
			throw new BleException(ErrorCode.PRICE_EDATE_IS_NULL.getErrorCode(),
					ErrorCode.PRICE_EDATE_IS_NULL.getMemo());
		}
		if (StringUtils.isBlank(pcmPriceDto.getChangecode())) {
			throw new BleException(ErrorCode.PRICE_CHANGECODE_IS_NULL.getErrorCode(),
					ErrorCode.PRICE_CHANGECODE_IS_NULL.getMemo());
		}

		int result = 0;
		int sbResult = 0;
		if (DateUtil.isValidDate(pcmPriceDto.getEdate(), Constants.yyyyMMdd)) {
			result = compareDate(DateUtil.formatDate(pcmPriceDto.getBdate(), Constants.yyyyMMdd),
					DateUtil.formatDate(pcmPriceDto.getEdate(), Constants.yyyyMMdd));
			if (0 == result) {
				result = -1;
			}
			if (-1 == result) {
				sbResult = compareDate(
						DateUtil.formatDate(DateUtil.formatToStr(new Date(), Constants.yyyyMMdd),
								Constants.yyyyMMdd),
						DateUtil.formatDate(pcmPriceDto.getBdate(), Constants.yyyyMMdd));
				if (0 == sbResult) {
					sbResult = -1;
				}
			}
		} else {
			result = compareDate(
					DateUtil.formatDate(pcmPriceDto.getBdate(), Constants.yyyyMMddHHmmss),
					DateUtil.formatDate(pcmPriceDto.getEdate(), Constants.yyyyMMddHHmmss));
			if (-1 == result) {
				sbResult = compareDate(
						DateUtil.formatDate(
								DateUtil.formatToStr(new Date(), Constants.yyyyMMddHHmmss),
								Constants.yyyyMMddHHmmss),
						DateUtil.formatDate(pcmPriceDto.getBdate(), Constants.yyyyMMddHHmmss));
				if (0 == sbResult) {
					sbResult = -1;
				}
			}
		}

		if (-1 != result) {
			throw new BleException(ErrorCode.PRICE_BEGIN_END_FAILED.getErrorCode(),
					ErrorCode.PRICE_BEGIN_END_FAILED.getMemo());
		}
		if (-1 != sbResult) {
			throw new BleException(ErrorCode.PRICE_BEGIN_CURDATE_FAILED.getErrorCode(),
					ErrorCode.PRICE_BEGIN_CURDATE_FAILED.getMemo());
		}
		// return errMsg.toString();
	}

	/**
	 * 单个变价请求数据有效性验证
	 *
	 * @param pcmPriceDto
	 * @param fromSystem
	 * @return String
	 * @Methods Name validPriceRequestData
	 * @Create In 2015年8月19日 By kongqf
	 */
	public void validBatchPriceRequestData(PcmPricePISDto pcmPricePISDto) {

		if (StringUtils.isBlank(pcmPricePISDto.getStorecode())) {
			throw new BleException(ErrorCode.PRICE_STORECODE_IS_NULL.getErrorCode(),
					ErrorCode.PRICE_STORECODE_IS_NULL.getMemo());
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("organizationCode", pcmPricePISDto.getStorecode());
			List<PcmOrganization> orgList = organizationMapper.selectListByParam(map);
			if (orgList == null || orgList.size() != 1) {
				throw new BleException(ErrorCode.SHOP_NULL.getErrorCode(),
						ErrorCode.SHOP_NULL.getMemo());
			}
		}
		if (StringUtils.isBlank(pcmPricePISDto.getChangecode())) {
			throw new BleException(ErrorCode.PRICE_CHANGECODE_IS_NULL.getErrorCode(),
					ErrorCode.PRICE_CHANGECODE_IS_NULL.getMemo());
		}
		if (StringUtils.isBlank(pcmPricePISDto.getSalepricetype())) {
			throw new BleException(ErrorCode.PRICE_SALEPRICETYPE_ERROR.getErrorCode(),
					ErrorCode.PRICE_SALEPRICETYPE_ERROR.getMemo());
		} else {
			if (!Constants.PRICE_CHANGE_TYPE1.equals(pcmPricePISDto.getSalepricetype())
					&& !Constants.PRICE_CHANGE_TYPE2.equals(pcmPricePISDto.getSalepricetype())) {
				throw new BleException(ErrorCode.PRICE_SALEPRICETYPE_ERROR.getErrorCode(),
						ErrorCode.PRICE_SALEPRICETYPE_ERROR.getMemo());
			}
		}
		if (StringUtils.isBlank(pcmPricePISDto.getSalepricevalue())) {
			throw new BleException(ErrorCode.PRICE_SALEPRICEVALUE_ERROR.getErrorCode(),
					ErrorCode.PRICE_SALEPRICEVALUE_ERROR.getMemo());
		} else {
			if (!NumberUtils.isNumber(pcmPricePISDto.getSalepricevalue())) {
				throw new BleException(ErrorCode.PRICE_SALEPRICEVALUE_ERROR.getErrorCode(),
						ErrorCode.PRICE_SALEPRICEVALUE_ERROR.getMemo());
			}
		}
		if (StringUtils.isBlank(pcmPricePISDto.getSuppliercode())
				&& StringUtils.isBlank(pcmPricePISDto.getShoppecode())
				&& StringUtils.isBlank(pcmPricePISDto.getCategorycode())) {
			throw new BleException(ErrorCode.PRICE_BATCHFILTER_ERROR.getErrorCode(),
					ErrorCode.PRICE_BATCHFILTER_ERROR.getMemo());
		}
		if (StringUtils.isNotBlank(pcmPricePISDto.getBdate())) {
			if (!DateUtil.isValidDate(pcmPricePISDto.getBdate(), Constants.yyyyMMdd)
					&& !DateUtil.isValidDate(pcmPricePISDto.getBdate(), Constants.yyyyMMddHHmmss)) {
				throw new BleException(ErrorCode.PRICE_BDATE_ERROR.getErrorCode(),
						ErrorCode.PRICE_BDATE_ERROR.getMemo());
			}
		} else {
			throw new BleException(ErrorCode.PRICE_BDATE_IS_NULL.getErrorCode(),
					ErrorCode.PRICE_BDATE_IS_NULL.getMemo());
		}
		if (StringUtils.isNotBlank(pcmPricePISDto.getEdate())) {
			if (!DateUtil.isValidDate(pcmPricePISDto.getEdate(), Constants.yyyyMMdd)
					&& !DateUtil.isValidDate(pcmPricePISDto.getEdate(), Constants.yyyyMMddHHmmss)) {
				throw new BleException(ErrorCode.PRICE_EDATEE_ERROR.getErrorCode(),
						ErrorCode.PRICE_EDATEE_ERROR.getMemo());
			}
		} else {
			throw new BleException(ErrorCode.PRICE_EDATE_IS_NULL.getErrorCode(),
					ErrorCode.PRICE_EDATE_IS_NULL.getMemo());
		}

		int result = 0;
		int sbResult = 0;
		if (DateUtil.isValidDate(pcmPricePISDto.getEdate(), Constants.yyyyMMdd)) {
			result = compareDate(DateUtil.formatDate(pcmPricePISDto.getBdate(), Constants.yyyyMMdd),
					DateUtil.formatDate(pcmPricePISDto.getEdate(), Constants.yyyyMMdd));
			if (0 == result) {
				result = -1;
			}
			if (-1 == result) {
				sbResult = compareDate(
						DateUtil.formatDate(DateUtil.formatToStr(new Date(), Constants.yyyyMMdd),
								Constants.yyyyMMdd),
						DateUtil.formatDate(pcmPricePISDto.getBdate(), Constants.yyyyMMdd));
				if (0 == sbResult) {
					sbResult = -1;
				}
			}
		} else {
			result = compareDate(
					DateUtil.formatDate(pcmPricePISDto.getBdate(), Constants.yyyyMMddHHmmss),
					DateUtil.formatDate(pcmPricePISDto.getEdate(), Constants.yyyyMMddHHmmss));
			if (-1 == result) {
				sbResult = compareDate(
						DateUtil.formatDate(
								DateUtil.formatToStr(new Date(), Constants.yyyyMMddHHmmss),
								Constants.yyyyMMddHHmmss),
						DateUtil.formatDate(pcmPricePISDto.getBdate(), Constants.yyyyMMddHHmmss));
				if (0 == sbResult) {
					sbResult = -1;
				}
			}
		}

		if (-1 != result) {
			throw new BleException(ErrorCode.PRICE_BEGIN_END_FAILED.getErrorCode(),
					ErrorCode.PRICE_BEGIN_END_FAILED.getMemo());
		}
	}

	/**
	 * 复制pcmprice实体
	 *
	 * @param pcmPrice
	 * @return PcmPrice
	 * @Methods Name getPcmPriceNew
	 * @Create In 2015年8月18日 By kongqf
	 */
	public PcmPrice getPcmPriceNew(PcmPrice pcmPrice) {
		PcmPrice pcmPriceNew = new PcmPrice();
		try {
			PropertyUtils.copyProperties(pcmPriceNew, pcmPrice);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return pcmPriceNew;
	}

	/**
	 * 获取查询有效价格查询信息
	 *
	 * @param dto
	 * @param startDate
	 * @return QueryPriceInfoDto
	 * @Methods Name getQueryPriceInfoDto
	 * @Create In 2015年8月12日 By kongqf
	 */
	public QueryPriceInfoDto getQueryPriceInfoDto(PcmPrice pcmPrice) {
		QueryPriceInfoDto queryPriceInfoDto = new QueryPriceInfoDto();
		queryPriceInfoDto.setShoppeProSid(pcmPrice.getShoppeProSid().toString());
		if (StringUtils.isNotBlank(pcmPrice.getChannelSid().toString())) {
			queryPriceInfoDto.setChannelSid(pcmPrice.getChannelSid().toString());
		} else {
			queryPriceInfoDto.setChannelSid(Constants.DEFAULT_CHANNEL_SID);
		}
		queryPriceInfoDto.setPromotionBeginTime(pcmPrice.getPromotionBeginTime());
		queryPriceInfoDto.setStoreCode(pcmPrice.getAttribute2());
		return queryPriceInfoDto;
	}

	/**
	 * 判断降价之后是否在阀值之内
	 *
	 * @param originalPrice
	 * @param currentPrice
	 * @return BigDecimal
	 * @Methods Name getPriceRatio
	 * @Create In 2015年8月18日 By kongqf
	 */
	private boolean checkIsActivePrice(BigDecimal currentPrice, BigDecimal originalPrice,
			BigDecimal upperLimit, BigDecimal lowerLimit) {
		BigDecimal ratio = currentPrice.divide(originalPrice, 2, BigDecimal.ROUND_DOWN);
		if (upperLimit.compareTo(BigDecimal.ZERO) != 0) {
			if (ratio.compareTo(upperLimit) == 1) {
				return false;
			}
		}
		if (lowerLimit.compareTo(BigDecimal.ZERO) != 0) {
			if (ratio.compareTo(lowerLimit) == -1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 时间比较 1: 2015-08-08,2015-07-08 -1:2015-06-08,2015-07-08
	 *
	 * @param beignDate
	 * @param endDate
	 * @return int
	 * @Methods Name compareDate
	 * @Create In 2015年8月12日 By kongqf
	 */
	private int compareDate(Date beignDate, Date endDate) {
		try {
			if (beignDate.getTime() > endDate.getTime()) {// 2015-08-08,2015-07-08
				return 1;
			} else if (beignDate.getTime() < endDate.getTime()) {// 2015-06-08,2015-07-08
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	/**
	 * 时间加减操作
	 *
	 * @param date
	 * @param filed
	 * @param value
	 * @return Date
	 * @Methods Name addDate
	 * @Create In 2015年8月18日 By kongqf
	 */
	private Date addDate(Date date, int filed, int value) {
		java.util.Calendar now = java.util.Calendar.getInstance();
		now.setTime(date);
		now.set(filed, value);
		return now.getTime();
	}

	/**
	 * 时间加减操作
	 *
	 * @param date
	 * @param filed
	 * @param value
	 * @param filed2
	 * @param value2
	 * @return Date
	 * @Methods Name addDate
	 * @Create In 2015年8月18日 By kongqf
	 */
	private Date addDate(Date date, int filed, int value, int filed2, int value2) {
		java.util.Calendar now = java.util.Calendar.getInstance();
		now.setTime(date);
		now.set(filed, value);
		now.set(filed2, value2);
		return now.getTime();
	}

	// [start] 无效价格信息挪到历史表

	/**
	 * 查询无效价格信息
	 *
	 * @return List<PcmPrice>
	 * @Methods Name queryExpirePriceInfoList
	 * @Create In 2015年8月10日 By kongqf
	 */
	@Override
	public List<PcmPrice> queryExpirePriceInfoList() {
		logger.info("start queryExpirePriceInfoList()");
		List<PcmPrice> pcmPriceList = new ArrayList<PcmPrice>();
		pcmPriceList = pcmPriceMapper.queryExpirePriceInfoList();
		logger.info("start queryExpirePriceInfoList(),result:" + pcmPriceList.toString());
		return pcmPriceList;
	}

	/**
	 * 保存无效价格信息到价格历史表
	 *
	 * @param pcmPrice
	 * @return boolean
	 * @Methods Name saveExpirePriceToPriceHis
	 * @Create In 2015年8月10日 By kongqf
	 */
	@Override
	public boolean saveExpirePriceToPriceHis(PcmPrice pcmPrice) {
		logger.info("start saveExpirePriceToPriceHis(),para:" + pcmPrice.toString());
		boolean flag = false;
		int count = pcmPriceHisMapper.insertSelective(pcmPrice);
		if (Constants.PUBLIC_1 == count) {
			count = 0;
			count = pcmPriceMapper.deleteByPrimaryKey(pcmPrice.getSid());
			if (Constants.PUBLIC_1 == count) {
				flag = true;
			} else {
				throw new RuntimeException("delete pcmprice failed,sid:" + pcmPrice.getSid());
			}
		}
		return flag;
	}

	// [end]

	// [start]批量变更下发

	/**
	 * 获取变价单信息
	 *
	 * @param pcmPricePISDtos
	 * @return List<SelectPcmPriceToERPPDto>
	 * @Methods Name getSelectPcmPriceToERPDto
	 * @Create In 2015年8月21日 By kongqf
	 */
	public List<SelectPcmPriceToERPPDto> getSelectPcmPriceToERPDto(
			List<PcmPricePISDto> pcmPricePISDtos) {
		List<SelectPcmPriceToERPPDto> selectPcmPriceToERPPDtos = new ArrayList<SelectPcmPriceToERPPDto>();
		List<String> changeCodeList = new ArrayList<String>();
		SelectPcmPriceToERPPDto dto = null;
		for (PcmPricePISDto pp : pcmPricePISDtos) {
			if (!changeCodeList.contains(pp.getChangecode())) {
				changeCodeList.add(pp.getChangecode());
				dto = new SelectPcmPriceToERPPDto();
				dto.setStoreCode(pp.getStorecode());
				dto.setChangeCode(pp.getChangecode());
				dto.setEndDate(Constants.PRICE_RETAIL_DATE);
				dto.setActionCode(Constants.ACTIONCODEA);

				selectPcmPriceToERPPDtos.add(dto);
			}
		}

		return selectPcmPriceToERPPDtos;
	}

	/**
	 * 根据变价号和pageSize查询下发数据总页数
	 *
	 * @param selectPcmPriceToERPPDto
	 * @param pageSize
	 * @return int
	 * @Methods Name queryPushBatchChangePriceInfoPages
	 * @Create In 2015年8月21日 By kongqf
	 */
	public int queryPushBatchChangePriceInfoPages(SelectPcmPriceToERPPDto selectPriceToERPPDto) {
		logger.info("start queryBatchChangePushPriceInfoPages(),param:"
				+ selectPriceToERPPDto.toString());
		Integer count = pcmPriceMapper.SelecPricePushCountBychangeCode(selectPriceToERPPDto);
		logger.info("start queryBatchChangePushPriceInfoPages(),count:" + count);
		Integer pages = 0;
		if (count != Constants.PUBLIC_0) {
			pages = (int) count / selectPriceToERPPDto.getPageSize();
			if (count % selectPriceToERPPDto.getPageSize() > 0) {
				pages++;
			}
		}
		logger.info("start queryBatchChangePushPriceInfoPages(),pages:" + pages);
		return pages;
	}

	/**
	 * 根据变价号查询下发数据
	 *
	 * @param selectPriceToERPPDto
	 * @return Page<PcmPriceToERPPDto>
	 * @Methods Name queryPushBatchChangePriceInfo
	 * @Create In 2015年8月21日 By kongqf
	 */
	public Page<PcmPriceToERPPDto> queryPushBatchChangePriceInfo(
			SelectPcmPriceToERPPDto selectPriceToERPPDto) {
		logger.info(
				"start queryPushBatchChangePriceInfo(),param:" + selectPriceToERPPDto.toString());
		Page<PcmPriceToERPPDto> pricePageDto = new Page<PcmPriceToERPPDto>();
		List<PcmPriceToERPPDto> pcmPriceToERPPDtos = new ArrayList<PcmPriceToERPPDto>();
		pcmPriceToERPPDtos = pcmPriceMapper.SelecPricePushInfoBychangeCode(selectPriceToERPPDto);
		pricePageDto.setList(pcmPriceToERPPDtos);
		logger.info(
				"start queryPushBatchChangePriceInfo(),result:" + pcmPriceToERPPDtos.toString());
		return pricePageDto;
	}

	// [end]

	/**
	 * 商品价格信息查询
	 *
	 * @param queryProductPriceInfoDto
	 * @return Page<QueryProductPriceInfoDto>
	 * @Methods Name queryProductPriceInfo
	 * @Create In 2015年9月7日 By kongqf
	 */
	public Page<SelectProductPriceInfoDto> queryProductPriceInfo(
			QueryProductPriceInfoDto queryProductPriceInfoDto) {
		logger.info("start queryProductPriceInfo(),param:" + queryProductPriceInfoDto.toString());
		Page<SelectProductPriceInfoDto> productPageDto = new Page<SelectProductPriceInfoDto>();
		List<SelectProductPriceInfoDto> queryProductPriceInfoDtos = new ArrayList<SelectProductPriceInfoDto>();
		Integer count = pcmPriceMapper.SelecProductPriceInfoCountByPara(queryProductPriceInfoDto);
		productPageDto.setCurrentPage(queryProductPriceInfoDto.getCurrentPage());
		productPageDto.setPageSize(queryProductPriceInfoDto.getPageSize());
		if (count != null && count != 0) {
			queryProductPriceInfoDtos = pcmPriceMapper
					.SelecProductPriceInfoByPara(queryProductPriceInfoDto);
			productPageDto.setList(queryProductPriceInfoDtos);
		}
		productPageDto.setCount(count);
		// logger.info("start queryProductPriceInfo(),result:" +
		// queryProductPriceInfoDtos.toString());
		return productPageDto;
	}

	/**
	 * 商品价格信息查询(优化)
	 *
	 * @param queryProductPriceInfoDto
	 * @return Page<QueryProductPriceInfoDto>
	 * @Methods Name queryProductPriceInfoOptimization
	 * @Create In 2015年9月7日 By kongqf
	 */
	@Override
	public Page<SelectProductPriceInfoDto> queryProductPriceInfoOptimization(
			QueryProductPriceInfoDto queryProductPriceInfoDto) {
		logger.info("start queryProductPriceInfoOptimization(),param:"
				+ queryProductPriceInfoDto.toString());
		Page<SelectProductPriceInfoDto> productPageDto = new Page<SelectProductPriceInfoDto>();
		List<SelectProductPriceInfoDto> queryProductPriceInfoDtos = new ArrayList<SelectProductPriceInfoDto>();
		Integer count = pcmPriceMapper.findProductPriceInfoCountByPara(queryProductPriceInfoDto);
		productPageDto.setCurrentPage(queryProductPriceInfoDto.getCurrentPage());
		productPageDto.setPageSize(queryProductPriceInfoDto.getPageSize());
		if (count != null && count != 0) {
			queryProductPriceInfoDtos = pcmPriceMapper
					.findProductPriceInfoByPara(queryProductPriceInfoDto);
			productPageDto.setList(queryProductPriceInfoDtos);
		}
		productPageDto.setCount(count);
		// logger.info("end queryProductPriceInfoOptimization(),result:" +
		// queryProductPriceInfoDtos.toString());
		return productPageDto;
	}

	/**
	 * 根据一组专柜商品编码查询价格
	 *
	 * @param queryProductPriceInfoDto
	 * @return List<SelectProductPriceInfoDto>
	 * @Methods Name findPriceInfoByParaForShoppeProduct
	 * @Create In 2016年4月12日 By wangxuan
	 */
	@Override
	public List<SelectProductPriceInfoDto> findPriceInfoByParaForShoppeProduct(
			QueryProductPriceInfoDto dto) {
		logger.info("start findPriceInfoByParaForShoppeProduct(),param:" + dto.toString());
		List<String> productCodeList = dto.getProductCodeList();
		if (productCodeList != null && productCodeList.size() == 0) {
			dto.setProductCodeList(null);
		}
		List<SelectProductPriceInfoDto> list = pcmPriceMapper
				.findPriceInfoByParaForShoppeProduct(dto);
		logger.info("end findPriceInfoByParaForShoppeProduct(),return:" + list.toString());
		return list;
	}

	/**
	 * 根据专柜商品查询价格信息
	 *
	 * @param queryShoppeProSidDto
	 * @return List<SelectShoppeProPriceInfoDto>
	 * @Methods Name queryShoppeProPriceInfoByShoppeProSid
	 * @Create In 2015年9月11日 By kongqf
	 */
	@Override
	public List<SelectShoppeProPriceInfoDto> queryShoppeProPriceInfoByShoppeProSid(
			QueryPriceDto queryPriceDto) {
		logger.info(
				"start queryShoppeProPriceInfoByShoppeProSid(),param:" + queryPriceDto.toString());
		List<SelectShoppeProPriceInfoDto> priceList = new ArrayList<SelectShoppeProPriceInfoDto>();
		if (StringUtils.isNotBlank(queryPriceDto.getShoppeProSid())) {
			priceList = pcmPriceMapper.queryShoppeProPriceInfoByShoppeProSid(queryPriceDto);
			if (priceList == null || priceList.size() < 0) {
				logger.info("start queryShoppeProPriceInfoByShoppeProSid(),result:"
						+ ErrorCode.PRICE_SHOPPEPRO_NOT_EXIST.getMemo());
				throw new BleException(ErrorCode.PRICE_SHOPPEPRO_NOT_EXIST.getErrorCode(),
						ErrorCode.PRICE_SHOPPEPRO_NOT_EXIST.getMemo());
			}
		} else {
			logger.info("start queryShoppeProPriceInfoByShoppeProSid(),result:"
					+ ErrorCode.PRICE_SUPPLIERPRODCODE_IS_NULL.getMemo());
			throw new BleException(ErrorCode.PRICE_SUPPLIERPRODCODE_IS_NULL.getErrorCode(),
					ErrorCode.PRICE_SUPPLIERPRODCODE_IS_NULL.getMemo());
		}
		logger.info("start queryShoppeProPriceInfoByShoppeProSid(),result:" + priceList.toString());
		return priceList;
	}

	/**
	 * 查询当前价格信息页数
	 *
	 * @param pcmPricePISDto
	 * @return Integer
	 * @Methods Name queryCurrPriceInfoPages
	 * @Create In 2015年9月17日 By kongqf
	 */
	@Override
	public Integer queryCurrPriceInfoPages(PcmPricePISDto pcmPricePISDto) {
		logger.info("start queryCurrPriceInfoPages(),param:" + pcmPricePISDto.toString());
		Integer count = pcmPriceMapper.queryCurrPriceInfoCount(pcmPricePISDto);
		logger.info("start queryCurrPriceInfoPages(),count:" + count);
		Integer pages = 0;
		if (count != 0) {
			pages = (int) count / pcmPricePISDto.getPageSize();
			if (count % pcmPricePISDto.getPageSize() > 0) {
				pages++;
			}
		}
		logger.info("start queryCurrPriceInfoPages(),pages:" + pages);
		return pages;
	}

	/**
	 * 查询当前价格信息（分页）
	 *
	 * @param pcmPricePISDto
	 * @return Page<SelectPriceDto>
	 * @Methods Name queryCurrPriceInfo
	 * @Create In 2015年9月17日 By kongqf
	 */
	@Override
	public Page<SelectPriceDto> queryCurrPriceInfo(PcmPricePISDto pcmPricePISDto) {
		logger.info("start queryCurrPriceInfo(),param:" + pcmPricePISDto.toString());
		Page<SelectPriceDto> pageDto = new Page<SelectPriceDto>();
		List<SelectPriceDto> priceDtoList = new ArrayList<SelectPriceDto>();
		Integer count = pcmPriceMapper.queryCurrPriceInfoCount(pcmPricePISDto);
		if (count != 0) {
			priceDtoList = pcmPriceMapper.queryCurrPriceInfo(pcmPricePISDto);
		}
		pageDto.setList(priceDtoList);
		pageDto.setCurrentPage(pcmPricePISDto.getCurrentPage());
		pageDto.setPageSize(pcmPricePISDto.getPageSize());
		pageDto.setCount(count);
		logger.info("start queryCurrPriceInfo(),param:" + priceDtoList.toString());
		return pageDto;
	}

	/**
	 * 查询当前生效变价单数量
	 *
	 * @param dto
	 * @return int
	 * @Methods Name queryCurChangePriceRecordPages
	 * @Create In 2015年9月23日 By kongqf
	 */
	public int queryCurChangePriceRecordPages(SelectChangePriceRecordDto dto) {
		logger.info("start queryCurChangePriceRecordPages(),param:" + dto.toString());
		Integer count = pcmChangePriceRecordMapper.queryCurChangePriceRecordCount();
		logger.info("start queryCurChangePriceRecordPages(),count:" + count);
		Integer pages = 0;
		if (count != Constants.PUBLIC_0) {
			pages = (int) count / dto.getPageSize();
			if (count % dto.getPageSize() > 0) {
				pages++;
			}
		}
		logger.info("start queryCurChangePriceRecordPages(),pages:" + pages);
		return pages;
	}

	/**
	 * 查询当前生效变价单
	 *
	 * @param dto
	 * @return Page<PcmChangePriceRecord>
	 * @Methods Name queryCurChangePriceRecordInfo
	 * @Create In 2015年9月23日 By kongqf
	 */
	public Page<PcmChangePriceRecord> queryCurChangePriceRecordInfo(
			SelectChangePriceRecordDto dto) {
		logger.info("start queryCurChangePriceRecordInfo(),param:" + dto.toString());
		Page<PcmChangePriceRecord> pricePageDto = new Page<PcmChangePriceRecord>();
		List<PcmChangePriceRecord> dtoList = new ArrayList<PcmChangePriceRecord>();
		dtoList = pcmChangePriceRecordMapper.queryCurChangePriceRecordByPara(dto);
		pricePageDto.setCurrentPage(dto.getCurrentPage());
		pricePageDto.setPageSize(dto.getPageSize());
		pricePageDto.setList(dtoList);
		logger.info("start queryCurChangePriceRecordInfo(),result:" + dtoList.toString());
		return pricePageDto;
	}

	/**
	 * 专柜商品价格信息查询导出Excel 查总数
	 *
	 * @param queryProductStockInfoDto
	 * @return List<SelectProductStockInfoDto>
	 * @Methods Name queryProductPriceInfoExcelCount
	 * @Create In 2016年04月05日 By wangxuan
	 */
	@Override
	public Page<SelectProductPriceInfoDto> queryProductPriceInfoExcelCount(
			QueryProductPriceInfoDto queryProductPriceInfoDto) {
		logger.info("start queryProductPriceInfoExcelCount(),param:"
				+ queryProductPriceInfoDto.toString());
		Page<SelectProductPriceInfoDto> page = new Page<SelectProductPriceInfoDto>();
		Integer count = pcmPriceMapper.findProductPriceInfoCountByPara(queryProductPriceInfoDto);
		if (count != null) {
			page.setCount(count);
		} else {
			page.setCount(0);
		}
		logger.info("end queryProductPriceInfoExcelCount(),result:" + count.toString());
		return page;
	}

	/**
	 * 专柜商品价格信息查询导出Excel
	 *
	 * @param queryProductStockInfoDto
	 * @return List<SelectProductStockInfoDto>
	 * @Methods Name queryProductStockInfoExcel
	 * @Create In 2015年10月8日 By kongqf
	 */
	@Override
	public List<SelectProductPriceInfoDto> queryProductPriceInfoExcel(
			QueryProductPriceInfoDto queryProductPriceInfoDto) {
		logger.info(
				"start queryProductPriceInfoExcel(),param:" + queryProductPriceInfoDto.toString());
		queryProductPriceInfoDto.setCurrentPage(null);
		queryProductPriceInfoDto.setPageSize(null);
		queryProductPriceInfoDto.setStart(null);
		queryProductPriceInfoDto.setLimit(null);
		List<SelectProductPriceInfoDto> queryProductPriceInfoDtos = pcmPriceMapper
				.findProductPriceInfoByPara(queryProductPriceInfoDto);
		logger.info(
				"end queryProductPriceInfoExcel(),result:" + queryProductPriceInfoDtos.toString());
		return queryProductPriceInfoDtos;
	}

	@Override
	public Map<String, Object> selectPriceByTimeFrame(Map<String, Object> map) {
		logger.info("start selectPriceByTimeFrame(),param:" + map.toString());
		List<Map<String, Object>> result = pcmPriceMapper.selectPriceByTimeFrame(map);
		logger.info("start selectPriceByTimeFrame(),result" + result.toString());
		Integer count = pcmPriceMapper.getCountByTimeFrame(map);
		logger.info("start getCountByTimeFrame(),result" + count.toString());
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", count);
		resultMap.put("list", result);
		return resultMap;
	}

	/**
	 * 根据专柜商品编码查询商品sid
	 */
	@Override
	public List<Map<String, Object>> selectShopProSidByShopProCode(List<String> shopProSids) {
		logger.info("start selectShopProSidByShopProCode,request:" + shopProSids.toString());
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		if (shopProSids != null && shopProSids.size() > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("shopProSids", shopProSids);
			map.put("sellingStatus", 1);
			map.put("saleStatus", 0);
			mapList = pcmPriceMapper.selectProSidListByShopProCode(map);
		}
		return mapList;
	}

}

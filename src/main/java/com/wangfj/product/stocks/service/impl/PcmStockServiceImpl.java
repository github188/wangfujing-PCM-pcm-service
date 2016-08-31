package com.wangfj.product.stocks.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangfj.core.cache.RedisVo;
import com.wangfj.core.constants.ComErrorCodeConstants;
import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.CacheUtils;
import com.wangfj.core.utils.DistributedLock;
import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.core.utils.PropertyUtil;
import com.wangfj.core.utils.RedisUtil;
import com.wangfj.product.common.domain.entity.PcmRedis;
import com.wangfj.product.common.domain.vo.PcmExceptionLogDto;
import com.wangfj.product.common.service.intf.IPcmExceptionLogService;
import com.wangfj.product.common.service.intf.IPcmRedisService;
import com.wangfj.product.constants.DomainName;
import com.wangfj.product.constants.FlagType;
import com.wangfj.product.constants.StatusCodeConstants.StatusCode;
import com.wangfj.product.maindata.persistence.PcmProDetailMapper;
import com.wangfj.product.maindata.service.intf.IPcmShoppeProductService;
import com.wangfj.product.stocks.domain.entity.PcmShoppeProRelation;
import com.wangfj.product.stocks.domain.entity.PcmStock;
import com.wangfj.product.stocks.domain.vo.PcmProductStockInfoDto;
import com.wangfj.product.stocks.domain.vo.PcmStockChangeDto;
import com.wangfj.product.stocks.domain.vo.PcmStockDto;
import com.wangfj.product.stocks.domain.vo.PcmStockInfoDto;
import com.wangfj.product.stocks.domain.vo.PcmStockWCSDto;
import com.wangfj.product.stocks.domain.vo.QueryProductStockInfoDto;
import com.wangfj.product.stocks.domain.vo.SelectProductStockInfoDto;
import com.wangfj.product.stocks.domain.vo.StockProCountDto;
import com.wangfj.product.stocks.domain.vo.StockProCountListDto;
import com.wangfj.product.stocks.domain.vo.StockProResultDto;
import com.wangfj.product.stocks.domain.vo.StockResultDto;
import com.wangfj.product.stocks.persistence.PcmStockMapper;
import com.wangfj.product.stocks.service.intf.IPcmShoppeProRelationService;
import com.wangfj.product.stocks.service.intf.IPcmStockChangeRecordService;
import com.wangfj.product.stocks.service.intf.IPcmStockLockRecordService;
import com.wangfj.product.stocks.service.intf.IPcmStockService;
import com.wangfj.util.Constants;

/**
 * @Class Name PcmStockServiceImpl
 * @Author yedong
 * @Create In 2015年7月7日
 */
@Service
@Transactional
public class PcmStockServiceImpl implements IPcmStockService {
	@Autowired
	private PcmStockMapper pcmStockMapper;
	@Autowired
	private IPcmStockLockRecordService pcmStockLockService;
	@Autowired
	private IPcmShoppeProductService pcmShoppeProSid;
	@Autowired
	private IPcmStockChangeRecordService pcmStockChangeRecordService;
	@Autowired
	private IPcmShoppeProRelationService pcmShoppeProRelationService;
	@Autowired
	private PcmProDetailMapper pcmProDetailMapper;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	@Autowired
	private IPcmExceptionLogService pcmExceptionLogService;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private IPcmRedisService redisService;

	private static final Logger logger = LoggerFactory.getLogger(PcmStockServiceImpl.class);

	public Integer saveOrUpdate(PcmStockDto record) {
		Integer saveOrUpdate = pcmStockMapper.saveOrUpdate(record);
		return saveOrUpdate;
	}

	/**
	 * 查询总库存（总库存数 = 全渠道残次品数 + 可售数 + 退货数 + 锁库数 + 其他渠道的锁库数）
	 *
	 * @param record
	 * @return Integer
	 * @Methods Name selectStockCountFromPcm
	 * @Create In 2015年7月28日 By yedong
	 */
	public int selectStockCountFromPcm(PcmStockDto record) {
		logger.info("start selectStockCountFromPcm(),param" + record.toString());
		PcmStock pcmStock = new PcmStock();
		pcmStock = pcmStockMapper.selectStockCount(record);

		if (pcmStock != null && StringUtils.isNotBlank(pcmStock.getProSum().toString())) {
			return pcmStock.getProSum().intValue();
		} else {
			return 0;
		}
	}

	/**
	 * 查询可售库存数量
	 *
	 * @param record
	 * @return Integer
	 * @Methods Name findStockFromPcm
	 * @Create In 2015年7月23日 By yedong
	 */
	// @RedisCacheGet(redisName = DomainName.getStock, returnObj =
	// "java.lang.Integer")
	public int findStockCountFromPcm(String key, PcmStockDto record) {
		logger.info("start findStockCountFromPcm(),param" + record.toString());
		PcmStock pcmStock = new PcmStock();
		Integer proSum = 0;
		String proStockSum = redisUtil.get(DomainName.getStock + key, "0000");
		if (!"0000".equals(proStockSum)) {
			proSum = Integer.parseInt(proStockSum);
		} else {
			pcmStock = pcmStockMapper.selectSaleCount(record);
			if (pcmStock != null && StringUtils.isNotBlank(pcmStock.getProSum().toString())) {
				proSum = pcmStock.getProSum().intValue();
				redisUtil.set(DomainName.getStock + key, proSum.toString());
				if (!CacheUtils.cacheFlag) {
					PcmRedis pcmRedisDto = new PcmRedis();
					pcmRedisDto.setRedisffield(DomainName.getStock);
					pcmRedisDto.setKeyname(DomainName.getStock + key);
					pcmRedisDto.setValue(proSum.toString());
					redisService.savePcmRedis(pcmRedisDto);
				}
			}
		}
		return proSum;
	}

	/**
	 * 查询可售库存
	 *
	 * @param record
	 * @return int
	 * @Methods Name findStockCountFromPcm
	 * @Create In 2015年8月28日 By kongqf
	 */
	private int findStockCountFromPcm(PcmStockDto record) {
		logger.info("start findStockCountFromPcm(),param" + record.toString());
		PcmStock pcmStock = new PcmStock();
		pcmStock = pcmStockMapper.selectSaleCount(record);

		if (pcmStock != null && StringUtils.isNotBlank(pcmStock.getProSum().toString())) {
			return pcmStock.getProSum().intValue();
		} else {
			return 0;
		}
	}

	/**
	 * 批量查询库存
	 *
	 * @param listDto
	 * @return List<PcmStockDto>
	 * @Methods Name findStockBigCountFromPcm
	 * @Create In 2015年8月6日 By yedong
	 */
	public List<PcmStockDto> findStockBigCountFromPcm(List<PcmStockDto> listDto) {
		logger.info("start findStockBigCountFromPcm(),param" + listDto.toString());
		List<PcmStockDto> reList = new ArrayList<PcmStockDto>();
		for (PcmStockDto pcmStockDto : listDto) {
			Map<String, Object> map = pcmShoppeProSid
					.selectStockInfo(pcmStockDto.getShoppeProSid());
			if (map != null) {
				PcmStockDto pcmStockDto1 = new PcmStockDto();
				Integer proSum = (int) findStockCountFromPcm(pcmStockDto);/* 可售库存数 */
				pcmStockDto1.setInventory((int) proSum);
				pcmStockDto1.setSupplyProductId(pcmStockDto.getShoppeProSid() + "");
				pcmStockDto1.setSku((String) map.get("sku"));
				pcmStockDto1.setLocation(map.get("shoppeSid").toString());
				pcmStockDto1.setLocationOwnerId(map.get("shopSid") + "");
				pcmStockDto1.setSuccess(Constants.SUCCESS);
				pcmStockDto1.setException(null);
				pcmStockDto1.setErrorCode(null);
				reList.add(pcmStockDto1);
			} else {
				PcmStockDto pcmStockDto1 = new PcmStockDto();
				pcmStockDto1.setSupplyProductId(pcmStockDto.getShoppeProSid() + "");
				pcmStockDto1.setSuccess(Constants.FAILURE);
				pcmStockDto1.setException("无此专柜商品");
				pcmStockDto1.setErrorCode(null);
				reList.add(pcmStockDto1);
			}
		}
		return reList;
	}

	/**
	 * 库存导入
	 *
	 * @param paraList
	 * @return List<PcmStockDto>
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @Methods Name findStockImportFromPcm
	 * @Create In 2015年7月27日 By yedong
	 */
	public PcmStockDto findStockImportFromPcm(PcmStockDto dto) {
		logger.info("start findStockImportFromPcm(),param" + dto.toString());
		validImportStockData(dto);
		Map<String, Object> map = pcmShoppeProSid.selectStockInfo(dto.getShoppeProSid());
		List<StockProResultDto> redisList = new ArrayList<StockProResultDto>();
		if (map != null) {/* 存在该专柜商品 */
			if (StringUtils.isNotEmpty(map.get("storeCode") + "")) {
				dto.setStoreCode(map.get("storeCode") + "");
			}
			/* 是否管库存 0管 1不管 */
			if (((Integer) map.get("isStock")).equals(0)) {
				int count = 0;
				int saleSum = 0;
				Integer sid = null;
				PcmStockDto record = new PcmStockDto();
				PcmStock pcmStock = new PcmStock();
				record.setShoppeProSid(dto.getShoppeProSid());
				record.setStockTypeSid(dto.getStockTypeSid());
				pcmStock = getPcmStock(record);
				dto.setSid(null);
				if (pcmStock != null) {
					sid = Integer.parseInt(pcmStock.getSid().toString());
					dto.setSid(pcmStock.getSid());
					saleSum = pcmStock.getProSum().intValue();
				}

				String lockURL = PropertyUtil.getSystemUrl("lockURL");
				DistributedLock ZKlock = new DistributedLock(lockURL, dto.getShoppeProSid());
				if (FlagType.zookeeper_lock == 0) {
					ZKlock.lock();
				}
				try {
					if (dto.getType().equals(Constants.PCMSTOCK_TYPE_ALL) || dto.getSid() == null) {
						if (dto.getStockTypeSid().equals(Constants.PCMSTOCK_TYPE_SALE)) {
							PcmStockDto lockRecord = new PcmStockDto();
							lockRecord.setShoppeProSid(dto.getShoppeProSid());
							lockRecord.setStockTypeSid(Constants.PCMSTOCK_TYPE_LOCK);
							PcmStockDto saleRecord = new PcmStockDto();
							saleRecord.setShoppeProSid(dto.getShoppeProSid());
							saleRecord.setStockTypeSid(Constants.PCMSTOCK_TYPE_SALE);
							saleSum = findStockCountFromPcm(saleRecord);
							int lockSum = 0;
							lockSum = findStockCountFromPcm(lockRecord);
							if (lockSum != 0) {
								if (lockSum <= dto.getProSum()) {
									dto.setProSum(dto.getProSum() - lockSum);
								} else {
									boolean isNegativeStock = checkIsNegativeStock(
											dto.getShoppeProSid());
									if (isNegativeStock) {
										if (FlagType.zookeeper_lock == 0) {
											ZKlock.unlock();
										}
										dto.setErrorCode(ErrorCode.STOCK_IMPORT_LOCKSUM_ERROR
												.getErrorCode());
										dto.setException(
												ErrorCode.STOCK_IMPORT_LOCKSUM_ERROR.getMemo());
										dto.setSuccess(Constants.FAILURE);
										return dto;
									} else {
										dto.setProSum(dto.getProSum() - lockSum);
									}
								}
							}
						}
						count = pcmStockMapper.saveOrUpdate(dto);
						if (count == 0) {
							if (FlagType.zookeeper_lock == 0) {
								ZKlock.unlock();
							}
							dto.setErrorCode(ErrorCode.STOCK_IMPORT_FAILED.getErrorCode());
							dto.setException(ErrorCode.STOCK_IMPORT_FAILED.getMemo());
							dto.setSuccess(Constants.FAILURE);
							return dto;
						} else {

						}
					} else if (dto.getType().equals(Constants.PCMSTOCK_TYPE_DELTA)
							|| dto.getType().equals("") && dto.getSid() != null) {
						if (Constants.FJERP.equals(dto.getSource().toUpperCase())
								|| Constants.SAPERP.equals(dto.getSource().toUpperCase())) {
							int changeSum = saleSum + dto.getInventory();
							if (changeSum < 0) {
								boolean isNegativeStock = checkIsNegativeStock(
										dto.getShoppeProSid());
								if (isNegativeStock) {
									if (FlagType.zookeeper_lock == 0) {
										ZKlock.unlock();
									}
									dto.setErrorCode(
											ErrorCode.STOCK_IMPORT_NEGATIVE_FAILED.getErrorCode());
									dto.setException(
											ErrorCode.STOCK_IMPORT_NEGATIVE_FAILED.getMemo());
									dto.setSuccess(Constants.FAILURE);
									return dto;
								}
							}
						}
						count = pcmStockMapper.stockImport(dto);
						if (count == 0) {
							if (FlagType.zookeeper_lock == 0) {
								ZKlock.unlock();
							}
							dto.setErrorCode(ErrorCode.STOCK_IMPORT_FAILED.getErrorCode());
							dto.setException(ErrorCode.STOCK_IMPORT_FAILED.getMemo());
							dto.setSuccess(Constants.FAILURE);
							return dto;
						}
					}
					if (FlagType.zookeeper_lock == 0) {
						ZKlock.unlock();
					}
				} catch (Exception e) {
					dto.setErrorCode(ErrorCode.STOCK_IMPORT_FAILED.getErrorCode());
					dto.setException(ErrorCode.STOCK_IMPORT_FAILED.getMemo());
					dto.setSuccess(Constants.FAILURE);
					if (FlagType.zookeeper_lock == 0) {
						ZKlock.unlock();
					}
				}
				if (sid == null) {
					/* 获得库存SID */
					sid = pcmStockMapper.selectSidByShoppeProSid(record);
					if (sid != null) {
						dto.setSid((long) sid);
					}
				}
				pcmStockChangeRecordService.changRecord(dto, null, saleSum);
				// 修改sku状态
				PcmProductStockInfoDto pcmProductStockInfoDto = new PcmProductStockInfoDto();
				pcmProductStockInfoDto.setShoppeProSid(dto.getShoppeProSid());
				PcmProductStockInfoDto stoc = SelectSkuStockSumBySku(pcmProductStockInfoDto);
				dto.setProSum(Long.valueOf(stoc.getSaleSum()));
			}
		} else {
			dto.setErrorCode(ErrorCode.STOCK_SHOPPEPROSID_IS_NOT_EXITS.getErrorCode());
			dto.setException(ErrorCode.STOCK_SHOPPEPROSID_IS_NOT_EXITS.getMemo());
			dto.setSuccess(Constants.FAILURE);
		}
		return dto;
	}

	/**
	 * 获取库存实体对象
	 * 
	 * @Methods Name getPcmStock
	 * @Create In 2016年3月29日 By kongqf
	 * @param record
	 * @return PcmStock
	 */
	private PcmStock getPcmStock(PcmStockDto record) {
		logger.info("start findStockCountFromPcm(),param" + record.toString());
		PcmStock pcmStock = new PcmStock();
		pcmStock = pcmStockMapper.selectSaleCount(record);

		return pcmStock;
	}

	/**
	 * 变更库存数据验证
	 *
	 * @param pcmStockDto
	 *            void
	 * @Methods Name validImportStockData
	 * @Create In 2015年11月15日 By kongqf
	 */
	public void validImportStockData(PcmStockDto pcmStockDto) {
		if (StringUtils.isBlank(pcmStockDto.getShoppeProSid())) {
			throw new BleException(ErrorCode.STOCK_SHOPPEPROSID_IS_NULL.getErrorCode(),
					ErrorCode.STOCK_SHOPPEPROSID_IS_NULL.getMemo());
		}
		if (Constants.SUPPLIERCENTER.equals(pcmStockDto.getSource().toUpperCase())) {
			Map<String, Object> paramMap = pcmShoppeProSid
					.selectParamByShoppeProCode(pcmStockDto.getShoppeProSid());
			if (paramMap != null && !paramMap.isEmpty()) {
				if (Constants.PCMORGANIZATION_E_STORE_CODE.equals(paramMap.get("shopCode"))) {
					String stockType = String.valueOf(paramMap.get("stockType"));
					String xxhcFlag = String.valueOf(paramMap.get("xxhcFlag"));

					if (!Constants.STOCKTYPE_2.equals(stockType)
							&& !Constants.XXHCFLAG_0.equals(xxhcFlag)) {
						throw new BleException(ErrorCode.NOT_XKORXXHC.getErrorCode(),
								ErrorCode.NOT_XKORXXHC.getMemo());
					}

				} else {
					String businessMode = String.valueOf(paramMap.get("businessMode"));
					if (!(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3 + "").equals(businessMode)) {
						throw new BleException(ErrorCode.NOT_ASSOCIATED.getErrorCode(),
								ErrorCode.NOT_ASSOCIATED.getMemo());
					}
				}
			} else {
				throw new BleException(ErrorCode.STOCK_SHOPPEPROSID_IS_NOT_EXITS.getErrorCode(),
						ErrorCode.STOCK_SHOPPEPROSID_IS_NOT_EXITS.getMemo());
			}
		}
		if (StringUtils.isBlank(pcmStockDto.getOperator())) {
			throw new BleException(ErrorCode.STOCK_IMPORTOPERATOR_IS_NULL.getErrorCode(),
					ErrorCode.STOCK_IMPORTOPERATOR_IS_NULL.getMemo());
		}
		if (!Constants.FJERP.equals(pcmStockDto.getSource().toUpperCase())
				&& !Constants.SAPERP.equals(pcmStockDto.getSource().toUpperCase())) {
			if (pcmStockDto.getInventory() != null && pcmStockDto.getInventory() < 0) {
				throw new BleException(ErrorCode.STOCK_IMPORTSTOCK_ERROR.getErrorCode(),
						ErrorCode.STOCK_IMPORTSTOCK_ERROR.getMemo());
			}
			if (pcmStockDto.getBorrowInventory() != null && pcmStockDto.getBorrowInventory() < 0) {
				throw new BleException(ErrorCode.STOCK_IMPORTSTOCK_ERROR.getErrorCode(),
						ErrorCode.STOCK_IMPORTSTOCK_ERROR.getMemo());
			}
			if (pcmStockDto.getDefectiveInventory() != null
					&& pcmStockDto.getDefectiveInventory() < 0) {
				throw new BleException(ErrorCode.STOCK_IMPORTSTOCK_ERROR.getErrorCode(),
						ErrorCode.STOCK_IMPORTSTOCK_ERROR.getMemo());
			}
		} else {
			if (pcmStockDto.getType().equals(Constants.PCMSTOCK_TYPE_ALL)) {
				if (pcmStockDto.getInventory() != null && pcmStockDto.getInventory() < 0) {
					throw new BleException(ErrorCode.STOCK_IMPORT_TPYEALL_ERROR.getErrorCode(),
							ErrorCode.STOCK_IMPORT_TPYEALL_ERROR.getMemo());
				}
				if (pcmStockDto.getBorrowInventory() != null
						&& pcmStockDto.getBorrowInventory() < 0) {
					throw new BleException(ErrorCode.STOCK_IMPORT_TPYEALL_ERROR.getErrorCode(),
							ErrorCode.STOCK_IMPORT_TPYEALL_ERROR.getMemo());
				}
				if (pcmStockDto.getDefectiveInventory() != null
						&& pcmStockDto.getDefectiveInventory() < 0) {
					throw new BleException(ErrorCode.STOCK_IMPORT_TPYEALL_ERROR.getErrorCode(),
							ErrorCode.STOCK_IMPORT_TPYEALL_ERROR.getMemo());
				}
			}
		}
	}

	/**
	 * 库存公用方法1 条件判断
	 *
	 * @param stockProCountDto
	 * @param map
	 * @param methodCount
	 * @return StockResultDto
	 * @throws Exception
	 * @Methods Name stockPublicMethod1
	 * @Create In 2015年8月4日 By yedong
	 */
	private List<StockProCountDto> stockPublicMethod1(List<StockProCountDto> products) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<StockProCountDto> dtoList = new ArrayList<StockProCountDto>();
		for (StockProCountDto stockProCountDto : products) {
			if (StringUtils.isNotBlank(stockProCountDto.getSupplyProductNo())) {
				map = pcmShoppeProSid.selectStockInfo(stockProCountDto.getSupplyProductNo());
				stockProCountDto.setStoreCode(map.get("storeCode").toString());
			}
			/* 判断是否为专柜商品 */
			if (map != null && stockProCountDto.getSupplyProductNo() != null) {
				/* 判断商品是否可售 0可售 1不可售 */
				if (((Integer) map.get("saleStatus")).equals(0)) {
					/* 是否管库存 0 管库存，1 不管库存 */
					if (((Integer) map.get("isStock")).equals(0)) {
						/* 判断该专柜商品是否存在可售库存 */
						PcmStockDto record = new PcmStockDto();
						record.setShoppeProSid(stockProCountDto.getSupplyProductNo());
						record.setStockTypeSid(Constants.PCMSTOCK_TYPE_SALE);
						if (Arrays.asList(Constants.PCMSTOCK_SALE_REDUCE)
								.contains(stockProCountDto.getStockType())) {
							if (StringUtils.isNotBlank(stockProCountDto.getChannelSid())) {
								record.setChannelSid(stockProCountDto.getChannelSid());
							}
						}
						Integer sid = pcmStockMapper.selectSidByShoppeProSid(record);
						if (sid != null) {
							dtoList.add(stockProCountDto);
						} else {
							logger.info("start,stockPublicMethod1,result:"
									+ stockProCountDto.getSupplyProductNo() + "无可售库存");
							throw new BleException(
									ErrorCode.STOCK_SHOPPEPROSID_NOT_SALESTOCK.getErrorCode(),
									ErrorCode.STOCK_SHOPPEPROSID_NOT_SALESTOCK.getMemo());
						}
					}
				} else {
					logger.info("start,stockPublicMethod1,result:"
							+ stockProCountDto.getSupplyProductNo() + "不可售");
					throw new BleException(ErrorCode.STOCK_SHOPPEPROSID_NOT_SALE.getErrorCode(),
							ErrorCode.STOCK_SHOPPEPROSID_NOT_SALE.getMemo());
				}
			} /* 判断是否为大码商品 */else if (stockProCountDto.getErpProductNo() == null) {
				logger.info("start,stockPublicMethod1,result:专柜商品、大码皆为空");
				throw new BleException(ErrorCode.STOCK_SHOPPEPROSID_IS_NOT_EXITS.getErrorCode(),
						ErrorCode.STOCK_SHOPPEPROSID_IS_NOT_EXITS.getMemo());
			}
		}
		/* 返回第一次判断成功的数据 */
		return dtoList;
	}

	/**
	 * 库存公用方法2-是否可包装
	 *
	 * @param stockProCountDto
	 * @param paramMap
	 * @return List<StockProCountDto>
	 * @Methods Name stockPublicMethod2
	 * @Create In 2015年8月4日 By yedong
	 */
	private List<StockProCountDto> stockPublicMethod2(StockProCountDto stockProCountDto,
			Map<String, Object> paramMap) {
		List<StockProCountDto> list = new ArrayList<StockProCountDto>();
		if ((Integer) paramMap.get("isPacking") == null) {
			paramMap.put("isPacking", 0);
		}
		int i = (Integer) paramMap.get("isPacking");
		/* 是否可包装 0可以，1不可以 */
		if (i == Constants.PUBLIC_0) {
			list.add(stockProCountDto);
		} else {
			List<PcmShoppeProRelation> list1 = pcmShoppeProRelationService
					.getSubPro(Long.parseLong(stockProCountDto.getSupplyProductNo()));
			for (PcmShoppeProRelation subSid : list1) {
				StockProCountDto dto = new StockProCountDto();
				dto.setSalesItemNo(stockProCountDto.getSalesItemNo());
				dto.setSupplyProductNo(subSid.getCode() + "");
				dto.setSaleSum(subSid.getAmount() * stockProCountDto.getSaleSum());
				dto.setStockType(stockProCountDto.getStockType());
				list.add(dto);
			}
		}
		return list;
	}

	/**
	 * 加减库操作
	 *
	 * @param dto
	 * @return StockResultDto
	 * @Methods Name findInsertAndReduceFromPcm
	 * @Create In 2015年8月14日 By yedong
	 */
	// public StockResultDto findInsertAndReduceFromPcm(StockProCountListDto
	// dto) {
	// StockResultDto resultDto = new StockResultDto();
	// List<StockProCountDto> products = dto.getProducts();
	// /*
	// * 判断操作类型 0 - 锁库 - 是否允许负库存销售 - 判断可售数量是否大于锁定数量 -
	// * 添加锁定记录、添加变动记录、可售库减少，锁定库增加 1 - 减库 - 是否已锁定 - 判断减库数量是否等于锁库数量
	// * -可售库不变，锁定库减少、添加解锁记录 2 - 解锁 - 是否已锁定 - 判断解锁数量是否等于锁库数量 -
	// * 可售库增加，锁定库减少、添加解锁记录、添加变动记录 3 - 还库 - 退货库增加
	// */
	// Map<String, Object> map = new HashMap<String, Object>();
	// if (dto.getCzType() == Constants.PUBLIC_0) {
	// for (StockProCountDto stockProCountDto : products) {
	// String lockURL = PropertyUtil.getSystemUrl("lockURL");
	// DistributedLock ZKlock = new DistributedLock(lockURL,
	// stockProCountDto.getSupplyProductNo());
	// if (FlagType.zookeeper_lock == Constants.PUBLIC_0) {
	// ZKlock.lock();
	// }
	// map =
	// pcmShoppeProSid.selectStockInfo(stockProCountDto.getSupplyProductNo());
	// if ((Integer) map.get("negative") == null) {
	// map.put("negative", 1);
	// }
	// /* 是否负库存销售 ：0 允许，1不允许 */
	// if (((Integer) map.get("negative")).equals(Constants.PUBLIC_1)) {
	//
	// PcmStockDto record = new PcmStockDto();
	// record.setShoppeProSid(stockProCountDto.getSupplyProductNo());
	// record.setStockTypeSid(Constants.PCMSTOCK_TYPE_SALE);
	// /* 根据专柜商品编码查询可售库存数 (stockCount) */
	//
	// Integer stockCount = (int) findStockCountFromPcm(record);
	// /* 联网状态， 判断锁定数量是否大于可售数量 */
	// if (Constants.PCM_ISOFFERLINE0.equals(dto.getIsOfferLine().toString())
	// && stockCount < stockProCountDto.getSaleSum()) {
	// logger.info("锁定数量大于可售库存数量");
	// if (FlagType.zookeeper_lock == Constants.PUBLIC_0) {
	// ZKlock.unlock();
	// }
	// throw new BleException(
	// ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
	// ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
	// } else {
	// if (stockProCountDto.getChannelSid() != null) {
	// record.setChannelSid(
	// Integer.parseInt(stockProCountDto.getChannelSid()));
	// }
	// /* 获得库存SID */
	// int recordId = pcmStockMapper.selectSidByShoppeProSid(record);
	// /* 添加锁定记录、添加变动记录、可售库减少，锁定库增加 */
	// /* 添加锁定记录 */
	// stockProCountDto.setSaleNo(dto.getSaleNo());
	// Integer status4 = pcmStockLockService.lockStock(stockProCountDto);
	// boolean status1 = false;
	// boolean status2 = false;
	// boolean status3 = false;
	// if (status4 == 1) {
	// /* 可售库减少，锁定库增加 */
	// record.setProSum(-(long) stockProCountDto.getSaleSum());
	// if (Constants.PCM_ISOFFERLINE1.equals(dto.getIsOfferLine())) {
	// Integer outOfStock = stockCount - stockProCountDto.getSaleSum();
	// if (outOfStock < 0) {
	// record.setProSum(-(long) stockCount);
	// }
	// }
	// status1 = addtOrReduce(record);
	// record.setStockTypeSid(Constants.PCMSTOCK_TYPE_LOCK);
	// record.setProSum((long) stockProCountDto.getSaleSum());
	// status2 = addtOrReduce(record);
	// /* 添加变动记录 */
	// status3 = pcmStockChangeRecordService.addChangeRecord(stockProCountDto,
	// recordId);
	// status4 = 0;
	// }
	// if ((status1 && status2 && status3 && status4 == 0) || status4 == 0) {
	// resultDto.setResultFlag(Constants.PUBLIC_1 + "");
	// resultDto.setResultMsg("锁库成功");
	// } else {
	// if (FlagType.zookeeper_lock == Constants.PUBLIC_0) {
	// ZKlock.unlock();
	// }
	// logger.info(record.getShoppeProSid() + "锁库失败");
	// throw new BleException(
	// ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getErrorCode(),
	// ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getMemo());
	// }
	// }
	// } else {
	// PcmStockDto record = new PcmStockDto();
	// record.setShoppeProSid(stockProCountDto.getSupplyProductNo());
	// record.setStockTypeSid(Constants.PCMSTOCK_TYPE_SALE);
	//
	// if (stockProCountDto.getChannelSid() != null) {
	// record.setChannelSid(Integer.parseInt(stockProCountDto.getChannelSid()));
	// }
	// /* 获得库存SID */
	// int recordId = pcmStockMapper.selectSidByShoppeProSid(record);
	// /* 添加锁定记录、添加变动记录、可售库减少，锁定库增加 */
	// /* 添加锁定记录 */
	// stockProCountDto.setSaleNo(dto.getSaleNo());
	// Integer status4 = pcmStockLockService.lockStock(stockProCountDto);
	// boolean status1 = false;
	// boolean status2 = false;
	// boolean status3 = false;
	// if (status4 == 1) {
	// /* 可售库减少，锁定库增加 */
	// record.setProSum(-(long) stockProCountDto.getSaleSum());
	// status1 = addtOrReduce(record);
	// record.setStockTypeSid(Constants.PCMSTOCK_TYPE_LOCK);
	// record.setProSum((long) stockProCountDto.getSaleSum());
	// status2 = addtOrReduce(record);
	// /* 添加变动记录 */
	// status3 = pcmStockChangeRecordService.addChangeRecord(stockProCountDto,
	// recordId);
	// status4 = 0;
	// }
	// if ((status1 && status2 && status3 && status4 == 0) || status4 == 0) {
	// resultDto.setResultFlag(Constants.PUBLIC_1 + "");
	// resultDto.setResultMsg("锁库成功");
	// } else {
	// if (FlagType.zookeeper_lock == Constants.PUBLIC_0) {
	// ZKlock.unlock();
	// }
	// logger.info(record.getShoppeProSid() + "锁库失败");
	// throw new BleException(
	// ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getErrorCode(),
	// ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getMemo());
	// }
	// }
	// if (FlagType.zookeeper_lock == Constants.PUBLIC_0) {
	// ZKlock.unlock();
	// }
	// }
	// }
	// if (dto.getCzType() == Constants.PUBLIC_1) {
	// for (StockProCountDto stockProCountDto : products) {
	// map =
	// pcmShoppeProSid.selectStockInfo(stockProCountDto.getSupplyProductNo());
	// /* 获得锁定库存的数量 */
	// Integer lockCount = pcmStockLockService
	// .selectLockCountByShoppePro(stockProCountDto);
	// /* 判断是否已锁定 */
	// if (lockCount == null) {
	// resultDto.setResultFlag("" + Constants.PUBLIC_0);
	// resultDto.setResultMsg(stockProCountDto.getSupplyProductNo() + "商品未锁定");
	// logger.info(resultDto.toString());
	// throw new BleException(
	// ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
	// ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
	// }
	// /* 判断减库数量是否等于锁库数量 */
	// if (!lockCount.equals(stockProCountDto.getSaleSum())) {
	// resultDto.setResultFlag("" + Constants.PUBLIC_0);
	// resultDto.setResultMsg(stockProCountDto.getSupplyProductNo() +
	// "减库数量不等于锁库数量");
	// logger.info(resultDto.toString());
	// throw new BleException(
	// ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
	// ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
	// }
	// /* 减库操作 可售库不变，锁定库减少、添加解锁记录 */
	// PcmStockDto record = new PcmStockDto();
	// record.setShoppeProSid(stockProCountDto.getSupplyProductNo());
	// record.setProSum(-(long) stockProCountDto.getSaleSum());
	// record.setStockTypeSid(Constants.PCMSTOCK_TYPE_LOCK);
	// boolean status1 = addtOrReduce(record);
	// stockProCountDto.setSaleNo(dto.getSaleNo());
	// boolean status2 = pcmStockLockService.unlockStock(stockProCountDto);
	// if (status1 && status2) {
	// resultDto.setResultFlag(Constants.PUBLIC_1 + "");
	// resultDto.setResultMsg("减库成功");
	// } else {
	// logger.info(record.getShoppeProSid() + "锁库失败");
	// throw new BleException(
	// ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getErrorCode(),
	// ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getMemo());
	// }
	// }
	// if (resultDto.getResultFlag().equals(Constants.PUBLIC_1.toString())) {
	// boolean isDel =
	// pcmStockLockService.delStockRecordBySalesItemNo(dto.getSaleNo());
	// if (isDel) {
	// logger.info("减库，锁库记录移库成功，销售单号：" + dto.getSaleNo());
	// } else {
	// logger.info("减库，锁库记录移库失败，销售单号：" + dto.getSaleNo());
	// }
	// }
	// }
	// if (dto.getCzType() == Constants.PUBLIC_2) {
	// for (StockProCountDto stockProCountDto : products) {
	// map =
	// pcmShoppeProSid.selectStockInfo(stockProCountDto.getSupplyProductNo());
	// /* 获得锁定库存的数量 */
	// Integer lockCount = pcmStockLockService
	// .selectLockCountByShoppePro(stockProCountDto);
	// /* 判断是否已锁定 */
	// if (lockCount == null) {
	// resultDto.setResultFlag("" + Constants.PUBLIC_0);
	// resultDto.setResultMsg(stockProCountDto.getSupplyProductNo() + "商品未锁定");
	// logger.info(resultDto.toString());
	// throw new BleException(
	// ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
	// ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
	// }
	// /* 判断解锁数量是否等于锁库数量 */
	// if (!lockCount.equals(stockProCountDto.getSaleSum())) {
	// resultDto.setResultFlag("" + Constants.PUBLIC_0);
	// resultDto.setResultMsg(stockProCountDto.getSupplyProductNo() +
	// "解锁数量不等于锁库数量");
	// logger.info(resultDto.toString());
	// throw new BleException(
	// ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
	// ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
	// }
	// PcmStockDto record = new PcmStockDto();
	// record.setShoppeProSid(stockProCountDto.getSupplyProductNo());
	// record.setStockTypeSid(Constants.PCMSTOCK_TYPE_SALE);
	// if (stockProCountDto.getChannelSid() != null) {
	// record.setChannelSid(Integer.parseInt(stockProCountDto.getChannelSid()));
	// }
	// /* 获得库存SID */
	// int recordId = pcmStockMapper.selectSidByShoppeProSid(record);
	// /* 可售库增加，锁定库减少、添加解锁记录、添加变动记录 */
	// /* 可售库增加，锁定库减少 */
	// boolean status4 = true;
	// record.setProSum((long) stockProCountDto.getSaleSum());
	// boolean status1 = addtOrReduce(record);
	// record.setStockTypeSid(Constants.PCMSTOCK_TYPE_LOCK);
	// record.setProSum(-(long) stockProCountDto.getSaleSum());
	// boolean status2 = addtOrReduce(record);
	// /* 添加变动记录 */
	// boolean status3 =
	// pcmStockChangeRecordService.addChangeRecord(stockProCountDto,
	// recordId);
	// /* 添加解锁记录 */
	// if (status3) {
	// stockProCountDto.setSaleNo(dto.getSaleNo());
	// status4 = pcmStockLockService.unlockStock(stockProCountDto);
	// }
	// if (status1 && status2 && status3 && status4) {
	// resultDto.setResultFlag(Constants.PUBLIC_1 + "");
	// resultDto.setResultMsg("解锁成功");
	// } else {
	// logger.info(record.getShoppeProSid() + "解锁失败");
	// throw new BleException(
	// ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getErrorCode(),
	// ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getMemo());
	// }
	// }
	//
	// if (resultDto.getResultFlag().equals(Constants.PUBLIC_1.toString())) {
	// boolean isDel =
	// pcmStockLockService.delStockRecordBySalesItemNo(dto.getSaleNo());
	// if (isDel) {
	// logger.info("解锁，锁库记录移库成功，销售单号：" + dto.getSaleNo());
	// } else {
	// logger.info("解锁，锁库记录移库失败，销售单号：" + dto.getSaleNo());
	// }
	// }
	// }
	// if (dto.getCzType() == Constants.PUBLIC_3) {
	// for (StockProCountDto stockProCountDto : products) {
	// PcmStockDto record = new PcmStockDto();
	// record.setShoppeProSid(stockProCountDto.getSupplyProductNo());
	// record.setStockTypeSid(Constants.PCMSTOCK_TYPE_SALE);
	// int recordId = pcmStockMapper.selectSidByShoppeProSid(record);
	// record.setStockTypeSid(Constants.PCMSTOCK_TYPE_RETURN);
	// record.setProSum((long) stockProCountDto.getSaleSum());
	// boolean status1 = addtOrReduce(record);
	// boolean status3 =
	// pcmStockChangeRecordService.addChangeRecord(stockProCountDto,
	// recordId);
	// if (status1 && status3) {
	// resultDto.setResultFlag(Constants.PUBLIC_1 + "");
	// resultDto.setResultMsg("还库成功");
	// } else {
	// logger.info(record.getShoppeProSid() + "还库失败");
	// throw new BleException(
	// ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getErrorCode(),
	// ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getMemo());
	// }
	// }
	// }
	// if (dto.getCzType() == Constants.PUBLIC_4) {
	// for (StockProCountDto stockProCountDto : products) {
	// PcmStockDto record = new PcmStockDto();
	// record.setShoppeProSid(stockProCountDto.getSupplyProductNo());
	// record.setStockTypeSid(Constants.PCMSTOCK_TYPE_SALE);
	// /* 可售数 */
	// Integer stockCount = (int) findStockCountFromPcm(record);
	// /* 获得SID */
	// int recordId = pcmStockMapper.selectSidByShoppeProSid(record);
	// if (stockProCountDto.getChannelSid() != null
	// && stockProCountDto.getChannelSid() != "") {
	// record.setChannelSid(Integer.parseInt(stockProCountDto.getChannelSid()));
	// }
	// /* 借出、调出 */
	// if (stockProCountDto.getStockType() == Constants.PCMSTOCK_TYPE_BORROW
	// || stockProCountDto.getStockType() == Constants.PCMSTOCK_OUT_TRANSFER) {
	// if (stockCount < stockProCountDto.getSaleSum()) {
	// logger.info(record.getShoppeProSid() + "借出、调出的数量大于可售数量");
	// throw new BleException(
	// ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getErrorCode(),
	// ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getMemo());
	// }
	// record.setProSum(-(long) stockProCountDto.getSaleSum());
	//
	// } else /* 归还、调入 */
	// if (stockProCountDto.getStockType() == Constants.PCMSTOCK_TYPE_REBORROW
	// || stockProCountDto.getStockType() == Constants.PCMSTOCK_IN_TRANSFER) {
	// record.setProSum((long) stockProCountDto.getSaleSum());
	// }
	// /* 添加变动记录、改变可售库 */
	// boolean status1 = addtOrReduce(record);
	// boolean status3 =
	// pcmStockChangeRecordService.addChangeRecord(stockProCountDto,
	// recordId);
	// if (status1 && status3) {
	// resultDto.setResultFlag(Constants.PUBLIC_1 + "");
	// resultDto.setResultMsg("成功");
	// } else {
	// logger.info(record.getShoppeProSid() + "失败");
	// throw new BleException(
	// ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getErrorCode(),
	// ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getMemo());
	// }
	// }
	// }
	// if (dto.getCzType() == Constants.PUBLIC_5) {
	// for (StockProCountDto stockProCountDto : products) {
	// PcmStockDto record = new PcmStockDto();
	// record.setShoppeProSid(stockProCountDto.getSupplyProductNo());
	// record.setStockTypeSid(stockProCountDto.getStockType());
	// if (stockProCountDto.getChannelSid() != null
	// && stockProCountDto.getChannelSid() != "") {
	// record.setChannelSid(Integer.parseInt(stockProCountDto.getChannelSid()));
	// }
	// /* 获得SID */
	// record.setProSum((long) stockProCountDto.getSaleSum());
	// /* 添加变动记录、改变指定库存数 */
	// boolean status1 = addtOrReduce(record);
	//
	// PcmStockDto record1 = new PcmStockDto();
	// record1.setShoppeProSid(stockProCountDto.getSupplyProductNo());
	// record1.setStockTypeSid(stockProCountDto.getStockType());
	// int recordId = pcmStockMapper.selectSidByShoppeProSid(record1);
	// boolean status3 =
	// pcmStockChangeRecordService.addChangeRecord(stockProCountDto,
	// recordId);
	// if (status1 && status3) {
	// resultDto.setResultFlag(Constants.PUBLIC_1 + "");
	// resultDto.setResultMsg("成功");
	// } else {
	// logger.info(record.getShoppeProSid() + "失败");
	// throw new BleException(
	// ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getErrorCode(),
	// ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getMemo());
	// }
	// }
	// }
	// return resultDto;
	// }
	public StockResultDto findInsertAndReduceFromPcmV2(StockProCountListDto dto) {
		logger.info("start,findInsertAndReduceFromPcm,para:" + dto.toString());
		StockResultDto resultDto = new StockResultDto();
		List<StockProCountDto> products = dto.getProducts();
		String lockURL = PropertyUtil.getSystemUrl("lockURL");
		boolean flag = false;
		List<StockProResultDto> redisList = new ArrayList<StockProResultDto>();
		StockProResultDto stockProResultDto = null;
		redisList.clear();

		// 判断操作类型 0 - 锁库 - 是否允许负库存销售 - 判断可售数量是否大于锁定数量 -添加锁定记录、添加变动记录、可售库减少，锁定库增加
		// 1 - 减库 - 是否已锁定 - 判断减库数量是否等于锁库数量 -可售库不变，锁定库减少、添加解锁记录
		// 2 - 解锁 - 是否已锁定 - 判断解锁数量是否等于锁库数量 - 可售库增加，锁定库减少、添加解锁记录、添加变动记录
		// 3 - 还库 - 退货库增加
		if (dto.getCzType() == Constants.PCMSTOCK_OPERATION_TYPE0) {// 锁库
			List<StockProResultDto> stockProResultDtoList = new ArrayList<StockProResultDto>();
			boolean isSucceed = true;
			// 判断是否联网：0联网，1脱机
			if (Constants.PCM_ISOFFERLINE0.equals(dto.getIsOfferLine().toString())) {
				for (StockProCountDto stockProCountDto : products) {
					stockProCountDto.setSaleNo(dto.getSaleNo());
					stockProResultDto = new StockProResultDto();
					// 是否负库存销售 ：false 允许，true不允许
					boolean isNegativeStock = checkIsNegativeStock(
							stockProCountDto.getSupplyProductNo());
					if (isNegativeStock) {
						DistributedLock ZKlock = new DistributedLock(lockURL,
								stockProCountDto.getSupplyProductNo());
						if (FlagType.zookeeper_lock == 0) {
							ZKlock.lock();
						}
						try {
							stockProResultDto = saveNotOffLineLockStockInfo(stockProCountDto,
									dto.getFromSystem());
							if (FlagType.zookeeper_lock == 0) {
								ZKlock.unlock();
							}
							redisList.add(stockProResultDto);
						} catch (Exception e) {
							if (FlagType.zookeeper_lock == 0) {
								ZKlock.unlock();
							}
							stockProResultDto
									.setSupplyProductNo(stockProCountDto.getSupplyProductNo());
							stockProResultDto.setProductName(stockProCountDto.getShoppeProName());
							stockProResultDto.setOutOfStock(0);
							stockProResultDto.setResultFlag(Constants.PCM_OPERATION_FAILED);
							stockProResultDto
									.setResultMsg(ErrorCode.STOCK_LOCK_FAILED_ERROR.getMemo());
						}
					} else {
						stockProResultDto = saveOffLineLockStockInfo(stockProCountDto,
								isNegativeStock, dto.getFromSystem());
						redisList.add(stockProResultDto);
					}
					if (stockProResultDto.getResultFlag().equals(Constants.PCM_OPERATION_FAILED)) {
						stockProResultDtoList.add(stockProResultDto);
						isSucceed = false;
					}
				}
			} else {// 脱机
				for (StockProCountDto stockProCountDto : products) {
					stockProCountDto.setSaleNo(dto.getSaleNo());
					// 是否负库存销售 ：false 允许，true不允许
					boolean isNegativeStock = checkIsNegativeStock(
							stockProCountDto.getSupplyProductNo());
					stockProResultDto = new StockProResultDto();
					try {
						stockProResultDto = saveOffLineLockStockInfo(stockProCountDto,
								isNegativeStock, dto.getFromSystem());
						redisList.add(stockProResultDto);
					} catch (Exception e) {
						stockProResultDto.setSupplyProductNo(stockProCountDto.getSupplyProductNo());
						stockProResultDto.setProductName(stockProCountDto.getShoppeProName());
						stockProResultDto.setOutOfStock(0);
						stockProResultDto.setResultFlag(Constants.PCM_OPERATION_FAILED);
						stockProResultDto.setResultMsg(ErrorCode.STOCK_LOCK_FAILED_ERROR.getMemo());
					}
					if (stockProResultDto.getResultFlag().equals(Constants.PUBLIC_0.toString())) {
						stockProResultDtoList.add(stockProResultDto);
						isSucceed = false;
					}
				}
			}
			if (isSucceed) {
				for (StockProResultDto redisDto : redisList) {
					RedisVo vo = new RedisVo();
					vo.setValue(redisDto.getProSum().toString());
					vo.setKey(redisDto.getSupplyProductNo() + redisDto.getChannelSid());
					vo.setField(DomainName.getStock);
					vo.setType(CacheUtils.HSET);
					CacheUtils.setRedisData(vo);
					logger.info("pcm_getStock Redis key:" + redisDto.getSupplyProductNo()
							+ ",value:" + redisDto.getProSum());
				}
				resultDto.setResultFlag(Constants.PCM_OPERATION_SUCCEED);
				resultDto.setResultMsg(ErrorCode.STOCK_LOCK_SUCCEED.getMemo());
			} else {
				throw new BleException(
						ComErrorCodeConstants.ErrorCode.STOCK_LOCK_FAILED_ERROR.getErrorCode(),
						JsonUtil.getJSONString(stockProResultDtoList));
			}
		} else if (dto.getCzType() == Constants.PCMSTOCK_OPERATION_TYPE1) {// 减库
			for (StockProCountDto stockProCountDto : products) {
				flag = false;
				logger.info("para:" + stockProCountDto.toString());
				// 获得锁定库存的数量
				Integer lockCount = pcmStockLockService
						.selectLockCountByShoppePro(stockProCountDto);
				// 判断减库数量是否等于锁库数量
				if (lockCount == null || !lockCount.equals(stockProCountDto.getSaleSum())) {
					logger.info(ErrorCode.STOCK_REDUCE_DIFFER_ERROR.getMemo());
					throw new BleException(ErrorCode.STOCK_REDUCE_DIFFER_ERROR.getErrorCode(),
							ErrorCode.STOCK_REDUCE_DIFFER_ERROR.getMemo());
				}
				// 减库操作 可售库不变，锁定库减少、添加解锁记录
				PcmStockDto record = new PcmStockDto();
				record.setShoppeProSid(stockProCountDto.getSupplyProductNo());
				record.setProSum(-(long) stockProCountDto.getSaleSum());
				record.setStockTypeSid(Constants.PCMSTOCK_TYPE_LOCK);
				flag = addtOrReduce(record);
				if (flag) {
					stockProCountDto.setSaleNo(dto.getSaleNo());
					flag = pcmStockLockService.unlockStock(stockProCountDto);
				}
				if (flag) {
					resultDto.setResultFlag(Constants.PCM_OPERATION_SUCCEED);
					resultDto.setResultMsg(ErrorCode.STOCK_REDUCE_SUCCEED.getMemo());
				} else {
					logger.info(ErrorCode.STOCK_REDUCE_FAILED_ERROR.getMemo());
					throw new BleException(ErrorCode.STOCK_REDUCE_FAILED_ERROR.getErrorCode(),
							ErrorCode.STOCK_REDUCE_FAILED_ERROR.getMemo());
				}
			}
			if (resultDto.getResultFlag().equals(Constants.PCM_OPERATION_SUCCEED)) {
				boolean isDel = pcmStockLockService.delStockRecordBySalesItemNo(dto.getSaleNo());
				logger.info("减库，锁库记录移库" + isDel + "，销售单号：" + dto.getSaleNo());
			}
		} else if (dto.getCzType() == Constants.PCMSTOCK_OPERATION_TYPE2) {// 解锁
			for (StockProCountDto stockProCountDto : products) {
				flag = false;

				logger.info("para:" + stockProCountDto.toString());
				// 获得锁定库存的数量
				Integer lockCount = pcmStockLockService
						.selectLockCountByShoppePro(stockProCountDto);

				// 判断解锁数量是否等于锁库数量
				if (lockCount == null || !lockCount.equals(stockProCountDto.getSaleSum())) {
					logger.info(ErrorCode.STOCK_REDUCE_DIFFER_ERROR.getMemo());
					throw new BleException(ErrorCode.STOCK_NO_UNLOCK_DIFFER_ERROR.getErrorCode(),
							ErrorCode.STOCK_NO_UNLOCK_DIFFER_ERROR.getMemo());
				}
				PcmStockDto record = new PcmStockDto();
				record.setShoppeProSid(stockProCountDto.getSupplyProductNo());
				record.setStockTypeSid(Constants.PCMSTOCK_TYPE_SALE);
				if (StringUtils.isNotBlank(stockProCountDto.getChannelSid())) {
					record.setChannelSid(stockProCountDto.getChannelSid());
				}
				// 查询可售库存数
				Integer stockCount = findStockCountFromPcm(record);
				// 获得库存SID
				int recordId = pcmStockMapper.selectSidByShoppeProSid(record);
				// 可售库增加
				record.setProSum((long) stockProCountDto.getSaleSum());
				flag = addtOrReduce(record);
				if (flag) {
					// 锁定库减少
					record.setStockTypeSid(Constants.PCMSTOCK_TYPE_LOCK);
					record.setProSum(-(long) stockProCountDto.getSaleSum());
					flag = addtOrReduce(record);
					if (flag) {
						// 添加变动记录
						flag = pcmStockChangeRecordService.addChangeRecord(stockProCountDto,
								recordId, stockCount, dto.getFromSystem());
						if (flag) {
							// 添加解锁记录
							stockProCountDto.setSaleNo(dto.getSaleNo());
							flag = pcmStockLockService.unlockStock(stockProCountDto);
						}
					}
				}
				if (flag) {
					resultDto.setResultFlag(Constants.PCM_OPERATION_SUCCEED);
					resultDto.setResultMsg(ErrorCode.STOCK_UNLOCK_SUCCEED.getMemo());
					stockProResultDto = new StockProResultDto();
					stockProResultDto.setSupplyProductNo(stockProCountDto.getSupplyProductNo());
					stockProResultDto.setProSum(stockCount + stockProCountDto.getSaleSum());
					if (StringUtils.isNotBlank(stockProCountDto.getChannelSid())) {
						stockProResultDto.setChannelSid(stockProCountDto.getChannelSid());
					} else {
						stockProResultDto.setChannelSid(Constants.DEFAULT_CHANNEL_SID);
					}
					redisList.add(stockProResultDto);
				} else {
					logger.info(ErrorCode.STOCK_UNLOCK_FAILED_ERROR.getMemo());
					throw new BleException(ErrorCode.STOCK_UNLOCK_FAILED_ERROR.getErrorCode(),
							ErrorCode.STOCK_UNLOCK_FAILED_ERROR.getMemo());
				}
			}

			if (resultDto.getResultFlag().equals(Constants.PCM_OPERATION_SUCCEED)) {
				for (StockProResultDto redisDto : redisList) {
					RedisVo vo = new RedisVo();
					vo.setValue(redisDto.getProSum().toString());
					vo.setKey(redisDto.getSupplyProductNo() + redisDto.getChannelSid());
					vo.setField(DomainName.getStock);
					vo.setType(CacheUtils.HSET);
					CacheUtils.setRedisData(vo);
					logger.info("pcm_getStock Redis key:" + redisDto.getSupplyProductNo()
							+ ",value:" + redisDto.getProSum());
				}

				boolean isDel = pcmStockLockService.delStockRecordBySalesItemNo(dto.getSaleNo());
				logger.info("减库，锁库记录移库" + isDel + "，销售单号：" + dto.getSaleNo());
			}
		} else if (dto.getCzType() == Constants.PCMSTOCK_OPERATION_TYPE3) {// 还库
			for (StockProCountDto stockProCountDto : products) {
				flag = false;
				logger.info("para:" + stockProCountDto.toString());
				PcmStockDto record = new PcmStockDto();
				record.setShoppeProSid(stockProCountDto.getSupplyProductNo());
				record.setStockTypeSid(Constants.PCMSTOCK_TYPE_SALE);
				if (StringUtils.isNotBlank(stockProCountDto.getChannelSid())) {
					record.setChannelSid(stockProCountDto.getChannelSid());
				}
				int recordId = pcmStockMapper.selectSidByShoppeProSid(record);
				record.setStockTypeSid(Constants.PCMSTOCK_TYPE_RETURN);
				record.setProSum((long) stockProCountDto.getSaleSum());
				// 库存数
				Integer stockCount = (int) findStockCountFromPcm(record);

				flag = addtOrReduce(record);
				if (flag) {
					flag = pcmStockChangeRecordService.addChangeRecord(stockProCountDto, recordId,
							stockCount, dto.getFromSystem());
				}
				if (flag) {
					resultDto.setResultFlag(Constants.PCM_OPERATION_SUCCEED);
					resultDto.setResultMsg(ErrorCode.STOCK_REFUND_SUCCEED.getMemo());
				} else {
					logger.info(ErrorCode.STOCK_REFUND_FAILED_ERROR.getMemo());
					throw new BleException(ErrorCode.STOCK_REFUND_FAILED_ERROR.getErrorCode(),
							ErrorCode.STOCK_REFUND_FAILED_ERROR.getMemo());
				}
			}
		} else if (dto.getCzType() == Constants.PCMSTOCK_OPERATION_TYPE4) {// 借出、调出
			for (StockProCountDto stockProCountDto : products) {
				stockProResultDto = new StockProResultDto();
				stockProResultDto.setSupplyProductNo(stockProCountDto.getSupplyProductNo());
				stockProResultDto.setChannelSid(Constants.DEFAULT_CHANNEL_SID);
				logger.info("para:" + stockProCountDto.toString());
				PcmStockDto record = new PcmStockDto();
				record.setShoppeProSid(stockProCountDto.getSupplyProductNo());
				record.setStockTypeSid(Constants.PCMSTOCK_TYPE_SALE);

				/* 可售数 */
				Integer stockCount = (int) findStockCountFromPcm(record);
				/* 获得SID */
				int recordId = pcmStockMapper.selectSidByShoppeProSid(record);
				if (StringUtils.isNotBlank(stockProCountDto.getChannelSid())) {
					record.setChannelSid(stockProCountDto.getChannelSid());
					stockProResultDto.setChannelSid(stockProCountDto.getChannelSid());
				}

				/* 借出、调出 、报残、转团、返厂 */
				if (Arrays.asList(Constants.PCMSTOCK_SALE_REDUCE)
						.contains(stockProCountDto.getStockType())) {
					if (stockCount < stockProCountDto.getSaleSum()) {
						logger.info(ErrorCode.STOCK_TRANSFER_ERROR.getMemo());
						throw new BleException(ErrorCode.STOCK_TRANSFER_ERROR.getErrorCode(),
								ErrorCode.STOCK_TRANSFER_ERROR.getMemo());
					}
					record.setProSum(-(long) stockProCountDto.getSaleSum());
					stockProResultDto.setProSum(stockCount - stockProCountDto.getSaleSum());
				} else if (Arrays.asList(Constants.PCMSTOCK_SALE_INCREASE)
						.contains(stockProCountDto.getStockType())) { // 归还、调入
					record.setProSum((long) stockProCountDto.getSaleSum());
					stockProResultDto.setProSum(stockCount + stockProCountDto.getSaleSum());
					if (StringUtils.isNotBlank(stockProCountDto.getChannelSid())
							&& !Constants.DEFAULT_CHANNEL_SID
									.equals(stockProCountDto.getChannelSid())) {
						PcmStockDto record1 = new PcmStockDto();
						record1.setShoppeProSid(stockProCountDto.getSupplyProductNo());
						record1.setStockTypeSid(Constants.PCMSTOCK_TYPE_SALE);
						record1.setChannelSid(stockProCountDto.getChannelSid());
						stockCount = (int) findStockCountFromPcm(record1);
						stockProResultDto.setProSum(stockCount + stockProCountDto.getSaleSum());
					}
				}
				/* 添加变动记录、改变可售库 */
				flag = addtOrReduce(record);
				if (flag) {
					flag = pcmStockChangeRecordService.addChangeRecord(stockProCountDto, recordId,
							stockCount, dto.getFromSystem());
				}
				if (flag) {
					redisList.add(stockProResultDto);
					resultDto.setResultFlag(Constants.PCM_OPERATION_SUCCEED);
					resultDto.setResultMsg(ErrorCode.STOCK_OPERATION_SUCCEED.getMemo());
				} else {
					logger.info(ErrorCode.STOCK_OPERATION_FAILED_ERROR.getMemo());
					throw new BleException(ErrorCode.STOCK_OPERATION_FAILED_ERROR.getErrorCode(),
							ErrorCode.STOCK_OPERATION_FAILED_ERROR.getMemo());
				}
			}
			if (flag) {
				for (StockProResultDto redisDto : redisList) {
					RedisVo vo = new RedisVo();
					vo.setValue(redisDto.getProSum().toString());
					vo.setKey(redisDto.getSupplyProductNo() + redisDto.getChannelSid());
					vo.setField(DomainName.getStock);
					vo.setType(CacheUtils.HSET);
					CacheUtils.setRedisData(vo);
					logger.info("pcm_getStock Redis key:" + redisDto.getSupplyProductNo()
							+ ",value:" + redisDto.getProSum());
				}
			}
		} else if (dto.getCzType() == Constants.PCMSTOCK_OPERATION_TYPE5)

		{// 退货库变更
			for (StockProCountDto stockProCountDto : products) {
				logger.info("para:" + stockProCountDto.toString());
				stockProResultDto = new StockProResultDto();
				stockProResultDto.setChannelSid(Constants.DEFAULT_CHANNEL_SID);
				PcmStockDto record = new PcmStockDto();
				record.setShoppeProSid(stockProCountDto.getSupplyProductNo());
				record.setStockTypeSid(stockProCountDto.getStockType());
				if (StringUtils.isNotBlank(stockProCountDto.getChannelSid())) {
					record.setChannelSid(stockProCountDto.getChannelSid());
					stockProResultDto.setChannelSid(stockProCountDto.getChannelSid());
				}
				// 库存数
				Integer stockCount = (int) findStockCountFromPcm(record);
				if (stockProCountDto.getStockType().equals(Constants.PCMSTOCK_TYPE_SALE)) {
					/* 可售数 */
					stockProResultDto.setSupplyProductNo(stockProCountDto.getSupplyProductNo());
					stockProResultDto.setProSum(stockCount + stockProCountDto.getSaleSum());
				}
				// 变更库存数 /
				record.setProSum((long) stockProCountDto.getSaleSum());
				// 添加变动记录、改变指定库存数
				flag = addtOrReduce(record);
				if (flag) {
					PcmStockDto record1 = new PcmStockDto();
					record1.setShoppeProSid(record.getShoppeProSid());
					record1.setStockTypeSid(record.getStockTypeSid());
					// 变动库位SID
					int recordId = pcmStockMapper.selectSidByShoppeProSid(record1);
					flag = pcmStockChangeRecordService.addChangeRecord(stockProCountDto, recordId,
							stockCount, dto.getFromSystem());
				}
				if (flag) {
					resultDto.setResultFlag(Constants.PCM_OPERATION_SUCCEED);
					resultDto.setResultMsg(ErrorCode.STOCK_OPERATION_SUCCEED.getMemo());
					if (stockProCountDto.getStockType().equals(Constants.PCMSTOCK_TYPE_SALE)) {
						redisList.add(stockProResultDto);
					}
				} else {
					logger.info(ErrorCode.STOCK_OPERATION_FAILED_ERROR.getMemo());
					throw new BleException(ErrorCode.STOCK_OPERATION_FAILED_ERROR.getErrorCode(),
							ErrorCode.STOCK_OPERATION_FAILED_ERROR.getMemo());
				}
			}
			if (flag) {
				for (StockProResultDto redisDto : redisList) {
					RedisVo vo = new RedisVo();
					vo.setValue(redisDto.getProSum().toString());
					vo.setKey(redisDto.getSupplyProductNo() + redisDto.getChannelSid());
					vo.setField(DomainName.getStock);
					vo.setType(CacheUtils.HSET);
					CacheUtils.setRedisData(vo);
					logger.info("pcm_getStock Redis key:" + redisDto.getSupplyProductNo()
							+ ",value:" + redisDto.getProSum());
				}
			}
		}
		return resultDto;

	}

	public StockResultDto findInsertAndReduceFromPcmByOffLine(StockProCountListDto dto) {
		logger.info("start,findInsertAndReduceFromPcmV3,para:" + dto.toString());
		StockResultDto resultDto = new StockResultDto();
		List<StockProCountDto> products = dto.getProducts();
		boolean flag = false;
		List<StockProResultDto> redisList = new ArrayList<StockProResultDto>();
		StockProResultDto stockProResultDto = null;
		redisList.clear();

		// 判断操作类型 0 - 锁库 - 是否允许负库存销售 - 判断可售数量是否大于锁定数量 -添加锁定记录、添加变动记录、可售库减少，锁定库增加
		// 1 - 减库 - 是否已锁定 - 判断减库数量是否等于锁库数量 -可售库不变，锁定库减少、添加解锁记录
		if (dto.getCzType() == Constants.PCMSTOCK_OPERATION_TYPE0) {// 锁库
			List<StockProResultDto> stockProResultDtoList = new ArrayList<StockProResultDto>();
			boolean isSucceed = true;
			for (StockProCountDto stockProCountDto : products) {
				stockProCountDto.setSaleNo(dto.getSaleNo());
				// 是否负库存销售 ：false 允许，true不允许
				boolean isNegativeStock = checkIsNegativeStock(
						stockProCountDto.getSupplyProductNo());
				stockProResultDto = new StockProResultDto();
				try {
					stockProResultDto = saveOffLineLockStockInfo(stockProCountDto, isNegativeStock,
							dto.getFromSystem());
					redisList.add(stockProResultDto);
				} catch (Exception e) {
					stockProResultDto.setSupplyProductNo(stockProCountDto.getSupplyProductNo());
					stockProResultDto.setProductName(stockProCountDto.getShoppeProName());
					stockProResultDto.setOutOfStock(0);
					stockProResultDto.setResultFlag(Constants.PCM_OPERATION_FAILED);
					stockProResultDto.setResultMsg(ErrorCode.STOCK_LOCK_FAILED_ERROR.getMemo());
				}
				if (stockProResultDto.getResultFlag().equals(Constants.PUBLIC_0.toString())) {
					stockProResultDtoList.add(stockProResultDto);
					isSucceed = false;
				}
			}
			if (isSucceed) {
				for (StockProResultDto redisDto : redisList) {
					RedisVo vo = new RedisVo();
					vo.setValue(redisDto.getProSum().toString());
					vo.setKey(redisDto.getSupplyProductNo() + redisDto.getChannelSid());
					vo.setField(DomainName.getStock);
					vo.setType(CacheUtils.HSET);
					CacheUtils.setRedisData(vo);
					logger.info("pcm_getStock Redis key:" + redisDto.getSupplyProductNo()
							+ ",value:" + redisDto.getProSum());
				}
				resultDto.setResultFlag(Constants.PCM_OPERATION_SUCCEED);
				resultDto.setResultMsg(ErrorCode.STOCK_LOCK_SUCCEED.getMemo());
			} else {
				throw new BleException(
						ComErrorCodeConstants.ErrorCode.STOCK_LOCK_FAILED_ERROR.getErrorCode(),
						JsonUtil.getJSONString(stockProResultDtoList));
			}
		} else if (dto.getCzType() == Constants.PCMSTOCK_OPERATION_TYPE1) {// 减库
			for (StockProCountDto stockProCountDto : products) {
				logger.info("para:" + stockProCountDto.toString());
				flag = false;
				stockProResultDto = new StockProResultDto();
				// 获得锁定库存的数量
				Integer lockCount = pcmStockLockService
						.selectLockCountByShoppePro(stockProCountDto);
				if (lockCount != 0 && !lockCount.equals(stockProCountDto.getSaleSum())) {// 判断减库数量是否等于锁库数量
					logger.info(ErrorCode.STOCK_REDUCE_DIFFER_ERROR.getMemo());
					throw new BleException(ErrorCode.STOCK_REDUCE_DIFFER_ERROR.getErrorCode(),
							ErrorCode.STOCK_REDUCE_DIFFER_ERROR.getMemo());
				}
				// 减库操作 可售库不变，锁定库减少、添加解锁记录
				PcmStockDto record = new PcmStockDto();
				record.setShoppeProSid(stockProCountDto.getSupplyProductNo());
				stockProResultDto.setSupplyProductNo(stockProCountDto.getSupplyProductNo());
				if (StringUtils.isNotBlank(stockProCountDto.getChannelSid())) {
					record.setChannelSid(stockProCountDto.getChannelSid());
					stockProResultDto.setChannelSid(stockProCountDto.getChannelSid());
				}

				if (lockCount == 0) {
					record.setStockTypeSid(Constants.PCMSTOCK_TYPE_SALE);
					Integer stockCount = findStockCountFromPcm(record);// 可售库存
					Integer recordId = pcmStockMapper.selectSidByShoppeProSid(record);// 获得库存SID
					boolean isNegativeStock = checkIsNegativeStock(
							stockProCountDto.getSupplyProductNo());
					record.setProSum(-(long) stockProCountDto.getSaleSum());
					stockProResultDto.setProSum(stockCount - stockProCountDto.getSaleSum());
					if (isNegativeStock) {
						if (stockCount < stockProCountDto.getSaleSum()) {
							record.setProSum(-(long) stockCount);
							stockProResultDto.setProSum(0);
							stockProResultDto
									.setOutOfStock(stockProCountDto.getSaleSum() - stockCount);
							stockProResultDto
									.setResultMsg(ErrorCode.STOCK_OUTOFSTOCK_ERROR.getMemo());
						}
					}
					flag = addtOrReduce(record);
					if (flag) {
						if (flag) {
							flag = pcmStockChangeRecordService.addChangeRecord(stockProCountDto,
									recordId, stockCount, dto.getFromSystem());// 添加变动记录
						}
					}
				} else {
					record.setStockTypeSid(Constants.PCMSTOCK_TYPE_LOCK);
					record.setProSum(-(long) stockProCountDto.getSaleSum());
					flag = addtOrReduce(record);
					if (flag) {
						stockProCountDto.setSaleNo(dto.getSaleNo());
						flag = pcmStockLockService.unlockStock(stockProCountDto);
					}
				}
				if (flag) {
					if (lockCount == 0) {
						redisList.add(stockProResultDto);
					}
					resultDto.setResultFlag(Constants.PCM_OPERATION_SUCCEED);
					resultDto.setResultMsg(ErrorCode.STOCK_REDUCE_SUCCEED.getMemo());
				} else {
					logger.info(ErrorCode.STOCK_REDUCE_FAILED_ERROR.getMemo());
					throw new BleException(ErrorCode.STOCK_REDUCE_FAILED_ERROR.getErrorCode(),
							ErrorCode.STOCK_REDUCE_FAILED_ERROR.getMemo());
				}
			}
			if (resultDto.getResultFlag().equals(Constants.PCM_OPERATION_SUCCEED)) {
				boolean isDel = pcmStockLockService.delStockRecordBySalesItemNo(dto.getSaleNo());
				logger.info("减库，锁库记录移库" + isDel + "，销售单号：" + dto.getSaleNo());
			}
		}
		if (flag) {
			for (StockProResultDto redisDto : redisList) {
				RedisVo vo = new RedisVo();
				vo.setValue(redisDto.getProSum().toString());
				vo.setKey(redisDto.getSupplyProductNo() + redisDto.getChannelSid());
				vo.setField(DomainName.getStock);
				vo.setType(CacheUtils.HSET);
				CacheUtils.setRedisData(vo);
				logger.info("pcm_getStock Redis key:" + redisDto.getSupplyProductNo() + ",value:"
						+ redisDto.getProSum());
			}
		}
		return resultDto;
	}

	/**
	 * 添加变动记录
	 *
	 * @param record
	 * @return boolean
	 * @Methods Name addtOrReduce
	 * @Create In 2015年8月27日 By kongqf
	 */
	private boolean addtOrReduce(PcmStockDto record) {
		boolean flag = false;
		Integer status = pcmStockMapper.addOrReduce(record);
		if (status > Constants.PUBLIC_0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 锁库
	 *
	 * @param StockProCountListDto
	 * @param dto
	 *            StockResultDto
	 * @throws Exception
	 * @Methods Name findStockLockFromPcm
	 * @Create In 2015年7月28日 By yedong
	 */
	public String findStockLockFromPcm(StockProCountListDto dto) {
		logger.info("start findStockLockFromPcm(),param" + dto.toString());
		List<StockProCountDto> products = dto.getProducts();
		String stockCore = PropertyUtil.getSystemUrl("stockCore");
		/* 设置操作类型 */
		dto.setCzType(Constants.PCMSTOCK_OPERATION_TYPE0);
		List<StockProCountDto> list = stockPublicMethod1(products);
		dto.setProducts(list);
		/* 调入core进行第二步判断及加减库操作 */
		System.out.println(JsonUtil.getJSONString(dto));
		String response = HttpUtil.doPost(stockCore, JsonUtil.getJSONString(dto));
		return response;
	}

	/**
	 * 减库
	 *
	 * @param dto
	 * @return StockResultDto
	 * @throws Exception
	 * @Methods Name findStockReduceFromPcm
	 * @Create In 2015年7月21日 By yedong
	 */
	public String findStockReduceFromPcm(StockProCountListDto dto) {
		logger.info("start findStockLockFromPcm(),param" + dto.toString());
		List<StockProCountDto> products = dto.getProducts();
		String stockCore = PropertyUtil.getSystemUrl("stockCore");
		/* 设置操作类型 */
		dto.setCzType(Constants.PCMSTOCK_OPERATION_TYPE1);
		List<StockProCountDto> list = stockPublicMethod1(products);
		dto.setProducts(list);
		/* 调入core进行第二步判断及加减库操作 */
		String response = HttpUtil.doPost(stockCore, JsonUtil.getJSONString(dto));
		return response;
	}

	/**
	 * 解锁
	 *
	 * @param dto
	 * @return StockResultDto
	 * @throws Exception
	 * @Methods Name findStockUnLockFromPcm
	 * @Create In 2015年7月21日 By yedong
	 */
	public String findStockUnLockFromPcm(StockProCountListDto dto) {
		logger.info("start findStockLockFromPcm(),param" + dto.toString());
		List<StockProCountDto> products = dto.getProducts();
		String stockCore = PropertyUtil.getSystemUrl("stockCore");
		/* 设置操作类型 */
		dto.setCzType(Constants.PCMSTOCK_OPERATION_TYPE2);
		List<StockProCountDto> list = stockPublicMethod1(products);
		dto.setProducts(list);
		/* 调入core进行第二步判断及加减库操作 */
		String response = HttpUtil.doPost(stockCore, JsonUtil.getJSONString(dto));
		return response;
	}

	/**
	 * 还库
	 *
	 * @param dto
	 * @return StockResultDto
	 * @throws Exception
	 * @Methods Name findStockRefundFromPcm
	 * @Create In 2015年7月21日 By yedong
	 */
	public String findStockRefundFromPcm(StockProCountListDto dto) {
		logger.info("start findStockRefundFromPcm(),param" + dto.toString());
		List<StockProCountDto> products = dto.getProducts();
		String stockCore = PropertyUtil.getSystemUrl("stockCore");
		/* 设置操作类型 */
		dto.setCzType(Constants.PCMSTOCK_OPERATION_TYPE3);
		List<StockProCountDto> list = stockPublicMethod1(products);
		dto.setProducts(list);
		/* 调入core进行第二步判断及加减库操作 */
		String response = HttpUtil.doPost(stockCore, JsonUtil.getJSONString(dto));
		return response;
	}

	/**
	 * 调入、调出、借出、归还
	 *
	 * @param dto
	 * @return StockResultDto
	 * @throws Exception
	 * @Methods Name findStockTransferFromPcm
	 * @Create In 2015年7月30日 By yedong
	 */
	public String findStockTransferFromPcm(StockProCountListDto dto) {
		logger.info("start findStockTransferFromPcm(),param" + dto.toString());
		List<StockProCountDto> products = dto.getProducts();
		String stockCore = PropertyUtil.getSystemUrl("stockCore");
		/* 设置操作类型 */
		dto.setCzType(Constants.PCMSTOCK_OPERATION_TYPE4);
		List<StockProCountDto> list = stockPublicMethod1(products);
		dto.setProducts(list);
		/* 调入core进行第二步判断及加减库操作 */
		String response = HttpUtil.doPost(stockCore, JsonUtil.getJSONString(dto));
		return response;
	}

	/**
	 * 修改类型
	 *
	 * @param paramList
	 * @return StockResultDto
	 * @throws Exception
	 * @Methods Name findStockTypeUpdateFromPcm
	 * @Create In 2015年7月30日 By yedong
	 */
	public String findStockTypeUpdateFromPcm(List<Map<String, Object>> paramList) throws Exception {
		logger.info("start findStockTypeUpdateFromPcm(),param" + paramList.toString());
		String stockCore = PropertyUtil.getSystemUrl("stockCore");
		StockProCountListDto dtoList = new StockProCountListDto();
		dtoList.setCzType(Constants.PUBLIC_5);
		List<StockProCountDto> products = new ArrayList<StockProCountDto>();
		StockResultDto resultDto = new StockResultDto();
		for (Map<String, Object> paramMap : paramList) {
			/* 查询该类型的库存数量 */
			List<PcmStock> list = pcmStockMapper.selectListByParam(paramMap);
			if (list.size() > Constants.PUBLIC_0) {

				Long proSum = list.get(Constants.PUBLIC_0).getProSum();
				PcmStockDto dto = new PcmStockDto();
				dto.setShoppeProSid(paramMap.get("shoppeProSid").toString());
				dto.setProSum(-Long.valueOf((Integer) paramMap.get("proSums")));
				dto.setStockTypeSid((Integer) paramMap.get("stockTypeSid"));

				if (-dto.getProSum() <= proSum) {
					StockProCountDto stockProCountDto1 = new StockProCountDto();
					StockProCountDto stockProCountDto2 = new StockProCountDto();
					stockProCountDto1.setSupplyProductNo(dto.getShoppeProSid());
					stockProCountDto1.setSaleSum(dto.getProSum().intValue());
					stockProCountDto1.setStockType(dto.getStockTypeSid());
					if (dto.getChannelSid() != null) {
						stockProCountDto1.setChannelSid(dto.getChannelSid() + "");
					}
					products.add(stockProCountDto1);
					stockProCountDto2.setSupplyProductNo(dto.getShoppeProSid());
					stockProCountDto2.setSaleSum(-dto.getProSum().intValue());
					stockProCountDto2.setStockType((Integer) paramMap.get("newType"));
					if (dto.getChannelSid() != null) {
						stockProCountDto2.setChannelSid(dto.getChannelSid() + "");
					}
					products.add(stockProCountDto2);
				} else {
					resultDto.setResultFlag("" + Constants.PUBLIC_0);
					resultDto.setResultMsg("退货库不足");
					throw new BleException(
							ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
							ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
				}
			} else {
				resultDto.setResultFlag("" + Constants.PUBLIC_0);
				resultDto.setResultMsg("无退货库");
				throw new BleException(
						ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
						ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
			}
		}
		dtoList.setProducts(products);
		String response = HttpUtil.doPost(stockCore, JsonUtil.getJSONString(dtoList));
		return response;
	}

	public String findStockTypeUpdateFromPcmV2(List<PcmStockChangeDto> pcmStockChangeDtos) {
		logger.info("start findStockTypeUpdateFromPcm(),param" + pcmStockChangeDtos.toString());

		String errMsg = validChangeStockRequestData(pcmStockChangeDtos);
		if (StringUtils.isNotBlank(errMsg)) {
			throw new BleException(ErrorCode.STOCK_TRANSFER_ERROR.getErrorCode(), errMsg);
		}

		String stockCore = PropertyUtil.getSystemUrl("stockCore");
		StockProCountListDto dtoList = new StockProCountListDto();
		dtoList.setCzType(Constants.PCMSTOCK_OPERATION_TYPE5);

		List<StockProCountDto> products = new ArrayList<StockProCountDto>();

		for (PcmStockChangeDto dto : pcmStockChangeDtos) {
			dtoList.setFromSystem(dto.getFromSystem());
			// 查询库存数量
			PcmStockDto pcmStockDto = new PcmStockDto();
			pcmStockDto.setShoppeProSid(dto.getShoppeProSid());
			pcmStockDto.setStockTypeSid(dto.getStockTypeSid());
			pcmStockDto.setChannelSid(dto.getChannelSid());
			int proSum = findStockCountFromPcm(pcmStockDto);

			if (-dto.getProSum() <= proSum) {
				StockProCountDto stockProCountDto1 = new StockProCountDto();
				StockProCountDto stockProCountDto2 = new StockProCountDto();

				stockProCountDto1.setSupplyProductNo(dto.getShoppeProSid());
				stockProCountDto1.setSaleSum(-dto.getProSum());
				stockProCountDto1.setStockType(dto.getStockTypeSid());
				stockProCountDto1.setChannelSid(dto.getChannelSid());
				products.add(stockProCountDto1);

				stockProCountDto2.setSupplyProductNo(dto.getShoppeProSid());
				stockProCountDto2.setSaleSum(dto.getProSum());
				stockProCountDto2.setStockType(dto.getNewStockType());
				stockProCountDto2.setChannelSid(dto.getChannelSid());
				products.add(stockProCountDto2);
			} else {
				throw new BleException(ErrorCode.STOCK_TRANSFER_ERROR.getErrorCode(),
						ErrorCode.STOCK_TRANSFER_ERROR.getMemo());
			}
		}
		dtoList.setProducts(products);
		String response = HttpUtil.doPost(stockCore, JsonUtil.getJSONString(dtoList));
		return response;
	}

	/**
	 * 库存清零
	 *
	 * @param shoppeProSid
	 *            void
	 * @Methods Name stockEmpty
	 * @Create In 2015年8月11日 By yedong
	 */
	public StockResultDto stockEmpty(String shoppeProSid) {
		logger.info("start findReduceStockFromPcm()");
		StockResultDto resultDto = new StockResultDto();
		PcmStock record = new PcmStock();
		record.setShoppeProSid(shoppeProSid);
		record.setProSum((long) Constants.PUBLIC_0);
		record.setLockCount((long) Constants.PUBLIC_0);
		Integer status = pcmStockMapper.updateByParamSelective(record);
		if (status == Constants.PUBLIC_0) {
			logger.info("");
			throw new BleException(ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
		}
		resultDto.setResultFlag("" + Constants.PUBLIC_1);
		resultDto.setResultMsg("成功");
		return resultDto;
	}

	/**
	 * 根据专柜商品编号查询是否允许负库存销售
	 *
	 * @param supplyProductNo
	 * @return boolean false 允许 true 不允许
	 * @Methods Name checkIsNegativeStock
	 * @Create In 2015年8月25日 By kongqf
	 */
	private boolean checkIsNegativeStock(String supplyProductNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map = pcmShoppeProSid.selectStockInfo(supplyProductNo);
		if (((Integer) map.get("negative")).equals(0)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 根据专柜商品编号查询是否管库存
	 *
	 * @param supplyProductNo
	 * @return boolean
	 * @Methods Name checkIsStock
	 * @Create In 2015年9月9日 By kongqf
	 */
	private boolean checkIsStock(String supplyProductNo) {
		Map<String, Object> map = pcmShoppeProSid.selectStockInfo(supplyProductNo);
		/* 是否管库存 0管 1不管 */
		if (((Integer) map.get("isStock")).equals(0)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 联网并且不允许负库存销售锁库
	 *
	 * @param stockProCountDto
	 * @return StockProResultDto
	 * @Methods Name saveNotOffLineLockStockInfo
	 * @Create In 2015年8月26日 By kongqf
	 */
	private StockProResultDto saveNotOffLineLockStockInfo(StockProCountDto stockProCountDto,
			String operator) {
		logger.info("para:" + stockProCountDto.toString());
		StockProResultDto stockProResultDto = new StockProResultDto();
		stockProResultDto.setSupplyProductNo(stockProCountDto.getSupplyProductNo());
		stockProResultDto.setProductName(stockProCountDto.getShoppeProName());
		stockProResultDto.setOutOfStock(0);
		stockProResultDto.setResultFlag(Constants.PCM_OPERATION_FAILED);
		stockProResultDto.setChannelSid(Constants.DEFAULT_CHANNEL_SID);
		PcmStockDto record = new PcmStockDto();
		record.setShoppeProSid(stockProCountDto.getSupplyProductNo());
		record.setStockTypeSid(Constants.PCMSTOCK_TYPE_SALE);

		// 查询可售库存数
		Integer stockCount = findStockCountFromPcm(record);
		stockProResultDto.setProSum(stockCount);
		// 判断锁定数量是否大于可售数量
		if (stockCount < stockProCountDto.getSaleSum()) {
			logger.info(ErrorCode.STOCK_OUTOFSTOCK_ERROR.getMemo());
			stockProResultDto.setOutOfStock(stockProCountDto.getSaleSum() - stockCount);
			stockProResultDto.setResultMsg(ErrorCode.STOCK_OUTOFSTOCK_ERROR.getMemo());

		} else {
			if (StringUtils.isNotBlank(stockProCountDto.getChannelSid())) {
				record.setChannelSid(stockProCountDto.getChannelSid());
				stockProResultDto.setChannelSid(stockProCountDto.getChannelSid());
			}
			// 获得库存SID
			Integer recordId = pcmStockMapper.selectSidByShoppeProSid(record);
			// 添加锁定记录、添加变动记录、可售库减少，锁定库增加
			Integer lockStatus = pcmStockLockService.lockStock(stockProCountDto);
			boolean flag = false;
			if (1 == lockStatus) {
				/* 可售库减少，锁定库增加 */
				record.setProSum(-(long) stockProCountDto.getSaleSum());
				stockProResultDto.setProSum(stockCount - stockProCountDto.getSaleSum());
				flag = addtOrReduce(record);
				if (flag) {
					record.setStockTypeSid(Constants.PCMSTOCK_TYPE_LOCK);
					record.setProSum((long) stockProCountDto.getSaleSum());
					flag = addtOrReduce(record);
					if (flag) {
						// 添加变动记录
						flag = pcmStockChangeRecordService.addChangeRecord(stockProCountDto,
								recordId, stockCount, operator);
						lockStatus = 0;
					}
				}
			}
			if ((flag && 0 == lockStatus) || 0 == lockStatus) {
				logger.info(ErrorCode.STOCK_LOCK_SUCCEED.getMemo());
				stockProResultDto.setResultMsg(ErrorCode.STOCK_LOCK_SUCCEED.getMemo());
				stockProResultDto.setResultFlag(Constants.PCM_OPERATION_SUCCEED);
			} else {
				logger.info(ErrorCode.STOCK_LOCK_FAILED_ERROR.getMemo());
				stockProResultDto.setResultMsg(ErrorCode.STOCK_LOCK_FAILED_ERROR.getMemo());
			}
		}
		return stockProResultDto;
	}

	/**
	 * 脱机或允许负库存销售锁库
	 *
	 * @param stockProCountDto
	 * @param isNegativeStock
	 * @return StockProResultDto
	 * @Methods Name saveOffLineLockStockInfo
	 * @Create In 2015年8月26日 By kongqf
	 */
	private StockProResultDto saveOffLineLockStockInfo(StockProCountDto stockProCountDto,
			Boolean isNegativeStock, String operator) {
		logger.info("para:" + stockProCountDto.toString() + ",isNegativeStock:" + isNegativeStock);
		StockProResultDto stockProResultDto = new StockProResultDto();
		stockProResultDto.setSupplyProductNo(stockProCountDto.getSupplyProductNo());
		stockProResultDto.setProductName(stockProCountDto.getShoppeProName());
		stockProResultDto.setOutOfStock(0);
		stockProResultDto.setResultFlag(Constants.PCM_OPERATION_FAILED);
		stockProResultDto.setChannelSid(Constants.DEFAULT_CHANNEL_SID);

		PcmStockDto record = new PcmStockDto();
		record.setShoppeProSid(stockProCountDto.getSupplyProductNo());
		record.setStockTypeSid(Constants.PCMSTOCK_TYPE_SALE);
		if (StringUtils.isNotBlank(stockProCountDto.getChannelSid())) {
			record.setChannelSid(stockProCountDto.getChannelSid());
			stockProResultDto.setChannelSid(stockProCountDto.getChannelSid());
		}

		// 查询可售库存数
		Integer stockCount = findStockCountFromPcm(record);
		stockProResultDto.setProSum(stockCount);
		// 获得库存SID
		Integer recordId = pcmStockMapper.selectSidByShoppeProSid(record);
		// 添加锁定记录、添加变动记录、可售库减少，锁定库增加
		Integer lockStatus = pcmStockLockService.lockStock(stockProCountDto);
		boolean flag = false;
		if (1 == lockStatus) {
			/* 可售库减少，锁定库增加 */
			record.setProSum(-(long) stockProCountDto.getSaleSum());
			stockProResultDto.setProSum(stockCount - stockProCountDto.getSaleSum());
			if (isNegativeStock) {
				if (stockCount < stockProCountDto.getSaleSum()) {
					stockProResultDto.setProSum(0);
					record.setProSum(-(long) stockCount);
					stockProResultDto.setOutOfStock(stockProCountDto.getSaleSum() - stockCount);
					stockProResultDto.setResultMsg(ErrorCode.STOCK_OUTOFSTOCK_ERROR.getMemo());
				}
			}
			flag = addtOrReduce(record);
			if (flag) {
				record.setStockTypeSid(Constants.PCMSTOCK_TYPE_LOCK);
				record.setProSum((long) stockProCountDto.getSaleSum());
				flag = addtOrReduce(record);
				if (flag) {
					// 添加变动记录
					flag = pcmStockChangeRecordService.addChangeRecord(stockProCountDto, recordId,
							stockCount, operator);
					lockStatus = 0;
				}
			}
		}
		if ((flag && 0 == lockStatus) || 0 == lockStatus) {
			logger.info(ErrorCode.STOCK_LOCK_SUCCEED.getMemo());
			stockProResultDto.setResultMsg(ErrorCode.STOCK_LOCK_SUCCEED.getMemo());
			stockProResultDto.setResultFlag(Constants.PCM_OPERATION_SUCCEED);
		} else {
			logger.info(ErrorCode.STOCK_LOCK_FAILED_ERROR.getMemo());
			stockProResultDto.setResultMsg(ErrorCode.STOCK_LOCK_FAILED_ERROR.getMemo());
		}

		return stockProResultDto;
	}

	/**
	 * 根据专柜商品编码和渠道查询库位信息
	 *
	 * @param record
	 * @return List<PcmStock>
	 * @Methods Name selectShoppeProStockInfo
	 * @Create In 2015年9月16日 By kongqf
	 */
	@Override
	public List<PcmStockInfoDto> selectShoppeProStockInfo(PcmStockDto record) {
		List<PcmStockInfoDto> pcmStocks = new ArrayList<PcmStockInfoDto>();
		pcmStocks = pcmStockMapper.selectShoppeProStockInfo(record);
		return pcmStocks;
	}

	/**
	 * 校验库位变更请求信息
	 *
	 * @param dtoList
	 * @return String
	 * @Methods Name validChangeStockRequestData
	 * @Create In 2015年9月18日 By kongqf
	 */
	public String validChangeStockRequestData(List<PcmStockChangeDto> dtoList) {
		StringBuilder errMsg = new StringBuilder();

		for (PcmStockChangeDto dto : dtoList) {
			if (StringUtils.isBlank(dto.getShoppeProSid())) {
				errMsg.append(ErrorCode.STOCK_SHOPPEPROSID_IS_NULL.getMemo());
			}
			if (0 == dto.getStockTypeSid() || 0 == dto.getNewStockType()) {
				errMsg.append(ErrorCode.STOCK_TYPE_IS_NULL.getMemo());
			}
			if (0 == dto.getProSum()) {
				errMsg.append(ErrorCode.STOCK_PROSUM_IS_NULL.getMemo());
			}
			if (StringUtils.isBlank(dto.getChannelSid())) {
				errMsg.append(ErrorCode.STOCK_CHANNELSID_IS_NULL.getMemo());
			}
		}
		return errMsg.toString();
	}

	/**
	 * 专柜商品库存信息查询
	 *
	 * @param queryProductStockInfoDto
	 * @return Page<SelectProductStockInfoDto>
	 * @Methods Name queryProductStockInfo
	 * @Create In 2015年9月28日 By kongqf
	 */
	public Page<SelectProductStockInfoDto> queryProductStockInfo(
			QueryProductStockInfoDto queryProductStockInfoDto) {
		logger.info("start queryProductStockInfo(),param:" + queryProductStockInfoDto.toString());
		Page<SelectProductStockInfoDto> productPageDto = new Page<SelectProductStockInfoDto>();
		List<SelectProductStockInfoDto> queryProductStockInfoDtos = new ArrayList<SelectProductStockInfoDto>();
		Integer count = pcmStockMapper.SelecProductStockInfoCountByPara(queryProductStockInfoDto);
		productPageDto.setCurrentPage(queryProductStockInfoDto.getCurrentPage());
		productPageDto.setPageSize(queryProductStockInfoDto.getPageSize());
		if (count != null && count != 0) {
			queryProductStockInfoDtos = pcmStockMapper
					.SelecProductStockInfoByPara(queryProductStockInfoDto);
			productPageDto.setList(queryProductStockInfoDtos);
		}
		productPageDto.setCount(count);
		// logger.info("start queryProductStockInfo(),result:" +
		// queryProductStockInfoDtos.toString());
		return productPageDto;
	}

	/**
	 * 专柜商品库存信息查询
	 *
	 * @param queryProductStockInfoDto
	 * @return Page<SelectProductStockInfoDto>
	 * @Methods Name queryProductStockInfoOptimization
	 * @Create In 2016年3月26日 By kongqf
	 */
	@Override
	public Page<SelectProductStockInfoDto> queryProductStockInfoOptimization(
			QueryProductStockInfoDto queryProductStockInfoDto) {
		logger.info("start queryProductStockInfoOptimization(),param:"
				+ queryProductStockInfoDto.toString());
		Page<SelectProductStockInfoDto> productPageDto = new Page<SelectProductStockInfoDto>();
		List<SelectProductStockInfoDto> queryProductStockInfoDtos = new ArrayList<SelectProductStockInfoDto>();
		Integer count = pcmStockMapper.findProductStockInfoCountByPara(queryProductStockInfoDto);
		productPageDto.setCurrentPage(queryProductStockInfoDto.getCurrentPage());
		productPageDto.setPageSize(queryProductStockInfoDto.getPageSize());
		if (count != null && count != 0) {
			queryProductStockInfoDtos = pcmStockMapper
					.findProductInfoByPara(queryProductStockInfoDto);
			if (queryProductStockInfoDtos.size() > 0) {
				List<String> productCodeList = new ArrayList<String>();
				for (SelectProductStockInfoDto tempDto : queryProductStockInfoDtos) {
					String productCode = tempDto.getProductCode();
					if (StringUtils.isNotEmpty(productCode)) {
						productCodeList.add(productCode);
					}
				}
				queryProductStockInfoDto.setProductCodeList(productCodeList);
				// 查询库存
				List<SelectProductStockInfoDto> stockList = pcmStockMapper
						.findStockInfoByProductPara(queryProductStockInfoDto);
				if (stockList.size() > 0) {
					for (SelectProductStockInfoDto stockDto : stockList) {
						String productCode = stockDto.getProductCode();
						if (StringUtils.isNotEmpty(productCode)) {
							for (SelectProductStockInfoDto productDto : queryProductStockInfoDtos) {
								String productDtoProductCode = productDto.getProductCode();
								if (productCode.equals(productDtoProductCode)) {
									productDto.setSaleStock(stockDto.getSaleStock());
									productDto.setEdefectiveStock(stockDto.getEdefectiveStock());
									productDto.setReturnStock(stockDto.getReturnStock());
									productDto.setLockedStock(stockDto.getLockedStock());
								}
							}
						}
					}
				}
			}
			productPageDto.setList(queryProductStockInfoDtos);
		}
		productPageDto.setCount(count);
		logger.info("end queryProductStockInfoOptimization(),result:"
				+ queryProductStockInfoDtos.toString());
		return productPageDto;
	}

	/**
	 * 根据一组专柜商品编码查询库存
	 *
	 * @param dto
	 * @return List<SelectProductStockInfoDto>
	 * @Methods Name findStockInfoByProductPara
	 * @Create In 2016年4月13日 By wangxuan
	 */
	@Override
	public List<SelectProductStockInfoDto> findStockInfoByProductPara(
			QueryProductStockInfoDto dto) {
		logger.info("start findStockInfoByProductPara(),param:" + dto.toString());
		List<String> productCodeList = dto.getProductCodeList();
		if (productCodeList != null && productCodeList.size() == 0) {
			dto.setProductCodeList(null);
		}
		List<SelectProductStockInfoDto> list = pcmStockMapper.findStockInfoByProductPara(dto);
		logger.info("end findStockInfoByProductPara(),return:" + list.toString());
		return list;
	}

	/**
	 * 专柜商品库存信息查询导出Excel 总数量
	 *
	 * @param queryProductStockInfoDto
	 * @return List<SelectProductStockInfoDto>
	 * @Methods Name queryProductStockInfoExcelCount
	 * @Create In 2016年04月05日 By wangxuan
	 */
	@Override
	public Page<SelectProductStockInfoDto> queryProductStockInfoExcelCount(
			QueryProductStockInfoDto queryProductStockInfoDto) {
		logger.info(
				"start queryProductStockInfoExcel(),param:" + queryProductStockInfoDto.toString());
		Page<SelectProductStockInfoDto> page = new Page<SelectProductStockInfoDto>();
		Integer count = pcmStockMapper.findProductStockInfoCountByPara(queryProductStockInfoDto);
		if (count != null) {
			page.setCount(count);
		} else {
			page.setCount(0);
		}
		logger.info("end queryProductStockInfoExcel(),result:" + count.toString());
		return page;
	}

	/**
	 * 专柜商品库存信息查询导出Excel
	 *
	 * @param queryProductStockInfoDto
	 * @return List<SelectProductStockInfoDto>
	 * @Methods Name queryProductStockInfoExcel
	 * @Create In 2015年10月8日 By kongqf
	 */
	@Override
	public List<SelectProductStockInfoDto> queryProductStockInfoExcel(
			QueryProductStockInfoDto queryProductStockInfoDto) {
		logger.info(
				"start queryProductStockInfoExcel(),param:" + queryProductStockInfoDto.toString());
		List<SelectProductStockInfoDto> queryProductStockInfoDtos = new ArrayList<SelectProductStockInfoDto>();
		queryProductStockInfoDto.setStart(null);
		queryProductStockInfoDto.setLimit(null);
		queryProductStockInfoDto.setPageSize(null);
		queryProductStockInfoDto.setCurrentPage(null);
		queryProductStockInfoDtos = pcmStockMapper
				.findProductStockInfoByPara(queryProductStockInfoDto);
		logger.info(
				"end queryProductStockInfoExcel(),result:" + queryProductStockInfoDtos.toString());
		return queryProductStockInfoDtos;
	}

	/**
	 * 查询sku库存数
	 *
	 * @param dto
	 * @return PcmProductStockInfoDto
	 * @Methods Name SelecSkuStockSumBySku
	 * @Create In 2015年11月25日 By kongqf
	 */
	@Override
	public PcmProductStockInfoDto SelectSkuStockSumBySku(PcmProductStockInfoDto dto) {
		logger.info("start SelecSkuStockSumBySku(),param:" + dto.toString());
		PcmProductStockInfoDto result = new PcmProductStockInfoDto();
		result = pcmStockMapper.SelectSkuStockSumBySku(dto);
		if (result != null) {
			boolean isUpdate = false;
			String stockStatus = "";
			Map<String, Object> paraMap = new HashMap<String, Object>();
			if (result.getSaleSum() > 0) {
				if (StringUtils.isBlank(result.getStockStatus())
						|| result.getStockStatus().equals(Constants.SKU_STOCK1)) {
					stockStatus = Constants.SKU_STOCK0;
					isUpdate = true;
				}
			} else if (result.getSaleSum() == 0
					&& result.getStockStatus().equals(Constants.SKU_STOCK0)) {
				stockStatus = Constants.SKU_STOCK1;
				isUpdate = true;
			}
			logger.info("is update sku stoctStatus:" + isUpdate);
			if (isUpdate) {
				paraMap.put("stockStatus", stockStatus);
				paraMap.put("productDetailSid", result.getSkuSid());
				int count = pcmProDetailMapper.updateStockStatusBySkuSid(paraMap);
				logger.info(String.format("update sku stoctStatus:{0},{1},{2}", count,
						dto.getSkuSid(), stockStatus));
				boolean del = redisUtil.del(DomainName.getCMSSHopperInfo + result.getSkuSid());
				if (!del) {// 如果删除失败
					PcmRedis redis = new PcmRedis();
					String proYeUrl = PropertyUtil.getSystemUrl("pcm-outer-sdc")
							+ "product/getProYeInfoBySpuCode.htm";
					String proYeJson = HttpUtil.doPost(proYeUrl,
							"{\"spuCode\":\"" + result.getSkuSid() + "\"}");
					redis.setCreatetime(new Date());
					redis.setValue(proYeJson);
					redis.setRedisffield(DomainName.getCMSSHopperInfo);
					redis.setKeyname(result.getSkuSid());
					redisService.savePcmRedis(redis);
				}
			}
			logger.info("end SelecSkuStockSumBySku(),result:" + result.toString());
		} else {
			PcmExceptionLogDto pcmExceptionLogDto = new PcmExceptionLogDto();
			pcmExceptionLogDto.setInterfaceName("update sku stock status");
			pcmExceptionLogDto.setExceptionType(StatusCode.EXCEPTION_STOCK.getStatus());
			pcmExceptionLogDto.setErrorMessage("update sku stock status failed");
			pcmExceptionLogDto.setDataContent(dto.toString());
			pcmExceptionLogDto.setUuid(UUID.randomUUID().toString());
			pcmExceptionLogService.saveExceptionLogInfo(pcmExceptionLogDto);
			logger.info("end SelecSkuStockSumBySku(),result:is null");

		}
		return result;
	}

	/**
	 * 库存变动修改SKU库存状态
	 *
	 * @Methods Name updateSKUStatus
	 * @Create In 2015年11月30日 By kongqf void
	 */

	@Override
	public void updateSKUStatus(PcmProductStockInfoDto dto) {
		logger.info("start updateSKUStatus(),param:" + dto.toString());
		final PcmProductStockInfoDto pcmProductStockInfoDto = dto;
		taskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					SelectSkuStockSumBySku(pcmProductStockInfoDto);
				} catch (Exception e) {
					logger.info("end updateSKUStatus(),Exception:" + e.getMessage());
				}
			}
		});
	}

	/**
	 * 导入库存更新缓存
	 *
	 * @param shoppeProSid
	 * @param channelSid
	 *            void
	 * @Methods Name updateImportStockCache
	 * @Create In 2016年3月10日 By kongqf
	 */
	@Override
	public void updateImportStockCache(String shoppeProSid, String channelSid) {
		if (StringUtils.isBlank(channelSid)) {
			channelSid = Constants.DEFAULT_CHANNEL_SID;
		}
		PcmStockDto saleRecord = new PcmStockDto();
		saleRecord.setShoppeProSid(shoppeProSid);
		saleRecord.setChannelSid(channelSid);
		saleRecord.setStockTypeSid(Constants.PCMSTOCK_TYPE_SALE);
		// 查询可售库存数
		Integer stockCount = findStockCountFromPcm(saleRecord);
		String key = DomainName.getStock + shoppeProSid + channelSid;
		boolean flag = redisUtil.set(key, stockCount.toString());
		if (!CacheUtils.cacheFlag) {
			PcmRedis pcmRedisDto = new PcmRedis();
			pcmRedisDto.setRedisffield(DomainName.getStock);
			pcmRedisDto.setKeyname(key);
			pcmRedisDto.setValue(stockCount.toString());
			redisService.savePcmRedis(pcmRedisDto);
		}
	}

	@Override
	public List<PcmStockWCSDto> selectProStockPushByParam(Map<String, Object> param) {
		return pcmStockMapper.selectProStockPushByParam(param);
	}

	@Override
	public List<PcmStockWCSDto> selectProStockPushByPros(Map<String, Object> param) {
		return pcmStockMapper.selectProStockPushByPros(param);
	}

	@Override
	public List<PcmStock> selectProStockByParam(Map<String, Object> param) {
		return pcmStockMapper.selectProStockByParam(param);
	}

}

package com.wangfj.product.maindata.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.core.utils.DistributedLock;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.core.utils.PropertyUtil;
import com.wangfj.product.common.domain.vo.PcmExceptionLogDto;
import com.wangfj.product.common.persistence.PcmExceptionLogMapper;
import com.wangfj.product.common.service.impl.PcmExceptionLogService;
import com.wangfj.product.constants.FlagType;
import com.wangfj.product.constants.StatusCodeConstants.StatusCode;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProduct;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProductEdiRelation;
import com.wangfj.product.maindata.domain.vo.EdiProDto;
import com.wangfj.product.maindata.domain.vo.PcmEdiProductStockDto;
import com.wangfj.product.maindata.domain.vo.QueryEdiProductStockDto;
import com.wangfj.product.maindata.domain.vo.ResultDto;
import com.wangfj.product.maindata.persistence.PcmShoppeProductEdiRelationMapper;
import com.wangfj.product.maindata.persistence.PcmShoppeProductMapper;
import com.wangfj.product.maindata.service.intf.IPcmProEdiRelationService;
import com.wangfj.product.organization.domain.entity.PcmChannelSaleConfig;
import com.wangfj.product.organization.persistence.PcmChannelSaleConfigMapper;
import com.wangfj.product.stocks.domain.entity.PcmStock;
import com.wangfj.product.stocks.persistence.PcmStockMapper;
import com.wangfj.util.Constants;

@Service
public class PcmProEdiRelationServiceImpl implements IPcmProEdiRelationService {
	@Autowired
	private PcmShoppeProductMapper proMapper;
	@Autowired
	private PcmShoppeProductEdiRelationMapper proEdiMapper;
	@Autowired
	private PcmStockMapper stockMapper;
	@Autowired
	private PcmChannelSaleConfigMapper saleMapper;
	@Autowired
	private PcmExceptionLogService pcmExceptionLogService;
	@Autowired
	private PcmExceptionLogMapper exLogMapper;

	/**
	 * edi查询信息基础service
	 * 
	 * @Methods Name selectListByParam
	 * @Create In 2016年7月19日 By yedong
	 * @param entity
	 * @return List<PcmShoppeProductEdiRelation>
	 */
	public List<PcmShoppeProductEdiRelation> selectListByParam(PcmShoppeProductEdiRelation entity) {
		List<PcmShoppeProductEdiRelation> ediList = proEdiMapper.selectListByParam(entity);
		return ediList;
	}

	@Override
	public ResultDto addShoppeProFromEdi(List<EdiProDto> dtoList) {
		String errorMag = null;
		String lockURL = PropertyUtil.getSystemUrl("lockURL");
		List<ResultDto> resultList = new ArrayList<ResultDto>();
		for (EdiProDto ediProDto : dtoList) {
			ResultDto dto = new ResultDto();
			dto.setNumIid(ediProDto.getEDIProCode());
			dto.setOuterId(ediProDto.getShoppeProCode());
			dto.setChannelCode(ediProDto.getChannelCode());
			dto.setProNum("0");
			dto.setStatus("1");
			if (ediProDto.getActionCode().equals(Constants.A)) {
				if (ediProDto.getEDIProCode() == null || ediProDto.getEDIProCode() == "") {
					dto.setStatus("1");
					errorMag = "EDI商品编码不能为空";
				}
				if (ediProDto.getShoppeProCode() == null || ediProDto.getShoppeProCode() == "") {
					dto.setStatus("1");
					errorMag = "专柜商品编码不能为空";
				}
				if (ediProDto.getChannelCode() == null || ediProDto.getChannelCode() == "") {
					dto.setStatus("1");
					errorMag = "渠道不能为空";
				}
				if (ediProDto.getIsPayReduceStock() == null
						|| ediProDto.getIsPayReduceStock() == "") {
					dto.setStatus("1");
					errorMag = "是否支持支付减库存不能为空";
				}
			}
			if (errorMag == null) {
				if (ediProDto.getActionCode().equals(Constants.A)) {
					DistributedLock ZKlock = new DistributedLock(lockURL,
							ediProDto.getShoppeProCode() + ediProDto.getEDIProCode()
									+ ediProDto.getChannelCode());
					if (FlagType.zookeeper_lock == 0) {
						ZKlock.lock();
					}
					PcmShoppeProduct pro = new PcmShoppeProduct();
					if (ediProDto.getShoppeProCode().length() == 18) {
						pro.setField4(ediProDto.getShoppeProCode());
					} else {
						pro.setShoppeProSid(ediProDto.getShoppeProCode());
					}
					pro.setSaleStatus(Constants.PUBLIC_0);
					List<PcmShoppeProduct> proList = proMapper.selectListByParam(pro);// 查询专柜商品是否存在

					if (proList == null || proList.size() == 0) {
						dto.setStatus("0");// 专柜商品不存在
						errorMag = "专柜商品不存在";
					} else {
						PcmChannelSaleConfig sale = new PcmChannelSaleConfig();
						sale.setChannelSid(ediProDto.getChannelCode());
						sale.setShoppeProSid((proList.get(0).getShoppeSid()));
						sale.setSaleStauts(0);
						List<PcmChannelSaleConfig> saleList = saleMapper.selectListByParam(sale);// 查询专柜商品可售渠道是否存在
						if (saleList != null && saleList.size() > 0) {
							PcmShoppeProductEdiRelation proEdi = new PcmShoppeProductEdiRelation();
							proEdi.setChannelCode(ediProDto.getChannelCode());
							proEdi.setShoppeProSid(ediProDto.getShoppeProCode());
							// proEdi.setChannelProSid(ediProDto.getEDIProCode());
							proEdi.setIfdel(Constants.PUBLIC_1);
							List<PcmShoppeProductEdiRelation> ediList = proEdiMapper
									.selectListByParam(proEdi);// 查询Edi关联关系是否存在
							if (ediList != null && ediList.size() > 0) {// 如果存在
								if (ediList.get(0).getChannelProSid()
										.equals(ediProDto.getEDIProCode())) {
									proEdi.setField1(ediList.get(0).getField1());
									PcmStock entity = new PcmStock();
									entity.setShoppeProSid(proEdi.getField1());
									entity.setStockTypeSid(Constants.PCMSTOCK_TYPE_SALE);
									entity.setChannelSid(ediProDto.getChannelCode());
									List<PcmStock> stock = stockMapper.selectListByParam(entity);
									if (stock != null && stock.size() > 0) {
										if (stock.get(0).getProSum() == 0) {
											if (Constants.PCMSTOCK_ISPUSH_EDI.equals("1")) {
												PcmStock entity1 = new PcmStock();
												entity1.setShoppeProSid(ediList.get(0).getField1());
												entity1.setStockTypeSid(Constants.PCMSTOCK_TYPE_SALE);
												entity1.setChannelSid("0");
												List<PcmStock> stock1 = stockMapper
														.selectListByParam(entity1);
												if (stock1 != null && stock1.size() > 0) {
													dto.setProNum(stock1.get(0).getProSum()
															.toString());
												}
											} else {
												dto.setProNum(stock.get(0).getProSum().toString());
											}
										} else {
											dto.setProNum(stock.get(0).getProSum().toString());
										}
									} else {
										if (Constants.PCMSTOCK_ISPUSH_EDI.equals("1")) {
											PcmStock entity1 = new PcmStock();
											entity1.setShoppeProSid(ediList.get(0).getField1());
											entity1.setStockTypeSid(Constants.PCMSTOCK_TYPE_SALE);
											entity1.setChannelSid("0");
											List<PcmStock> stock1 = stockMapper
													.selectListByParam(entity1);
											if (stock1 != null && stock1.size() > 0) {
												dto.setProNum(stock1.get(0).getProSum().toString());
											}
										} else {
											dto.setStatus("0");// 开关关闭-库存不足
											errorMag = "开关关闭-库存不足";
										}
									}
								} else {
									dto.setStatus("0");// 已上架
									errorMag = "已上架";
								}
							} else {
								proEdi.setChannelProSid(ediProDto.getEDIProCode());
								proEdi.setField1(proList.get(0).getShoppeProSid());
								PcmStock entity = new PcmStock();
								entity.setShoppeProSid(proEdi.getField1());
								entity.setStockTypeSid(Constants.PCMSTOCK_TYPE_SALE);
								entity.setChannelSid(ediProDto.getChannelCode());
								List<PcmStock> stock = stockMapper.selectListByParam(entity);
								if (stock != null && stock.size() > 0) {
									if (stock.get(0).getProSum() == 0) {
										if (Constants.PCMSTOCK_ISPUSH_EDI.equals("1")) {
											PcmStock entity1 = new PcmStock();
											entity1.setShoppeProSid(ediList.get(0).getField1());
											entity1.setStockTypeSid(Constants.PCMSTOCK_TYPE_SALE);
											entity1.setChannelSid("0");
											List<PcmStock> stock1 = stockMapper
													.selectListByParam(entity1);
											if (stock1 != null && stock1.size() > 0) {
												dto.setProNum(stock1.get(0).getProSum().toString());
											}
										} else {
											dto.setProNum(stock.get(0).getProSum().toString());
										}
									} else {
										dto.setProNum(stock.get(0).getProSum().toString());
									}
								} else {
									if (Constants.PCMSTOCK_ISPUSH_EDI.equals("1")) {
										PcmStock entity1 = new PcmStock();
										entity1.setShoppeProSid(proEdi.getField1());
										entity1.setStockTypeSid(Constants.PCMSTOCK_TYPE_SALE);
										entity1.setChannelSid("0");
										List<PcmStock> stock1 = stockMapper
												.selectListByParam(entity1);
										if (stock1 != null && stock1.size() > 0) {
											dto.setProNum(stock1.get(0).getProSum().toString());
										}
									} else {
										dto.setStatus("0");// 开关关闭-库存不足
										errorMag = "开关关闭-库存不足";
									}
								}
								if (errorMag == null) {
									proEdi.setIsPayReducestock(Integer.parseInt(ediProDto
											.getIsPayReduceStock()));
									if (StringUtils.isNotBlank(ediProDto.getIsPresell())) {
										proEdi.setIsPresell(Integer.parseInt(ediProDto
												.getIsPresell()));
									}
									SimpleDateFormat df = new SimpleDateFormat(
											"yyyy-MM-dd HH:mm:ss");
									String time = df.format(new Date());
									proEdi.setField2(time);
									int i = proEdiMapper.insertSelective(proEdi);
									if (i == 0) {
										dto.setStatus("0");// 数据库错误
										errorMag = "数据库错误";
									} else {

									}
								}
							}
						} else {
							dto.setStatus("0");// 渠道不可售
							errorMag = "渠道不可售";
						}
					}
					if (FlagType.zookeeper_lock == 0) {
						ZKlock.unlock();
					}
				} else if (ediProDto.getActionCode().equals(Constants.U)) {
					dto.setProNum(null);
					PcmShoppeProductEdiRelation proEdi = new PcmShoppeProductEdiRelation();
					proEdi.setChannelCode(ediProDto.getChannelCode());
					proEdi.setShoppeProSid(ediProDto.getShoppeProCode());
					proEdi.setChannelProSid(ediProDto.getEDIProCode());
					proEdi.setIfdel(Constants.PUBLIC_1);
					List<PcmShoppeProductEdiRelation> proEdiList = proEdiMapper
							.selectListByParam(proEdi);
					if (proEdiList != null && proEdiList.size() > 0) {
						PcmShoppeProductEdiRelation proEdi_1 = new PcmShoppeProductEdiRelation();
						proEdi_1.setSid(proEdiList.get(0).getSid());
						proEdi_1.setIsPayReducestock(Integer.parseInt(ediProDto
								.getIsPayReduceStock()));
						int u = proEdiMapper.updateByPrimaryKeySelective(proEdi_1);
						if (u == 0) {
							dto.setStatus("0");// 数据库错误
							errorMag = "update数据库错误";
						}
					} else {
						dto.setStatus("0");// 该商品不存在
						errorMag = ediProDto.getShoppeProCode() + ediProDto.getEDIProCode()
								+ "该商品不存在";
					}
				} else if (ediProDto.getActionCode().equals(Constants.D)) {
					if (ediProDto.getShoppeProCode().equals("")
							|| ediProDto.getShoppeProCode().equals(null)) {
						dto.setStatus("0");//
						errorMag = "下架商品编码不能为空";
					} else {
						PcmShoppeProductEdiRelation proEdi = new PcmShoppeProductEdiRelation();
						proEdi.setChannelCode(ediProDto.getChannelCode());
						proEdi.setShoppeProSid(ediProDto.getShoppeProCode());
						proEdi.setChannelProSid(ediProDto.getEDIProCode());
						proEdi.setIfdel(Constants.PUBLIC_1);
						List<PcmShoppeProductEdiRelation> proEdiList = proEdiMapper
								.selectListByParam(proEdi);
						if (proEdiList != null && proEdiList.size() > 0) {
							for (PcmShoppeProductEdiRelation ediPro : proEdiList) {
								proEdi.setSid(ediPro.getSid());
								proEdi.setIfdel(Constants.PUBLIC_0);
								SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								String time = df.format(new Date());
								proEdi.setField2(time);
								int u = proEdiMapper.updateByPrimaryKeySelective(proEdi);
								if (u == 0) {
									dto.setStatus("0");// 数据库错误
									errorMag = "update数据库错误";
								}
							}
						} else {
							dto.setStatus("0");//
							errorMag = "商品未上架，不可下架";
						}
					}
				}
			}
			resultList.add(dto);
			if (errorMag != null) {
				PcmExceptionLogDto pcmExceptionLogDto = new PcmExceptionLogDto();
				pcmExceptionLogDto.setInterfaceName("addShoppeProFromEdi");
				pcmExceptionLogDto.setExceptionType(StatusCode.EXCEPTION_PRODUCT.getStatus());
				pcmExceptionLogDto.setErrorMessage(errorMag);
				pcmExceptionLogDto.setDataContent(resultList.get(0).toString());
				pcmExceptionLogDto.setUuid(UUID.randomUUID().toString());
				exLogMapper.insertSelective(pcmExceptionLogDto);
				// pcmExceptionLogService.saveExceptionLogInfo(pcmExceptionLogDto);
			}
		}
		return resultList.get(0);
	}

	/**
	 * 根据专柜商品查询渠道商品的库存信息
	 * 
	 * @Methods Name selectEdiProStockInfo
	 * @Create In 2016年1月21日 By kongqf
	 * @param dto
	 * @return List<PcmEdiProductStockDto>
	 */
	@Override
	public List<PcmEdiProductStockDto> selectEdiProStockInfo(QueryEdiProductStockDto dto) {
		List<PcmEdiProductStockDto> resultList = new ArrayList<PcmEdiProductStockDto>();
		List<PcmEdiProductStockDto> tempList = null;
		QueryEdiProductStockDto query = null;
		if (dto != null && dto.getShoppeProSids().size() > 0) {
			for (String prosid : dto.getShoppeProSids()) {
				query = new QueryEdiProductStockDto();
				query.setShoppeProSid(prosid);
				tempList = new ArrayList<PcmEdiProductStockDto>();
				tempList = proEdiMapper.selectEdiProtListByProCode(query);
				PcmEdiProductStockDto result = new PcmEdiProductStockDto();

				if (tempList != null && tempList.size() > 0) {
					for (PcmEdiProductStockDto tempDto : tempList) {
						try {
							result = getEdiStockList(tempDto, dto.getChannelCode());
						} catch (Exception e) {
							result = null;
							PcmExceptionLogDto pcmExceptionLogDto = new PcmExceptionLogDto();
							pcmExceptionLogDto.setInterfaceName("pushstocktoedi");
							pcmExceptionLogDto.setExceptionType(StatusCode.EXCEPTION_STOCK
									.getStatus());
							pcmExceptionLogDto.setErrorMessage(e.getMessage());
							pcmExceptionLogDto.setDataContent(JsonUtil.getJSONString(tempDto));
							pcmExceptionLogDto.setUuid(UUID.randomUUID().toString());
							pcmExceptionLogService.saveExceptionLogInfo(pcmExceptionLogDto);
						}
						if (result != null) {
							resultList.add(result);
						}
					}
				}
			}
		}
		return resultList;
	}

	public PcmEdiProductStockDto getEdiStockList(PcmEdiProductStockDto dto, String saleChanelCode) {
		QueryEdiProductStockDto query = new QueryEdiProductStockDto();
		query.setShoppeProSid(dto.getField1());
		query.setChannelCode(dto.getChannelCode());
		query.setStockChannelCode(dto.getChannelCode());
		PcmEdiProductStockDto result = new PcmEdiProductStockDto();
		result = proEdiMapper.selectProStoctListByParam(query);
		if (result != null) {
			if ("0".equals(result.getProSum())) {
				if ("1".equals(Constants.PCMSTOCK_ISPUSH_EDI)) {
					if (!query.getStockChannelCode().equals(Constants.DEFAULT_CHANNEL_SID)) {
						query.setStockChannelCode(Constants.DEFAULT_CHANNEL_SID);
						result = proEdiMapper.selectProStoctListByParam(query);
					}
				}
			} else {
				if (!saleChanelCode.equals(result.getChannelCode())) {
					result = null;
				}
			}
		} else {
			if ("1".equals(Constants.PCMSTOCK_ISPUSH_EDI)) {
				if (!Constants.DEFAULT_CHANNEL_SID.equals(query.getStockChannelCode())) {
					query.setStockChannelCode(Constants.DEFAULT_CHANNEL_SID);
					result = proEdiMapper.selectProStoctListByParam(query);
				}
			}
		}
		return result;
	}

	@Override
	public List<PcmEdiProductStockDto> selectEdiProStockInfoByChannelId(QueryEdiProductStockDto dto) {
		List<PcmEdiProductStockDto> resultList = new ArrayList<PcmEdiProductStockDto>();
		List<PcmEdiProductStockDto> tempList = null;
		QueryEdiProductStockDto query = null;
		if (dto != null && dto.getShoppeProSids().size() > 0) {
			for (String prosid : dto.getShoppeProSids()) {
				query = new QueryEdiProductStockDto();
				query.setShoppeProSid(prosid);
				query.setChannelCode(dto.getChannelCode());
				tempList = new ArrayList<PcmEdiProductStockDto>();
				tempList = proEdiMapper.selectEdiProtListByProCode(query);
				PcmEdiProductStockDto result = new PcmEdiProductStockDto();

				if (tempList != null && tempList.size() > 0) {
					for (PcmEdiProductStockDto tempDto : tempList) {
						try {
							result = getEdiStockList(tempDto, dto.getChannelCode());
						} catch (Exception e) {
							result = null;
							PcmExceptionLogDto pcmExceptionLogDto = new PcmExceptionLogDto();
							pcmExceptionLogDto.setInterfaceName("pushstocktoedi");
							pcmExceptionLogDto.setExceptionType(StatusCode.EXCEPTION_STOCK
									.getStatus());
							pcmExceptionLogDto.setErrorMessage(e.getMessage());
							pcmExceptionLogDto.setDataContent(JsonUtil.getJSONString(tempDto));
							pcmExceptionLogDto.setUuid(UUID.randomUUID().toString());
							pcmExceptionLogService.saveExceptionLogInfo(pcmExceptionLogDto);
						}
						if (result != null) {
							resultList.add(result);
						}
					}
				}
			}
		}
		return resultList;
	}
}

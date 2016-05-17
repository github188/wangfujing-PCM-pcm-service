package com.wangfj.util.mq;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.core.domain.entity.PcmMq;
import com.wangfj.product.core.service.intf.IPcmMqService;
import com.wfj.mq.dto.ItgCallbackDto;
import com.wfj.mq.utils.JsonUtils;

@Component
public class MqUtil {

	private static final Logger logger = LoggerFactory.getLogger(MqUtil.class);
	@Autowired
	private IPcmMqService mqService;

	private static MqUtil mqUtil;

	@PostConstruct
	public void init() {
		mqUtil = this;
		mqUtil.mqService = this.mqService;
	}

	/**
	 * mq消息发送
	 * 
	 * @Methods Name sendMessage
	 * @Create In 2015-7-31 By liuhp
	 * @param mqDTO
	 *            void
	 */
	public static void sendMessage(MqDTO mqDTO) {
		MqMessage message = new MqMessage();
		message.setData(mqDTO.getData());
		Header header = new Header();

		BeanUtils.copyProperties(mqDTO, header);
		header.setBizType("17");
		header.setVersion("1");
		if (StringUtils.isNotBlank(mqDTO.getVersion())) {
			header.setVersion(mqDTO.getVersion());
		}
		header.setPriority("1");
		header.setRouteKey("2");

		message.setHeader(header);

		// ITGInBoundClient inBoundService = new ITGInBoundClient();
		// inBoundService.setUrl(mqDTO.getUrl());
		// inBoundService.send(url, opName, json);

		String json = JsonUtil.getJSONString(message);
		logger.info(json);

		ItgCallbackDto dto = new ItgCallbackDto();
		String response = "";
		try {
			response = HttpUtil.doPost(mqDTO.getUrl(), json);
			if (response != null) {
				dto = JsonUtils.jsonToBean(response, ItgCallbackDto.class); //
			} else {
				dto.setRespStatus("0");
			}
		} catch (Exception e) {
			dto.setRespStatus("0");
		}

		// 插入MQ接收记录表
		PcmMq pcmMq = new PcmMq();
		pcmMq.setUrl(mqDTO.getUrl());
		pcmMq.setDesturl(mqDTO.getDestUrl());
		pcmMq.setCallbackurl(mqDTO.getCallbackUrl());
		pcmMq.setData(JsonUtil.getJSONString(mqDTO.getData()));
		pcmMq.setData(json);
		pcmMq.setServiceid(mqDTO.getServiceID());
		pcmMq.setSourcesysid(mqDTO.getSourceSysID());
		pcmMq.setCount(Integer.valueOf(mqDTO.getCount()));
		pcmMq.setC4(mqDTO.getField3());
		pcmMq.setCreatedate(new Date());
		pcmMq.setMessageid(dto.getMessageID());
		pcmMq.setBizdesc(dto.getBizDesc());
		pcmMq.setBizcode(dto.getBizCode());
		pcmMq.setC1(dto.getRespStatus());
		if ("0".equals(dto.getRespStatus())) {
			pcmMq.setData(json);
		}
		try {
			mqUtil.mqService.insertSelective(pcmMq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(JsonUtil.getJSONString(pcmMq));
	}

	/**
	 * mq消息发送
	 * 
	 * @Methods Name sendMessage
	 * @Create In 2015-7-31 By liuhp
	 * @param mqDTO
	 *            void
	 */
	public static void sendMessage_2(MqDto_2 mqDTO) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("data", mqDTO.getData());
		Header header = new Header();

		BeanUtils.copyProperties(mqDTO, header);
		header.setBizType("17");
		header.setVersion("1");
		header.setPriority("1");
		header.setRouteKey("2");

		paramMap.put("header", header);

		// ITGInBoundClient inBoundService = new ITGInBoundClient();
		// inBoundService.setUrl(mqDTO.getUrl());
		// inBoundService.send(url, opName, json);

		// ItgCallbackDto dto =
		// inBoundService.send(JsonUtil.getJSONString(paramMap));
		String json = JsonUtil.getJSONString(paramMap);
		logger.info(json);
		ItgCallbackDto dto = new ItgCallbackDto();
		String response = "";
		try {
			response = HttpUtil.doPost(mqDTO.getUrl(), json);
			dto = JsonUtils.jsonToBean(response, ItgCallbackDto.class); //
		} catch (Exception e) {
			dto.setRespStatus("0");
		}

		// 插入MQ接收记录表
		PcmMq pcmMq = new PcmMq();
		pcmMq.setUrl(mqDTO.getUrl());
		pcmMq.setDesturl(mqDTO.getDestUrl());
		pcmMq.setCallbackurl(mqDTO.getCallbackUrl());
		// pcmMq.setData(JsonUtil.getJSONString(mqDTO.getData()));
		// pcmMq.setData(json);
		pcmMq.setServiceid(mqDTO.getServiceID());
		pcmMq.setSourcesysid(mqDTO.getSourceSysID());
		pcmMq.setCount(Integer.valueOf(mqDTO.getCount()));
		pcmMq.setC4(mqDTO.getField3());
		pcmMq.setCreatedate(new Date());
		pcmMq.setMessageid(dto.getMessageID());
		pcmMq.setBizdesc(dto.getBizDesc());
		pcmMq.setBizcode(dto.getBizCode());
		pcmMq.setC1(dto.getRespStatus());
		if ("0".equals(dto.getRespStatus())) {
			pcmMq.setData(json);
		}
		try {
			mqUtil.mqService.insertSelective(pcmMq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(JsonUtil.getJSONString(pcmMq));
	}

	/**
	 * 接收到MQ信息后返回信息
	 * 
	 * @Methods Name GetMqResponseInfo
	 * @Create In 2015年9月10日 By kongqf
	 * @param header
	 * @return MqResponse
	 */
	public static MqResponse GetMqResponseInfo(RequestHeader header) {
		MqResponse response = new MqResponse();
		response.setMessageID(header.getMessageID());
		response.setServiceID(header.getServiceID());
		response.setRespStatus("1");
		return response;
	}

}

package com.wangfj.product.common.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoField;
import com.sap.conn.jco.JCoFieldIterator;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.common.domain.entity.PcmJCOLog;
import com.wangfj.product.common.service.intf.IJcoSAPUtil;
import com.wangfj.product.common.service.intf.IPcmExceptionLogService;
import com.wangfj.product.constants.ConnectSAPServer;
import com.wangfj.util.Constants;

@Service
public class JcoSAPUtil implements IJcoSAPUtil {
	private static final Logger logger = LoggerFactory.getLogger(JcoSAPUtil.class);

	@Autowired
	private IPcmExceptionLogService exService;

	/**
	 * 调用sap function
	 * 
	 * @Methods Name functionExecute
	 * @Create In 2015年11月17日 By kongqf
	 * @param functionName
	 * @param requestTableName
	 * @param resultTableName
	 * @param listMap
	 * @return List<Map<String,String>>
	 */
	public List<Map<String, String>> functionExecute(String functionName, String requestTableName,
			String resultTableName, List<Map<String, Object>> listMap) {
		logger.info("function:" + functionName + ";" + listMap.toString());
		PcmJCOLog jcoLog = new PcmJCOLog();
		jcoLog.setFunctionName(functionName);
		jcoLog.setInputTable(requestTableName);
		jcoLog.setOutputTable(resultTableName);
		jcoLog.setDataContent(JsonUtil.getJSONString(listMap));
		jcoLog.setField1(listMap != null ? listMap.size() + "" : "0");
		jcoLog.setStatus(Constants.FAILURE);
		JCoDestination jCoDestination = ConnectSAPServer.Connect();
		JCoFunction function;
		try {
			function = jCoDestination.getRepository().getFunction(functionName);
			if (function == null) {
				logger.error(functionName + " not found in SAP.");
				jcoLog.setResultContent(functionName + " not found in SAP.");
				exService.saveJcoLog(jcoLog);
				throw new RuntimeException(functionName + " not found in SAP.");
			}
			JCoTable tempTable = function.getTableParameterList().getTable(requestTableName);

			for (Map<String, Object> map : listMap) {
				tempTable.appendRow();
				for (String key : map.keySet()) {
					tempTable.setValue(key, map.get(key));
				}
			}
			function.execute(jCoDestination);
			List<Map<String, String>> resultListMap = new ArrayList<Map<String, String>>();
			resultListMap = getResultListMap(function, resultTableName);
			jcoLog.setResultContent(JsonUtil.getJSONString(resultListMap));
			if (resultListMap != null && resultListMap.size() > 0) {
				jcoLog.setStatus(Constants.SUCCESS);
			}
			exService.saveJcoLog(jcoLog);
			return resultListMap;
		} catch (JCoException e) {
			logger.error(e.getMessage());
			jcoLog.setResultContent(e.getMessage());
			exService.saveJcoLog(jcoLog);
		}
		return null;
	}

	/**
	 * 获取返回信息，把返回的table转换成map
	 * 
	 * @Methods Name getResultListMap
	 * @Create In 2015年11月17日 By kongqf
	 * @param jcoFunction
	 * @param tableName
	 * @return List<Map<String,String>>
	 */
	public List<Map<String, String>> getResultListMap(JCoFunction jcoFunction, String tableName) {
		List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
		JCoTable table = jcoFunction.getTableParameterList().getTable(tableName);
		int rows = table.getNumRows();
		Map<String, String> map = null;
		for (int i = 0, len = rows; i < len; i++) {
			// 游标移动到row
			table.setRow(i);
			JCoFieldIterator iterator = table.getFieldIterator();
			map = new HashMap<String, String>();
			while (iterator.hasNextField()) {
				JCoField recordField = iterator.nextField();
				map.put(recordField.getName(), recordField.getString());
			}
			listMap.add(map);
		}
		return listMap;
	}

	/**
	 * 调用sap function
	 * 
	 * @Methods Name functionExecute
	 * @Create In 2015年11月17日 By kongqf
	 * @param functionName
	 * @param requestTableName
	 * @param listMap
	 * @return List<Map<String,String>>
	 */
	public void functionExecute(String functionName, String requestTableName,
			List<Map<String, Object>> listMap) {
		PcmJCOLog jcoLog = new PcmJCOLog();
		jcoLog.setFunctionName(functionName);
		jcoLog.setInputTable(requestTableName);
		jcoLog.setDataContent(JsonUtil.getJSONString(listMap));
		jcoLog.setField1(listMap != null ? listMap.size() + "" : "0");
		jcoLog.setStatus(Constants.FAILURE);
		JCoDestination jCoDestination = ConnectSAPServer.Connect();
		JCoFunction function;
		try {
			function = jCoDestination.getRepository().getFunction(functionName);
			if (function == null) {
				logger.info(functionName + " not found in SAP.");
				jcoLog.setResultContent(functionName + " not found in SAP.");
				exService.saveJcoLog(jcoLog);
				throw new RuntimeException(functionName + " not found in SAP.");
			}
			JCoTable tempTable = function.getTableParameterList().getTable(requestTableName);

			for (Map<String, Object> map : listMap) {
				tempTable.appendRow();
				for (String key : map.keySet()) {
					tempTable.setValue(key, map.get(key));
				}
			}
			function.execute(jCoDestination);
			jcoLog.setStatus(Constants.SUCCESS);
			exService.saveJcoLog(jcoLog);
		} catch (JCoException e) {
			logger.error(e.getMessage());
			jcoLog.setResultContent(e.getMessage());
			exService.saveJcoLog(jcoLog);
		}
	}

}

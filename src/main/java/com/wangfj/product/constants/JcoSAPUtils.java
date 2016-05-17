package com.wangfj.product.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoField;
import com.sap.conn.jco.JCoFieldIterator;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoTable;

public class JcoSAPUtils {
	private static final Logger logger = LoggerFactory.getLogger(JcoSAPUtils.class);

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
	public static List<Map<String, String>> functionExecute(String functionName,
			String requestTableName, String resultTableName, List<Map<String, Object>> listMap) {
		logger.info("function:" + functionName + ";" + listMap.toString());
		JCoDestination jCoDestination = ConnectSAPServer.Connect();
		JCoFunction function;
		try {
			function = jCoDestination.getRepository().getFunction(functionName);
			if (function == null) {
				logger.info(functionName + " not found in SAP.");
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

			return getResultListMap(function, resultTableName);

		} catch (JCoException e) {
			logger.info(e.getMessage());
		}
		return null;
	}

	public static List<Map<String, String>> functionExecute(String functionName,
			String requestTableName, String resultTableName, List<Map<String, Object>> listMap,
			String[] columnName) {
		JCoDestination jCoDestination = ConnectSAPServer.Connect();
		JCoFunction function;
		try {
			function = jCoDestination.getRepository().getFunction(functionName);
			if (function == null) {
				logger.info(functionName + " not found in SAP.");
				throw new RuntimeException(functionName + " not found in SAP.");
			}
			JCoTable tempTable = function.getTableParameterList().getTable(requestTableName);

			for (Map<String, Object> map : listMap) {
				tempTable.appendRow();
				for (String key : columnName) {
					tempTable.setValue(key, map.get(key));
				}
			}
			function.execute(jCoDestination);

			return getResultListMap(function, resultTableName);

		} catch (JCoException e) {
			logger.info(e.getMessage());
		}
		return null;
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
	public static void functionExecute(String functionName, String requestTableName,
			List<Map<String, Object>> listMap) {
		JCoDestination jCoDestination = ConnectSAPServer.Connect();
		JCoFunction function;
		try {
			function = jCoDestination.getRepository().getFunction(functionName);
			if (function == null) {
				logger.info(functionName + " not found in SAP.");
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

		} catch (JCoException e) {
			logger.info(e.getMessage());
		}
	}

	/**
	 * 调用sap function 返回result
	 * 
	 * @Methods Name functionExecute
	 * @Create In 2015年11月27日 By kongqf
	 * @param functionName
	 * @param requestTableName
	 * @param listMap
	 * @return String
	 */
	public static String functionExecuteResultString(String functionName, String requestTableName,
			List<Map<String, Object>> listMap, String resultName) {
		JCoDestination jCoDestination = ConnectSAPServer.Connect();
		JCoFunction function;
		String result = StringUtils.EMPTY;
		try {
			function = jCoDestination.getRepository().getFunction(functionName);
			if (function == null) {
				logger.info(functionName + " not found in SAP.");
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

			JCoParameterList exportParam = function.getExportParameterList();
			result = exportParam.getString(resultName);

		} catch (JCoException e) {
			logger.info(e.getMessage());
		}
		return result;
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
	public static List<Map<String, String>> getResultListMap(JCoFunction jcoFunction,
			String tableName) {
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
}

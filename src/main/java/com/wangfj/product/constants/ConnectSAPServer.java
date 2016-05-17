package com.wangfj.product.constants;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.ext.DestinationDataProvider;
import com.wangfj.core.utils.PropertyUtil;

public class ConnectSAPServer {
	private static final Logger logger = LoggerFactory.getLogger(ConnectSAPServer.class);

	static String ABAP_AS_POOLED = "ABAP_AS_WITH_POOL";
	private static JCoDestination destination = null;

	static {
		Properties connectProperties = new Properties();
		connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST,
				PropertyUtil.getSystemUrl("JCO_ASHOST"));
		connectProperties.setProperty(DestinationDataProvider.JCO_SYSNR,
				PropertyUtil.getSystemUrl("JCO_SYSNR")); // 系统编号
		connectProperties.setProperty(DestinationDataProvider.JCO_CLIENT,
				PropertyUtil.getSystemUrl("JCO_CLIENT")); // SAP集团
		connectProperties.setProperty(DestinationDataProvider.JCO_USER,
				PropertyUtil.getSystemUrl("JCO_USER")); // SAP用户名
		connectProperties.setProperty(DestinationDataProvider.JCO_PASSWD,
				PropertyUtil.getSystemUrl("JCO_PASSWD")); // 密码
		connectProperties.setProperty(DestinationDataProvider.JCO_LANG,
				PropertyUtil.getSystemUrl("JCO_LANG")); // 登录语言
		connectProperties.setProperty(DestinationDataProvider.JCO_POOL_CAPACITY,
				PropertyUtil.getSystemUrl("JCO_POOL_CAPACITY")); // 最大连接数
		connectProperties.setProperty(DestinationDataProvider.JCO_PEAK_LIMIT,
				PropertyUtil.getSystemUrl("JCO_PEAK_LIMIT")); // 最大连接线程

		createDataFile(ABAP_AS_POOLED, "jcoDestination", connectProperties);
	}

	static void createDataFile(String name, String suffix, Properties properties) {
		File cfg = new File(name + "." + suffix);
		if (!cfg.exists()) {
			try {
				FileOutputStream fos = new FileOutputStream(cfg, false);
				properties.store(fos, "PCM Connect SAP");
				fos.close();
			} catch (Exception e) {
				logger.info("Unable to create the destination file " + cfg.getName());
				throw new RuntimeException("Unable to create the destination file " + cfg.getName(),
						e);
			}
		}
	}

	public static JCoFunction getFunction(String functionName) {
		JCoFunction function = null;
		try {
			function = Connect().getRepository().getFunctionTemplate(functionName).getFunction();
		} catch (JCoException e) {
			logger.error("JCO getFunction:" + e.getMessage());
		} catch (NullPointerException e) {
			logger.error("JCO getFunction:" + e.getMessage());
		}
		return function;
	}

	public static JCoDestination Connect() {
		// JCoDestination destination = null;
		try {
			if (destination == null) {
				destination = JCoDestinationManager.getDestination(ABAP_AS_POOLED);
			}
		} catch (JCoException e) {
			logger.error("JCO Connect:" + e.getMessage());
		}

		return destination;
	}
}
package com.wangfj.util.mq;

import org.junit.Test;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={
//      "classpath:applicationContext.xml"
//      })
public class MqSender {

	@Test
    public void test() {
		MqDTO mqDTO = new MqDTO();
		mqDTO.setCallbackUrl("http");
		mqDTO.setData(null);
		mqDTO.setDestUrl("http");
		mqDTO.setCount("200");
		mqDTO.setMessageID("1");
		mqDTO.setServiceID("P181_01");
		mqDTO.setDestCallType(0);
		mqDTO.setSourceSysID("P012");
		mqDTO.setField3("getData");
		MqUtil.sendMessage(mqDTO);
		
    }
}

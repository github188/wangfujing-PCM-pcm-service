package com.wangfj.product.constants;


/**
 * 说明:
 * 
 * @author guansq
 * @date 2012-12-10 下午02:56:48
 * @modify
 */
public class JobTimeCodeConstants {
    public enum JobTimeCode {
        
    	COMMON_ORDER(1,"普通订单",-1440),
		GROUP_TICKET_ORDER(2,"团购券订单",-1440),
		MIAOSHA_ORDER(3,"秒杀订单",-1440),
		VAT_ORDER(4,"蔬菜随心订订单",-1440),
		YUDING_ORDER(5,"预定订单",-1440),
		HOUTAI_SALE_ORDER(6,"后台销售订单",-1440),
		MOVIE_ORDER(7,"电影票订单",-15),
		BIG_ORDER(8,"大宗订单",-1440),
		SUPPLIER(11,"供应商直送",-1440),
		APP_ORDER(12,"移动商城",-1440),
		TAOBAO_ORDER(13,"淘宝订单",-1440),
		DIANXIN_ORDER(14,"后台电信订单",-1440),
		SHOUGONG_ORDER(15,"后台百联手工订单",-1440),
		QIYEGROUPBY_ORDER(16,"企业团购订单",-1440),
		STOP_ORDER(17,"停车场订单",-1440),
		IBIANDANG_ORDER(18,"i便当订单",-30),
		TASTY_NO_WAIT(19,"美味不用等",-1440),
    	WATER_ORDER(20,"水费",-1440),
		ELECTRIC_ORDER(21,"电费",-1440),
		GAS_ORDER(22,"燃气费",-1440),
		RECHARGE_ORDER(23,"电话充值",-120);

        private Integer jobCode;
        private String  jobCodeDesc;
        private Integer date;

        private JobTimeCode() {};

        private JobTimeCode(Integer jobCode,String jobCodeDesc, Integer date) {
            this.jobCode = jobCode;
            this.jobCodeDesc = jobCodeDesc;
            this.date = date;
        }

		public Integer getJobCode() {
			return jobCode;
		}

		public void setJobCode(Integer jobCode) {
			this.jobCode = jobCode;
		}

		public String getJobCodeDesc() {
			return jobCodeDesc;
		}

		public void setJobCodeDesc(String jobCodeDesc) {
			this.jobCodeDesc = jobCodeDesc;
		}

		public Integer getDate() {
			return date;
		}

		public void setDate(Integer date) {
			this.date = date;
		}
		
    }
    public static void main(String args[]){
		for(JobTimeCode sc:JobTimeCode.values()){
			System.out.println("jobCode:"+sc.getJobCode()+" jobCodeDesc:"+sc.getJobCodeDesc());
		}
	}
}

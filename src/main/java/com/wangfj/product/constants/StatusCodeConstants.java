package com.wangfj.product.constants;

public class StatusCodeConstants {
	public enum StatusCode{
		EXCEPTION_JIGOU("00101001","组织机构"),
		EXCEPTION_SHOPPE("00103001","专柜"),
		EXCEPTION_BRAND("00107001","品牌"),
		EXCEPTION_SUPPLY("00108001","供应商"),
		EXCEPTION_CATEGORY("00109001","品类"),
		EXCEPTION_CONTRACT("00110001","合同"),
		EXCEPTION_PRODUCT("00111001","商品"),
		EXCEPTION_REGION("00112001","行政区域"),
		EXCEPTION_PRICE("00300001","价格"),
		EXCEPTION_STOCK("00301101","库存");

		private String status;
		private String comment;
		private StatusCode(){}
		private StatusCode(String status,String comment){
			this.status = status;
			this.comment = comment;
		}
		public static StatusCode getStatusCodeByCode(String code){
			for(StatusCode sc:StatusCode.values()){
				if(sc.getStatus()==code){
					return sc;
				}
			}
			return null;
		}
		
		public static String getStatusValue(String code){
			StatusCode sc =getStatusCodeByCode(code);
			if(sc!=null){
				return sc.getComment();
			}else{
				return null;
			}
		}
		
		public static String getStatusName(String code){
			StatusCode sc =getStatusCodeByCode(code);
			if(sc!=null){
				return sc.name();
			}else{
				return null;
			}
		}
		
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}
		
		
	}
	
	
	public static void main(String args[]){
		
		System.out.println(StatusCode.getStatusName("00301101"));
		System.out.println(StatusCode.EXCEPTION_BRAND.getStatus());
		
	}
}

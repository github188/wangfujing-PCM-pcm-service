package com.wangfj.product.constants;



public class StatusCodeConstants {
	public enum StatusCode{
		EXCEPTION_PRODUCT("1001","商品"),
		EXCEPTION_BRAND("1002","品牌"),
		EXCEPTION_CATEGORY("1003","品类"),
		EXCEPTION_SUPPLY("1004","供应商"),
		EXCEPTION_PRICE("1005","价格"),
		EXCEPTION_STOCK("1006","库存"),
		EXCEPTION_CONTRACT("1007","合同"),
		EXCEPTION_JIGOU("1008","组织机构"),
		EXCEPTION_SHOPPE("1009","专柜"),
		EXCEPTION_REGION("1010","行政区域");

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
		System.out.println(StatusCode.EXCEPTION_BRAND.getStatus());
		
	}
}

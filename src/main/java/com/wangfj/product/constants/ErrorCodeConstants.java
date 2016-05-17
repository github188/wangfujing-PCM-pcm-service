package com.wangfj.product.constants;

/**
 * 说明:
 * 
 * @author guansq
 * @date 2012-12-10 下午02:56:48
 * @modify
 */
public class ErrorCodeConstants {
    public enum ErrorCode {
        /**
         * 错误编码规范： 长度：7位 00 0 00 00 含义： 12位：系统，销售系统是09 3位：消息等级，默认为1
         * 45位：模块，(01)oms_service (02)oms_core (03)oms_sdc
         * 67位：具体错误 系统异常：0010001-0010099
         */
        /* 21100** */
        SYSTEM_ERROR("0910101", "系统运行异常！"), 
        CALL_SRC_ERROR("0910102","接口调用方来源不明"),
        PARAM_ERROR("0910103", "参数不正确！"),
        DUPLICATE_KEY_ERROR("0910104", "主键已存在！"),
        STOCK_LOCK_FAILED("0810101","库存锁定失败"),
        STOCK_SUBSTRACT_FAILED("0810102","库存减库失败"),
        MONEY_ERROR("0910105", "商品明细总金额与订单金额不一致"),
        STATUS_ERROR("0910105","需要修改的状态不对"),
        LOCK_ERROR("00100009","锁库失败"),
        LOCK_TICKET_ERROR("00100010","锁券失败"),
        GET_ORDERNO_ERROR("00100011","获取订单号失败"),
        PROMO_CALC_ERROR("0910106","促销分摊计算发生错误");
        private String errorCode;
        private String memo;

        private ErrorCode() {
        };

        private ErrorCode(String errorCode, String memo) {
            this.errorCode = errorCode;
            this.memo = memo;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }
    }
}

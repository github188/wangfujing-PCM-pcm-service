package com.wangfj.util;

import java.io.Serializable;

/**
 * ajax请求返回结果
 * status,以1,表示成功,0表示失败,(如果失败提示更多,以0开头,如01,02)
 * User: user Suhaibo
 * Date: 2010-1-27
 * Time: 11:51:34
 * To change this template use File | Settings | File Templates.
 */
public class AjaxMessageVO implements Serializable {
    /**
     * 请求的响应状态
     */
    private String status;
    /**
     * 提示消息
     */
    private String message;

    public  AjaxMessageVO(){

    }

    public  AjaxMessageVO(String status,String message){
        this.status=status;
        this.message=message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static AjaxMessageVO getInstance(){
        return new  AjaxMessageVO();
    }

    public AjaxMessageVO addstatus(String status){
        this.setStatus(status);
        return this;
    }

    public AjaxMessageVO addMessage(String message){
        this.setMessage(message);
        return this;
    }
    
}

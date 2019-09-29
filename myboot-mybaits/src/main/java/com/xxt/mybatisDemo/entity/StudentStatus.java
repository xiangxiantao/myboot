package com.xxt.mybatisDemo.entity;

/**
 *@ClassName StudentStatus
 *@Description 希望数据库保存的是枚举类型的某一个属性，需要自定义类型解析器
 *@Author xiang.xiantao
 *@Date 2019/4/2 15:52
 *@Version 1.0
 **/
public enum StudentStatus {
    LOGIN(100,"登陆"),LOGOUT(200,"登出"),REMOVE(300,"不存在");

    private Integer code;
    private String msg;
    private StudentStatus(Integer code, String msg){
        this.code=code;
        this.msg=msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

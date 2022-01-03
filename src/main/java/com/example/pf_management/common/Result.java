package com.example.pf_management.common;

import lombok.Data;

@Data
public class Result{
    private int code;

    private String msg;

    private Object data;

    public Result(int code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result succ(Object data){
        return new Result(Constant.successCode, Constant.successMsg, data);
    }

    public static Result succ(){
        return new Result(Constant.successCode, Constant.successMsg, null);
    }

    public static Result fail(int code, String msg, Object data){
        return new Result(code, msg, data);
    }

    public static Result fail(int code, String msg){
        return new Result(code, msg, null);
    }
}

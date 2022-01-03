package com.example.pf_management.common;

import lombok.Getter;

public class BusinessException extends RuntimeException{
    @Getter
    private String msg;
    @Getter
    private Throwable throwable;

    public BusinessException(String msg){
        super(msg);
        this.msg = msg;
    }

    public BusinessException(String msg, Throwable throwable){
        super(msg, throwable);
        this.throwable = throwable;
    }
}

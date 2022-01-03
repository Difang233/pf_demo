package com.example.pf_management.common;

import org.springframework.dao.DataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {MissingServletRequestParameterException.class, HttpMessageNotReadableException.class})
    public Result parameterExceptionHandler(){
        return Result.fail(Constant.paramError, "参数错误");
    }

    @ResponseBody
    @ExceptionHandler(value = DataAccessException.class)
    public Result dataExceptionHandler(DataAccessException e){
        e.printStackTrace();
        return Result.fail(Constant.dataError, "数据库错误");
    }

    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public Result businessExceptionHandler(BusinessException e){
        e.printStackTrace();
        return Result.fail(Constant.paramError, e.getMsg());
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result otherExceptionHandler(Exception e){
        e.printStackTrace();
        return Result.fail(Constant.systemError, "系统异常");
    }
}

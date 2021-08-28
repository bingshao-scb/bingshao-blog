package com.bs.auth.advice;

import com.bs.common.exception.ServiceException;
import com.bs.common.utils.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author bingshao
 * @date 2021/8/20
 * 全局异常拦截
 **/
@Slf4j
@ControllerAdvice(basePackages = "com.bs.auth.controller")
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = ServiceException.class)
    public CommonResult serviceExceptionHandler (ServiceException e) {
        log.error("GlobalExceptionHandler===========>{}",e);
        return CommonResult.failed(e.getCode(), e.getMessage());
    }
}

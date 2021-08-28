package com.bs.common.exception;

import com.bs.common.utils.ResultCode;
import lombok.Data;

/**
 * @author bingshao
 * @date 2021/8/19
 **/
@Data
public final class ServiceException extends RuntimeException {

    /**
     * 错误码
     */
    private final Integer code;

    /**
     * 信息
     */

    private final String message;

    /**
     * 构造方法
     * @param resultCode 封装的枚举类异常
     */
    public ServiceException(ResultCode resultCode) {

        this.code = resultCode.getCode();

        this.message = resultCode.getMessage();
    }

}

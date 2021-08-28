package com.bs.common.enums;

/**
 * @author bingshao
 * @date 2021/8/24
 **/
public enum DeleteFlagEnum {
    DELETE(0),
    NOT_DELETE(1);

    int code;

    DeleteFlagEnum(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

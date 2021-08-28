package com.bs.common.utils;

/**
 * @author bingshao
 * @date 2021/8/19
 **/
public enum ResultCode{
    /**
     * 异常枚举
     */
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),
    PASSWORD_ERROR(10001,"账号密码错误"),
    INVALID_TOKEN(10002,"无效的token"),
    CREATE_TOKEN_FAIL(10003,"生成token失败"),
    SAVE_BLOG_FAIL(10004,"保存博客失败"),
    UPDATE_BLOG_FAIL(10005,"修改博客失败"),
    SAVE_LABEL_FAIL(10006,"新增标签失败");
    private final Integer code;
    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

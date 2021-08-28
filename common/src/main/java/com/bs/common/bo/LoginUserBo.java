package com.bs.common.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author bingshao
 * @date 2021/8/20
 **/
@Data
public class LoginUserBo implements Serializable {
    private static final long serialVersionUID = -3830839606135034497L;

    private Long userId;

    /**
     * 用户类型，0：普通用户，1：后台管理员
     */
    private Integer userType;
}

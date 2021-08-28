package com.bs.auth.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author bingshao
 * @date 2021/8/19
 **/
@Data
public class PasswordLoginVo implements Serializable {
    private static final long serialVersionUID = 7139842004702617661L;

    private String username;

    private String password;
}

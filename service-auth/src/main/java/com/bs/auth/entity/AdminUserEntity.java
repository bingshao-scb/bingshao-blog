package com.bs.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author bingshao
 * @date 2021/8/19
 **/
@Data
@TableName("admin_user")
public class AdminUserEntity implements Serializable {
    private static final long serialVersionUID = -36127431884579433L;

    private Long id;

    private String username;

    private String password;

    /**
     * 角色，0：普通用户，1：后台管理员
     */
    private Integer role;

    private LocalDateTime crtTime;

    private LocalDateTime uptTime;
}

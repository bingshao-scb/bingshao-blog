package com.bs.blog.feign.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author bingshao
 * @date 2021/8/24
 **/
@Data
public class AdminUserInfoEntity implements Serializable {
    private static final long serialVersionUID = 4464792492223049712L;

    private Long id;

    private String nickname;

    private String pictureUrl;

    private Integer deleteFlag;

    private LocalDateTime crtTime;

    private LocalDateTime uptTime;

}

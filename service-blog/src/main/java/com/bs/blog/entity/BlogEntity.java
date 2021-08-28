package com.bs.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author bingshao
 * @date 2021/8/20
 **/
@Data
@TableName("blog")
public class BlogEntity implements Serializable {
    private static final long serialVersionUID = -2054294475759166966L;

    private Long id;

    private String title;

    private String showContent;

    private String originContent;

    private Long crtUserId;

    private Long uptUserId;

    private Integer deleteFlag;

    private LocalDateTime crtTime;

    private LocalDateTime uptTime;

}

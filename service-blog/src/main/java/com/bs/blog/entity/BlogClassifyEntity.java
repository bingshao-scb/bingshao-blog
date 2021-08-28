package com.bs.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author bingshao
 * @date 2021/8/26
 **/
@Data
@TableName("blog_classify")
public class BlogClassifyEntity implements Serializable {
    private static final long serialVersionUID = 3858125374739916803L;

    private Long blogId;

    private Integer classifyId;

    private Long crtUserId;

    private long uptUserId;

    private LocalDateTime crtTime;

    private LocalDateTime uptTime;
}

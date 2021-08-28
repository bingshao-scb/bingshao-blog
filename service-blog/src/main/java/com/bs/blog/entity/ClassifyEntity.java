package com.bs.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author bingshao
 * @date 2021/8/26
 **/
@Data
@TableName("classify")
public class ClassifyEntity implements Serializable {
    private static final long serialVersionUID = -4651605514662290205L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String columnName;

    private Long crtUserId;

    private Long uptUserId;

    private LocalDateTime crtTime;

    private LocalDateTime uptTime;
}

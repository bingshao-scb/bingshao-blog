package com.bs.blog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author bingshao
 * @date 2021/8/24
 **/
@Data
public class ListBlog implements Serializable {
    private static final long serialVersionUID = -2387220525966311503L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    private String title;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime date;

    private Integer deleteFlag;

    private List<String> classifyNames;

    private String author;

}

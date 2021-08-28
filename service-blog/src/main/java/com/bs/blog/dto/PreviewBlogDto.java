package com.bs.blog.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author bingshao
 * @date 2021/8/24
 **/
@Data
public class PreviewBlogDto implements Serializable {
    private static final long serialVersionUID = -6630368582088933432L;

    private String title;

    private String showContent;

    private String author;

    private Long views;

    private List<String> classifyNames;

}

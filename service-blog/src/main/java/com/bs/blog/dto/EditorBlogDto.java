package com.bs.blog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author bingshao
 * @date 2021/8/24
 **/
@Data
public class EditorBlogDto implements Serializable {
    private static final long serialVersionUID = 4649427400085109636L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    private String title;

    private String showContent;

    private String originContent;

    private List<String> classifyNames;

}

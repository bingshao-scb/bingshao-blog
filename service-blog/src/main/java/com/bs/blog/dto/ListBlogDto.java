package com.bs.blog.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author bingshao
 * @date 2021/8/24
 **/
@Data
public class ListBlogDto implements Serializable {
    private static final long serialVersionUID = -9148183713315976462L;

    private List<ListBlog> listBlogDtoList;

    private Long total;
}


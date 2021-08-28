package com.bs.blog.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author bingshao
 * @date 2021/8/24
 **/
@Data
public class EditorBlogVo implements Serializable {
    private static final long serialVersionUID = 1997398134422459398L;

    private Long id;

    private String title;

    private String showContent;

    private String originContent;

    private Integer deleteFlag;
}

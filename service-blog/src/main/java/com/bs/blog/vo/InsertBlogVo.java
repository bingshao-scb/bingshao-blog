package com.bs.blog.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author bingshao
 * @date 2021/8/20
 **/
@Data
public class InsertBlogVo implements Serializable {
    private static final long serialVersionUID = 3251704222047231080L;

    private List<Integer> classifyIds;

    private String title;

    private String showContent;

    private String originContent;

}

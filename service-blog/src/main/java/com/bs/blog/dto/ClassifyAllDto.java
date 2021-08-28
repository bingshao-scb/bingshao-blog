package com.bs.blog.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author bingshao
 * @date 2021/8/26
 **/
@Data
public class ClassifyAllDto implements Serializable {
    private static final long serialVersionUID = -3585340592991885739L;

    private Integer id;

    private String columnName;
}

package com.bs.blog.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author bingshao
 * @date 2021/8/28
 **/
@Data
public class InsertLabelVo implements Serializable {
    private static final long serialVersionUID = 6991583414377802594L;

    private String columnName;
}

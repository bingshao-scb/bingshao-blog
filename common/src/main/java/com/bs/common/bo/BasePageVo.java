package com.bs.common.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author bingshao
 * @date 2021/8/24
 **/
@Data
public class BasePageVo implements Serializable {

    private Integer pageNum = 1;

    private Integer pageSize = 10;
}

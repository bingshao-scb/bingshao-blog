package com.bs.common.utils;

import cn.hutool.core.util.IdUtil;

/**
 * @author bingshao
 * @date 2021/8/24
 **/
public class IdUtils {

    /**
     * 雪花算法获取id
     * @return 随机id
     */
    public static Long getSnowflakesId(){
        return IdUtil.getSnowflake(0,0).nextId();
    }
}

package com.bs.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.blog.entity.BlogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author bingshao
 * @date 2021/8/20
 **/
@Mapper
public interface BlogMapper extends BaseMapper<BlogEntity> {
}

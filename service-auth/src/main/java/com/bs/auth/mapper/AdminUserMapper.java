package com.bs.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.auth.entity.AdminUserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author bingshao
 * @date 2021/8/19
 **/
@Mapper
public interface AdminUserMapper extends BaseMapper<AdminUserEntity> {
}

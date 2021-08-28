package com.bs.blog.feign;

import com.bs.blog.feign.entity.AdminUserInfoEntity;
import com.bs.common.utils.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author bingshao
 * @date 2021/8/25
 **/
@FeignClient("service-admin")
public interface AdminUserFeign {

    /**
     * 根据userId获取管理员用户信息
     * @param id 用户id
     * @return 管理员用户信息
     */
    @GetMapping("/user/getInfoById/{id}")
    CommonResult<AdminUserInfoEntity> getInfoById(@PathVariable("id") Long id);
}

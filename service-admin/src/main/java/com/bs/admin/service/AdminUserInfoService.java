package com.bs.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.admin.dto.AdminInfoDto;
import com.bs.admin.entity.AdminUserInfoEntity;

/**
 * @author bingshao
 * @date 2021/8/24
 **/
public interface AdminUserInfoService extends IService<AdminUserInfoEntity> {

    /**
     * 获取本人信息
     * @return 本人信息
     */
    AdminInfoDto getInfo();
}

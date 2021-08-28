package com.bs.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.admin.dto.AdminInfoDto;
import com.bs.admin.entity.AdminUserInfoEntity;
import com.bs.admin.interceptor.AdminRestInterceptor;
import com.bs.admin.mapper.AdminUserInfoMapper;
import com.bs.admin.service.AdminUserInfoService;
import com.bs.common.enums.DeleteFlagEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author bingshao
 * @date 2021/8/24
 **/
@Service
public class AdminUserInfoServiceImpl extends ServiceImpl<AdminUserInfoMapper, AdminUserInfoEntity> implements AdminUserInfoService {

    @Resource
    private AdminUserInfoMapper adminUserInfoMapper;

    @Override
    public AdminInfoDto getInfo() {
        Long userId = AdminRestInterceptor.loginUserBoThreadLocal.get().getUserId();
        AdminUserInfoEntity adminUserInfoEntity = adminUserInfoMapper.selectOne(new LambdaQueryWrapper<AdminUserInfoEntity>()
                .eq(AdminUserInfoEntity::getId,userId)
                .eq(AdminUserInfoEntity::getDeleteFlag, DeleteFlagEnum.NOT_DELETE.getCode()));
        AdminInfoDto adminInfoDto = new AdminInfoDto();
        BeanUtils.copyProperties(adminUserInfoEntity,adminInfoDto);
        return adminInfoDto;
    }
}

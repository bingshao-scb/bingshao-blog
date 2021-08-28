package com.bs.admin.controller;

import com.bs.admin.dto.AdminInfoDto;
import com.bs.admin.entity.AdminUserInfoEntity;
import com.bs.admin.service.AdminUserInfoService;
import com.bs.common.utils.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author bingshao
 * @date 2021/8/24
 **/
@RestController
@RequestMapping("/user")
public class AdminUserInfoController {

    @Resource
    private AdminUserInfoService adminUserInfoService;

    @GetMapping("/getInfo")
    public CommonResult<AdminInfoDto> getInfo(){
        AdminInfoDto adminInfoDto = adminUserInfoService.getInfo();
        return CommonResult.success(adminInfoDto);
    }

    @GetMapping("getInfoById/{id}")
    public CommonResult<AdminUserInfoEntity> getInfoById(@PathVariable("id") Long id){
        AdminUserInfoEntity adminUserInfoEntity = adminUserInfoService.getById(id);
        return CommonResult.success(adminUserInfoEntity);
    }
}

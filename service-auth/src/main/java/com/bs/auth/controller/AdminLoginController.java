package com.bs.auth.controller;

import com.bs.auth.service.AdminUserService;
import com.bs.auth.vo.PasswordLoginVo;
import com.bs.common.utils.CommonResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author bingshao
 * @date 2021/8/19
 **/
@RestController
@RequestMapping("/auth")
public class AdminLoginController {

    @Resource
    private AdminUserService adminUserService;

    @PostMapping("/adminLogin")
    public CommonResult<Object> adminLogin(@RequestBody PasswordLoginVo passwordLoginVo){
        String token = adminUserService.passwordLogin(passwordLoginVo);
        return CommonResult.success(token);
    }

}

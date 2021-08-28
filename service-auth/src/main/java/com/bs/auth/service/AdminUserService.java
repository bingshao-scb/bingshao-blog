package com.bs.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.auth.entity.AdminUserEntity;
import com.bs.auth.vo.PasswordLoginVo;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @author bingshao
 * @date 2021/8/19
 **/
public interface AdminUserService extends IService<AdminUserEntity> {

    String passwordLogin(PasswordLoginVo passwordLoginVo);
}

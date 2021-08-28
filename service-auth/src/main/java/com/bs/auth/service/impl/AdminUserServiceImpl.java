package com.bs.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.auth.entity.AdminUserEntity;
import com.bs.auth.mapper.AdminUserMapper;
import com.bs.auth.service.AdminUserService;
import com.bs.auth.vo.PasswordLoginVo;
import com.bs.common.bo.LoginUserBo;
import com.bs.common.exception.ServiceException;
import com.bs.common.utils.JwtTokenUtils;
import com.bs.common.utils.ResultCode;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.DigestSignatureSpi;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @author bingshao
 * @date 2021/8/19
 **/
@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUserEntity> implements AdminUserService {

    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    public String passwordLogin(PasswordLoginVo passwordLoginVo) {
        AdminUserEntity adminUserEntity = adminUserMapper.selectOne(new LambdaQueryWrapper<AdminUserEntity>()
                .eq(AdminUserEntity::getUsername, passwordLoginVo.getUsername())
                .eq(AdminUserEntity::getPassword, DigestUtils.md5Hex(passwordLoginVo.getPassword())));
        if (ObjectUtils.isEmpty(adminUserEntity)){
            throw new ServiceException(ResultCode.PASSWORD_ERROR);
        }
        LoginUserBo loginUserBo = new LoginUserBo();
        loginUserBo.setUserId(adminUserEntity.getId());
        loginUserBo.setUserType(adminUserEntity.getRole());
        String token = null;
        try {
            token = JwtTokenUtils.creatToken(loginUserBo);
        } catch (Exception e) {
            throw new ServiceException(ResultCode.CREATE_TOKEN_FAIL);
        }
        return token;
    }
}
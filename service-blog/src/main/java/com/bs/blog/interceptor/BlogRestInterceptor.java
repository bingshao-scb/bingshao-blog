package com.bs.blog.interceptor;

import com.bs.common.bo.LoginUserBo;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author bingshao
 * @date 2021/8/24
 **/
public class BlogRestInterceptor extends HandlerInterceptorAdapter {
    public static ThreadLocal<LoginUserBo> loginUserBoThreadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getHeader("userId");
        String userType = request.getHeader("userType");
        LoginUserBo loginUserBo = new LoginUserBo();
        loginUserBo.setUserId(Long.valueOf(userId));
        loginUserBo.setUserType(Integer.valueOf(userType));
        loginUserBoThreadLocal.set(loginUserBo);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        loginUserBoThreadLocal.remove();
    }
}

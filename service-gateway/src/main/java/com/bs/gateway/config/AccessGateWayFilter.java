package com.bs.gateway.config;

import com.bs.common.bo.LoginUserBo;
import com.bs.common.constants.LoginConstant;
import com.bs.common.exception.ServiceException;
import com.bs.common.utils.JwtTokenUtils;
import com.bs.common.utils.ResultCode;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author bingshao
 * @date 2021/8/21
 **/
@Component
public class AccessGateWayFilter implements GlobalFilter, Order {

    /**
     * 路径匹配器
     */
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public boolean match(String u,String s){
        return antPathMatcher.match(u, s);
    }


    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String url = exchange.getRequest().getURI().getPath();

        /**
         *跳过放行的url
         */
        for (String skip : getExcludePathPatterns()) {
            if (match(skip, url)) {
                return chain.filter(exchange);
            }
        }
        String token = exchange.getRequest().getHeaders().getFirst(LoginConstant.AUTHORIZATION);
        try{
            LoginUserBo loginUser = JwtTokenUtils.verifierToken(token);
            ServerHttpRequest host = exchange.getRequest().mutate().header("userId", loginUser.getUserId().toString())
                    .header("userType",loginUser.getUserType().toString()).build();
            ServerWebExchange build = exchange.mutate().request(host).build();
            return chain.filter(build);
        }catch (Exception e){
            throw new ServiceException(ResultCode.INVALID_TOKEN);
        }
    }

    @Override
    public int value() {
        return 0;
    }

    /**
     * 放行的url
     * @return 需要放行的url
     */
    private ArrayList<String> getExcludePathPatterns(){
        ArrayList<String> list = new ArrayList<>();
        String [] urls ={
                "/auth/adminLogin",
                "/blog/getListBlog",
                "blog/getPreviewBlog/{id}"
        };
        Collections.addAll(list,urls);
        return list;
    }
}

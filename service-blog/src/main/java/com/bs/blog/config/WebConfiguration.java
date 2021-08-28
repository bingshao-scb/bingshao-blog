package com.bs.blog.config;

import com.bs.blog.interceptor.BlogRestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author bingshao
 * @date 2021/8/24
 **/
@Configuration("blogWebConfig")
@Primary
public class WebConfiguration implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getBlogRestInterceptor()).addPathPatterns(getIncludePathPatterns()).excludePathPatterns(getExcludePathPatterns());
    }

    @Bean
    BlogRestInterceptor getBlogRestInterceptor(){
        return new BlogRestInterceptor();
    }


    /**
     * 需要拦截的url
     * @return 需要拦截的url
     */
    private ArrayList<String> getIncludePathPatterns() {
        ArrayList<String> list = new ArrayList<>();
        String[] urls = {
                "/blog/**",
                "/classify/**"
        };
        Collections.addAll(list, urls);
        return list;
    }


    /**
     * 需要放行的url
     * @return 需要放行的url
     */
    private ArrayList<String> getExcludePathPatterns(){
        ArrayList<String> list = new ArrayList<>();
        String[] urls = {
                "/blog/getListBlog",
                "/blog/getPreviewBlog/{id}"
        };
        Collections.addAll(list, urls);
        return list;
    }

}

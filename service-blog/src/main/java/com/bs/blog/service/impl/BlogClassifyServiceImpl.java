package com.bs.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.blog.entity.BlogClassifyEntity;
import com.bs.blog.mapper.BlogClassifyMapper;
import com.bs.blog.service.BlogClassifyService;
import org.springframework.stereotype.Service;

/**
 * @author bingshao
 * @date 2021/8/26
 **/
@Service
public class BlogClassifyServiceImpl extends ServiceImpl<BlogClassifyMapper, BlogClassifyEntity> implements BlogClassifyService {
}

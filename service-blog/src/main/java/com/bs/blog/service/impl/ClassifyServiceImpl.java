package com.bs.blog.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.blog.dto.ClassifyAllDto;
import com.bs.blog.entity.ClassifyEntity;
import com.bs.blog.interceptor.BlogRestInterceptor;
import com.bs.blog.mapper.ClassifyMapper;
import com.bs.blog.service.ClassifyService;
import com.bs.blog.vo.InsertLabelVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * @author bingshao
 * @date 2021/8/26
 **/
@Service
public class ClassifyServiceImpl extends ServiceImpl<ClassifyMapper, ClassifyEntity> implements ClassifyService {

    @Resource
    private ClassifyMapper classifyMapper;


    @Override
    public List<ClassifyAllDto> getAllClassify() {
        List<ClassifyAllDto> classifyAllDtoList = new LinkedList<>();
        List<ClassifyEntity> classifyEntityList = classifyMapper.selectList(new LambdaQueryWrapper<>());
        if (ObjectUtil.isNotNull(classifyEntityList)){
            classifyEntityList.forEach(classifyEntity -> {
                ClassifyAllDto classifyAllDto = new ClassifyAllDto();
                classifyAllDto.setId(classifyEntity.getId());
                classifyAllDto.setColumnName(classifyEntity.getColumnName());
                classifyAllDtoList.add(classifyAllDto);
            });
        }
        return classifyAllDtoList;
    }

    @Override
    public int addLabel(InsertLabelVo insertLabelVo) {
        Long userId = BlogRestInterceptor.loginUserBoThreadLocal.get().getUserId();
        ClassifyEntity classifyEntity = new ClassifyEntity();
        classifyEntity.setColumnName(insertLabelVo.getColumnName());
        classifyEntity.setCrtUserId(userId);
        classifyEntity.setUptUserId(userId);
        classifyEntity.setCrtTime(LocalDateTime.now());
        classifyEntity.setUptTime(LocalDateTime.now());
        return classifyMapper.insert(classifyEntity);
    }
}

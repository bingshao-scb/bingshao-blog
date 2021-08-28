package com.bs.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.blog.dto.ClassifyAllDto;
import com.bs.blog.entity.ClassifyEntity;
import com.bs.blog.vo.InsertLabelVo;

import java.util.List;

/**
 * @author bingshao
 * @date 2021/8/26
 **/
public interface ClassifyService extends IService<ClassifyEntity> {

    /**
     * 获取所有标签
     * @return 标签名称
     */
    List<ClassifyAllDto> getAllClassify();

    /**
     * 新增标签
     * @param insertLabelVo 标签名字
     * @return 影响的行数
     */
    int addLabel(InsertLabelVo insertLabelVo);
}

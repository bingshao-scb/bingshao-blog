package com.bs.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.blog.dto.EditorBlogDto;
import com.bs.blog.dto.ListBlogDto;
import com.bs.blog.dto.PreviewBlogDto;
import com.bs.blog.entity.BlogEntity;
import com.bs.blog.vo.EditorBlogVo;
import com.bs.blog.vo.InsertBlogVo;
import com.bs.common.bo.BasePageVo;


/**
 * @author bingshao
 * @date 2021/8/20
 **/
public interface BlogService extends IService<BlogEntity> {

    /**
     * 写博客
     * @param insertBlogVo 接受的参数
     * @return 受影响的行数
     */
    int saveBlog(InsertBlogVo insertBlogVo);

    /**
     * 分页获取博客
     * @param basePageVo 分页参数
     * @return 博客
     */
    ListBlogDto getListBlog(BasePageVo basePageVo);

    /**
     * 预览博客
     * @param id 博客id
     * @return 博客内容html解析格式
     */
    PreviewBlogDto getPreviewBlog(Long id);

    /**
     * 编辑博客
     * @param id 博客id
     * @return 博客的编辑页面详情
     */
    EditorBlogDto getEditorBlog(Long id);

    /**
     * 修改博客
     * @param editorBlogVo 需要修改的参数
     * @return 受影响行
     */
    int updateBlog(EditorBlogVo editorBlogVo);
}

package com.bs.blog.controller;

import com.bs.blog.dto.EditorBlogDto;
import com.bs.blog.dto.ListBlogDto;
import com.bs.blog.dto.PreviewBlogDto;
import com.bs.blog.service.BlogService;
import com.bs.blog.vo.EditorBlogVo;
import com.bs.blog.vo.InsertBlogVo;
import com.bs.common.bo.BasePageVo;
import com.bs.common.utils.CommonResult;
import com.bs.common.utils.ResultCode;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @author bingshao
 * @date 2021/8/20
 **/
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Resource
    private BlogService blogService;

    @PostMapping("/saveBlog")
    public CommonResult<ResultCode> saveBlog(@RequestBody InsertBlogVo insertBlogVo){
        if(blogService.saveBlog(insertBlogVo)>0){
            return CommonResult.success(ResultCode.SUCCESS);
        }else {
            return CommonResult.failed(ResultCode.SAVE_BLOG_FAIL);
        }
    }

    @PostMapping("/getListBlog")
    public CommonResult<ListBlogDto> getListBlog(@RequestBody BasePageVo basePageVo){
        ListBlogDto listBlogDto = blogService.getListBlog(basePageVo);
        return CommonResult.success(listBlogDto);
    }

    @GetMapping("/getPreviewBlog/{id}")
    public CommonResult<PreviewBlogDto> getPreviewBlog(@PathVariable("id") Long id){
        PreviewBlogDto previewBlog = blogService.getPreviewBlog(id);
        return CommonResult.success(previewBlog);
    }

    @GetMapping("/getEditorBlog/{id}")
    public CommonResult<EditorBlogDto> getEditorBlog(@PathVariable("id") Long id){
        EditorBlogDto editorBlog = blogService.getEditorBlog(id);
        return CommonResult.success(editorBlog);
    }

    @PutMapping("/updateBlog")
    public CommonResult<ResultCode> updateBlog(@RequestBody EditorBlogVo editorBlogVo){
        if(blogService.updateBlog(editorBlogVo)>0){
            return CommonResult.success(ResultCode.SUCCESS);
        }else {
            return CommonResult.failed(ResultCode.UPDATE_BLOG_FAIL);
        }
    }
}

package com.bs.blog.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.blog.dto.EditorBlogDto;
import com.bs.blog.dto.ListBlog;
import com.bs.blog.dto.ListBlogDto;
import com.bs.blog.dto.PreviewBlogDto;
import com.bs.blog.entity.BlogClassifyEntity;
import com.bs.blog.entity.BlogEntity;
import com.bs.blog.feign.AdminUserFeign;
import com.bs.blog.feign.entity.AdminUserInfoEntity;
import com.bs.blog.interceptor.BlogRestInterceptor;
import com.bs.blog.mapper.BlogMapper;
import com.bs.blog.service.BlogClassifyService;
import com.bs.blog.service.BlogService;
import com.bs.blog.service.ClassifyService;
import com.bs.blog.vo.EditorBlogVo;
import com.bs.blog.vo.InsertBlogVo;
import com.bs.common.bo.BasePageVo;
import com.bs.common.constants.BlogViewConstant;
import com.bs.common.enums.DeleteFlagEnum;
import com.bs.common.utils.CommonResult;
import com.bs.common.utils.IdUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * @author bingshao
 * @date 2021/8/20
 **/
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, BlogEntity> implements BlogService {

    @Resource
    private BlogMapper blogMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private AdminUserFeign adminUserFeign;

    @Resource
    private ClassifyService classifyService;

    @Resource
    private BlogClassifyService blogClassifyService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveBlog(InsertBlogVo insertBlogVo) {
        Long id = IdUtils.getSnowflakesId();
        Long userId = BlogRestInterceptor.loginUserBoThreadLocal.get().getUserId();
        BlogEntity blogEntity = new BlogEntity();
        BeanUtils.copyProperties(insertBlogVo,blogEntity);
        blogEntity.setCrtUserId(userId);
        blogEntity.setUptUserId(userId);
        blogEntity.setDeleteFlag(DeleteFlagEnum.NOT_DELETE.getCode());
        blogEntity.setCrtTime(LocalDateTime.now());
        blogEntity.setUptTime(LocalDateTime.now());
        blogEntity.setId(id);
        List<Integer> classifyIds = insertBlogVo.getClassifyIds();
        classifyIds.forEach(classifyId->{
            BlogClassifyEntity blogClassifyEntity = new BlogClassifyEntity();
            blogClassifyEntity.setBlogId(id);
            blogClassifyEntity.setClassifyId(classifyId);
            blogClassifyEntity.setCrtUserId(userId);
            blogClassifyEntity.setUptUserId(userId);
            blogClassifyEntity.setCrtTime(LocalDateTime.now());
            blogClassifyEntity.setUptTime(LocalDateTime.now());
            blogClassifyService.save(blogClassifyEntity);
        });
        return blogMapper.insert(blogEntity);
    }

    @Override
    public ListBlogDto getListBlog(BasePageVo basePageVo) {
        PageHelper.startPage(basePageVo.getPageNum(),basePageVo.getPageSize());
        List<BlogEntity> blogEntityList = blogMapper.selectList(new LambdaQueryWrapper<BlogEntity>().orderByDesc(BlogEntity::getUptTime));
        PageInfo<BlogEntity> pageInfo = new PageInfo<>(blogEntityList);
        List<ListBlog> listBlogList = new LinkedList<>();
        ListBlogDto listBlogDto = new ListBlogDto();
        if (ObjectUtil.isNotNull(pageInfo)){
            pageInfo.getList().forEach(blogEntity -> {
                ListBlog listBlog = new ListBlog();
                List<String> classifyNames = new LinkedList<>();
                listBlog.setId(blogEntity.getId());
                listBlog.setTitle(blogEntity.getTitle());
                listBlog.setDeleteFlag(blogEntity.getDeleteFlag());
                listBlog.setDate(blogEntity.getUptTime());
                List<BlogClassifyEntity> blogClassifyEntityList = blogClassifyService.list(new LambdaQueryWrapper<BlogClassifyEntity>()
                        .eq(BlogClassifyEntity::getBlogId, blogEntity.getId()));
                blogClassifyEntityList.forEach(blogClassifyEntity -> classifyNames.add(classifyService.getById(blogClassifyEntity.getClassifyId()).getColumnName()));
                listBlog.setClassifyNames(classifyNames);
                CommonResult<AdminUserInfoEntity> adminUserInfoEntityCommonResult = adminUserFeign.getInfoById(blogEntity.getCrtUserId());
                AdminUserInfoEntity adminUserInfoEntity = adminUserInfoEntityCommonResult.getData();
                Assert.notNull(adminUserInfoEntity,"系统错误");
                listBlog.setAuthor(adminUserInfoEntity.getNickname());
                listBlogList.add(listBlog);
            });
        }
        listBlogDto.setListBlogDtoList(listBlogList);
        listBlogDto.setTotal(pageInfo.getTotal());
        return listBlogDto;
    }

    @Override
    public PreviewBlogDto getPreviewBlog(Long id) {
        Long views = redisTemplate.opsForValue().increment(BlogViewConstant.VIEW + id);
        BlogEntity blogEntity = blogMapper.selectById(id);
        PreviewBlogDto previewBlogDto = new PreviewBlogDto();
        if (ObjectUtil.isNotNull(blogEntity)){
            List<String> classifyNames = new LinkedList<>();
            previewBlogDto.setTitle(blogEntity.getTitle());
            previewBlogDto.setShowContent(blogEntity.getShowContent());
            CommonResult<AdminUserInfoEntity> adminUserInfoEntityCommonResult = adminUserFeign.getInfoById(blogEntity.getCrtUserId());
            AdminUserInfoEntity adminUserInfoEntity = adminUserInfoEntityCommonResult.getData();
            Assert.notNull(adminUserInfoEntity,"系统错误");
            previewBlogDto.setAuthor(adminUserInfoEntity.getNickname());
            previewBlogDto.setViews(views);
            List<BlogClassifyEntity> blogClassifyEntityList = blogClassifyService.list(new LambdaQueryWrapper<BlogClassifyEntity>()
                    .eq(BlogClassifyEntity::getBlogId, blogEntity.getId()));
            blogClassifyEntityList.forEach(blogClassifyEntity -> classifyNames.add(classifyService.getById(blogClassifyEntity.getClassifyId()).getColumnName()));
            previewBlogDto.setClassifyNames(classifyNames);
        }
        return previewBlogDto;
    }

    @Override
    public EditorBlogDto getEditorBlog(Long id) {
        BlogEntity blogEntity = blogMapper.selectById(id);
        EditorBlogDto editorBlogDto = new EditorBlogDto();
        if (ObjectUtil.isNotNull(blogEntity)){
            List<String> classifyNames = new LinkedList<>();
            editorBlogDto.setId(blogEntity.getId());
            editorBlogDto.setTitle(blogEntity.getTitle());
            editorBlogDto.setShowContent(blogEntity.getShowContent());
            editorBlogDto.setOriginContent(blogEntity.getOriginContent());
            List<BlogClassifyEntity> blogClassifyEntityList = blogClassifyService.list(new LambdaQueryWrapper<BlogClassifyEntity>()
                    .eq(BlogClassifyEntity::getBlogId, blogEntity.getId()));
            blogClassifyEntityList.forEach(blogClassifyEntity -> classifyNames.add(classifyService.getById(blogClassifyEntity.getClassifyId()).getColumnName()));
            editorBlogDto.setClassifyNames(classifyNames);
        }
        return editorBlogDto;
    }

    @Override
    public int updateBlog(EditorBlogVo editorBlogVo) {
        Long userId = BlogRestInterceptor.loginUserBoThreadLocal.get().getUserId();
        BlogEntity blogEntity = new BlogEntity();
        BeanUtils.copyProperties(editorBlogVo,blogEntity);
        blogEntity.setUptUserId(userId);
        blogEntity.setUptTime(LocalDateTime.now());
        return blogMapper.update(blogEntity,new LambdaUpdateWrapper<BlogEntity>().eq(BlogEntity::getId,editorBlogVo.getId()));
    }
}

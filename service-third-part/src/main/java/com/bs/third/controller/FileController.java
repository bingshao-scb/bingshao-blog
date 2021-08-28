package com.bs.third.controller;

import com.bs.common.utils.CommonResult;
import com.bs.third.service.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author bingshao
 * @date 2021/8/23
 **/
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private FileService fileService;

    @PostMapping("/uoloadFiles")
    public CommonResult<List<String>> uoloadFiles(@RequestPart("multipartFiles") MultipartFile[] multipartFiles){
        List<String> urlList = fileService.uploadFile(multipartFiles);
        return CommonResult.success(urlList);
    }
}

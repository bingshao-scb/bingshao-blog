package com.bs.third.service.impl;

import com.bs.third.service.FileService;
import com.bs.third.utils.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author bingshao
 * @date 2021/8/23
 **/
@Service
public class FileServiceImpl implements FileService {

    @Resource
    FileUtils fileUtils;


    @Override
    public List<String> uploadFile(MultipartFile[] multipartFiles) {
        return fileUtils.uploadFile(multipartFiles);
    }
}

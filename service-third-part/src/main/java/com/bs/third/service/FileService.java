package com.bs.third.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author bingshao
 * @date 2021/8/23
 **/
public interface FileService {

    List<String> uploadFile(MultipartFile[] multipartFiles);
}

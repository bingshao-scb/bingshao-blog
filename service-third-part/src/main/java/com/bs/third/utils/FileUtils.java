package com.bs.third.utils;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author bingshao
 * @date 2021/8/23
 **/
@PropertySource("classpath:bootstrap.yml")
@Component
public class FileUtils {

    @Value("${spring.cloud.alicloud.oss.bucket}")
    public String bucketName;

    @Value("${spring.cloud.alicloud.oss.endpoint}")
    public String endpoint;

    @Autowired
    public OSS ossClient;

    public List<String> uploadFile(MultipartFile[] multipartFiles){
        List<String> urlList = new LinkedList<>();
        Arrays.stream(multipartFiles).forEach(multipartFile -> {
            String originalFilename = multipartFile.getOriginalFilename();
            String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            String dir = format + "/";
            String fileName = originalFilename;
            fileName = dir + fileName;
            URL url = null;
            try {
                InputStream inputStream = multipartFile.getInputStream();
                ossClient.putObject(bucketName, fileName, inputStream);
                //设置返回的url有效期为10年
                Date expiration = new Date(System.currentTimeMillis() + 10* 365 * 24 * 60 * 60 * 1000);
                GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName,fileName, HttpMethod.GET);
                req.setExpiration(expiration);
                //回显url
                url = ossClient.generatePresignedUrl(req);
                urlList.add(url.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }
            finally {
//                ossClient.shutdown();
            }
        });

        return urlList;
    }
}

package com.tyrone.blog.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yingxiu.zty
 * @createDate on 2025/6/14
 * @createTime on 23:47
 */
@Service
public interface UploadService {

    /**
     * 通用文件上传
     */
    String upload(MultipartFile file, String dir);

    /**
     * 图片文件上传（自动校验）
     */
    String uploadImage(MultipartFile file, String dir);

}

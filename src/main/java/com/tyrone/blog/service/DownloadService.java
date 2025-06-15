package com.tyrone.blog.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author yingxiu.zty
 * @createDate on 2025/6/15
 * @createTime on 00:15
 */
@Service
public interface DownloadService {

    /**
     * 文件下载
     */
    ResponseEntity<byte[]> download(String filename);

    /**
     * 获取文件临时访问URL
     */
    String getFileUrl(String filename);
}

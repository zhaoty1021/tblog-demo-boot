package com.tyrone.blog.service.impl;

import com.tyrone.blog.service.DownloadService;
import com.tyrone.blog.utils.file.MinioUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yingxiu.zty
 * @createDate on 2025/6/15
 * @createTime on 00:15
 */
@Service
public class DownloadServiceImpl implements DownloadService {

    @Resource
    private MinioUtils minioUtils;

    @Override
    public ResponseEntity<byte[]> download(String filename) {
        return minioUtils.downloadFile(filename);
    }

    @Override
    public String getFileUrl(String filename) {
        return minioUtils.getFileUrl(filename);
    }
}


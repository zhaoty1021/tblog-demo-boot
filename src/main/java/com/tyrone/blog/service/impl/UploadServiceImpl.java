package com.tyrone.blog.service.impl;

import cn.hutool.core.date.DateUtil;
import com.tyrone.blog.enums.CodeEnum;
import com.tyrone.blog.exceptions.BizException;
import com.tyrone.blog.service.UploadService;
import com.tyrone.blog.utils.StringUtils;
import com.tyrone.blog.utils.file.MinioUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * @author yingxiu.zty
 * @createDate on 2025/6/14
 * @createTime on 23:50
 */
@Service
public class UploadServiceImpl implements UploadService {

    @Resource
    private MinioUtils minioUtils;


    @Override
    public String upload(MultipartFile file, String prefix) {
        // 生成存储路径
        String filePath = buildFilePath(prefix, file.getOriginalFilename());

        // 上传文件
        minioUtils.uploadFile(file, filePath);
        return minioUtils.getPermanentUrl(filePath);
    }

    @Override
    public String uploadImage(MultipartFile file, String prefix) {
        // 校验文件类型
        if (!minioUtils.isImage(file)) {
            throw new BizException(CodeEnum.FILE_TYPE_ERROR, "只允许上传图片文件");
        }
        return upload(file, prefix);
    }

    /**
     * 构建文件存储路径
     * 格式: {prefix}/{yyyyMMdd}/{UUID}.{ext}
     */
    private String buildFilePath(String prefix, String originalFilename) {
        // 1. 标准化前缀（去除首尾斜杠）
        String normalizedPrefix = StringUtils.strip(prefix, "/");

        // 2. 获取日期部分
        String datePath = DateUtil.format(new Date(), "yyyyMMdd");

        // 3. 生成唯一文件名
        String filename = generateFilename(originalFilename);

        return String.format("%s/%s/%s",
                normalizedPrefix,
                datePath,
                filename);
    }

    /**
     * 生成唯一文件名
     * 格式: {UUID}.{ext}
     */
    private String generateFilename(String originalFilename) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String ext = minioUtils.getExtension(originalFilename);
        return uuid + (StringUtils.isEmpty(ext) ? "" : "." + ext.toLowerCase());
    }
}

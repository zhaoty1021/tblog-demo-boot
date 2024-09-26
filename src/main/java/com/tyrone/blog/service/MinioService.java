package com.tyrone.blog.service;

import com.tyrone.blog.enums.CodeEnum;
import com.tyrone.blog.exceptions.BizException;
import io.minio.*;
import io.minio.http.Method;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * @author yingxiu.zty
 * @createTime on 2024/9/26
 * @description
 */
@Service
public class MinioService {
    @Value("${minio.bucket-name}")
    private String bucketName;

    @Resource
    private MinioClient minioClient;

    /**
     * description: 判断bucket是否存在，不存在则创建
     * @param name name of the bucket
     *
     */
    public boolean existBucket(String name) {
        try {
            boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(name).build());
            return exists;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * description: 上传文件
     *
     * @param file 文件
     * @return List<String> 文件名集合
     */
    public boolean uploadFile(MultipartFile file){
        try {
            // 检查存储桶是否存在
            if(!existBucket(bucketName)){
                throw new BizException(CodeEnum.BUCKET_NOT_EXIST);
            }
            // 上传文件
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(file.getOriginalFilename())
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
            return true;
        }catch (Exception e){
            throw new BizException(CodeEnum.UPLOAD_FILE_ERROR);
        }

    }

    /**
     * description: 下载文件
     *
     * @param filename 文件名
     * @param response 响应
     */
    public void downloadFile(HttpServletResponse response, String filename){
        InputStream in = null;
        try {
            // 获取对象信息
            StatObjectResponse stat = minioClient.statObject(StatObjectArgs.builder().bucket(bucketName).object(filename).build());
            response.setContentType(stat.contentType());
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            // 文件下载
            in = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(filename).build());
            IOUtils.copy(in, response.getOutputStream());
        } catch (Exception e) {
            throw new BizException(CodeEnum.DOWNLOAD_FILE_ERROR);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    throw new BizException(CodeEnum.DOWNLOAD_FILE_ERROR);
                }
            }
        }
    }
    /**
     * 获取文件访问地址
     *
     * @param fileName 文件名称
     */
    public String getFileUrl(String fileName) {
        try {
            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(bucketName)
                    .object(fileName)
                    .build()
            );
        } catch (Exception e) {
            throw new BizException(CodeEnum.GET_FILE_URL_ERROR);
        }
    }
}

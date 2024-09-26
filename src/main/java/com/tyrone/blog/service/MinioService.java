package com.tyrone.blog.service;

import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.tyrone.blog.enums.CodeEnum;
import com.tyrone.blog.exceptions.BizException;
import io.minio.*;
import io.minio.http.Method;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;

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
     * @return ResponseEntity<byte[]> 文件
     */
    public ResponseEntity<byte[]> downloadFile(String filename){
        ResponseEntity<byte[]> responseEntity = null;
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(filename).build());
            out = new ByteArrayOutputStream();
            IOUtils.copy(in, out);
            //封装返回值
            byte[] bytes = out.toByteArray();
            HttpHeaders headers = new HttpHeaders();
            try {
                headers.add("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, Constants.UTF_8));
            } catch (UnsupportedEncodingException e) {
                throw new BizException(CodeEnum.DOWNLOAD_FILE_ERROR);
            }
            headers.setContentLength(bytes.length);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setAccessControlExposeHeaders(Arrays.asList("*"));
            responseEntity = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            throw new BizException(CodeEnum.DOWNLOAD_FILE_ERROR);
        } finally {
            try {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        throw new BizException(CodeEnum.DOWNLOAD_FILE_ERROR);
                    }
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                throw new BizException(CodeEnum.DOWNLOAD_FILE_ERROR);
            }
        }
        return responseEntity;
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

package com.tyrone.blog.utils.file;

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
 * @createTime on 2024/10/10
 * @description
 */
@Service
public class MinioUtils {
    @Value("${minio.bucket-name}")
    private String bucketName;

    @Value("${minio.endpoint}")
    private String baseUrl;

    @Resource
    private MinioClient minioClient;

    public MinioUtils(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    /**
     * description: 判断bucket是否存在，不存在则创建
     *
     */
    private void ensureBucketExists() throws Exception {
        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    /**
     * description: 上传文件
     *
     * @param file 文件
     * @return List<String> 文件名集合
     */
    public boolean uploadFile(MultipartFile file, String objectName) {
        try {
            // 确保存储桶存在
            ensureBucketExists();

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());
            return true;
        } catch (Exception e) {
            throw new BizException("文件上传失败: " + e.getMessage());
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

    public String getPermanentUrl(String fileName) {
        return baseUrl+ "/" + bucketName + "/" + fileName;
    }

    public boolean isImage(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return false;
        }
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }

    public String getExtension(String filename) {
        if (filename == null) {
            return null;
        }
        int index = filename.lastIndexOf('.');
        return index == -1 ? "" : filename.substring(index + 1);
    }
}


package com.tyrone.blog.controller;

import com.tyrone.blog.domain.response.ResultResponse;
import com.tyrone.blog.exceptions.BizException;
import com.tyrone.blog.utils.file.MinioUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author yingxiu.zty
 * @createTime on 2024/9/26
 * @description minio控制器
 */
@RestController
@RequestMapping("/api/minio")
@Tag(name = "MinioController")
public class MinioController {

    @Resource
    private MinioUtils minioUtils;

    @Value("test/")
    private String dir;

    @PostMapping(value = "/upload")
    @Operation(summary = "上传文件")
    public ResultResponse upload(@RequestParam("file") MultipartFile file){
        try {
            if(minioUtils.uploadFile(file,dir)){
                return ResultResponse.success(true, "上传成功");
            }else {
                return ResultResponse.success(false, "上传失败");
            }
        }catch (BizException e){
            return ResultResponse.error(e);
        }
    }

    @PostMapping(value = "/download")
    @Operation(summary = "下载文件")
    public ResultResponse upload(@RequestParam String fileName){
        try {
            return ResultResponse.success(minioUtils.downloadFile(fileName), "下载成功");
        }catch (BizException e){
            return ResultResponse.error(e);
        }
    }
}

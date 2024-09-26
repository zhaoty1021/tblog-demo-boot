package com.tyrone.blog.controller;

import com.tyrone.blog.domain.response.ResultResponse;
import com.tyrone.blog.exceptions.BizException;
import com.tyrone.blog.service.MinioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author yingxiu.zty
 * @createTime on 2024/9/26
 * @description
 */
@RestController
@RequestMapping("/api/minio")
public class MinioController {

    @Resource
    private MinioService minioService;

    @PostMapping(value = "/upload")
    public ResultResponse upload(@RequestParam("file") MultipartFile file){
        try {
            if(minioService.uploadFile(file)){
                return ResultResponse.success(true, "上传成功");
            }else {
                return ResultResponse.success(false, "上传失败");
            }
        }catch (BizException e){
            return ResultResponse.error(e);
        }
    }
}

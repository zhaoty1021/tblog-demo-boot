package com.tyrone.blog.controller;

import com.tyrone.blog.domain.response.ResultResponse;
import com.tyrone.blog.exceptions.BizException;
import com.tyrone.blog.service.DownloadService;
import com.tyrone.blog.service.UploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author yingxiu.zty
 * @createDate on 2025/6/14
 * @createTime on 23:42
 */
@RestController
@RequestMapping("/api/file")
@Tag(name = "文件管理")
public class FileController {

    @Resource
    private UploadService uploadService;

    @Resource
    private DownloadService downloadService;

    /**
     * 通用文件上传接口
     */
    @PostMapping("/upload")
    @Operation(summary = "通用文件上传")
    public ResultResponse<String> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "dir", required = false, defaultValue = "") String dir) {
        try {
            String fileUrl = uploadService.upload(file, dir);
            return ResultResponse.success(fileUrl, "上传成功");
        } catch (BizException e) {
            return ResultResponse.error(e);
        }
    }

    /**
     * 头像专用上传接口
     */
    @PostMapping("/avatar")
    @Operation(summary = "上传头像")
    public ResultResponse<String> uploadAvatar(@RequestParam("file") MultipartFile file) {
        try {
            // 指定头像存储目录
            String fileUrl = uploadService.uploadImage(file, "avatars");
            return ResultResponse.success(fileUrl, "头像上传成功");
        } catch (BizException e) {
            return ResultResponse.error(e);
        }
    }

    /**
     * 获取文件临时访问URL
     */
    @GetMapping("/url")
    @Operation(summary = "获取文件访问URL")
    public ResultResponse<String> getFileUrl(
            @RequestParam("filename") String filename) {
        try {
            String url = downloadService.getFileUrl(filename);
            return ResultResponse.success(url, "获取成功");
        } catch (BizException e) {
            return ResultResponse.error(e);
        }
    }

    /**
     * 文件下载接口（保留ResponseEntity返回方式）
     */
    @GetMapping("/download")
    @Operation(summary = "文件下载")
    public ResponseEntity<byte[]> downloadFile(
            @RequestParam("filename") String filename) {
        return downloadService.download(filename);
    }
}


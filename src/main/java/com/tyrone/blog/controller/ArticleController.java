package com.tyrone.blog.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.tyrone.blog.annotation.SysLog;
import com.tyrone.blog.domain.dto.ArticleDTO;
import com.tyrone.blog.domain.response.Pagination;
import com.tyrone.blog.domain.response.ResultResponse;
import com.tyrone.blog.domain.vo.ArticleVO;
import com.tyrone.blog.exceptions.BizException;
import com.tyrone.blog.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author tyronechiao
 * @createTime on 2025/6/24
 */
@RestController
@RequestMapping("/api/articles")
@Tag(name = "文章管理")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @PostMapping("/create")
    @SaCheckLogin
    @SysLog("创建文章")
    @Operation(summary = "创建文章")
    public ResultResponse<ArticleVO> createArticle(@RequestBody ArticleDTO articleDTO) {
        try {
            return ResultResponse.success(
                    articleService.createArticle(articleDTO),
                    "创建文章成功");
        } catch (BizException e) {
            return ResultResponse.fail(e.getCode(), e.getMessage());
        }
    }

    @PostMapping("/update/{id}")
    @SaCheckLogin
    @SysLog("更新文章")
    @Operation(summary = "更新文章")
    public ResultResponse<ArticleVO> updateArticle(@RequestBody ArticleDTO articleDTO) {
        try {
            return ResultResponse.success(
                    articleService.updateArticle(articleDTO),
                    "更新文章成功");
        } catch (BizException e) {
            return ResultResponse.fail(e.getCode(), e.getMessage());
        }
    }

    @GetMapping("/detail/{id}")
    @SysLog("获取文章详情")
    @Operation(summary = "获取文章详情")
    public ResultResponse<ArticleVO> getArticle(@PathVariable Long id) {
        try {
            return ResultResponse.success(
                    articleService.getArticleById(id),
                    "获取文章成功");
        } catch (BizException e) {
            return ResultResponse.fail(e.getCode(), e.getMessage());
        }
    }

    @GetMapping("/list")
    @SysLog("分页查询文章列表")
    @Operation(summary = "分页查询文章列表")
    public ResultResponse<Pagination<ArticleVO>> listArticles(
            @RequestParam(defaultValue = "1") int currentPage,
            @RequestParam(defaultValue = "10") int pageSize) {
        try {
            return ResultResponse.success(
                    articleService.listArticlesByPage(currentPage, pageSize),
                    "查询成功");
        } catch (BizException e) {
            return ResultResponse.fail(e.getCode(), e.getMessage());
        }
    }

    @PostMapping("/delete/{id}")
    @SaCheckLogin
    @SysLog("删除文章")
    @Operation(summary = "删除文章")
    public ResultResponse<Void> deleteArticle(@PathVariable Long id) {
        try {
            articleService.deleteArticle(id);
            return ResultResponse.success(null, "删除文章成功");
        } catch (BizException e) {
            return ResultResponse.fail(e.getCode(), e.getMessage());
        }
    }
}
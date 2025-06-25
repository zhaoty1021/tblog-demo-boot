package com.tyrone.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tyrone.blog.converter.ArticleConverter;
import com.tyrone.blog.domain.dto.ArticleDTO;
import com.tyrone.blog.domain.pojo.Article;
import com.tyrone.blog.domain.response.Pagination;
import com.tyrone.blog.domain.vo.ArticleVO;
import com.tyrone.blog.enums.CodeEnum;
import com.tyrone.blog.exceptions.BizException;
import com.tyrone.blog.mapper.ArticleMapper;
import com.tyrone.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author tyronechiao
* @description 针对表【t_article(文章表)】的数据库操作Service实现
* @createDate 2025-06-24 14:41:21
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleConverter articleConverter;

    @Override
    @Transactional
    public ArticleVO createArticle(ArticleDTO articleDTO) throws BizException {
        try {
            Article article = articleConverter.articleDTOToArticle(articleDTO);
            this.save(article);
            return articleConverter.articleToArticleVO(article);
        } catch (Exception e) {
            throw new BizException(CodeEnum.ARTICLE_OPERATION_ERROR, "创建文章失败: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public ArticleVO updateArticle(ArticleDTO articleDTO) throws BizException {
        try {
            Article article = articleConverter.articleDTOToArticle(articleDTO);
            this.updateById(article);
            return articleConverter.articleToArticleVO(article);
        } catch (Exception e) {
            throw new BizException(CodeEnum.ARTICLE_OPERATION_ERROR, "更新文章失败: " + e.getMessage());
        }
    }

    @Override
    public ArticleVO getArticleById(Long id) throws BizException {
        Article article = this.getById(id);
        if (article == null || article.getIsDeleted() == 1) {
            throw new BizException(CodeEnum.ARTICLE_NOT_EXIST, "文章不存在或已被删除");
        }
        return articleConverter.articleToArticleVO(article);
    }

    @Override
    public Pagination<ArticleVO> listArticlesByPage(int currentPage, int pageSize) throws BizException {
        try {
            Page<Article> page = new Page<>(currentPage, pageSize);
            LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Article::getIsDeleted, 0)
                    .orderByDesc(Article::getIsTop)
                    .orderByDesc(Article::getCreateTime);

            this.page(page, queryWrapper);
            return new Pagination<>(page.convert(articleConverter::articleToArticleVO));
        } catch (Exception e) {
            throw new BizException(CodeEnum.ARTICLE_OPERATION_ERROR, "查询文章列表失败: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteArticle(Long id) throws BizException {
        try {
            Article article = new Article();
            article.setId(id);
            article.setIsDeleted(1);
            this.updateById(article);
        } catch (Exception e) {
            throw new BizException(CodeEnum.ARTICLE_OPERATION_ERROR, "删除文章失败: " + e.getMessage());
        }
    }
}
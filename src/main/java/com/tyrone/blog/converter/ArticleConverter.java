package com.tyrone.blog.converter;

import com.tyrone.blog.domain.dto.ArticleDTO;
import com.tyrone.blog.domain.pojo.Article;
import com.tyrone.blog.domain.vo.ArticleVO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tyronechiao
 * @createTime on 2025/6/24
 */
@Component
public class ArticleConverter {
    public static final ArticleConverter INSTANCE = new ArticleConverter();

    private ArticleConverter() {}

    public ArticleDTO articleToArticleDTO(Article article) {
        if (article == null) {
            return null;
        }
        ArticleDTO dto = new ArticleDTO();
        dto.setId(article.getId());
        dto.setArticleTitle(article.getArticleTitle());
        dto.setArticleContent(article.getArticleContent());
        dto.setArticleCover(article.getArticleCover());
        dto.setArticleDesc(article.getArticleDesc());
        dto.setArticleType(article.getArticleType());
        dto.setWordCount(article.getWordCount());
        dto.setIsTop(article.getIsTop());
        dto.setStatus(article.getStatus());
        return dto;
    }

    public Article articleDTOToArticle(ArticleDTO articleDTO) {
        if (articleDTO == null) {
            return null;
        }
        Article article = new Article();
        article.setId(articleDTO.getId());
        article.setArticleTitle(articleDTO.getArticleTitle());
        article.setArticleContent(articleDTO.getArticleContent());
        article.setArticleCover(articleDTO.getArticleCover());
        article.setArticleDesc(articleDTO.getArticleDesc());
        article.setArticleType(articleDTO.getArticleType());
        article.setWordCount(articleDTO.getWordCount());
        article.setIsTop(articleDTO.getIsTop());
        article.setStatus(articleDTO.getStatus());
        return article;
    }

    public ArticleVO articleToArticleVO(Article article) {
        if (article == null) {
            return null;
        }
        ArticleVO vo = new ArticleVO();
        vo.setId(article.getId());
        vo.setArticleTitle(article.getArticleTitle());
        vo.setArticleContent(article.getArticleContent());
        vo.setArticleCover(article.getArticleCover());
        vo.setArticleDesc(article.getArticleDesc());
        vo.setArticleType(article.getArticleType());
        vo.setWordCount(article.getWordCount());
        vo.setIsTop(article.getIsTop());
        vo.setStatus(article.getStatus());
        vo.setCreateTime(article.getCreateTime());
        vo.setUpdateTime(article.getUpdateTime());
        return vo;
    }

    public List<ArticleVO> articlesToArticleVOs(List<Article> articles) {
        if (articles == null) {
            return null;
        }
        return articles.stream()
                .map(this::articleToArticleVO)
                .collect(Collectors.toList());
    }
}
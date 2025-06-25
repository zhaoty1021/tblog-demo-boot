package com.tyrone.blog.service;

import com.tyrone.blog.domain.dto.ArticleDTO;
import com.tyrone.blog.domain.pojo.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tyrone.blog.domain.response.Pagination;
import com.tyrone.blog.domain.vo.ArticleVO;
import com.tyrone.blog.exceptions.BizException;

/**
 * 文章服务接口
 *
 * @author tyronechiao
 * @description 针对表【t_article(文章表)】的数据库操作Service
 * @createDate 2025-06-24 14:41:21
 */
public interface ArticleService extends IService<Article> {

    /**
     * 创建文章
     *
     * @param articleDTO 文章数据传输对象，包含文章标题、内容、封面等信息
     * @return ArticleVO 文章视图对象，包含创建后的文章信息
     * @throws BizException 当创建文章失败时抛出业务异常
     */
    ArticleVO createArticle(ArticleDTO articleDTO) throws BizException;

    /**
     * 更新文章
     *
     * @param articleDTO 文章数据传输对象，包含需要更新的文章信息
     * @return ArticleVO 文章视图对象，包含更新后的文章信息
     * @throws BizException 当更新文章失败或文章不存在时抛出业务异常
     */
    ArticleVO updateArticle(ArticleDTO articleDTO) throws BizException;

    /**
     * 根据ID获取文章详情
     *
     * @param id 文章ID
     * @return ArticleVO 文章视图对象，包含文章详细信息
     * @throws BizException 当文章不存在或已被删除时抛出业务异常
     */
    ArticleVO getArticleById(Long id) throws BizException;

    /**
     * 分页查询文章列表
     *
     * @param currentPage 当前页码，从1开始
     * @param pageSize 每页记录数
     * @return Pagination<ArticleVO> 分页结果对象，包含文章列表和分页信息
     * @throws BizException 当查询失败时抛出业务异常
     */
    Pagination<ArticleVO> listArticlesByPage(int currentPage, int pageSize) throws BizException;

    /**
     * 删除文章（逻辑删除）
     *
     * @param id 文章ID
     * @throws BizException 当删除失败或文章不存在时抛出业务异常
     */
    void deleteArticle(Long id) throws BizException;
}
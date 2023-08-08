package com.myblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myblog.entity.Article;
import com.myblog.mapper.ArticleMapper;
import com.myblog.model.dto.ArticleCardDTO;
import com.myblog.model.dto.TopAndFeaturedArticlesDTO;
import com.myblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public TopAndFeaturedArticlesDTO listTopAndFeaturedArticles() {
        List<ArticleCardDTO> articleCardDTOS = articleMapper.listTopAndFeaturedArticles();
        TopAndFeaturedArticlesDTO topAndFeaturedArticlesDTO = new TopAndFeaturedArticlesDTO();
        ArticleCardDTO topArticle = articleCardDTOS.stream()
                .filter(articleCardDTO -> articleCardDTO.getIsTop().equals(1))
                .findFirst()
                .orElse(null);
        topAndFeaturedArticlesDTO.setArticleCardDTO(topArticle);
        List<ArticleCardDTO> featuredArticles = articleCardDTOS.stream()
                .filter(articleCardDTO ->
                        articleCardDTO.getIsFeatured().equals(1) && topArticle != null && topArticle.getId() != articleCardDTO.getId())
                .collect(Collectors.toList());
        if (featuredArticles.size() > 2) {
            featuredArticles = featuredArticles.subList(0, 2);
        }
        topAndFeaturedArticlesDTO.setArticleCardDTOList(featuredArticles);
        return topAndFeaturedArticlesDTO;
    }
}

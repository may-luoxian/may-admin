package com.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myblog.entity.Article;
import com.myblog.model.dto.TopAndFeaturedArticlesDTO;

public interface ArticleService extends IService<Article> {
    TopAndFeaturedArticlesDTO listTopAndFeaturedArticles();
}

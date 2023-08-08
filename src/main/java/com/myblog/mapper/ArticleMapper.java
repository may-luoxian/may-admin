package com.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myblog.entity.Article;
import com.myblog.model.dto.ArticleCardDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper extends BaseMapper<Article> {
    List<ArticleCardDTO> listTopAndFeaturedArticles();
}

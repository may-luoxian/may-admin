package com.myblog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//置顶和推荐返回数据
public class TopAndFeaturedArticlesDTO {
    private ArticleCardDTO articleCardDTO;
    private List<ArticleCardDTO> articleCardDTOList;
}

package com.myblog.model.vo.oj;

import lombok.Data;

@Data
public class OjQuestionSubmitVO {
    private Integer questionId;

    private Integer userId;

    private String language;

    private String code;
}

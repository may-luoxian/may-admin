package com.myblog.model.dto.oj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OjQuestionSubmitDTO {

    private Integer questionId;

    private Integer userId;

    private String language;

    private String code;

    private String judgeInfo;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;
}

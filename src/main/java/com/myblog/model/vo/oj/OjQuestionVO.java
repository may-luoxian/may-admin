package com.myblog.model.vo.oj;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "题目")
public class OjQuestionVO {
    @ApiModelProperty(name = "id", value = "题目id", dataType = "Integer")
    private Integer id;

    @ApiModelProperty(name = "title", value = "题目标题", dataType = "String")
    private String title;

    @ApiModelProperty(name = "content", value = "题目内容", dataType = "String")
    private String content;

    @ApiModelProperty(name = "tags", value = "标签", dataType = "List<String>")
    private List<String> tags;

    @ApiModelProperty(name = "answer", value = "标准答案", dataType = "String")
    private String answer;

    @ApiModelProperty(name = "judgeConfig", value = "判题配置", dataType = "String")
    private String judgeConfig;

    @ApiModelProperty(name = "judgeCase", value = "判题用例", dataType = "String")
    private String judgeCase;

}

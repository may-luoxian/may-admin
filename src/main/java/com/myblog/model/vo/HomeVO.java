package com.myblog.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class HomeVO {
    @ApiModelProperty(name = "id", value = "门户块id", dataType = "Integer")
    private Integer id;

    @ApiModelProperty(name = "userId", value = "修改门户的用户Id", dataType = "Integer")
    private Integer userId;

    @ApiModelProperty(name = "name", value = "门户块名称", dataType = "String")
    private String name;

    @ApiModelProperty(name = "widthValue", value = "门户块宽度", dataType = "Integer")
    private Integer widthValue;

    @ApiModelProperty(name = "component", value = "门户块组件名称", dataType = "String")
    private String component;

    @ApiModelProperty(name = "description", value = "门户块描述", dataType = "String")
    private String description;

    @ApiModelProperty(name = "previewImg", value = "预览图片", dataType = "String")
    private String previewImg;

    @ApiModelProperty(name = "controlStatus", value = "操作状态", dataType = "Integer")
    private Integer controlStatus;
}

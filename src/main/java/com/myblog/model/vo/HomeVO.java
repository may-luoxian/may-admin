package com.myblog.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class HomeVO {
    @ApiModelProperty(name = "id", value = "门户块id", dataType = "Integer")
    private Integer id;

    @ApiModelProperty(name = "name", value = "门户块名称", dataType = "String")
    private String name;

    @ApiModelProperty(name = "id", value = "门户块宽度", dataType = "Integer")
    private Integer widthValue;

    @ApiModelProperty(name = "id", value = "门户块组件名称", dataType = "String")
    private String component;

    @ApiModelProperty(name = "id", value = "门户块描述", dataType = "String")
    private String description;

    @ApiModelProperty(name = "id", value = "是否启用", dataType = "Integer")
    private Integer isEnable;
}

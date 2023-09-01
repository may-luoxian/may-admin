package com.myblog.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResourceVO {
    @ApiModelProperty(name = "id", value = "资源id", dataType = "Integer")
    private Integer id;

    @ApiModelProperty(name = "resourceName", value = "资源名称", dataType = "String")
    private String resourceName;

    @ApiModelProperty(name = "url", value = "请求路径", dataType = "String")
    private String url;

    @ApiModelProperty(name = "requestMethod", value = "请求类型", dataType = "String")
    private String requestMethod;

    @ApiModelProperty(name = "parentId", value = "父id", dataType = "Integer")
    private Integer parentId;

    @ApiModelProperty(name = "isAnonymous", value = "是否匿名", dataType = "Integer")
    private Integer isAnonymous;

    @ApiModelProperty(name = "current", value = "页号", dataType = "Integer")
    private Long current;

    @ApiModelProperty(name = "size", value = "页数", dataType = "Integer")
    private Long size;
}

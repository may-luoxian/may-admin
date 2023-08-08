package com.myblog.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "菜单")
public class MenuVO {
    @ApiModelProperty(name = "id", value = "菜单id", dataType = "Integer")
    private Integer id;

    @ApiModelProperty(name = "name", value = "菜单名称", dataType = "String")
    @NotBlank(message = "菜单名称不能为空")
    private String name;

    @ApiModelProperty(name = "path", value = "菜单路径", dataType = "String")
    @NotBlank(message = "菜单路径不能为空")
    private String path;

    @ApiModelProperty(name = "component", value = "组件路径", dataType = "String")
    @NotBlank(message = "组件路径不能为空")
    private String component;

    @ApiModelProperty(name = "icon", value = "菜单图标", dataType = "String")
    @NotBlank(message = "菜单图标不能为空")
    private String icon;

    @ApiModelProperty(name = "menuType", value = "菜单类型 0 目录 1菜单", dataType = "Integer")
    @NotNull(message = "菜单类型不能为空")
    private Integer menuType;

    @ApiModelProperty(name = "orderNum", value = "排序", dataType = "Integer")
    @NotNull(message = "排序不能为空")
    private Integer orderNum;

    @ApiModelProperty(name = "parentId", value = "父目录id", dataType = "Integer")
    private Integer parentId;

    @ApiModelProperty(name = "isHidden", value = "是否隐藏 0显示 1隐藏 默认显示", dataType = "Integer")
    private Integer isHidden;
}

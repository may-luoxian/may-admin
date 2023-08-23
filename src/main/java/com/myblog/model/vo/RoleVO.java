package com.myblog.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "角色")
public class RoleVO {
    @ApiModelProperty(name = "id", value = "角色id", dataType = "Integer")
    private Integer id;

    @ApiModelProperty(name = "roleName", value = "角色名称", dataType = "String")
    private String roleName;

    @ApiModelProperty(name = "roleMenuIds", value = "角色菜单ids", dataType = "List<Integer>")
    private List<Integer> roleMenuIds;

    @ApiModelProperty(name = "roleResourceIds", value = "角色资源ids", dataType = "List<Integer>")
    private List<Integer> roleResourceIds;
}

package com.myblog.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "角色")
public class RoleVO {
    @ApiModelProperty(name = "id", value = "角色id", dataType = "Integer")
    private Integer id;

    @NotBlank(message = "角色名不能为空")
    @ApiModelProperty(name = "roleName", value = "角色名称", dataType = "String")
    private String roleName;

    @NotNull(message = "是否禁用不能为空")
    @ApiModelProperty(name = "isDisable", value = "是否禁用", dataType = "Integer")
    private Integer isDisable;

    @ApiModelProperty(name = "describe", value = "角色描述", dataType = "String")
    private String describe;

    @ApiModelProperty(name = "roleMenuIds", value = "角色菜单ids", dataType = "List<Integer>")
    private List<Integer> roleMenuIds;

    @ApiModelProperty(name = "roleResourceIds", value = "角色资源ids", dataType = "List<Integer>")
    private List<Integer> roleResourceIds;
}

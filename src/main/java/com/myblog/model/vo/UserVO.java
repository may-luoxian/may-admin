package com.myblog.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "用户")
public class UserVO {
    @ApiModelProperty(name = "id", value = "用户id", dataType = "Integer")
    private Integer id;

    @ApiModelProperty(name = "nickname", value = "用户昵称", dataType = "String")
    @NotBlank(message = "用户昵称不能为空")
    private String nickname;

    @ApiModelProperty(name = "avatar", value = "用户头像", dataType = "MultipartFile")
    private MultipartFile avatar;

    @ApiModelProperty(name = "isDisable", value = "是否禁用", dataType = "Integer")
    private Integer isDisable;

    @ApiModelProperty(name = "roleIds", value = "用户拥有角色", dataType = "List<Integer>")
    private List<Integer> roleIds;
}

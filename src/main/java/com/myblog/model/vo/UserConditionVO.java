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
public class UserConditionVO {
    @ApiModelProperty(name = "current", value = "页码", dataType = "Long")
    private Long current;

    @ApiModelProperty(name = "size", value = "条数", dataType = "Long")
    private Long size;

    @ApiModelProperty(name = "username", value = "用户名", dataType = "String")
    private String username;

    @ApiModelProperty(name = "nickname", value = "昵称", dataType = "String")
    private String nickname;

    @ApiModelProperty(name = "loginType", value = "登录方式", dataType = "Integer")
    private Integer loginType;
}

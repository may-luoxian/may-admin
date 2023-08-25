package com.myblog.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAdminDTO {

    private Integer id;

    private Integer userInfoId;

    private String avatar;

    private String nickname;

    private List<UserRoleDTO> roles;

    private Integer loginType;

    private String ipAddress;

    private String ipSource;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastLoginTime;

    private Integer isDisable;

    private Integer status;

}

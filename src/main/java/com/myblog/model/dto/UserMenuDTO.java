package com.myblog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserMenuDTO {
    private String path;

    private String name;

    private Integer id;

    private Integer menuType;

    private String component;

    private String icon;

    private Integer isHidden;

    private Integer parentId;

    private Integer orderNum;

    private List<UserMenuDTO> children;
}

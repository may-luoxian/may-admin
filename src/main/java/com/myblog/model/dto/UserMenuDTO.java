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
    private Integer id;

    private String name;

    private Number menuType;

    private String path;

    private String component;

    private String icon;

    private Boolean hidden;

    private Integer parentId;

    private List<UserMenuDTO> children;
}

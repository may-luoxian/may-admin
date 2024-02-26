package com.myblog.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class HomeEnableVO {
    private Integer userInfoId;
    private Integer roleId;
    private Integer widthValue;
    private String enableType;
    private List<HomeOrderVO> enableData;
}

package com.myblog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeDTO {
    private Integer id;


    private String name;


    private Integer widthValue;


    private String component;


    private String description;

    private String previewImg;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}

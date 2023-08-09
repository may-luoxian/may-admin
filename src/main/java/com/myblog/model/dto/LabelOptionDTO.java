package com.myblog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LabelOptionDTO {

    private Integer id;

    private String label;

    private String icon;

    private Integer orderNum;

    private List<LabelOptionDTO> children;
}

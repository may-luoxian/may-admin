package com.myblog.model.dto.oj;

import lombok.Data;

@Data
public class JudgeConfigDTO {
    private Long timeLimit;
    private Long stackLimit;
    private Long memoryLimit;
}

package com.myblog.judge.strategy;

import com.myblog.entity.OjQuestion;
import com.myblog.entity.OjQuestionSubmit;
import com.myblog.model.dto.oj.JudgeCaseDTO;
import com.myblog.model.dto.oj.JudgeInfoDTO;
import lombok.Data;

import java.util.List;

/**
 * 策略上下文（用于定义在策略中传递的参数）
 */
@Data
public class JudgeContext {
    private JudgeInfoDTO judgeInfoDTO;

    private List<String> inputList;

    private List<String> outputList;

    private List<JudgeCaseDTO> judgeCaseDTOList;

    private OjQuestion ojQuestion;

    private OjQuestionSubmit ojQuestionSubmit;
}

package com.myblog.judge.strategy.impl;

import cn.hutool.json.JSONUtil;
import com.myblog.entity.OjQuestion;
import com.myblog.enums.JudgeInfoMsgEnum;
import com.myblog.judge.strategy.JudgeContext;
import com.myblog.judge.strategy.JudgeStrategy;
import com.myblog.model.dto.oj.JudgeCaseDTO;
import com.myblog.model.dto.oj.JudgeConfigDTO;
import com.myblog.judge.codesandbox.model.JudgeInfo;

import java.util.List;

/**
 * 默认判题策略
 */
public class DefaultJudgeStrategy implements JudgeStrategy {
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        List<String> inputList = judgeContext.getInputList();
        List<String> outputList = judgeContext.getOutputList();
        OjQuestion ojQuestion = judgeContext.getOjQuestion();
        List<JudgeCaseDTO> judgeCaseDTOList = judgeContext.getJudgeCaseDTOList();
        Long realMemory = judgeInfo.getMemory();
        Long realTime = judgeInfo.getTime();
        JudgeInfo judgeInfoResponse = new JudgeInfo();
        judgeInfoResponse.setMemory(realMemory);
        judgeInfoResponse.setTime(realTime);
        JudgeInfoMsgEnum judgeInfoMsgEnum = JudgeInfoMsgEnum.ACCEPTED;
        if (outputList.size() != inputList.size()) {
            judgeInfoMsgEnum = JudgeInfoMsgEnum.WRONG_ANSWER;
            judgeInfoResponse.setMessage(judgeInfoMsgEnum.getValue());
            return judgeInfoResponse;
        }
        for (int i = 0; i < judgeCaseDTOList.size(); i++) {
            JudgeCaseDTO judgeCaseDTO = judgeCaseDTOList.get(i);
            if (judgeCaseDTO.getOutput().equals(outputList.get(i))) {
                judgeInfoMsgEnum = JudgeInfoMsgEnum.WRONG_ANSWER;
                judgeInfoResponse.setMessage(judgeInfoMsgEnum.getValue());
                return judgeInfoResponse;
            }
        }
        String judgeConfig = ojQuestion.getJudgeConfig();
        JudgeConfigDTO judgeConfigDTO = JSONUtil.toBean(judgeConfig, JudgeConfigDTO.class);
        Long memoryLimit = judgeConfigDTO.getMemoryLimit();
        Long timeLimit = judgeConfigDTO.getTimeLimit();
        if (realMemory > memoryLimit) {
            judgeInfoMsgEnum = JudgeInfoMsgEnum.MEMORY_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMsgEnum.getValue());
            return judgeInfoResponse;
        }
        if (realTime > timeLimit) {
            judgeInfoMsgEnum = JudgeInfoMsgEnum.TIME_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMsgEnum.getValue());
            return judgeInfoResponse;
        }

        judgeInfoResponse.setMessage(judgeInfoMsgEnum.getValue());
        return judgeInfoResponse;
    }
}

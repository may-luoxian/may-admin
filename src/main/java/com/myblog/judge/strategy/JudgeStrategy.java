package com.myblog.judge.strategy;

import com.myblog.model.dto.oj.JudgeInfoDTO;

/**
 * 判题策略
 */
public interface JudgeStrategy {
    /**
     * 执行判题
     * @param judgeContext
     * @return
     */
    JudgeInfoDTO doJudge(JudgeContext judgeContext);
}

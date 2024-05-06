package com.myblog.judge;

import com.myblog.entity.OjQuestionSubmit;

/**
 * 判题服务
 */
public interface JudgeService {
    /**
     * 判题
     * @param questionSubmitId
     * @return
     */
    OjQuestionSubmit doJudge(Integer questionSubmitId);
}

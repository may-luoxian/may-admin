package com.myblog.judge;

import com.myblog.entity.OjQuestionSubmit;
import com.myblog.enums.QuestionSubmitLanguageEnum;
import com.myblog.judge.strategy.JudgeContext;
import com.myblog.judge.strategy.JudgeStrategy;
import com.myblog.judge.strategy.impl.DefaultJudgeStrategy;
import com.myblog.model.dto.oj.JudgeInfoDTO;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 */
@Service
public class JudgeManager {
    public JudgeInfoDTO doJudge(JudgeContext judgeContext) {
        OjQuestionSubmit ojQuestionSubmit = judgeContext.getOjQuestionSubmit();
        String language = ojQuestionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if (QuestionSubmitLanguageEnum.JAVA.getValue().equals(language)) {
            judgeStrategy = new DefaultJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}

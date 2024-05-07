package com.myblog.judge;

import cn.hutool.json.JSONUtil;
import com.myblog.entity.OjQuestion;
import com.myblog.entity.OjQuestionSubmit;
import com.myblog.enums.QuestionSubStateEnum;
import com.myblog.exception.BizException;
import com.myblog.judge.codesandbox.CodeSandBox;
import com.myblog.judge.codesandbox.CodeSandBoxFactory;
import com.myblog.judge.codesandbox.model.ExecuteCodeRequest;
import com.myblog.judge.codesandbox.model.ExecuteCodeResponse;
import com.myblog.judge.strategy.JudgeContext;
import com.myblog.model.dto.oj.JudgeCaseDTO;
import com.myblog.judge.codesandbox.model.JudgeInfo;
import com.myblog.service.OjQuestionService;
import com.myblog.service.OjQuestionSubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JudgeServiceImpl implements JudgeService {

    @Value("${codeSandBox.type:example}")
    private String type;

    @Autowired
    private OjQuestionService ojQuestionService;

    @Autowired
    private OjQuestionSubmitService ojQuestionSubmitService;

    @Autowired
    private JudgeManager judgeManager;

    @Override
    public OjQuestionSubmit doJudge(Integer questionSubmitId) {
        // 1.获取题目提交，题目信息
        OjQuestionSubmit ojQuestionSubmit = ojQuestionSubmitService.getById(questionSubmitId);
        if (ojQuestionSubmit == null) {
            throw new BizException("题目提交信息不存在");
        }
        Integer questionId = ojQuestionSubmit.getQuestionId();
        OjQuestion ojQuestion = ojQuestionService.getById(questionId);
        if (ojQuestion == null) {
            throw new BizException("题目信息不存在");
        }
        // 2.如果题目提交状态不为等待中，不再重复执行了。
        if (!ojQuestionSubmit.getStatus().equals(QuestionSubStateEnum.UNTREATED.getValue())) {
            throw new BizException("题目正在判题中");
        }
        // 3.更改题目提交状态为“判题中”
        OjQuestionSubmit questionSubmit = new OjQuestionSubmit();
        questionSubmit.setId(questionSubmitId);
        questionSubmit.setStatus(QuestionSubStateEnum.IN_PROCESS.getValue());
        boolean update = ojQuestionSubmitService.updateById(questionSubmit);
        if (!update) {
            throw new BizException("题目状态更新错误");
        }
        // 4.调用代码沙箱
        List<JudgeCaseDTO> judgeCaseDTOS = JSONUtil.toList(ojQuestion.getJudgeCase(), JudgeCaseDTO.class);
        List<String> inputList = judgeCaseDTOS.stream().map(JudgeCaseDTO::getInput).collect(Collectors.toList());
        CodeSandBox codeSandBox = CodeSandBoxFactory.newInstance(type);
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .language(ojQuestionSubmit.getLanguage())
                .code(ojQuestionSubmit.getCode())
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);
        /**
         * 5. 根据沙箱的执行结果，设置题目的判题状态和信息
         * 1）判断沙箱执行的结果输出数量是否和预期输出数量相等
         * 2）依次判断每一项输出和预期输出相等
         * 3）判题题目的限制是否符合要求
         * 4）其他错误处理
         */
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        judgeContext.setJudgeCaseDTOList(judgeCaseDTOS);
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(executeCodeResponse.getOutputList());
        judgeContext.setOjQuestion(ojQuestion);
        judgeContext.setOjQuestionSubmit(ojQuestionSubmit);

        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);
        // 6.更改题目提交状态为“成功”
        questionSubmit = new OjQuestionSubmit();
        questionSubmit.setId(questionSubmitId);
        questionSubmit.setStatus(QuestionSubStateEnum.SUCCESS.getValue());
        questionSubmit.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        update = ojQuestionSubmitService.updateById(questionSubmit);
        if (!update) {
            throw new BizException("题目状态更新错误");
        }
        OjQuestionSubmit ojQuestionResult = ojQuestionSubmitService.getById(questionId);
        return ojQuestionResult;
    }
}

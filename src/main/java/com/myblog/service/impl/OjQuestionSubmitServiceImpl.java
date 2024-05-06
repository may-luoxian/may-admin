package com.myblog.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myblog.entity.OjQuestion;
import com.myblog.entity.OjQuestionSubmit;
import com.myblog.enums.QuestionSubStateEnum;
import com.myblog.exception.BizException;
import com.myblog.judge.JudgeService;
import com.myblog.model.dto.oj.JudgeInfoDTO;
import com.myblog.model.vo.oj.OjQuestionSubmitVO;
import com.myblog.service.OjQuestionService;
import com.myblog.service.OjQuestionSubmitService;
import com.myblog.mapper.OjQuestionSubmitMapper;
import com.myblog.util.BeanCopyUtil;
import com.myblog.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @author sunyukun
 * @description 针对表【oj_question_submit】的数据库操作Service实现
 * @createDate 2024-01-28 06:19:53
 */
@Service
public class OjQuestionSubmitServiceImpl extends ServiceImpl<OjQuestionSubmitMapper, OjQuestionSubmit>
        implements OjQuestionSubmitService {

    @Autowired
    private OjQuestionService questionService;

    @Autowired
    private JudgeService judgeService;

    @Override
    public Integer questionSubmit(OjQuestionSubmitVO questionSubmitVO) {
        OjQuestion question = questionService.getById(questionSubmitVO.getQuestionId());
        if (question == null) {
            throw new BizException("题目不存在");
        }
        // 题目提交初始化操作
        Integer userInfoId = UserUtil.getUserDetailsDTO().getUserInfoId();
        OjQuestionSubmit questionSubmit = BeanCopyUtil.copyObject(questionSubmitVO, OjQuestionSubmit.class);
        questionSubmit.setUserInfoId(userInfoId);
        questionSubmit.setStatus(QuestionSubStateEnum.UNTREATED.getValue());
        JudgeInfoDTO judgeInfoDTO = new JudgeInfoDTO();
        questionSubmit.setJudgeInfo(JSONUtil.toJsonStr(judgeInfoDTO));
        boolean save = this.save(questionSubmit);
        if (!save) {
            throw new BizException("题目提交失败");
        }
        // TODO: 执行判题服务
        Integer questionSubmitId = questionSubmit.getId();
        CompletableFuture.runAsync(() -> {
            judgeService.doJudge(questionSubmitId);
        });
        return questionSubmitId;
    }
}

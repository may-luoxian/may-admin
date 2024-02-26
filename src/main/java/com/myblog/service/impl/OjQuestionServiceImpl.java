package com.myblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myblog.entity.OjQuestion;
import com.myblog.model.dto.oj.JudgeCaseDTO;
import com.myblog.model.dto.oj.JudgeConfigDTO;
import com.myblog.model.dto.oj.OjQuestionDTO;
import com.myblog.service.OjQuestionService;
import com.myblog.mapper.OjQuestionMapper;
import com.myblog.util.BeanCopyUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * @author sunyukun
 * @description 针对表【oj_question(oj模块-题目)】的数据库操作Service实现
 * @createDate 2024-01-28 06:17:29
 */
@Service
public class OjQuestionServiceImpl extends ServiceImpl<OjQuestionMapper, OjQuestion> implements OjQuestionService {

    @Override
    public void validateQuestion(OjQuestion ojQuestion, boolean add) {

    }

    @Override
    public OjQuestion copyQuestion(OjQuestionDTO ojQuestionDTO) {
        OjQuestion ojQuestion = BeanCopyUtil.copyObject(ojQuestionDTO, OjQuestion.class);
        List<String> tags = ojQuestionDTO.getTags();
        if (Objects.nonNull(tags)) {
            ojQuestion.setTags(toJSONString(tags));
        }
        List<JudgeCaseDTO> judgeCases = ojQuestionDTO.getJudgeCases();
        if (Objects.nonNull(judgeCases)) {
            ojQuestion.setJudgeCase(toJSONString(judgeCases));
        }
        JudgeConfigDTO judgeConfig = ojQuestionDTO.getJudgeConfig();
        if (Objects.nonNull(judgeConfig)) {
            ojQuestion.setJudgeConfig(toJSONString(judgeConfig));
        }
        return ojQuestion;
    }
}





package com.myblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myblog.entity.OjQuestion;
import com.myblog.service.OjQuestionService;
import com.myblog.mapper.OjQuestionMapper;
import org.springframework.stereotype.Service;

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
}





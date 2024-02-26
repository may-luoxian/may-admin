package com.myblog.service;

import com.myblog.entity.OjQuestion;
import com.baomidou.mybatisplus.extension.service.IService;
import com.myblog.model.dto.oj.OjQuestionDTO;

/**
* @author sunyukun
* @description 针对表【oj_question(oj模块-题目)】的数据库操作Service
* @createDate 2024-01-28 06:17:29
*/
public interface OjQuestionService extends IService<OjQuestion> {
    void validateQuestion(OjQuestion ojQuestion, boolean add);

    OjQuestion copyQuestion(OjQuestionDTO ojQuestionDTO);
}

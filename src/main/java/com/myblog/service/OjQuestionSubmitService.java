package com.myblog.service;

import com.myblog.entity.OjQuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.myblog.model.vo.oj.OjQuestionSubmitVO;

/**
* @author sunyukun
* @description 针对表【oj_question_submit】的数据库操作Service
* @createDate 2024-01-28 06:19:53
*/
public interface OjQuestionSubmitService extends IService<OjQuestionSubmit> {
    Integer questionSubmit(OjQuestionSubmitVO questionSubmitVO);
}

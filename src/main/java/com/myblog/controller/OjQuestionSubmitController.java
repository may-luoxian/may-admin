package com.myblog.controller;

import com.myblog.enums.StatusCodeEnum;
import com.myblog.exception.BizException;
import com.myblog.model.vo.ResultVO;
import com.myblog.model.vo.oj.OjQuestionSubmitVO;
import com.myblog.service.OjQuestionSubmitService;
import com.myblog.util.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "oj模块-题目提交")
@RestController
@RequestMapping("/oj/questionSubmit")
@Slf4j
public class OjQuestionSubmitController {
    @Autowired
    private OjQuestionSubmitService ojQuestionSubmitService;

    @ApiOperation("提交题目")
    @PostMapping("/submit")
    public ResultVO<Integer> questionSubmit(@RequestBody OjQuestionSubmitVO questionSubmitVO) {
        if (questionSubmitVO == null || questionSubmitVO.getQuestionId() <= 0) {
            throw new BizException(StatusCodeEnum.VALID_ERROR.getDesc());
        }
        Integer userInfoId = UserUtil.getUserDetailsDTO().getUserInfoId();
        questionSubmitVO.setUserInfoId(userInfoId);
        Integer questionSubmitId = ojQuestionSubmitService.questionSubmit(questionSubmitVO);
        return ResultVO.ok(questionSubmitId);
    }

    @ApiOperation("运行题目")
    @PostMapping("/operation")
    public ResultVO<Integer> questionOperation(OjQuestionSubmitVO questionSubmitVO) {
        return ResultVO.ok();
    }
}

package com.myblog.controller;

import com.myblog.service.OjQuestionSubmitService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "oj模块-题目提交")
@RestController
@RequestMapping("/oj/questionSubmit")
@Slf4j
public class OjQuestionSubmitController {
    @Autowired
    private OjQuestionSubmitService ojQuestionSubmitService;
}

package com.myblog.controller;

import com.myblog.entity.OjQuestion;
import com.myblog.exception.BizException;
import com.myblog.model.dto.oj.OjQuestionDTO;
import com.myblog.model.vo.ResultVO;
import com.myblog.service.OjQuestionService;
import com.myblog.util.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "oj模块-题目")
@RestController
@RequestMapping("/oj/question")
@Slf4j
public class OjQuestionControl {
    @Autowired
    private OjQuestionService ojQuestionService;

    @ApiOperation("添加题目")
    @PostMapping("/")
    public ResultVO<Integer> addQuestion(@RequestBody OjQuestionDTO ojQuestionDTO) {
        OjQuestion ojQuestion = ojQuestionService.copyQuestion(ojQuestionDTO);
        // 验证题目是否符合规范
        ojQuestionService.validateQuestion(ojQuestion, true);

        Integer userId = UserUtil.getUserDetailsDTO().getId();
        ojQuestion.setUserId(userId);
        boolean save = ojQuestionService.save(ojQuestion);
        if (!save) {
            throw new BizException("题目添加失败");
        }
        Integer questionId = ojQuestion.getId();
        return ResultVO.ok(questionId);
    }

    @ApiOperation("修改题目")
    @PutMapping("/")
    public ResultVO<Integer> updateQuestion(@RequestBody OjQuestionDTO ojQuestionDTO) {
        OjQuestion ojQuestion = ojQuestionService.copyQuestion(ojQuestionDTO);
        // 验证题目是否符合规范
        ojQuestionService.validateQuestion(ojQuestion, false);

        Integer userId = UserUtil.getUserDetailsDTO().getId();
        ojQuestion.setUserId(userId);
        boolean update = ojQuestionService.updateById(ojQuestion);
        if (!update) {
            throw new BizException("题目修改失败");
        }
        Integer questionId = ojQuestion.getId();
        return ResultVO.ok(questionId);
    }

    @ApiOperation("删除题目")
    @DeleteMapping("/")
    public ResultVO<Boolean> deleteQuestion(@RequestBody List<Integer> ids) {
        boolean b = ojQuestionService.removeByIds(ids);
        return ResultVO.ok(b);
    }
}

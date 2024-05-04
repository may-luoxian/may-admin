package com.myblog.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @TableName oj_question_submit
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "oj_question_submit")
public class OjQuestionSubmit {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 题目id
     */
    private Integer questionId;

    /**
     * 创建用户id
     */
    private Integer userInfoId;

    /**
     * 编程语言
     */
    private String language;

    /**
     * 用户代码
     */
    private String code;

    /**
     * 判题信息（json）
     * [
     * {
     * message: xxx, 程序执行信息
     * time: xxx,  执行时间 ms
     * memory: xxx  占用内存 kb
     * }
     * ]
     */
    private String judgeInfo;

    /**
     * 判题状态 （0：待判题，1：判题中，2：成功，3：失败）
     */
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    private Integer isDelete;
}

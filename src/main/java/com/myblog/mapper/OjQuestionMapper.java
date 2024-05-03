package com.myblog.mapper;

import com.myblog.entity.OjQuestion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author sunyukun
* @description 针对表【oj_question(oj模块-题目)】的数据库操作Mapper
* @createDate 2024-01-28 06:17:29
* @Entity com.myblog.entity.OjQuestion
*/
@Mapper
public interface OjQuestionMapper extends BaseMapper<OjQuestion> {
    List<OjQuestion> selectQuestionList();

    OjQuestion selectQuestionById(@Param("id") Integer id);
}





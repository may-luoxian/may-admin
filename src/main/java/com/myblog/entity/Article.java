package com.myblog.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_article")
public class Article {
    private Integer id;
    private Integer userId;
    private Integer categoryId;
    private String articleCover;
    private String articleTitle;
    private String articleContent;
    private Integer isTop;
    private Integer isFeatured;
    private Integer isDelete;
    private Integer status;
    private Integer type;
    private String password;
    private String originalUrl;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}

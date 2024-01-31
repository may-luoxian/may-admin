package com.myblog.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_home")
public class Home {
    /**
     * 模块id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 模块名称
     */
    private String name;

    /**
     * 模块宽度
     * 1：1/4
     * 2：1/3
     * 3：1/2
     * 4：2/3
     * 5：100%
     */
    private Integer widthValue;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否启用
     */
    private Integer isEnable;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}

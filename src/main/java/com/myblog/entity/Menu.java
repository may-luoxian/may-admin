package com.myblog.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_menu")
public class Menu {
    //@TableId 数据库主键 value对应数据库字段，type对应主键模式 auto为自增
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer menuType;

    private String path;

    private String component;

    private String icon;

    private Integer orderNum;

    private Integer parentId;

    private Integer isHidden;

    //根据配置类MyMetaObjectHandler在插入时自动填充
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    //根据配置类MyMetaObjectHandler在更新时自动填充
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}

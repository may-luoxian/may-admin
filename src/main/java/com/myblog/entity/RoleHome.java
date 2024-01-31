package com.myblog.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_role")
public class RoleHome {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer homeId;

    private Integer roleId;

    private Integer orderNum;
}

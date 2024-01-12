package com.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myblog.entity.RoleMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    long batchDeleteByRoleids(@Param("ids") List<Integer> ids);
}

package com.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myblog.entity.RoleResource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleResourceMapper extends BaseMapper<RoleResource> {
    long batchDeleteByRoleids(@Param("ids") List<Integer> ids);

    long batchDeleteByResourceIds(@Param("ids") List<Integer> ids);
}

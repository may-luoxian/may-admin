package com.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myblog.entity.Resource;
import com.myblog.model.vo.ResourceVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceMapper extends BaseMapper<Resource> {
    List<Resource> selectResource(@Param("resourceVO") ResourceVO resourceVO);
}

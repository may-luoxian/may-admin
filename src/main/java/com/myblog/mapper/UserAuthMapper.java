package com.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myblog.entity.UserAuth;
import com.myblog.model.dto.UserAdminDTO;
import com.myblog.model.vo.UserConditionVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAuthMapper extends BaseMapper<UserAuth> {
    List<UserAdminDTO> listUsers(@Param("current") Long pageLimit, @Param("size") Long size, @Param("conditionVO") UserConditionVO conditionVO);

    Integer countUser(@Param("conditionVO") UserConditionVO conditionVO);
}

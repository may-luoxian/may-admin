package com.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myblog.entity.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    int insertUserInfo(UserInfo userInfo);
}

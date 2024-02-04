package com.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myblog.entity.UserHome;
import com.myblog.mapper.UserHomeMapper;
import com.myblog.service.UserHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserHomeServiceImpl extends ServiceImpl<UserHomeMapper, UserHome> implements UserHomeService {

    @Autowired
    private UserHomeMapper userHomeMapper;

    @Override
    public UserHome selectByUserId(Integer userInfoId) {
        LambdaQueryWrapper<UserHome> wrapper = new LambdaQueryWrapper<UserHome>().eq(UserHome::getUserInfoId, userInfoId);
        return userHomeMapper.selectOne(wrapper);
    }
}

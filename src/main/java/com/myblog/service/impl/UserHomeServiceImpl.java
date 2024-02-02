package com.myblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myblog.entity.UserHome;
import com.myblog.mapper.UserHomeMapper;
import com.myblog.service.UserHomeService;
import org.springframework.stereotype.Service;

@Service
public class UserHomeServiceImpl extends ServiceImpl<UserHomeMapper, UserHome> implements UserHomeService {
}

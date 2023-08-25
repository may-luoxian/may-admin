package com.myblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myblog.entity.UserRole;
import com.myblog.mapper.UserRoleMapper;
import com.myblog.service.UserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}

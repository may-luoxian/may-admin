package com.myblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myblog.entity.RoleResource;
import com.myblog.mapper.RoleResourceMapper;
import com.myblog.service.RoleResourceService;
import org.springframework.stereotype.Service;


@Service
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResource> implements RoleResourceService {
}

package com.myblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myblog.entity.UserAuth;
import com.myblog.mapper.UserAuthMapper;
import com.myblog.model.dto.UserAdminDTO;
import com.myblog.model.vo.ConditionVO;
import com.myblog.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth> implements UserAuthService {

    @Autowired
    private UserAuthMapper userAuthMapper;

    @Override
    public List<UserAdminDTO> listUsers(ConditionVO conditionVO) {
        return userAuthMapper.listUsers(conditionVO);
    }
}

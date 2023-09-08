package com.myblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myblog.entity.UserAuth;
import com.myblog.mapper.UserAuthMapper;
import com.myblog.model.dto.UserAdminDTO;
import com.myblog.model.vo.ConditionVO;
import com.myblog.service.RedisService;
import com.myblog.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.myblog.constant.RedisConstant.LOGIN_USER;

@Service
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth> implements UserAuthService {

    @Autowired
    private UserAuthMapper userAuthMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public List<UserAdminDTO> listUsers(ConditionVO conditionVO) {
        return userAuthMapper.listUsers(conditionVO);
    }

    @Override
    public void logout(Integer id) {
        redisService.hDel(LOGIN_USER, String.valueOf(id));
    }
}

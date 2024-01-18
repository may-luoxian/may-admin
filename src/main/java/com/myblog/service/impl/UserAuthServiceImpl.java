package com.myblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myblog.entity.UserAuth;
import com.myblog.enums.FilePathEnum;
import com.myblog.mapper.UserAuthMapper;
import com.myblog.model.dto.UserAdminDTO;
import com.myblog.model.dto.UserInfoDTO;
import com.myblog.model.vo.ConditionVO;
import com.myblog.service.RedisService;
import com.myblog.service.UserAuthService;
import com.myblog.strategy.context.UploadStrategyContext;
import com.myblog.util.BeanCopyUtil;
import com.myblog.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.myblog.constant.RedisConstant.LOGIN_USER;

@Service
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth> implements UserAuthService {

    @Autowired
    private UserAuthMapper userAuthMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    @Override
    public List<UserAdminDTO> listUsers(ConditionVO conditionVO) {
        return userAuthMapper.listUsers(conditionVO);
    }

    @Override
    public UserInfoDTO userInfo() {
        UserInfoDTO userInfoDTO = BeanCopyUtil.copyObject(UserUtil.getUserDetailsDTO(), UserInfoDTO.class);
        return userInfoDTO;
    }

    @Override
    public void logout(Integer id) {
        redisService.hDel(LOGIN_USER, String.valueOf(id));
    }

    @Override
    public String uploadAvatar(MultipartFile file) {
        String path = uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.AVATAR.getPath());
        return path;
    }
}

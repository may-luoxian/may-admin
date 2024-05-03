package com.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myblog.entity.UserAuth;
import com.myblog.entity.UserHome;
import com.myblog.entity.UserInfo;
import com.myblog.entity.UserRole;
import com.myblog.enums.FilePathEnum;
import com.myblog.exception.BizException;
import com.myblog.mapper.UserAuthMapper;
import com.myblog.mapper.UserHomeMapper;
import com.myblog.mapper.UserInfoMapper;
import com.myblog.mapper.UserRoleMapper;
import com.myblog.model.dto.PageResultDTO;
import com.myblog.model.dto.UserAdminDTO;
import com.myblog.model.dto.UserDetailsDTO;
import com.myblog.model.dto.UserInfoDTO;
import com.myblog.model.vo.UserConditionVO;
import com.myblog.service.RedisService;
import com.myblog.service.UserAuthService;
import com.myblog.strategy.context.UploadStrategyContext;
import com.myblog.util.BeanCopyUtil;
import com.myblog.util.PageUtil;
import com.myblog.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.myblog.constant.RedisConstant.LOGIN_USER;

@Service
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth> implements UserAuthService {

    @Autowired
    private UserAuthMapper userAuthMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserHomeMapper userHomeMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    @Override
    public PageResultDTO<UserAdminDTO> listUsers(UserConditionVO conditionVO) {
        List<UserAdminDTO> userAdminDTOS = userAuthMapper.listUsers(PageUtil.getLimitCurrent(), PageUtil.getSize(), conditionVO);
        Integer count = userAuthMapper.countUser(conditionVO);
        return new PageResultDTO<>(userAdminDTOS, count);
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createUser(UserDetailsDTO userDetailsDTO) {
        if (checkUser(userDetailsDTO)) {
            throw new BizException("该账号已被注册");
        }
        UserInfo userInfo = UserInfo.builder()
                .email(userDetailsDTO.getEmail())
                .nickname(userDetailsDTO.getNickname())
                .avatar(userDetailsDTO.getAvatar())
                .intro(userDetailsDTO.getIntro())
                .website(userDetailsDTO.getWebsite())
                .isSubscribe(userDetailsDTO.getIsSubscribe())
                .isDisable(userDetailsDTO.getIsDisable())
                .createTime(LocalDateTime.now())
                .build();
        userInfoMapper.insertUserInfo(userInfo);
        UserAuth userAuth = UserAuth.builder()
                .userInfoId(userInfo.getId())
                .username(userDetailsDTO.getUsername())
                .password(BCrypt.hashpw(userDetailsDTO.getPassword(), BCrypt.gensalt()))
                .loginType(userDetailsDTO.getLoginType())
                .build();
        userAuthMapper.insert(userAuth);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteUser(Integer id) {
        UserAuth userAuth = userAuthMapper.selectOne(new LambdaQueryWrapper<UserAuth>()
                .select(UserAuth::getUserInfoId)
                .eq(UserAuth::getId, id));
        Integer userInfoId = userAuth.getUserInfoId();
        userRoleMapper.delete(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, userInfoId));
        userInfoMapper.delete(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getId, userInfoId));
        userAuthMapper.delete(new LambdaQueryWrapper<UserAuth>().eq(UserAuth::getId, id));
        userHomeMapper.delete(new LambdaQueryWrapper<UserHome>().eq(UserHome::getUserInfoId, userInfoId));
    }

    private Boolean checkUser(UserDetailsDTO userDetailsDTO) {
        UserAuth userAuth = userAuthMapper.selectOne(new LambdaQueryWrapper<UserAuth>()
                .select(UserAuth::getUsername)
                .eq(UserAuth::getUsername, userDetailsDTO.getUsername()));
        return Objects.nonNull(userAuth);
    }
}

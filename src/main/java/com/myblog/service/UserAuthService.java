package com.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myblog.entity.UserAuth;
import com.myblog.model.dto.PageResultDTO;
import com.myblog.model.dto.UserAdminDTO;
import com.myblog.model.dto.UserDetailsDTO;
import com.myblog.model.dto.UserInfoDTO;
import com.myblog.model.vo.ConditionVO;
import com.myblog.model.vo.UserConditionVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserAuthService extends IService<UserAuth> {
    PageResultDTO<UserAdminDTO> listUsers(UserConditionVO conditionVO);

    UserInfoDTO userInfo();

    void logout(Integer id);

    String uploadAvatar(MultipartFile file);

    void createUser(UserDetailsDTO userDetailsDTO);

    void deleteUser(Integer id);
}

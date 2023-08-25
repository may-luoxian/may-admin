package com.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myblog.entity.UserAuth;
import com.myblog.model.dto.UserAdminDTO;
import com.myblog.model.vo.ConditionVO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserAuthService extends IService<UserAuth> {
    List<UserAdminDTO> listUsers(ConditionVO conditionVO);
}

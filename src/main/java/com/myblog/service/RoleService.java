package com.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myblog.entity.Role;
import com.myblog.model.dto.PageResultDTO;
import com.myblog.model.dto.RoleDTO;
import com.myblog.model.vo.ConditionVO;

import java.util.List;

public interface RoleService extends IService<Role> {
    PageResultDTO<RoleDTO> listRoles(ConditionVO conditionVO);
}

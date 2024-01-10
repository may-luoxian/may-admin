package com.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myblog.entity.Role;
import com.myblog.model.dto.LabelOptionDTO;
import com.myblog.model.dto.PageResultDTO;
import com.myblog.model.dto.RoleDTO;
import com.myblog.model.vo.ConditionVO;
import com.myblog.model.vo.RoleVO;
import com.myblog.model.vo.UserVO;

import java.util.List;

public interface RoleService extends IService<Role> {
    PageResultDTO<RoleDTO> listRoles(ConditionVO conditionVO);
    List<LabelOptionDTO> listRoleMenus();
    void saveOrUpdateMenuAuth(Integer roleId, List<Integer> ids);
    void saveOrUpdateResourceAuth(Integer roleId, List<Integer> ids);
    List<RoleDTO> listAllowRoles();
    void updateAllowRoles(UserVO userVO);
    void saveOrUpdateRole(RoleVO roleVO);
}

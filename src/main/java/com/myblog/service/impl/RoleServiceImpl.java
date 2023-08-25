package com.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myblog.entity.*;
import com.myblog.exception.BizException;
import com.myblog.handler.FilterInvocationSecurityMetadataSourceImpl;
import com.myblog.mapper.MenuMapper;
import com.myblog.mapper.RoleMapper;
import com.myblog.mapper.RoleMenuMapper;
import com.myblog.model.dto.LabelOptionDTO;
import com.myblog.model.dto.PageResultDTO;
import com.myblog.model.dto.RoleDTO;
import com.myblog.model.vo.ConditionVO;
import com.myblog.model.vo.UserVO;
import com.myblog.service.RoleMenuService;
import com.myblog.service.RoleResourceService;
import com.myblog.service.RoleService;
import com.myblog.service.UserRoleService;
import com.myblog.util.BeanCopyUtil;
import com.myblog.util.PageUtil;
import com.myblog.util.TreeUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private RoleResourceService roleResourceService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private FilterInvocationSecurityMetadataSourceImpl filterInvocationSecurityMetadataSource;

    @SneakyThrows
    @Override
    public PageResultDTO<RoleDTO> listRoles(ConditionVO conditionVO) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<Role>()
                .like(StringUtils.isNotBlank(conditionVO.getKeywords()), Role::getRoleName, conditionVO.getKeywords());
        CompletableFuture<Integer> supplyCount = supplyAsync(() -> roleMapper.selectCount(queryWrapper));
        List<RoleDTO> roleDTOs = roleMapper.listRoles(PageUtil.getLimitCurrent(), PageUtil.getSize(), conditionVO);
        return new PageResultDTO<>(roleDTOs, supplyCount.get());
    }

    @Override
    public List<LabelOptionDTO> listRoleMenus() {
        List<Menu> menus = menuMapper.selectList(null);
        List<Menu> catalogs = listCatalogs(menus);
        Map<Integer, List<Menu>> childrenMap = getMenuMap(menus);
        HashMap<Integer, List<LabelOptionDTO>> labelOptionDTOListHashMap = new HashMap<>();
        childrenMap.forEach((id, menuList) -> {
            labelOptionDTOListHashMap.put(id, menuList.stream()
                    .map((item) -> LabelOptionDTO.builder()
                            .id(item.getId())
                            .label(item.getName())
                            .icon(item.getIcon())
                            .orderNum(item.getOrderNum())
                            .build())
                    .collect(Collectors.toList()));
        });
        List<LabelOptionDTO> rootList = catalogs.stream()
                .map(item -> LabelOptionDTO.builder()
                        .id(item.getId())
                        .label(item.getName())
                        .icon(item.getIcon())
                        .orderNum(item.getOrderNum())
                        .build())
                .sorted(Comparator.comparing(LabelOptionDTO::getOrderNum))
                .collect(Collectors.toList());
        LabelOptionDTO t = new LabelOptionDTO();
        TreeUtil.buildTree(rootList, labelOptionDTOListHashMap, t, "children");
        return rootList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateMenuAuth(Integer roleId, List<Integer> ids) {
        if (Objects.isNull(roleId)) {
            throw new BizException("未选择角色");
        }
        roleMenuService.remove(new LambdaQueryWrapper<RoleMenu>()
                .eq(RoleMenu::getRoleId, roleId));
        if (ids != null && ids.size() != 0) {
            List<RoleMenu> roleMenuList = ids.stream()
                    .map(id -> RoleMenu.builder()
                            .roleId(roleId)
                            .menuId(id)
                            .build())
                    .collect(Collectors.toList());
            roleMenuService.saveBatch(roleMenuList);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateResourceAuth(Integer roleId, List<Integer> ids) {
        if (Objects.isNull(roleId)) {
            throw new BizException("未选择角色");
        }
        roleResourceService.remove(new LambdaQueryWrapper<RoleResource>()
                .eq(RoleResource::getRoleId, roleId));
        if (ids != null && ids.size() != 0) {
            List<RoleResource> roleResourceList = ids.stream()
                    .map(id -> RoleResource.builder()
                            .roleId(roleId)
                            .resourceId(id)
                            .build())
                    .collect(Collectors.toList());
            roleResourceService.saveBatch(roleResourceList);
        }
        filterInvocationSecurityMetadataSource.clearDataSource();
    }

    @Override
    public List<RoleDTO> listAllowRoles() {
        LambdaQueryWrapper<Role> lambdaQueryWrapper = new LambdaQueryWrapper<Role>()
                .eq(Role::getIsDisable, 0);
        List<Role> roles = roleMapper.selectList(lambdaQueryWrapper);
        return BeanCopyUtil.copyList(roles, RoleDTO.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateAllowRoles(UserVO userVO) {
        userRoleService.remove(new LambdaQueryWrapper<UserRole>()
                .eq(UserRole::getUserId, userVO.getId()));
        List<Integer> roleIds = userVO.getRoleIds();
        if (roleIds != null && roleIds.size() != 0) {
            List<UserRole> userRoles = roleIds.stream()
                    .map((id) -> UserRole
                            .builder()
                            .userId(userVO.getId())
                            .roleId(id)
                            .build())
                    .collect(Collectors.toList());
            userRoleService.saveBatch(userRoles);
        }
    }

    private List<Menu> listCatalogs(List<Menu> menus) {
        return menus.stream()
                .filter(item -> Objects.isNull(item.getParentId()))
                .sorted(Comparator.comparing(Menu::getOrderNum))
                .collect(Collectors.toList());
    }

    private Map<Integer, List<Menu>> getMenuMap(List<Menu> menus) {
        return menus.stream()
                .filter(item -> Objects.nonNull(item.getParentId()))
                .collect(Collectors.groupingBy(Menu::getParentId));
    }
}

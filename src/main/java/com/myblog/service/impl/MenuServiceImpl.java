package com.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myblog.entity.Menu;
import com.myblog.model.dto.LabelOptionDTO;
import com.myblog.model.dto.MenuDTO;
import com.myblog.model.dto.UserMenuDTO;
import com.myblog.mapper.MenuMapper;
import com.myblog.model.vo.MenuVO;
import com.myblog.service.MenuService;
import com.myblog.util.BeanCopyUtil;
import com.myblog.util.TreeUtil;
import com.myblog.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.myblog.constant.CommonConstant.TRUE;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    //获取用户菜单
    @Override
    public List<UserMenuDTO> listUserMenus() {
        List<Menu> menus = menuMapper.listMenusByUserInfoId(UserUtil.getUserDetailsDTO().getUserInfoId());
        List<Menu> catalogs = listCatalogs(menus);
        Map<Integer, List<Menu>> childrenMap = getMenuMap(menus);
        List<UserMenuDTO> rootList = catalogs.stream()
                .map(item -> BeanCopyUtil.copyObject(item, UserMenuDTO.class))
                .sorted(Comparator.comparing(UserMenuDTO::getOrderNum))
                .collect(Collectors.toList());
        Map<Integer, List<UserMenuDTO>> childDTOMap = new HashMap<>();
        childrenMap.forEach((id, menuList) -> {
            List<UserMenuDTO> menuDTOList = BeanCopyUtil.copyList(menuList, UserMenuDTO.class);
            childDTOMap.put(id, menuDTOList);
        });
        UserMenuDTO t = new UserMenuDTO();
        TreeUtil.buildTree(rootList, childDTOMap, t, "children");
        return rootList;
    }

    @Override
    public List<MenuDTO> listMenus() {
        List<Menu> menus = menuMapper.selectList(null);
        List<Menu> catalogs = listCatalogs(menus);
        Map<Integer, List<Menu>> childrenMap = getMenuMap(menus);
        List<MenuDTO> rootList = catalogs.stream()
                .map(item -> BeanCopyUtil.copyObject(item, MenuDTO.class))
                .sorted(Comparator.comparing(MenuDTO::getOrderNum))
                .collect(Collectors.toList());
        Map<Integer, List<MenuDTO>> childDTOMap = new HashMap<>();
        childrenMap.forEach((id, menuList) -> {
            List<MenuDTO> menuDTOList = BeanCopyUtil.copyList(menuList, MenuDTO.class);
            childDTOMap.put(id, menuDTOList);
        });
        MenuDTO t = new MenuDTO();
        TreeUtil.buildTree(rootList, childDTOMap, t, "children");
        return rootList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateMenu(MenuVO menuVO) {
        Menu menu = BeanCopyUtil.copyObject(menuVO, Menu.class);
        this.saveOrUpdate(menu);
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

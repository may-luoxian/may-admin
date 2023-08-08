package com.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myblog.entity.Menu;
import com.myblog.model.dto.MenuDTO;
import com.myblog.model.dto.UserMenuDTO;
import com.myblog.model.vo.MenuVO;

import java.util.List;

public interface MenuService extends IService<Menu> {
    //获取用户菜单列表
    List<UserMenuDTO> listUserMenus();

    // 获取系统菜单列表
    List<MenuDTO> listMenus();

    // 新增或修改菜单
    void saveOrUpdateMenu(MenuVO menuVO);
}

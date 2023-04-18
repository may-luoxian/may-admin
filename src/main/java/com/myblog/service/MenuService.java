package com.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myblog.entity.Menu;
import com.myblog.model.dto.UserMenuDTO;

import java.util.List;

public interface MenuService extends IService<Menu> {
    //获取菜单列表
    List<UserMenuDTO> listUserMenus();
}

package com.myblog.controller;

import com.myblog.model.dto.LabelOptionDTO;
import com.myblog.model.dto.MenuDTO;
import com.myblog.model.dto.UserMenuDTO;
import com.myblog.model.vo.MenuVO;
import com.myblog.model.vo.ResultVO;
import com.myblog.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "系统模块")
@RestController
public class MenuController {
    @Autowired
    private MenuService menuService;

    @ApiOperation("获取当前用户路由")
    @GetMapping("/admin/user/menus")
    public ResultVO<List<UserMenuDTO>> listUserMenus() {
        return ResultVO.ok(menuService.listUserMenus());
    }

    @ApiOperation("新增或修改菜单")
    @PostMapping("/admin/menus")
    public ResultVO<?> saveOrUpdateMenu(@Valid @RequestBody MenuVO menuVO) {
        menuService.saveOrUpdateMenu(menuVO);
        return ResultVO.ok();
    }

    @ApiOperation("获取系统路由")
    @GetMapping("/admin/menus")
    public ResultVO<List<MenuDTO>> listMenus() {
        return ResultVO.ok(menuService.listMenus());
    }

    @ApiOperation(value = "查询角色菜单")
    @GetMapping("/admin/role/menus")
    public ResultVO<List<LabelOptionDTO>> listRoleMenus() {
        return ResultVO.ok(menuService.listRoleMenus());
    }

}

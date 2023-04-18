package com.myblog.controller;

import com.myblog.model.vo.ResultVO;
import com.myblog.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "系统模块")
@RestController
public class MenuController {
    @Autowired
    private MenuService menuService;

    @ApiOperation("获取系统路由")
    @GetMapping("/system/menus")
    public ResultVO getMenu() {
        return ResultVO.ok(menuService.listUserMenus());
    }
}

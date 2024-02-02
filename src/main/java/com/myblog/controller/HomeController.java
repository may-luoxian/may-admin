package com.myblog.controller;

import com.myblog.exception.BizException;
import com.myblog.model.dto.HomeEnableAndNotEnableDTO;
import com.myblog.model.vo.HomeEnableVO;
import com.myblog.model.vo.HomeOrderVO;
import com.myblog.model.vo.HomeVO;
import com.myblog.model.vo.ResultVO;
import com.myblog.service.HomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "首页模块")
@RestController
@RequestMapping("/admin/home")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @ApiOperation("创建模块")
    @PostMapping("/add")
    public ResultVO<?> createModel(@RequestBody HomeVO homeVO) {
        homeService.createModel(homeVO);
        return ResultVO.ok();
    }

    @ApiOperation("编辑模块")
    @PostMapping("/edit")
    public ResultVO<?> editModel(@RequestBody HomeVO homeVO) {
        homeService.editModel(homeVO);
        return ResultVO.ok();
    }

    @ApiOperation("查询用户启用，未启用模块")
    @GetMapping("/listByUser")
    public ResultVO<HomeEnableAndNotEnableDTO> getHomeListByUser(Integer userId) {
        return ResultVO.ok(homeService.getEnableNotEnableListByUser(userId));
    }

    @ApiOperation("查询角色启用，未启用模块")
    @GetMapping("/listByRole")
    public ResultVO<HomeEnableAndNotEnableDTO> getHomeListByRole(Integer roleId) {
        return ResultVO.ok(homeService.getEnableNotEnableListByRole(roleId));
    }

    @ApiOperation("处理门户块启用")
    @PostMapping("/enable")
    public ResultVO enableHome(@RequestBody HomeEnableVO homeEnableVO) {
        String enableType = homeEnableVO.getEnableType();
        List<HomeOrderVO> homeOrderVOList = homeEnableVO.getEnableData();
        if (enableType.equals("user")) {
            Integer userInfoId = homeEnableVO.getUserInfoId();
            homeService.enableUserHome(userInfoId, homeOrderVOList);
        } else if (enableType.equals("role")) {
            Integer roleId = homeEnableVO.getRoleId();
            homeService.enableRoleHome(roleId, homeOrderVOList);
        } else {
            throw new BizException("不能识别启用类型");
        }
        return ResultVO.ok();
    }
}

package com.myblog.controller;

import com.myblog.model.dto.HomeEnableAndNotEnableDTO;
import com.myblog.model.vo.HomeVO;
import com.myblog.model.vo.ResultVO;
import com.myblog.service.HomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("查询启用，未启用模块")
    @GetMapping("/list")
    public ResultVO<HomeEnableAndNotEnableDTO> getHomeList() {
        return ResultVO.ok(homeService.getEnableNotEnableList());
    }
}

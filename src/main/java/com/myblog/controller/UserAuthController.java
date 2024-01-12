package com.myblog.controller;

import com.myblog.model.dto.UserAdminDTO;
import com.myblog.model.dto.UserInfoDTO;
import com.myblog.model.vo.ConditionVO;
import com.myblog.model.vo.ResultVO;
import com.myblog.service.UserAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "用户账号模块")
@RestController
@RequestMapping("/admin/users")
public class UserAuthController {

    @Autowired
    private UserAuthService userAuthService;

    @ApiOperation("获取用户列表")
    @GetMapping("/list")
    public ResultVO<List<UserAdminDTO>> listUsers(ConditionVO conditionVO) {
        return ResultVO.ok(userAuthService.listUsers(conditionVO));
    }

    @ApiOperation("查询用户信息")
    @GetMapping("/info")
    public ResultVO<UserInfoDTO> getUserInfo() {
        return ResultVO.ok(userAuthService.userInfo());
    }

    @ApiOperation("退出登录")
    @GetMapping("/logout")
    public ResultVO<?> logout(@RequestParam("userId") Integer id) {
        userAuthService.logout(id);
        return ResultVO.ok();
    }
}

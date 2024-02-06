package com.myblog.controller;

import com.myblog.model.dto.PageResultDTO;
import com.myblog.model.dto.UserAdminDTO;
import com.myblog.model.dto.UserDetailsDTO;
import com.myblog.model.dto.UserInfoDTO;
import com.myblog.model.vo.ConditionVO;
import com.myblog.model.vo.ResultVO;
import com.myblog.model.vo.UserConditionVO;
import com.myblog.service.UserAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Api(tags = "用户账号模块")
@RestController
@RequestMapping("/admin/users")
public class UserAuthController {
    @Autowired
    private UserAuthService userAuthService;

    @ApiOperation("获取用户列表")
    @GetMapping("/list")
    public ResultVO<PageResultDTO<UserAdminDTO>> listUsers(UserConditionVO conditionVO) {
        return ResultVO.ok(userAuthService.listUsers(conditionVO));
    }

    @ApiOperation("查询用户信息")
    @GetMapping("/info")
    public ResultVO<UserInfoDTO> getUserInfo() {
        return ResultVO.ok(userAuthService.userInfo());
    }

    @ApiOperation("上传头像")
    @PostMapping("/avatar")
    public ResultVO<?> uploadAvatar(MultipartFile file) {
        String path = userAuthService.uploadAvatar(file);
        return ResultVO.ok(path);
    }

    @ApiOperation("创建用户")
    @PostMapping("/user")
    public ResultVO<?> createUser(@RequestBody UserDetailsDTO userDetailsDTO) {
        userAuthService.createUser(userDetailsDTO);
        return ResultVO.ok();
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/user")
    public ResultVO<?> deleteUser(@RequestBody Map map) {
        userAuthService.deleteUser((Integer) map.get("id"));
        return ResultVO.ok();
    }

    @ApiOperation("退出登录")
    @GetMapping("/logout")
    public ResultVO<?> logout(@RequestParam("userId") Integer id) {
        userAuthService.logout(id);
        return ResultVO.ok();
    }
}

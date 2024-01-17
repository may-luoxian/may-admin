package com.myblog.controller;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.myblog.exception.BizException;
import com.myblog.model.dto.LabelOptionDTO;
import com.myblog.model.dto.PageResultDTO;
import com.myblog.model.dto.RoleDTO;
import com.myblog.model.vo.ConditionVO;
import com.myblog.model.vo.ResultVO;
import com.myblog.model.vo.RoleVO;
import com.myblog.model.vo.UserVO;
import com.myblog.service.ResourceService;
import com.myblog.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Api(tags = "角色模块")
@RestController
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;

    @ApiOperation(value = "查询角色列表")
    @GetMapping("/roles")
    public ResultVO<PageResultDTO<RoleDTO>> listRoles(ConditionVO conditionVO) {
        return ResultVO.ok(roleService.listRoles(conditionVO));
    }

    @ApiOperation(value = "新增或修改角色")
    @PostMapping("/roles")
    public ResultVO<?> saveOrUpdateRole(@RequestBody @Valid RoleVO roleVO) {
        roleService.saveOrUpdateRole(roleVO);
        return ResultVO.ok();
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("/roles")
    public ResultVO<Object> deleteRoles(@RequestBody Map map) {
        List<Integer> ids = (List<Integer>) map.get("ids");
        if (ids != null && ids.size() != 0) {
            roleService.deleteRoles(ids);
            return ResultVO.ok();
        }
        return ResultVO.fail("未选择角色");
    }

    @ApiOperation(value = "查询角色菜单")
    @GetMapping("/menus")
    public ResultVO<List<LabelOptionDTO>> listRoleMenus() {
        return ResultVO.ok(roleService.listRoleMenus());
    }

    @ApiOperation(value = "新增或修改角色菜单权限")
    @PostMapping("/menus")
    public ResultVO<?> saveOrUpdateMenuAuth(@RequestBody RoleVO roleVO) {
        roleService.saveOrUpdateMenuAuth(roleVO.getId(), roleVO.getRoleMenuIds());
        return ResultVO.ok();
    }

    @ApiOperation(value = "新增或修改角色资源权限")
    @PostMapping("/resources")
    public ResultVO<?> saveOrUpdateResourceAuth(@RequestBody RoleVO roleVO) {
        roleService.saveOrUpdateResourceAuth(roleVO.getId(), roleVO.getRoleResourceIds());
        return ResultVO.ok();
    }

    @ApiOperation(value = "获取可分配角色列表")
    @GetMapping("/allow")
    public ResultVO<List<RoleDTO>> listAllowRoles() {
        return ResultVO.ok(roleService.listAllowRoles());
    }

    @ApiOperation(value = "分配角色")
    @PutMapping("/allow")
    public ResultVO<?> updateAllowRoles(@RequestBody UserVO userVO) {
        roleService.updateAllowRoles(userVO);
        return ResultVO.ok();
    }

    @ApiOperation(value = "获取角色资源列表")
    @GetMapping("/resources")
    public ResultVO<List<LabelOptionDTO>> listRoleResources() {
        return ResultVO.ok(resourceService.listRoleResources());
    }
}

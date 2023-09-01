package com.myblog.controller;

import com.myblog.model.dto.LabelOptionDTO;
import com.myblog.model.dto.PageResultDTO;
import com.myblog.model.dto.ResourceDTO;
import com.myblog.model.vo.ResourceVO;
import com.myblog.model.vo.ResultVO;
import com.myblog.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "资源模块")
@RestController
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @ApiOperation(value = "获取资源列表")
    @GetMapping("/admin/resources")
    public ResultVO<List<ResourceDTO>> listResources(ResourceVO resourceVO) {
        return ResultVO.ok(resourceService.listResources(resourceVO));
    }

    @ApiOperation(value = "获取角色资源列表")
    @GetMapping("/admin/role/resources")
    public ResultVO<List<LabelOptionDTO>> listRoleResources() {
        return ResultVO.ok(resourceService.listRoleResources());
    }

    @ApiOperation(value = "新增或修改资源")
    @PostMapping("/admin/resource")
    public ResultVO<?> saveOrUpdateResource(@RequestBody ResourceVO resourceVO) {
        resourceService.saveOrUpdateResource(resourceVO);
        return ResultVO.ok();
    }
}

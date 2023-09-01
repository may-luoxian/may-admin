package com.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myblog.entity.Resource;
import com.myblog.model.dto.LabelOptionDTO;
import com.myblog.model.dto.PageResultDTO;
import com.myblog.model.dto.ResourceDTO;
import com.myblog.model.vo.ResourceVO;
import com.myblog.model.vo.ResultVO;

import java.util.List;

public interface ResourceService extends IService<Resource> {
    List<LabelOptionDTO> listRoleResources();

    List<ResourceDTO> listResources(ResourceVO resourceVO);

    void saveOrUpdateResource(ResourceVO resourceVO);
}

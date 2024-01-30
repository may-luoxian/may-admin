package com.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myblog.entity.Resource;
import com.myblog.handler.FilterInvocationSecurityMetadataSourceImpl;
import com.myblog.mapper.ResourceMapper;
import com.myblog.mapper.RoleResourceMapper;
import com.myblog.model.dto.LabelOptionDTO;
import com.myblog.model.dto.ResourceDTO;
import com.myblog.model.vo.ResourceVO;
import com.myblog.service.ResourceService;
import com.myblog.util.BeanCopyUtil;
import com.myblog.util.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    @Autowired
    private FilterInvocationSecurityMetadataSourceImpl filterInvocationSecurityMetadataSource;
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Override
    public List<LabelOptionDTO> listRoleResources(ResourceVO resourceVO) {
        LambdaQueryWrapper<Resource> wrapper = new LambdaQueryWrapper<Resource>().like(StringUtils.isNotBlank(resourceVO.getResourceName()), Resource::getResourceName, resourceVO.getResourceName());
        List<Resource> resources = resourceMapper.selectList(wrapper);
        if (StringUtils.isBlank(resourceVO.getResourceName())) {
            List<Resource> catalogs = listCatalogs(resources);
            Map<Integer, List<Resource>> resourceMap = getResourceMap(resources);
            List<LabelOptionDTO> rootList = catalogs.stream()
                    .map(item -> LabelOptionDTO.builder()
                            .id(item.getId())
                            .label(item.getResourceName())
                            .build())
                    .collect(Collectors.toList());
            HashMap<Integer, List<LabelOptionDTO>> childMap = new HashMap<>();
            resourceMap.forEach((id, item) -> {
                List<LabelOptionDTO> labelOptionDTOs = item.stream()
                        .map(child -> LabelOptionDTO.builder()
                                .label(child.getResourceName())
                                .id(child.getId()).build())
                        .collect(Collectors.toList());
                childMap.put(id, labelOptionDTOs);
            });
            LabelOptionDTO t = new LabelOptionDTO();
            TreeUtil.buildTree(rootList, childMap, t, "children", false);
            return rootList;
        } else {
            return resources.stream().map(item ->
                            LabelOptionDTO.builder()
                                    .id(item.getId())
                                    .label(item.getResourceName())
                                    .build()
                    )
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<ResourceDTO> listResources(ResourceVO resourceVO) {
        List<Resource> resourceList = resourceMapper.selectResource(resourceVO);
        List<Resource> catalogs = listCatalogs(resourceList);
        Map<Integer, List<Resource>> resourceMap = getResourceMap(resourceList);
        HashMap<Integer, List<ResourceDTO>> childMap = new HashMap<>();
        resourceMap.forEach((id, item) -> {
            List<ResourceDTO> resourceDTOS = BeanCopyUtil.copyList(item, ResourceDTO.class);
            resourceDTOS.stream().forEach(resourceDTO -> resourceDTO.setIsModel(0));
            childMap.put(id, resourceDTOS);
        });
        List<ResourceDTO> rootList = catalogs.stream()
                .map(item -> {
                    ResourceDTO resourceDTO = BeanCopyUtil.copyObject(item, ResourceDTO.class);
                    resourceDTO.setIsModel(1);
                    return resourceDTO;
                })
                .collect(Collectors.toList());
        ResourceDTO t = new ResourceDTO();
        TreeUtil.buildTree(rootList, childMap, t, "children", true);
        if (CollectionUtils.isNotEmpty(childMap)) {
            List<ResourceDTO> childrenList = new ArrayList<>();
            childMap.values().forEach(childrenList::addAll);
            rootList.addAll(childrenList);
        }
        return rootList;
    }

    @Override
    public void saveOrUpdateResource(ResourceVO resourceVO) {
        Resource resource = BeanCopyUtil.copyObject(resourceVO, Resource.class);
        this.saveOrUpdate(resource);
        filterInvocationSecurityMetadataSource.clearDataSource();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteResources(List<Integer> ids) {
        roleResourceMapper.batchDeleteByResourceIds(ids);
        resourceMapper.deleteBatchIds(ids);
    }

    public List<Resource> listCatalogs(List<Resource> resources) {
        return resources.stream()
                .filter(item -> Objects.isNull(item.getParentId()))
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Resource>> getResourceMap(List<Resource> resources) {
        return resources.stream()
                .filter(item -> Objects.nonNull(item.getParentId()))
                .collect(Collectors.groupingBy(Resource::getParentId));
    }
}

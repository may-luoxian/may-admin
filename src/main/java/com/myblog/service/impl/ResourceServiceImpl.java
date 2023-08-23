package com.myblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myblog.entity.Resource;
import com.myblog.handler.FilterInvocationSecurityMetadataSourceImpl;
import com.myblog.mapper.ResourceMapper;
import com.myblog.model.dto.LabelOptionDTO;
import com.myblog.model.dto.ResourceDTO;
import com.myblog.model.vo.ResourceVO;
import com.myblog.service.ResourceService;
import com.myblog.util.BeanCopyUtil;
import com.myblog.util.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    @Autowired
    private FilterInvocationSecurityMetadataSourceImpl filterInvocationSecurityMetadataSource;
    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<LabelOptionDTO> listRoleResources() {
        List<Resource> resources = resourceMapper.selectList(null);
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
        TreeUtil.buildTree(rootList, childMap, t, "children");
        return rootList;
    }

    @Override
    public List<ResourceDTO> listResources() {
        List<Resource> resources = resourceMapper.selectList(null);
        List<Resource> catalogs = listCatalogs(resources);
        Map<Integer, List<Resource>> resourceMap = getResourceMap(resources);
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
        TreeUtil.buildTree(rootList, childMap, t, "children");
        return rootList;
    }

    @Override
    public void saveOrUpdateResource(ResourceVO resourceVO) {
        Resource resource = BeanCopyUtil.copyObject(resourceVO, Resource.class);
        this.saveOrUpdate(resource);
        filterInvocationSecurityMetadataSource.clearDataSource();
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

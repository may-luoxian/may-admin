package com.myblog.handler;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.myblog.mapper.RoleMapper;
import com.myblog.model.dto.ResourceRoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;

/**
 * 该类用于构建资源与角色之间的映射关系
 */
@Component
public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private RoleMapper roleMapper;

    private static List<ResourceRoleDTO> resourceRoleList;

    // 查询全部资源角色数据
    @PostConstruct
    private void loadResourceRoleList() {
        resourceRoleList = roleMapper.listResourceRoles();
    }

    public void clearDataSource() {
        resourceRoleList = null;
    }

    // 当接收到请求后，若请求参数匹配成功，构建角色数组，否则只生成disable角色
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (CollectionUtils.isEmpty(resourceRoleList)) {
            this.loadResourceRoleList();
        }
        FilterInvocation fi = (FilterInvocation) object;
        String method = fi.getRequest().getMethod();
        String url = fi.getRequest().getRequestURI();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        for (ResourceRoleDTO resourceRoleDTO : resourceRoleList) {
            if (antPathMatcher.match(resourceRoleDTO.getUrl(), url) && resourceRoleDTO.getRequestMethod().equals(method)) {
                List<String> roleList = resourceRoleDTO.getRoleList();
                if (CollectionUtils.isEmpty(roleList)) {
                    return SecurityConfig.createList("disable");
                }
                return SecurityConfig.createList(roleList.toArray(new String[]{}));
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}

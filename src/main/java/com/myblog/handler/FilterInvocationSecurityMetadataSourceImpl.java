package com.myblog.handler;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.Collection;
@Component
public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
//        if (CollectionUtils.isEmpty(resourceRoleList)) {
//            this.loadResourceRoleList();
//        }
//        FilterInvocation fi = (FilterInvocation) object;
//        String method = fi.getRequest().getMethod();
//        String url = fi.getRequest().getRequestURI();
//        AntPathMatcher antPathMatcher = new AntPathMatcher();
//        for (ResourceRoleDTO resourceRoleDTO : resourceRoleList) {
//            if (antPathMatcher.match(resourceRoleDTO.getUrl(), url) && resourceRoleDTO.getRequestMethod().equals(method)) {
//                List<String> roleList = resourceRoleDTO.getRoleList();
//                if (CollectionUtils.isEmpty(roleList)) {
//                    return SecurityConfig.createList("disable");
//                }
//                return SecurityConfig.createList(roleList.toArray(new String[]{}));
//            }
//        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}

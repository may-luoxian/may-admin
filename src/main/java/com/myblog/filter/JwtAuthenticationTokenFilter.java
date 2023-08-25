package com.myblog.filter;

import com.myblog.model.dto.UserDetailsDTO;
import com.myblog.service.TokenService;
import com.myblog.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
@SuppressWarnings("all")
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    public AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 从请求头携带的token查询redis，获取用户信息
        UserDetailsDTO userDetailsDTO = tokenService.getUserDetailDTO(request);
        if (Objects.nonNull(userDetailsDTO) && Objects.isNull(UserUtil.getAuthentication())) {
            // 更新redis中存储用户信息的过期时间
            tokenService.renewToken(userDetailsDTO);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetailsDTO, null, userDetailsDTO.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}

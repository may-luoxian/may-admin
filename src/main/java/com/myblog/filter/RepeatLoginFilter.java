package com.myblog.filter;

import com.alibaba.fastjson.JSON;
import com.myblog.constant.CommonConstant;
import com.myblog.enums.StatusCodeEnum;
import com.myblog.model.dto.UserDetailsDTO;
import com.myblog.model.vo.ResultVO;
import com.myblog.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import static com.myblog.constant.AuthConstant.TOKEN_HEADER;
import static com.myblog.constant.AuthConstant.TOKEN_PREFIX;

@Component
public class RepeatLoginFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestToken = Optional.ofNullable(request.getHeader(TOKEN_HEADER)).orElse("").replaceFirst(TOKEN_PREFIX, "");
        if (requestToken == "") {
            filterChain.doFilter(request, response);
            return;
        }
        UserDetailsDTO userDetailsDTO = tokenService.getUserDetailDTO(request);
        if (Objects.isNull(userDetailsDTO)) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = userDetailsDTO.getToken();
        if (repeatLoginValidate(token, requestToken)) {
            filterChain.doFilter(request, response);
            return;
        }
        repeatLogout(response);
    }

    private boolean repeatLoginValidate(String redisToken, String requestToken) {
        if (redisToken.equals(requestToken)) {
            return true;
        }
        Date redisIssuedAt = tokenService.parseToken(redisToken).getIssuedAt();
        Date requestIssuedAt = tokenService.parseToken(requestToken).getIssuedAt();
        if (requestIssuedAt.after(redisIssuedAt)) {
            return true;
        } else {
            return false;
        }

    }

    private void repeatLogout(HttpServletResponse response) throws IOException {
        response.setContentType(CommonConstant.APPLICATION_JSON);
        response.getWriter().write(JSON.toJSONString(ResultVO.fail(StatusCodeEnum.REPEAT_LOGIN)));
    }
}

package com.myblog.service;

import com.myblog.model.dto.UserDetailsDTO;
import io.jsonwebtoken.Claims;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {
    public String createToken(UserDetailsDTO userDetailsDTO);
    public String createToken(String subject);
    public Claims parseToken(String token);
    void renewToken(UserDetailsDTO userDetailsDTO);
    void refreshToken(UserDetailsDTO userDetailsDTO);
    UserDetailsDTO getUserDetailDTO(HttpServletRequest request);
}

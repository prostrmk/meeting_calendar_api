package com.itechart.webflux.web.config.jwt;

import com.itechart.webflux.web.core.model.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JwtSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String token = httpServletRequest.getHeader("Authorization");
        if (token != null) {
            token = token.split(" ")[1];
        }
        User validate = new JwtVal().validate(token);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(validate.getRole().name()));
        JwtAuthToken jwtAuthToken = new JwtAuthToken(token, validate.getUsername(), validate.getRole(), authorities);
        jwtAuthToken.setDetails(jwtAuthToken.getCredentials());
        SecurityContextHolder.getContext().setAuthentication(jwtAuthToken);

    }
}

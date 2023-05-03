package com.example.filter;

import com.example.cloneboard.jwt.JwtProvider;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // authentication, authorization

        String path = request.getRequestURI();
        String method = request.getMethod();

        // allow sign in, sign up or request with GET method without token
        if(method.equals("GET") || path.contains("/users/sign")) {
            chain.doFilter(request, response);
            return;
        }

        // validate token
        String accessToken = jwtProvider.resolveAccessToken(request);
        boolean isTokenValid = jwtProvider.validateToken(accessToken);

        if (isTokenValid) this.setAuthentication(accessToken);  // if token valid, set user info(email, role, etc..) in security context

        chain.doFilter(request, response);  // filter chain must be executed in any circumstance.

    }

    private void setAuthentication(String token){
        // get user info(email, role, etc..) from token
        Authentication authentication = jwtProvider.getAuthentication(token);
        // set user info in security context to use it anywhere(check user role in spring security, ...)
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}

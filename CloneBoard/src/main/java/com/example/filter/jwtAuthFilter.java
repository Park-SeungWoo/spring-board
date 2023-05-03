package com.example.filter;

import com.example.cloneboard.jwt.JwtProvider;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class jwtAuthFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // authentication, authorization

        String path = request.getRequestURI();
        String method = request.getMethod();

        // allow sign in, sign up or request with GET method without token
        if(method.equals("GET") || path.contains("/users/sign"))
            chain.doFilter(request, response);
        else {
            // validate token
            String accessToken = jwtProvider.resolveAccessToken(request);
            boolean isTokenValid = jwtProvider.validateToken(accessToken);

            if (isTokenValid) chain.doFilter(request, response);
            else throw new JwtException("Invalid token");
        }
    }
}

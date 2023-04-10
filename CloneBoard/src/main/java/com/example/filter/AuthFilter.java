package com.example.filter;

import com.example.auth.dto.users.UserAuthorizedDto;
import com.example.auth.service.users.UserAuthorizationService;
import com.example.auth.service.users.UserAuthorizationServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.util.StreamUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map;

@RequiredArgsConstructor
public class AuthFilter implements Filter {

    private final UserAuthorizationService userAuthorizationService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // authentication, authorization
        /*
        get body
         */
//        byte[] inputStreamBytes = StreamUtils.copyToByteArray(request.getInputStream());
//        System.out.println(inputStreamBytes.length);  // null == length is 0
//        Map<String, String> jsonRequest = new ObjectMapper().readValue(inputStreamBytes, Map.class);
//        System.out.println(jsonRequest);

        /*
        get specific header
         */
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        String token = httpRequest.getHeader("Authorization");

        /*
        get all headers
         */
//        Enumeration<String> headers = httpRequest.getHeaderNames();
//        for (String name; headers.hasMoreElements();){
//            name = headers.nextElement();
//            String value = httpRequest.getHeader(name);
//            System.out.println(value);
//        }

        HttpServletRequest httpReq = (HttpServletRequest) request;
        String token = httpReq.getHeader("Authorization");

        // athorization
        if(httpReq.getMethod().equals("GET") || httpReq.getRequestURI().equals("/users/login")){  // /login/~~ or GET method
            chain.doFilter(request, response);
        } else{
            UserAuthorizedDto userStatus = userAuthorizationService.validate(token);
            if(userStatus == null){
                HttpServletResponse httpRes = (HttpServletResponse) response;
                httpRes.setStatus(403, "forbidden");
            } else {
                httpReq.setAttribute("userStatus", userStatus);
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

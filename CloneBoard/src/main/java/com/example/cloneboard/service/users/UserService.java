package com.example.cloneboard.service.users;

import com.example.cloneboard.dto.users.UserAuthenticationRequestDto;
import com.example.cloneboard.dto.users.UserJoinRequestDto;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;

public interface UserService {
    public ResponseEntity<String> signUp(UserJoinRequestDto userJoinRequestDto);

    public ResponseEntity<String> delete(String email);

    public boolean signIn(UserAuthenticationRequestDto userAuthenticationRequestDto, HttpServletResponse response);
}

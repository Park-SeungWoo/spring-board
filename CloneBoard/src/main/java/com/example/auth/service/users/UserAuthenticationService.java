package com.example.auth.service.users;

import com.example.auth.dto.users.UserAuthenticationRequestDto;
import org.springframework.http.ResponseEntity;

public interface UserAuthenticationService {
    public ResponseEntity<String> userAuthentication(UserAuthenticationRequestDto userAuthenticationRequestDto);
}

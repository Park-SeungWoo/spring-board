package com.example.auth.service.users;

import com.example.auth.dto.users.UserAuthenticationRequestDto;

public interface UserAuthenticationService {
    public String userAuthentication(UserAuthenticationRequestDto userAuthenticationRequestDto);
}

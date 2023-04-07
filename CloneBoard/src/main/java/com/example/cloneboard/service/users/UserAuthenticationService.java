package com.example.cloneboard.service.users;

import com.example.cloneboard.dto.users.UserAuthenticationRequestDto;

public interface UserAuthenticationService {
    public String userAuthentication(UserAuthenticationRequestDto userAuthenticationRequestDto);
}

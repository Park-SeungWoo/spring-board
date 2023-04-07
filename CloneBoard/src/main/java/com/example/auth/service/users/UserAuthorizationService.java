package com.example.auth.service.users;

import com.example.auth.dto.users.UserAuthorizedDto;

public interface UserAuthorizationService {
    public String createToken(String email);
    public UserAuthorizedDto validate(String userToken);
}

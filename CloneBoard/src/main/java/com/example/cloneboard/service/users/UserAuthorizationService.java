package com.example.cloneboard.service.users;

import com.example.cloneboard.dto.users.UserAuthorizedDto;
import com.example.cloneboard.entity.UserEntity;

public interface UserAuthorizationService {
    public String createToken(String email);
    public UserAuthorizedDto validate(String userToken);
}

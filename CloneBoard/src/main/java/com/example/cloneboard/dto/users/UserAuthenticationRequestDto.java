package com.example.cloneboard.dto.users;

import com.example.cloneboard.entity.UserEntity;
import lombok.Getter;

@Getter
public class UserAuthenticationRequestDto {
    private String email;
    private String password;
}

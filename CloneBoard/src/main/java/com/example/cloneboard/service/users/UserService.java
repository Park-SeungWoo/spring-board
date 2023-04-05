package com.example.cloneboard.service.users;

import com.example.cloneboard.dto.users.UserJoinRequestDto;
import com.example.cloneboard.dto.users.UserResponseDto;

public interface UserService {
    public Long join(UserJoinRequestDto userJoinRequestDto);
    public UserResponseDto findOne(String email);
    public void delete(String email);
}

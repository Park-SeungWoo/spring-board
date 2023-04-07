package com.example.cloneboard.service.users;

import com.example.cloneboard.dto.users.UserJoinRequestDto;
import com.example.cloneboard.dto.users.UserResponseDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    public ResponseEntity<String> join(UserJoinRequestDto userJoinRequestDto);
    public UserResponseDto findOne(String email);
    public ResponseEntity<String> delete(String email, String token);
}

package com.example.cloneboard.service.users;

import com.example.cloneboard.dao.users.UserRepository;
import com.example.cloneboard.dto.users.UserAuthenticationRequestDto;
import com.example.cloneboard.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class UserAuthenticationServiceImpl implements UserAuthenticationService {
    private final UserRepository userRepository;
    private final UserAuthorizationService userAuthorizationService;
    @Override
    public String userAuthentication(UserAuthenticationRequestDto userAuthenticationRequestDto) {
        UserEntity user = userRepository.findByEmailAndPassword(userAuthenticationRequestDto.getEmail(), userAuthenticationRequestDto.getPassword());
        return userAuthorizationService.createToken(user.getNickname());
    }
}

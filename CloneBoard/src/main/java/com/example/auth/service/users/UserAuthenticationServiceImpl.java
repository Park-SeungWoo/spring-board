package com.example.auth.service.users;

import com.example.cloneboard.dao.users.UserRepository;
import com.example.auth.dto.users.UserAuthenticationRequestDto;
import com.example.cloneboard.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class UserAuthenticationServiceImpl implements UserAuthenticationService {
    private final UserRepository userRepository;
    private final UserAuthorizationService userAuthorizationService;
    @Override
    public ResponseEntity<String> userAuthentication(UserAuthenticationRequestDto userAuthenticationRequestDto) {
        UserEntity user = userRepository.findByEmailAndPassword(userAuthenticationRequestDto.getEmail(), userAuthenticationRequestDto.getPassword());
        if(user != null)
            return ResponseEntity.ok(userAuthorizationService.createToken(user.getNickname()));
        else
            return ResponseEntity.ok("No user");
    }
}

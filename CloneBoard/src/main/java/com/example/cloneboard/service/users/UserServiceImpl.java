package com.example.cloneboard.service.users;

import com.example.auth.dto.users.UserAuthorizedDto;
import com.example.auth.service.users.UserAuthorizationService;
import com.example.cloneboard.dao.users.UserRepository;
import com.example.cloneboard.dto.users.UserJoinRequestDto;
import com.example.cloneboard.dto.users.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor  // final, not null로 선언된 필드 생성자 자동 생성
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserAuthorizationService userAuthorizationService;

    @Transactional
    @Override
    public ResponseEntity<String> join(UserJoinRequestDto userJoinRequestDto){  // save a user
        try {
            userRepository.save(userJoinRequestDto.toEntity());
            return ResponseEntity.ok("성공: 회원가입이 성공적으로 완료되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @Override
    public UserResponseDto findOne(String email){  // find a user by email
        return userRepository.findByEmail(email).stream().map(UserResponseDto::new).collect(Collectors.toList()).get(0);
        // parse 1 length of UserEntity list(found user) to UserResponseDto
    }

    @Transactional
    @Override
    public ResponseEntity<String> delete(String email, UserAuthorizedDto authorizedUser){  // delete a user by email
        try {
            if(authorizedUser.getEmail().equals(email)) {
                userRepository.deleteByEmail(email);
                return ResponseEntity.ok("성공: 성공적으로 탈퇴했습니다.");
            } else {
                return ResponseEntity.ok("실패: 유저 정보가 다릅니다.");
            }
        } catch (Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
    }
}

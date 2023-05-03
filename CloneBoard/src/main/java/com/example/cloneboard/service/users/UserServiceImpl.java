package com.example.cloneboard.service.users;

import com.example.cloneboard.dao.users.UserRepository;
import com.example.cloneboard.dto.users.UserAuthenticationRequestDto;
import com.example.cloneboard.dto.users.UserJoinRequestDto;
import com.example.cloneboard.entity.UserEntity;
import com.example.cloneboard.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
@Primary
@RequiredArgsConstructor  // final, not null로 선언된 필드 생성자 자동 생성
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final JwtProvider jwtProvider;

    /*
    토큰 정보와 게시글 작성, 업데이트, 삭제 요청 내부의 정보 확인 즉 토큰 속 정보와, 실제 토큰 사용자를 체크하는 것은 추후에 진행하기
     */

    @Transactional
    @Override
    public ResponseEntity<String> delete(String email) {  // delete a user by email
        try {
            userRepository.deleteByEmail(email);
            // delete user's posts
            return ResponseEntity.ok("성공: 성공적으로 탈퇴했습니다.");
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @Transactional
    @Override
    public ResponseEntity<String> signUp(UserJoinRequestDto userJoinRequestDto) {  // save a user
        try {
            userRepository.save(userJoinRequestDto.toEntity());
            return ResponseEntity.ok("성공: 회원가입이 성공적으로 완료되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @Override
    public boolean signIn(UserAuthenticationRequestDto userAuthenticationRequestDto, HttpServletResponse response) {
        UserEntity user = userRepository.findByEmailAndPassword(userAuthenticationRequestDto.getEmail(), userAuthenticationRequestDto.getPassword());
        if (user != null) {
            response.setHeader("Authorization", jwtProvider.createAccessToken(userAuthenticationRequestDto.getEmail()));
            return true;
        } else {
            response.setHeader("Authorization", "user not exists");
            return false;
        }
    }
}

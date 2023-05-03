package com.example.cloneboard.controller.users;

import com.example.cloneboard.dto.users.UserAuthenticationRequestDto;
import com.example.cloneboard.dto.users.UserJoinRequestDto;
import com.example.cloneboard.service.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

// Restfull 하게 patch, delete등도 사용함. 실무에선 get, post 이외의 것들은 잘 안쓴다고 함
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserJoinRequestDto userJoinRequestDto) {
        return userService.signUp(userJoinRequestDto);
    }

    @DeleteMapping
    // delete는 requestheader에 정보 받아오기, body는 delete메서드에서 body에 정보 담겨서 오면 reject해버리는 서버가 많다고 함.  https://humblego.tistory.com/18
    public ResponseEntity<String> delete(@RequestHeader(value = "email") String email) {
        return userService.delete(email);
    }

    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody UserAuthenticationRequestDto userAuthenticationRequestDto, HttpServletResponse response) {
        if (userService.signIn(userAuthenticationRequestDto, response))
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
}

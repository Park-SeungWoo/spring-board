package com.example.cloneboard.controller.users;

import com.example.auth.dto.users.UserAuthenticationRequestDto;
import com.example.auth.dto.users.UserAuthorizedDto;
import com.example.cloneboard.dto.users.UserJoinRequestDto;
import com.example.cloneboard.dto.users.UserResponseDto;
import com.example.auth.service.users.UserAuthenticationService;
import com.example.cloneboard.service.users.UserService;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.apache.catalina.User;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.Attribute;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

// Restfull 하게 patch, delete등도 사용함. 실무에선 get, post 이외의 것들은 잘 안쓴다고 함
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserAuthenticationService userAuthenticationService;

    @PostMapping
    public ResponseEntity<String> joinUser(@RequestBody UserJoinRequestDto userJoinRequestDto) {
        return userService.join(userJoinRequestDto);
    }

    @GetMapping("/{email}")
    public UserResponseDto findOne(@PathVariable("email") String email) {
        return userService.findOne(email);
    }

    @DeleteMapping
    // delete는 requestheader에 정보 받아오기, body는 delete메서드에서 body에 정보 담겨서 오면 reject해버리는 서버가 많다고 함.  https://humblego.tistory.com/18
    public ResponseEntity<String> delete(@RequestHeader(value = "email") String email,
                                         ServletRequest req) {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        UserAuthorizedDto authorizedUser = (UserAuthorizedDto) httpReq.getAttribute("userStatus");
        return userService.delete(email, authorizedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserAuthenticationRequestDto userAuthenticationRequestDto) {
        return userAuthenticationService.userAuthentication(userAuthenticationRequestDto);
    }
}

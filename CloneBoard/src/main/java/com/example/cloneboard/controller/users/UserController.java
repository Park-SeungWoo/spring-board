package com.example.cloneboard.controller.users;

import com.example.cloneboard.dto.users.UserJoinRequestDto;
import com.example.cloneboard.dto.users.UserResponseDto;
import com.example.cloneboard.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// Restfull 하게 patch, delete등도 사용함. 실무에선 get, post 이외의 것들은 잘 안쓴다고 함
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public Long joinUser(@RequestBody UserJoinRequestDto userJoinRequestDto){
        return userService.join(userJoinRequestDto);
    }

    @GetMapping("/{email}")
    public UserResponseDto findOne(@PathVariable("email") String email){
        return userService.findOne(email);
    }

    @DeleteMapping  // delete는 requestheader에 정보 받아오기, body는 delete메서드에서 body에 정보 담겨서 오면 reject해버리는 서버가 많다고 함.  https://humblego.tistory.com/18
    public void delete(@RequestHeader(value = "email") String email){
        userService.delete(email);
    }
}

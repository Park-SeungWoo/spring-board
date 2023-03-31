package com.example.cloneboard.dto.users;

import com.example.cloneboard.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
//@Builder  // 모든 field에 대한 builder생성자 자동 생성
public class UserResponseDto {
    private Long id;
    private String email;
    private String password;
    private String nickname;
    private String introduce;

    public UserResponseDto(UserEntity entity){
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.nickname = entity.getNickname();
        this.introduce = entity.getIntroduce();
    }
}

package com.example.cloneboard.dto.users;

import com.example.cloneboard.entity.UserEntity;
import lombok.Getter;

@Getter
public class UserAuthorizedDto {
    private String email;
    private String password;
    private String nickname;
    private String introduce;

    public UserAuthorizedDto(UserEntity userEntity){
        this.email = userEntity.getEmail();
        this.password = userEntity.getPassword();
        this.nickname = userEntity.getNickname();
        this.introduce = userEntity.getIntroduce();
    }
}

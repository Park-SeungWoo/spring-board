package com.example.cloneboard.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="users")
@Getter
@NoArgsConstructor
@ToString  // for tests
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // db한테 id관리 위임하겠다.
    private Long id;

    @Column   // nullable = true is default
    private String email;

    @Column // (length = 20)  // field의 가용 길이 설정 가능
    private String password;
    @Column
    private String nickname;
    @Column
    private String introduce;

    @Builder
    public UserEntity(String email, String password, String nickname, String introduce){
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.introduce = introduce;
    }
}

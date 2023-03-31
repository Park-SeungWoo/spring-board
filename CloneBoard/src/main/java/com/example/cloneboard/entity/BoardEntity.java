package com.example.cloneboard.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "boards")  // naming rules
@Getter
@NoArgsConstructor
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // db한테 id관리(pk) 위임하겠다. (mysql은 이거, 다른 것들은 sequence등으로 해야 정확한 쿼리 넣음)
    private Long id;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private String nickname;

    @Builder
    public BoardEntity(String title, String content, String nickname){
        this.title = title;
        this.content = content;
        this.nickname = nickname;
    }
}
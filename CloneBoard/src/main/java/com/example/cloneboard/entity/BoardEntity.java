package com.example.cloneboard.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "boards")  // naming rules
@Getter
@DynamicUpdate  // dirty checking으로 update query 생성 시 변경된 부분만 변경하도록 함
@Builder
@NoArgsConstructor
@AllArgsConstructor  // 모든 필드 받는 생성자
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

    public void changeTitleAndContent(String title, String content){  // 1차 캐싱(영속성 컨텍스트)되어있는곳에서 같은 데이터 찾아서 변화하면 트랜잭션 끝나고 db자동 반영
        this.title = title != null ? title : this.title;
        this.content = content != null ? content : this.content;
    }
}

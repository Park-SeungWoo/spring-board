package com.example.cloneboard.entity;

import com.example.enums.UserRole;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name="users")
@DynamicUpdate
@Getter
@NoArgsConstructor
@ToString  // for tests
@Builder
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // db한테 id관리 위임하겠다.
    private Long id;
    @Enumerated(EnumType.STRING)  // save type name in db (USER, ADMIN)
    @Column
    private UserRole userRole;
    @Column   // nullable = true is default
    private String email;
    @Column // (length = 20)  // field의 가용 길이 설정 가능
    private String password;
    @Column
    private String nickname;
    @Column
    private String introduce;
    @Column(name = "post_sequence")
    private Long postSequence;

    public void addPostSequence(){
        this.postSequence++;
    }
}

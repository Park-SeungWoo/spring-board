package com.deep.park.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//@Getter @Setter
@Entity
@Table(name = "member")  // not necessary
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // primary key로 등록 identity는 mysql에서 많이 쓰는 방법으로 기본키 생성을 db에게 위임한다는 뜻.
    private Long id;
    private String name;

    private String pw;

    public void setName(String name) {
        this.name = name;
    }

    public void setPw(String pw){
        this.pw = pw;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPw() {
        return this.pw;
    }
}

package com.deep.park.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//@Getter @Setter
@Entity
@Table(name="member")  // not necessary
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }
}

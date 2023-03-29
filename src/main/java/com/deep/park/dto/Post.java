package com.deep.park.dto;

import javax.persistence.*;

@Entity
@Table(name="post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="creator_id")
    private Long creatorId;
    private String title;
    private String content;

    public void setCreatorId(Long creator) {
        this.creatorId = creator;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return this.id;
    }

    public Long getCreatorId() {
        return this.creatorId;
    }

    public String getTitle(){
        return this.title;
    }

    public String getContent() {
        return this.content;
    }
}

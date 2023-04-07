package com.example.cloneboard.dto.boards;

import com.example.cloneboard.entity.BoardEntity;
import lombok.Getter;

@Getter
//@Data // getter, setter automatically add
public class BoardResponseDto {
    private Long postId;
    private String title;
    private String content;
    private String nickname;

    public BoardResponseDto(BoardEntity entity){
        this.postId = entity.getPostId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.nickname = entity.getNickname();
    }
}

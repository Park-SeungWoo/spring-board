package com.example.cloneboard.dto.boards;

import com.example.cloneboard.entity.BoardEntity;
import lombok.Getter;

@Getter
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
    private String nickname;

    public BoardResponseDto(BoardEntity entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.nickname = entity.getNickname();
    }
}

package com.example.cloneboard.dto.boards;

import com.example.cloneboard.entity.BoardEntity;
import lombok.Data;
import lombok.Getter;

@Getter
//@Data // getter, setter automatically add
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
